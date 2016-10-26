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

package com.mediamath.terminalone.models;

import java.util.HashMap;
import java.util.Map;

public class Data {

  public class enabled {
    String active;

    public String getActive() {
      return active;
    }

    public void setActive(String active) {
      this.active = active;
    }

  }

  Map<String, String> data = new HashMap<String, String>();
  public enabled enabled;

  public Map<String, String> getData() {
    return data;
  }

  public void setData(Map<String, String> data) {
    this.data = data;
  }

  public enabled getEnabled() {
    return enabled;
  }

  public void setEnabled(enabled enabled) {
    this.enabled = enabled;
  }

}
