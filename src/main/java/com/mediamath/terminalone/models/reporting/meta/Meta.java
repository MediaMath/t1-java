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

import com.mediamath.terminalone.models.T1Entity;

import java.util.HashMap;

public class Meta implements T1Entity {

  private static final String entityName = "meta";

  HashMap<String, MetaData> metaData = null;

  @Override
  public String getEntityname() {
    return entityName;
  }

  public HashMap<String, MetaData> getMetaData() {
    return metaData;
  }

  public void setMetaData(HashMap<String, MetaData> metaData) {
    this.metaData = metaData;
  }

}
