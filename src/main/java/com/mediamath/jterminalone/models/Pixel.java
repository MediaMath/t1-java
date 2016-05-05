package com.mediamath.jterminalone.models;

import java.util.Date;

public class Pixel implements T1Entity {
	
	private static final String entityName = "PixelBundle";

	public enum pixel_types {
		creative, event, data, segment
	};

	public enum pricing {
		CPM, CPTS
	};

	public enum rmx_conv_types {
		one, variable
	};

	public enum tag_types {
		dfa, uat, image, iframe, js
	};
	
	public enum roi_fields{
		S1,S2,V1,V2
	}

	private int advertiser_id;
	private int agency_id;
	private float cost_cpm;
	private float cost_cpts;
	private float cost_pct_cpm;
	private Date created_on;
	private roi_fields currency;
	private String currency_fixed;
	private boolean eligible;
	private String external_identifier;
	private int id;
	private String keywords;
	private String name;
	private pixel_types pixel_type;
	private pricing pricing;
	private int provider_id;
	private roi_fields revenue;
	private int rmx_conversion_minutes;
	private rmx_conv_types rmx_conversion_type;
	private boolean rmx_friendly;
	private boolean rmx_merit;
	private int rmx_pc_window_minutes;
	private int rmx_pv_window_minutes;
	private String segment_op;
	private boolean status;
	private tag_types tag_type;
	private String tags;
	private String type;
	private Date updated_on;
	private int version;

	public int getAdvertiser_id() {
		return advertiser_id;
	}

	public void setAdvertiser_id(int advertiser_id) {
		this.advertiser_id = advertiser_id;
	}

	public int getAgency_id() {
		return agency_id;
	}

	public void setAgency_id(int agency_id) {
		this.agency_id = agency_id;
	}

	public float getCost_cpm() {
		return cost_cpm;
	}

	public void setCost_cpm(float cost_cpm) {
		this.cost_cpm = cost_cpm;
	}

	public float getCost_cpts() {
		return cost_cpts;
	}

	public void setCost_cpts(float cost_cpts) {
		this.cost_cpts = cost_cpts;
	}

	public float getCost_pct_cpm() {
		return cost_pct_cpm;
	}

	public void setCost_pct_cpm(float cost_pct_cpm) {
		this.cost_pct_cpm = cost_pct_cpm;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public boolean isEligible() {
		return eligible;
	}

	public void setEligible(boolean eligible) {
		this.eligible = eligible;
	}

	public String getExternal_identifier() {
		return external_identifier;
	}

	public void setExternal_identifier(String external_identifier) {
		this.external_identifier = external_identifier;
	}

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

	public pixel_types getPixel_type() {
		return pixel_type;
	}

	public void setPixel_type(pixel_types pixel_type) {
		this.pixel_type = pixel_type;
	}

	public pricing getPricing() {
		return pricing;
	}

	public void setPricing(pricing pricing) {
		this.pricing = pricing;
	}

	public int getProvider_id() {
		return provider_id;
	}

	public void setProvider_id(int provider_id) {
		this.provider_id = provider_id;
	}

	public int getRmx_conversion_minutes() {
		return rmx_conversion_minutes;
	}

	public void setRmx_conversion_minutes(int rmx_conversion_minutes) {
		this.rmx_conversion_minutes = rmx_conversion_minutes;
	}

	public rmx_conv_types getRmx_conversion_type() {
		return rmx_conversion_type;
	}

	public void setRmx_conversion_type(rmx_conv_types rmx_conversion_type) {
		this.rmx_conversion_type = rmx_conversion_type;
	}

	public boolean isRmx_friendly() {
		return rmx_friendly;
	}

	public void setRmx_friendly(boolean rmx_friendly) {
		this.rmx_friendly = rmx_friendly;
	}

	public boolean isRmx_merit() {
		return rmx_merit;
	}

	public void setRmx_merit(boolean rmx_merit) {
		this.rmx_merit = rmx_merit;
	}

	public int getRmx_pc_window_minutes() {
		return rmx_pc_window_minutes;
	}

	public void setRmx_pc_window_minutes(int rmx_pc_window_minutes) {
		this.rmx_pc_window_minutes = rmx_pc_window_minutes;
	}

	public int getRmx_pv_window_minutes() {
		return rmx_pv_window_minutes;
	}

	public void setRmx_pv_window_minutes(int rmx_pv_window_minutes) {
		this.rmx_pv_window_minutes = rmx_pv_window_minutes;
	}

	public String getSegment_op() {
		return segment_op;
	}

	public void setSegment_op(String segment_op) {
		this.segment_op = segment_op;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public tag_types getTag_type() {
		return tag_type;
	}

	public void setTag_type(tag_types tag_type) {
		this.tag_type = tag_type;
	}

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

	public String getEntityname() {
		return entityName;
	}

	public roi_fields getCurrency() {
		return currency;
	}

	public void setCurrency(roi_fields currency) {
		this.currency = currency;
	}

	public String getCurrency_fixed() {
		return currency_fixed;
	}

	public void setCurrency_fixed(String currency_fixed) {
		this.currency_fixed = currency_fixed;
	}

	public roi_fields getRevenue() {
		return revenue;
	}

	public void setRevenue(roi_fields revenue) {
		this.revenue = revenue;
	}

	
}
