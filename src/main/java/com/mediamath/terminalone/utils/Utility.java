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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utility {

  private static final Logger logger = LoggerFactory.getLogger(Utility.class);

  private static Properties vConfigProp = new Properties();
  
  private static Properties vEntityReadOnlyFields = new Properties();

  /**
   * gets String "on" or "off" based on the boolean value supplied.
   * 
   * @param bool
   *          requires a boolean value.
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
   *          requires a boolean value.
   * @return String object.
   */
  public static String getOneOrZero(boolean bool) {
    String response = "0";
    if (bool) {
      response = "1";
    }
    return response;
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
          logger.info("Unable to load the configurations");
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
  
  public static Properties loadEntityReadOnlyFields() {
    if (vEntityReadOnlyFields.isEmpty()) {
      InputStream input = null;
      try {
        String filename = "EntityReadOnlyFields.properties";
        input = Utility.class.getClassLoader().getResourceAsStream(filename);
        if (input == null) {
          logger.info("Unable to load the configurations");
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
  
  
  public static List<String> getList(String propStr) {
    String[] readOnlyFields = propStr.split(",");
    List<String> s = Arrays.asList(readOnlyFields);
    return s;
  }

  public static Properties getConfigProperties() {
    return vConfigProp;
  }

  /**
   * this utility takes in the Exception object and logs the entire stact tracer to the logger.
   * 
   * @param exception
   *          Exception object.
   */
  public static void logStackTrace(Exception exception) {
    StringBuilder strBuffer = new StringBuilder(exception.getMessage());
    StackTraceElement[] stactTraceElements = exception.getStackTrace();
    for (StackTraceElement ste : stactTraceElements) {
      strBuffer.append(ste.toString());
    }
    logger.error(strBuffer.toString());
  }

  public boolean isArrayOfType(Object[] array,Class<?> type) {
    return array.length > 0
            && array.getClass().getComponentType().isAssignableFrom(type);
  }

}
