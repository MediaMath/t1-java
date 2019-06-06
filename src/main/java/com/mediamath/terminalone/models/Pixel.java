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
import org.javers.core.metamodel.annotation.DiffIgnore;
import org.javers.core.metamodel.annotation.Id;

public class Pixel implements T1Entity {

  private static final String entityName = "PixelBundle";

  public enum pixelTypes {
    creative, event, data, segment
  }

  public enum pricing {
    CPM, CPTS
  }

  public enum rmxConvTypes {
    one, variable
  }

  public enum tagTypes {
    dfa, uat, image, iframe, js
  }

  public enum roiFields {
    S1, S2, V1, V2
  }

  private int advertiser_id;
  private int agency_id;
  private float cost_cpm;
  private float cost_cpts;
  private float cost_pct_cpm;
  private Date created_on;
  private roiFields currency;
  private String currency_fixed;
  private boolean eligible;
  private String external_identifier;
  private int id;
  private String keywords;
  private String name;
  private pixelTypes pixel_type;
  private pricing pricing;
  private int provider_id;
  private roiFields revenue;
  private int rmx_conversion_minutes;
  private rmxConvTypes rmx_conversion_type;
  private boolean rmx_friendly;
  private boolean rmx_merit;
  private int rmx_pc_window_minutes;
  private int rmx_pv_window_minutes;
  private String segment_op;
  private boolean status;
  private tagTypes tag_type;
  private String tags;
  private String type;
  private Date updated_on;
  private int version;

  private Advertiser advertiser;
  private Agency agency;
  private PixelProvider provider;

  public int getAdvertiserId() {
    return advertiser_id;
  }

  public void setAdvertiserId(int advertiser_id) {
    this.advertiser_id = advertiser_id;
  }

  public int getAgencyId() {
    return agency_id;
  }

  public void setAgencyId(int agency_id) {
    this.agency_id = agency_id;
  }

  public float getCostCpm() {
    return cost_cpm;
  }

  public void setCostCpm(float cost_cpm) {
    this.cost_cpm = cost_cpm;
  }

  public float getCostCpts() {
    return cost_cpts;
  }

  public void setCostCpts(float cost_cpts) {
    this.cost_cpts = cost_cpts;
  }

  public float getCostPctCpm() {
    return cost_pct_cpm;
  }

  public void setCostPctCpm(float cost_pct_cpm) {
    this.cost_pct_cpm = cost_pct_cpm;
  }

  @DiffIgnore
  public Date getCreatedOn() {
    return created_on;
  }

  public void setCreatedOn(Date created_on) {
    this.created_on = created_on;
  }

  public boolean isEligible() {
    return eligible;
  }

  public void setEligible(boolean eligible) {
    this.eligible = eligible;
  }

  @DiffIgnore
  public String getExternalIdentifier() {
    return external_identifier;
  }

  public void setExternalIdentifier(String external_identifier) {
    this.external_identifier = external_identifier;
  }

  @Id
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getKeywords() {
    return keywords;
  }

  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public pixelTypes getPixelType() {
    return pixel_type;
  }

  public void setPixelType(pixelTypes pixel_type) {
    this.pixel_type = pixel_type;
  }

  @DiffIgnore
  public pricing getPricing() {
    return pricing;
  }

  public void setPricing(pricing pricing) {
    this.pricing = pricing;
  }

  public int getProviderId() {
    return provider_id;
  }

  public void setProviderId(int provider_id) {
    this.provider_id = provider_id;
  }

  @DiffIgnore
  public int getRmxConversionMinutes() {
    return rmx_conversion_minutes;
  }

  public void setRmxConversionMinutes(int rmx_conversion_minutes) {
    this.rmx_conversion_minutes = rmx_conversion_minutes;
  }

  @DiffIgnore
  public rmxConvTypes getRmxConversionType() {
    return rmx_conversion_type;
  }

  public void setRmxConversionType(rmxConvTypes rmx_conversion_type) {
    this.rmx_conversion_type = rmx_conversion_type;
  }

  public boolean isRmxFriendly() {
    return rmx_friendly;
  }

  public void setRmxFriendly(boolean rmx_friendly) {
    this.rmx_friendly = rmx_friendly;
  }

  public boolean isRmxMerit() {
    return rmx_merit;
  }

  public void setRmxMerit(boolean rmx_merit) {
    this.rmx_merit = rmx_merit;
  }

  public int getRmxPcWindowMinutes() {
    return rmx_pc_window_minutes;
  }

  public void setRmxPcWindowMinutes(int rmx_pc_window_minutes) {
    this.rmx_pc_window_minutes = rmx_pc_window_minutes;
  }

  public int getRmxPvWindowMinutes() {
    return rmx_pv_window_minutes;
  }

  public void setRmxPvWindowMinutes(int rmx_pv_window_minutes) {
    this.rmx_pv_window_minutes = rmx_pv_window_minutes;
  }

  public String getSegmentOp() {
    return segment_op;
  }

  public void setSegmentOp(String segment_op) {
    this.segment_op = segment_op;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public tagTypes getTagType() {
    return tag_type;
  }

  public void setTagType(tagTypes tag_type) {
    this.tag_type = tag_type;
  }

  @DiffIgnore
  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @DiffIgnore
  public Date getUpdatedOn() {
    return updated_on;
  }

  public void setUpdatedOn(Date updated_on) {
    this.updated_on = updated_on;
  }

  @DiffIgnore
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

  public roiFields getCurrency() {
    return currency;
  }

  public void setCurrency(roiFields currency) {
    this.currency = currency;
  }

  public String getCurrencyFixed() {
    return currency_fixed;
  }

  public void setCurrencyFixed(String currency_fixed) {
    this.currency_fixed = currency_fixed;
  }

  public roiFields getRevenue() {
    return revenue;
  }

  public void setRevenue(roiFields revenue) {
    this.revenue = revenue;
  }

  public Advertiser getAdvertiser() {
    return advertiser;
  }

  public void setAdvertiser(Advertiser advertiser) {
    this.advertiser = advertiser;
  }

  public Agency getAgency() {
    return agency;
  }

  public void setAgency(Agency agency) {
    this.agency = agency;
  }

  public PixelProvider getProvider() {
    return provider;
  }

  public void setProvider(PixelProvider provider) {
    this.provider = provider;
  }

  @Override
  @DiffIgnore
  public Form getForm() {

    Form pixelForm = new Form();

    if (this.getAdvertiserId() > 0) {
      pixelForm.param("advertiser_id", String.valueOf(this.getAdvertiserId()));
    }

    if (this.getAgencyId() > 0) {
      pixelForm.param("agency_id", String.valueOf(this.getAgencyId()));
    }

    if (this.getProviderId() > 0) {
      pixelForm.param("provider_id", String.valueOf(this.getProviderId()));
    }

    if (this.getCostCpm() > 0f) {
      pixelForm.param("cost_cpm", String.valueOf(this.getCostCpm()));
    }

    if (this.getCostPctCpm() > 0f) {
      pixelForm.param("cost_pct_cpm", String.valueOf(this.getCostPctCpm()));
    }

    if (this.getCostCpts() > 0f) {
      pixelForm.param("cost_cpts", String.valueOf(this.getCostCpts()));
    }

    if (this.getCreatedOn() != null) {
      pixelForm.param("created_on", this.getCreatedOn().toString());
    }

    if (this.getCurrency() != null) {
      pixelForm.param("currency", this.getCurrency().toString());
    }

    if (this.getCurrencyFixed() != null) {
      pixelForm.param("currency_fixed", this.getCurrencyFixed());
    }

    if (this.getRevenue() != null) {
      pixelForm.param("revenue", this.getRevenue().toString());
    }

    pixelForm.param("eligible", Utility.getOnOrOff(this.isEligible()));

    if (this.getExternalIdentifier() != null) {
      pixelForm.param("external_identifier", this.getExternalIdentifier());
    }

    if (this.getKeywords() != null) {
      pixelForm.param("keywords", this.getKeywords());
    }

    if (this.getName() != null) {
      pixelForm.param("name", this.getName());
    }

    if (this.getPixelType() != null) {
      pixelForm.param("pixel_type", this.getPixelType().toString());
    } else {
      pixelForm.param("pixel_type", "event");
    }

    if (this.getPricing() != null) {
      pixelForm.param("pricing", this.getPricing().toString());
    } else {
      pixelForm.param("pricing", "CPM");
    }

    if (this.getRmxConversionMinutes() > 0) {
      pixelForm.param("rmx_conversion_minutes", String.valueOf(this.getRmxConversionMinutes()));
    }

    if (this.getRmxConversionType() != null) {
      pixelForm.param("rmx_conversion_type", this.getRmxConversionType().toString());
    } else {
      pixelForm.param("rmx_conversion_type", "one");
    }

    pixelForm.param("rmx_friendly", Utility.getOnOrOff(this.isRmxFriendly()));
    pixelForm.param("rmx_merit", Utility.getOnOrOff(this.isRmxMerit()));

    if (this.getRmxPcWindowMinutes() > 0) {
      pixelForm.param("rmx_pc_window_minutes", String.valueOf(this.getRmxPcWindowMinutes()));
    }

    if (this.getRmxPvWindowMinutes() > 0) {
      pixelForm.param("rmx_pv_window_minutes", String.valueOf(this.getRmxPvWindowMinutes()));
    }

    if (this.getSegmentOp() != null) {
      pixelForm.param("segment_op", this.getSegmentOp());
    }

    pixelForm.param("status", Utility.getOnOrOff(this.isStatus()));

    if (this.getTagType() != null) {
      pixelForm.param("tag_type", this.getTagType().toString());
    } else {
      pixelForm.param("tag_type", "image");
    }

    if (this.getTags() != null) {
      pixelForm.param("tags", this.getTags());
    }

    if (this.getType() != null) {
      pixelForm.param("type", this.getType());
    }

    if (this.getVersion() >= 0) {
      pixelForm.param("version", String.valueOf(this.getVersion()));
    }

    return Utility.getFilteredForm(pixelForm, "pixelbundle");
  }

  @Override
  @DiffIgnore
  public String getUri() {
    StringBuilder uri = new StringBuilder();
    if (this.getId() > 0) {
      uri.append("/");
      uri.append(this.getId());
    }
    return uri.toString();
  }

}
