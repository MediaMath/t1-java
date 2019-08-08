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

package com.mediamath.terminalone;

import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.ws.rs.ProcessingException;
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

import com.mediamath.terminalone.exceptions.ClientException;
import com.mediamath.terminalone.models.T1User;
import com.mediamath.terminalone.utils.Utility;

/**
 * This Class is responsible for connection to T1 servers.
 * 
 * @author chauhan_n
 *
 */
public class Connection {

	private static final String BEARER = "Bearer ";

	private static final String AUTHORIZATION = "Authorization";

	private static final String ADAMA_SESSION = "adama_session";

	private static final String APPLICATION_VND_MEDIAMATH_V1_JSON = "application/vnd.mediamath.v1+json";

	private static final String ACCEPT = "Accept";

	private static final String USER_AGENT = "User-Agent";

	private static final String TARGET_URL = "Target URL: %s";

	private static final String NO_POST_DATA = "No Post Data";

	private static final String UNKNOWN_HOST_EXCEPTION = "java.net.UnknownHostException";

	private static final Logger logger = LoggerFactory.getLogger(Connection.class);

	private static Properties configprop = Utility.loadConfigProperty();

	private String userAgent = null;

	/**
	 * Constructor.
	 * 
	 */
	public Connection() {
		userAgent = generateUserAgent();
	}

	/**
	 * Handles the POST operation to a given endpoint.
	 * 
	 * @param url
	 *            api end point url.
	 * @param data
	 *            requires a Form data object.
	 * @param userMap
	 *            requires a valid user login session.
	 * @return Response object.
	 * @throws ClientException
	 *             throws a client exception.
	 */
	public Response post(String url, Form data, T1User userMap) throws ClientException {

		if (data == null) {
			throw new ClientException(NO_POST_DATA);
		}

		Response response;
		Client client = instantiateSimpleClient();
		Invocation.Builder invocationBuilder = configureInvocationBuilder(url, userMap, client);
		try {
			response = invocationBuilder.post(Entity.entity(data, MediaType.APPLICATION_FORM_URLENCODED));
		} catch (ProcessingException ex) {
			if(isUnknownHostException(ex)) {
				throw new ClientException(UNKNOWN_HOST_EXCEPTION, ex);
			} else {
				throw ex;
			}
		}
		return response;
	}

	private boolean isUnknownHostException(ProcessingException ex) {
		return ex.getCause() != null && ex.getCause() instanceof UnknownHostException;
	}

	/**
	 * Handles the POST operation to a given endpoint -> Mainly Used for Login.
	 * 
	 * @param url
	 *            api end point url.
	 * @param data
	 *            requires a Form data object.
	 * @return Response object.
	 * @throws ClientException
	 *             throws a client exception.
	 */
	public Response post(String url, Form data) throws ClientException {

		if (data == null) {
			throw new ClientException(NO_POST_DATA);
		}

		Response response;
		Client client = instantiateSimpleClient();
		Invocation.Builder invocationBuilder = configureInvocationBuilder(url, null, client);
		try {
			response = invocationBuilder.post(Entity.entity(data, MediaType.APPLICATION_FORM_URLENCODED));
		} catch (ProcessingException ex) {
			if(isUnknownHostException(ex)) {
				throw new ClientException(UNKNOWN_HOST_EXCEPTION, ex);
			} else {
				throw ex;
			}
		}
		return response;
	}

	private Invocation.Builder configureInvocationBuilder(String url, T1User userMap, Client client) {
		if (url != null && !url.isEmpty()) {
			String targetURL = String.format(TARGET_URL, url);
			logger.info(targetURL);
		}
		WebTarget webTarget = client.target(url);
		Invocation.Builder invocationBuilder = webTarget.request();
		invocationBuilder.header(USER_AGENT, userAgent);
		invocationBuilder.header(ACCEPT, APPLICATION_VND_MEDIAMATH_V1_JSON);
		userSessionCheck(userMap, invocationBuilder);
		return invocationBuilder;
	}

	/**
	 * POST multipart data.
	 * 
	 * @param url
	 *            api endpoint url.
	 * @param data
	 *            FormDataMultiPart object.
	 * @param userMap
	 *            requires a valid logged in user session.
	 * @return Response object.
	 * @throws ClientException
	 *             exception
	 */
	public Response post(String url, FormDataMultiPart data, T1User userMap) throws ClientException {
		if (data == null) {
			throw new ClientException(NO_POST_DATA);
		}

		Response response;
		Client client = instantiateMultipartClient();
		Invocation.Builder invocationBuilder = configureInvocationBuilder(url, userMap, client);
		try {
			response = invocationBuilder.post(Entity.entity(data, data.getMediaType()));
		} catch (ProcessingException ex) {
			if(isUnknownHostException(ex)) {
				throw new ClientException(UNKNOWN_HOST_EXCEPTION, ex);
			} else {
				throw ex;
			}
		}
		return response;

	}

	/**
	 * POST Binary data.
	 * 
	 * @param url
	 *            api endpoint url.
	 * @param data
	 *            InputStream object.
	 * @param userMap
	 *            requires a valid logged in user session.
	 * @return Response object.
	 * @throws ClientException
	 *             exception
	 */
	public Response post(String url, InputStream data, T1User userMap) throws ClientException {
		if (data == null) {
			throw new ClientException(NO_POST_DATA);
		}

		Response response;
		Client client = instantiateSimpleClient();
		Invocation.Builder invocationBuilder = configureInvocationBuilder(url, userMap, client);
		try {
			response = invocationBuilder.post(Entity.entity(data, MediaType.APPLICATION_OCTET_STREAM_TYPE));
		} catch (ProcessingException ex) {
			if(isUnknownHostException(ex)) {
				throw new ClientException(UNKNOWN_HOST_EXCEPTION, ex);
			} else {
				throw ex;
			}
		}
		return response;

	}

	/**
	 * Application / Json POST.
	 * 
	 * @param url
	 *            requires api end point url.
	 * @param data
	 *            Json String to post.
	 * @param userMap
	 *            requires a valid login user information.
	 * @return Response object.
	 * @throws ClientException
	 *             exception.
	 */
	public Response post(String url, String data, T1User userMap) throws ClientException {
		if (data == null) {
			throw new ClientException(NO_POST_DATA);
		}

		Response response;
		Client client = instantiateMultipartClient();
		Invocation.Builder invocationBuilder = configureInvocationBuilder(url, userMap, client);
		try {
			response = invocationBuilder.post(Entity.entity(data, MediaType.APPLICATION_JSON));
		} catch (ProcessingException ex) {
			if(isUnknownHostException(ex)) {
				throw new ClientException(UNKNOWN_HOST_EXCEPTION, ex);
			} else {
				throw ex;
			}
		}
		return response;
	}

	/**
	 * Sends DELETE http request
	 * @param url
	 * 				requires api end point url.
	 * @param userMap
	 * 				requires a valid login user information.
	 * @return Response object.
	 */
	public Response delete(String url, T1User userMap) throws ClientException {
		Response response;
		Client client = instantiateSimpleClient();
		Invocation.Builder invocationBuilder = configureInvocationBuilder(url, userMap, client);
		try {
			response = invocationBuilder.delete();
		} catch (ProcessingException ex) {
			if(isUnknownHostException(ex)) {
				throw new ClientException(UNKNOWN_HOST_EXCEPTION, ex);
			} else {
				throw ex;
			}
		}
		return response;
	}

	/**
	 * handles GET requests
	 * 
	 * @param url
	 *            api end point url.
	 * @param userMap
	 *            requires a valid user login session.
	 * @return String object.
	 */
	public String get(String url, T1User userMap) throws ClientException{
		Response response;
		Client client = instantiateSimpleClient();
		Invocation.Builder invocationBuilder = configureInvocationBuilder(url, userMap, client);
		try {
			response = invocationBuilder.get();
		} catch (ProcessingException ex) {
			if(isUnknownHostException(ex)) {
				throw new ClientException(UNKNOWN_HOST_EXCEPTION, ex);
			} else {
				throw ex;
			}
		}
		return response.readEntity(String.class);
	}

	/**
	 * handles GET requests for Reports.
	 * 
	 * @param url
	 *            requires a valid url api endpoint.
	 * @param userMap
	 *            requires a valid user login session.
	 * @return Response object.
	 */
	public Response getReportData(String url, T1User userMap) throws ClientException {
		Response response;
		Client client = instantiateSimpleClient();
		Invocation.Builder invocationBuilder = configureInvocationBuilder(url, userMap, client);
		try {
			response = invocationBuilder.get();
		} catch (ProcessingException ex) {
			if(isUnknownHostException(ex)) {
				throw new ClientException(UNKNOWN_HOST_EXCEPTION, ex);
			} else {
				throw ex;
			}
		}
		return response;
	}

	private static Client client;
	private Client instantiateSimpleClient() {
		if (client==null)
			client = ClientBuilder.newClient(new ClientConfig());
		return client;
		//avoid leaking Client objects as causes issues under load.
	}

	private static Client multiClient;
	private Client instantiateMultipartClient() {
		if (multiClient==null)
			multiClient=ClientBuilder.newClient(new ClientConfig()).register(MultiPartFeature.class);
		return multiClient;
		//avoid leaking Client objects as causes issues under load.

	}

	private void userSessionCheck(T1User userMap, Invocation.Builder invocationBuilder) {
		if (userMap != null && userMap.getToken() != null && !userMap.getToken().isEmpty()) {
			invocationBuilder.header(AUTHORIZATION, BEARER + userMap.getToken());
		}
		if (checkUserMapData(userMap) && checkSession(userMap) && checkSessionID(userMap)) {
			invocationBuilder.cookie(ADAMA_SESSION, userMap.getData().getSession().getSessionid());
		}
	}

	private boolean checkSessionID(T1User userMap) {
		return userMap.getData().getSession().getSessionid() != null
				&& !userMap.getData().getSession().getSessionid().isEmpty();
	}

	private boolean checkUserMapData(T1User userMap) {
		return userMap != null && userMap.getData() != null;
	}

	private boolean checkSession(T1User userMap) {
		return userMap.getData().getSession() != null;
	}

	private String generateUserAgent() {
		String version = configprop.getProperty("t1.version");
		return "t1-java/" + version + " java-client/" + System.getProperty("java.version");
	}

}
