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

package com.mediamath.terminalone.models.reporting;

public enum Reports {

  APPTRANSPARENCY("app_transparency"), 
  AUDIENCEINDEX("audience_index"), 
  AUDIENCEINDEXPIXEL("audience_index_pixel"), 
  DATAPIXELLOADS("data_pixel_loads"), DAYPART("day_part"), 
  DEVICETECHNOLOGY("device_technology"), 
  EVENTPIXELLOADS("event_pixel_loads"), 
  GEO("geo"), 
  PERFORMANCE("performance"), 
  POSTALCODE("postal_code"), 
  PULSE("pulse"), 
  REACHFREQUENCY("reach_frequency"), 
  SITETRANSPARENCY("site_transparency"), 
  VIDEO("video"), 
  VIDEOSITETRANSPARENCY("video_site_transparency"), 
  WATERMARK("watermark"), 
  WINLOSS("win_loss"), 
  WINLOSSCREATIVE("win_loss_creative");

  String reportName;

  Reports(String name) {
    reportName = name;
  }

  public String getReportName() {
    return this.reportName;
  }

  public String getReportNameWithMeta() {
    return this.reportName + "/meta";
  }

}
