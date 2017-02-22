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

public class SiteList implements T1Entity {

  private static final String entityName = "SiteList";

  public enum restrictions {
    INCLUDE, EXCLUDE
  }

  private Date created_on;
  private String filename;
  private int id;
  private String name;
  private int organization_id;
  private restrictions restriction;
  private boolean status;
  private Date updated_on;
  private int version;
  private int sites_count;
  private int sites_count_app;

  private Organization organization;

  public Date getCreatedOn() {
    return created_on;
  }

  public void setCreatedOn(Date created_on) {
    this.created_on = created_on;
  }

  public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
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

  public int getOrganizationId() {
    return organization_id;
  }

  public void setOrganizationId(int organization_id) {
    this.organization_id = organization_id;
  }

  public restrictions getRestriction() {
    return restriction;
  }

  public void setRestriction(restrictions restriction) {
    this.restriction = restriction;
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

  public Organization getOrganization() {
    return organization;
  }

  public void setOrganization(Organization organization) {
    this.organization = organization;
  }

  public int getSitesCount() {
    return sites_count;
  }

  public void setSitesCount(int sites_count) {
    this.sites_count = sites_count;
  }

  public int getSitesCountApp() {
    return sites_count_app;
  }

  public void setSitesCountApp(int sites_count_app) {
    this.sites_count_app = sites_count_app;
  }

  @Override
  public String getEntityname() {
    return entityName;
  }

  @Override
  public Form getForm() {
    Form siteListForm = new Form();

    siteListForm.param("name", this.getName());
    siteListForm.param("filename", this.getFilename());

    if (this.getRestriction() != null) {
      siteListForm.param("restriction", String.valueOf(this.getRestriction()));
    } else {
      siteListForm.param("restriction", "EXCLUDE");
    }

    if (this.getOrganizationId() > 0) {
      siteListForm.param("organization_id", String.valueOf(this.getOrganizationId()));
    }

    siteListForm.param("status", Utility.getOnOrOff(this.isStatus()));

    if (this.getVersion() >= 0) {
      siteListForm.param("version", String.valueOf(this.getVersion()));
    }

    if (this.getSitesCount() > 0) {
      siteListForm.param("sites_count", String.valueOf(this.getSitesCount()));
    }

    if (this.getSitesCountApp() > 0) {
      siteListForm.param("sites_count_app", String.valueOf(this.getSitesCountApp()));
    }

    return siteListForm;
  }

  @Override
  public String getUri() {
    StringBuilder uri = new StringBuilder();

    if (this.getId() > 0) {
      uri.append("/" + this.getId());
    }
    return uri.toString();
  }

}
