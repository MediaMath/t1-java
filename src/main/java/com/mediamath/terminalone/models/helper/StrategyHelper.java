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

import com.mediamath.terminalone.Exceptions.T1Exception;
import com.mediamath.terminalone.Exceptions.ValidationException;
import com.mediamath.terminalone.models.Advertiser.freq_types;
import com.mediamath.terminalone.models.Segments;
import com.mediamath.terminalone.models.Strategy;
import com.mediamath.terminalone.models.Strategy.aud_seg_exc;
import com.mediamath.terminalone.models.Strategy.aud_seg_inc;
import com.mediamath.terminalone.models.Strategy.freq_int;
import com.mediamath.terminalone.models.Strategy.freq_type;
import com.mediamath.terminalone.models.Strategy.goal_type;
import com.mediamath.terminalone.models.Strategy.media_type;
import com.mediamath.terminalone.models.Strategy.pac_int;
import com.mediamath.terminalone.models.Strategy.pac_type;
import com.mediamath.terminalone.models.Strategy.site_select;
import com.mediamath.terminalone.models.Strategy.supply_type;
import com.mediamath.terminalone.models.StrategyDomain;
import com.mediamath.terminalone.models.TargetValues;
import com.mediamath.terminalone.utils.Utility;

public class StrategyHelper {

	private static final String YYYY_MM_DDTHH_MM_SS_Z = "yyyy-MM-dd'T'HH:mm:ssZ";
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DDTHH_MM_SS_Z);
	
	public static void validateFields() throws T1Exception {
		
	}
	
	public static void validateRequiredFields(Strategy entity) throws T1Exception {/*
		if(entity.getAudience_segment_exclude_op()==null){
			entity.setAudience_segment_exclude_op(aud_seg_exc.OR);
		}
		
		if(entity.getAudience_segment_include_op()==null){
			entity.setAudience_segment_include_op(aud_seg_inc.OR);
		}
		
		if(entity.getBid_aggresiveness()< 0 || entity.getBid_aggresiveness() > 100 ){
			entity.setBid_aggresiveness(50.00f);
		}
		if(entity.getBudget() != null && entity.getBudget().getValue() < 0){
			throw new ValidationException("please enter Valid Budget");
		}
		if (entity.getCampaign_id() <= 0) {
			throw new ValidationException("please enter a valid campaign id");
		}
		
		if (entity.getGoal_type() == null){
			throw new ValidationException("please enter Goal Type");
		}
		
		if (entity.getGoal_value() != null 
				&& entity.getGoal_value().getValue() <=0 
				&& (entity.getGoal_type().equals(goal_type.cpa) 
						|| entity.getGoal_type().equals(goal_type.cpc) 
						|| entity.getGoal_type().equals(goal_type.reach) 
						|| entity.getGoal_type().equals(goal_type.cpe))
		){
			throw new ValidationException("please enter Goal Value");
		}
		
		if (entity.getFrequency_type() == null){
			entity.setFrequency_type(freq_type.valueOf("no_limit"));
		}
		
		if (entity.getFrequency_type() != null &&  (entity.getFrequency_type().equals(freq_types.even)|| entity.getFrequency_type().equals(freq_types.asap))) {
			if(entity.getFrequency_interval()==null){
				entity.setFrequency_interval(freq_int.valueOf("not_applicable"));
			}
			if(entity.getFrequency_amount() <= 0){
				throw new ValidationException("please enter valid a frequency amount");
			}
		}
		
		if (entity.getMax_bid() != null && entity.getMax_bid().getValue() <= 0) {
			throw new ValidationException("please enter a valid Max Bid");
		}
		
		if (entity.getPacing_amount() != null && entity.getPacing_amount().getValue() <= 0) {
			throw new ValidationException("please enter a valid Pacing Amount");
		}
		
		if(entity.getPacing_interval()==null){
			entity.setPacing_interval(pac_int.day);
		}
		
		if(entity.getPacing_type()==null){
			entity.setPacing_type(pac_type.even);
		}
		
		if(entity.getMedia_type()==null){
			entity.setMedia_type(media_type.DISPLAY);
		}
		
		if(entity.getGoal_type().equals(goal_type.roi) && entity.getRoi_target() != null && entity.getRoi_target().getValue() <=0){
			throw new ValidationException("please enter a ROI Target");
		}
		
		if (entity.getName() == null || entity.getName().isEmpty()) {
			throw new ValidationException("please enter a name for the advertiser");
		} else if (entity.getName().length() > 64) {
			throw new ValidationException("please make sure name does not exceed 64 characters.");
		}
		
		
		if(entity.getSite_selectiveness()==null){
			entity.setSite_selectiveness(site_select.REDUCED);
		}
		
		if(entity.getSupply_type()==null){
			entity.setSupply_type(supply_type.RTB);
		}
		
		if(entity.getType()==null){
			throw new ValidationException("please Enter Type");
		}
		
		if(entity.getVersion() <= 0) {
			throw new ValidationException("please add version");
		}
		
		
	*/}
	
	public static Form getForm(Strategy entity) {
		Form strategyForm = new Form();
		if(entity.getStrategy_domain_restrictions().size() <=0){
			if(entity.getAudience_segment_exclude_op()!=null){
				strategyForm.param("exclude_op", entity.getAudience_segment_exclude_op().toString());
			}
			if(entity.getAudience_segment_include_op()!=null){
				strategyForm.param("include_op", entity.getAudience_segment_include_op().toString());
			}
			if(entity.getBid_aggresiveness()>0f){
				strategyForm.param("bid_aggressiveness", String.valueOf(entity.getBid_aggresiveness()));
			}
	
			strategyForm.param("bid_price_is_media_only", Utility.getOneOrZero(entity.isBid_price_is_media_only()));
			
			if(entity.getBudget() != null && entity.getBudget().size() > 0 && entity.getBudget().get(0).getValue() > 0){
				strategyForm.param("budget", String.valueOf(entity.getBudget().get(0).getValue()));
			}
			if(entity.getCampaign_id()>0){
				strategyForm.param("campaign_id", String.valueOf(entity.getCampaign_id()));
			}
			
			if(entity.getCreated_on() != null){
				strategyForm.param("created_on", entity.getCreated_on());
			}
			
			if(entity.getId() > 0) {
				strategyForm.param("id", String.valueOf(entity.getId()));
			}
			
			if(entity.getDescription() != null) {
				strategyForm.param("description", entity.getDescription());
			}
			
			if(entity.getEffective_goal_value() != null && entity.getEffective_goal_value().size() > 0 && entity.getEffective_goal_value().get(0).getValue() > 0) {
				strategyForm.param("effective_goal_value", String.valueOf(entity.getEffective_goal_value().get(0).getValue()));
			}
			
			if(entity.getFrequency_type() != null) {
				strategyForm.param("frequency_type", entity.getFrequency_type().toString());
			}
			
			if(entity.getFrequency_interval()!=null) {
				strategyForm.param("frequency_interval", String.valueOf(entity.getFrequency_interval()));
			}
			
			if(entity.getFrequency_amount() > 0) {
				strategyForm.param("frequency_amount", String.valueOf(entity.getFrequency_amount()));
			}
			
			if(entity.getEnd_date() != null && !entity.isUse_campaign_end()){
				sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
				String endDate = sdf.format(entity.getEnd_date());
				strategyForm.param("end_date", endDate);
			}
			
			if(!entity.isUse_campaign_start() && entity.getStart_date() != null){
				sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
				String startDate = sdf.format(entity.getStart_date());
				strategyForm.param("start_date", startDate);
			}
			
			if(entity.getGoal_type() != null){
			strategyForm.param("goal_type", String.valueOf(entity.getGoal_type()));
			}
			
			if(entity.getGoal_type() != null && entity.getGoal_value().size() > 0 && entity.getGoal_value().get(0).getValue() > 0){
				strategyForm.param("goal_value", String.valueOf(entity.getGoal_value().get(0).getValue()));
			}
			
			if(entity.getImpression_cap()> 0){
				strategyForm.param("impression_cap", String.valueOf(entity.getImpression_cap()));
			}
	
			if(entity.getMax_bid() != null && entity.getMax_bid().size() > 0 && entity.getMax_bid().get(0).getValue() > 0){
				strategyForm.param("max_bid", String.valueOf(entity.getMax_bid().get(0).getValue()));
			}
			
			if(entity.getMedia_type()!=null){
				strategyForm.param("media_type", String.valueOf(entity.getMedia_type()));
			}
			if(entity.getName()!=null){
				strategyForm.param("name", entity.getName());
			}
			
			if(entity.getPacing_amount() != null && entity.getPacing_amount().size() > 0 && entity.getPacing_amount().get(0).getValue() > 0){
				strategyForm.param("pacing_amount", String.valueOf(entity.getPacing_amount().get(0).getValue()));
			}
			if(entity.getPacing_interval()!=null){
				strategyForm.param("pacing_interval", String.valueOf(entity.getPacing_interval()));
			}
			if(entity.getPacing_type()!=null){
			strategyForm.param("pacing_type", String.valueOf(entity.getPacing_type()));
			}
			
			if(entity.getPixel_target_expr()!=null){
				strategyForm.param("pixel_target_expr", String.valueOf(entity.getPixel_target_expr()));
			}
			
			if(entity.getRoi_target() != null && entity.getRoi_target().size() > 0 && entity.getRoi_target().get(0).getValue() > 0){
				strategyForm.param("roi_target", String.valueOf(entity.getRoi_target().get(0).getValue()));
			}

			strategyForm.param("run_on_all_exchanges", Utility.getOnOrOff(entity.isRun_on_all_exchanges()));
			strategyForm.param("run_on_all_pmp", Utility.getOnOrOff(entity.isRun_on_all_pmp()));
			strategyForm.param("run_on_display", Utility.getOnOrOff(entity.isRun_on_display()));
			strategyForm.param("run_on_mobile", Utility.getOnOrOff(entity.isRun_on_mobile()));
			strategyForm.param("run_on_streaming", Utility.getOnOrOff(entity.isRun_on_streaming()));
			if(entity.getSite_selectiveness()!=null){
				strategyForm.param("site_selectiveness", String.valueOf(entity.getSite_selectiveness()));
			}
			
			strategyForm.param("site_restriction_transparent_urls", Utility.getOnOrOff(entity.isSite_restriction_transparent_urls()));
			strategyForm.param("use_campaign_start", Utility.getOnOrOff(entity.isUse_campaign_start()));
			strategyForm.param("use_campaign_end", Utility.getOnOrOff(entity.isUse_campaign_end()));
			strategyForm.param("status", Utility.getOnOrOff(entity.isStatus()));
			
			if(entity.getSupply_type()!=null){
				strategyForm.param("supply_type", String.valueOf(entity.getSupply_type()));
			}
			
			strategyForm.param("use_mm_freq", Utility.getOnOrOff(false));
			if(entity.getGoal_type()!=null && !entity.getGoal_type().equals(goal_type.spend)){
				strategyForm.param("use_optimization", Utility.getOnOrOff(entity.isUse_optimization()));
			}
			if(entity.getVersion() > 0) {
				strategyForm.param("version", String.valueOf(entity.getVersion()));
			}
			
			if(entity.getZone_name()!=null){
				strategyForm.param("zone_name", entity.getZone_name());
			}
			
			if(entity.getType()!=null){
				strategyForm.param("type", entity.getType().toString());
			}
		}
		//strategy domain restrictions
		if(entity.getStrategy_domain_restrictions().size()>0){
			int i=1;
			for(StrategyDomain sd: entity.getStrategy_domain_restrictions()){
				if(sd!=null){
					strategyForm.param("domains."+String.valueOf(i)+".domain", sd.getDomain());
					strategyForm.param("domains."+String.valueOf(i)+".restriction", sd.getRestriction().name());
					i++;
				}
			}
			
		}
		//strategy audio segments
		if(entity.getAudience_segments().size()>0 && entity.getAudience_segment_exclude_op()!=null && entity.getAudience_segment_include_op()!=null){
			int i=1;
			for(Segments sd: entity.getAudience_segments()){
				if(sd!=null){
					strategyForm.param("segments."+String.valueOf(i)+".id", String.valueOf(sd.getId()));
					strategyForm.param("segments."+String.valueOf(i)+".restriction", sd.getRestriction().name());
					
					i++;
				}
			}
		}
		//target values
		if(entity.getTarget_values().size()>0){
			int i=1;
			String valueIds = "";
			for(TargetValues tv: entity.getTarget_values()){
				if(tv!=null){
					strategyForm.param("dimensions."+String.valueOf(i)+".code", tv.getCode().name());
					strategyForm.param("dimensions."+String.valueOf(i)+".restriction", tv.getRestriction().name());
					
					if(tv.getValue_ids().size()>0){
						for(Integer vi: tv.getValue_ids()){
							valueIds+=vi.toString();
						}
					}
					strategyForm.param("dimensions."+String.valueOf(i)+".value_ids", valueIds);
					
					i++;
				}
			}
		}

		
		
		
		return strategyForm;
		
	}	
}
