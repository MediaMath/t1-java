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
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mediamath.jterminalone.Exceptions.ClientException;
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

	/**
	 * Constructors
	 * 
	 */
	public Connection() {
		userAgent = configprop.getProperty("useragent");
	}

	/**
	 * handles post requests.
	 * 
	 * @param uri
	 * @param data
	 * @return
	 * @throws ClientException 
	 */
	// TODO refactor this.
	public String post(String url, Object data, HashMap<String, HashMap<String, String>> userMap) throws ClientException {
		
		if(data == null) {
			throw new ClientException("No Post Data");
		}
		
		Client client = ClientBuilder.newClient(new ClientConfig());
		
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

		return response;
	}

	/**handles GET requests
	 * 
	 * @param uri
	 * @param userMap
	 * @return
	 */
	public String get(String url, HashMap<String, HashMap<String, String>> userMap) {
		
		Response response = null;
		
		Client client = ClientBuilder.newClient(new ClientConfig());

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

		response = invocationBuilder.get();
		
		return response.readEntity(String.class);
	}

}
