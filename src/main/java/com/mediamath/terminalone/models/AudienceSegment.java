package com.mediamath.terminalone.models;

import java.util.Date;

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
	public int getAudience_vendor_id() {
		return audience_vendor_id;
	}
	public void setAudience_vendor_id(int audience_vendor_id) {
		this.audience_vendor_id = audience_vendor_id;
	}
	public boolean isBuyable() {
		return buyable;
	}
	public void setBuyable(boolean buyable) {
		this.buyable = buyable;
	}
	public int getChild_count() {
		return child_count;
	}
	public void setChild_count(int child_count) {
		this.child_count = child_count;
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
	public String getFull_path() {
		return full_path;
	}
	public void setFull_path(String full_path) {
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
	public int getParent_audience_segment_id() {
		return parent_audience_segment_id;
	}
	public void setParent_audience_segment_id(int parent_audience_segment_id) {
		this.parent_audience_segment_id = parent_audience_segment_id;
	}
	public float getRetail_cpm() {
		return retail_cpm;
	}
	public void setRetail_cpm(float retail_cpm) {
		this.retail_cpm = retail_cpm;
	}
	public float getWholesale_cpm() {
		return wholesale_cpm;
	}
	public void setWholesale_cpm(float wholesale_cpm) {
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
    
    
}
