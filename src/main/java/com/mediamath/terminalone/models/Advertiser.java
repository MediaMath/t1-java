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

public class Advertiser implements T1Entity {
	
	private static final String entityName = "Advertiser" ;

	public enum dmp_settings {
		inherits, disabled
	};
	
	public enum freq_ints {
		hour("hour"), day("day"), week("week"), month("month"), not_applicable("not-applcable");
		String val;
		freq_ints(String s){
			val=s;
		}
	}; // should be not-applicable

	public enum freq_types {
		even("even"), asap("asap"), no_limit("no-limit");
		String val;
		freq_types(String s){
			val=s;
		}
	}; // should be no-limit

	

	private int id;
	private float ad_server_fee;
	private int ad_server_id;
	private String ad_server_password;
	private String ad_server_username;
	private int agency_id;
	private boolean allow_x_strat_optimization;
	private int billing_contact_id;
	private Date created_on;
	private String domain;
	private dmp_settings dmp_enabled;
	private freq_types frequency_type;
	private freq_ints frequency_interval;
	private int frequency_amount;
	private boolean minimize_multi_ads;
	private String name;
	private int sales_contact_id;
	private boolean status;
	private Date updated_on;
	private int version;
	private int vertical_id;
	private Agency agency;
	private Contact billing_contact;
	private Contact sales_contact;
	private Vertical vertical;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getAd_server_fee() {
		return ad_server_fee;
	}

	public void setAd_server_fee(float ad_server_fee) {
		this.ad_server_fee = ad_server_fee;
	}

	public int getAd_server_id() {
		return ad_server_id;
	}

	public void setAd_server_id(int ad_server_id) {
		this.ad_server_id = ad_server_id;
	}

	public String getAd_server_password() {
		return ad_server_password;
	}

	public void setAd_server_password(String ad_server_password) {
		this.ad_server_password = ad_server_password;
	}

	public String getAd_server_username() {
		return ad_server_username;
	}

	public void setAd_server_username(String ad_server_username) {
		this.ad_server_username = ad_server_username;
	}

	public int getAgency_id() {
		return agency_id;
	}

	public void setAgency_id(int agency_id) {
		this.agency_id = agency_id;
	}

	public boolean isAllow_x_strat_optimization() {
		return allow_x_strat_optimization;
	}

	public void setAllow_x_strat_optimization(boolean allow_x_strat_optimization) {
		this.allow_x_strat_optimization = allow_x_strat_optimization;
	}

	public int getBilling_contact_id() {
		return billing_contact_id;
	}

	public void setBilling_contact_id(int billing_contact_id) {
		this.billing_contact_id = billing_contact_id;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public dmp_settings getDmp_enabled() {
		return dmp_enabled;
	}

	public void setDmp_enabled(dmp_settings dmp_enabled) {
		this.dmp_enabled = dmp_enabled;
	}

	public freq_types getFrequency_type() {
		return frequency_type;
	}

	public void setFrequency_type(freq_types frequency_type) {
		this.frequency_type = frequency_type;
	}

	public freq_ints getFrequency_interval() {
		return frequency_interval;
	}

	public void setFrequency_interval(freq_ints frequency_interval) {
		this.frequency_interval = frequency_interval;
	}

	public int getFrequency_amount() {
		return frequency_amount;
	}

	public void setFrequency_amount(int frequency_amount) {
		this.frequency_amount = frequency_amount;
	}

	public boolean isMinimize_multi_ads() {
		return minimize_multi_ads;
	}

	public void setMinimize_multi_ads(boolean minimize_multi_ads) {
		this.minimize_multi_ads = minimize_multi_ads;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSales_contact_id() {
		return sales_contact_id;
	}

	public void setSales_contact_id(int sales_contact_id) {
		this.sales_contact_id = sales_contact_id;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
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

	public int getVertical_id() {
		return vertical_id;
	}

	public void setVertical_id(int vertical_id) {
		this.vertical_id = vertical_id;
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}
	
	public Contact getBilling_contact() {
		return billing_contact;
	}

	public void setBilling_contact(Contact billing_contact) {
		this.billing_contact = billing_contact;
	}

	public Contact getSales_contact() {
		return sales_contact;
	}

	public void setSales_contact(Contact sales_contact) {
		this.sales_contact = sales_contact;
	}

	public Vertical getVertical() {
		return vertical;
	}

	public void setVertical(Vertical vertical) {
		this.vertical = vertical;
	}

	public String getEntityname() {
		return entityName;
	}
}
