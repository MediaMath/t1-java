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
