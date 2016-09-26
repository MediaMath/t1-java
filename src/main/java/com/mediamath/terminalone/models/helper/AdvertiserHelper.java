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
import com.mediamath.terminalone.models.Advertiser;
import com.mediamath.terminalone.models.Advertiser.dmp_settings;
import com.mediamath.terminalone.models.Advertiser.freq_ints;
import com.mediamath.terminalone.models.Advertiser.freq_types;
import com.mediamath.terminalone.utils.Utility;

public class AdvertiserHelper {

	public static void validateRequiredFields(Advertiser entity) throws T1Exception {
		if (entity.getName() == null || entity.getName().isEmpty()) {
			throw new ValidationException("please enter a name for the advertiser");
		} else if (entity.getName().length() > 64) {
			throw new ValidationException("please make sure name does not exceed 64 characters.");
		}
		
		if (entity.getDomain() == null || entity.getDomain().isEmpty()) {
			throw new ValidationException("please enter a Domain for the advertiser");
		} else if (entity.getDomain().length() > 255) {
			throw new ValidationException("please make sure domain does not exceed 255 characters.");
		}
		
		if (entity.getDmp_enabled() == null){
			entity.setDmp_enabled(dmp_settings.disabled);
		}
		
		if (entity.getFrequency_type() == null){
			entity.setFrequency_type(freq_types.valueOf("no_limit"));
		}
		
		if (entity.getFrequency_type() != null &&  (entity.getFrequency_type().equals(freq_types.even)|| entity.getFrequency_type().equals(freq_types.asap))) {
			if(entity.getFrequency_interval()==null){
				entity.setFrequency_interval(freq_ints.valueOf("not_applicable"));
			}
			if(entity.getFrequency_amount()<=0){
				throw new ValidationException("please enter valid a frequency amount");
			}
		}
		
		if (entity.getAd_server_id() <= 0) {
			throw new ValidationException("please enter a valid ad_server id");
		}
		if (entity.getAgency_id() <= 0) {
			throw new ValidationException("please enter a valid Agency id");
		}
		
		if (entity.getVertical_id() <= 0) {
			throw new ValidationException("please enter a valid Vertical id");
		}
	}
	
	public static Form getForm(Advertiser entity) {
		Form advertiserForm = new Form();
		//required
				advertiserForm.param("name", entity.getName());
				
				advertiserForm.param("ad_server_id", String.valueOf(entity.getAd_server_id()));

				//optional
				advertiserForm.param("allow_x_strat_optimization", Utility.getOnOrOff(entity.isAllow_x_strat_optimization()));
				
				advertiserForm.param("agency_id", String.valueOf(entity.getAgency_id()));
				
				if(entity.getBilling_contact_id() > 0) {
					advertiserForm.param("billing_contact_id", String.valueOf(entity.getBilling_contact_id()));
				}
				
				if(entity.getDomain() != null) {
					advertiserForm.param("domain", entity.getDomain());
				}
				
				if(entity.getCreated_on() != null){
					advertiserForm.param("created_on", entity.getCreated_on().toString());
				}
				
				if(entity.getId() > 0) {
					advertiserForm.param("id", String.valueOf(entity.getId()));
				}
				
				if(entity.getFrequency_type() != null) {
					advertiserForm.param("frequency_type", entity.getFrequency_type().toString());
				}
				
				if(entity.getFrequency_interval()!=null) {
					advertiserForm.param("frequency_interval", String.valueOf(entity.getFrequency_interval()));
				}
				
				if(entity.getFrequency_amount() > 0) {
					advertiserForm.param("frequency_amount", String.valueOf(entity.getFrequency_amount()));
				}
				
				advertiserForm.param("minimize_multi_ads",Utility.getOnOrOff(entity.isMinimize_multi_ads()));
				
				if(entity.getSales_contact_id() > 0) {
					advertiserForm.param("sales_contact_id", String.valueOf(entity.getSales_contact_id()));
				}
				
				advertiserForm.param("status", Utility.getOnOrOff(entity.isStatus()));
				
				if(entity.getVersion() > 0) {
					advertiserForm.param("version", String.valueOf(entity.getVersion()));
				}
				
				if(entity.getVertical_id() > 0) {
					advertiserForm.param("vertical_id", String.valueOf(entity.getVertical_id()));
				}
				
				if(entity.getUpdated_on() != null) {
					advertiserForm.param("updated_on", String.valueOf(entity.getUpdated_on()));
				}
				
				
		return advertiserForm;
	}
	
	
}
