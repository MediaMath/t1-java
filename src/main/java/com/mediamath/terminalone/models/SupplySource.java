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

	
	public int getBidder_exchange_identifier() {
		return bidder_exchange_identifier;
	}

	public void setBidder_exchange_identifier(int bidder_exchange_identifier) {
		this.bidder_exchange_identifier = bidder_exchange_identifier;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public String getDefault_seat_identifier() {
		return default_seat_identifier;
	}

	public void setDefault_seat_identifier(String default_seat_identifier) {
		this.default_seat_identifier = default_seat_identifier;
	}

	public boolean isDistribute() {
		return distribute;
	}

	public void setDistribute(boolean distribute) {
		this.distribute = distribute;
	}

	public boolean isHas_display() {
		return has_display;
	}

	public void setHas_display(boolean has_display) {
		this.has_display = has_display;
	}

	public boolean isHas_mobile_display() {
		return has_mobile_display;
	}

	public void setHas_mobile_display(boolean has_mobile_display) {
		this.has_mobile_display = has_mobile_display;
	}

	public boolean isHas_mobile_video() {
		return has_mobile_video;
	}

	public void setHas_mobile_video(boolean has_mobile_video) {
		this.has_mobile_video = has_mobile_video;
	}

	public boolean isHas_video() {
		return has_video;
	}

	public void setHas_video(boolean has_video) {
		this.has_video = has_video;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isIs_proservice() {
		return is_proservice;
	}

	public void setIs_proservice(boolean is_proservice) {
		this.is_proservice = is_proservice;
	}

	public boolean isMm_safe() {
		return mm_safe;
	}

	public void setMm_safe(boolean mm_safe) {
		this.mm_safe = mm_safe;
	}

	public int getParent_supply_id() {
		return parent_supply_id;
	}

	public void setParent_supply_id(int parent_supply_id) {
		this.parent_supply_id = parent_supply_id;
	}

	public String getPixel_tag() {
		return pixel_tag;
	}

	public void setPixel_tag(String pixel_tag) {
		this.pixel_tag = pixel_tag;
	}

	public boolean isPmp_enabled() {
		return pmp_enabled;
	}

	public void setPmp_enabled(boolean pmp_enabled) {
		this.pmp_enabled = pmp_enabled;
	}

	public boolean isRtb_enabled() {
		return rtb_enabled;
	}

	public void setRtb_enabled(boolean rtb_enabled) {
		this.rtb_enabled = rtb_enabled;
	}

	public String getRtb_type() {
		return rtb_type;
	}

	public void setRtb_type(String rtb_type) {
		this.rtb_type = rtb_type;
	}

	public boolean isSeat_enabled() {
		return seat_enabled;
	}

	public void setSeat_enabled(boolean seat_enabled) {
		this.seat_enabled = seat_enabled;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getSupply_type() {
		return supply_type;
	}

	public void setSupply_type(String supply_type) {
		this.supply_type = supply_type;
	}

	public Date getUpdated_on() {
		return updated_on;
	}

	public void setUpdated_on(Date updated_on) {
		this.updated_on = updated_on;
	}

	public boolean isUse_pool() {
		return use_pool;
	}

	public void setUse_pool(boolean use_pool) {
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

}
