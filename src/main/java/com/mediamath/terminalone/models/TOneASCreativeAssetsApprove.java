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

public class TOneASCreativeAssetsApprove implements T1Entity {

	private static final String entityName = "TOneCreativeAssetsApprove";

	private ArrayList<TOneASCreativeAssetsApproveData> dataList = new ArrayList<TOneASCreativeAssetsApproveData>();

	public void create(boolean is_https, 
						String advertiserid, 
						String landingPage, 
						String click_url, 
						String primary,
						String backup, 
						String concept) 
	{
		TOneASCreativeAssetsApproveData data = new TOneASCreativeAssetsApproveData();

		data.setIsHttps(is_https);

		if (advertiserid != null && !advertiserid.isEmpty())
			data.setAdvertiserid(advertiserid);

		if (landingPage != null && !landingPage.isEmpty())
			data.setLandingPage(landingPage);

		if (click_url != null && !click_url.isEmpty())
			data.setClickUrl(click_url);

		if (primary != null && !primary.isEmpty())
			data.setPrimary(primary);

		if (concept != null && !concept.isEmpty())
			data.setConcept(concept);

		if (backup != null && !backup.isEmpty())
			data.setBackup(backup);

		dataList.add(data);
	}

	@Override
	public String getEntityname() {
		return entityName;
	}

	public ArrayList<TOneASCreativeAssetsApproveData> getDataList() {
		return dataList;
	}

	public void setDataList(ArrayList<TOneASCreativeAssetsApproveData> dataList) {
		this.dataList = dataList;
	}

}
