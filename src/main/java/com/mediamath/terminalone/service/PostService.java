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

package com.mediamath.terminalone.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;
import com.mediamath.terminalone.Connection;
import com.mediamath.terminalone.exceptions.ClientException;
import com.mediamath.terminalone.exceptions.ParseException;
import com.mediamath.terminalone.models.BudgetFlight;
import com.mediamath.terminalone.models.Campaign;
import com.mediamath.terminalone.models.CampaignCustomBrainSelection;
import com.mediamath.terminalone.models.Contract;
import com.mediamath.terminalone.models.FieldError;
import com.mediamath.terminalone.models.JsonPostErrorResponse;
import com.mediamath.terminalone.models.JsonResponse;
import com.mediamath.terminalone.models.SiteList;
import com.mediamath.terminalone.models.Strategy;
import com.mediamath.terminalone.models.StrategyAudienceSegment;
import com.mediamath.terminalone.models.StrategyConcept;
import com.mediamath.terminalone.models.StrategyDayPart;
import com.mediamath.terminalone.models.StrategyTarget;
import com.mediamath.terminalone.models.StrategyTargetValues;
import com.mediamath.terminalone.models.StrategyTargetingSegment;
import com.mediamath.terminalone.models.T1Entity;
import com.mediamath.terminalone.models.T1Error;
import com.mediamath.terminalone.models.T1File;
import com.mediamath.terminalone.models.T1Meta;
import com.mediamath.terminalone.models.T1User;
import com.mediamath.terminalone.models.TOneASCreativeAssetsApprove;
import com.mediamath.terminalone.models.TOneASCreativeAssetsUpload;
import com.mediamath.terminalone.models.TPASCreativeBatchApprove;
import com.mediamath.terminalone.models.TPASCreativeUpload;
import com.mediamath.terminalone.models.User;
import com.mediamath.terminalone.models.UserPermissions;
import com.mediamath.terminalone.models.VendorContract;
import com.mediamath.terminalone.models.VideoCreative;
import com.mediamath.terminalone.models.VideoCreativeResponse;
import com.mediamath.terminalone.models.VideoCreativeUploadResponse;
import com.mediamath.terminalone.models.VideoCreativeUploadStatus;
import com.mediamath.terminalone.models.ZipCodes;
import com.mediamath.terminalone.models.ZipCodesJsonResponse;
import com.mediamath.terminalone.models.helper.StrategyHelper;
import com.mediamath.terminalone.models.helper.TOneCreativeAssetsApproveHelper;
import com.mediamath.terminalone.models.helper.TPasCreativeUploadBatchHelper;
import com.mediamath.terminalone.models.helper.VideoCreativeHelper;
import com.mediamath.terminalone.utils.Constants;
import com.mediamath.terminalone.utils.T1JsonToObjParser;
import com.mediamath.terminalone.utils.Utility;

public class PostService {

	private static final String CREATIVE_ASSETS_APPROVE = "creative_assets/approve";

	private static final String CREATIVES_UPLOAD = "creatives/upload/";

	private static final String CREATIVES = "/creatives";

	private static final String CREATIVES_STR = "creatives";

	private static final String CREATIVE_ASSETS = "creative_assets";
	
    private static final String COULD_NOT_PARSE_RESPONSE = "Could not parse response";

	private static final Logger logger = LoggerFactory.getLogger(PostService.class);

	private T1Service t1Service = null;

	private Connection connection = null;

	private T1User user = null;

	private static final String YYYY_MM_DD_T_HH_MM_SS = "yyyy-MM-dd'T'HH:mm:ss";

	public PostService() {
		// default constructor.
	}

	/**
	 * Constructor for Initializing PostService.
	 * 
	 * @param connection
	 *            requries a connection Object.
	 * @param user
	 *            requires a valid user session.
	 * @param t1Service
	 *            requires T1Service service object.
	 */
	public PostService(Connection connection, T1User user, T1Service t1Service) {
		this.connection = connection;
		this.user = user;
		this.t1Service = t1Service;
	}

	/**
	 * form a URI for the given entity.
	 * 
	 * @param entity
	 *            T can be any given entity implementing T1Entity.
	 * @return StringBuilder object.
	 */
	private <T extends T1Entity> StringBuilder getUri(T entity) {
		String entityName = entity.getEntityname();

		return new StringBuilder(Constants.entityPaths.get(entityName));
	}

	/**
	 * 
	 * @param entity
	 *            expects T1Entity
	 * @return T1Entity
	 * @throws ClientException
	 * @throws ParseException
	 */
	public T1Entity save(T1Entity entity) throws ClientException, ParseException {

		if (entity == null)
			return null;

		JsonResponse<? extends T1Entity> finalJsonResponse;

		StringBuilder uri = getUri(entity);
		String uriPath = entity.getUri();

		if (checkString(uriPath)) {
			uri.append(uriPath);
		}

		String path = t1Service.constructUrl(uri, Constants.entityPaths.get(entity.getEntityname()));

		String responseString = getResponseString(entity, path);

		finalJsonResponse = getJsonResponse(entity, responseString);

		if (finalJsonResponse == null)
			return null;

		return finalJsonResponse.getData();
	}

	private String getResponseString(T1Entity entity, String path) throws ClientException {
		String uriPath = entity.getUri();
		Response responseObj;
		if(entity.getEntityname() != null && ("Contract").equals(entity.getEntityname()) && (uriPath!=null && !uriPath.isEmpty())){
			responseObj = this.connection.put(path, entity.getForm(), this.user);
		}else{
			responseObj = this.connection.post(path, entity.getForm(), this.user);
		}
		
		return readPostResponseToString(responseObj);

	}

	private String getStrategyResponseString(Strategy entity, String path) throws ClientException {
		Response responseObj = this.connection.post(path, StrategyHelper.getForm(entity), this.user);
		return readPostResponseToString(responseObj);
	}
	
	private String readPostResponseToString(Response responseObj) throws ClientException {
		String response = responseObj.readEntity(String.class);
		JsonPostErrorResponse error = jsonPostErrorResponseParser(response, responseObj);
		if (error != null)
			throwExceptions(error);

		return response;
	}

	/**Saves User entity and its permissions.
	 * 
	 * @param entity
	 * @return
	 * @throws ClientException
	 * @throws ParseException
	 */
	public User save(User entity) throws ClientException, ParseException {

		User userSaved = null;	
		
		if (entity == null){
			return null;
		}else{
			StringBuilder uri = getUri(entity);
			if (entity.getId() > 0) {
				uri.append("/");
				uri.append(entity.getId());
			}

			if (entity.getId() > 0 && !entity.getPermissionsList().isEmpty()) {
				uri.append("/permissions");
				entity.setEntityname("UserPermissions");
			}

			JsonResponse<? extends T1Entity> finalJsonResponse;

			String path = t1Service.constructUrl(uri, Constants.entityPaths.get(entity.getEntityname()));

			String responseString = getResponseString(entity, path);

			finalJsonResponse = getJsonResponse(entity, responseString);

			if (finalJsonResponse == null)
				return null;
			
			if (finalJsonResponse.getData() instanceof User) {
				userSaved = (User) finalJsonResponse.getData();
			} else if (finalJsonResponse.getData() instanceof UserPermissions){
				UserPermissions uPerm = (UserPermissions) finalJsonResponse.getData();
				userSaved = (User) uPerm.getUser();
				userSaved.setPermissions(uPerm.getPermissions());
			}

		}
				

		return userSaved;
	}
	
	/**
	 * saves a Strategy entity.
	 * 
	 * @param entity
	 *            expects a Strategy entity.
	 * @return Strategy object.
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 * @throws ParseException
	 *             a parse exception is thrown when the response cannot be
	 *             parsed.
	 * @throws IOException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Strategy save(Strategy entity) throws ClientException, ParseException {

		Strategy strategy = null;
		JsonResponse<? extends T1Entity> finalJsonResponse;
		if (entity != null) {
			StringBuilder uri = getUri(entity);

			if (entity.getId() > 0) {
				uri.append("/");
				uri.append(entity.getId());
			}

			if (entity.getId() > 0 && !entity.getStrategyDomainRestrictions().isEmpty()) {
				uri.append("/domain_restrictions");
			}

			if (entity.getId() > 0 && !entity.getAudienceSegments().isEmpty()
					&& entity.getAudienceSegmentExcludeOp() != null && entity.getAudienceSegmentIncludeOp() != null) {
				uri.append("/audience_segments");
			}

			if (entity.getId() > 0 && !entity.getSiteLists().isEmpty()) {
				uri.append("/site_lists");
			}

			if (entity.getId() > 0 && !entity.getStrategyTargetingSegments().isEmpty()
					&& entity.getTargetingSegmentExcludeOp() != null && entity.getTargetingSegmentIncludeOp() != null) {
				uri.append("/targeting_segments");
			}

			if (entity.getId() > 0 && !entity.getTargetValues().isEmpty()) {
				uri.append("/target_values");
			}

			if (entity.getId() > 0 && !entity.getStrategyDayParts().isEmpty()) {
				uri.append("/day_parts");
			}

			if (entity.getId() > 0 && !entity.getDealIds().isEmpty()) {
				uri.append("/deals");
			}

			if (entity.getId() > 0 && entity.isCopyStrategy() == true) {
				uri.append("/copy");
			}

			if (entity.getId() > 0 && entity.getTargetDimensions() != null) {
				uri.append("/target_dimensions");
				if (entity.getTargetDimensions().getId() > 0) {
					uri.append("/" + String.valueOf(entity.getTargetDimensions().getId()));
				}
			}
			
			String path = t1Service.constructUrl(uri, Constants.entityPaths.get(entity.getEntityname()));
			
			String responseString;
			responseString = getStrategyResponseString(entity, path);

			finalJsonResponse = getJsonResponse(entity, responseString);

			if (finalJsonResponse == null)
				return null;

			if (finalJsonResponse.getData() == null)
				return null;

			if (finalJsonResponse.getData() instanceof ArrayList) {
				List dataList = (ArrayList) finalJsonResponse.getData();
				if (dataList.get(0) != null && dataList.get(0) instanceof StrategyAudienceSegment) {
					strategy = entity;
					strategy.getStrategyAudienceSegments().clear();
					strategy.setStrategyAudienceSegments(dataList);
				}
				if (dataList.get(0) != null && dataList.get(0) instanceof StrategyTargetingSegment) {
					strategy = entity;
					strategy.getStrategyTargetingSegments().clear();
					strategy.setStrategyTargetingSegments(dataList);
				}
				if (dataList.get(0) != null && dataList.get(0) instanceof SiteList) {
					strategy = entity;
					strategy.getSiteLists().clear();
					strategy.setSiteLists(dataList);
				}
				if (dataList.get(0) != null && dataList.get(0) instanceof StrategyDayPart) {
					strategy = entity;
					strategy.getStrategyDayParts().clear();
					strategy.setStrategyDayParts(dataList);
				}
				if (dataList.get(0) != null && dataList.get(0) instanceof StrategyTarget) {
					strategy = entity;
					strategy.getStrategyTarget().clear();
					strategy.setStrategyTarget(dataList);
				}
			} else {
				if (finalJsonResponse.getData() instanceof StrategyTargetValues) {
					strategy = entity;
					strategy.setStrategyTargetValues((StrategyTargetValues) finalJsonResponse.getData());
				} else {
					strategy = (Strategy) finalJsonResponse.getData();
				}
			}
		}
		return strategy;
	}
	
	/**
	 * saves a ZipCodes against Strategy entity.
	 * 
	 * @param entity
	 *            expects a ZipCodes entity.
	 * @return ZipCodesJsonResponse object.
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 * @throws ParseException
	 *             a parse exception is thrown when the response cannot be
	 *             parsed.
	 * @throws IOException 
	 */
	public ZipCodesJsonResponse save(ZipCodes entity) throws ClientException, ParseException {

		ZipCodesJsonResponse finalJsonResponse = null;
		if (entity != null) {
			StringBuilder uri = new StringBuilder("strategies");

			if (entity.getStrategyId() > 0) {
				uri.append("/");
				uri.append(entity.getStrategyId());
				uri.append("/target_postcodes");
			}

			String path = t1Service.constructUrl(uri, Constants.entityPaths.get(entity.getEntityname()));

			String responseString;

			FormDataMultiPart multipart = new FormDataMultiPart();
			FileDataBodyPart filePart = new FileDataBodyPart("file", new File(entity.getFile()));

			multipart.field("restriction", entity.getRestriction().toString())
					.field("validate_only", Utility.getOneOrZero(entity.isValidateOnly()))
					.field("ignore_errors", Utility.getOneOrZero(entity.isIgnoreErrors()))
					.field("active", Utility.getOneOrZero(entity.isActive())).bodyPart(filePart);

			Response responseObj = this.connection.post(path, multipart, this.user);
			try {
				multipart.close();
			} catch (IOException e) {
				logger.error(" "+e.getCause());
			}

			responseString = responseObj.readEntity(String.class);
			JsonPostErrorResponse error = jsonPostErrorResponseParser(responseString, responseObj);
			if (error != null)
				throwExceptions(error);

			try {
				Gson gson = new GsonBuilder().setDateFormat(YYYY_MM_DD_T_HH_MM_SS).create();
				finalJsonResponse = gson.fromJson(responseString, ZipCodesJsonResponse.class);
			} catch (JsonParseException parseException) {
				Utility.logStackTrace(parseException);
				throw new ParseException(COULD_NOT_PARSE_RESPONSE);
			}

		}
		return finalJsonResponse;
	}

	/**
	 * Copies Strategies in bulk from one campaign to another campaign.
	 * 
	 * @param entity
	 *            expects a Strategy entity.
	 * @return JsonResponse<? extends T1Entity> object.
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 * @throws ParseException
	 *             a parse exception is thrown when the response cannot be
	 *             parsed.
	 */
	public JsonResponse<? extends T1Entity> bulkCopy(Strategy entity) throws ClientException, ParseException {

		JsonResponse<? extends T1Entity> finalJsonResponse = null;
		if (entity != null) {
			StringBuilder uri = getUri(entity);

			if (entity.getFromCampaignId() > 0 && entity.getToCampaignId() > 0) {
				uri.append("/bulk_copy");
			} else {
				return null;
			}

			String path = t1Service.constructUrl(uri, Constants.entityPaths.get(entity.getEntityname()));

			String responseString = getStrategyResponseString(entity, path);

			finalJsonResponse = getJsonResponse(entity, responseString);
		}

		if (finalJsonResponse != null && finalJsonResponse.getData() == null) {
			return null;
		}

		return finalJsonResponse;

	}

	/**
	 * saves a Campaign entity.
	 * 
	 * @param entity
	 *            expects a Campaign entity.
	 * @return Strategy object.
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 * @throws ParseException
	 *             a parse exception is thrown when the response cannot be
	 *             parsed.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Campaign save(Campaign entity) throws ClientException, ParseException {

		Campaign campaign = null;
		JsonResponse<? extends T1Entity> finalJsonResponse;
		if (entity != null) {
			StringBuilder uri = getUri(entity);

			if (entity.getId() > 0) {
				uri.append("/");
				uri.append(entity.getId());
			}

			if (entity.getId() > 0 && entity.getMargins().size() > 0) {
				uri.append("/margins");
			}

			if (entity.getId() > 0 && !entity.getSiteLists().isEmpty()) {
				uri.append("/site_lists");
			}

			if (entity.getId() > 0 && entity.isCopyCampaign() == true) {
				uri.append("/copy");
			}
			
			if (entity.getId() > 0 && entity.getBudgetFlights().size()==1) {
				uri.append("/budget_flights");
				if(entity.getBudgetFlights().get(0).getId()> 0){
					uri.append("/");
					uri.append(entity.getBudgetFlights().get(0).getId());
				}
				if(entity.getBudgetFlights().get(0).isDeleted()){
					uri.append("/delete");
				}
			}
			
			if (entity.getId() > 0 && entity.getBudgetFlights().size()>1) {
				uri.append("/budget_flights/bulk");
			}
			
			if (entity.getId() > 0 && entity.getCampaignCustomBrainSelection().size()==1) {
				uri.append("/custom_brain_selections");
				if(entity.getCampaignCustomBrainSelection().get(0)!=null && entity.getCampaignCustomBrainSelection().get(0).getId()>0){
					uri.append("/");
					uri.append(entity.getCampaignCustomBrainSelection().get(0).getId());
				}
				if(entity.getCampaignCustomBrainSelection().get(0)!=null && entity.getCampaignCustomBrainSelection().get(0).isDeleted()){
					uri.append("/delete");
				}
			}
			
			if (entity.getId() > 0 && entity.getCampaignCustomBrainSelection().size()>1) {
				uri.append("/custom_brain_selections/bulk");
			}

			String path = t1Service.constructUrl(uri, Constants.entityPaths.get(entity.getEntityname()));

			String responseString = getResponseString(entity, path);

			finalJsonResponse = getJsonResponse(entity, responseString);

			if (finalJsonResponse == null)
				return null;

			if (finalJsonResponse.getData() == null)
				return null;

			if (finalJsonResponse.getData() instanceof ArrayList) {
				ArrayList dataList = (ArrayList) finalJsonResponse.getData();
				if (dataList.get(0) != null && dataList.get(0) instanceof SiteList) {
					campaign = entity;
					campaign.getSiteLists().clear();
					campaign.setSiteLists(dataList);
				}
				else if (dataList.get(0) != null && dataList.get(0) instanceof BudgetFlight) {
					campaign = entity;
					campaign.getBudgetFlights().clear();
					campaign.setBudgetFlights(dataList);
				}
				else if (dataList.get(0) != null && dataList.get(0) instanceof CampaignCustomBrainSelection) {
					campaign = entity;
					campaign.getCampaignCustomBrainSelection().clear();
					campaign.setCampaignCustomBrainSelection(dataList);
				}
			} else {
				
				if (finalJsonResponse.getData() instanceof BudgetFlight) {
					campaign = entity;
					BudgetFlight bfData =  (BudgetFlight)finalJsonResponse.getData();
					campaign.getBudgetFlights().clear();
					campaign.getBudgetFlights().add(bfData);
				}
				else if (finalJsonResponse.getData() instanceof CampaignCustomBrainSelection) {
					campaign = entity;
					CampaignCustomBrainSelection ccbsData =  (CampaignCustomBrainSelection)finalJsonResponse.getData();
					campaign.getCampaignCustomBrainSelection().clear();
					campaign.getCampaignCustomBrainSelection().add(ccbsData);
				}
				else{
					campaign = (Campaign) finalJsonResponse.getData();
				}
			}
		}

		return campaign;
	}

	private JsonResponse<? extends T1Entity> getJsonResponse(T1Entity entity, String response)
			throws ClientException, ParseException {

		JsonResponse<? extends T1Entity> finalJsonResponse;

		T1JsonToObjParser parser = new T1JsonToObjParser();

		if (response.isEmpty())
			return null;

		finalJsonResponse = parsePostData(response, parser, entity);

		return finalJsonResponse;
	}

	/**
	 * saves a VideoCreative entity. this is the First Call to Create a Video
	 * Creative.
	 * 
	 * @param entity
	 *            expects a VideoCreative entity.
	 * 
	 * @return VideoCreativeResponse entity.
	 * 
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 */
	public VideoCreativeResponse save(VideoCreative entity) throws ClientException {

		VideoCreativeResponse videoCreative = null;

		if (entity != null) {

			StringBuilder path = new StringBuilder(
					t1Service.getApi_base() + t1Service.getVideoCreativeURL() + CREATIVES);

			if (entity.getCreativeId() > 0) {
				path.append("/" + entity.getCreativeId());
			}

			String videoCreativePath = path.toString();

			Response responseObj = this.connection.post(videoCreativePath, VideoCreativeHelper.getJson(entity),
					this.user);

			String response = responseObj.readEntity(String.class);

			T1JsonToObjParser parser = new T1JsonToObjParser();

			if (response.isEmpty())
				return null;

			JsonPostErrorResponse error = jsonPostErrorResponseParser(response, responseObj);

			if (error != null)
				throwExceptions(error);

			VideoCreativeResponse parsedVideoCreativeResponse = parser.parseVideoCreative(response);
			if (parsedVideoCreativeResponse != null && parsedVideoCreativeResponse.getCreativeId() != null
					&& !parsedVideoCreativeResponse.getCreativeId().isEmpty()) {

				videoCreative = parsedVideoCreativeResponse;
			}
			else if(entity.getCreativeId() > 0){
				videoCreative = new VideoCreativeResponse();
				videoCreative.setCreativeId(String.valueOf(entity.getCreativeId()));
				videoCreative.setStatus(parsedVideoCreativeResponse.getStatus());
			}
		}
		return videoCreative;
	}

	/**
	 * Gets the Video Creative Upload status
	 * 
	 * @param creativeId
	 *            requires a creativeId string.
	 * 
	 * @return VideoCreativeUploadStatus object.
	 * 
	 */
	public VideoCreativeUploadStatus getVideoCreativeUploadStatus(String creativeId) {
		VideoCreativeUploadStatus uploadStatus = null;
		if (checkString(creativeId)) {
			StringBuilder path = new StringBuilder(t1Service.getApi_base() + t1Service.getVideoCreativeURL() + CREATIVES
					+ "/" + creativeId + "/status");
			String pathstr = path.toString();
			logger.info(pathstr);
			String response = this.connection.get(path.toString(), this.user);
			T1JsonToObjParser parser = new T1JsonToObjParser();
			uploadStatus = parser.parseVideoCreativeUploadStatus(response);
		}
		return uploadStatus;
	}

	/**
	 * this method uploads the given video creative file.
	 * 
	 * @param filePath
	 *            path to the actual file data.
	 * 
	 * @param fileName
	 *            name of the file.
	 * 
	 * @param creativeId
	 *            requires a creative id string.
	 * 
	 * @return VideoCreativeResponse object.
	 * 
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 * @throws IOException
	 * 
	 * @throws ParseException
	 *             a parse exception is thrown when the response cannot be
	 *             parsed.
	 */
	public VideoCreativeResponse uploadVideoCreative(String filePath, String fileName, String creativeId)
			throws ClientException, IOException {

		VideoCreativeResponse videoCreative = null;

		if (checkString(filePath) && checkString(creativeId) && checkString(fileName)) {

			StringBuilder path = new StringBuilder(t1Service.getApi_base() + t1Service.getVideoCreativeURL() + CREATIVES
					+ "/" + creativeId + "/upload?fileName=" + fileName);

			String finalPath = path.toString();
			InputStream inputStream = null;

			// post binary only
			try {
				inputStream = new FileInputStream(new File(filePath));
				Response responseObj = this.connection.post(finalPath, inputStream, this.user);

				String response = responseObj.readEntity(String.class);

				T1JsonToObjParser parser = new T1JsonToObjParser();

				if (response.isEmpty()) {
					inputStream.close();
					return null;
				}

				JsonPostErrorResponse error = jsonPostErrorResponseParser(response, responseObj);

				if (error != null)
					throwExceptions(error);

				VideoCreativeResponse parsedVideoCreativeResponse = parser.parseVideoCreative(response);
				if (parsedVideoCreativeResponse != null && parsedVideoCreativeResponse.getStatus() != null) {
					parsedVideoCreativeResponse.setCreativeId(creativeId);
					videoCreative = parsedVideoCreativeResponse;
				}

			} finally {
				if (inputStream != null) {
					inputStream.close();
				}
			}
		}
		return videoCreative;
	}
	
	/** TEMPORARY SOLUTION, DO BE DEPRECATED ONCE VIDEO CREATIVE UPLOAD PROBLEM SOLVES
	 * 
	 * this method uploads the given video creative file.
	 * 
	 * @param filePath
	 *            path to the actual file data.
	 * 
	 * @param fileName
	 *            name of the file.
	 * 
	 * @param key
	 *            requires a key string.
	 *            
	 * @param host
	 *            requires a host string.
	 * 
	 * @return VideoCreativeResponse object.
	 * 
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 * @throws IOException
	 * 
	 * @throws ParseException
	 *             a parse exception is thrown when the response cannot be
	 *             parsed.
	 */
	public VideoCreativeUploadResponse videoCreativeUpload(String filePath, String fileName, String key, String host)
			throws ClientException, IOException {

		VideoCreativeUploadResponse videoCreative = null;

		if (checkString(filePath) && checkString(key) && checkString(fileName)) {

			String finalPath = "https://"+host;
			final File fileToUpload=new File(filePath);
			try {
				//add key
				final FormDataMultiPart multiPart=new FormDataMultiPart();
				multiPart.field("key", key);
				//add file
				if(!fileToUpload.equals(null)){
					FormDataBodyPart fileDataBodyPart = new FormDataBodyPart(FormDataContentDisposition.name("file").fileName(fileName).build(),fileToUpload, MediaType.APPLICATION_OCTET_STREAM_TYPE);
					multiPart.bodyPart(fileDataBodyPart);
				}
				Response responseObj = this.connection.post(finalPath, multiPart, this.user);
				String response = responseObj.readEntity(String.class);
				//close resources
				multiPart.close();

				T1JsonToObjParser parser = new T1JsonToObjParser();

				if (response.isEmpty()) {
					return null;
				}

				JsonPostErrorResponse error = jsonPostErrorResponseParser(response, responseObj);

				if (error != null)
					throwExceptions(error);

				VideoCreativeUploadResponse parsedVideoCreativeResponse = parser.parseVideoCreativeUpload(response);
				if (parsedVideoCreativeResponse != null && parsedVideoCreativeResponse.getStatus() != null) {
					videoCreative = parsedVideoCreativeResponse;
				}

			} finally {
				
			}
		}
		return videoCreative;
	}

	private boolean checkString(String str) {
		return str != null && !str.isEmpty();
	}

	/**
	 * deletes a StrategyConcept entity.
	 * 
	 * @param strategyConcept
	 *            expects a StrategyConcept entity.
	 * 
	 * @return JsonResponse<? extends T1Entity> returns a jsonResponse of type T
	 *         entity.
	 * 
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 * 
	 * @throws ParseException
	 *             a parse exception is thrown when the response cannot be
	 *             parsed.
	 */
	public JsonResponse<? extends T1Entity> delete(StrategyConcept strategyConcept)
			throws ClientException, ParseException {
		StringBuilder path = new StringBuilder();

		if (strategyConcept.getId() > 0) {
			path.append(Constants.entityPaths.get("StrategyConcept"));
			path.append("/");
			path.append(strategyConcept.getId());
			path.append("/delete");
		}

		String finalPath = t1Service.constructUrl(path, Constants.entityPaths.get("StrategyConcept"));

		Form strategyConceptForm = new Form();

		Response responseObj = connection.post(finalPath, strategyConceptForm, this.user);
		String response = responseObj.readEntity(String.class);

		T1JsonToObjParser parser = new T1JsonToObjParser();

		return parser.parseJsonToObj(response, Constants.getEntityType.get("strategy_concepts"));
	}

	/**
	 * deletes a StrategyDayPart entity.
	 * 
	 * @param strategyDayPart
	 *            expects a StrategyDayPart entity.
	 * @return JsonResponse<? extends T1Entity> returns a JsonResponse of type T
	 *         entity.
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 * 
	 * @throws ParseException
	 *             a parse exception is thrown when the response cannot be
	 *             parsed.
	 */
	public JsonResponse<? extends T1Entity> delete(StrategyDayPart strategyDayPart)
			throws ClientException, ParseException {
		StringBuilder path = new StringBuilder();

		if (strategyDayPart.getId() > 0) {
			path.append(Constants.entityPaths.get("StrategyDayPart"));
			path.append("/");
			path.append(strategyDayPart.getId());
			path.append("/delete");
		}

		String finalPath = t1Service.constructUrl(path, Constants.entityPaths.get("StrategyDayPart"));

		Form strategyConceptForm = new Form();
		if (strategyDayPart.getVersion() >= 0) {
			strategyConceptForm.param("version", String.valueOf(strategyDayPart.getVersion()));
		}

		Response responseObj = connection.post(finalPath, strategyConceptForm, this.user);
		String response = responseObj.readEntity(String.class);

		T1JsonToObjParser parser = new T1JsonToObjParser();

		return parser.parseJsonToObj(response, Constants.getEntityType.get("strategy_day_parts"));
	}
	
	/** Delete Contract Entity
	 * 
	 * @param contract
	 * @return JsonResponse<? extends T1Entity> returns a JsonResponse of type T
	 *         entity.
	 * @throws ClientException 
	 * @throws ParseException 
	 */
	public JsonResponse<? extends T1Entity> delete(Contract contract) throws ClientException, ParseException {
		StringBuilder path = new StringBuilder();

		if (contract.getId() > 0) {
			path.append(Constants.entityPaths.get("Contract"));
			path.append("/");
			path.append(contract.getId());
		}

		String finalPath = t1Service.constructUrl(path, Constants.entityPaths.get("Contract"));
		
		Form contractForm = new Form();
		if (contract.getVersion() >= 0) {
			contractForm.param("version", String.valueOf(contract.getVersion()));
		}
		Response responseObj = connection.delete(finalPath,contractForm, this.user);
		
		String response = responseObj.readEntity(String.class);

		T1JsonToObjParser parser = new T1JsonToObjParser();

		return getJsonResponse(contract, response);
	}
	
	/** Delete Vendor Contract Entity
	 * 
	 * @param Vendor Contract Entity
	 * @return JsonResponse<? extends T1Entity> returns a JsonResponse of type T
	 *         entity.
	 * @throws ClientException 
	 * @throws ParseException 
	 */
	public JsonResponse<? extends T1Entity> delete(VendorContract contract) throws ClientException, ParseException {
		StringBuilder path = new StringBuilder();

		if (contract.getId() > 0) {
			path.append(Constants.entityPaths.get("VendorContract"));
			path.append("/");
			path.append(contract.getId());
			path.append("/delete");
		}

		String finalPath = t1Service.constructUrl(path, Constants.entityPaths.get("VendorContract"));
		
		Form contractForm = new Form();
		if (contract.getVersion() >= 0) {
			contractForm.param("version", String.valueOf(contract.getVersion()));
		}
		Response responseObj = connection.post(finalPath, contractForm, this.user);
		String response = responseObj.readEntity(String.class);

		return getJsonResponse(contract, response);
	}

	/**
	 * saves a 3PAS creative upload.
	 * 
	 * @param filePath
	 *            path to the file.
	 * 
	 * @param fileName
	 *            name of file.
	 * 
	 * @param name
	 *            name of 3pas upload.
	 * 
	 * @return TPASCreativeUpload object.
	 * 
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 * 
	 * @throws ParseException
	 *             a parse exception is thrown when the response cannot be
	 *             parsed.
	 */
	public TPASCreativeUpload saveTPASCreativeUpload(String filePath, String fileName, String name)
			throws ClientException, IOException {

		TPASCreativeUpload tpasCreativeUploadResponse = null;
		StringBuilder uri = new StringBuilder("creatives/upload");
		String response = saveCreativeUploads(uri, filePath, name, fileName, CREATIVES_STR);
		T1JsonToObjParser parser = new T1JsonToObjParser();
		if (checkString(response)) {
			tpasCreativeUploadResponse = parseTPASCreativeUploadData(response, parser);
		}
		return tpasCreativeUploadResponse;
	}

	private String saveCreativeUploads(StringBuilder uri, String filePath, String name, String fileName,
			String collection) throws ClientException, IOException {
		if (filePath == null && name == null && fileName == null) {
			throw new ClientException("please enter a valid filename and file path");
		}

		String path = t1Service.constructUrl(uri, collection);

		FileDataBodyPart filePart = new FileDataBodyPart("file", new File(filePath));

		FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
		FormDataMultiPart multipart = (FormDataMultiPart) formDataMultiPart.field("filename", fileName)
				.field("name", name).bodyPart(filePart);
		
		Response responseObj = this.connection.post(path, multipart, this.user);
		String response = responseObj.readEntity(String.class);

		formDataMultiPart.close();
		multipart.close();

		return response;
	}
	
	
	private String saveCreativeMultipleUploads(StringBuilder uri, List<T1File> fileList,
			String collection) throws ClientException, IOException {
		if (fileList == null) {
			throw new ClientException("please enter a valid filename and file path");
		}

		String path = t1Service.constructUrl(uri, collection);

		FormDataMultiPart multipart = new FormDataMultiPart();

		for(T1File t1File : fileList){
			if(t1File!=null && (t1File.getFile()!=null && t1File.getFilename()!=null && t1File.getName()!=null)){
			FileDataBodyPart filePart = new FileDataBodyPart("file", new File(t1File.getFile()));
			
			multipart.field("filename", t1File.getFilename()).field("name", t1File.getName()).bodyPart(filePart);
			}
		}

		Response responseObj = this.connection.post(path, multipart, this.user);
		String response = responseObj.readEntity(String.class);

		multipart.close();

		return response;
	}

	private TPASCreativeUpload parseTPASCreativeUploadData(String response, T1JsonToObjParser parser) {
		TPASCreativeUpload finalResponse;
		finalResponse = parser.parse3PasCreativeUploadResponseTOObj(response);
		return finalResponse;
	}

	/**
	 * this API call in the Bulk 3PAS process saves particular creative out of a
	 * given batch to the T1 database. second call to save the 3pas creative
	 * upload
	 * 
	 * @param entity
	 *            requires a TPASCreativeBatchApprove entity.
	 * 
	 * @return JsonResponse<? extends T1Entity> returns a JsonResponse of type
	 *         T.
	 * 
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 * 
	 * @throws ParseException
	 *             a parse exception is thrown when the response cannot be
	 *             parsed.
	 * 
	 * @throws IOException
	 *             is thrown when this method is unable to create a file
	 *             containing report data.
	 */
	public JsonResponse<? extends T1Entity> saveTPASCreativeUploadBatch(TPASCreativeBatchApprove entity)
			throws ClientException, IOException, ParseException {
		FormDataMultiPart formData = new FormDataMultiPart();
		JsonResponse<? extends T1Entity> finalJsonResponse = null;
		if (entity != null) {

			StringBuilder uri = new StringBuilder(CREATIVES_UPLOAD);

			if (entity.getBatchId() != null && !entity.getBatchId().isEmpty()) {
				uri.append(entity.getBatchId());
				String path = t1Service.constructUrl(uri, CREATIVES_STR);
				TPasCreativeUploadBatchHelper.getMultiPartForm(entity, formData);
				Response responseObj = this.connection.post(path, formData, this.user);
				String response = responseObj.readEntity(String.class);

				T1JsonToObjParser parser = new T1JsonToObjParser();
				JsonPostErrorResponse jsonPostResponse;

				jsonPostResponse = jsonPostErrorResponseParser(response, responseObj);
				if (jsonPostResponse == null) {
					finalJsonResponse = parsePostData(response, parser, null);
				} else {
					throwExceptions(jsonPostResponse);
				}
			}
		}

		formData.close();

		return finalJsonResponse;
	}

	/**
	 * saves upload to T1AS. first call to upload the file. <br>
	 * <br>
	 * example: <br>
	 * <br>
	 * <i>saveT1ASCreativeAssetsUpload(
	 * "C:\\exampledir1\\exampledir2\\samplefile.txt", "samplefile"
	 * ,"samplefile");</i>
	 * 
	 * @param filePath
	 *            path to the file data.
	 * 
	 * @param fileName
	 *            a filename
	 * 
	 * @param name
	 *            String.
	 * 
	 * @return TOneASCreativeAssetsUpload object.
	 * 
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 * 
	 * @throws IOException
	 *             is thrown when this method is unable to create a file
	 *             containing report data.
	 */
	public TOneASCreativeAssetsUpload saveTOneASCreativeAssets(String filePath, String fileName, String name)
			throws ClientException, IOException {

		TOneASCreativeAssetsUpload assetsUploadResponse = null;
		StringBuilder uri = new StringBuilder("creative_assets/upload");
		String response = saveCreativeUploads(uri, filePath, name, fileName, CREATIVE_ASSETS);
		T1JsonToObjParser parser = new T1JsonToObjParser();
		if (checkString(response)) {
			assetsUploadResponse = parseTOneASCreativeAssetsUploadData(response, parser);
		}
		return assetsUploadResponse;
	}
	
	/**saves multiple file upload to T1AS. first call to upload the HTML5 file along with backup files. <br>
	 * <br>
	 * example: <br>
	 * <br>
	 * <i>saveT1ASCreativeAssetsUpload(List<T1File> fileList);</i>
	 * 
	 * @param fileList
	 * 			a valid list of T1File object is required.
	 * 			T1File Object, can accept name, filename and filepath	
	 * 
	 * @return TOneASCreativeAssetsUpload
	 * @throws ClientException
	 * @throws IOException
	 */
	public TOneASCreativeAssetsUpload saveTOneASCreativeAssets(List<T1File> fileList)
			throws ClientException, IOException {

		TOneASCreativeAssetsUpload assetsUploadResponse = null;
		StringBuilder uri = new StringBuilder("creative_assets/upload");
		String response = saveCreativeMultipleUploads(uri, fileList, CREATIVE_ASSETS);
		T1JsonToObjParser parser = new T1JsonToObjParser();
		if (checkString(response)) {
			assetsUploadResponse = parseTOneASCreativeAssetsUploadData(response, parser);
		}
		return assetsUploadResponse;
	}

	/**
	 * parses TOneASCreativeAssetsUploadData response and creates a
	 * TOneASCreativeAssetsUpload object.
	 * 
	 * @param response
	 *            string json to parse.
	 * @param parser
	 *            requires an instance of T1JsonToObjParser
	 * @return TOneASCreativeAssetsUpload object.
	 */
	private TOneASCreativeAssetsUpload parseTOneASCreativeAssetsUploadData(String response, T1JsonToObjParser parser) {
		TOneASCreativeAssetsUpload finalResponse;
		finalResponse = parser.parseTOneASCreativeAssetsUploadResponseTOObj(response);
		return finalResponse;
	}

	/**
	 * saves TOneASCreativeAssetsApprove entity.
	 * 
	 * @param entity
	 *            expects a TOneASCreativeAssetsApprove entity.
	 * 
	 * @return JsonResponse<? extends T1Entity> returns JsonResponse of type T
	 * 
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 * @throws IOException
	 *             exception is thrown when the multipart form is left open.
	 */
	public JsonResponse<? extends T1Entity> saveTOneASCreativeAssetsApprove(TOneASCreativeAssetsApprove entity)
			throws ClientException, IOException {

		FormDataMultiPart formData = new FormDataMultiPart();

		JsonResponse<? extends T1Entity> parsedJsonResponse = null;

		if (entity == null) {
			formData.close();
			return null;
		}

		StringBuilder uri = new StringBuilder(CREATIVE_ASSETS_APPROVE);
		String path = t1Service.constructUrl(uri, CREATIVE_ASSETS);
		TOneCreativeAssetsApproveHelper.getMultiPartForm(entity, formData);
		Response responseObj = this.connection.post(path, formData, this.user);
		String jsonResponse = responseObj.readEntity(String.class);
		T1JsonToObjParser parser = new T1JsonToObjParser();
		JsonPostErrorResponse jsonPostErrorResponse;
		jsonPostErrorResponse = jsonPostErrorResponseParser(jsonResponse, responseObj);

		if (jsonPostErrorResponse == null) {
			parsedJsonResponse = parser.parseTOneASCreativeAssetsApproveResponse(jsonResponse);
		} else {
			throwExceptions(jsonPostErrorResponse);
		}

		return parsedJsonResponse;
	}

	/**
	 * parses error response of a POST activity.
	 * 
	 * @param responseStr
	 *            requires a response JSON string
	 * @param responseObj
	 *            requires a Response object.
	 * @return JsonPostErrorResponse entity.
	 */
	public JsonPostErrorResponse jsonPostErrorResponseParser(String responseStr, Response responseObj) {
		JsonParser parser1 = new JsonParser();
		JsonObject obj = parser1.parse(responseStr).getAsJsonObject();

		JsonElement errorsElement = obj.get("errors");
		JsonElement errorElement = obj.get("error");
		JsonElement metaElement = obj.get("meta");

		JsonPostErrorResponse errorResponse = null;

		if (checkErrorAndErrorsElement(errorsElement, errorElement)
				|| checkResponseStatusAndMetaElement(responseObj, metaElement)) {

			errorResponse = new JsonPostErrorResponse();
			GsonBuilder builder = new GsonBuilder();
			builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES);
			builder.setDateFormat(YYYY_MM_DD_T_HH_MM_SS);

			Gson gson = builder.create();

			parseErrorsElement(errorsElement, errorResponse, gson);

			parseErrorElement(errorElement, errorResponse, gson);

			parseMetaElement(responseObj, metaElement, errorResponse, gson);
		}

		return errorResponse;
	}

	private boolean checkResponseStatusAndMetaElement(Response responseObj, JsonElement metaElement) {
		return responseObj != null && responseObj.getStatus() == 403 && metaElement != null;
	}

	private boolean checkErrorAndErrorsElement(JsonElement errorsElement, JsonElement errorElement) {
		return ((errorsElement != null && errorsElement.isJsonArray() && errorsElement.getAsJsonArray().size() > 0) || errorElement != null);
	}

	private void parseMetaElement(Response responseObj, JsonElement metaElement, JsonPostErrorResponse errorResponse,
			Gson gson) {
		if (metaElement != null) {
			if (responseObj != null && responseObj.getStatus() == 403) {
				T1Meta meta = gson.fromJson(metaElement, T1Meta.class);
				errorResponse.setMeta(meta);
			}
		}
	}

	private void parseErrorElement(JsonElement errorElement, JsonPostErrorResponse errorResponse, Gson gson) {
		if (errorElement != null) {
			T1Error error = gson.fromJson(errorElement, T1Error.class);
			errorResponse.setError(error);
		}
	}

	private void parseErrorsElement(JsonElement errorsElement, JsonPostErrorResponse errorResponse, Gson gson) {
		if (errorsElement != null) {
			if (errorsElement.isJsonObject()) {
				T1Error errors = gson.fromJson(errorsElement, T1Error.class);
				// specific to video creatives
				if (checkErrorContent(errors) && checkErrorField(errors) && checkErrorMessage(errors)) {

					GsonBuilder videoBuilder = new GsonBuilder();
					videoBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
					videoBuilder.setDateFormat(YYYY_MM_DD_T_HH_MM_SS);

					Gson vidgson = videoBuilder.create();

					errors = vidgson.fromJson(errorsElement, T1Error.class);
				}
				errorResponse.setErrors(errors);
			} else if (errorsElement.isJsonArray()) {
				JsonArray array = errorsElement.getAsJsonArray();
				JsonArray newArray = new JsonArray();

				for (int i = 0; i < array.size(); i++) {
					if (!(array.get(i) instanceof JsonPrimitive)) {
						newArray.add(array.get(i));

					}
				}
				if (newArray.size() > 0) {
					Type type = new TypeToken<ArrayList<T1Error>>() {
					}.getType();
					List<T1Error> errors = gson.fromJson(newArray, type);
					errorResponse.setErrors(errors);
				}
			}
		}
	}

	private boolean checkErrorMessage(T1Error errors) {
		return errors.getMessage() == null;
	}

	private boolean checkErrorField(T1Error errors) {
		return errors.getField() == null && errors.getFieldError() == null;
	}

	private boolean checkErrorContent(T1Error errors) {
		return errors != null && errors.getContent() == null;
	}

	/**
	 * this method is responsible to parse the JsonPostErrorResponse and throw
	 * relevant exceptions.
	 * 
	 * @param jsonPostResponse
	 *            object.
	 * @throws ClientException
	 *             a client exception is thrown if any error occurs.
	 */
	public void throwExceptions(JsonPostErrorResponse jsonPostResponse) throws ClientException {

		StringBuilder strbuilder = null;

		strbuilder = parseErrorException(jsonPostResponse, strbuilder);

		strbuilder = parseErrorsException(jsonPostResponse, strbuilder);

		strbuilder = parseMetaException(jsonPostResponse, strbuilder);
		// throw the error to client
		if (strbuilder != null) {
			throw new ClientException(strbuilder.toString());
		}
	}

	private StringBuilder parseMetaException(JsonPostErrorResponse jsonPostResponse, StringBuilder strBuilder) {
		if (jsonPostResponse != null && jsonPostResponse.getMeta() != null
				&& checkPostResponseMetaStatus(jsonPostResponse)) {
			if (strBuilder == null) {
				strBuilder = new StringBuilder(jsonPostResponse.getMeta().getStatus());
			} else {
				strBuilder.append(", Status: " + jsonPostResponse.getMeta().getStatus());
			}
		}
		return strBuilder;
	}

	private boolean checkPostResponseMetaStatus(JsonPostErrorResponse jsonPostResponse) {
		return jsonPostResponse.getMeta().getStatus() != null && !jsonPostResponse.getMeta().getStatus().isEmpty();
	}

	private StringBuilder parseErrorsException(JsonPostErrorResponse jsonPostResponse, StringBuilder strBuilder) {

		if (jsonPostResponse == null)
			return strBuilder;

		if (jsonPostResponse.getErrors() == null)
			return strBuilder;

		if (jsonPostResponse.getErrors() instanceof ArrayList) {
			@SuppressWarnings("unchecked")
			ArrayList<T1Error> al = (ArrayList<T1Error>) jsonPostResponse.getErrors();
			for (T1Error error : al) {
				if (error.getMessage() != null) {
					strBuilder = formErrorString(strBuilder, error);
				}
				if (error.getFieldError() != null) {
					for (FieldError fe : error.getFieldError()) {
						strBuilder = formFieldErrorString(strBuilder, fe);
					}
				}
			}
		} else {

			T1Error error = (T1Error) jsonPostResponse.getErrors();

			if (error.getMessage() != null) {
				strBuilder = formErrorString(strBuilder, error);
			}
			if (error.getFieldError() != null) {
				for (FieldError fe : error.getFieldError()) {
					strBuilder = formFieldErrorString(strBuilder, fe);
				}
			}
		}

		return strBuilder;
	}

	private StringBuilder formFieldErrorString(StringBuilder strBuilder, FieldError fe) {
		if (strBuilder == null) {
			strBuilder = new StringBuilder(
					"Name: " + fe.getName() + ", Code: " + fe.getCode() + ", Error: " + fe.getError());
		} else {
			strBuilder.append(", " + "Name: " + fe.getName() + ", Code: " + fe.getCode() + ", Error: " + fe.getError());
		}
		return strBuilder;
	}

	private StringBuilder formErrorString(StringBuilder strBuilder, T1Error error) {
		if (strBuilder == null) {
			strBuilder = new StringBuilder(error.getMessage()); // add error
																// field
		} else {
			strBuilder.append(", " + error.getMessage());
		}
		return strBuilder;
	}

	private StringBuilder parseErrorException(JsonPostErrorResponse jsonPostResponse, StringBuilder strBuilder) {
		if (jsonPostResponse != null && jsonPostResponse.getError() != null) {
			T1Error error = jsonPostResponse.getError();

			if (error.getContent() != null) {
				strBuilder = new StringBuilder("Content: " + error.getContent());
			}

			if (error.getField() != null) {
				if (strBuilder == null) {
					strBuilder = new StringBuilder("Field: " + error.getField());
				} else {
					strBuilder.append(", " + "Field: " + error.getField());
				}
			}

			if (error.getMessage() != null) {
				if (strBuilder == null) {
					strBuilder = new StringBuilder("Message: " + error.getMessage());
				} else {
					strBuilder.append(", " + "Message: " + error.getMessage());
				}
			}

			if (error.getType() != null) {
				if (strBuilder == null) {
					strBuilder = new StringBuilder("Type: " + error.getType());
				} else {
					strBuilder.append(", " + "Type: " + error.getType());
				}
			}
		}
		return strBuilder;
	}

	private <T extends T1Entity> JsonResponse<? extends T1Entity> parsePostData(String response,
			T1JsonToObjParser parser, T entity) throws ParseException {

		// parse the string to gson objs
		JsonResponse<? extends T1Entity> finalJsonResponse = null;
		JsonElement element = parser.getDataFromResponse(response);
		if (element != null) {
			if (element.isJsonArray()) {
				// do something
				JsonArray dataList = element.getAsJsonArray();
				if (dataList.size() > 0) {
					JsonElement data = dataList.get(0);
					finalJsonResponse = parseJsonArrayDataList(response, parser, finalJsonResponse, data);
				}

			} else if (element.isJsonObject()) {
				JsonObject obj = element.getAsJsonObject();
				String entityType = getEntityType(obj);
				if(entityType!=null){
					finalJsonResponse = parser.parseJsonToObj(response, Constants.getEntityType.get(entityType));
				}else{
					finalJsonResponse = parser.parseJsonToObj(response,Constants.getEntityType.get(entity.getEntityname().toLowerCase()));
				}
			}
		} else {
			if (entity != null) {
				finalJsonResponse = parser.parseJsonToObj(response,
						Constants.getEntityType.get(entity.getEntityname().toLowerCase()));
				if (finalJsonResponse != null) {
					finalJsonResponse.setData(null);
				}
			}
		}
		return finalJsonResponse;
	}

	private String getEntityType(JsonObject obj) {
		JsonElement entityTypeElement = obj.get("entity_type");
		if (entityTypeElement == null && obj.get("exclude_op") != null && obj.get("include_op") != null
				&& obj.get("enabled") != null) {
			return "strategy_target_values";
		} else {
			return ((entityTypeElement != null) ? entityTypeElement.getAsString() : null);
		}
	}

	private JsonResponse<? extends T1Entity> parseJsonArrayDataList(String response, T1JsonToObjParser parser,
			JsonResponse<? extends T1Entity> finalJsonResponse, JsonElement data) throws ParseException {
		String entityType;
		if (data != null) {
			JsonObject dataObj = data.getAsJsonObject();
			if (dataObj != null) {
				JsonElement entityTypeElem = dataObj.get("entity_type");
				if (entityTypeElem != null) {
					entityType = entityTypeElem.getAsString();
					finalJsonResponse = parseArrayJsonResponse(response, parser, finalJsonResponse, entityType);
				}
			}
		}
		return finalJsonResponse;
	}

	private JsonResponse<? extends T1Entity> parseArrayJsonResponse(String response, T1JsonToObjParser parser,
			JsonResponse<? extends T1Entity> finalJsonResponse, String entityType) throws ParseException {
		if (checkString(entityType) && Constants.getListoFEntityType.get(entityType) != null) {
			finalJsonResponse = parser.parseJsonToObj(response, Constants.getListoFEntityType.get(entityType));
		}
		return finalJsonResponse;
	}
}
