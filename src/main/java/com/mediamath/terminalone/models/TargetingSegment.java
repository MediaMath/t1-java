package com.mediamath.terminalone.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mediamath.terminalone.exceptions.ClientException;
import org.javers.core.metamodel.annotation.DiffIgnore;

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
	private List<Currency> retail_cpm = new ArrayList<Currency>();;
	private List<Currency> wholesale_cpm = new ArrayList<Currency>();
	private String tag;
	private int objective_id;
	private String external_code;
	private Date updated_on;
	private int version;

	public int getTargetingVendorId() {
		return targeting_vendor_id;
	}

	public void setTargetingVendorId(int targeting_vendor_id) {
		this.targeting_vendor_id = targeting_vendor_id;
	}

	public boolean isBuyable() {
		return buyable;
	}

	public void setBuyable(boolean buyable) {
		this.buyable = buyable;
	}

	public int getChildCount() {
		return child_count;
	}

	public void setChildCount(int child_count) {
		this.child_count = child_count;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreatedOn() {
		return created_on;
	}

	public void setCreatedOn(Date created_on) {
		this.created_on = created_on;
	}

	public String getFullPath() {
		return full_path;
	}

	public void setFullPath(String full_path) {
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

	public int getParentTargetingSegmentId() {
		return parent_targeting_segment_id;
	}

	public void setParentTargetingSegmentId(int parent_targeting_segment_id) {
		this.parent_targeting_segment_id = parent_targeting_segment_id;
	}

	public List<Currency> getRetailCpm() {
		return retail_cpm;
	}

	public void setRetailCpm(List<Currency> retail_cpm) {
		this.retail_cpm = retail_cpm;
	}

	public void setRetailCpm(BigDecimal retail_cpm) {
		Currency curr = new Currency();
		curr.setValue(retail_cpm);
		this.retail_cpm.add(curr);
	}

	public List<Currency> getWholesaleCpm() {
		return wholesale_cpm;
	}

	public void setWholesaleCpm(List<Currency> wholesale_cpm) {
		this.wholesale_cpm = wholesale_cpm;
	}

	public void setWholesaleCpm(BigDecimal wholesale_cpm) {
		Currency curr = new Currency();
		curr.setValue(wholesale_cpm);
		this.wholesale_cpm.add(curr);
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getObjectiveId() {
		return objective_id;
	}

	public void setObjectiveId(int objective_id) {
		this.objective_id = objective_id;
	}

	public String getExternalCode() {
		return external_code;
	}

	public void setExternalCode(String external_code) {
		this.external_code = external_code;
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

	@Override
	public String getEntityname() {
		return entityName;
	}

	@Override
	@DiffIgnore
	@JsonIgnore
	public Form getForm() {
		return null;
	}

	@Override
	@DiffIgnore
	@JsonIgnore
	public String getUri() throws ClientException {
		StringBuilder uri = new StringBuilder();

		if (this.getId() > 0) {
			uri.append("/");
			uri.append(this.getId());
		}

		return uri.toString();
	}

}
