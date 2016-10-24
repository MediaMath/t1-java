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

import java.text.SimpleDateFormat;

import javax.ws.rs.core.Form;

import com.mediamath.terminalone.models.Campaign;
import com.mediamath.terminalone.utils.Utility;

public class CampaignHelper  {

  private static final String YYYY_MM_DDTHH_MM_SS_Z = "yyyy-MM-dd'T'HH:mm:ss Z";

  private static final SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DDTHH_MM_SS_Z);

  /**
   * creates a Campaign Form object.
   * @param entity expects Campaign
   * @return Form object.
   */
  public static Form getForm(Campaign entity) {

    Form campaignForm = new Form();

    campaignForm.param("name", entity.getName());

    if (entity.getAdServerFee().size() > 0) { 
      campaignForm.param("ad_server_fee", String.valueOf(entity.getAdServerFee().get(0).getValue()));
    }

    if (entity.getTotalBudget().size() > 0) {
      campaignForm.param("total_budget", String.valueOf(entity.getTotalBudget().get(0).getValue()));
    }

    if (entity.getSpendCapAmount().size() > 0) {
      campaignForm.param("spend_cap_amount", String.valueOf(entity.getSpendCapAmount().get(0).getValue()));
    }

    if (entity.getGoalValue().size() > 0) {
      campaignForm.param("goal_value", String.valueOf(entity.getGoalValue().get(0).getValue()));
    }

    if (entity.getAdServerId() >= 0) {
      campaignForm.param("ad_server_id", String.valueOf(entity.getAdServerId()));
    }

    if (entity.getAdServerId() > 0) {
      campaignForm.param("advertiser_id", String.valueOf(entity.getAdvertiserId()));
    }

    campaignForm.param("conversion_type", entity.getConversionType());

    campaignForm.param("conversion_variable_minutes", String.valueOf(entity.getConversionVariableMinutes()));

    if (entity.getEndDate() != null) {
      String endDate = sdf.format(entity.getEndDate());
      campaignForm.param("end_date", endDate);
    }

    campaignForm.param("goal_type", String.valueOf(entity.getGoalType()));

    campaignForm.param("service_type", String.valueOf(entity.getServiceType()));

    if (entity.getStartDate() != null) {
      String startDate = sdf.format(entity.getStartDate());
      campaignForm.param("start_date", startDate);
    }

    campaignForm.param("use_mm_freq", Utility.getOnOrOff(entity.isUseMmFreq()));

    if (entity.getAdServerPassword() != null) {
      campaignForm.param("ad_server_password", entity.getAdServerPassword());
    }

    if (entity.getAdServerUsername() != null) {
      campaignForm.param("ad_server_username", entity.getAdServerUsername());
    }

    if (entity.getAgencyFeePct() > 0) {
      campaignForm.param("agency_fee_pct", String.valueOf(entity.getAgencyFeePct()));
    }

    if (entity.getCurrencyCode() != null) {
      campaignForm.param("currency_code", entity.getCurrencyCode());
    }

    campaignForm.param("dcs_data_is_campaign_level", Utility.getOnOrOff(entity.isDcsDataIsCampaignLevel()));

    // check null
    if (entity.getFrequencyAmount() >= 0) {
      campaignForm.param("frequency_amount", String.valueOf(entity.getFrequencyAmount()));
    }

    if (entity.getFrequencyInterval() != null) {
      campaignForm.param("frequency_interval", String.valueOf(entity.getFrequencyInterval()));
    }

    if (entity.getFrequencyType() != null ) {
      campaignForm.param("frequency_type", String.valueOf(entity.getFrequencyType()));
    }

    if (entity.getGoalAlert() >= 0) {
      campaignForm.param("goal_alert", String.valueOf(entity.getGoalAlert()));
    }

    if (entity.getGoalCategory() != null) {
      campaignForm.param("goal_category", String.valueOf(entity.getGoalCategory()));
    }

    campaignForm.param("has_custom_attribution", Utility.getOnOrOff(entity.isHasCustomAttribution()));

    if (entity.getIoName() != null) {
      campaignForm.param("io_name", entity.getIoName());
    }

    if (entity.getIoReferenceNum() != null) {
      campaignForm.param("io_reference_num", entity.getIoReferenceNum());
    }

    if (entity.getInitialStartDate() != null) {
      campaignForm.param("initial_start_date", String.valueOf(entity.getInitialStartDate()));
    }

    if (entity.getMarginPct() >= 0) {
      campaignForm.param("margin_pct", String.valueOf(entity.getMarginPct()));
    }

    if (entity.getMeritPixelId() > 0) {
      campaignForm.param("merit_pixel_id", String.valueOf(entity.getMeritPixelId()));
    }

    if (entity.getPcWindowMinutes() > 0) {
      campaignForm.param("pc_window_minutes", String.valueOf(entity.getPcWindowMinutes()));
    }

    if (entity.getPvPct() > 0) {
      campaignForm.param("pv_pct", String.valueOf(entity.getPvPct()));
    }

    if (entity.getPvWindowMinutes() > 0) {
      campaignForm.param("pv_window_minutes", String.valueOf(entity.getPvWindowMinutes()));
    }

    campaignForm.param("spend_cap_automatic", Utility.getOnOrOff(entity.isSpendCapAutomatic()));

    campaignForm.param("spend_cap_enabled", Utility.getOnOrOff(entity.isSpendCapEnabled()));

    campaignForm.param("use_default_ad_server", Utility.getOnOrOff(entity.isUseDefaultAdServer()));

    if (entity.getZoneName() != null) {
      campaignForm.param("zone_name", entity.getZoneName());
    }

    if (entity.getCreatedOn() != null) {
      campaignForm.param("created_on", entity.getCreatedOn().toString());
    }

    if (entity.getId() > 0) {
      campaignForm.param("id", String.valueOf(entity.getId()));
    }

    campaignForm.param("status", Utility.getOnOrOff(entity.isStatus()));

    if (entity.getVersion() > 0) {
      campaignForm.param("version", String.valueOf(entity.getVersion()));
    }

    if (entity.getUpdatedOn() != null) {
      campaignForm.param("updated_on", String.valueOf(entity.getUpdatedOn()));
    }
    
    Form finalCampaignForm = Utility.getFilteredForm(campaignForm, "campaign");

    return finalCampaignForm;
  }
}
