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
import java.util.List;

import javax.ws.rs.core.Form;

public class TargetDimensions implements T1Entity {

	private static final String entityName = "TargetDimensions";

	public enum excludeOp {
		AND, OR
	}

	public enum includeOp {
		AND, OR
	}
	
	private int id;
	private int name;
	private List<Integer> exclude = new ArrayList<Integer>();
	private List<Integer> include = new ArrayList<Integer>();
	private includeOp include_op;
	private excludeOp exclude_op;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	public List<Integer> getExclude() {
		return exclude;
	}

	public void setExclude(List<Integer> exclude) {
		this.exclude = exclude;
	}

	public List<Integer> getInclude() {
		return include;
	}

	public void setInclude(List<Integer> include) {
		this.include = include;
	}

	public includeOp getIncludeOp() {
		return include_op;
	}

	public void setIncludeOp(includeOp include_op) {
		this.include_op = include_op;
	}

	public excludeOp getExcludeOp() {
		return exclude_op;
	}

	public void setExcludeOp(excludeOp exclude_op) {
		this.exclude_op = exclude_op;
	}

	@Override
	public String getEntityname() {
		return entityName;
	}

	@Override
	public Form getForm() {
		return null;
	}

	@Override
	public String getUri() {
		return null;
	}

}
