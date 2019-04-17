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

public class OAuthResponse {

	public static final Long DEFAULT_EXPIRES_IN_SECONDS = 3600L;

	private String access_token;

	private String id_token;

	private Long expires_in;

	private String token_type;

	public String getAccessToken() {
		return access_token;
	}

	public void setAccessToken(String access_token) {
		this.access_token = access_token;
	}

	public Long getExpiresIn() {
		return expires_in;
	}

	public void setExpiresIn(Long expires_in) {
		this.expires_in = expires_in;
	}

	public String getTokenType() {
		return token_type;
	}

	public void setTokenType(String token_type) {
		this.token_type = token_type;
	}

	public String getIdToken() {
		return id_token;
	}

	public void setIdToken(String id_token) {
		this.id_token = id_token;
	}
}