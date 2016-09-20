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

public class TargetValues implements T1Entity {
	
	private static final String entityName = "TargetValues";

	public enum oper {
		AND, OR
	};
	
	public enum restrictions {
		INCLUDE, EXCLUDE
	};
	
	public enum codes{BSER,CSPD,DVCE,FOLD,ISPX,INVT,DMAX,REGN,CHNL,SFTY,VAUD,VCON,VLIN,VPSZ};
	
	 private String _type;
	 private codes code;
	 private int id;
     private boolean is_targetable;
     private String name;
     private int target_dimension_id;
     private int value;
     
     private restrictions restriction;
     private oper operation;
     private List<Integer> value_ids = new ArrayList<Integer>();
     
     
	public String get_type() {
		return _type;
	}
	public void set_type(String _type) {
		this._type = _type;
	}
	public codes getCode() {
		return code;
	}
	public void setCode(codes code) {
		this.code = code;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isIs_targetable() {
		return is_targetable;
	}
	public void setIs_targetable(boolean is_targetable) {
		this.is_targetable = is_targetable;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTarget_dimension_id() {
		return target_dimension_id;
	}
	public void setTarget_dimension_id(int target_dimension_id) {
		this.target_dimension_id = target_dimension_id;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getEntityname() {
		return entityName;
	}
	public restrictions getRestriction() {
		return restriction;
	}
	public void setRestriction(restrictions restriction) {
		this.restriction = restriction;
	}
	public oper getOperation() {
		return operation;
	}
	public void setOperation(oper operation) {
		this.operation = operation;
	}
	public List<Integer> getValue_ids() {
		return value_ids;
	}
	public void setValue_ids(List<Integer> value_ids) {
		this.value_ids = value_ids;
	}
	
	
     
}
