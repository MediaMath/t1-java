package com.mediamath.terminalone.models.reporting.meta;

import java.util.HashMap;

public class TimeField {
	
	HashMap<String, TimeInterval> data = new HashMap<String, TimeInterval>();
	
	TimeInterval interval;

	public TimeInterval getInterval() {
		return interval;
	}

	public void setInterval(TimeInterval interval) {
		this.interval = interval;
	}

	public HashMap<String, TimeInterval> getData() {
		return data;
	}

	public void setData(HashMap<String, TimeInterval> data) {
		this.data = data;
	}
}
