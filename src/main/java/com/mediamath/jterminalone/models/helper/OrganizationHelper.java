package com.mediamath.jterminalone.models.helper;

import javax.ws.rs.core.Form;

import com.google.gson.Gson;
import com.mediamath.jterminalone.Exceptions.T1Exception;
import com.mediamath.jterminalone.Exceptions.ValidationException;
import com.mediamath.jterminalone.models.Organization;
import com.mediamath.jterminalone.utils.Utility;

public class OrganizationHelper {

	
	public static void validateRequiredFields(Organization entity) throws T1Exception {
		if (entity.getName() == null || entity.getName().isEmpty()) {
			throw new ValidationException("please enter a name for the sitelist");
		} else if (entity.getName().length() > 64) {
			throw new ValidationException("please make sure name does not exceed 64 characters.");
		}
		
		if (entity.getAddress_1() == null || entity.getAddress_1().isEmpty()) {
			throw new ValidationException("please enter a address 1 for the sitelist");
		} else if (entity.getAddress_1().length() > 256) {
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
		
		if (entity.getMm_contact_name() == null || entity.getMm_contact_name().isEmpty()) {
			throw new ValidationException("please enter a Contact Name for the sitelist");
		} else if (entity.getMm_contact_name().length() > 64) {
			throw new ValidationException("please make sure Contact Name does not exceed 64 characters.");
		}
		
		if(entity.getCurency_code()==null){
			entity.setCurency_code("USD");
		}
		
		if(entity.getAdx_seat_account_id() < 0){
			entity.setAdx_seat_account_id(41519752);
		}
		
		if(entity.getBilling_country_code()==null){
			entity.setBilling_country_code("US");;
		}
		
		if(entity.getSuspicious_traffic_filter_level() <0 || entity.getSuspicious_traffic_filter_level() >100){
			entity.setSuspicious_traffic_filter_level(25);
		}
		
		if(entity.getOrg_type().size()<=0){
			entity.getOrg_type().add("buyer");
		}
		
	}
	
	public static Form getForm(Organization entity) {
		Gson g= new Gson();
		Form orgForm = new Form();
		
		orgForm.param("name", entity.getName());
		orgForm.param("status", Utility.getOnOrOff(entity.isStatus()));
		if(entity.getContact_name()!=null){
			orgForm.param("contact_name", entity.getContact_name());
		}
		
		orgForm.param("address_1", entity.getAddress_1());
		orgForm.param("address_2", entity.getAddress_2());
		orgForm.param("city", entity.getCity());
		orgForm.param("state", entity.getState());
		orgForm.param("zip", entity.getZip());
		orgForm.param("country", entity.getCountry());
		orgForm.param("phone", entity.getPhone());
		orgForm.param("mm_contact_name", entity.getMm_contact_name());
		orgForm.param("allow_x_agency_pixels", Utility.getOnOrOff(entity.isAllow_x_agency_pixels()));
		orgForm.param("use_evidon_optout", Utility.getOnOrOff(entity.isUse_evidon_optout()));
		orgForm.param("allow_byo_price", Utility.getOnOrOff(entity.isAllow_byo_price()));
		orgForm.param("currency_code", entity.getCurency_code());
		orgForm.param("adx_seat_account_id", String.valueOf(entity.getAdx_seat_account_id()));
		orgForm.param("billing_country_code", entity.getBilling_country_code());
		orgForm.param("override_suspicious_traffic_filter", Utility.getOnOrOff(entity.isOverride_suspicious_traffic_filter()));
		orgForm.param("suspicious_traffic_filter_level", String.valueOf(entity.getSuspicious_traffic_filter_level()));
		//TODO check how to pass array to form
		orgForm.param("org_type", g.toJson(entity.getOrg_type()));
		
		return orgForm;
	}
}
