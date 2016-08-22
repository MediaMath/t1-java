package com.mediamath.terminalone.models.reporting.meta;

import java.util.HashMap;

public class MetaMetrics {
	
	HashMap<String, MetricsData> metricsMap = new HashMap<String, MetricsData>();

	public HashMap<String, MetricsData> getMetricsMap() {
		return metricsMap;
	}

	public void setMetricsMap(HashMap<String, MetricsData> metricsMap) {
		this.metricsMap = metricsMap;
	}

}
