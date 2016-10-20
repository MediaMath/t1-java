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
	
	
	public int getAdSlot() {
		return ad_slot;
	}


	public void setAdSlot(int ad_slot) {
		this.ad_slot = ad_slot;
	}


	public boolean isAllowRemnant() {
		return allow_remnant;
	}


	public void setAllowRemnant(boolean allow_remnant) {
		this.allow_remnant = allow_remnant;
	}


	public String getAuctionType() {
		return auction_type;
	}


	public void setAuctionType(String auction_type) {
		this.auction_type = auction_type;
	}


	public float getBudget() {
		return budget;
	}


	public void setBudget(float budget) {
		this.budget = budget;
	}


	public float getBuyPrice() {
		return buy_price;
	}


	public void setBuyPrice(float buy_price) {
		this.buy_price = buy_price;
	}


	public String getBuyPriceType() {
		return buy_price_type;
	}


	public void setBuyPriceType(String buy_price_type) {
		this.buy_price_type = buy_price_type;
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


	public Date getEndDate() {
		return end_date;
	}


	public void setEndDate(Date end_date) {
		this.end_date = end_date;
	}


	public float getEstVolume() {
		return est_volume;
	}


	public void setEstVolume(float est_volume) {
		this.est_volume = est_volume;
	}


	public int getFrequencyAmount() {
		return frequency_amount;
	}


	public void setFrequencyAmount(int frequency_amount) {
		this.frequency_amount = frequency_amount;
	}


	public String getFrequencyInterval() {
		return frequency_interval;
	}


	public void setFrequencyInterval(String frequency_interval) {
		this.frequency_interval = frequency_interval;
	}


	public String getFrequencyType() {
		return frequency_type;
	}


	public void setFrequencyType(String frequency_type) {
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


	public float getPrmPubCeiling() {
		return prm_pub_ceiling;
	}


	public void setPrmPubCeiling(float prm_pub_ceiling) {
		this.prm_pub_ceiling = prm_pub_ceiling;
	}


	public float getPrmPubMarkup() {
		return prm_pub_markup;
	}


	public void setPrmPubMarkup(float prm_pub_markup) {
		this.prm_pub_markup = prm_pub_markup;
	}


	public float getSellPrice() {
		return sell_price;
	}


	public void setSellPrice(float sell_price) {
		this.sell_price = sell_price;
	}


	public String getSellPriceType() {
		return sell_price_type;
	}


	public void setSellPriceType(String sell_price_type) {
		this.sell_price_type = sell_price_type;
	}


	public int getSitePlacementId() {
		return site_placement_id;
	}


	public void setSitePlacementId(int site_placement_id) {
		this.site_placement_id = site_placement_id;
	}


	public Date getStartDate() {
		return start_date;
	}


	public void setStartDate(Date start_date) {
		this.start_date = start_date;
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


	public String getVolumeUnit() {
		return volume_unit;
	}


	public void setVolumeUnit(String volume_unit) {
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
