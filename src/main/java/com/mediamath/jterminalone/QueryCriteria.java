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
	
	String query=null;
	
	String queryParam = null;
	
	String queryParamValueStr = null;

	boolean queryParamValueBoolean;
	
	Number queryParamValueNumber = null;
	
	List<Object> queryParamValueList = new ArrayList<Object>();
	
	String queryOperator = null;
	
	public QueryCriteria(Builder builder) {
		
		collection = builder.collection;
		
		entity = builder.entity;
		
		child = builder.child;
		
		includeConditionList = builder.includeConditionList;
		
		sortBy = builder.sortBy;
		
		if(builder.pageLimit > 0){
			pageLimit = builder.pageLimit;
		}
		
		parent = builder.parent;
		
		full = builder.full;
		
		pageOffset = builder.pageOffset;
		
		limit = builder.limit;
		
		query=builder.query;
		
		queryParam = builder.queryParam;
		
		queryParamValueStr = builder.queryParamValueStr;

		queryParamValueBoolean = builder.queryParamValueBoolean;
		
		queryParamValueNumber = builder.queryParamValueNumber;
		
		queryParamValueList = builder.queryParamValueList;
		
		queryOperator = builder.queryOperator;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static Builder builder(QueryCriteria oldQueryCriteria) {
		return new Builder(oldQueryCriteria);
	}
	
	public static class Builder {
	
		private String collection = null; 
		
		private long entity; 
		
		private String child = null; 
		
		List<ConditionQuery> includeConditionList = new ArrayList<ConditionQuery>();
		
		private String sortBy = null;
		
		private int pageLimit;
		
		private long parent;
		
		private String full = null;
		
		private int pageOffset;
		
		private Map<String, Long> limit = new HashMap<String, Long>();
		
		private String query=null;
		
		private String queryParam = null;
		
		private String queryParamValueStr = null;

		private boolean queryParamValueBoolean;
		
		private Number queryParamValueNumber=null;
		
		private List<Object> queryParamValueList = new ArrayList<Object>();
		
		private String queryOperator = null;
		
		private Builder() {}
		
		private Builder(QueryCriteria old) {
			
			collection = old.collection;
			
			entity = old.entity;
			
			child = old.child;
			
			includeConditionList = old.includeConditionList;
			
			sortBy = old.sortBy;
			
			if(old.pageLimit > 0){
				pageLimit = old.pageLimit;
			}
			
			parent = old.parent;
			
			full = old.full;
			
			pageOffset = old.pageOffset;
			
			limit = old.limit;
			
			query = old.query;
			
			queryParam = old.queryParam;
			
			queryParamValueStr = old.queryParamValueStr;

			queryParamValueBoolean = old.queryParamValueBoolean;
			
			queryParamValueNumber = old.queryParamValueNumber;
			
			queryParamValueList = old.queryParamValueList;
			
			queryOperator = old.queryOperator;
		}
		
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
		
		public Builder setSortBy(String sortBy) {
			this.sortBy = sortBy;
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
		
		public Builder setIncludeConditionList(List<ConditionQuery> includeConditionList) {
			this.includeConditionList = includeConditionList;
			return this;
		}

		public Builder setQuery(String query) {
			this.query = query;
			return this;
		}

		public Builder setQueryParam(String queryParam) {
			this.queryParam = queryParam;
			return this;
		}
		
		public Builder setQueryParamValueStr(String queryParamValueStr) {
			this.queryParamValueStr = queryParamValueStr;
			return this;
		}

		public Builder setQueryParamValueBoolean(boolean queryParamValueBoolean) {
			this.queryParamValueBoolean = queryParamValueBoolean;
			return this;
		}

		public Builder setQueryParamValueNumber(Number queryParamValueNumber) {
			this.queryParamValueNumber = queryParamValueNumber;
			return this;
		}

		public Builder setQueryParamValueList(List<Object> queryParamValueList) {
			this.queryParamValueList = queryParamValueList;
			return this;
		}

		public Builder setQueryOperator(String queryOperator) {
			this.queryOperator = queryOperator;
			return this;
		}

		public QueryCriteria build() {
			return new QueryCriteria(this);
		}
	}

}
