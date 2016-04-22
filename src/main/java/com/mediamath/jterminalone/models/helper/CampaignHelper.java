package com.mediamath.jterminalone.models.helper;

import java.text.SimpleDateFormat;

import javax.ws.rs.core.Form;

import com.mediamath.jterminalone.Exceptions.T1Exception;
import com.mediamath.jterminalone.models.Campaign;
import com.mediamath.jterminalone.utils.Utility;

public class CampaignHelper  {

	private static final String YYYY_MM_DDTHH_MM_SS_Z = "yyyy-MM-dd'T'HH:mm:ss Z";
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DDTHH_MM_SS_Z);

	public static void validateFields() throws T1Exception {
/*		if (sales_contact_id < 0) {
			throw new ValidationException("please enter a valid sales contact id");
		}
*/
	}

	public static void validateRequiredFields() throws T1Exception {
/*		if (name == null || name.isEmpty()) {
			throw new ValidationException("please enter a name for the agency");
		} else if (name.length() > 64) {
			throw new ValidationException("please make sure name does not exceed 64 characters.");
		}
		if (organization_id == 0 || organization_id <= 0) {
			throw new ValidationException("please enter a valid organization id");
		}*/
		
	}

	public static Form getForm(Campaign entity) {

		Form campaignForm = new Form();

		campaignForm.param("name", entity.getName());
		
		if(entity.getAd_server_fee() >= 0) { 
			campaignForm.param("ad_server_fee", String.valueOf(entity.getAd_server_fee()));
		}
		
		if(entity.getAd_server_id() >= 0) {
			campaignForm.param("ad_server_id", String.valueOf(entity.getAd_server_id()));	
		}
		
		if(entity.getAd_server_id() > 0) {
			campaignForm.param("advertiser_id", String.valueOf(entity.getAdvertiser_id()));
		}
		
		campaignForm.param("conversion_type", entity.getConversion_type());
		
		campaignForm.param("conversion_variable_minutes", String.valueOf(entity.getConversion_variable_minutes()));

		if(entity.getEnd_date() != null) {
			String endDate = sdf.format(entity.getEnd_date());
			campaignForm.param("end_date", endDate);
		}
		
		campaignForm.param("goal_type", String.valueOf(entity.getGoal_type()));		
		
		campaignForm.param("goal_value", String.valueOf(entity.getGoal_value()));
		
		campaignForm.param("service_type", String.valueOf(entity.getService_type()));
		
		
		if(entity.getStart_date() != null) {
			String startDate = sdf.format(entity.getStart_date());
			campaignForm.param("start_date", startDate);
		}
		
		//campaignForm.param("start_date", "2016-04-23T15:28:05+0000");
		
		campaignForm.param("total_budget", String.valueOf(entity.getTotal_budget()));
		
		campaignForm.param("use_mm_freq", Utility.getOnOrOff(entity.isUse_mm_freq()));
		
		if(entity.getAd_server_password() != null) {
			campaignForm.param("ad_server_password", entity.getAd_server_password());
		}
		
		if(entity.getAd_server_username() != null) {
			campaignForm.param("ad_server_username", entity.getAd_server_username());
		}
		
		if(entity.getAgency_fee_pct() > 0) {
			campaignForm.param("agency_fee_pct", String.valueOf(entity.getAgency_fee_pct()));
		}
		
		if(entity.getCurrency_code() != null) {
			campaignForm.param("currency_code", entity.getCurrency_code());
		}
		
		campaignForm.param("dcs_data_is_campaign_level", Utility.getOnOrOff(entity.isDcs_data_is_campaign_level()));
		
		// check null
		if(entity.getFrequency_amount() >= 0) {
			campaignForm.param("frequency_amount", String.valueOf(entity.getFrequency_amount()));
		}
		
		if(entity.getFrequency_interval() != null){
			campaignForm.param("frequency_interval", String.valueOf(entity.getFrequency_interval()));
		}
		
		if(entity.getFrequency_type() != null ) {
			campaignForm.param("frequency_type", String.valueOf(entity.getFrequency_type()));
		}
		
		if(entity.getGoal_alert() >= 0) {
			campaignForm.param("goal_alert", String.valueOf(entity.getGoal_alert()));
		}
		
		if(entity.getGoal_category() != null) {
			campaignForm.param("goal_category", String.valueOf(entity.getGoal_category()));
		}
		
		campaignForm.param("has_custom_attribution", Utility.getOnOrOff(entity.isHas_custom_attribution()));
		
		if(entity.getIo_name() != null) {
			campaignForm.param("io_name", entity.getIo_name());
		}
		
		if(entity.getIo_reference_num() != null) {
			campaignForm.param("io_reference_num", entity.getIo_reference_num());
		}
		
		if(entity.getInitial_start_date() != null) {
			campaignForm.param("initial_start_date", String.valueOf(entity.getInitial_start_date()));
		}
		
		if(entity.getMargin_pct() >= 0) {
			campaignForm.param("margin_pct", String.valueOf(entity.getMargin_pct()));
		}
		
		if(entity.getMerit_pixel_id() > 0) {
			campaignForm.param("merit_pixel_id", String.valueOf(entity.getMerit_pixel_id()));
		}
		
		if(entity.getPc_window_minutes() > 0) {
			campaignForm.param("pc_window_minutes", String.valueOf(entity.getPc_window_minutes()));
		}
		
		if(entity.getPv_pct() > 0) {
			campaignForm.param("pv_pct", String.valueOf(entity.getPv_pct()));
		}
		
		if(entity.getPv_window_minutes() > 0) {
			campaignForm.param("pv_window_minutes", String.valueOf(entity.getPv_window_minutes()));
		}
		
		if(entity.getSpend_cap_amount() > 0){
			campaignForm.param("spend_cap_amount", String.valueOf(entity.getSpend_cap_amount()));
		}
		
		
		campaignForm.param("spend_cap_automatic", Utility.getOnOrOff(entity.isSpend_cap_automatic()));
		
		campaignForm.param("spend_cap_enabled", Utility.getOnOrOff(entity.isSpend_cap_enabled()));
		
		campaignForm.param("use_default_ad_server", Utility.getOnOrOff(entity.isUse_default_ad_server()));
		
		if(entity.getZone_name() != null){
			campaignForm.param("zone_name", entity.getZone_name());
		}
		
		if(entity.getCreated_on() != null){
			campaignForm.param("created_on", entity.getCreated_on().toString());
		}
		
		if(entity.getId() > 0) {
			campaignForm.param("id", String.valueOf(entity.getId()));
		}
		
		campaignForm.param("status", Utility.getOnOrOff(entity.isStatus()));
		
		if(entity.getVersion() > 0) {
			campaignForm.param("version", String.valueOf(entity.getVersion()));
		}
		
		if(entity.getUpdated_on() != null) {
			campaignForm.param("updated_on", String.valueOf(entity.getUpdated_on()));
		}
		
		return campaignForm;
	}

}
