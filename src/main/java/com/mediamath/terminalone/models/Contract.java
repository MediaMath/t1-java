package com.mediamath.terminalone.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mediamath.terminalone.exceptions.ClientException;
import com.mediamath.terminalone.utils.Utility;
import org.javers.core.metamodel.annotation.DiffIgnore;

public class Contract implements T1Entity {

	private static final String YYYY_MM_DDTHH_MM_SS_Z = "yyyy-MM-dd'T'HH:mm:ss Z";
	private static final String entityName = "Contract";

	private List<Currency> adaptive_segment_cpm = new ArrayList<Currency>();
	private int contract_number;
	private Date created_on;
	private String currency_code;
	private Date effective_end_date;
	private Date effective_start_date;
	private List<Currency> evidon_privacy_cost_cpm = new ArrayList<Currency>();
	private boolean exclude_agency_margin;
	private List<Currency> external_media_tracking_cpm = new ArrayList<Currency>();
	private List<Currency> fbx_dynamic_cpm = new ArrayList<Currency>();
	private int id;
	private int managed_service_fee_pct;
	private String managed_service_fee_unit;
	private String name;
	private int optimization_fee_pct;
	private String optimization_fee_unit;
	private int organization_id;
	private List<Currency> peer39_channel_fee_cpm = new ArrayList<Currency>();
	private List<Currency> peer39_custom_fee_cpm = new ArrayList<Currency>();
	private List<Currency> peer39_quality_fee_cpm = new ArrayList<Currency>();
	private List<Currency> peer39_safety_fee_cpm = new ArrayList<Currency>();
	private int platform_access_fee_pct;
	private String platform_access_fee_unit;
	private int platform_monthly_min;
	private List<Currency> pmp_optimization_off_fee_cpm = new ArrayList<Currency>();
	private String pmp_optimization_off_unit;
	private int pmp_optimization_off_fee_pct;
	private List<Currency> pmp_optimization_on_fee_cpm = new ArrayList<Currency>();
	private String pmp_optimization_on_unit;
	private int pmp_optimization_on_fee_pct;
	private int profit_share_fee_pct;
	private int spend_cap;
	private List<Currency> t1_as_fee_cpm = new ArrayList<Currency>();
	private List<Currency> t1_html5_fee_cpm = new ArrayList<Currency>();
	private List<Currency> t1_vads_fee_cpm = new ArrayList<Currency>();
	private Date updated_on;
	private int version;

	public List<Currency> getAdaptiveSegmentCpm() {
		return adaptive_segment_cpm;
	}

	public void setAdaptiveSegmentCpm(List<Currency> adaptive_segment_cpm) {
		this.adaptive_segment_cpm = adaptive_segment_cpm;
	}

	public int getContractNumber() {
		return contract_number;
	}

	public void setContractNumber(int contract_number) {
		this.contract_number = contract_number;
	}

	public Date getCreatedOn() {
		return created_on;
	}

	public void setCreatedOn(Date created_on) {
		this.created_on = created_on;
	}

	public String getCurrencyCode() {
		return currency_code;
	}

	public void setCurrencyCode(String currency_code) {
		this.currency_code = currency_code;
	}

	public Date getEffectiveEndDate() {
		return effective_end_date;
	}

	public void setEffectiveEndDate(Date effective_end_date) {
		this.effective_end_date = effective_end_date;
	}

	public Date getEffectiveStartDate() {
		return effective_start_date;
	}

	public void setEffectiveStartDate(Date effective_start_date) {
		this.effective_start_date = effective_start_date;
	}

	public List<Currency> getEvidonPrivacyCostCpm() {
		return evidon_privacy_cost_cpm;
	}

	public void setEvidonPrivacyCostCpm(List<Currency> evidon_privacy_cost_cpm) {
		this.evidon_privacy_cost_cpm = evidon_privacy_cost_cpm;
	}

	public boolean isExcludeAgencyMargin() {
		return exclude_agency_margin;
	}

	public void setExcludeAgencyMargin(boolean exclude_agency_margin) {
		this.exclude_agency_margin = exclude_agency_margin;
	}

	public List<Currency> getExternalMediaTrackingCpm() {
		return external_media_tracking_cpm;
	}

	public void setExternalMediaTrackingCpm(List<Currency> external_media_tracking_cpm) {
		this.external_media_tracking_cpm = external_media_tracking_cpm;
	}

	public List<Currency> getFbxDynamicCpm() {
		return fbx_dynamic_cpm;
	}

	public void setFbxDynamicCpm(List<Currency> fbx_dynamic_cpm) {
		this.fbx_dynamic_cpm = fbx_dynamic_cpm;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getManagedServiceFeePct() {
		return managed_service_fee_pct;
	}

	public void setManagedServiceFeePct(int managed_service_fee_pct) {
		this.managed_service_fee_pct = managed_service_fee_pct;
	}

	public String getManagedServiceFeeUnit() {
		return managed_service_fee_unit;
	}

	public void setManagedServiceFeeUnit(String managed_service_fee_unit) {
		this.managed_service_fee_unit = managed_service_fee_unit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOptimizationFeePct() {
		return optimization_fee_pct;
	}

	public void setOptimizationFeePct(int optimization_fee_pct) {
		this.optimization_fee_pct = optimization_fee_pct;
	}

	public String getOptimizationFeeUnit() {
		return optimization_fee_unit;
	}

	public void setOptimizationFeeUnit(String optimization_fee_unit) {
		this.optimization_fee_unit = optimization_fee_unit;
	}

	public int getOrganizationId() {
		return organization_id;
	}

	public void setOrganizationId(int organization_id) {
		this.organization_id = organization_id;
	}

	public List<Currency> getPeer39ChannelFeeCpm() {
		return peer39_channel_fee_cpm;
	}

	public void setPeer39ChannelFeeCpm(List<Currency> peer39_channel_fee_cpm) {
		this.peer39_channel_fee_cpm = peer39_channel_fee_cpm;
	}

	public List<Currency> getPeer39CustomFeeCpm() {
		return peer39_custom_fee_cpm;
	}

	public void setPeer39CustomFeeCpm(List<Currency> peer39_custom_fee_cpm) {
		this.peer39_custom_fee_cpm = peer39_custom_fee_cpm;
	}

	public List<Currency> getPeer39QualityFeeCpm() {
		return peer39_quality_fee_cpm;
	}

	public void setPeer39QualityFeeCpm(List<Currency> peer39_quality_fee_cpm) {
		this.peer39_quality_fee_cpm = peer39_quality_fee_cpm;
	}

	public List<Currency> getPeer39SafetyFeeCpm() {
		return peer39_safety_fee_cpm;
	}

	public void setPeer39SafetyFeeCpm(List<Currency> peer39_safety_fee_cpm) {
		this.peer39_safety_fee_cpm = peer39_safety_fee_cpm;
	}

	public int getPlatformAccessFeePct() {
		return platform_access_fee_pct;
	}

	public void setPlatformAccessFeePct(int platform_access_fee_pct) {
		this.platform_access_fee_pct = platform_access_fee_pct;
	}

	public String getPlatformAccessFeeUnit() {
		return platform_access_fee_unit;
	}

	public void setPlatformAccessFeeUnit(String platform_access_fee_unit) {
		this.platform_access_fee_unit = platform_access_fee_unit;
	}

	public int getPlatformMonthlyMin() {
		return platform_monthly_min;
	}

	public void setPlatformMonthlyMin(int platform_monthly_min) {
		this.platform_monthly_min = platform_monthly_min;
	}

	public List<Currency> getPmpOptimizationOffFeeCpm() {
		return pmp_optimization_off_fee_cpm;
	}

	public void setPmpOptimizationOffFeeCpm(List<Currency> pmp_optimization_off_fee_cpm) {
		this.pmp_optimization_off_fee_cpm = pmp_optimization_off_fee_cpm;
	}

	public String getPmpOptimizationOffUnit() {
		return pmp_optimization_off_unit;
	}

	public void setPmpOptimizationOffUnit(String pmp_optimization_off_unit) {
		this.pmp_optimization_off_unit = pmp_optimization_off_unit;
	}

	public List<Currency> getPmpOptimizationOnFeeCpm() {
		return pmp_optimization_on_fee_cpm;
	}

	public void setPmpOptimizationOnFeeCpm(List<Currency> pmp_optimization_on_fee_cpm) {
		this.pmp_optimization_on_fee_cpm = pmp_optimization_on_fee_cpm;
	}

	public String getPmpOptimizationOnUnit() {
		return pmp_optimization_on_unit;
	}

	public void setPmpOptimizationOnUnit(String pmp_optimization_on_unit) {
		this.pmp_optimization_on_unit = pmp_optimization_on_unit;
	}

	public int getProfitShareFeePct() {
		return profit_share_fee_pct;
	}

	public void setProfitShareFeePct(int profit_share_fee_pct) {
		this.profit_share_fee_pct = profit_share_fee_pct;
	}

	public int getSpendCap() {
		return spend_cap;
	}

	public void setSpendCap(int spend_cap) {
		this.spend_cap = spend_cap;
	}

	public List<Currency> getT1AsFeeCpm() {
		return t1_as_fee_cpm;
	}

	public void setT1AsFeeCpm(List<Currency> t1_as_fee_cpm) {
		this.t1_as_fee_cpm = t1_as_fee_cpm;
	}

	public List<Currency> getT1Html5FeeCpm() {
		return t1_html5_fee_cpm;
	}

	public void setT1Html5FeeCpm(List<Currency> t1_html5_fee_cpm) {
		this.t1_html5_fee_cpm = t1_html5_fee_cpm;
	}

	public List<Currency> getT1VadsFeeCpm() {
		return t1_vads_fee_cpm;
	}

	public void setT1VadsFeeCpm(List<Currency> t1_vads_fee_cpm) {
		this.t1_vads_fee_cpm = t1_vads_fee_cpm;
	}

	public Date getUpdatedOn() {
		return updated_on;
	}

	public void setUpdatedOn(Date updated_on) {
		this.updated_on = updated_on;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getPmpOptimizationOffFeePct() {
		return pmp_optimization_off_fee_pct;
	}

	public void setPmpOptimizationOffFeePct(int pmp_optimization_off_fee_pct) {
		this.pmp_optimization_off_fee_pct = pmp_optimization_off_fee_pct;
	}

	public int getPmpOptimizationOnFeePct() {
		return pmp_optimization_on_fee_pct;
	}

	public void setPmpOptimizationOnFeePct(int pmp_optimization_on_fee_pct) {
		this.pmp_optimization_on_fee_pct = pmp_optimization_on_fee_pct;
	}

	@Override
	public String getEntityname() {
		return entityName;
	}

	@Override
	@DiffIgnore
	@JsonIgnore
	public Form getForm() {
		final SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DDTHH_MM_SS_Z);

		Form contractForm = new Form();

		if (!this.getAdaptiveSegmentCpm().isEmpty()) {
			contractForm.param("adaptive_segment_cpm", String.valueOf(this.getAdaptiveSegmentCpm().get(0).getValue()));
		}
		if (this.getContractNumber() > 0) {
			contractForm.param("contract_number", String.valueOf(this.getContractNumber()));
		}
		if (this.getCurrencyCode() != null) {
			contractForm.param("currency_code", this.getCurrencyCode());
		}
		if (this.getEffectiveEndDate() != null) {
			contractForm.param("effective_end_date", sdf.format(this.getEffectiveEndDate()));
		}
		if (this.getEffectiveStartDate() != null) {
			contractForm.param("effective_start_date", sdf.format(this.getEffectiveStartDate()));
		}
		if (!this.getEvidonPrivacyCostCpm().isEmpty()) {
			contractForm.param("evidon_privacy_cost_cpm",
					String.valueOf(this.getEvidonPrivacyCostCpm().get(0).getValue()));
		}
		contractForm.param("exclude_agency_margin", Utility.getOneOrZero(this.isExcludeAgencyMargin()));

		if (!this.getExternalMediaTrackingCpm().isEmpty() && this.getExternalMediaTrackingCpm().get(0).getValue().floatValue() > 0) {
			contractForm.param("external_media_tracking_cpm",
					String.valueOf(this.getExternalMediaTrackingCpm().get(0).getValue()));
		}
		if (!this.getFbxDynamicCpm().isEmpty()) {
			contractForm.param("fbx_dynamic_cpm", String.valueOf(this.getFbxDynamicCpm().get(0).getValue()));
		}
		if (this.getManagedServiceFeePct() > 0) {
			contractForm.param("managed_service_fee_pct", String.valueOf(this.getManagedServiceFeePct()));
		}
		if (this.getManagedServiceFeeUnit() != null) {
			contractForm.param("managed_service_fee_unit", this.getManagedServiceFeeUnit());
		}
		if (this.getName() != null) {
			contractForm.param("name", this.getName());
		}
		if (this.getOptimizationFeePct() > 0) {
			contractForm.param("optimization_fee_pct", String.valueOf(this.getOptimizationFeePct()));
		}
		if (this.getOptimizationFeeUnit() != null) {
			contractForm.param("optimization_fee_unit", this.getOptimizationFeeUnit());
		}
		if (this.getOrganizationId() > 0) {
			contractForm.param("organization_id", String.valueOf(this.getOrganizationId()));
		}
		if (!this.getPeer39ChannelFeeCpm().isEmpty()) {
			contractForm.param("peer39_channel_fee_cpm",
					String.valueOf(this.getPeer39ChannelFeeCpm().get(0).getValue()));
		}
		if (!this.getPeer39CustomFeeCpm().isEmpty()) {
			contractForm.param("peer39_custom_fee_cpm", String.valueOf(this.getPeer39CustomFeeCpm().get(0).getValue()));
		}
		if (!this.getPeer39QualityFeeCpm().isEmpty()) {
			contractForm.param("peer39_quality_fee_cpm",
					String.valueOf(this.getPeer39QualityFeeCpm().get(0).getValue()));
		}
		if (!this.getPeer39SafetyFeeCpm().isEmpty()) {
			contractForm.param("peer39_safety_fee_cpm", String.valueOf(this.getPeer39SafetyFeeCpm().get(0).getValue()));
		}
		if (this.getPlatformAccessFeePct() > 0) {
			contractForm.param("platform_access_fee_pct", String.valueOf(this.getPlatformAccessFeePct()));
		}
		if (this.getPlatformAccessFeeUnit() != null) {
			contractForm.param("platform_access_fee_unit", this.getPlatformAccessFeeUnit());
		}
		if (this.getPlatformMonthlyMin() > 0) {
			contractForm.param("platform_monthly_min", String.valueOf(this.getPlatformMonthlyMin()));
		}
		if (!this.getPmpOptimizationOffFeeCpm().isEmpty()) {
			contractForm.param("pmp_optimization_off_fee_cpm",
					String.valueOf(this.getPmpOptimizationOffFeeCpm().get(0).getValue()));
		}
		if (this.getPmpOptimizationOffUnit() != null) {
			contractForm.param("pmp_optimization_off_unit", this.getPmpOptimizationOffUnit());
		}
		if (this.getPmpOptimizationOffFeePct() > 0) {
			contractForm.param("pmp_optimization_off_fee_pct", String.valueOf(this.getPmpOptimizationOffFeePct()));
		}
		if (!this.getPmpOptimizationOnFeeCpm().isEmpty()) {
			contractForm.param("pmp_optimization_on_fee_cpm",
					String.valueOf(this.getPmpOptimizationOnFeeCpm().get(0).getValue()));
		}
		if (this.getPmpOptimizationOnUnit() != null) {
			contractForm.param("pmp_optimization_on_unit", this.getPmpOptimizationOnUnit());
		}
		if (this.getPmpOptimizationOnFeePct() > 0) {
			contractForm.param("pmp_optimization_on_fee_pct", String.valueOf(this.getPmpOptimizationOnFeePct()));
		}
		if (this.getProfitShareFeePct() > 0) {
			contractForm.param("profit_share_fee_pct", String.valueOf(this.getProfitShareFeePct()));
		}
		if (this.getSpendCap() > 0) {
			contractForm.param("spend_cap", String.valueOf(this.getSpendCap()));
		}
		if (!this.getT1AsFeeCpm().isEmpty()) {
			contractForm.param("t1_as_fee_cpm", String.valueOf(this.getT1AsFeeCpm().get(0).getValue()));
		}
		if (!this.getT1Html5FeeCpm().isEmpty()) {
			contractForm.param("t1_html5_fee_cpm", String.valueOf(this.getT1Html5FeeCpm().get(0).getValue()));
		}
		if (!this.getT1VadsFeeCpm().isEmpty()) {
			contractForm.param("t1_vads_fee_cpm", String.valueOf(this.getT1VadsFeeCpm().get(0).getValue()));
		}
		if (this.getVersion() >= 0) {
			contractForm.param("version", String.valueOf(this.getVersion()));
		}

		contractForm = Utility.getFilteredForm(contractForm, "contract");

		return contractForm;

	}

	@Override
	@DiffIgnore
	@JsonIgnore
	public String getUri() throws ClientException {
		StringBuilder uri = new StringBuilder();
		if (this.getId() > 0) {
			uri.append("/");
			uri.append(this.getId());
		}
		return uri.toString();
	}

}
