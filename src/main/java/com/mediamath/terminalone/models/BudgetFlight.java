package com.mediamath.terminalone.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.core.Form;

import com.mediamath.terminalone.exceptions.ClientException;
import org.javers.core.metamodel.annotation.DiffIgnore;
import org.javers.core.metamodel.annotation.Id;

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
	private int total_impression_budget;
	private boolean isDeleted = false;

	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@DiffIgnore
	public int getCampaignId() {
		return campaign_id;
	}

	public void setCampaignId(int campaign_id) {
		this.campaign_id = campaign_id;
	}

	@DiffIgnore
	public String getCreatedOn() {
		return created_on;
	}

	public void setCreatedOn(String created_on) {
		this.created_on = created_on;
	}

	public String getCurrencyCode() {
		return currency_code;
	}

	public void setCurrencyCode(String currency_code) {
		this.currency_code = currency_code;
	}

	public Date getEndDate() {
		return end_date;
	}

	public void setEndDate(Date end_date) {
		this.end_date = end_date;
	}

	@DiffIgnore
	public boolean isIsRelevant() {
		return is_relevant;
	}

	public void setIsRelevant(boolean is_relevant) {
		this.is_relevant = is_relevant;
	}

	@DiffIgnore
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//as we change start date if it is in the past, we cannot differ it
	@DiffIgnore
	public Date getStartDate() {
		return start_date;
	}

	public void setStartDate(Date start_date) {
		this.start_date = start_date;
	}

	public ArrayList<T1Cost> getTotalBudget() {
		return total_budget;
	}

	@DiffIgnore
	public Date getUpdatedOn() {
		return updated_on;
	}

	public void setUpdatedOn(Date updated_on) {
		this.updated_on = updated_on;
	}

	@DiffIgnore
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@DiffIgnore
	public String getZoneName() {
		return zone_name;
	}

	public void setZoneName(String zone_name) {
		this.zone_name = zone_name;
	}

	public int getTotalImpressionBudget() {
		return total_impression_budget;
	}

	public void setTotalImpressionBudget(int total_impression_budget) {
		this.total_impression_budget = total_impression_budget;
	}

	public void setTotalBudget(BigDecimal value, String currency_code) {
		this.total_budget.clear();
		if (value.compareTo(BigDecimal.ZERO) > 0) {
			T1Cost cost = new T1Cost();
			cost.setValue(value);

			if (currency_code != null && !currency_code.isEmpty()) {
				cost.setCurrencyCode(currency_code);
			}

			this.total_budget.add(cost);
		}
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
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
	public String getUri() throws ClientException {
		StringBuilder uri = new StringBuilder();

		if (this.getId() > 0) {
			uri.append("/");
			uri.append(this.getId());
		}

		return uri.toString();
	}

}
