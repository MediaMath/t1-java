package com.mediamath.terminalone.models;

public class StrategySupplySource implements T1Entity {
	
	private static final String entityName = "StrategySupplySource";

	private int id;
	private int strategy_id;
	private int supply_source_id;
	private int version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStrategy_id() {
		return strategy_id;
	}

	public void setStrategy_id(int strategy_id) {
		this.strategy_id = strategy_id;
	}

	public int getSupply_source_id() {
		return supply_source_id;
	}

	public void setSupply_source_id(int supply_source_id) {
		this.supply_source_id = supply_source_id;
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
