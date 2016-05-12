package com.mediamath.terminalone.models.helper;

import javax.ws.rs.core.Form;

import com.mediamath.terminalone.Exceptions.T1Exception;
import com.mediamath.terminalone.Exceptions.ValidationException;
import com.mediamath.terminalone.models.StrategyConcept;
import com.mediamath.terminalone.utils.Utility;

public class StrategyConceptHelper {

	
	public static void validateRequiredFields(StrategyConcept entity) throws T1Exception {
		if (entity.getConcept_id() <= 0) {
			throw new ValidationException("please enter a valid concept id");
		}
		
		if (entity.getStrategy_id() <= 0) {
			throw new ValidationException("please enter a valid strategy id");
		}
		
		if(entity.getVersion() <= 0) {
			throw new ValidationException("please add version");
		}
		
	}
	
	public static Form getForm(StrategyConcept entity) {
		Form strategyConceptForm = new Form();
		
		if(entity.getConcept_id() > 0) {
			strategyConceptForm.param("concept_id", String.valueOf(entity.getConcept_id()));
		}

		if(entity.getStrategy_id() > 0) {
			strategyConceptForm.param("strategy_id", String.valueOf(entity.getStrategy_id()));
		}

		strategyConceptForm.param("status", Utility.getOnOrOff(entity.isStatus()));
		
		if(entity.getVersion() > 0) {
			strategyConceptForm.param("version", String.valueOf(entity.getVersion()));
		}
		
		return strategyConceptForm;
	}
	
	
	
	
}
