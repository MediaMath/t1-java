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
import com.mediamath.terminalone.models.SiteList;
import com.mediamath.terminalone.utils.Utility;

public class SiteListHelper {
	
	
	public static void validateRequiredFields(SiteList entity) throws T1Exception {
		if (entity.getName() == null || entity.getName().isEmpty()) {
			throw new ValidationException("please enter a name for the sitelist");
		} else if (entity.getName().length() > 64) {
			throw new ValidationException("please make sure name does not exceed 64 characters.");
		}

		if (entity.getOrganization_id() <= 0) {
			throw new ValidationException("please enter a valid Organization id");
		}
		
		if(entity.getRestriction()==null){
			throw new ValidationException("please enter a valid Restriction");
		}
		
		if (entity.getFilename() == null || entity.getFilename().isEmpty()) {
			throw new ValidationException("please enter a file name for the sitelist");
		}
		
		if(entity.getVersion() <= 0) {
			throw new ValidationException("please add version");
		}
		
	}
	
	public static Form getForm(SiteList entity) {
		Form strategyConceptForm = new Form();
		
		strategyConceptForm.param("name", entity.getName());
		strategyConceptForm.param("filename", entity.getFilename());
		
		if(entity.getRestriction()!=null) {
			strategyConceptForm.param("restriction", String.valueOf(entity.getRestriction()));
		}
		
		if(entity.getOrganization_id() > 0) {
			strategyConceptForm.param("organization_id", String.valueOf(entity.getOrganization_id()));
		}

		strategyConceptForm.param("status", Utility.getOnOrOff(entity.isStatus()));
		
		if(entity.getVersion() > 0) {
			strategyConceptForm.param("version", String.valueOf(entity.getVersion()));
		}
		
		return strategyConceptForm;
	}

}
