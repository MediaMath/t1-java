package com.mediamath.terminalone.models;

public class TargetValue implements T1Entity {
	
	private static final String entityName = "TargetValue";

	 private String _type;
	 private String code;
	 private int id;
     private boolean is_targetable;
     private String name;
     private int target_dimension_id;
     private int value;
	public String get_type() {
		return _type;
	}
	public void set_type(String _type) {
		this._type = _type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
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
	
     
}
