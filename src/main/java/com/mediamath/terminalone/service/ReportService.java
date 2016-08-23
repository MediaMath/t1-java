package com.mediamath.terminalone.service;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.mediamath.terminalone.ReportCriteria;
import com.mediamath.terminalone.models.JsonResponse;
import com.mediamath.terminalone.models.T1Entity;
import com.mediamath.terminalone.models.reporting.Having;
import com.mediamath.terminalone.models.reporting.ReportFilter;
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
	
	public StringBuffer getReportURI(ReportCriteria report) {
		StringBuffer path = null;
		
		if(report != null) {
			
			path = new StringBuffer(report.getReportName());
			
			// dimensions
			if(report.getDimensions() != null && report.getDimensions().size() > 0) {
				if(path.indexOf("?") == -1) {
					path.append("?");
				} else {
					path.append("&");
				}
			
				StringBuffer buffer = new StringBuffer();
				for(String dimension: report.getDimensions()) {
					if(buffer.length() == 0) {
						buffer.append("dimensions=" + dimension);
					} else {
						buffer.append(","+ dimension);
					}
				}
				path.append(buffer.toString());
			}
			
			// filters
			if(report.getFilters() != null && report.getFilters().size() > 0 && !report.getFilters().isEmpty()) {
				if(path.indexOf("?") == -1) {
					path.append("?filter=");
				} else {
					path.append("&filter=");
				}
				
				for(ReportFilter f : report.getFilters()) {
					if(f.getKey() != null 
						&& f.getOperator() != null 
						&& f.getValue() != null 
						&& !f.getKey().isEmpty() 
						&& !f.getOperator().isEmpty() 
						&& !f.getValue().isEmpty()) {

						path.append("&");
						path.append(f.getKey() + f.getOperator() + f.getValue());
						
					}
				}
			}

			//having.
			if(report.getHaving() != null && !report.getHaving().isEmpty()) {
				if(path.indexOf("?") == -1) {
					path.append("?having=");
				} else {
					path.append("&having=");
				}
				for(Having having: report.getHaving())  {
					if(having.getKey() != null
							&& having.getOperator() != null
							&& having.getValue() != null
							&& !having.getKey().isEmpty()
							&& !having.getOperator().isEmpty()
							&& !having.getValue().isEmpty()) {
						
						path.append("&");
						path.append(having.getKey() + having.getOperator() + having.getValue());
					}
				}
			}
			
			// metrics
			if(report.getMetrics() != null && report.getMetrics().size() > 0 && !report.getMetrics().isEmpty()) {
				if(path.indexOf("?") == -1) {
					path.append("?");
				} else {
					path.append("&");
				}
				
				StringBuffer buffer = new StringBuffer(); 
				for(String metric : report.getMetrics()) {
					if(buffer.length() == 0) {
						buffer.append("metrics=" + metric);
					} else {
						buffer.append(","+ metric);
					}
				}
				path.append(buffer);
			}
			
			// precision
			if(report.getPrecision() > 0) {
				path.append("&" + String.valueOf(report.getPrecision()));
			}
			
		}
		
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
