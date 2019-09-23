package com.mediamath.terminalone.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import javax.ws.rs.core.Form;

public class Taxonomy implements T1Entity {

	private static final String entityName = "Taxonomy";

	private TaxonomyPermissions permissions;
	@SerializedName("vendor_id")
	private Integer vendorId;
	private TaxonomyDetails taxonomy;
	@SerializedName("audience_vendor_id")
	private Integer audienceVendorId;
	@SerializedName("revenue_share_pct")
	private Integer revenueSharePct;
	@SerializedName("taxonomy_id")
	private Integer taxonomyId;

	public Taxonomy() {
	}

	public Taxonomy(TaxonomyPermissions permissions, Integer vendorId, TaxonomyDetails taxonomy) {
		this.permissions = permissions;
		this.vendorId = vendorId;
		this.taxonomy = taxonomy;
	}

	public Taxonomy(TaxonomyPermissions permissions, Integer vendorId, TaxonomyDetails taxonomy, Integer audienceVendorId, Integer revenueSharePct, Integer taxonomyId) {
		this.permissions = permissions;
		this.vendorId = vendorId;
		this.taxonomy = taxonomy;
		this.audienceVendorId = audienceVendorId;
		this.revenueSharePct = revenueSharePct;
		this.taxonomyId = taxonomyId;
	}

	public static String getEntityName() {
		return entityName;
	}

	public TaxonomyPermissions getPermissions() {
		return permissions;
	}

	public void setPermissions(TaxonomyPermissions permissions) {
		this.permissions = permissions;
	}

	public Integer getVendorId() {
		return vendorId;
	}

	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}

	public TaxonomyDetails getTaxonomy() {
		return taxonomy;
	}

	public void setTaxonomy(TaxonomyDetails taxonomy) {
		this.taxonomy = taxonomy;
	}

	public Integer getAudienceVendorId() {
		return audienceVendorId;
	}

	public void setAudienceVendorId(Integer audienceVendorId) {
		this.audienceVendorId = audienceVendorId;
	}

	public Integer getRevenueSharePct() {
		return revenueSharePct;
	}

	public void setRevenueSharePct(Integer revenueSharePct) {
		this.revenueSharePct = revenueSharePct;
	}

	public Integer getTaxonomyId() {
		return taxonomyId;
	}

	public void setTaxonomyId(Integer taxonomyId) {
		this.taxonomyId = taxonomyId;
	}

	@Override
	@JsonIgnore
	public Form getForm() {
		return null;
	}

	@Override
	@JsonIgnore
	public String getUri() {
		return null;
	}

	@Override
	public String getEntityname() {
		return entityName;
	}
}
