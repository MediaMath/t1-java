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

//Filters for use with JT1.find
public final class Filters {

  public static String IN = "()";
  public static String NULL = ":";
  public static String NOT_NULL = ":!";
  // Equals operator is *different* between M&E (==) and Picard (=)
  public static String EQUALS = "==";
  public static String NOT_EQUALS = "!=";
  public static String GREATER = "%3E";
  public static String GREATER_OR_EQUAL = "%3E=";
  public static String LESS = "%3C";
  public static String LESS_OR_EQUAL = "%3C=";
  public static String CASE_INS_STRING = "=:";

}
