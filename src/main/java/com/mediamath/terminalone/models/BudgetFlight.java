package com.mediamath.terminalone.models;

import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.core.Form;

import com.mediamath.terminalone.exceptions.ClientException;

public class BudgetFlight implements T1Entity {

	private static final String entityName = "BudgetFlight";

	private int id;
	private int campaign_id;
	private String created_on;
	private String currency_code;
	private Date end_date;
	private boolean is_relevant;
	private String name;
	private Date start_date;
	private ArrayList<T1Cost> total_budget = new ArrayList<T1Cost>();
	private Date updated_on;
	private int version;
	private String zone_name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCampaign_id() {
		return campaign_id;
	}

	public void setCampaign_id(int campaign_id) {
		this.campaign_id = campaign_id;
	}

	public String getCreated_on() {
		return created_on;
	}

	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}

	public String getCurrency_code() {
		return currency_code;
	}

	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public boolean isIs_relevant() {
		return is_relevant;
	}

	public void setIs_relevant(boolean is_relevant) {
		this.is_relevant = is_relevant;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public ArrayList<T1Cost> getTotal_budget() {
		return total_budget;
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

	public String getZone_name() {
		return zone_name;
	}

	public void setZone_name(String zone_name) {
		this.zone_name = zone_name;
	}

	public void setTotalBudget(double value, String currency_code) {
		this.total_budget.clear();
		if (value > 0) {
			T1Cost cost = new T1Cost();
			cost.setValue(value);

			if (currency_code != null && !currency_code.isEmpty()) {
				cost.setCurrency_code(currency_code);
			}

			this.total_budget.add(cost);
		}
	}

	@Override
	public String getEntityname() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Form getForm() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUri() throws ClientException {
		StringBuilder uri = new StringBuilder();

		if (this.getId() > 0) {
			uri.append("/");
			uri.append(this.getId());
		}

		return uri.toString();
	}

}
