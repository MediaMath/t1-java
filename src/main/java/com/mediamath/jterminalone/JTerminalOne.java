package com.mediamath.jterminalone;

import java.lang.reflect.Type;
import java.util.HashMap;

import javax.ws.rs.core.Form;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
	}

	/**
	 * the other constructor, tries to connect with the credentials provided.
	 * 
	 */
	public JTerminalOne(String username, String password, String api_key) {

		this();

		logger.info("Loading Environment - Authenticating.");
		Form form = new Form();
		form.param("user", username);
		form.param("password", password);
		form.param("api_key", api_key);

		logger.info("Loading Environment - Authenticating.");
		String response = connection.post("login", form, null);
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

	//TODO refactor this.
	public String get(String collection, int entity) {
		String path = collection + "/" + String.valueOf(entity+ "?with=agency");
		return path;
	}

	/**
	 * basic authentication method.
	 * 
	 * @return boolean isauthenticated.
	 */
	public boolean authenticate(String username, String password, String api_key) {

		// TODO validate
		logger.info("Authenticating.");
		Form form = new Form();
		form.param("user", username);
		form.param("password", password);
		form.param("api_key", api_key);

		String response = connection.post("login", form, null);
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
