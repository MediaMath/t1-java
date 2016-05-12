package com.mediamath.terminalone.utils;

import java.util.ArrayList;
import java.util.List;

public class QueryParamValues {

	private String strValue = null;

	private boolean boolValue;
	
	private Number numberValue = null;
	
	private List<Object> listValue = new ArrayList<Object>();

	public QueryParamValues(String strValue){
		this.strValue = strValue;
	}
	
	public QueryParamValues(Number numVal){
		this.numberValue = numVal;
	}
	
	public QueryParamValues(boolean boolVal){
		this.boolValue = boolVal;
	}
	
	public QueryParamValues(List<Object> listVal){
		this.listValue  = listVal;
	}

	public String getStrValue() {
		return strValue;
	}

	public void setStrValue(String strValue) {
		this.strValue = strValue;
	}

	public boolean getBoolValue() {
		return boolValue;
	}

	public void setBoolValue(boolean boolValue) {
		this.boolValue = boolValue;
	}

	public Number getNumberValue() {
		return numberValue;
	}

	public void setNumberValue(Number numberValue) {
		this.numberValue = numberValue;
	}

	public List<Object> getListValue() {
		return listValue;
	}

	public void setListValue(List<Object> listValue) {
		this.listValue = listValue;
	}	
	
	
	
	
}
