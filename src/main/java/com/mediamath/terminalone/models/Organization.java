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
import java.util.Date;

import javax.ws.rs.core.Form;

import com.mediamath.terminalone.exceptions.ClientException;
import com.mediamath.terminalone.utils.Utility;

public class Organization implements T1Entity {

  private static final String entityName = "Organization";

  private String address_1;
  private String address_2;
  private int adx_seat_account_id;
  private boolean allow_byo_price;
  private boolean allow_x_agency_pixels;
  private String city;
  private String contact_name;
  private String country;
  private Date created_on;
  private String curency_code;
  private int id;
  private String mm_contact_name;
  private String name;
  private String phone;
  private String state;
  private boolean status;
  private String tag_ruleset;
  private Date updated_on;
  private boolean use_evidon_optout;
  private ArrayList<String> org_type = new ArrayList<String>();
  private int version;
  private String zip;
  private String billing_country_code;
  private boolean override_suspicious_traffic_filter;
  private int suspicious_traffic_filter_level;

  public String getAddress1() {
    return address_1;
  }

  public void setAddress1(String address_1) {
    this.address_1 = address_1;
  }

  public String getAddress2() {
    return address_2;
  }

  public void setAddress2(String address_2) {
    this.address_2 = address_2;
  }

  public int getAdxSeatAccountId() {
    return adx_seat_account_id;
  }

  public void setAdxSeatAccountId(int adx_seat_account_id) {
    this.adx_seat_account_id = adx_seat_account_id;
  }

  public boolean isAllowByoPrice() {
    return allow_byo_price;
  }

  public void setAllowByoPrice(boolean allow_byo_price) {
    this.allow_byo_price = allow_byo_price;
  }

  public boolean isAllowXAgencyPixels() {
    return allow_x_agency_pixels;
  }

  public void setAllowXAgencyPixels(boolean allow_x_agency_pixels) {
    this.allow_x_agency_pixels = allow_x_agency_pixels;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getContactName() {
    return contact_name;
  }

  public void setContactName(String contact_name) {
    this.contact_name = contact_name;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Date getCreatedOn() {
    return created_on;
  }

  public void setCreatedOn(Date created_on) {
    this.created_on = created_on;
  }

  public String getCurencyCode() {
    return curency_code;
  }

  public void setCurencyCode(String curency_code) {
    this.curency_code = curency_code;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getMmContactName() {
    return mm_contact_name;
  }

  public void setMmContactName(String mm_contact_name) {
    this.mm_contact_name = mm_contact_name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public String getTagRuleset() {
    return tag_ruleset;
  }

  public void setTagRuleset(String tag_ruleset) {
    this.tag_ruleset = tag_ruleset;
  }

  public Date getUpdatedOn() {
    return updated_on;
  }

  public void setUpdatedOn(Date updated_on) {
    this.updated_on = updated_on;
  }

  public boolean isUseEvidonOptout() {
    return use_evidon_optout;
  }

  public void setUseEvidonOptout(boolean use_evidon_optout) {
    this.use_evidon_optout = use_evidon_optout;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public ArrayList<String> getOrgType() {
    return org_type;
  }

  public void setOrgType(ArrayList<String> org_type) {
    this.org_type = org_type;
  }

  public String getBillingCountryCode() {
    return billing_country_code;
  }

  public void setBillingCountryCode(String billing_country_code) {
    this.billing_country_code = billing_country_code;
  }

  public boolean isOverrideSuspiciousTrafficFilter() {
    return override_suspicious_traffic_filter;
  }

  public void setOverrideSuspiciousTrafficFilter(boolean override_suspicious_traffic_filter) {
    this.override_suspicious_traffic_filter = override_suspicious_traffic_filter;
  }

  public int getSuspiciousTrafficFilterLevel() {
    return suspicious_traffic_filter_level;
  }

  public void setSuspiciousTrafficFilterLevel(int suspicious_traffic_filter_level) {
    this.suspicious_traffic_filter_level = suspicious_traffic_filter_level;
  }

  public String getEntityname() {
    return entityName;
  }
  
  @Override
  public Form getForm() {

    Form orgForm = new Form();
    if (this.getName() != null) {
      orgForm.param("name", this.getName());
    }
    orgForm.param("status", Utility.getOnOrOff(this.isStatus()));

    if (this.getContactName() != null) {
      orgForm.param("contact_name", this.getContactName());
    }

    if (this.getAddress1() != null) {
      orgForm.param("address_1", this.getAddress1());
    }
    if (this.getAddress2() != null) {
      orgForm.param("address_2", this.getAddress2());
    }
    if (this.getCity() != null) {
      orgForm.param("city", this.getCity());
    }
    if (this.getState() != null) {
      orgForm.param("state", this.getState());
    }
    if (this.getZip() != null) {
      orgForm.param("zip", this.getZip());
    }
    if (this.getCountry() != null) {
      orgForm.param("country", this.getCountry());
    }
    if (this.getPhone() != null) {
      orgForm.param("phone", this.getPhone());
    }
    if (this.getMmContactName() != null) {
      orgForm.param("mm_contact_name", this.getMmContactName());
    }

    orgForm.param("allow_x_agency_pixels", Utility.getOnOrOff(this.isAllowXAgencyPixels()));
    orgForm.param("use_evidon_optout", Utility.getOnOrOff(this.isUseEvidonOptout()));
    orgForm.param("allow_byo_price", Utility.getOnOrOff(this.isAllowByoPrice()));
    if (this.getCurencyCode() != null) {
      orgForm.param("currency_code", this.getCurencyCode());
    }

    orgForm.param("adx_seat_account_id", String.valueOf(this.getAdxSeatAccountId()));
    if (this.getBillingCountryCode() != null) {
      orgForm.param("billing_country_code", this.getBillingCountryCode());
    }

    orgForm.param("override_suspicious_traffic_filter",
        Utility.getOnOrOff(this.isOverrideSuspiciousTrafficFilter()));
    if (this.getSuspiciousTrafficFilterLevel() > 0) {
      orgForm.param("suspicious_traffic_filter_level",
          String.valueOf(this.getSuspiciousTrafficFilterLevel()));
    }

    if (this.getVersion() >= 0) {
      orgForm.param("version", String.valueOf(this.getVersion()));
    }

    // TODO check how to pass array to form
    orgForm.param("org_type", (!this.getOrgType().isEmpty()) ? this.getOrgType().get(0) : "buyer");

    Form finalOrgForm = Utility.getFilteredForm(orgForm, "organization");

    return finalOrgForm;

  }

  @Override
  public String getUri() throws ClientException {
    StringBuilder uri = new StringBuilder();
    if (this.getId() > 0) {
      uri.append("/");
      uri.append(this.getId());
    } else {
      throw new ClientException("Can not update Organization, ID not found!!");
    }
    return uri.toString();
  }

}
