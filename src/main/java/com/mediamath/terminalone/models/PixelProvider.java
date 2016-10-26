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

public class PixelProvider implements T1Entity {

  private static final String entityName = "PixelProvider";

  public enum executors {
    MEDIAMATH, UDI
  };

  private int agency_id;
  private Date created_on;
  private executors execution_by;
  private int id;
  private String name;
  private boolean status;
  private String taxonomy_file;
  private Date updated_on;
  private int vendor_id;
  private int version;

  private Agency agency;
  private Vendor vendor;

  public int getAgencyId() {
    return agency_id;
  }

  public void setAgencyId(int agency_id) {
    this.agency_id = agency_id;
  }

  public Date getCreatedOn() {
    return created_on;
  }

  public void setCreatedOn(Date created_on) {
    this.created_on = created_on;
  }

  public executors getExecutionBy() {
    return execution_by;
  }

  public void setExecutionBy(executors execution_by) {
    this.execution_by = execution_by;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public String getTaxonomyFile() {
    return taxonomy_file;
  }

  public void setTaxonomyFile(String taxonomy_file) {
    this.taxonomy_file = taxonomy_file;
  }

  public Date getUpdatedOn() {
    return updated_on;
  }

  public void setUpdatedOn(Date updated_on) {
    this.updated_on = updated_on;
  }

  public int getVendorId() {
    return vendor_id;
  }

  public void setVendorId(int vendor_id) {
    this.vendor_id = vendor_id;
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

  public Agency getAgency() {
    return agency;
  }

  public void setAgency(Agency agency) {
    this.agency = agency;
  }

  public Vendor getVendor() {
    return vendor;
  }

  public void setVendor(Vendor vendor) {
    this.vendor = vendor;
  }

}
