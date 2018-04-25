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

public class T1Meta {

    private String etag;
    private String called_on;
    private String status;
    private String count;
    private String offset;
    private String total_count;
    private String next_page;

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getCalledOn() {
        return called_on;
    }

    public void setCalledOn(String called_on) {
        this.called_on = called_on;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getTotalCount() {
        return total_count;
    }

    public void setTotalCount(String total_count) {
        this.total_count = total_count;
    }

    public String getNextPage() {
        return next_page;
    }

    public void setNextPage(String next_page) {
        this.next_page = next_page;
    }

}
