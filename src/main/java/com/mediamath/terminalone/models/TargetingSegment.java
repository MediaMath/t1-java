package com.mediamath.terminalone.models;

import java.util.Date;

import javax.ws.rs.core.Form;

import com.mediamath.terminalone.exceptions.ClientException;

public class TargetingSegment implements T1Entity {
	
	private static final String entityName = "TargetingSegment";

	  private int targeting_vendor_id;
	  private boolean buyable;
	  private int child_count;
	  private String code;
	  private Date created_on;
	  private String full_path;
	  private int id;
	  private String name;
	  private int parent_targeting_segment_id;
	  private Currency retail_cpm;
	  private Currency wholesale_cpm;
	  private String tag;
	  private int objective_id;
	  private String external_code;
	  private Date updated_on;
	  private int version;
	
	

	public int getTargeting_vendor_id() {
		return targeting_vendor_id;
	}

	public void setTargeting_vendor_id(int targeting_vendor_id) {
		this.targeting_vendor_id = targeting_vendor_id;
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

	public int getParent_targeting_segment_id() {
		return parent_targeting_segment_id;
	}

	public void setParent_targeting_segment_id(int parent_targeting_segment_id) {
		this.parent_targeting_segment_id = parent_targeting_segment_id;
	}

	public Currency getRetail_cpm() {
		return retail_cpm;
	}

	public void setRetail_cpm(Currency retail_cpm) {
		this.retail_cpm = retail_cpm;
	}
	
	public void setRetailCpm(float retail_cpm) {
		Currency curr = new Currency();
		curr.setValue(retail_cpm);
		this.retail_cpm = curr;
	}

	public Currency getWholesale_cpm() {
		return wholesale_cpm;
	}

	public void setWholesale_cpm(Currency wholesale_cpm) {
		this.wholesale_cpm = wholesale_cpm;
	}
	
	public void setWholesaleCpm(float wholesale_cpm) {
		Currency curr = new Currency();
		curr.setValue(wholesale_cpm);
		this.wholesale_cpm = curr;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getObjective_id() {
		return objective_id;
	}

	public void setObjective_id(int objective_id) {
		this.objective_id = objective_id;
	}

	public String getExternal_code() {
		return external_code;
	}

	public void setExternal_code(String external_code) {
		this.external_code = external_code;
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

	@Override
	public String getEntityname() {
		 return entityName;
	}

	@Override
	public Form getForm() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUri() throws ClientException {
		StringBuilder uri = new StringBuilder();

	    if (this.getId() > 0) {
	      uri.append("/");
	      uri.append(this.getId());
	    }

	    return uri.toString();
	}

}
