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

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.core.Form;

import com.mediamath.terminalone.utils.Utility;

public class AtomicCreative implements T1Entity {

	private static final String YYYY_MM_DDTHH_MM_SS_Z = "yyyy-MM-dd'T'HH:mm:ss Z";

	private static final String entityName = "AtomicCreative";

	public enum adFormats {
		DISPLAY, EXPANDABLE, MOBILE
	}

	public enum adServers {
		ATLAS, DART, EYEWONDER, MEDIAMIND, MEDIAPLEX, POINTROLL, YIELD_MANAGER, TERMINALONE, MEDIAFORGE, OTHER
	}

	public enum expandDir {
		NONRESTRICTED
	}

	public enum expandTrig {
		AUTOMATIC, MOUSEOVER, CLICK
	}

	public enum fileTypes {
		swf, gif, html5, jpg, jpeg, tif, tiff, png, unknown, vast
	}

	public enum mediaTypes {
		display, video, mobile
	}

	public enum tagTypes {
		IFRAME_SCRIPT_NOSCRIPT, IFRAME_SCRIPT, IFRAME_NOSCRIPT, IFRAME_IMG, SCRIPT_NOSCRIPT, SCRIPT, NOSCRIPT, IFRAME, IMG
	}

	public enum expandValues {
		L, R, U, D, LD, RD, LU, RU
	}

	private int advertiser_id;
	private adFormats ad_format;
	private adServers ad_server_type;
	private String approval_status;
	private Date build_date;
	private String build_errors;
	private boolean built;
	private int built_by_user_id;
	private String click_through_url;
	private String click_url;
	private int concept_id;
	private Date created_on;
	private int creative_import_file_id;
	private String edited_tag;
	private Date end_date;
	private expandDir expansion_direction;
	private expandTrig expansion_trigger;
	private String external_identifier;
	private fileTypes file_type;
	private boolean has_sound;
	private int height;
	private int id;
	private boolean is_https;
	private boolean is_multi_creative;
	private Date last_modified;
	private mediaTypes media_type;
	private String name;
	private String rejected_reason;
	private boolean rich_media;
	private String rich_media_provider;
	private Date start_date;
	private boolean status;
	private boolean t1as;
	private String tag;
	private tagTypes tag_type;
	private String tpas_ad_tag;
	private String tpas_ad_tag_name;
	private String type;
	private Date updated_on;
	private int version;
	private int width;
	private boolean mraid;
	private expandValues expand;

	private Advertiser advertiser;
	private Concept concept;
	private CreativeApproval creative_approvals;
	private Creative creative;

	public int getAdvertiserId() {
		return advertiser_id;
	}

	public void setAdvertiserId(int advertiser_id) {
		this.advertiser_id = advertiser_id;
	}

	public adFormats getAdFormat() {
		return ad_format;
	}

	public void setAdFormat(adFormats ad_format) {
		this.ad_format = ad_format;
	}

	public adServers getAdServerType() {
		return ad_server_type;
	}

	public void setAdServerType(adServers ad_server_type) {
		this.ad_server_type = ad_server_type;
	}

	public String getApprovalStatus() {
		return approval_status;
	}

	public void setApprovalStatus(String approval_status) {
		this.approval_status = approval_status;
	}

	public Date getBuildDate() {
		return build_date;
	}

	public void setBuildDate(Date build_date) {
		this.build_date = build_date;
	}

	public String getBuildErrors() {
		return build_errors;
	}

	public void setBuildErrors(String build_errors) {
		this.build_errors = build_errors;
	}

	public boolean isBuilt() {
		return built;
	}

	public void setBuilt(boolean built) {
		this.built = built;
	}

	public int getBuiltByUserId() {
		return built_by_user_id;
	}

	public void setBuiltByUserId(int built_by_user_id) {
		this.built_by_user_id = built_by_user_id;
	}

	public String getClickThroughUrl() {
		return click_through_url;
	}

	public void setClickThroughUrl(String click_through_url) {
		this.click_through_url = click_through_url;
	}

	public String getClickUrl() {
		return click_url;
	}

	public void setClickUrl(String click_url) {
		this.click_url = click_url;
	}

	public int getConceptId() {
		return concept_id;
	}

	public void setConceptId(int concept_id) {
		this.concept_id = concept_id;
	}

	public Date getCreatedOn() {
		return created_on;
	}

	public void setCreatedOn(Date created_on) {
		this.created_on = created_on;
	}

	public int getCreativeImportFileId() {
		return creative_import_file_id;
	}

	public void setCreativeImportFileId(int creative_import_file_id) {
		this.creative_import_file_id = creative_import_file_id;
	}

	public String getEditedTag() {
		return edited_tag;
	}

	public void setEditedTag(String edited_tag) {
		this.edited_tag = edited_tag;
	}

	public Date getEndDate() {
		return end_date;
	}

	public void setEndDate(Date end_date) {
		this.end_date = end_date;
	}

	public expandDir getExpansionDirection() {
		return expansion_direction;
	}

	public void setExpansionDirection(expandDir expansion_direction) {
		this.expansion_direction = expansion_direction;
	}

	public expandTrig getExpansionTrigger() {
		return expansion_trigger;
	}

	public void setExpansionTrigger(expandTrig expansion_trigger) {
		this.expansion_trigger = expansion_trigger;
	}

	public String getExternalIdentifier() {
		return external_identifier;
	}

	public void setExternalIdentifier(String external_identifier) {
		this.external_identifier = external_identifier;
	}

	public fileTypes getFileType() {
		return file_type;
	}

	public void setFileType(fileTypes file_type) {
		this.file_type = file_type;
	}

	public boolean isHasSound() {
		return has_sound;
	}

	public void setHasSound(boolean has_sound) {
		this.has_sound = has_sound;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isIsHttps() {
		return is_https;
	}

	public void setIsHttps(boolean is_https) {
		this.is_https = is_https;
	}

	public boolean isIsMultiCreative() {
		return is_multi_creative;
	}

	public void setIsMultiCreative(boolean is_multi_creative) {
		this.is_multi_creative = is_multi_creative;
	}

	public Date getLastModified() {
		return last_modified;
	}

	public void setLastModified(Date last_modified) {
		this.last_modified = last_modified;
	}

	public mediaTypes getMediaType() {
		return media_type;
	}

	public void setMediaType(mediaTypes media_type) {
		this.media_type = media_type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRejectedReason() {
		return rejected_reason;
	}

	public void setRejectedReason(String rejected_reason) {
		this.rejected_reason = rejected_reason;
	}

	public boolean isRichMedia() {
		return rich_media;
	}

	public void setRichMedia(boolean rich_media) {
		this.rich_media = rich_media;
	}

	public String getRichMediaProvider() {
		return rich_media_provider;
	}

	public void setRichMediaProvider(String rich_media_provider) {
		this.rich_media_provider = rich_media_provider;
	}

	public Date getStartDate() {
		return start_date;
	}

	public void setStartDate(Date start_date) {
		this.start_date = start_date;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isT1as() {
		return t1as;
	}

	public void setT1as(boolean t1as) {
		this.t1as = t1as;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public tagTypes getTagType() {
		return tag_type;
	}

	public void setTagType(tagTypes tag_type) {
		this.tag_type = tag_type;
	}

	public String getTpasAdTag() {
		return tpas_ad_tag;
	}

	public void setTpasAdTag(String tpas_ad_tag) {
		this.tpas_ad_tag = tpas_ad_tag;
	}

	public String getTpasAdTagName() {
		return tpas_ad_tag_name;
	}

	public void setTpasAdTagName(String tpas_ad_tag_name) {
		this.tpas_ad_tag_name = tpas_ad_tag_name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getUpdatedOn() {
		return updated_on;
	}

	public void setUpdatedOn(Date updated_on) {
		this.updated_on = updated_on;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Advertiser getAdvertiser() {
		return advertiser;
	}

	public void setAdvertiser(Advertiser advertiser) {
		this.advertiser = advertiser;
	}

	public Concept getConcept() {
		return concept;
	}

	public void setConcept(Concept concept) {
		this.concept = concept;
	}

	public CreativeApproval getCreativeApprovals() {
		return creative_approvals;
	}

	public void setCreativeApprovals(CreativeApproval creative_approvals) {
		this.creative_approvals = creative_approvals;
	}

	public Creative getCreative() {
		return creative;
	}

	public void setCreative(Creative creative) {
		this.creative = creative;
	}

	public boolean isMraid() {
		return mraid;
	}

	public void setMraid(boolean mraid) {
		this.mraid = mraid;
	}

	public expandValues getExpand() {
		return expand;
	}

	public void setExpand(expandValues expand) {
		this.expand = expand;
	}

	@Override
	public String getEntityname() {
		return entityName;
	}

	@Override
	public Form getForm() {

		final SimpleDateFormat SDF = new SimpleDateFormat(YYYY_MM_DDTHH_MM_SS_Z);

		Form atomicCreativeForm = new Form();

		if (this.getAdvertiserId() > 0) {
			atomicCreativeForm.param("advertiser_id", String.valueOf(this.getAdvertiserId()));
		}

		if (this.getAdFormat() != null) {
			atomicCreativeForm.param("ad_format", this.getAdFormat().toString());
		} else {
			atomicCreativeForm.param("ad_format", "DISPLAY");
		}

		if (this.getAdServerType() != null) {
			atomicCreativeForm.param("ad_server_type", this.getAdServerType().toString());
		} else {
			atomicCreativeForm.param("ad_server_type", "OTHER");
		}

		if (this.getApprovalStatus() != null) {
			atomicCreativeForm.param("approval_status", this.getApprovalStatus());
		}

		if (this.getBuildDate() != null) {
			String buildDate = SDF.format(this.getBuildDate());
			atomicCreativeForm.param("build_date", buildDate);
		}

		if (this.getBuildErrors() != null && !this.getBuildErrors().isEmpty()) {
			atomicCreativeForm.param("build_errors", this.getBuildErrors());
		}

		atomicCreativeForm.param("built", Utility.getOnOrOff(this.isBuilt()));

		if (this.getBuiltByUserId() > 0) {
			atomicCreativeForm.param("built_by_user_id", String.valueOf(this.getBuiltByUserId()));
		}

		if (this.getClickThroughUrl() != null && !this.getClickThroughUrl().isEmpty()) {
			atomicCreativeForm.param("click_through_url", this.getClickThroughUrl());
		}

		if (this.getClickUrl() != null && !this.getClickUrl().isEmpty()) {
			atomicCreativeForm.param("click_url", this.getClickUrl());
		}

		if (this.getConceptId() > 0) {
			atomicCreativeForm.param("concept_id", String.valueOf(this.getConceptId()));
		}

		if (this.getCreativeImportFileId() > 0) {
			atomicCreativeForm.param("creative_import_file_id", String.valueOf(this.getCreativeImportFileId()));
		}

		if (this.getEditedTag() != null && !this.getEditedTag().isEmpty()) {
			atomicCreativeForm.param("edited_tag", this.getEditedTag());
		}

		if (this.getEndDate() != null) {
			String endDate = SDF.format(this.getEndDate());
			atomicCreativeForm.param("end_date", endDate);
		}

		if (this.getExpansionDirection() != null) {
			atomicCreativeForm.param("expansion_direction", this.getExpansionDirection().toString());
		} else {
			atomicCreativeForm.param("expansion_direction", "NONRESTRICTED");
		}

		if (this.getExpansionTrigger() != null) {
			atomicCreativeForm.param("expansion_trigger", this.getExpansionTrigger().toString());
		} else {
			atomicCreativeForm.param("expansion_trigger", "CLICK");
		}

		if (this.getExternalIdentifier() != null && !this.getExternalIdentifier().isEmpty()) {
			atomicCreativeForm.param("external_identifier", this.getExternalIdentifier());
		}

		if (this.getFileType() != null) {
			atomicCreativeForm.param("file_type", this.getFileType().toString());
		} else {
			atomicCreativeForm.param("file_type", "unknown");
		}

		atomicCreativeForm.param("has_sound", Utility.getOnOrOff(this.isHasSound()));

		if (this.getHeight() > 0) {
			atomicCreativeForm.param("height", String.valueOf(this.getHeight()));
		}

		atomicCreativeForm.param("is_https", Utility.getOnOrOff(this.isIsHttps()));

		atomicCreativeForm.param("is_multi_creative", Utility.getOnOrOff(this.isIsMultiCreative()));

		if (this.getMediaType() != null) {
			atomicCreativeForm.param("media_type", this.getMediaType().toString());
		} else {
			atomicCreativeForm.param("media_type", "display");
		}

		if (this.getName() != null && !this.getName().isEmpty()) {
			atomicCreativeForm.param("name", this.getName());
		}

		if (this.getRejectedReason() != null && !this.getRejectedReason().isEmpty()) {
			atomicCreativeForm.param("rejected_reason", this.getRejectedReason());
		}

		atomicCreativeForm.param("rich_media", Utility.getOnOrOff(this.isRichMedia()));

		if (this.getRichMediaProvider() != null && !this.getRichMediaProvider().isEmpty()) {
			atomicCreativeForm.param("rich_media_provider", this.getRichMediaProvider());
		}

		if (this.getStartDate() != null) {
			String startDate = SDF.format(this.getStartDate());
			atomicCreativeForm.param("start_date", startDate);
		}

		atomicCreativeForm.param("status", Utility.getOnOrOff(this.isStatus()));

		atomicCreativeForm.param("t1as", Utility.getOnOrOff(this.isT1as()));

		if (this.getTag() != null && !this.getTag().isEmpty()) {
			atomicCreativeForm.param("tag", this.getTag());
		}

		if (this.getTagType() != null) {
			atomicCreativeForm.param("tag_type", this.getTagType().toString());
		} else {
			atomicCreativeForm.param("tag_type", "NOSCRIPT");
		}

		if (this.getTpasAdTag() != null && !this.getTpasAdTag().isEmpty()) {
			atomicCreativeForm.param("tpas_ad_tag", this.getTpasAdTag());
		}

		if (this.getTpasAdTagName() != null && !this.getTpasAdTagName().isEmpty()) {
			atomicCreativeForm.param("tpas_ad_tag_name", this.getTpasAdTagName());
		}

		if (this.getType() != null && !this.getType().isEmpty()) {
			atomicCreativeForm.param("type", this.getType());
		}

		if (this.getVersion() >= 0) {
			atomicCreativeForm.param("version", String.valueOf(this.getVersion()));
		}

		if (this.getWidth() > 0) {
			atomicCreativeForm.param("width", String.valueOf(this.getWidth()));
		}

		atomicCreativeForm.param("mraid", Utility.getOnOrOff(this.isMraid()));

		if (this.getExpand() != null) {
			atomicCreativeForm.param("expand", this.getExpand().toString());
		}

		return Utility.getFilteredForm(atomicCreativeForm, "atomiccreative");

	}

	@Override
	public String getUri() {
		return null;
	}

}
