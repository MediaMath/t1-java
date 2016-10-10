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

    if (entity.getAdvertiser_id() != null && !entity.getAdvertiser_id().isEmpty()) {
      creativeBatchForm.param("advertiser_id", entity.getAdvertiser_id());
    }

    if (entity.getBatch() != null) {
      for (ThreePASCreativeBatchIndex batchIndex : entity.getBatch()) {
        if (batchIndex.getBatch_index() != null && !batchIndex.getBatch_index().isEmpty()) {
          creativeBatchForm.param("batch_index", batchIndex.getBatch_index());

          if (batchIndex.getConceptId() != null && !batchIndex.getConceptId().isEmpty()) {
            creativeBatchForm.param("concept_" + batchIndex.getBatch_index(),
                batchIndex.getConceptId());
          }

          if (batchIndex.getClick_url() != null && !batchIndex.getClick_url().isEmpty()) {
            creativeBatchForm.param("click_url_" + batchIndex.getBatch_index(),
                batchIndex.getClick_url());
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

    if (entity.getAdvertiser_id() != null && !entity.getAdvertiser_id().isEmpty()) {
      formDataMultiPart = formDataMultiPart.field("advertiser_id", entity.getAdvertiser_id());
    }

    if (entity.getBatch() != null) {
      for (ThreePASCreativeBatchIndex batchIndex : entity.getBatch()) {
        if (batchIndex.getBatch_index() != null && !batchIndex.getBatch_index().isEmpty()) {

          formDataMultiPart = formDataMultiPart.field("batch_index", batchIndex.getBatch_index());

          if (batchIndex.getConceptId() != null && !batchIndex.getConceptId().isEmpty()) {
            formDataMultiPart = formDataMultiPart.field("concept_" + batchIndex.getBatch_index(),
                batchIndex.getConceptId());
          }

          if (batchIndex.getClick_url() != null && !batchIndex.getClick_url().isEmpty()) {
            formDataMultiPart = formDataMultiPart.field("click_url_" + batchIndex.getBatch_index(),
                batchIndex.getClick_url());
          }
        }
      }
    }
  }
}
