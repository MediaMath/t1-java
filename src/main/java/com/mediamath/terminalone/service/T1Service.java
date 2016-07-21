package com.mediamath.terminalone.service;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.ws.rs.core.Form;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mediamath.terminalone.utils.ConditionQuery;
import com.mediamath.terminalone.utils.Constants;
import com.mediamath.terminalone.utils.Utility;


/** Service to support terminalone class
 * 
 * @author chaudhari_j
 *
 */
public class T1Service {

	private static final Logger logger = LoggerFactory.getLogger(T1Service.class);

	private static Properties configprop = Utility.loadConfigProperty();
	private String URL =null;
	private String api_base = null;
	private String path_mgmt = null;
	private String videoCreativeURL = null;
	
	public T1Service(){
		this.api_base = configprop.getProperty("api_base");
		this.path_mgmt = configprop.getProperty("path_mgmt");
		this.videoCreativeURL = configprop.getProperty("videoCreativeURL");
	}
	
	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}
	
	public String getApi_base() {
		return api_base;
	}

	public void setApi_base(String api_base) {
		this.api_base = api_base;
	}

	public String getPath_mgmt() {
		return path_mgmt;
	}

	public void setPath_mgmt(String path_mgmt) {
		this.path_mgmt = path_mgmt;
	}

	/**Construct Full URL by joining api_base, mgt_path and path created in TerminalOne Service
	 * 
	 * @param path
	 * @return
	 */
	public String constructURL(StringBuffer path){
		String url = api_base + path_mgmt + "/" + path.toString();
		return url;
	}
	
	/**Construct form data required for login purpose
	 * 
	 * @param username
	 * @param password
	 * @param api_key
	 * @return
	 */
	public Form getLoginFormData(String username, String password, String api_key){
		
		Form form = new Form();
		form.param("user", username);
		form.param("password", password);
		form.param("api_key", api_key);
		
		return form;
	}

	public String getVideoCreativeURL() {
		return videoCreativeURL;
	}

	public void setVideoCreativeURL(String videoCreativeURL) {
		this.videoCreativeURL = videoCreativeURL;
	}
	
	
	
	
}
