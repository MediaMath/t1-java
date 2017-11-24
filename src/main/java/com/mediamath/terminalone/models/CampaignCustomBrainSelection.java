package com.mediamath.terminalone.models;

import java.util.ArrayList;

public class CampaignCustomBrainSelection {

	public enum SELTYPES {
		EventPixel("Event Pixel"), Dynamic("Dynamic"), AudienceTarget("Audience Target"), DataPixel("Data Pixel");
		
		String value;
		SELTYPES(String s) {
			value = s;
		}
		public String getValue(){
			return value;
		}
	};

	private int campaign_id;
	private SELTYPES selection_type;
	private int selection_id;
	private boolean active;
	private int id;
	private int load_count;
	private String name;
	private String owner_name;
	private int is_retired;
	private int parent_audience_segment_id;
	private String full_path;
	private int child_count;
	private ArrayList<Currency> retail_cpm = new ArrayList<>();
	private ArrayList<Currency> wholesale_cpm = new ArrayList<>();
	private String refer_url;
	private int version;
	private String selection_name;
	private int uniques;
	private boolean isDeleted = false;

	public int getCampaignId() {
		return campaign_id;
	}

	public void setCampaignId(int campaign_id) {
		this.campaign_id = campaign_id;
	}

	public SELTYPES getSelectionType() {
		return selection_type;
	}

	public void setSelectionType(SELTYPES selection_type) {
		this.selection_type = selection_type;
	}

	public int getSelectionId() {
		return selection_id;
	}

	public void setSelectionId(int selection_id) {
		this.selection_id = selection_id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLoadCount() {
		return load_count;
	}

	public void setLoadCount(int load_count) {
		this.load_count = load_count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwnerName() {
		return owner_name;
	}

	public void setOwnerName(String owner_name) {
		this.owner_name = owner_name;
	}

	public int isIsRetired() {
		return is_retired;
	}

	public void setIsRetired(int is_retired) {
		this.is_retired = is_retired;
	}

	public int getParentAudienceSegmentId() {
		return parent_audience_segment_id;
	}

	public void setParentAudienceSegmentId(int parent_audience_segment_id) {
		this.parent_audience_segment_id = parent_audience_segment_id;
	}

	public String getFullPath() {
		return full_path;
	}

	public void setFullPath(String full_path) {
		this.full_path = full_path;
	}

	public int getChildCount() {
		return child_count;
	}

	public void setChildCount(int child_count) {
		this.child_count = child_count;
	}

	public ArrayList<Currency> getRetailCpm() {
		return retail_cpm;
	}

	public void setRetailCpm(ArrayList<Currency> retail_cpm) {
		this.retail_cpm = retail_cpm;
	}

	public ArrayList<Currency> getWholesaleCpm() {
		return wholesale_cpm;
	}

	public void setWholesaleCpm(ArrayList<Currency> wholesale_cpm) {
		this.wholesale_cpm = wholesale_cpm;
	}

	public String getReferUrl() {
		return refer_url;
	}

	public void setReferUrl(String refer_url) {
		this.refer_url = refer_url;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getSelectionName() {
		return selection_name;
	}

	public void setSelectionName(String selection_name) {
		this.selection_name = selection_name;
	}

	public int getUniques() {
		return uniques;
	}

	public void setUniques(int uniques) {
		this.uniques = uniques;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}
