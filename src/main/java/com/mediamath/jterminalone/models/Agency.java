package com.mediamath.jterminalone.models;

public class Agency implements T1Entity {

	private static final String entityName = "Agency";

	private boolean allow_x_adv_optimization =false;
	private boolean allow_x_adv_pixels = false;
	private int billing_contact_id;
	private String dmp_enabled;
	private String created_on;
	private int id;
	private String logo;
	private String name;
	private int organization_id;
	private int sales_contact_id;
	private boolean status;
	private String updated_on;
	private int version;
	private int traffic_contact_id;
	Organization organization;
	
	public Agency(){}

	public Agency(boolean allow_x_adv_optimization, boolean allow_x_adv_pixels, int billing_contact_id,
			String dmp_enabled, String created_on, int id, String logo, String name, int organization_id,
			int sales_contact_id, boolean status, String updated_on, int version, int traffic_contact_id) {
		super();
		this.allow_x_adv_optimization = allow_x_adv_optimization;
		this.allow_x_adv_pixels = allow_x_adv_pixels;
		this.billing_contact_id = billing_contact_id;
		this.dmp_enabled = dmp_enabled;
		this.created_on = created_on;
		this.id = id;
		this.logo = logo;
		this.name = name;
		this.organization_id = organization_id;
		this.sales_contact_id = sales_contact_id;
		this.status = status;
		this.updated_on = updated_on;
		this.version = version;
		this.traffic_contact_id = traffic_contact_id;
	}

	public boolean isAllow_x_adv_optimization() {
		return allow_x_adv_optimization;
	}

	public void setAllow_x_adv_optimization(boolean allow_x_adv_optimization) {
		this.allow_x_adv_optimization = allow_x_adv_optimization;
	}

	public int getBilling_contact_id() {
		return billing_contact_id;
	}

	public void setBilling_contact_id(int billing_contact_id) {
		this.billing_contact_id = billing_contact_id;
	}

	public String getCreated_on() {
		return created_on;
	}

	public void setCreated_on(String created_on) {
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

	public String getUpdated_on() {
		return updated_on;
	}

	public void setUpdated_on(String updated_on) {
		this.updated_on = updated_on;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getEntityname() {
		return entityName;
	}

	public boolean isAllow_x_adv_pixels() {
		return allow_x_adv_pixels;
	}

	public void setAllow_x_adv_pixels(boolean allow_x_adv_pixels) {
		this.allow_x_adv_pixels = allow_x_adv_pixels;
	}

	public String getDmp_enabled() {
		return dmp_enabled;
	}

	public void setDmp_enabled(String dmp_enabled) {
		this.dmp_enabled = dmp_enabled;
	}

	public int getTraffic_contact_id() {
		return traffic_contact_id;
	}

	public void setTraffic_contact_id(int traffic_contact_id) {
		this.traffic_contact_id = traffic_contact_id;
	}
	


}
