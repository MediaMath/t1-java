package com.mediamath.terminalone.models.reporting;

public enum Reports {

	APPTRANSPARENCY("app_transparency"),
	AUDIENCEINDEX("audience_index"),
	AUDIENCEINDEXPIXEL("audience_index_pixel"),
	DATAPIXELLOADS("data_pixel_loads"),
	DAYPART("day_part"),
	DEVICETECHNOLOGY("device_technology"),
	EVENTPIXELLOADS("event_pixel_loads"),
	GEO("geo"),
	PERFORMANCE("performance"),
	POSTALCODE("postal_code"),
	PULSE("pulse"),
	REACHFREQUENCY("reach_frequency"),
	SITETRANSPARENCY("site_transparency"),
	VIDEO("video"),
	VIDEOSITETRANSPARENCY("video_site_transparency"),
	WATERMARK("watermark"),
	WINLOSS("win_loss"),
	WINLOSSCREATIVE("win_loss_creative");
	
	String reportName;

	Reports(String name) {
		reportName = name;
	}
	
	public String getReportName(){
		return this.reportName;
	}
	
	public String getReportNameWithMeta() {
		return this.reportName + "/meta";
	}
	
}
