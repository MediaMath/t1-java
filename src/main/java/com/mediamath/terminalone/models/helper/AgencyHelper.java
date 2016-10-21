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

package com.mediamath.terminalone.models.helper;


import java.util.List;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import com.mediamath.terminalone.models.Agency;
import com.mediamath.terminalone.service.T1Service;
import com.mediamath.terminalone.utils.Utility;


public class AgencyHelper  {
  
  /**
   * creates a Agency Form object.
   * 
   * @param entity expects Agency Entity.
   * @return Form object.
   */
  public static Form getForm(Agency entity) {
    
    String readOnlyFields = T1Service.getEntityReadOnlyFields().getProperty("agency");
    String requiredFields = T1Service.getEntityReadOnlyFields().getProperty("agencyRequired");
    
    List<String> readOnlyFieldList = Utility.getList(readOnlyFields);
    List<String> requiredFieldList = Utility.getList(requiredFields);

    Form agencyForm = new Form();

    //required
    agencyForm.param("name", entity.getName());

    agencyForm.param("organization_id", String.valueOf(entity.getOrganizationId()));

    //optional
    agencyForm.param("allow_x_adv_optimization", Utility.getOnOrOff(entity.isAllowXAdvOptimization()));

    agencyForm.param("allow_x_adv_pixels", Utility.getOnOrOff(entity.isAllowXAdvPixels()));

    if (entity.getBillingContactId() > 0) {
      agencyForm.param("billing_contact_id", String.valueOf(entity.getBillingContactId()));
    }

    if (entity.getDmpEnabled() != null) {
      agencyForm.param("dmp_enabled", entity.getDmpEnabled());
    }

    if (entity.getCreatedOn() != null) {
      agencyForm.param("created_on", entity.getCreatedOn().toString());
    }

    if (entity.getId() > 0) {
      agencyForm.param("id", String.valueOf(entity.getId()));
    }

    if  (entity.getLogo() != null) {
      agencyForm.param("logo", entity.getLogo());
    }

    if (entity.getSalesContactId() > 0) {
      agencyForm.param("sales_contact_id", String.valueOf(entity.getSalesContactId()));
    }

    agencyForm.param("status", Utility.getOnOrOff(entity.isStatus()));

    if (entity.getVersion() >= 0) {
      agencyForm.param("version", String.valueOf(entity.getVersion()));
    }

    if (entity.getTrafficContactId() > 0) {
      agencyForm.param("traffic_contact_id", String.valueOf(entity.getTrafficContactId()));
    }

    if (entity.getUpdatedOn() != null) {
      agencyForm.param("updated_on", String.valueOf(entity.getUpdatedOn()));
    }

    MultivaluedMap<String, String> multiValMap = agencyForm.asMap();
    
    for(String str : readOnlyFieldList) {
      if(multiValMap.containsKey(str)) {
        multiValMap.remove(str);
      }
    }
    
    Form finalAgencyForm = new Form(multiValMap);
    
    return finalAgencyForm;
  }

}
