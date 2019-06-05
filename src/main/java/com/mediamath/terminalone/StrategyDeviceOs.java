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

import com.mediamath.terminalone.models.T1Entity;
import org.javers.core.metamodel.annotation.DiffIgnore;
import org.javers.core.metamodel.annotation.Id;

import javax.ws.rs.core.Form;
import java.util.Date;

public class StrategyDeviceOs implements T1Entity {

  public static final String YYYY_MM_DDTHH_MM_SS_Z = "yyyy-MM-dd hh:mm:ss.sss+hh";

  private static final String entityName = "StrategyDeviceModel";

  public enum restrictions {
    INCLUDE, EXCLUDE
  }

    public enum operator {
        OR, AND
    }

  private Date created_on;
  private int target_id;
  private operator operator;
  private int id;
  private restrictions restriction;
  private int strategy_id;
  private Date updated_on;
  private int version;

  public StrategyDeviceOs(){}

  @DiffIgnore
  public Date getCreatedAt() {
    return created_on;
  }

  public void setCreatedAt(Date created_at) {
    this.created_on = created_at;
  }

  @DiffIgnore
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Id
  public String getDifferId() {
    return String.valueOf(getTargetId()) + String.valueOf(getStrategyId());
  }


  public restrictions getRestriction() {
    return restriction;
  }

  public void setRestriction(restrictions restriction) {
    this.restriction = restriction;
  }

  public int getStrategyId() {
    return strategy_id;
  }

  public void setStrategyId(int strategy_id) {
    this.strategy_id = strategy_id;
  }

  @DiffIgnore
  public Date getUpdatedOn() {
    return updated_on;
  }

  public void setUpdatedOn(Date updated_on) {
    this.updated_on = updated_on;
  }

  @DiffIgnore
  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  @DiffIgnore
  public Date getCreated_on() {
        return created_on;
    }


  public void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }

  public int getTargetId() {
        return target_id;
    }

  public void setTargetId(int target_id) {
        this.target_id = target_id;
  }

  public StrategyDeviceOs.operator getOperator() {
        return operator;
    }

  public void setOperator(StrategyDeviceOs.operator operator) {
        this.operator = operator;
    }

  @DiffIgnore
  public Date getUpdated_on() {
        return updated_on;
    }

  public void setUpdated_on(Date updated_on) {
        this.updated_on = updated_on;
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
  @DiffIgnore
  public String getUri() {
	  return null;
  }

}
