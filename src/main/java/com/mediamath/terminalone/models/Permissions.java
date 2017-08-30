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
import java.util.Map;

public class Permissions {

	private boolean all_organizations;
	private List<Integer> organization_ids = new ArrayList<>();
	private List<Integer> agency_ids = new ArrayList<>();
	private List<Integer> advertiser_ids = new ArrayList<>();
	
	class Access{
		public List<Map<String, String>> access = new ArrayList<Map<String, String>>();
	}
	
	class AccessAdvertiser{
		public List<Advertiser> access = new ArrayList<Advertiser>(); 
	}
	class AccessAgency{
		public List<Agency> access = new ArrayList<Agency>(); 
	}
	class AccessOrganization{
		public List<Organization> access = new ArrayList<Organization>(); 
	}
	
	class Entities{
		public List<AccessAdvertiser> advertiser = new ArrayList<AccessAdvertiser>();
		public List<AccessAdvertiser> agency = new ArrayList<AccessAdvertiser>();
		public List<AccessAdvertiser> organization = new ArrayList<AccessAdvertiser>();
	}
	
	private Entities entities;
	private List<Access> flags = new ArrayList<Access>();

	public boolean isAllOrganizations() {
		return all_organizations;
	}

	public void setAllOrganizations(boolean all_organizations) {
		this.all_organizations = all_organizations;
	}

	public List<Integer> getOrganizationIds() {
		return organization_ids;
	}

	public void setOrganizationIds(List<Integer> organization_ids) {
		this.organization_ids = organization_ids;
	}

	public List<Integer> getAgencyIds() {
		return agency_ids;
	}

	public void setAgency_ids(List<Integer> agency_ids) {
		this.agency_ids = agency_ids;
	}

	public List<Integer> getAdvertiser_ids() {
		return advertiser_ids;
	}

	public void setAdvertiserIds(List<Integer> advertiser_ids) {
		this.advertiser_ids = advertiser_ids;
	}

	public Entities getEntities() {
		return entities;
	}

	public void setEntities(Entities entities) {
		this.entities = entities;
	}

	public List<Access> getFlags() {
		return flags;
	}

	public void setFlags(List<Access> flags) {
		this.flags = flags;
	}

}
