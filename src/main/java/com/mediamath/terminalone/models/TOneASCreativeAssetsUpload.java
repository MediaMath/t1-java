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

public class TOneASCreativeAssetsUpload {

    private List<TOneASCreativeAssetsData> data = new ArrayList<>();

    private T1Meta meta;

    public List<TOneASCreativeAssetsData> getData() {
        return data;
    }

    public void setData(List<TOneASCreativeAssetsData> data) {
        this.data = data;
    }

    public T1Meta getMeta() {
        return meta;
    }

    public void setMeta(T1Meta meta) {
        this.meta = meta;
    }

}
