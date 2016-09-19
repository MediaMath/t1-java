package com.mediamath.terminalone.models;

import java.util.Date;

public class VendorDomain implements T1Entity {
	
	private static final String entityName = "VendorDomain";

	private boolean allow_subdomain_match;
	private Date created_on;
	private String domain;
	private int id;
	private Date updated_on;
	private int vendor_id;
	private int version;
	
	
	public boolean isAllow_subdomain_match() {
		return allow_subdomain_match;
	}
	public void setAllow_subdomain_match(boolean allow_subdomain_match) {
		this.allow_subdomain_match = allow_subdomain_match;
	}
	public Date getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getUpdated_on() {
		return updated_on;
	}
	public void setUpdated_on(Date updated_on) {
		this.updated_on = updated_on;
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
