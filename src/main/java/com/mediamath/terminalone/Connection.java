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

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
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
	public String post(String url, Form data, T1Response userMap) throws ClientException {
		
		if (data == null) {
			throw new ClientException("No Post Data");
		}

		Response response = null;
		Client client = null;
		HostnameVerifier allHostsValid = new InsecureHostnameVerifier();

		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			if (sc != null) {
				TrustManager[] trustAllCerts = { new InsecureTrustManager() };
				sc.init(null, trustAllCerts, new java.security.SecureRandom());
			} else if (sc == null) {
				// java 8 specific routine
				sc = SSLContext.getDefault();
			}

			client = ClientBuilder.newBuilder().sslContext(sc).hostnameVerifier(allHostsValid).build();

			logger.info("Target URL: " + url);

			WebTarget webTarget = client.target(url);
			Invocation.Builder invocationBuilder = webTarget.request(); // (MediaType.APPLICATION_JSON);
			invocationBuilder.header("User-Agent", userAgent);
			invocationBuilder.header("Accept", "application/vnd.mediamath.v1+json");

			userSessionCheck(userMap, invocationBuilder);

			response = invocationBuilder.post(Entity.entity(data, MediaType.APPLICATION_FORM_URLENCODED));
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response.readEntity(String.class);
	}
	
	public Response loginPost(String url, Form data, T1Response userMap) throws ClientException {

		if (data == null) {
			throw new ClientException("No Post Data");
		}

		Response response = null;
		Client client = null;
		HostnameVerifier allHostsValid = new InsecureHostnameVerifier();

		try {
			
			SSLContext sc = SSLContext.getInstance("SSL");
			if (sc != null) {
				TrustManager[] trustAllCerts = { new InsecureTrustManager() };
				sc.init(null, trustAllCerts, new java.security.SecureRandom());
			} else if (sc == null) {
				// java 8 specific routine
				sc = SSLContext.getDefault();
			}

			client = ClientBuilder.newBuilder().sslContext(sc).hostnameVerifier(allHostsValid).build();

			logger.info("Target URL: " + url);

			WebTarget webTarget = client.target(url);
			Invocation.Builder invocationBuilder = webTarget.request(); // (MediaType.APPLICATION_JSON);
			invocationBuilder.header("User-Agent", userAgent);
			invocationBuilder.header("Accept", "application/vnd.mediamath.v1+json");

			userSessionCheck(userMap, invocationBuilder);

			response = invocationBuilder.post(Entity.entity(data, MediaType.APPLICATION_FORM_URLENCODED));

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
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
		if (data == null) {
			throw new ClientException("No Post Data");
		}

		Response response = null;
		Client client = null;
		HostnameVerifier allHostsValid = new InsecureHostnameVerifier();

		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			if (sc != null) {
				TrustManager[] trustAllCerts = { new InsecureTrustManager() };
				sc.init(null, trustAllCerts, new java.security.SecureRandom());
			} else if (sc == null) {
				// java 8 specific routine
				sc = SSLContext.getDefault();
			}

			client = ClientBuilder.newBuilder()
						.sslContext(sc)
						.hostnameVerifier(allHostsValid)
						.register(MultiPartFeature.class)
						.build();
			
			logger.info("Target URL: " + url);

			WebTarget webTarget = client.target(url);
			Invocation.Builder invocationBuilder = webTarget.request();
			invocationBuilder.header("User-Agent", userAgent);
			invocationBuilder.header("Accept", "application/vnd.mediamath.v1+json");

			userSessionCheck(userMap, invocationBuilder);

			response = invocationBuilder.post(Entity.entity(data, data.getMediaType()));

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response.readEntity(String.class);

	}
	
	/**
	 * Application / Json POST.
	 * 
	 * @param url
	 * @param data
	 * @param userMap
	 * @return
	 * @throws ClientException
	 */
	public String post(String url, String data, T1Response userMap) throws ClientException {
		if (data == null) {
			throw new ClientException("No Post Data");
		}

		Response response = null;
		Client client = null;
		HostnameVerifier allHostsValid = new InsecureHostnameVerifier();

		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			if (sc != null) {
				TrustManager[] trustAllCerts = { new InsecureTrustManager() };
				sc.init(null, trustAllCerts, new java.security.SecureRandom());
			} else if (sc == null) {
				// java 8 specific routine
				sc = SSLContext.getDefault();
			}

			client = ClientBuilder.newBuilder()
						.sslContext(sc)
						.hostnameVerifier(allHostsValid)
						.register(MultiPartFeature.class)
						.build();
			
			logger.info("Target URL: " + url);

			WebTarget webTarget = client.target(url);
			Invocation.Builder invocationBuilder = webTarget.request();
			invocationBuilder.header("User-Agent", userAgent);
			invocationBuilder.header("Accept", "application/vnd.mediamath.v1+json");

			userSessionCheck(userMap, invocationBuilder);

			response = invocationBuilder.post(Entity.entity(data, MediaType.APPLICATION_JSON));

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		Client client = null;
		HostnameVerifier allHostsValid = new InsecureHostnameVerifier();
		try {
			SSLContext sc = SSLContext.getInstance("SSL");

			if (sc != null) {
				TrustManager[] trustAllCerts = { new InsecureTrustManager() };
				sc.init(null, trustAllCerts, new java.security.SecureRandom());
			} else if (sc == null) {
				// java 8 specific routine
				sc = SSLContext.getDefault();
			}

			client = ClientBuilder.newBuilder().sslContext(sc).hostnameVerifier(allHostsValid).build();

			logger.info("Target URL: " + url);

			WebTarget webTarget = client.target(url);
			Invocation.Builder invocationBuilder = webTarget.request();
			invocationBuilder.header("User-Agent", userAgent);
			invocationBuilder.header("Accept", "application/vnd.mediamath.v1+json");

			userSessionCheck(userMap, invocationBuilder);

			response = invocationBuilder.get();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
		return response.readEntity(String.class);
	}
	
	public String secureGet(String url, T1Response userMap) {
		Response response = null;
		Client client = null;
		HostnameVerifier allHostsValid = new InsecureHostnameVerifier();
		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			
			if(sc != null) {
				TrustManager[] trustAllCerts = { new InsecureTrustManager() };
				sc.init(null, trustAllCerts, new java.security.SecureRandom());
			} else if(sc == null) {
				// java 8 specific routine
				sc = SSLContext.getDefault();
			}
			
			client = ClientBuilder.newBuilder().sslContext(sc).hostnameVerifier(allHostsValid).build();
			WebTarget webTarget = client.target(url);
			Invocation.Builder invocationBuilder = webTarget.request();
			invocationBuilder.header("User-Agent", userAgent);
			invocationBuilder.header("Accept","application/vnd.mediamath.v1+json");
			
			userSessionCheck(userMap, invocationBuilder);
			
			response = invocationBuilder.get();

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (KeyManagementException e) {
			e.printStackTrace();
		}
		
		return response.readEntity(String.class);
	}
	
	public String securePost(String url, FormDataMultiPart data, T1Response userMap) throws ClientException {
		
		if(data == null) {
			throw new ClientException("No Post Data");
		}
		
		Response response = null;
		Client client = null;
		HostnameVerifier allHostsValid = new InsecureHostnameVerifier();

		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			if (sc != null) {
				TrustManager[] trustAllCerts = { new InsecureTrustManager() };
				sc.init(null, trustAllCerts, new java.security.SecureRandom());
			} else if (sc == null) {
				// java 8 specific routine
				sc = SSLContext.getDefault();
			}

			client = ClientBuilder.newBuilder()
						.sslContext(sc)
						.hostnameVerifier(allHostsValid)
						.register(MultiPartFeature.class)
						.build();
			
			logger.info("Target URL: " + url);

			WebTarget webTarget = client.target(url);
			Invocation.Builder invocationBuilder = webTarget.request();
			invocationBuilder.header("User-Agent", userAgent);
			invocationBuilder.header("Accept","application/vnd.mediamath.v1+json");
			
			userSessionCheck(userMap, invocationBuilder);
			response = invocationBuilder.post(Entity.entity(data,  data.getMediaType()));

			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response.readEntity(String.class);

	}
	
	
	/**handles GET requests
	 * 
	 * @param uri
	 * @param userMap
	 * @return
	 */
	public Response getReportData(String url, T1Response userMap) {

		Response response = null;
		Client client = null;
		HostnameVerifier allHostsValid = new InsecureHostnameVerifier();
		try {
			SSLContext sc = SSLContext.getInstance("SSL");

			if (sc != null) {
				TrustManager[] trustAllCerts = { new InsecureTrustManager() };
				sc.init(null, trustAllCerts, new java.security.SecureRandom());
			} else if (sc == null) {
				// java 8 specific routine
				sc = SSLContext.getDefault();
			}

			client = ClientBuilder.newBuilder().sslContext(sc).hostnameVerifier(allHostsValid).build();
			logger.info("Target URL: " + url);

			WebTarget webTarget = client.target(url);
			Invocation.Builder invocationBuilder = webTarget.request();
			invocationBuilder.header("User-Agent", userAgent);
			invocationBuilder.header("Accept", "application/vnd.mediamath.v1+json");

			userSessionCheck(userMap, invocationBuilder);

			response = invocationBuilder.get();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}

	private void userSessionCheck(T1Response userMap, Invocation.Builder invocationBuilder) {
		if (userMap != null ) {
			if (userMap.getData() != null 
					&& userMap.getData().getSession() != null 
					&& userMap.getData().getSession().getSessionid() != null 
					&& !userMap.getData().getSession().getSessionid().isEmpty()) {
				
				invocationBuilder.cookie("adama_session", userMap.getData().getSession().getSessionid());
			}
		}
	}
	
	private String generateUserAgent(){
		String version = configprop.getProperty("version");
		return "t1-java/"+version+" java-client/"+System.getProperty("java.version");
	}

}
