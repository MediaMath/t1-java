package com.mediamath.terminalone.service;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map.Entry;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.mediamath.terminalone.models.JsonResponse;
import com.mediamath.terminalone.models.T1Entity;
import com.mediamath.terminalone.models.reporting.meta.Meta;
import com.mediamath.terminalone.models.reporting.meta.MetaData;

public class ReportService {

	private static final String META = "meta";

	private static final String YYYY_MM_DD_T_HH_MM_SS = "yyyy-MM-dd'T'HH:mm:ss";
	
	/**
	 * construct path for the requested report.
	 * 
	 * @param meta
	 * @return
	 */
	public StringBuffer getMetaURI() {
		StringBuffer path = new StringBuffer();
		path.append(META);
		return path;
	}
	
	
	public JsonResponse<? extends T1Entity> parseMetaResponse(String response) {
		JsonParser parser = new JsonParser();
		JsonResponse<Meta> finalResponse = null;
		JsonObject obj = parser.parse(response).getAsJsonObject();

		JsonElement reportsElement = obj.get("reports");
		JsonObject reportsObj = reportsElement.getAsJsonObject();

		if(reportsObj != null) {
			
			Meta meta = new Meta();
			Type type = new TypeToken<MetaData>(){}.getType();
			HashMap<String, MetaData> metaData = new HashMap<String, MetaData>();

			GsonBuilder builder = new GsonBuilder();
			builder.setDateFormat(YYYY_MM_DD_T_HH_MM_SS);
			Gson g = builder.create();

			for(Entry<String, JsonElement> a: reportsObj.entrySet()) {
				String key = a.getKey();
				MetaData value = g.fromJson(a.getValue(), type);
				metaData.put(key, value);
			}
			meta.setMetaData(metaData);
			finalResponse = new JsonResponse<Meta>(meta);
		}
		
		return finalResponse;
	}

}
