package com.mediamath.terminalone.models.helper;

import javax.ws.rs.core.Form;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import com.mediamath.terminalone.Exceptions.T1Exception;
import com.mediamath.terminalone.models.ThreePASCreativeBatchIndex;
import com.mediamath.terminalone.models.ThreePASCreativeBatchApprove;

public class ThreePasCreativeUploadBatchHelper {
	
	public static void validateRequiredFields(ThreePASCreativeBatchApprove entity) throws T1Exception {
		
		
	}
	
	public static Form getForm(ThreePASCreativeBatchApprove entity) {
		Form creativeBatchForm = new Form();
		
		if(entity.getAdvertiser_id() != null && !entity.getAdvertiser_id().isEmpty()) {
			creativeBatchForm.param("advertiser_id", entity.getAdvertiser_id());
		}
		
		if(entity.getBatch() != null) {
			for(ThreePASCreativeBatchIndex batchIndex : entity.getBatch()) {
				if(batchIndex.getBatch_index() != null && !batchIndex.getBatch_index().isEmpty()) {
					creativeBatchForm.param("batch_index", batchIndex.getBatch_index());
					
					if(batchIndex.getConceptId() != null && !batchIndex.getConceptId().isEmpty()) {
						creativeBatchForm.param("concept_" + batchIndex.getBatch_index(), batchIndex.getConceptId());
					}
					
					if(batchIndex.getClick_url() != null && !batchIndex.getClick_url().isEmpty()) {
						creativeBatchForm.param("click_url_" + batchIndex.getBatch_index(), batchIndex.getClick_url());
					}
				}
			}
		}
		
		return creativeBatchForm;
	}
	
	public static void getMultiPartForm(ThreePASCreativeBatchApprove entity, FormDataMultiPart formDataMultiPart) {
		
		if(entity.getAdvertiser_id() != null && !entity.getAdvertiser_id().isEmpty()) {
			formDataMultiPart = formDataMultiPart.field("advertiser_id", entity.getAdvertiser_id());
		}
		
		if(entity.getBatch() != null) {
			for(ThreePASCreativeBatchIndex batchIndex : entity.getBatch()) {
				if(batchIndex.getBatch_index() != null && !batchIndex.getBatch_index().isEmpty()) {
					
					formDataMultiPart = formDataMultiPart.field("batch_index", batchIndex.getBatch_index());
					
					if(batchIndex.getConceptId() != null && !batchIndex.getConceptId().isEmpty()) {
						formDataMultiPart = formDataMultiPart.field("concept_" + batchIndex.getBatch_index(), batchIndex.getConceptId());
					}
					
					if(batchIndex.getClick_url() != null && !batchIndex.getClick_url().isEmpty()) {
						formDataMultiPart = formDataMultiPart.field("click_url_" + batchIndex.getBatch_index(), batchIndex.getClick_url());
					}
				}
			}
		}
	}
}
