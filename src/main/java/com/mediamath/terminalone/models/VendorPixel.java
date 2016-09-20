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

import java.util.Date;

public class VendorPixel implements T1Entity {
	
	private static final String entityName = "VendorPixel";

	private Date created_on;
	private int creative_id;
    private int id;
    private String set_by;
    private String tag;
    private String tag_type;
    private Date updated_on;
    private int version;
    
    private Creative creative;
    
    
	public Date getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
	public int getCreative_id() {
		return creative_id;
	}
	public void setCreative_id(int creative_id) {
		this.creative_id = creative_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSet_by() {
		return set_by;
	}
	public void setSet_by(String set_by) {
		this.set_by = set_by;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getTag_type() {
		return tag_type;
	}
	public void setTag_type(String tag_type) {
		this.tag_type = tag_type;
	}
	public Date getUpdated_on() {
		return updated_on;
	}
	public void setUpdated_on(Date updated_on) {
		this.updated_on = updated_on;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
	public Creative getCreative() {
		return creative;
	}
	public void setCreative(Creative creative) {
		this.creative = creative;
	}
	public String getEntityname() {
		return entityName;
	}
	
    
}
