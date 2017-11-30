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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data {

	private User user;
	private Permissions permissions;
	private Map<String, String> data = new HashMap<>();
	private List<TargetValue> include = new ArrayList<>();
	private List<TargetValue> exclude = new ArrayList<>();
	private String exclude_op;
	private String include_op;
	
	public class enabled {
		String active;

		public String getActive() {
			return active;
		}

		public void setActive(String active) {
			this.active = active;
		}
	}

	public enabled enabled;

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

	public enabled getEnabled() {
		return enabled;
	}

	public void setEnabled(enabled enabled) {
		this.enabled = enabled;
	}

	public List<TargetValue> getInclude() {
		return include;
	}

	public void setInclude(List<TargetValue> include) {
		this.include = include;
	}

	public List<TargetValue> getExclude() {
		return exclude;
	}

	public void setExclude(List<TargetValue> exclude) {
		this.exclude = exclude;
	}

	public String getExclude_op() {
		return exclude_op;
	}

	public void setExclude_op(String exclude_op) {
		this.exclude_op = exclude_op;
	}

	public String getInclude_op() {
		return include_op;
	}

	public void setInclude_op(String include_op) {
		this.include_op = include_op;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Permissions getPermissions() {
		return permissions;
	}

	public void setPermissions(Permissions permissions) {
		this.permissions = permissions;
	}

}
