package com.mediamath.jterminalone.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.mediamath.jterminalone.models.JsonResponse;

public class T1JsonToObjParser {
	
	/*public JsonResponse<T> parseJsonToObj(String jsonstr, Type vClassType) {
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		JsonObject obj = parser.parse(jsonstr).getAsJsonObject();
		JsonResponse<T> jsonResponse = gson.fromJson(obj, vClassType);
		return jsonResponse;
	}
	*/
	
	public JsonResponse<?> parseJsonToObj(String jsonstr, Type vClassType) {
		Gson gson = new GsonBuilder().registerTypeAdapter(JsonResponse.class, new CustomInstanceCreator()).create();
		JsonResponse<?> jsonResponse = gson.fromJson(jsonstr, vClassType);
		return jsonResponse;
	}

}



class CustomInstanceCreator implements InstanceCreator<JsonResponse<?>>  
{  
 @SuppressWarnings({ "unchecked", "rawtypes" })  
 public JsonResponse<?> createInstance(Type type)  
 {  
  Type[] typeParameters = ((ParameterizedType) type).getActualTypeArguments();
  Type idType = typeParameters[0];
  if(idType instanceof Class<?>) {
	  return new JsonResponse((Class<?>) idType);
  } else {
	  // extract inner type and send it. (Class<?>) 
	  //Type[] typeParams2 = ((ParameterizedType) idType).getActualTypeArguments();
	  //return new JsonResponse((Class<?>) typeParams2[0]);
	  return new JsonResponse(idType);
  }
 }  
} 
