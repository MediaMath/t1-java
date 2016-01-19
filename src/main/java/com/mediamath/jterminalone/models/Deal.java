package com.mediamath.jterminalone.models;

import java.util.Date;

public class Deal implements T1Entity {

	public enum deal_sources {
		USER, INTERNAL
	};

	public enum media_types {
		DISPLAY, VIDEO
	};

	public enum price_methods {
		CPM
	};

	public enum price_types {
		FIXED, FLOOR
	};

	private int advertiser_id;
	private Date created_on;
	private String currency_code;
	private String deal_identifier;
	private deal_sources deal_source;
	private String description;
	private Date end_datetime;
	private int id;
	private media_types media_type;
	private String name;
	private boolean partner_sourced;
	private float price;
	private price_methods price_method;
	private price_types price_type;
	private int publisher_id;
	private Date start_datetime;
	private boolean status;
	private int supply_source_id;
	private Date updated_on;
	private int version;
	private String zone_name;

	public int getAdvertiser_id() {
		return advertiser_id;
	}

	public void setAdvertiser_id(int advertiser_id) {
		this.advertiser_id = advertiser_id;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public String getCurrency_code() {
		return currency_code;
	}

	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}

	public String getDeal_identifier() {
		return deal_identifier;
	}

	public void setDeal_identifier(String deal_identifier) {
		this.deal_identifier = deal_identifier;
	}

	public deal_sources getDeal_source() {
		return deal_source;
	}

	public void setDeal_source(deal_sources deal_source) {
		this.deal_source = deal_source;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEnd_datetime() {
		return end_datetime;
	}

	public void setEnd_datetime(Date end_datetime) {
		this.end_datetime = end_datetime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public media_types getMedia_type() {
		return media_type;
	}

	public void setMedia_type(media_types media_type) {
		this.media_type = media_type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isPartner_sourced() {
		return partner_sourced;
	}

	public void setPartner_sourced(boolean partner_sourced) {
		this.partner_sourced = partner_sourced;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public price_methods getPrice_method() {
		return price_method;
	}

	public void setPrice_method(price_methods price_method) {
		this.price_method = price_method;
	}

	public price_types getPrice_type() {
		return price_type;
	}

	public void setPrice_type(price_types price_type) {
		this.price_type = price_type;
	}

	public int getPublisher_id() {
		return publisher_id;
	}

	public void setPublisher_id(int publisher_id) {
		this.publisher_id = publisher_id;
	}

	public Date getStart_datetime() {
		return start_datetime;
	}

	public void setStart_datetime(Date start_datetime) {
		this.start_datetime = start_datetime;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getSupply_source_id() {
		return supply_source_id;
	}

	public void setSupply_source_id(int supply_source_id) {
		this.supply_source_id = supply_source_id;
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

	public String getZone_name() {
		return zone_name;
	}

	public void setZone_name(String zone_name) {
		this.zone_name = zone_name;
	}

}
