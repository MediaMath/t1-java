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

package com.mediamath.terminalone.models.helper;



import java.text.SimpleDateFormat;

import javax.ws.rs.core.Form;

import com.mediamath.terminalone.models.AtomicCreative;
import com.mediamath.terminalone.utils.Utility;



public class AtomicCreativeHelper {

  private static final String YYYY_MM_DDTHH_MM_SS_Z = "yyyy-MM-dd'T'HH:mm:ss Z";

  private static final SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DDTHH_MM_SS_Z);

  /**
   * creates a AtomicCreative Form Object.
   * @param entity expects AtomicCreative Object.
   * @return Form Object. 
   */
  public static Form getForm(AtomicCreative entity) {
    
    Form atomicCreativeForm = new Form();

    if (entity.getAdvertiserId() > 0) {
      atomicCreativeForm.param("advertiser_id", String.valueOf(entity.getAdvertiserId()));
    }

    if (entity.getAdFormat() != null) {
      atomicCreativeForm.param("ad_format", entity.getAdFormat().toString());
    }

    if (entity.getAdServerType() != null) {
      atomicCreativeForm.param("ad_server_type", entity.getAdServerType().toString());
    }

    if (entity.getApprovalStatus() != null) {
      atomicCreativeForm.param("approval_status", entity.getApprovalStatus().toString());
    }

    if (entity.getBuildDate() != null) {
      String buildDate = sdf.format(entity.getBuildDate());
      atomicCreativeForm.param("build_date", buildDate);
    }

    if (entity.getBuildErrors() != null && !entity.getBuildErrors().isEmpty()) {
      atomicCreativeForm.param("build_errors", entity.getBuildErrors());
    }

    atomicCreativeForm.param("built", Utility.getOnOrOff(entity.isBuilt()));

    if (entity.getBuiltByUserId() > 0) {
      atomicCreativeForm.param("built_by_user_id", String.valueOf(entity.getBuiltByUserId()));
    }
    
    if (entity.getClickThroughUrl() != null && !entity.getClickThroughUrl().isEmpty()) {
      atomicCreativeForm.param("click_through_url", entity.getClickThroughUrl());
    }

    if (entity.getClickUrl() != null && !entity.getClickUrl().isEmpty()) {
      atomicCreativeForm.param("click_url", entity.getClickUrl());
    }

    if (entity.getConceptId() > 0) {
      atomicCreativeForm.param("concept_id", String.valueOf(entity.getConceptId()));
    }

    if (entity.getCreativeImportFileId() > 0 ) {
      atomicCreativeForm.param("creative_import_file_id", String.valueOf(entity.getCreativeImportFileId()));
    }

    if (entity.getEditedTag() != null && !entity.getEditedTag().isEmpty()) {
      atomicCreativeForm.param("edited_tag", entity.getEditedTag());
    }

    if (entity.getEndDate() != null) {
      String endDate = sdf.format(entity.getEndDate());
      atomicCreativeForm.param("end_date", endDate);
    }

    if (entity.getExpansionDirection() != null) {
      atomicCreativeForm.param("expansion_direction", entity.getExpansionDirection().toString());
    }

    if (entity.getExpansionTrigger() != null) {
      atomicCreativeForm.param("expansion_trigger", entity.getExpansionTrigger().toString());
    }

    if (entity.getExternalIdentifier() != null && !entity.getExternalIdentifier().isEmpty()) {
      atomicCreativeForm.param("external_identifier", entity.getExternalIdentifier());
    }

    if (entity.getFileType() != null) {
      atomicCreativeForm.param("file_type", entity.getFileType().toString());
    }

    atomicCreativeForm.param("has_sound", Utility.getOnOrOff(entity.isHasSound()));

    if (entity.getHeight() > 0) {
      atomicCreativeForm.param("height", String.valueOf(entity.getHeight()));
    }

    atomicCreativeForm.param("is_https", Utility.getOnOrOff(entity.isIsHttps()));

    atomicCreativeForm.param("is_multi_creative", Utility.getOnOrOff(entity.isIsMultiCreative()));

    if (entity.getMediaType() != null) {
      atomicCreativeForm.param("media_type", entity.getMediaType().toString());
    }

    if (entity.getName() != null && !entity.getName().isEmpty()) {
      atomicCreativeForm.param("name", entity.getName());
    }

    if (entity.getRejectedReason() != null && !entity.getRejectedReason().isEmpty()) {
      atomicCreativeForm.param("rejected_reason", entity.getRejectedReason());
    }

    atomicCreativeForm.param("rich_media", Utility.getOnOrOff(entity.isRichMedia()));

    if (entity.getRichMediaProvider() != null && !entity.getRichMediaProvider().isEmpty()) {
      atomicCreativeForm.param("rich_media_provider", entity.getRichMediaProvider());
    }

    if (entity.getStartDate() != null) {
      String startDate = sdf.format(entity.getStartDate());
      atomicCreativeForm.param("start_date", startDate);
    }

    atomicCreativeForm.param("status", Utility.getOnOrOff(entity.isStatus()));

    atomicCreativeForm.param("t1as", Utility.getOnOrOff(entity.isT1as()));

    if (entity.getTag() != null && !entity.getTag().isEmpty()) {
      atomicCreativeForm.param("tag", entity.getTag());
    }

    if (entity.getTagType() != null) {
      atomicCreativeForm.param("tag_type", entity.getTagType().toString());
    }

    if (entity.getTpasAdTag() != null && !entity.getTpasAdTag().isEmpty()) {
      atomicCreativeForm.param("tpas_ad_tag", entity.getTpasAdTag());
    }

    if (entity.getTpasAdTagName() != null && !entity.getTpasAdTagName().isEmpty()) {
      atomicCreativeForm.param("tpas_ad_tag_name", entity.getTpasAdTagName());
    }

    if (entity.getType() != null && !entity.getType().isEmpty()) {
      atomicCreativeForm.param("type", entity.getType());
    }

    if (entity.getVersion() >= 0) {
      atomicCreativeForm.param("version", String.valueOf(entity.getVersion()));
    }

    if (entity.getWidth() > 0) {
      atomicCreativeForm.param("width", String.valueOf(entity.getWidth()));
    }
    
    Form finalAtomicCreativeForm = Utility.getFilteredForm(atomicCreativeForm, "atomiccreative");

    return finalAtomicCreativeForm;
   
  }
}
