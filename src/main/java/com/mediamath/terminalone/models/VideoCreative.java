package com.mediamath.terminalone.models;

import java.util.ArrayList;

public class VideoCreative {
	
	private String name;
	private long startTime;
	private String landingUrl;
	private String customVAST;
	private ArrayList<Long> vendors = new ArrayList<Long>();
	private long advertiser;
	private boolean desktopEncoding;
	private long endTime;
	private Integer skippableDuration; // null = non skippable as per documentation. 
	private boolean mobileEncoding;
	private long concept;
	private String clickthroughUrl;
	private boolean active;
	private eventPixelsEnum eventPixels;
	private long creativeId;
	
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

	public eventPixelsEnum getEventPixels() {
		return eventPixels;
	}

	public void setEventPixels(eventPixelsEnum eventPixels) {
		this.eventPixels = eventPixels;
	}

	public long getCreativeId() {
		return creativeId;
	}

	public void setCreativeId(long creativeId) {
		this.creativeId = creativeId;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

}
