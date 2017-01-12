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
import java.util.List;

import javax.ws.rs.core.Form;

public class Vendor implements T1Entity {

  private static final String entityName = "Vendor";

  public enum vendorTypes {
    AD_SERVER, AD_VERIFICATION, CONTEXTUAL, DATA, DSP, DYNAMIC_CREATIVE, NETWORK, OBA_COMPLIANCE, OTHER, PIXEL_TRACKING, RICH_MEDIA, SURVEY
  };

  private boolean adx_approved;
  private boolean adx_declaration_required;
  private boolean adx_ssl_approved;
  private String adx_vendor_identifier;
  private boolean adx_video_approved;
  private boolean adx_video_ssl_approved;
  private Date created_on;
  private String description;
  private int id;
  private boolean is_eligible;
  private boolean mm_contract_available;
  private String name;
  private float rate_card_price;
  private String rate_card_type;
  private Date updated_on;
  private vendorTypes vendor_type;
  private int version;
  private float wholesale_price;

  private List<VendorContract> vendor_contracts;
  private List<VendorDomain> vendor_domains;

  public boolean isAdxApproved() {
    return adx_approved;
  }

  public void setAdxApproved(boolean adx_approved) {
    this.adx_approved = adx_approved;
  }

  public boolean isAdxDeclarationRequired() {
    return adx_declaration_required;
  }

  public void setAdxDeclarationRequired(boolean adx_declaration_required) {
    this.adx_declaration_required = adx_declaration_required;
  }

  public boolean isAdxSslApproved() {
    return adx_ssl_approved;
  }

  public void setAdxSslApproved(boolean adx_ssl_approved) {
    this.adx_ssl_approved = adx_ssl_approved;
  }

  public String getAdxVendorIdentifier() {
    return adx_vendor_identifier;
  }

  public void setAdxVendorIdentifier(String adx_vendor_identifier) {
    this.adx_vendor_identifier = adx_vendor_identifier;
  }

  public boolean isAdxVideoApproved() {
    return adx_video_approved;
  }

  public void setAdxVideoApproved(boolean adx_video_approved) {
    this.adx_video_approved = adx_video_approved;
  }

  public boolean isAdxVideoSslApproved() {
    return adx_video_ssl_approved;
  }

  public void setAdxVideoSslApproved(boolean adx_video_ssl_approved) {
    this.adx_video_ssl_approved = adx_video_ssl_approved;
  }

  public Date getCreatedOn() {
    return created_on;
  }

  public void setCreatedOn(Date created_on) {
    this.created_on = created_on;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public boolean isEligible() {
    return is_eligible;
  }

  public void setIsEligible(boolean is_eligible) {
    this.is_eligible = is_eligible;
  }

  public boolean isMmContractAvailable() {
    return mm_contract_available;
  }

  public void setMmContractAvailable(boolean mm_contract_available) {
    this.mm_contract_available = mm_contract_available;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public float getRateCardPrice() {
    return rate_card_price;
  }

  public void setRateCardPrice(float rate_card_price) {
    this.rate_card_price = rate_card_price;
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

  public vendorTypes getVendorType() {
    return vendor_type;
  }

  public void setVendorType(vendorTypes vendor_type) {
    this.vendor_type = vendor_type;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public float getWholesalePrice() {
    return wholesale_price;
  }

  public void setWholesalePrice(float wholesale_price) {
    this.wholesale_price = wholesale_price;
  }

  public List<VendorContract> getVendorContracts() {
    return vendor_contracts;
  }

  public void setVendorContracts(List<VendorContract> vendor_contracts) {
    this.vendor_contracts = vendor_contracts;
  }

  public List<VendorDomain> getVendorDomains() {
    return vendor_domains;
  }

  public void setVendorDomains(List<VendorDomain> vendor_domains) {
    this.vendor_domains = vendor_domains;
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
