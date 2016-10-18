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
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mediamath.terminalone.exceptions.ClientException;
import com.mediamath.terminalone.exceptions.ParseException;
import com.mediamath.terminalone.models.Advertiser;
import com.mediamath.terminalone.models.Agency;
import com.mediamath.terminalone.models.AtomicCreative;
import com.mediamath.terminalone.models.Campaign;
import com.mediamath.terminalone.models.Concept;
import com.mediamath.terminalone.models.Data;
import com.mediamath.terminalone.models.JsonPostErrorResponse;
import com.mediamath.terminalone.models.JsonResponse;
import com.mediamath.terminalone.models.Organization;
import com.mediamath.terminalone.models.Pixel;
import com.mediamath.terminalone.models.Strategy;
import com.mediamath.terminalone.models.StrategyConcept;
import com.mediamath.terminalone.models.StrategyDayPart;
import com.mediamath.terminalone.models.StrategySupplySource;
import com.mediamath.terminalone.models.T1Entity;
import com.mediamath.terminalone.models.T1User;
import com.mediamath.terminalone.models.TOneASCreativeAssetsApprove;
import com.mediamath.terminalone.models.TOneASCreativeAssetsUpload;
import com.mediamath.terminalone.models.TPASCreativeBatchApprove;
import com.mediamath.terminalone.models.TPASCreativeUpload;
import com.mediamath.terminalone.models.VideoCreative;
import com.mediamath.terminalone.models.VideoCreativeResponse;
import com.mediamath.terminalone.models.VideoCreativeUploadStatus;
import com.mediamath.terminalone.models.reporting.ReportValidationResponse;
import com.mediamath.terminalone.models.reporting.Reports;
import com.mediamath.terminalone.models.reporting.meta.MetaData;
import com.mediamath.terminalone.service.GetService;
import com.mediamath.terminalone.service.PostService;
import com.mediamath.terminalone.service.ReportService;
import com.mediamath.terminalone.service.T1Service;
import com.mediamath.terminalone.utils.Constants;
import com.mediamath.terminalone.utils.T1JsonToObjParser;





/**
 * handles the authentication, session, entity retrieval, creation etc.
 *
 */
public class TerminalOne {

  private static final Logger logger = LoggerFactory.getLogger(TerminalOne.class);

  public Connection connection = null;

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
   * constructor.
   */
  public TerminalOne() {
    logger.info("Loading Environment - setting up connection.");
    connection = new Connection();
    tOneService = new T1Service();
  }

  /**
   * constructor, tries to connect with the credentials provided.
   * 
   * @throws ClientException
   *           a client exception is thrown if any error occurs.
   * 
   */
  public TerminalOne(String username, String password, String apiKey) throws ClientException {
    this();

    validateLoginCredentials(username, password, apiKey);

    logger.info("Loading Environment - Authenticating.");
    Form form = tOneService.getLoginFormData(username, password, apiKey);
    String url = tOneService.constructUrl(new StringBuffer("login"));
    Response loginResponse = connection.post(url, form, null);
    parseLoginError(loginResponse);
    String response = loginResponse.readEntity(String.class);

    setUserSessionInfo(response);
    postService = new PostService(connection, user);
    getService = new GetService();
    reportService = new ReportService();

    if (this.getUser() != null && this.getUser().getData() != null) {
      if (this.getUser().getData().getSession() != null
          && this.getUser().getData().getSession().getSessionid() != null
          && !this.getUser().getData().getSession().getSessionid().isEmpty()) {
        this.authenticated = true;
      }
    }

  }

  private void parseLoginError(Response response) throws ClientException {
    if (response.getStatus() == 403) {
      logger.error(
          "Authentication Failed: please provide valid credentials for the given environment");
      throw new ClientException(
          "Authentication Failed: please provide valid credentials for the given environment");
    }
  }

  /**
   * private method to validate login credentials.
   * 
   * @param username a valid username is required.
   * @param password a valid password is required.
   * @param apiKey a valid environment api key is required.
   * @throws ClientException exception.
   */
  private void validateLoginCredentials(String username, String password, String apiKey)
      throws ClientException {
    if (apiKey == null || apiKey.isEmpty()) {
      logger.error("Environment does not exist");
      throw new ClientException("Please Provide Valid API Key");
    }

    if (username.isEmpty() || password.isEmpty()) {
      logger.error("Please provide valid credentials.");
      throw new ClientException("Please Provide Valid Username and Password.");
    }
  }

  /**
   * used to authenticate using given credentials.
   * 
   * @return boolean
   * @throws ClientException
   *           a client exception is thrown if any error occurs.
   */
  public boolean authenticate(String username, String password, String apiKey)
      throws ClientException {

    // TODO validate
    logger.info("Authenticating.");
    validateLoginCredentials(username, password, apiKey);

    Form form = tOneService.getLoginFormData(username, password, apiKey);
    String url = tOneService.constructUrl(new StringBuffer("login"));

    Response loginResponse = connection.post(url, form, null);
    parseLoginError(loginResponse);
    String response = loginResponse.readEntity(String.class);

    setUserSessionInfo(response);
    
    postService = new PostService(connection, user);
    getService = new GetService();
    reportService = new ReportService();

    if (this.getUser() != null && this.getUser().getData() != null) {
      if (this.getUser().getData().getSession() != null
          && this.getUser().getData().getSession().getSessionid() != null
          && !this.getUser().getData().getSession().getSessionid().isEmpty()) {
        this.authenticated = true;
      }
    }

    return isAuthenticated();
  }

  /**
   * get Authorization url for oauth login.
   * 
   * @param redirectUri valid redirect uri is required. 
   * @param apiKey valid apiKey is required.
   * @return String object.
   * @throws ClientException
   *           a client exception is thrown if any error occurs.
   */
  public String getAuthorizationUrl(String redirectUri, String apiKey) throws ClientException {
    String oauthAuthorizationUrl = tOneService.constructOauthUrl(new StringBuffer("authorize"));
    OAuthClientRequest request = null;
    try {
      request = OAuthClientRequest.authorizationLocation(oauthAuthorizationUrl)
          .setClientId(apiKey).setRedirectURI(redirectUri)
          .setResponseType(ResponseType.CODE.toString()).buildQueryMessage();
    } catch (OAuthSystemException oauthSystemException) {
      throw new ClientException("Unable to get OAuth authorization URL: " + oauthSystemException.getMessage());
    }
    return request.getLocationUri();
  }

  /**
   * Gets OAuthTokens.
   * 
   * @param code valid code is required.
   * @param apiKey valid api key is required.
   * @param secret valid secret key is required.
   * @param redirectUri a valid redirect URI is required.
   * @return OAuthJSONAccessTokenResponse object.
   * @throws ClientException
   *           a client exception is thrown if any error occurs.
   */
  public OAuthJSONAccessTokenResponse getOauthToken(String code, String apiKey, String secret, String redirectUri) throws ClientException {
    String oauthTokenUrl = tOneService.constructOauthUrl(new StringBuffer("token"));
    try {
      OAuthClientRequest request = OAuthClientRequest.tokenLocation(oauthTokenUrl)
          .setGrantType(GrantType.AUTHORIZATION_CODE).setClientId(apiKey).setClientSecret(secret)
          .setRedirectURI(redirectUri).setCode(code).buildQueryMessage();
      OAuthClient oauthClient = new OAuthClient(new URLConnectionClient());
      OAuthJSONAccessTokenResponse oauthResponse = oauthClient.accessToken(request,
          OAuthJSONAccessTokenResponse.class);
      return oauthResponse;
    } catch (OAuthSystemException oauthSystemException) {
      throw new ClientException("Unable to get OAuth token: " + oauthSystemException.getMessage());
    } catch (OAuthProblemException oauthProblemException) {
      throw new ClientException("Unable to get OAuth token: " + oauthProblemException.getMessage());
    }
  }

  /**
   * method to refresh oauth token.
   * 
   * @param refreshToken requires a refresh token.
   * 
   * @param apiKey requires valid api key.
   * 
   * @param secret requires a valid secret. 
   * 
   * @return OAuthJSONAccessTokenResponse object.
   * 
   * @throws ClientException
   *           a client exception is thrown if any error occurs.
   */
  public OAuthJSONAccessTokenResponse refreshOauthToken(String refreshToken, String apiKey, String secret) throws ClientException {
    String oauthTokenUrl = tOneService.constructOauthUrl(new StringBuffer("token"));
    try {
      OAuthClientRequest request = OAuthClientRequest.tokenLocation(oauthTokenUrl)
          .setGrantType(GrantType.REFRESH_TOKEN).setClientId(apiKey).setClientSecret(secret)
          .setRefreshToken(refreshToken).buildQueryMessage();
      OAuthClient oauthClient = new OAuthClient(new URLConnectionClient());
      OAuthJSONAccessTokenResponse oauthResponse = oauthClient.accessToken(request,
          OAuthJSONAccessTokenResponse.class);
      return oauthResponse;
    } catch (OAuthSystemException oauthSystemException) {
      throw new ClientException("Unable to get OAuth token: " + oauthSystemException.getMessage());
    } catch (OAuthProblemException oauthProblemException) {
      throw new ClientException("Unable to get OAuth token: " + oauthProblemException.getMessage());
    }
  }

  /**
   * Maintains user session information.
   * 
   */
  private void setUserSessionInfo(String response) {
    Gson gson = new Gson();
    T1User resp = null;

    Type responseTypeInfo = new TypeToken<T1User>() {
    }.getType();
    resp = gson.fromJson(response, responseTypeInfo);
    this.setUser(resp);

  }

 
  /**
   * 
   * @param entity expects an Agency Entity.
   * @return Agency entity.
   * @throws ClientException exception.
   * @throws ParseException exception.
   */
  public Agency save(Agency entity) throws ClientException, ParseException {
    Agency agency = null;
    if (isAuthenticated()) {
      agency = postService.save(entity);
    }
    return agency;
  }

  /**
   * saves Advertiser.
   * 
   * @param entity expects Advertiser entity.
   * @return Advertiser entity.
   * @throws ClientException exception.
   * @throws ParseException exception.
   */
  public Advertiser save(Advertiser entity) throws ClientException, ParseException {
    Advertiser advertiser = null;
    if (isAuthenticated()) {
      advertiser = postService.save(entity);
    }
    return advertiser;
  }


  /**
   * Saves Strategy entity.
   * 
   * @param entity expects Strategy entity.
   * @return Strategy object.
   * @throws ClientException
   *           a client exception is thrown if any error occurs.
   * @throws ParseException
   *           a parse exception is thrown when the response cannot be parsed.
   */
  public Strategy save(Strategy entity) throws ClientException, ParseException {
    Strategy strategy = null;
    if (isAuthenticated()) {
      strategy = postService.save(entity);
    }
    return strategy;
  }

  /**
   * saves Strategy Concepts.
   * 
   * @param entity expects StrategyConcept entity.
   * @return StrategyConcept entity.
   * @throws ClientException
   *           a client exception is thrown if any error occurs.
   * @throws ParseException
   *           a parse exception is thrown when the response cannot be parsed.
   */
  public StrategyConcept save(StrategyConcept entity) throws ClientException, ParseException {
    StrategyConcept strategyConcept = null;
    if (isAuthenticated()) {
      strategyConcept = postService.save(entity);
    }
    return strategyConcept;
  }

  /**
   * saves Strategy Supply Sources.
   * 
   * @param entity expects a StrategySupplySource entity. 
   * 
   * @return StrategySupplySource

   * @throws ClientException
   *           a client exception is thrown if any error occurs.
   * @throws ParseException
   *           a parse exception is thrown when the response cannot be parsed.
   */
  public StrategySupplySource save(StrategySupplySource entity)
      throws ClientException, ParseException {
    StrategySupplySource strategySupplySource = null;
    if (isAuthenticated()) {
      strategySupplySource = postService.save(entity);
    }
    return strategySupplySource;
  }

  /**
   * saves StrategyDayPart entity.
   * 
   * @param entity expects a StrategyDayPart entity. 
   * @return StrategyDayPart entity.
   * @throws ClientException
   *           a client exception is thrown if any error occurs.
   * @throws ParseException
   *           a parse exception is thrown when the response cannot be parsed.
   */
  public StrategyDayPart save(StrategyDayPart entity) throws ClientException, ParseException {
    StrategyDayPart strategy = null;
    if (isAuthenticated()) {
      strategy = postService.save(entity);
    }
    return strategy;
  }

  /**
   * saves Organization.
   * 
   * @param entity expects an Organization entity.
   * @return Organization entity.
   * @throws ClientException
   *           a client exception is thrown if any error occurs.
   * @throws ParseException
   *           a parse exception is thrown when the response cannot be parsed.
   */
  public Organization save(Organization entity) throws ClientException, ParseException {
    Organization organization = null;
    if (isAuthenticated()) {
      organization = postService.save(entity);
    }
    return organization;
  }

  /**
   * saves Pixel.
   * 
   * @param entity expects a Pixel entity.
   * 
   * @return Pixel object.
   * 
   * @throws ClientException
   *           a client exception is thrown if any error occurs.
   * @throws ParseException
   *           a parse exception is thrown when the response cannot be parsed.
   */
  public Pixel save(Pixel entity) throws ClientException, ParseException {
    Pixel pixel = null;
    if (isAuthenticated()) {
      pixel = postService.save(entity);
    }
    return pixel;
  }

  /**
   * saves Campaign.
   * 
   * @param entity expects Campaign entity.
   *  
   * @return Campaign object.
   * 
   * @throws ClientException
   *           a client exception is thrown if any error occurs.
   * @throws ParseException
   *           a parse exception is thrown when the response cannot be parsed.
   */
  public Campaign save(Campaign entity) throws ParseException, ClientException {
    Campaign campaign = null;
    if (isAuthenticated()) {
      campaign = postService.save(entity);
    }
    return campaign;
  }

  /**
   * saves Concepts
   * 
   * @param entity expects Concept entity.
   * @return Concept object.
   * @throws ClientException
   *           a client exception is thrown if any error occurs.
   * @throws ParseException
   *           a parse exception is thrown when the response cannot be parsed.
   */
  public Concept save(Concept entity) throws ParseException, ClientException {
    Concept concept = null;
    if (isAuthenticated()) {
      concept = postService.save(entity);
    }
    return concept;
  }

  /**
   * saves Atomic Creative
   * 
   * @param entity expects AtomcCreative entity.
   * @return AtomicCreative object.
   * @throws ClientException
   *           a client exception is thrown if any error occurs.
   * @throws ParseException
   *           a parse exception is thrown when the response cannot be parsed.
   */
  public AtomicCreative save(AtomicCreative entity) throws ParseException, ClientException {
    AtomicCreative atomicCreative = null;
    if (isAuthenticated()) {
      atomicCreative = postService.save(entity);
    }
    return atomicCreative;
  }

  /**
   * saves 3pas creative upload file.
   * first call to upload the file. <br>
   * <br>
   * example:<br>
   * <br>
   * <i>save3pasCreativeUpload("C:\\exampledir1\\exampledir2\\samplefile.txt", "samplefile","samplefile");</i>
   * 
   * 
   * @param filePath
   *          a valid filePath is required.
   *           
   * @param fileName
   *          a valid fileName is required.
   * @param name
   *          a valid name is required.
   *          
   * @return TPASCreativeUpload object.
   * 
   * @throws ClientException
   *           a client exception is thrown if any error occurs.
   * @throws IOException
   *           a IOException is thrown when the file cannot be uploaded.
   */
  public TPASCreativeUpload saveTPASCreativeUpload(String filePath, String fileName,
      String name) throws ClientException, IOException {
    TPASCreativeUpload response = null;
    if (isAuthenticated()) {
      response = postService.saveTPASCreativeUpload(filePath, fileName, name);
    }
    return response;
  }

  /**
   * This API call in the Bulk 3PAS process saves particular creative out of a given batch to the T1
   * database.
   * second call to save the 3pas creative upload
   * 
   * @param batchApprove requires TPASCreativeBatchApprove entity. 
   * @return JsonResponse<? extends T1Entity> JsonResponse of type T is returned.
   * @throws ClientException
   *           a client exception is thrown if any error occurs.
   * @throws IOException
   *           a IOException is thrown when the file cannot be uploaded.
   * @throws ParseException
   *           a parse exception is thrown when the response cannot be parsed.           
   */
  public JsonResponse<? extends T1Entity> saveTPASCreativeUploadBatch(
      TPASCreativeBatchApprove batchApprove)
      throws ClientException, IOException, ParseException {
    JsonResponse<? extends T1Entity> finalJsonResponse = null;
    if (isAuthenticated()) {
      finalJsonResponse = postService.saveTPASCreativeUploadBatch(batchApprove);
    }
    return finalJsonResponse;
  }

  /**
   * saves upload to T1AS.
   * first call to upload the file. <br>
   * <br>
   * example: <br>
   * <br>
   * <i>saveT1ASCreativeAssetsUpload("C:\\exampledir1\\exampledir2\\samplefile.txt", "samplefile","samplefile");</i>
   * 
   * @param filePath
   *          a valid filePath is required.
   * 
   * @param fileName
   *          a valid fileName is required. 
   * @param name
   *          a valid name is required.
   * @return TOneASCreativeAssetsUpload object.
   * 
   * @throws ClientException
   *           a client exception is thrown if any error occurs.
   * 
   * @throws IOException
   *           a IOException is thrown when the file cannot be uploaded.
   */
  public TOneASCreativeAssetsUpload saveTOneASCreativeAssetsUpload(String filePath, String fileName, String name) throws ClientException, IOException {
    TOneASCreativeAssetsUpload response = null;
    if (isAuthenticated()) {
      response = postService.saveTOneASCreativeAssets(filePath, fileName, name);
    }
    return response;
  }

  /**
   * Second call, Approves the upload done in the first call.
   * 
   * @param creativeAssetsApprove expects a TOneASCreativeAssetsApprove entity.
   * @return JsonResponse<? extends T1Entity> JsonResponse of type T is returned.
   * @throws ClientException
   *           a client exception is thrown if any error occurs.
   */
  public JsonResponse<? extends T1Entity> saveTOneASCreativeAssetsApprove(TOneASCreativeAssetsApprove creativeAssetsApprove) throws ClientException {
    JsonResponse<? extends T1Entity> response = null;
    if (isAuthenticated()) {
      response = postService.saveTOneASCreativeAssetsApprove(creativeAssetsApprove);
    }
    return response;
  }

  /**
   * First Call to Create a Video Creative.
   * 
   * @param videoCreative expects a VideoCreative entity.
   * @return VideoCreativeResponse object.
   * @throws ClientException
   *           a client exception is thrown if any error occurs. 
   */
  public VideoCreativeResponse saveVideoCreatives(VideoCreative videoCreative)
      throws ClientException {
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
   *          a valid filePath is required.
   * @param fileName
   *          a valid fileName is required.
   * @param creativeId
   *          a valid creativeId is required.
   * @return VideoCreativeResponse object.
   * 
   * @throws ClientException
   *           a client exception is thrown if any error occurs. 
   * 
   */
  public VideoCreativeResponse uploadVideoCreative(String filePath, String fileName,
      String creativeId) throws ClientException {
    VideoCreativeResponse response = null;
    if (isAuthenticated()) {
      if (filePath != null && !filePath.isEmpty() && fileName != null && !fileName.isEmpty()
          && creativeId != null && !creativeId.isEmpty()) {

        response = postService.uploadVideoCreative(filePath, fileName, creativeId);
      }
    }
    return response;
  }

  /**
   * Check the status of the uploaded video creative.
   * 
   * @param creativeId 
   *          a valid creativeId is required.
   * @return VideoCreativeUploadStatus object.
   * 
   */
  public VideoCreativeUploadStatus getVideoCreativeUploadStatus(String creativeId) {
    VideoCreativeUploadStatus response = null;
    if (isAuthenticated()) {
      if (creativeId != null && !creativeId.isEmpty()) {
        response = postService.getVideoCreativeUploadStatus(creativeId);
      }
    }
    return response;
  }

  /**
   * second call to get the upload token for video creative.
   * 
   * @deprecated this method is deprecated since this step is not required in the new flow.
   */
  @Deprecated
  public VideoCreativeResponse getVideoCreativesUploadToken(VideoCreativeResponse videoCreative)
      throws ClientException, ParseException {
    VideoCreativeResponse response = null;
    if (isAuthenticated()) {
      response = postService.getVideoCreativeUploadToken(videoCreative);
    }
    return response;
  }

  /**
   * Get.
   * 
   * @param query expects a QueryCriteria entity.
   * 
   * @return JsonResponse<? extends T1Entity> JsonResponse of type T is returned.
   * 
   * @throws ClientException
   *           a client exception is thrown if any error occurs.
   * @throws ParseException
   *           a parse exception is thrown when the response cannot be parsed.     
   */
  public JsonResponse<? extends T1Entity> get(QueryCriteria query)
      throws ClientException, ParseException {

    StringBuffer path = getService.get(query);

    // get the data from t1 servers.
    String finalPath = tOneService.constructUrl(path);
    String response = this.connection.get(finalPath, this.getUser());
    JsonResponse<? extends T1Entity> jsonResponse;
    // parse the data to entities.
    try {
      jsonResponse = parseGetData(response, query);
      // jsonResponse = checkResponseEntities(jsonResponse);

    } catch (ParseException parseException) {

      throw new ClientException("Unable to parse the response");

    }

    // filter and validate data
    return jsonResponse;
  }

  /**
   * GET meta of all the reports available.
   * 
   * @return JsonResponse<? extends T1Entity> JsonResponse of type T is returned.
   */
  public JsonResponse<? extends T1Entity> getMeta() {
    JsonResponse<? extends T1Entity> jsonResponse = null;
    StringBuffer path = reportService.getMetaUri();
    String finalPath = tOneService.constructReportingUrl(path);
    String response = this.connection.get(finalPath, this.getUser());
    jsonResponse = reportService.parseMetaResponse(response);
    return jsonResponse;
  }

  /**
   * GET meta data for a specific report.
   * 
   * @param report expects a Reports name. 
   *    
   * @return MetaData object.
   */
  public MetaData getReportsMeta(Reports report) {
    StringBuffer reportName = new StringBuffer(report.getReportNameWithMeta());
    String finalPath = tOneService.constructReportingUrl(reportName);
    String response = this.connection.get(finalPath, this.getUser());
    MetaData metaResponse = reportService.parseReportMetaResponse(response);
    return metaResponse;
  }

  /**
   * GET a specific report.
   * 
   * @param report expects a report name from the Reports enum.
   * 
   * @param criteria expects ReportCriteria entity. 
   *
   * @return reader BufferedReader is returned. 
   * 
   * @throws ClientException
   *           a client exception is thrown if any error occurs.
   *
   * 
   */
  public BufferedReader getReport(Reports report, ReportCriteria criteria) throws ClientException {
    criteria.setReportName(report.getReportName());
    StringBuffer path = null;
    path = reportService.getReportUri(criteria);
    String finalPath = tOneService.constructReportingUrl(path);
    BufferedReader reader = reportService.getReportData(report, finalPath, connection, user);
    return reader;
  }

  /**
   * Validates a given Report.
   * 
   * @param report expects a valid report name from Reports enum.
   * 
   * @param criteria expects a valid ReportCriteria entity. 
   * 
   * @return ReportValidationResponse object.
   * 
   * @throws ClientException
   *           a client exception is thrown if any error occurs.
   * @throws ParseException
   *           a parse exception is thrown when the response cannot be parsed.     
   */
  public ReportValidationResponse validateReport(Reports report, ReportCriteria criteria)
      throws ClientException {
    criteria.setReportName(report.getReportName() + "/validate");
    StringBuffer path = null;
    path = reportService.getReportUri(criteria);
    String finalPath = tOneService.constructReportingUrl(path);
    ReportValidationResponse validationResponse = reportService.validateReportData(report,
        finalPath, connection, user);
    return validationResponse;
  }

  /**
   * parses the response to objects.
   * 
   * @param response
   *         a valid JSON response string is required.
   * @param QueryCriteria
   *         a valid query object is required.
   * @return JsonResponse<? extends T1Entity> JsonResponse of type T is returned.
   * @throws ClientException
   *           a client exception is thrown if any error occurs.
   * @throws ParseException
   *           a parse exception is thrown when the response cannot be parsed.     
   */
  private <T extends T1Entity> JsonResponse<? extends T1Entity> parseGetData(String response, QueryCriteria query) throws ParseException, ClientException {
    T1JsonToObjParser parser = new T1JsonToObjParser();
    // parse the string to gson objs
    JsonResponse<? extends T1Entity> finalJsonResponse = null;
    JsonPostErrorResponse jsonPostErrorResponse = null;

    // check whether error present
    jsonPostErrorResponse = getService.jsonGetErrorResponseParser(response);
    // if no error
    if (jsonPostErrorResponse == null) {
      JsonElement element = parser.getDataFromResponse(response);
      if (element != null) {
        if (element.isJsonArray()) {
          // do something
          JsonArray dataList = element.getAsJsonArray();

          String entityType;
          if (dataList.size() > 0) {
            JsonElement data = dataList.get(0);
            if (data != null) {
              JsonObject dataObj = data.getAsJsonObject();
              if (dataObj != null) {
                JsonElement entityTypeElem = dataObj.get("entity_type");
                if (entityTypeElem != null) {
                  entityType = entityTypeElem.getAsString();
                  if (entityType != null && !entityType.isEmpty()) {
                    if (Constants.getListoFEntityType.get(entityType) != null) {
                      finalJsonResponse = parser.parseJsonToObj(response,
                          Constants.getListoFEntityType.get(entityType));
                    }
                  }
                }
              }
            }
          } else {
            if (query.collection != null) {
              finalJsonResponse = parser.parseJsonToObj(response,
                  Constants.getListoFEntityType.get(query.collection.toLowerCase()));
            }
          }

        } else if (element.isJsonObject()) {
          JsonObject obj = element.getAsJsonObject();
          JsonElement entityTypeElement = obj.get("entity_type");
          String entityType = (entityTypeElement != null) ? entityTypeElement.getAsString() : null;
          if (entityType != null && !entityType.equalsIgnoreCase("")) {
            finalJsonResponse = parser.parseJsonToObj(response,
                Constants.getEntityType.get(entityType));
          } else {
            finalJsonResponse = parser.parseJsonToObj(response,
                new TypeToken<JsonResponse<Data>>() {
                }.getType());
          }
        }
      } else if (element == null) {
        if (query.collection != null) {
          finalJsonResponse = parser.parseJsonToObj(response,
              Constants.getEntityType.get(query.collection.toLowerCase()));
          if (finalJsonResponse != null) {
            finalJsonResponse.setData(null);
          }
        }
      }
    } else {
      postService.throwExceptions(jsonPostErrorResponse);
    }
    return finalJsonResponse;
  }

  /**
   * Find method, alternative to get method.
   * 
   * @param query a valid QueryCriteria entity is required.
   * 
   * @return JsonResponse<? extends T1Entity> JsonResponse of type T is returned.
   * 
   * @throws ClientException
   *           a client exception is thrown if any error occurs.
   * @throws ParseException
   *           a parse exception is thrown when the response cannot be parsed.     
   */
  public JsonResponse<? extends T1Entity> find(QueryCriteria query)
      throws ClientException, ParseException {
    String paramVal = getService.find(query);
    query.query = paramVal;
    return this.get(query);

  }

  /**
   * deletes a StrategyConcept entity.
   * 
   * @param strategyConcept expects a StrategyConcept entity
   * @return JsonResponse<? extends T1Entity> returns a JsonResponse of type T
   * @throws ClientException
   *           a client exception is thrown if any error occurs.
   * @throws ParseException
   *           a parse exception is thrown when the response cannot be parsed.  
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
   * @param strategyDayPart expects a StrategyDayPart entity.
   * @return JsonResponse<? extends T1Entity> returns Json Response of type T
   * @throws ClientException
   *           a client exception is thrown if any error occurs.
   * @throws ParseException
   *           a parse exception is thrown when the response cannot be parsed.     
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
