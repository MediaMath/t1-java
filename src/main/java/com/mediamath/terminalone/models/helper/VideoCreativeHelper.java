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

package com.mediamath.terminalone.models.helper;

import com.google.gson.Gson;
import com.mediamath.terminalone.models.VideoCreative;

public class VideoCreativeHelper {

	private VideoCreativeHelper() {
		throw new IllegalAccessError("VideoCreativeHelper cannot be instantiated");
	}

	/**
	 * forms a JSON object from the given video creative entity.
	 * 
	 * @param entity
	 *            expects a VideoCreative entity.
	 * @return String object.
	 */
	public static String getJson(VideoCreative entity) {
		Gson gson = new Gson();
		return gson.toJson(entity);
	}

}
