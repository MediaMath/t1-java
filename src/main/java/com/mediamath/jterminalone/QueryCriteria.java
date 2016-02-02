package com.mediamath.jterminalone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryCriteria {
	
	String collection = null; 
	
	long entity = 0; 
	
	String child = null; 
	
	List<ConditionQuery> includeConditionList = new ArrayList<ConditionQuery>();
	
	String sortBy = null;
	
	int pageLimit = 100;
	
	int pageOffset = 0;
	
	long parent = 0;
	
	String full = null;
	
	Map<String, Long> limit = new HashMap<String, Long>();
	
	public QueryCriteria(Builder builder) {
		
		collection = builder.collection;
		
		entity = builder.entity;
		
		child = builder.child;
		
		includeConditionList = builder.includeConditionList;
		
		sortBy = builder.sortby;
		
		if(builder.pageLimit > 0){
			pageLimit = builder.pageLimit;
		}
		
		parent = builder.parent;
		
		full = builder.full;
		
		pageOffset = builder.pageOffset;
		
		limit = builder.limit;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
	
		private String collection = null; 
		
		private long entity; 
		
		private String child = null; 
		
		List<ConditionQuery> includeConditionList = new ArrayList<ConditionQuery>();
		
		private String sortby = null;
		
		private int pageLimit;
		
		private long parent;
		
		private String full = null;
		
		private int pageOffset;
		
		private Map<String, Long> limit = new HashMap<String, Long>();
		
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
		
		public Builder setSortby(String sortby) {
			this.sortby = sortby;
			return this;
		}

		public Builder setPageLimit(int pageLimit) {
			this.pageLimit = pageLimit;
			return this;
		}

		public Builder setParent(long parent) {
			this.parent = parent;
			return this;
		}

		public Builder setFull(String full) {
			this.full = full;
			return this;
		}

		public Builder setPageOffset(int pageOffset) {
			this.pageOffset = pageOffset;
			return this;
		}

		public Builder setLimit(Map<String, Long> limit) {
			this.limit = limit;
			return this;
		}

		public QueryCriteria build() {
			return new QueryCriteria(this);
		}
	}

}
