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

public class Segments {
	
	public enum restrictions {
		INCLUDE, EXCLUDE
	};
	
	public enum aud_seg_exc {
		AND, OR
	};

	public enum aud_seg_inc {
		AND, OR
	};
	
	private int id;
	private restrictions restriction;
	private aud_seg_exc exclude;
	private aud_seg_inc include;
	
	public Segments(int id, restrictions restriction, aud_seg_exc exclude, aud_seg_inc include) {
		this.id = id;
		this.restriction = restriction;
		this.exclude = exclude;
		this.include = include;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public restrictions getRestriction() {
		return restriction;
	}
	public void setRestriction(restrictions restriction) {
		this.restriction = restriction;
	}
	public aud_seg_exc getExclude() {
		return exclude;
	}
	public void setExclude(aud_seg_exc exclude) {
		this.exclude = exclude;
	}
	public aud_seg_inc getInclude() {
		return include;
	}
	public void setInclude(aud_seg_inc include) {
		this.include = include;
	}
	
	

}
