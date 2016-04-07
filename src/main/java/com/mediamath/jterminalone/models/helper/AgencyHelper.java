package com.mediamath.jterminalone.models.helper;

import javax.ws.rs.core.Form;

import com.mediamath.jterminalone.Exceptions.T1Exception;
import com.mediamath.jterminalone.Exceptions.ValidationException;
import com.mediamath.jterminalone.models.Agency;
import com.mediamath.jterminalone.utils.Utility;

public class AgencyHelper  {

	public static void validateFields() throws T1Exception {
/*		if (sales_contact_id < 0) {
			throw new ValidationException("please enter a valid sales contact id");
		}
*/
	}

	public static void validateRequiredFields(Agency entity) throws T1Exception {
		if (entity.getName() == null || entity.getName().isEmpty()) {
			throw new ValidationException("please enter a name for the agency");
		} else if (entity.getName().length() > 64) {
			throw new ValidationException("please make sure name does not exceed 64 characters.");
		}
		if (entity.getOrganization_id() <= 0) {
			throw new ValidationException("please enter a valid organization id");
		}
		if(entity.getId()  > 0 && entity.getVersion() <=0){
			throw new ValidationException("Version is required for Updates");
		}
		
	}

	public static Form getForm(Agency entity) {

		Form agencyForm = new Form();

		//required
		agencyForm.param("name", entity.getName());
		
		agencyForm.param("organization_id", String.valueOf(entity.getOrganization_id()));

		//optional
		agencyForm.param("allow_x_adv_optimization", Utility.getOnOrOff(entity.isAllow_x_adv_optimization()));
		
		agencyForm.param("allow_x_adv_pixels", Utility.getOnOrOff(entity.isAllow_x_adv_pixels()));
		
		if(entity.getBilling_contact_id() > 0) {
			agencyForm.param("billing_contact_id", String.valueOf(entity.getBilling_contact_id()));
		}
		
		if(entity.getDmp_enabled() != null) {
			agencyForm.param("dmp_enabled", entity.getDmp_enabled());
		}
		
		if(entity.getCreated_on() != null){
			agencyForm.param("created_on", entity.getCreated_on().toString());
		}
		
		if(entity.getId() > 0) {
			agencyForm.param("id", String.valueOf(entity.getId()));
		}
		
		if(entity.getLogo() != null) {
			agencyForm.param("logo", entity.getLogo());
		}
		
		if(entity.getSales_contact_id() > 0) {
			agencyForm.param("sales_contact_id", String.valueOf(entity.getSales_contact_id()));
		}
		
		agencyForm.param("status", Utility.getOnOrOff(entity.isStatus()));
		
		if(entity.getVersion() > 0) {
			agencyForm.param("version", String.valueOf(entity.getVersion()));
		}
		
		if(entity.getTraffic_contact_id() > 0) {
			agencyForm.param("traffic_contact_id", String.valueOf(entity.getTraffic_contact_id()));
		}
		
		if(entity.getUpdated_on() != null) {
			agencyForm.param("updated_on", String.valueOf(entity.getUpdated_on()));
		}
		
		return agencyForm;
	}

}
