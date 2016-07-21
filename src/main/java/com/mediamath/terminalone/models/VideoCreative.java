package com.mediamath.terminalone.models;

import java.util.ArrayList;

public class VideoCreative {
	
	private String name;
	private Long startTime;
	private String landingUrl;
	private String customVAST;
	private ArrayList<Long> vendors = new ArrayList<Long>();
	private Integer advertiser;
	private boolean desktopEncoding;
	private Long endTime;
	private Integer skippableDuration;
	private boolean mobileEncoding;
	private Integer concept;
	private String clickthroughUrl;
	private boolean active;
	private eventPixelsEnum eventPixels;
	private Integer creativeId;
	
	public enum eventPixelsEnum {
		
		ImpSkippable("imp:skippable"), 
		Complete("complete"), 
		CreativeClick("creative:click"), 
		FullScreen("fullscreen"), 
		FirstQuartile("firstQuartile"), 
		CreativeError("creative:err"), 
		Rewind("rewind"), 
		MidPoint("midpoint"), 
		Start("start"), 
		CreativeImp("creative:imp"), 
		CreativeView("creativeView"), 
		Expand("expand"), 
		Close("close"), 
		Collapse("collapse"), 
		AcceptInvitation("acceptInvitation"), 
		Mute("mute"), 
		ThirdQuartile("thirdQuartile"), 
		Unmute("unmute"), Resume("resume"), Pause("pause"), Skip("skip"), 
		EngagedView("engagedView");
		
		String val;
		eventPixelsEnum(String pVal) {
			val = pVal;
		}
		
	}

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

	public int getAdvertiser() {
		return advertiser;
	}

	public void setAdvertiser(int advertiser) {
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

	public int getSkippableDuration() {
		return skippableDuration;
	}

	public void setSkippableDuration(int skippableDuration) {
		this.skippableDuration = skippableDuration;
	}

	public boolean isMobileEncoding() {
		return mobileEncoding;
	}

	public void setMobileEncoding(boolean mobileEncoding) {
		this.mobileEncoding = mobileEncoding;
	}

	public int getConcept() {
		return concept;
	}

	public void setConcept(int concept) {
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

	public eventPixelsEnum getEventPixels() {
		return eventPixels;
	}

	public void setEventPixels(eventPixelsEnum eventPixels) {
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

}
