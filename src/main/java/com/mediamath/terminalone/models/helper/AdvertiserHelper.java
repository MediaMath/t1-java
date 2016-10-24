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

import javax.ws.rs.core.Form;

import com.mediamath.terminalone.exceptions.T1Exception;
import com.mediamath.terminalone.exceptions.ValidationException;
import com.mediamath.terminalone.models.Advertiser;
import com.mediamath.terminalone.models.Advertiser.dmpSettings;
import com.mediamath.terminalone.models.Advertiser.freqInts;
import com.mediamath.terminalone.models.Advertiser.freqTypes;
import com.mediamath.terminalone.utils.Utility;



public class AdvertiserHelper {

  /**
   * Validates required fields for Advertiser entity.
   * @param entity expects Advertiser Entity.
   * @throws T1Exception is thrown 
   */
  public static void validateRequiredFields(Advertiser entity) throws T1Exception {
    
    if (entity.getName() == null || entity.getName().isEmpty()) {
      throw new ValidationException("please enter a name for the advertiser");
    } else if (entity.getName().length() > 64) {
      throw new ValidationException("please make sure name does not exceed 64 characters.");
    }

    if (entity.getDomain() == null || entity.getDomain().isEmpty()) {
      throw new ValidationException("please enter a Domain for the advertiser");
    } else if (entity.getDomain().length() > 255) {
      throw new ValidationException("please make sure domain does not exceed 255 characters.");
    }

    if (entity.getDmpEnabled() == null) {
      entity.setDmpEnabled(dmpSettings.disabled);
    }

    if (entity.getFrequencyType() == null) {
      entity.setFrequencyType(freqTypes.valueOf("no_limit"));
    }

    if (entity.getFrequencyType() != null &&  (entity.getFrequencyType().equals(freqTypes.even) || entity.getFrequencyType().equals(freqTypes.asap))) {
      if (entity.getFrequencyInterval() == null) {
        entity.setFrequencyInterval(freqInts.valueOf("not_applicable"));
      }
      if (entity.getFrequencyAmount() <= 0) {
        throw new ValidationException("please enter valid a frequency amount");
      }
    }

    if (entity.getAdServerId() <= 0) {
      throw new ValidationException("please enter a valid ad_server id");
    }
    
    if (entity.getAgencyId() <= 0) {
      throw new ValidationException("please enter a valid Agency id");
    }

    if (entity.getVerticalId() <= 0) {
      throw new ValidationException("please enter a valid Vertical id");
    }
  }

  /**
   * creates a Form object to pass it to connection
   * @param entity expects an Advertiser Entity.
   * @return Form object.
   */
  public static Form getForm(Advertiser entity) {
    Form advertiserForm = new Form();

    //required
    advertiserForm.param("name", entity.getName());

    advertiserForm.param("ad_server_id", String.valueOf(entity.getAdServerId()));

    //optional
    advertiserForm.param("allow_x_strat_optimization", Utility.getOnOrOff(entity.isAllowXStratOptimization()));

    advertiserForm.param("agency_id", String.valueOf(entity.getAgencyId()));

    if (entity.getBillingContactId() > 0) {
      advertiserForm.param("billing_contact_id", String.valueOf(entity.getBillingContactId()));
    }

    if (entity.getDomain() != null) {
      advertiserForm.param("domain", entity.getDomain());
    }

    if (entity.getCreatedOn() != null) {
      advertiserForm.param("created_on", entity.getCreatedOn().toString());
    }

    if (entity.getFrequencyType() != null) {
      advertiserForm.param("frequency_type", entity.getFrequencyType().toString());
    }

    if (entity.getFrequencyInterval() != null) {
      advertiserForm.param("frequency_interval", String.valueOf(entity.getFrequencyInterval()));
    }

    if (entity.getFrequencyAmount() > 0) {
      advertiserForm.param("frequency_amount", String.valueOf(entity.getFrequencyAmount()));
    }

    advertiserForm.param("minimize_multi_ads",Utility.getOnOrOff(entity.isMinimizeMultiAds()));

    if (entity.getSalesContactId() > 0) {
      advertiserForm.param("sales_contact_id", String.valueOf(entity.getSalesContactId()));
    }

    advertiserForm.param("status", Utility.getOnOrOff(entity.isStatus()));

    if (entity.getVersion() >= 0) {
      advertiserForm.param("version", String.valueOf(entity.getVersion()));
    }

    if (entity.getVerticalId() > 0) {
      advertiserForm.param("vertical_id", String.valueOf(entity.getVerticalId()));
    }

    if (entity.getUpdatedOn() != null) {
      advertiserForm.param("updated_on", String.valueOf(entity.getUpdatedOn()));
    }

    Form finalAdvertiserForm = Utility.getFilteredForm(advertiserForm, "advertiser");
    
    return finalAdvertiserForm;
  }

}
