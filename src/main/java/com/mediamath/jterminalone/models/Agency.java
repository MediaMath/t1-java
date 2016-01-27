package com.mediamath.jterminalone.models;

import java.util.Date;
import java.util.List;

public class Agency {

	private boolean allow_x_adv_optimization;
	private boolean billing_contact_id;
	private Date created_on;
	private int id;
	private String logo;
	private String name;
	private int organization_id;
	private int sales_contact_id;
	private boolean status;
	private Date updated_on;
	private int version;
	List<Organization> organization;

	public Agency(boolean allow_x_adv_optimization, boolean billing_contact_id, Date created_on, int id, String logo,
			String name, int organization_id, int sales_contact_id, boolean status, Date updated_on, int version) {
		super();
		this.allow_x_adv_optimization = allow_x_adv_optimization;
		this.billing_contact_id = billing_contact_id;
		this.created_on = created_on;
		this.id = id;
		this.logo = logo;
		this.name = name;
		this.organization_id = organization_id;
		this.sales_contact_id = sales_contact_id;
		this.status = status;
		this.updated_on = updated_on;
		this.version = version;
	}

	public boolean isAllow_x_adv_optimization() {
		return allow_x_adv_optimization;
	}

	public void setAllow_x_adv_optimization(boolean allow_x_adv_optimization) {
		this.allow_x_adv_optimization = allow_x_adv_optimization;
	}

	public boolean isBilling_contact_id() {
		return billing_contact_id;
	}

	public void setBilling_contact_id(boolean billing_contact_id) {
		this.billing_contact_id = billing_contact_id;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrganization_id() {
		return organization_id;
	}

	public void setOrganization_id(int organization_id) {
		this.organization_id = organization_id;
	}

	public int getSales_contact_id() {
		return sales_contact_id;
	}

	public void setSales_contact_id(int sales_contact_id) {
		this.sales_contact_id = sales_contact_id;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getUpdated_on() {
		return updated_on;
	}

	public void setUpdated_on(Date updated_on) {
		this.updated_on = updated_on;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public List<Organization> getOrganization() {
		return organization;
	}

	public void setOrganization(List<Organization> organization) {
		this.organization = organization;
	}

}
