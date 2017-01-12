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

public class Deal implements T1Entity {

  private static final String entityName = "Deal";

  public enum dealSources {
    USER, INTERNAL
  };

  public enum mediaTypes {
    DISPLAY, VIDEO
  };

  public enum priceMethods {
    CPM
  };

  public enum priceTypes {
    FIXED, FLOOR
  };

  private int advertiser_id;
  private Date created_on;
  private String currency_code;
  private String deal_identifier;
  private dealSources deal_source;
  private String description;
  private Date end_datetime;
  private int id;
  private mediaTypes media_type;
  private String name;
  private boolean partner_sourced;
  private float price;
  private priceMethods price_method;
  private priceTypes price_type;
  private int publisher_id;
  private Date start_datetime;
  private boolean status;
  private int supply_source_id;
  private Date updated_on;
  private int version;
  private String zone_name;

  private Advertiser advertiser;
  private Publisher publisher;
  private SupplySource supply_source;

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

  public String getCurrencyCode() {
    return currency_code;
  }

  public void setCurrencyCode(String currency_code) {
    this.currency_code = currency_code;
  }

  public String getDealIdentifier() {
    return deal_identifier;
  }

  public void setDealIdentifier(String deal_identifier) {
    this.deal_identifier = deal_identifier;
  }

  public dealSources getDealSource() {
    return deal_source;
  }

  public void setDealSource(dealSources deal_source) {
    this.deal_source = deal_source;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getEndDatetime() {
    return end_datetime;
  }

  public void setEndDatetime(Date end_datetime) {
    this.end_datetime = end_datetime;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public mediaTypes getMediaType() {
    return media_type;
  }

  public void setMediaType(mediaTypes media_type) {
    this.media_type = media_type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isPartnerSourced() {
    return partner_sourced;
  }

  public void setPartnerSourced(boolean partner_sourced) {
    this.partner_sourced = partner_sourced;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public priceMethods getPriceMethod() {
    return price_method;
  }

  public void setPriceMethod(priceMethods price_method) {
    this.price_method = price_method;
  }

  public priceTypes getPriceType() {
    return price_type;
  }

  public void setPriceType(priceTypes price_type) {
    this.price_type = price_type;
  }

  public int getPublisherId() {
    return publisher_id;
  }

  public void setPublisherId(int publisher_id) {
    this.publisher_id = publisher_id;
  }

  public Date getStartDatetime() {
    return start_datetime;
  }

  public void setStartDatetime(Date start_datetime) {
    this.start_datetime = start_datetime;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public int getSupplySourceId() {
    return supply_source_id;
  }

  public void setSupplySourceId(int supply_source_id) {
    this.supply_source_id = supply_source_id;
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

  public String getZoneName() {
    return zone_name;
  }

  public void setZoneName(String zone_name) {
    this.zone_name = zone_name;
  }

  public Advertiser getAdvertiser() {
    return advertiser;
  }

  public void setAdvertiser(Advertiser advertiser) {
    this.advertiser = advertiser;
  }

  public Publisher getPublisher() {
    return publisher;
  }

  public void setPublisher(Publisher publisher) {
    this.publisher = publisher;
  }

  public SupplySource getSupplySource() {
    return supply_source;
  }

  public void setSupplySource(SupplySource supply_source) {
    this.supply_source = supply_source;
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
