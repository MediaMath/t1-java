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

public class CreativeApproval implements T1Entity {
	
	private static final String entityName = "CreativeApproval";

	private String additional_detail;
	private String approval_status;
    private int atomic_creative_id;
    private Date created_on;
    private int creative_import_file_id;
    private String external_identifier;
    private int id;
    private String rejected_reason;
    private int supply_source_id;
    private Date updated_on;
    private int version;
	public String getAdditional_detail() {
		return additional_detail;
	}
	public void setAdditional_detail(String additional_detail) {
		this.additional_detail = additional_detail;
	}
	public String getApproval_status() {
		return approval_status;
	}
	public void setApproval_status(String approval_status) {
		this.approval_status = approval_status;
	}
	public int getAtomic_creative_id() {
		return atomic_creative_id;
	}
	public void setAtomic_creative_id(int atomic_creative_id) {
		this.atomic_creative_id = atomic_creative_id;
	}
	public Date getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
	public int getCreative_import_file_id() {
		return creative_import_file_id;
	}
	public void setCreative_import_file_id(int creative_import_file_id) {
		this.creative_import_file_id = creative_import_file_id;
	}
	public String getExternal_identifier() {
		return external_identifier;
	}
	public void setExternal_identifier(String external_identifier) {
		this.external_identifier = external_identifier;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRejected_reason() {
		return rejected_reason;
	}
	public void setRejected_reason(String rejected_reason) {
		this.rejected_reason = rejected_reason;
	}
	public int getSupply_source_id() {
		return supply_source_id;
	}
	public void setSupply_source_id(int supply_source_id) {
		this.supply_source_id = supply_source_id;
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
