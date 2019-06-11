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

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.javers.core.metamodel.annotation.DiffIgnore;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Form;

public class TargetValues implements T1Entity {

	private static final String entityName = "TargetValues";

	public enum oper {
		AND, OR
	}

	public enum restrictions {
		INCLUDE, EXCLUDE
	}

	public enum codes {
		BSER, CSPD, DVCE, FOLD, ISPX, INVT, DMAX, REGN, CHNL, SFTY, VAUD, VCON, VLIN, VPSZ, NEMO
	}

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

	public TargetValues() {
	}

	public TargetValues(codes code, restrictions restriction, oper operation, List<Integer> value_ids) {
		this.code = code;
		this.restriction = restriction;
		this.operation = operation;
		this.value_ids = value_ids;
	}

	public String getType() {
		return _type;
	}

	public void setType(String _type) {
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

	public boolean isTargetable() {
		return is_targetable;
	}

	public void setIsTargetable(boolean is_targetable) {
		this.is_targetable = is_targetable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTargetDimensionId() {
		return target_dimension_id;
	}

	public void setTargetDimensionId(int target_dimension_id) {
		this.target_dimension_id = target_dimension_id;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
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

	public List<Integer> getValueIds() {
		return value_ids;
	}

	public void setValueIds(List<Integer> value_ids) {
		this.value_ids = value_ids;
	}

	@Override
	@DiffIgnore
	@JsonIgnore
	public Form getForm() {
		return null;
	}

	@Override
	@DiffIgnore
	@JsonIgnore
	public String getUri() {
		return null;
	}

}
