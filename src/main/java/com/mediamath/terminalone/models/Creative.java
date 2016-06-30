package com.mediamath.terminalone.models;

import java.util.Date;

public class Creative implements T1Entity {

	private static final String entityName = "Creative";

	private int atomic_creative_id;
	private Date created_on;
	private int id;
	private Date last_modified;
	private String tag;
	private String tag_type;
	private int version;

	public int getAtomic_creative_id() {
		return atomic_creative_id;
	}

	public void setAtomic_creative_id(int atomic_creative_id) {
		this.atomic_creative_id = atomic_creative_id;
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

	public Date getLast_modified() {
		return last_modified;
	}

	public void setLast_modified(Date last_modified) {
		this.last_modified = last_modified;
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
