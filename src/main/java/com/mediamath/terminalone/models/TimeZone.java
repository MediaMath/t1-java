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
import java.util.Date;

public class TimeZone implements T1Entity {

  private static final String entityName = "TimeZone";

  private int id;
  private int version;
  private Date created_on;
  private Date updated_on;
  private String name;
  private String display_name;
  private String description;
  private String zone_name;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public Date getCreatedOn() {
    return created_on;
  }

  public void setCreatedOn(Date created_on) {
    this.created_on = created_on;
  }

  public Date getUpdatedOn() {
    return updated_on;
  }

  public void setUpdatedOn(Date updated_on) {
    this.updated_on = updated_on;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDisplay_name() {
    return display_name;
  }

  public void setDisplay_name(String display_name) {
    this.display_name = display_name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getZoneName() {
    return zone_name;
  }

  public void setZoneName(String zoneName) {
    this.zone_name = zoneName;
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
