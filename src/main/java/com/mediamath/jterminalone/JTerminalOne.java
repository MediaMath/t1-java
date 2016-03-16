package com.mediamath.jterminalone;

import java.lang.reflect.Type;
import java.util.HashMap;

import javax.ws.rs.core.Form;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mediamath.jterminalone.Exceptions.ClientException;
import com.mediamath.jterminalone.Exceptions.ParseException;
import com.mediamath.jterminalone.Exceptions.T1Exception;
import com.mediamath.jterminalone.models.JsonResponse;
import com.mediamath.jterminalone.models.T1Entity;
import com.mediamath.jterminalone.models.T1Error;
import com.mediamath.jterminalone.service.JT1Service;
import com.mediamath.jterminalone.utils.Constants;
import com.mediamath.jterminalone.utils.Filters;
import com.mediamath.jterminalone.utils.T1JsonToObjParser;

/**
 * handles the authentication, session, entity
 * retrieval, creation etc.
 *
 */
public class JTerminalOne {

	/*
	 * logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(JTerminalOne.class);

	/*
	 * connection object
	 */
	public Connection connection = null;

	/*
	 * service object.
	 */
	private JT1Service jt1Service =null;
	
	/*
	 * maintains user session
	 */
	private HashMap<String, HashMap<String, String>> user = new HashMap<String, HashMap<String, String>>();
	
	
	/*
	 * is authenticated? 
	 */
	private boolean authenticated = false;

	/**
	 * Default Constructor
	 */
	public JTerminalOne() {
		logger.info("Loading Environment - setting up connection.");
		connection = new Connection();
		jt1Service = new JT1Service();
	}

	/**
	 * the other constructor, tries to connect with the credentials provided.
	 * @throws ClientException 
	 * 
	 */
	public JTerminalOne(String username, String password, String api_key) throws ClientException {
		this();
		
		if(api_key == null || api_key.isEmpty()) {
			logger.error("Environment does not exist");
			throw new ClientException("Please Provide Valid Enviornment");
		}
		
		if(username.isEmpty() || password.isEmpty()) {
			logger.error("Please provide valid credentials.");
			throw new IllegalStateException();
		}

		logger.info("Loading Environment - Authenticating.");
		Form form = jt1Service.getLoginFormData(username, password, api_key);

		logger.info("Loading Environment - Authenticating.");
		String url = jt1Service.constructURL(new StringBuffer("login"));
		String response = connection.post(url, form, null);
		getUserSessionInfo(response);
		
		authenticated = true;
		
	}

	/**
	 * Maintains user session information.
	 * 
	 * @param response
	 */
	private void getUserSessionInfo(String response) {
		Gson gson = new Gson();
		Type stringStringMap = new TypeToken<HashMap<String, HashMap<String, String>>>() {}.getType();
		HashMap<String, HashMap<String, String>> map = gson.fromJson(response, stringStringMap);
		this.setUser(map);
	}
	
	public JsonResponse<? extends T1Entity> get(QueryCriteria query) throws ClientException, ParseException {
		
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
			HashMap<String, Integer> childMap = Constants.childPaths.get(query.child);
			for(String s : childMap.keySet()){
				if(s.equalsIgnoreCase("target_dimensions")){
					childPath += "?target_dimensions="+String.valueOf(childMap.get("target_dimensions"));
				}
				else{
					childPath +="?"+query.child;
				}
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
			for(ConditionQuery conditionquery : query.includeConditionList) {
				if(includePath.toString().equalsIgnoreCase("")) {
					if(conditionquery.getInclude() != null) {
						includePath.append("?with=" + conditionquery.getInclude());
						if(conditionquery.getWith() != null) {
							includePath.append("," + conditionquery.getWith());
						}
					}
				} else {
					if(conditionquery.getInclude() != null) {
						includePath.append("&with=" + conditionquery.getInclude());
						if(conditionquery.getWith() != null) {
							includePath.append("," + conditionquery.getInclude());
						}
					}
				}
			}
			
			if(!path.toString().equalsIgnoreCase("") && !includePath.toString().equalsIgnoreCase("")) {
				path.append(includePath.toString());
			}
		}//end of include
		
		//param sortby example: sortby=id
		if(query.sortBy!=null){
			if(!path.toString().equalsIgnoreCase("") && !includePath.toString().equalsIgnoreCase("") && path.indexOf("?")!=-1){
				path.append("&sort_by="+query.sortBy);
			}
			else{
				path.append("?sort_by="+query.sortBy);
			}
		}//end sortby
		
		//param pageLimit, should not be > 100 example: page_limit=30
		if(query.pageLimit > 100){
			throw new ClientException("Page_Limit parameter should not exceed 100");
		}
		else{
			if(!path.toString().equalsIgnoreCase("") && path.indexOf("?")!=-1){
				path.append("&page_limit="+String.valueOf(query.pageLimit));
			}
			else{
				path.append("?page_limit="+String.valueOf(query.pageLimit));
			}
		}//end pageLimit
		
		//param pageOffset, should not be > 100 example: page_offset=100
		if(query.pageOffset>0){
			if(!path.toString().equalsIgnoreCase("") && path.indexOf("?")!=-1){
				path.append("&page_offset="+String.valueOf(query.pageOffset));
			}
			else{
				path.append("?page_offset="+String.valueOf(query.pageOffset));
			}
		}//end pageoffset
		
		//param QUERY example 
		if(query.query!=null){
			if(!path.toString().equalsIgnoreCase("") && path.indexOf("?")!=-1){
				path.append("&q="+query.query);
			}
			else{
				path.append("?q="+query.query);
			}
		}
		
		
		// get the data from t1 servers.
		String finalPath = jt1Service.constructURL(path);
		String response = this.connection.get(finalPath, this.getUser());
		JsonResponse<? extends T1Entity> jsonResponse;
		// parse the data to entities.
		try{
			jsonResponse = parseResponse(query, response);
			jsonResponse = checkResponseEntities(jsonResponse);
		
		} catch (ParseException e) {
			
			throw new ClientException("Unable to parse the response");
		
		}
		
		// filter and validate data
		
		
		
		return jsonResponse;
	}
	
	private JsonResponse<? extends T1Entity> checkResponseEntities(JsonResponse<? extends T1Entity> jsonResponse) {
		
		if(jsonResponse != null) {
			StringBuffer strbuff = new StringBuffer();
			if(jsonResponse.getErrors() != null) {
				for(T1Error error: jsonResponse.getErrors()) {
					if(error.getMessage() != null) {
						
						strbuff.append(error.getMessage());
					}
				}
			}
		}
		
		return jsonResponse;
	}

/**
 * parses the response to objects.
 * 
 * @param query
 * @param response
 * @return
 * @throws ParseException
 */
private JsonResponse<? extends T1Entity> parseResponse(QueryCriteria query, String response) throws ParseException {
	T1JsonToObjParser parser = new T1JsonToObjParser();
	int result = parser.getJsonElementType(response);
	Type JsonResponseType = null;
	JsonResponse<? extends T1Entity> jsonresponse = null;
	
	if(query.collection != null) {
		
		if (result != 0) {
			if (result == 1) {
				JsonResponseType = Constants.getEntityType.get(query.collection);
			} else if (result == 2) {
				JsonResponseType = Constants.getListoFEntityType.get(query.collection);
			}

			jsonresponse = parser.parseJsonToObj(response, JsonResponseType);
			
		}
	}
	return jsonresponse;
}
	
	
	/** Find method alternative to query of get
	 * 
	 * @param query
	 * @return
	 * @throws ParseException 
	 * @throws ClientException 
	 */
	public JsonResponse<? extends T1Entity> find(QueryCriteria query) throws ClientException, ParseException  {
		
		StringBuffer qParamVal = new StringBuffer();
		
		qParamVal.append(query.queryParam);
		//qParamVal.append(query.queryOperator);

		//If operator is IN ie. when IN query, then check list provided and validate list for numbers and string only
		if(query.queryOperator.equalsIgnoreCase(Filters.IN)){
			if(query.queryParamValueList==null || (query.queryParamValueList!=null && query.queryParamValueList.size() <1)){
				//TODO raise TypeError
			}else{
				qParamVal.append("(");
				if(query.queryParamValueList.get(0) instanceof String || query.queryParamValueList.get(0) instanceof Number){
					String prefix = "";
					for(Object obj : query.queryParamValueList){
						qParamVal.append(prefix);
						qParamVal.append(String.valueOf(obj));
						prefix = ",";
					}
				}else{
					//TODO raise typeError
				}
				
				qParamVal.append(")");
			}
		} 
		else if(query.queryParamValueStr!=null){
			qParamVal.append(query.queryParamValueStr);
		}
		else if(query.queryParamValueNumber != null){
			qParamVal.append(query.queryParamValueNumber);
		}
		else if(query.queryParamValueBoolean==true){
			qParamVal.append(1);
		}
		else if(query.queryParamValueBoolean==false){
			qParamVal.append(0);
		}

		query.query =  qParamVal.toString();
		
		return this.get(query);
		
	}


	/**
	 * basic authentication method.
	 * 
	 * @return boolean isauthenticated.
	 */
	public boolean authenticate(String username, String password, String api_key) throws ClientException {

		// TODO validate
		logger.info("Authenticating.");
		
		Form form = jt1Service.getLoginFormData(username, password, api_key);
		String url = jt1Service.constructURL(new StringBuffer("login"));
		String response = null;
		
		response = connection.post(url, form, null);
		
		getUserSessionInfo(response);

		// TODO handle Exception
		if (response != null && !response.isEmpty())
			return true;
		else
			return false;

	}

	/*
	 * getters and setters
	 */
	public HashMap<String, HashMap<String, String>> getUser() {
		return user;
	}

	public void setUser(HashMap<String, HashMap<String, String>> user) {
		this.user = user;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}
	
}
