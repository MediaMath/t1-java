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

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Permissions {

	@JsonProperty("all_organizations")
	private boolean all_organizations;
	@JsonProperty("organization_ids")
	private List<Integer> organization_ids = new ArrayList<>();
	@JsonProperty("agency_ids")
	private List<Integer> agency_ids = new ArrayList<>();
	@JsonProperty("advertiser_ids")
	private List<Integer> advertiser_ids = new ArrayList<>();
	private List<Access> flags = new ArrayList<>();
	private Entities entities;

	class Access {
		private List<Map<String, String>> access = new ArrayList<>();

		public List<Map<String, String>> getAccess() {
			return access;
		}

		public void setAccess(List<Map<String, String>> access) {
			this.access = access;
		}
	}

	class AccessAdvertiser {
		private List<Advertiser> access = new ArrayList<>();

		public List<Advertiser> getAccess() {
			return access;
		}

		public void setAccess(List<Advertiser> access) {
			this.access = access;
		}
	}

	class AccessAgency {
		private List<Agency> access = new ArrayList<>();

		public List<Agency> getAccess() {
			return access;
		}

		public void setAccess(List<Agency> access) {
			this.access = access;
		}
	}

	class AccessOrganization {
		private List<Organization> access = new ArrayList<>();

		public List<Organization> getAccess() {
			return access;
		}

		public void setAccess(List<Organization> access) {
			this.access = access;
		}
	}

	class Entities {
		private List<AccessAdvertiser> advertiser = new ArrayList<>();
		private List<AccessAdvertiser> agency = new ArrayList<>();
		private List<AccessAdvertiser> organization = new ArrayList<>();
		
		public List<AccessAdvertiser> getAdvertiser() {
			return advertiser;
		}
		public void setAdvertiser(List<AccessAdvertiser> advertiser) {
			this.advertiser = advertiser;
		}
		public List<AccessAdvertiser> getAgency() {
			return agency;
		}
		public void setAgency(List<AccessAdvertiser> agency) {
			this.agency = agency;
		}
		public List<AccessAdvertiser> getOrganization() {
			return organization;
		}
		public void setOrganization(List<AccessAdvertiser> organization) {
			this.organization = organization;
		}
	}

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

	public void setAgencyIds(List<Integer> agency_ids) {
		this.agency_ids = agency_ids;
	}

	public List<Integer> getAdvertiserIds() {
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
