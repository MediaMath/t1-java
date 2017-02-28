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

import com.mediamath.terminalone.models.StrategySupplySource;

public class StrategySupplySourceHelper {
	
  private StrategySupplySourceHelper() {
		throw new IllegalAccessError("StrategySupplySourceHelper cannot be instantiated");
  }

  /**
   * creates a StrategySupplySource Form object.
   * 
   * @param entity
   *          expects a StrategySupplySource entity
   * @return Form entity.
   */
  public static Form getForm(StrategySupplySource entity) {
    Form strategySupplySourceForm = new Form();

    if (entity.getSupplySourceId() > 0) {
      strategySupplySourceForm.param("supply_source_id",
          String.valueOf(entity.getSupplySourceId()));
    }

    if (entity.getStrategyId() > 0) {
      strategySupplySourceForm.param("strategy_id", String.valueOf(entity.getStrategyId()));
    }

    if (entity.getVersion() >= 0) {
      strategySupplySourceForm.param("version", String.valueOf(entity.getVersion()));
    }
    
    if (entity.getName() != null) {
    	strategySupplySourceForm.param("name", entity.getName());
    }

    return strategySupplySourceForm;
  }

}
