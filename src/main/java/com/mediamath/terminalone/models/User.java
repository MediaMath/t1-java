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

import com.mediamath.terminalone.utils.Utility;

import javax.ws.rs.core.Form;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class User extends Entity {

    public User() {
        super("User");
    }

    private boolean access_internal_fees;
    private boolean active;
    private Date created_on;
    private int creator_id;
    private boolean edit_data_definition;
    private boolean edit_campaigns;
    private boolean edit_margins_and_performance;
    private boolean edit_segments;
    private String email;
    private String first_name;
    private int id;
    private boolean labs_enable_rmx;
    private Date last_login_on;
    private String last_name;
    private boolean link_ldap;
    private boolean link_saml;
    private String name;
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

    private Permissions permissions;
    private Map<String, Integer> permissionList = new HashMap<String, Integer>();

    public boolean isAccessInternalFees() {
        return access_internal_fees;
    }

    public void setAccessInternalFees(boolean access_internal_fees) {
        this.access_internal_fees = access_internal_fees;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getCreatedOn() {
        return created_on;
    }

    public void setCreatedOn(Date created_on) {
        this.created_on = created_on;
    }

    public int getCreatorId() {
        return creator_id;
    }

    public void setCreatorId(int creator_id) {
        this.creator_id = creator_id;
    }

    public boolean isEditDataDefinition() {
        return edit_data_definition;
    }

    public void setEditDataDefinition(boolean edit_data_definition) {
        this.edit_data_definition = edit_data_definition;
    }

    public boolean isEditCampaigns() {
        return edit_campaigns;
    }

    public void setEditCampaigns(boolean edit_campaigns) {
        this.edit_campaigns = edit_campaigns;
    }

    public boolean isEditMarginsAndPerformance() {
        return edit_margins_and_performance;
    }

    public void setEditMarginsAndPerformance(boolean edit_margins_and_performance) {
        this.edit_margins_and_performance = edit_margins_and_performance;
    }

    public boolean isEditSegments() {
        return edit_segments;
    }

    public void setEditSegments(boolean edit_segments) {
        this.edit_segments = edit_segments;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isLabsEnableRmx() {
        return labs_enable_rmx;
    }

    public void setLabsEnableRmx(boolean labs_enable_rmx) {
        this.labs_enable_rmx = labs_enable_rmx;
    }

    public Date getLastLoginOn() {
        return last_login_on;
    }

    public void setLastLoginOn(Date last_login_on) {
        this.last_login_on = last_login_on;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public boolean isLinkLdap() {
        return link_ldap;
    }

    public void setLinkLdap(boolean link_ldap) {
        this.link_ldap = link_ldap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getPasswordResetSent() {
        return password_reset_sent;
    }

    public void setPasswordResetSent(Date password_reset_sent) {
        this.password_reset_sent = password_reset_sent;
    }

    public String getPasswordResetToken() {
        return password_reset_token;
    }

    public void setPasswordResetToken(String password_reset_token) {
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

    public Date getSsoAuthSent() {
        return sso_auth_sent;
    }

    public void setSsoAuthSent(Date sso_auth_sent) {
        this.sso_auth_sent = sso_auth_sent;
    }

    public String getSsoAuthToken() {
        return sso_auth_token;
    }

    public void setSsoAuthToken(String sso_auth_token) {
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

    public Date getUpdatedOn() {
        return updated_on;
    }

    public void setUpdatedOn(Date updated_on) {
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

    public boolean isViewDataDefinition() {
        return view_data_definition;
    }

    public void setViewDataDefinition(boolean view_data_definition) {
        this.view_data_definition = view_data_definition;
    }

    public boolean isViewDmpReports() {
        return view_dmp_reports;
    }

    public void setViewDmpReports(boolean view_dmp_reports) {
        this.view_dmp_reports = view_dmp_reports;
    }

    public boolean isViewOrganizations() {
        return view_organizations;
    }

    public void setViewOrganizations(boolean view_organizations) {
        this.view_organizations = view_organizations;
    }

    public boolean isViewSegments() {
        return view_segments;
    }

    public void setViewSegments(boolean view_segments) {
        this.view_segments = view_segments;
    }

    public boolean isLinkSaml() {
        return link_saml;
    }

    public void setLinkSaml(boolean link_saml) {
        this.link_saml = link_saml;
    }

    public Map<String, Integer> getPermissionsList() {
        return permissionList;
    }

    public void setPermissionList(Map<String, Integer> permissions) {
        this.permissionList = permissions;
    }

    public Permissions getPermissions() {
        return permissions;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    public void setEntityname(String eName) {
        this.entityName = eName;
    }

    @Override
    public Form getForm() {

        Form userForm = new Form();

        if (this.getCreatorId() > 0) {
            userForm.param("creator_id", String.valueOf(this.getCreatorId()));
        }
        if (this.getFirstName() != null) {
            userForm.param("first_name", this.getFirstName());
        }
        if (this.getLastName() != null) {
            userForm.param("last_name", this.getLastName());
        }

        if (this.getPassword() != null) {
            userForm.param("password", this.getPassword());
        }

        if (this.getPhone() != null) {
            userForm.param("phone", this.getPhone());
        }
        if (this.getRole() != null) {
            userForm.param("role", this.getRole());
        }
        if (this.getScope() != null) {
            userForm.param("scope", this.getScope());
        }

        if (this.getTitle() != null) {
            userForm.param("title", this.getTitle());
        }
        if (this.getType() != null) {
            userForm.param("type", this.getType());
        }
        if (this.getUsername() != null) {
            userForm.param("username", this.getUsername());
        }
        if (this.getVersion() >= 0) {
            userForm.param("version", String.valueOf(this.getVersion()));
        }

        if (permissionList != null && permissionList.isEmpty()) {
            userForm.param("access_internal_fees", Utility.getOneOrZero(this.isAccessInternalFees()));
            userForm.param("active", String.valueOf(this.isActive()));
            userForm.param("edit_campaigns", Utility.getOneOrZero(this.isEditCampaigns()));
            userForm.param("edit_data_definition", Utility.getOneOrZero(this.isEditDataDefinition()));
            userForm.param("edit_margins_and_performance", Utility.getOneOrZero(this.isEditMarginsAndPerformance()));
            userForm.param("edit_segments", Utility.getOneOrZero(this.isEditSegments()));
            userForm.param("labs_enable_rmx", Utility.getOneOrZero(this.isLabsEnableRmx()));
            userForm.param("link_ldap", Utility.getOneOrZero(this.isLinkLdap()));
            userForm.param("view_data_definition", Utility.getOneOrZero(this.isViewDataDefinition()));
            userForm.param("view_dmp_reports", Utility.getOneOrZero(this.isViewDmpReports()));
            userForm.param("view_organizations", Utility.getOneOrZero(this.isViewOrganizations()));
            userForm.param("view_segments", Utility.getOneOrZero(this.isViewSegments()));
        }

        // set permissions
        if (permissionList != null && !permissionList.isEmpty()) {
            for (String key : permissionList.keySet()) {
                if (permissionList.get(key) != null) {
                    userForm.param(key, String.valueOf(permissionList.get(key)));
                }
            }
        }

        return Utility.getFilteredForm(userForm, "user");
    }
}
