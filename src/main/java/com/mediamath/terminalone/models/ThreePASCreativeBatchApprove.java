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
	
