package com.mediamath.terminalone;

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
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mediamath.terminalone.Exceptions.ClientException;
import com.mediamath.terminalone.models.T1Response;
import com.mediamath.terminalone.utils.Utility;

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
		userAgent = generateUserAgent();
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
	public String post(String url, Form data, T1Response userMap) throws ClientException {
		
		if(data == null) {
			throw new ClientException("No Post Data");
		}
		
		Client client = ClientBuilder.newClient(new ClientConfig());
		
		logger.info("Target URL: " + url);

		WebTarget webTarget = client.target(url);
		Invocation.Builder invocationBuilder = webTarget.request();	//(MediaType.APPLICATION_JSON);
		invocationBuilder.header("User-Agent", userAgent);
		invocationBuilder.header("Accept","application/vnd.mediamath.v1+json");

		// if session is available, then send it in the request.
		/*if (userMap != null && !userMap.isEmpty()) {
			if (!userMap.get("session").isEmpty() && userMap.get("session").get("sessionid") != null) {
				invocationBuilder.cookie("adama_session", userMap.get("session").get("sessionid"));
			}
		}*/
		
		if (userMap != null ) {
			if (userMap.getData() != null 
					&& userMap.getData().getSession() != null 
					&& userMap.getData().getSession().getSessionid() != null 
					&& !userMap.getData().getSession().getSessionid().isEmpty()) {
				
				invocationBuilder.cookie("adama_session", userMap.getData().getSession().getSessionid());
			}
		}

		Response response = null;
		
		response = invocationBuilder.post(Entity.entity(data, MediaType.APPLICATION_FORM_URLENCODED));
		
		return response.readEntity(String.class);
		
		//response = invocationBuilder.post(Entity.entity(data, MediaType.APPLICATION_FORM_URLENCODED),String.class);
		//return response;
	}

	/**
	 * Multipart post.
	 * 
	 * @param url
	 * @param data
	 * @param userMap
	 * @return
	 * @throws ClientException
	 */
	public String post(String url, FormDataMultiPart data, T1Response userMap) throws ClientException {
		if(data == null) {
			throw new ClientException("No Post Data");
		}
		
		Client client = ClientBuilder.newClient(new ClientConfig()).register(MultiPartFeature.class);
		
		logger.info("Target URL: " + url);

		WebTarget webTarget = client.target(url);
		Invocation.Builder invocationBuilder = webTarget.request();
		invocationBuilder.header("User-Agent", userAgent);
		invocationBuilder.header("Accept","application/vnd.mediamath.v1+json");
		
		// if session is available, then send it in the request.
/*		if (userMap != null && !userMap.isEmpty()) {
			if (!userMap.get("session").isEmpty() && userMap.get("session").get("sessionid") != null) {
				invocationBuilder.cookie("adama_session", userMap.get("session").get("sessionid"));
			}
		}*/
		
		if (userMap != null ) {
			if (userMap.getData() != null 
					&& userMap.getData().getSession() != null 
					&& userMap.getData().getSession().getSessionid() != null 
					&& !userMap.getData().getSession().getSessionid().isEmpty()) {
				
				invocationBuilder.cookie("adama_session", userMap.getData().getSession().getSessionid());
			}
		}

		Response response = null;
		
		response = invocationBuilder.post(Entity.entity(data,  data.getMediaType()));
		
		return response.readEntity(String.class);
		
		
	}
	
	
	/**handles GET requests
	 * 
	 * @param uri
	 * @param userMap
	 * @return
	 */
	public String get(String url, T1Response userMap) {
		
		Response response = null;
		
		Client client = ClientBuilder.newClient(new ClientConfig());

		logger.info("Target URL: " + url);

		WebTarget webTarget = client.target(url);
		Invocation.Builder invocationBuilder = webTarget.request();
		invocationBuilder.header("User-Agent", userAgent);
		invocationBuilder.header("Accept","application/vnd.mediamath.v1+json");
		
		// if session is available, then send it in the request.
		/*if (userMap != null ) {
			if (!userMap.get("session").isEmpty() && userMap.get("session").get("sessionid") != null) {
				invocationBuilder.cookie("adama_session", userMap.get("session").get("sessionid"));
			}
		}*/
		
		if (userMap != null ) {
			if (userMap.getData() != null 
					&& userMap.getData().getSession() != null 
					&& userMap.getData().getSession().getSessionid() != null 
					&& !userMap.getData().getSession().getSessionid().isEmpty()) {
				
				invocationBuilder.cookie("adama_session", userMap.getData().getSession().getSessionid());
			}
		}

		response = invocationBuilder.get();
		
		return response.readEntity(String.class);
	}

	private String generateUserAgent(){
		String version = configprop.getProperty("version");
		return "t1-java/"+version+" java-client/"+System.getProperty("java.version");
	}

}
