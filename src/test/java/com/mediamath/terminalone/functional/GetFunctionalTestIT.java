package com.mediamath.terminalone.functional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mediamath.terminalone.QueryCriteria;
import com.mediamath.terminalone.QueryCriteria.CreativeType;
import com.mediamath.terminalone.TerminalOne;
import com.mediamath.terminalone.exceptions.ClientException;
import com.mediamath.terminalone.exceptions.ParseException;
import com.mediamath.terminalone.models.AdServer;
import com.mediamath.terminalone.models.Advertiser;
import com.mediamath.terminalone.models.AtomicCreative;
import com.mediamath.terminalone.models.AudienceSegment;
import com.mediamath.terminalone.models.Campaign;
import com.mediamath.terminalone.models.Concept;
import com.mediamath.terminalone.models.CreativeApproval;
import com.mediamath.terminalone.models.CreativeDetailsResponse;
import com.mediamath.terminalone.models.Data;
import com.mediamath.terminalone.models.JsonResponse;
import com.mediamath.terminalone.models.Pixel;
import com.mediamath.terminalone.models.Strategy;
import com.mediamath.terminalone.models.StrategyAudienceSegment;
import com.mediamath.terminalone.models.StrategyDayPart;
import com.mediamath.terminalone.models.StrategySupplySource;
import com.mediamath.terminalone.models.StrategyTargetingSegment;
import com.mediamath.terminalone.models.SupplySource;
import com.mediamath.terminalone.utils.ConditionQuery;
import com.mediamath.terminalone.utils.Filters;
import com.mediamath.terminalone.utils.FullParamValues;
import com.mediamath.terminalone.utils.QueryParamValues;

public class GetFunctionalTestIT {

	private static Properties testConfig = new Properties();

	private static String user = null;

	private static String password = null;

	private static String apiKey = null;

	private static String productionKey = null;

	private static String oauthKey = null;

	private static String oauthSecret = null;

	@BeforeClass
	public static void init() throws Exception {
		InputStream input = PostFunctionalTestIT.class.getClassLoader().getResourceAsStream("test.properties");
		testConfig.load(input);
		user = testConfig.getProperty("t1.username");
		password = testConfig.getProperty("t1.password");
		apiKey = testConfig.getProperty("t1.sandbox_api_key");
		productionKey = testConfig.getProperty("t1.production_api_key");
		oauthKey = testConfig.getProperty("t1.oauth_api_key");
		oauthSecret = testConfig.getProperty("t1.oauth_secret");
	}

	@After
	public final void tearDown() throws InterruptedException {
		Thread.sleep(5000);
	}

	@Test
	public void testJTerminalOneStringStringString() throws ClientException {
		TerminalOne t1;
		t1 = new TerminalOne("nitesh.chauhan@xoriant.com", "xoriant123#", "e34f74vnubr9uxasz2n7bdfv");
		assertEquals(true, t1.isAuthenticated());
	}

	@Test
	public void testBaiscGetWithChildUsingQueryCriteria() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);

		QueryCriteria query = QueryCriteria.builder().setCollection("advertisers").setEntity(154161)
				.setInclude(new ConditionQuery("agency", "organization")).build();

		query = QueryCriteria.builder(query).setInclude(new ConditionQuery("ad_server"))
				.setInclude(new ConditionQuery("vertical")).build();

		JsonResponse<?> jsonresponse = null;

		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		Advertiser advertiser = (Advertiser) jsonresponse.getData();
		assertNotNull(advertiser.getAgency());
		assertNotNull(advertiser.getVerticalId());
		assertNotNull(advertiser.getAgency());

		query = QueryCriteria.builder().setCollection("advertisers").setEntity(154161).build();

		jsonresponse = null;

		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		advertiser = (Advertiser) jsonresponse.getData();
		assertNull(advertiser.getAgency());
		assertNotNull(advertiser.getVerticalId());
	}

	@Test
	public void testBaiscGetWithSortByUsingQueryCriteria() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);

		QueryCriteria query = QueryCriteria.builder().setCollection("advertisers").setSortBy("-id").build();

		JsonResponse<?> jsonresponse = null;

		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);

	}

	@Test
	public void testBaiscGetWithParentUsingQueryCriteria() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, productionKey);
		FullParamValues fpv = new FullParamValues();
		fpv.setBoolValue(true);
		QueryCriteria query = QueryCriteria.builder().setCollection("audience_segments").setSortBy("id")
				.setParent(27962).setFull(fpv).build();

		JsonResponse<?> jsonresponse = null;

		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);

	}

	@Test
	public void testBaiscGetWithPageLimit() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);

		QueryCriteria query = QueryCriteria.builder().setCollection("advertisers").setPageLimit(40).build();

		JsonResponse<?> jsonresponse = null;

		try {
			jsonresponse = jt1.get(query);
			System.out.println(jsonresponse.getData());
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		List<Advertiser> advertisers = (List<Advertiser>) jsonresponse.getData();
		assertEquals(40, advertisers.size());

		query = QueryCriteria.builder().setCollection("advertisers").setPageLimit(50).build();

		jsonresponse = null;

		try {
			jsonresponse = jt1.get(query);
			System.out.println(jsonresponse.getData());
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		advertisers = (List<Advertiser>) jsonresponse.getData();
		assertEquals(50, advertisers.size());

		// Default page limit is 100
		query = QueryCriteria.builder().setCollection("advertisers").build();

		jsonresponse = null;

		try {
			jsonresponse = jt1.get(query);
			System.out.println(jsonresponse.getData());
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		advertisers = (List<Advertiser>) jsonresponse.getData();
		assertEquals(100, advertisers.size());
	}

	@Test
	public void testBaiscGetWithPageLimitOffset() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);

		// default page offset is 0
		QueryCriteria query = QueryCriteria.builder().setCollection("advertisers").setPageLimit(40).build();

		JsonResponse<?> jsonresponse_0_to_40 = null;

		try {
			jsonresponse_0_to_40 = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse_0_to_40);

		query = QueryCriteria.builder().setCollection("advertisers").setPageLimit(40).setPageOffset(40).build();

		JsonResponse<?> jsonresponse_40_to_80 = null;

		try {
			jsonresponse_40_to_80 = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse_0_to_40);
		assertFalse(jsonresponse_40_to_80.equals(jsonresponse_0_to_40));

	}

	@Test
	public void testBaiscGetWithLimit() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);

		Map<String, Long> limitList = new HashMap<String, Long>();
		limitList.put("agency", Long.valueOf(111555));
		FullParamValues fpv = new FullParamValues();
		fpv.setStrValue("advertiser");
		QueryCriteria query = QueryCriteria.builder().setCollection("advertisers").setLimit(limitList).setFull(fpv)
				.setPageLimit(100).build();

		JsonResponse<?> jsonresponse = null;

		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		List<Advertiser> advertisers = (List<Advertiser>) jsonresponse.getData();

		for (int i = 0; i < advertisers.size(); i++) {
			Advertiser advertiser = advertisers.get(i);
			assertEquals(111555, advertiser.getAgencyId());
		}
	}

	@Test
	public void testBaiscGetWithQuery() throws ClientException {

		TerminalOne jt1 = new TerminalOne(user, password, apiKey);

		FullParamValues fpv = new FullParamValues();
		fpv.setStrValue("advertiser");

		QueryCriteria query = QueryCriteria.builder().setCollection("advertisers")
				.setInclude(new ConditionQuery("agency", "organization")).setQuery("agency_id==109308")
				.setPageLimit(100).setFull(fpv).build();
		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);

		List<Advertiser> advertisers = (List<Advertiser>) jsonresponse.getData();

		for (int i = 0; i < advertisers.size(); i++) {
			Advertiser advertiser = advertisers.get(i);
			assertEquals(109308, advertiser.getAgencyId());
		}

	}

	@Test
	public void testBaiscGetWithGetAll() throws ClientException {

		TerminalOne jt1 = new TerminalOne(user, password, apiKey);

		QueryCriteria query = QueryCriteria.builder().setCollection("campaigns").setGetAll(true)
				.setSortBy("-updated_on").build();
		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		List<Campaign> campaigns = (List<Campaign>) jsonresponse.getData();

		query = QueryCriteria.builder().setCollection("campaigns").setSortBy("-updated_on").build();
		jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(jsonresponse);
		List<Campaign> campaigns_without_get_all = (List<Campaign>) jsonresponse.getData();
		assertTrue(campaigns_without_get_all.size() <= 100);
		assertTrue(campaigns.size() >= campaigns_without_get_all.size());

	}

	@Test
	public void testBaiscGetWithFullBoolean() throws ClientException {

		TerminalOne jt1 = new TerminalOne(user, password, apiKey);
		FullParamValues fpv = new FullParamValues();
		fpv.setBoolValue(true);
		QueryCriteria query = QueryCriteria.builder().setCollection("campaigns").setFull(fpv).setPageLimit(1)
				.setSortBy("-updated_on").build();
		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		List<Campaign> campaigns = (List<Campaign>) jsonresponse.getData();
		assertTrue(campaigns.get(0).getAdvertiserId() != 0);
		assertNotNull(campaigns.get(0).getGoalType());

		query = QueryCriteria.builder().setCollection("campaigns").setSortBy("-updated_on").setPageLimit(1).build();
		jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		campaigns = (List<Campaign>) jsonresponse.getData();
		assertEquals(0, campaigns.get(0).getAdvertiserId());

	}

	@Test
	public void testBaiscGetWithFullString() throws ClientException {

		TerminalOne jt1 = new TerminalOne(user, password, apiKey);
		FullParamValues fpv = new FullParamValues();
		fpv.setStrValue("campaign");
		QueryCriteria query = QueryCriteria.builder().setCollection("campaigns").setFull(fpv).setSortBy("-updated_on")
				.setPageLimit(10).build();
		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		List<Campaign> campaigns = (List<Campaign>) jsonresponse.getData();
		assertTrue(campaigns.get(0).getAdvertiserId() != 0);
		assertNotNull(campaigns.get(0).getGoalType());

		query = QueryCriteria.builder().setCollection("campaigns").setSortBy("-updated_on").setPageLimit(1).build();
		jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		campaigns = (List<Campaign>) jsonresponse.getData();
		assertEquals(0, campaigns.get(0).getAdvertiserId());

	}

	@Test
	public void testBaiscGetWithFullList() throws ClientException {

		TerminalOne jt1 = new TerminalOne(user, password, apiKey);
		FullParamValues fpv = new FullParamValues();
		List<String> newList = new ArrayList<String>();
		newList.add("campaign");
		newList.add("advertiser");
		fpv.setListValue(newList);
		QueryCriteria query = QueryCriteria.builder().setCollection("campaigns")
				.setInclude(new ConditionQuery("advertiser")).setFull(fpv).setSortBy("-updated_on").setPageLimit(1)
				.build();
		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		List<Campaign> campaigns = (List<Campaign>) jsonresponse.getData();
		assertTrue(campaigns.get(0).getAdvertiserId() != 0);
		assertNotNull(campaigns.get(0).getGoalType());
		assertNotNull(campaigns.get(0).getAdvertiser());
		assertTrue(campaigns.get(0).getAdvertiser().getAgencyId() != 0);

		query = QueryCriteria.builder().setCollection("campaigns").setInclude(new ConditionQuery("advertiser"))
				.setSortBy("-updated_on").setPageLimit(1).build();
		jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		campaigns = (List<Campaign>) jsonresponse.getData();
		assertTrue(campaigns.get(0).getAdvertiserId() == 0);
		assertNotNull(campaigns.get(0).getAdvertiser());
		assertTrue(campaigns.get(0).getAdvertiser().getAgencyId() == 0);

	}

	@Test
	public void testBaiscGetWithFind() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);

		FullParamValues fpv = new FullParamValues();
		fpv.setBoolValue(true);

		QueryCriteria query = QueryCriteria.builder().setCollection("advertisers")
				.setInclude(new ConditionQuery("agency")).setQueryParamName("agency_id").setFull(fpv)
				.setQueryOperator(Filters.GREATER_OR_EQUAL).setQueryParams(new QueryParamValues(109308))
				.setPageLimit(100).build();
		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.find(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			throw new AssertionError();

		}

		assertNotNull(jsonresponse);
		List<Advertiser> advertisers = (List<Advertiser>) jsonresponse.getData();

		for (int i = 0; i < advertisers.size(); i++) {
			Advertiser advertiser = advertisers.get(i);
			assertTrue(advertiser.getAgencyId() != 0);
			assertTrue(109308 <= advertiser.getAgencyId());
		}
	}

	@Test
	public void testBaiscGetWithFind1() throws ClientException {

		TerminalOne jt1 = new TerminalOne(user, password, apiKey);

		Map<String, Long> limitList = new HashMap<String, Long>();
		limitList.put("agency", Long.valueOf(111555));
		QueryCriteria query = QueryCriteria.builder().setCollection("advertisers").setQueryParamName("name")
				.setQueryOperator(Filters.EQUAL).setQueryParams(new QueryParamValues("Retirement")).setPageLimit(100)
				.build();

		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.find(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);

		List<Advertiser> advertisers = (List<Advertiser>) jsonresponse.getData();

		for (int i = 0; i < advertisers.size(); i++) {
			Advertiser advertiser = advertisers.get(i);
			assertEquals("Retirement", advertiser.getName());
		}

	}

	@Test
	public void testBaiscGetWithFind2() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);

		Map<String, Long> limitList = new HashMap<String, Long>();
		limitList.put("agency", Long.valueOf(111555));
		List<Object> qParams = new ArrayList<Object>();
		qParams.add(154121);
		qParams.add(153226);
		qParams.add(150994);
		QueryCriteria query = QueryCriteria.builder().setCollection("advertisers")
				.setQueryParams(new QueryParamValues("id")).setQueryOperator(Filters.IN)
				.setQueryParams(new QueryParamValues(qParams)).setPageLimit(100).build();
		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.find(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);

		List<Advertiser> advertisers = (List<Advertiser>) jsonresponse.getData();

		for (int i = 0; i < advertisers.size(); i++) {
			Advertiser advertiser = advertisers.get(i);
			assertTrue(qParams.contains(advertiser.getId()));
		}

	}

	@Test
	public void testGetWithChildByUsingQC() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);
		QueryCriteria query = QueryCriteria.builder().setCollection("strategies").setEntity(1377524)
				.setChild("domain_restrictions").setPageLimit(1).build();

		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		Strategy strategy = (Strategy) jsonresponse.getData();
		assertNotNull(strategy);
		assertEquals(1377524, strategy.getId());
		assertNotNull(strategy.getStrategyDomainRestrictions());
		assertEquals(2, strategy.getStrategyDomainRestrictions().size());
	}

	@Test
	public void testGetForStrategyConceptsByUsingQC() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);
		QueryCriteria query = QueryCriteria.builder().setCollection("strategies").setEntity(1376198)
				.setChild("concepts").setPageLimit(1).build();

		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		ArrayList<Concept> concept = (ArrayList<Concept>) jsonresponse.getData();
		assertNotNull(concept);
		assertNotNull(concept.get(0));

	}

	@Test
	public void testGetForStrategyTotalSpend() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);
		QueryCriteria query = QueryCriteria.builder().setCollection("strategies").setEntity(1376198)
				.setChild("total_spend").setPageLimit(1).build();

		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		Strategy strategy = (Strategy) jsonresponse.getData();
		assertNotNull(strategy);
		assertNotNull(strategy.getAggregate());
	}

	@Test
	public void testGetForStrategyDeals() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);
		QueryCriteria query = QueryCriteria.builder().setCollection("strategies").setEntity(2145568).setChild("deals")
				.setPageLimit(1).build();

		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		Strategy strategy = (Strategy) jsonresponse.getData();
		assertNotNull(strategy);
		// assertNotNull(strategy.getAggregate());
	}

	@Test
	public void testStrategyGetWithConcepts() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);

		QueryCriteria query = QueryCriteria.builder().setCollection("strategies").setEntity(1377388)
				.setInclude(new ConditionQuery("concepts")).build();

		JsonResponse<?> jsonresponse = null;

		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		Strategy strategy = (Strategy) jsonresponse.getData();
		assertNotNull(strategy);
		assertNotNull(strategy.getConcepts());

	}

	@Test
	public void testGetForStrategyChildBrowser() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);
		QueryCriteria query = QueryCriteria.builder().setCollection("strategies").setEntity(1376198).setChild("browser")
				.setPageLimit(1).build();

		JsonResponse<?> jsonresponse = null;

		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		Data data = (Data) jsonresponse.getData();
		assertNotNull(data);
		assertTrue(data.enabled.getActive() == "true");

	}

	@Test
	public void testGetForStrategyChildAudienceSegments() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);
		QueryCriteria query = QueryCriteria.builder().setCollection("strategies").setEntity(1377457)
				.setChild("audience_segments").setPageLimit(1).build();

		JsonResponse<?> jsonresponse = null;

		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		List<StrategyAudienceSegment> data = (List<StrategyAudienceSegment>) jsonresponse.getData();
		assertNotNull(data);

	}

	@Test
	public void testGetForStrategyChildDayParts() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);
		QueryCriteria query = QueryCriteria.builder().setCollection("strategies").setEntity(1376198)
				.setChild("day_parts").setPageLimit(1).build();

		JsonResponse<?> jsonresponse = null;

		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		List<StrategyDayPart> day_parts = (List<StrategyDayPart>) jsonresponse.getData();
		assertNotNull(day_parts);
		assertTrue(day_parts.size() > 0);
	}

	@Test
	public void testGetForSupplySources() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);
		QueryCriteria query = QueryCriteria.builder().setCollection("supply_sources").setPageLimit(1).build();

		JsonResponse<?> jsonresponse = null;

		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		List<SupplySource> supply_sources = (List<SupplySource>) jsonresponse.getData();
		assertNotNull(supply_sources);
		assertTrue(supply_sources.size() > 0);
	}

	@Test
	public void testGetForAudienceSegments() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);
		QueryCriteria query = QueryCriteria.builder().setCollection("audience_segments").setPageLimit(1).build();

		JsonResponse<?> jsonresponse = null;

		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		List<AudienceSegment> audience_segments = (List<AudienceSegment>) jsonresponse.getData();
		assertNotNull(audience_segments);
		assertTrue(audience_segments.size() > 0);
	}

	@Test
	public void testGetForPixelBundles() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);
		QueryCriteria query = QueryCriteria.builder().setCollection("pixel_bundles").setPageLimit(1).build();

		JsonResponse<?> jsonresponse = null;

		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		List<Pixel> pixel_bundles = (List<Pixel>) jsonresponse.getData();
		assertNotNull(pixel_bundles);
		assertTrue(pixel_bundles.size() > 0);
	}

	@Test
	public void testGetForStrategySupplySources() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);
		Map<String, Long> limitList = new HashMap<String, Long>();
		limitList.put("strategy", Long.valueOf(1376337));
		QueryCriteria query = QueryCriteria.builder().setCollection("strategy_supply_sources").setLimit(limitList)
				.setPageLimit(1).build();

		JsonResponse<?> jsonresponse = null;

		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		List<StrategySupplySource> strategy_supply_sources = (List<StrategySupplySource>) jsonresponse.getData();
		assertNotNull(strategy_supply_sources);
		assertTrue(strategy_supply_sources.size() > 0);
	}

	@Test
	public void testGetForConcepts() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);
		QueryCriteria query = QueryCriteria.builder().setCollection("concepts").setPageLimit(1).build();

		JsonResponse<?> jsonresponse = null;

		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		List<Concept> concepts = (List<Concept>) jsonresponse.getData();
		assertNotNull(concepts);
		assertTrue(concepts.size() > 0);
	}

	@Test
	public void testGetForAtomicCreatives() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);
		QueryCriteria query = QueryCriteria.builder().setCollection("atomic_creatives").setPageLimit(1).build();

		JsonResponse<?> jsonresponse = null;

		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		List<AtomicCreative> atomic_creatives = (List<AtomicCreative>) jsonresponse.getData();
		assertNotNull(atomic_creatives);
		assertTrue(atomic_creatives.size() > 0);
	}

	@Test
	public void testGetForAtomicCreativesWithCreativeApprovals() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);
		QueryCriteria query = QueryCriteria.builder().setCollection("atomic_creatives")
				.setInclude(new ConditionQuery("creative_approvals")).setEntity(2691868).setPageLimit(1).build();

		JsonResponse<?> jsonresponse = null;

		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		AtomicCreative atomic_creative = (AtomicCreative) jsonresponse.getData();
		assertNotNull(atomic_creative);
		assertNotNull(atomic_creative.getCreativeApprovals());
	}

	@Test
	public void testGetCreativeApprovalsAsChild() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);
		QueryCriteria query = QueryCriteria.builder().setCollection("atomic_creatives").setEntity(2691868)
				.setChild("creative_approvals").setPageLimit(1).build();

		JsonResponse<?> jsonresponse = null;

		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		List<CreativeApproval> creatoveApproval = (List<CreativeApproval>) jsonresponse.getData();
		assertNotNull(creatoveApproval);
		assertTrue(creatoveApproval.size() > 0);
	}

	@Test
	public void testGetForAdservers() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);
		QueryCriteria query = QueryCriteria.builder().setCollection("ad_servers").setPageLimit(1).build();

		JsonResponse<?> jsonresponse = null;

		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		@SuppressWarnings("unchecked")
		List<AdServer> adservers = (List<AdServer>) jsonresponse.getData();
		assertNotNull(adservers);
		assertTrue(adservers.size() > 0);
	}

	@Test
	public void testGetForSiteListDownload() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);
		QueryCriteria query = QueryCriteria.builder().setCollection("site_lists").setEntity(95358)
				.setDownloadSiteList(true).setPageLimit(0).build();

		BufferedReader reader = null;

		try {
			reader = jt1.getSiteListData(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(reader);

	}

	@Test
	public void testBaiscGetWithDealUsingQueryCriteria() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);

		QueryCriteria query = QueryCriteria.builder().setCollection("deals").setSortBy("-id").build();

		JsonResponse<?> jsonresponse = null;

		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);

	}

	@Test
	public void testBaiscGetWithDealUsingQC() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);

		QueryCriteria query = QueryCriteria.builder().setCollection("deals").setEntity(173131).setSortBy("-id").build();

		JsonResponse<?> jsonresponse = null;

		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);

	}

	@Test
	public void testBaiscGetWithDealUsingQC1() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);

		Map<String, Long> limitList = new HashMap<String, Long>();
		limitList.put("permissions.advertiser_id", Long.valueOf(145998));

		QueryCriteria query = QueryCriteria.builder().setCollection("deals").setLimit(limitList).setSortBy("-id")
				.build();

		JsonResponse<?> jsonresponse = null;

		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
	}
	
	@Test
	public void testAdServerAllGet() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, productionKey);
		FullParamValues fpv = new FullParamValues();
		fpv.setBoolValue(true);
		QueryCriteria query = QueryCriteria.builder().setCollection("ad_servers").setFull(fpv).setSortBy("-id")
				.build();
		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			e.printStackTrace();
		}
		assertNotNull(jsonresponse);
	}
	@Test
	public void testSingleAdServerGet() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, productionKey);
		FullParamValues fpv = new FullParamValues();
		fpv.setBoolValue(true);
		QueryCriteria query = QueryCriteria.builder().setCollection("ad_servers").setEntity(9).setFull(fpv).setSortBy("-id")
				.build();
		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			e.printStackTrace();
		}
		assertNotNull(jsonresponse);
	}
	
	@Test
	public void testAdvertiserAllGet() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, productionKey);
		FullParamValues fpv = new FullParamValues();
		fpv.setBoolValue(true);
		QueryCriteria query = QueryCriteria.builder().setCollection("advertisers").setFull(fpv).setSortBy("-id")
				.build();
		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			e.printStackTrace();
		}
		assertNotNull(jsonresponse);
	}
	@Test
	public void testSingleAdvertiserGet() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, productionKey);
		FullParamValues fpv = new FullParamValues();
		fpv.setBoolValue(true);
		QueryCriteria query = QueryCriteria.builder().setCollection("advertisers").setEntity(186504).setFull(fpv).setSortBy("-id")
				.build();
		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			e.printStackTrace();
		}
		assertNotNull(jsonresponse);
	}
	
	@Test
	public void testAgencyAllGet() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, productionKey);
		FullParamValues fpv = new FullParamValues();
		fpv.setBoolValue(true);
		QueryCriteria query = QueryCriteria.builder().setCollection("agencies").setFull(fpv).setSortBy("-id")
				.build();
		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			e.printStackTrace();
		}
		assertNotNull(jsonresponse);
	}
	@Test
	public void testSingleAgencyGet() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, productionKey);
		FullParamValues fpv = new FullParamValues();
		fpv.setBoolValue(true);
		QueryCriteria query = QueryCriteria.builder().setCollection("agencies").setEntity(117099).setFull(fpv).setSortBy("-id")
				.build();
		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			e.printStackTrace();
		}
		assertNotNull(jsonresponse);
	}

	@Test
	public void testCampaignAllGet() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, productionKey);
		FullParamValues fpv = new FullParamValues();
		fpv.setBoolValue(true);
		QueryCriteria query = QueryCriteria.builder().setCollection("campaigns").setFull(fpv).setSortBy("-id")
				.build();
		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			e.printStackTrace();
		}
		assertNotNull(jsonresponse);
	}
	@Test
	public void testSingleCampaignGet() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, productionKey);
		FullParamValues fpv = new FullParamValues();
		fpv.setBoolValue(true);
		QueryCriteria query = QueryCriteria.builder().setCollection("campaigns").setEntity(364126).setFull(fpv).setSortBy("-id")
				.build();
		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			e.printStackTrace();
		}
		assertNotNull(jsonresponse);
	}
	@Test
	public void testStrategyTgtDimensionGet() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, productionKey);
		FullParamValues fpv = new FullParamValues();
		fpv.setBoolValue(true);
		QueryCriteria query = QueryCriteria.builder().setCollection("strategies").setEntity(2195001).setChild("target_dimensions").setChild(String.valueOf(7))
				.build();
		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			e.printStackTrace();
		}
		assertNotNull(jsonresponse);
	}
	
	@Test
	public void testCampaignBudgetFlightGet() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, productionKey);
		FullParamValues fpv = new FullParamValues();
		fpv.setBoolValue(true);
		QueryCriteria query = QueryCriteria.builder().setCollection("campaigns").setEntity(380492).setChild("budget_flights")
				.build();
		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			e.printStackTrace();
		}
		assertNotNull(jsonresponse);
	}
	
	@Test
	public void testCampaignBudgetFlightIdGet() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, productionKey);
		FullParamValues fpv = new FullParamValues();
		fpv.setBoolValue(true);
		QueryCriteria query = QueryCriteria.builder().setCollection("campaigns").setEntity(380492).setChild("budget_flights").setChild("449087")
				.build();
		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			e.printStackTrace();
		}
		assertNotNull(jsonresponse);
	}
	
	@Test
	public void testCampaignBudgetFlightRelevantGet() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, productionKey);
		FullParamValues fpv = new FullParamValues();
		fpv.setBoolValue(true);
		QueryCriteria query = QueryCriteria.builder().setCollection("campaigns").setEntity(380492).setChild("budget_flights").setChild("relevant")
				.build();
		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			e.printStackTrace();
		}
		assertNotNull(jsonresponse);
	}
	
	@Test
	public void testCampaignBudgetFlightWithRelevantGet() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, productionKey);
		FullParamValues fpv = new FullParamValues();
		fpv.setBoolValue(true);
		QueryCriteria query = QueryCriteria.builder().setCollection("campaigns").setEntity(380492).setInclude(new ConditionQuery("relevant_budget_flight"))
				.build();
		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			e.printStackTrace();
		}
		assertNotNull(jsonresponse);
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testGetWithCampaignCBS() throws ClientException, ParseException {
		TerminalOne jt1 = new TerminalOne(user, password, productionKey);
		QueryCriteria query = QueryCriteria.builder().setCollection("campaigns").setEntity(338158).setChild("custom_brain_selections").build();

		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testGetWithCampaignCBSWithId() throws ClientException, ParseException {
		TerminalOne jt1 = new TerminalOne(user, password, productionKey);
		QueryCriteria query = QueryCriteria.builder().setCollection("campaigns").setEntity(338158).setChild("custom_brain_selections").setChild("143213").build();

		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
	}
	
	@Test
	public void testGetWithVideoCreativeToOrg() throws ClientException, ParseException {
		TerminalOne jt1 = new TerminalOne(user, password, productionKey);
		
		QueryCriteria query = QueryCriteria.builder().setCollection("creatives").setEntity(4534579).setCreativeType(CreativeType.video).build();

		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CreativeDetailsResponse cdr = (CreativeDetailsResponse) jsonresponse.getData();

		assertNotNull(jsonresponse);
		assertNotNull(cdr);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testUpdateStrategiestoCampaign() throws ClientException, ParseException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		TerminalOne jt1 = new TerminalOne(user, password, productionKey);
		FullParamValues fpm = new FullParamValues();
		fpm.setStrValue("strategy");
		Map<String, Long> limitMap = new HashMap<String, Long>();
		limitMap.put("campaign", (long) 406396);
		QueryCriteria query = QueryCriteria.builder().setCollection("strategies").setLimit(limitMap).setFull(fpm).setGetAll(true).build();
		List<Strategy> strategyList = new ArrayList<Strategy>();
		
		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			e.printStackTrace();
		}
		strategyList = (List<Strategy>) jsonresponse.getData();
		
		Strategy updator = new Strategy();
		updator.setBidAggresiveness(100f);
		List<Strategy> updatedStrategyList =null;
		long startTime = System.currentTimeMillis();
		System.out.println("start= "+startTime);
		updatedStrategyList =	jt1.bulkUpdate(strategyList, updator);
		System.out.println("total time = "+((new Date()).getTime() - startTime)/1000);

		assertNotNull(updatedStrategyList);
	}
	
	@Test
	public void testGetWithAllUsers() throws ClientException, ParseException {
		TerminalOne jt1 = new TerminalOne(user, password, productionKey);
		QueryCriteria query = QueryCriteria.builder().setCollection("users").build();

		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
	}
	@Test
	public void testGetWithSingleUsers() throws ClientException, ParseException {
		TerminalOne jt1 = new TerminalOne(user, password, productionKey);
		QueryCriteria query = QueryCriteria.builder().setCollection("users").setEntity(20301).build();

		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
	}
	
	@Test
	public void testGetWithUserPermissions() throws ClientException, ParseException {
		TerminalOne jt1 = new TerminalOne(user, password, productionKey);
		QueryCriteria query = QueryCriteria.builder().setCollection("users").setEntity(20301).setChild("permissions").build();

		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
	}
	
	@Test
	public void testGetWithVideoCreativeUpload() throws ClientException, ParseException {
		TerminalOne jt1 = new TerminalOne(user, password, productionKey);
		
		QueryCriteria query = QueryCriteria.builder().setCollection("creatives").setEntity(4784583).setCreativeType(CreativeType.video).setChild("upload").build();

		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CreativeDetailsResponse cdr = (CreativeDetailsResponse) jsonresponse.getData();

		assertNotNull(jsonresponse);
		assertNotNull(cdr);
	}
	
	@Test
	public void testGetWithStrategyTargetingSegments() throws ClientException, IOException, ParseException {
		// will work only on production.

		TerminalOne one = new TerminalOne(user, password, productionKey);

		FullParamValues fpv = new FullParamValues();
		fpv.setBoolValue(true);
		QueryCriteria query = QueryCriteria.builder().setCollection("strategies").setEntity(2099177).setChild("targeting_segments") // limit
				.setFull(fpv) // getAll
				.build();
		JsonResponse<?> jsonresponse = one.get(query);	
		assertNotNull(jsonresponse);	
	}
	
	
}
