package com.mediamath.jterminalone.utils;

public class ConditionQuery {
	
	private String include;
	
	private String with;
	
	public ConditionQuery(String include) {
		this.include = include;
	}
	
	public ConditionQuery(String include, String with) {
		this.include = include;
		this.with = with;
	}

	public String getInclude() {
		return include;
	}

	public String getWith() {
		return with;
	}

}
