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
import java.util.TimeZone;

import javax.ws.rs.core.Form;

import com.mediamath.terminalone.models.BulkStrategy;
import com.mediamath.terminalone.models.Segments;
import com.mediamath.terminalone.models.SiteList;
import com.mediamath.terminalone.models.Strategy;
import com.mediamath.terminalone.models.Strategy.goalType;
import com.mediamath.terminalone.models.StrategyDayPart;
import com.mediamath.terminalone.models.StrategyDomain;
import com.mediamath.terminalone.models.StrategyTargetingSegment;
import com.mediamath.terminalone.models.TargetValues;
import com.mediamath.terminalone.utils.Utility;

public class StrategyHelper {

	private static final String INCLUDE_OP = "include_op";
	private static final String EXCLUDE_OP = "exclude_op";
	private static final String STRATEGIES = "strategies.";
	private static final String DAY_PARTS = "day_parts.";
	private static final String DIMENSIONS = "dimensions.";
	private static final String SEGMENTS = "segments.";
	private static final String RESTRICTION = ".restriction";

	private StrategyHelper() {
		throw new IllegalAccessError("StrategyHelper cannot be instantiated");
	}

	/**
	 * creates a Strategy Form object.
	 * 
	 * @param entity
	 *            expects a Strategy entity.
	 * @return Form object.
	 */
	public static Form getForm(Strategy entity) {
		final String YYYY_MM_DDTHH_MM_SS_Z = "yyyy-MM-dd'T'HH:mm:ssZ";
		final SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DDTHH_MM_SS_Z);

		Form strategyForm = new Form();

		if (entity.getAudienceSegmentExcludeOp() != null && !entity.getAudienceSegments().isEmpty()) {
			strategyForm.param(EXCLUDE_OP, entity.getAudienceSegmentExcludeOp().toString());
		}

		if (entity.getAudienceSegmentIncludeOp() != null && !entity.getAudienceSegments().isEmpty()) {
			strategyForm.param(INCLUDE_OP, entity.getAudienceSegmentIncludeOp().toString());
		}

		if (entity.getTargetingSegmentExcludeOp() != null && !entity.getStrategyTargetingSegments().isEmpty()) {
			strategyForm.param(EXCLUDE_OP, String.valueOf(entity.getTargetingSegmentExcludeOp()));
		}

		if (entity.getTargetingSegmentIncludeOp() != null && !entity.getStrategyTargetingSegments().isEmpty()) {
			strategyForm.param(INCLUDE_OP, String.valueOf(entity.getTargetingSegmentIncludeOp()));
		}

		if (entity.getTargetValues().isEmpty() && entity.getStrategyDomainRestrictions().isEmpty()
				&& entity.getAudienceSegments().isEmpty() && entity.getStrategyTargetingSegments().isEmpty()
				&& entity.getSiteLists().isEmpty() && entity.getDealIds().isEmpty()
				&& entity.getStrategyDayParts().isEmpty() && !entity.isCopyStrategy()
				&& (entity.getFromCampaignId() <= 0 && entity.getToCampaignId() <= 0)) {

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

			if (entity.getAudienceSegmentExcludeOp() != null) {
				strategyForm.param("audience_segment_exclude_op", entity.getAudienceSegmentExcludeOp().toString());
			}

			if (entity.getAudienceSegmentIncludeOp() != null) {
				strategyForm.param("audience_segment_include_op", entity.getAudienceSegmentIncludeOp().toString());
			}

			if (entity.getTargetingSegmentExcludeOp() != null) {
				strategyForm.param("targeting_segment_exclude_op",
						String.valueOf(entity.getTargetingSegmentExcludeOp()));
			}

			if (entity.getTargetingSegmentIncludeOp() != null) {
				strategyForm.param("targeting_segment_include_op",
						String.valueOf(entity.getTargetingSegmentIncludeOp()));
			}

			if (entity.getBidAggresiveness() > 0f) {
				strategyForm.param("bid_aggressiveness", String.valueOf(entity.getBidAggresiveness()));
			}

			if (entity.getBudget() != null && !entity.getBudget().isEmpty()
					&& entity.getBudget().get(0).getValue() > 0) {
				strategyForm.param("budget", String.valueOf(entity.getBudget().get(0).getValue()));
			}
			if (entity.getCampaignId() > 0) {
				strategyForm.param("campaign_id", String.valueOf(entity.getCampaignId()));
			}

			if (entity.getCreatedOn() != null) {
				strategyForm.param("created_on", entity.getCreatedOn());
			}

			if (entity.getDescription() != null) {
				strategyForm.param("description", entity.getDescription());
			}

			if (entity.getEffectiveGoalValue() != null && !entity.getEffectiveGoalValue().isEmpty()
					&& entity.getEffectiveGoalValue().get(0).getValue() > 0) {
				strategyForm.param("effective_goal_value",
						String.valueOf(entity.getEffectiveGoalValue().get(0).getValue()));
			}

			if (entity.getFrequencyType() != null) {
				strategyForm.param("frequency_type", entity.getFrequencyType().getValue());
			}

			if (entity.getFrequencyInterval() != null) {
				strategyForm.param("frequency_interval", String.valueOf(entity.getFrequencyInterval().getValue()));
			}

			if (entity.getFrequencyAmount() > 0) {
				strategyForm.param("frequency_amount", String.valueOf(entity.getFrequencyAmount()));
			}

			if (entity.getGoalType() != null) {
				strategyForm.param("goal_type", String.valueOf(entity.getGoalType().getValue()));
			}

			if (entity.getGoalType() != null && !entity.getGoalValue().isEmpty()
					&& entity.getGoalValue().get(0).getValue() > 0) {
				strategyForm.param("goal_value", String.valueOf(entity.getGoalValue().get(0).getValue()));
			}

			if (entity.getMaxBid() != null && !entity.getMaxBid().isEmpty()
					&& entity.getMaxBid().get(0).getValue() > 0) {
				strategyForm.param("max_bid", String.valueOf(entity.getMaxBid().get(0).getValue()));
			}

			if (entity.getMinBid() != null && !entity.getMinBid().isEmpty()
					&& entity.getMinBid().get(0).getValue() > 0) {
				strategyForm.param("min_bid", String.valueOf(entity.getMinBid().get(0).getValue()));
			}

			if (entity.getMediaType() != null) {
				strategyForm.param("media_type", String.valueOf(entity.getMediaType()));
			}
			if (entity.getName() != null) {
				strategyForm.param("name", entity.getName());
			}

			if (entity.getPacingAmount() != null && !entity.getPacingAmount().isEmpty()
					&& entity.getPacingAmount().get(0).getValue() > 0) {
				strategyForm.param("pacing_amount", String.valueOf(entity.getPacingAmount().get(0).getValue()));
			}
			if (entity.getPacingInterval() != null) {
				strategyForm.param("pacing_interval", String.valueOf(entity.getPacingInterval().getValue()));
			}
			if (entity.getPacingType() != null) {
				strategyForm.param("pacing_type", String.valueOf(entity.getPacingType().getValue()));
			}

			if (entity.getImpressionCapType() != null) {
				strategyForm.param("impression_cap_type", String.valueOf(entity.getImpressionCapType().getValue()));
			}

			if (entity.getImpressionPacingInterval() != null) {
				strategyForm.param("impression_pacing_interval", String.valueOf(entity.getImpressionPacingInterval().getValue()));
			}

			if (entity.getImpressionPacingType() != null) {
				strategyForm.param("impression_pacing_type", String.valueOf(entity.getImpressionPacingType().getValue()));
			}

			if (entity.getImpressionPacingAmount() > 0) {
				strategyForm.param("impression_pacing_amount", String.valueOf(entity.getImpressionPacingAmount()));
			}

			StringBuilder pixelTargetExpression = new StringBuilder();
			StringBuilder includePixels = new StringBuilder();
			StringBuilder excludePixels = new StringBuilder();

			if (entity.getIncludePixels() != null && !entity.getIncludePixels().isEmpty()) {
				includePixels.append("(");
				int size = entity.getIncludePixels().size() - 1;
				for (Integer i : entity.getIncludePixels()) {
					if (size != 0) {
						includePixels.append("[" + i + "] AND ");
						size--;
					} else {
						includePixels.append("[" + i + "]");
					}
				}
				includePixels.append(")");
				pixelTargetExpression.append(includePixels.toString());
			}

			if (entity.getExcludePixels() != null && !entity.getExcludePixels().isEmpty()) {
				excludePixels.append("(");
				int size = entity.getExcludePixels().size() - 1;
				for (Integer i : entity.getExcludePixels()) {
					if (size != 0) {
						excludePixels.append("[" + i + "] OR ");
						size--;
					} else {
						excludePixels.append("[" + i + "]");
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

			if (entity.getRoiTarget() != null && !entity.getRoiTarget().isEmpty()
					&& entity.getRoiTarget().get(0).getValue() > 0) {
				strategyForm.param("roi_target", String.valueOf(entity.getRoiTarget().get(0).getValue()));
			}

			strategyForm.param("frequency_optimization", Utility.getOnOrOff(entity.isFrequencyOptimization()));
			strategyForm.param("bid_price_is_media_only", Utility.getOneOrZero(entity.isBidPriceIsMediaOnly()));
			strategyForm.param("run_on_all_exchanges", Utility.getOnOrOff(entity.isRunOnAllExchanges()));
			strategyForm.param("run_on_all_pmp", Utility.getOnOrOff(entity.isRunOnAllPmp()));
			strategyForm.param("run_on_display", Utility.getOnOrOff(entity.isRunOnDisplay()));
			strategyForm.param("run_on_mobile", Utility.getOnOrOff(entity.isRunOnMobile()));
			strategyForm.param("run_on_streaming", Utility.getOnOrOff(entity.isRunOnStreaming()));
			strategyForm.param("site_restriction_transparent_urls",
					Utility.getOnOrOff(entity.isSiteRestrictionTransparentUrls()));
			strategyForm.param("use_campaign_start", Utility.getOneOrZero(entity.isUseCampaignStart()));
			strategyForm.param("use_campaign_end", Utility.getOneOrZero(entity.isUseCampaignEnd()));
			strategyForm.param("status", Utility.getOnOrOff(entity.isStatus()));
			strategyForm.param("use_mm_freq", Utility.getOnOrOff(false));

			if (entity.getSiteSelectiveness() != null) {
				strategyForm.param("site_selectiveness", String.valueOf(entity.getSiteSelectiveness()));
			}

			if (entity.getSupplyType() != null) {
				strategyForm.param("supply_type", String.valueOf(entity.getSupplyType()));
			}

			if (entity.getGoalType() != null && !entity.getGoalType().equals(goalType.spend)) {
				strategyForm.param("use_optimization", Utility.getOnOrOff(entity.isUseOptimization()));
			}

			if (entity.getVersion() >= 0) {
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
		if (!entity.getStrategyDomainRestrictions().isEmpty()) {
			int inc = 1;
			for (StrategyDomain sd : entity.getStrategyDomainRestrictions()) {
				if (sd != null) {
					strategyForm.param("domains." + inc + ".domain", sd.getDomain());
					strategyForm.param("domains." + inc + RESTRICTION, sd.getRestriction().name());
					inc++;
				}
			}
		}

		// strategy audience segments
		if (!entity.getAudienceSegments().isEmpty() && entity.getAudienceSegmentExcludeOp() != null
				&& entity.getAudienceSegmentIncludeOp() != null) {
			int inc = 1;
			for (Segments sd : entity.getAudienceSegments()) {
				if (sd != null) {
					strategyForm.param(SEGMENTS + inc + ".id", String.valueOf(sd.getId()));
					strategyForm.param(SEGMENTS + inc + RESTRICTION, sd.getRestriction().name());
					inc++;
				}
			}
		}

		// strategy Targeting segments
		if (!entity.getStrategyTargetingSegments().isEmpty() && entity.getTargetingSegmentExcludeOp() != null
				&& entity.getTargetingSegmentIncludeOp() != null) {
			int inc = 1;
			for (StrategyTargetingSegment sd : entity.getStrategyTargetingSegments()) {
				if (sd != null) {
					strategyForm.param(SEGMENTS + inc + ".id", String.valueOf(sd.getTargetingSegmentId()));
					strategyForm.param(SEGMENTS + inc + RESTRICTION, sd.getRestriction());
					if(sd.getUserCpm()!=null && !sd.getUserCpm().isEmpty()){
						strategyForm.param(SEGMENTS + inc + ".user_cpm", String.valueOf(sd.getUserCpm().get(0).getValue()));
					}
					strategyForm.param(SEGMENTS + inc + ".operator", sd.getOperator());
					inc++;
				}
			}
		}

		// target values
		if (!entity.getTargetValues().isEmpty()) {
			int inc = 1;
			
			for (TargetValues tv : entity.getTargetValues()) {
				if (tv != null) {
					strategyForm.param(DIMENSIONS + inc + ".code", tv.getCode().name());
					strategyForm.param(DIMENSIONS + inc + RESTRICTION, tv.getRestriction().name());
					int cntr = 1;
					StringBuilder valueIds = new StringBuilder();
					for (Integer vi : tv.getValueIds()) {
						valueIds.append(vi.toString());
						if (tv.getValueIds().size() != cntr) {
							valueIds.append(",");
						}
						cntr++;
					}
					strategyForm.param(DIMENSIONS + inc + ".value_ids", valueIds.toString());
					strategyForm.param(DIMENSIONS + inc + ".operation", tv.getOperation().toString());
					inc++;
				}
			}
		}

		// site lists
		if (!entity.getSiteLists().isEmpty()) {
			int inc = 1;
			for (SiteList sl : entity.getSiteLists()) {
				if (sl != null && sl.getId() > 0) {
					strategyForm.param("site_lists." + inc + ".id", String.valueOf(sl.getId()));
					strategyForm.param("site_lists." + inc + ".assigned", Utility.getOneOrZero(sl.isAssigned()));
					inc++;
				}
				
			}
		}

		// Deals
		if (!entity.getDealIds().isEmpty()) {
			int inc = 1;
			for (Integer sl : entity.getDealIds()) {
				if (sl != null) {
					strategyForm.param("deal." + inc + ".id", String.valueOf(sl));
					inc++;
				}
				
			}
		}

		// Strategy Day Parts
		if (!entity.getStrategyDayParts().isEmpty()) {
			int inc = 1;
			for (StrategyDayPart sdp : entity.getStrategyDayParts()) {
				if (sdp != null) {
					strategyForm.param(DAY_PARTS + inc + ".start_hour", String.valueOf(sdp.getStartHour()));
					strategyForm.param(DAY_PARTS + inc + ".end_hour", String.valueOf(sdp.getEndHour()));
					strategyForm.param(DAY_PARTS + inc + ".days", String.valueOf(sdp.getDays()));
					strategyForm.param(DAY_PARTS + inc + ".user_time", Utility.getOneOrZero(sdp.isUserTime()));
					inc++;
				}
			}
		}

		// Target Dimensions -> target values
		if (entity.getTargetDimensions() != null) {
			if (!entity.getTargetDimensions().getInclude().isEmpty()) {
				for (Integer i : entity.getTargetDimensions().getInclude()) {
					strategyForm.param("include", String.valueOf(i));
				}
			}
			if (!entity.getTargetDimensions().getExclude().isEmpty()) {
				for (Integer i : entity.getTargetDimensions().getExclude()) {
					strategyForm.param("exclude", String.valueOf(i));
				}
			}

			if (entity.getTargetDimensions().getExcludeOp() != null) {
				strategyForm.param(EXCLUDE_OP, entity.getTargetDimensions().getExcludeOp().toString());
			}

			if (entity.getTargetDimensions().getIncludeOp() != null) {
				strategyForm.param(INCLUDE_OP, entity.getTargetDimensions().getIncludeOp().toString());
			}
		}

		// Strategy Bulk Copy
		if (entity.getFromCampaignId() > 0) {
			strategyForm.param("from_campaign_id", String.valueOf(entity.getFromCampaignId()));
		}

		if (entity.getToCampaignId() > 0) {
			strategyForm.param("to_campaign_id", String.valueOf(entity.getToCampaignId()));
		}

		if (entity.getFromCampaignId() > 0 && entity.getToCampaignId() > 0 && !entity.getBulkStrategy().isEmpty()) {
			int inc = 1;
			for (BulkStrategy bk : entity.getBulkStrategy()) {
				if(bk!=null){
					if (bk.getId() > 0) {
						strategyForm.param(STRATEGIES + inc + ".id", String.valueOf(bk.getId()));
					}
					if (bk.getName() != null) {
						strategyForm.param(STRATEGIES + inc + ".name", bk.getName());
					}
	
					strategyForm.param(STRATEGIES + inc + ".supply_management",
							Utility.getOneOrZero(bk.isSupplyManagement()));
					strategyForm.param(STRATEGIES + inc + ".contextual_targeting",
							Utility.getOneOrZero(bk.isContextualTargeting()));
					strategyForm.param(STRATEGIES + inc + ".creative_assignment",
							Utility.getOneOrZero(bk.isCreativeAssignment()));
					strategyForm.param(STRATEGIES + inc + ".pixel_targeting",
							Utility.getOneOrZero(bk.isPixelTargeting()));
					strategyForm.param(STRATEGIES + inc + ".audience_targeting",
							Utility.getOneOrZero(bk.isAudienceTargeting()));
					strategyForm.param(STRATEGIES + inc + ".strategy_targeting",
							Utility.getOneOrZero(bk.isStrategyTargeting()));
					strategyForm.param(STRATEGIES + inc + ".site_lists", Utility.getOneOrZero(bk.isSiteLists()));
					strategyForm.param(STRATEGIES + inc + ".video_targeting",
							Utility.getOneOrZero(bk.isVideoTargeting()));
					inc++;
				}
			}

		}

		return Utility.getFilteredForm(strategyForm, "strategy");
	}
}
