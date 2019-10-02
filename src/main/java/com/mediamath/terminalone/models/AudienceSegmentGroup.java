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

import org.javers.core.metamodel.annotation.ShallowReference;

import java.util.HashSet;
import java.util.Set;

public class AudienceSegmentGroup {

  public enum Operator {
    AND, OR
  }

  //TODO: should this really be shallow compared?  all segments in the group have the same restrictions and AND/OR
  // so this is only a problem if all segments are flipped between these, but we can probably still diff all of it if it's stable?
  @ShallowReference
  private Set<Segments> audienceSegments = new HashSet<>();
  private Operator operator;

  /**
   * Constructor.
   *
   * @param audienceSegments set of segments.
   */
  public AudienceSegmentGroup(Set<Segments> audienceSegments, Operator operator) {
    this.audienceSegments = audienceSegments;
    this.operator = operator;
  }

  public AudienceSegmentGroup() {
  }

  @ShallowReference
  public Set<Segments> getAudienceSegments() {
    return audienceSegments;
  }

  public void setAudienceSegments(Set<Segments> audienceSegments) {
    this.audienceSegments = audienceSegments;
  }

  public Operator getOperator() {
    return operator;
  }

  public void setOperator(Operator operator) {
    this.operator = operator;
  }

}
