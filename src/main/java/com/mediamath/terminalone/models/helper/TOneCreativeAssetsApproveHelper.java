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

import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import com.mediamath.terminalone.models.TOneASCreativeAssetsApprove;
import com.mediamath.terminalone.models.TOneASCreativeAssetsApproveData;
import com.mediamath.terminalone.utils.Utility;

public class TOneCreativeAssetsApproveHelper {

	public static void getMultiPartForm(TOneASCreativeAssetsApprove entity, FormDataMultiPart formData) {
		
		if(entity != null) {
			if(!entity.getDataList().isEmpty()) {
				
				for(int i = 0; i < entity.getDataList().size(); i++) {
					int inc = i + 1;
					TOneASCreativeAssetsApproveData data = entity.getDataList().get(i);
					
					if(data.isIs_https()) {
						formData = formData.field("is_https", Utility.getOneOrZero(data.isIs_https()));
					}
					
					if(data.getAdvertiserid() != null && !data.getAdvertiserid().isEmpty()) {
						formData = formData.field("advertiserid", data.getAdvertiserid());
					}
					
					if(data.getLandingPage() != null && !data.getLandingPage().isEmpty()) {
						formData = formData.field("landingPage." + inc, data.getLandingPage());
					}
					
					if(data.getClick_url() != null && !data.getClick_url().isEmpty()) {
						formData = formData.field("click_url." + inc, data.getClick_url());
					}
					
					if(data.getPrimary() != null && !data.getPrimary().isEmpty()) {
						formData = formData.field("primary." + inc, data.getPrimary());
					}
					
					if(data.getBackup() != null && !data.getBackup().isEmpty()) {
						formData = formData.field("backup." + inc, data.getBackup());
					}
					
					if(data.getConcept() != null && !data.getConcept().isEmpty()) {
						formData = formData.field("concept." + inc, data.getConcept());
					}
					
				}
			}
		}
		
		
		
	}

}
