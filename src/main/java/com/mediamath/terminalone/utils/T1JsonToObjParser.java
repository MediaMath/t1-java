package com.mediamath.terminalone.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.mediamath.terminalone.Exceptions.ParseException;
import com.mediamath.terminalone.models.JsonResponse;
import com.mediamath.terminalone.models.T1Entity;
import com.mediamath.terminalone.models.TOneASCreativeAssetsApproveResponse;
import com.mediamath.terminalone.models.TOneASCreativeAssetsUpload;
import com.mediamath.terminalone.models.ThreePASCreativeUpload;
import com.mediamath.terminalone.models.VideoCreativeResponse;
import com.mediamath.terminalone.models.VideoCreativeUploadStatus;

public class T1JsonToObjParser {

	private static final String YYYY_MM_DD_T_HH_MM_SS = "yyyy-MM-dd'T'HH:mm:ss";
	private static final Logger logger = LoggerFactory.getLogger(T1JsonToObjParser.class);

	public JsonElement getDataFromResponse(String response) {
		JsonParser parser = new JsonParser();
		JsonObject obj = parser.parse(response).getAsJsonObject();
		JsonElement element = obj.get("data");
		return element;
	}

	public int getJsonElementType(String response) {
		int isArrayObj = 0; // 0 = null
		JsonParser parser = new JsonParser();
		JsonObject obj = parser.parse(response).getAsJsonObject();
		JsonElement element = obj.get("data");
		if (element != null) {
			if (element.isJsonArray()) {
				isArrayObj = 2; // array object
			} else if (element.isJsonObject()) {
				isArrayObj = 1; // single object
			} else if (element.isJsonNull()) {
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

	public JsonResponse<? extends T1Entity> parseJsonToObj(String jsonstr, Type vClassType) throws ParseException {
		JsonResponse<? extends T1Entity> jsonResponse = null;
		try {
			Gson gson = new GsonBuilder().registerTypeAdapter(JsonResponse.class, new CustomInstanceCreator())
					.setDateFormat(YYYY_MM_DD_T_HH_MM_SS).create();
			jsonResponse = gson.fromJson(jsonstr, vClassType);
		} catch (JsonParseException parseException) {
			logger.error("Parse Exception");
			throw new ParseException("Could not parse response");
		}
		return jsonResponse;
	}

	public ThreePASCreativeUpload parse3PasCreativeUploadResponseTOObj(String json3PasCreativeResponseString) {

		ThreePASCreativeUpload response = null;
		GsonBuilder builder = new GsonBuilder();
		builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES);
		builder.setDateFormat(YYYY_MM_DD_T_HH_MM_SS);
		Gson gson = builder.create();
		response = gson.fromJson(json3PasCreativeResponseString, ThreePASCreativeUpload.class);
		return response;
	}

	public TOneASCreativeAssetsUpload parseTOneASCreativeAssetsUploadResponseTOObj(String jsonT1CAUesponse) {
		TOneASCreativeAssetsUpload response = null;
		GsonBuilder builder = new GsonBuilder();
		builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES);
		builder.setDateFormat(YYYY_MM_DD_T_HH_MM_SS);
		Gson gson = builder.create();
		response = gson.fromJson(jsonT1CAUesponse, TOneASCreativeAssetsUpload.class);
		return response;

	}

	public JsonResponse<? extends T1Entity> parseTOneASCreativeAssetsApproveResponse(String pResponse) {
		JsonResponse<? extends T1Entity> response = null;

		GsonBuilder builder = new GsonBuilder();
		builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES);
		builder.setDateFormat(YYYY_MM_DD_T_HH_MM_SS);
		Gson gson = builder.create();

		Type type = new TypeToken<JsonResponse<ArrayList<TOneASCreativeAssetsApproveResponse>>>() {
		}.getType();

		response = gson.fromJson(pResponse, type);
		return response;

	}

	public VideoCreativeResponse parseVideoCreative(String pResponse) {
		VideoCreativeResponse response = null;

		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat(YYYY_MM_DD_T_HH_MM_SS);
		Gson gson = builder.create();
		response = gson.fromJson(pResponse, VideoCreativeResponse.class);

		return response;
	}

	public VideoCreativeUploadStatus parseVideoCreativeUploadStatus(String pResponse) {
		VideoCreativeUploadStatus response = null;
		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat(YYYY_MM_DD_T_HH_MM_SS);
		Gson gson = builder.create();
		response = gson.fromJson(pResponse, VideoCreativeUploadStatus.class);
		return response;
	}

	class CustomInstanceCreator implements InstanceCreator<JsonResponse<?>> {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public JsonResponse<?> createInstance(Type type) {
			Type[] typeParameters = ((ParameterizedType) type).getActualTypeArguments();
			Type idType = typeParameters[0];
			if (idType instanceof Class<?>) {
				return new JsonResponse((Class<?>) idType);
			} else {
				// extract inner type and send it.
				// Type[] typeParams2 = ((ParameterizedType)
				// idType).getActualTypeArguments();
				// return new JsonResponse((Class<?>) typeParams2[0]);
				return new JsonResponse(idType);
			}
		}

	}
}
