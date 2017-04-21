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

  public void setAudienceSegmentId(int audience_segment_id) {
    this.audience_segment_id = audience_segment_id;
  }

  public Date getCreatedOn() {
    return created_on;
  }

  public void setCreatedOn(Date created_on) {
    this.created_on = created_on;
  }

  public String getGroupIdentifier() {
    return group_identifier;
  }

  public void setGroupIdentifier(String group_identifier) {
    this.group_identifier = group_identifier;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Date getUpdatedOn() {
    return updated_on;
  }

  public void setUpdatedOn(Date updated_on) {
    this.updated_on = updated_on;
  }

  public Currency getUserCpm() {
    return user_cpm;
  }

  public void setUserCpm(Currency user_cpm) {
    this.user_cpm = user_cpm;
  }
  
  public void setUserCpm(float user_cpm) {
	Currency curr = new Currency();
	curr.setValue(user_cpm);
	this.user_cpm = curr;
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
  public Form getForm() {
    return null;
  }

  @Override
  public String getUri() {
    return null;
  }

  public String getName() {
	  return name;
  }

  public void setName(String name) {
	  this.name = name;
  }

}
