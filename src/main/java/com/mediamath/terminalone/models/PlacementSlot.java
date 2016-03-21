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

public class PlacementSlot implements T1Entity {

	private static final String entityName = "PlacementSlot";
	
	private int ad_slot;
	private boolean allow_remnant;
	private String auction_type;
	private float budget;
	private float buy_price;
	private String buy_price_type;
	private Date created_on;
	private String description;
	private Date end_date;
	private float est_volume;
	private int frequency_amount;
	private String frequency_interval;
	private String frequency_type;
	private int height;
	private int id;
	private float prm_pub_ceiling;
	private float prm_pub_markup;
	private float sell_price;
	private String sell_price_type;
	private int site_placement_id;
	private Date start_date;
	private Date updated_on;
	private int version;
	private String volume_unit;
	private int width;
	
	
	public int getAd_slot() {
		return ad_slot;
	}


	public void setAd_slot(int ad_slot) {
		this.ad_slot = ad_slot;
	}


	public boolean isAllow_remnant() {
		return allow_remnant;
	}


	public void setAllow_remnant(boolean allow_remnant) {
		this.allow_remnant = allow_remnant;
	}


	public String getAuction_type() {
		return auction_type;
	}


	public void setAuction_type(String auction_type) {
		this.auction_type = auction_type;
	}


	public float getBudget() {
		return budget;
	}


	public void setBudget(float budget) {
		this.budget = budget;
	}


	public float getBuy_price() {
		return buy_price;
	}


	public void setBuy_price(float buy_price) {
		this.buy_price = buy_price;
	}


	public String getBuy_price_type() {
		return buy_price_type;
	}


	public void setBuy_price_type(String buy_price_type) {
		this.buy_price_type = buy_price_type;
	}


	public Date getCreated_on() {
		return created_on;
	}


	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getEnd_date() {
		return end_date;
	}


	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}


	public float getEst_volume() {
		return est_volume;
	}


	public void setEst_volume(float est_volume) {
		this.est_volume = est_volume;
	}


	public int getFrequency_amount() {
		return frequency_amount;
	}


	public void setFrequency_amount(int frequency_amount) {
		this.frequency_amount = frequency_amount;
	}


	public String getFrequency_interval() {
		return frequency_interval;
	}


	public void setFrequency_interval(String frequency_interval) {
		this.frequency_interval = frequency_interval;
	}


	public String getFrequency_type() {
		return frequency_type;
	}


	public void setFrequency_type(String frequency_type) {
		this.frequency_type = frequency_type;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public float getPrm_pub_ceiling() {
		return prm_pub_ceiling;
	}


	public void setPrm_pub_ceiling(float prm_pub_ceiling) {
		this.prm_pub_ceiling = prm_pub_ceiling;
	}


	public float getPrm_pub_markup() {
		return prm_pub_markup;
	}


	public void setPrm_pub_markup(float prm_pub_markup) {
		this.prm_pub_markup = prm_pub_markup;
	}


	public float getSell_price() {
		return sell_price;
	}


	public void setSell_price(float sell_price) {
		this.sell_price = sell_price;
	}


	public String getSell_price_type() {
		return sell_price_type;
	}


	public void setSell_price_type(String sell_price_type) {
		this.sell_price_type = sell_price_type;
	}


	public int getSite_placement_id() {
		return site_placement_id;
	}


	public void setSite_placement_id(int site_placement_id) {
		this.site_placement_id = site_placement_id;
	}


	public Date getStart_date() {
		return start_date;
	}


	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}


	public Date getUpdated_on() {
		return updated_on;
	}


	public void setUpdated_on(Date updated_on) {
		this.updated_on = updated_on;
	}


	public int getVersion() {
		return version;
	}


	public void setVersion(int version) {
		this.version = version;
	}


	public String getVolume_unit() {
		return volume_unit;
	}


	public void setVolume_unit(String volume_unit) {
		this.volume_unit = volume_unit;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	@Override
	public String getEntityname() {
		return entityName;
	}

}
