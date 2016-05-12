package com.mediamath.terminalone.models;

import java.util.Date;

public class ChildPixel implements T1Entity {
	
	private static final String entityName = "ChildPixel";

	private int bundle_id;
	private Date created_on;
	private boolean distributed;
	private int id;
	private String pixel_type;
	private int supply_source_id;
	private String tag;
	private Date updated_on;
	private int version;

	public int getBundle_id() {
		return bundle_id;
	}

	public void setBundle_id(int bundle_id) {
		this.bundle_id = bundle_id;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public boolean isDistributed() {
		return distributed;
	}

	public void setDistributed(boolean distributed) {
		this.distributed = distributed;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPixel_type() {
		return pixel_type;
	}

	public void setPixel_type(String pixel_type) {
		this.pixel_type = pixel_type;
	}

	public int getSupply_source_id() {
		return supply_source_id;
	}

	public void setSupply_source_id(int supply_source_id) {
		this.supply_source_id = supply_source_id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
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
