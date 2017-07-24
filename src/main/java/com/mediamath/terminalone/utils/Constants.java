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

package com.mediamath.terminalone.utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.reflect.TypeToken;
import com.mediamath.terminalone.models.AdServer;
import com.mediamath.terminalone.models.Advertiser;
import com.mediamath.terminalone.models.Agency;
import com.mediamath.terminalone.models.AtomicCreative;
import com.mediamath.terminalone.models.AudienceSegment;
import com.mediamath.terminalone.models.BudgetFlight;
import com.mediamath.terminalone.models.Campaign;
import com.mediamath.terminalone.models.CampaignCustomBrainSelection;
import com.mediamath.terminalone.models.ChildPixel;
import com.mediamath.terminalone.models.Concept;
import com.mediamath.terminalone.models.Contact;
import com.mediamath.terminalone.models.Creative;
import com.mediamath.terminalone.models.CreativeApproval;
import com.mediamath.terminalone.models.Deal;
import com.mediamath.terminalone.models.JsonResponse;
import com.mediamath.terminalone.models.Organization;
import com.mediamath.terminalone.models.Pixel;
import com.mediamath.terminalone.models.PixelProvider;
import com.mediamath.terminalone.models.PlacementSlot;
import com.mediamath.terminalone.models.Publisher;
import com.mediamath.terminalone.models.PublisherSite;
import com.mediamath.terminalone.models.SiteList;
import com.mediamath.terminalone.models.SitePlacement;
import com.mediamath.terminalone.models.Strategy;
import com.mediamath.terminalone.models.StrategyAudienceSegment;
import com.mediamath.terminalone.models.StrategyConcept;
import com.mediamath.terminalone.models.StrategyDayPart;
import com.mediamath.terminalone.models.StrategyDomain;
import com.mediamath.terminalone.models.StrategySupplySource;
import com.mediamath.terminalone.models.StrategyTarget;
import com.mediamath.terminalone.models.StrategyTargetValues;
import com.mediamath.terminalone.models.StrategyTargetingSegment;
import com.mediamath.terminalone.models.SupplySource;
import com.mediamath.terminalone.models.TargetDimension;
import com.mediamath.terminalone.models.TargetValues;
import com.mediamath.terminalone.models.TargetingSegment;
import com.mediamath.terminalone.models.User;
import com.mediamath.terminalone.models.Vendor;
import com.mediamath.terminalone.models.VendorContract;
import com.mediamath.terminalone.models.VendorDomain;
import com.mediamath.terminalone.models.VendorPixel;
import com.mediamath.terminalone.models.VendorPixelDomain;
import com.mediamath.terminalone.models.Vertical;

public final class Constants {

	private static final String REPORTS = "reports";

	private static final String PERMISSIONS = "permissions";

	private static final String CREATIVE_APPROVAL = "creative_approval";

	private static final String STRATEGY_AUDIENCE_SEGMENT = "strategy_audience_segment";
	private static final String STRATEGY_TARGETING_SEGMENT = "strategy_targeting_segment";

	private static final String STRATEGY_AUDIENCE_SEGMENTS = "strategy_audience_segments";
	private static final String STRATEGY_TARGETING_SEGMENTS = "strategy_targeting_segments";
	
	private static final String STRATEGY_TARGET_VALUES = "strategy_target_values";

	private static final String SITE_PLACEMENT = "site_placement";

	private static final String SITE_LIST_U = "site_list";
	private static final String SITE_LIST = "sitelist";

	private static final String PLACEMENT_SLOT = "placement_slot";

	private static final String PIXEL_PROVIDER = "pixel_provider";

	private static final String DEAL = "deal";

	private static final String AD_SERVER = "ad_server";

	private static final String DAY_PART = "day_part";

	private static final String AUDIENCE_SEGMENT = "audience_segment";

	private static final String TARGETING_SEGMENT = "targeting_segment";

	private static final String VERTICAL = "vertical";

	private static final String VERTICALS = "verticals";

	private static final String VENDOR_PIXEL_DOMAIN = "vendor_pixel_domain";

	private static final String VENDOR_PIXEL = "vendor_pixel";

	private static final String VENDOR_DOMAIN = "vendor_domain";

	private static final String VENDOR_CONTRACT = "vendor_contract";

	private static final String VENDOR = "vendor";

	private static final String VENDOR_PIXEL_DOMAINS = "vendor_pixel_domains";

	private static final String VENDOR_PIXELS = "vendor_pixels";

	private static final String VENDOR_DOMAINS = "vendor_domains";

	private static final String VENDOR_CONTRACTS = "vendor_contracts";

	private static final String VENDORS = "vendors";

	private static final String TRAFFIC_CONTACT = "traffic_contact";

	private static final String BILLING_CONTACT = "billing_contact";

	private static final String SALES_CONTACT = "sales_contact";

	private static final String TARGET_VALUE = "target_value";

	private static final String TARGET_DIMENSION = "target_dimension";

	private static final String TARGET_VALUES = "target_values";

	private static final String TARGET_DIMENSIONS = "target_dimensions";

	private static final String USER = "user";

	private static final String USERS = "users";

	private static final String SUPPLY_SOURCE = "supply_source";

	private static final String STRATEGY_SUPPLY_SOURCE = "strategy_supply_source";

	private static final String STRATEGY_DOMAIN_RESTRICTION = "strategy_domain_restriction";

	private static final String STRATEGY_DAY_PART = "strategydaypart";

	private static final String STRATEGY_CONCEPT = "strategy_concept";

	private static final String SUPPLY_SOURCES = "supply_sources";

	private static final String STRATEGY_SUPPLY_SOURCES = "strategy_supply_sources";

	private static final String STRATEGY_TARGET = "strategy_target";

	private static final String STRATEGY_DOMAIN_RESTRICTIONS = "strategy_domain_restrictions";

	private static final String STRATEGY_DAY_PARTS = "strategydayparts";

	private static final String STRATEGY_CONCEPTS = "strategy_concepts";

	private static final String STRATEGY_DAY_PARTS_U = "strategy_day_parts";

	private static final String STRATEGY_DAY_PART_U = "strategy_day_part";

	private static final String STRATEGY = "strategy";

	private static final String STRATEGIES = "strategies";

	private static final String SITE_PLACEMENTS = "site_placements";

	private static final String SITE_LISTS = "site_lists";

	private static final String PUBLISHER_SITE = "publisher_site";

	private static final String PUBLISHER_SITES = "publisher_sites";

	private static final String PUBLISHER = "publisher";

	private static final String PUBLISHERS = "publishers";

	private static final String PLACEMENT_SLOTS = "placement_slots";

	private static final String PIXEL_PROVIDERS = "pixel_providers";

	private static final String PIXEL_BUNDLES = "pixel_bundles";

	private static final String PIXEL_BUNDLE = "pixel_bundle";

	private static final String PIXEL = "pixel";

	private static final String PIXELS = "pixels";

	private static final String ORGANIZATION = "organization";

	private static final String ORGANIZATIONS = "organizations";

	private static final String DEALS = "deals";

	private static final String CREATIVE_APPROVALS = "creative_approvals";

	private static final String CREATIVE = "creative";

	private static final String CONCEPT = "concept";

	private static final String CREATIVES = "creatives";

	private static final String CONCEPTS = "concepts";

	private static final String CAMPAIGN = "campaign";

	private static final String CAMPAIGNS = "campaigns";
	
	private static final String CAMPAIGN_CUSTOM_BRAIN_SELECTION = "campaign_custom_brain_selection";
	private static final String CAMPAIGN_CUSTOM_BRAIN_SELECTIONS = "campaign_custom_brain_selections";
	
	private static final String AUDIENCE_SEGMENTS = "audience_segments";

	private static final String ATOMIC_CREATIVE = "atomic_creative";

	private static final String ATOMIC_CREATIVES = "atomic_creatives";

	private static final String AGENCY = "agency";

	private static final String AGENCIES = "agencies";

	private static final String ADVERTISER = "advertiser";

	private static final String ADVERTISERS = "advertisers";

	private static final String AD_SERVERS = "ad_servers";

	private static final String SITE_LISTS_FOR_STRATEGY = "site_lists_for_strategy";

	private static final String SITE_LISTS_FOR_CAMPAIGN = "site_lists_for_campaign";
	
	private static final String BUDGET_FLIGHT = "budget_flight";
	private static final String BUDGET_FLIGHTS = "budget_flights";
	

	// required for converting requested string path names to entity names when
	// collection demanded
	public static HashMap<String, String> pathToCollectionEntity = new HashMap<String, String>();

	// Required for converting entity names to path names [to form paths.]
	public static HashMap<String, String> entityPaths = new HashMap<String, String>();

	// Required for Identifying entity based on requiested path/entity string
	public static HashMap<String, String> pathToEntity = new HashMap<String, String>();

	public static HashMap<String, Integer> childPathSub = new HashMap<String, Integer>();

	public static HashMap<String, HashMap<String, Integer>> childPaths = new HashMap<String, HashMap<String, Integer>>();

	// get the type of entity.. required for parsing.
	public static HashMap<String, Type> getEntityType = new HashMap<String, Type>();

	// get the type of list of entity.. required for parsing.
	public static HashMap<String, Type> getListoFEntityType = new HashMap<String, Type>();

	static {

		getEntityType.put(AD_SERVERS, new TypeToken<JsonResponse<AdServer>>() {
		}.getType());
		getEntityType.put(AD_SERVER, new TypeToken<JsonResponse<AdServer>>() {
		}.getType());
		getEntityType.put(ADVERTISERS, new TypeToken<JsonResponse<Advertiser>>() {
		}.getType());
		getEntityType.put(ADVERTISER, new TypeToken<JsonResponse<Advertiser>>() {
		}.getType());
		getEntityType.put(AGENCIES, new TypeToken<JsonResponse<Agency>>() {
		}.getType());
		getEntityType.put(AGENCY, new TypeToken<JsonResponse<Agency>>() {
		}.getType());

		getEntityType.put(ATOMIC_CREATIVES, new TypeToken<JsonResponse<AtomicCreative>>() {
		}.getType());
		getEntityType.put(ATOMIC_CREATIVE, new TypeToken<JsonResponse<AtomicCreative>>() {
		}.getType());

		getEntityType.put(AUDIENCE_SEGMENTS, new TypeToken<JsonResponse<AudienceSegment>>() {
		}.getType());
		
		getEntityType.put(BUDGET_FLIGHT, new TypeToken<JsonResponse<BudgetFlight>>() {
		}.getType());
		
		getEntityType.put(BUDGET_FLIGHTS, new TypeToken<JsonResponse<BudgetFlight>>() {
		}.getType());

		getEntityType.put(TARGETING_SEGMENT, new TypeToken<JsonResponse<TargetingSegment>>() {
		}.getType());

		getEntityType.put(CAMPAIGNS, new TypeToken<JsonResponse<Campaign>>() {
		}.getType());
		getEntityType.put(CAMPAIGN, new TypeToken<JsonResponse<Campaign>>() {
		}.getType());
		
		getEntityType.put(CAMPAIGN_CUSTOM_BRAIN_SELECTION, new TypeToken<JsonResponse<CampaignCustomBrainSelection>>() {
		}.getType());
		
		getEntityType.put(CONCEPTS, new TypeToken<JsonResponse<Concept>>() {
		}.getType());
		getEntityType.put(CONCEPT, new TypeToken<JsonResponse<Concept>>() {
		}.getType());

		getEntityType.put(CREATIVES, new TypeToken<JsonResponse<Creative>>() {
		}.getType());
		getEntityType.put(CREATIVE, new TypeToken<JsonResponse<Creative>>() {
		}.getType());

		getEntityType.put(CREATIVE_APPROVALS, new TypeToken<JsonResponse<CreativeApproval>>() {
		}.getType());
		getEntityType.put(DEALS, new TypeToken<JsonResponse<Deal>>() {
		}.getType());
		getEntityType.put(DEAL, new TypeToken<JsonResponse<Deal>>() {
		}.getType());
		getEntityType.put(ORGANIZATIONS, new TypeToken<JsonResponse<Organization>>() {
		}.getType());
		getEntityType.put(ORGANIZATION, new TypeToken<JsonResponse<Organization>>() {
		}.getType());
		getEntityType.put(PIXELS, new TypeToken<JsonResponse<ChildPixel>>() {
		}.getType());
		getEntityType.put(PIXEL, new TypeToken<JsonResponse<ChildPixel>>() {
		}.getType());
		getEntityType.put(PIXEL_BUNDLE, new TypeToken<JsonResponse<Pixel>>() {
		}.getType());
		getEntityType.put(PIXEL_BUNDLES, new TypeToken<JsonResponse<Pixel>>() {
		}.getType());
		getEntityType.put(PIXEL_PROVIDERS, new TypeToken<JsonResponse<PixelProvider>>() {
		}.getType());
		getEntityType.put(PLACEMENT_SLOTS, new TypeToken<JsonResponse<PlacementSlot>>() {
		}.getType());

		getEntityType.put(PUBLISHERS, new TypeToken<JsonResponse<Publisher>>() {
		}.getType());
		getEntityType.put(PUBLISHER, new TypeToken<JsonResponse<Publisher>>() {
		}.getType());
		getEntityType.put(PUBLISHER_SITES, new TypeToken<JsonResponse<PublisherSite>>() {
		}.getType());
		getEntityType.put(PUBLISHER_SITE, new TypeToken<JsonResponse<PublisherSite>>() {
		}.getType());

		getEntityType.put(SITE_LISTS, new TypeToken<JsonResponse<SiteList>>() {
		}.getType());
		getEntityType.put(SITE_LIST_U, new TypeToken<JsonResponse<SiteList>>() {
		}.getType());
		getEntityType.put(SITE_LIST, new TypeToken<JsonResponse<SiteList>>() {
		}.getType());
		getEntityType.put(SITE_PLACEMENTS, new TypeToken<JsonResponse<SitePlacement>>() {
		}.getType());

		getEntityType.put(STRATEGIES, new TypeToken<JsonResponse<Strategy>>() {
		}.getType());
		getEntityType.put(STRATEGY, new TypeToken<JsonResponse<Strategy>>() {
		}.getType());
		getEntityType.put(STRATEGY_CONCEPTS, new TypeToken<JsonResponse<StrategyConcept>>() {
		}.getType());
		getEntityType.put(STRATEGY_CONCEPT, new TypeToken<JsonResponse<StrategyConcept>>() {
		}.getType());
		getEntityType.put(STRATEGY_DAY_PARTS, new TypeToken<JsonResponse<StrategyDayPart>>() {
		}.getType());
		getEntityType.put(STRATEGY_DAY_PART, new TypeToken<JsonResponse<StrategyDayPart>>() {
		}.getType());

		getEntityType.put(STRATEGY_DAY_PARTS_U, new TypeToken<JsonResponse<StrategyDayPart>>() {
		}.getType());
		getEntityType.put(STRATEGY_DAY_PART_U, new TypeToken<JsonResponse<StrategyDayPart>>() {
		}.getType());

		getEntityType.put(STRATEGY_DOMAIN_RESTRICTIONS, new TypeToken<JsonResponse<StrategyDomain>>() {
		}.getType());
		getEntityType.put(STRATEGY_DOMAIN_RESTRICTION, new TypeToken<JsonResponse<StrategyDomain>>() {
		}.getType());

		getEntityType.put(STRATEGY_SUPPLY_SOURCES, new TypeToken<JsonResponse<StrategySupplySource>>() {
		}.getType());
		getEntityType.put(STRATEGY_SUPPLY_SOURCE, new TypeToken<JsonResponse<StrategySupplySource>>() {
		}.getType());
		getEntityType.put(STRATEGY_TARGET_VALUES, new TypeToken<JsonResponse<StrategyTargetValues>>() {
		}.getType());

		getEntityType.put(STRATEGY_TARGET, new TypeToken<JsonResponse<StrategyTarget>>() {
		}.getType());

		getEntityType.put(SUPPLY_SOURCES, new TypeToken<JsonResponse<SupplySource>>() {
		}.getType());
		getEntityType.put(SUPPLY_SOURCE, new TypeToken<JsonResponse<SupplySource>>() {
		}.getType());

		getEntityType.put(USERS, new TypeToken<JsonResponse<User>>() {
		}.getType());
		getEntityType.put(USER, new TypeToken<JsonResponse<User>>() {
		}.getType());

		getEntityType.put(TARGET_DIMENSIONS, new TypeToken<JsonResponse<TargetDimension>>() {
		}.getType());
		getEntityType.put(TARGET_DIMENSION, new TypeToken<JsonResponse<TargetDimension>>() {
		}.getType());

		getEntityType.put(TARGET_VALUES, new TypeToken<JsonResponse<TargetValues>>() {
		}.getType());
		getEntityType.put(TARGET_VALUE, new TypeToken<JsonResponse<TargetValues>>() {
		}.getType());
		getEntityType.put(SALES_CONTACT, new TypeToken<JsonResponse<Contact>>() {
		}.getType());
		getEntityType.put(BILLING_CONTACT, new TypeToken<JsonResponse<Contact>>() {
		}.getType());
		getEntityType.put(TRAFFIC_CONTACT, new TypeToken<JsonResponse<Contact>>() {
		}.getType());

		getEntityType.put(VENDORS, new TypeToken<JsonResponse<Vendor>>() {
		}.getType());
		getEntityType.put(VENDOR, new TypeToken<JsonResponse<Vendor>>() {
		}.getType());

		getEntityType.put(VENDOR_CONTRACTS, new TypeToken<JsonResponse<VendorContract>>() {
		}.getType());
		getEntityType.put(VENDOR_CONTRACT, new TypeToken<JsonResponse<VendorContract>>() {
		}.getType());

		getEntityType.put(VENDOR_DOMAINS, new TypeToken<JsonResponse<VendorDomain>>() {
		}.getType());
		getEntityType.put(VENDOR_DOMAIN, new TypeToken<JsonResponse<VendorDomain>>() {
		}.getType());

		getEntityType.put(VENDOR_PIXELS, new TypeToken<JsonResponse<VendorPixel>>() {
		}.getType());
		getEntityType.put(VENDOR_PIXEL, new TypeToken<JsonResponse<VendorPixel>>() {
		}.getType());

		getEntityType.put(VENDOR_PIXEL_DOMAINS, new TypeToken<JsonResponse<VendorPixelDomain>>() {
		}.getType());
		getEntityType.put(VENDOR_PIXEL_DOMAIN, new TypeToken<JsonResponse<VendorPixelDomain>>() {
		}.getType());

		getEntityType.put(VERTICALS, new TypeToken<JsonResponse<Vertical>>() {
		}.getType());
		getEntityType.put(VERTICAL, new TypeToken<JsonResponse<Vertical>>() {
		}.getType());

		/* LIST RETURN TYPE */
		getListoFEntityType.put(AD_SERVERS, new TypeToken<JsonResponse<ArrayList<AdServer>>>() {
		}.getType());
		getListoFEntityType.put(AD_SERVER, new TypeToken<JsonResponse<ArrayList<AdServer>>>() {
		}.getType());
		getListoFEntityType.put(ADVERTISERS, new TypeToken<JsonResponse<ArrayList<Advertiser>>>() {
		}.getType());
		getListoFEntityType.put(ADVERTISER, new TypeToken<JsonResponse<ArrayList<Advertiser>>>() {
		}.getType());
		getListoFEntityType.put(AGENCIES, new TypeToken<JsonResponse<ArrayList<Agency>>>() {
		}.getType());
		getListoFEntityType.put(AGENCY, new TypeToken<JsonResponse<ArrayList<Agency>>>() {
		}.getType());
		getListoFEntityType.put(ATOMIC_CREATIVES, new TypeToken<JsonResponse<ArrayList<AtomicCreative>>>() {
		}.getType());
		getListoFEntityType.put(ATOMIC_CREATIVE, new TypeToken<JsonResponse<ArrayList<AtomicCreative>>>() {
		}.getType());
		getListoFEntityType.put(AUDIENCE_SEGMENTS, new TypeToken<JsonResponse<ArrayList<AudienceSegment>>>() {
		}.getType());
		getListoFEntityType.put(AUDIENCE_SEGMENT, new TypeToken<JsonResponse<ArrayList<AudienceSegment>>>() {
		}.getType());
		getListoFEntityType.put(BUDGET_FLIGHT, new TypeToken<JsonResponse<ArrayList<BudgetFlight>>>() {
		}.getType());
		getListoFEntityType.put(BUDGET_FLIGHTS, new TypeToken<JsonResponse<ArrayList<BudgetFlight>>>() {
		}.getType());
		getListoFEntityType.put(CAMPAIGNS, new TypeToken<JsonResponse<ArrayList<Campaign>>>() {
		}.getType());
		getListoFEntityType.put(CAMPAIGN, new TypeToken<JsonResponse<ArrayList<Campaign>>>() {
		}.getType());
		getListoFEntityType.put(CAMPAIGN_CUSTOM_BRAIN_SELECTION, new TypeToken<JsonResponse<ArrayList<CampaignCustomBrainSelection>>>() {
		}.getType());
		getListoFEntityType.put(CONCEPTS, new TypeToken<JsonResponse<ArrayList<Concept>>>() {
		}.getType());
		getListoFEntityType.put(CONCEPT, new TypeToken<JsonResponse<ArrayList<Concept>>>() {
		}.getType());
		getListoFEntityType.put(CREATIVES, new TypeToken<JsonResponse<ArrayList<Creative>>>() {
		}.getType());
		getListoFEntityType.put(CREATIVE, new TypeToken<JsonResponse<ArrayList<Creative>>>() {
		}.getType());
		getListoFEntityType.put(CREATIVE_APPROVALS, new TypeToken<JsonResponse<ArrayList<CreativeApproval>>>() {
		}.getType());
		getListoFEntityType.put(CREATIVE_APPROVAL, new TypeToken<JsonResponse<ArrayList<CreativeApproval>>>() {
		}.getType());
		getListoFEntityType.put(DEALS, new TypeToken<JsonResponse<ArrayList<Deal>>>() {
		}.getType());
		getListoFEntityType.put(DEAL, new TypeToken<JsonResponse<ArrayList<Deal>>>() {
		}.getType());
		getListoFEntityType.put(ORGANIZATIONS, new TypeToken<JsonResponse<ArrayList<Organization>>>() {
		}.getType());
		getListoFEntityType.put(ORGANIZATION, new TypeToken<JsonResponse<ArrayList<Organization>>>() {
		}.getType());
		getListoFEntityType.put(PIXELS, new TypeToken<JsonResponse<ArrayList<ChildPixel>>>() {
		}.getType());
		getListoFEntityType.put(PIXEL, new TypeToken<JsonResponse<ArrayList<ChildPixel>>>() {
		}.getType());
		getListoFEntityType.put(PIXEL_BUNDLES, new TypeToken<JsonResponse<ArrayList<Pixel>>>() {
		}.getType());
		getListoFEntityType.put(PIXEL_BUNDLE, new TypeToken<JsonResponse<ArrayList<Pixel>>>() {
		}.getType());
		getListoFEntityType.put(PIXEL_PROVIDERS, new TypeToken<JsonResponse<ArrayList<PixelProvider>>>() {
		}.getType());
		getListoFEntityType.put(PIXEL_PROVIDER, new TypeToken<JsonResponse<ArrayList<PixelProvider>>>() {
		}.getType());
		getListoFEntityType.put(PLACEMENT_SLOTS, new TypeToken<JsonResponse<ArrayList<PlacementSlot>>>() {
		}.getType());
		getListoFEntityType.put(PLACEMENT_SLOT, new TypeToken<JsonResponse<ArrayList<PlacementSlot>>>() {
		}.getType());
		getListoFEntityType.put(PUBLISHERS, new TypeToken<JsonResponse<ArrayList<Publisher>>>() {
		}.getType());
		getListoFEntityType.put(PUBLISHER, new TypeToken<JsonResponse<ArrayList<Publisher>>>() {
		}.getType());
		getListoFEntityType.put(PUBLISHER_SITES, new TypeToken<JsonResponse<ArrayList<PublisherSite>>>() {
		}.getType());
		getListoFEntityType.put(PUBLISHER_SITE, new TypeToken<JsonResponse<ArrayList<PublisherSite>>>() {
		}.getType());
		getListoFEntityType.put(SITE_LISTS, new TypeToken<JsonResponse<ArrayList<SiteList>>>() {
		}.getType());
		getListoFEntityType.put(SITE_LIST_U, new TypeToken<JsonResponse<ArrayList<SiteList>>>() {
		}.getType());
		getListoFEntityType.put(SITE_LIST, new TypeToken<JsonResponse<ArrayList<SiteList>>>() {
		}.getType());

		getListoFEntityType.put(SITE_LISTS_FOR_CAMPAIGN, new TypeToken<JsonResponse<ArrayList<SiteList>>>() {
		}.getType());

		getListoFEntityType.put(SITE_LISTS_FOR_STRATEGY, new TypeToken<JsonResponse<ArrayList<SiteList>>>() {
		}.getType());

		getListoFEntityType.put(SITE_PLACEMENTS, new TypeToken<JsonResponse<ArrayList<SitePlacement>>>() {
		}.getType());
		getListoFEntityType.put(SITE_PLACEMENT, new TypeToken<JsonResponse<ArrayList<SitePlacement>>>() {
		}.getType());
		getListoFEntityType.put(STRATEGIES, new TypeToken<JsonResponse<ArrayList<Strategy>>>() {
		}.getType());
		getListoFEntityType.put(STRATEGY, new TypeToken<JsonResponse<ArrayList<Strategy>>>() {
		}.getType());
		getListoFEntityType.put(STRATEGY_CONCEPT, new TypeToken<JsonResponse<ArrayList<StrategyConcept>>>() {
		}.getType());
		getListoFEntityType.put(STRATEGY_CONCEPTS, new TypeToken<JsonResponse<ArrayList<StrategyConcept>>>() {
		}.getType());
		getListoFEntityType.put(STRATEGY_DAY_PARTS, new TypeToken<JsonResponse<ArrayList<StrategyDayPart>>>() {
		}.getType());
		getListoFEntityType.put(STRATEGY_DAY_PART, new TypeToken<JsonResponse<ArrayList<StrategyDayPart>>>() {
		}.getType());
		getListoFEntityType.put(STRATEGY_DAY_PARTS_U, new TypeToken<JsonResponse<ArrayList<StrategyDayPart>>>() {
		}.getType());
		getListoFEntityType.put(STRATEGY_DAY_PART_U, new TypeToken<JsonResponse<ArrayList<StrategyDayPart>>>() {
		}.getType());
		getListoFEntityType.put(STRATEGY_DOMAIN_RESTRICTION, new TypeToken<JsonResponse<ArrayList<StrategyDomain>>>() {
		}.getType());
		getListoFEntityType.put(STRATEGY_DOMAIN_RESTRICTIONS, new TypeToken<JsonResponse<ArrayList<StrategyDomain>>>() {
		}.getType());
		getListoFEntityType.put(STRATEGY_SUPPLY_SOURCES,
				new TypeToken<JsonResponse<ArrayList<StrategySupplySource>>>() {
				}.getType());
		getListoFEntityType.put(STRATEGY_SUPPLY_SOURCE, new TypeToken<JsonResponse<ArrayList<StrategySupplySource>>>() {
		}.getType());
		getListoFEntityType.put(SUPPLY_SOURCES, new TypeToken<JsonResponse<ArrayList<SupplySource>>>() {
		}.getType());
		getListoFEntityType.put(SUPPLY_SOURCE, new TypeToken<JsonResponse<ArrayList<SupplySource>>>() {
		}.getType());
		getListoFEntityType.put(STRATEGY_AUDIENCE_SEGMENTS,
				new TypeToken<JsonResponse<ArrayList<StrategyAudienceSegment>>>() {
				}.getType());
		getListoFEntityType.put(STRATEGY_AUDIENCE_SEGMENT,
				new TypeToken<JsonResponse<ArrayList<StrategyAudienceSegment>>>() {
				}.getType());
		getListoFEntityType.put(STRATEGY_TARGETING_SEGMENTS,
				new TypeToken<JsonResponse<ArrayList<StrategyTargetingSegment>>>() {
				}.getType());
		getListoFEntityType.put(STRATEGY_TARGETING_SEGMENT,
				new TypeToken<JsonResponse<ArrayList<StrategyTargetingSegment>>>() {
				}.getType());
		getListoFEntityType.put(STRATEGY_TARGET, new TypeToken<JsonResponse<ArrayList<StrategyTarget>>>() {
		}.getType());
		getListoFEntityType.put(USERS, new TypeToken<JsonResponse<ArrayList<User>>>() {
		}.getType());
		getListoFEntityType.put(USER, new TypeToken<JsonResponse<ArrayList<User>>>() {
		}.getType());
		getListoFEntityType.put(TARGET_DIMENSIONS, new TypeToken<JsonResponse<ArrayList<TargetDimension>>>() {
		}.getType());
		getListoFEntityType.put(TARGET_DIMENSION, new TypeToken<JsonResponse<ArrayList<TargetDimension>>>() {
		}.getType());
		getListoFEntityType.put(TARGET_VALUES, new TypeToken<JsonResponse<ArrayList<TargetValues>>>() {
		}.getType());
		getListoFEntityType.put(TARGET_VALUE, new TypeToken<JsonResponse<ArrayList<TargetValues>>>() {
		}.getType());
		getListoFEntityType.put(VENDORS, new TypeToken<JsonResponse<ArrayList<Vendor>>>() {
		}.getType());
		getListoFEntityType.put(VENDOR, new TypeToken<JsonResponse<ArrayList<Vendor>>>() {
		}.getType());
		getListoFEntityType.put(VENDOR_CONTRACTS, new TypeToken<JsonResponse<ArrayList<VendorContract>>>() {
		}.getType());
		getListoFEntityType.put(VENDOR_CONTRACT, new TypeToken<JsonResponse<ArrayList<VendorContract>>>() {
		}.getType());
		getListoFEntityType.put(VENDOR_DOMAINS, new TypeToken<JsonResponse<ArrayList<VendorDomain>>>() {
		}.getType());
		getListoFEntityType.put(VENDOR_DOMAIN, new TypeToken<JsonResponse<ArrayList<VendorDomain>>>() {
		}.getType());
		getListoFEntityType.put(VENDOR_PIXELS, new TypeToken<JsonResponse<ArrayList<VendorPixel>>>() {
		}.getType());
		getListoFEntityType.put(VENDOR_PIXEL, new TypeToken<JsonResponse<ArrayList<VendorPixel>>>() {
		}.getType());
		getListoFEntityType.put(VENDOR_PIXEL_DOMAINS, new TypeToken<JsonResponse<ArrayList<VendorPixelDomain>>>() {
		}.getType());
		getListoFEntityType.put(VENDOR_PIXEL_DOMAIN, new TypeToken<JsonResponse<ArrayList<VendorPixelDomain>>>() {
		}.getType());
		getListoFEntityType.put(VERTICALS, new TypeToken<JsonResponse<ArrayList<Vertical>>>() {
		}.getType());
		getListoFEntityType.put(VERTICAL, new TypeToken<JsonResponse<ArrayList<Vertical>>>() {
		}.getType());

		// required for converting requested string path names to entity names
		// when collection demended
		pathToCollectionEntity.put(AD_SERVERS, "AdServer");
		pathToCollectionEntity.put(ADVERTISERS, "Advertiser");
		pathToCollectionEntity.put(AGENCIES, "Agency");
		pathToCollectionEntity.put(ATOMIC_CREATIVES, "AtomicCreative");
		pathToCollectionEntity.put(AUDIENCE_SEGMENTS, "AudienceSegment");
		pathToCollectionEntity.put(CAMPAIGNS, "Campaign");
		pathToCollectionEntity.put(CAMPAIGN_CUSTOM_BRAIN_SELECTION, "CampaignCustomBrainSelection");
		pathToCollectionEntity.put(CONCEPTS, "Concept");
		pathToCollectionEntity.put(CREATIVES, "Creative");
		pathToCollectionEntity.put(CREATIVE_APPROVALS, "CreativeApproval");
		pathToCollectionEntity.put(DEALS, "Deal");
		pathToCollectionEntity.put(DAY_PART, "DayPart");
		pathToCollectionEntity.put(ORGANIZATIONS, "Organization");
		pathToCollectionEntity.put(PIXELS, "ChildPixel");
		pathToCollectionEntity.put(PIXEL_BUNDLES, "PixelBundle");
		pathToCollectionEntity.put(PIXEL_PROVIDERS, "PixelProvider");
		pathToCollectionEntity.put(PLACEMENT_SLOTS, "PlacementSlot");
		pathToCollectionEntity.put(PUBLISHERS, "Publisher");
		pathToCollectionEntity.put(PUBLISHER_SITES, "PublisherSite");
		pathToCollectionEntity.put(SITE_LISTS, "SiteList");
		pathToCollectionEntity.put(SITE_PLACEMENTS, "SitePlacement");
		pathToCollectionEntity.put(STRATEGIES, "Strategy");
		pathToCollectionEntity.put(STRATEGY_CONCEPTS, "StrategyConcept");
		pathToCollectionEntity.put(STRATEGY_DAY_PARTS, "StrategyDayPart");
		pathToCollectionEntity.put(STRATEGY_DOMAIN_RESTRICTIONS, "StrategyDomain");
		pathToCollectionEntity.put(STRATEGY_SUPPLY_SOURCES, "StrategySupplySource");
		pathToCollectionEntity.put(SUPPLY_SOURCES, "SupplySource");
		pathToCollectionEntity.put(USERS, "User");
		pathToCollectionEntity.put(TARGET_DIMENSIONS, "TargetDimension");
		pathToCollectionEntity.put(TARGET_VALUES, "TargetValue");
		pathToCollectionEntity.put(PERMISSIONS, "Permission");
		pathToCollectionEntity.put(REPORTS, "Report");
		pathToCollectionEntity.put(VENDORS, "Vendor");
		pathToCollectionEntity.put(VENDOR_CONTRACTS, "VendorContract");
		pathToCollectionEntity.put(VENDOR_DOMAINS, "VendorDomain");
		pathToCollectionEntity.put(VENDOR_PIXELS, "VendorPixel");
		pathToCollectionEntity.put(VENDOR_PIXEL_DOMAINS, "VendorPixelDomain");
		pathToCollectionEntity.put(VERTICALS, "Vertical");

		// Required for converting entity names to path names [to form paths.]
		entityPaths.put("AdServer", AD_SERVERS);
		entityPaths.put("Advertiser", ADVERTISERS);
		entityPaths.put("Agency", AGENCIES);
		entityPaths.put("AtomicCreative", ATOMIC_CREATIVES);
		entityPaths.put("AudienceSegment", AUDIENCE_SEGMENTS);
		entityPaths.put("Campaign", CAMPAIGNS);
		entityPaths.put("CampaignCustomBrainSelection", CAMPAIGN_CUSTOM_BRAIN_SELECTION);
		entityPaths.put("Concept", CONCEPTS);
		entityPaths.put("Creative", CREATIVES);
		entityPaths.put("CreativeApproval", ATOMIC_CREATIVES);
		entityPaths.put("Deal", DEALS);
		entityPaths.put("DayPart", DAY_PART);
		entityPaths.put("Organization", ORGANIZATIONS);
		entityPaths.put("ChildPixel", PIXELS);
		entityPaths.put("PixelBundle", PIXEL_BUNDLES);
		entityPaths.put("PixelProvider", PIXEL_PROVIDERS);
		entityPaths.put("PlacementSlot", PLACEMENT_SLOTS);
		entityPaths.put("Publisher", PUBLISHERS);
		entityPaths.put("PublisherSite", PUBLISHER_SITES);
		entityPaths.put("SiteList", SITE_LISTS);
		entityPaths.put("SitePlacement", SITE_PLACEMENTS);
		entityPaths.put("Strategy", STRATEGIES);
		entityPaths.put("StrategyConcept", STRATEGY_CONCEPTS);
		entityPaths.put("StrategyDayPart", STRATEGY_DAY_PARTS_U);
		entityPaths.put("StrategyDomain", STRATEGY_DOMAIN_RESTRICTIONS);
		entityPaths.put("StrategySupplySource", STRATEGY_SUPPLY_SOURCES);
		entityPaths.put("StrategyTargetValues", STRATEGY_TARGET_VALUES);
		entityPaths.put("SupplySource", SUPPLY_SOURCES);
		entityPaths.put("User", USERS);
		entityPaths.put("TargetDimension", TARGET_DIMENSIONS);
		entityPaths.put("TargetValue", TARGET_VALUES);
		entityPaths.put("Permission", PERMISSIONS);
		entityPaths.put("Report", REPORTS);
		entityPaths.put("Vendor", VENDORS);
		entityPaths.put("VendorContract", VENDOR_CONTRACTS);
		entityPaths.put("VendorDomain", VENDOR_DOMAINS);
		entityPaths.put("VendorPixel", VENDOR_PIXELS);
		entityPaths.put("VendorPixelDomain", VENDOR_PIXEL_DOMAINS);
		entityPaths.put("Vertical", VERTICALS);

		// Required for Identifying entity based on requested path/entity string
		pathToEntity.put(AD_SERVER, "AdServer");
		pathToEntity.put(ADVERTISER, "Advertiser");
		pathToEntity.put(AGENCY, "Agency");
		pathToEntity.put(ATOMIC_CREATIVE, "AtomicCreative");
		pathToEntity.put(AUDIENCE_SEGMENT, "AudienceSegment");
		pathToEntity.put(CAMPAIGN, "Campaign");
		pathToEntity.put(CONCEPT, "Concept");
		pathToEntity.put(CREATIVE, "Creative");
		pathToEntity.put(CREATIVE_APPROVAL, "CreativeApproval");
		pathToEntity.put(DEAL, "Deal");
		pathToEntity.put(DAY_PART, "DayPart");
		pathToEntity.put(ORGANIZATION, "Organization");
		pathToEntity.put(PIXEL, "ChildPixel");
		pathToEntity.put(PIXEL_BUNDLE, "PixelBundle");
		pathToEntity.put(PIXEL_PROVIDER, "PixelProvider");
		pathToEntity.put(PLACEMENT_SLOT, "PlacementSlot");
		pathToEntity.put(PUBLISHER, "Publisher");
		pathToEntity.put(PUBLISHER_SITE, "PublisherSite");
		pathToEntity.put(SITE_LIST_U, "SiteList");
		pathToEntity.put(SITE_PLACEMENT, "SitePlacement");
		pathToEntity.put(STRATEGY, "Strategy");
		pathToEntity.put(STRATEGY_CONCEPT, "StrategyConcept");
		pathToEntity.put(STRATEGY_DAY_PART, "StrategyDayPart");
		pathToEntity.put(STRATEGY_DOMAIN_RESTRICTION, "StrategyDomain");
		pathToEntity.put(STRATEGY_SUPPLY_SOURCE, "StrategySupplySource");
		pathToEntity.put(SUPPLY_SOURCE, "SupplySource");
		pathToEntity.put(USER, "User");
		pathToEntity.put(TARGET_DIMENSION, "TargetDimension");
		pathToEntity.put(TARGET_VALUE, "TargetValue");
		pathToEntity.put("permission", "Permission");
		pathToEntity.put("report", "Report");
		pathToEntity.put(VENDOR, "Vendor");
		pathToEntity.put(VENDOR_CONTRACT, "VendorContract");
		pathToEntity.put(VENDOR_DOMAIN, "VendorDomain");
		pathToEntity.put(VENDOR_PIXEL, "VendorPixel");
		pathToEntity.put(VENDOR_PIXEL_DOMAIN, "VendorPixelDomain");
		pathToEntity.put(VERTICAL, "Vertical");

		// CHILD PATHS
		HashMap<String, Integer> subMap1 = new HashMap<String, Integer>();
		subMap1.put("acl", 0);
		childPaths.put("acl", (new HashMap<String, Integer>()));
		HashMap<String, Integer> subMap2 = new HashMap<String, Integer>();
		subMap2.put(TARGET_DIMENSIONS, 22);
		childPaths.put("audio", subMap2);
		HashMap<String, Integer> subMap3 = new HashMap<String, Integer>();
		subMap3.put(TARGET_DIMENSIONS, 4);
		childPaths.put("browser", subMap3);
		HashMap<String, Integer> subMap4 = new HashMap<String, Integer>();
		subMap4.put(TARGET_DIMENSIONS, 16);
		childPaths.put("channels", subMap4);
		HashMap<String, Integer> subMap5 = new HashMap<String, Integer>();
		subMap5.put(CONCEPTS, 0);
		childPaths.put(CONCEPTS, subMap5);

		HashMap<String, Integer> subMap6 = new HashMap<String, Integer>();
		subMap6.put(TARGET_DIMENSIONS, 2);
		childPaths.put("connection speed", subMap6);
		HashMap<String, Integer> subMap7 = new HashMap<String, Integer>();
		subMap7.put(TARGET_DIMENSIONS, 21);
		childPaths.put("content initiation", subMap7);
		HashMap<String, Integer> subMap8 = new HashMap<String, Integer>();
		subMap8.put(TARGET_DIMENSIONS, 14);
		childPaths.put("country", subMap8);
		HashMap<String, Integer> subMap9 = new HashMap<String, Integer>();
		subMap9.put("day_parts", 0);
		childPaths.put("day_parts", subMap9);
		HashMap<String, Integer> subMap10 = new HashMap<String, Integer>();
		subMap10.put(TARGET_DIMENSIONS, 24);
		childPaths.put("device", subMap10);
		HashMap<String, Integer> subMap11 = new HashMap<String, Integer>();
		subMap11.put(TARGET_DIMENSIONS, 1);
		childPaths.put("dma", subMap11);
		HashMap<String, Integer> subMap12 = new HashMap<String, Integer>();
		subMap12.put(TARGET_DIMENSIONS, 19);
		childPaths.put("fold position", subMap12);
		HashMap<String, Integer> subMap13 = new HashMap<String, Integer>();
		subMap13.put(TARGET_DIMENSIONS, 3);
		childPaths.put("isp", subMap13);
		HashMap<String, Integer> subMap14 = new HashMap<String, Integer>();
		subMap14.put(TARGET_DIMENSIONS, 20);
		childPaths.put("linear format", subMap14);
		HashMap<String, Integer> subMap15 = new HashMap<String, Integer>();
		subMap15.put(TARGET_DIMENSIONS, 8);
		childPaths.put("mathselect250", subMap15);
		HashMap<String, Integer> subMap16 = new HashMap<String, Integer>();
		subMap16.put(TARGET_DIMENSIONS, 5);
		childPaths.put("os", subMap16);
		HashMap<String, Integer> subMap17 = new HashMap<String, Integer>();
		subMap17.put(PERMISSIONS, 0);
		childPaths.put("permission", subMap17);
		HashMap<String, Integer> subMap18 = new HashMap<String, Integer>();
		subMap18.put(PERMISSIONS, 0);
		childPaths.put(PERMISSIONS, subMap18);
		HashMap<String, Integer> subMap19 = new HashMap<String, Integer>();
		subMap19.put(TARGET_DIMENSIONS, 23);
		childPaths.put("player size", subMap19);
		HashMap<String, Integer> subMap20 = new HashMap<String, Integer>();
		subMap20.put(TARGET_DIMENSIONS, 7);
		childPaths.put("region", subMap20);
		HashMap<String, Integer> subMap21 = new HashMap<String, Integer>();
		subMap21.put(TARGET_DIMENSIONS, 15);
		childPaths.put("safety", subMap21);
		HashMap<String, Integer> subMap22 = new HashMap<String, Integer>();
		subMap22.put("supplies", 0);
		childPaths.put("supplies", subMap22);
		HashMap<String, Integer> subMap23 = new HashMap<String, Integer>();
		subMap23.put("targeting_segments", 0);
		childPaths.put("targeting_segments", subMap23);
		HashMap<String, Integer> subMap24 = new HashMap<String, Integer>();
		subMap24.put(AUDIENCE_SEGMENTS, 0);
		childPaths.put(AUDIENCE_SEGMENTS, subMap24);
	}

}
