package com.mediamath.jterminalone;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.Form;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mediamath.jterminalone.service.JT1Service;
import com.mediamath.jterminalone.utils.Constants;

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

	public JT1Service jt1Service =null;
	
	/*
	 * maintains user session
	 */
	private HashMap<String, HashMap<String, String>> user = new HashMap<String, HashMap<String, String>>();
	
	
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
	 * 
	 */
	public JTerminalOne(String username, String password, String api_key) {
		this();
		
		if(username.isEmpty() || password.isEmpty() || api_key.isEmpty()) {
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
	
	public String get(QueryCriteria query) {
		StringBuffer path=new StringBuffer("");
		String childPath = "";
		StringBuffer includePath = new StringBuffer("");
		
		//param collection String example "advertisers"
		if(!query.collection.equals(null)){
			path.append(query.collection);
		}
		
		//param entity Int example ID 12121
		if(query.entity > 0){
			path.append("/"+String.valueOf(query.entity));
		}
		
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
		if(query.pageLimit>100){
			//TODO throw clientexception
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
		
		return jt1Service.constructURL(path);
	}
	


	/**
	 * basic authentication method.
	 * 
	 * @return boolean isauthenticated.
	 */
	public boolean authenticate(String username, String password, String api_key) {

		// TODO validate
		logger.info("Authenticating.");
		
		Form form = jt1Service.getLoginFormData(username, password, api_key);
		String url = jt1Service.constructURL(new StringBuffer("login"));
		String response = connection.post(url, form, null);
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
	
	
	public static void main(String args[]){
		JTerminalOne jt1 = new JTerminalOne();
		jt1.authenticate("nitesh.chauhan@xoriant.com", "xoriant123#", "e34f74vnubr9uxasz2n7bdfv");
		
		QueryCriteria query = QueryCriteria.builder()
									.setCollection("advertisers")
									.setSortby("-id")
									.build();
		
		String uri = jt1.get(query);
		
		String response = jt1.connection.get(uri, jt1.getUser());
		logger.info(response);
	}
}
