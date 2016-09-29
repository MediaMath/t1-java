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
package com.mediamath.terminalone.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.Response;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;
import com.mediamath.terminalone.QueryCriteria;
import com.mediamath.terminalone.Exceptions.ClientException;
import com.mediamath.terminalone.Exceptions.ParseException;
import com.mediamath.terminalone.models.JsonPostErrorResponse;
import com.mediamath.terminalone.models.T1Error;
import com.mediamath.terminalone.models.T1Meta;
import com.mediamath.terminalone.utils.ConditionQuery;
import com.mediamath.terminalone.utils.Constants;
import com.mediamath.terminalone.utils.Filters;

public class GetService {
	
	private static final String YYYY_MM_DD_T_HH_MM_SS = "yyyy-MM-dd'T'HH:mm:ss";
	
	public StringBuffer get(QueryCriteria query) throws ClientException, ParseException {
		
		StringBuffer path=new StringBuffer("");
		
		String childPath = "";
		
		StringBuffer includePath = new StringBuffer("");
		
		//param collection String example "advertisers"
		if(!query.collection.equals(null)){
			path.append(query.collection);
		} else {
			throw new IllegalArgumentException("please specify: collection");
		}
		
		//param entity Int example ID 12121
		if(query.entity > 0){
			path.append("/"+String.valueOf(query.entity));
		}
		
		//param child String example: acl, permissions
		if(query.child!=null){
			childPath = constructChildPath(query.child);
			//if child path is not available then add child in GET URL
			if(childPath.equalsIgnoreCase("") && (query.child!=null && !query.child.equalsIgnoreCase(""))){
				childPath = "/"+query.child;
			}
			if(!path.toString().equalsIgnoreCase("") && !childPath.equalsIgnoreCase("")){
				path.append(childPath);
			}
		} //end of child
		
		//param limit, should be key=value pair. example organization : 123456
		if(query.limit.size()>0){
			path.append("/limit/");
			for(String s : query.limit.keySet()){
				if(!path.toString().equalsIgnoreCase("") && path.indexOf("?")!=-1){
					//TODO raise error
				}
				if(!path.toString().equalsIgnoreCase("")){
					path.append(s+"="+String.valueOf(query.limit.get(s)));
				}
			}
		}
		
		//param include
		if(query.includeConditionList != null && !query.includeConditionList.isEmpty()) {
			includePath = constructIncludePath(query.includeConditionList);
			
			if(!path.toString().equalsIgnoreCase("") && !includePath.toString().equalsIgnoreCase("")) {
				path.append(includePath.toString());
			}
		}//end of include
		
		//param sortby example: sortby=id
		if(query.sortBy!=null){
			if(!path.toString().equalsIgnoreCase("") && path.indexOf("?")!=-1){
				path.append("&sort_by="+query.sortBy);
			}
			else{
				path.append("?sort_by="+query.sortBy);
			}
		}//end sortby
		
		//param get_all, get_all=True removes the need to worry about pagination
		if(query.getAll){
			if(!path.toString().equalsIgnoreCase("") && path.indexOf("?")!=-1){
				path.append("&get_all="+query.getAll);
			}
			else{
				path.append("?get_all="+query.getAll);
			}
		}
		
		//param pageLimit should not be > 100 example: page_limit=30 
		//and param pageOffset, should be > 0 example: page_offset=10 
		if(query.pageLimit > 100){
			throw new ClientException("Page_Limit parameter should not exceed 100");
		}
		else if(!query.getAll){
			String pagePath = "";
			if(query.pageLimit>0 && query.pageOffset > 0){
				query.getAll=false;
			}
			pagePath = constructPaginationPath(query.pageLimit, query.pageOffset);
			if(!path.toString().equalsIgnoreCase("") && path.indexOf("?")!=-1){
				path.append("&"+pagePath);
			}
			else{
				path.append("?"+pagePath);
			}
		}//end pageLimit
		
		
		
		//param full can be string, list<String>, boolean
		StringBuffer fullPath = new StringBuffer("");
		if(query.full!=null){
			if(query.full.getListValue().size()>0){
				fullPath.append(constructFullPath(query.full.getListValue()));
			}else if(query.full.getBoolValue()){
				fullPath.append("*");
			}else if(query.full.getStrValue()!=null){
				fullPath.append(query.full.getStrValue());
			}
			
			if(!path.toString().equalsIgnoreCase("") && path.indexOf("?")!=-1){
				path.append("&full="+fullPath);
			}
			else{
				path.append("?full="+fullPath);
			}
		}
		
		
		//param QUERY example 
		if(query.query!=null){
			if(!path.toString().equalsIgnoreCase("") && path.indexOf("?")!=-1){
				path.append("&q="+query.query);
			}
			else{
				path.append("?q="+query.query);
			}
		}
		
		
		return path;
	}
	
	public String find(QueryCriteria query) throws ClientException, ParseException  {
		
		StringBuffer qParamVal = new StringBuffer();
		
		if(query.queryOperator.equalsIgnoreCase(Filters.IN)){
			if(query.queryParams.getListValue()==null || (query.queryParams.getListValue()!=null && query.queryParams.getListValue().size() <1)){
				//TODO raise TypeError
			}else{
				qParamVal.append("(");
				if(query.queryParams.getListValue().get(0) instanceof String || query.queryParams.getListValue().get(0) instanceof Number){
					String prefix = "";
					for(Object obj : query.queryParams.getListValue()){
						qParamVal.append(prefix);
						qParamVal.append(String.valueOf(obj));
						prefix = ",";
					}
				}else{
					//TODO raise typeError
				}
				
				qParamVal.append(")");
			}
		}else{
			qParamVal.append(query.queryParamName);
			qParamVal.append(query.queryOperator);
			
			if(query.queryParams.getStrValue()!=null){
				qParamVal.append(query.queryParams.getStrValue());
			}
			else if(query.queryParams.getNumberValue() != null){
				qParamVal.append(query.queryParams.getNumberValue());
			}
			else if(query.queryParams.getBoolValue()==true){
				qParamVal.append(1);
			}
			else if(query.queryParams.getBoolValue()==false){
				qParamVal.append(0);
			}
	
		}
		
		qParamVal.toString();
		
	
		
		return qParamVal.toString();
		
	}


	private String constructChildPath(String child) {
		String childPath = "";
		HashMap<String, Integer> childMap = Constants.childPaths.get(child);
		if(childMap!=null){
			for (String s : childMap.keySet()) {
				/*if (s.equalsIgnoreCase("target_dimensions")) {
					childPath += "?target_dimensions=" + String.valueOf(childMap.get("target_dimensions"));
				} else {
					childPath += "?" + child;
				}*/
				
				if(childMap.get(s) > 0){
					childPath += "/"+s+"/"+childMap.get(s);
				}else{
					childPath += "/"+s;
				}
			}
		}

		return childPath;
	}
		
	private StringBuffer constructIncludePath(List<ConditionQuery> includeConditionList) {
		StringBuffer includePath = new StringBuffer("");
		for (ConditionQuery conditionquery : includeConditionList) {
			if (includePath.toString().equalsIgnoreCase("")) {
				if (conditionquery.getInclude() != null) {
					includePath.append("?with=" + conditionquery.getInclude());
					if (conditionquery.getWith() != null) {
						includePath.append("," + conditionquery.getWith());
					}
				}
			} else {
				if (conditionquery.getInclude() != null) {
					includePath.append("&with=" + conditionquery.getInclude());
					if (conditionquery.getWith() != null) {
						includePath.append("," + conditionquery.getInclude());
					}
				}
			}
		}

		return includePath;
	}
		
	private StringBuffer constructFullPath(List<String> fullList) {
		StringBuffer fullListPath = new StringBuffer("");
		for (String fullStr : fullList) {
			if (fullListPath.toString().equalsIgnoreCase("")) {
				fullListPath.append(fullStr);
			} else {
				fullListPath.append(",");
				fullListPath.append(fullStr);
			}
		}

		return fullListPath;
	}
		
	private String constructPaginationPath(int pageLimit, int pageOffset) {
		String pagePath = "";
		if (pageLimit > 0 && pageLimit <= 100) {
			pagePath += "page_limit=" + String.valueOf(pageLimit);
		}
		if (pageOffset > 0) {
			if (!pagePath.toString().equalsIgnoreCase("")) {
				pagePath += "&page_offset=" + String.valueOf(pageOffset);
			} else {
				pagePath += "page_offset=" + String.valueOf(pageOffset);
			}
		}

		return pagePath;
	}

	public JsonPostErrorResponse jsonGetErrorResponseParser(String responseStr) {
		JsonParser parser1 = new JsonParser();
		JsonObject obj = parser1.parse(responseStr).getAsJsonObject();
		
		JsonElement errorsElement = obj.get("errors");
		JsonElement errorElement = obj.get("error");
		JsonElement metaElement = obj.get("meta");
		
		JsonPostErrorResponse errorResponse = null;
		
		if(errorsElement != null || errorElement != null) {
			errorResponse = new JsonPostErrorResponse();
			GsonBuilder builder = new GsonBuilder();
			builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES);
			builder.setDateFormat(YYYY_MM_DD_T_HH_MM_SS);
			
			Gson g = builder.create();

			if (errorsElement != null) {
				if (errorsElement.isJsonNull()) {

				} else if (errorsElement.isJsonObject()) {
					T1Error errors = g.fromJson(errorsElement, T1Error.class);
					// specific to video creatives
					if(errors != null 
							&& errors.getContent() == null 
							&& errors.getField() == null 
							&& errors.getFieldError() == null 
							&& errors.getMessage() == null) {
						
						GsonBuilder videoBuilder = new GsonBuilder();
						videoBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
						videoBuilder.setDateFormat(YYYY_MM_DD_T_HH_MM_SS);
						
						Gson vidgson = videoBuilder.create();

						errors = vidgson.fromJson(errorsElement, T1Error.class);
					}
					errorResponse.setErrors(errors);
				} else if (errorsElement.isJsonArray()) {
					JsonArray array = errorsElement.getAsJsonArray();
					JsonArray newArray = new JsonArray();
					
					for(int i = 0; i < array.size(); i++) {
						if(!(array.get(i) instanceof JsonPrimitive)) {
							newArray.add(array.get(i));
							
						}
					}
					if(newArray.size() > 0) {
						errorsElement = newArray;
						Type t =  new TypeToken<ArrayList<T1Error>>(){}.getType();
						List<T1Error> errors = g.fromJson(errorsElement, t);
						errorResponse.setErrors(errors);
					}
				}
			}

			if (errorElement != null) {
				T1Error error = g.fromJson(errorElement, T1Error.class);
				errorResponse.setError(error);
			}

			if(metaElement != null) {
				T1Meta meta = g.fromJson(metaElement, T1Meta.class);
				errorResponse.setMeta(meta);	
			}
		}
		
		return errorResponse;
	}
	
	
}
