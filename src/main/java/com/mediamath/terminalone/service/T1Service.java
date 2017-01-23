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

package com.mediamath.terminalone.service;

import java.util.Properties;

import javax.ws.rs.core.Form;

import com.mediamath.terminalone.utils.Utility;

/**
 * Service to support terminalone class.
 * 
 * @author chaudhari_j
 *
 */
public class T1Service {

  private static Properties configprop = Utility.loadConfigProperty();

  private static Properties entityReadOnlyFields = Utility.loadEntityReadOnlyFields();

  private String URL = null;

  private String apiBase = null;

  private String pathMgmt = null;

  private String videoCreativeURL = null;

  private String reportingURL = null;

  private String oauthURL = null;

  /**
   * constructor.
   */
  public T1Service() {
    this.apiBase = configprop.getProperty("t1.api_base");
    this.pathMgmt = configprop.getProperty("t1.path_mgmt");
    this.videoCreativeURL = configprop.getProperty("t1.videoCreativeURL");
    this.reportingURL = configprop.getProperty("t1.reportingURL");
    this.oauthURL = configprop.getProperty("t1.oauthURL");

  }

  /**
   * constructs a url for a given path.
   * 
   * @param path
   *          requires a path uri.
   * 
   * @return String object.
   */
  public String constructUrl(StringBuilder path) {
    return apiBase + pathMgmt + "/" + path.toString();
  }

  public String constructReportingUrl(StringBuilder path) {
    return apiBase + reportingURL + "/" + path.toString();
  }

  public String constructOauthUrl(StringBuffer path) {
    return apiBase + oauthURL + "/" + path.toString();
  }

  /**
   * gets form object of login related information.
   * 
   * @param username
   *          requires a valid username.
   * @param password
   *          requires a valid password.
   * @param apiKey
   *          requires a valid environment api key.
   * @return Form object.
   */
  public Form getLoginFormData(String username, String password, String apiKey) {

    Form form = new Form();
    form.param("user", username);
    form.param("password", password);
    form.param("api_key", apiKey);

    return form;
  }

  public String getVideoCreativeURL() {
    return videoCreativeURL;
  }

  public void setVideoCreativeURL(String videoCreativeURL) {
    this.videoCreativeURL = videoCreativeURL;
  }

  public String getURL() {
    return URL;
  }

  public void setURL(String uRL) {
    URL = uRL;
  }

  public String getApi_base() {
    return apiBase;
  }

  public void setApiBase(String apiBase) {
    this.apiBase = apiBase;
  }

  public String getPathMgmt() {
    return pathMgmt;
  }

  public void setPathMgmt(String pathMgmt) {
    this.pathMgmt = pathMgmt;
  }

  public String getReportingURL() {
    return reportingURL;
  }

  public void setReportingURL(String reportingURL) {
    this.reportingURL = reportingURL;
  }

  public static Properties getEntityReadOnlyFields() {
    return entityReadOnlyFields;
  }

}
