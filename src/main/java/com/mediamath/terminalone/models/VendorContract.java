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

public class VendorContract implements T1Entity {

  private static final String entityName = "VendorContract";

  private int campaign_id;
  private Date created_on;
  private int id;
  private float price;
  private String rate_card_type;
  private Date updated_on;
  private boolean use_mm_contract;
  private int vendor_id;
  private int version;

  private Campaign campaign;
  private Vendor vendor;

  public int getCampaignId() {
    return campaign_id;
  }

  public void setCampaignId(int campaign_id) {
    this.campaign_id = campaign_id;
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

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public String getRateCardType() {
    return rate_card_type;
  }

  public void setRateCardType(String rate_card_type) {
    this.rate_card_type = rate_card_type;
  }

  public Date getUpdatedOn() {
    return updated_on;
  }

  public void setUpdatedOn(Date updated_on) {
    this.updated_on = updated_on;
  }

  public boolean isUseMmContract() {
    return use_mm_contract;
  }

  public void setUseMmContract(boolean use_mm_contract) {
    this.use_mm_contract = use_mm_contract;
  }

  public int getVendorId() {
    return vendor_id;
  }

  public void setVendorId(int vendor_id) {
    this.vendor_id = vendor_id;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public Campaign getCampaign() {
    return campaign;
  }

  public void setCampaign(Campaign campaign) {
    this.campaign = campaign;
  }

  public Vendor getVendor() {
    return vendor;
  }

  public void setVendor(Vendor vendor) {
    this.vendor = vendor;
  }

  public String getEntityname() {
    return entityName;
  }
  
  @Override
  public Form getForm() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getUri() {
    // TODO Auto-generated method stub
    return null;
  }

}
