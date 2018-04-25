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

import java.util.ArrayList;
import java.util.List;

public class StrategyTargetValues extends Entity {

    private Enabled enabled;
    private List<TargetValue> include = new ArrayList<>();
    private List<TargetValue> exclude = new ArrayList<>();
    private String exclude_op;
    private String include_op;

    public class Enabled {
        private String active;

        public String getActive() {
            return active;
        }

        public void setActive(String active) {
            this.active = active;
        }
    }

    public StrategyTargetValues() {
        super("StrategyTargetValues");
    }

    public Enabled getEnabled() {
        return enabled;
    }

    public void setEnabled(Enabled enabled) {
        this.enabled = enabled;
    }

    public List<TargetValue> getInclude() {
        return include;
    }

    public void setInclude(List<TargetValue> include) {
        this.include = include;
    }

    public List<TargetValue> getExclude() {
        return exclude;
    }

    public void setExclude(List<TargetValue> exclude) {
        this.exclude = exclude;
    }

    public String getExcludeOp() {
        return exclude_op;
    }

    public void setExcludeOp(String exclude_op) {
        this.exclude_op = exclude_op;
    }

    public String getIncludeOp() {
        return include_op;
    }

    public void setIncludeOp(String include_op) {
        this.include_op = include_op;
    }

}
