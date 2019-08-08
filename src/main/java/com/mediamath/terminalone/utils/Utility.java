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

package com.mediamath.terminalone.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.MultivaluedMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mediamath.terminalone.service.T1Service;

public class Utility {

	private static final String UNABLE_TO_LOAD_THE_CONFIGURATIONS = "Unable to load the configurations";

	private static final Logger logger = LoggerFactory.getLogger(Utility.class);

	private static final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule()).registerModule(new Jdk8Module());

	private static Properties vConfigProp = new Properties();

	private static Properties vEntityReadOnlyFields = new Properties();

	private static Properties vServicesPathForEntity = new Properties();

	public static Properties getConfigProperties() {
		return vConfigProp;
	}

	/**
	 * gets String "on" or "off" based on the boolean value supplied.
	 * 
	 * @param bool
	 *            requires a boolean value.
	 * @return String either "on" or "off"
	 */
	public static String getOnOrOff(boolean bool) {
		String response = "off";
		if (bool) {
			response = "on";
		}
		return response;
	}

	/**
	 * gets String value of "0" or "1" based on the boolean value supplied.
	 * 
	 * @param bool
	 *            requires a boolean value.
	 * @return String object.
	 */
	public static String getOneOrZero(boolean bool) {
		String response = "0";
		if (bool) {
			response = "1";
		}
		return response;
	}

	public static String objectToString(Object o) {
		try {
			return objectMapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * this utility is used to load the config by reading the property files.
	 * 
	 * @return Properties object.
	 */
	public static Properties loadConfigProperty() {
		if (vConfigProp.isEmpty()) {
			InputStream input = null;
			try {
				String filename = "config.properties";
				input = Utility.class.getClassLoader().getResourceAsStream(filename);
				if (input == null) {
					logger.info(UNABLE_TO_LOAD_THE_CONFIGURATIONS);
					return null;
				}
				vConfigProp.load(input);
			} catch (IOException ioException) {
				logStackTrace(ioException);
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException ioException) {
						logStackTrace(ioException);
					}
				}
			}
		}

		return vConfigProp;
	}

	/**
	 * This utility is used to load the readOnlyFields by reading the property
	 * files.
	 * 
	 * @return Property Object.
	 */
	public static Properties loadEntityReadOnlyFields() {
		if (vEntityReadOnlyFields.isEmpty()) {
			InputStream input = null;
			try {
				String filename = "EntityReadOnlyFields.properties";
				input = Utility.class.getClassLoader().getResourceAsStream(filename);
				if (input == null) {
					logger.info(UNABLE_TO_LOAD_THE_CONFIGURATIONS);
					return null;
				}
				vEntityReadOnlyFields.load(input);
			} catch (IOException ioException) {
				logStackTrace(ioException);
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException ioException) {
						logStackTrace(ioException);
					}
				}
			}
		}

		return vEntityReadOnlyFields;
	}

	/**
	 * This utility is used to load the MicroServices URLs for specific entities
	 * by reading the property files.
	 * 
	 * @return Property Object.
	 */
	public static Properties loadServicesPath() {
		if (vServicesPathForEntity.isEmpty()) {
			InputStream input = null;
			try {
				String filename = "services.properties";
				input = Utility.class.getClassLoader().getResourceAsStream(filename);
				if (input == null) {
					logger.info(UNABLE_TO_LOAD_THE_CONFIGURATIONS);
					return null;
				}
				vServicesPathForEntity.load(input);
			} catch (IOException ioException) {
				logStackTrace(ioException);
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException ioException) {
						logStackTrace(ioException);
					}
				}
			}
		}

		return vServicesPathForEntity;
	}

	/**
	 * Utility function to split the string and return an array list.
	 * 
	 * @param propStr
	 *            comma seperated string.
	 * @return List of string.
	 */
	public static List<String> getList(String propStr) {
		String[] readOnlyFields = propStr.split(",");
		return Arrays.asList(readOnlyFields);
	}

	/**
	 * Returns a filtered Form object.
	 * 
	 * @param entity
	 *            requires a Form object for specific entity
	 * @param entityName
	 *            specify the entity name for fetching read only fields.
	 * @return Form object.
	 */
	public static Form getFilteredForm(Form entity, String entityName) {

		if (entityName == null || entityName.isEmpty()) {
			return null;
		}

		MultivaluedMap<String, String> multiValMap = entity.asMap();
		String readOnlyFields = T1Service.getEntityReadOnlyFields().getProperty(entityName);
		if(readOnlyFields!=null){
			List<String> readOnlyFieldList = Utility.getList(readOnlyFields);
			if(readOnlyFieldList!=null){
				for (String str : readOnlyFieldList) {
					if (multiValMap.containsKey(str)) {
						multiValMap.remove(str);
					}
				}
			}
		}
		
		return new Form(multiValMap);
	}

	/**
	 * Returns a service path for specific entity.
	 * 
	 * @param collection
	 *            specify the entity name for fetching service paths.
	 * @return Form object.
	 */
	public static String getServicePath(String collection) {

		if (collection == null || collection.isEmpty()) {
			return null;
		}

		return (T1Service.getEntityServicesPaths().getProperty(collection) != null)
				? T1Service.getEntityServicesPaths().getProperty(collection)
				: T1Service.getEntityServicesPaths().getProperty("others");
	}

	/**
	 * this utility takes in the Exception object and logs the entire stack
	 * tracer to the logger.
	 * 
	 * @param exception
	 *            Exception object.
	 */
	public static void logStackTrace(Exception exception) {
		StringBuilder strBuilder = new StringBuilder(exception.getMessage());
		StackTraceElement[] stactTraceElements = exception.getStackTrace();
		for (StackTraceElement ste : stactTraceElements) {
			strBuilder.append(ste.toString());
		}

		if (!strBuilder.toString().isEmpty()) {
			String logstr = strBuilder.toString();
			logger.error(logstr);
		}
	}

	public boolean isArrayOfType(Object[] array, Class<?> type) {
		return array.length > 0 && array.getClass().getComponentType().isAssignableFrom(type);
	}

}
