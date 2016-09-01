package com.mediamath.terminalone.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.mediamath.terminalone.Connection;
import com.mediamath.terminalone.ReportCriteria;
import com.mediamath.terminalone.Exceptions.ClientException;
import com.mediamath.terminalone.models.JsonResponse;
import com.mediamath.terminalone.models.T1Entity;
import com.mediamath.terminalone.models.T1Response;
import com.mediamath.terminalone.models.reporting.Having;
import com.mediamath.terminalone.models.reporting.ReportError;
import com.mediamath.terminalone.models.reporting.ReportErrorEntityInfo;
import com.mediamath.terminalone.models.reporting.ReportFilter;
import com.mediamath.terminalone.models.reporting.ReportStatus;
import com.mediamath.terminalone.models.reporting.ReportValidationResponse;
import com.mediamath.terminalone.models.reporting.Reports;
import com.mediamath.terminalone.models.reporting.meta.Meta;
import com.mediamath.terminalone.models.reporting.meta.MetaData;
import com.mediamath.terminalone.utils.Utility;

public class ReportService {
	
	private static final String YYYY_MM_DD_HH_MM_SS = "yyyy_MM_dd_hh_mm_ss";

	private static final Logger logger = LoggerFactory.getLogger(ReportService.class);

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
		// System.out.println(path.substring(0, path.indexOf("?") + 1) +
		// URLEncoder.encode(path.substring(path.indexOf("?") + 1), "UTF-8"));
		if (report != null) {

			path = new StringBuffer(report.getReportName());
			try {
				// dimensions
				if (report.getDimensions() != null && report.getDimensions().size() > 0) {
					if (path.indexOf("?") == -1) {
						path.append("?dimensions=");
					} else {
						path.append("&dimensions=");
					}

					StringBuffer buffer = new StringBuffer();
					for (String dimension : report.getDimensions()) {
						if (buffer.length() == 0) {
							buffer.append(dimension);
						} else {
							buffer.append("," + dimension);
						}
					}
					// Encode
					path.append(URLEncoder.encode(buffer.toString(), "UTF-8"));
				}

				// filters
				if (report.getFilters() != null && report.getFilters().size() > 0 && !report.getFilters().isEmpty()) {
					if (path.indexOf("?") == -1) {
						path.append("?filter=");
					} else {
						path.append("&filter=");
					}

					int i = 0;
					StringBuffer buffer = new StringBuffer();
					for (ReportFilter f : report.getFilters()) {
						if (f.getKey() != null && f.getOperator() != null && f.getValue() != null
								&& !f.getKey().isEmpty() && !f.getOperator().isEmpty() && !f.getValue().isEmpty()) {

							buffer.append(f.getKey() + f.getOperator() + f.getValue());
							if (i != report.getFilters().size() - 1) {
								buffer.append("&");
							}
							i++;
						}
					}
					// encode
					path.append(URLEncoder.encode(buffer.toString(), "UTF-8"));
				}

				// having.
				if (report.getHaving() != null && !report.getHaving().isEmpty()) {
					if (path.indexOf("?") == -1) {
						path.append("?having=");
					} else {
						path.append("&having=");
					}

					StringBuffer buffer = new StringBuffer();
					int i = 0;
					for (Having having : report.getHaving()) {
						if (having.getKey() != null && having.getOperator() != null && having.getValue() != null
								&& !having.getKey().isEmpty() && !having.getOperator().isEmpty()
								&& !having.getValue().isEmpty()) {

							buffer.append(having.getKey() + having.getOperator() + having.getValue());

							if (i != report.getHaving().size() - 1) {
								buffer.append("&");
							}
							i++;
						}
					}
					// encode
					path.append(URLEncoder.encode(buffer.toString(), "UTF-8"));
				}

				// metrics
				if (report.getMetrics() != null && report.getMetrics().size() > 0 && !report.getMetrics().isEmpty()) {
					if (path.indexOf("?") == -1) {
						path.append("?metrics=");
					} else {
						path.append("&metrics=");
					}

					StringBuffer buffer = new StringBuffer();
					for (String metric : report.getMetrics()) {
						if (buffer.length() == 0) {
							buffer.append(metric);
						} else {
							buffer.append("," + metric);
						}
					}
					path.append(URLEncoder.encode(buffer.toString(), "UTF-8"));
				}

				// time rollup.
				if (report.getTime_rollup() != null && !report.getTime_rollup().isEmpty()) {
					uriAppender(path);

					path.append("time_rollup=" + report.getTime_rollup());
				}

				// time_ window || start_date || end_date
				if (report.getTime_window() != null && !report.getTime_window().isEmpty()
						&& report.getStart_date() == null && report.getEnd_date() == null) {

					uriAppender(path);

					path.append("time_window=" + report.getTime_window());

				} else if (report.getTime_window() == null 
						&& report.getStart_date() != null
						&& !report.getStart_date().isEmpty()
						&& report.getEnd_date() != null
						&& !report.getEnd_date().isEmpty()) {

					uriAppender(path);

					path.append("start_date=" + report.getStart_date());
					path.append("&end_date=" + report.getEnd_date());

				}

				// order
				if (report.getOrder() != null && report.getOrder().size() > 0 && !report.getOrder().isEmpty()) {
					uriAppender(path);

					StringBuffer buffer = new StringBuffer();
					for (String order : report.getOrder()) {
						if (buffer.length() == 0) {
							buffer.append("order=" + order);
						} else {
							buffer.append("," + order);
						}
					}
					path.append(buffer);
				}

				// pagelimit & page offset
				if (report.getPage_limit() != null && !report.getPage_limit().isEmpty()
						&& (report.getPage_offset() == null || report.getPage_offset().isEmpty())) {
					uriAppender(path);
					path.append("page_limit=" + report.getPage_limit());
				} else if (report.getPage_offset() != null && !report.getPage_offset().isEmpty()
						&& report.getPage_limit() != null && !report.getPage_limit().isEmpty()) {
					uriAppender(path);
					path.append("page_limit=" + report.getPage_limit());
					path.append("&page_offset=" + report.getPage_offset());
				}

				// precision
				if (report.getPrecision() > 0) {
					path.append("&" + String.valueOf(report.getPrecision()));
				}

			}

			catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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


	public void getReportData(Reports report, String finalPath, Connection connection, T1Response user)
	throws  ClientException {
		
		Response response = connection.getReportData(finalPath, user);
		
		if(response.getMediaType().getType().equals("text") 
				&& response.getMediaType().getSubtype().equals("xml")
				&& response.getStatus() != 200)  {
			
			try {
				
				ObjectMapper xmlMapper = new XmlMapper();
				String error = response.readEntity(String.class);
				ReportError re = xmlMapper.readValue(error, ReportError.class);
				throwReportError(re);

			} catch (JsonParseException e) {
				Utility.logStackTrace(e);
				throw new ClientException("Json Parse Exception Occured");
			} catch (JsonMappingException e) {
				Utility.logStackTrace(e);
				throw new ClientException("Json Mapping Exception Occured");
			} catch (IOException e) {
				Utility.logStackTrace(e);
				throw new ClientException("IO Exception Occured");
			}
		
		} else if(response.getMediaType().getType().equals("text") 
				&& response.getMediaType().getSubtype().equals("csv")
				&& response.getStatus() == 200){
			try {
				InputStream responseStream = response.readEntity(InputStream.class);
				BufferedReader reader = new BufferedReader(new InputStreamReader(responseStream));
				SimpleDateFormat df = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
				
				File dir = new File("reports");

				if(!dir.exists()) {
					dir.mkdir();
				} 
				
				File file = new File("reports/" + report.getReportName() + "_" + df.format(new Date()) + ".csv");
				FileWriter writer = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(writer);

				String line;

				while ((line = reader.readLine()) != null) {
					bw.write(line);
					bw.newLine();
				}

				bw.close();
				reader.close();
				
			} catch (IOException e) {
				Utility.logStackTrace(e);
				throw new ClientException("IO Exception Occured while saving the report");
			}
		}
	}
	
	public ReportValidationResponse validateReportData(Reports report, String finalPath, Connection connection, T1Response user)
	throws  ClientException {

		Response response = connection.getReportData(finalPath, user);
		ReportValidationResponse re = null;
		
		if(response.getMediaType().getType().equals("text") 
				&& response.getMediaType().getSubtype().equals("xml")) {
			
			try {
				
				ObjectMapper xmlMapper = new XmlMapper();
				String error = response.readEntity(String.class);
				re = xmlMapper.readValue(error, ReportValidationResponse.class);

			} catch (JsonParseException e) {
				Utility.logStackTrace(e);
				throw new ClientException("Json Parse Exception Occured");
			} catch (JsonMappingException e) {
				Utility.logStackTrace(e);
				throw new ClientException("Json Mapping Exception Occured");
			} catch (IOException e) {
				Utility.logStackTrace(e);
				throw new ClientException("IO Exception Occured");
			}
			
		}
		return re; 
	}

	private void throwReportError(ReportError re) throws ClientException {
		if(re != null) {
			StringBuffer buffer = new StringBuffer();
			if(re.getEntity() != null && re.getEntity().length > 0) {
				for(ReportErrorEntityInfo rentity : re.getEntity())	{
					if(rentity.getId()!= null 
							&& rentity.getType() != null
							&& !rentity.getId().isEmpty()
							&& !rentity.getType().isEmpty()) {
						
						buffer.append("Entity[ ID: " + rentity.getId() + ", Type: " + rentity.getType() + " ]");	
					}
					
				}
				
			}
			
			if(re.getStatus() != null && re.getStatus().length > 0) {
				for(ReportStatus stats : re.getStatus()) {
					if(stats.getCode() != null 
							&& !stats.getCode().isEmpty() 
							&& stats.getReason() != null 
							&& !stats.getReason().isEmpty()
							&& stats.getValue() != null 
							&& !stats.getValue().isEmpty()) {
						
						buffer.append("Status[ Code: " + stats.getCode() + ", Reason: " + stats.getReason() + ", value: " + stats.getValue() + " ]");
						
					}
				}
			}
			throw new ClientException(buffer.toString()); 
		}
		
	}

}
