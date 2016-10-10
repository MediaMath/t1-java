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

import javax.ws.rs.core.Form;

import com.mediamath.terminalone.models.ChildPixel;
import com.mediamath.terminalone.utils.Utility;

public class ChildPixelHelper {

  /**
   * Creates a ChildPixel Form Object.
   * 
   * @param entity
   *          expects a Pixel Entity.
   * @return Form object
   */
  public static Form getForm(ChildPixel entity) {

    Form pixelForm = new Form();

    if (entity.getBundleId() > 0) {
      pixelForm.param("bundle_id", String.valueOf(entity.getBundleId()));
    }

    pixelForm.param("distributed", Utility.getOnOrOff(entity.isDistributed()));

    if (entity.getPixelType() != null) {
      pixelForm.param("pixel_type", entity.getPixelType().toString());
    }

    if (entity.getTag() != null) {
      pixelForm.param("tag", entity.getTag());
    }

    if (entity.getSupplySourceId() > 0) {
      pixelForm.param("supply_source_id", String.valueOf(entity.getSupplySourceId()));
    }

    if (entity.getVersion() >= 0) {
      pixelForm.param("version", String.valueOf(entity.getVersion()));
    }
<<<<<<< HEAD
    
    
    Form finalChildPixelForm = Utility.getFilteredForm(pixelForm, "childpixel");
    
    return finalChildPixelForm;
=======

    Form finalAdvertiserForm = Utility.getFilteredForm(pixelForm, "childpixel");

    return finalAdvertiserForm;
>>>>>>> 524c2a6... formatting the code with checkstyle.
  }
}
