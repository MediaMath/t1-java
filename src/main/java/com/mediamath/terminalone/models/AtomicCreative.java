package com.mediamath.terminalone.models;

import java.util.Date;

public class AtomicCreative implements T1Entity {
	
	private static final String entityName = "AtomicCreative";

	public enum adFormats {
		DISPLAY, EXPANDABLE, MOBILE
	};

	public enum adServers {
		ATLAS, DART, EYEWONDER, MEDIAMIND, MEDIAPLEX, POINTROLL, YIELD_MANAGER, TERMINALONE, MEDIAFORGE, OTHER
	};

	public enum expandDir {
		NONRESTRICTED
	};

	public enum expandTrig {
		AUTOMATIC, MOUSEOVER, CLICK
	};

	public enum fileTypes {
		swf, gif, html5, jpg, jpeg, tif, tiff, png, unknown, vast
	};

	public enum mediaTypes {
		display, video, mobile
	};

	public enum tagTypes {
		IFRAME_SCRIPT_NOSCRIPT, IFRAME_SCRIPT, IFRAME_NOSCRIPT, IFRAME_IMG, SCRIPT_NOSCRIPT, SCRIPT, NOSCRIPT, IFRAME,IMG
	};

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

	public int getAdvertiser_id() {
		return advertiser_id;
	}

	public void setAdvertiser_id(int advertiser_id) {
		this.advertiser_id = advertiser_id;
	}

	public adFormats getAd_format() {
		return ad_format;
	}

	public void setAd_format(adFormats ad_format) {
		this.ad_format = ad_format;
	}

	public adServers getAd_server_type() {
		return ad_server_type;
	}

	public void setAd_server_type(adServers ad_server_type) {
		this.ad_server_type = ad_server_type;
	}

	public String getApproval_status() {
		return approval_status;
	}

	public void setApproval_status(String approval_status) {
		this.approval_status = approval_status;
	}

	public Date getBuild_date() {
		return build_date;
	}

	public void setBuild_date(Date build_date) {
		this.build_date = build_date;
	}

	public String getBuild_errors() {
		return build_errors;
	}

	public void setBuild_errors(String build_errors) {
		this.build_errors = build_errors;
	}

	public boolean isBuilt() {
		return built;
	}

	public void setBuilt(boolean built) {
		this.built = built;
	}

	public int getBuilt_by_user_id() {
		return built_by_user_id;
	}

	public void setBuilt_by_user_id(int built_by_user_id) {
		this.built_by_user_id = built_by_user_id;
	}

	public String getClick_through_url() {
		return click_through_url;
	}

	public void setClick_through_url(String click_through_url) {
		this.click_through_url = click_through_url;
	}

	public String getClick_url() {
		return click_url;
	}

	public void setClick_url(String click_url) {
		this.click_url = click_url;
	}

	public int getConcept_id() {
		return concept_id;
	}

	public void setConcept_id(int concept_id) {
		this.concept_id = concept_id;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public int getCreative_import_file_id() {
		return creative_import_file_id;
	}

	public void setCreative_import_file_id(int creative_import_file_id) {
		this.creative_import_file_id = creative_import_file_id;
	}

	public String getEdited_tag() {
		return edited_tag;
	}

	public void setEdited_tag(String edited_tag) {
		this.edited_tag = edited_tag;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public expandDir getExpansion_direction() {
		return expansion_direction;
	}

	public void setExpansion_direction(expandDir expansion_direction) {
		this.expansion_direction = expansion_direction;
	}

	public expandTrig getExpansion_trigger() {
		return expansion_trigger;
	}

	public void setExpansion_trigger(expandTrig expansion_trigger) {
		this.expansion_trigger = expansion_trigger;
	}

	public String getExternal_identifier() {
		return external_identifier;
	}

	public void setExternal_identifier(String external_identifier) {
		this.external_identifier = external_identifier;
	}

	public fileTypes getFile_type() {
		return file_type;
	}

	public void setFile_type(fileTypes file_type) {
		this.file_type = file_type;
	}

	public boolean isHas_sound() {
		return has_sound;
	}

	public void setHas_sound(boolean has_sound) {
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

	public boolean isIs_https() {
		return is_https;
	}

	public void setIs_https(boolean is_https) {
		this.is_https = is_https;
	}

	public boolean isIs_multi_creative() {
		return is_multi_creative;
	}

	public void setIs_multi_creative(boolean is_multi_creative) {
		this.is_multi_creative = is_multi_creative;
	}

	public Date getLast_modified() {
		return last_modified;
	}

	public void setLast_modified(Date last_modified) {
		this.last_modified = last_modified;
	}

	public mediaTypes getMedia_type() {
		return media_type;
	}

	public void setMedia_type(mediaTypes media_type) {
		this.media_type = media_type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRejected_reason() {
		return rejected_reason;
	}

	public void setRejected_reason(String rejected_reason) {
		this.rejected_reason = rejected_reason;
	}

	public boolean isRich_media() {
		return rich_media;
	}

	public void setRich_media(boolean rich_media) {
		this.rich_media = rich_media;
	}

	public String getRich_media_provider() {
		return rich_media_provider;
	}

	public void setRich_media_provider(String rich_media_provider) {
		this.rich_media_provider = rich_media_provider;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
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

	public tagTypes getTag_type() {
		return tag_type;
	}

	public void setTag_type(tagTypes tag_type) {
		this.tag_type = tag_type;
	}

	public String getTpas_ad_tag() {
		return tpas_ad_tag;
	}

	public void setTpas_ad_tag(String tpas_ad_tag) {
		this.tpas_ad_tag = tpas_ad_tag;
	}

	public String getTpas_ad_tag_name() {
		return tpas_ad_tag_name;
	}

	public void setTpas_ad_tag_name(String tpas_ad_tag_name) {
		this.tpas_ad_tag_name = tpas_ad_tag_name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getUpdated_on() {
		return updated_on;
	}

	public void setUpdated_on(Date updated_on) {
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

	public String getEntityname() {
		return entityName;
	}

}
