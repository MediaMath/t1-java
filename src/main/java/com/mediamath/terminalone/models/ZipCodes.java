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

import com.mediamath.terminalone.exceptions.ClientException;

import javax.ws.rs.core.Form;

public class ZipCodes implements T1Entity {

    private static final String entityName = "ZipCodes";

    public enum restrictions {
        INCLUDE, EXCLUDE
    }

    private int strategyId;
    private restrictions restriction;
    private String file;
    private boolean validate_only;
    private boolean ignore_errors;
    private boolean active;

    public ZipCodes() {
    }

    public ZipCodes(int strategyId, restrictions restriction, String file, boolean validate_only, boolean ignore_errors,
                    boolean active) {
        super();
        this.strategyId = strategyId;
        this.restriction = restriction;
        this.file = file;
        this.validate_only = validate_only;
        this.ignore_errors = ignore_errors;
        this.active = active;
    }


    public int getStrategyId() {
        return strategyId;
    }


    public void setStrategyId(int strategyId) {
        this.strategyId = strategyId;
    }


    public restrictions getRestriction() {
        return restriction;
    }

    public void setRestriction(restrictions restriction) {
        this.restriction = restriction;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public boolean isValidateOnly() {
        return validate_only;
    }

    public void setValidateOnly(boolean validate_only) {
        this.validate_only = validate_only;
    }

    public boolean isIgnoreErrors() {
        return ignore_errors;
    }

    public void setIgnoreErrors(boolean ignore_errors) {
        this.ignore_errors = ignore_errors;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
    public String getUri() throws ClientException {
        return null;
    }

}
