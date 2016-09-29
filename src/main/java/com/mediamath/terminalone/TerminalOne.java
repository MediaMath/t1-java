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


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mediamath.terminalone.Exceptions.ClientException;
import com.mediamath.terminalone.Exceptions.ParseException;
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
import com.mediamath.terminalone.models.T1Error;
import com.mediamath.terminalone.models.T1Response;
import com.mediamath.terminalone.models.TOneASCreativeAssetsApprove;
import com.mediamath.terminalone.models.TOneASCreativeAssetsUpload;
import com.mediamath.terminalone.models.ThreePASCreativeBatchApprove;
import com.mediamath.terminalone.models.ThreePASCreativeUpload;
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
 * handles the authentication, session, entity
 * retrieval, creation etc.
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
	private T1Response user = null;

	private boolean authenticated = false;
	
	public TerminalOne() {
		logger.info("Loading Environment - setting up connection.");
		connection = new Connection();
		tOneService = new T1Service();
	}

	/**
	 * constructor, tries to connect with the credentials provided.
	 * 
	 * @throws ClientException 
	 * 
	 */
	public TerminalOne(String username, String password, String api_key) throws ClientException {
		this();

		
		validateLoginCredentials(username, password, api_key);

		logger.info("Loading Environment - Authenticating.");
		Form form = tOneService.getLoginFormData(username, password, api_key);
		String url = tOneService.constructURL(new StringBuffer("login"));
		Response loginResponse = connection.post(url, form, null);
		parseLoginError(loginResponse);
		String response = loginResponse.readEntity(String.class);
		
		getUserSessionInfo(response);
		postService = new PostService(connection, user);
		getService  = new GetService();
		reportService = new ReportService();
		
		if(this.getUser() != null && this.getUser().getData() != null) {
			if(this.getUser().getData().getSession() != null && this.getUser().getData().getSession().getSessionid() != null 
					&& !this.getUser().getData().getSession().getSessionid().isEmpty()) {
				this.authenticated = true;
			}
		}
		
	}

	private void parseLoginError(Response response) throws ClientException {
		if(response.getStatus() == 403) {
			logger.error("Authentication Failed: please provide valid credentials for the given environment");
			throw new ClientException("Authentication Failed: please provide valid credentials for the given environment");
		}
	}

	/**
	 * 
	 * private method to validate login credentials.
	 * 
	 * @param username
	 * @param password
	 * @param api_key
	 * @throws ClientException
	 */
	private void validateLoginCredentials(String username, String password, String api_key) throws ClientException {
		if(api_key == null || api_key.isEmpty()) {
			logger.error("Environment does not exist");
			throw new ClientException("Please Provide Valid Enviornment");
		}
		
		if(username.isEmpty() || password.isEmpty()) {
			logger.error("Please provide valid credentials.");
			throw new ClientException("Please Provide Valid Username and Password.");
		}
	}
	
	/**
	 * used to authenticate using given credentials.
	 * 
	 * @return boolean
	 */
	public boolean authenticate(String username, String password, String api_key) throws ClientException {

		// TODO validate
		logger.info("Authenticating.");
		validateLoginCredentials(username, password, api_key);
		
		Form form = tOneService.getLoginFormData(username, password, api_key);
		String url = tOneService.constructURL(new StringBuffer("login"));

		Response loginResponse = connection.post(url, form, null);
		parseLoginError(loginResponse);
		String response = loginResponse.readEntity(String.class);
		
		getUserSessionInfo(response);
		postService = new PostService(connection, user);
		getService  = new GetService();
		reportService = new ReportService();
		
		if(this.getUser() != null && this.getUser().getData() != null) {
			if(this.getUser().getData().getSession() != null && this.getUser().getData().getSession().getSessionid() != null 
					&& !this.getUser().getData().getSession().getSessionid().isEmpty()) {
				this.authenticated = true;
			}
		}
		
		return isAuthenticated();
	}

	/**
	 * Maintains user session information.
	 * 
	 * @param response
	 */
	private void getUserSessionInfo(String response) {
		Gson gson = new Gson();
		T1Response resp = null;
		
		Type responseTypeInfo = new TypeToken<T1Response>() {}.getType();
		resp = gson.fromJson(response, responseTypeInfo);
		this.setUser(resp);

	}
	
	
	/**
	 * saves Agency.
	 * 
	 * @return Agency
	 * @param  Agency
	 * @throws ClientException 
	 * @throws ParseException 
	 */
	public Agency save(Agency entity) throws ClientException, ParseException {
		Agency agency = null;
		if(isAuthenticated()) {
			agency = postService.save(entity);
		}
		return agency;
	}
	
	/**
	 * saves Advertiser.
	 * 
	 * @return Advertiser
	 * @param  Advertiser
	 * @throws ClientException 
	 * @throws ParseException 
	 */
	public Advertiser save(Advertiser entity) throws ClientException, ParseException {
		Advertiser advertiser = null;
		if(isAuthenticated()) {
			advertiser = postService.save(entity);
		}
		return advertiser;
	}
	
	
	/**
	 * saves Strategy.
	 * 
	 * @return Strategy
	 * @param  Strategy
	 * @throws ClientException 
	 * @throws ParseException 
	 */
	public Strategy save(Strategy entity) throws ClientException, ParseException {
		Strategy strategy = null;
		if(isAuthenticated()) {
			strategy = postService.save(entity);
		}
		return strategy;
	}
	
	/**
	 * saves Strategy Concepts.
	 * 
	 * @return StrategyConcept
	 * @param  StrategyConcept
	 * @throws ClientException 
	 * @throws ParseException 
	 */
	public StrategyConcept save(StrategyConcept entity) throws ClientException, ParseException {
		StrategyConcept strategyConcept = null;
		if(isAuthenticated()) {
			strategyConcept = postService.save(entity);
		}
		return strategyConcept;
	}
	
	/**
	 * saves Strategy Supply Sources.
	 * 
	 * @return StrategySupplySource
	 * @param  StrategySupplySource
	 * @throws ClientException 
	 * @throws ParseException 
	 */
	public StrategySupplySource save(StrategySupplySource entity) throws ClientException, ParseException {
		StrategySupplySource strategySupplySource = null;
		if(isAuthenticated()) {
			strategySupplySource = postService.save(entity); 
		}
		return strategySupplySource;
	}
	
	/**
	 * saves Organization.
	 * 
	 * @return Organization
	 * @param  Organization
	 * @throws ClientException 
	 * @throws ParseException 
	 */
	public Organization save(Organization entity) throws ClientException, ParseException {
		Organization organization = null;
		if(isAuthenticated()) {
			organization = postService.save(entity);
		}
		return organization;
	}
	
	/**
	 * saves Pixel.
	 * 
	 * @return Pixel
	 * @param  Pixel
	 * @throws ClientException 
	 * @throws ParseException 
	 */
	public Pixel save(Pixel entity) throws ClientException, ParseException {
		Pixel pixel = null;
		if(isAuthenticated()) {
			pixel = postService.save(entity); 
		}
		return pixel;
	}
	
	/**
	 * saves Campaign.
	 * 
	 * @return Campaign
	 * @param T1Entity Campaign
	 * @return Campaign
	 * @throws ParseException
	 * @throws ClientException
	 */
	public Campaign save(Campaign entity) throws ParseException, ClientException {
		Campaign campaign = null;
		if(isAuthenticated()) {
			campaign = postService.save(entity); 
		}
		return campaign;
	}
	
	/**
	 * saves Concepts
	 * 
	 * @param  Concept
	 * @return Concept
	 * @throws ParseException
	 * @throws ClientException
	 */
	public Concept save(Concept entity) throws ParseException, ClientException {
		Concept concept = null;
		if(isAuthenticated()) {
			concept = postService.save(entity); 
		}
		return concept;
	}
	
	/**
	 * saves Atomic Creative
	 * 
	 * @param  AtomicCreative
	 * @return AtomicCreative
	 * @throws ParseException
	 * @throws ClientException
	 */
	public AtomicCreative save(AtomicCreative entity) throws ParseException, ClientException {
		AtomicCreative atomicCreative = null;
		if(isAuthenticated()) {
			atomicCreative = postService.save(entity); 
		}
		return atomicCreative;
	}
	
	/**
	 * 
	 * saves 3pas creative upload file.
	 * 
	 * first call to upload the file.
	 * <br><br>
	 * example:<br><br>
	 *  <i>save3pasCreativeUpload("C:\\exampledir1\\exampledir2\\samplefile.txt", "samplefile" ,"samplefile");</i>
	 * 
	 * 
	 * @param String filePath
	 * @param String fileName
	 * @param String name
	 * @return ThreePASCreativeUpload
	 * @throws ClientException
	 * @throws IOException
	 */
	public ThreePASCreativeUpload save3pasCreativeUpload(String filePath, String fileName, String name) throws ClientException, IOException {
		ThreePASCreativeUpload response = null;
		if(isAuthenticated()) {
			response = postService.save3pasCreativeUpload(filePath, fileName, name);
		}
		return response;
	}
	

	/**
	 * 
	 * this API call in the Bulk 3PAS process saves particular creative out of a given batch to the T1 database.
	 * 
	 * second call to save the 3pas creative upload
	 * 
	 * @param  ThreePASCreativeBatchApprove
	 * @return JsonResponse<? extends T1Entity>
	 * @throws ClientException
	 * @throws IOException
	 * @throws ParseException
	 */
	public JsonResponse<? extends T1Entity> save3pasCreativeUploadBatch(ThreePASCreativeBatchApprove batchApprove) throws ClientException, IOException, ParseException {
		JsonResponse<? extends T1Entity> finalJsonResponse = null;
		if(isAuthenticated()) {
				finalJsonResponse = postService.save3pasCreativeUploadBatch(batchApprove);
		}
		return finalJsonResponse;
	}
	
	/**
	 * 
	 * saves upload to T1AS.
	 * 
	 * first call to upload the file.
	 * <br><br>
	 * example: <br><br>
	 *  <i>saveT1ASCreativeAssetsUpload("C:\\exampledir1\\exampledir2\\samplefile.txt", "samplefile" ,"samplefile");</i>
	 * 
	 * @param  String filePath
	 * @param  String fileName
	 * @param  String name
	 * @return TOneASCreativeAssetsUpload
	 * @throws ClientException
	 * @throws IOException
	 */
	public TOneASCreativeAssetsUpload saveT1ASCreativeAssetsUpload(String filePath, String fileName, String name) throws ClientException, IOException {
		TOneASCreativeAssetsUpload response = null;
		if(isAuthenticated()) {
			response = postService.saveT1asCreativeAssets(filePath, fileName, name);
		}
		return response;
	}
	
	/**
	 * Second call, Approves the upload done in the first call.
	 * 
	 * @param  TOneASCreativeAssetsApprove
	 * @return JsonResponse<? extends T1Entity>
	 * @throws ClientException
	 */
	public JsonResponse<? extends T1Entity> saveTOneASCreativeAssetsApprove(TOneASCreativeAssetsApprove creativeAssetsApprove) throws ClientException {
		JsonResponse<? extends T1Entity> response = null;
		if(isAuthenticated()) {
			response = postService.saveTOneASCreativeAssetsApprove(creativeAssetsApprove);
		}
		return response;
	}
	
	/**
	 * First Call to Create a Video Creative.
	 * 
	 * @param  VideoCreative
	 * @return VideoCreativeResponse
	 * @throws ClientException
	 */
	public VideoCreativeResponse saveVideoCreatives(VideoCreative videoCreative) throws ClientException {
		VideoCreativeResponse response = null;
		if(isAuthenticated()) {
			 response = postService.save(videoCreative);
		}
		return response;
	}
	
	/**
	 * second call to upload video creative media using the creativeId.
	 * 
	 * @param  String filePath
	 * @param  String fileName
	 * @param  String creativeId
	 * @throws ClientException 
	 * @return VideoCreativeResponse
	 */
	public VideoCreativeResponse uploadVideoCreative(String filePath, String fileName, String creativeId) throws ClientException {
		VideoCreativeResponse response = null;
		if(isAuthenticated()) {
			if(filePath != null 
					&& !filePath.isEmpty()
					&& fileName != null
					&& !fileName.isEmpty()
					&& creativeId != null
					&& !creativeId.isEmpty()) {
				
				response = postService.uploadVideoCreative(filePath, fileName, creativeId);
			}
		}
		return response;
	}
	
	/**
	 * Check the status of the uploaded video creative.
	 * 
	 * @param String creativeId
	 * @return VideoCreativeUploadStatus
	 */
	public VideoCreativeUploadStatus getVideoCreativeUploadStatus(String creativeId) {
		VideoCreativeUploadStatus response = null;
		if(isAuthenticated()) {
			if(creativeId != null && !creativeId.isEmpty()) {
				response = postService.getVideoCreativeUploadStatus(creativeId);
			}
		}
		return response;
	}
	
	/**
	 * second call to get the upload token for video creative.
	 * 
	 * @deprecated
	 * @param  VideoCreativeResponse
	 * @return VideoCreativeResponse
	 * @throws ClientException
	 * @throws ParseException 
	 */
	@Deprecated
	public VideoCreativeResponse getVideoCreativesUploadToken(VideoCreativeResponse videoCreative) throws ClientException, ParseException {
		VideoCreativeResponse response = null;
		if(isAuthenticated()) {
			 response = postService.getVideoCreativeUploadToken(videoCreative);
		}
		return response;
	}
	
	/**
	 * Get.
	 * 
	 * @param  QueryCriteria
	 * @return JsonResponse<? extends T1Entity>
	 * @throws ClientException
	 * @throws ParseException
	 */
	public JsonResponse<? extends T1Entity> get(QueryCriteria query) throws ClientException, ParseException {
		
		StringBuffer path= getService.get(query);
		
		// get the data from t1 servers.
		String finalPath = tOneService.constructURL(path);
		String response = this.connection.get(finalPath, this.getUser());
		JsonResponse<? extends T1Entity> jsonResponse;
		// parse the data to entities.
		try{
			jsonResponse = parseGetData(response,query);
			//jsonResponse = checkResponseEntities(jsonResponse);
		
		} catch (ParseException e) {
			
			throw new ClientException("Unable to parse the response");
		
		}
		
		// filter and validate data
		return jsonResponse;
	}
	
	
	/**
	 * GET meta of all the reports available.
	 * 
	 * @return JsonResponse<? extends T1Entity>
	 */
	public JsonResponse<? extends T1Entity> getMeta() {
		JsonResponse<? extends T1Entity> jsonResponse = null;
		StringBuffer path = reportService.getMetaURI();
		String finalPath = tOneService.constructReportingURL(path);
		String response = this.connection.get(finalPath, this.getUser());
		jsonResponse = reportService.parseMetaResponse(response);
		return jsonResponse;
	}
	

	/**
	 * GET meta data for a specific report.
	 * 
	 * @return MetaData
	 * @param  Reports
	 * @return MetaData
	 */
	public MetaData getReportsMeta(Reports report) {
		StringBuffer reportName = new StringBuffer(report.getReportNameWithMeta());
		String finalPath = tOneService.constructReportingURL(reportName);
		String response = this.connection.get(finalPath, this.getUser());
		MetaData metaResponse = reportService.parseReportMetaResponse(response);
		return metaResponse;
	}
	
	
	/**
	 * GET a specific report.
	 * 
	 * @param  Reports
	 * @param  ReportCriteria
	 * @throws IOException 
	 * @throws ClientException 
	 * @throws UnsupportedEncodingException 
	 * 
	 */
	public void getReport(Reports report, ReportCriteria criteria) throws ClientException {
		criteria.setReportName(report.getReportName());
		StringBuffer path = null;
		path = reportService.getReportURI(criteria);
		String finalPath = tOneService.constructReportingURL(path);
		reportService.getReportData(report, finalPath, connection, user);
	}
	
	
	/**
	 * Validates a given Report.
	 * 
	 * @param  Reports
	 * @param  ReportCriteria
	 * @return ReportValidationResponse
	 * @throws ClientException
	 */
	public ReportValidationResponse validateReport(Reports report, ReportCriteria criteria) throws ClientException {
		criteria.setReportName(report.getReportName() + "/validate");
		StringBuffer path = null;
		path = reportService.getReportURI(criteria);
		String finalPath = tOneService.constructReportingURL(path);
		ReportValidationResponse validationResponse = reportService.validateReportData(report, finalPath, connection, user);
		return validationResponse;
	}

	/*private JsonResponse<? extends T1Entity> checkResponseEntities(JsonResponse<? extends T1Entity> jsonResponse) throws ClientException {
		if(jsonResponse != null) {
			StringBuffer strbuff = null;
			if(jsonResponse.getErrors() != null) {
				for(T1Error error: jsonResponse.getErrors()) {
					if(error.getMessage() != null) {
						if(strbuff == null){ 
							strbuff = new StringBuffer(error.getMessage());
						} else {
							strbuff.append(", " + error.getMessage());
						}
					}
				}
				// throw the error to client
				throw new ClientException(strbuff.toString());
			}
		}
		// else return the object
		return jsonResponse;
	}*/

	/**
	 * parses the response to objects.
	 * 
	 * @param query
	 * @param response
	 * @return
	 * @throws ParseException
	 */
	/*private JsonResponse<? extends T1Entity> parseResponse(QueryCriteria query, String response) throws ParseException {
		T1JsonToObjParser parser = new T1JsonToObjParser();
		int result = parser.getJsonElementType(response);
		Type JsonResponseType = null;
		JsonResponse<? extends T1Entity> jsonresponse = null;
		
		if(query.collection != null) {
			
			if (result != 0) {
				if (result == 1) {
					JsonResponseType = Constants.getEntityType.get(query.collection);
				} else if (result == 2) {
					JsonResponseType = Constants.getListoFEntityType.get(query.collection);
				}
	
				jsonresponse = parser.parseJsonToObj(response, JsonResponseType);
				
			}
		}
		return jsonresponse;
	}*/
	
	/**
	 * parses the response to objects.
	 * 
	 * @param  String response
	 * @param  QueryCriteria query
	 * @return JsonResponse<? extends T1Entity>
	 * @throws ParseException
	 * @throws ClientException
	 */
	private <T extends T1Entity> JsonResponse<? extends T1Entity> parseGetData(String response,  QueryCriteria query) throws ParseException, ClientException {
				T1JsonToObjParser parser = new T1JsonToObjParser();
				// parse the string to gson objs
				JsonResponse<? extends T1Entity>  finalJsonResponse= null;
				JsonPostErrorResponse jsonPostErrorResponse = null;
				
				//check whether error present
				jsonPostErrorResponse = getService.jsonGetErrorResponseParser(response);
				//if no error
				if(jsonPostErrorResponse==null)
				{
						JsonElement element = parser.getDataFromResponse(response);
						if(element != null) {
							if(element.isJsonArray()) {
								// do something
								JsonArray dataList = element.getAsJsonArray();
								
								String entityType; 
								if(dataList.size() > 0) {
									JsonElement data = dataList.get(0);
									if(data != null) {
										JsonObject dataObj = data.getAsJsonObject();
										if(dataObj != null) {
											JsonElement entityTypeElem = dataObj.get("entity_type");
											if(entityTypeElem != null) {
												entityType = entityTypeElem.getAsString();
												if(entityType != null && !entityType.isEmpty()) {
													if(Constants.getListoFEntityType.get(entityType) != null) {
														finalJsonResponse = parser.parseJsonToObj(response, Constants.getListoFEntityType.get(entityType));
													}
												}
											}
										}
									}
								}else {
							         if(query.collection != null) {
							             finalJsonResponse = parser.parseJsonToObj(response, Constants.getListoFEntityType.get(query.collection.toLowerCase()));
							            }
							    }
								
							} else if (element.isJsonObject()) {
								JsonObject obj = element.getAsJsonObject();
								JsonElement entityTypeElement = obj.get("entity_type");
								String entityType =(entityTypeElement!=null) ? entityTypeElement.getAsString() : null;
								if(entityType!=null && !entityType.equalsIgnoreCase("")){
									finalJsonResponse = parser.parseJsonToObj(response, Constants.getEntityType.get(entityType));
								}else{
									finalJsonResponse = parser.parseJsonToObj(response, new TypeToken<JsonResponse<Data>>(){}.getType());
								}
							}
						} else if(element == null) {
							if(query.collection != null) {
								finalJsonResponse = parser.parseJsonToObj(response, Constants.getEntityType.get(query.collection.toLowerCase()));
								if(finalJsonResponse != null) {
									finalJsonResponse.setData(null);
								}
							}
						}
				}else{
					postService.throwExceptions(jsonPostErrorResponse);
				}
				return finalJsonResponse;
	}


	
	
	/** 
	 * Find method, alternative to get method.
	 * 
	 * @param  QueryCriteria
	 * @return JsonResponse<? extends T1Entity>
	 * @throws ParseException 
	 * @throws ClientException 
	 */
	public JsonResponse<? extends T1Entity> find(QueryCriteria query) throws ClientException, ParseException  {
		String qParamVal = getService.find(query);
		query.query = qParamVal;		
		return this.get(query);
		
	}
	
	
	/**
	 * Delete Method for strategy day parts-> budget flight -> strategy-concepts
	 * 
	 * @param  T - i.e. Entity with id and extra params if required
	 * @return JsonResponse<? extends T1Entity>
	 * @throws ClientException
	 * @throws ParseException
	 */
	public JsonResponse<? extends T1Entity> delete(StrategyConcept strategyConcept) throws ClientException, ParseException  {
		JsonResponse<? extends T1Entity> jResponse = null;
		if(isAuthenticated()) {
			jResponse = postService.delete(strategyConcept);
		}
		return jResponse;
	}


	/**
	 * Delete Method for Strategy Day Parts
	 * 
	 * @param  T - i.e. Entity with id and extra params if required
	 * @return JsonResponse<? extends T1Entity>
	 * @throws ClientException
	 * @throws ParseException
	 */
	public JsonResponse<? extends T1Entity> delete(StrategyDayPart strategyDayPart) throws ClientException, ParseException  {
		JsonResponse<? extends T1Entity> jResponse = null;
		if(isAuthenticated()) {
			jResponse = postService.delete(strategyDayPart);
		}
		return jResponse;
	}


	/**
	 * checks if the user is authenticated.
	 * 
	 * @return boolean
	 */
	public boolean isAuthenticated() {
		return authenticated;
	}

	public T1Response getUser() {
		return user;
	}

	public void setUser(T1Response user) {
		this.user = user;
	}

	public void setAuthenticated(boolean b) {
		this.authenticated = b;
		
	}

}
