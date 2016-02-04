package com.mediamath.jterminalone.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.mediamath.jterminalone.Exceptions.ParseException;
import com.mediamath.jterminalone.models.JsonResponse;
import com.mediamath.jterminalone.models.T1Entity;

public class T1JsonToObjParser {
	
	private static final Logger logger = LoggerFactory.getLogger(T1JsonToObjParser.class);
	
	public int getJsonElementType(String response) {
		int isArrayObj = 0; // 0 = null
		JsonParser parser = new JsonParser();
		JsonObject obj = parser.parse(response).getAsJsonObject();
		JsonElement element = obj.get("data");
		if(element != null) {
			if(element.isJsonArray()) {
				isArrayObj = 2; // array object
			} else if(element.isJsonObject()) {
				isArrayObj = 1; // single object
			} else if(element.isJsonNull()) {
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
		if(errorElement != null) {
			if(errorElement.isJsonNull()) {
				isArrayObj = 0; // null
			} else {
				isArrayObj = 2; //array
			}
		}
		return isArrayObj;
	}
	 

	public JsonResponse<? extends T1Entity> parseJsonToObj(String jsonstr, Type vClassType) throws ParseException {
		JsonResponse<? extends T1Entity> jsonResponse = null;
		try {
			Gson gson = new GsonBuilder().registerTypeAdapter(JsonResponse.class, new CustomInstanceCreator()).create();
			jsonResponse = gson.fromJson(jsonstr, vClassType);
		} catch(JsonParseException parseException) {
			logger.error("Parse Exception");
			throw new ParseException("Could not parse response");
		}
		return jsonResponse;
	}

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
			 //Type[] typeParams2 = ((ParameterizedType) idType).getActualTypeArguments();
			//return new JsonResponse((Class<?>) typeParams2[0]);
			 return new JsonResponse(idType);
		}
	}
}
