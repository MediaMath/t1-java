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


import java.text.SimpleDateFormat;

import javax.ws.rs.core.Form;

import com.mediamath.terminalone.models.Concept;
import com.mediamath.terminalone.utils.Utility;


public class ConceptHelper {

  private static final String YYYY_MM_DDTHH_MM_SS_Z = "yyyy-MM-dd'T'HH:mm:ss Z";

  private static final SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DDTHH_MM_SS_Z);


  /**
   * Creates a Concept Form object.
   * @param entity expects Concept Entity.
   * @return Form object.
   */
  public static Form getForm(Concept entity) {
    Form conceptForm = new Form();

    conceptForm.param("name", entity.getName());

    if (entity.getAdvertiserId() > 0) {
      conceptForm.param("advertiser_id", String.valueOf(entity.getAdvertiserId()));
    }

    conceptForm.param("status", Utility.getOnOrOff(entity.isStatus()));

    if (entity.getVersion() >= 0) {
      conceptForm.param("version", String.valueOf(entity.getVersion()));
    }

    return conceptForm;
  }

}
