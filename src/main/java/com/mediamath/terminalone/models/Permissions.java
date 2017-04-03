package com.mediamath.terminalone.models;

import java.util.ArrayList;
import java.util.List;

public class Permissions {

	private boolean all_organizations;
	private List<Integer> organization_ids = new ArrayList<>();
	private List<Integer> agency_ids = new ArrayList<>();
	private List<Integer> advertiser_ids = new ArrayList<>();

	public boolean isAll_organizations() {
		return all_organizations;
	}

	public void setAll_organizations(boolean all_organizations) {
		this.all_organizations = all_organizations;
	}

	public List<Integer> getOrganization_ids() {
		return organization_ids;
	}

	public void setOrganization_ids(List<Integer> organization_ids) {
		this.organization_ids = organization_ids;
	}

	public List<Integer> getAgency_ids() {
		return agency_ids;
	}

	public void setAgency_ids(List<Integer> agency_ids) {
		this.agency_ids = agency_ids;
	}

	public List<Integer> getAdvertiser_ids() {
		return advertiser_ids;
	}

	public void setAdvertiser_ids(List<Integer> advertiser_ids) {
		this.advertiser_ids = advertiser_ids;
	}

}
