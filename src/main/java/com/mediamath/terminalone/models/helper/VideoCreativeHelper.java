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

import javax.ws.rs.core.Form;

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

	/**
	 * creates a VideoCreative Form object.
	 * 
	 * @param entity
	 *            expects a VideoCreative entity.
	 * @return Form object.
	 */
	public static Form getForm(VideoCreative entity) {
		Form form = new Form();
		if (entity != null) {
			setName(entity, form);

			setStartTime(entity, form);

			setLandingURL(entity, form);

			setCustomVAST(entity, form);

			setVendors(entity, form);

			setAdvertiser(entity, form);

			form.param("desktopEncoding", String.valueOf(entity.isDesktopEncoding()));

			setEndTime(entity, form);

			setSkippableDuration(entity, form);

			form.param("mobileEncoding", String.valueOf(entity.isMobileEncoding()));

			setConcept(entity, form);

			setClickThroughURL(entity, form);

			setEventPixels(entity, form);

			form.param("active", String.valueOf(entity.isActive()));

		}
		return form;
	}
	
	private static void setEventPixels(VideoCreative entity, Form form) {
		if (entity.getEventPixels() != null && !entity.getEventPixels().isEmpty()) {
			Gson gson = new Gson();
			String eventPixels = gson.toJson(entity.getEventPixels());
			form.param("eventPixels", eventPixels);
		}
	}

	private static void setClickThroughURL(VideoCreative entity, Form form) {
		if (entity.getClickthroughUrl() != null && !entity.getClickthroughUrl().isEmpty()) {
			form.param("clickthroughUrl", entity.getClickthroughUrl());
		}
	}

	private static void setConcept(VideoCreative entity, Form form) {
		if (entity.getConcept() > 0) {
			form.param("concept", String.valueOf(entity.getConcept()));
		}
	}

	private static void setSkippableDuration(VideoCreative entity, Form form) {
		if (entity.getSkippableDuration() > 0) {
			form.param("skippableDuration", String.valueOf(entity.getSkippableDuration()));
		}
	}

	private static void setEndTime(VideoCreative entity, Form form) {
		if (entity.getEndTime() > 0) {
			form.param("endTime", String.valueOf(entity.getEndTime()));
		}
	}

	private static void setAdvertiser(VideoCreative entity, Form form) {
		if (entity.getAdvertiser() > 0) {
			form.param("advertiser", String.valueOf(entity.getAdvertiser()));
		}
	}

	private static void setVendors(VideoCreative entity, Form form) {
		if (!entity.getVendors().isEmpty()) {
			Gson gson = new Gson();
			String vendors = gson.toJson(entity.getVendors());
			form.param("vendors", vendors);
		}
	}

	private static void setCustomVAST(VideoCreative entity, Form form) {
		if (entity.getCustomVAST() != null && !entity.getCustomVAST().isEmpty()) {
			form.param("customVAST", entity.getCustomVAST());
		}
	}

	private static void setLandingURL(VideoCreative entity, Form form) {
		if (entity.getLandingUrl() != null && !entity.getLandingUrl().isEmpty()) {
			form.param("landingUrl", entity.getLandingUrl());
		}
	}

	private static void setStartTime(VideoCreative entity, Form form) {
		if (entity.getStartTime() > 0) {
			form.param("startTime", String.valueOf(entity.getStartTime()));
		}
	}

	private static void setName(VideoCreative entity, Form form) {
		if (entity.getName() != null && !entity.getName().isEmpty()) {
			form.param("name", entity.getName());
		}
	}

}
