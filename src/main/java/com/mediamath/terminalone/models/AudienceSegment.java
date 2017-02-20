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

public class AudienceSegment implements T1Entity {

  private static final String entityName = "AudienceSegment";

  private int audience_vendor_id;
  private boolean buyable;
  private int child_count;
  private String code;
  private Date created_on;
  private String full_path;
  private int id;
  private String name;
  private int parent_audience_segment_id;
  private float retail_cpm;
  private float wholesale_cpm;
  private String tag;
  private int uniques;
  private Date updated_on;
  private int version;
  private String entity_type;


  public int getAudienceVendorId() {
    return audience_vendor_id;
  }

  public void setAudienceVendorId(int audience_vendor_id) {
    this.audience_vendor_id = audience_vendor_id;
  }

  public boolean isBuyable() {
    return buyable;
  }

  public void setBuyable(boolean buyable) {
    this.buyable = buyable;
  }

  public int getChildCount() {
    return child_count;
  }

  public void setChildCount(int child_count) {
    this.child_count = child_count;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Date getCreatedOn() {
    return created_on;
  }

  public void setCreatedOn(Date created_on) {
    this.created_on = created_on;
  }

  public String getFullPath() {
    return full_path;
  }

  public void setFullPath(String full_path) {
    this.full_path = full_path;
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

  public int getParentAudienceSegmentId() {
    return parent_audience_segment_id;
  }

  public void setParentAudienceSegmentId(int parent_audience_segment_id) {
    this.parent_audience_segment_id = parent_audience_segment_id;
  }

  public float getRetailCpm() {
    return retail_cpm;
  }

  public void setRetailCpm(float retail_cpm) {
    this.retail_cpm = retail_cpm;
  }

  public float getWholesaleCpm() {
    return wholesale_cpm;
  }

  public void setWholesaleCpm(float wholesale_cpm) {
    this.wholesale_cpm = wholesale_cpm;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public int getUniques() {
    return uniques;
  }

  public void setUniques(int uniques) {
    this.uniques = uniques;
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

  public String getEntityType() {
	return entity_type;
}

public void setEntityType(String entity_type) {
	this.entity_type = entity_type;
}

@Override
  public String getEntityname() {
    return entityName;
  }

  @Override
  public Form getForm() {
    return null;
  }

  @Override
  public String getUri() {
    return null;
  }
}
