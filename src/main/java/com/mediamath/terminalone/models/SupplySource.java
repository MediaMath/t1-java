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

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.javers.core.metamodel.annotation.DiffIgnore;

import java.util.Date;

import javax.ws.rs.core.Form;

public class SupplySource implements T1Entity {

  private static final String entityName = "SupplySource";

  private int bidder_exchange_identifier;
  private String code;
  private Date created_on;
  private String default_seat_identifier;
  private boolean distribute;
  private boolean has_display;
  private boolean has_mobile_display;
  private boolean has_mobile_video;
  private boolean has_video;
  private int id;
  private boolean is_proservice;
  private boolean mm_safe;
  private int parent_supply_id;
  private String pixel_tag;
  private boolean pmp_enabled;
  private boolean rtb_enabled;
  private String rtb_type;
  private boolean seat_enabled;
  private boolean status;
  private String supply_type;
  private Date updated_on;
  private boolean use_pool;
  private int version;
  private String name;

  public int getBidderExchangeIdentifier() {
    return bidder_exchange_identifier;
  }

  public void setBidderExchangeIdentifier(int bidder_exchange_identifier) {
    this.bidder_exchange_identifier = bidder_exchange_identifier;
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

  public String getDefaultSeatIdentifier() {
    return default_seat_identifier;
  }

  public void setDefaultSeatIdentifier(String default_seat_identifier) {
    this.default_seat_identifier = default_seat_identifier;
  }

  public boolean isDistribute() {
    return distribute;
  }

  public void setDistribute(boolean distribute) {
    this.distribute = distribute;
  }

  public boolean isHasDisplay() {
    return has_display;
  }

  public void setHasDisplay(boolean has_display) {
    this.has_display = has_display;
  }

  public boolean isHasMobileDisplay() {
    return has_mobile_display;
  }

  public void setHasMobileDisplay(boolean has_mobile_display) {
    this.has_mobile_display = has_mobile_display;
  }

  public boolean isHasMobileVideo() {
    return has_mobile_video;
  }

  public void setHasMobileVideo(boolean has_mobile_video) {
    this.has_mobile_video = has_mobile_video;
  }

  public boolean isHasVideo() {
    return has_video;
  }

  public void setHasVideo(boolean has_video) {
    this.has_video = has_video;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public boolean isProservice() {
    return is_proservice;
  }

  public void setIsProservice(boolean is_proservice) {
    this.is_proservice = is_proservice;
  }

  public boolean isMmSafe() {
    return mm_safe;
  }

  public void setMmSafe(boolean mm_safe) {
    this.mm_safe = mm_safe;
  }

  public int getParentSupplyId() {
    return parent_supply_id;
  }

  public void setParentSupplyId(int parent_supply_id) {
    this.parent_supply_id = parent_supply_id;
  }

  public String getPixelTag() {
    return pixel_tag;
  }

  public void setPixelTag(String pixel_tag) {
    this.pixel_tag = pixel_tag;
  }

  public boolean isPmpEnabled() {
    return pmp_enabled;
  }

  public void setPmpEnabled(boolean pmp_enabled) {
    this.pmp_enabled = pmp_enabled;
  }

  public boolean isRtbEnabled() {
    return rtb_enabled;
  }

  public void setRtbEnabled(boolean rtb_enabled) {
    this.rtb_enabled = rtb_enabled;
  }

  public String getRtbType() {
    return rtb_type;
  }

  public void setRtbType(String rtb_type) {
    this.rtb_type = rtb_type;
  }

  public boolean isSeatEnabled() {
    return seat_enabled;
  }

  public void setSeatEnabled(boolean seat_enabled) {
    this.seat_enabled = seat_enabled;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public String getSupplyType() {
    return supply_type;
  }

  public void setSupplyType(String supply_type) {
    this.supply_type = supply_type;
  }

  public Date getUpdatedOn() {
    return updated_on;
  }

  public void setUpdatedOn(Date updated_on) {
    this.updated_on = updated_on;
  }

  public boolean isUsePool() {
    return use_pool;
  }

  public void setUsePool(boolean use_pool) {
    this.use_pool = use_pool;
  }

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
  
  @Override
  @DiffIgnore
  @JsonIgnore
  public Form getForm() {
    return null;
  }

  @Override
  @DiffIgnore
  @JsonIgnore
  public String getUri() {
    return null;
  }

  public String getName() {
	  return name;
  }

  public void setName(String name) {
	  this.name = name;
  }

}
