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

public class SitePlacement implements T1Entity {
	
	private static final String entityName = "SitePlacement";

	public enum dealSources  {USER, INTERNAL};
	public enum mediaTypes  {display, video, mobile};
	public enum pmpTypes  {DIRECT, PREMIUM};
	
	private boolean bill_media_to_client;
    private Date created_on;
    private dealSources deal_source;
    private String display_text;
    private int id;
    private mediaTypes media_type;
    private String name;
    private pmpTypes pmp_type;
    private int publisher_site_id;
    private Date updated_on;
    private int version;
	public boolean isBill_media_to_client() {
		return bill_media_to_client;
	}
	public void setBill_media_to_client(boolean bill_media_to_client) {
		this.bill_media_to_client = bill_media_to_client;
	}
	public Date getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
	public dealSources getDeal_source() {
		return deal_source;
	}
	public void setDeal_source(dealSources deal_source) {
		this.deal_source = deal_source;
	}
	public String getDisplay_text() {
		return display_text;
	}
	public void setDisplay_text(String display_text) {
		this.display_text = display_text;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public mediaTypes getMedia_type() {
		return media_type;
	}
	public void setMedia_type(mediaTypes media_type) {
		this.media_type = media_type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public pmpTypes getPmp_type() {
		return pmp_type;
	}
	public void setPmp_type(pmpTypes pmp_type) {
		this.pmp_type = pmp_type;
	}
	public int getPublisher_site_id() {
		return publisher_site_id;
	}
	public void setPublisher_site_id(int publisher_site_id) {
		this.publisher_site_id = publisher_site_id;
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
	public String getEntityname() {
		return entityName;
	}
	
	
}
