package com.mediamath.jterminalone;

import java.util.ArrayList;
import java.util.List;

public class QueryCriteria {
	
	String collection = null; 
	
	long entity = 0; 
	
	String child = null; 
	
	List<ConditionQuery> includeConditionList = new ArrayList<ConditionQuery>();
	
	public QueryCriteria(Builder builder) {
		
		collection = builder.collection;
		
		entity = builder.entity;
		
		child = builder.child;
		
		includeConditionList = builder.includeConditionList;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
	
		private String collection = null; 
		
		private long entity; 
		
		private String child = null; 
		
		List<ConditionQuery> includeConditionList = new ArrayList<ConditionQuery>();
		
		private Builder() {}
		
		public Builder setCollection(String value) {
			collection = value;
			return this;
		}
		
		public Builder setEntity(long value) {
			entity = value;
			return this;
		}
		
		public Builder setChild(String value) {
			child = value;
			return this;
		}
		
		public Builder setInclude(ConditionQuery value) {
			includeConditionList.add(value);
			return this;
		}
		
		public QueryCriteria build() {
			return new QueryCriteria(this);
		}
	}

}
