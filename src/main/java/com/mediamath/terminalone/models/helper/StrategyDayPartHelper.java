package com.mediamath.terminalone.models.helper;

import javax.ws.rs.core.Form;

import com.mediamath.terminalone.Exceptions.T1Exception;
import com.mediamath.terminalone.Exceptions.ValidationException;
import com.mediamath.terminalone.models.StrategyDayPart;
import com.mediamath.terminalone.utils.Utility;

public class StrategyDayPartHelper {

	

	public static void validateRequiredFields(StrategyDayPart entity) throws T1Exception {
		if (entity.getDays() ==null) {
			throw new ValidationException("please enter a valid Day");
		}
		
		if(entity.getEnd_hour() < 0 || entity.getEnd_hour() > 23 ){
			throw new ValidationException("please enter a valid End Hour");
		}

		if(entity.getStart_hour() < 0 || entity.getStart_hour() > 23 ){
			throw new ValidationException("please enter a valid Start Hour");
		}

		if (entity.getStrategy_id() <= 0) {
			throw new ValidationException("please enter a valid strategy id");
		}
		
		if(entity.getVersion() <= 0) {
			throw new ValidationException("please add version");
		}
		
	}
	
	public static Form getForm(StrategyDayPart entity) {
		Form strategyConceptForm = new Form();
		
		strategyConceptForm.param("days", String.valueOf(entity.getDays()));
		
		if(entity.getEnd_hour() > 0 && entity.getEnd_hour() < 23) {
			strategyConceptForm.param("end_hour", String.valueOf(entity.getEnd_hour()));
		}
		
		if(entity.getStart_hour() > 0 && entity.getStart_hour() < 23) {
			strategyConceptForm.param("start_hour", String.valueOf(entity.getStart_hour()));
		}

		if(entity.getStrategy_id() > 0) {
			strategyConceptForm.param("strategy_id", String.valueOf(entity.getStrategy_id()));
		}

		strategyConceptForm.param("user_time", Utility.getOnOrOff(entity.isUser_time()));
		
		if(entity.getVersion() > 0) {
			strategyConceptForm.param("version", String.valueOf(entity.getVersion()));
		}
		
		return strategyConceptForm;
	}
	
}
