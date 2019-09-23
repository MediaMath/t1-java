package com.mediamath.terminalone.models;

import java.util.Set;

public class TaxonomyDetails {
	private String name;
	private Set<TaxonomySegment> children;

	public TaxonomyDetails() {
	}

	public TaxonomyDetails(String name, Set<TaxonomySegment> children) {
		this.name = name;
		this.children = children;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<TaxonomySegment> getChildren() {
		return children;
	}

	public void setChildren(Set<TaxonomySegment> children) {
		this.children = children;
	}
}
