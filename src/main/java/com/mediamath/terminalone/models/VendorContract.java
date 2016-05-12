package com.mediamath.terminalone.models;

import java.util.Date;

public class VendorContract implements T1Entity {
	
	private static final String entityName = "VendorContract";

	private int campaign_id;
	private Date created_on;
    private int id;
    private float price;
    private String rate_card_type;
    private Date updated_on;
    private boolean use_mm_contract;
    private int vendor_id;
    private int version;
	public int getCampaign_id() {
		return campaign_id;
	}
	public void setCampaign_id(int campaign_id) {
		this.campaign_id = campaign_id;
	}
	public Date getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
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
	public boolean isUse_mm_contract() {
		return use_mm_contract;
	}
	public void setUse_mm_contract(boolean use_mm_contract) {
		this.use_mm_contract = use_mm_contract;
	}
	public int getVendor_id() {
		return vendor_id;
	}
	public void setVendor_id(int vendor_id) {
		this.vendor_id = vendor_id;
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
	
    
}
