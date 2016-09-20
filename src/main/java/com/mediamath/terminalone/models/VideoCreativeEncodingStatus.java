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

public class VideoCreativeEncodingStatus {
	
	private String status;
	
	private long percent;
	
	private ArrayList<VideoCreativeEncodings> encodings = new ArrayList<VideoCreativeEncodings>();

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getPercent() {
		return percent;
	}

	public void setPercent(long percent) {
		this.percent = percent;
	}

	public ArrayList<VideoCreativeEncodings> getEncodings() {
		return encodings;
	}

	public void setEncodings(ArrayList<VideoCreativeEncodings> encodings) {
		this.encodings = encodings;
	}

}
