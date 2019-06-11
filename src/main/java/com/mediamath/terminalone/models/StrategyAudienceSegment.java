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

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.javers.core.metamodel.annotation.DiffIgnore;
import org.javers.core.metamodel.annotation.Id;

import java.math.BigDecimal;
import java.util.Date;

import javax.ws.rs.core.Form;

public class StrategyAudienceSegment implements T1Entity {

	private static final String entityName = "StrategyAudienceSegment";

	private int audience_segment_id;
	private Date created_on;
	private String group_identifier;
	private int id;
	private String operator;
	private String restriction;
	private int strategy_id;
	private String type;
	private Date updated_on;
	private Currency user_cpm;
	private int version;
	private String name;

	private Strategy strategy;
	private AudienceSegment audience_segment;

	public int getAudienceSegmentId() {
		return audience_segment_id;
	}

	//Audience Segment Id cannot be annotated with @Id because it is not unique inside a campaign
	@Id
	public String getDifferId() {
		return String.valueOf(getAudienceSegmentId()) + String.valueOf(getStrategyId());
	}

	public void setAudienceSegmentId(int audience_segment_id) {
		this.audience_segment_id = audience_segment_id;
	}

	@DiffIgnore
	public Date getCreatedOn() {
		return created_on;
	}

	public void setCreatedOn(Date created_on) {
		this.created_on = created_on;
	}

	@DiffIgnore
	public String getGroupIdentifier() {
		return group_identifier;
	}

	public void setGroupIdentifier(String group_identifier) {
		this.group_identifier = group_identifier;
	}

	@DiffIgnore
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@DiffIgnore
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getRestriction() {
		return restriction;
	}

	public void setRestriction(String restriction) {
		this.restriction = restriction;
	}

	public int getStrategyId() {
		return strategy_id;
	}

	public void setStrategyId(int strategy_id) {
		this.strategy_id = strategy_id;
	}

	@DiffIgnore
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@DiffIgnore
	public Date getUpdatedOn() {
		return updated_on;
	}

	public void setUpdatedOn(Date updated_on) {
		this.updated_on = updated_on;
	}

	@DiffIgnore
	public Currency getUserCpm() {
		return user_cpm;
	}

	public void setUserCpm(Currency user_cpm) {
		this.user_cpm = user_cpm;
	}

	public void setUserCpm(BigDecimal user_cpm) {
		Currency curr = new Currency();
		curr.setValue(user_cpm);
		this.user_cpm = curr;
	}

	@DiffIgnore
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@DiffIgnore
	public Strategy getStrategy() {
		return strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	@DiffIgnore
	public AudienceSegment getAudienceSegment() {
		return audience_segment;
	}

	public void setAudienceSegment(AudienceSegment audience_segment) {
		this.audience_segment = audience_segment;
	}

	@Override
	public String getEntityname() {
		return entityName;
	}

	@Override
	@DiffIgnore
	@JsonIgnore
	public Form getForm() {
		return null;
	}

	@Override
	@DiffIgnore
	@JsonIgnore
	public String getUri() {
		return null;
	}

	@DiffIgnore
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
