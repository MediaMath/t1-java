package com.mediamath.jterminalone.utils;

import java.util.HashMap;
public final class Constants {

	public static HashMap<String, String> pathToCollectionEntity = new HashMap<String,String>();
	public static HashMap<String, String> entityPaths = new HashMap<String,String>();
	public static HashMap<String, String> pathToEntity = new HashMap<String,String>();
	public static HashMap<String, Integer> childPathSub = new HashMap<String, Integer>();
	public static HashMap<String, HashMap<String, Integer>> childPaths = new HashMap<String, HashMap<String, Integer>>();
	
	static{
		
		//required for converting requested string path names to entity names when collection demended
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
	    
	    //Required for converting entity names to path names
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
	    
	    //Required for Identifying entity based on requiested path/entity string
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
		

	    childPathSub.put("acl", 0);
	    childPaths.put("acl", childPathSub);
	    childPathSub.clear();
	    childPathSub.put("target_dimensions",22);
	    childPaths.put("audio", childPathSub);
	    childPathSub.clear(); childPathSub.put("target_dimensions",4);
	    childPaths.put("browser", childPathSub);
	    childPathSub.clear(); childPathSub.put("target_dimensions",16);
	    childPaths.put("channels", childPathSub);
	    childPathSub.clear(); childPathSub.put("concepts",0);
	    childPaths.put("concepts", childPathSub);
	    childPathSub.clear(); childPathSub.put("target_dimensions",2);
	    childPaths.put("connection speed", childPathSub);
	    childPathSub.clear(); childPathSub.put("target_dimensions",21);
	    childPaths.put("content initiation", childPathSub);
	    childPathSub.clear(); childPathSub.put("target_dimensions",14);
	    childPaths.put("country", childPathSub);
	    childPathSub.clear(); childPathSub.put("day_parts",0);
	    childPaths.put("day_parts", childPathSub);
	    childPathSub.clear(); childPathSub.put("target_dimensions",24);
	    childPaths.put("device",childPathSub);
	    childPathSub.clear(); childPathSub.put("target_dimensions",1);
	    childPaths.put("dma", childPathSub);
	    childPathSub.clear(); childPathSub.put("target_dimensions",19);
	    childPaths.put("fold position", childPathSub);
	    childPathSub.clear(); childPathSub.put("target_dimensions",3);
	    childPaths.put("isp", childPathSub);
	    childPathSub.clear(); childPathSub.put("target_dimensions",20);
	    childPaths.put("linear format", childPathSub);
	    childPathSub.clear(); childPathSub.put("target_dimensions",8);
	    childPaths.put("mathselect250", childPathSub);
	    childPathSub.clear(); childPathSub.put("target_dimensions",5);
	    childPaths.put("os", childPathSub);
	    childPathSub.clear(); childPathSub.put("permissions",0);
	    childPaths.put("permission", childPathSub);
	    childPathSub.clear(); childPathSub.put("permissions",0);
	    childPaths.put("permissions", childPathSub);
	    childPathSub.clear(); childPathSub.put("target_dimensions",23);
	    childPaths.put("player size", childPathSub);
	    childPathSub.clear(); childPathSub.put("target_dimensions",7);
	    childPaths.put("region", childPathSub);
	    childPathSub.clear(); childPathSub.put("target_dimensions",15);
	    childPaths.put("safety", childPathSub);
	    childPathSub.clear(); childPathSub.put("supplies",0);
	    childPaths.put("supplies", childPathSub);
	    
	    
	}
	
}
