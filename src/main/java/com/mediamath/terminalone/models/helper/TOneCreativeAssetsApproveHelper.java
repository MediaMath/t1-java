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

import com.mediamath.terminalone.models.TOneASCreativeAssetsApprove;
import com.mediamath.terminalone.models.TOneASCreativeAssetsApproveData;
import com.mediamath.terminalone.utils.Utility;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;

public class TOneCreativeAssetsApproveHelper {

  /**
   * Creates a Multi Part Form object.
   * 
   * @param entity
   *          expects a TOneASCreativeAssetsApprove entity.
   * @param formData
   *          expects a FormDataMultiPart formData object.
   */
  public static void getMultiPartForm(TOneASCreativeAssetsApprove entity,
      FormDataMultiPart formData) {

    if (entity != null) {
      if (!entity.getDataList().isEmpty()) {

        for (int i = 0; i < entity.getDataList().size(); i++) {
          int inc = i + 1;
          TOneASCreativeAssetsApproveData data = entity.getDataList().get(i);

          if (data.isHttps()) {
            formData = formData.field("is_https", Utility.getOneOrZero(data.isHttps()));
          }

          if (data.getAdvertiserid() != null && !data.getAdvertiserid().isEmpty()) {
            formData = formData.field("advertiserid", data.getAdvertiserid());
          }

          if (data.getLandingPage() != null && !data.getLandingPage().isEmpty()) {
            formData = formData.field("landingPage." + inc, data.getLandingPage());
          }

          if (data.getClickUrl() != null && !data.getClickUrl().isEmpty()) {
            formData = formData.field("click_url." + inc, data.getClickUrl());
          }

          if (data.getPrimary() != null && !data.getPrimary().isEmpty()) {
            formData = formData.field("primary." + inc, data.getPrimary());
          }

          if (data.getBackup() != null && !data.getBackup().isEmpty()) {
            formData = formData.field("backup." + inc, data.getBackup());
          }

          if (data.getConcept() != null && !data.getConcept().isEmpty()) {
            formData = formData.field("concept." + inc, data.getConcept());
          }

        }
      }
    }

  }

}
