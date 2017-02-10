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

import com.mediamath.terminalone.utils.Constants;
import com.mediamath.terminalone.utils.Utility;

public class StrategyConcept implements T1Entity {

  private static final String entityName = "StrategyConcept";

  private int advertiser_id;
  private int concept_id;
  private Date created_on;
  private int id;
  private boolean status;
  private int strategy_id;
  private Date updated_on;
  private int version;
  String weighting;
  String name;

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

  public int getAdvertiserId() {
    return advertiser_id;
  }

  public void setAdvertiserId(int advertiserId) {
    this.advertiser_id = advertiserId;
  }

  public String getWeighting() {
    return weighting;
  }

  public void setWeighting(String weighting) {
    this.weighting = weighting;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  @Override
  public Form getForm() {
	  Form strategyConceptForm = new Form();

	    if (getConceptId() > 0) {
	      strategyConceptForm.param("concept_id", String.valueOf(getConceptId()));
	    }

	    if (getStrategyId() > 0) {
	      strategyConceptForm.param("strategy_id", String.valueOf(getStrategyId()));
	    }

	    strategyConceptForm.param("status", Utility.getOnOrOff(isStatus()));

	    if (getVersion() >= 0) {
	      strategyConceptForm.param("version", String.valueOf(getVersion()));
	    }

	    Form finalStrategyConceptForm = Utility.getFilteredForm(strategyConceptForm, "strategyconcept");

	    return finalStrategyConceptForm;
  }

  @Override
  public String getUri() {
	  StringBuilder uri = new StringBuilder();
	    
	    if (this.getId() > 0) {
	      uri.append("/" + this.getId());
	    }
		return uri.toString();
  }

}
