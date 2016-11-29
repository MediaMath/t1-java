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

public class StrategyDayPart implements T1Entity {

  private static final String entityName = "StrategyDayPart";

  public enum daysEnum {
    M, T, W, R, F, S, U
  };

  private Date created_on;
  private daysEnum days;
  private int end_hour;
  private int id;
  private int start_hour;
  private boolean status;
  private int strategy_id;
  private Date udpated_on;
  private boolean user_time;
  private int version;
  private String name;

  private Strategy strategy;

  public Date getCreatedOn() {
    return created_on;
  }

  public void setCreatedOn(Date created_on) {
    this.created_on = created_on;
  }

  public daysEnum getDays() {
    return days;
  }

  public void setDays(daysEnum days) {
    this.days = days;
  }

  public int getEndHour() {
    return end_hour;
  }

  public void setEndHour(int end_hour) {
    this.end_hour = end_hour;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getStartHour() {
    return start_hour;
  }

  public void setStartHour(int start_hour) {
    this.start_hour = start_hour;
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

  public Date getUdpatedOn() {
    return udpated_on;
  }

  public void setUdpatedOn(Date udpated_on) {
    this.udpated_on = udpated_on;
  }

  public boolean isUserTime() {
    return user_time;
  }

  public void setUserTime(boolean user_time) {
    this.user_time = user_time;
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  @Override
  public Form getForm() {
	  Form strategyDayPartForm = new Form();

	    strategyDayPartForm.param("days", String.valueOf(this.getDays()));

	    if (this.getEndHour() > 0 && this.getEndHour() < 23) {
	      strategyDayPartForm.param("end_hour", String.valueOf(this.getEndHour()));
	    }

	    if (this.getStartHour() > 0 && this.getStartHour() < 23) {
	      strategyDayPartForm.param("start_hour", String.valueOf(this.getStartHour()));
	    }

	    if (this.getStrategyId() > 0) {
	      strategyDayPartForm.param("strategy_id", String.valueOf(this.getStrategyId()));
	    }

	    strategyDayPartForm.param("user_time", Utility.getOnOrOff(this.isUserTime()));

	    if (this.getVersion() >= 0) {
	      strategyDayPartForm.param("version", String.valueOf(this.getVersion()));
	    }

	    Form finalStrategyDayPartForm = Utility.getFilteredForm(strategyDayPartForm, "strategydaypart");

	    return finalStrategyDayPartForm;
  }

  @Override
  public String getUri() {
	  StringBuffer uri = new StringBuffer();
	    
	    if (this.getId() > 0) {
	      uri.append("/" + this.getId());
	    }
		return uri.toString();
  }

}
