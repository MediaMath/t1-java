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

public class Segments {

  public enum restrictions {
    INCLUDE, EXCLUDE
  }

  public enum audSegExc {
    AND, OR
  }

  public enum audSegInc {
    AND, OR
  }

  private int id;
  private restrictions restriction;
  private audSegExc exclude;
  private audSegInc include;

  /**
   * Constructor.
   * 
   * @param id id.
   * @param restriction restriction.
   * @param exclude exclude.
   * @param include include.
   */
  public Segments(int id, restrictions restriction, audSegExc exclude, audSegInc include) {
    this.id = id;
    this.restriction = restriction;
    this.exclude = exclude;
    this.include = include;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public restrictions getRestriction() {
    return restriction;
  }

  public void setRestriction(restrictions restriction) {
    this.restriction = restriction;
  }

  public audSegExc getExclude() {
    return exclude;
  }

  public void setExclude(audSegExc exclude) {
    this.exclude = exclude;
  }

  public audSegInc getInclude() {
    return include;
  }

  public void setInclude(audSegInc include) {
    this.include = include;
  }

}
