package com.mediamath.terminalone.models;

import java.util.Date;

public class User implements T1Entity {
	
	private static final String entityName = "User";
	
	private boolean access_internal_fees;
	private boolean active;
	private Date created_on;
	private int creator_id;
	private boolean edit_data_definition;
	private boolean edit_campaigns;
	private boolean edit_margins_and_performance;
	private boolean edit_segments;
	private String fax;
	private String first_name;
	private int id;
	private boolean labs_enable_rmx;
	private Date last_login_on;
	private String last_name;
	private boolean link_ldap;
	private String mobile;
	private String password;
	private Date password_reset_sent;
	private String password_reset_token;
	private String phone;
	private String role;
	private String scope;
	private Date sso_auth_sent;
	private String sso_auth_token;
	private String title;
	private String type;
	private Date updated_on;
	private String username;
	private int version;
	private boolean view_data_definition;
	private boolean view_dmp_reports;
	private boolean view_organizations;
	private boolean view_segments;

	
	
	
	public boolean isAccess_internal_fees() {
		return access_internal_fees;
	}




	public void setAccess_internal_fees(boolean access_internal_fees) {
		this.access_internal_fees = access_internal_fees;
	}




	public boolean isActive() {
		return active;
	}




	public void setActive(boolean active) {
		this.active = active;
	}




	public Date getCreated_on() {
		return created_on;
	}




	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}




	public int getCreator_id() {
		return creator_id;
	}




	public void setCreator_id(int creator_id) {
		this.creator_id = creator_id;
	}




	public boolean isEdit_data_definition() {
		return edit_data_definition;
	}




	public void setEdit_data_definition(boolean edit_data_definition) {
		this.edit_data_definition = edit_data_definition;
	}




	public boolean isEdit_campaigns() {
		return edit_campaigns;
	}




	public void setEdit_campaigns(boolean edit_campaigns) {
		this.edit_campaigns = edit_campaigns;
	}




	public boolean isEdit_margins_and_performance() {
		return edit_margins_and_performance;
	}




	public void setEdit_margins_and_performance(boolean edit_margins_and_performance) {
		this.edit_margins_and_performance = edit_margins_and_performance;
	}




	public boolean isEdit_segments() {
		return edit_segments;
	}




	public void setEdit_segments(boolean edit_segments) {
		this.edit_segments = edit_segments;
	}




	public String getFax() {
		return fax;
	}




	public void setFax(String fax) {
		this.fax = fax;
	}




	public String getFirst_name() {
		return first_name;
	}




	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public boolean isLabs_enable_rmx() {
		return labs_enable_rmx;
	}




	public void setLabs_enable_rmx(boolean labs_enable_rmx) {
		this.labs_enable_rmx = labs_enable_rmx;
	}




	public Date getLast_login_on() {
		return last_login_on;
	}




	public void setLast_login_on(Date last_login_on) {
		this.last_login_on = last_login_on;
	}




	public String getLast_name() {
		return last_name;
	}




	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}




	public boolean isLink_ldap() {
		return link_ldap;
	}




	public void setLink_ldap(boolean link_ldap) {
		this.link_ldap = link_ldap;
	}




	public String getMobile() {
		return mobile;
	}




	public void setMobile(String mobile) {
		this.mobile = mobile;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public Date getPassword_reset_sent() {
		return password_reset_sent;
	}




	public void setPassword_reset_sent(Date password_reset_sent) {
		this.password_reset_sent = password_reset_sent;
	}




	public String getPassword_reset_token() {
		return password_reset_token;
	}




	public void setPassword_reset_token(String password_reset_token) {
		this.password_reset_token = password_reset_token;
	}




	public String getPhone() {
		return phone;
	}




	public void setPhone(String phone) {
		this.phone = phone;
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




	public Date getSso_auth_sent() {
		return sso_auth_sent;
	}




	public void setSso_auth_sent(Date sso_auth_sent) {
		this.sso_auth_sent = sso_auth_sent;
	}




	public String getSso_auth_token() {
		return sso_auth_token;
	}




	public void setSso_auth_token(String sso_auth_token) {
		this.sso_auth_token = sso_auth_token;
	}




	public String getTitle() {
		return title;
	}




	public void setTitle(String title) {
		this.title = title;
	}




	public String getType() {
		return type;
	}




	public void setType(String type) {
		this.type = type;
	}




	public Date getUpdated_on() {
		return updated_on;
	}




	public void setUpdated_on(Date updated_on) {
		this.updated_on = updated_on;
	}




	public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}




	public int getVersion() {
		return version;
	}




	public void setVersion(int version) {
		this.version = version;
	}




	public boolean isView_data_definition() {
		return view_data_definition;
	}




	public void setView_data_definition(boolean view_data_definition) {
		this.view_data_definition = view_data_definition;
	}




	public boolean isView_dmp_reports() {
		return view_dmp_reports;
	}




	public void setView_dmp_reports(boolean view_dmp_reports) {
		this.view_dmp_reports = view_dmp_reports;
	}




	public boolean isView_organizations() {
		return view_organizations;
	}




	public void setView_organizations(boolean view_organizations) {
		this.view_organizations = view_organizations;
	}




	public boolean isView_segments() {
		return view_segments;
	}




	public void setView_segments(boolean view_segments) {
		this.view_segments = view_segments;
	}




	public String getEntityname() {
		return entityName;
	}

}
