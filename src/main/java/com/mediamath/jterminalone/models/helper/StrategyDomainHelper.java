package com.mediamath.jterminalone.models.helper;

import java.text.SimpleDateFormat;

import javax.ws.rs.core.Form;

import com.mediamath.jterminalone.Exceptions.T1Exception;
import com.mediamath.jterminalone.Exceptions.ValidationException;
import com.mediamath.jterminalone.models.StrategyDomain;

public class StrategyDomainHelper {

	private static final String YYYY_MM_DDTHH_MM_SS_Z = "yyyy-MM-dd'T'HH:mm:ss Z";
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DDTHH_MM_SS_Z);
	
	public static void validateRequiredFields(StrategyDomain entity) throws T1Exception {
		if (entity.getDomain()==null) {
			throw new ValidationException("please enter a domain value");
		}
		
		if(entity.getRestriction()==null){
			throw new ValidationException("please add restrictions");
		}
		
		if (entity.getStrategy_id() <= 0) {
			throw new ValidationException("please enter a valid strategy id");
		}
		
		if(entity.getId()>0 && entity.getVersion() <= 0) {
			throw new ValidationException("please add version");
		}
		
	}
	
	public static Form getForm(StrategyDomain entity) {
		Form strategyDomainForm = new Form();
		
		if(entity.getDomain()!=null) {
			strategyDomainForm.param("domain", String.valueOf(entity.getDomain()));
		}

		if(entity.getStrategy_id() > 0) {
			strategyDomainForm.param("strategy_id", String.valueOf(entity.getStrategy_id()));
		}

		if(entity.getRestriction()!=null){
			strategyDomainForm.param("restriction", entity.getRestriction().name());
		}
		
		if(entity.getCreated_at()!=null){
			String createdAt = sdf.format(entity.getCreated_at());
			strategyDomainForm.param("created_at", createdAt);
		}
		
		if(entity.getId()>0){
			if(entity.getVersion()>0){
				strategyDomainForm.param("restriction", String.valueOf(entity.getVersion()));
			}
			if(entity.getUpdated_on()!=null){
				String updatedOn = sdf.format(entity.getUpdated_on());
				strategyDomainForm.param("updated_on", updatedOn);
			}
			
		}
		
		return strategyDomainForm;
	}
	
	
	
	
}
