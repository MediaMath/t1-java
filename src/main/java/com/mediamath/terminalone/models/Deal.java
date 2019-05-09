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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.ws.rs.core.Form;

import com.google.gson.Gson;
import com.mediamath.terminalone.utils.Utility;

public class Deal implements T1Entity {

	private static final String entityName = "Deal";

	public enum dealSources {
		USER, INTERNAL
	}

	public enum mediaTypes {
		DISPLAY, VIDEO
	}

	public enum priceMethods {
		CPM
	}

	public enum priceTypes {
		FIXED, FLOOR
	}

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
	private Currency price;
	private priceMethods price_method;
	private priceTypes price_type;
	private int publisher_id;
	private Date start_datetime;
	private boolean status;
	private int supply_source_id;
	private Date updated_on;
	private int version;
	private String zone_name;
	private Owner owner;
	private Permissions permissions;

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

	public Currency getPrice() {
		return price;
	}

	public void setPrice(Currency price) {
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

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public Permissions getPermissions() {
		return permissions;
	}

	public void setPermissions(Permissions permissions) {
		this.permissions = permissions;
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

	@Override
	public String getEntityname() {
		return entityName;
	}

	@Override
	public Form getForm() {
		final String YYYY_MM_DDTHH_MM_SS_Z = "yyyy-MM-dd'T'HH:mm:ssZ";
		final SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DDTHH_MM_SS_Z);
		Gson gson = new Gson();
		Form dealForm = new Form();

		if (this.getName() != null) {
			dealForm.param("name", this.getName());
		}

		if (this.getPrice() != null && this.getPrice().getValue().floatValue() > 0) {
			dealForm.param("price", String.valueOf(this.getPrice().getValue()));
		}

		if (this.getAdvertiserId() > 0) {
			dealForm.param("advertiser_id", String.valueOf(this.getAdvertiserId()));
		}

		if (this.getCurrencyCode() != null) {
			dealForm.param("currency_code", this.getCurrencyCode());
		}

		if (this.getDealIdentifier() != null) {
			dealForm.param("deal_identifier", this.getDealIdentifier());
		}

		if (this.getDealSource() != null) {
			dealForm.param("deal_source", String.valueOf(this.getDealSource()));
		}

		if (this.getDescription() != null) {
			dealForm.param("description", this.getDescription());
		}

		if (this.getMediaType() != null) {
			dealForm.param("media_type", String.valueOf(this.getMediaType()));
		}

		if (this.getOwner() != null) {
			Map<String, Object> oMap = new HashMap<String, Object>();
			oMap.put("type", this.getOwner().getType());
			oMap.put("id", this.getOwner().getId());
			dealForm.param("owner", gson.toJson(oMap));
		}

		if (this.getPermissions() != null) {
			Map<String, Object> pMap = new HashMap<String, Object>();
			pMap.put("advertiser_ids", this.getPermissions().getAdvertiserIds());
			pMap.put("agency_ids", this.getPermissions().getAgencyIds());
			pMap.put("organization_ids", this.getPermissions().getOrganizationIds());
			pMap.put("all_organizations", this.getPermissions().isAllOrganizations());

			dealForm.param("permissions", gson.toJson(pMap));
		}

		if (this.getPriceMethod() != null) {
			dealForm.param("price_method", String.valueOf(this.getPriceMethod()));
		}

		if (this.getPriceType() != null) {
			dealForm.param("price_type", String.valueOf(this.getPriceType()));
		}

		if (this.getPublisherId() > 0) {
			dealForm.param("publisher_id", String.valueOf(this.getPublisherId()));
		}

		if (this.getSupplySourceId() > 0) {
			dealForm.param("supply_source_id", String.valueOf(this.getSupplySourceId()));
		}

		if (this.getVersion() > 0) {
			dealForm.param("supply_source_id", String.valueOf(this.getVersion()));
		}

		if (this.getZoneName() != null) {
			dealForm.param("zone_name", this.getZoneName());
		}

		dealForm.param("status", Utility.getOnOrOff(this.isStatus()));
		dealForm.param("partner_sourced", Utility.getOneOrZero(this.isPartnerSourced()));

		if (this.getEndDatetime() != null) {
			sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
			String endDate = sdf.format(this.getEndDatetime());
			dealForm.param("end_datetime", endDate);
		}

		if (this.getStartDatetime() != null) {
			sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
			String startDate = sdf.format(this.getStartDatetime());
			dealForm.param("start_datetime", startDate);
		}

		return Utility.getFilteredForm(dealForm, "deal");
	}

	@Override
	public String getUri() {
		StringBuilder uri = new StringBuilder();

		if (this.getId() > 0) {
			uri.append("/");
			uri.append(this.getId());
		}

		return uri.toString();
	}

}
