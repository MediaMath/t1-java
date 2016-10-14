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

import com.mediamath.terminalone.exceptions.T1Exception;
import com.mediamath.terminalone.exceptions.ValidationException;
import com.mediamath.terminalone.models.Organization;
import com.mediamath.terminalone.utils.Utility;

import javax.ws.rs.core.Form;

public class OrganizationHelper {

  /**
   * validates required fields for organization entity.
   * 
   * @param entity
   *          expects Organization entity.
   * @throws T1Exception
   *           throws T1Excpetion.
   */
  public static void validateRequiredFields(Organization entity) throws T1Exception {
    if (entity.getName() == null || entity.getName().isEmpty()) {
      throw new ValidationException("please enter a name for the sitelist");
    } else if (entity.getName().length() > 64) {
      throw new ValidationException("please make sure name does not exceed 64 characters.");
    }

    if (entity.getAddress1() == null || entity.getAddress1().isEmpty()) {
      throw new ValidationException("please enter a address 1 for the sitelist");
    } else if (entity.getAddress1().length() > 256) {
      throw new ValidationException("please make sure address 1 does not exceed 256 characters.");
    }

    if (entity.getCity() == null || entity.getCity().isEmpty()) {
      throw new ValidationException("please enter a city for the sitelist");
    } else if (entity.getCity().length() > 32) {
      throw new ValidationException("please make sure city does not exceed 32 characters.");
    }

    if (entity.getState() == null || entity.getState().isEmpty()) {
      throw new ValidationException("please enter a State for the sitelist");
    } else if (entity.getState().length() > 2) {
      throw new ValidationException("please make sure State does not exceed 2 characters.");
    }

    if (entity.getZip() == null || entity.getZip().isEmpty()) {
      throw new ValidationException("please enter a Zip for the sitelist");
    } else if (entity.getZip().length() > 10) {
      throw new ValidationException("please make sure Zip does not exceed 10 characters.");
    }

    if (entity.getCountry() == null || entity.getCountry().isEmpty()) {
      throw new ValidationException("please enter a Country for the sitelist");
    } else if (entity.getCountry().length() > 2) {
      throw new ValidationException("please make sure Country does not exceed 32 characters.");
    }

    if (entity.getPhone() == null || entity.getPhone().isEmpty()) {
      throw new ValidationException("please enter a Phone Number for the sitelist");
    } else if (entity.getPhone().length() > 24) {
      throw new ValidationException("please make sure Phone Number does not exceed 24 characters.");
    }

    if (entity.getMmContactName() == null || entity.getMmContactName().isEmpty()) {
      throw new ValidationException("please enter a Contact Name for the sitelist");
    } else if (entity.getMmContactName().length() > 64) {
      throw new ValidationException("please make sure Contact Name does not exceed 64 characters.");
    }

    if (entity.getCurencyCode() == null) {
      entity.setCurencyCode("USD");
    }

    if (entity.getAdxSeatAccountId() < 0) {
      entity.setAdxSeatAccountId(41519752);
    }

    if (entity.getBillingCountryCode() == null) {
      entity.setBillingCountryCode("US");
      ;
    }

    if (entity.getSuspiciousTrafficFilterLevel() < 0
        || entity.getSuspiciousTrafficFilterLevel() > 100) {
      entity.setSuspiciousTrafficFilterLevel(25);
    }

    if (entity.getOrgType().size() <= 0) {
      entity.getOrgType().add("buyer");
    }

  }

  /**
   * Creates a Organization Form object.
   * 
   * @param entity
   *          expects Organization entity.
   * @return Form object.
   */
  public static Form getForm(Organization entity) {

    Form orgForm = new Form();
    if (entity.getName() != null) {
      orgForm.param("name", entity.getName());
    }
    orgForm.param("status", Utility.getOnOrOff(entity.isStatus()));

    if (entity.getContactName() != null) {
      orgForm.param("contact_name", entity.getContactName());
    }

    if (entity.getAddress1() != null) {
      orgForm.param("address_1", entity.getAddress1());
    }
    if (entity.getAddress2() != null) {
      orgForm.param("address_2", entity.getAddress2());
    }
    if (entity.getCity() != null) {
      orgForm.param("city", entity.getCity());
    }
    if (entity.getState() != null) {
      orgForm.param("state", entity.getState());
    }
    if (entity.getZip() != null) {
      orgForm.param("zip", entity.getZip());
    }
    if (entity.getCountry() != null) {
      orgForm.param("country", entity.getCountry());
    }
    if (entity.getPhone() != null) {
      orgForm.param("phone", entity.getPhone());
    }
    if (entity.getMmContactName() != null) {
      orgForm.param("mm_contact_name", entity.getMmContactName());
    }

    orgForm.param("allow_x_agency_pixels", Utility.getOnOrOff(entity.isAllowXAgencyPixels()));
    orgForm.param("use_evidon_optout", Utility.getOnOrOff(entity.isUseEvidonOptout()));
    orgForm.param("allow_byo_price", Utility.getOnOrOff(entity.isAllowByoPrice()));
    if (entity.getCurencyCode() != null) {
      orgForm.param("currency_code", entity.getCurencyCode());
    }

    orgForm.param("adx_seat_account_id", String.valueOf(entity.getAdxSeatAccountId()));
    if (entity.getBillingCountryCode() != null) {
      orgForm.param("billing_country_code", entity.getBillingCountryCode());
    }

    orgForm.param("override_suspicious_traffic_filter",
        Utility.getOnOrOff(entity.isOverrideSuspiciousTrafficFilter()));
    if (entity.getSuspiciousTrafficFilterLevel() > 0) {
      orgForm.param("suspicious_traffic_filter_level",
          String.valueOf(entity.getSuspiciousTrafficFilterLevel()));
    }

    // TODO check how to pass array to form
    orgForm.param("org_type",
        (entity.getOrgType().size() > 0) ? entity.getOrgType().get(0).toString() : "buyer");

    return orgForm;
  }
}
