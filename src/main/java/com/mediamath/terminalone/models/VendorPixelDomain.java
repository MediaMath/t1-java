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
import java.util.Date;

public class VendorPixelDomain extends Entity {

    public VendorPixelDomain() {
        super("VendorPixelDomain");
    }

    private Date created_on;
    private String domain;
    private int id;
    private int vendor_domain_id;
    private int vendor_pixel_id;
    private int version;

    private VendorDomain vendor_domain;
    private VendorPixel vendor_pixel;

    public Date getCreatedOn() {
        return created_on;
    }

    public void setCreatedOn(Date created_on) {
        this.created_on = created_on;
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

    public int getVendorDomainId() {
        return vendor_domain_id;
    }

    public void setVendorDomainId(int vendor_domain_id) {
        this.vendor_domain_id = vendor_domain_id;
    }

    public int getVendorPixelId() {
        return vendor_pixel_id;
    }

    public void setVendorPixelId(int vendor_pixel_id) {
        this.vendor_pixel_id = vendor_pixel_id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public VendorDomain getVendorDomain() {
        return vendor_domain;
    }

    public void setVendorDomain(VendorDomain vendor_domain) {
        this.vendor_domain = vendor_domain;
    }

    public VendorPixel getVendorPixel() {
        return vendor_pixel;
    }

    public void setVendorPixel(VendorPixel vendor_pixel) {
        this.vendor_pixel = vendor_pixel;
    }

}
