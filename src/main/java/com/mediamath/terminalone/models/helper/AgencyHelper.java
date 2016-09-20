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

import com.mediamath.terminalone.Exceptions.T1Exception;
import com.mediamath.terminalone.Exceptions.ValidationException;
import com.mediamath.terminalone.models.Agency;
import com.mediamath.terminalone.utils.Utility;

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
