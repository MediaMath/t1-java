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

public class BulkStrategy {
	
	private int id;
	private String name;
	private boolean  supply_management;
	private boolean  contextual_targeting;
	private boolean  creative_assignment;
	private boolean  pixel_targeting;
	private boolean  audience_targeting;
	private boolean  strategy_targeting;
	private boolean  site_lists;
	private boolean  video_targeting;
	
	public BulkStrategy(){}
	
	public BulkStrategy(int id, String name, boolean supply_management, boolean contextual_targeting,
			boolean creative_assignment, boolean pixel_targeting, boolean audience_targeting,
			boolean strategy_targeting, boolean site_lists, boolean video_targeting) {
		super();
		this.id = id;
		this.name = name;
		this.supply_management = supply_management;
		this.contextual_targeting = contextual_targeting;
		this.creative_assignment = creative_assignment;
		this.pixel_targeting = pixel_targeting;
		this.audience_targeting = audience_targeting;
		this.strategy_targeting = strategy_targeting;
		this.site_lists = site_lists;
		this.video_targeting = video_targeting;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isSupplyManagement() {
		return supply_management;
	}
	public void setSupplyManagement(boolean supply_management) {
		this.supply_management = supply_management;
	}
	public boolean isContextualTargeting() {
		return contextual_targeting;
	}
	public void setContextualTargeting(boolean contextual_targeting) {
		this.contextual_targeting = contextual_targeting;
	}
	public boolean isCreativeAssignment() {
		return creative_assignment;
	}
	public void setCreativeAssignment(boolean creative_assignment) {
		this.creative_assignment = creative_assignment;
	}
	public boolean isPixelTargeting() {
		return pixel_targeting;
	}
	public void setPixelTargeting(boolean pixel_targeting) {
		this.pixel_targeting = pixel_targeting;
	}
	public boolean isAudienceTargeting() {
		return audience_targeting;
	}
	public void setAudienceTargeting(boolean audience_targeting) {
		this.audience_targeting = audience_targeting;
	}
	public boolean isStrategyTargeting() {
		return strategy_targeting;
	}
	public void setStrategyTargeting(boolean strategy_targeting) {
		this.strategy_targeting = strategy_targeting;
	}
	public boolean isSiteLists() {
		return site_lists;
	}
	public void setSiteLists(boolean site_lists) {
		this.site_lists = site_lists;
	}
	public boolean isVideoTargeting() {
		return video_targeting;
	}
	public void setVideoTargeting(boolean video_targeting) {
		this.video_targeting = video_targeting;
	}
}
