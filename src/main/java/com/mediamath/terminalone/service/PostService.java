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
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

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
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;
import com.mediamath.terminalone.Connection;
import com.mediamath.terminalone.Exceptions.ClientException;
import com.mediamath.terminalone.Exceptions.ParseException;
import com.mediamath.terminalone.models.Advertiser;
import com.mediamath.terminalone.models.Agency;
import com.mediamath.terminalone.models.AtomicCreative;
import com.mediamath.terminalone.models.Campaign;
import com.mediamath.terminalone.models.Concept;
import com.mediamath.terminalone.models.FieldError;
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
import com.mediamath.terminalone.models.T1Meta;
import com.mediamath.terminalone.models.T1Response;
import com.mediamath.terminalone.models.TOneASCreativeAssetsApprove;
import com.mediamath.terminalone.models.TOneASCreativeAssetsUpload;
import com.mediamath.terminalone.models.ThreePASCreativeBatchApprove;
import com.mediamath.terminalone.models.ThreePASCreativeUpload;
import com.mediamath.terminalone.models.VideoCreative;
import com.mediamath.terminalone.models.VideoCreativeResponse;
import com.mediamath.terminalone.models.VideoCreativeUploadStatus;
import com.mediamath.terminalone.models.helper.AdvertiserHelper;
import com.mediamath.terminalone.models.helper.AgencyHelper;
import com.mediamath.terminalone.models.helper.AtomicCreativeHelper;
import com.mediamath.terminalone.models.helper.CampaignHelper;
import com.mediamath.terminalone.models.helper.ConceptHelper;
import com.mediamath.terminalone.models.helper.OrganizationHelper;
import com.mediamath.terminalone.models.helper.PixelHelper;
import com.mediamath.terminalone.models.helper.StrategyConceptHelper;
import com.mediamath.terminalone.models.helper.StrategyHelper;
import com.mediamath.terminalone.models.helper.StrategySupplySourceHelper;
import com.mediamath.terminalone.models.helper.TOneCreativeAssetsApproveHelper;
import com.mediamath.terminalone.models.helper.ThreePasCreativeUploadBatchHelper;
import com.mediamath.terminalone.models.helper.VideoCreativeHelper;
import com.mediamath.terminalone.utils.Constants;
import com.mediamath.terminalone.utils.T1JsonToObjParser;

public class PostService {
	
	private static final Logger logger = LoggerFactory.getLogger(PostService.class);
	
	private static T1Service t1Service = new T1Service();
	
	private Connection connection = null; 
	
	private T1Response user = null;
	
	
	private static final String YYYY_MM_DD_T_HH_MM_SS = "yyyy-MM-dd'T'HH:mm:ss";
	
	public PostService() {}
	
	public PostService(Connection pConnection, T1Response pUser) {
		this.connection = pConnection;
		this.user = pUser;
	}
	
	private <T extends T1Entity> StringBuffer getURI(T entity) {
		String entityName = entity.getEntityname();
		StringBuffer uri = new StringBuffer(Constants.entityPaths.get(entityName));
		return uri;
	}

	public Agency save(Agency entity) throws ClientException, ParseException {

		Agency agency = null;
		
		if(entity != null) {
		
			JsonResponse<? extends T1Entity>  finalJsonResponse = null;
			
			StringBuffer uri = getURI(entity);
			
			if (entity.getId() > 0) {
				uri.append("/");
				uri.append(entity.getId());
			}
			
			String path = t1Service.constructURL(uri);
			
			Response responseObj = this.connection.post(path, AgencyHelper.getForm(entity), this.user);
			
			String response = responseObj.readEntity(String.class);
			
			// parse response
			T1JsonToObjParser parser = new T1JsonToObjParser();
			if(!response.isEmpty()) {
				JsonPostErrorResponse error = jsonPostErrorResponseParser(response,responseObj);
				if(error == null) {
					finalJsonResponse = parsePostData(response, parser, entity);
					if(finalJsonResponse != null && finalJsonResponse.getData() != null) {
						agency = (Agency) finalJsonResponse.getData();
					}
				} else {
					throwExceptions(error);
				}
			}
			
		}
		return agency;
	}

	private <T extends T1Entity> JsonResponse<? extends T1Entity> parsePostData(String response, T1JsonToObjParser parser, T entity ) 
	throws ParseException {
	
		// parse the string to gson objs
		JsonResponse<? extends T1Entity>  finalJsonResponse= null;
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
				}
				
			} else if (element.isJsonObject()) {
				JsonObject obj = element.getAsJsonObject();
				JsonElement entityTypeElement = obj.get("entity_type");
				String entityType =entityTypeElement.getAsString();
				finalJsonResponse = parser.parseJsonToObj(response, Constants.getEntityType.get(entityType));
			}
		} else if(element == null) {
			if(entity != null) {
				finalJsonResponse = parser.parseJsonToObj(response, Constants.getEntityType.get(entity.getEntityname().toLowerCase()));
				if(finalJsonResponse != null) {
					finalJsonResponse.setData(null);
				}
			}
		}
		return finalJsonResponse;
	}

	public Advertiser save(Advertiser entity) throws ClientException, ParseException {
		Advertiser advertiser = null;
		
		if(entity != null) {
			JsonResponse<? extends T1Entity>  finalJsonResponse = null;

			StringBuffer uri = getURI(entity);
			
			if (entity.getId() > 0) {
				uri.append("/");
				uri.append(entity.getId());
			}
			
			String path = t1Service.constructURL(uri);

			Response responseObj = this.connection.post(path, AdvertiserHelper.getForm(entity), this.user);
			String response = responseObj.readEntity(String.class);
			// parse response
			T1JsonToObjParser parser = new T1JsonToObjParser();

			if(!response.isEmpty()) {
				JsonPostErrorResponse error = jsonPostErrorResponseParser(response,responseObj);
				if(error == null) {
					finalJsonResponse = parsePostData(response, parser, entity);
					if(finalJsonResponse != null && finalJsonResponse.getData() != null) {
						advertiser = (Advertiser) finalJsonResponse.getData();
					}
				} else {
					throwExceptions(error);
				}
			}
			
		}
		return advertiser;
	}
	
	public Strategy save(Strategy entity) throws ClientException, ParseException {
		
		Strategy strategy = null;
		JsonResponse<? extends T1Entity>  finalJsonResponse = null;
		if(entity != null) {
			StringBuffer uri = getURI(entity);
			
			if (entity.getId() > 0) {
				uri.append("/");
				uri.append(entity.getId());
			}
			
			if (entity.getId() > 0 && entity.getStrategy_domain_restrictions().size() > 0) {
				uri.append("/domain_restrictions");
			}
			
			if (entity.getId() > 0 && entity.getAudience_segments().size() > 0 && entity.getAudience_segment_exclude_op()!=null && entity.getAudience_segment_include_op()!=null) {
				uri.append("/audience_segments");
			}
			
			String path = t1Service.constructURL(uri);

			Response responseObj = this.connection.post(path, StrategyHelper.getForm(entity), this.user);
			String response = responseObj.readEntity(String.class);
			// parse response
			T1JsonToObjParser parser = new T1JsonToObjParser();

			if(!response.isEmpty()) {
				// parse error
				JsonPostErrorResponse error = jsonPostErrorResponseParser(response,responseObj);
				if(error == null) {
					finalJsonResponse = parsePostData(response, parser, entity);
					if(finalJsonResponse != null && finalJsonResponse.getData() != null) {
						strategy = (Strategy) finalJsonResponse.getData();
					} 
					
				} else {
					throwExceptions(error);
				}
			}
		}
		return strategy;
	}
	
	public StrategyConcept save(StrategyConcept entity) throws ClientException, ParseException {
		
		StrategyConcept strategyConcept = null;
		
		if(entity != null) {
			JsonResponse<? extends T1Entity>  finalJsonResponse = null;

			StringBuffer uri = getURI(entity);
			
			if (entity.getId() > 0) {
				uri.append("/");
				uri.append(entity.getId());
			}
			
			String path = t1Service.constructURL(uri);

			Response responseObj = this.connection.post(path, StrategyConceptHelper.getForm(entity), this.user);
			String response = responseObj.readEntity(String.class);
			// parse response
			T1JsonToObjParser parser = new T1JsonToObjParser();

			if(!response.isEmpty()) {
				JsonPostErrorResponse error = jsonPostErrorResponseParser(response,responseObj);
				if(error == null) {
					finalJsonResponse = parsePostData(response, parser, entity);
					if(finalJsonResponse != null && finalJsonResponse.getData() != null) {
						strategyConcept = (StrategyConcept) finalJsonResponse.getData();
					}
				} else {
					throwExceptions(error);
				}
			}
			
		}
		return strategyConcept;
	}
	
	public StrategySupplySource save(StrategySupplySource entity) throws ClientException, ParseException {

		StrategySupplySource strategySupplySource = null;
		
		if(entity != null) {
			
			JsonResponse<? extends T1Entity>  finalJsonResponse = null;

			StringBuffer uri = getURI(entity);
			
			if (entity.getId() > 0) {
				uri.append("/");
				uri.append(entity.getId());
			}
			
			String path = t1Service.constructURL(uri);

			Response responseObj = this.connection.post(path, StrategySupplySourceHelper.getForm(entity), this.user);
			String response = responseObj.readEntity(String.class);
			T1JsonToObjParser parser = new T1JsonToObjParser();

			if(!response.isEmpty()) {
				JsonPostErrorResponse error = jsonPostErrorResponseParser(response,responseObj);
				if(error == null) {
					finalJsonResponse = parsePostData(response, parser, entity);
					if(finalJsonResponse != null && finalJsonResponse.getData() != null) {
						strategySupplySource = (StrategySupplySource) finalJsonResponse.getData();
					}
				} else {
					throwExceptions(error);
				}
			}
			
			
		}
		return strategySupplySource;
	}
	
	public Organization save(Organization entity) throws ClientException, ParseException {

		Organization org = null;
		
		if(entity != null) {
			JsonResponse<? extends T1Entity>  finalJsonResponse = null;

			StringBuffer uri = getURI(entity);
			
			if (entity.getId() > 0) {
				uri.append("/");
				uri.append(entity.getId());
			}
			
			String path = t1Service.constructURL(uri);

			Response responseObj = this.connection.post(path, OrganizationHelper.getForm(entity), this.user);
			String response = responseObj.readEntity(String.class);
			// parse response
			T1JsonToObjParser parser = new T1JsonToObjParser();
			if(!response.isEmpty()) {
				JsonPostErrorResponse error = jsonPostErrorResponseParser(response,responseObj);
				if(error == null) {
					finalJsonResponse = parsePostData(response, parser, entity);
					if(finalJsonResponse != null && finalJsonResponse.getData() != null) {
						org = (Organization) finalJsonResponse.getData();
					}
				} else {
					throwExceptions(error);
				}
			}
			
		}
		return org;
	}
	
	public Pixel save(Pixel entity) throws ClientException, ParseException {
	
		Pixel px = null;
		
		if(entity != null) {
			JsonResponse<? extends T1Entity>  finalJsonResponse = null;

			StringBuffer uri = getURI(entity);
			
			if (entity.getId() > 0) {
				uri.append("/");
				uri.append(entity.getId());
			}
			
			String path = t1Service.constructURL(uri);

			Response responseObj = this.connection.post(path, PixelHelper.getForm(entity), this.user);
			String response = responseObj.readEntity(String.class);
			// parse response
			T1JsonToObjParser parser = new T1JsonToObjParser();

			if(!response.isEmpty()) {
				JsonPostErrorResponse error = jsonPostErrorResponseParser(response,responseObj);
				if(error == null) {
					finalJsonResponse = parsePostData(response, parser, entity);
					if(finalJsonResponse != null && finalJsonResponse.getData() != null) {
						px = (Pixel) finalJsonResponse.getData();
					}
				} else {
					throwExceptions(error);
				}
			}
			
		}
		return px;
	}
	
	public Campaign save(Campaign entity) throws ParseException, ClientException {

		Campaign campaign = null;
		
		if (entity != null) {
		
			JsonResponse<? extends T1Entity> finalJsonResponse = null;

			StringBuffer uri = getURI(entity);

			if (entity.getId() > 0 && entity.getMargins().size() > 0) {
				uri.append("/");
				uri.append(entity.getId());
				uri.append("/margins");
			}
			String path = t1Service.constructURL(uri);
			
			Response responseObj = this.connection.post(path, CampaignHelper.getForm(entity), this.user);
			String response = responseObj.readEntity(String.class);
			
			T1JsonToObjParser parser = new T1JsonToObjParser();

			if(!response.isEmpty()) {
				JsonPostErrorResponse error = jsonPostErrorResponseParser(response,responseObj);
				if(error == null) {
					finalJsonResponse = parsePostData(response, parser, entity);
					if(finalJsonResponse != null && finalJsonResponse.getData() != null) {
						campaign = (Campaign) finalJsonResponse.getData();
					}
				} else {
					throwExceptions(error);
				}
			}
		}
		return campaign == null ? entity : campaign;
	}
	
	public Concept save(Concept entity) throws ParseException, ClientException {
		
		Concept concept = null;

		if (entity != null) {
			JsonResponse<? extends T1Entity> finalJsonResponse = null;

			StringBuffer uri = getURI(entity);

			String path = t1Service.constructURL(uri);
			// post
			Response responseObj = this.connection.post(path, ConceptHelper.getForm(entity), this.user);
			String response = responseObj.readEntity(String.class);
			T1JsonToObjParser parser = new T1JsonToObjParser();

			if(!response.isEmpty()) {
				JsonPostErrorResponse error = jsonPostErrorResponseParser(response,responseObj);
				if(error == null) {
					finalJsonResponse = parsePostData(response, parser, entity);
					if(finalJsonResponse != null && finalJsonResponse.getData() != null) {
						concept = (Concept) finalJsonResponse.getData();
					}
				} else {
					throwExceptions(error);
				}
				
			}
		}
		return concept == null ? entity : concept;
	}
	
	public VideoCreativeResponse save(VideoCreative entity) throws ClientException {

		VideoCreativeResponse videoCreative = null;

		if (entity != null) {

			StringBuffer path = new StringBuffer(
					t1Service.getApi_base() + t1Service.getVideoCreativeURL() + "/creatives");

			if (entity.getCreativeId() > 0) {
				path.append("/" + entity.getCreativeId());
			}

			String videoCreativePath = path.toString();

			Response responseObj = this.connection.post(videoCreativePath, VideoCreativeHelper.getJson(entity), this.user);
			String response = responseObj.readEntity(String.class);
			
			T1JsonToObjParser parser = new T1JsonToObjParser();

			if (!response.isEmpty()) {
				JsonPostErrorResponse error = jsonPostErrorResponseParser(response,responseObj);
				if (error == null) {
					VideoCreativeResponse parsedVideoCreativeResponse = parser.parseVideoCreative(response);
					if (parsedVideoCreativeResponse != null && parsedVideoCreativeResponse.getCreativeId() != null
							&& !parsedVideoCreativeResponse.getCreativeId().isEmpty()) {
						videoCreative = parsedVideoCreativeResponse;
					}
				} else {
					throwExceptions(error);
				}
			}

		}
		return videoCreative;
	}

	@Deprecated
	public VideoCreativeResponse getVideoCreativeUploadToken(VideoCreativeResponse videoCreative) throws ParseException {
		VideoCreativeResponse parsedResponse = null;
		if (videoCreative != null 
				&& videoCreative.getCreativeId() != null
				&& !videoCreative.getCreativeId().isEmpty()) {

			StringBuffer path = new StringBuffer(t1Service.getApi_base() + t1Service.getVideoCreativeURL() + "/creatives" + "/" + videoCreative.getCreativeId() + "/upload");
			
			logger.info(path.toString());
			
			String response = this.connection.get(path.toString(), this.user);
			T1JsonToObjParser parser = new T1JsonToObjParser();
			parsedResponse = parser.parseVideoCreative(response);
			parsedResponse.setCreativeId(videoCreative.getCreativeId());
		}

		return parsedResponse;

	}
	
	public VideoCreativeUploadStatus getVideoCreativeUploadStatus(String creativeId) {
		VideoCreativeUploadStatus uploadStatus = null;
		if(creativeId != null && !creativeId.isEmpty()) {
			StringBuffer path = new StringBuffer(t1Service.getApi_base() + t1Service.getVideoCreativeURL() + "/creatives" + "/" + creativeId + "/status");
			logger.info(path.toString());
			String response = this.connection.get(path.toString(), this.user);
			T1JsonToObjParser parser = new T1JsonToObjParser();
			uploadStatus = parser.parseVideoCreativeUploadStatus(response);
		}
		return uploadStatus;
	}
	
	public VideoCreativeResponse uploadVideoCreative(String filePath, String fileName, String creativeId) throws ClientException {
		
		VideoCreativeResponse videoCreative = null;
		
		if(filePath != null 
				&& !filePath.isEmpty() 
				&& creativeId != null 
				&& !creativeId.isEmpty() 
				&& fileName != null
				&& !fileName.isEmpty()) {
	

			StringBuffer path = new StringBuffer(t1Service.getApi_base() 
													+ t1Service.getVideoCreativeURL() 
													+ "/creatives" 
													+ "/" 
													+ creativeId 
													+ "/upload?fileName="
													+ fileName);
			
			String finalPath = path.toString();
			
			//post binary only
			FileDataBodyPart filePart = new FileDataBodyPart("file", new File(filePath));
			FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
			final FormDataMultiPart multipart = (FormDataMultiPart) formDataMultiPart.bodyPart(filePart);
			
			Response responseObj = this.connection.post(finalPath, multipart, this.user);
			String response = responseObj.readEntity(String.class);
			
			T1JsonToObjParser parser = new T1JsonToObjParser();
			if(!response.isEmpty()) {
				JsonPostErrorResponse error = jsonPostErrorResponseParser(response,responseObj);
				if(error == null) {
					VideoCreativeResponse parsedVideoCreativeResponse = parser.parseVideoCreative(response);
					if(parsedVideoCreativeResponse != null && parsedVideoCreativeResponse.getStatus() != null) {
						parsedVideoCreativeResponse.setCreativeId(creativeId);
						videoCreative = parsedVideoCreativeResponse;
					}
				} else {
					throwExceptions(error);
				}
			}
		}
		return videoCreative;
	}
	

	public JsonResponse<? extends T1Entity> delete(StrategyConcept strategyConcept) throws ClientException, ParseException  {
		StringBuffer path = new StringBuffer();

		if(strategyConcept.getId() > 0){
				path.append(Constants.entityPaths.get("StrategyConcept"));
				path.append("/");
				path.append(strategyConcept.getId());
				path.append("/delete");
		}

		String finalPath = t1Service.constructURL(path);
		
		Form strategyConceptForm = new Form();
		
		Response responseObj  = connection.post(finalPath, strategyConceptForm, this.user);
		String response = responseObj.readEntity(String.class);
		
		T1JsonToObjParser parser = new T1JsonToObjParser();
		JsonResponse<? extends T1Entity> jsonResponse = parser.parseJsonToObj(response, Constants.getEntityType.get("strategy_concepts"));
		
		
		return jsonResponse;
	}
	
	public JsonResponse<? extends T1Entity> delete(StrategyDayPart strategyDayPart) throws ClientException, ParseException  {
		StringBuffer path = new StringBuffer();

		if(strategyDayPart.getId() > 0){
				path.append(Constants.entityPaths.get("strategyDayPart"));
				path.append("/");
				path.append(strategyDayPart.getId());
				path.append("/delete");
		}

		String finalPath = t1Service.constructURL(path);
		
		Form strategyConceptForm = new Form();
		if(strategyDayPart.getVersion() > 0){
			strategyConceptForm.param("version", String.valueOf(strategyDayPart.getVersion()));
		}
		
		Response responseObj  = connection.post(finalPath, strategyConceptForm, this.user);
		String response = responseObj.readEntity(String.class);
		
		T1JsonToObjParser parser = new T1JsonToObjParser();
		JsonResponse<? extends T1Entity> jsonResponse = parser.parseJsonToObj(response, Constants.getEntityType.get("strategy_day_parts"));
		
		
		return jsonResponse;
	}

	
	public AtomicCreative save(AtomicCreative entity) throws ParseException, ClientException {
		AtomicCreative atomicCreative = null;

		if (entity != null) {
			JsonResponse<? extends T1Entity> finalJsonResponse = null;

			StringBuffer uri = getURI(entity);

			String path = t1Service.constructURL(uri);
			// post
			Response responseObj = this.connection.post(path, AtomicCreativeHelper.getForm(entity), this.user);
			String response = responseObj.readEntity(String.class);
			
			T1JsonToObjParser parser = new T1JsonToObjParser();

			if(!response.isEmpty()) {
				JsonPostErrorResponse error = jsonPostErrorResponseParser(response,responseObj);
				if(error == null) {
					finalJsonResponse = parsePostData(response, parser, entity);
					if(finalJsonResponse != null && finalJsonResponse.getData() != null) {
						atomicCreative = (AtomicCreative) finalJsonResponse.getData();
					}
				} else {
					throwExceptions(error);
				}
			}
		}
		return atomicCreative == null ? entity : atomicCreative;
	}
	
	public ThreePASCreativeUpload save3pasCreativeUpload(String filePath, String fileName, String name) throws ClientException, IOException {
		
		ThreePASCreativeUpload threePassCreativeUploadResponse = null;
		
		if(filePath != null && name != null && fileName != null) {
			 
			// formt the url
			StringBuffer uri = new StringBuffer("creatives/upload");
			String path = t1Service.constructURL(uri);

			//form the data
			FileDataBodyPart filePart = new FileDataBodyPart("file", new File(filePath));
			FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
			final FormDataMultiPart multipart = (FormDataMultiPart) formDataMultiPart.field("filename", fileName)
																	.field("name", name)
																	.bodyPart(filePart);
			
			Response responseObj = this.connection.post(path, multipart, this.user);
			String response = responseObj.readEntity(String.class);
			
			T1JsonToObjParser parser = new T1JsonToObjParser();
			
			// parse
			// create object and send the response to user.
			if(response != null && !response.isEmpty()) {
				threePassCreativeUploadResponse = parse3PasCreativeUploadData(response, parser);
			}
			
			formDataMultiPart.close();
			multipart.close();
		} else {
			throw new ClientException("please enter a valid filename and file path");
		}
		
		return threePassCreativeUploadResponse;
	}
	
	private ThreePASCreativeUpload parse3PasCreativeUploadData(String response, T1JsonToObjParser parser) {
		ThreePASCreativeUpload finalResponse = null;
		finalResponse = parser.parse3PasCreativeUploadResponseTOObj(response);
		return finalResponse;
	}
	
	
	public JsonResponse<? extends T1Entity> save3pasCreativeUploadBatch(ThreePASCreativeBatchApprove entity) throws ClientException, IOException, ParseException {
		FormDataMultiPart formData = new FormDataMultiPart();
		JsonResponse<? extends T1Entity> finalJsonResponse = null;
		if (entity != null) {
			
			StringBuffer uri = new StringBuffer("creatives/upload/");
			
			if(entity.getBatchId() != null && !entity.getBatchId().isEmpty()) {
				uri.append(entity.getBatchId());
				String path = t1Service.constructURL(uri);
				ThreePasCreativeUploadBatchHelper.getMultiPartForm(entity, formData);
				Response responseObj = this.connection.post(path, formData, this.user);
				String response = responseObj.readEntity(String.class);
				
				T1JsonToObjParser parser = new T1JsonToObjParser();
				JsonPostErrorResponse jsonPostResponse = null;
				
				jsonPostResponse = jsonPostErrorResponseParser(response,responseObj);
				if (jsonPostResponse == null) {
					finalJsonResponse = parsePostData(response, parser, null);
				} else {
					throwExceptions(jsonPostResponse);
				}
			}
		}
		if(formData != null) {
			formData.close();
		}
		return finalJsonResponse;
	}

	public TOneASCreativeAssetsUpload saveT1asCreativeAssets(String filePath, String fileName, String name) throws ClientException, IOException {
		TOneASCreativeAssetsUpload assetsUploadResponse = null;
		if(filePath != null && name != null && fileName != null) {
			 
			// formt the url
			StringBuffer uri = new StringBuffer("creative_assets/upload");
			String path = t1Service.constructURL(uri);

			//form the data
			FileDataBodyPart filePart = new FileDataBodyPart("file", new File(filePath));
			FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
			final FormDataMultiPart multipart = (FormDataMultiPart) formDataMultiPart.field("filename", fileName)
																	.field("name", name)
																	.bodyPart(filePart);
			
			Response responseObj = this.connection.post(path, multipart, this.user);
			String response = responseObj.readEntity(String.class);
			
			T1JsonToObjParser parser = new T1JsonToObjParser();
			
			// parse
			// create object and send the response to user.
			if(response != null && !response.isEmpty()) {
				assetsUploadResponse = parseTOneASCreativeAssetsUploadData(response, parser);
			}
			
			formDataMultiPart.close();
			multipart.close();
		} else {
			throw new ClientException("please enter a valid filename and file path");
		}
		return assetsUploadResponse;
	}
	
	
	private TOneASCreativeAssetsUpload parseTOneASCreativeAssetsUploadData(String response, T1JsonToObjParser parser) {
		TOneASCreativeAssetsUpload finalResponse = null;
		finalResponse = parser.parseTOneASCreativeAssetsUploadResponseTOObj(response);
		return finalResponse;
	}
	
	public JsonResponse<? extends T1Entity> saveTOneASCreativeAssetsApprove(TOneASCreativeAssetsApprove entity) throws ClientException {
		FormDataMultiPart formData = new FormDataMultiPart();
		//TOneASCreativeAssetsApproveResponse response = null;
		JsonResponse<? extends T1Entity> parsedJsonResponse = null;
		if (entity != null) {
			
			StringBuffer uri = new StringBuffer("creative_assets/approve");
			
			String path = t1Service.constructURL(uri);
			
			TOneCreativeAssetsApproveHelper.getMultiPartForm(entity, formData);
			
			Response responseObj = this.connection.post(path, formData, this.user);
			String jsonResponse = responseObj.readEntity(String.class);
			
			T1JsonToObjParser parser = new T1JsonToObjParser();
			JsonPostErrorResponse jsonPostErrorResponse = null;
			
			jsonPostErrorResponse = jsonPostErrorResponseParser(jsonResponse,responseObj);
			
			if (jsonPostErrorResponse == null) {
				
				parsedJsonResponse = parser.parseTOneASCreativeAssetsApproveResponse(jsonResponse);
				/*if (parsedJsonResponse.getData() instanceof TOneASCreativeAssetsApproveResponse) {
					response = (TOneASCreativeAssetsApproveResponse) parsedJsonResponse.getData();
				}*/
				
			} else {
				throwExceptions(jsonPostErrorResponse);
			}
		}
		return parsedJsonResponse;
	}
	
	public JsonPostErrorResponse jsonPostErrorResponseParser(String responseStr) {
		return jsonPostErrorResponseParser(responseStr, null);
	}
	
	public JsonPostErrorResponse jsonPostErrorResponseParser(String responseStr, Response responseObj) {
		JsonParser parser1 = new JsonParser();
		JsonObject obj = parser1.parse(responseStr).getAsJsonObject();
		
		JsonElement errorsElement = obj.get("errors");
		JsonElement errorElement = obj.get("error");
		JsonElement metaElement = obj.get("meta");
		
		JsonPostErrorResponse errorResponse = null;
		
		if(errorsElement != null || errorElement != null || (responseObj != null && responseObj.getStatus() == 403 && metaElement != null )) {
			errorResponse = new JsonPostErrorResponse();
			GsonBuilder builder = new GsonBuilder();
			builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES);
			builder.setDateFormat(YYYY_MM_DD_T_HH_MM_SS);
			
			Gson g = builder.create();

			if (errorsElement != null) {
				if (errorsElement.isJsonNull()) {

				} else if (errorsElement.isJsonObject()) {
					T1Error errors = g.fromJson(errorsElement, T1Error.class);
					// specific to video creatives
					if(errors != null 
							&& errors.getContent() == null 
							&& errors.getField() == null 
							&& errors.getFieldError() == null 
							&& errors.getMessage() == null) {
						
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
					
					for(int i = 0; i < array.size(); i++) {
						if(!(array.get(i) instanceof JsonPrimitive)) {
							newArray.add(array.get(i));
							
						}
					}
					if(newArray.size() > 0) {
						errorsElement = newArray;
						Type t =  new TypeToken<ArrayList<T1Error>>(){}.getType();
						List<T1Error> errors = g.fromJson(errorsElement, t);
						errorResponse.setErrors(errors);
					}
				}
			}

			if (errorElement != null) {
				T1Error error = g.fromJson(errorElement, T1Error.class);
				errorResponse.setError(error);
			}

			if(metaElement != null) {
				if(responseObj.getStatus() == 403) {
					T1Meta meta = g.fromJson(metaElement, T1Meta.class);
					errorResponse.setMeta(meta);	
				} 
			}
		}
		
		return errorResponse;
	}
	
	public void throwExceptions(JsonPostErrorResponse jsonPostResponse) throws ClientException {
	
		StringBuffer strbuff = null;

		if (jsonPostResponse != null && jsonPostResponse.getError() != null) {
			T1Error error = jsonPostResponse.getError();

			if (error.getContent() != null) {
				strbuff = new StringBuffer("Content: " + error.getContent());
			}

			if (error.getField() != null) {
				if (strbuff == null) {
					strbuff = new StringBuffer("Field: " + error.getField());
				} else {
					strbuff.append(", " + "Field: " + error.getField());
				}
			}

			if (error.getMessage() != null) {
				if (strbuff == null) {
					strbuff = new StringBuffer("Message: " + error.getMessage());
				} else {
					strbuff.append(", " + "Message: " + error.getMessage());
				}
			}

			if (error.getType() != null) {
				if (strbuff == null) {
					strbuff = new StringBuffer("Type: " + error.getType());
				} else {
					strbuff.append(", " + "Type: " + error.getType());
				}
			}
		}

		if (jsonPostResponse != null && jsonPostResponse.getErrors() != null) {
			if (jsonPostResponse.getErrors() instanceof ArrayList) {
				@SuppressWarnings("unchecked")
				ArrayList<T1Error> al = (ArrayList<T1Error>) jsonPostResponse.getErrors();
				for (T1Error error : al) {
					if (error.getMessage() != null) {
						if (strbuff == null) {
							strbuff = new StringBuffer(error.getMessage()); //add error field
						} else {
							strbuff.append(", " + error.getMessage());
						}
					}
					if (error.getFieldError() != null) {
						for (FieldError fe : error.getFieldError()) {
							if (strbuff == null) {
								strbuff = new StringBuffer("Name: " + fe.getName() + ", Code: " + fe.getCode()
										+ ", Error: " + fe.getError());
							} else {
								strbuff.append(", " + "Name: " + fe.getName() + ", Code: " + fe.getCode()
										+ ", Error: " + fe.getError());
							}
						}
					}
				}
			} else {

				T1Error error = (T1Error) jsonPostResponse.getErrors();

				if (error.getMessage() != null) {
					if (strbuff == null) {
						strbuff = new StringBuffer(error.getMessage());
					} else {
						strbuff.append(", " + error.getMessage());
					}
				}
				if (error.getFieldError() != null) {
					for (FieldError fe : error.getFieldError()) {
						if (strbuff == null) {
							strbuff = new StringBuffer("Name: " + fe.getName() + ", Code: " + fe.getCode()
									+ ", Error: " + fe.getError());
						} else {
							strbuff.append(", " + "Name: " + fe.getName() + ", Code: " + fe.getCode()
									+ ", Error: " + fe.getError());
						}
					}
				}
			}
		}
		
		if(jsonPostResponse != null && jsonPostResponse.getMeta() != null) {
			if(jsonPostResponse.getMeta().getStatus() != null && !jsonPostResponse.getMeta().getStatus().isEmpty()) {
				if (strbuff == null) {
					strbuff = new StringBuffer(jsonPostResponse.getMeta().getStatus());
				} else {
					strbuff.append(", Status: " + jsonPostResponse.getMeta().getStatus());
				}
			}
		}
		// throw the error to client
		throw new ClientException(strbuff.toString());
	}


	
}
