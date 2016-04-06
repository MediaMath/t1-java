package com.mediamath.jterminalone.models.helper;

import javax.ws.rs.core.Form;

import com.mediamath.jterminalone.Exceptions.T1Exception;
import com.mediamath.jterminalone.Exceptions.ValidationException;
import com.mediamath.jterminalone.models.Advertiser.freq_types;
import com.mediamath.jterminalone.models.Strategy;
import com.mediamath.jterminalone.models.Strategy.aud_seg_exc;
import com.mediamath.jterminalone.models.Strategy.aud_seg_inc;
import com.mediamath.jterminalone.models.Strategy.freq_int;
import com.mediamath.jterminalone.models.Strategy.freq_type;
import com.mediamath.jterminalone.models.Strategy.goal_type;
import com.mediamath.jterminalone.models.Strategy.media_type;
import com.mediamath.jterminalone.models.Strategy.pac_int;
import com.mediamath.jterminalone.models.Strategy.pac_type;
import com.mediamath.jterminalone.models.Strategy.site_selec;
import com.mediamath.jterminalone.models.Strategy.supply_type;
import com.mediamath.jterminalone.utils.Utility;

public class StrategyHelper {

	
	public static void validateFields() throws T1Exception {
		
	}
	
	public static void validateRequiredFields(Strategy entity) throws T1Exception {
		if(entity.getAudience_segment_exclude_op()==null){
			entity.setAudience_segment_exclude_op(aud_seg_exc.OR);
		}
		
		if(entity.getAudience_segment_include_op()==null){
			entity.setAudience_segment_include_op(aud_seg_inc.OR);
		}
		
		if(entity.getBid_aggresiveness()< 0 || entity.getBid_aggresiveness() > 100 ){
			entity.setBid_aggresiveness(50.00f);
		}
		if(entity.getBudget()< 0){
			throw new ValidationException("please enter Valid Budget");
		}
		if (entity.getCampaign_id() <= 0) {
			throw new ValidationException("please enter a valid campaign id");
		}
		
		if (entity.getGoal_type() == null){
			throw new ValidationException("please enter Goal Type");
		}
		
		if (entity.getGoal_value() <=0 && (entity.getGoal_type().equals(goal_type.cpa) || entity.getGoal_type().equals(goal_type.cpc) || entity.getGoal_type().equals(goal_type.reach) || entity.getGoal_type().equals(goal_type.cpe))){
			throw new ValidationException("please enter Goal Value");
		}
		
		if (entity.getFrequency_type() == null){
			entity.setFrequency_type(freq_type.valueOf("no_limit"));
		}
		
		if (entity.getFrequency_type() != null &&  (entity.getFrequency_type().equals(freq_types.even)|| entity.getFrequency_type().equals(freq_types.asap))) {
			if(entity.getFrequency_interval()==null){
				entity.setFrequency_interval(freq_int.valueOf("not_applicable"));
			}
			if(entity.getFrequency_amount()<=0){
				throw new ValidationException("please enter valid a frequency amount");
			}
		}
		
		if (entity.getMax_bid() <= 0) {
			throw new ValidationException("please enter a valid Max Bid");
		}
		
		if (entity.getPacing_amount() <= 0) {
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
		
		if(entity.getGoal_type().equals(goal_type.roi) && entity.getRoi_target()<=0){
			throw new ValidationException("please enter a ROI Target");
		}
		
		if (entity.getName() == null || entity.getName().isEmpty()) {
			throw new ValidationException("please enter a name for the advertiser");
		} else if (entity.getName().length() > 64) {
			throw new ValidationException("please make sure name does not exceed 64 characters.");
		}
		if(entity.getSite_selectiveness()==null){
			entity.setSite_selectiveness(site_selec.REDUCED);
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
		
		
	}
	
	public static Form getForm(Strategy entity) {
		Form strategyForm = new Form();
		strategyForm.param("audience_segment_exclude_op", entity.getAudience_segment_exclude_op().toString());
		strategyForm.param("audience_segment_include_op", entity.getAudience_segment_include_op().toString());
		strategyForm.param("bid_aggressiveness", String.valueOf(entity.getBid_aggresiveness()));
		strategyForm.param("bid_price_is_media_only", Utility.getOneOrZero(entity.isBid_price_is_media_only()));
		strategyForm.param("budget", String.valueOf(entity.getBudget()));
		strategyForm.param("campaign_id", String.valueOf(entity.getCampaign_id()));
		if(entity.getCreated_on() != null){
			strategyForm.param("created_on", entity.getCreated_on());
		}
		
		if(entity.getId() > 0) {
			strategyForm.param("id", String.valueOf(entity.getId()));
		}
		
		if(entity.getDescription() != null) {
			strategyForm.param("description", entity.getDescription());
		}
		
		if(entity.getEffective_goal_value() > 0) {
			strategyForm.param("effective_goal_value", String.valueOf(entity.getEffective_goal_value()));
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
			strategyForm.param("end_date", entity.getEnd_date());
		}
		
		if(entity.getGoal_type() != null){
		strategyForm.param("goal_type", String.valueOf(entity.getGoal_type()));
		}
		
		if(entity.getGoal_value() > 0){
			strategyForm.param("goal_value", String.valueOf(entity.getGoal_value()));
		}
		
		if(entity.getImpression_cap()> 0){
			strategyForm.param("impression_cap", String.valueOf(entity.getImpression_cap()));
		}
		
		strategyForm.param("max_bid", String.valueOf(entity.getMax_bid()));
		
		strategyForm.param("media_type", String.valueOf(entity.getMedia_type()));
		strategyForm.param("name", entity.getName());
		
		strategyForm.param("pacing_amount", String.valueOf(entity.getPacing_amount()));
		strategyForm.param("pacing_interval", String.valueOf(entity.getPacing_interval()));
		strategyForm.param("pacing_type", String.valueOf(entity.getPacing_type()));
		if(entity.getPixel_target_expr()!=null){
			strategyForm.param("pixel_target_expr", String.valueOf(entity.getPixel_target_expr()));
		}
		
		if(entity.getRoi_target()>0){
			strategyForm.param("roi_target", String.valueOf(entity.getRoi_target()));
		}
		
		strategyForm.param("max_bid", String.valueOf(entity.getMax_bid()));
		
		strategyForm.param("run_on_all_exchanges", Utility.getOnOrOff(entity.isRun_on_all_exchanges()));
		strategyForm.param("run_on_all_pmp", Utility.getOnOrOff(entity.isRun_on_all_pmp()));
		strategyForm.param("run_on_display", Utility.getOnOrOff(entity.isRun_on_display()));
		strategyForm.param("run_on_mobile", Utility.getOnOrOff(entity.isRun_on_mobile()));
		strategyForm.param("run_on_streaming", Utility.getOnOrOff(entity.isRun_on_streaming()));
		
		strategyForm.param("site_selectiveness", String.valueOf(entity.getSite_selectiveness()));
		strategyForm.param("site_restriction_transparent_urls", Utility.getOnOrOff(entity.isSite_restriction_transparent_urls()));
		strategyForm.param("use_campaign_start", Utility.getOnOrOff(entity.isUse_campaign_start()));
		if(!entity.isUse_campaign_start()){
			strategyForm.param("start_date", entity.getStart_date());
		}
		strategyForm.param("use_campaign_end", Utility.getOnOrOff(entity.isUse_campaign_end()));
		strategyForm.param("status", Utility.getOnOrOff(entity.isStatus()));
		
		strategyForm.param("supply_type", String.valueOf(entity.getSupply_type()));
		strategyForm.param("use_mm_freq", Utility.getOnOrOff(false));
		if(!entity.getGoal_type().equals(goal_type.spend)){
			strategyForm.param("use_optimization", Utility.getOnOrOff(entity.isUse_optimization()));
		}
		if(entity.getVersion() > 0) {
			strategyForm.param("version", String.valueOf(entity.getVersion()));
		}
		
		if(entity.getZone_name()!=null){
			strategyForm.param("zone_name", entity.getZone_name());
		}
		
		return strategyForm;
		
	}	
}
