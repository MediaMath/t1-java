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
	List<TargetValue> target_values = new ArrayList<TargetValue>();
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
	public int getStrategy_id() {
		return strategy_id;
	}
	public void setStrategy_id(int strategy_id) {
		this.strategy_id = strategy_id;
	}
	public TargetDimension getTarget_dimension() {
		return target_dimension;
	}
	public void setTarget_dimension(TargetDimension target_dimension) {
		this.target_dimension = target_dimension;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getTarget_op() {
		return target_op;
	}
	public void setTarget_op(String target_op) {
		this.target_op = target_op;
	}
	public int getTarget_dimension_id() {
		return target_dimension_id;
	}
	public void setTarget_dimension_id(int target_dimension_id) {
		this.target_dimension_id = target_dimension_id;
	}
	public List<TargetValue> getTarget_values() {
		return target_values;
	}
	public void setTarget_values(List<TargetValue> target_values) {
		this.target_values = target_values;
	}
	
}
