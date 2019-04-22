/*******************************************************************************
 * Copyright 2016 MediaMath
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.mediamath.terminalone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mediamath.terminalone.utils.ConditionQuery;
import com.mediamath.terminalone.utils.FullParamValues;
import com.mediamath.terminalone.utils.QueryParamValues;

public class QueryCriteria {
	
	public enum CreativeType {
	    video, normal;
	}

	public String collection = null;

	public long entity = 0;

	public List<String> child = new ArrayList<>();

	public List<ConditionQuery> includeConditionList = new ArrayList<>();

	public String sortBy = null;

	public int pageLimit = 100;

	public int pageOffset = 0;

	public boolean getAll;

	public long parent = 0;

	public Map<String, Long> limit = new HashMap<>();

	public String query = null;

	public String queryParamName = null;

	public boolean downloadSiteList = false;

	public String queryOperator = null;

	public QueryParamValues queryParams = null;

	public Map<String,String> extraParams = new HashMap<>();

	public FullParamValues full = null;
	
	public CreativeType creativeType;

	/**
	 * Query Criteria builder object.
	 * 
	 * @param builder
	 * 
	 */
	public QueryCriteria(Builder builder) {

		collection = builder.collection;

		entity = builder.entity;

		child = builder.child;

		includeConditionList = builder.includeConditionList;

		sortBy = builder.sortBy;

		if (builder.pageLimit > 0) {
			pageLimit = builder.pageLimit;
		}

		parent = builder.parent;

		pageOffset = builder.pageOffset;

		getAll = builder.getAll;

		limit = builder.limit;

		extraParams = builder.extraParams;

		query = builder.query;

		queryParamName = builder.queryParamName;

		full = builder.full;

		queryParams = builder.queryParams;

		queryOperator = builder.queryOperator;

		downloadSiteList = builder.downloadSiteList;
		
		creativeType = builder.creativeType;
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

		public List<String> child = new ArrayList<>();

		private List<ConditionQuery> includeConditionList = new ArrayList<>();

		private String sortBy = null;

		private int pageLimit;

		private long parent;

		private int pageOffset;

		private boolean getAll;

		private Map<String, Long> limit = new HashMap<>();

		public Map<String,String> extraParams = new HashMap<>();

		private String query = null;

		private String queryParamName = null;

		private String queryOperator = null;

		private QueryParamValues queryParams = null;

		private FullParamValues full = null;

		private boolean downloadSiteList = false;
		
		private CreativeType creativeType;

		private Builder() {
		}

		private Builder(QueryCriteria old) {

			collection = old.collection;

			entity = old.entity;

			child = old.child;

			includeConditionList = old.includeConditionList;

			sortBy = old.sortBy;

			if (old.pageLimit > 0) {
				pageLimit = old.pageLimit;
			}

			parent = old.parent;

			full = old.full;

			pageOffset = old.pageOffset;

			limit = old.limit;

			extraParams = old.extraParams;

			query = old.query;

			queryParamName = old.queryParamName;

			queryOperator = old.queryOperator;

			queryParams = old.queryParams;

			downloadSiteList = old.downloadSiteList;
			
			creativeType = old.creativeType;
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
			child.add(value);
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

		public Builder setFull(FullParamValues full) {
			this.full = full;
			return this;
		}

		public Builder setPageOffset(int pageOffset) {
			this.pageOffset = pageOffset;
			return this;
		}

		public Builder setGetAll(boolean getAll) {
			this.getAll = getAll;
			return this;
		}

		public Builder setLimit(Map<String, Long> limit) {
			this.limit = limit;
			return this;
		}

		public Builder setExtraParams(Map<String, String> extraParams) {
			this.extraParams = extraParams;
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

		public Builder setQueryParamName(String queryParamName) {
			this.queryParamName = queryParamName;
			return this;
		}

		public Builder setQueryOperator(String queryOperator) {
			this.queryOperator = queryOperator;
			return this;
		}

		public Builder setQueryParams(QueryParamValues queryParams) {
			this.queryParams = queryParams;
			return this;
		}

		public Builder setDownloadSiteList(boolean downloadSiteList) {
			this.downloadSiteList = downloadSiteList;
			return this;
		}
		
		public Builder setCreativeType(CreativeType creativeType) {
			this.creativeType = creativeType;
			return this;
		}

		public QueryCriteria build() {
			return new QueryCriteria(this);
		}
	}

}
