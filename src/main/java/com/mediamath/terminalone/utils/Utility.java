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
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mediamath.terminalone.TerminalOne;

public class Utility {
	
	private static final Logger logger = LoggerFactory.getLogger(Utility.class);

	private static Properties vConfigProp = new Properties();
	
	public static String getOnOrOff(boolean bool) {
		String response = "off";
		if(bool) {
			response = "on";
		}
		return response;
	}
	
	public static String getOneOrZero(boolean bool) {
		String response = "0";
		if(bool) {
			response = "1";
		}
		return response;
	}

	public static Properties loadConfigProperty() {
		if (vConfigProp.isEmpty()) {
			InputStream input = null;
			try {
				String filename = "config.properties";
				input = Utility.class.getClassLoader().getResourceAsStream(filename);
				if (input == null) {
					logger.info("Unable to load the configurations");
					// TODO throw exception
					return null;
				}

				vConfigProp.load(input);

			} catch (IOException e) {
				// TODO handle exceptions
				e.printStackTrace();
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						
						// TODO handle exception
						e.printStackTrace();
					}
				}
			}
		}

		return vConfigProp;
	}
	
	public static Properties getConfigProperties() {
		return vConfigProp; 
	}
	
	
	public static void logStackTrace(Exception pEx) {
		StringBuilder vStrBuffer = new StringBuilder(pEx.getMessage());
		StackTraceElement[] vStactTraceElements = pEx.getStackTrace();
		for (StackTraceElement vSte : vStactTraceElements) {
			vStrBuffer.append(vSte.toString());
		}
		logger.error(vStrBuffer.toString());
	}

}
