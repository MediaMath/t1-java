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

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class VideoCreativeResponse {

    private String creativeId;

    private String key;

    private VideoCreativeUploader uploader;

    private Status status;

    @SerializedName("errors")
    private ArrayList<T1Error> errors;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCreativeId() {
        return creativeId;
    }

    public void setCreativeId(String creativeId) {
        this.creativeId = creativeId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public VideoCreativeUploader getUploader() {
        return uploader;
    }

    public void setUploader(VideoCreativeUploader uploader) {
        this.uploader = uploader;
    }

    public ArrayList<T1Error> getErrors() {
        return errors;
    }

    public void setErrors(ArrayList<T1Error> errors) {
        this.errors = errors;
    }

}
