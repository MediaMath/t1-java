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

public class StrategyDayPart implements T1Entity {
	
	private static final String entityName = "StrategyDayPart";
	public enum daysEnum {M,T,W,R,F,S,U};
	
	
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
	
	private Strategy strategy;

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public daysEnum getDays() {
		return days;
	}

	public void setDays(daysEnum days) {
		this.days = days;
	}

	public int getEnd_hour() {
		return end_hour;
	}

	public void setEnd_hour(int end_hour) {
		this.end_hour = end_hour;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStart_hour() {
		return start_hour;
	}

	public void setStart_hour(int start_hour) {
		this.start_hour = start_hour;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getStrategy_id() {
		return strategy_id;
	}

	public void setStrategy_id(int strategy_id) {
		this.strategy_id = strategy_id;
	}

	public Date getUdpated_on() {
		return udpated_on;
	}

	public void setUdpated_on(Date udpated_on) {
		this.udpated_on = udpated_on;
	}

	public boolean isUser_time() {
		return user_time;
	}

	public void setUser_time(boolean user_time) {
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

}
