package com.mediamath.jterminalone.models.helper;

import javax.ws.rs.core.Form;

import com.mediamath.jterminalone.Exceptions.T1Exception;
import com.mediamath.jterminalone.Exceptions.ValidationException;
import com.mediamath.jterminalone.models.StrategyConcept;
import com.mediamath.jterminalone.models.StrategySupplySource;
import com.mediamath.jterminalone.utils.Utility;

public class StrategySupplySourceHelper {

	
	public static void validateRequiredFields(StrategySupplySource entity) throws T1Exception {
		if (entity.getSupply_source_id() <= 0) {
			throw new ValidationException("please enter a valid Supply Source id");
		}
		
		if (entity.getStrategy_id() <= 0) {
			throw new ValidationException("please enter a valid strategy id");
		}
		
		/*if(entity.getVersion() <= 0) {
			throw new ValidationException("please add version");
		}*/
		
	}
	
	public static Form getForm(StrategySupplySource entity) {
		Form strategyConceptForm = new Form();
		
		if(entity.getSupply_source_id() > 0) {
			strategyConceptForm.param("supply_source_id", String.valueOf(entity.getSupply_source_id()));
		}

		if(entity.getStrategy_id() > 0) {
			strategyConceptForm.param("strategy_id", String.valueOf(entity.getStrategy_id()));
		}

		
		if(entity.getVersion() > 0) {
			strategyConceptForm.param("version", String.valueOf(entity.getVersion()));
		}
		
		return strategyConceptForm;
	}
	
	
	
	
}
