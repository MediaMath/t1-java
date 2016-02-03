package com.mediamath.jterminalone.models;

import java.util.Date;

public class StrategyDomain implements T1Entity {

	public enum restrictions {
		INCLUDE, EXCLUDE
	};

	private Date created_at;
	private String domain;
	private int id;
	private restrictions restriction;
	private int strategy_id;
	private Date updated_on;
	private int version;

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
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

	public restrictions getRestriction() {
		return restriction;
	}

	public void setRestriction(restrictions restriction) {
		this.restriction = restriction;
	}

	public int getStrategy_id() {
		return strategy_id;
	}

	public void setStrategy_id(int strategy_id) {
		this.strategy_id = strategy_id;
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

}
