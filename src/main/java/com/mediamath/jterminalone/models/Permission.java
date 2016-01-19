package com.mediamath.jterminalone.models;

public class Permission {

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

	public int getEdit_data_definition() {
		return edit_data_definition;
	}

	public void setEdit_data_definition(int edit_data_definition) {
		this.edit_data_definition = edit_data_definition;
	}

	public int getView_data_definition() {
		return view_data_definition;
	}

	public void setView_data_definition(int view_data_definition) {
		this.view_data_definition = view_data_definition;
	}

	public int getEdit_segments() {
		return edit_segments;
	}

	public void setEdit_segments(int edit_segments) {
		this.edit_segments = edit_segments;
	}

	public int getEdit_campaigns() {
		return edit_campaigns;
	}

	public void setEdit_campaigns(int edit_campaigns) {
		this.edit_campaigns = edit_campaigns;
	}

	public int getAccess_internal_fees() {
		return access_internal_fees;
	}

	public void setAccess_internal_fees(int access_internal_fees) {
		this.access_internal_fees = access_internal_fees;
	}

	public int getEdit_margins_and_performance() {
		return edit_margins_and_performance;
	}

	public void setEdit_margins_and_performance(int edit_margins_and_performance) {
		this.edit_margins_and_performance = edit_margins_and_performance;
	}

	public int getView_organizations() {
		return view_organizations;
	}

	public void setView_organizations(int view_organizations) {
		this.view_organizations = view_organizations;
	}

	public int getView_segments() {
		return view_segments;
	}

	public void setView_segments(int view_segments) {
		this.view_segments = view_segments;
	}

	public int getView_dmp_reports() {
		return view_dmp_reports;
	}

	public void setView_dmp_reports(int view_dmp_reports) {
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

}
