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

import com.mediamath.terminalone.utils.Utility;

import javax.ws.rs.core.Form;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Campaign extends Entity {

    private static final String YYYY_MM_DDTHH_MM_SS_Z = "yyyy-MM-dd'T'HH:mm:ss Z";

    public Campaign() {
        super("Campaign");
    }

    public enum conv {
        every, one, variable
    }

    public enum freqInts {
        hour("hour"), day("day"), week("week"), month("month"), not_applicable("not-applcable");

        String value;

        freqInts(String s) {
            value = s;
        }

        public String getValue() {
            return value;
        }
    } // should be not-applicable

    public enum freqTypes {
        even("even"), asap("asap"), no_limit("no-limit");
        String value;

        freqTypes(String s) {
            value = s;
        }

        public String getValue() {
            return value;
        }
    } // should be no-limit

    public enum goalCats {
        audience("audience"), engagement("engagement"), response("response"), none("none");

        String value;

        goalCats(String s) {
            value = s;
        }

        public String getValue() {
            return value;
        }
    }

    public enum goalTypes {
        spend("spend"), reach("reach"), cpc("cpc"), cpa("cpa"), roi("roi"), none("none");

        String value;

        goalTypes(String s) {
            value = s;
        }

        public String getValue() {
            return value;
        }

    }

    public enum servTypes {
        SELF, MANAGED
    }

    private ArrayList<T1Cost> ad_server_fee = new ArrayList<T1Cost>();
    private int ad_server_id;
    private String ad_server_password;
    private String ad_server_username;
    private int advertiser_id;
    private float agency_fee_pct;
    private String conversion_type;
    private int conversion_variable_minutes;
    private Date created_on;
    private String currency_code;
    private boolean dcs_data_is_campaign_level;
    private Date end_date;
    private int frequency_amount;
    private freqInts frequency_interval;
    private freqTypes frequency_type;
    private boolean frequency_optimization;
    private float goal_alert;
    private goalCats goal_category;
    private goalTypes goal_type;
    private ArrayList<T1Cost> goal_value = new ArrayList<T1Cost>();
    private boolean has_custom_attribution;
    private int id;
    private int impression_cap_amount;
    private boolean impression_cap_automatic;
    private freqTypes impression_cap_type;
    private freqTypes spend_cap_type;
    private String io_name;
    private String io_reference_num;
    private Date initial_start_date;
    private float margin_pct;
    private boolean minimize_multi_ads;
    private int merit_pixel_id;
    private String name;
    private boolean override_suspicious_traffic_filter;
    private int pc_window_minutes;
    private float pv_pct;
    private int pv_window_minutes;
    private boolean restrict_targeting_to_deterministic_id;
    private boolean restrict_targeting_to_same_device_id;
    private servTypes service_type;
    private ArrayList<T1Cost> spend_cap_amount = new ArrayList<T1Cost>();
    private boolean spend_cap_automatic = true;
    private Date start_date;
    private boolean status;
    private int suspicious_traffic_filter_level;
    private ArrayList<T1Cost> total_budget = new ArrayList<T1Cost>();
    private int total_impression_budget;
    private Date updated_on;
    private boolean use_default_ad_server;
    private boolean use_mm_freq;
    private int version;
    private String zone_name;
    private boolean copyCampaign = false;
    private BudgetFlight relevant_budget_flight;
    private Map<Date, Double> margins = new HashMap<Date, Double>();

    private Advertiser advertiser;
    private AdServer ad_server;
    private Pixel merit_pixel;
    private ArrayList<SiteList> site_lists = new ArrayList<SiteList>();
    private ArrayList<BudgetFlight> budget_flights = new ArrayList<>();

    private ArrayList<CampaignCustomBrainSelection> campaign_custom_brain_selection = new ArrayList<>();

    public void setMargins(Date date, Double doubleval) {
        margins.put(date, new BigDecimal(doubleval).setScale(4, RoundingMode.HALF_EVEN).doubleValue());
    }

    public void resetMargins() {
        margins.clear();
    }

    public Map<Date, Double> getMargins() {
        return this.margins;
    }

    public int getAdServerId() {
        return ad_server_id;
    }

    public void setAdServerId(int ad_server_id) {
        this.ad_server_id = ad_server_id;
    }

    public String getAdServerPassword() {
        return ad_server_password;
    }

    public void setAdServerPassword(String ad_server_password) {
        this.ad_server_password = ad_server_password;
    }

    public String getAdServerUsername() {
        return ad_server_username;
    }

    public void setAdServerUsername(String ad_server_username) {
        this.ad_server_username = ad_server_username;
    }

    public int getAdvertiserId() {
        return advertiser_id;
    }

    public void setAdvertiserId(int advertiser_id) {
        this.advertiser_id = advertiser_id;
    }

    public float getAgencyFeePct() {
        return agency_fee_pct;
    }

    public void setAgencyFeePct(float agency_fee_pct) {
        this.agency_fee_pct = agency_fee_pct;
    }

    public String getConversionType() {
        return conversion_type;
    }

    public void setConversionType(String conversion_type) {
        this.conversion_type = conversion_type;
    }

    public int getConversionVariableMinutes() {
        return conversion_variable_minutes;
    }

    public void setConversionVariableMinutes(int conversion_variable_minutes) {
        this.conversion_variable_minutes = conversion_variable_minutes;
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

    public boolean isDcsDataIsCampaignLevel() {
        return dcs_data_is_campaign_level;
    }

    public void setDcsDataIsCampaignLevel(boolean dcs_data_is_campaign_level) {
        this.dcs_data_is_campaign_level = dcs_data_is_campaign_level;
    }

    public Date getEndDate() {
        return end_date;
    }

    public void setEndDate(Date end_date) {
        this.end_date = end_date;
    }

    public int getFrequencyAmount() {
        return frequency_amount;
    }

    public void setFrequencyAmount(int frequency_amount) {
        this.frequency_amount = frequency_amount;
    }

    public freqInts getFrequencyInterval() {
        return frequency_interval;
    }

    public void setFrequencyInterval(freqInts frequency_interval) {
        this.frequency_interval = frequency_interval;
    }

    public freqTypes getFrequencyType() {
        return frequency_type;
    }

    public void setFrequencyType(freqTypes frequency_type) {
        this.frequency_type = frequency_type;
    }

    public boolean isFrequencyOptimization() {
        return frequency_optimization;
    }

    public void setFrequencyOptimization(boolean frequency_optimization) {
        this.frequency_optimization = frequency_optimization;
    }

    public float getGoalAlert() {
        return goal_alert;
    }

    public void setGoalAlert(float goal_alert) {
        this.goal_alert = goal_alert;
    }

    public goalCats getGoalCategory() {
        return goal_category;
    }

    public void setGoalCategory(goalCats goal_category) {
        this.goal_category = goal_category;
    }

    public goalTypes getGoalType() {
        return goal_type;
    }

    public void setGoalType(goalTypes goal_type) {
        this.goal_type = goal_type;
    }

    public boolean isHasCustomAttribution() {
        return has_custom_attribution;
    }

    public void setHasCustomAttribution(boolean has_custom_attribution) {
        this.has_custom_attribution = has_custom_attribution;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImpressionCapAmount() {
        return impression_cap_amount;
    }

    public void setImpressionCapAmount(int impression_cap_amount) {
        this.impression_cap_amount = impression_cap_amount;
    }

    public boolean isImpressionCapAutomatic() {
        return impression_cap_automatic;
    }

    public void setImpressionCapAutomatic(boolean impression_cap_automatic) {
        this.impression_cap_automatic = impression_cap_automatic;
    }

    public freqTypes getImpressionCapType() {
        return impression_cap_type;
    }

    public void setImpressionCapType(freqTypes impression_cap_type) {
        this.impression_cap_type = impression_cap_type;
    }

    public String getIoName() {
        return io_name;
    }

    public void setIoName(String io_name) {
        this.io_name = io_name;
    }

    public String getIoReferenceNum() {
        return io_reference_num;
    }

    public void setIoReferenceNum(String io_reference_num) {
        this.io_reference_num = io_reference_num;
    }

    public Date getInitialStartDate() {
        return initial_start_date;
    }

    public void setInitialStartDate(Date initial_start_date) {
        this.initial_start_date = initial_start_date;
    }

    public float getMarginPct() {
        return margin_pct;
    }

    public void setMarginPct(float margin_pct) {
        this.margin_pct = margin_pct;
    }

    public boolean isMinimizeMultiAds() {
        return minimize_multi_ads;
    }

    public void setMinimizeMultiAds(boolean minimize_multi_ads) {
        this.minimize_multi_ads = minimize_multi_ads;
    }

    public String getName() {
        return name;
    }

    public boolean isOverrideSuspiciousTrafficFilter() {
        return override_suspicious_traffic_filter;
    }

    public void setOverrideSuspiciousTrafficFilter(boolean override_suspicious_traffic_filter) {
        this.override_suspicious_traffic_filter = override_suspicious_traffic_filter;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPcWindowMinutes() {
        return pc_window_minutes;
    }

    public void setPcWindowMinutes(int pc_window_minutes) {
        this.pc_window_minutes = pc_window_minutes;
    }

    public float getPvPct() {
        return pv_pct;
    }

    public void setPvPct(float pv_pct) {
        this.pv_pct = pv_pct;
    }

    public int getPvWindowMinutes() {
        return pv_window_minutes;
    }

    public void setPvWindowMinutes(int pv_window_minutes) {
        this.pv_window_minutes = pv_window_minutes;
    }

    public boolean isRestrictTargetingToDeterministicId() {
        return restrict_targeting_to_deterministic_id;
    }

    public void setRestrictTargetingToDeterministicId(boolean restrict_targeting_to_deterministic_id) {
        this.restrict_targeting_to_deterministic_id = restrict_targeting_to_deterministic_id;
    }

    public boolean isRestrictTargetingToSameDeviceId() {
        return restrict_targeting_to_same_device_id;
    }

    public void setRestrictTargetingToSameDeviceId(boolean restrict_targeting_to_same_device_id) {
        this.restrict_targeting_to_same_device_id = restrict_targeting_to_same_device_id;
    }

    public servTypes getServiceType() {
        return service_type;
    }

    public void setServiceType(servTypes service_type) {
        this.service_type = service_type;
    }

    public boolean isSpendCapAutomatic() {
        return spend_cap_automatic;
    }

    public void setSpendCapAutomatic(boolean spend_cap_automatic) {
        this.spend_cap_automatic = spend_cap_automatic;
    }

    public Date getStartDate() {
        return start_date;
    }

    public void setStartDate(Date start_date) {
        this.start_date = start_date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getSuspiciousTrafficFilterLevel() {
        return suspicious_traffic_filter_level;
    }

    public void setSuspiciousTrafficFilterLevel(int suspicious_traffic_filter_level) {
        this.suspicious_traffic_filter_level = suspicious_traffic_filter_level;
    }

    public Date getUpdatedOn() {
        return updated_on;
    }

    public void setUpdatedOn(Date updated_on) {
        this.updated_on = updated_on;
    }

    public boolean isUseDefaultAdServer() {
        return use_default_ad_server;
    }

    public void setUseDefaultAdServer(boolean use_default_ad_server) {
        this.use_default_ad_server = use_default_ad_server;
    }

    public boolean isUseMmFreq() {
        return use_mm_freq;
    }

    public void setUseMmFreq(boolean use_mm_freq) {
        this.use_mm_freq = use_mm_freq;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getZoneName() {
        return zone_name;
    }

    public void setZoneName(String zone_name) {
        this.zone_name = zone_name;
    }

    public boolean isCopyCampaign() {
        return copyCampaign;
    }

    public void setCopyCampaign(boolean copyCampaign) {
        this.copyCampaign = copyCampaign;
    }

    public BudgetFlight getRelevantBudgetFlight() {
        return relevant_budget_flight;
    }

    public void setRelevantBudgetFlight(BudgetFlight relevant_budget_flight) {
        this.relevant_budget_flight = relevant_budget_flight;
    }

    public int getMeritPixelId() {
        return merit_pixel_id;
    }

    public void setMeritPixelId(int merit_pixel_id) {
        this.merit_pixel_id = merit_pixel_id;
    }

    public ArrayList<T1Cost> getTotalBudget() {
        return total_budget;
    }

    public Advertiser getAdvertiser() {
        return advertiser;
    }

    public void setAdvertiser(Advertiser advertiser) {
        this.advertiser = advertiser;
    }

    public AdServer getAdServer() {
        return ad_server;
    }

    public void setAdServer(AdServer ad_server) {
        this.ad_server = ad_server;
    }

    public Pixel getMeritPixel() {
        return merit_pixel;
    }

    public void setMeritPixel(Pixel merit_pixel) {
        this.merit_pixel = merit_pixel;
    }

    public void setTotalBudget(double value, String currency_code) {
        setCurrencyFieldValue(value, currency_code, this.total_budget);
    }


    public ArrayList<T1Cost> getAdServerFee() {
        return ad_server_fee;
    }

    public void setAdServerFee(double value, String currency_code) {
       setCurrencyFieldValue(value, currency_code, this.ad_server_fee);
    }

    public ArrayList<T1Cost> getSpendCapAmount() {
        return spend_cap_amount;
    }

    public void setSpendCapAmount(double value, String currency_code) {
        setCurrencyFieldValue(value, currency_code, this.spend_cap_amount);
    }

    public ArrayList<T1Cost> getGoalValue() {
        return goal_value;
    }

    public void setGoalValue(double value, String currency_code) {
        setCurrencyFieldValue(value, currency_code, this.goal_value);
    }

    public void setGoalValue(double value) {
        this.goal_value.clear();
        if (value > 0) {
            T1Cost cost = new T1Cost();
            cost.setValue(value);
            this.goal_value.add(cost);
        }
    }

    public freqTypes getSpendCapType() {
        return spend_cap_type;
    }

    public void setSpendCapType(freqTypes spend_cap_type) {
        this.spend_cap_type = spend_cap_type;
    }

    public int getTotalImpressionBudget() {
        return total_impression_budget;
    }

    public void setTotalImpressionBudget(int total_impressions_budget) {
        this.total_impression_budget = total_impressions_budget;
    }

    public ArrayList<SiteList> getSiteLists() {
        return site_lists;
    }

    public void setSiteLists(ArrayList<SiteList> siteLists) {
        this.site_lists = siteLists;
    }

    public ArrayList<BudgetFlight> getBudgetFlights() {
        return budget_flights;
    }

    public void setBudgetFlights(ArrayList<BudgetFlight> budget_flights) {
        this.budget_flights = budget_flights;
    }

    public ArrayList<CampaignCustomBrainSelection> getCampaignCustomBrainSelection() {
        return campaign_custom_brain_selection;
    }

    public void setCampaignCustomBrainSelection(ArrayList<CampaignCustomBrainSelection> campaign_custom_brain_selection) {
        this.campaign_custom_brain_selection = campaign_custom_brain_selection;
    }

    @Override
    public Form getForm() {

        final SimpleDateFormat SDF = new SimpleDateFormat(YYYY_MM_DDTHH_MM_SS_Z);

        Form campaignForm = new Form();

        if (this.getName() != null) {
            campaignForm.param("name", this.getName());
        }

        if (!this.getAdServerFee().isEmpty()) {
            campaignForm.param("ad_server_fee", String.valueOf(this.getAdServerFee().get(0).getValue()));
        }

        if (!this.getTotalBudget().isEmpty()) {
            campaignForm.param("total_budget", String.valueOf(this.getTotalBudget().get(0).getValue()));
        }

        if (this.getTotalImpressionBudget() > 0) {
            campaignForm.param("total_impression_budget", String.valueOf(this.getTotalImpressionBudget()));
        }

        if (!this.getSpendCapAmount().isEmpty()) {
            campaignForm.param("spend_cap_amount", String.valueOf(this.getSpendCapAmount().get(0).getValue()));
        }

        if (!this.getGoalValue().isEmpty()) {
            campaignForm.param("goal_value", String.valueOf(this.getGoalValue().get(0).getValue()));
        }

        if (this.getAdServerId() > 0) {
            campaignForm.param("ad_server_id", String.valueOf(this.getAdServerId()));
        }

        if (this.getAdServerId() > 0) {
            campaignForm.param("advertiser_id", String.valueOf(this.getAdvertiserId()));
        }

        if (this.getConversionType() != null) {
            campaignForm.param("conversion_type", this.getConversionType());
        }

        if (this.getConversionVariableMinutes() > 0) {
            campaignForm.param("conversion_variable_minutes", String.valueOf(this.getConversionVariableMinutes()));
        }

        if (this.getEndDate() != null) {
            String endDate = SDF.format(this.getEndDate());
            campaignForm.param("end_date", endDate);
        }

        if (this.getGoalType() != null) {
            campaignForm.param("goal_type", String.valueOf(this.getGoalType().getValue()));
        }

        if (this.getServiceType() != null) {
            campaignForm.param("service_type", String.valueOf(this.getServiceType()));
        }

        if (this.getStartDate() != null) {
            String startDate = SDF.format(this.getStartDate());
            campaignForm.param("start_date", startDate);
        }

        if (this.getAdServerPassword() != null) {
            campaignForm.param("ad_server_password", this.getAdServerPassword());
        }

        if (this.getAdServerUsername() != null) {
            campaignForm.param("ad_server_username", this.getAdServerUsername());
        }

        if (this.getAgencyFeePct() > 0) {
            campaignForm.param("agency_fee_pct", String.valueOf(this.getAgencyFeePct()));
        }

        if (this.getCurrencyCode() != null) {
            campaignForm.param("currency_code", this.getCurrencyCode());
        }

        // check null
        if (this.getFrequencyAmount() > 0) {
            campaignForm.param("frequency_amount", String.valueOf(this.getFrequencyAmount()));
        }

        if (this.getFrequencyInterval() != null) {
            campaignForm.param("frequency_interval", String.valueOf(this.getFrequencyInterval().getValue()));
        }

        if (this.getFrequencyType() != null) {
            campaignForm.param("frequency_type", String.valueOf(this.getFrequencyType().getValue()));
        }

        if (this.getGoalAlert() > 0) {
            campaignForm.param("goal_alert", String.valueOf(this.getGoalAlert()));
        }

        if (this.getGoalCategory() != null) {
            campaignForm.param("goal_category", String.valueOf(this.getGoalCategory().getValue()));
        }

        if (!this.isCopyCampaign() && this.getBudgetFlights().isEmpty() && this.getCampaignCustomBrainSelection().isEmpty()) {
            campaignForm.param("use_mm_freq", Utility.getOnOrOff(this.isUseMmFreq()));
            campaignForm.param("dcs_data_is_campaign_level", Utility.getOnOrOff(this.isDcsDataIsCampaignLevel()));
            campaignForm.param("frequency_optimization", Utility.getOnOrOff(this.isFrequencyOptimization()));
            campaignForm.param("has_custom_attribution", Utility.getOnOrOff(this.isHasCustomAttribution()));
            campaignForm.param("impression_cap_automatic", Utility.getOnOrOff(this.isImpressionCapAutomatic()));
            campaignForm.param("minimize_multi_ads", Utility.getOnOrOff(this.isMinimizeMultiAds()));
            campaignForm.param("override_suspicious_traffic_filter",
                    Utility.getOnOrOff(this.isOverrideSuspiciousTrafficFilter()));
            campaignForm.param("restrict_targeting_to_deterministic_id",
                    Utility.getOnOrOff(this.isRestrictTargetingToDeterministicId()));
            campaignForm.param("restrict_targeting_to_same_device_id",
                    Utility.getOnOrOff(this.isRestrictTargetingToSameDeviceId()));
            campaignForm.param("spend_cap_automatic", Utility.getOnOrOff(this.isSpendCapAutomatic()));
            campaignForm.param("status", Utility.getOnOrOff(this.isStatus()));
            campaignForm.param("use_default_ad_server", Utility.getOnOrOff(this.isUseDefaultAdServer()));
        }

        if (this.getImpressionCapAmount() > 0) {
            campaignForm.param("impression_cap_amount", String.valueOf(this.getImpressionCapAmount()));
        }

        if (this.getImpressionCapType() != null) {
            campaignForm.param("impression_cap_type", String.valueOf(this.getImpressionCapType().getValue()));
        }

        if (this.getSpendCapType() != null) {
            campaignForm.param("spend_cap_type", String.valueOf(this.getSpendCapType().getValue()));
        } /*
         * else { campaignForm.param("spend_cap_type",
         * freqTypes.no_limit.value); }
         */

        if (this.getIoName() != null) {
            campaignForm.param("io_name", this.getIoName());
        }

        if (this.getIoReferenceNum() != null) {
            campaignForm.param("io_reference_num", this.getIoReferenceNum());
        }

        if (this.getMarginPct() > 0) {
            campaignForm.param("margin_pct", String.valueOf(this.getMarginPct()));
        }

        if (this.getMeritPixelId() > 0) {
            campaignForm.param("merit_pixel_id", String.valueOf(this.getMeritPixelId()));
        }

        if (this.getPcWindowMinutes() > 0) {
            campaignForm.param("pc_window_minutes", String.valueOf(this.getPcWindowMinutes()));
        }

        if (this.getPvPct() > 0) {
            campaignForm.param("pv_pct", String.valueOf(this.getPvPct()));
        }

        if (this.getPvWindowMinutes() > 0) {
            campaignForm.param("pv_window_minutes", String.valueOf(this.getPvWindowMinutes()));
        }

        if (this.getZoneName() != null) {
            campaignForm.param("zone_name", this.getZoneName());
        }

        if (this.getSuspiciousTrafficFilterLevel() > 0) {
            campaignForm.param("suspicious_traffic_filter_level",
                    String.valueOf(this.getSuspiciousTrafficFilterLevel()));
        }

        if (this.getVersion() > 0) {
            campaignForm.param("version", String.valueOf(this.getVersion()));
        }

        //site lists
        if (!this.getSiteLists().isEmpty()) {
            int inc = 1;
            for (SiteList sl : this.getSiteLists()) {
                if (sl != null && sl.getId() > 0) {
                    campaignForm.param("site_lists." + inc + ".id", String.valueOf(sl.getId()));
                    campaignForm.param("site_lists." + inc + ".assigned", Utility.getOneOrZero(sl.isAssigned()));
                    inc++;
                }
            }
        }

        //budget flights bulk
        if (!this.getBudgetFlights().isEmpty()) {
            int inc = 1;
            for (BudgetFlight sl : this.getBudgetFlights()) {
                if (sl != null) {
                    if (sl.getId() > 0) {
                        campaignForm.param("budget_flights." + inc + ".id", String.valueOf(sl.getId()));
                    }

                    campaignForm.param("budget_flights." + inc + ".version", String.valueOf(sl.getVersion()));

                    if (sl.getStartDate() != null) {
                        campaignForm.param("budget_flights." + inc + ".start_date", SDF.format(sl.getStartDate()));
                    }
                    if (sl.getEndDate() != null) {
                        campaignForm.param("budget_flights." + inc + ".end_date", SDF.format(sl.getEndDate()));
                    }

                    if (!sl.getTotalBudget().isEmpty()) {
                        campaignForm.param("budget_flights." + inc + ".total_budget", String.valueOf(sl.getTotalBudget().get(0).getValue()));
                    }

                    if (sl.getTotalImpressionBudget() > 0) {
                        campaignForm.param("budget_flights." + inc + ".total_impression_budget", String.valueOf(sl.getTotalImpressionBudget()));
                    }
                    inc++;
                }
            }
        }

        //custom brain selections - single
        if (!this.getCampaignCustomBrainSelection().isEmpty() && this.getCampaignCustomBrainSelection().size() == 1) {
            for (CampaignCustomBrainSelection ccbs : this.getCampaignCustomBrainSelection()) {
                if (ccbs != null) {
                    if (ccbs.getSelectionId() > 0) {
                        campaignForm.param("selection_id", String.valueOf(ccbs.getSelectionId()));
                    }
                    if (ccbs.getSelectionType() != null) {
                        campaignForm.param("selection_type", ccbs.getSelectionType().value);
                    }
                    if (ccbs.getSelectionId() > 0) {
                        campaignForm.param("active", Utility.getOneOrZero(ccbs.isActive()));
                    }
                }
            }
        }


        //custom brain selections
        if (!this.getCampaignCustomBrainSelection().isEmpty() && this.getCampaignCustomBrainSelection().size() > 1) {
            int inc = 1;
            for (CampaignCustomBrainSelection ccbs : this.getCampaignCustomBrainSelection()) {
                if (ccbs != null) {
                    if (ccbs.getSelectionId() > 0) {
                        campaignForm.param("custom_brain_selections." + inc + ".selection_id", String.valueOf(ccbs.getSelectionId()));
                    }
                    if (ccbs.getSelectionType() != null) {
                        campaignForm.param("custom_brain_selections." + inc + ".selection_type", ccbs.getSelectionType().value);
                    }
                    if (ccbs.getSelectionId() > 0) {
                        campaignForm.param("custom_brain_selections." + inc + ".active", Utility.getOneOrZero(ccbs.isActive()));
                    }
                    inc++;
                }

            }
        }

        campaignForm = Utility.getFilteredForm(campaignForm, "campaign");

        return campaignForm;

    }

}
