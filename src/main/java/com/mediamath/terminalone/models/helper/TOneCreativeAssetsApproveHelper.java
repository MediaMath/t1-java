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

	private TOneCreativeAssetsApproveHelper() {
		throw new IllegalAccessError("TOneCreativeAssetsApproveHelper cannot be instantiated");
	}

	/**
	 * Creates a Multi Part Form object.
	 * 
	 * @param entity
	 *            expects a TOneASCreativeAssetsApprove entity.
	 * @param formData
	 *            expects a FormDataMultiPart formData object.
	 * @return 
	 */
	public static FormDataMultiPart getMultiPartForm(TOneASCreativeAssetsApprove entity, FormDataMultiPart formData) {

		FormDataMultiPart localFormData = formData;
		
		if (entity != null && !entity.getDataList().isEmpty()) {

			for (int i = 0; i < entity.getDataList().size(); i++) {

				int inc = i + 1;

				TOneASCreativeAssetsApproveData data = entity.getDataList().get(i);

				localFormData = setIsHttps(localFormData, data);

				localFormData = setAdvertiserID(localFormData, data);

				localFormData = setLandingPage(localFormData, inc, data);

				localFormData = setClickURL(localFormData, inc, data);

				localFormData = setPrimary(localFormData, inc, data);

				localFormData = setBackup(localFormData, inc, data);

				localFormData = setConcept(localFormData, inc, data);

			}

		}
		
		return localFormData;

	}

	private static FormDataMultiPart setConcept(FormDataMultiPart formData, int inc,
			TOneASCreativeAssetsApproveData data) {
		FormDataMultiPart localFormData = formData;
		if (data.getConcept() != null && !data.getConcept().isEmpty()) {
			localFormData = localFormData.field("concept." + inc, data.getConcept());
		}
		return localFormData;
	}

	private static FormDataMultiPart setBackup(FormDataMultiPart formData, int inc,
			TOneASCreativeAssetsApproveData data) {
		FormDataMultiPart localFormData = formData;
		if (data.getBackup() != null && !data.getBackup().isEmpty()) {
			localFormData = localFormData.field("backup." + inc, data.getBackup());
		}
		return localFormData;
	}

	private static FormDataMultiPart setPrimary(FormDataMultiPart formData, int inc,
			TOneASCreativeAssetsApproveData data) {
		FormDataMultiPart localFormData = formData;
		if (data.getPrimary() != null && !data.getPrimary().isEmpty()) {
			localFormData = localFormData.field("primary." + inc, data.getPrimary());
		}
		return localFormData;
	}

	private static FormDataMultiPart setClickURL(FormDataMultiPart formData, int inc,
			TOneASCreativeAssetsApproveData data) {
		FormDataMultiPart localFormData = formData;
		if (data.getClickUrl() != null && !data.getClickUrl().isEmpty()) {
			localFormData = localFormData.field("click_url." + inc, data.getClickUrl());
		}
		return localFormData;
	}

	private static FormDataMultiPart setLandingPage(FormDataMultiPart formData, int inc,
			TOneASCreativeAssetsApproveData data) {
		FormDataMultiPart localFormData = formData;
		if (data.getLandingPage() != null && !data.getLandingPage().isEmpty()) {
			localFormData = localFormData.field("landingPage." + inc, data.getLandingPage());
		}
		return localFormData;
	}

	private static FormDataMultiPart setAdvertiserID(FormDataMultiPart formData, TOneASCreativeAssetsApproveData data) {
		FormDataMultiPart localFormData = formData;
		if (data.getAdvertiserid() != null && !data.getAdvertiserid().isEmpty()) {
			localFormData = localFormData.field("advertiserid", data.getAdvertiserid());
		}
		return localFormData;
	}

	private static FormDataMultiPart setIsHttps(FormDataMultiPart formData, TOneASCreativeAssetsApproveData data) {
		FormDataMultiPart localFormData = formData;
		if (data.isHttps()) {
			localFormData = localFormData.field("is_https", Utility.getOneOrZero(data.isHttps()));
		}
		return localFormData;
	}

}
