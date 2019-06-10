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
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mediamath.terminalone.models.*;
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
import com.mediamath.terminalone.models.helper.StrategyHelper;
import com.mediamath.terminalone.models.helper.TOneCreativeAssetsApproveHelper;
import com.mediamath.terminalone.models.helper.TPasCreativeUploadBatchHelper;
import com.mediamath.terminalone.models.helper.VideoCreativeHelper;
import com.mediamath.terminalone.utils.Constants;
import com.mediamath.terminalone.utils.T1JsonToObjParser;
import com.mediamath.terminalone.utils.Utility;

import static com.mediamath.terminalone.utils.Constants.DMP_SEGMENT;

public class PostService {

    private static final String ENTITY_TYPE = "entity_type";

    private static final String VERSION = "version";

    private static final String DELETE = "/delete";

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

    private static final Object STRATEGY = "strategy";

    public PostService() {
        // default constructor.
    }

    /**
     * Constructor for Initializing PostService.
     *
     * @param connection requries a connection Object.
     * @param user       requires a valid user session.
     * @param t1Service  requires T1Service service object.
     */
    public PostService(Connection connection, T1User user, T1Service t1Service) {
        this.connection = connection;
        this.user = user;
        this.t1Service = t1Service;
    }

    /**
     * form a URI for the given entity.
     *
     * @param entity T can be any given entity implementing T1Entity.
     * @return StringBuilder object.
     */
    private <T extends T1Entity> StringBuilder getUri(T entity) {
        String entityName = entity.getEntityname();

        return new StringBuilder(Constants.entityPaths.get(entityName));
    }

    /**
     * @param entity expects T1Entity
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
        Response responseObj;
        responseObj = this.connection.post(path, entity.getForm(), this.user);
        return readPostResponseToString(responseObj);
    }

    private String getStrategyResponseString(Strategy entity, String path) throws ClientException {
        Response responseObj = this.connection.post(path, StrategyHelper.getForm(entity), this.user);
        return readPostResponseToString(responseObj);
    }

    private String delete(String path) throws ClientException {
        Response responseObj = this.connection.delete(path, this.user);
        return readPostResponseToString(responseObj);
    }

    private String post(String path, JsonElement jsonElement) throws ClientException {
        String json = new Gson().toJson(jsonElement);
        Response responseObj = this.connection.post(path, json, this.user);
        return readPostResponseToString(responseObj);
    }

    private String readPostResponseToString(Response responseObj) throws ClientException {
        String response = responseObj.readEntity(String.class);
        JsonPostErrorResponse error = jsonPostErrorResponseParser(response, responseObj);
        if (error != null)
            throwExceptions(error);

        return response;
    }

    /**
     * Saves User entity and its permissions.
     *
     * @param entity
     * @return
     * @throws ClientException
     * @throws ParseException
     */
    public User save(User entity) throws ClientException, ParseException {

        User userSaved = null;

        if (entity == null) {
            return null;
        } else {
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
            } else if (finalJsonResponse.getData() instanceof UserPermissions) {
                UserPermissions uPerm = (UserPermissions) finalJsonResponse.getData();
                userSaved = uPerm.getUser();
                userSaved.setPermissions(uPerm.getPermissions());
            }

        }

        return userSaved;
    }

    /**
     * Performs OS and Devices Targeting
     * @param entity expects a Strategy entity.
     * @throws ClientException a client exception is thrown if any error occurs.
     */
    private void saveWurflTargeting(Strategy entity) throws ClientException {
        StringBuilder nemoPathBuilder = new StringBuilder()
                .append("nemo/attachment?strategy_id=")
                .append(entity.getId())
                .append("&dimension=WURF&dimension_code=WURF");
        String nemoPath = t1Service.constructUrl(nemoPathBuilder, Constants.entityPaths.get(entity.getEntityname()));
        //clean up existing wurfl targeting (OS and Devices)
        delete(nemoPath);
        for(TargetValues targetValues : entity.getTargetValues()) {
            if (targetValues.getCode() == TargetValues.codes.NEMO) {
                JsonElement jsonElement = StrategyHelper.getNemoTargetingJson(entity.getId(), targetValues);
                post(nemoPath, jsonElement);
            }
        }
    }

    /**
     * saves a Strategy entity.
     *
     * @param entity expects a Strategy entity.
     * @return Strategy object.
     * @throws ClientException a client exception is thrown if any error occurs.
     * @throws ParseException  a parse exception is thrown when the response cannot be
     *                         parsed.
     * @throws IOException
     */
    public Strategy save(Strategy entity) throws ClientException, ParseException {

        Strategy strategy = null;
        JsonResponse<? extends T1Entity> finalJsonResponse;
        if (entity != null) {
            if (entity.getTargetValues() != null && !entity.getTargetValues().isEmpty()) {
                //perform wurfl targeting / NEMO target code: OS and Devices
                saveWurflTargeting(entity);
            }

            StringBuilder uri = getUri(entity);

            constructStrategyURIPath(entity, uri);

            String path = t1Service.constructUrl(uri, Constants.entityPaths.get(entity.getEntityname()));

            String responseString;
            responseString = getStrategyResponseString(entity, path);

            finalJsonResponse = getJsonResponse(entity, responseString);

            if (finalJsonResponse == null)
                return null;

            if (finalJsonResponse.getData() == null)
                return null;

            strategy = constructStrategyResponseObject(entity, strategy, finalJsonResponse);
        }
        return strategy;
    }

    /**
     * @param entity
     * @param strategy
     * @param finalJsonResponse
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private Strategy constructStrategyResponseObject(Strategy entity, Strategy strategy,
                                                     JsonResponse<? extends T1Entity> finalJsonResponse) {
        Strategy localStrategy = strategy;
        if (finalJsonResponse.getData() instanceof ArrayList) {
            List dataList = (ArrayList) finalJsonResponse.getData();
            if (dataList.get(0) != null) {
                if (dataList.get(0) instanceof StrategyAudienceSegment) {
                    localStrategy = entity;
                    localStrategy.getStrategyAudienceSegments().clear();
                    localStrategy.setStrategyAudienceSegments(dataList);
                }
                if (dataList.get(0) instanceof StrategyTargetingSegment) {
                    localStrategy = entity;
                    localStrategy.getStrategyTargetingSegments().clear();
                    localStrategy.setStrategyTargetingSegments(dataList);
                }
                if (dataList.get(0) instanceof SiteList) {
                    localStrategy = entity;
                    localStrategy.getSiteLists().clear();
                    localStrategy.setSiteLists(new HashSet<>(dataList));
                }
                if (dataList.get(0) instanceof StrategyDayPart) {
                    localStrategy = entity;
                    localStrategy.getStrategyDayParts().clear();
                    localStrategy.setStrategyDayParts(dataList);
                }
                if (dataList.get(0) instanceof StrategyTarget) {
                    localStrategy = entity;
                    localStrategy.getStrategyTarget().clear();
                    localStrategy.setStrategyTarget(new HashSet<>(dataList));
                }
            }

        } else {
            if (finalJsonResponse.getData() instanceof StrategyTargetValues) {
                localStrategy = entity;
                localStrategy.setStrategyTargetValues((StrategyTargetValues) finalJsonResponse.getData());
            } else {
                localStrategy = (Strategy) finalJsonResponse.getData();
            }
        }
        return localStrategy;
    }

    /**
     * @param entity
     * @param uri
     */
    private void constructStrategyURIPath(Strategy entity, StringBuilder uri) {
        if (entity.getId() > 0) {
            uri.append("/");
            uri.append(entity.getId());

            if (!entity.getStrategyDomainRestrictions().isEmpty()) {
                uri.append("/domain_restrictions");
            }

            if (!entity.getAudienceSegments().isEmpty()
                    && entity.getAudienceSegmentExcludeOp() != null && entity.getAudienceSegmentIncludeOp() != null) {
                uri.append("/audience_segments");
            }

            if (!entity.getSiteLists().isEmpty()) {
                uri.append("/site_lists");
            }

            if (!entity.getStrategyTargetingSegments().isEmpty()
                    && entity.getTargetingSegmentExcludeOp() != null && entity.getTargetingSegmentIncludeOp() != null) {
                uri.append("/targeting_segments");
            }

            if (!entity.getTargetValues().isEmpty() && entity.hasTargetValuesExcept(TargetValues.codes.NEMO)) {
                uri.append("/target_values");
            }

            if (!entity.getStrategyDayParts().isEmpty()) {
                uri.append("/day_parts");
            }

            if (entity.getStrategyConcepts() != null) {
                uri.append("/concepts");
            }

            if (entity.getDealIds() != null) {
                uri.append("/deals");
            }

            if (entity.isCopyStrategy()) {
                uri.append("/copy");
            }

            if (entity.getTargetDimensions() != null) {
                uri.append("/target_dimensions");
                if (entity.getTargetDimensions().getId() > 0) {
                    uri.append("/" + entity.getTargetDimensions().getId());
                }
            }
        }

    }

    /**
     * saves a ZipCodes against Strategy entity.
     *
     * @param entity expects a ZipCodes entity.
     * @return ZipCodesJsonResponse object.
     * @throws ClientException a client exception is thrown if any error occurs.
     * @throws ParseException  a parse exception is thrown when the response cannot be
     *                         parsed.
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
                logger.error(" " + e.getCause());
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
     * @param entity expects a Strategy entity.
     * @return JsonResponse<?       extends       T1Entity> object.
     * @throws ClientException a client exception is thrown if any error occurs.
     * @throws ParseException  a parse exception is thrown when the response cannot be
     *                         parsed.
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
     * @param entity expects a Campaign entity.
     * @return Strategy object.
     * @throws ClientException a client exception is thrown if any error occurs.
     * @throws ParseException  a parse exception is thrown when the response cannot be
     *                         parsed.
     */
    public Campaign save(Campaign entity) throws ClientException, ParseException {

        Campaign campaign = null;
        JsonResponse<? extends T1Entity> finalJsonResponse;
        if (entity != null) {
            StringBuilder uri = getUri(entity);

            constructCampaignURIPath(entity, uri);

            String path = t1Service.constructUrl(uri, Constants.entityPaths.get(entity.getEntityname()));

            String responseString = getResponseString(entity, path);

            finalJsonResponse = getJsonResponse(entity, responseString);

            if (finalJsonResponse == null)
                return null;

            if (finalJsonResponse.getData() == null)
                return null;

            campaign = constructCampaignResponseObject(entity, campaign, finalJsonResponse);
        }

        return campaign;
    }

    /**
     * @param entity
     * @param campaign
     * @param finalJsonResponse
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private Campaign constructCampaignResponseObject(Campaign entity, Campaign campaign,
                                                     JsonResponse<? extends T1Entity> finalJsonResponse) {
        Campaign localCampaign = campaign;

        if (finalJsonResponse.getData() instanceof ArrayList) {
            ArrayList dataList = (ArrayList) finalJsonResponse.getData();
            if (dataList.get(0) != null && dataList.get(0) instanceof SiteList) {
                localCampaign = entity;
                localCampaign.getSiteLists().clear();
                localCampaign.setSiteLists(dataList);
            } else if (dataList.get(0) != null && dataList.get(0) instanceof BudgetFlight) {
                localCampaign = entity;
                localCampaign.getBudgetFlights().clear();
                localCampaign.setBudgetFlights(dataList);
            } else if (dataList.get(0) != null && dataList.get(0) instanceof CampaignCustomBrainSelection) {
                localCampaign = entity;
                localCampaign.getCampaignCustomBrainSelection().clear();
                localCampaign.setCampaignCustomBrainSelection(dataList);
            }
        } else {

            if (finalJsonResponse.getData() instanceof BudgetFlight) {
                localCampaign = entity;
                BudgetFlight bfData = (BudgetFlight) finalJsonResponse.getData();
                localCampaign.getBudgetFlights().clear();
                localCampaign.getBudgetFlights().add(bfData);
            } else if (finalJsonResponse.getData() instanceof CampaignCustomBrainSelection) {
                localCampaign = entity;
                CampaignCustomBrainSelection ccbsData = (CampaignCustomBrainSelection) finalJsonResponse.getData();
                localCampaign.getCampaignCustomBrainSelection().clear();
                localCampaign.getCampaignCustomBrainSelection().add(ccbsData);
            } else {
                localCampaign = (Campaign) finalJsonResponse.getData();
            }
        }
        return localCampaign;
    }

    /**
     * @param entity
     * @param uri
     */
    private void constructCampaignURIPath(Campaign entity, StringBuilder uri) {

        if (entity.getId() > 0) {
            uri.append("/");
            uri.append(entity.getId());

            if (entity.getMargins().size() > 0) {
                uri.append("/margins");
            } else if (!entity.getSiteLists().isEmpty()) {
                uri.append("/site_lists");
            } else if (entity.isCopyCampaign()) {
                uri.append("/copy");
            } else if (entity.getBudgetFlights().size() > 0) {
                uri.append("/budget_flights/bulk");
            } else if (entity.getCampaignCustomBrainSelection().size() == 1) {
                uri.append("/custom_brain_selections");
                if (entity.getCampaignCustomBrainSelection().get(0) != null
                        && entity.getCampaignCustomBrainSelection().get(0).getId() > 0) {
                    uri.append("/").append(entity.getCampaignCustomBrainSelection().get(0).getId());
                }
                if (entity.getCampaignCustomBrainSelection().get(0) != null
                        && entity.getCampaignCustomBrainSelection().get(0).isDeleted()) {
                    uri.append(DELETE);
                }
            } else if (entity.getCampaignCustomBrainSelection().size() > 1) {
                uri.append("/custom_brain_selections/bulk");
            }

        }
    }

    private JsonResponse<? extends T1Entity> getJsonResponse(T1Entity entity, String response)
            throws ParseException {

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
     * @param entity expects a VideoCreative entity.
     * @return VideoCreativeResponse entity.
     * @throws ClientException a client exception is thrown if any error occurs.
     */
    public VideoCreativeResponse save(VideoCreative entity) throws ClientException {

        VideoCreativeResponse videoCreative = null;

        if (entity != null) {

            StringBuilder path = new StringBuilder(
                    t1Service.getApiBase() + t1Service.getVideoCreativeURL() + CREATIVES);

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
            } else if (parsedVideoCreativeResponse != null && entity.getCreativeId() > 0) {
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
     * @param creativeId requires a creativeId string.
     * @return VideoCreativeUploadStatus object.
     */
    public VideoCreativeUploadStatus getVideoCreativeUploadStatus(String creativeId) {
        VideoCreativeUploadStatus uploadStatus = null;
        if (checkString(creativeId)) {
            StringBuilder path = new StringBuilder(t1Service.getApiBase() + t1Service.getVideoCreativeURL() + CREATIVES
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
     * @param filePath   path to the actual file data.
     * @param fileName   name of the file.
     * @param creativeId requires a creative id string.
     * @return VideoCreativeResponse object.
     * @throws ClientException a client exception is thrown if any error occurs.
     * @throws IOException
     * @throws ParseException  a parse exception is thrown when the response cannot be
     *                         parsed.
     */
    public VideoCreativeResponse uploadVideoCreative(String filePath, String fileName, String creativeId)
            throws ClientException, IOException {

        VideoCreativeResponse videoCreative = null;

        if (checkString(filePath) && checkString(creativeId) && checkString(fileName)) {

            StringBuilder path = new StringBuilder(t1Service.getApiBase() + t1Service.getVideoCreativeURL() + CREATIVES
                    + "/" + creativeId + "/upload?fileName=" + fileName);

            String finalPath = path.toString();

            // post binary only
            try (InputStream inputStream = new FileInputStream(new File(filePath));) {

                Response responseObj = this.connection.post(finalPath, inputStream, this.user);

                inputStream.close();

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

            }
        }
        return videoCreative;
    }

    /**
     * TEMPORARY SOLUTION, DO BE DEPRECATED ONCE VIDEO CREATIVE UPLOAD PROBLEM
     * SOLVES
     * <p>
     * this method uploads the given video creative file.
     *
     * @param filePath path to the actual file data.
     * @param fileName name of the file.
     * @param key      requires a key string.
     * @param host     requires a host string.
     * @return VideoCreativeResponse object.
     * @throws ClientException a client exception is thrown if any error occurs.
     * @throws IOException
     * @throws ParseException  a parse exception is thrown when the response cannot be
     *                         parsed.
     */
    public VideoCreativeUploadResponse videoCreativeUpload(String filePath, String fileName, String key, String host)
            throws ClientException, IOException {

        VideoCreativeUploadResponse videoCreative = null;

        if (checkString(filePath) && checkString(key) && checkString(fileName)) {

            String finalPath = "https://" + host;
            final File fileToUpload = new File(filePath);
            try (final FormDataMultiPart multiPart = new FormDataMultiPart();) {

                // add key
                multiPart.field("key", key);
                // add file
                FormDataBodyPart fileDataBodyPart = new FormDataBodyPart(
                        FormDataContentDisposition.name("file").fileName(fileName).build(), fileToUpload,
                        MediaType.APPLICATION_OCTET_STREAM_TYPE);
                multiPart.bodyPart(fileDataBodyPart);

                Response responseObj = this.connection.post(finalPath, multiPart, this.user);
                String response = responseObj.readEntity(String.class);
                // close resources

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
     * @param strategyConcept expects a StrategyConcept entity.
     * @return JsonResponse<?       extends       T1Entity> returns a jsonResponse of type T
     * entity.
     * @throws ClientException a client exception is thrown if any error occurs.
     * @throws ParseException  a parse exception is thrown when the response cannot be
     *                         parsed.
     */
    public JsonResponse<? extends T1Entity> delete(StrategyConcept strategyConcept)
            throws ClientException, ParseException {
        StringBuilder path = new StringBuilder();

        if (strategyConcept.getId() > 0) {
            path.append(Constants.entityPaths.get("StrategyConcept"));
            path.append("/");
            path.append(strategyConcept.getId());
            path.append(DELETE);
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
     * @param strategyDayPart expects a StrategyDayPart entity.
     * @return JsonResponse<?       extends       T1Entity> returns a JsonResponse of type T
     * entity.
     * @throws ClientException a client exception is thrown if any error occurs.
     * @throws ParseException  a parse exception is thrown when the response cannot be
     *                         parsed.
     */
    public JsonResponse<? extends T1Entity> delete(StrategyDayPart strategyDayPart)
            throws ClientException, ParseException {
        StringBuilder path = new StringBuilder();

        if (strategyDayPart.getId() > 0) {
            path.append(Constants.entityPaths.get("StrategyDayPart"));
            path.append("/");
            path.append(strategyDayPart.getId());
            path.append(DELETE);
        }

        String finalPath = t1Service.constructUrl(path, Constants.entityPaths.get("StrategyDayPart"));

        Form strategyConceptForm = new Form();
        if (strategyDayPart.getVersion() >= 0) {
            strategyConceptForm.param(VERSION, String.valueOf(strategyDayPart.getVersion()));
        }

        Response responseObj = connection.post(finalPath, strategyConceptForm, this.user);
        String response = responseObj.readEntity(String.class);

        T1JsonToObjParser parser = new T1JsonToObjParser();

        return parser.parseJsonToObj(response, Constants.getEntityType.get("strategy_day_parts"));
    }


    /**
     * Delete Vendor Contract Entity
     *
     * @param Vendor Contract Entity
     * @return JsonResponse<?       extends       T1Entity> returns a JsonResponse of type T
     * entity.
     * @throws ClientException
     * @throws ParseException
     */
    public JsonResponse<? extends T1Entity> delete(VendorContract contract) throws ClientException, ParseException {
        StringBuilder path = new StringBuilder();

        if (contract.getId() > 0) {
            path.append(Constants.entityPaths.get("VendorContract"));
            path.append("/");
            path.append(contract.getId());
            path.append(DELETE);
        }

        String finalPath = t1Service.constructUrl(path, Constants.entityPaths.get("VendorContract"));

        Form contractForm = new Form();
        if (contract.getVersion() >= 0) {
            contractForm.param(VERSION, String.valueOf(contract.getVersion()));
        }
        Response responseObj = connection.post(finalPath, contractForm, this.user);
        String response = responseObj.readEntity(String.class);

        return getJsonResponse(contract, response);
    }

    /**
     * saves a 3PAS creative upload.
     *
     * @param filePath path to the file.
     * @param fileName name of file.
     * @param name     name of 3pas upload.
     * @return TPASCreativeUpload object.
     * @throws ClientException a client exception is thrown if any error occurs.
     * @throws ParseException  a parse exception is thrown when the response cannot be
     *                         parsed.
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
                                       String collection) throws ClientException {
        String response = null;

        if (filePath == null && name == null && fileName == null) {
            throw new ClientException("please enter a valid filename and file path");
        }

        String path = t1Service.constructUrl(uri, collection);

        FileDataBodyPart filePart = new FileDataBodyPart("file", new File(filePath));

        try (FormDataMultiPart formDataMultiPart = new FormDataMultiPart();) {

            FormDataMultiPart multipart = (FormDataMultiPart) formDataMultiPart.field("filename", fileName)
                    .field("name", name).bodyPart(filePart);

            Response responseObj = this.connection.post(path, multipart, this.user);
            response = responseObj.readEntity(String.class);
            formDataMultiPart.close();
            multipart.close();

            JsonPostErrorResponse error = jsonPostErrorResponseParser(response, responseObj);
            if (error != null) {
                throwExceptions(error);
            }

        } catch (IOException e) {
            Utility.logStackTrace(e);
        }


        return response;
    }

    private String saveCreativeMultipleUploads(StringBuilder uri, List<T1File> fileList, String collection)
            throws ClientException, IOException {
        if (fileList == null) {
            throw new ClientException("please enter a valid filename and file path");
        }

        String path = t1Service.constructUrl(uri, collection);

        FormDataMultiPart multipart = new FormDataMultiPart();

        for (T1File t1File : fileList) {
            if (t1File != null
                    && (t1File.getFile() != null && t1File.getFilename() != null && t1File.getName() != null)) {
                FileDataBodyPart filePart = new FileDataBodyPart("file", new File(t1File.getFile()));

                multipart.field("filename", t1File.getFilename()).field("name", t1File.getName()).bodyPart(filePart);
            }
        }

        Response responseObj = this.connection.post(path, multipart, this.user);
        String response = responseObj.readEntity(String.class);

        multipart.close();

        JsonPostErrorResponse error = jsonPostErrorResponseParser(response, responseObj);
        if (error != null) {
            throwExceptions(error);
        }

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
     * @param entity requires a TPASCreativeBatchApprove entity.
     * @return JsonResponse<?       extends       T1Entity> returns a JsonResponse of type
     * T.
     * @throws ClientException a client exception is thrown if any error occurs.
     * @throws ParseException  a parse exception is thrown when the response cannot be
     *                         parsed.
     * @throws IOException     is thrown when this method is unable to create a file
     *                         containing report data.
     */
    @SuppressWarnings("resource")
    public JsonResponse<? extends T1Entity> saveTPASCreativeUploadBatch(TPASCreativeBatchApprove entity)
            throws ClientException, IOException, ParseException {
        FormDataMultiPart formData = new FormDataMultiPart();
        JsonResponse<? extends T1Entity> finalJsonResponse = null;
        if (entity != null) {

            StringBuilder uri = new StringBuilder(CREATIVES_UPLOAD);

            if (entity.getBatchId() != null && !entity.getBatchId().isEmpty()) {
                uri.append(entity.getBatchId());
                String path = t1Service.constructUrl(uri, CREATIVES_STR);
                formData = TPasCreativeUploadBatchHelper.getMultiPartForm(entity, formData);
                Response responseObj = this.connection.post(path, formData, this.user);
                formData.close();
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
     * @param filePath path to the file data.
     * @param fileName a filename
     * @param name     String.
     * @return TOneASCreativeAssetsUpload object.
     * @throws ClientException a client exception is thrown if any error occurs.
     * @throws IOException     is thrown when this method is unable to create a file
     *                         containing report data.
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

    /**
     * saves multiple file upload to T1AS. first call to upload the HTML5 file
     * along with backup files. <br>
     * <br>
     * example: <br>
     * <br>
     * <i>saveT1ASCreativeAssetsUpload(List<T1File> fileList);</i>
     *
     * @param fileList a valid list of T1File object is required. T1File Object, can
     *                 accept name, filename and filepath
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
     * @param response string json to parse.
     * @param parser   requires an instance of T1JsonToObjParser
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
     * @param entity expects a TOneASCreativeAssetsApprove entity.
     * @return JsonResponse<?       extends       T1Entity> returns JsonResponse of type T
     * @throws ClientException a client exception is thrown if any error occurs.
     * @throws IOException     exception is thrown when the multipart form is left open.
     */
    @SuppressWarnings("resource")
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
        formData = TOneCreativeAssetsApproveHelper.getMultiPartForm(entity, formData);
        Response responseObj = this.connection.post(path, formData, this.user);
        formData.close();
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
     * @param responseStr requires a response JSON string
     * @param responseObj requires a Response object.
     * @return JsonPostErrorResponse entity.
     */
    public JsonPostErrorResponse jsonPostErrorResponseParser(String responseStr, Response responseObj) {
        JsonParser parser1 = new JsonParser();
        JsonObject obj = parser1.parse(responseStr).getAsJsonObject();

        JsonElement errorsElement = obj.get("errors");
        JsonElement errorElement = obj.get("error");
        JsonElement metaElement = obj.get("meta");

        JsonPostErrorResponse errorResponse = null;

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        if (errorsElement != null) {
            errorResponse = new JsonPostErrorResponse();
            parseErrorsElement(errorsElement, errorResponse, gson);
        }

        if (checkErrorAndErrorsElement(errorsElement, errorElement)
                || checkResponseStatusAndMetaElement(responseObj, metaElement)) {
            if (errorResponse == null) {
                errorResponse = new JsonPostErrorResponse();
            }

            builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES);
            builder.setDateFormat(YYYY_MM_DD_T_HH_MM_SS);


            parseErrorElement(errorElement, errorResponse, gson);

            parseMetaElement(responseObj, metaElement, errorResponse, gson);
        }

        return errorResponse;
    }

    private boolean checkResponseStatusAndMetaElement(Response responseObj, JsonElement metaElement) {
        return responseObj != null && responseObj.getStatus() == 403 && metaElement != null;
    }

    private boolean checkErrorAndErrorsElement(JsonElement errorsElement, JsonElement errorElement) {
        return ((errorsElement != null && errorsElement.isJsonArray() && errorsElement.getAsJsonArray().size() > 0)
                || errorElement != null);
    }

    private void parseMetaElement(Response responseObj, JsonElement metaElement, JsonPostErrorResponse errorResponse,
                                  Gson gson) {
        if (metaElement != null && responseObj != null && responseObj.getStatus() == 403) {
            T1Meta meta = gson.fromJson(metaElement, T1Meta.class);
            errorResponse.setMeta(meta);
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
     * @param jsonPostResponse object.
     * @throws ClientException a client exception is thrown if any error occurs.
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
        StringBuilder stringBuilder = strBuilder;

        if (jsonPostResponse != null && jsonPostResponse.getMeta() != null
                && checkPostResponseMetaStatus(jsonPostResponse)) {
            if (stringBuilder == null) {
                stringBuilder = new StringBuilder(jsonPostResponse.getMeta().getStatus());
            } else {
                stringBuilder.append(", Status: " + jsonPostResponse.getMeta().getStatus());
            }
        }

        return stringBuilder;
    }

    private boolean checkPostResponseMetaStatus(JsonPostErrorResponse jsonPostResponse) {
        return jsonPostResponse.getMeta().getStatus() != null && !jsonPostResponse.getMeta().getStatus().isEmpty();
    }

    private StringBuilder parseErrorsException(JsonPostErrorResponse jsonPostResponse, StringBuilder strBuilder) {
        StringBuilder stringBuilder = strBuilder;
        if (jsonPostResponse == null)
            return stringBuilder;

        if (jsonPostResponse.getErrors() == null)
            return stringBuilder;

        if (jsonPostResponse.getErrors() instanceof ArrayList) {
            @SuppressWarnings("unchecked")
            ArrayList<T1Error> al = (ArrayList<T1Error>) jsonPostResponse.getErrors();
            for (T1Error error : al) {
                if (error.getMessage() != null) {
                    stringBuilder = formErrorString(stringBuilder, error);
                }
                if (error.getFieldError() != null) {
                    for (FieldError fe : error.getFieldError()) {
                        stringBuilder = formFieldErrorString(stringBuilder, fe);
                    }
                }
            }
        } else {

            T1Error error = (T1Error) jsonPostResponse.getErrors();

            if (error.getMessage() != null) {
                stringBuilder = formErrorString(stringBuilder, error);
            }
            if (error.getFieldError() != null) {
                for (FieldError fe : error.getFieldError()) {
                    stringBuilder = formFieldErrorString(stringBuilder, fe);
                }
            }
        }

        return stringBuilder;
    }

    private StringBuilder formFieldErrorString(StringBuilder strBuilder, FieldError fe) {
        StringBuilder stringBuilder = strBuilder;
        if (stringBuilder == null) {
            stringBuilder = new StringBuilder(
                    "Name: " + fe.getName() + ", Code: " + fe.getCode() + ", Error: " + fe.getError());
        } else {
            stringBuilder.append(", " + "Name: " + fe.getName() + ", Code: " + fe.getCode() + ", Error: " + fe.getError());
        }
        return stringBuilder;
    }

    private StringBuilder formErrorString(StringBuilder strBuilder, T1Error error) {
        StringBuilder stringBuilder = strBuilder;
        if (stringBuilder == null) {
            stringBuilder = new StringBuilder(error.getMessage()); // add error
            // field
        } else {
            stringBuilder.append(", " + error.getMessage());
        }
        return stringBuilder;
    }

    private StringBuilder parseErrorException(JsonPostErrorResponse jsonPostResponse, StringBuilder strBuilder) {
        StringBuilder stringBuilder = strBuilder;
        if (jsonPostResponse != null && jsonPostResponse.getError() != null) {
            T1Error error = jsonPostResponse.getError();

            if (error.getContent() != null) {
                stringBuilder = new StringBuilder("Content: " + error.getContent());
            }

            if (error.getField() != null) {
                if (stringBuilder == null) {
                    stringBuilder = new StringBuilder("Field: " + error.getField());
                } else {
                    stringBuilder.append(", " + "Field: " + error.getField());
                }
            }

            if (error.getMessage() != null) {
                if (stringBuilder == null) {
                    stringBuilder = new StringBuilder("Message: " + error.getMessage());
                } else {
                    stringBuilder.append(", " + "Message: " + error.getMessage());
                }
            }

            if (error.getType() != null) {
                if (stringBuilder == null) {
                    stringBuilder = new StringBuilder("Type: " + error.getType());
                } else {
                    stringBuilder.append(", " + "Type: " + error.getType());
                }
            }
        }
        return stringBuilder;
    }

    private <T extends T1Entity> JsonResponse<? extends T1Entity> parsePostData(String response,
                                                                                T1JsonToObjParser parser, T entity) throws ParseException {

        // parse the string to gson objs
        JsonResponse<? extends T1Entity> finalJsonResponse = null;
        JsonParser parsers = new JsonParser();
        JsonObject responseObject = parsers.parse(new StringReader(response)).getAsJsonObject();
        String modifiedJsonString = null;

        JsonElement element = parser.getDataFromResponse(response);
        if (element != null && element.isJsonArray()) {
            // do something
            JsonArray dataList = element.getAsJsonArray();
            if (dataList.size() > 0) {
                JsonElement data = dataList.get(0);
                if (data == null) {
                    return null;
                }
                JsonObject dataObj = data.getAsJsonObject();
                if (dataObj == null) {
                    return null;
                }
                // Check whether strategy have values in old format, if yes change them to current format
                if (dataObj.get(ENTITY_TYPE).equals(STRATEGY)) {
                    modifiedJsonString = checkAndFixStrategyJson(responseObject);
                }

                if (modifiedJsonString == null) {
                    modifiedJsonString = response;
                }

                JsonElement entityTypeElem = dataObj.get(ENTITY_TYPE);
                if (entityTypeElem != null) {
                    String entityType = entityTypeElem.getAsString();
                    //finalJsonResponse = parseArrayJsonResponse(modifiedJsonString.replaceAll("\\\\", ""), parser, finalJsonResponse, entityType);
                    //Replacing \" does not work in case a json element has quotes inside, removing this, we will see if any impact
                    finalJsonResponse = parseArrayJsonResponse(modifiedJsonString, parser, finalJsonResponse, entityType);
                }

            }

        } else if (element != null && element.isJsonObject()) {
            JsonObject obj = element.getAsJsonObject();
            String entityType = getEntityType(obj);
            if (entityType != null) {
                if (entityType.equals(STRATEGY)) {
                    modifiedJsonString = checkAndFixStrategyJson(responseObject);
                }
                if (modifiedJsonString == null) {
                    modifiedJsonString = response;
                }

                //finalJsonResponse = parser.parseJsonToObj(modifiedJsonString.replaceAll("\\\\", ""), Constants.getEntityType.get(entityType));
                //Replacing \" does not work in case a json element has quotes inside, removing this, we will see if any impact
                finalJsonResponse = parser.parseJsonToObj(modifiedJsonString, Constants.getEntityType.get(entityType));
            } else {
                if (modifiedJsonString == null) {
                    modifiedJsonString = response;
                }

                entityType = obj.get("type") == null ? null : obj.get("type").getAsString();
                if (entityType != null && entityType.equals(DMP_SEGMENT)) {
                    finalJsonResponse = parser.parseJsonToObj(modifiedJsonString, Constants.getEntityType.get(entityType));
                } else {
                    //finalJsonResponse = parser.parseJsonToObj(modifiedJsonString.replaceAll("\\\\", ""), Constants.getEntityType.get(entity.getEntityname().toLowerCase()));
                    //Replacing \" does not work in case a json element has quotes inside, removing this, we will see if any impact
                    finalJsonResponse = parser.parseJsonToObj(modifiedJsonString, Constants.getEntityType.get(entity.getEntityname().toLowerCase()));
                }
            }
        } else if (entity != null) {
            finalJsonResponse = parser.parseJsonToObj(response, Constants.getEntityType.get(entity.getEntityname().toLowerCase()));
            if (finalJsonResponse != null) {
                finalJsonResponse.setData(null);
            }
        }

        return finalJsonResponse;
    }

    private String getEntityType(JsonObject obj) {
        JsonElement entityTypeElement = obj.get(ENTITY_TYPE);
        if (entityTypeElement == null && obj.get("exclude_op") != null && obj.get("include_op") != null
                && obj.get("enabled") != null) {
            return "strategy_target_values";
        } else {
            return (entityTypeElement != null) ? entityTypeElement.getAsString() : null;
        }
    }

    private JsonResponse<? extends T1Entity> parseArrayJsonResponse(String response, T1JsonToObjParser parser,
                                                                    JsonResponse<? extends T1Entity> finalJsonResponse, String entityType) throws ParseException {
        JsonResponse<? extends T1Entity> localJsonResponse = finalJsonResponse;

        if (checkString(entityType) && Constants.getListoFEntityType.get(entityType) != null) {
            localJsonResponse = parser.parseJsonToObj(response, Constants.getListoFEntityType.get(entityType));
        }
        return localJsonResponse;
    }

    @SuppressWarnings("rawtypes")
    private String checkAndFixStrategyJson(JsonObject responseObject) {
        Gson gson = new Gson();
        JsonObject data = responseObject.getAsJsonObject("data");

        Class strategyClass = Strategy.class;
        Field[] fieldList = strategyClass.getDeclaredFields();

        for (Field field : fieldList) {
            if (field.getType().isInstance(new ArrayList<Currency>())
                    && ("java.util.ArrayList<com.mediamath.terminalone.models.Currency>").equals(field.getGenericType().toString())
                    && (data.get(field.getName()) != null && data.get(field.getName()).isJsonPrimitive())) {
                BigDecimal fieldValue = data.get(field.getName()).getAsBigDecimal();
                responseObject.getAsJsonObject("data").remove(field.getName());
                ArrayList<Currency> egvList = new ArrayList<>();
                Currency curr = new Currency();
                curr.setValue(fieldValue);
                if (data.get("currency_code") != null) {
                    curr.setCurrencyCode(data.get("currency_code").getAsString());
                }
                egvList.add(curr);
                JsonParser parser = new JsonParser();
                JsonElement je = parser.parse(gson.toJson(egvList, ArrayList.class));
                responseObject.getAsJsonObject("data").add(field.getName(), je);
                data = responseObject.getAsJsonObject("data");
            }
        }

        return responseObject.toString();
    }
}
