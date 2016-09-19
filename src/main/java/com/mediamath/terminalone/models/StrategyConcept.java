package com.mediamath.terminalone.models;

import java.util.Date;

public class StrategyConcept implements T1Entity {
	
	private static final String entityName = "StrategyConcept";

	private int concept_id;
	private Date created_on;
	private int id;
	private boolean status;
	private int strategy_id;
	private Date updated_on;
	private int version;

	private Concept concept;
	private Strategy strategy;
	
	public int getConcept_id() {
		return concept_id;
	}

	public void setConcept_id(int concept_id) {
		this.concept_id = concept_id;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
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
	

	public Concept getConcept() {
		return concept;
	}

	public void setConcept(Concept concept) {
		this.concept = concept;
	}

	public Strategy getStrategy() {
		return strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	public String getEntityname() {
		return entityName;
	}

}
