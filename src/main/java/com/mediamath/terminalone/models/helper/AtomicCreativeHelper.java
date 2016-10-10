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



import com.mediamath.terminalone.exceptions.T1Exception;
import com.mediamath.terminalone.exceptions.ValidationException;
import com.mediamath.terminalone.models.AtomicCreative;
import com.mediamath.terminalone.utils.Utility;

import java.text.SimpleDateFormat;
import javax.ws.rs.core.Form;



public class AtomicCreativeHelper {

  private static final String YYYY_MM_DDTHH_MM_SS_Z = "yyyy-MM-dd'T'HH:mm:ss Z";

  private static final SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DDTHH_MM_SS_Z);

  /**
   * validates required fields.
   * @param entity expects Atomic Creative Entity.
   * @throws T1Exception throws T1Exception.
   */
  public static void validateRequiredFields(AtomicCreative entity) throws T1Exception {
    if (entity.getName() == null || entity.getName().isEmpty()) {
      throw new ValidationException("please enter a name for the concept");
    }
  }

  /**
   * creates a AtomicCreative Form Object.
   * @param entity expects AtomicCreative Object.
   * @return Form Object. 
   */
  public static Form getForm(AtomicCreative entity) {
    
    Form atomicCreativeForm = new Form();

    atomicCreativeForm.param("name", entity.getName());

    if (entity.getAdvertiser_id() > 0) {
      atomicCreativeForm.param("advertiser_id", String.valueOf(entity.getAdvertiser_id()));
    }

    if (entity.getAd_format() != null) {
      atomicCreativeForm.param("ad_format", entity.getAd_format().toString());
    }

    if (entity.getAd_server_type() != null) {
      atomicCreativeForm.param("ad_server_type", entity.getAd_server_type().toString());
    }

    if (entity.getApproval_status() != null) {
      atomicCreativeForm.param("approval_status", entity.getApproval_status().toString());
    }

    if (entity.getBuild_date() != null) {
      String buildDate = sdf.format(entity.getBuild_date());
      atomicCreativeForm.param("build_date", buildDate);
    }

    if (entity.getBuild_errors() != null && !entity.getBuild_errors().isEmpty()) {
      atomicCreativeForm.param("build_errors", entity.getBuild_errors());
    }

    atomicCreativeForm.param("built", Utility.getOnOrOff(entity.isBuilt()));

    if (entity.getBuilt_by_user_id() > 0) {
      atomicCreativeForm.param("built_by_user_id", String.valueOf(entity.getBuilt_by_user_id()));
    }
    
    if (entity.getClick_through_url() != null && !entity.getClick_through_url().isEmpty()) {
      atomicCreativeForm.param("click_through_url", entity.getClick_through_url());
    }

    if (entity.getClick_url() != null && !entity.getClick_url().isEmpty()) {
      atomicCreativeForm.param("click_url", entity.getClick_url());
    }

    if (entity.getConcept_id() > 0) {
      atomicCreativeForm.param("concept_id", String.valueOf(entity.getConcept_id()));
    }

    if (entity.getCreative_import_file_id() > 0 ) {
      atomicCreativeForm.param("creative_import_file_id", String.valueOf(entity.getCreative_import_file_id()));
    }

    if (entity.getEdited_tag() != null && !entity.getEdited_tag().isEmpty()) {
      atomicCreativeForm.param("edited_tag", entity.getEdited_tag());
    }

    if (entity.getEnd_date() != null) {
      String endDate = sdf.format(entity.getEnd_date());
      atomicCreativeForm.param("end_date", endDate);
    }

    if (entity.getExpansion_direction() != null) {
      atomicCreativeForm.param("expansion_direction", entity.getExpansion_direction().toString());
    }

    if (entity.getExpansion_trigger() != null) {
      atomicCreativeForm.param("expansion_trigger", entity.getExpansion_trigger().toString());
    }

    if (entity.getExternal_identifier() != null && !entity.getExternal_identifier().isEmpty()) {
      atomicCreativeForm.param("external_identifier", entity.getExternal_identifier());
    }

    if (entity.getFile_type() != null) {
      atomicCreativeForm.param("file_type", entity.getFile_type().toString());
    }

    atomicCreativeForm.param("has_sound", Utility.getOnOrOff(entity.isHas_sound()));

    if (entity.getHeight() > 0) {
      atomicCreativeForm.param("height", String.valueOf(entity.getHeight()));
    }

    if (entity.getId() > 0) {
      atomicCreativeForm.param("id", String.valueOf(entity.getId()));
    }

    atomicCreativeForm.param("is_https", Utility.getOnOrOff(entity.isIs_https()));

    atomicCreativeForm.param("is_multi_creative", Utility.getOnOrOff(entity.isIs_multi_creative()));

    if (entity.getMedia_type() != null) {
      atomicCreativeForm.param("media_type", entity.getMedia_type().toString());
    }

    if (entity.getName() != null && !entity.getName().isEmpty()) {
      atomicCreativeForm.param("name", entity.getName());
    }

    if (entity.getRejected_reason() != null && !entity.getRejected_reason().isEmpty()) {
      atomicCreativeForm.param("rejected_reason", entity.getRejected_reason());
    }

    atomicCreativeForm.param("rich_media", Utility.getOnOrOff(entity.isRich_media()));

    if (entity.getRich_media_provider() != null && !entity.getRich_media_provider().isEmpty()) {
      atomicCreativeForm.param("rich_media_provider", entity.getRich_media_provider());
    }

    if (entity.getStart_date() != null) {
      String startDate = sdf.format(entity.getStart_date());
      atomicCreativeForm.param("start_date", startDate);
    }

    atomicCreativeForm.param("status", Utility.getOnOrOff(entity.isStatus()));

    atomicCreativeForm.param("t1as", Utility.getOnOrOff(entity.isT1as()));

    if (entity.getTag() != null && !entity.getTag().isEmpty()) {
      atomicCreativeForm.param("tag", entity.getTag());
    }

    if (entity.getTag_type() != null) {
      atomicCreativeForm.param("tag_type", entity.getTag_type().toString());
    }

    if (entity.getTpas_ad_tag() != null && !entity.getTpas_ad_tag().isEmpty()) {
      atomicCreativeForm.param("tpas_ad_tag", entity.getTpas_ad_tag());
    }

    if (entity.getTpas_ad_tag_name() != null && !entity.getTpas_ad_tag_name().isEmpty()) {
      atomicCreativeForm.param("tpas_ad_tag_name", entity.getTpas_ad_tag_name());
    }

    if (entity.getType() != null && !entity.getType().isEmpty()) {
      atomicCreativeForm.param("type", entity.getType());
    }

    if (entity.getVersion() > 0) {
      atomicCreativeForm.param("version", String.valueOf(entity.getVersion()));
    }

    if (entity.getWidth() > 0) {
      atomicCreativeForm.param("width", String.valueOf(entity.getWidth()));
    }

    return atomicCreativeForm;
  }
}
