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

		data.setIs_https(is_https);

		if (advertiserid != null && !advertiserid.isEmpty())
			data.setAdvertiserid(advertiserid);

		if (landingPage != null && !landingPage.isEmpty())
			data.setLandingPage(landingPage);

		if (click_url != null && !click_url.isEmpty())
			data.setClick_url(click_url);

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
