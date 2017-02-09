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

import javax.ws.rs.core.Form;

import com.mediamath.terminalone.models.StrategyConcept;
import com.mediamath.terminalone.utils.Utility;

public class StrategyConceptHelper {

  private StrategyConceptHelper() {
		throw new IllegalAccessError("StrategyConceptHelper cannot be instantiated");
  }
	
  /**
   * Creates a StrategyConcept form object.
   * 
   * @param entity
   *          expect StrategyConcept entity.
   * @return Form object.
   */
  public static Form getForm(StrategyConcept entity) {
    Form strategyConceptForm = new Form();

    if (entity.getConceptId() > 0) {
      strategyConceptForm.param("concept_id", String.valueOf(entity.getConceptId()));
    }

    if (entity.getStrategyId() > 0) {
      strategyConceptForm.param("strategy_id", String.valueOf(entity.getStrategyId()));
    }

    strategyConceptForm.param("status", Utility.getOnOrOff(entity.isStatus()));

    if (entity.getVersion() >= 0) {
      strategyConceptForm.param("version", String.valueOf(entity.getVersion()));
    }

    return Utility.getFilteredForm(strategyConceptForm, "strategyconcept");

  }

}
