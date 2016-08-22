package com.mediamath.terminalone.models.reporting.meta;

import java.util.ArrayList;

import com.mediamath.terminalone.models.reporting.meta.MetaStructure;

public class MetaData {

	String Description;
	String Name;
	String Type;
	String URI_Data;
	String URI_Meta;
	String Version;
	String currency;
	String data_retention;
	MetaStructure structure;
	String time_aggregations;
	ArrayList<String> time_rollups;
	ArrayList<String> time_windows;
	String timezone;

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getURI_Data() {
		return URI_Data;
	}

	public void setURI_Data(String uRI_Data) {
		URI_Data = uRI_Data;
	}

	public String getURI_Meta() {
		return URI_Meta;
	}

	public void setURI_Meta(String uRI_Meta) {
		URI_Meta = uRI_Meta;
	}

	public String getVersion() {
		return Version;
	}

	public void setVersion(String version) {
		Version = version;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getData_retention() {
		return data_retention;
	}

	public void setData_retention(String data_retention) {
		this.data_retention = data_retention;
	}

	public MetaStructure getStructure() {
		return structure;
	}

	public void setStructure(MetaStructure structure) {
		this.structure = structure;
	}

	public String getTime_aggregations() {
		return time_aggregations;
	}

	public void setTime_aggregations(String time_aggregations) {
		this.time_aggregations = time_aggregations;
	}

	public ArrayList<String> getTime_rollups() {
		return time_rollups;
	}

	public void setTime_rollups(ArrayList<String> time_rollups) {
		this.time_rollups = time_rollups;
	}

	public ArrayList<String> getTime_windows() {
		return time_windows;
	}

	public void setTime_windows(ArrayList<String> time_windows) {
		this.time_windows = time_windows;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

}
