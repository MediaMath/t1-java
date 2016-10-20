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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Strategy implements T1Entity {
	
	private static final String entityName = "Strategy";
	
	public enum audSegExc {
		AND, OR
	};

	public enum audSegInc {
		AND, OR
	};

	public enum freqInt {
		hour("hour"), day("day"), week("week"), month("month"),campaign("campaign"), not_applicable("not-applcable");
		String val;
		freqInt(String s){
			val=s;
		}
	}; // should be not-applicable

	public enum freqType {
		even("even"), asap("asap"), no_limit("no-limit");
		String val;
		freqType(String s){
			val=s;
		}
	}; // should be no-limit


	public enum goalType {
		spend, reach, cpc, cpe, cpa, roi
	};

	public enum mediaType {
		DISPLAY, VIDEO
	};

	public enum pacInt {
		hour, day
	};

	public enum pacType {
		even, asap
	};

	public enum siteSelect {
		MATHSELECT_250, EXCLUDE_UGC, ALL, REDUCED
	};

	public enum supplyType {
		RTB, RMX_API, T1_RMX
	};

	public enum type {
		REM, GBO, AUD
	};

	private audSegExc audience_segment_exclude_op;
	private audSegInc audience_segment_include_op;
	private float bid_aggresiveness;
	private boolean bid_price_is_media_only;
	private ArrayList<Currency> budget = new ArrayList<Currency>();
	private int campaign_id;
	private String created_on;
	private String description;
	private ArrayList<Currency> effective_goal_value = new ArrayList<Currency>();
	private Date end_date;
	private String feature_compatibility;
	private int frequency_amount;
	private freqInt frequency_interval;
	private freqType frequency_type;
	private goalType goal_type;
	private ArrayList<Currency> goal_value = new ArrayList<Currency>();
	private int id;
	private int impression_cap;
	private ArrayList<Currency> max_bid = new ArrayList<Currency>();
	private mediaType media_type;
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
	private type type;
	private String updated_on;
	private boolean use_campaign_end;
	private boolean use_campaign_start;
	private boolean use_mm_freq;
	private boolean use_optimization;
	private int version;
	private String zone_name;
	private Aggregate aggregate;
	
	private Campaign campaign;
	
	List<StrategyDomain> strategy_domain_restrictions= new ArrayList<StrategyDomain>();
	List<Segments> audience_segments= new ArrayList<Segments>();
	List<TargetValues> target_values= new ArrayList<TargetValues>();
	List<Concept> concepts = new ArrayList<Concept>();
	List<StrategyAudienceSegment> strategyAudienceSegments = new ArrayList<StrategyAudienceSegment>();
	
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
		return bid_aggresiveness;
	}

	public void setBidAggresiveness(float bid_aggresiveness) {
		this.bid_aggresiveness = bid_aggresiveness;
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

	///
	public ArrayList<Currency> getBudget() {
		return budget;
	}

	public void setBudget(ArrayList<Currency> budget) {
		this.budget = budget;
	}
	
	public void setBudget(float budget) {
		Currency currency = new Currency();
		currency.setValue(budget);
		this.budget.add(currency);
	}

	public ArrayList<Currency> getEffectiveGoalValue() {
		return effective_goal_value;
	}

	public void setEffectiveGoalValue(ArrayList<Currency> effective_goal_value) {
		this.effective_goal_value = effective_goal_value;
	}
	
	public void setEffectiveGoalValue(float effective_goal_value) {
		Currency currency = new Currency();
		currency.setValue(effective_goal_value);
		this.effective_goal_value.add(currency);
	}

	public ArrayList<Currency> getGoalValue() {
		return goal_value;
	}

	public void setGoalValue(ArrayList<Currency> goal_value) {
		this.goal_value = goal_value;
	}
	
	public void setGoalValue(float goal_value) {
		Currency currency = new Currency();
		currency.setValue(goal_value);
		this.goal_value.add(currency);
	}

	public ArrayList<Currency> getMaxBid() {
		return max_bid;
	}

	public void setMaxBid(ArrayList<Currency> max_bid) {
		this.max_bid = max_bid;
	}
	
	public void setMaxBid(float max_bid) {
		Currency currency = new Currency();
		currency.setValue(max_bid);
		this.max_bid.add(currency);
	}

	public ArrayList<Currency> getPacingAmount() {
		return pacing_amount;
	}

	public void setPacingAmount(ArrayList<Currency> pacing_amount) {
		this.pacing_amount = pacing_amount;
	}
	
	public void setPacingAmount(float pacing_amount) {
		Currency currency = new Currency();
		currency.setValue(pacing_amount);
		this.pacing_amount.add(currency);
	}

	public ArrayList<Currency> getRoiTarget() {
		return roi_target;
	}

	public void setRoiTarget(ArrayList<Currency> roi_target) {
		this.roi_target = roi_target;
	}

	public void setRoiTarget(float roi_target) {
		Currency currency = new Currency();
		currency.setValue(roi_target);
		this.roi_target.add(currency);
	}

	public Aggregate getAggregate() {
		return aggregate;
	}

	public void setAggregate(Aggregate aggregate) {
		this.aggregate = aggregate;
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
	
	
}
