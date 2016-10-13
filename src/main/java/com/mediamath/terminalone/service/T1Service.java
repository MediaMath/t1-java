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

import com.mediamath.terminalone.utils.Utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

import javax.ws.rs.core.Form;

/**
 * Service to support terminalone class.
 * 
 * @author chaudhari_j
 *
 */
public class T1Service {

  private static final Logger logger = LoggerFactory.getLogger(T1Service.class);

  private static Properties configprop = Utility.loadConfigProperty();
  
  private String URL = null;
  
  private String api_base = null;
  
  private String path_mgmt = null;
  
  private String videoCreativeURL = null;
  
  private String reportingURL = null;
  
  private String oauthURL = null;

  /**
   * constructor.
   */
  public T1Service() {
    this.api_base = configprop.getProperty("api_base");
    this.path_mgmt = configprop.getProperty("path_mgmt");
    this.videoCreativeURL = configprop.getProperty("videoCreativeURL");
    this.reportingURL = configprop.getProperty("reportingURL");
    this.oauthURL = configprop.getProperty("oauthURL");
  }

  /**
   * constructs a url for a given path.
   * 
   * @param path requires a path uri.
   * 
   * @return String object.
   */
  public String constructUrl(StringBuffer path) {
    String url = api_base + path_mgmt + "/" + path.toString();
    return url;
  }

  public String constructReportingUrl(StringBuffer path) {
    String url = api_base + reportingURL + "/" + path.toString();
    return url;
  }

  public String constructOauthUrl(StringBuffer path) {
    String url = api_base + oauthURL + "/" + path.toString();
    return url;
  }

  
  /**
   * gets form object of login related information.
   * 
   * @param username requires a valid username.
   * @param password requires a valid password.
   * @param api_key requires a valid environment api key.
   * @return Form object.
   */
  public Form getLoginFormData(String username, String password, String api_key) {

    Form form = new Form();
    form.param("user", username);
    form.param("password", password);
    form.param("api_key", api_key);

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
    return api_base;
  }

  public void setApi_base(String api_base) {
    this.api_base = api_base;
  }

  public String getPath_mgmt() {
    return path_mgmt;
  }

  public void setPath_mgmt(String path_mgmt) {
    this.path_mgmt = path_mgmt;
  }

  public String getReportingURL() {
    return reportingURL;
  }

  public void setReportingURL(String reportingURL) {
    this.reportingURL = reportingURL;
  }

}
