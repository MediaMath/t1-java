/*******************************************************************************
 * Copyright 2016 MediaMath
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.mediamath.terminalone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.mediamath.terminalone.QueryCriteria.CreativeType;
import com.mediamath.terminalone.exceptions.ClientException;
import com.mediamath.terminalone.exceptions.ParseException;
import com.mediamath.terminalone.models.Campaign;
import com.mediamath.terminalone.models.Currency;
import com.mediamath.terminalone.models.Data;
import com.mediamath.terminalone.models.JsonPostErrorResponse;
import com.mediamath.terminalone.models.JsonResponse;
import com.mediamath.terminalone.models.OAuthResponse;
import com.mediamath.terminalone.models.Strategy;
import com.mediamath.terminalone.models.StrategyConcept;
import com.mediamath.terminalone.models.StrategyDayPart;
import com.mediamath.terminalone.models.T1Entity;
import com.mediamath.terminalone.models.T1File;
import com.mediamath.terminalone.models.T1User;
import com.mediamath.terminalone.models.TOneASCreativeAssetsApprove;
import com.mediamath.terminalone.models.TOneASCreativeAssetsUpload;
import com.mediamath.terminalone.models.TPASCreativeBatchApprove;
import com.mediamath.terminalone.models.TPASCreativeUpload;
import com.mediamath.terminalone.models.User;
import com.mediamath.terminalone.models.VendorContract;
import com.mediamath.terminalone.models.VideoCreative;
import com.mediamath.terminalone.models.VideoCreativeResponse;
import com.mediamath.terminalone.models.VideoCreativeUploadResponse;
import com.mediamath.terminalone.models.VideoCreativeUploadStatus;
import com.mediamath.terminalone.models.ZipCodes;
import com.mediamath.terminalone.models.ZipCodesJsonResponse;
import com.mediamath.terminalone.models.reporting.ReportValidationResponse;
import com.mediamath.terminalone.models.reporting.Reports;
import com.mediamath.terminalone.models.reporting.meta.MetaData;
import com.mediamath.terminalone.service.GetService;
import com.mediamath.terminalone.service.PostService;
import com.mediamath.terminalone.service.ReportService;
import com.mediamath.terminalone.service.T1Service;
import com.mediamath.terminalone.utils.Constants;
import com.mediamath.terminalone.utils.T1JsonToObjParser;
import com.mediamath.terminalone.utils.Utility;

/**
 * Handles the authentication, session, entity retrieval, creation etc.
 *
 */
public class TerminalOne {

	private static final String ENTITY_TYPE = "entity_type";

	private static final String STRATEGY = "strategy";

	private static final String CONTEXT = "context";

	private static final String UNABLE_TO_PARSE_THE_RESPONSE = "Unable to parse the response";

	private static final String CREATIVES = "creatives";

	private static final String TOKEN = "token";

	private static final String UNABLE_TO_GET_O_AUTH_TOKEN = "Unable to get OAuth token: ";

	private static final String LOGIN = "login";

	private static final Logger logger = LoggerFactory.getLogger(TerminalOne.class);

	private Connection connection = null;

	private T1Service tOneService = null;

	private PostService postService = null;

	private GetService getService = null;

	private ReportService reportService = null;

	/*
	 * maintains user session
	 */
	private T1User user = null;

	private boolean authenticated = false;

	/**
	 * Constructor.
	 */
	public TerminalOne() {
		logger.info("Loading Environment - setting up connection.");
		connection = new Connection();
		tOneService = new T1Service();
	}

	/**
	 * Constructor, tries to connect with the credentials provided.
	 * 
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 * 
	 */
	public TerminalOne(String username, String password, String apiKey) throws ClientException {
		this();
		validateLoginCredentials(username, password, apiKey);
		login(username, password, apiKey);
	}

	private void login(String username, String password, String apiKey) throws ClientException {
		logger.info("Authenticating.");
		Form form = tOneService.getLoginFormData(username, password, apiKey);
		String url = tOneService.constructUrl(new StringBuilder(LOGIN), LOGIN);
		Response loginResponse = connection.post(url, form);
		parseLoginError(loginResponse);
		String response = loginResponse.readEntity(String.class);
		setUserSessionInfo(response);
		setServices();

		if (checkUserAndUserData() && checkUserSession() && checkUserSessionID()) {
			this.authenticated = true;
		}
	}

	private void setServices() {
		postService = new PostService(this.connection, this.user, this.tOneService);
		getService = new GetService();
		reportService = new ReportService();
	}

	private boolean checkUserSessionID() {
		return this.getUser().getData().getSession().getSessionid() != null
				&& !this.getUser().getData().getSession().getSessionid().isEmpty();
	}

	private boolean checkUserSession() {
		return this.getUser().getData().getSession() != null;
	}

	private boolean checkUserAndUserData() {
		return this.getUser() != null && this.getUser().getData() != null;
	}

	private void parseLoginError(Response response) throws ClientException {
		if (response.getStatus() == 403) {
			logger.error("Authentication Failed: please provide valid credentials for the given environment");
			throw new ClientException(
					"Authentication Failed: please provide valid credentials for the given environment");
		}
	}

	/**
	 * Private method to validate login credentials.
	 * 
	 * @param username
	 *            a valid username is required.
	 * @param password
	 *            a valid password is required.
	 * @param apiKey
	 *            a valid environment api key is required.
	 * @throws ClientException
	 *             exception.
	 */
	private void validateLoginCredentials(String username, String password, String apiKey) throws ClientException {
		if (apiKey == null || apiKey.isEmpty()) {
			logger.error("Please Provide Valid API Key");
			throw new ClientException("Please Provide Valid API Key");
		}

		if (username.isEmpty() || password.isEmpty()) {
			logger.error("Please Provide Valid Username and Password.");
			throw new ClientException("Please Provide Valid Username and Password.");
		}
	}

	/**
	 * Used to authenticate using given credentials.
	 * 
	 * @return boolean
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 */
	public boolean authenticate(String username, String password, String apiKey) throws ClientException {
		validateLoginCredentials(username, password, apiKey);
		login(username, password, apiKey);
		return isAuthenticated();
	}

	/**
	 * used to authenticate using given credentials.
	 * 
	 * @return boolean
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 */
	public boolean authenticate(String username, String password, String clientId, String clientSecret)
			throws ClientException {

		logger.info("Authenticating.");

		user = new T1User();
		OAuthResponse oauthResponse = this.getOAuthToken(username, password, clientId, clientSecret);
		if (oauthResponse != null && oauthResponse.getAccessToken() != null) {
			user.setToken(oauthResponse.getAccessToken());
		}

		setServices();

		if (this.getUser() != null && this.getUser().getToken() != null && !this.getUser().getToken().isEmpty()) {
			this.authenticated = true;
		}

		return isAuthenticated();
	}

	/**
	 * Gets OAuthTokens.
	 * 
	 * @param code
	 *            valid code is required.
	 * @param apiKey
	 *            valid api key is required.
	 * @param secret
	 *            valid secret key is required.
	 * @param redirectUri
	 *            a valid redirect URI is required.
	 * @return OAuthJSONAccessTokenResponse object.
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 */
	public OAuthResponse getOAuthToken(String username, String password, String clientId, String clientSecret)
			throws ClientException {
		String oauthTokenUrl = tOneService.constructOauthUrl(new StringBuilder(TOKEN));
		logger.info("Authenticating via OAuth Auth0.");
		Form form = tOneService.getOAuthFormData(username, password, clientId, clientSecret);
		Response oauthResponse = connection.post(oauthTokenUrl, form, null);
		parseLoginError(oauthResponse);
		String response = oauthResponse.readEntity(String.class);
		return parseOAuthResponse(response);
	}

	private OAuthResponse parseOAuthResponse(String response) {
		Gson gson = new Gson();
		OAuthResponse resp;
		Type responseTypeInfo = new TypeToken<OAuthResponse>() {
		}.getType();
		resp = gson.fromJson(response, responseTypeInfo);
		return resp;
	}

	/**
	 * Method to refresh oauth token.
	 * 
	 * @param refreshToken
	 *            requires a refresh token.
	 * 
	 * @param apiKey
	 *            requires valid api key.
	 * 
	 * @param secret
	 *            requires a valid secret.
	 * 
	 * @return OAuthJSONAccessTokenResponse object.
	 * 
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 */
	public OAuthJSONAccessTokenResponse refreshOauthToken(String refreshToken, String apiKey, String secret)
			throws ClientException {
		String oauthTokenUrl = tOneService.constructOauthUrl(new StringBuilder(TOKEN));
		try {
			OAuthClientRequest request = OAuthClientRequest.tokenLocation(oauthTokenUrl)
					.setGrantType(GrantType.REFRESH_TOKEN).setClientId(apiKey).setClientSecret(secret)
					.setRefreshToken(refreshToken).buildQueryMessage();
			OAuthClient oauthClient = new OAuthClient(new URLConnectionClient());
			return oauthClient.accessToken(request, OAuthJSONAccessTokenResponse.class);
		} catch (OAuthSystemException oauthSystemException) {
			Utility.logStackTrace(oauthSystemException);
			throw new ClientException(UNABLE_TO_GET_O_AUTH_TOKEN + oauthSystemException.getMessage());
		} catch (OAuthProblemException oauthProblemException) {
			Utility.logStackTrace(oauthProblemException);
			throw new ClientException(UNABLE_TO_GET_O_AUTH_TOKEN + oauthProblemException.getMessage());
		}
	}

	/**
	 * Maintains user session information.
	 * 
	 */
	private void setUserSessionInfo(String response) {
		Gson gson = new Gson();
		T1User resp;
		Type responseTypeInfo = new TypeToken<T1User>() {
		}.getType();
		resp = gson.fromJson(response, responseTypeInfo);
		this.setUser(resp);
	}

	public T1Entity save(T1Entity entity) throws ClientException, ParseException {
		if (entity == null)
			return null;

		if (!isAuthenticated())
			return null;

		return postService.save(entity);
	}

	/**
	 * Saves Strategy entity.
	 * 
	 * @param entity
	 *            expects Strategy entity.
	 * @return Strategy object.
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 * @throws ParseException
	 *             a parse exception is thrown when the response cannot be
	 *             parsed.
	 */
	public Strategy save(Strategy entity) throws ClientException, ParseException {
		Strategy strategy = null;
		if (isAuthenticated()) {
			strategy = postService.save(entity);
		}
		return strategy;
	}

	/**
	 * Saves ZipCodes against Strategy entity.
	 * 
	 * @param entity
	 *            expects ZipCodes entity.
	 * @return ZipCodesJsonResponse object.
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 * @throws ParseException
	 *             a parse exception is thrown when the response cannot be
	 *             parsed.
	 */
	public ZipCodesJsonResponse save(ZipCodes entity) throws ClientException, ParseException {
		ZipCodesJsonResponse response = null;
		if (isAuthenticated()) {
			response = postService.save(entity);
		}
		return response;
	}

	/**
	 * Saves List of Strategy entity ie. bulk strategy update
	 * 
	 * @param strategyList
	 * @return List<Strategy>
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public List<Strategy> save(List<Strategy> strategyList) throws InterruptedException, ExecutionException {
		List<Strategy> finList = new ArrayList<>();
		ExecutorService exec = Executors.newFixedThreadPool(20);
		List<FutureTask<Strategy>> taskList = new ArrayList<>();
		if (isAuthenticated()) {
			for (final Strategy str : strategyList) {

				FutureTask<Strategy> futureTask = new FutureTask<>(new Callable<Strategy>() {
					@Override
					public Strategy call() throws ClientException, ParseException {
						return postService.save(str);
					}
				});
				taskList.add(futureTask);
				exec.execute(futureTask);
			}

			for (FutureTask<Strategy> futureTsk : taskList) {
				finList.add(futureTsk.get());
			}
			exec.shutdown();
		}
		return finList;
	}

	/**
	 * Copies Bulk Strategy entities.
	 * 
	 * @param entity
	 *            expects Strategy entity.
	 * @return Strategy object.
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 * @throws ParseException
	 *             a parse exception is thrown when the response cannot be
	 *             parsed.
	 */
	public JsonResponse<? extends T1Entity> bulkCopy(Strategy entity) throws ClientException, ParseException {
		JsonResponse<? extends T1Entity> jsonResponse = null;
		if (isAuthenticated()) {
			jsonResponse = postService.bulkCopy(entity);
		}
		return jsonResponse;
	}

	/**
	 * Saves Campaign entity.
	 * 
	 * @param entity
	 *            expects Campaign entity.
	 * @return Campaign object.
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 * @throws ParseException
	 *             a parse exception is thrown when the response cannot be
	 *             parsed.
	 */
	public Campaign save(Campaign entity) throws ClientException, ParseException {
		Campaign campaign = null;
		if (isAuthenticated()) {
			campaign = postService.save(entity);
		}
		return campaign;
	}

	/**
	 * Saves User entity and its permissions.
	 * 
	 * @param entity
	 *            expects User entity.
	 * @return User object.
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 * @throws ParseException
	 *             a parse exception is thrown when the response cannot be
	 *             parsed.
	 */
	public User save(User entity) throws ClientException, ParseException {
		User userSaved = null;
		if (isAuthenticated()) {
			userSaved = postService.save(entity);
		}
		return userSaved;
	}

	/**
	 * saves 3pas creative upload file. first call to upload the file. <br>
	 * <br>
	 * example:<br>
	 * <br>
	 * <i>save3pasCreativeUpload("C:\\exampledir1\\exampledir2\\samplefile.txt",
	 * "samplefile","samplefile");</i>
	 * 
	 * 
	 * @param filePath
	 *            a valid filePath is required.
	 * 
	 * @param fileName
	 *            a valid fileName is required.
	 * @param name
	 *            a valid name is required.
	 * 
	 * @return TPASCreativeUpload object.
	 * 
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 * @throws IOException
	 *             a IOException is thrown when the file cannot be uploaded.
	 */
	public TPASCreativeUpload saveTPASCreativeUpload(String filePath, String fileName, String name)
			throws ClientException, IOException {
		TPASCreativeUpload response = null;
		if (isAuthenticated()) {
			response = postService.saveTPASCreativeUpload(filePath, fileName, name);
		}
		return response;
	}

	/**
	 * This API call in the Bulk 3PAS process saves particular creative out of a
	 * given batch to the T1 database. second call to save the 3pas creative
	 * upload
	 * 
	 * @param batchApprove
	 *            requires TPASCreativeBatchApprove entity.
	 * @return JsonResponse<? extends T1Entity> JsonResponse of type T is
	 *         returned.
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 * @throws IOException
	 *             a IOException is thrown when the file cannot be uploaded.
	 * @throws ParseException
	 *             a parse exception is thrown when the response cannot be
	 *             parsed.
	 */
	public JsonResponse<? extends T1Entity> saveTPASCreativeUploadBatch(TPASCreativeBatchApprove batchApprove)
			throws ClientException, IOException, ParseException {
		JsonResponse<? extends T1Entity> finalJsonResponse = null;
		if (isAuthenticated()) {
			finalJsonResponse = postService.saveTPASCreativeUploadBatch(batchApprove);
		}
		return finalJsonResponse;
	}

	/**
	 * saves upload to T1AS. first call to upload the file. <br>
	 * <br>
	 * example: <br>
	 * <br>
	 * <i>saveT1ASCreativeAssetsUpload(
	 * "C:\\exampledir1\\exampledir2\\samplefile.txt",
	 * "samplefile","samplefile");</i>
	 * 
	 * @param filePath
	 *            a valid filePath is required.
	 * 
	 * @param fileName
	 *            a valid fileName is required.
	 * @param name
	 *            a valid name is required.
	 * @return TOneASCreativeAssetsUpload object.
	 * 
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 * 
	 * @throws IOException
	 *             a IOException is thrown when the file cannot be uploaded.
	 */
	public TOneASCreativeAssetsUpload saveTOneASCreativeAssetsUpload(String filePath, String fileName, String name)
			throws ClientException, IOException {
		TOneASCreativeAssetsUpload response = null;
		if (isAuthenticated()) {
			response = postService.saveTOneASCreativeAssets(filePath, fileName, name);
		}
		return response;
	}

	/**
	 * saves HTML5 file upload to T1AS. first call to upload the HTML5 file
	 * along with backup file. <br>
	 * <br>
	 * example: <br>
	 * <br>
	 * <i>saveT1ASCreativeAssetsUpload(List<T1File> fileList);</i>
	 * 
	 * @param fileList
	 *            a valid list of T1File object is required. T1File Object, can
	 *            accept name, filename and filepath
	 * 
	 * @return TOneASCreativeAssetsUpload object.
	 * 
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 * 
	 * @throws IOException
	 *             a IOException is thrown when the file cannot be uploaded.
	 */
	public TOneASCreativeAssetsUpload saveTOneASCreativeAssetsUpload(List<T1File> fileList)
			throws ClientException, IOException {

		TOneASCreativeAssetsUpload response = null;
		if (isAuthenticated()) {
			response = postService.saveTOneASCreativeAssets(fileList);
		}
		return response;
	}

	/**
	 * Second call, Approves the upload done in the first call.
	 * 
	 * @param creativeAssetsApprove
	 *            expects a TOneASCreativeAssetsApprove entity.
	 * @return JsonResponse<? extends T1Entity> JsonResponse of type T is
	 *         returned.
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 * @throws IOException
	 *             an io exception is thrown.
	 */
	public JsonResponse<? extends T1Entity> saveTOneASCreativeAssetsApprove(
			TOneASCreativeAssetsApprove creativeAssetsApprove) throws ClientException, IOException {
		JsonResponse<? extends T1Entity> response = null;
		if (isAuthenticated()) {
			response = postService.saveTOneASCreativeAssetsApprove(creativeAssetsApprove);
		}
		return response;
	}

	/**
	 * First Call to Create a Video Creative.
	 * 
	 * @param videoCreative
	 *            expects a VideoCreative entity.
	 * @return VideoCreativeResponse object.
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 */
	public VideoCreativeResponse saveVideoCreatives(VideoCreative videoCreative) throws ClientException {
		VideoCreativeResponse response = null;
		if (isAuthenticated()) {
			response = postService.save(videoCreative);
		}
		return response;
	}

	/**
	 * second call to upload video creative media using the creativeId.
	 * 
	 * @param filePath
	 *            a valid filePath is required.
	 * @param fileName
	 *            a valid fileName is required.
	 * @param creativeId
	 *            a valid creativeId is required.
	 * @return VideoCreativeResponse object.
	 * 
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 * @throws IOException
	 *             IoException is thrown in case if there is an error while
	 *             uploading a file.
	 * 
	 */
	public VideoCreativeResponse uploadVideoCreative(String filePath, String fileName, String creativeId)
			throws ClientException, IOException {
		VideoCreativeResponse response = null;
		if (isAuthenticated() && checkFilePath(filePath) && checkFileName(fileName) && checkCreativeID(creativeId)) {
			response = postService.uploadVideoCreative(filePath, fileName, creativeId);
		}
		return response;
	}

	/**
	 * TEMPORARY SOLUTION, DO BE DEPRECATED ONCE VIDEO CREATIVE UPLOAD PROBLEM
	 * SOLVES
	 * 
	 * second call to upload video creative media using the creativeId.
	 * 
	 * @param filePath
	 *            a valid filePath is required.
	 * @param fileName
	 *            a valid fileName is required.
	 * @param key
	 *            a valid key is required.
	 * @param host
	 *            a valid host is required.
	 * 
	 * @return VideoCreativeResponse object.
	 * 
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 * @throws IOException
	 *             IoException is thrown in case if there is an error while
	 *             uploading a file.
	 * 
	 */
	public VideoCreativeUploadResponse videoCreativeUpload(String filePath, String fileName, String key, String host)
			throws ClientException, IOException {
		VideoCreativeUploadResponse response = null;
		if (isAuthenticated() && checkFilePath(filePath) && checkFileName(fileName) && checkKey(key)) {
			response = postService.videoCreativeUpload(filePath, fileName, key, host);
		}
		return response;
	}

	private boolean checkCreativeID(String creativeId) {
		return creativeId != null && !creativeId.isEmpty();
	}

	private boolean checkFileName(String fileName) {
		return fileName != null && !fileName.isEmpty();
	}

	private boolean checkFilePath(String filePath) {
		return filePath != null && !filePath.isEmpty();
	}

	private boolean checkKey(String key) {
		return key != null && !key.isEmpty();
	}

	/**
	 * Check the status of the uploaded video creative.
	 * 
	 * @param creativeId
	 *            a valid creativeId is required.
	 * @return VideoCreativeUploadStatus object.
	 * 
	 */
	public VideoCreativeUploadStatus getVideoCreativeUploadStatus(String creativeId) {
		VideoCreativeUploadStatus response = null;
		if (isAuthenticated() && checkCreativeID(creativeId)) {
			response = postService.getVideoCreativeUploadStatus(creativeId);
		}
		return response;
	}

	/**
	 * Get.
	 * 
	 * @param query
	 *            expects a QueryCriteria entity.
	 * 
	 * @return JsonResponse<? extends T1Entity> JsonResponse of type T is
	 *         returned.
	 * 
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 * @throws ParseException
	 *             a parse exception is thrown when the response cannot be
	 *             parsed.
	 */
	public JsonResponse<? extends T1Entity> get(QueryCriteria query) throws ClientException, ParseException {
		StringBuilder path = getService.get(query);
		String finalPath;

		if (query.collection.equals(CREATIVES)
				&& (query.creativeType != null && query.creativeType.equals(CreativeType.video))) {
			finalPath = tOneService.constructVideoCreativeUrl(path);
		} else {
			// If collection=deals then use for media api base
			finalPath = tOneService.constructUrl(path, query.collection);
		}

		String response = this.connection.get(finalPath, this.getUser());

		JsonResponse<? extends T1Entity> jsonResponse;
		// parse the data to entities.
		try {
			jsonResponse = parseGetData(response, query);
		} catch (ParseException parseException) {
			throw new ClientException(UNABLE_TO_PARSE_THE_RESPONSE);
		}

		jsonResponse = checkGetAllResponse(query, response, jsonResponse);

		return jsonResponse;
	}

	/**
	 * If get_all is true, then fetch all data from server and add to existing
	 * records.
	 * 
	 * @param query
	 * @param response
	 * @param jsonResponse
	 * @return
	 * @throws ClientException
	 */
	private JsonResponse<? extends T1Entity> checkGetAllResponse(QueryCriteria query, String response,
			JsonResponse<? extends T1Entity> jsonResponse) throws ClientException {

		// Using NEXT_PAGE param of meta from each call, in case of get_all
		
		JsonResponse<? extends T1Entity> jsonResponseData = jsonResponse;

		if (jsonResponseData != null && query.getAll) {
			JsonArray mainData = extractData(response);
			String lastCallResponse = null;
			// loop till next_page !=null
			while (jsonResponseData != null && jsonResponseData.getMeta() != null && jsonResponseData.getMeta().getNextPage() != null) {
				String pageResponse = this.connection.get(jsonResponseData.getMeta().getNextPage(), this.getUser());

				JsonArray data = extractData(pageResponse);
				mainData.addAll(data);

				JsonResponse<? extends T1Entity> pageJsonResponse1;
				// parse the data to entities.
				try {
					pageJsonResponse1 = parseGetData(pageResponse, query);
				} catch (ParseException parseException) {
					throw new ClientException(UNABLE_TO_PARSE_THE_RESPONSE);
				}

				jsonResponseData = pageJsonResponse1;
				lastCallResponse = pageResponse;
			}

			JsonParser parser = new JsonParser();
			JsonObject obj = parser.parse(response).getAsJsonObject();
			obj.add("data", mainData);
			// add last call meta to response string
			if (lastCallResponse != null) {
				obj.add("meta", new JsonParser().parse(lastCallResponse).getAsJsonObject().get("meta"));
			}
			jsonResponseData = null;

			try {
				jsonResponseData = parseGetData(obj.toString(), query);
			} catch (ParseException parseException) {
				throw new ClientException(UNABLE_TO_PARSE_THE_RESPONSE);
			}
		}
		return jsonResponseData;
	}

	/**
	 * Extract Data from Json Response String
	 * 
	 * @param response
	 * @return
	 */
	private JsonArray extractData(String response) {
		JsonParser parser = new JsonParser();
		JsonObject obj = parser.parse(response).getAsJsonObject();
		return obj.get("data").getAsJsonArray();
	}

	/**
	 * Updates all strategies from any campaign using provided strategy updater
	 * object
	 * 
	 * @param strategyList
	 * @param updator
	 * @return
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public List<Strategy> bulkUpdate(List<Strategy> strategyList, Strategy updator) throws InterruptedException, ExecutionException {
		String overridedMethods = "getForm,getUri,clone,getId,getEntityname";
		List<Strategy> updatorStrategyList = new ArrayList<>();
		for (Strategy strategyRecord : strategyList) {
			for (Method method : updator.getClass().getDeclaredMethods()) {
				if (Modifier.isPublic(method.getModifiers()) && method.getParameterTypes().length == 0
						&& method.getReturnType() != void.class
						&& (method.getName().startsWith("get") || method.getName().startsWith("is"))) {
					Object value = null;
					try {
						value = method.invoke(updator);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						 logger.info(CONTEXT, e);
					}
					if (method.getName().startsWith("get") && value != null
							&& overridedMethods.indexOf(method.getName()) == -1) {
						String methodName = "set" + method.getName().substring(3);
						invokeStrategyMethod(strategyRecord, method, value, methodName);
					}
					if (method.getName().startsWith("is") && (value != null && !(value.equals(Boolean.FALSE)))) {
						String methodName = "set" + method.getName().substring(2);
						invokeStrategyMethod(strategyRecord, method, value, methodName);
					}
				} // if
			} // for method list
			updatorStrategyList.add(strategyRecord);
		} // for strategy list

		return this.save(updatorStrategyList);
	}

	/**
	 * @param strategyRecord
	 * @param method
	 * @param value
	 * @param methodName
	 */
	private void invokeStrategyMethod(Strategy strategyRecord, Method method, Object value, String methodName) {
		Method method1;
		try {
			method1 = strategyRecord.getClass().getMethod(methodName, method.getReturnType());
			method1.invoke(strategyRecord, value);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			logger.info(CONTEXT, e);
		}
	}

	/**
	 * getSiteListData for SiteList Download
	 * 
	 * @param query
	 *            expects a QueryCriteria entity.
	 * 
	 * @return BufferedReader.
	 * 
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 * @throws ParseException
	 *             a parse exception is thrown when the response cannot be
	 *             parsed.
	 */
	public BufferedReader getSiteListData(QueryCriteria query) throws ClientException, ParseException {

		StringBuilder path = getService.get(query);

		// get the data from t1 servers.
		String finalPath = tOneService.constructUrl(path, query.collection);
		Response response = connection.getReportData(finalPath, this.getUser());
		BufferedReader reader = null;

		if (response.getStatus() != 200) {
			JsonPostErrorResponse jsonPostErrorResponse = getService
					.jsonGetErrorResponseParser(response.readEntity(String.class));
			if (jsonPostErrorResponse != null) {
				postService.throwExceptions(jsonPostErrorResponse);
			}
		} else if ("text".equalsIgnoreCase(response.getMediaType().getType())
				&& "csv".equalsIgnoreCase(response.getMediaType().getSubtype()) && response.getStatus() == 200) {
			InputStream responseStream = response.readEntity(InputStream.class);
			reader = new BufferedReader(new InputStreamReader(responseStream));
		}
		return reader;
	}

	/**
	 * GET meta of all the reports available.
	 * 
	 * @return JsonResponse<? extends T1Entity> JsonResponse of type T is
	 *         returned.
	 */
	public JsonResponse<? extends T1Entity> getMeta() {
		JsonResponse<? extends T1Entity> jsonResponse;
		StringBuilder path = reportService.getMetaUri();
		String finalPath = tOneService.constructReportingUrl(path);
		String response = this.connection.get(finalPath, this.getUser());
		jsonResponse = reportService.parseMetaResponse(response);
		return jsonResponse;
	}

	/**
	 * GET meta data for a specific report.
	 * 
	 * @param report
	 *            expects a Reports name.
	 * 
	 * @return MetaData object.
	 */
	public MetaData getReportsMeta(Reports report) {
		StringBuilder reportName = new StringBuilder(report.getReportNameWithMeta());
		String finalPath = tOneService.constructReportingUrl(reportName);
		String response = this.connection.get(finalPath, this.getUser());

		return reportService.parseReportMetaResponse(response);
	}

	/**
	 * GET a specific report.
	 * 
	 * @param report
	 *            expects a report name from the Reports enum.
	 * 
	 * @param criteria
	 *            expects ReportCriteria entity.
	 *
	 * @return reader BufferedReader is returned.
	 * 
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 *
	 * 
	 */
	public BufferedReader getReport(Reports report, ReportCriteria criteria) throws ClientException {
		criteria.setReportName(report.getReportName());
		StringBuilder path;
		path = reportService.getReportUri(criteria);
		String finalPath = tOneService.constructReportingUrl(path);

		return reportService.getReportData(report, finalPath, connection, user);
	}

	/**
	 * Validates a given Report.
	 * 
	 * @param report
	 *            expects a valid report name from Reports enum.
	 * 
	 * @param criteria
	 *            expects a valid ReportCriteria entity.
	 * 
	 * @return ReportValidationResponse object.
	 * 
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 * @throws ParseException
	 *             a parse exception is thrown when the response cannot be
	 *             parsed.
	 */
	public ReportValidationResponse validateReport(Reports report, ReportCriteria criteria) throws ClientException {
		criteria.setReportName(report.getReportName() + "/validate");
		StringBuilder path;
		path = reportService.getReportUri(criteria);
		String finalPath = tOneService.constructReportingUrl(path);

		return reportService.validateReportData(report, finalPath, connection, user);
	}

	/**
	 * parses the response to objects.
	 * 
	 * @param response
	 *            a valid JSON response string is required.
	 * @param QueryCriteria
	 *            a valid query object is required.
	 * @return JsonResponse<? extends T1Entity> JsonResponse of type T is
	 *         returned.
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 * @throws ParseException
	 *             a parse exception is thrown when the response cannot be
	 *             parsed.
	 */
	private JsonResponse<? extends T1Entity> parseGetData(String response, QueryCriteria query)
			throws ParseException, ClientException {
		T1JsonToObjParser parser = new T1JsonToObjParser();
		JsonResponse<? extends T1Entity> finalJsonResponse = null;
		JsonPostErrorResponse jsonPostErrorResponse;

		// check whether error present
		jsonPostErrorResponse = getService.jsonGetErrorResponseParser(response);

		if (jsonPostErrorResponse != null) {
			postService.throwExceptions(jsonPostErrorResponse);
		}

		JsonElement element = parser.getDataFromResponse(response);
		JsonParser parsers = new JsonParser();
		JsonObject responseObject = parsers.parse(new StringReader(response)).getAsJsonObject();
		String modifiedJsonString = null;

		if (element != null && element.isJsonArray()) {

			JsonArray dataList = element.getAsJsonArray();

			if (dataList!=null && dataList.size() <= 0 && query.collection != null) {
					finalJsonResponse = parser.parseJsonToObj(response,
							Constants.getListoFEntityType.get(query.collection.toLowerCase()));
					return finalJsonResponse;
			}

			JsonElement data = ((dataList!=null) ? dataList.get(0) : null);

			if (data == null) {
				return null;
			}

			JsonObject dataObj = data.getAsJsonObject();
			//Check whether strategy have values in old format, if yes change them to current format
			if(dataObj.get(ENTITY_TYPE).equals(STRATEGY)){
				modifiedJsonString = checkAndFixStrategyJson(responseObject);
			}
			
			if(modifiedJsonString==null){
				modifiedJsonString=response;
			}
			if (dataObj == null) {
				return null;
			}

			JsonElement entityTypeElem = dataObj.get(ENTITY_TYPE);

			if (entityTypeElem == null) {
				return null;
			}

			String entityType = entityTypeElem.getAsString();

			if (entityType == null || entityType.isEmpty()) {
				return null;
			}

			if (Constants.getListoFEntityType.get(entityType) == null) {
				return null;
			}

			finalJsonResponse = parser.parseJsonToObj(modifiedJsonString.replaceAll("\\\\", ""), Constants.getListoFEntityType.get(entityType));

		}

		if (element != null && element.isJsonObject()) {

			JsonObject obj = element.getAsJsonObject();
			JsonElement entityTypeElement = obj.get(ENTITY_TYPE);
			if(entityTypeElement!=null && entityTypeElement.getAsString().equals(STRATEGY)){
				modifiedJsonString = checkAndFixStrategyJson(responseObject);
			}
			if(modifiedJsonString==null){
				modifiedJsonString=response;
			}
			
			
			String entityType = (entityTypeElement != null) ? entityTypeElement.getAsString() : null;

			if (entityType != null && !"".equalsIgnoreCase(entityType)) {
				finalJsonResponse = parser.parseJsonToObj(modifiedJsonString.replaceAll("\\\\", ""), Constants.getEntityType.get(entityType));
			} else {
				finalJsonResponse = parser.parseJsonToObj(modifiedJsonString.replaceAll("\\\\", ""), new TypeToken<JsonResponse<Data>>() {
				}.getType());
			}
		}

		if (element == null) {
			if (query.collection == null) {
				return null;
			}
			if (query.collection.equals(CREATIVES)
					&& (query.creativeType != null && query.creativeType.equals(CreativeType.video))) {
				String finResponse = "{\"data\":" + response + "}";
				finalJsonResponse = parser.parseJsonToObj(finResponse,
						new TypeToken<JsonResponse<VideoCreativeResponse>>() {
						}.getType());
			} else {
				finalJsonResponse = parser.parseJsonToObj(response,
						Constants.getEntityType.get(query.collection.toLowerCase()));
			}

			if (finalJsonResponse != null && !(query.collection.equals(CREATIVES)
					&& (query.creativeType != null && query.creativeType.equals(CreativeType.video)))) {
				finalJsonResponse.setData(null);
			}
		}

		return finalJsonResponse;
	}

	@SuppressWarnings("rawtypes")
	private String checkAndFixStrategyJson(JsonObject responseObject) {
		Gson gson = new Gson();
		JsonObject data = responseObject.getAsJsonObject("data");
		
		Class strategyClass = Strategy.class;
		Field[]fieldList = strategyClass.getDeclaredFields();
		
		for(Field field: fieldList){
			if(field.getType().isInstance(new ArrayList<Currency>()) 
			&& ("java.util.ArrayList<com.mediamath.terminalone.models.Currency>").equals(field.getGenericType().toString())
			&& (data.get(field.getName())!=null && data.get(field.getName()).isJsonPrimitive()))
			{
					float fieldValue = data.get(field.getName()).getAsFloat();
					responseObject.getAsJsonObject("data").remove(field.getName());
					ArrayList<Currency> egvList = new ArrayList<>();
					Currency curr = new Currency();
					curr.setValue(fieldValue);
					if(data.get("currency_code")!=null){
						curr.setCurrencyCode(data.get("currency_code").getAsString());
					}
					egvList.add(curr);
					JsonParser parser = new JsonParser();
					JsonElement je = parser.parse(gson.toJson(egvList,ArrayList.class));
					responseObject.getAsJsonObject("data").add(field.getName(),je);
					data = responseObject.getAsJsonObject("data");
			}
		}
		
		return responseObject.toString();
	}

	/**
	 * Find method, alternative to get method.
	 * 
	 * @param query
	 *            a valid QueryCriteria entity is required.
	 * 
	 * @return JsonResponse<? extends T1Entity> JsonResponse of type T is
	 *         returned.
	 * 
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 * @throws ParseException
	 *             a parse exception is thrown when the response cannot be
	 *             parsed.
	 */
	public JsonResponse<? extends T1Entity> find(QueryCriteria query) throws ClientException, ParseException {
		String paramVal = getService.find(query);
		query.query = paramVal;
		return this.get(query);

	}

	/**
	 * deletes a Vendor Contracts entity.
	 * 
	 * @param Contracts
	 *            expects a Vendor Contracts entity
	 * @return JsonResponse<? extends T1Entity> returns a JsonResponse of type T
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 * @throws ParseException
	 *             a parse exception is thrown when the response cannot be
	 *             parsed.
	 */
	public JsonResponse<? extends T1Entity> delete(VendorContract vc) throws ClientException, ParseException {
		JsonResponse<? extends T1Entity> response = null;
		if (isAuthenticated()) {
			response = postService.delete(vc);
		}
		return response;
	}

	/**
	 * deletes a StrategyConcept entity.
	 * 
	 * @param strategyConcept
	 *            expects a StrategyConcept entity
	 * @return JsonResponse<? extends T1Entity> returns a JsonResponse of type T
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 * @throws ParseException
	 *             a parse exception is thrown when the response cannot be
	 *             parsed.
	 */
	public JsonResponse<? extends T1Entity> delete(StrategyConcept strategyConcept)
			throws ClientException, ParseException {
		JsonResponse<? extends T1Entity> response = null;
		if (isAuthenticated()) {
			response = postService.delete(strategyConcept);
		}
		return response;
	}

	/**
	 * deletes the StrategyDayPart entity.
	 * 
	 * @param strategyDayPart
	 *            expects a StrategyDayPart entity.
	 * @return JsonResponse<? extends T1Entity> returns Json Response of type T
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 * @throws ParseException
	 *             a parse exception is thrown when the response cannot be
	 *             parsed.
	 */
	public JsonResponse<? extends T1Entity> delete(StrategyDayPart strategyDayPart)
			throws ClientException, ParseException {
		JsonResponse<? extends T1Entity> response = null;
		if (isAuthenticated()) {
			response = postService.delete(strategyDayPart);
		}
		return response;
	}

	/**
	 * checks if the user is authenticated.
	 * 
	 * @return boolean
	 */
	public boolean isAuthenticated() {
		return authenticated;
	}

	public T1User getUser() {
		return user;
	}

	public void setUser(T1User user) {
		this.user = user;
	}

	public void setAuthenticated(boolean bool) {
		this.authenticated = bool;

	}
}
