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

public class VendorDomain implements T1Entity {

  private static final String entityName = "VendorDomain";

  private boolean allow_subdomain_match;
  private Date created_on;
  private String domain;
  private int id;
  private Date updated_on;
  private int vendor_id;
  private int version;

  public boolean isAllowSubdomainMatch() {
    return allow_subdomain_match;
  }

  public void setAllowSubdomainMatch(boolean allow_subdomain_match) {
    this.allow_subdomain_match = allow_subdomain_match;
  }

  public Date getCreatedOn() {
    return created_on;
  }

  public void setCreatedOn(Date created_on) {
    this.created_on = created_on;
  }

  public String getDomain() {
    return domain;
  }

  public void setDomain(String domain) {
    this.domain = domain;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

}
