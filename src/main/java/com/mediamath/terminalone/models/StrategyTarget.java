package com.mediamath.terminalone.models;

import org.javers.core.metamodel.annotation.DiffIgnore;
import org.javers.core.metamodel.annotation.Id;
import org.javers.core.metamodel.annotation.ShallowReference;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StrategyTarget {

	private int id;
	private String name;
	private boolean exclude;
	private int strategy_id;
	private TargetDimension target_dimension;
	private boolean active;
	private String target_op;
	private int target_dimension_id;
	@ShallowReference
	private Set<TargetValue> target_values = new HashSet<>();

	@DiffIgnore
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@DiffIgnore
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

	@DiffIgnore
	public int getStrategyId() {
		return strategy_id;
	}

	public void setStrategyId(int strategy_id) {
		this.strategy_id = strategy_id;
	}

	@DiffIgnore
	public TargetDimension getTargetDimension() {
		return target_dimension;
	}

	public void setTargetDimension(TargetDimension target_dimension) {
		this.target_dimension = target_dimension;
	}

	@DiffIgnore
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

	@DiffIgnore
	public int getTargetDimensionId() {
		return target_dimension_id;
	}

	public void setTargetDimensionId(int target_dimension_id) {
		this.target_dimension_id = target_dimension_id;
	}

	@ShallowReference
	public Set<TargetValue> getTargetValues() {
		return target_values;
	}

	public void setTargetValues(Set<TargetValue> target_values) {
		this.target_values = target_values;
	}

	@Id
	public String getDifferId() {
		String ids = getTargetValues().stream().map(x -> String.valueOf(x.getId())).sorted().collect(Collectors.joining( "," ) );
		return String.join("", String.valueOf(isExclude()), getTargetOp(), ids);
	}

}
