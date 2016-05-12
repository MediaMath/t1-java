package com.mediamath.terminalone.models.helper;

import java.text.SimpleDateFormat;

import javax.ws.rs.core.Form;


import com.mediamath.terminalone.Exceptions.*;
import com.mediamath.terminalone.models.Concept;
import com.mediamath.terminalone.utils.Utility;

public class ConceptHelper {
	
	private static final String YYYY_MM_DDTHH_MM_SS_Z = "yyyy-MM-dd'T'HH:mm:ss Z";

	private static final SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DDTHH_MM_SS_Z);

	public static void validateRequiredFields(Concept entity) throws T1Exception {
		if(entity.getAdvertiser_id() < 0) {
			throw new ValidationException("Please Enter Advertiser ID");
		}
		
		if(entity.getName() == null || entity.getName().isEmpty()) {
			throw new ValidationException("please enter a name for the concept");
		}
	}

	public static Form getForm(Concept entity) {
		Form conceptForm = new Form();

		conceptForm.param("name", entity.getName());
		
		if(entity.getAdvertiser_id() > 0) {
			conceptForm.param("advertiser_id", String.valueOf(entity.getAdvertiser_id()));
		}
		
		conceptForm.param("status", Utility.getOnOrOff(entity.isStatus()));
		
		if(entity.getVersion() > 0) {
			conceptForm.param("version", String.valueOf(entity.getVersion()));
		}
		
		return conceptForm;
	}

}
