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

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Form;

import com.mediamath.terminalone.exceptions.ClientException;

public class VideoCreative implements T1Entity {

	private String name;
	private Long startTime;
	private String landingUrl;
	private String customVAST;
	private ArrayList<Long> vendors = new ArrayList<Long>();
	private long advertiser;
	private boolean desktopEncoding;
	private Long endTime;
	private Integer skippableDuration; // null = non skippable as per
										// documentation.
	private boolean mobileEncoding;
	private long concept;
	private String clickthroughUrl;
	private boolean active;
	private List<EventPixel> eventPixels = new ArrayList();
	
	private int creativeId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLandingUrl() {
		return landingUrl;
	}

	public void setLandingUrl(String landingUrl) {
		this.landingUrl = landingUrl;
	}

	public String getCustomVAST() {
		return customVAST;
	}

	public void setCustomVAST(String customVAST) {
		this.customVAST = customVAST;
	}

	public ArrayList<Long> getVendors() {
		return vendors;
	}

	public void setVendors(long pVendor) {
		this.vendors.add(pVendor);
	}

	public long getAdvertiser() {
		return advertiser;
	}

	public void setAdvertiser(long advertiser) {
		this.advertiser = advertiser;
	}

	public boolean isDesktopEncoding() {
		return desktopEncoding;
	}

	public void setDesktopEncoding(boolean desktopEncoding) {
		this.desktopEncoding = desktopEncoding;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public Integer getSkippableDuration() {
		return skippableDuration;
	}

	public void setSkippableDuration(Integer skippableDuration) {
		this.skippableDuration = skippableDuration;
	}

	public boolean isMobileEncoding() {
		return mobileEncoding;
	}

	public void setMobileEncoding(boolean mobileEncoding) {
		this.mobileEncoding = mobileEncoding;
	}

	public long getConcept() {
		return concept;
	}

	public void setConcept(long concept) {
		this.concept = concept;
	}

	public String getClickthroughUrl() {
		return clickthroughUrl;
	}

	public void setClickthroughUrl(String clickthroughUrl) {
		this.clickthroughUrl = clickthroughUrl;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<EventPixel> getEventPixels() {
		return eventPixels;
	}

	public void setEventPixels(List<EventPixel> eventPixels) {
		this.eventPixels = eventPixels;
	}

	public int getCreativeId() {
		return creativeId;
	}

	public void setCreativeId(int creativeId) {
		this.creativeId = creativeId;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	@Override
	public String getEntityname() {
		return null;
	}

	@Override
	public Form getForm() {
		return null;
	}

	@Override
	public String getUri() throws ClientException {
		return null;
	}

}
