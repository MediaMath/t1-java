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
import org.glassfish.jersey.client.ClientProperties;
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

	private static final String NO_PUT_DATA = "No Put Data";

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
		response = invocationBuilder.post(Entity.entity(data, MediaType.APPLICATION_FORM_URLENCODED));
		return response;
	}

	/**
	 * Handles the DELETE operation to a given endpoint.
	 * 
	 * @param url
	 *            api end point url.
	 * @param userMap
	 *            requires a valid user login session.
	 * @return Response object.
	 * @throws ClientException
	 *             throws a client exception.
	 */
	public Response delete(String url, Form data, T1User userMap) throws ClientException {

		Response response;
		Client client = instantiateSimpleClientSupressed();
		Invocation.Builder invocationBuilder = configureInvocationBuilder(url, userMap, client);
		response = invocationBuilder.method("DELETE", Entity.entity(data, MediaType.APPLICATION_FORM_URLENCODED));
		return response;
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
		response = invocationBuilder.post(Entity.entity(data, MediaType.APPLICATION_FORM_URLENCODED));
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
		response = invocationBuilder.post(Entity.entity(data, data.getMediaType()));
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
		response = invocationBuilder.post(Entity.entity(data, MediaType.APPLICATION_OCTET_STREAM_TYPE));
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
		response = invocationBuilder.post(Entity.entity(data, MediaType.APPLICATION_JSON));
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
	public String get(String url, T1User userMap) {
		Response response;
		Client client = instantiateSimpleClient();
		Invocation.Builder invocationBuilder = configureInvocationBuilder(url, userMap, client);
		response = invocationBuilder.get();
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
	public Response getReportData(String url, T1User userMap) {
		Response response;
		Client client = instantiateSimpleClient();
		Invocation.Builder invocationBuilder = configureInvocationBuilder(url, userMap, client);
		response = invocationBuilder.get();
		return response;
	}

	private Client instantiateSimpleClient() {
		return ClientBuilder.newClient(new ClientConfig());
	}

	private Client instantiateSimpleClientSupressed() {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.property(ClientProperties.SUPPRESS_HTTP_COMPLIANCE_VALIDATION, true);
		return ClientBuilder.newClient(clientConfig);
	}

	private Client instantiateMultipartClient() {
		return ClientBuilder.newClient(new ClientConfig()).register(MultiPartFeature.class);
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
