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

import com.mediamath.terminalone.utils.Utility;

import javax.ws.rs.core.Form;
import java.util.Date;

public class ChildPixel extends Entity {

    public ChildPixel() {
        super("ChildPixel");
    }

    private int bundle_id;
    private Date created_on;
    private boolean distributed;
    private int id;
    private String pixel_type;
    private int supply_source_id;
    private String tag;
    private Date updated_on;
    private int version;

    private Pixel pixel_bundle;

    public int getBundleId() {
        return bundle_id;
    }

    public void setBundleId(int bundle_id) {
        this.bundle_id = bundle_id;
    }

    public Date getCreatedOn() {
        return created_on;
    }

    public void setCreatedOn(Date created_on) {
        this.created_on = created_on;
    }

    public boolean isDistributed() {
        return distributed;
    }

    public void setDistributed(boolean distributed) {
        this.distributed = distributed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPixelType() {
        return pixel_type;
    }

    public void setPixelType(String pixel_type) {
        this.pixel_type = pixel_type;
    }

    public int getSupplySourceId() {
        return supply_source_id;
    }

    public void setSupplySourceId(int supply_source_id) {
        this.supply_source_id = supply_source_id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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

    public Pixel getPixelBundle() {
        return pixel_bundle;
    }

    public void setPixelBundle(Pixel pixel_bundle) {
        this.pixel_bundle = pixel_bundle;
    }

    @Override
    public Form getForm() {

        Form pixelForm = new Form();

        if (this.getBundleId() > 0) {
            pixelForm.param("bundle_id", String.valueOf(this.getBundleId()));
        }

        pixelForm.param("distributed", Utility.getOnOrOff(this.isDistributed()));

        if (this.getPixelType() != null) {
            pixelForm.param("pixel_type", this.getPixelType());
        }

        if (this.getTag() != null) {
            pixelForm.param("tag", this.getTag());
        }

        if (this.getSupplySourceId() > 0) {
            pixelForm.param("supply_source_id", String.valueOf(this.getSupplySourceId()));
        }

        if (this.getVersion() >= 0) {
            pixelForm.param("version", String.valueOf(this.getVersion()));
        }

        return Utility.getFilteredForm(pixelForm, "childpixel");
    }


    @Override
    public String getUri() {
        StringBuilder uri = new StringBuilder();
        if (this.getId() > 0) {
            uri.append("/");
            uri.append(this.getId());
        }
        return uri.toString();
    }

}
