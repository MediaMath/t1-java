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

import com.mediamath.terminalone.exceptions.T1Exception;
import com.mediamath.terminalone.exceptions.ValidationException;
import com.mediamath.terminalone.models.SiteList;
import com.mediamath.terminalone.utils.Utility;

import javax.ws.rs.core.Form;

public class SiteListHelper {
  /**
   * creates a SiteList Form object.
   * @param entity expects a SiteList entity.
   * @return Form object.
   */
  public static Form getForm(SiteList entity) {
    Form strategyConceptForm = new Form();

    strategyConceptForm.param("name", entity.getName());
    strategyConceptForm.param("filename", entity.getFilename());

    if (entity.getRestriction() != null) {
      strategyConceptForm.param("restriction", String.valueOf(entity.getRestriction()));
    }

    if (entity.getOrganizationId() > 0) {
      strategyConceptForm.param("organization_id", String.valueOf(entity.getOrganizationId()));
    }

    strategyConceptForm.param("status", Utility.getOnOrOff(entity.isStatus()));

    if (entity.getVersion() >= 0) {
      strategyConceptForm.param("version", String.valueOf(entity.getVersion()));
    }

    return strategyConceptForm;
  }

}
