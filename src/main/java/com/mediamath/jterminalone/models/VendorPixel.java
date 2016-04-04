package com.mediamath.jterminalone.models;

import java.util.Date;

public class VendorPixel implements T1Entity {
	
	private static final String entityName = "VendorPixel";

	private Date created_on;
	private int creative_id;
    private int id;
    private String set_by;
    private String tag;
    private String tag_type;
    private Date updated_on;
    private int version;
	public Date getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
	public int getCreative_id() {
		return creative_id;
	}
	public void setCreative_id(int creative_id) {
		this.creative_id = creative_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSet_by() {
		return set_by;
	}
	public void setSet_by(String set_by) {
		this.set_by = set_by;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getTag_type() {
		return tag_type;
	}
	public void setTag_type(String tag_type) {
		this.tag_type = tag_type;
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
