package com.mediamath.jterminalone;

import java.util.HashMap;
import java.util.Properties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mediamath.jterminalone.models.Advertiser;
import com.mediamath.jterminalone.models.T1Entity;
import com.mediamath.jterminalone.models.JsonResponse;
import com.mediamath.jterminalone.utils.Utility;

/**
 * This Class is responsible for connection to T1 servers
 * 
 * @author chauhan_n
 *
 */
public class Connection {

	private static final Logger logger = LoggerFactory.getLogger(Connection.class);

	private static Properties configprop = Utility.loadConfigProperty();

	private String userAgent = null;

	private String api_base = null;

	private String path_mgmt = null;

	private HashMap<String, HashMap<String, String>> user = new HashMap<String, HashMap<String, String>>();

	
	/**
	 * Constructors
	 * 
	 */
	public Connection() {
		userAgent = configprop.getProperty("useragent");
		api_base = configprop.getProperty("api_base");
		path_mgmt = configprop.getProperty("path_mgmt");
	}

	/**
	 * handles post requests.
	 * 
	 * @param uri
	 * @param data
	 * @return
	 */
	// TODO refactor this.
	public String post(String uri, Object data, HashMap<String, HashMap<String, String>> userMap) {
		
		Client client = ClientBuilder.newClient(new ClientConfig());

		String url = api_base + path_mgmt + "/" + uri;
		logger.info("Target URL: " + url);

		WebTarget webTarget = client.target(url);
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		invocationBuilder.header("User-Agent", userAgent);

		// if session is available, then send it in the request.
		if (userMap != null && !userMap.isEmpty()) {
			if (!userMap.get("session").isEmpty() && userMap.get("session").get("sessionid") != null) {
				invocationBuilder.cookie("adama_session", userMap.get("session").get("sessionid"));
			}
		}

		Form form = null;
		if (data instanceof Form) {
			form = (Form) data;
		}

		String response = invocationBuilder.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED),String.class);
		logger.info("String Response Status " + response);
		
		
		/*logger.info("Response Status " + response.getStatus());
		boolean bufferflag = response.bufferEntity();
		logger.info("buffer flag status " + bufferflag);
		logger.info("Response Data" + response.readEntity(String.class));*/

		return response;
	}

	public String get(String uri, HashMap<String, HashMap<String, String>> userMap) {
		
		Client client = ClientBuilder.newClient(new ClientConfig());

		String url = api_base + path_mgmt + "/" + uri;

		logger.info("Target URL: " + url);

		WebTarget webTarget = client.target(url);
		Invocation.Builder invocationBuilder = webTarget.request();
		invocationBuilder.header("User-Agent", userAgent);
		invocationBuilder.header("Accept","application/vnd.mediamath.v1+json");
		
		// if session is available, then send it in the request.
		if (userMap != null && !userMap.isEmpty()) {
			if (!userMap.get("session").isEmpty() && userMap.get("session").get("sessionid") != null) {
				invocationBuilder.cookie("adama_session", userMap.get("session").get("sessionid"));
			}
		}

		//Response response = invocationBuilder.get();
		String response = invocationBuilder.get(String.class);

		//logger.info("String Response Status " + response);
/*		logger.info("Response Status " + response.getStatus());
		boolean bufferflag = response.bufferEntity();
		logger.info("buffer flag status " + bufferflag);
		logger.info("Response Data" + response.readEntity(String.class));
*/
		return response;
	}

	public HashMap<String, HashMap<String, String>> getUser() {
		return user;
	}

	public void setUser(HashMap<String, HashMap<String, String>> user) {
		this.user = user;
	}

}
