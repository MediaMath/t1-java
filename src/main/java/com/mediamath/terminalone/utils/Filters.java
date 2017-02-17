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
  
  private Filters() {
    // Auto-generated constructor stub
  }

  public static final String IN = "()";
  public static final String NULL = ":";
  public static final String NOT_NULL = ":!";
  // Equals operator is *different* between M&E (==) and Picard (=)
  public static final String EQUALS = "==";
  public static final String NOT_EQUALS = "!=";
  public static final String GREATER = "%3E";
  public static final String GREATER_OR_EQUAL = "%3E=";
  public static final String LESS = "%3C";
  public static final String LESS_OR_EQUAL = "%3C=";
  public static final String CASE_INS_STRING = "=:";

}
