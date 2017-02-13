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

import javax.ws.rs.core.Form;

public class TOneASCreativeAssetsApproveResponse implements T1Entity {

  private static final String entityName = "TOneASCreativeAssetsApproveResponse";

  String updated_on;
  String created_on;
  String last_modified;
  boolean is_https;
  String ad_format;
  String rich_media;
  String expansion_trigger;
  String tag;
  String id;
  boolean built;
  String name;
  boolean t1as;
  String file_type;
  String built_by_user_id;
  String expansion_direction;
  String concept_id;
  String advertiser_id;
  String width;
  boolean has_sound;
  boolean status;
  String external_identifier;
  String click_through_url;
  String build_date;
  boolean is_multi_creative;
  String entity_type;
  String media_type;
  String edited_tag;
  String click_url;
  String approval_status;
  String version;
  String height;
  String tag_type;
  String ad_server_type;
  String default_t1as_tag;
  String tpas_ad_tag_name;

  @Override
  public String getEntityname() {
    return entityName;
  }

  public String getUpdatedOn() {
    return updated_on;
  }

  public void setUpdatedOn(String updated_on) {
    this.updated_on = updated_on;
  }

  public String getCreatedOn() {
    return created_on;
  }

  public void setCreatedOn(String created_on) {
    this.created_on = created_on;
  }

  public String getLastModified() {
    return last_modified;
  }

  public void setLastModified(String last_modified) {
    this.last_modified = last_modified;
  }

  public boolean isHttps() {
    return is_https;
  }

  public void setIsHttps(boolean is_https) {
    this.is_https = is_https;
  }

  public String getAdFormat() {
    return ad_format;
  }

  public void setAdFormat(String ad_format) {
    this.ad_format = ad_format;
  }

  public String getRichMedia() {
    return rich_media;
  }

  public void setRichMedia(String rich_media) {
    this.rich_media = rich_media;
  }

  public String getExpansionTrigger() {
    return expansion_trigger;
  }

  public void setExpansionTrigger(String expansion_trigger) {
    this.expansion_trigger = expansion_trigger;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public boolean isBuilt() {
    return built;
  }

  public void setBuilt(boolean built) {
    this.built = built;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isT1as() {
    return t1as;
  }

  public void setT1as(boolean t1as) {
    this.t1as = t1as;
  }

  public String getFileType() {
    return file_type;
  }

  public void setFileType(String file_type) {
    this.file_type = file_type;
  }

  public String getBuiltByUserId() {
    return built_by_user_id;
  }

  public void setBuiltByUserId(String built_by_user_id) {
    this.built_by_user_id = built_by_user_id;
  }

  public String getExpansionDirection() {
    return expansion_direction;
  }

  public void setExpansionDirection(String expansion_direction) {
    this.expansion_direction = expansion_direction;
  }

  public String getConceptId() {
    return concept_id;
  }

  public void setConceptId(String concept_id) {
    this.concept_id = concept_id;
  }

  public String getAdvertiserId() {
    return advertiser_id;
  }

  public void setAdvertiserId(String advertiser_id) {
    this.advertiser_id = advertiser_id;
  }

  public String getWidth() {
    return width;
  }

  public void setWidth(String width) {
    this.width = width;
  }

  public boolean isHasSound() {
    return has_sound;
  }

  public void setHasSound(boolean has_sound) {
    this.has_sound = has_sound;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public String getExternalIdentifier() {
    return external_identifier;
  }

  public void setExternalIdentifier(String external_identifier) {
    this.external_identifier = external_identifier;
  }

  public String getClickThroughUrl() {
    return click_through_url;
  }

  public void setClickThroughUrl(String click_through_url) {
    this.click_through_url = click_through_url;
  }

  public String getBuildDate() {
    return build_date;
  }

  public void setBuildDate(String build_date) {
    this.build_date = build_date;
  }

  public boolean isIsMultiCreative() {
    return is_multi_creative;
  }

  public void setIsMultiCreative(boolean is_multi_creative) {
    this.is_multi_creative = is_multi_creative;
  }

  public String getEntityType() {
    return entity_type;
  }

  public void setEntityType(String entity_type) {
    this.entity_type = entity_type;
  }

  public String getMediaType() {
    return media_type;
  }

  public void setMediaType(String media_type) {
    this.media_type = media_type;
  }

  public String getEditedTag() {
    return edited_tag;
  }

  public void setEditedTag(String edited_tag) {
    this.edited_tag = edited_tag;
  }

  public String getClickUrl() {
    return click_url;
  }

  public void setClickUrl(String click_url) {
    this.click_url = click_url;
  }

  public String getApprovalStatus() {
    return approval_status;
  }

  public void setApprovalStatus(String approval_status) {
    this.approval_status = approval_status;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getHeight() {
    return height;
  }

  public void setHeight(String height) {
    this.height = height;
  }

  public String getTagType() {
    return tag_type;
  }

  public void setTagType(String tag_type) {
    this.tag_type = tag_type;
  }

  public String getAdServerType() {
    return ad_server_type;
  }

  public void setAdServerType(String ad_server_type) {
    this.ad_server_type = ad_server_type;
  }

  public String getDefaultT1asTag() {
    return default_t1as_tag;
  }

  public void setDefaultT1asTag(String default_t1as_tag) {
    this.default_t1as_tag = default_t1as_tag;
  }

  public String getTpasAdTagName() {
    return tpas_ad_tag_name;
  }

  public void setTpasAdTagName(String tpas_ad_tag_name) {
    this.tpas_ad_tag_name = tpas_ad_tag_name;
  }
  
  @Override
  public Form getForm() {
    return null;
  }

  @Override
  public String getUri() {
    return null;
  }

}
