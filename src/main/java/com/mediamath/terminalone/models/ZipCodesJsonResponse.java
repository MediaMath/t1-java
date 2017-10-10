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

package com.mediamath.terminalone.models;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class ZipCodesJsonResponse {


  @SerializedName("data")
  ArrayList<T1Data> data;

  @SerializedName("errors")
  ArrayList<T1Error> errors;

  @SerializedName("meta")
  T1Meta meta;

  @SerializedName("entity_type")
  String entity_type;
  
  public ZipCodesJsonResponse() {
    //default.
  }

  public ZipCodesJsonResponse(ArrayList<T1Data> datavalue) {
    this.data = datavalue;
  }

  public ArrayList<T1Error> getErrors() {
    return errors;
  }

  public void setErrors(ArrayList<T1Error> errors) {
    this.errors = errors;
  }

  public ArrayList<T1Data> getData() {
    return data;
  }

  public void setData(ArrayList<T1Data> data) {
    this.data = data;
  }

  public T1Meta getMeta() {
    return meta;
  }

  public void setMeta(T1Meta meta) {
    this.meta = meta;
  }

  public String getEntity_type() {
    return entity_type;
  }

  public void setEntity_type(String entity_type) {
    this.entity_type = entity_type;
  }

}
