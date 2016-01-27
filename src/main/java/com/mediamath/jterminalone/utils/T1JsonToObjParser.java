package com.mediamath.jterminalone.utils;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mediamath.jterminalone.models.JsonResponse;

public class T1JsonToObjParser<T> {
	
	public JsonResponse<T> parseJsonToObj(String jsonstr, Type vClassType) {
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		JsonObject obj = parser.parse(jsonstr).getAsJsonObject();
		JsonResponse<T> jsonResponse = gson.fromJson(obj, vClassType);
		return jsonResponse;
	}

}
