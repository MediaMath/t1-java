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

public class Advertiser implements T1Entity {

  private static final String entityName = "Advertiser";

  public enum dmpSettings {
    inherits("inherits"), disabled("disabled");
    String val;

    dmpSettings(String s) {
      val = s;
    }

  }

  public enum freqInts {
    hour("hour"), day("day"), week("week"), month("month"), not_applicable("not-applcable");
    String val;

    freqInts(String s) {
      val = s;
    }
  }

  public enum freqTypes {
    even("even"), asap("asap"), no_limit("no-limit");
    String val;

    freqTypes(String s) {
      val = s;
    }
  }

  private int id;
  private float ad_server_fee;
  private int ad_server_id;
  private String ad_server_password;
  private String ad_server_username;
  private int agency_id;
  private boolean allow_x_strat_optimization;
  private int billing_contact_id;
  private Date created_on;
  private String domain;
  private dmpSettings dmp_enabled;
  private freqTypes frequency_type;
  private freqInts frequency_interval;
  private int frequency_amount;
  private boolean minimize_multi_ads;
  private String name;
  private int sales_contact_id;
  private boolean status;
  private Date updated_on;
  private int version;
  private int vertical_id;
  private Agency agency;
  private Contact billing_contact;
  private Contact sales_contact;
  private Vertical vertical;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public float getAdServerFee() {
    return ad_server_fee;
  }

  public void setAdServerFee(float ad_server_fee) {
    this.ad_server_fee = ad_server_fee;
  }

  public int getAdServerId() {
    return ad_server_id;
  }

  public void setAdServerId(int ad_server_id) {
    this.ad_server_id = ad_server_id;
  }

  public String getAdServerPassword() {
    return ad_server_password;
  }

  public void setAdServerPassword(String ad_server_password) {
    this.ad_server_password = ad_server_password;
  }

  public String getAdServerUsername() {
    return ad_server_username;
  }

  public void setAdServerUsername(String ad_server_username) {
    this.ad_server_username = ad_server_username;
  }

  public int getAgencyId() {
    return agency_id;
  }

  public void setAgencyId(int agency_id) {
    this.agency_id = agency_id;
  }

  public boolean isAllowXStratOptimization() {
    return allow_x_strat_optimization;
  }

  public void setAllowXStratOptimization(boolean allow_x_strat_optimization) {
    this.allow_x_strat_optimization = allow_x_strat_optimization;
  }

  public int getBillingContactId() {
    return billing_contact_id;
  }

  public void setBillingContactId(int billing_contact_id) {
    this.billing_contact_id = billing_contact_id;
  }

  public Date getCreatedOn() {
    return created_on;
  }

  public void setCreatedOn(Date created_on) {
    this.created_on = created_on;
  }

  public String getDomain() {
    return domain;
  }

  public void setDomain(String domain) {
    this.domain = domain;
  }

  public dmpSettings getDmpEnabled() {
    return dmp_enabled;
  }

  public void setDmpEnabled(dmpSettings dmp_enabled) {
    this.dmp_enabled = dmp_enabled;
  }

  public freqTypes getFrequencyType() {
    return frequency_type;
  }

  public void setFrequencyType(freqTypes frequency_type) {
    this.frequency_type = frequency_type;
  }

  public freqInts getFrequencyInterval() {
    return frequency_interval;
  }

  public void setFrequencyInterval(freqInts frequency_interval) {
    this.frequency_interval = frequency_interval;
  }

  public int getFrequencyAmount() {
    return frequency_amount;
  }

  public void setFrequencyAmount(int frequency_amount) {
    this.frequency_amount = frequency_amount;
  }

  public boolean isMinimizeMultiAds() {
    return minimize_multi_ads;
  }

  public void setMinimizeMultiAds(boolean minimize_multi_ads) {
    this.minimize_multi_ads = minimize_multi_ads;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public int getVerticalId() {
    return vertical_id;
  }

  public void setVerticalId(int vertical_id) {
    this.vertical_id = vertical_id;
  }

  public Agency getAgency() {
    return agency;
  }

  public void setAgency(Agency agency) {
    this.agency = agency;
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

  public Vertical getVertical() {
    return vertical;
  }

  public void setVertical(Vertical vertical) {
    this.vertical = vertical;
  }

  @Override
  public String getEntityname() {
    return entityName;
  }

  @Override
  /**
   * creates a Form object to pass it to connection
   * 
   * @param entity
   *          expects an Advertiser Entity.
   * @return Form object.
   */
  public Form getForm() {
    Form advertiserForm = new Form();

    // required
    advertiserForm.param("name", this.getName());

    advertiserForm.param("ad_server_id", String.valueOf(this.getAdServerId()));

    if (this.getAdServerFee() > 0) {
        advertiserForm.param("ad_server_fee", String.valueOf(this.getAdServerFee()));
      }
    
    // optional
    advertiserForm.param("allow_x_strat_optimization",
        Utility.getOnOrOff(this.isAllowXStratOptimization()));

    advertiserForm.param("agency_id", String.valueOf(this.getAgencyId()));

    if (this.getBillingContactId() > 0) {
      advertiserForm.param("billing_contact_id", String.valueOf(this.getBillingContactId()));
    }

    if (this.getCreatedOn() != null) {
      advertiserForm.param("created_on", this.getCreatedOn().toString());
    }

    if (this.getFrequencyType() != null) {
      advertiserForm.param("frequency_type", this.getFrequencyType().toString());
    } else {
      advertiserForm.param("frequency_type", "no-limit");
    }

    if (this.getFrequencyInterval() != null) {
      advertiserForm.param("frequency_interval", String.valueOf(this.getFrequencyInterval()));
    } else {
      advertiserForm.param("frequency_interval", "not-applicable");
    }

    if (this.getFrequencyAmount() > 0) {
      advertiserForm.param("frequency_amount", String.valueOf(this.getFrequencyAmount()));
    }

    advertiserForm.param("minimize_multi_ads", Utility.getOnOrOff(this.isMinimizeMultiAds()));

    if (this.getSalesContactId() > 0) {
      advertiserForm.param("sales_contact_id", String.valueOf(this.getSalesContactId()));
    }

    advertiserForm.param("status", Utility.getOnOrOff(this.isStatus()));

    if (this.getVersion() >= 0) {
      advertiserForm.param("version", String.valueOf(this.getVersion()));
    }

    if (this.getVerticalId() > 0) {
      advertiserForm.param("vertical_id", String.valueOf(this.getVerticalId()));
    }

    if (this.getUpdatedOn() != null) {
      advertiserForm.param("updated_on", String.valueOf(this.getUpdatedOn()));
    }

    if (this.getDmpEnabled() != null) {
      advertiserForm.param("dmp_enabled", String.valueOf(this.getDmpEnabled()));
    } else {
      advertiserForm.param("dmp_enabled", "inherits");
    }

    if (this.getDomain() != null) {
        advertiserForm.param("domain", this.getDomain());
    }

    return Utility.getFilteredForm(advertiserForm, "advertiser");
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
