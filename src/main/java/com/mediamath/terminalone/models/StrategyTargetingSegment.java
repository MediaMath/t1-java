package com.mediamath.terminalone.models;

import java.util.Date;

public class StrategyTargetingSegment implements T1Entity {

	private static final String entityName = "StrategyTargetingSegment";
	
	private Date created_on;
	private String group_identifier;
	private int id;
	private String  operator;
	private String restriction;
	private int strategy_id;
	private int targeting_segment_id;
	private String type;
	private Date updated_on;
	private float user_cpm;
	private int version;
	
	private Strategy strategy;
	
	
	public Date getCreated_on() {
		return created_on;
	}



	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}



	public String getGroup_identifier() {
		return group_identifier;
	}



	public void setGroup_identifier(String group_identifier) {
		this.group_identifier = group_identifier;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getOperator() {
		return operator;
	}



	public void setOperator(String operator) {
		this.operator = operator;
	}



	public String getRestriction() {
		return restriction;
	}



	public void setRestriction(String restriction) {
		this.restriction = restriction;
	}



	public int getStrategy_id() {
		return strategy_id;
	}



	public void setStrategy_id(int strategy_id) {
		this.strategy_id = strategy_id;
	}



	public int getTargeting_segment_id() {
		return targeting_segment_id;
	}



	public void setTargeting_segment_id(int targeting_segment_id) {
		this.targeting_segment_id = targeting_segment_id;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public Date getUpdated_on() {
		return updated_on;
	}



	public void setUpdated_on(Date updated_on) {
		this.updated_on = updated_on;
	}



	public float getUser_cpm() {
		return user_cpm;
	}



	public void setUser_cpm(float user_cpm) {
		this.user_cpm = user_cpm;
	}



	public int getVersion() {
		return version;
	}



	public void setVersion(int version) {
		this.version = version;
	}

	

	public Strategy getStrategy() {
		return strategy;
	}



	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}



	@Override
	public String getEntityname() {
		return entityName;
	}

}
