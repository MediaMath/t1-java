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

import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import com.mediamath.terminalone.models.TPASCreativeBatchApprove;
import com.mediamath.terminalone.models.TPASCreativeBatchIndex;

public class TPasCreativeUploadBatchHelper {

	private TPasCreativeUploadBatchHelper() {
		throw new IllegalAccessError("TPasCreativeUploadBatchHelper cannot be instantiated");
	}

	/**
	 * Creates a MultiPart Form object
	 * 
	 * @param entity
	 *            expects TPASCreativeBatchApprove entity.
	 * @param formDataMultiPart
	 *            expects a FormDataMultiPart object.
	 * @return
	 */
	public static FormDataMultiPart getMultiPartForm(TPASCreativeBatchApprove entity,
			FormDataMultiPart formDataMultiPart) {

		FormDataMultiPart formData = formDataMultiPart;

		formData = setMultipartAdvertiserID(entity, formData);

		if (entity.getBatch() != null) {
			for (TPASCreativeBatchIndex batchIndex : entity.getBatch()) {
				if (batchIndex.getBatchIndex() != null && !batchIndex.getBatchIndex().isEmpty()) {

					formData = formData.field("batch_index", batchIndex.getBatchIndex());

					formData = setMultipartConcept(formData, batchIndex);

					formData = setMultipartClickURL(formData, batchIndex);
				}
			}
		}

		return formData;
	}

	private static FormDataMultiPart setMultipartClickURL(FormDataMultiPart formDataMultiPart,
			TPASCreativeBatchIndex batchIndex) {
		FormDataMultiPart formData = formDataMultiPart;
		if (batchIndex.getClickUrl() != null && !batchIndex.getClickUrl().isEmpty()) {
			formData = formData.field("click_url_" + batchIndex.getBatchIndex(), batchIndex.getClickUrl());
		}
		return formData;
	}

	private static FormDataMultiPart setMultipartConcept(FormDataMultiPart formDataMultiPart,
			TPASCreativeBatchIndex batchIndex) {
		FormDataMultiPart formData = formDataMultiPart;
		if (batchIndex.getConceptId() != null && !batchIndex.getConceptId().isEmpty()) {
			formData = formData.field("concept_" + batchIndex.getBatchIndex(), batchIndex.getConceptId());
		}
		return formData;
	}

	private static FormDataMultiPart setMultipartAdvertiserID(TPASCreativeBatchApprove entity,
			FormDataMultiPart formDataMultiPart) {
		FormDataMultiPart formData = formDataMultiPart;
		if (entity.getAdvertiserId() != null && !entity.getAdvertiserId().isEmpty()) {
			formData = formData.field("advertiser_id", entity.getAdvertiserId());
		}
		return formData;
	}
}
