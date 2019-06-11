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

import java.util.Date;

import javax.ws.rs.core.Form;

public class Creative implements T1Entity {

  private static final String entityName = "Creative";

  private int atomic_creative_id;
  private Date created_on;
  private int id;
  private Date last_modified;
  private String tag;
  private String tag_type;
  private int version;
  private String name;

  public int getAtomicCreativeId() {
    return atomic_creative_id;
  }

  public void setAtomicCreativeId(int atomic_creative_id) {
    this.atomic_creative_id = atomic_creative_id;
  }

  public Date getCreatedOn() {
    return created_on;
  }

  public void setCreatedOn(Date created_on) {
    this.created_on = created_on;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Date getLastModified() {
    return last_modified;
  }

  public void setLastModified(Date last_modified) {
    this.last_modified = last_modified;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public String getTagType() {
    return tag_type;
  }

  public void setTagType(String tag_type) {
    this.tag_type = tag_type;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
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

  public String getName() {
	  return name;
  }

  public void setName(String name) {
	  this.name = name;
  }

}
