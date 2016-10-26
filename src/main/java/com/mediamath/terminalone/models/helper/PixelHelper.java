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

import com.mediamath.terminalone.models.Pixel;
import com.mediamath.terminalone.utils.Utility;


public class PixelHelper {

  /**
   * Creates a Pixel Form Object.
   * 
   * @param entity expects a Pixel Entity.
   * @return Form object
   */
  public static Form getForm(Pixel entity) {

    Form pixelForm = new Form();

    if (entity.getAdvertiserId() > 0) {
      pixelForm.param("advertiser_id", String.valueOf(entity.getAdvertiserId()));
    }
    if (entity.getAgencyId() > 0) {
      pixelForm.param("agency_id", String.valueOf(entity.getAgencyId()));
    }
    if (entity.getProviderId() > 0) {
      pixelForm.param("provider_id", String.valueOf(entity.getProviderId()));
    }
    if (entity.getCostCpm() > 0f) {
      pixelForm.param("cost_cpm", String.valueOf(entity.getCostCpm()));
    }
    if (entity.getCostPctCpm() > 0f) {
      pixelForm.param("cost_pct_cpm", String.valueOf(entity.getCostPctCpm()));
    }
    if (entity.getCostCpts() > 0f) {
      pixelForm.param("cost_cpts", String.valueOf(entity.getCostCpts()));
    }
    if (entity.getCreatedOn() != null) {
      pixelForm.param("created_on", entity.getCreatedOn().toString());
    }
    if (entity.getCurrency() != null) {
      pixelForm.param("currency", entity.getCurrency().toString());
    }
    if (entity.getCurrencyFixed() != null) {
      pixelForm.param("currency_fixed", entity.getCurrencyFixed());
    }

    if (entity.getRevenue() != null) {
      pixelForm.param("revenue", entity.getRevenue().toString());
    }
    pixelForm.param("eligible", Utility.getOnOrOff(entity.isEligible()));
    if (entity.getExternalIdentifier() != null) {
      pixelForm.param("external_identifier", entity.getExternalIdentifier());
    }
    if (entity.getKeywords() != null) {
      pixelForm.param("keywords", entity.getKeywords());
    }

    if (entity.getName() != null) {
      pixelForm.param("name", entity.getName());
    }

    if (entity.getPixelType() != null) {
      pixelForm.param("pixel_type", entity.getPixelType().toString());
    }

    if (entity.getPricing() != null) {
      pixelForm.param("pricing", entity.getPricing().toString());
    }
    if (entity.getRmxConversionMinutes() > 0) {
      pixelForm.param("rmx_conversion_minutes", String.valueOf(entity.getRmxConversionMinutes()));
    }

    if (entity.getRmxConversionType() != null) {
      pixelForm.param("rmx_conversion_type", entity.getRmxConversionType().toString());
    }

    pixelForm.param("rmx_friendly", Utility.getOnOrOff(entity.isRmxFriendly()));
    pixelForm.param("rmx_merit", Utility.getOnOrOff(entity.isRmxMerit()));

    if (entity.getRmxPcWindowMinutes() > 0) {
      pixelForm.param("rmx_pc_window_minutes", String.valueOf(entity.getRmxPcWindowMinutes()));
    }

    if (entity.getRmxPvWindowMinutes() > 0) {
      pixelForm.param("rmx_pv_window_minutes", String.valueOf(entity.getRmxPvWindowMinutes()));
    }

    if (entity.getSegmentOp() != null) {
      pixelForm.param("segment_op", entity.getSegmentOp());
    }
    pixelForm.param("status", Utility.getOnOrOff(entity.isStatus()));

    if (entity.getTagType() != null) {
      pixelForm.param("tag_type", entity.getTagType().toString());
    }
    if (entity.getTags() != null) {
      pixelForm.param("tags", entity.getTags());
    }
    if (entity.getType() != null) {
      pixelForm.param("type", entity.getType());
    }
    if (entity.getVersion() >= 0) {
      pixelForm.param("version", String.valueOf(entity.getVersion()));
    }

    Form finalAdvertiserForm = Utility.getFilteredForm(pixelForm, "pixelbundle");
    
    return finalAdvertiserForm;
  }
}
