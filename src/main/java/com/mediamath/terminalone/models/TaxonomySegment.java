package com.mediamath.terminalone.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

public class TaxonomySegment {
    private String code;
	private Integer uniques;
	@SerializedName("retail_cpm")
	private BigDecimal retailCpm;
	private Boolean buyable;
	private String name;
	private Set<TaxonomySegment> children;
	private Integer id;

	public TaxonomySegment() {
	}

	public TaxonomySegment(String code, Integer uniques, BigDecimal retailCpm, Boolean buyable, String name, Set<TaxonomySegment> children, Integer id) {
		this.code = code;
		this.uniques = uniques;
		this.retailCpm = retailCpm;
		this.buyable = buyable;
		this.name = name;
		this.children = children;
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getUniques() {
		return uniques;
	}

	public void setUniques(Integer uniques) {
		this.uniques = uniques;
	}

	public BigDecimal getRetailCpm() {
		return retailCpm;
	}

	public void setRetailCpm(BigDecimal retailCpm) {
		this.retailCpm = retailCpm;
	}

	public Boolean getBuyable() {
		return buyable;
	}

	public void setBuyable(Boolean buyable) {
		this.buyable = buyable;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof TaxonomySegment)) return false;
		TaxonomySegment that = (TaxonomySegment) o;
		return getCode().equals(that.getCode()) &&
				getName().equals(that.getName()) &&
				Objects.equals(getId(), that.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getCode());
	}
}
