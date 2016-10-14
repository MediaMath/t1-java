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
import com.mediamath.terminalone.models.Segments;
import com.mediamath.terminalone.models.Strategy;
import com.mediamath.terminalone.models.Strategy.goalType;
import com.mediamath.terminalone.models.StrategyDomain;
import com.mediamath.terminalone.models.TargetValues;
import com.mediamath.terminalone.utils.Utility;

import java.text.SimpleDateFormat;
import java.util.TimeZone;
import javax.ws.rs.core.Form;

public class StrategyHelper {

  private static final String YYYY_MM_DDTHH_MM_SS_Z = "yyyy-MM-dd'T'HH:mm:ssZ";

  private static final SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DDTHH_MM_SS_Z);

  /**
   * validates required fields.
   * @param entity expects Strategy entity.
   * @throws T1Exception exception.
   */
  public static void validateRequiredFields(Strategy entity) throws T1Exception {
    /*
     * if(entity.getAudience_segment_exclude_op()==null){
     * entity.setAudience_segment_exclude_op(aud_seg_exc.OR); }
     * 
     * if(entity.getAudience_segment_include_op()==null){
     * entity.setAudience_segment_include_op(aud_seg_inc.OR); }
     * 
     * if(entity.getBid_aggresiveness()< 0 || entity.getBid_aggresiveness() > 100 ){
     * entity.setBid_aggresiveness(50.00f); } if(entity.getBudget() != null &&
     * entity.getBudget().getValue() < 0){ throw new
     * ValidationException("please enter Valid Budget"); } if (entity.getCampaign_id() <= 0) { throw
     * new ValidationException("please enter a valid campaign id"); }
     * 
     * if (entity.getGoal_type() == null){ throw new ValidationException("please enter Goal Type");
     * }
     * 
     * if (entity.getGoal_value() != null && entity.getGoal_value().getValue() <=0 &&
     * (entity.getGoal_type().equals(goal_type.cpa) || entity.getGoal_type().equals(goal_type.cpc)
     * || entity.getGoal_type().equals(goal_type.reach) ||
     * entity.getGoal_type().equals(goal_type.cpe)) ){ throw new
     * ValidationException("please enter Goal Value"); }
     * 
     * if (entity.getFrequency_type() == null){
     * entity.setFrequency_type(freq_type.valueOf("no_limit")); }
     * 
     * if (entity.getFrequency_type() != null &&
     * (entity.getFrequency_type().equals(freq_types.even)||
     * entity.getFrequency_type().equals(freq_types.asap))) {
     * if(entity.getFrequency_interval()==null){
     * entity.setFrequency_interval(freq_int.valueOf("not_applicable")); }
     * if(entity.getFrequency_amount() <= 0){ throw new
     * ValidationException("please enter valid a frequency amount"); } }
     * 
     * if (entity.getMax_bid() != null && entity.getMax_bid().getValue() <= 0) { throw new
     * ValidationException("please enter a valid Max Bid"); }
     * 
     * if (entity.getPacing_amount() != null && entity.getPacing_amount().getValue() <= 0) { throw
     * new ValidationException("please enter a valid Pacing Amount"); }
     * 
     * if(entity.getPacing_interval()==null){ entity.setPacing_interval(pac_int.day); }
     * 
     * if(entity.getPacing_type()==null){ entity.setPacing_type(pac_type.even); }
     * 
     * if(entity.getMedia_type()==null){ entity.setMedia_type(media_type.DISPLAY); }
     * 
     * if(entity.getGoal_type().equals(goal_type.roi) && entity.getRoi_target() != null &&
     * entity.getRoi_target().getValue() <=0){ throw new
     * ValidationException("please enter a ROI Target"); }
     * 
     * if (entity.getName() == null || entity.getName().isEmpty()) { throw new
     * ValidationException("please enter a name for the advertiser"); } else if
     * (entity.getName().length() > 64) { throw new
     * ValidationException("please make sure name does not exceed 64 characters."); }
     * 
     * 
     * if(entity.getSite_selectiveness()==null){ entity.setSite_selectiveness(site_select.REDUCED);
     * }
     * 
     * if(entity.getSupply_type()==null){ entity.setSupply_type(supply_type.RTB); }
     * 
     * if(entity.getType()==null){ throw new ValidationException("please Enter Type"); }
     * 
     * if(entity.getVersion() <= 0) { throw new ValidationException("please add version"); }
     * 
     * 
     */
  }

  /**
   * creates a Strategy Form object.
   * @param entity expects a Strategy entity.
   * @return Form object.
   */
  public static Form getForm(Strategy entity) {
    Form strategyForm = new Form();
    if (entity.getStrategyDomainRestrictions().size() <= 0) {
      if (entity.getAudienceSegmentExcludeOp() != null) {
        strategyForm.param("exclude_op", entity.getAudienceSegmentExcludeOp().toString());
      }
      if (entity.getAudienceSegmentIncludeOp() != null) {
        strategyForm.param("include_op", entity.getAudienceSegmentIncludeOp().toString());
      }
      if (entity.getBidAggresiveness() > 0f) {
        strategyForm.param("bid_aggressiveness", String.valueOf(entity.getBidAggresiveness()));
      }

      strategyForm.param("bid_price_is_media_only",
          Utility.getOneOrZero(entity.isBidPriceIsMediaOnly()));

      if (entity.getBudget() != null && entity.getBudget().size() > 0
          && entity.getBudget().get(0).getValue() > 0) {
        strategyForm.param("budget", String.valueOf(entity.getBudget().get(0).getValue()));
      }
      if (entity.getCampaignId() > 0) {
        strategyForm.param("campaign_id", String.valueOf(entity.getCampaignId()));
      }

      if (entity.getCreatedOn() != null) {
        strategyForm.param("created_on", entity.getCreatedOn());
      }

      if (entity.getId() > 0) {
        strategyForm.param("id", String.valueOf(entity.getId()));
      }

      if (entity.getDescription() != null) {
        strategyForm.param("description", entity.getDescription());
      }

      if (entity.getEffectiveGoalValue() != null && entity.getEffectiveGoalValue().size() > 0
          && entity.getEffectiveGoalValue().get(0).getValue() > 0) {
        strategyForm.param("effective_goal_value",
            String.valueOf(entity.getEffectiveGoalValue().get(0).getValue()));
      }

      if (entity.getFrequencyType() != null) {
        strategyForm.param("frequency_type", entity.getFrequencyType().toString());
      }

      if (entity.getFrequencyInterval() != null) {
        strategyForm.param("frequency_interval", String.valueOf(entity.getFrequencyInterval()));
      }

      if (entity.getFrequencyAmount() > 0) {
        strategyForm.param("frequency_amount", String.valueOf(entity.getFrequencyAmount()));
      }

      if (entity.getEndDate() != null && !entity.isUseCampaignEnd()) {
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String endDate = sdf.format(entity.getEndDate());
        strategyForm.param("end_date", endDate);
      }

      if (!entity.isUseCampaignStart() && entity.getStartDate() != null) {
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String startDate = sdf.format(entity.getStartDate());
        strategyForm.param("start_date", startDate);
      }

      if (entity.getGoalType() != null) {
        strategyForm.param("goal_type", String.valueOf(entity.getGoalType()));
      }

      if (entity.getGoalType() != null && entity.getGoalValue().size() > 0
          && entity.getGoalValue().get(0).getValue() > 0) {
        strategyForm.param("goal_value", String.valueOf(entity.getGoalValue().get(0).getValue()));
      }

      if (entity.getImpressionCap() > 0) {
        strategyForm.param("impression_cap", String.valueOf(entity.getImpressionCap()));
      }

      if (entity.getMaxBid() != null && entity.getMaxBid().size() > 0
          && entity.getMaxBid().get(0).getValue() > 0) {
        strategyForm.param("max_bid", String.valueOf(entity.getMaxBid().get(0).getValue()));
      }

      if (entity.getMediaType() != null) {
        strategyForm.param("media_type", String.valueOf(entity.getMediaType()));
      }
      if (entity.getName() != null) {
        strategyForm.param("name", entity.getName());
      }

      if (entity.getPacingAmount() != null && entity.getPacingAmount().size() > 0
          && entity.getPacingAmount().get(0).getValue() > 0) {
        strategyForm.param("pacing_amount",
            String.valueOf(entity.getPacingAmount().get(0).getValue()));
      }
      if (entity.getPacingInterval() != null) {
        strategyForm.param("pacing_interval", String.valueOf(entity.getPacingInterval()));
      }
      if (entity.getPacingType() != null) {
        strategyForm.param("pacing_type", String.valueOf(entity.getPacingType()));
      }

      StringBuffer pixelTargetExpression = new StringBuffer();
      StringBuffer includePixels = new StringBuffer();
      StringBuffer excludePixels = new StringBuffer();

      if (entity.getIncludePixels() != null && entity.getIncludePixels().size() > 0) {
        includePixels.append("(");
        int size = entity.getIncludePixels().size() - 1;
        for (Integer i : entity.getIncludePixels()) {
          if (size != 0) {
            includePixels.append("[" + String.valueOf(i) + "] AND ");
            size--;
          } else {
            includePixels.append("[" + String.valueOf(i) + "]");
          }
        }
        includePixels.append(")");
        pixelTargetExpression.append(includePixels.toString());
      }

      if (entity.getExcludePixels() != null && entity.getExcludePixels().size() > 0) {
        excludePixels.append("(");
        int size = entity.getExcludePixels().size() - 1;
        for (Integer i : entity.getExcludePixels()) {
          if (size != 0) {
            excludePixels.append("[" + String.valueOf(i) + "] OR ");
            size--;
          } else {
            excludePixels.append("[" + String.valueOf(i) + "]");
          }
        }
        excludePixels.append(")");

        if (pixelTargetExpression.length() > 0) {
          pixelTargetExpression.append(" AND NOT " + excludePixels.toString());
        }
      }

      if (pixelTargetExpression.length() > 0) {
        strategyForm.param("pixel_target_expr", pixelTargetExpression.toString());
      }

      if (entity.getRoiTarget() != null && entity.getRoiTarget().size() > 0
          && entity.getRoiTarget().get(0).getValue() > 0) {
        strategyForm.param("roi_target", String.valueOf(entity.getRoiTarget().get(0).getValue()));
      }

      strategyForm.param("run_on_all_exchanges",
          Utility.getOnOrOff(entity.isRunOnAllExchanges()));
      strategyForm.param("run_on_all_pmp", Utility.getOnOrOff(entity.isRunOnAllPmp()));
      strategyForm.param("run_on_display", Utility.getOnOrOff(entity.isRunOnDisplay()));
      strategyForm.param("run_on_mobile", Utility.getOnOrOff(entity.isRunOnMobile()));
      strategyForm.param("run_on_streaming", Utility.getOnOrOff(entity.isRunOnStreaming()));
      if (entity.getSiteSelectiveness() != null) {
        strategyForm.param("site_selectiveness", String.valueOf(entity.getSiteSelectiveness()));
      }

      strategyForm.param("site_restriction_transparent_urls",
          Utility.getOnOrOff(entity.isSiteRestrictionTransparentUrls()));
      strategyForm.param("use_campaign_start", Utility.getOnOrOff(entity.isUseCampaignStart()));
      strategyForm.param("use_campaign_end", Utility.getOnOrOff(entity.isUseCampaignEnd()));
      strategyForm.param("status", Utility.getOnOrOff(entity.isStatus()));

      if (entity.getSupplyType() != null) {
        strategyForm.param("supply_type", String.valueOf(entity.getSupplyType()));
      }

      strategyForm.param("use_mm_freq", Utility.getOnOrOff(false));
      if (entity.getGoalType() != null && !entity.getGoalType().equals(goalType.spend)) {
        strategyForm.param("use_optimization", Utility.getOnOrOff(entity.isUseOptimization()));
      }
      if (entity.getVersion() > 0) {
        strategyForm.param("version", String.valueOf(entity.getVersion()));
      }

      if (entity.getZoneName() != null) {
        strategyForm.param("zone_name", entity.getZoneName());
      }

      if (entity.getType() != null) {
        strategyForm.param("type", entity.getType().toString());
      }
    }
    // strategy domain restrictions
    if (entity.getStrategyDomainRestrictions().size() > 0) {
      int inc = 1;
      for (StrategyDomain sd : entity.getStrategyDomainRestrictions()) {
        if (sd != null) {
          strategyForm.param("domains." + String.valueOf(inc) + ".domain", sd.getDomain());
          strategyForm.param("domains." + String.valueOf(inc) + ".restriction",
              sd.getRestriction().name());
          inc++;
        }
      }

    }
    // strategy audio segments
    if (entity.getAudienceSegments().size() > 0 && entity.getAudienceSegmentExcludeOp() != null
        && entity.getAudienceSegmentIncludeOp() != null) {
      int inc = 1;
      for (Segments sd : entity.getAudienceSegments()) {
        if (sd != null) {
          strategyForm.param("segments." + String.valueOf(inc) + ".id", String.valueOf(sd.getId()));
          strategyForm.param("segments." + String.valueOf(inc) + ".restriction",
              sd.getRestriction().name());

          inc++;
        }
      }
    }
    // target values
    if (entity.getTargetValues().size() > 0) {
      int inc = 1;
      String valueIds = "";
      for (TargetValues tv : entity.getTargetValues()) {
        if (tv != null) {
          strategyForm.param("dimensions." + String.valueOf(inc) + ".code", tv.getCode().name());
          strategyForm.param("dimensions." + String.valueOf(inc) + ".restriction",
              tv.getRestriction().name());

          if (tv.getValueIds().size() > 0) {
            for (Integer vi : tv.getValueIds()) {
              valueIds += vi.toString();
            }
          }
          strategyForm.param("dimensions." + String.valueOf(inc) + ".value_ids", valueIds);

          inc++;
        }
      }
    }

    return strategyForm;

  }
}
