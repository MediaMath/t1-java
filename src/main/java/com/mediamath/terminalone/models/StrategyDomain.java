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
import java.text.SimpleDateFormat;
import java.util.Date;

public class StrategyDomain extends Entity {

    private static final String YYYY_MM_DDTHH_MM_SS_Z = "yyyy-MM-dd'T'HH:mm:ss Z";

    public enum restrictions {
        INCLUDE, EXCLUDE
    }

    private Date created_at;
    private String domain;
    private int id;
    private restrictions restriction;
    private int strategy_id;
    private Date updated_on;
    private int version;

    private Strategy strategy;

    public StrategyDomain() {
        super("StrategyDomain");
    }

    public StrategyDomain(String domain, restrictions restriction) {
        super("StrategyDomain");
        this.domain = domain;
        this.restriction = restriction;
    }

    public Date getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(Date created_at) {
        this.created_at = created_at;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public restrictions getRestriction() {
        return restriction;
    }

    public void setRestriction(restrictions restriction) {
        this.restriction = restriction;
    }

    public int getStrategyId() {
        return strategy_id;
    }

    public void setStrategyId(int strategy_id) {
        this.strategy_id = strategy_id;
    }

    public Date getUpdatedOn() {
        return updated_on;
    }

    public void setUpdatedOn(Date updated_on) {
        this.updated_on = updated_on;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public Form getForm() {
        final SimpleDateFormat SDF = new SimpleDateFormat(YYYY_MM_DDTHH_MM_SS_Z);

        Form strategyDomainForm = new Form();

        if (this.getDomain() != null) {
            strategyDomainForm.param("domain", String.valueOf(this.getDomain()));
        }

        if (this.getStrategyId() > 0) {
            strategyDomainForm.param("strategy_id", String.valueOf(this.getStrategyId()));
        }

        if (this.getRestriction() != null) {
            strategyDomainForm.param("restriction", this.getRestriction().name());
        }

        if (this.getCreatedAt() != null) {
            String createdAt = SDF.format(this.getCreatedAt());
            strategyDomainForm.param("created_at", createdAt);
        }

        if (this.getId() > 0) {
            if (this.getVersion() > 0) {
                strategyDomainForm.param("restriction", String.valueOf(this.getVersion()));
            }
            if (this.getUpdatedOn() != null) {
                String updatedOn = SDF.format(this.getUpdatedOn());
                strategyDomainForm.param("updated_on", updatedOn);
            }

        }

        return strategyDomainForm;
    }

    @Override
    public String getUri() {
        StringBuilder uri = new StringBuilder();

        if (this.getId() > 0) {
            uri.append("/" + this.getId());
        }
        return uri.toString();
    }

}
