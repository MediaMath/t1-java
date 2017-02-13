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

package com.mediamath.terminalone.utils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import com.mediamath.terminalone.exceptions.ParseException;
import com.mediamath.terminalone.models.JsonResponse;
import com.mediamath.terminalone.models.T1Entity;
import com.mediamath.terminalone.models.TOneASCreativeAssetsApproveResponse;
import com.mediamath.terminalone.models.TOneASCreativeAssetsUpload;
import com.mediamath.terminalone.models.TPASCreativeUpload;
import com.mediamath.terminalone.models.VideoCreativeResponse;
import com.mediamath.terminalone.models.VideoCreativeUploadStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class T1JsonToObjParser {

  private static final String COULD_NOT_PARSE_RESPONSE = "Could not parse response";
  private static final String YYYY_MM_DD_T_HH_MM_SS = "yyyy-MM-dd'T'HH:mm:ss";
  
  /**
   * Extracts Data object from the response.
   * 
   * @param response
   *          JSON response string.
   * 
   * @return JsonElement object.
   * 
   */
  public JsonElement getDataFromResponse(String response) {
    JsonParser parser = new JsonParser();
    JsonObject obj = parser.parse(response).getAsJsonObject();
    return obj.get("data");
  }

  /**
   * Determines the Json Element type from the given response.
   * 
   * @param response
   *          JSON response string.
   * 
   * @return int value <br>
   *         0 = null body<br>
   *         1 = Json Element is of type Object. <br>
   *         2 = Json Element is of type Array.
   * 
   */
  public int getJsonElementType(String response) {
    int isArrayObj = 0; // 0 = null
    JsonParser parser = new JsonParser();
    JsonObject obj = parser.parse(response).getAsJsonObject();
    JsonElement dataElement = obj.get("data");
    if (dataElement != null) {
      if (dataElement.isJsonArray()) {
        isArrayObj = 2; // array object
      } else if (dataElement.isJsonObject()) {
        isArrayObj = 1; // single object
      } else if (dataElement.isJsonNull()) {
        isArrayObj = 0; // nothing.
      }
    } else {
      isArrayObj = getErrorElementType(response);
    }
    return isArrayObj;
  }

  private int getErrorElementType(String response) {
    int isArrayObj = 0; // 0 = null
    JsonParser parser = new JsonParser();
    JsonObject obj = parser.parse(response).getAsJsonObject();
    JsonElement errorElement = obj.get("errors");
    if (errorElement != null) {
      if (errorElement.isJsonNull()) {
        isArrayObj = 0; // null
      } else {
        isArrayObj = 2; // array
      }
    }
    return isArrayObj;
  }

  /**
   * parses Json response String to dynamically selected Entity of type T1Entity.
   * 
   * @param jsonstr
   *          the Json String to parse.
   * 
   * @param vClassType
   *          Type of the class to parse to.
   * 
   * @return JsonResponse<? extends T1Entity> Json Response of type T entity. T is selected
   *         dynamically based on the type info passed in the parameter.
   * 
   * @throws ParseException
   *           a Parse Exception is thrown
   */
  public JsonResponse<? extends T1Entity> parseJsonToObj(String jsonstr, Type vClassType)
      throws ParseException {
    JsonResponse<? extends T1Entity> jsonResponse = null;
    try {
      Gson gson = new GsonBuilder()
          .registerTypeAdapter(JsonResponse.class, new CustomInstanceCreator())
          .setDateFormat(YYYY_MM_DD_T_HH_MM_SS).create();
      jsonResponse = gson.fromJson(jsonstr, vClassType);
    } catch (JsonParseException parseException) {
    	Utility.logStackTrace(parseException);
      throw new ParseException(COULD_NOT_PARSE_RESPONSE);
    }
    return jsonResponse;
  }

  /**
   * parses 3pas creative upload response string to TPASCreativeUpload object.
   * 
   * @param json3PasCreativeResponseString
   *          required response string
   * @return TPASCreativeUpload object.
   */
  public TPASCreativeUpload parse3PasCreativeUploadResponseTOObj(
      String json3PasCreativeResponseString) {
    TPASCreativeUpload response;
    GsonBuilder builder = new GsonBuilder();
    builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES);
    builder.setDateFormat(YYYY_MM_DD_T_HH_MM_SS);
    Gson gson = builder.create();
    response = gson.fromJson(json3PasCreativeResponseString, TPASCreativeUpload.class);
    return response;
  }

  /**
   * parses T1AS creative asset upload response to TOneASCreativeAssetsUpload entity.
   * 
   * @param jsonT1CAUesponse
   *          response string.
   * @return TOneASCreativeAssetsUpload object.
   */
  public TOneASCreativeAssetsUpload parseTOneASCreativeAssetsUploadResponseTOObj(
      String jsonT1CAUesponse) {
    TOneASCreativeAssetsUpload response;
    GsonBuilder builder = new GsonBuilder();
    builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES);
    builder.setDateFormat(YYYY_MM_DD_T_HH_MM_SS);
    Gson gson = builder.create();
    response = gson.fromJson(jsonT1CAUesponse, TOneASCreativeAssetsUpload.class);
    return response;
  }

  /**
   * parses the response of second call to T1AS creative assets approve api call.
   * 
   * @param pResponse
   *          requires a response string.
   * @return JsonResponse<? extends T1Entity> returns an entity of type T.
   */
  public JsonResponse<? extends T1Entity> parseTOneASCreativeAssetsApproveResponse(
      String pResponse) {
    JsonResponse<? extends T1Entity> response;

    GsonBuilder builder = new GsonBuilder();
    builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES);
    builder.setDateFormat(YYYY_MM_DD_T_HH_MM_SS);
    Gson gson = builder.create();

    Type type = new TypeToken<JsonResponse<ArrayList<TOneASCreativeAssetsApproveResponse>>>() {
    }.getType();

    response = gson.fromJson(pResponse, type);
    return response;

  }

  /**
   * parse video creative to VideoCreativeReponse entity.
   * 
   * @param pResponse
   *          response String.
   * @return VideoCreativeResponse object.
   */
  public VideoCreativeResponse parseVideoCreative(String pResponse) {
    VideoCreativeResponse response;

    GsonBuilder builder = new GsonBuilder();
    builder.setDateFormat(YYYY_MM_DD_T_HH_MM_SS);
    Gson gson = builder.create();
    response = gson.fromJson(pResponse, VideoCreativeResponse.class);

    return response;
  }

  /**
   * parses video creative upload status response to VideoCreativeUploadStatus entity.
   * 
   * @param pResponse
   *          response string.
   * @return VideoCreativeUploadStatus object.
   */
  public VideoCreativeUploadStatus parseVideoCreativeUploadStatus(String pResponse) {
    VideoCreativeUploadStatus response;
    GsonBuilder builder = new GsonBuilder();
    builder.setDateFormat(YYYY_MM_DD_T_HH_MM_SS);
    Gson gson = builder.create();
    response = gson.fromJson(pResponse, VideoCreativeUploadStatus.class);
    return response;
  }

  /**
   * Creates a JsonResponse instance based on the type information extracted from the Actual Type
   * arguments or the type information passed in.
   * 
   * @author chauhan_n
   *
   */
  class CustomInstanceCreator implements InstanceCreator<JsonResponse<?>> {
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public JsonResponse<?> createInstance(Type type) {
      Type[] typeParameters = ((ParameterizedType) type).getActualTypeArguments();
      Type idType = typeParameters[0];
      if (idType instanceof Class<?>) {
        return new JsonResponse((Class<?>) idType);
      } else {
        return new JsonResponse(idType);
      }
    }

  }
}
