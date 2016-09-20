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
package com.mediamath.terminalone.models;

import java.util.Date;

public class StrategyDomain implements T1Entity {
	
	private static final String entityName = "StrategyDomain";

	public enum restrictions {
		INCLUDE, EXCLUDE
	};

	private Date created_at;
	private String domain;
	private int id;
	private restrictions restriction;
	private int strategy_id;
	private Date updated_on;
	private int version;
	
	private Strategy strategy;

	public StrategyDomain(String domain, restrictions restriction) {
		super();
		this.domain = domain;
		this.restriction = restriction;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public restrictions getRestriction() {
		return restriction;
	}

	public void setRestriction(restrictions restriction) {
		this.restriction = restriction;
	}

	public int getStrategy_id() {
		return strategy_id;
	}

	public void setStrategy_id(int strategy_id) {
		this.strategy_id = strategy_id;
	}

	public Date getUpdated_on() {
		return updated_on;
	}

	public void setUpdated_on(Date updated_on) {
		this.updated_on = updated_on;
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

	public String getEntityname() {
		return entityName;
	}

}
