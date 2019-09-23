package com.mediamath.terminalone.models;
import java.util.Set;

public class TaxonomyPermissions {

	private Set<Integer> organizations;
	private Set<Integer> agencies;
	private Set<Integer> advertisers;

	public TaxonomyPermissions() {
	}

	public TaxonomyPermissions(Set<Integer> organizations, Set<Integer> agencies, Set<Integer> advertisers) {
		this.organizations = organizations;
		this.agencies = agencies;
		this.advertisers = advertisers;
	}

	public Set<Integer> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(Set<Integer> organizations) {
		this.organizations = organizations;
	}

	public Set<Integer> getAgencies() {
		return agencies;
	}

	public void setAgencies(Set<Integer> agencies) {
		this.agencies = agencies;
	}

	public Set<Integer> getAdvertisers() {
		return advertisers;
	}

	public void setAdvertisers(Set<Integer> advertisers) {
		this.advertisers = advertisers;
	}
}
