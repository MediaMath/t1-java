package com.mediamath.jterminalone.service;

import java.util.Properties;

import javax.ws.rs.core.Form;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mediamath.jterminalone.utils.Utility;
/** Service to support JTerminalOne class
 * 
 * @author chaudhari_j
 *
 */
public class JT1Service {

	private static final Logger logger = LoggerFactory.getLogger(JT1Service.class);

	private static Properties configprop = Utility.loadConfigProperty();
	private String URL =null;
	private String api_base = null;
	private String path_mgmt = null;

	
	public JT1Service(){
		this.api_base = configprop.getProperty("api_base");
		this.path_mgmt = configprop.getProperty("path_mgmt");
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
}
