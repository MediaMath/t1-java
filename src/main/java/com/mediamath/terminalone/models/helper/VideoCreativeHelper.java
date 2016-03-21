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
	
	public static String getJson(VideoCreative entity) {
		Gson g = new Gson();
		String response = g.toJson(entity);
		return response;
	}
	
	public static Form getForm(VideoCreative entity) {
		Form form = new Form();
		if(entity != null) {
			if(entity.getName() != null && !entity.getName().isEmpty()) {
				form.param("name", entity.getName());
			}
			
			if(entity.getStartTime() > 0) {
				form.param("startTime", String.valueOf(entity.getStartTime()));
			}
			if(entity.getLandingUrl() != null && !entity.getLandingUrl().isEmpty()) {
				form.param("landingUrl", entity.getLandingUrl());
			}
			if(entity.getCustomVAST() != null && !entity.getCustomVAST().isEmpty()) {
				form.param("customVAST", entity.getCustomVAST());
			}
			
			if(entity.getVendors().size() > 0) {
				Gson g= new Gson();
				String vendors = g.toJson(entity.getVendors());
				form.param("vendors", vendors);
			}
			
			if(entity.getAdvertiser() > 0) {
				form.param("advertiser", String.valueOf(entity.getAdvertiser()));
			}
			if(entity.getEventPixels() != null) {
				
			}
			
			form.param("desktopEncoding", String.valueOf(entity.isDesktopEncoding()));
			
			if(entity.getEndTime() > 0) {
				form.param("endTime", String.valueOf(entity.getEndTime()));
			}
			if(entity.getSkippableDuration() > 0) {
				form.param("skippableDuration", String.valueOf(entity.getSkippableDuration()));
			}
			
			form.param("mobileEncoding", String.valueOf(entity.isMobileEncoding()));
			
			if(entity.getConcept() > 0) {
				form.param("concept", String.valueOf(entity.getConcept()));
			}
			if(entity.getClickthroughUrl() != null && !entity.getClickthroughUrl().isEmpty()) {
				form.param("clickthroughUrl", entity.getClickthroughUrl());
			}
			
			form.param("active", String.valueOf(entity.isActive()));
			
		}
		return form;
	}

}
