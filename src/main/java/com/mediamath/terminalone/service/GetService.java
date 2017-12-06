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
import com.mediamath.terminalone.exceptions.ClientException;
import com.mediamath.terminalone.exceptions.ParseException;
import com.mediamath.terminalone.models.JsonPostErrorResponse;
import com.mediamath.terminalone.models.T1Error;
import com.mediamath.terminalone.models.T1Meta;
import com.mediamath.terminalone.utils.ConditionQuery;
import com.mediamath.terminalone.utils.Constants;
import com.mediamath.terminalone.utils.Filters;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetService {

	private static final String YYYY_MM_DD_T_HH_MM_SS = "yyyy-MM-dd'T'HH:mm:ss";

	/**
	 * GET service implementation.
	 * 
	 * @param query
	 *            expects a QueryCriteria
	 * @return StringBuilder object.
	 * @throws ClientException
	 *             exception.
	 * @throws ParseException
	 *             exception.
	 */
	public StringBuilder get(QueryCriteria query) throws ClientException {

		StringBuilder path = new StringBuilder("");

		String childPath;

		StringBuilder includePath;

		// param collection String example "advertisers"
		if (query.collection != null) {
			path.append(query.collection);
		} else {
			throw new ClientException("please specify: collection");
		}

		// param entity Int example ID 12121
		if (query.entity > 0) {
			path.append("/" + query.entity);
		}

		// param child String example: acl, permissions
		if (!query.child.isEmpty()) {
			childPath = constructChildPath(query.child);
			if (!"".equalsIgnoreCase(childPath)) {
				path.append(childPath);
			}
		} // end of child

		// param limit, should be key=value pair. example organization : 123456
		if (query.limit.size() == 1) {
			// for deals, "/media/" API base is used, which supports
			// ?[related_entity_name]_id=[related_entity ID]
			if (("deals").equals(query.collection)) {
				path.append("?");
			} else {
				path.append("/limit/");
			}
			for (String s : query.limit.keySet()) {
				if (!("".equals(path.toString()))) {
					path.append(s + "=" + query.limit.get(s));
				}
			}
		} else if (query.limit.size() > 1) {
			throw new ClientException(
					"Limit must consist of one parent collection (or chained parent collection) and a single value for it");
		}

		// applicable only for sitelist with id
		if (query.downloadSiteList) {
			path.append("/download.csv");
		}

		// param include
		if (query.includeConditionList != null && !query.includeConditionList.isEmpty()) {
			includePath = constructIncludePath(query.includeConditionList);
			if (includePath != null && !("".equals(path.toString()))
					&& !("".equalsIgnoreCase(includePath.toString()))) {
				path.append(includePath.toString());
			}
		} // end of include

		// param sortby example: sortby=id
		if (query.sortBy != null) {
			if (!("".equals(path.toString())) && path.indexOf("?") != -1) {
				path.append("&sort_by=" + query.sortBy);
			} else {
				path.append("?sort_by=" + query.sortBy);
			}
		} // end sortby

		// get_all and pagination parameters
		path = constructGetAllAndPagingPath(query, path);

		// param full can be string, list<String>, boolean
		StringBuilder fullPath = new StringBuilder("");
		if (query.full != null) {
			if (!query.full.getListValue().isEmpty()) {
				fullPath.append(constructFullPath(query.full.getListValue()));
			} else if (query.full.getBoolValue()) {
				fullPath.append("*");
			} else if (query.full.getStrValue() != null) {
				fullPath.append(query.full.getStrValue());
			}

			if (!("".equals(path.toString())) && path.indexOf("?") != -1) {
				path.append("&full=" + fullPath);
			} else {
				path.append("?full=" + fullPath);
			}
		}
		// param parent can be id only
		if (query.parent > 0) {
			if (!("".equals(path.toString())) && path.indexOf("?") != -1) {
				path.append("&parent=" + query.parent);
			} else {
				path.append("?parent=" + query.parent);
			}
		}

		// param QUERY example
		path = constructQueryPath(query, path);

		return path;
	}

	private StringBuilder constructQueryPath(QueryCriteria query, StringBuilder path) {
		if (query.query != null) {
			if (!("".equals(path.toString())) && path.indexOf("?") != -1) {
				path.append("&q=" + query.query);
			} else {
				path.append("?q=" + query.query);
			}
		}
		return path;
	}

	private StringBuilder constructGetAllAndPagingPath(QueryCriteria query, StringBuilder path) throws ClientException {
		// param get_all, get_all=True removes the need to worry about
		// pagination
		if (query.getAll) {
			if (!("".equals(path.toString())) && path.indexOf("?") != -1) {
				path.append("&get_all=" + query.getAll);
			} else {
				path.append("?get_all=" + query.getAll);
			}
		} // end getall
		else {
			// param pageLimit should not be > 100 example: page_limit=30
			// and param pageOffset, should be > 0 example: page_offset=10
			if (query.pageLimit > 100) {
				throw new ClientException("Page_Limit parameter should not exceed 100");
			}
			String pagePath;
			if (query.pageLimit > 0 && query.pageOffset > 0) {
				query.getAll = false;
			}
			pagePath = constructPaginationPath(query.pageLimit, query.pageOffset);
			if (!("".equals(path.toString())) && path.indexOf("?") != -1) {
				path.append("&" + pagePath);
			} else {
				path.append("?" + pagePath);
			}
		} // end pageLimit

		return path;
	}

	/**
	 * Find implementation.
	 * 
	 * @param query
	 *            expects a QueryCriteria.
	 * @return String object.
	 * @throws ClientException
	 *             exception.
	 * @throws ParseException
	 *             exception.
	 */
	public String find(QueryCriteria query) throws ClientException {

		StringBuilder paramVal = new StringBuilder();

		if (query.queryOperator.equalsIgnoreCase(Filters.IN)) {
			if (query.queryParams.getListValue().isEmpty()) {
				throw new ClientException("please specify: list for IN query");
			} else if (query.queryParams.getListValue().get(0) instanceof String
					|| query.queryParams.getListValue().get(0) instanceof Number) {
				paramVal.append("(");
				String prefix = "";
				for (Object obj : query.queryParams.getListValue()) {
					paramVal.append(prefix);
					paramVal.append(String.valueOf(obj));
					prefix = ",";
				}
				paramVal.append(")");
			} else {
				throw new ClientException("please specify: list values either in number or string");
			}
		} else {
			paramVal.append(query.queryParamName);
			paramVal.append(query.queryOperator);

			if (query.queryParams.getStrValue() != null) {
				paramVal.append(query.queryParams.getStrValue());
			} else if (query.queryParams.getNumberValue() != null) {
				paramVal.append(query.queryParams.getNumberValue());
			} else if (query.queryParams.getBoolValue()) {
				paramVal.append(1);
			} else if (!query.queryParams.getBoolValue()) {
				paramVal.append(0);
			}
		} // else

		return paramVal.toString();

	}

	private String constructChildPath(List<String> child) {
		StringBuilder childPathBuilder = new StringBuilder("");
		for (String childVal : child) {
			HashMap<String, Integer> childMap = Constants.childPaths.get(childVal);
			if (childMap != null) {
				for (Map.Entry<String, Integer> entry : childMap.entrySet()) {
					String key = entry.getKey();
					Integer val = entry.getValue();
					childPathBuilder.append((val > 0) ? ("/" + key + "/" + val) : ("/" + key));
				}
			} else {
				childPathBuilder.append("/" + childVal);
			}
		}
		return childPathBuilder.toString();
	}

	private StringBuilder constructIncludePath(List<ConditionQuery> includeConditionList) {
		StringBuilder includePath = new StringBuilder("");

		if (includeConditionList != null && includeConditionList.isEmpty()) {
			return includePath;
		}

		for (ConditionQuery conditionquery : includeConditionList) {
			if ("".equalsIgnoreCase(includePath.toString())) {
				includePath.append("?");
			} else {
				includePath.append("&");
			}

			if (conditionquery.getInclude() != null) {
				includePath.append("with=" + conditionquery.getInclude());
			}
			if (conditionquery.getWith() != null) {
				includePath.append("," + conditionquery.getWith());
			}
		} // for

		return includePath;
	}

	private StringBuilder constructFullPath(List<String> fullList) {
		StringBuilder fullListPath = new StringBuilder("");

		if (fullList != null && fullList.isEmpty()) {
			return fullListPath;
		}

		for (String fullStr : fullList) {
			if ("".equalsIgnoreCase(fullListPath.toString())) {
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
			pagePath += "page_limit=" + pageLimit;
		}
		if (pageOffset > 0 && !"".equalsIgnoreCase(pagePath)) {
			pagePath += "&page_offset=" + pageOffset;
		} else if ("".equalsIgnoreCase(pagePath)) {
			pagePath += "page_offset=" + pageOffset;
		}

		return pagePath;
	}

	/**
	 * parses error response of Get operation.
	 * 
	 * @param responseStr
	 *            string.
	 * @return JsonPostErrorResponse object.
	 */
	public JsonPostErrorResponse jsonGetErrorResponseParser(String responseStr) {
		JsonParser parser1 = new JsonParser();
		JsonObject obj = parser1.parse(responseStr).getAsJsonObject();

		JsonElement errorsElement = obj.get("errors");
		JsonElement errorElement = obj.get("error");
		JsonElement metaElement = obj.get("meta");

		JsonPostErrorResponse errorResponse = null;

		if (errorsElement != null || errorElement != null) {
			errorResponse = new JsonPostErrorResponse();
			GsonBuilder builder = new GsonBuilder();
			builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES);
			builder.setDateFormat(YYYY_MM_DD_T_HH_MM_SS);

			Gson gson = builder.create();

			parseErrorsElement(errorsElement, errorResponse, gson);

			parseErrorElement(errorElement, errorResponse, gson);

			parseMetaElement(metaElement, errorResponse, gson);
		}

		return errorResponse;
	}

	private void parseMetaElement(JsonElement metaElement, JsonPostErrorResponse errorResponse, Gson gson) {
		if (metaElement != null) {
			T1Meta meta = gson.fromJson(metaElement, T1Meta.class);
			errorResponse.setMeta(meta);
		}
	}

	private void parseErrorElement(JsonElement errorElement, JsonPostErrorResponse errorResponse, Gson gson) {
		if (errorElement != null) {
			T1Error error = gson.fromJson(errorElement, T1Error.class);
			errorResponse.setError(error);
		}
	}

	private void parseErrorsElement(JsonElement errorsElement, JsonPostErrorResponse errorResponse, Gson gson) {
		if (errorsElement != null) {
			if (!errorsElement.isJsonNull() && errorsElement.isJsonObject()) {
				T1Error errors = gson.fromJson(errorsElement, T1Error.class);
				// specific to video creatives
				if (errors != null && errors.getContent() == null && errors.getField() == null
						&& errors.getFieldError() == null && errors.getMessage() == null) {

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

				for (int i = 0; i < array.size(); i++) {
					if (!(array.get(i) instanceof JsonPrimitive)) {
						newArray.add(array.get(i));
					}
				}
				if (newArray.size() > 0) {
					Type type = new TypeToken<ArrayList<T1Error>>() {
					}.getType();
					List<T1Error> errors = gson.fromJson(newArray, type);
					errorResponse.setErrors(errors);
				}
			}
		}
	}

}
