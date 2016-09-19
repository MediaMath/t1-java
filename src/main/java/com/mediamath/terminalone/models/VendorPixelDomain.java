package com.mediamath.terminalone.models;

import java.util.Date;

public class VendorPixelDomain implements T1Entity {
	
	private static final String entityName = "VendorPixelDomain";

	private Date created_on;
    private String domain;
    private int id;
    private int vendor_domain_id;
    private int vendor_pixel_id;
    private int version;
    
    private VendorDomain vendor_domain;
    private VendorPixel vendor_pixel;
    
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
	public int getVendor_domain_id() {
		return vendor_domain_id;
	}
	public void setVendor_domain_id(int vendor_domain_id) {
		this.vendor_domain_id = vendor_domain_id;
	}
	public int getVendor_pixel_id() {
		return vendor_pixel_id;
	}
	public void setVendor_pixel_id(int vendor_pixel_id) {
		this.vendor_pixel_id = vendor_pixel_id;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
	public VendorDomain getVendor_domain() {
		return vendor_domain;
	}
	public void setVendor_domain(VendorDomain vendor_domain) {
		this.vendor_domain = vendor_domain;
	}
	public VendorPixel getVendor_pixel() {
		return vendor_pixel;
	}
	public void setVendor_pixel(VendorPixel vendor_pixel) {
		this.vendor_pixel = vendor_pixel;
	}
	public String getEntityname() {
		return entityName;
	}
	
    
}
