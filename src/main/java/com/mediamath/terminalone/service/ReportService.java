package com.mediamath.terminalone.service;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	
	public StringBuffer getReportURI(ReportCriteria report) throws UnsupportedEncodingException {
		StringBuffer path = null;
		// System.out.println(path.substring(0, path.indexOf("?") + 1) + URLEncoder.encode(path.substring(path.indexOf("?") + 1), "UTF-8"));
		if(report != null) {
			
			path = new StringBuffer(report.getReportName());
			
			// dimensions
			if(report.getDimensions() != null && report.getDimensions().size() > 0) {
				if(path.indexOf("?") == -1) {
					path.append("?dimensions=");
				} else {
					path.append("&dimensions=");
				}
			
				StringBuffer buffer = new StringBuffer();
				for(String dimension: report.getDimensions()) {
					if(buffer.length() == 0) {
						buffer.append(dimension);
					} else {
						buffer.append(","+ dimension);
					}
				}
				
				// Encode
				path.append(URLEncoder.encode(buffer.toString(), "UTF-8"));
			}
			
			// filters
			if(report.getFilters() != null && report.getFilters().size() > 0 && !report.getFilters().isEmpty()) {
				if(path.indexOf("?") == -1) {
					path.append("?filter=");
				} else {
					path.append("&filter=");
				}
				
				int i = 0;
				StringBuffer buffer = new StringBuffer();
				for(ReportFilter f : report.getFilters()) {
					if(f.getKey() != null 
						&& f.getOperator() != null 
						&& f.getValue() != null 
						&& !f.getKey().isEmpty() 
						&& !f.getOperator().isEmpty() 
						&& !f.getValue().isEmpty()) {

						buffer.append(f.getKey() + f.getOperator() + f.getValue());
						if(i != report.getFilters().size() - 1) {
							buffer.append("&");
						}
						i++;
					}
				}
				//encode
				path.append(URLEncoder.encode(buffer.toString(), "UTF-8"));
			}

			//having.
			if(report.getHaving() != null && !report.getHaving().isEmpty()) {
				if(path.indexOf("?") == -1) {
					path.append("?having=");
				} else {
					path.append("&having=");
				}
				
				StringBuffer buffer = new StringBuffer();
				int i = 0;
				for(Having having: report.getHaving())  {
					if(having.getKey() != null
							&& having.getOperator() != null
							&& having.getValue() != null
							&& !having.getKey().isEmpty()
							&& !having.getOperator().isEmpty()
							&& !having.getValue().isEmpty()) {
												
						buffer.append(having.getKey() + having.getOperator() + having.getValue());
						
						if(i != report.getHaving().size() - 1) {
							buffer.append("&");
						}
						i++;
					}
				}
				//encode
				path.append(URLEncoder.encode(buffer.toString(), "UTF-8"));
			}
			
			// metrics
			if(report.getMetrics() != null && report.getMetrics().size() > 0 && !report.getMetrics().isEmpty()) {
				if(path.indexOf("?") == -1) {
					path.append("?metrics=");
				} else {
					path.append("&metrics=");
				}
				
				StringBuffer buffer = new StringBuffer(); 
				for(String metric : report.getMetrics()) {
					if(buffer.length() == 0) {
						buffer.append(metric);
					} else {
						buffer.append(","+ metric);
					}
				}
				path.append(URLEncoder.encode(buffer.toString(), "UTF-8"));
			}
			
			//time rollup.
			if(report.getTime_rollup() != null && !report.getTime_rollup().isEmpty()) {
				uriAppender(path);
				
				path.append("time_rollup=" + report.getTime_rollup());
			}
		
			//time_ window || start_date || end_date
			if(report.getTime_window() != null 
					&& !report.getTime_window().isEmpty()
					&& report.getStart_date() == null
					&& report.getEnd_date() == null) {
				
				uriAppender(path);
				
				path.append("time_window=" + report.getTime_window());
				
				
			} else if(report.getTime_window() == null 
					&& report.getStart_date() != null 
					&& report.getEnd_date() != null) {
				
				uriAppender(path);
				
				Date startDate = report.getStart_date();
				Date endDate = report.getEnd_date();
				
				
				//TODO check documentation for other date formats 
				SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd");
				String sDate = df.format(startDate);
				String eDate = df.format(endDate);
				
				path.append("start_date=" + sDate);
				path.append("&end_date=" + eDate);
				
			}
			
			//order
			if(report.getOrder() != null && report.getOrder().size() > 0 && !report.getOrder().isEmpty()) {
				uriAppender(path);
				
				StringBuffer buffer = new StringBuffer(); 
				for(String order : report.getOrder()) {
					if(buffer.length() == 0) {
						buffer.append("order=" + order);
					} else {
						buffer.append(","+ order);
					}
				}
				path.append(buffer);
			}
			
			//pagelimit & page offset
			if(report.getPage_limit() != null 
					&& !report.getPage_limit().isEmpty()
					&& (report.getPage_offset() == null || report.getPage_offset().isEmpty())) {
				uriAppender(path);
				path.append("page_limit="+ report.getPage_limit());
			} else if(report.getPage_offset() != null 
					&& !report.getPage_offset().isEmpty()
					&& report.getPage_limit() != null 
					&& !report.getPage_limit().isEmpty()) {
				uriAppender(path);
				path.append("page_limit="+ report.getPage_limit());
				path.append("&page_offset=" + report.getPage_offset());
			}
			
			
			// precision
			if(report.getPrecision() > 0) {
				path.append("&" + String.valueOf(report.getPrecision()));
			}
			
		}
		
		return path;
	}

	private void uriAppender(StringBuffer path) {
		if(path.indexOf("?") == -1) {
			path.append("?");
		} else {
			path.append("&");
		}
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
