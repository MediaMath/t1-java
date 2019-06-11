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

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.javers.core.metamodel.annotation.DiffIgnore;

import javax.ws.rs.core.Form;

public class TargetDimension implements T1Entity {

  private static final String entityName = "TargetDimension";

  private String type;
  private String exclude;
  private String include;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getExclude() {
    return exclude;
  }

  public void setExclude(String exclude) {
    this.exclude = exclude;
  }

  public String getInclude() {
    return include;
  }

  public void setInclude(String include) {
    this.include = include;
  }

  @Override
  public String getEntityname() {
    return entityName;
  }
  
  @Override
  @DiffIgnore
  @JsonIgnore
  public Form getForm() {
    return null;
  }

  @Override
  @DiffIgnore
  @JsonIgnore
  public String getUri() {
    return null;
  }

}
