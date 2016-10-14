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

public class Permission implements T1Entity {
	
	private static final String entityName = "Permission";

	// private String type; // seems duplicate one private one local
	private String advertiser;
	private String agency;
	private String organization;
	private int edit_data_definition;
	private int view_data_definition;
	private int edit_segments;
	private int edit_campaigns;
	private int access_internal_fees;
	private int edit_margins_and_performance;
	private int view_organizations;
	private int view_segments;
	private int view_dmp_reports;
	private String type;
	private String role;
	private String scope;

	public String getAdvertiser() {
		return advertiser;
	}

	public void setAdvertiser(String advertiser) {
		this.advertiser = advertiser;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public int getEditDataDefinition() {
		return edit_data_definition;
	}

	public void setEditDataDefinition(int edit_data_definition) {
		this.edit_data_definition = edit_data_definition;
	}

	public int getViewDataDefinition() {
		return view_data_definition;
	}

	public void setViewDataDefinition(int view_data_definition) {
		this.view_data_definition = view_data_definition;
	}

	public int getEditSegments() {
		return edit_segments;
	}

	public void setEditSegments(int edit_segments) {
		this.edit_segments = edit_segments;
	}

	public int getEditCampaigns() {
		return edit_campaigns;
	}

	public void setEditCampaigns(int edit_campaigns) {
		this.edit_campaigns = edit_campaigns;
	}

	public int getAccessInternalFees() {
		return access_internal_fees;
	}

	public void setAccessInternalFees(int access_internal_fees) {
		this.access_internal_fees = access_internal_fees;
	}

	public int getEditMarginsAndPerformance() {
		return edit_margins_and_performance;
	}

	public void setEditMarginsAndPerformance(int edit_margins_and_performance) {
		this.edit_margins_and_performance = edit_margins_and_performance;
	}

	public int getViewOrganizations() {
		return view_organizations;
	}

	public void setViewOrganizations(int view_organizations) {
		this.view_organizations = view_organizations;
	}

	public int getViewSegments() {
		return view_segments;
	}

	public void setViewSegments(int view_segments) {
		this.view_segments = view_segments;
	}

	public int getViewDmpReports() {
		return view_dmp_reports;
	}

	public void setViewDmpReports(int view_dmp_reports) {
		this.view_dmp_reports = view_dmp_reports;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getEntityname() {
		return entityName;
	}

}
