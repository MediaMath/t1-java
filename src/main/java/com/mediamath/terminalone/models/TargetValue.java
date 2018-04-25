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

import javax.ws.rs.core.Form;

public class TargetValue implements T1Entity {

    private static final String entityName = "TargetValue";

    private String _type;
    private String code;
    private int id;
    private boolean is_targetable;
    private String name;
    private int target_dimension_id;
    private int value;

    private TargetDimension target_dimension;

    public String getType() {
        return _type;
    }

    public void setType(String _type) {
        this._type = _type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isTargetable() {
        return is_targetable;
    }

    public void setIsTargetable(boolean is_targetable) {
        this.is_targetable = is_targetable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTargetDimensionId() {
        return target_dimension_id;
    }

    public void setTargetDimensionId(int target_dimension_id) {
        this.target_dimension_id = target_dimension_id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public TargetDimension getTargetDimension() {
        return target_dimension;
    }

    public void setTargetDimension(TargetDimension target_dimension) {
        this.target_dimension = target_dimension;
    }

    @Override
    public String getEntityname() {
        return entityName;
    }

    @Override
    public Form getForm() {
        return null;
    }

    @Override
    public String getUri() {
        return null;
    }

}
