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

public class StrategyConcept implements T1Entity {
	
	private static final String entityName = "StrategyConcept";

	private int concept_id;
	private Date created_on;
	private int id;
	private boolean status;
	private int strategy_id;
	private Date updated_on;
	private int version;

	private Concept concept;
	private Strategy strategy;
	
	public int getConceptId() {
		return concept_id;
	}

	public void setConceptId(int concept_id) {
		this.concept_id = concept_id;
	}

	public Date getCreatedOn() {
		return created_on;
	}

	public void setCreatedOn(Date created_on) {
		this.created_on = created_on;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getStrategyId() {
		return strategy_id;
	}

	public void setStrategyId(int strategy_id) {
		this.strategy_id = strategy_id;
	}

	public Date getUpdatedOn() {
		return updated_on;
	}

	public void setUpdatedOn(Date updated_on) {
		this.updated_on = updated_on;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	

	public Concept getConcept() {
		return concept;
	}

	public void setConcept(Concept concept) {
		this.concept = concept;
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
