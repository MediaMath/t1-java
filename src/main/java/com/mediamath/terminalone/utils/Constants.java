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

import com.google.gson.reflect.TypeToken;
import com.mediamath.terminalone.models.AdServer;
import com.mediamath.terminalone.models.Advertiser;
import com.mediamath.terminalone.models.Agency;
import com.mediamath.terminalone.models.AtomicCreative;
import com.mediamath.terminalone.models.AudienceSegment;
import com.mediamath.terminalone.models.Campaign;
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
import com.mediamath.terminalone.models.SupplySource;
import com.mediamath.terminalone.models.TargetDimension;
import com.mediamath.terminalone.models.TargetValues;
import com.mediamath.terminalone.models.User;
import com.mediamath.terminalone.models.Vendor;
import com.mediamath.terminalone.models.VendorContract;
import com.mediamath.terminalone.models.VendorDomain;
import com.mediamath.terminalone.models.VendorPixel;
import com.mediamath.terminalone.models.VendorPixelDomain;
import com.mediamath.terminalone.models.Vertical;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;



public final class Constants {

  // required for converting requested string path names to entity names when collection demended
  public static HashMap<String, String> pathToCollectionEntity = new HashMap<String, String>();

  // Required for converting entity names to path names [to form paths.]
  public static HashMap<String, String> entityPaths = new HashMap<String, String>();

  // Required for Identifying entity based on requiested path/entity string
  public static HashMap<String, String> pathToEntity = new HashMap<String, String>();

  public static HashMap<String, Integer> childPathSub = new HashMap<String, Integer>();

  public static HashMap<String, HashMap<String, Integer>> childPaths = new HashMap<String, HashMap<String, Integer>>();

  // TODO: clean up
  // get the type of entity.. required for parsing.
  public static HashMap<String, Type> getEntityType = new HashMap<String, Type>();

  // TODO: clean up
  // get the type of list of entity.. required for parsing.
  public static HashMap<String, Type> getListoFEntityType = new HashMap<String, Type>();

  static {

    getEntityType.put("ad_servers", new TypeToken<JsonResponse<AdServer>>() {
    }.getType());
    getEntityType.put("advertisers", new TypeToken<JsonResponse<Advertiser>>() {
    }.getType());
    getEntityType.put("advertiser", new TypeToken<JsonResponse<Advertiser>>() {
    }.getType());

    getEntityType.put("agencies", new TypeToken<JsonResponse<Agency>>() {
    }.getType());
    getEntityType.put("agency", new TypeToken<JsonResponse<Agency>>() {
    }.getType());

    getEntityType.put("atomic_creatives", new TypeToken<JsonResponse<AtomicCreative>>() {
    }.getType());
    getEntityType.put("atomic_creative", new TypeToken<JsonResponse<AtomicCreative>>() {
    }.getType());

    getEntityType.put("audience_segments", new TypeToken<JsonResponse<AudienceSegment>>() {
    }.getType());

    getEntityType.put("campaigns", new TypeToken<JsonResponse<Campaign>>() {
    }.getType());
    getEntityType.put("campaign", new TypeToken<JsonResponse<Campaign>>() {
    }.getType());
    getEntityType.put("concepts", new TypeToken<JsonResponse<Concept>>() {
    }.getType());
    getEntityType.put("creatives", new TypeToken<JsonResponse<Creative>>() {
    }.getType());
    getEntityType.put("concept", new TypeToken<JsonResponse<Concept>>() {
    }.getType());
    getEntityType.put("creative", new TypeToken<JsonResponse<Creative>>() {
    }.getType());

    getEntityType.put("ad_servers", new TypeToken<JsonResponse<AdServer>>() {
    }.getType());
    getEntityType.put("advertisers", new TypeToken<JsonResponse<Advertiser>>() {
    }.getType());
    getEntityType.put("advertiser", new TypeToken<JsonResponse<Advertiser>>() {
    }.getType());
    getEntityType.put("agencies", new TypeToken<JsonResponse<Agency>>() {
    }.getType());
    getEntityType.put("agency", new TypeToken<JsonResponse<Agency>>() {
    }.getType());
    getEntityType.put("atomic_creatives", new TypeToken<JsonResponse<AtomicCreative>>() {
    }.getType());
    getEntityType.put("audience_segments", new TypeToken<JsonResponse<AudienceSegment>>() {
    }.getType());
    getEntityType.put("creative_approvals", new TypeToken<JsonResponse<CreativeApproval>>() {
    }.getType());
    getEntityType.put("deals", new TypeToken<JsonResponse<Deal>>() {
    }.getType());
    getEntityType.put("organizations", new TypeToken<JsonResponse<Organization>>() {
    }.getType());
    getEntityType.put("organization", new TypeToken<JsonResponse<Organization>>() {
    }.getType());
    getEntityType.put("pixels", new TypeToken<JsonResponse<ChildPixel>>() {
    }.getType());
    getEntityType.put("pixel", new TypeToken<JsonResponse<ChildPixel>>() {
    }.getType());
    getEntityType.put("pixel_bundle", new TypeToken<JsonResponse<Pixel>>() {
    }.getType());
    getEntityType.put("pixel_bundles", new TypeToken<JsonResponse<Pixel>>() {
    }.getType());
    getEntityType.put("pixel_providers", new TypeToken<JsonResponse<PixelProvider>>() {
    }.getType());
    getEntityType.put("placement_slots", new TypeToken<JsonResponse<PlacementSlot>>() {
    }.getType());

    getEntityType.put("publishers", new TypeToken<JsonResponse<Publisher>>() {
    }.getType());
    getEntityType.put("publisher", new TypeToken<JsonResponse<Publisher>>() {
    }.getType());
    getEntityType.put("publisher_sites", new TypeToken<JsonResponse<PublisherSite>>() {
    }.getType());
    getEntityType.put("publisher_site", new TypeToken<JsonResponse<PublisherSite>>() {
    }.getType());

    // getEntityType.put("site_lists", new TypeToken<JsonResponse<SiteList>>(){}.getType());
    // getEntityType.put("site_placements", new
    // TypeToken<JsonResponse<SitePlacement>>(){}.getType());

    getEntityType.put("strategies", new TypeToken<JsonResponse<Strategy>>() {
    }.getType());
    getEntityType.put("strategy", new TypeToken<JsonResponse<Strategy>>() {
    }.getType());

    getEntityType.put("strategy_concepts", new TypeToken<JsonResponse<StrategyConcept>>() {
    }.getType());
    getEntityType.put("strategy_day_parts", new TypeToken<JsonResponse<StrategyDayPart>>() {
    }.getType());
    getEntityType.put("strategy_domain_restrictions",
        new TypeToken<JsonResponse<StrategyDomain>>() {
        }.getType());
    getEntityType.put("strategy_supply_sources",
        new TypeToken<JsonResponse<StrategySupplySource>>() {
        }.getType());
    getEntityType.put("supply_sources", new TypeToken<JsonResponse<SupplySource>>() {
    }.getType());
    getEntityType.put("strategy_concept", new TypeToken<JsonResponse<StrategyConcept>>() {
    }.getType());
    getEntityType.put("strategy_day_part", new TypeToken<JsonResponse<StrategyDayPart>>() {
    }.getType());
    getEntityType.put("strategy_domain_restriction", new TypeToken<JsonResponse<StrategyDomain>>() {
    }.getType());
    getEntityType.put("strategy_supply_source",
        new TypeToken<JsonResponse<StrategySupplySource>>() {
        }.getType());
    getEntityType.put("supply_source", new TypeToken<JsonResponse<SupplySource>>() {
    }.getType());

    getEntityType.put("users", new TypeToken<JsonResponse<User>>() {
    }.getType());
    getEntityType.put("user", new TypeToken<JsonResponse<User>>() {
    }.getType());
    getEntityType.put("target_dimensions", new TypeToken<JsonResponse<TargetDimension>>() {
    }.getType());
    getEntityType.put("target_values", new TypeToken<JsonResponse<TargetValues>>() {
    }.getType());
    getEntityType.put("target_dimension", new TypeToken<JsonResponse<TargetDimension>>() {
    }.getType());
    getEntityType.put("target_value", new TypeToken<JsonResponse<TargetValues>>() {
    }.getType());
    getEntityType.put("sales_contact", new TypeToken<JsonResponse<Contact>>() {
    }.getType());
    getEntityType.put("billing_contact", new TypeToken<JsonResponse<Contact>>() {
    }.getType());
    getEntityType.put("traffic_contact", new TypeToken<JsonResponse<Contact>>() {
    }.getType());
    // getEntityType.put("permissions", "Permission");
    // getEntityType.put("reports", "Report");
    getEntityType.put("vendors", new TypeToken<JsonResponse<Vendor>>() {
    }.getType());
    getEntityType.put("vendor_contracts", new TypeToken<JsonResponse<VendorContract>>() {
    }.getType());
    getEntityType.put("vendor_domains", new TypeToken<JsonResponse<VendorDomain>>() {
    }.getType());
    getEntityType.put("vendor_pixels", new TypeToken<JsonResponse<VendorPixel>>() {
    }.getType());
    getEntityType.put("vendor_pixel_domains", new TypeToken<JsonResponse<VendorPixelDomain>>() {
    }.getType());
    getEntityType.put("vendor", new TypeToken<JsonResponse<Vendor>>() {
    }.getType());
    getEntityType.put("vendor_contract", new TypeToken<JsonResponse<VendorContract>>() {
    }.getType());
    getEntityType.put("vendor_domain", new TypeToken<JsonResponse<VendorDomain>>() {
    }.getType());
    getEntityType.put("vendor_pixel", new TypeToken<JsonResponse<VendorPixel>>() {
    }.getType());
    getEntityType.put("vendor_pixel_domain", new TypeToken<JsonResponse<VendorPixelDomain>>() {
    }.getType());
    getEntityType.put("verticals", new TypeToken<JsonResponse<Vertical>>() {
    }.getType());
    getEntityType.put("vertical", new TypeToken<JsonResponse<Vertical>>() {
    }.getType());

    /* LIST RETURN TYPE */
    getListoFEntityType.put("atomic_creatives",
        new TypeToken<JsonResponse<ArrayList<AtomicCreative>>>() {
        }.getType());
    getListoFEntityType.put("atomic_creative",
        new TypeToken<JsonResponse<ArrayList<AtomicCreative>>>() {
        }.getType());
    getListoFEntityType.put("audience_segments",
        new TypeToken<JsonResponse<ArrayList<AudienceSegment>>>() {
        }.getType());
    getListoFEntityType.put("audience_segment",
        new TypeToken<JsonResponse<ArrayList<AudienceSegment>>>() {
        }.getType());
    getListoFEntityType.put("campaigns", new TypeToken<JsonResponse<ArrayList<Campaign>>>() {
    }.getType());
    getListoFEntityType.put("campaign", new TypeToken<JsonResponse<ArrayList<Campaign>>>() {
    }.getType());
    getListoFEntityType.put("ad_servers", new TypeToken<JsonResponse<ArrayList<AdServer>>>() {
    }.getType());
    getListoFEntityType.put("ad_server", new TypeToken<JsonResponse<ArrayList<AdServer>>>() {
    }.getType());
    getListoFEntityType.put("advertisers", new TypeToken<JsonResponse<ArrayList<Advertiser>>>() {
    }.getType());
    getListoFEntityType.put("advertiser", new TypeToken<JsonResponse<ArrayList<Advertiser>>>() {
    }.getType());
    getListoFEntityType.put("agencies", new TypeToken<JsonResponse<ArrayList<Agency>>>() {
    }.getType());
    getListoFEntityType.put("agency", new TypeToken<JsonResponse<ArrayList<Agency>>>() {
    }.getType());
    getListoFEntityType.put("concepts", new TypeToken<JsonResponse<ArrayList<Concept>>>() {
    }.getType());
    getListoFEntityType.put("concept", new TypeToken<JsonResponse<ArrayList<Concept>>>() {
    }.getType());
    getListoFEntityType.put("deals", new TypeToken<JsonResponse<ArrayList<Deal>>>() {
    }.getType());
    getListoFEntityType.put("deal", new TypeToken<JsonResponse<ArrayList<Deal>>>() {
    }.getType());
    getListoFEntityType.put("organizations",
        new TypeToken<JsonResponse<ArrayList<Organization>>>() {
        }.getType());
    getListoFEntityType.put("organization", new TypeToken<JsonResponse<ArrayList<Organization>>>() {
    }.getType());
    getListoFEntityType.put("pixels", new TypeToken<JsonResponse<ArrayList<ChildPixel>>>() {
    }.getType());
    getListoFEntityType.put("pixel", new TypeToken<JsonResponse<ArrayList<ChildPixel>>>() {
    }.getType());
    getListoFEntityType.put("creatives", new TypeToken<JsonResponse<ArrayList<Creative>>>() {
    }.getType());
    getListoFEntityType.put("creative", new TypeToken<JsonResponse<ArrayList<Creative>>>() {
    }.getType());
    getListoFEntityType.put("atomic_creatives",
        new TypeToken<JsonResponse<ArrayList<AtomicCreative>>>() {
        }.getType());
    getListoFEntityType.put("atomic_creative",
        new TypeToken<JsonResponse<ArrayList<AtomicCreative>>>() {
        }.getType());
    getListoFEntityType.put("audience_segments",
        new TypeToken<JsonResponse<ArrayList<AudienceSegment>>>() {
        }.getType());
    getListoFEntityType.put("audience_segment",
        new TypeToken<JsonResponse<ArrayList<AudienceSegment>>>() {
        }.getType());
    getListoFEntityType.put("creative_approvals",
        new TypeToken<JsonResponse<ArrayList<CreativeApproval>>>() {
        }.getType());
    getListoFEntityType.put("creative_approval",
        new TypeToken<JsonResponse<ArrayList<CreativeApproval>>>() {
        }.getType());
    getListoFEntityType.put("pixel_bundles", new TypeToken<JsonResponse<ArrayList<Pixel>>>() {
    }.getType());
    getListoFEntityType.put("pixel_bundle", new TypeToken<JsonResponse<ArrayList<Pixel>>>() {
    }.getType());
    getListoFEntityType.put("pixel_providers",
        new TypeToken<JsonResponse<ArrayList<PixelProvider>>>() {
        }.getType());
    getListoFEntityType.put("pixel_provider",
        new TypeToken<JsonResponse<ArrayList<PixelProvider>>>() {
        }.getType());
    getListoFEntityType.put("placement_slots",
        new TypeToken<JsonResponse<ArrayList<PlacementSlot>>>() {
        }.getType());
    getListoFEntityType.put("placement_slot",
        new TypeToken<JsonResponse<ArrayList<PlacementSlot>>>() {
        }.getType());
    getListoFEntityType.put("publishers", new TypeToken<JsonResponse<ArrayList<Publisher>>>() {
    }.getType());
    getListoFEntityType.put("publisher", new TypeToken<JsonResponse<ArrayList<Publisher>>>() {
    }.getType());
    getListoFEntityType.put("publisher_sites",
        new TypeToken<JsonResponse<ArrayList<PublisherSite>>>() {
        }.getType());
    getListoFEntityType.put("publisher_site",
        new TypeToken<JsonResponse<ArrayList<PublisherSite>>>() {
        }.getType());
    getListoFEntityType.put("site_lists", new TypeToken<JsonResponse<ArrayList<SiteList>>>() {
    }.getType());
    getListoFEntityType.put("site_list", new TypeToken<JsonResponse<ArrayList<SiteList>>>() {
    }.getType());
    getListoFEntityType.put("site_placements",
        new TypeToken<JsonResponse<ArrayList<SitePlacement>>>() {
        }.getType());
    getListoFEntityType.put("site_placement",
        new TypeToken<JsonResponse<ArrayList<SitePlacement>>>() {
        }.getType());
    getListoFEntityType.put("strategies", new TypeToken<JsonResponse<ArrayList<Strategy>>>() {
    }.getType());
    getListoFEntityType.put("strategy", new TypeToken<JsonResponse<ArrayList<Strategy>>>() {
    }.getType());
    getListoFEntityType.put("strategy_concepts",
        new TypeToken<JsonResponse<ArrayList<StrategyConcept>>>() {
        }.getType());
    getListoFEntityType.put("strategy_day_parts",
        new TypeToken<JsonResponse<ArrayList<StrategyDayPart>>>() {
        }.getType());
    getListoFEntityType.put("strategy_domain_restrictions",
        new TypeToken<JsonResponse<ArrayList<StrategyDomain>>>() {
        }.getType());
    getListoFEntityType.put("strategy_supply_sources",
        new TypeToken<JsonResponse<ArrayList<StrategySupplySource>>>() {
        }.getType());
    getListoFEntityType.put("supply_sources",
        new TypeToken<JsonResponse<ArrayList<SupplySource>>>() {
        }.getType());
    getListoFEntityType.put("strategy_concept",
        new TypeToken<JsonResponse<ArrayList<StrategyConcept>>>() {
        }.getType());
    getListoFEntityType.put("strategy_day_part",
        new TypeToken<JsonResponse<ArrayList<StrategyDayPart>>>() {
        }.getType());
    getListoFEntityType.put("strategy_domain_restriction",
        new TypeToken<JsonResponse<ArrayList<StrategyDomain>>>() {
        }.getType());
    getListoFEntityType.put("strategy_supply_source",
        new TypeToken<JsonResponse<ArrayList<StrategySupplySource>>>() {
        }.getType());
    getListoFEntityType.put("supply_source",
        new TypeToken<JsonResponse<ArrayList<SupplySource>>>() {
        }.getType());
    getListoFEntityType.put("strategy_audience_segments",
        new TypeToken<JsonResponse<ArrayList<StrategyAudienceSegment>>>() {
        }.getType());
    getListoFEntityType.put("strategy_audience_segment",
        new TypeToken<JsonResponse<ArrayList<StrategyAudienceSegment>>>() {
        }.getType());
    getListoFEntityType.put("users", new TypeToken<JsonResponse<ArrayList<User>>>() {
    }.getType());
    getListoFEntityType.put("user", new TypeToken<JsonResponse<ArrayList<User>>>() {
    }.getType());

    getListoFEntityType.put("target_dimensions",
        new TypeToken<JsonResponse<ArrayList<TargetDimension>>>() {
        }.getType());
    getListoFEntityType.put("target_dimension",
        new TypeToken<JsonResponse<ArrayList<TargetDimension>>>() {
        }.getType());
    getListoFEntityType.put("target_values",
        new TypeToken<JsonResponse<ArrayList<TargetValues>>>() {
        }.getType());
    getListoFEntityType.put("target_value", new TypeToken<JsonResponse<ArrayList<TargetValues>>>() {
    }.getType());
    // getListoFEntityType.put("permissions", "Permission");
    // getListoFEntityType.put("reports", "Report");
    getListoFEntityType.put("vendors", new TypeToken<JsonResponse<ArrayList<Vendor>>>() {
    }.getType());
    getListoFEntityType.put("vendor", new TypeToken<JsonResponse<ArrayList<Vendor>>>() {
    }.getType());
    getListoFEntityType.put("vendor_contracts",
        new TypeToken<JsonResponse<ArrayList<VendorContract>>>() {
        }.getType());
    getListoFEntityType.put("vendor_contract",
        new TypeToken<JsonResponse<ArrayList<VendorContract>>>() {
        }.getType());
    getListoFEntityType.put("vendor_domains",
        new TypeToken<JsonResponse<ArrayList<VendorDomain>>>() {
        }.getType());
    getListoFEntityType.put("vendor_domain",
        new TypeToken<JsonResponse<ArrayList<VendorDomain>>>() {
        }.getType());
    getListoFEntityType.put("vendor_pixels", new TypeToken<JsonResponse<ArrayList<VendorPixel>>>() {
    }.getType());
    getListoFEntityType.put("vendor_pixel", new TypeToken<JsonResponse<ArrayList<VendorPixel>>>() {
    }.getType());
    getListoFEntityType.put("vendor_pixel_domains",
        new TypeToken<JsonResponse<ArrayList<VendorPixelDomain>>>() {
        }.getType());
    getListoFEntityType.put("vendor_pixel_domain",
        new TypeToken<JsonResponse<ArrayList<VendorPixelDomain>>>() {
        }.getType());
    getListoFEntityType.put("verticals", new TypeToken<JsonResponse<ArrayList<Vertical>>>() {
    }.getType());
    getListoFEntityType.put("vertical", new TypeToken<JsonResponse<ArrayList<Vertical>>>() {
    }.getType());

    // required for converting requested string path names to entity names when collection demended
    pathToCollectionEntity.put("ad_servers", "AdServer");
    pathToCollectionEntity.put("advertisers", "Advertiser");
    pathToCollectionEntity.put("agencies", "Agency");
    pathToCollectionEntity.put("atomic_creatives", "AtomicCreative");
    pathToCollectionEntity.put("audience_segments", "AudienceSegment");
    pathToCollectionEntity.put("campaigns", "Campaign");
    pathToCollectionEntity.put("concepts", "Concept");
    pathToCollectionEntity.put("creatives", "Creative");
    pathToCollectionEntity.put("creative_approvals", "CreativeApproval");
    pathToCollectionEntity.put("deals", "Deal");
    pathToCollectionEntity.put("organizations", "Organization");
    pathToCollectionEntity.put("pixels", "ChildPixel");
    pathToCollectionEntity.put("pixel_bundles", "PixelBundle");
    pathToCollectionEntity.put("pixel_providers", "PixelProvider");
    pathToCollectionEntity.put("placement_slots", "PlacementSlot");
    pathToCollectionEntity.put("publishers", "Publisher");
    pathToCollectionEntity.put("publisher_sites", "PublisherSite");
    pathToCollectionEntity.put("site_lists", "SiteList");
    pathToCollectionEntity.put("site_placements", "SitePlacement");
    pathToCollectionEntity.put("strategies", "Strategy");
    pathToCollectionEntity.put("strategy_concepts", "StrategyConcept");
    pathToCollectionEntity.put("strategy_day_parts", "StrategyDayPart");
    pathToCollectionEntity.put("strategy_domain_restrictions", "StrategyDomain");
    pathToCollectionEntity.put("strategy_supply_sources", "StrategySupplySource");
    pathToCollectionEntity.put("supply_sources", "SupplySource");
    pathToCollectionEntity.put("users", "User");
    pathToCollectionEntity.put("target_dimensions", "TargetDimension");
    pathToCollectionEntity.put("target_values", "TargetValue");
    pathToCollectionEntity.put("permissions", "Permission");
    pathToCollectionEntity.put("reports", "Report");
    pathToCollectionEntity.put("vendors", "Vendor");
    pathToCollectionEntity.put("vendor_contracts", "VendorContract");
    pathToCollectionEntity.put("vendor_domains", "VendorDomain");
    pathToCollectionEntity.put("vendor_pixels", "VendorPixel");
    pathToCollectionEntity.put("vendor_pixel_domains", "VendorPixelDomain");
    pathToCollectionEntity.put("verticals", "Vertical");

    // Required for converting entity names to path names [to form paths.]
    entityPaths.put("AdServer", "ad_servers");
    entityPaths.put("Advertiser", "advertisers");
    entityPaths.put("Agency", "agencies");
    entityPaths.put("AtomicCreative", "atomic_creatives");
    entityPaths.put("AudienceSegment", "audience_segments");
    entityPaths.put("Campaign", "campaigns");
    entityPaths.put("Concept", "concepts");
    entityPaths.put("Creative", "creatives");
    entityPaths.put("CreativeApproval", "atomic_creatives");
    entityPaths.put("Deal", "deals");
    entityPaths.put("Organization", "organizations");
    entityPaths.put("ChildPixel", "pixels");
    entityPaths.put("PixelBundle", "pixel_bundles");
    entityPaths.put("PixelProvider", "pixel_providers");
    entityPaths.put("PlacementSlot", "placement_slots");
    entityPaths.put("Publisher", "publishers");
    entityPaths.put("PublisherSite", "publisher_sites");
    entityPaths.put("SiteList", "site_lists");
    entityPaths.put("SitePlacement", "site_placements");
    entityPaths.put("Strategy", "strategies");
    entityPaths.put("StrategyConcept", "strategy_concepts");
    entityPaths.put("StrategyDayPart", "strategy_day_parts");
    entityPaths.put("StrategyDomain", "strategy_domain_restrictions");
    entityPaths.put("StrategySupplySource", "strategy_supply_sources");
    entityPaths.put("SupplySource", "supply_sources");
    entityPaths.put("User", "users");
    entityPaths.put("TargetDimension", "target_dimensions");
    entityPaths.put("TargetValue", "target_values");
    entityPaths.put("Permission", "permissions");
    entityPaths.put("Report", "reports");
    entityPaths.put("Vendor", "vendors");
    entityPaths.put("VendorContract", "vendor_contracts");
    entityPaths.put("VendorDomain", "vendor_domains");
    entityPaths.put("VendorPixel", "vendor_pixels");
    entityPaths.put("VendorPixelDomain", "vendor_pixel_domains");
    entityPaths.put("Vertical", "verticals");

    // Required for Identifying entity based on requiested path/entity string
    pathToEntity.put("ad_server", "AdServer");
    pathToEntity.put("advertiser", "Advertiser");
    pathToEntity.put("agency", "Agency");
    pathToEntity.put("atomic_creative", "AtomicCreative");
    pathToEntity.put("audience_segment", "AudienceSegment");
    pathToEntity.put("campaign", "Campaign");
    pathToEntity.put("concept", "Concept");
    pathToEntity.put("creative", "Creative");
    pathToEntity.put("creative_approval", "CreativeApproval");
    pathToEntity.put("deal", "Deal");
    pathToEntity.put("organization", "Organization");
    pathToEntity.put("pixel", "ChildPixel");
    pathToEntity.put("pixel_bundle", "PixelBundle");
    pathToEntity.put("pixel_provider", "PixelProvider");
    pathToEntity.put("placement_slot", "PlacementSlot");
    pathToEntity.put("publisher", "Publisher");
    pathToEntity.put("publisher_site", "PublisherSite");
    pathToEntity.put("site_list", "SiteList");
    pathToEntity.put("site_placement", "SitePlacement");
    pathToEntity.put("strategy", "Strategy");
    pathToEntity.put("strategy_concept", "StrategyConcept");
    pathToEntity.put("strategy_day_part", "StrategyDayPart");
    pathToEntity.put("strategy_domain_restriction", "StrategyDomain");
    pathToEntity.put("strategy_supply_source", "StrategySupplySource");
    pathToEntity.put("supply_source", "SupplySource");
    pathToEntity.put("user", "User");
    pathToEntity.put("target_dimension", "TargetDimension");
    pathToEntity.put("target_value", "TargetValue");
    pathToEntity.put("permission", "Permission");
    pathToEntity.put("report", "Report");
    pathToEntity.put("vendor", "Vendor");
    pathToEntity.put("vendor_contract", "VendorContract");
    pathToEntity.put("vendor_domain", "VendorDomain");
    pathToEntity.put("vendor_pixel", "VendorPixel");
    pathToEntity.put("vendor_pixel_domain", "VendorPixelDomain");
    pathToEntity.put("vertical", "Vertical");

    // CHILD PATHS
    HashMap<String, Integer> subMap1 = new HashMap<String, Integer>();
    subMap1.put("acl", 0);
    childPaths.put("acl", (new HashMap<String, Integer>()));
    HashMap<String, Integer> subMap2 = new HashMap<String, Integer>();
    subMap2.put("target_dimensions", 22);
    childPaths.put("audio", subMap2);
    HashMap<String, Integer> subMap3 = new HashMap<String, Integer>();
    subMap3.put("target_dimensions", 4);
    childPaths.put("browser", subMap3);
    HashMap<String, Integer> subMap4 = new HashMap<String, Integer>();
    subMap4.put("target_dimensions", 16);
    childPaths.put("channels", subMap4);
    HashMap<String, Integer> subMap5 = new HashMap<String, Integer>();
    subMap5.put("concepts", 0);
    childPaths.put("concepts", subMap5);

    HashMap<String, Integer> subMap6 = new HashMap<String, Integer>();
    subMap6.put("target_dimensions", 2);
    childPaths.put("connection speed", subMap6);
    HashMap<String, Integer> subMap7 = new HashMap<String, Integer>();
    subMap7.put("target_dimensions", 21);
    childPaths.put("content initiation", subMap7);
    HashMap<String, Integer> subMap8 = new HashMap<String, Integer>();
    subMap8.put("target_dimensions", 14);
    childPaths.put("country", subMap8);
    HashMap<String, Integer> subMap9 = new HashMap<String, Integer>();
    subMap9.put("day_parts", 0);
    childPaths.put("day_parts", subMap9);
    HashMap<String, Integer> subMap10 = new HashMap<String, Integer>();
    subMap10.put("target_dimensions", 24);
    childPaths.put("device", subMap10);
    HashMap<String, Integer> subMap11 = new HashMap<String, Integer>();
    subMap11.put("target_dimensions", 1);
    childPaths.put("dma", subMap11);
    HashMap<String, Integer> subMap12 = new HashMap<String, Integer>();
    subMap12.put("target_dimensions", 19);
    childPaths.put("fold position", subMap12);
    HashMap<String, Integer> subMap13 = new HashMap<String, Integer>();
    subMap13.put("target_dimensions", 3);
    childPaths.put("isp", subMap13);
    HashMap<String, Integer> subMap14 = new HashMap<String, Integer>();
    subMap14.put("target_dimensions", 20);
    childPaths.put("linear format", subMap14);
    HashMap<String, Integer> subMap15 = new HashMap<String, Integer>();
    subMap15.put("target_dimensions", 8);
    childPaths.put("mathselect250", subMap15);
    HashMap<String, Integer> subMap16 = new HashMap<String, Integer>();
    subMap16.put("target_dimensions", 5);
    childPaths.put("os", subMap16);
    HashMap<String, Integer> subMap17 = new HashMap<String, Integer>();
    subMap17.put("permissions", 0);
    childPaths.put("permission", subMap17);
    HashMap<String, Integer> subMap18 = new HashMap<String, Integer>();
    subMap18.put("permissions", 0);
    childPaths.put("permissions", subMap18);
    HashMap<String, Integer> subMap19 = new HashMap<String, Integer>();
    subMap19.put("target_dimensions", 23);
    childPaths.put("player size", subMap19);
    HashMap<String, Integer> subMap20 = new HashMap<String, Integer>();
    subMap20.put("target_dimensions", 7);
    childPaths.put("region", subMap20);
    HashMap<String, Integer> subMap21 = new HashMap<String, Integer>();
    subMap21.put("target_dimensions", 15);
    childPaths.put("safety", subMap21);
    HashMap<String, Integer> subMap22 = new HashMap<String, Integer>();
    subMap22.put("supplies", 0);
    childPaths.put("supplies", subMap22);
    HashMap<String, Integer> subMap23 = new HashMap<String, Integer>();
    subMap23.put("targeting_segments", 0);
    childPaths.put("targeting_segments", subMap23);
    HashMap<String, Integer> subMap24 = new HashMap<String, Integer>();
    subMap24.put("audience_segments", 0);
    childPaths.put("audience_segments", subMap24);

  }

}
