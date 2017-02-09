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
import com.mediamath.terminalone.exceptions.ClientException;
import com.mediamath.terminalone.exceptions.ParseException;
import com.mediamath.terminalone.models.FieldError;
import com.mediamath.terminalone.models.JsonPostErrorResponse;
import com.mediamath.terminalone.models.JsonResponse;
import com.mediamath.terminalone.models.Strategy;
import com.mediamath.terminalone.models.StrategyAudienceSegment;
import com.mediamath.terminalone.models.StrategyConcept;
import com.mediamath.terminalone.models.StrategyDayPart;
import com.mediamath.terminalone.models.T1Entity;
import com.mediamath.terminalone.models.T1Error;
import com.mediamath.terminalone.models.T1Meta;
import com.mediamath.terminalone.models.T1User;
import com.mediamath.terminalone.models.TOneASCreativeAssetsApprove;
import com.mediamath.terminalone.models.TOneASCreativeAssetsUpload;
import com.mediamath.terminalone.models.TPASCreativeBatchApprove;
import com.mediamath.terminalone.models.TPASCreativeUpload;
import com.mediamath.terminalone.models.VideoCreative;
import com.mediamath.terminalone.models.VideoCreativeResponse;
import com.mediamath.terminalone.models.VideoCreativeUploadStatus;
import com.mediamath.terminalone.models.helper.StrategyHelper;
import com.mediamath.terminalone.models.helper.TOneCreativeAssetsApproveHelper;
import com.mediamath.terminalone.models.helper.TPasCreativeUploadBatchHelper;
import com.mediamath.terminalone.models.helper.VideoCreativeHelper;
import com.mediamath.terminalone.utils.Constants;
import com.mediamath.terminalone.utils.T1JsonToObjParser;

public class PostService {

  private static final String CREATIVE_ASSETS_APPROVE = "creative_assets/approve";

  private static final String CREATIVES_UPLOAD = "creatives/upload/";

  private static final String CREATIVES = "/creatives";

  private static final Logger logger = LoggerFactory.getLogger(PostService.class);

  private T1Service t1Service = null;

  private Connection connection = null;

  private T1User user = null;

  private static final String YYYY_MM_DD_T_HH_MM_SS = "yyyy-MM-dd'T'HH:mm:ss";

  public PostService() {}

  /**
   * Constructor for Initializing PostService.
   * 
   * @param connection
   *          requries a connection Object.
   * @param user
   *          requires a valid user session.
   * @param t1Service
   *          requires T1Service service object.
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
   *          T can be any given entity implementing T1Entity.
   * @return StringBuilder object.
   */
  private <T extends T1Entity> StringBuilder getUri(T entity) {
    String entityName = entity.getEntityname();
    
    return new StringBuilder(Constants.entityPaths.get(entityName));
  }

  /**
   * 
   * @param entity
   *          expects T1Entity
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

    if (checkString(uriPath))
      uri.append(uriPath);

    String path = t1Service.constructUrl(uri);

    Response responseObj = this.connection.post(path, entity.getForm(), this.user);

    finalJsonResponse = getJsonResponse(entity, responseObj);

    if (finalJsonResponse == null)
      return null;

    return finalJsonResponse.getData();
  }

  /**
   * saves a Strategy entity.
   * 
   * @param entity
   *          expects a Strategy entity.
   * @return Strategy object.
   * @throws ClientException
   *           a client exception is thrown if any error occurs.
   * @throws ParseException
   *           a parse exception is thrown when the response cannot be parsed.
   */
  @SuppressWarnings("rawtypes")
  public Strategy save(Strategy entity) throws ClientException, ParseException {

    Strategy strategy = null;
    JsonResponse<? extends T1Entity> finalJsonResponse = null;
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
          && entity.getAudienceSegmentExcludeOp() != null
          && entity.getAudienceSegmentIncludeOp() != null) {
        uri.append("/audience_segments");
      }

      String path = t1Service.constructUrl(uri);

      Response responseObj = this.connection.post(path, StrategyHelper.getForm(entity), this.user);

      finalJsonResponse = getJsonResponse(entity, responseObj);

      if (finalJsonResponse == null)
        return null;

      if (finalJsonResponse.getData() == null)
        return null;

      if (finalJsonResponse.getData() instanceof ArrayList) {

        List dataList = (ArrayList) finalJsonResponse.getData();

        if (dataList.get(0) != null && dataList.get(0) instanceof StrategyAudienceSegment) {
          strategy = entity;
          strategy.setStrategyAudienceSegments(dataList);
        }

      } else {
        strategy = (Strategy) finalJsonResponse.getData();
      }

    }

    return strategy;
  }

  private JsonResponse<? extends T1Entity> getJsonResponse(T1Entity entity, Response responseObj)
      throws ClientException, ParseException {
    
    JsonResponse<? extends T1Entity> finalJsonResponse;

    String response = responseObj.readEntity(String.class);

    T1JsonToObjParser parser = new T1JsonToObjParser();

    if (response.isEmpty())
      return null;

    JsonPostErrorResponse error = jsonPostErrorResponseParser(response, responseObj);

    if (error != null)
      throwExceptions(error);

    finalJsonResponse = parsePostData(response, parser, entity);
    
    return finalJsonResponse;
  }

  /**
   * saves a VideoCreative entity. this is the First Call to Create a Video Creative.
   * 
   * @param entity
   *          expects a VideoCreative entity.
   * 
   * @return VideoCreativeResponse entity.
   * 
   * @throws ClientException
   *           a client exception is thrown if any error occurs.
   */
  public VideoCreativeResponse save(VideoCreative entity) throws ClientException {

    VideoCreativeResponse videoCreative = null;

    if (entity != null) {

      StringBuilder path = new StringBuilder(t1Service.getApi_base() + t1Service.getVideoCreativeURL() + CREATIVES);

      if (entity.getCreativeId() > 0) {
        path.append("/" + entity.getCreativeId());
      }

      String videoCreativePath = path.toString();

      Response responseObj = this.connection.post(videoCreativePath, VideoCreativeHelper.getJson(entity), this.user);

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

    }
    return videoCreative;
  }

  /**
   * Gets the Video Creative Upload status
   * 
   * @param creativeId
   *          requires a creativeId string.
   * 
   * @return VideoCreativeUploadStatus object.
   * 
   */
  public VideoCreativeUploadStatus getVideoCreativeUploadStatus(String creativeId) {
    VideoCreativeUploadStatus uploadStatus = null;
    if (checkString(creativeId)) {
      StringBuilder path = new StringBuilder(t1Service.getApi_base() + t1Service.getVideoCreativeURL() + CREATIVES + "/" + creativeId + "/status");
      if(path.length() > 0) {
        logger.info(path.toString());
      }
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
   *          path to the actual file data.
   * 
   * @param fileName
   *          name of the file.
   * 
   * @param creativeId
   *          requires a creative id string.
   * 
   * @return VideoCreativeResponse object.
   * 
   * @throws ClientException
   *           a client exception is thrown if any error occurs.
   * @throws IOException
   * 
   * @throws ParseException
   *           a parse exception is thrown when the response cannot be parsed.
   */
  public VideoCreativeResponse uploadVideoCreative(String filePath, String fileName,
      String creativeId) throws ClientException, IOException {

    VideoCreativeResponse videoCreative = null;

    if (checkString(filePath) && checkString(creativeId) && checkString(fileName)) {

      StringBuilder path = new StringBuilder(t1Service.getApi_base() + t1Service.getVideoCreativeURL()
          + CREATIVES + "/" + creativeId + "/upload?fileName=" + fileName);

      String finalPath = path.toString();

      // post binary only
      FileDataBodyPart filePart = new FileDataBodyPart("file", new File(filePath));
      FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
      final FormDataMultiPart multipart = (FormDataMultiPart) formDataMultiPart.bodyPart(filePart);

      Response responseObj = this.connection.post(finalPath, multipart, this.user);

      String response = responseObj.readEntity(String.class);

      T1JsonToObjParser parser = new T1JsonToObjParser();

      if (response.isEmpty()) {
        formDataMultiPart.close();
        multipart.close();
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

      formDataMultiPart.close();
      multipart.close();
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
   *          expects a StrategyConcept entity.
   * 
   * @return JsonResponse<? extends T1Entity> returns a jsonResponse of type T entity.
   * 
   * @throws ClientException
   *           a client exception is thrown if any error occurs.
   * 
   * @throws ParseException
   *           a parse exception is thrown when the response cannot be parsed.
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

    String finalPath = t1Service.constructUrl(path);

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
   *          expects a StrategyDayPart entity.
   * @return JsonResponse<? extends T1Entity> returns a JsonResponse of type T entity.
   * @throws ClientException
   *           a client exception is thrown if any error occurs.
   * 
   * @throws ParseException
   *           a parse exception is thrown when the response cannot be parsed.
   */
  public JsonResponse<? extends T1Entity> delete(StrategyDayPart strategyDayPart)
      throws ClientException, ParseException {
    StringBuilder path = new StringBuilder();

    if (strategyDayPart.getId() > 0) {
      path.append(Constants.entityPaths.get("strategyDayPart"));
      path.append("/");
      path.append(strategyDayPart.getId());
      path.append("/delete");
    }

    String finalPath = t1Service.constructUrl(path);

    Form strategyConceptForm = new Form();
    if (strategyDayPart.getVersion() > 0) {
      strategyConceptForm.param("version", String.valueOf(strategyDayPart.getVersion()));
    }

    Response responseObj = connection.post(finalPath, strategyConceptForm, this.user);
    String response = responseObj.readEntity(String.class);

    T1JsonToObjParser parser = new T1JsonToObjParser();
    
    return parser.parseJsonToObj(response,
        Constants.getEntityType.get("strategy_day_parts"));
  }

  /**
   * saves a 3PAS creative upload.
   * 
   * @param filePath
   *          path to the file.
   * 
   * @param fileName
   *          name of file.
   * 
   * @param name
   *          name of 3pas upload.
   * 
   * @return TPASCreativeUpload object.
   * 
   * @throws ClientException
   *           a client exception is thrown if any error occurs.
   * 
   * @throws ParseException
   *           a parse exception is thrown when the response cannot be parsed.
   */
  public TPASCreativeUpload saveTPASCreativeUpload(String filePath, String fileName, String name)
      throws ClientException, IOException {

    TPASCreativeUpload tpasCreativeUploadResponse = null;
    StringBuilder uri = new StringBuilder("creatives/upload");
    String response = saveCreativeUploads(uri, filePath, name, fileName);
    T1JsonToObjParser parser = new T1JsonToObjParser();
    if (checkString(response)) {
      tpasCreativeUploadResponse = parseTPASCreativeUploadData(response, parser);
    }
    return tpasCreativeUploadResponse;
  }
  
  private String saveCreativeUploads(StringBuilder uri, String filePath, String name, String fileName) throws ClientException, IOException {
    if (filePath == null && name == null && fileName == null) {
      throw new ClientException("please enter a valid filename and file path");
    }
    
    String path = t1Service.constructUrl(uri);

    FileDataBodyPart filePart = new FileDataBodyPart("file", new File(filePath));
    
    FormDataMultiPart formDataMultiPart = new FormDataMultiPart();   
    FormDataMultiPart multipart = null;
    
    multipart = (FormDataMultiPart) formDataMultiPart
        .field("filename", fileName).field("name", name).bodyPart(filePart);
    
    Response responseObj = this.connection.post(path, multipart, this.user);
    String response = responseObj.readEntity(String.class);
    
    formDataMultiPart.close();
    multipart.close();
    
    return response;
  }

  private TPASCreativeUpload parseTPASCreativeUploadData(String response,
      T1JsonToObjParser parser) {
    TPASCreativeUpload finalResponse;
    finalResponse = parser.parse3PasCreativeUploadResponseTOObj(response);
    return finalResponse;
  }

  /**
   * this API call in the Bulk 3PAS process saves particular creative out of a given batch to the T1
   * database. second call to save the 3pas creative upload
   * 
   * @param entity
   *          requires a TPASCreativeBatchApprove entity.
   * 
   * @return JsonResponse<? extends T1Entity> returns a JsonResponse of type T.
   * 
   * @throws ClientException
   *           a client exception is thrown if any error occurs.
   * 
   * @throws ParseException
   *           a parse exception is thrown when the response cannot be parsed.
   * 
   * @throws IOException
   *           is thrown when this method is unable to create a file containing report data.
   */
  public JsonResponse<? extends T1Entity> saveTPASCreativeUploadBatch(
      TPASCreativeBatchApprove entity) throws ClientException, IOException, ParseException {
    FormDataMultiPart formData = new FormDataMultiPart();
    JsonResponse<? extends T1Entity> finalJsonResponse = null;
    if (entity != null) {

      StringBuilder uri = new StringBuilder(CREATIVES_UPLOAD);

      if (entity.getBatchId() != null && !entity.getBatchId().isEmpty()) {
        uri.append(entity.getBatchId());
        String path = t1Service.constructUrl(uri);
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
   * <i>saveT1ASCreativeAssetsUpload("C:\\exampledir1\\exampledir2\\samplefile.txt", "samplefile"
   * ,"samplefile");</i>
   * 
   * @param filePath
   *          path to the file data.
   * 
   * @param fileName
   *          a filename
   * 
   * @param name
   *          String.
   * 
   * @return TOneASCreativeAssetsUpload object.
   * 
   * @throws ClientException
   *           a client exception is thrown if any error occurs.
   * 
   * @throws IOException
   *           is thrown when this method is unable to create a file containing report data.
   */
  public TOneASCreativeAssetsUpload saveTOneASCreativeAssets(String filePath, String fileName,
      String name) throws ClientException, IOException {

    TOneASCreativeAssetsUpload assetsUploadResponse = null;
    StringBuilder uri = new StringBuilder("creative_assets/upload");
    String response = saveCreativeUploads(uri, filePath, name, fileName);
    T1JsonToObjParser parser = new T1JsonToObjParser();
    if (checkString(response)) {
      assetsUploadResponse = parseTOneASCreativeAssetsUploadData(response, parser);
    }
    return assetsUploadResponse;
  }

  /**
   * parses TOneASCreativeAssetsUploadData response and creates a TOneASCreativeAssetsUpload object.
   * 
   * @param response
   *          string json to parse.
   * @param parser
   *          requires an instance of T1JsonToObjParser
   * @return TOneASCreativeAssetsUpload object.
   */
  private TOneASCreativeAssetsUpload parseTOneASCreativeAssetsUploadData(String response,
      T1JsonToObjParser parser) {
    TOneASCreativeAssetsUpload finalResponse;
    finalResponse = parser.parseTOneASCreativeAssetsUploadResponseTOObj(response);
    return finalResponse;
  }

  /**
   * saves TOneASCreativeAssetsApprove entity.
   * 
   * @param entity
   *          expects a TOneASCreativeAssetsApprove entity.
   * 
   * @return JsonResponse<? extends T1Entity> returns JsonResponse of type T
   * 
   * @throws ClientException
   *           a client exception is thrown if any error occurs.
   * @throws IOException
   *           exception is thrown when the multipart form is left open.
   */
  public JsonResponse<? extends T1Entity> saveTOneASCreativeAssetsApprove(TOneASCreativeAssetsApprove entity) throws ClientException, IOException {
    
    FormDataMultiPart formData = new FormDataMultiPart();

    JsonResponse<? extends T1Entity> parsedJsonResponse = null;

    if (entity == null) {
      formData.close();
      return null;
    }

    StringBuilder uri = new StringBuilder(CREATIVE_ASSETS_APPROVE);
    String path = t1Service.constructUrl(uri);
    TOneCreativeAssetsApproveHelper.getMultiPartForm(entity, formData);
    Response responseObj = this.connection.post(path, formData, this.user);
    String jsonResponse = responseObj.readEntity(String.class);
    T1JsonToObjParser parser = new T1JsonToObjParser();
    JsonPostErrorResponse jsonPostErrorResponse;
    jsonPostErrorResponse = jsonPostErrorResponseParser(jsonResponse, responseObj);

    if (jsonPostErrorResponse == null) {
      parsedJsonResponse = parser.parseTOneASCreativeAssetsApproveResponse(jsonResponse);
      /*
       * if (parsedJsonResponse.getData() instanceof TOneASCreativeAssetsApproveResponse) { response
       * = (TOneASCreativeAssetsApproveResponse) parsedJsonResponse.getData(); }
       */
    } else {
      throwExceptions(jsonPostErrorResponse);
    }

    return parsedJsonResponse;
  }

  /**
   * parses error response of a POST activity.
   * 
   * @param responseStr
   *          requires a response JSON string
   * @param responseObj
   *          requires a Response object.
   * @return JsonPostErrorResponse entity.
   */
  public JsonPostErrorResponse jsonPostErrorResponseParser(String responseStr,
      Response responseObj) {
    JsonParser parser1 = new JsonParser();
    JsonObject obj = parser1.parse(responseStr).getAsJsonObject();

    JsonElement errorsElement = obj.get("errors");
    JsonElement errorElement = obj.get("error");
    JsonElement metaElement = obj.get("meta");

    JsonPostErrorResponse errorResponse = null;

    if (checkErrorAndErrorsElement(errorsElement, errorElement) || checkResponseStatusAndMetaElement(responseObj, metaElement)) {

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
    return errorsElement != null || errorElement != null;
  }

  private void parseMetaElement(Response responseObj, JsonElement metaElement,
      JsonPostErrorResponse errorResponse, Gson gson) {
    if (metaElement != null) {
      if (responseObj != null && responseObj.getStatus() == 403) {
        T1Meta meta = gson.fromJson(metaElement, T1Meta.class);
        errorResponse.setMeta(meta);
      }
    }
  }

  private void parseErrorElement(JsonElement errorElement, JsonPostErrorResponse errorResponse,
      Gson gson) {
    if (errorElement != null) {
      T1Error error = gson.fromJson(errorElement, T1Error.class);
      errorResponse.setError(error);
    }
  }

  private void parseErrorsElement(JsonElement errorsElement, JsonPostErrorResponse errorResponse,
      Gson gson) {
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
          errorsElement = newArray;
          Type type = new TypeToken<ArrayList<T1Error>>() {
          }.getType();
          List<T1Error> errors = gson.fromJson(errorsElement, type);
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
   * this method is responsible to parse the JsonPostErrorResponse and throw relevant exceptions.
   * 
   * @param jsonPostResponse
   *          object.
   * @throws ClientException
   *           a client exception is thrown if any error occurs.
   */
  public void throwExceptions(JsonPostErrorResponse jsonPostResponse) throws ClientException {

    StringBuilder strbuilder = null;

    strbuilder = parseErrorException(jsonPostResponse, strbuilder);

    strbuilder = parseErrorsException(jsonPostResponse, strbuilder);

    strbuilder = parseMetaException(jsonPostResponse, strbuilder);
    // throw the error to client
    throw new ClientException(strbuilder.toString());
  }

  private StringBuilder parseMetaException(JsonPostErrorResponse jsonPostResponse, StringBuilder strBuilder) {
    if (jsonPostResponse != null && jsonPostResponse.getMeta() != null && checkPostResponseMetaStatus(jsonPostResponse)) {
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
      strBuilder = new StringBuilder("Name: " + fe.getName() + ", Code: " + fe.getCode() + ", Error: " + fe.getError());
    } else {
      strBuilder.append(", " + "Name: " + fe.getName() + ", Code: " + fe.getCode() + ", Error: " + fe.getError());
    }
    return strBuilder;
  }

  private StringBuilder formErrorString(StringBuilder strBuilder, T1Error error) {
    if (strBuilder == null) {
      strBuilder = new StringBuilder(error.getMessage()); // add error field
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
        JsonElement entityTypeElement = obj.get("entity_type");
        String entityType = entityTypeElement.getAsString();
        finalJsonResponse = parser.parseJsonToObj(response,
            Constants.getEntityType.get(entityType));
      }
    } else if (element == null) {
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

  private JsonResponse<? extends T1Entity> parseJsonArrayDataList(String response, T1JsonToObjParser parser, JsonResponse<? extends T1Entity> finalJsonResponse, JsonElement data) throws ParseException {
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

  private JsonResponse<? extends T1Entity> parseArrayJsonResponse(String response,
      T1JsonToObjParser parser, JsonResponse<? extends T1Entity> finalJsonResponse,
      String entityType) throws ParseException {
    if (checkString(entityType) && Constants.getListoFEntityType.get(entityType) != null) {
        finalJsonResponse = parser.parseJsonToObj(response, Constants.getListoFEntityType.get(entityType));
    }
    return finalJsonResponse;
  }

}
