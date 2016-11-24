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

import java.util.Date;

import javax.ws.rs.core.Form;

import com.mediamath.terminalone.utils.Utility;

public class Concept implements T1Entity {

  private static final String entityName = "Concept";

  private int advertiser_id;
  private Date created_on;
  private int id;
  private String name;
  private boolean status;
  private Date updated_on;
  private int version;

  private Advertiser advertiser;

  public int getAdvertiserId() {
    return advertiser_id;
  }

  public void setAdvertiserId(int advertiser_id) {
    this.advertiser_id = advertiser_id;
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public Date getUpdatedOn() {
    return updated_on;
  }

  public void setUpdatedOn(Date updated_on) {
    this.updated_on = updated_on;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public Advertiser getAdvertiser() {
    return advertiser;
  }

  public void setAdvertiser(Advertiser advertiser) {
    this.advertiser = advertiser;
  }

  public String getEntityname() {
    return entityName;
  }
  
  @Override
  public Form getForm() {
    Form conceptForm = new Form();

    conceptForm.param("name", this.getName());

    if (this.getAdvertiserId() > 0) {
      conceptForm.param("advertiser_id", String.valueOf(this.getAdvertiserId()));
    }

    conceptForm.param("status", Utility.getOnOrOff(this.isStatus()));

    if (this.getVersion() >= 0) {
      conceptForm.param("version", String.valueOf(this.getVersion()));
    }

    return conceptForm;
  }


  @Override
  public String getUri() {
    // TODO Auto-generated method stub
    return null;
  }

}
