package com.mediamath.jterminalone.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mediamath.jterminalone.models.JsonResponse;
import com.mediamath.jterminalone.models.T1Entity;

public class T1JsonToObjParser {
	
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
		}
		
		return isArrayObj;
	}
	 

	public JsonResponse<? extends T1Entity> parseJsonToObj(String jsonstr, Type vClassType) {
		Gson gson = new GsonBuilder().registerTypeAdapter(JsonResponse.class, new CustomInstanceCreator()).create();
		JsonResponse<? extends T1Entity> jsonResponse = gson.fromJson(jsonstr, vClassType);
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
