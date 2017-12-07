package com.mediamath.terminalone.models;

import java.util.ArrayList;
import java.util.List;

public class StrategyTarget {

	private int id;
	private String name;
	private boolean exclude;
	private int strategy_id;
	private TargetDimension target_dimension;
	private boolean active;
	private String target_op;
	private int target_dimension_id;
	private List<TargetValue> target_values = new ArrayList<>();

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

	public boolean isExclude() {
		return exclude;
	}

	public void setExclude(boolean exclude) {
		this.exclude = exclude;
	}

	public int getStrategyId() {
		return strategy_id;
	}

	public void setStrategyId(int strategy_id) {
		this.strategy_id = strategy_id;
	}

	public TargetDimension getTargetDimension() {
		return target_dimension;
	}

	public void setTargetDimension(TargetDimension target_dimension) {
		this.target_dimension = target_dimension;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getTargetOp() {
		return target_op;
	}

	public void setTargetOp(String target_op) {
		this.target_op = target_op;
	}

	public int getTargetDimensionId() {
		return target_dimension_id;
	}

	public void setTargetDimensionId(int target_dimension_id) {
		this.target_dimension_id = target_dimension_id;
	}

	public List<TargetValue> getTargetValues() {
		return target_values;
	}

	public void setTargetValues(List<TargetValue> target_values) {
		this.target_values = target_values;
	}

}
