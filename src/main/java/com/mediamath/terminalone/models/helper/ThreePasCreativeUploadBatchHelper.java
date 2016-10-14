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

import com.mediamath.terminalone.models.ThreePASCreativeBatchApprove;
import com.mediamath.terminalone.models.ThreePASCreativeBatchIndex;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import javax.ws.rs.core.Form;

public class ThreePasCreativeUploadBatchHelper {

  /**
   * creates a ThreePASCreativeBatchApprove form object.
   * 
   * @param entity
   *          expects a ThreePASCreativeBatchApprove entity.
   * @return Form object.
   */
  public static Form getForm(ThreePASCreativeBatchApprove entity) {
    Form creativeBatchForm = new Form();

    if (entity.getAdvertiserId() != null && !entity.getAdvertiserId().isEmpty()) {
      creativeBatchForm.param("advertiser_id", entity.getAdvertiserId());
    }

    if (entity.getBatch() != null) {
      for (ThreePASCreativeBatchIndex batchIndex : entity.getBatch()) {
        if (batchIndex.getBatchIndex() != null && !batchIndex.getBatchIndex().isEmpty()) {
          creativeBatchForm.param("batch_index", batchIndex.getBatchIndex());

          if (batchIndex.getConceptId() != null && !batchIndex.getConceptId().isEmpty()) {
            creativeBatchForm.param("concept_" + batchIndex.getBatchIndex(),
                batchIndex.getConceptId());
          }

          if (batchIndex.getClickUrl() != null && !batchIndex.getClickUrl().isEmpty()) {
            creativeBatchForm.param("click_url_" + batchIndex.getBatchIndex(),
                batchIndex.getClickUrl());
          }
        }
      }
    }

    return creativeBatchForm;
  }

  /**
   * Creates a MultiPart Form object
   * 
   * @param entity
   *          expects ThreePASCreativeBatchApprove entity.
   * @param formDataMultiPart
   *          expects a FormDataMultiPart object.
   */
  public static void getMultiPartForm(ThreePASCreativeBatchApprove entity,
      FormDataMultiPart formDataMultiPart) {

    if (entity.getAdvertiserId() != null && !entity.getAdvertiserId().isEmpty()) {
      formDataMultiPart = formDataMultiPart.field("advertiser_id", entity.getAdvertiserId());
    }

    if (entity.getBatch() != null) {
      for (ThreePASCreativeBatchIndex batchIndex : entity.getBatch()) {
        if (batchIndex.getBatchIndex() != null && !batchIndex.getBatchIndex().isEmpty()) {

          formDataMultiPart = formDataMultiPart.field("batch_index", batchIndex.getBatchIndex());

          if (batchIndex.getConceptId() != null && !batchIndex.getConceptId().isEmpty()) {
            formDataMultiPart = formDataMultiPart.field("concept_" + batchIndex.getBatchIndex(),
                batchIndex.getConceptId());
          }

          if (batchIndex.getClickUrl() != null && !batchIndex.getClickUrl().isEmpty()) {
            formDataMultiPart = formDataMultiPart.field("click_url_" + batchIndex.getBatchIndex(),
                batchIndex.getClickUrl());
          }
        }
      }
    }
  }
}
