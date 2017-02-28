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

import javax.ws.rs.core.Form;

public class StrategySupplySource implements T1Entity {

  private static final String entityName = "StrategySupplySource";

  private int id;
  private int strategy_id;
  private int supply_source_id;
  private int version;
  private String name;

  private Strategy strategy;
  private SupplySource supply_source;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getStrategyId() {
    return strategy_id;
  }

  public void setStrategyId(int strategy_id) {
    this.strategy_id = strategy_id;
  }

  public int getSupplySourceId() {
    return supply_source_id;
  }

  public void setSupplySourceId(int supply_source_id) {
    this.supply_source_id = supply_source_id;
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

  public SupplySource getSupplySource() {
    return supply_source;
  }

  public void setSupplySource(SupplySource supply_source) {
    this.supply_source = supply_source;
  }

  @Override
  public String getEntityname() {
    return entityName;
  }
  
  @Override
  public Form getForm() {
	  Form strategySupplySourceForm = new Form();

	    if (this.getSupplySourceId() > 0) {
	      strategySupplySourceForm.param("supply_source_id",
	          String.valueOf(this.getSupplySourceId()));
	    }

	    if (this.getStrategyId() > 0) {
	      strategySupplySourceForm.param("strategy_id", String.valueOf(this.getStrategyId()));
	    }

	    if (this.getVersion() >= 0) {
	      strategySupplySourceForm.param("version", String.valueOf(this.getVersion()));
	    }

	    return strategySupplySourceForm;
  }

  @Override
  public String getUri() {
	  StringBuilder uri = new StringBuilder();

      if (this.getId() > 0) {
        uri.append("/");
        uri.append(this.getId());
      }
      
      return uri.toString();
  }

  public String getName() {
	  return name;
  }

  public void setName(String name) {
	  this.name = name;
  }

}
