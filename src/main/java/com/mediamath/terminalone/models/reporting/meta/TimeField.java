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

public class TimeField {

	private HashMap<String, TimeInterval> data = new HashMap<String, TimeInterval>();

	private TimeInterval interval;

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
