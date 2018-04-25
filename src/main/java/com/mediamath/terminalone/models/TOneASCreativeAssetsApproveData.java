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

public class TOneASCreativeAssetsApproveData {

    private boolean is_https;

    private String advertiserid;

    private String landingPage;

    private String click_url;

    private String primary;

    private String backup;

    private String concept;

    public boolean isHttps() {
        return is_https;
    }

    public void setIsHttps(boolean is_https) {
        this.is_https = is_https;
    }

    public String getAdvertiserid() {
        return advertiserid;
    }

    public void setAdvertiserid(String advertiserid) {
        this.advertiserid = advertiserid;
    }

    public String getLandingPage() {
        return landingPage;
    }

    public void setLandingPage(String landingPage) {
        this.landingPage = landingPage;
    }

    public String getClickUrl() {
        return click_url;
    }

    public void setClickUrl(String click_url) {
        this.click_url = click_url;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public String getBackup() {
        return backup;
    }

    public void setBackup(String backup) {
        this.backup = backup;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }
}
