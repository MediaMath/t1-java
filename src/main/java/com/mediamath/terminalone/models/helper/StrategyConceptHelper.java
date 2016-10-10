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
import com.mediamath.terminalone.models.StrategyConcept;
import com.mediamath.terminalone.utils.Utility;

import javax.ws.rs.core.Form;



public class StrategyConceptHelper {

  /**
   * validates required fields
   * 
   * @param entity expects StrategyConcept entity.
   * @throws T1Exception exception
   */
  public static void validateRequiredFields(StrategyConcept entity) throws T1Exception {
    
    if (entity.getConcept_id() <= 0) {
      throw new ValidationException("please enter a valid concept id");
    }

    if (entity.getStrategy_id() <= 0) {
      throw new ValidationException("please enter a valid strategy id");
    }

    if (entity.getVersion() <= 0) {
      throw new ValidationException("please add version");
    }

  }

  /**
   * Creates a StrategyConcept form object.
   * 
   * @param entity expect StrategyConcept entity.
   * @return Form object.
   */
  public static Form getForm(StrategyConcept entity) {
    Form strategyConceptForm = new Form();

    if (entity.getConcept_id() > 0) {
      strategyConceptForm.param("concept_id", String.valueOf(entity.getConcept_id()));
    }

    if (entity.getStrategy_id() > 0) {
      strategyConceptForm.param("strategy_id", String.valueOf(entity.getStrategy_id()));
    }

    strategyConceptForm.param("status", Utility.getOnOrOff(entity.isStatus()));

    if (entity.getVersion() > 0) {
      strategyConceptForm.param("version", String.valueOf(entity.getVersion()));
    }

    return strategyConceptForm;
  }

}
