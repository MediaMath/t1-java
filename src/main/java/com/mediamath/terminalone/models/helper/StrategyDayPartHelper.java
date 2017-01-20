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

import com.mediamath.terminalone.models.StrategyDayPart;
import com.mediamath.terminalone.utils.Utility;

public class StrategyDayPartHelper {
	
  private StrategyDayPartHelper() {
		throw new IllegalAccessError("StrategyDayPartHelper class");
  }

  /**
   * Creates a StrategyDayPart Form object.
   * 
   * @param entity
   *          expects StrategyDayPart entity.
   * @return Form object.
   */
  public static Form getForm(StrategyDayPart entity) {
    Form strategyDayPartForm = new Form();

    strategyDayPartForm.param("days", String.valueOf(entity.getDays()));

    if (entity.getEndHour() > 0 && entity.getEndHour() < 23) {
      strategyDayPartForm.param("end_hour", String.valueOf(entity.getEndHour()));
    }

    if (entity.getStartHour() > 0 && entity.getStartHour() < 23) {
      strategyDayPartForm.param("start_hour", String.valueOf(entity.getStartHour()));
    }

    if (entity.getStrategyId() > 0) {
      strategyDayPartForm.param("strategy_id", String.valueOf(entity.getStrategyId()));
    }

    strategyDayPartForm.param("user_time", Utility.getOnOrOff(entity.isUserTime()));

    if (entity.getVersion() >= 0) {
      strategyDayPartForm.param("version", String.valueOf(entity.getVersion()));
    }


    return Utility.getFilteredForm(strategyDayPartForm, "strategydaypart");

  }

}
