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
