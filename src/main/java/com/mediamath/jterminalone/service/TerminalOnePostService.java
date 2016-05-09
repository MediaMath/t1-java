package com.mediamath.jterminalone.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;
import com.mediamath.jterminalone.Connection;
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
import com.mediamath.jterminalone.utils.Constants;
import com.mediamath.jterminalone.utils.T1JsonToObjParser;

public class TerminalOnePostService {
	
	private static JT1Service jt1Service = new JT1Service();
	
	private Connection connection = null; 
	
	private HashMap<String, HashMap<String, String>> user = new HashMap<String, HashMap<String,String>>();
	
	private static final String YYYY_MM_DD_T_HH_MM_SS = "yyyy-MM-dd'T'HH:mm:ss";
	
	public TerminalOnePostService() {
		// TODO Auto-generated constructor stub
	}
	
	public TerminalOnePostService(Connection pConnection, HashMap<String, HashMap<String, String>> pUser) {
		this.connection = pConnection;
		this.user = pUser;
	}
	
	
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


}
