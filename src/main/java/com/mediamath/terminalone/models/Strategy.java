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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Form;

public class Strategy implements T1Entity, Cloneable {

	private static final String entityName = "Strategy";

	public enum audSegExc {
		AND, OR
	}

	public enum audSegInc {
		AND, OR
	}

	public enum tgtSegExc {
		AND, OR
	}

	public enum tgtSegInc {
		AND, OR
	}

	public enum freqInt {
		hour("hour"), day("day"), week("week"), month("month"), campaign("campaign");
		String value;

		freqInt(String s) {
			value = s;
		}
		
		public String getValue(){
			return value;
		}
	} // should be not-applicable

	public enum freqType {
		even("even"), asap("asap"), no_limit("no-limit");

		String value;

		freqType(String s) {
			value = s;
		}
		
		public String getValue(){
			return value;
		}

	} // should be no-limit  'ctr', 'vcpm', 'vcr' and 'viewability_rate'

	public enum goalType {
		spend("spend"), reach("reach"), cpc("cpc"), cpa("cpa"), roi("roi"), ctr("ctr"), vcpm("vcpm"), vcr("vcr"), viewability_rate("viewability_rate");

		String value;

		goalType(String s) {
			value = s;
		}
		
		public String getValue(){
			return value;
		}

	}

	public enum mediaType {
		DISPLAY, VIDEO
	}

	public enum pacInt {
		hour("hour"), day("day");

		String value;

		pacInt(String s) {
			value = s;
		}
		
		public String getValue(){
			return value;
		}

	}

	public enum pacType {
		even("even"), asap("asap");

		String value;

		pacType(String s) {
			value = s;
		}
		public String getValue(){
			return value;
		}

	}

	public enum siteSelect {
		MATHSELECT_250, EXCLUDE_UGC, ALL, REDUCED
	}

	public enum supplyType {
		RTB, RMX_API, T1_RMX
	}

	public enum type {
		REM, GBO, AUD
	}

	private audSegExc audience_segment_exclude_op;
	private audSegInc audience_segment_include_op;
	private float bid_aggressiveness;
	private boolean bid_price_is_media_only;
	private ArrayList<Currency> budget = new ArrayList<Currency>();
	private int campaign_id;
	private String created_on;
	private String currency_code;
	private String description;
	//This field is manually deserialized
	private transient GoalValue effective_goal_value;
	private Date end_date;
	private String feature_compatibility;
	private int frequency_amount;
	private freqInt frequency_interval;
	private boolean frequency_optimization;
	private freqType frequency_type;
	private goalType goal_type;
	//This field is manually deserialized
	private transient GoalValue goal_value;
	private int id;
	private int impression_cap;
	private freqType impression_cap_type;
	private int impression_pacing_amount;
	private freqType impression_pacing_type;
	private freqInt impression_pacing_interval;
	private ArrayList<Currency> max_bid = new ArrayList<Currency>();
	private mediaType media_type;
	private ArrayList<Currency> min_bid = new ArrayList<Currency>();
	private String name;
	private ArrayList<Currency> pacing_amount = new ArrayList<Currency>();
	private pacInt pacing_interval;
	private pacType pacing_type;
	private ArrayList<Currency> roi_target = new ArrayList<Currency>();
	private String pixel_target_expr;
	private ArrayList<Integer> includePixels = new ArrayList<Integer>();
	private ArrayList<Integer> excludePixels = new ArrayList<Integer>();
	private boolean run_on_all_exchanges;
	private boolean run_on_all_pmp;
	private boolean run_on_display;
	private boolean run_on_mobile;
	private boolean run_on_streaming;
	private boolean site_restriction_transparent_urls;
	private siteSelect site_selectiveness;
	private Date start_date;
	private boolean status;
	private supplyType supply_type;
	private tgtSegExc targeting_segment_exclude_op;
	private tgtSegInc targeting_segment_include_op;
	private type type;
	private String updated_on;
	private boolean use_campaign_end;
	private boolean use_campaign_start;
	private boolean use_mm_freq;
	private boolean use_optimization;
	private int version;
	private String zone_name;
	private Aggregate aggregate;
	private boolean copyStrategy = false;
	private int from_campaign_id;
	private int to_campaign_id;

	private Campaign campaign;
	private TargetDimensions targetDimensions;
	private StrategyTargetValues strategyTargetValues;

	private List<StrategyDomain> strategy_domain_restrictions = new ArrayList<StrategyDomain>();
	private List<Segments> audience_segments = new ArrayList<Segments>();
	private List<Segments> targeting_segments = new ArrayList<Segments>();
	private List<TargetValues> target_values = new ArrayList<TargetValues>();
	private List<Concept> concepts = new ArrayList<Concept>();
	private List<StrategyConcept> strategy_concepts = new ArrayList<StrategyConcept>();
	private List<StrategyAudienceSegment> strategyAudienceSegments = new ArrayList<StrategyAudienceSegment>();
	private List<StrategyTargetingSegment> strategyTargetingSegments = new ArrayList<StrategyTargetingSegment>();
	private List<StrategyDayPart> strategyDayParts = new ArrayList<StrategyDayPart>();
	private List<StrategyTarget> strategyTarget = new ArrayList<StrategyTarget>();
	private List<BulkStrategy> bulkStrategy = new ArrayList<BulkStrategy>();

	private List<Deal> deals = new ArrayList<>();
	private List<Integer> dealIds = new ArrayList<>();

	private List<SiteList> site_lists = new ArrayList<SiteList>();

	public audSegExc getAudienceSegmentExcludeOp() {
		return audience_segment_exclude_op;
	}

	public void setAudienceSegmentExcludeOp(audSegExc audience_segment_exclude_op) {
		this.audience_segment_exclude_op = audience_segment_exclude_op;
	}

	public audSegInc getAudienceSegmentIncludeOp() {
		return audience_segment_include_op;
	}

	public void setAudienceSegmentIncludeOp(audSegInc audience_segment_include_op) {
		this.audience_segment_include_op = audience_segment_include_op;
	}

	public float getBidAggresiveness() {
		return bid_aggressiveness;
	}

	public void setBidAggresiveness(float bid_aggresiveness) {
		this.bid_aggressiveness = bid_aggresiveness;
	}

	public boolean isBidPriceIsMediaOnly() {
		return bid_price_is_media_only;
	}

	public void setBidPriceIsMediaOnly(boolean bid_price_is_media_only) {
		this.bid_price_is_media_only = bid_price_is_media_only;
	}

	public int getCampaignId() {
		return campaign_id;
	}

	public void setCampaignId(int campaign_id) {
		this.campaign_id = campaign_id;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFeatureCompatibility() {
		return feature_compatibility;
	}

	public void setFeatureCompatibility(String feature_compatibility) {
		this.feature_compatibility = feature_compatibility;
	}

	public freqInt getFrequencyInterval() {
		return frequency_interval;
	}

	public void setFrequencyInterval(freqInt frequency_interval) {
		this.frequency_interval = frequency_interval;
	}

	public boolean isFrequencyOptimization() {
		return frequency_optimization;
	}

	public void setFrequencyOptimization(boolean frequency_optimization) {
		this.frequency_optimization = frequency_optimization;
	}

	public freqType getFrequencyType() {
		return frequency_type;
	}

	public void setFrequencyType(freqType frequency_type) {
		this.frequency_type = frequency_type;
	}

	public goalType getGoalType() {
		return goal_type;
	}

	public void setGoalType(goalType goal_type) {
		this.goal_type = goal_type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getImpressionCap() {
		return impression_cap;
	}

	public void setImpressionCap(int impression_cap) {
		this.impression_cap = impression_cap;
	}

	public freqType getImpressionCapType() {
		return impression_cap_type;
	}

	public void setImpressionCapType(freqType impression_cap_type) {
		this.impression_cap_type = impression_cap_type;
	}

	public int getImpressionPacingAmount() {
		return impression_pacing_amount;
	}

	public void setImpressionPacingAmount(int impression_pacing_amount) {
		this.impression_pacing_amount = impression_pacing_amount;
	}

	public freqType getImpressionPacingType() {
		return impression_pacing_type;
	}

	public void setImpressionPacingType(freqType impression_pacing_type) {
		this.impression_pacing_type = impression_pacing_type;
	}

	public freqInt getImpressionPacingInterval() {
		return impression_pacing_interval;
	}

	public void setImpressionPacingInterval(freqInt impression_pacing_interval) {
		this.impression_pacing_interval = impression_pacing_interval;
	}

	public mediaType getMediaType() {
		return media_type;
	}

	public void setMediaType(mediaType media_type) {
		this.media_type = media_type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public pacInt getPacingInterval() {
		return pacing_interval;
	}

	public void setPacingInterval(pacInt pacing_interval) {
		this.pacing_interval = pacing_interval;
	}

	public pacType getPacingType() {
		return pacing_type;
	}

	public void setPacingType(pacType pacing_type) {
		this.pacing_type = pacing_type;
	}

	public String getPixelTargetExpr() {
		return pixel_target_expr;
	}

	public void setPixelTargetExpr(String pixel_target_expr) {
		this.pixel_target_expr = pixel_target_expr;
	}

	public boolean isRunOnAllExchanges() {
		return run_on_all_exchanges;
	}

	public void setRunOnAllExchanges(boolean run_on_all_exchanges) {
		this.run_on_all_exchanges = run_on_all_exchanges;
	}

	public boolean isRunOnAllPmp() {
		return run_on_all_pmp;
	}

	public void setRunOnAllPmp(boolean run_on_all_pmp) {
		this.run_on_all_pmp = run_on_all_pmp;
	}

	public boolean isRunOnDisplay() {
		return run_on_display;
	}

	public void setRunOnDisplay(boolean run_on_display) {
		this.run_on_display = run_on_display;
	}

	public boolean isRunOnMobile() {
		return run_on_mobile;
	}

	public void setRunOnMobile(boolean run_on_mobile) {
		this.run_on_mobile = run_on_mobile;
	}

	public boolean isRunOnStreaming() {
		return run_on_streaming;
	}

	public void setRunOnStreaming(boolean run_on_streaming) {
		this.run_on_streaming = run_on_streaming;
	}

	public boolean isSiteRestrictionTransparentUrls() {
		return site_restriction_transparent_urls;
	}

	public void setSiteRestrictionTransparentUrls(boolean site_restriction_transparent_urls) {
		this.site_restriction_transparent_urls = site_restriction_transparent_urls;
	}

	public siteSelect getSiteSelectiveness() {
		return site_selectiveness;
	}

	public void setSiteSelectiveness(siteSelect site_selectiveness) {
		this.site_selectiveness = site_selectiveness;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public supplyType getSupplyType() {
		return supply_type;
	}

	public void setSupplyType(supplyType supply_type) {
		this.supply_type = supply_type;
	}

	public tgtSegExc getTargetingSegmentExcludeOp() {
		return targeting_segment_exclude_op;
	}

	public void setTargetingSegmentExcludeOp(tgtSegExc targeting_segment_exclude_op) {
		this.targeting_segment_exclude_op = targeting_segment_exclude_op;
	}

	public tgtSegInc getTargetingSegmentIncludeOp() {
		return targeting_segment_include_op;
	}

	public void setTargetingSegmentIncludeOp(tgtSegInc targeting_segment_include_op) {
		this.targeting_segment_include_op = targeting_segment_include_op;
	}

	public type getType() {
		return type;
	}

	public void setType(type type) {
		this.type = type;
	}

	public String getUpdatedOn() {
		return updated_on;
	}

	public void setUpdatedOn(String updated_on) {
		this.updated_on = updated_on;
	}

	public boolean isUseCampaignEnd() {
		return use_campaign_end;
	}

	public void setUseCampaignEnd(boolean use_campaign_end) {
		this.use_campaign_end = use_campaign_end;
	}

	public boolean isUseCampaignStart() {
		return use_campaign_start;
	}

	public void setUseCampaignStart(boolean use_campaign_start) {
		this.use_campaign_start = use_campaign_start;
	}

	public boolean isUseMmFreq() {
		return use_mm_freq;
	}

	public void setUseMmFreq(boolean use_mm_freq) {
		this.use_mm_freq = use_mm_freq;
	}

	public boolean isUseOptimization() {
		return use_optimization;
	}

	public void setUseOptimization(boolean use_optimization) {
		this.use_optimization = use_optimization;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String getEntityname() {
		return entityName;
	}

	public String getZoneName() {
		return zone_name;
	}

	public void setZoneName(String zone_name) {
		this.zone_name = zone_name;
	}

	public List<StrategyDomain> getStrategyDomainRestrictions() {
		return strategy_domain_restrictions;
	}

	public void setStrategyDomainRestrictions(List<StrategyDomain> strategy_domain_restrictions) {
		this.strategy_domain_restrictions = strategy_domain_restrictions;
	}

	public List<Segments> getAudienceSegments() {
		return audience_segments;
	}

	public void setAudienceSegments(List<Segments> audience_segments) {
		this.audience_segments = audience_segments;
	}

	public List<TargetValues> getTargetValues() {
		return target_values;
	}

	public boolean hasTargetValuesExcept(TargetValues.codes code) {
		if (getTargetValues() == null || getTargetValues().isEmpty()) {
			return false;
		} else {
			for(TargetValues targetValues : getTargetValues()) {
				if (targetValues.getCode() != null && targetValues.getCode() != code) {
					return true;
				}
			}
			return false;
		}
	}

	public void setTargetValues(List<TargetValues> target_values) {
		this.target_values = target_values;
	}

	public Campaign getCampaign() {
		return campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}

	public List<Concept> getConcepts() {
		return concepts;
	}

	public void setConcepts(List<Concept> concepts) {
		this.concepts = concepts;
	}

	public List<StrategyConcept> getStrategyConcepts() {
		return strategy_concepts;
	}

	public void setStrategyConcepts(List<StrategyConcept> strategyConcepts) {
		this.strategy_concepts = strategyConcepts;
	}

	public Date getEndDate() {
		return end_date;
	}

	public void setEndDate(Date end_date) {
		this.end_date = end_date;
	}

	public Date getStartDate() {
		return start_date;
	}

	public void setStartDate(Date start_date) {
		this.start_date = start_date;
	}

	public int getFrequencyAmount() {
		return frequency_amount;
	}

	public void setFrequencyAmount(int frequency_amount) {
		this.frequency_amount = frequency_amount;
	}

	public ArrayList<Currency> getBudget() {
		return budget;
	}

	public void setBudget(ArrayList<Currency> budget) {
		this.budget = budget;
	}

	@Deprecated
	public void setBudget(BigDecimal budget) {
		Currency currency = new Currency();
		currency.setValue(budget);
		this.budget.add(currency);
	}

	public void setBudget(BigDecimal budget, String currency_code) {
		this.budget.clear();
		Currency currency = new Currency();
		currency.setValue(budget);
		currency.setCurrencyCode(currency_code);
		this.budget.add(currency);
	}

	public GoalValue getEffectiveGoalValue() {
		return this.effective_goal_value;
	}

	public Double getEffectiveGoalDoubleValue() {
		if (getGoalType() == goalType.spend || getGoalType() == goalType.reach
				|| getGoalType() == goalType.cpa || getGoalType() == goalType.cpc
				|| getGoalType() == goalType.roi) {
			if (this.getEffectiveGoalValue() == null || this.getEffectiveGoalValue().getAbsoluteValue() == null || this.getEffectiveGoalValue().getAbsoluteValue().isEmpty()) {
				return null;
			} else {
				return this.getEffectiveGoalValue().getAbsoluteValue().get(0).getValue();
			}
		} else if (getGoalType() == goalType.vcr || getGoalType() == goalType.ctr) {
			if (this.getEffectiveGoalValue() == null) {
				return null;
			} else {
				return this.getEffectiveGoalValue().getPercentageValue();
			}
		} else {
			throw new IllegalStateException("Goal type " + getGoalType() + " is invalid");
		}
	}

	public void setEffectiveGoalValue(double value, String currency_code) {
		if (getGoalType() == goalType.spend || getGoalType() == goalType.reach
				|| getGoalType() == goalType.cpa || getGoalType() == goalType.cpc
				|| getGoalType() == goalType.roi ) {
			GoalValue goalValue = new GoalValue();
			if (value > 0) {
				T1Cost cost = new T1Cost();
				cost.setValue(value);

				if (currency_code != null && !currency_code.isEmpty()) {
					cost.setCurrencyCode(currency_code);
				}

				ArrayList<T1Cost> arrayList = new ArrayList<>();
				arrayList.add(cost);
				goalValue.setAbsoluteValue(arrayList);
				this.effective_goal_value = goalValue;
			}
		} else {
			throw new IllegalStateException("Goal type " + getGoalType() + " is invalid");
		}
	}

	public void setEffectiveGoalValue(double value) {
		if (getGoalType() == goalType.spend || getGoalType() == goalType.reach
				|| getGoalType() == goalType.cpa || getGoalType() == goalType.cpc
				|| getGoalType() == goalType.roi) {
			setEffectiveGoalValue(value, null);
		} else if (getGoalType() == goalType.vcr || getGoalType() == goalType.ctr) {
			GoalValue goalValue = new GoalValue();
			goalValue.setPercentageValue(value);
			this.effective_goal_value = goalValue;
		} else {
			throw new IllegalStateException("Goal type " + getGoalType() + " is invalid");
		}
	}

	public GoalValue getGoalValue() {
		return this.goal_value;
	}

	public Double getGoalDoubleValue() {
		if (getGoalType() == goalType.spend || getGoalType() == goalType.reach
				|| getGoalType() == goalType.cpa || getGoalType() == goalType.cpc
				|| getGoalType() == goalType.roi) {
			if (this.getGoalValue() == null || this.getGoalValue().getAbsoluteValue() == null || this.getGoalValue().getAbsoluteValue().isEmpty()) {
				return null;
			} else {
				return this.getGoalValue().getAbsoluteValue().get(0).getValue();
			}
		} else if (getGoalType() == goalType.vcr || getGoalType() == goalType.ctr) {
			if (this.getGoalValue() == null) {
				return null;
			} else {
				return this.getGoalValue().getPercentageValue();
			}
		} else {
			throw new IllegalStateException("Goal type " + getGoalType() + " is invalid");
		}
	}

	public void setGoalValue(double value, String currency_code) {
		if (getGoalType() == goalType.spend || getGoalType() == goalType.reach
				|| getGoalType() == goalType.cpa || getGoalType() == goalType.cpc
				|| getGoalType() == goalType.roi ) {
			GoalValue goalValue = new GoalValue();
			if (value > 0) {
				T1Cost cost = new T1Cost();
				cost.setValue(value);

				if (currency_code != null && !currency_code.isEmpty()) {
					cost.setCurrencyCode(currency_code);
				}

				ArrayList<T1Cost> arrayList = new ArrayList<>();
				arrayList.add(cost);
				goalValue.setAbsoluteValue(arrayList);
				this.goal_value = goalValue;
			}
		} else {
			throw new IllegalStateException("Goal type " + getGoalType() + " is invalid");
		}
	}

	public void setGoalValue(double value) {
		if (getGoalType() == goalType.spend || getGoalType() == goalType.reach
				|| getGoalType() == goalType.cpa || getGoalType() == goalType.cpc
				|| getGoalType() == goalType.roi) {
			setGoalValue(value, null);
		} else if (getGoalType() == goalType.vcr || getGoalType() == goalType.ctr) {
			GoalValue goalValue = new GoalValue();
			goalValue.setPercentageValue(value);
			this.goal_value = goalValue;
		} else {
			throw new IllegalStateException("Goal type " + getGoalType() + " is invalid");
		}
	}

	public ArrayList<Currency> getMaxBid() {
		return max_bid;
	}

	public void setMaxBid(ArrayList<Currency> max_bid) {
		this.max_bid = max_bid;
	}

	@Deprecated
	public void setMaxBid(BigDecimal max_bid) {
		Currency currency = new Currency();
		currency.setValue(max_bid);
		this.max_bid.add(currency);
	}

	public void setMaxBid(BigDecimal max_bid, String currency_code) {
		this.max_bid.clear();
		Currency currency = new Currency();
		currency.setValue(max_bid);
		currency.setCurrencyCode(currency_code);
		this.max_bid.add(currency);
	}

	public ArrayList<Currency> getMinBid() {
		return min_bid;
	}

	public void setMinBid(ArrayList<Currency> min_bid) {
		this.min_bid = min_bid;
	}

	@Deprecated
	public void setMinBid(BigDecimal min_bid) {
		Currency currency = new Currency();
		currency.setValue(min_bid);
		this.min_bid.add(currency);
	}

	public void setMinBid(BigDecimal min_bid, String currency_code) {
		this.min_bid.clear();
		Currency currency = new Currency();
		currency.setValue(min_bid);
		currency.setCurrencyCode(currency_code);
		this.min_bid.add(currency);
	}

	public ArrayList<Currency> getPacingAmount() {
		return pacing_amount;
	}

	public void setPacingAmount(ArrayList<Currency> pacing_amount) {
		this.pacing_amount = pacing_amount;
	}

	@Deprecated
	public void setPacingAmount(BigDecimal pacing_amount) {
		Currency currency = new Currency();
		currency.setValue(pacing_amount);
		this.pacing_amount.add(currency);
	}

	public void setPacingAmount(BigDecimal pacing_amount, String currency_code) {
		this.pacing_amount.clear();
		Currency currency = new Currency();
		currency.setValue(pacing_amount);
		currency.setCurrencyCode(currency_code);
		this.pacing_amount.add(currency);
	}

	public ArrayList<Currency> getRoiTarget() {
		return roi_target;
	}

	public void setRoiTarget(ArrayList<Currency> roi_target) {
		this.roi_target = roi_target;
	}

	@Deprecated
	public void setRoiTarget(BigDecimal roi_target) {
		Currency currency = new Currency();
		currency.setValue(roi_target);
		this.roi_target.add(currency);
	}

	public void setRoiTarget(BigDecimal roi_target, String currency_code) {
		this.roi_target.clear();
		Currency currency = new Currency();
		currency.setValue(roi_target);
		currency.setCurrencyCode(currency_code);
		this.roi_target.add(currency);
	}

	public Aggregate getAggregate() {
		return aggregate;
	}

	public void setAggregate(Aggregate aggregate) {
		this.aggregate = aggregate;
	}

	public boolean isCopyStrategy() {
		return copyStrategy;
	}

	public void setCopyStrategy(boolean copyStrategy) {
		this.copyStrategy = copyStrategy;
	}

	public int getFromCampaignId() {
		return from_campaign_id;
	}

	public void setFromCampaignId(int from_campaign_id) {
		this.from_campaign_id = from_campaign_id;
	}

	public int getToCampaignId() {
		return to_campaign_id;
	}

	public void setToCampaignId(int to_campaign_id) {
		this.to_campaign_id = to_campaign_id;
	}

	public List<BulkStrategy> getBulkStrategy() {
		return bulkStrategy;
	}

	public void setBulkStrategy(List<BulkStrategy> bulkStrategy) {
		this.bulkStrategy = bulkStrategy;
	}

	public List<SiteList> getSiteLists() {
		return site_lists;
	}

	public void setSiteLists(List<SiteList> siteLists) {
		this.site_lists = siteLists;
	}

	public ArrayList<Integer> getIncludePixels() {
		return includePixels;
	}

	public void setIncludePixels(ArrayList<Integer> includePixels) {
		this.includePixels = includePixels;
	}

	public void setIncludePixels(Integer pixelId) {
		this.includePixels.add(pixelId);
	}

	public ArrayList<Integer> getExcludePixels() {
		return excludePixels;
	}

	public void setExcludePixels(ArrayList<Integer> excludePixels) {
		this.excludePixels = excludePixels;
	}

	public void setExcludePixels(Integer pixelId) {
		this.excludePixels.add(pixelId);
	}

	public List<StrategyAudienceSegment> getStrategyAudienceSegments() {
		return strategyAudienceSegments;
	}

	public void setStrategyAudienceSegments(List<StrategyAudienceSegment> strategyAudienceSegments) {
		this.strategyAudienceSegments = strategyAudienceSegments;
	}

	public List<Deal> getDeals() {
		return deals;
	}

	public void setDeals(List<Deal> deals) {
		this.deals = deals;
	}

	public List<Integer> getDealIds() {
		return dealIds;
	}

	public void setDealIds(List<Integer> dealIds) {
		this.dealIds = dealIds;
	}

	public List<Segments> getTargetingSegments() {
		return targeting_segments;
	}

	public void setTargetingSegments(List<Segments> targeting_segments) {
		this.targeting_segments = targeting_segments;
	}

	public List<StrategyTargetingSegment> getStrategyTargetingSegments() {
		return strategyTargetingSegments;
	}

	public void setStrategyTargetingSegments(List<StrategyTargetingSegment> strategyTargetingSegments) {
		this.strategyTargetingSegments = strategyTargetingSegments;
	}

	public List<StrategyDayPart> getStrategyDayParts() {
		return strategyDayParts;
	}

	public void setStrategyDayParts(List<StrategyDayPart> strategyDayParts) {
		this.strategyDayParts = strategyDayParts;
	}

	public List<StrategyTarget> getStrategyTarget() {
		return strategyTarget;
	}

	public void setStrategyTarget(List<StrategyTarget> strategyTarget) {
		this.strategyTarget = strategyTarget;
	}

	public TargetDimensions getTargetDimensions() {
		return targetDimensions;
	}

	public void setTargetDimensions(TargetDimensions targetDimensions) {
		this.targetDimensions = targetDimensions;
	}

	public StrategyTargetValues getStrategyTargetValues() {
		return strategyTargetValues;
	}

	public void setStrategyTargetValues(StrategyTargetValues strategyTargetValues) {
		this.strategyTargetValues = strategyTargetValues;
	}

	@Override
	public Form getForm() {
		return null;
	}

	@Override
	public String getUri() {
		return null;
	}

	@Override
	public Strategy clone() throws CloneNotSupportedException{
		return (Strategy) super.clone();
	}
}
