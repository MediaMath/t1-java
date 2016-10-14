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

import java.util.ArrayList;

public class ThreePASCreativeBatchPlacement {

	String width;
	String media_ext;
	ArrayList<String> converted_tag;
	String batch_index;
	String name;
	String third_party_uuid;
	String height;
	String valid;
	ArrayList<String> raw_tag;
	ThreePASCreativeCreativeAssets assets;
	String ad_server_type;
	String tag_type;
	String tpas_ad_tag_name;
	String asset_bytes;

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getMediaExt() {
		return media_ext;
	}

	public void setMediaExt(String media_ext) {
		this.media_ext = media_ext;
	}

	public ArrayList<String> getConvertedTag() {
		return converted_tag;
	}

	public void setConvertedTag(ArrayList<String> converted_tag) {
		this.converted_tag = converted_tag;
	}

	public String getBatchIndex() {
		return batch_index;
	}

	public void setBatchIndex(String batch_index) {
		this.batch_index = batch_index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getThirdPartyUuid() {
		return third_party_uuid;
	}

	public void setThirdPartyUuid(String third_party_uuid) {
		this.third_party_uuid = third_party_uuid;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public ArrayList<String> getRawTag() {
		return raw_tag;
	}

	public void setRawTag(ArrayList<String> raw_tag) {
		this.raw_tag = raw_tag;
	}

	public ThreePASCreativeCreativeAssets getAssets() {
		return assets;
	}

	public void setAssets(ThreePASCreativeCreativeAssets assets) {
		this.assets = assets;
	}

	public String getAdServerType() {
		return ad_server_type;
	}

	public void setAdServerType(String ad_server_type) {
		this.ad_server_type = ad_server_type;
	}

	public String getTagType() {
		return tag_type;
	}

	public void setTagType(String tag_type) {
		this.tag_type = tag_type;
	}

	public String getTpasAdTagName() {
		return tpas_ad_tag_name;
	}

	public void setTpasAdTagName(String tpas_ad_tag_name) {
		this.tpas_ad_tag_name = tpas_ad_tag_name;
	}

	public String getAssetBytes() {
		return asset_bytes;
	}

	public void setAssetBytes(String asset_bytes) {
		this.asset_bytes = asset_bytes;
	}

}
