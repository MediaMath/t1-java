package com.mediamath.terminalone.models;

import java.util.Date;

public class Vendor implements T1Entity {
	
	private static final String entityName = "Vendor";

	public enum vendorTypes {
		AD_SERVER, AD_VERIFICATION, CONTEXTUAL, DATA, DSP, DYNAMIC_CREATIVE, NETWORK, OBA_COMPLIANCE, OTHER, PIXEL_TRACKING, RICH_MEDIA, SURVEY
	};

	private boolean adx_approved;
	private boolean adx_declaration_required;
	private boolean adx_ssl_approved;
	private String adx_vendor_identifier;
	private boolean adx_video_approved;
	private boolean adx_video_ssl_approved;
	private Date created_on;
	private String description;
	private int id;
	private boolean is_eligible;
	private boolean mm_contract_available;
	private String name;
	private float rate_card_price;
	private String rate_card_type;
	private Date updated_on;
	private vendorTypes vendor_type;
	private int version;
	private float wholesale_price;

	public boolean isAdx_approved() {
		return adx_approved;
	}

	public void setAdx_approved(boolean adx_approved) {
		this.adx_approved = adx_approved;
	}

	public boolean isAdx_declaration_required() {
		return adx_declaration_required;
	}

	public void setAdx_declaration_required(boolean adx_declaration_required) {
		this.adx_declaration_required = adx_declaration_required;
	}

	public boolean isAdx_ssl_approved() {
		return adx_ssl_approved;
	}

	public void setAdx_ssl_approved(boolean adx_ssl_approved) {
		this.adx_ssl_approved = adx_ssl_approved;
	}

	public String getAdx_vendor_identifier() {
		return adx_vendor_identifier;
	}

	public void setAdx_vendor_identifier(String adx_vendor_identifier) {
		this.adx_vendor_identifier = adx_vendor_identifier;
	}

	public boolean isAdx_video_approved() {
		return adx_video_approved;
	}

	public void setAdx_video_approved(boolean adx_video_approved) {
		this.adx_video_approved = adx_video_approved;
	}

	public boolean isAdx_video_ssl_approved() {
		return adx_video_ssl_approved;
	}

	public void setAdx_video_ssl_approved(boolean adx_video_ssl_approved) {
		this.adx_video_ssl_approved = adx_video_ssl_approved;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isIs_eligible() {
		return is_eligible;
	}

	public void setIs_eligible(boolean is_eligible) {
		this.is_eligible = is_eligible;
	}

	public boolean isMm_contract_available() {
		return mm_contract_available;
	}

	public void setMm_contract_available(boolean mm_contract_available) {
		this.mm_contract_available = mm_contract_available;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getRate_card_price() {
		return rate_card_price;
	}

	public void setRate_card_price(float rate_card_price) {
		this.rate_card_price = rate_card_price;
	}

	public String getRate_card_type() {
		return rate_card_type;
	}

	public void setRate_card_type(String rate_card_type) {
		this.rate_card_type = rate_card_type;
	}

	public Date getUpdated_on() {
		return updated_on;
	}

	public void setUpdated_on(Date updated_on) {
		this.updated_on = updated_on;
	}

	public vendorTypes getVendor_type() {
		return vendor_type;
	}

	public void setVendor_type(vendorTypes vendor_type) {
		this.vendor_type = vendor_type;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public float getWholesale_price() {
		return wholesale_price;
	}

	public void setWholesale_price(float wholesale_price) {
		this.wholesale_price = wholesale_price;
	}

	public String getEntityname() {
		return entityName;
	}

}
