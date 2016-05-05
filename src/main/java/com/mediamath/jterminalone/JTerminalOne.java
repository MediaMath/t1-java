package com.mediamath.jterminalone;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.Form;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;
import com.mediamath.jterminalone.Exceptions.ClientException;
import com.mediamath.jterminalone.Exceptions.ParseException;
import com.mediamath.jterminalone.models.Advertiser;
import com.mediamath.jterminalone.models.Agency;
import com.mediamath.jterminalone.models.AtomicCreative;
import com.mediamath.jterminalone.models.Campaign;
import com.mediamath.jterminalone.models.Concept;
import com.mediamath.jterminalone.models.Data;
import com.mediamath.jterminalone.models.FieldError;
import com.mediamath.jterminalone.models.JsonPostResponse;
import com.mediamath.jterminalone.models.JsonResponse;
import com.mediamath.jterminalone.models.Organization;
import com.mediamath.jterminalone.models.Pixel;
import com.mediamath.jterminalone.models.Status;
import com.mediamath.jterminalone.models.Strategy;
import com.mediamath.jterminalone.models.StrategyConcept;
import com.mediamath.jterminalone.models.StrategySupplySource;
import com.mediamath.jterminalone.models.T1Entity;
import com.mediamath.jterminalone.models.T1Error;
import com.mediamath.jterminalone.models.T1Property;
import com.mediamath.jterminalone.models.helper.AdvertiserHelper;
import com.mediamath.jterminalone.models.helper.AgencyHelper;
import com.mediamath.jterminalone.models.helper.AtomicCreativeHelper;
import com.mediamath.jterminalone.models.helper.CampaignHelper;
import com.mediamath.jterminalone.models.helper.ConceptHelper;
import com.mediamath.jterminalone.models.helper.OrganizationHelper;
import com.mediamath.jterminalone.models.helper.PixelHelper;
import com.mediamath.jterminalone.models.helper.StrategyConceptHelper;
import com.mediamath.jterminalone.models.helper.StrategyHelper;
import com.mediamath.jterminalone.models.helper.StrategySupplySourceHelper;
import com.mediamath.jterminalone.service.JT1Service;
import com.mediamath.jterminalone.service.JTerminalOneService;
import com.mediamath.jterminalone.utils.Constants;
import com.mediamath.jterminalone.utils.Filters;
import com.mediamath.jterminalone.utils.T1JsonToObjParser;

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
	 * service object.
	 */
	private JT1Service jt1Service =null;
	
	
	private JTerminalOneService jTerminalOneService=null;
	/*
	 * maintains user session
	 */
	private HashMap<String, HashMap<String, String>> user = new HashMap<String, HashMap<String, String>>();

	/**
	 * date format
	 */
	private static final String YYYY_MM_DD_T_HH_MM_SS = "yyyy-MM-dd'T'HH:mm:ss";
	
	/*
	 * is authenticated? 
	 */
	private boolean authenticated = false;

	/**
	 * Default Constructor
	 */
	public JTerminalOne() {
		logger.info("Loading Environment - setting up connection.");
		connection = new Connection();
		jt1Service = new JT1Service();
		jTerminalOneService = new JTerminalOneService();
	}

	/**
	 * the other constructor, tries to connect with the credentials provided.
	 * @throws ClientException 
	 * 
	 */
	public JTerminalOne(String username, String password, String api_key) throws ClientException {
		this();
		
		if(api_key == null || api_key.isEmpty()) {
			logger.error("Environment does not exist");
			throw new ClientException("Please Provide Valid Enviornment");
		}
		
		if(username.isEmpty() || password.isEmpty()) {
			logger.error("Please provide valid credentials.");
			throw new ClientException("Please Provide Valid Username and Password.");
		}

		logger.info("Loading Environment - Authenticating.");
		Form form = jt1Service.getLoginFormData(username, password, api_key);

		logger.info("Loading Environment - Authenticating.");
		String url = jt1Service.constructURL(new StringBuffer("login"));
		String response = connection.post(url, form, null);
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
	
	
	/**
	 * saves the given Agency.
	 * 
	 * @param entity
	 * @throws ClientException 
	 * @throws ParseException 
	 */
	public Agency save(Agency entity) throws ClientException, ParseException {
		Agency agency = null;
		
		if(entity != null) {
			JsonResponse<? extends T1Entity>  finalJsonResponse = null;
			
			// detect
			String entityName = entity.getEntityname();
			// form a path
			StringBuffer uri = new StringBuffer(Constants.entityPaths.get(entityName));
			if (entity.getId() > 0) {
				uri.append("/");
				uri.append(entity.getId());
			}
			
			String path = jt1Service.constructURL(uri);

			//post
			//Form agencyForm = AgencyHelper.getForm(entity);
			//TODO: check.
			String response = this.connection.post(path, AgencyHelper.getForm(entity), this.user);
			
			// parse response
			T1JsonToObjParser parser = new T1JsonToObjParser();
			JsonPostResponse jsonPostResponse =  null;
			
			jsonPostResponse = jsonPostErrorResponseParser(response);
			
			if(jsonPostResponse == null) {
				jsonPostResponse = parser.parsePOSTResponseTOObj(response);
				Data data = new Data();
				for(T1Property p : jsonPostResponse.getEntity().getProp()) {
					data.getData().put(p.getName(), p.getValue());
				}
				data.getData().put("name", jsonPostResponse.getEntity().getName());
				data.getData().put("entity_type", jsonPostResponse.getEntity().getType());
				data.getData().put("id", jsonPostResponse.getEntity().getId());
				data.getData().put("version", String.valueOf(jsonPostResponse.getEntity().getVersion()));
				// parse data to json.
				Gson g = new Gson();
				String s = g.toJson(data);
				// update the existing object. or create new object.
				finalJsonResponse = parseResponse(s, jsonPostResponse.getEntity().getType());
				if(finalJsonResponse.getData() instanceof Agency) {
					agency = (Agency) finalJsonResponse.getData();
				}
			} else {
				throwExceptions(jsonPostResponse);
			}
			
		}
		return agency;
	}
	
	/**
	 * saves the given Advertiser.
	 * 
	 * @param entity
	 * @throws ClientException 
	 * @throws ParseException 
	 */
	public Advertiser save(Advertiser entity) throws ClientException, ParseException {
		Advertiser advertiser = null;
		
		if(entity != null) {
			JsonResponse<? extends T1Entity>  finalJsonResponse = null;
			
			// detect
			String entityName = entity.getEntityname();
			// form a path
			StringBuffer uri = new StringBuffer(Constants.entityPaths.get(entityName));
			if (entity.getId() > 0) {
				uri.append("/");
				uri.append(entity.getId());
			}
			
			String path = jt1Service.constructURL(uri);

			//post
			//Form agencyForm = AgencyHelper.getForm(entity);
			//TODO: check.
			String response = this.connection.post(path, AdvertiserHelper.getForm(entity), this.user);
			
			// parse response
			T1JsonToObjParser parser = new T1JsonToObjParser();
			JsonPostResponse jsonPostResponse =  null;
			
			jsonPostResponse = jsonPostErrorResponseParser(response);
			
			if(jsonPostResponse == null) {
				jsonPostResponse = parser.parsePOSTResponseTOObj(response);
				Data data = new Data();
				for(T1Property p : jsonPostResponse.getEntity().getProp()) {
					data.getData().put(p.getName(), p.getValue());
				}
				data.getData().put("name", jsonPostResponse.getEntity().getName());
				data.getData().put("entity_type", jsonPostResponse.getEntity().getType());
				data.getData().put("id", jsonPostResponse.getEntity().getId());
				data.getData().put("version", String.valueOf(jsonPostResponse.getEntity().getVersion()));
				// parse data to json.
				Gson g = new Gson();
				String s = g.toJson(data);
				// update the existing object. or create new object.
				finalJsonResponse = parseResponse(s, jsonPostResponse.getEntity().getType());
				if(finalJsonResponse.getData() instanceof Advertiser) {
					advertiser = (Advertiser) finalJsonResponse.getData();
				}
			} else {
				throwExceptions(jsonPostResponse);
			}
			
		}
		return advertiser;
	}
	
	
	/**
	 * saves the given Strategy.
	 * 
	 * @param entity
	 * @throws ClientException 
	 * @throws ParseException 
	 */
	public Strategy save(Strategy entity) throws ClientException, ParseException {
		Strategy strategy = null;
		
		if(entity != null) {
			JsonResponse<? extends T1Entity>  finalJsonResponse = null;
			
			// detect
			String entityName = entity.getEntityname();
			// form a path
			StringBuffer uri = new StringBuffer(Constants.entityPaths.get(entityName));
			if (entity.getId() > 0) {
				uri.append("/");
				uri.append(entity.getId());
			}
			
			if (entity.getId() > 0 && entity.getDomain_restrictions().size() > 0) {
				uri.append("/domain_restrictions");
			}
			
			String path = jt1Service.constructURL(uri);

			String response = this.connection.post(path, StrategyHelper.getForm(entity), this.user);
			
			// parse response
			T1JsonToObjParser parser = new T1JsonToObjParser();
			JsonPostResponse jsonPostResponse =  null;
			
			jsonPostResponse = jsonPostErrorResponseParser(response);
			
			if(jsonPostResponse == null) {
				jsonPostResponse = parser.parsePOSTResponseTOObj(response);
				Data data = new Data();
				if(jsonPostResponse.getEntity()!=null){
					for(T1Property p : jsonPostResponse.getEntity().getProp()) {
						data.getData().put(p.getName(), p.getValue());
					}
					data.getData().put("name", jsonPostResponse.getEntity().getName());
					data.getData().put("entity_type", jsonPostResponse.getEntity().getType());
					data.getData().put("id", jsonPostResponse.getEntity().getId());
					data.getData().put("version", String.valueOf(jsonPostResponse.getEntity().getVersion()));
					
					Gson g = new Gson();
					String s = g.toJson(data);

					// parse data to json.
					finalJsonResponse = parseResponse(s, (jsonPostResponse.getEntity() !=null) ? jsonPostResponse.getEntity().getType() : null);
					// update the existing object. or create new object.
					if(finalJsonResponse.getData() instanceof Strategy) {
						strategy = (Strategy) finalJsonResponse.getData();
					}
				}
				
				
			} else {
				throwExceptions(jsonPostResponse);
			}
			
		}
		return ((strategy==null) ? entity : strategy);
	}
	
	/**
	 * saves the given Strategy Concepts.
	 * 
	 * @param entity
	 * @throws ClientException 
	 * @throws ParseException 
	 */
	public StrategyConcept save(StrategyConcept entity) throws ClientException, ParseException {
		StrategyConcept strategyConcept = null;
		
		if(entity != null) {
			JsonResponse<? extends T1Entity>  finalJsonResponse = null;
			
			// detect
			String entityName = entity.getEntityname();
			// form a path
			StringBuffer uri = new StringBuffer(Constants.entityPaths.get(entityName));
			if (entity.getId() > 0) {
				uri.append("/");
				uri.append(entity.getId());
			}
			
			String path = jt1Service.constructURL(uri);

			//post
			//Form agencyForm = AgencyHelper.getForm(entity);
			//TODO: check.
			String response = this.connection.post(path, StrategyConceptHelper.getForm(entity), this.user);
			
			// parse response
			T1JsonToObjParser parser = new T1JsonToObjParser();
			JsonPostResponse jsonPostResponse =  null;
			
			jsonPostResponse = jsonPostErrorResponseParser(response);
			
			if(jsonPostResponse == null) {
				jsonPostResponse = parser.parsePOSTResponseTOObj(response);
				Data data = new Data();
				for(T1Property p : jsonPostResponse.getEntity().getProp()) {
					data.getData().put(p.getName(), p.getValue());
				}
				data.getData().put("name", jsonPostResponse.getEntity().getName());
				data.getData().put("entity_type", jsonPostResponse.getEntity().getType());
				data.getData().put("id", jsonPostResponse.getEntity().getId());
				data.getData().put("version", String.valueOf(jsonPostResponse.getEntity().getVersion()));
				// parse data to json.
				Gson g = new Gson();
				String s = g.toJson(data);
				// update the existing object. or create new object.
				finalJsonResponse = parseResponse(s, jsonPostResponse.getEntity().getType());
				if(finalJsonResponse.getData() instanceof StrategyConcept) {
					strategyConcept = (StrategyConcept) finalJsonResponse.getData();
				}
			} else {
				throwExceptions(jsonPostResponse);
			}
			
		}
		return strategyConcept;
	}
	
	/**
	 * saves the given Strategy Supply Sources.
	 * 
	 * @param entity
	 * @throws ClientException 
	 * @throws ParseException 
	 */
	public StrategySupplySource save(StrategySupplySource entity) throws ClientException, ParseException {
		StrategySupplySource strategySupplySource = null;
		
		if(entity != null) {
			JsonResponse<? extends T1Entity>  finalJsonResponse = null;
			
			// detect
			String entityName = entity.getEntityname();
			// form a path
			StringBuffer uri = new StringBuffer(Constants.entityPaths.get(entityName));
			if (entity.getId() > 0) {
				uri.append("/");
				uri.append(entity.getId());
			}
			
			String path = jt1Service.constructURL(uri);

			//post
			//Form agencyForm = AgencyHelper.getForm(entity);
			//TODO: check.
			String response = this.connection.post(path, StrategySupplySourceHelper.getForm(entity), this.user);
			
			// parse response
			T1JsonToObjParser parser = new T1JsonToObjParser();
			JsonPostResponse jsonPostResponse =  null;
			
			jsonPostResponse = jsonPostErrorResponseParser(response);
			
			if(jsonPostResponse == null) {
				jsonPostResponse = parser.parsePOSTResponseTOObj(response);
				Data data = new Data();
				for(T1Property p : jsonPostResponse.getEntity().getProp()) {
					data.getData().put(p.getName(), p.getValue());
				}
				data.getData().put("name", jsonPostResponse.getEntity().getName());
				data.getData().put("entity_type", jsonPostResponse.getEntity().getType());
				data.getData().put("id", jsonPostResponse.getEntity().getId());
				data.getData().put("version", String.valueOf(jsonPostResponse.getEntity().getVersion()));
				// parse data to json.
				Gson g = new Gson();
				String s = g.toJson(data);
				// update the existing object. or create new object.
				finalJsonResponse = parseResponse(s, jsonPostResponse.getEntity().getType());
				if(finalJsonResponse.getData() instanceof StrategySupplySource) {
					strategySupplySource = (StrategySupplySource) finalJsonResponse.getData();
				}
			} else {
				throwExceptions(jsonPostResponse);
			}
			
		}
		return strategySupplySource;
	}
	
	/**
	 * saves the given Organization.
	 * 
	 * @param entity
	 * @throws ClientException 
	 * @throws ParseException 
	 */
	public Organization save(Organization entity) throws ClientException, ParseException {
		Organization org = null;
		
		if(entity != null) {
			JsonResponse<? extends T1Entity>  finalJsonResponse = null;
			
			// detect
			String entityName = entity.getEntityname();
			// form a path
			StringBuffer uri = new StringBuffer(Constants.entityPaths.get(entityName));
			if (entity.getId() > 0) {
				uri.append("/");
				uri.append(entity.getId());
			}
			
			String path = jt1Service.constructURL(uri);

			//post
			//Form agencyForm = AgencyHelper.getForm(entity);
			//TODO: check.
			String response = this.connection.post(path, OrganizationHelper.getForm(entity), this.user);
			
			// parse response
			T1JsonToObjParser parser = new T1JsonToObjParser();
			JsonPostResponse jsonPostResponse =  null;
			
			jsonPostResponse = jsonPostErrorResponseParser(response);
			
			if(jsonPostResponse == null) {
				jsonPostResponse = parser.parsePOSTResponseTOObj(response);
				Data data = new Data();
				for(T1Property p : jsonPostResponse.getEntity().getProp()) {
					data.getData().put(p.getName(), p.getValue());
				}
				data.getData().put("name", jsonPostResponse.getEntity().getName());
				data.getData().put("entity_type", jsonPostResponse.getEntity().getType());
				data.getData().put("id", jsonPostResponse.getEntity().getId());
				data.getData().put("version", String.valueOf(jsonPostResponse.getEntity().getVersion()));
				// parse data to json.
				Gson g = new Gson();
				String s = g.toJson(data);
				// update the existing object. or create new object.
				finalJsonResponse = parseResponse(s, jsonPostResponse.getEntity().getType());
				if(finalJsonResponse.getData() instanceof Organization) {
					org = (Organization) finalJsonResponse.getData();
				}
			} else {
				throwExceptions(jsonPostResponse);
			}
			
		}
		return org;
	}
	
	/**
	 * saves the given Pixel.
	 * 
	 * @param entity
	 * @throws ClientException 
	 * @throws ParseException 
	 */
	public Pixel save(Pixel entity) throws ClientException, ParseException {
		Pixel px = null;
		
		if(entity != null) {
			JsonResponse<? extends T1Entity>  finalJsonResponse = null;
			
			// detect
			String entityName = entity.getEntityname();
			// form a path
			StringBuffer uri = new StringBuffer(Constants.entityPaths.get(entityName));
			if (entity.getId() > 0) {
				uri.append("/");
				uri.append(entity.getId());
			}
			
			String path = jt1Service.constructURL(uri);

			//post
			//Form agencyForm = AgencyHelper.getForm(entity);
			//TODO: check.
			String response = this.connection.post(path, PixelHelper.getForm(entity), this.user);
			
			// parse response
			T1JsonToObjParser parser = new T1JsonToObjParser();
			JsonPostResponse jsonPostResponse =  null;
			
			jsonPostResponse = jsonPostErrorResponseParser(response);
			
			if(jsonPostResponse == null) {
				jsonPostResponse = parser.parsePOSTResponseTOObj(response);
				Data data = new Data();
				for(T1Property p : jsonPostResponse.getEntity().getProp()) {
					data.getData().put(p.getName(), p.getValue());
				}
				data.getData().put("name", jsonPostResponse.getEntity().getName());
				data.getData().put("entity_type", jsonPostResponse.getEntity().getType());
				data.getData().put("id", jsonPostResponse.getEntity().getId());
				data.getData().put("version", String.valueOf(jsonPostResponse.getEntity().getVersion()));
				// parse data to json.
				Gson g = new Gson();
				String s = g.toJson(data);
				// update the existing object. or create new object.
				finalJsonResponse = parseResponse(s, jsonPostResponse.getEntity().getType());
				if(finalJsonResponse.getData() instanceof Pixel) {
					px = (Pixel) finalJsonResponse.getData();
				}
			} else {
				throwExceptions(jsonPostResponse);
			}
			
		}
		return px;
	}
	
	public Campaign save(Campaign entity) throws ParseException, ClientException {
		Campaign campaign = null;
		if(entity != null) {
			JsonResponse<? extends T1Entity>  finalJsonResponse = null;
			
			// detect
			String entityName = entity.getEntityname();
			// form a path
			StringBuffer uri = new StringBuffer(Constants.entityPaths.get(entityName));
			
			if (entity.getId() > 0 && entity.getMargins().size() > 0) {
				uri.append("/");
				uri.append(entity.getId());
				uri.append("/margins");
			}
			String path = jt1Service.constructURL(uri);
			//post
			String response = this.connection.post(path, CampaignHelper.getForm(entity), this.user);
			JsonPostResponse jsonPostResponse =  null;
			jsonPostResponse = jsonPostErrorResponseParser(response); 
			if (jsonPostResponse == null) {
				// parse response
				T1JsonToObjParser parser = new T1JsonToObjParser();
				jsonPostResponse = parser.parsePOSTResponseTOObj(response);

				Data data = new Data();
				if (jsonPostResponse.getEntity() != null) {

					for (T1Property p : jsonPostResponse.getEntity().getProp()) {
						data.getData().put(p.getName(), p.getValue());
					}

					data.getData().put("name", jsonPostResponse.getEntity().getName());
					data.getData().put("entity_type", jsonPostResponse.getEntity().getType());
					data.getData().put("id", jsonPostResponse.getEntity().getId());
					data.getData().put("version", String.valueOf(jsonPostResponse.getEntity().getVersion()));

					// parse data to json.
					Gson g = new Gson();
					String s = g.toJson(data);

					// update the existing object. or create new object.
					finalJsonResponse = parseResponse(s, jsonPostResponse.getEntity().getType());

					if (finalJsonResponse.getData() instanceof Campaign) {
						campaign = (Campaign) finalJsonResponse.getData();
					}
				}
			} else {
				throwExceptions(jsonPostResponse);
			}
		}
		return campaign == null ? entity : campaign;
	}
	
	/**
	 * saves concepts
	 * 
	 * @param entity
	 * @return
	 * @throws ParseException
	 * @throws ClientException
	 */
	public Concept save(Concept entity) throws ParseException, ClientException {
		Concept concept = null;
		
		if(entity != null) {
			JsonResponse<? extends T1Entity>  finalJsonResponse = null;
			
			// detect
			String entityName = entity.getEntityname();
			// form a path
			StringBuffer uri = new StringBuffer(Constants.entityPaths.get(entityName));
			
			String path = jt1Service.constructURL(uri);
			//post
			String response = this.connection.post(path, ConceptHelper.getForm(entity), this.user);
		
			JsonPostResponse jsonPostResponse =  null;
			jsonPostResponse = jsonPostErrorResponseParser(response); 
			
			if (jsonPostResponse == null) {
				// parse response
				T1JsonToObjParser parser = new T1JsonToObjParser();
				jsonPostResponse = parser.parsePOSTResponseTOObj(response);

				Data data = new Data();
				if (jsonPostResponse.getEntity() != null) {

					for (T1Property p : jsonPostResponse.getEntity().getProp()) {
						data.getData().put(p.getName(), p.getValue());
					}

					data.getData().put("name", jsonPostResponse.getEntity().getName());
					data.getData().put("entity_type", jsonPostResponse.getEntity().getType());
					data.getData().put("id", jsonPostResponse.getEntity().getId());
					data.getData().put("version", String.valueOf(jsonPostResponse.getEntity().getVersion()));

					// parse data to json.
					Gson g = new Gson();
					String s = g.toJson(data);

					// update the existing object. or create new object.
					finalJsonResponse = parseResponse(s, jsonPostResponse.getEntity().getType());

					if (finalJsonResponse.getData() instanceof Concept) {
						concept = (Concept) finalJsonResponse.getData();
					}
				}
			} else {
				throwExceptions(jsonPostResponse);
			}
		}
		return concept == null ? entity : concept;
	}
	
	/**
	 * saves Atomic Creative
	 * 
	 * @param entity
	 * @return
	 * @throws ParseException
	 * @throws ClientException
	 */
	public AtomicCreative save(AtomicCreative entity) throws ParseException, ClientException {
		AtomicCreative atomicCreative = null;
		
		if(entity != null) {
			JsonResponse<? extends T1Entity>  finalJsonResponse = null;
			
			// detect
			String entityName = entity.getEntityname();
			// form a path
			StringBuffer uri = new StringBuffer(Constants.entityPaths.get(entityName));
			
			String path = jt1Service.constructURL(uri);
			//post
			String response = this.connection.post(path, AtomicCreativeHelper.getForm(entity), this.user);
		
			JsonPostResponse jsonPostResponse =  null;
			jsonPostResponse = jsonPostErrorResponseParser(response); 
			
			if (jsonPostResponse == null) {
				// parse response
				T1JsonToObjParser parser = new T1JsonToObjParser();
				jsonPostResponse = parser.parsePOSTResponseTOObj(response);

				Data data = new Data();
				if (jsonPostResponse.getEntity() != null) {

					for (T1Property p : jsonPostResponse.getEntity().getProp()) {
						data.getData().put(p.getName(), p.getValue());
					}

					data.getData().put("name", jsonPostResponse.getEntity().getName());
					data.getData().put("entity_type", jsonPostResponse.getEntity().getType());
					data.getData().put("id", jsonPostResponse.getEntity().getId());
					data.getData().put("version", String.valueOf(jsonPostResponse.getEntity().getVersion()));

					// parse data to json.
					Gson g = new Gson();
					String s = g.toJson(data);

					// update the existing object. or create new object.
					finalJsonResponse = parseResponse(s, jsonPostResponse.getEntity().getType());

					if (finalJsonResponse.getData() instanceof AtomicCreative) {
						atomicCreative = (AtomicCreative) finalJsonResponse.getData();
					}
				}
			} else {
				throwExceptions(jsonPostResponse);
			}
		}
		return atomicCreative == null ? entity : atomicCreative;
	}
	
	/**
	 * saves creative upload for 3pas bulk update
	 */
	public void saveCreativeUpload() {
		
	}

	/**
	 * @param jsonPostResponse
	 * @throws ClientException
	 */
	private void throwExceptions(JsonPostResponse jsonPostResponse) throws ClientException {
	
		StringBuffer strbuff = null;

		if (jsonPostResponse.getError() != null) {
			T1Error error = jsonPostResponse.getError();

			if (error.getContent() != null) {
				strbuff = new StringBuffer("Content: " + error.getContent());
			}

			if (error.getField() != null) {
				if (strbuff == null) {
					strbuff = new StringBuffer("Field: " + error.getField());
				} else {
					strbuff.append(", " + "Field: " + error.getField());
				}
			}

			if (error.getMessage() != null) {
				if (strbuff == null) {
					strbuff = new StringBuffer("Message: " + error.getMessage());
				} else {
					strbuff.append(", " + "Message: " + error.getMessage());
				}
			}

			if (error.getType() != null) {
				if (strbuff == null) {
					strbuff = new StringBuffer("Type: " + error.getType());
				} else {
					strbuff.append(", " + "Type: " + error.getType());
				}
			}
		}

		if (jsonPostResponse.getErrors() != null) {
			if (jsonPostResponse.getErrors() instanceof ArrayList) {
				@SuppressWarnings("unchecked")
				ArrayList<T1Error> al = (ArrayList<T1Error>) jsonPostResponse.getErrors();
				for (T1Error error : al) {
					if (error.getMessage() != null) {
						if (strbuff == null) {
							strbuff = new StringBuffer(error.getMessage());
						} else {
							strbuff.append(", " + error.getMessage());
						}
					}
					if (error.getFieldError() != null) {
						for (FieldError fe : error.getFieldError()) {
							if (strbuff == null) {
								strbuff = new StringBuffer("Name: " + fe.getName() + ", Code: " + fe.getCode()
										+ ", Error: " + fe.getError());
							} else {
								strbuff.append(", " + "Name: " + fe.getName() + ", Code: " + fe.getCode()
										+ ", Error: " + fe.getError());
							}
						}
					}
				}
			} else {

				T1Error error = (T1Error) jsonPostResponse.getErrors();

				if (error.getMessage() != null) {
					if (strbuff == null) {
						strbuff = new StringBuffer(error.getMessage());
					} else {
						strbuff.append(", " + error.getMessage());
					}
				}
				if (error.getFieldError() != null) {
					for (FieldError fe : error.getFieldError()) {
						if (strbuff == null) {
							strbuff = new StringBuffer("Name: " + fe.getName() + ", Code: " + fe.getCode()
									+ ", Error: " + fe.getError());
						} else {
							strbuff.append(", " + "Name: " + fe.getName() + ", Code: " + fe.getCode()
									+ ", Error: " + fe.getError());
						}
					}
				}
			}
		}
		// throw the error to client
		throw new ClientException(strbuff.toString());
	}

	/**
	 * @param responseStr
	 */
	private JsonPostResponse jsonPostErrorResponseParser(String responseStr) {
		JsonParser parser1 = new JsonParser();
		JsonObject obj = parser1.parse(responseStr).getAsJsonObject();
		
		JsonElement errorsElement = obj.get("errors");
		JsonElement errorElement = obj.get("error");
		JsonElement statusElement = obj.get("status");
		JsonElement calledOnElement = obj.get("called_on");
		
		JsonPostResponse response = null;
		
		if(errorsElement != null || errorElement != null ) {
			response = new JsonPostResponse();

			GsonBuilder builder = new GsonBuilder();
			builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES);
			builder.setDateFormat(YYYY_MM_DD_T_HH_MM_SS);
			
			Gson g = builder.create();

			if (errorsElement != null) {
				if (errorsElement.isJsonNull()) {

				} else if (errorsElement.isJsonObject()) {
					T1Error errors = g.fromJson(errorsElement, T1Error.class);
					response.setErrors(errors);

				} else if (errorsElement.isJsonArray()) {
					JsonArray array = errorsElement.getAsJsonArray();
					JsonArray newArray = new JsonArray();
					
					for(int i = 0; i < array.size(); i++) {
						if(!(array.get(i) instanceof JsonPrimitive)) {
							newArray.add(array.get(i));
							
						}
					}
					if(newArray.size() > 0) {
						errorsElement = newArray;
						Type t =  new TypeToken<ArrayList<T1Error>>(){}.getType();
						List<T1Error> errors = g.fromJson(errorsElement, t);
						response.setErrors(errors);
					}
				}
			}

			if (errorElement != null) {
				T1Error error = g.fromJson(errorElement, T1Error.class);
				response.setError(error);
			}

			if (statusElement != null) {
				Status status = g.fromJson(statusElement, Status.class);
				response.setStatus(status);
			}
			if (calledOnElement != null) {
				String calledOnStr = g.fromJson(calledOnElement, String.class);
				response.setCalled_on(calledOnStr);
			}
		}
		
		return response;
	}


	/**
	 * Get.
	 * 
	 * @param query
	 * @return
	 * @throws ClientException
	 * @throws ParseException
	 */
	public JsonResponse<? extends T1Entity> get(QueryCriteria query) throws ClientException, ParseException {
		
		StringBuffer path=new StringBuffer("");
		
		String childPath = "";
		
		StringBuffer includePath = new StringBuffer("");
		
		//param collection String example "advertisers"
		if(!query.collection.equals(null)){
			path.append(query.collection);
		} else {
			throw new IllegalArgumentException("please specify: collection");
		}
		
		//param entity Int example ID 12121
		if(query.entity > 0){
			path.append("/"+String.valueOf(query.entity));
		}
		
		//param child String example: acl, permissions
		if(query.child!=null){
			childPath = jTerminalOneService.constructChildPath(query.child);
			if(!path.toString().equalsIgnoreCase("") && !childPath.equalsIgnoreCase("")){
				path.append(childPath);
			}
		} //end of child
		
		//param limit, should be key=value pair. example organization : 123456
		if(query.limit.size()>0){
			path.append("/limit/");
			for(String s : query.limit.keySet()){
				if(!path.toString().equalsIgnoreCase("") && path.indexOf("?")!=-1){
					//TODO raise error
				}
				if(!path.toString().equalsIgnoreCase("")){
					path.append(s+"="+String.valueOf(query.limit.get(s)));
				}
			}
		}
		
		//param include
		if(query.includeConditionList != null && !query.includeConditionList.isEmpty()) {
			includePath = jTerminalOneService.constructIncludePath(query.includeConditionList);
			
			if(!path.toString().equalsIgnoreCase("") && !includePath.toString().equalsIgnoreCase("")) {
				path.append(includePath.toString());
			}
		}//end of include
		
		//param sortby example: sortby=id
		if(query.sortBy!=null){
			if(!path.toString().equalsIgnoreCase("") && !includePath.toString().equalsIgnoreCase("") && path.indexOf("?")!=-1){
				path.append("&sort_by="+query.sortBy);
			}
			else{
				path.append("?sort_by="+query.sortBy);
			}
		}//end sortby
		
		//param pageLimit should not be > 100 example: page_limit=30 
		//and param pageOffset, should be > 0 example: page_offset=10 
		if(query.pageLimit > 100){
			throw new ClientException("Page_Limit parameter should not exceed 100");
		}
		else{
			String pagePath = "";
			pagePath = jTerminalOneService.constructPaginationPath(query.pageLimit, query.pageOffset);
			if(!path.toString().equalsIgnoreCase("") && path.indexOf("?")!=-1){
				path.append("&"+pagePath);
			}
			else{
				path.append("?"+pagePath);
			}
		}//end pageLimit
		
		//param QUERY example 
		if(query.query!=null){
			if(!path.toString().equalsIgnoreCase("") && path.indexOf("?")!=-1){
				path.append("&q="+query.query);
			}
			else{
				path.append("?q="+query.query);
			}
		}
		
		
		// get the data from t1 servers.
		String finalPath = jt1Service.constructURL(path);
		String response = this.connection.get(finalPath, this.getUser());
		JsonResponse<? extends T1Entity> jsonResponse;
		// parse the data to entities.
		try{
			jsonResponse = parseResponse(query, response);
			jsonResponse = checkResponseEntities(jsonResponse);
		
		} catch (ParseException e) {
			
			throw new ClientException("Unable to parse the response");
		
		}
		
		// filter and validate data
		
		
		
		return jsonResponse;
	}
	
	private JsonResponse<? extends T1Entity> checkResponseEntities(JsonResponse<? extends T1Entity> jsonResponse) throws ClientException {
		
		if(jsonResponse != null) {
			StringBuffer strbuff = null;
			
			if(jsonResponse.getErrors() != null) {
				
				for(T1Error error: jsonResponse.getErrors()) {
					if(error.getMessage() != null) {
						if(strbuff == null){ 
							strbuff = new StringBuffer(error.getMessage());
						} else {
							strbuff.append(", " + error.getMessage());
						}
					}
				}
				// throw the error to client
				throw new ClientException(strbuff.toString());
			}
		}
		// else return the object
		return jsonResponse;
	}

/**
 * parses the response to objects.
 * 
 * @param query
 * @param response
 * @return
 * @throws ParseException
 */
	//TODO
private JsonResponse<? extends T1Entity> parseResponse(String response, String entityType) throws ParseException {
	T1JsonToObjParser parser = new T1JsonToObjParser();
	int result = parser.getJsonElementType(response);
	Type JsonResponseType = null;
	JsonResponse<? extends T1Entity> jsonresponse = null;
	
	if(entityType != null) {
		
		if (result != 0) {
			if (result == 1) {
				JsonResponseType = Constants.getEntityType.get(entityType);
			} else if (result == 2) {
				JsonResponseType = Constants.getListoFEntityType.get(entityType);
			}

			jsonresponse = parser.parseJsonToObj(response, JsonResponseType);
			
		}
	}
	return jsonresponse;
}

/**
 * parses the response to objects.
 * 
 * @param query
 * @param response
 * @return
 * @throws ParseException
 */
private JsonResponse<? extends T1Entity> parseResponse(QueryCriteria query, String response) throws ParseException {
	T1JsonToObjParser parser = new T1JsonToObjParser();
	int result = parser.getJsonElementType(response);
	Type JsonResponseType = null;
	JsonResponse<? extends T1Entity> jsonresponse = null;
	
	if(query.collection != null) {
		
		if (result != 0) {
			if (result == 1) {
				JsonResponseType = Constants.getEntityType.get(query.collection);
			} else if (result == 2) {
				JsonResponseType = Constants.getListoFEntityType.get(query.collection);
			}

			jsonresponse = parser.parseJsonToObj(response, JsonResponseType);
			
		}
	}
	return jsonresponse;
}

	
	
	/** Find method alternative to query of get
	 * 
	 * @param query
	 * @return
	 * @throws ParseException 
	 * @throws ClientException 
	 */
	public JsonResponse<? extends T1Entity> find(QueryCriteria query) throws ClientException, ParseException  {
		
		StringBuffer qParamVal = new StringBuffer();
		
		if(query.queryOperator.equalsIgnoreCase(Filters.IN)){
			if(query.queryParams.getListValue()==null || (query.queryParams.getListValue()!=null && query.queryParams.getListValue().size() <1)){
				//TODO raise TypeError
			}else{
				qParamVal.append("(");
				if(query.queryParams.getListValue().get(0) instanceof String || query.queryParams.getListValue().get(0) instanceof Number){
					String prefix = "";
					for(Object obj : query.queryParams.getListValue()){
						qParamVal.append(prefix);
						qParamVal.append(String.valueOf(obj));
						prefix = ",";
					}
				}else{
					//TODO raise typeError
				}
				
				qParamVal.append(")");
			}
		}else{
			qParamVal.append(query.queryParamName);
			qParamVal.append(query.queryOperator);
			
			if(query.queryParams.getStrValue()!=null){
				qParamVal.append(query.queryParams.getStrValue());
			}
			else if(query.queryParams.getNumberValue() != null){
				qParamVal.append(query.queryParams.getNumberValue());
			}
			else if(query.queryParams.getBoolValue()==true){
				qParamVal.append(1);
			}
			else if(query.queryParams.getBoolValue()==false){
				qParamVal.append(0);
			}
	
		}
		
		query.query =  qParamVal.toString();
		
	
		
		return this.get(query);
		
	}


	/**
	 * basic authentication method.
	 * 
	 * @return boolean isauthenticated.
	 */
	public boolean authenticate(String username, String password, String api_key) throws ClientException {

		// TODO validate
		logger.info("Authenticating.");
		
		Form form = jt1Service.getLoginFormData(username, password, api_key);
		String url = jt1Service.constructURL(new StringBuffer("login"));
		String response = null;
		
		response = connection.post(url, form, null);
		
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
	private HashMap<String, HashMap<String, String>> getUser() {
		return user;
	}

	private void setUser(HashMap<String, HashMap<String, String>> user) {
		this.user = user;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}
	
}
