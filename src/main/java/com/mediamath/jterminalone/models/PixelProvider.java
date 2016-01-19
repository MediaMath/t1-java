package com.mediamath.jterminalone.models;

import java.util.Date;

public class PixelProvider {
	public enum executors {
		MEDIAMATH, UDI
	};

	private int agency_id;
	private Date created_on;
	private executors execution_by;
	private int id;
	private String name;
	private boolean status;
	private String taxonomy_file;
	private Date updated_on;
	private int vendor_id;
	private int version;

	public int getAgency_id() {
		return agency_id;
	}

	public void setAgency_id(int agency_id) {
		this.agency_id = agency_id;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public executors getExecution_by() {
		return execution_by;
	}

	public void setExecution_by(executors execution_by) {
		this.execution_by = execution_by;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getTaxonomy_file() {
		return taxonomy_file;
	}

	public void setTaxonomy_file(String taxonomy_file) {
		this.taxonomy_file = taxonomy_file;
	}

	public Date getUpdated_on() {
		return updated_on;
	}

	public void setUpdated_on(Date updated_on) {
		this.updated_on = updated_on;
	}

	public int getVendor_id() {
		return vendor_id;
	}

	public void setVendor_id(int vendor_id) {
		this.vendor_id = vendor_id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
