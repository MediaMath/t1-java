package com.mediamath.terminalone.models;

import java.util.ArrayList;

public class ThreePASCreativeBatchApprove implements T1Entity {
	
	private static final String entityName = "ThreePasCreativeBatchApprove";

	String batchId;
	
	String advertiser_id;
	
	ArrayList<ThreePASCreativeBatchIndex> batch = new ArrayList<ThreePASCreativeBatchIndex>();
	
	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getAdvertiser_id() {
		return advertiser_id;
	}

	public void setAdvertiser_id(String advertiser_id) {
		this.advertiser_id = advertiser_id;
	}

	public void setBatchIndex(String pBatchIndex, String concept, String click_url) {
		
		ThreePASCreativeBatchIndex batchIndex = null;
		
		if(pBatchIndex != null && !pBatchIndex.isEmpty()) {
			batchIndex = new ThreePASCreativeBatchIndex();
			
			batchIndex.setBatch_index(pBatchIndex);
			
			if(concept != null && !concept.isEmpty()) {
				batchIndex.setConceptId(concept);
			}
			
			if(click_url != null && !click_url.isEmpty()) {
				batchIndex.setClick_url(click_url);
			}
		}
		
		if(batchIndex != null) {
			batch.add(batchIndex);
		}
	}

	@Override
	public String getEntityname() {
		return entityName;
	}

	public ArrayList<ThreePASCreativeBatchIndex> getBatch() {
		return batch;
	}
	
}
	