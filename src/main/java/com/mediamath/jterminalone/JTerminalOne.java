package com.mediamath.jterminalone;

import java.lang.reflect.Type;
import java.util.HashMap;

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

		logger.info("Loading Environment - Authenticating.");
		Form form = jt1Service.getLoginFormData(username, password, api_key);

		logger.info("Loading Environment - Authenticating.");
		String url = jt1Service.constructURL("login");
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

	/**public GET Method
	 *  TODO - replace Input params by T1Request object 
	 * @param collection
	 * @param entity
	 * @param child
	 * @return
	 */
	public String get(String collection, long entity, String child) {
		String path="";
		String childPath = "";
		
		//param collection String example "advertisers"
		if(!collection.equals(null)){
			path = collection+"/";
		}
		
		//param entity Int example ID 12121
		if(entity > 0){
			path += entity;
		}
		
		//param child String example: acl, permissions
		if(child!=null){
			HashMap<String, Integer> childMap = Constants.childPaths.get(child);
			for(String s : childMap.keySet()){
				if(s.equalsIgnoreCase("target_dimensions")){
					childPath += "?target_dimensions="+String.valueOf(childMap.get("target_dimensions"));
				}
				else{
					childPath +="?"+child;
				}
			}
			if(!path.equalsIgnoreCase("") && !childPath.equalsIgnoreCase("")){
				path += childPath;
			}
		} //end of child
		
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
		String url = jt1Service.constructURL("login");
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
	
}
