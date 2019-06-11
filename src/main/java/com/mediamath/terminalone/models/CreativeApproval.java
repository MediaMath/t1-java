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

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.javers.core.metamodel.annotation.DiffIgnore;

import java.util.Date;

import javax.ws.rs.core.Form;

public class CreativeApproval implements T1Entity {

  private static final String entityName = "CreativeApproval";

  private String additional_detail;
  private String approval_status;
  private int atomic_creative_id;
  private Date created_on;
  private String deal_status;
  private String deal_status_rejected_reason;
  private String entity_type;
  private int creative_import_file_id;
  private String external_identifier;
  private int id;
  private String name;
  private String rejected_reason;
  private int supply_source_id;
  private Date updated_on;
  private int version;

  public String getAdditionalDetail() {
    return additional_detail;
  }

  public void setAdditionalDetail(String additional_detail) {
    this.additional_detail = additional_detail;
  }

  public String getApprovalStatus() {
    return approval_status;
  }

  public void setApprovalStatus(String approval_status) {
    this.approval_status = approval_status;
  }

  public int getAtomicCreativeId() {
    return atomic_creative_id;
  }

  public void setAtomicCreativeId(int atomic_creative_id) {
    this.atomic_creative_id = atomic_creative_id;
  }

  public Date getCreatedOn() {
    return created_on;
  }

  public void setCreatedOn(Date created_on) {
    this.created_on = created_on;
  }

  public int getCreativeImportFileId() {
    return creative_import_file_id;
  }

  public void setCreativeImportFileId(int creative_import_file_id) {
    this.creative_import_file_id = creative_import_file_id;
  }

  public String getExternalIdentifier() {
    return external_identifier;
  }

  public void setExternalIdentifier(String external_identifier) {
    this.external_identifier = external_identifier;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getRejectedReason() {
    return rejected_reason;
  }

  public void setRejectedReason(String rejected_reason) {
    this.rejected_reason = rejected_reason;
  }

  public int getSupplySourceId() {
    return supply_source_id;
  }

  public void setSupplySourceId(int supply_source_id) {
    this.supply_source_id = supply_source_id;
  }

  public Date getUpdatedOn() {
    return updated_on;
  }

  public void setUpdatedOn(Date updated_on) {
    this.updated_on = updated_on;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public String getDealStatus() {
    return deal_status;
  }

  public void setDealStatus(String deal_status) {
    this.deal_status = deal_status;
  }

  public String getEntityType() {
	return entity_type;
}

public void setEntityType(String entity_type) {
	this.entity_type = entity_type;
}

public String getDealStatusRejectedReason() {
    return deal_status_rejected_reason;
  }

  public void setDealStatusRejectedReason(String deal_status_rejected_reason) {
    this.deal_status_rejected_reason = deal_status_rejected_reason;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getEntityname() {
    return entityName;
  }

  @Override
  @DiffIgnore
  @JsonIgnore
  public Form getForm() {
    return null;
  }

  @Override
  @DiffIgnore
  @JsonIgnore
  public String getUri() {
    return null;
  }

}
