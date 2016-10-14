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

package com.mediamath.terminalone.models.reporting.meta;

public class MetaStructure {

  MetaDimensions dimensions;
  
  MetaMetrics metrics;
  
  TimeField time_field;

  public MetaDimensions getDimensions() {
    return dimensions;
  }

  public void setDimensions(MetaDimensions dimensions) {
    this.dimensions = dimensions;
  }

  public MetaMetrics getMetrics() {
    return metrics;
  }

  public void setMetrics(MetaMetrics metrics) {
    this.metrics = metrics;
  }

  public TimeField getTimeField() {
    return time_field;
  }

  public void setTimeField(TimeField time_field) {
    this.time_field = time_field;
  }

}
