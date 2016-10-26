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
   *          api end point url.
   * @param data
   *          requires a Form data object.
   * @param userMap
   *          requires a valid user login session.
   * @return Response object.
   * @throws ClientException
   *           throws a client exception.
   */
  public Response post(String url, Form data, T1User userMap) throws ClientException {

    if (data == null) {
      throw new ClientException("No Post Data");
    }

    Response response = null;
    Client client = ClientBuilder.newClient(new ClientConfig());

    logger.info("Target URL: " + url);

    WebTarget webTarget = client.target(url);
    Invocation.Builder invocationBuilder = webTarget.request(); // (MediaType.APPLICATION_JSON);
    invocationBuilder.header("User-Agent", userAgent);
    invocationBuilder.header("Accept", "application/vnd.mediamath.v1+json");

    userSessionCheck(userMap, invocationBuilder);

    response = invocationBuilder.post(Entity.entity(data, MediaType.APPLICATION_FORM_URLENCODED));

    // return response.readEntity(String.class);
    return response;
  }

  /**
   * POST multipart data.
   * 
   * @param url
   *          api endpoint url.
   * @param data
   *          FormDataMultiPart object.
   * @param userMap
   *          requires a valid logged in user session.
   * @return Response object.
   * @throws ClientException
   *           exception
   */
  public Response post(String url, FormDataMultiPart data, T1User userMap) throws ClientException {
    if (data == null) {
      throw new ClientException("No Post Data");
    }

    Response response = null;
    Client client = null;

    client = ClientBuilder.newClient(new ClientConfig()).register(MultiPartFeature.class);

    logger.info("Target URL: " + url);

    WebTarget webTarget = client.target(url);
    Invocation.Builder invocationBuilder = webTarget.request();
    invocationBuilder.header("User-Agent", userAgent);
    invocationBuilder.header("Accept", "application/vnd.mediamath.v1+json");

    userSessionCheck(userMap, invocationBuilder);

    response = invocationBuilder.post(Entity.entity(data, data.getMediaType()));

    // return response.readEntity(String.class);
    return response;

  }

  /**
   * Application / Json POST.
   * 
   * @param url
   *          requires api end point url.
   * @param data
   *          Json String to post.
   * @param userMap
   *          requires a valid login user information.
   * @return Response object.
   * @throws ClientException
   *           exception.
   */
  public Response post(String url, String data, T1User userMap) throws ClientException {
    if (data == null) {
      throw new ClientException("No Post Data");
    }

    Response response = null;
    Client client = null;
    client = ClientBuilder.newClient(new ClientConfig()).register(MultiPartFeature.class);

    logger.info("Target URL: " + url);

    WebTarget webTarget = client.target(url);
    Invocation.Builder invocationBuilder = webTarget.request();
    invocationBuilder.header("User-Agent", userAgent);
    invocationBuilder.header("Accept", "application/vnd.mediamath.v1+json");

    userSessionCheck(userMap, invocationBuilder);

    response = invocationBuilder.post(Entity.entity(data, MediaType.APPLICATION_JSON));

    // return response.readEntity(String.class);
    return response;
  }

  /**
   * handles GET requests
   * 
   * @param url
   *          api end point url.
   * @param userMap
   *          requires a valid user login session.
   * @return String object.
   */
  public String get(String url, T1User userMap) {
    Response response = null;
    Client client = null;
    client = ClientBuilder.newClient(new ClientConfig());

    logger.info("Target URL: " + url);

    WebTarget webTarget = client.target(url);
    Invocation.Builder invocationBuilder = webTarget.request();
    invocationBuilder.header("User-Agent", userAgent);
    invocationBuilder.header("Accept", "application/vnd.mediamath.v1+json");

    userSessionCheck(userMap, invocationBuilder);

    response = invocationBuilder.get();

    return response.readEntity(String.class);
  }

  /**
   * handles GET requests for Reports.
   * 
   * @param url
   *          requires a valid url api endpoint.
   * @param userMap
   *          requires a valid user login session.
   * @return Response object.
   */
  public Response getReportData(String url, T1User userMap) {

    Response response = null;
    Client client = null;

    client = ClientBuilder.newClient(new ClientConfig());
    logger.info("Target URL: " + url);

    WebTarget webTarget = client.target(url);
    Invocation.Builder invocationBuilder = webTarget.request();
    invocationBuilder.header("User-Agent", userAgent);
    invocationBuilder.header("Accept", "application/vnd.mediamath.v1+json");

    userSessionCheck(userMap, invocationBuilder);

    response = invocationBuilder.get();
    return response;
  }

  private void userSessionCheck(T1User userMap, Invocation.Builder invocationBuilder) {
    if (userMap != null && userMap.getToken() != null && !userMap.getToken().isEmpty()) {
      invocationBuilder.header("Authorization", "Bearer " + userMap.getToken());
    }
    if (userMap != null && userMap.getData() != null && userMap.getData().getSession() != null
        && userMap.getData().getSession().getSessionid() != null
        && !userMap.getData().getSession().getSessionid().isEmpty()) {
      invocationBuilder.cookie("adama_session", userMap.getData().getSession().getSessionid());
    }
  }

  private String generateUserAgent() {
    String version = configprop.getProperty("version");
    return "t1-java/" + version + " java-client/" + System.getProperty("java.version");
  }

}
