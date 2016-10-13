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

import java.util.ArrayList;
import java.util.List;

public class QueryParamValues {

  private String strValue = null;

  private boolean boolValue;

  private Number numberValue = null;

  private List<Object> listValue = new ArrayList<Object>();

  public QueryParamValues(String strValue) {
    this.strValue = strValue;
  }

  public QueryParamValues(Number numVal) {
    this.numberValue = numVal;
  }

  public QueryParamValues(boolean boolVal) {
    this.boolValue = boolVal;
  }

  public QueryParamValues(List<Object> listVal) {
    this.listValue = listVal;
  }

  public String getStrValue() {
    return strValue;
  }

  public void setStrValue(String strValue) {
    this.strValue = strValue;
  }

  public boolean getBoolValue() {
    return boolValue;
  }

  public void setBoolValue(boolean boolValue) {
    this.boolValue = boolValue;
  }

  public Number getNumberValue() {
    return numberValue;
  }

  public void setNumberValue(Number numberValue) {
    this.numberValue = numberValue;
  }

  public List<Object> getListValue() {
    return listValue;
  }

  public void setListValue(List<Object> listValue) {
    this.listValue = listValue;
  }

}
