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

import javax.ws.rs.core.Form;

import com.mediamath.terminalone.utils.Utility;

public class Agency implements T1Entity {

  private static final String entityName = "Agency";

  private boolean allow_x_adv_optimization = false;
  private boolean allow_x_adv_pixels = false;
  private int billing_contact_id;
  private String dmp_enabled;
  private String created_on;
  private int id;
  private String logo;
  private String name;
  private int organization_id;
  private int sales_contact_id;
  private boolean status;
  private String updated_on;
  private int version;
  private int traffic_contact_id;
  private String entity_type;
  
  
  private Organization organization;
  private Contact billing_contact;
  private Contact sales_contact;
  private Contact traffic_contact;

  public Agency() {
  }

  public boolean isAllowXAdvOptimization() {
    return allow_x_adv_optimization;
  }

  public void setAllowXAdvOptimization(boolean allow_x_adv_optimization) {
    this.allow_x_adv_optimization = allow_x_adv_optimization;
  }

  public int getBillingContactId() {
    return billing_contact_id;
  }

  public void setBillingContactId(int billing_contact_id) {
    this.billing_contact_id = billing_contact_id;
  }

  public String getCreatedOn() {
    return created_on;
  }

  public void setCreatedOn(String created_on) {
    this.created_on = created_on;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = logo;
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

  public int getSalesContactId() {
    return sales_contact_id;
  }

  public void setSalesContactId(int sales_contact_id) {
    this.sales_contact_id = sales_contact_id;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public String getUpdatedOn() {
    return updated_on;
  }

  public void setUpdatedOn(String updated_on) {
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

  public Contact getBillingContact() {
    return billing_contact;
  }

  public void setBillingContact(Contact billing_contact) {
    this.billing_contact = billing_contact;
  }

  public Contact getSalesContact() {
    return sales_contact;
  }

  public void setSalesContact(Contact sales_contact) {
    this.sales_contact = sales_contact;
  }

  public Contact getTrafficContact() {
    return traffic_contact;
  }

  public void setTrafficContact(Contact traffic_contact) {
    this.traffic_contact = traffic_contact;
  }

  @Override
  public String getEntityname() {
    return entityName;
  }

  public boolean isAllowXAdvPixels() {
    return allow_x_adv_pixels;
  }

  public void setAllowXAdvPixels(boolean allow_x_adv_pixels) {
    this.allow_x_adv_pixels = allow_x_adv_pixels;
  }

  public String getDmpEnabled() {
    return dmp_enabled;
  }

  public void setDmpEnabled(String dmp_enabled) {
    this.dmp_enabled = dmp_enabled;
  }

  public int getTrafficContactId() {
    return traffic_contact_id;
  }

  public void setTrafficContactId(int traffic_contact_id) {
    this.traffic_contact_id = traffic_contact_id;
  }
  
  
  
  
  public String getEntityType() {
	return entity_type;
}

public void setEntityType(String entity_type) {
	this.entity_type = entity_type;
}

/**
   * creates an Agency Form object.
   * 
   */
  @Override
  public Form getForm() {

    Form agencyForm = new Form();

    // required
    agencyForm.param("name", this.getName());

    agencyForm.param("organization_id", String.valueOf(this.getOrganizationId()));

    // optional
    agencyForm.param("allow_x_adv_optimization",
        Utility.getOnOrOff(this.isAllowXAdvOptimization()));

    agencyForm.param("allow_x_adv_pixels", Utility.getOnOrOff(this.isAllowXAdvPixels()));

    if (this.getBillingContactId() > 0) {
      agencyForm.param("billing_contact_id", String.valueOf(this.getBillingContactId()));
    }

    if (this.getDmpEnabled() != null) {
      agencyForm.param("dmp_enabled", this.getDmpEnabled());
    }

    if (this.getCreatedOn() != null) {
      agencyForm.param("created_on", this.getCreatedOn());
    }

    if (this.getLogo() != null) {
      agencyForm.param("logo", this.getLogo());
    }

    if (this.getSalesContactId() > 0) {
      agencyForm.param("sales_contact_id", String.valueOf(this.getSalesContactId()));
    }

    agencyForm.param("status", Utility.getOnOrOff(this.isStatus()));

    if (this.getVersion() >= 0) {
      agencyForm.param("version", String.valueOf(this.getVersion()));
    }

    if (this.getTrafficContactId() > 0) {
      agencyForm.param("traffic_contact_id", String.valueOf(this.getTrafficContactId()));
    }

    if (this.getUpdatedOn() != null) {
      agencyForm.param("updated_on", String.valueOf(this.getUpdatedOn()));
    }

    if (this.getEntityType() != null) {
        agencyForm.param("entity_type", String.valueOf(this.getEntityType()));
    }

    return Utility.getFilteredForm(agencyForm, "agency");
  }
  
  @Override
  public String getUri() {
    StringBuilder uri = new StringBuilder();
    
    if (this.getId() > 0) {
      uri.append("/");
      uri.append(this.getId());
    }
    
    
    return uri.toString();
  }

}
