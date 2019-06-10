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

package com.mediamath.terminalone.functional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mediamath.terminalone.QueryCriteria;
import com.mediamath.terminalone.QueryCriteria.CreativeType;
import com.mediamath.terminalone.TerminalOne;
import com.mediamath.terminalone.exceptions.ClientException;
import com.mediamath.terminalone.exceptions.ParseException;
import com.mediamath.terminalone.models.Advertiser;
import com.mediamath.terminalone.models.Agency;
import com.mediamath.terminalone.models.AtomicCreative;
import com.mediamath.terminalone.models.BudgetFlight;
import com.mediamath.terminalone.models.BulkStrategy;
import com.mediamath.terminalone.models.Campaign;
import com.mediamath.terminalone.models.CampaignCustomBrainSelection;
import com.mediamath.terminalone.models.CampaignCustomBrainSelection.SELTYPES;
import com.mediamath.terminalone.models.ChildPixel;
import com.mediamath.terminalone.models.Concept;
import com.mediamath.terminalone.models.Contract;
import com.mediamath.terminalone.models.CreativeDetailsResponse;
import com.mediamath.terminalone.models.Currency;
import com.mediamath.terminalone.models.EventPixel;
import com.mediamath.terminalone.models.EventPixel.EventPixelsEnum;
import com.mediamath.terminalone.models.JsonResponse;
import com.mediamath.terminalone.models.Organization;
import com.mediamath.terminalone.models.Segments;
import com.mediamath.terminalone.models.SiteList;
import com.mediamath.terminalone.models.Strategy;
import com.mediamath.terminalone.models.Strategy.freqInt;
import com.mediamath.terminalone.models.Strategy.freqType;
import com.mediamath.terminalone.models.Strategy.goalType;
import com.mediamath.terminalone.models.Strategy.type;
import com.mediamath.terminalone.models.StrategyConcept;
import com.mediamath.terminalone.models.StrategyDayPart;
import com.mediamath.terminalone.models.StrategyDayPart.daysEnum;
import com.mediamath.terminalone.models.StrategyDomain;
import com.mediamath.terminalone.models.StrategyDomain.restrictions;
import com.mediamath.terminalone.models.StrategySupplySource;
import com.mediamath.terminalone.models.StrategyTarget;
import com.mediamath.terminalone.models.StrategyTargetingSegment;
import com.mediamath.terminalone.models.T1Entity;
import com.mediamath.terminalone.models.T1File;
import com.mediamath.terminalone.models.TOneASCreativeAssetsApprove;
import com.mediamath.terminalone.models.TOneASCreativeAssetsUpload;
import com.mediamath.terminalone.models.TPASCreativeBatchApprove;
import com.mediamath.terminalone.models.TPASCreativeUpload;
import com.mediamath.terminalone.models.TargetDimensions;
import com.mediamath.terminalone.models.TargetDimensions.excludeOp;
import com.mediamath.terminalone.models.TargetDimensions.includeOp;
import com.mediamath.terminalone.models.TargetValues;
import com.mediamath.terminalone.models.User;
import com.mediamath.terminalone.models.VendorContract;
import com.mediamath.terminalone.models.VideoCreative;
import com.mediamath.terminalone.models.VideoCreativeResponse;
import com.mediamath.terminalone.models.VideoCreativeUploadResponse;
import com.mediamath.terminalone.models.VideoCreativeUploadStatus;
import com.mediamath.terminalone.models.ZipCodes;
import com.mediamath.terminalone.models.ZipCodesJsonResponse;
import com.mediamath.terminalone.utils.FullParamValues;

public class PostFunctionalTestIT {

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
		t1 = new TerminalOne(user, password, apiKey);
		assertEquals(true, t1.isAuthenticated());
	}

	@Test
	public void testAuthenticateCookie() throws ClientException {
		TerminalOne t1 = new TerminalOne();
		// t1.authenticate(user,password, productionKey);
		t1.authenticate("fausto@infiniamobile.com", "Infiniafausto0919!", "gtybp9ru7ajkvfhz6pzaq732");
		assertEquals(true, t1.isAuthenticated());
	}

	// TEST WILL FAIL UNTIL AUTHO WORKS IN PRODUCTION
	@Test
	public void testOauthTokenAuthentication() throws ClientException {
		TerminalOne t1 = new TerminalOne();
		t1.authenticate(user, password, oauthKey, oauthSecret);
		assertEquals(true, t1.isAuthenticated());

		Agency agency = new Agency();
		agency.setName("TestAgency");
		agency.setOrganizationId(100048);
		try {
			agency = (Agency) t1.save(agency);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		QueryCriteria query = QueryCriteria.builder().setCollection("agencies").setEntity(agency.getId())
				.setGetAll(true).build();
		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = t1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		Agency agencyCreated = (Agency) jsonresponse.getData();
		assertEquals("TestAgency", agencyCreated.getName());
		assertEquals(100048, agencyCreated.getOrganizationId());
	}

	// TEST WILL FAIL UNTIL AUTHO WORKS IN PRODUCTION
	@Test
	public void testOAuthHGetToken() throws ClientException {
		TerminalOne t1 = new TerminalOne();
		boolean isAuthenticated = t1.authenticate(user, password, oauthKey, oauthSecret);
		assertTrue(isAuthenticated);
	}

	/**
	 * Create Agency
	 * 
	 * @throws ClientException
	 */
	@Test
	public void testAgencyPost() throws ClientException {
		TerminalOne t1 = new TerminalOne(user, password, apiKey);

		Agency agency = new Agency();
		agency.setName("TestAgency");
		agency.setOrganizationId(101558);
		try {
			agency = (Agency) t1.save(agency);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		QueryCriteria query = QueryCriteria.builder().setCollection("agencies").setEntity(agency.getId())
				.setGetAll(true).build();
		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = t1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		Agency agencyCreated = (Agency) jsonresponse.getData();
		assertEquals("TestAgency", agencyCreated.getName());
		assertEquals(101558, agencyCreated.getOrganizationId());

	}

	/**
	 * Agency Update
	 * 
	 * @throws ClientException
	 */
	@Test
	public void testCommonAgencyUpdatePost() throws ClientException {

		TerminalOne t1 = new TerminalOne(user, password, apiKey);

		QueryCriteria query = QueryCriteria.builder().setCollection("agencies").setEntity(116678).build();

		JsonResponse<?> jsonresponse = null;

		try {
			jsonresponse = t1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Agency agency = (Agency) jsonresponse.getData();
		agency.setName("TestAgencyUpdated");

		try {
			agency = (Agency) t1.save(agency);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			jsonresponse = t1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Agency updatedAgency = (Agency) jsonresponse.getData();

		assertEquals("TestAgencyUpdated", updatedAgency.getName());

	}

	/**
	 * Create Campaign.
	 * 
	 * @throws ClientException
	 * @throws java.text.ParseException
	 */
	@Test
	public void testCampaignPost() throws ClientException, java.text.ParseException {
		TerminalOne t1 = new TerminalOne(user, password, apiKey);

		Campaign camp = new Campaign();
		// camp.setId(268746);

		camp.setAdServerId(9);
		camp.setAdvertiserId(182395);
		camp.setName("CampaignTest One");
		camp.setAdServerFee(new BigDecimal("10.01"), null);
		camp.setConversionType("variable");
		camp.setConversionVariableMinutes(1);
		camp.setGoalType(Campaign.goalTypes.cpa);
		camp.setGoalValue(new BigDecimal(100), null);
		camp.setServiceType(Campaign.servTypes.SELF);

		Calendar endcal = Calendar.getInstance();
		Calendar startcal = Calendar.getInstance();
		endcal.roll(Calendar.DATE, true);
		endcal.roll(Calendar.MONTH, true);
		Date endd = endcal.getTime();

		// startcal.roll(Calendar.DATE, true);
		// startcal.roll(Calendar.DATE, true);
		Date startd = startcal.getTime();
		camp.setEndDate(endd);
		camp.setStartDate(startd);

		camp.setPcWindowMinutes(1);
		camp.setSpendCapAmount(new BigDecimal(10), null);
		camp.setSpendCapType(Campaign.freqTypes.asap);
		camp.setImpressionCapType(Campaign.freqTypes.asap);
		camp.setTotalImpressionBudget(100);
		camp.setImpressionCapAutomatic(true);
		camp.setUseMmFreq(false);
		camp.setMeritPixelId(800781);
		camp.setTotalBudget(new BigDecimal(200), "USD");

		try {
			camp = (Campaign) t1.save(camp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		QueryCriteria query = QueryCriteria.builder().setCollection("campaigns").setEntity(camp.getId()).setGetAll(true)
				.build();
		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = t1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		Campaign campaignCreated = (Campaign) jsonresponse.getData();
		assertEquals(122631, campaignCreated.getAdvertiserId());
		assertEquals(Campaign.goalTypes.cpa, campaignCreated.getGoalType());
		assertEquals(Campaign.servTypes.SELF, campaignCreated.getServiceType());
		assertEquals(800781, campaignCreated.getMeritPixelId());
	}

	/**
	 * Create Campaign.
	 * 
	 * @throws ClientException
	 * @throws java.text.ParseException
	 */
	@Test
	public void testCampaignPostSpendCapEven() throws ClientException, java.text.ParseException {
		TerminalOne t1 = new TerminalOne(user, password, apiKey);

		Campaign camp = new Campaign();
		// camp.setId(268746);

		camp.setAdServerId(9);
		camp.setAdvertiserId(122631);
		camp.setName("CampaignTest One");
		camp.setAdServerFee(new BigDecimal("10.01"), null);
		camp.setConversionType("variable");
		camp.setConversionVariableMinutes(1);
		camp.setGoalType(Campaign.goalTypes.cpa);
		camp.setGoalValue(new BigDecimal(100), null);
		camp.setServiceType(Campaign.servTypes.SELF);

		Calendar endcal = Calendar.getInstance();
		Calendar startcal = Calendar.getInstance();
		endcal.roll(Calendar.DATE, true);
		endcal.roll(Calendar.MONTH, true);
		Date endd = endcal.getTime();

		// startcal.roll(Calendar.DATE, true);
		// startcal.roll(Calendar.DATE, true);
		Date startd = startcal.getTime();
		camp.setEndDate(endd);
		camp.setStartDate(startd);

		camp.setPcWindowMinutes(1);
		camp.setSpendCapAmount(new BigDecimal(10), null);
		camp.setSpendCapType(Campaign.freqTypes.even);
		camp.setImpressionCapType(Campaign.freqTypes.even);
		camp.setImpressionCapAmount(10);
		camp.setUseMmFreq(false);
		camp.setMeritPixelId(800781);
		camp.setTotalBudget(new BigDecimal(200), "USD");

		try {
			camp = (Campaign) t1.save(camp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		QueryCriteria query = QueryCriteria.builder().setCollection("campaigns").setEntity(camp.getId()).setGetAll(true)
				.build();
		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = t1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		Campaign campaignCreated = (Campaign) jsonresponse.getData();
		assertEquals(122631, campaignCreated.getAdvertiserId());
		assertEquals(Campaign.goalTypes.cpa, campaignCreated.getGoalType());
		assertEquals(Campaign.servTypes.SELF, campaignCreated.getServiceType());
		assertEquals(800781, campaignCreated.getMeritPixelId());
	}

	/**
	 * Create Campaign.
	 * 
	 * @throws ClientException
	 * @throws java.text.ParseException
	 */
	@Test
	public void testCampaignPostSpendCapDefault() throws ClientException, java.text.ParseException {
		TerminalOne t1 = new TerminalOne(user, password, apiKey);

		Campaign camp = new Campaign();
		camp.setAdServerId(9);
		camp.setAdvertiserId(122631);
		camp.setName("CampaignTest One");
		camp.setAdServerFee(new BigDecimal("10.01"), null);
		camp.setConversionType("variable");
		camp.setConversionVariableMinutes(1);
		camp.setGoalType(Campaign.goalTypes.cpa);
		camp.setGoalValue(new BigDecimal(100), null);
		camp.setServiceType(Campaign.servTypes.SELF);

		Calendar endcal = Calendar.getInstance();
		Calendar startcal = Calendar.getInstance();
		endcal.roll(Calendar.DATE, true);
		endcal.roll(Calendar.MONTH, true);
		Date endd = endcal.getTime();

		Date startd = startcal.getTime();
		camp.setEndDate(endd);
		camp.setStartDate(startd);

		camp.setPcWindowMinutes(1);
		camp.setImpressionCapType(Campaign.freqTypes.even);
		camp.setImpressionCapAmount(10);
		camp.setUseMmFreq(false);
		camp.setMeritPixelId(800781);
		camp.setTotalBudget(new BigDecimal(200), "USD");
		// camp.setSpendCapAutomatic(false);

		try {
			camp = (Campaign) t1.save(camp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		QueryCriteria query = QueryCriteria.builder().setCollection("campaigns").setEntity(camp.getId()).setGetAll(true)
				.build();
		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = t1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		Campaign campaignCreated = (Campaign) jsonresponse.getData();
		assertEquals(122631, campaignCreated.getAdvertiserId());
		assertEquals(Campaign.goalTypes.cpa, campaignCreated.getGoalType());
		assertEquals(Campaign.servTypes.SELF, campaignCreated.getServiceType());
		assertEquals(800781, campaignCreated.getMeritPixelId());
	}

	/**
	 * Create Campaign.
	 * 
	 * @throws ClientException
	 * @throws java.text.ParseException
	 */
	@Test
	public void testCampaignPostImpressionCapDefault() throws ClientException, java.text.ParseException {
		TerminalOne t1 = new TerminalOne(user, password, apiKey);

		Campaign camp = new Campaign();
		// camp.setId(268746);

		camp.setAdServerId(9);
		camp.setAdvertiserId(182395);
		camp.setName("CampaignTest One");
		camp.setAdServerFee(new BigDecimal("10.01"), null);
		camp.setConversionType("variable");
		camp.setConversionVariableMinutes(1);
		camp.setGoalType(Campaign.goalTypes.cpc);
		camp.setGoalValue(new BigDecimal(100), null);
		camp.setServiceType(Campaign.servTypes.SELF);

		Calendar endcal = Calendar.getInstance();
		Calendar startcal = Calendar.getInstance();
		endcal.roll(Calendar.DATE, true);
		endcal.roll(Calendar.MONTH, true);
		Date endd = endcal.getTime();

		// startcal.roll(Calendar.DATE, true);
		// startcal.roll(Calendar.DATE, true);
		Date startd = startcal.getTime();
		camp.setEndDate(endd);
		camp.setStartDate(startd);

		camp.setPcWindowMinutes(1);
		camp.setUseMmFreq(false);
		/* camp.setMeritPixelId(800781); */
		camp.setTotalBudget(new BigDecimal(200), "USD");
		camp.setZoneName("Europe/Paris");

		try {
			camp = (Campaign) t1.save(camp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		QueryCriteria query = QueryCriteria.builder().setCollection("campaigns").setEntity(camp.getId()).setGetAll(true)
				.build();
		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = t1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		Campaign campaignCreated = (Campaign) jsonresponse.getData();
		assertEquals(182395, campaignCreated.getAdvertiserId());
		assertEquals(Campaign.goalTypes.cpa, campaignCreated.getGoalType());
		assertEquals(Campaign.servTypes.SELF, campaignCreated.getServiceType());
		assertEquals(800781, campaignCreated.getMeritPixelId());
	}

	/**
	 * Campaign Update.
	 * 
	 * @throws ClientException
	 * @throws java.text.ParseException
	 */
	@Test
	public void testCampaignUpdatePost() throws ClientException, java.text.ParseException {
		TerminalOne t1 = new TerminalOne(user, password, apiKey);

		QueryCriteria query = QueryCriteria.builder().setCollection("campaigns").setEntity(269897).build();

		JsonResponse<?> jsonresponse = null;

		try {
			jsonresponse = t1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Campaign camp = (Campaign) jsonresponse.getData();

		camp.setFrequencyAmount(10);

		Calendar startcal = Calendar.getInstance();
		startcal.roll(Calendar.DATE, true);
		startcal.roll(Calendar.DATE, true);
		Date startd = startcal.getTime();
		camp.setStartDate(startd);

		try {
			camp = (Campaign) t1.save(camp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			jsonresponse = t1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Campaign updatedCampaign = (Campaign) jsonresponse.getData();
		assertEquals("Campaign Test One updated", updatedCampaign.getName());
	}

	/**
	 * Create Advertiser.
	 * 
	 * @throws ClientException
	 */
	@Test
	public void testAdvertiserPost() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);

		Advertiser adv = new Advertiser();
		adv.setAdServerId(9);
		adv.setAgencyId(116678);
		adv.setDomain("http://www.advertiser.com");
		adv.setName("TestAdvertiser");
		adv.setVerticalId(11);
		try {
			adv = (Advertiser) jt1.save(adv);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		QueryCriteria query = QueryCriteria.builder().setCollection("advertisers").setEntity(adv.getId())
				.setGetAll(true).build();
		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		Advertiser advertiserCreated = (Advertiser) jsonresponse.getData();
		// assertEquals("TestAdvertiser", advertiserCreated.getName());
		assertEquals(116678, advertiserCreated.getAgencyId());
		assertEquals(9, advertiserCreated.getAdServerId());
		assertEquals(11, advertiserCreated.getVerticalId());
	}

	/**
	 * Advertiser Update.
	 * 
	 * @throws ClientException
	 */
	@Test
	public void testAdvertiserUpdatePost() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);

		QueryCriteria query = QueryCriteria.builder().setCollection("advertisers").setEntity(187006).build();

		JsonResponse<?> jsonresponse = null;

		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Advertiser adv = (Advertiser) jsonresponse.getData();
		adv.setDomain("http://www.updatedadvertiser.com");
		adv.setVersion(0);

		try {
			adv = (Advertiser) jt1.save(adv);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Advertiser updatedAdvertiser = (Advertiser) jsonresponse.getData();
		assertEquals("http://www.updatedadvertiser.com", updatedAdvertiser.getDomain());

	}

	/**
	 * Create Strategy.
	 * 
	 * @throws ClientException
	 */
	@Test
	public void testStrategyPost() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);

		Strategy str = new Strategy();
		str.setName("TestStrategy");
		str.setBudget(new BigDecimal(100.12f));
		str.setCampaignId(349751);
		str.setFrequencyType(freqType.asap);
		str.setFrequencyAmount(10);
		str.setFrequencyInterval(freqInt.day);
		str.setGoalType(goalType.spend);
		str.setGoalValue(new BigDecimal("12.12"));
		str.setMaxBid(new BigDecimal(10f));
		str.setPacingAmount(new BigDecimal(10f));
		str.setType(type.REM);
		str.setUseCampaignStart(false);

		str.setUseCampaignEnd(true);

		// str.setStart_date("2016-09-22T21:42:29+0000");
		// str.setEnd_date("2016-10-15T21:42:29+0000");
		// 2016-10-22T16:28:35+0530
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

		cal.roll(Calendar.DATE, true);
		Date startDate = cal.getTime();
		str.setStartDate(startDate);

		/*
		 * cal.roll(Calendar.DATE, true); cal.roll(Calendar.MONTH, true); Date
		 * endd = cal.getTime(); str.setEndDate(endd);
		 */
		try {
			str = jt1.save(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		QueryCriteria query = QueryCriteria.builder().setCollection("strategies").setEntity(str.getId()).setGetAll(true)
				.build();
		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		Strategy strategyCreated = (Strategy) jsonresponse.getData();
		// assertEquals("TestStrategy", strategyCreated.getName());
		assertEquals(349751, strategyCreated.getCampaignId());
		assertEquals(goalType.spend, strategyCreated.getGoalType());
		assertEquals(type.REM, strategyCreated.getType());
		assertEquals(freqType.asap, strategyCreated.getFrequencyType());
		assertEquals(freqInt.day, strategyCreated.getFrequencyInterval());
	}

	/**
	 * Strategy Update.
	 * 
	 * @throws ClientException
	 */
	@Test
	public void testStrategyUpdatePost() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, productionKey);

		QueryCriteria query = QueryCriteria.builder().setCollection("strategies").setEntity(2196344).build();

		JsonResponse<?> jsonresponse = null;

		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Strategy str = (Strategy) jsonresponse.getData();

		str.setName("UpdatedTestStrategy");
		str.setDescription("description updated");

		try {
			str = jt1.save(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		Strategy strategyUpdated = (Strategy) jsonresponse.getData();
		assertEquals("description updated", strategyUpdated.getDescription());
	}

	/**
	 * Create Strategy Audience Segments.
	 * 
	 * @throws ClientException
	 */
	@Test
	public void testStrategyAudienceSegmentsPost() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);

		Strategy str = new Strategy();
		str.setId(2205653);
		str.setAudienceSegmentExcludeOp(Strategy.audSegExc.OR);
		str.setAudienceSegmentIncludeOp(Strategy.audSegInc.OR);
		List<Segments> asList = new ArrayList<Segments>();

		asList.add(new Segments(691, Segments.restrictions.INCLUDE, Segments.audSegExc.OR, Segments.audSegInc.OR));
		str.setAudienceSegments(asList);
		try {
			str = jt1.save(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Segments> audienceSeg = str.getAudienceSegments();
		assertNotNull(audienceSeg);
	}

	@Test
	public void testStrategyDomainRestrictionPost() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);

		Strategy str = new Strategy();
		str.setId(1377524);
		List<StrategyDomain> sdList = new ArrayList<StrategyDomain>();

		sdList.add(new StrategyDomain("google.com", restrictions.EXCLUDE));
		sdList.add(new StrategyDomain("gmail.com", restrictions.INCLUDE));
		str.setStrategyDomainRestrictions(sdList);

		try {
			str = jt1.save(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(str);
	}

	/**
	 * create Strategy Day Parts Post.
	 * 
	 * @throws ClientException
	 */
	@Test
	public void testStrategyDayParts() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, productionKey);

		StrategyDayPart strategyDayPart = new StrategyDayPart();

		strategyDayPart.setDays(daysEnum.U);
		strategyDayPart.setEndHour(23);
		strategyDayPart.setStartHour(0);
		strategyDayPart.setStrategyId(2196344);
		strategyDayPart.setUserTime(true);

		try {
			strategyDayPart = (StrategyDayPart) jt1.save(strategyDayPart);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		assertNotNull(strategyDayPart);
	}

	/**
	 * Strategy Day Parts Update.
	 * 
	 * @throws ClientException
	 */
	@Test
	public void testStrategyDayPartsUpdate() throws ClientException, ParseException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);

		QueryCriteria query = QueryCriteria.builder().setCollection("strategies").setEntity(1377524)
				.setChild("day_parts").build();

		JsonResponse<?> jsonresponse = null;

		jsonresponse = jt1.get(query);

		List<?> strategyDayPartList = (ArrayList<?>) jsonresponse.getData();
		StrategyDayPart strategyDayPart = (StrategyDayPart) strategyDayPartList.get(0);

		strategyDayPart.setDays(daysEnum.M);
		strategyDayPart.setEndHour(20);

		try {
			strategyDayPart = (StrategyDayPart) jt1.save(strategyDayPart);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		strategyDayPartList = (ArrayList<?>) jsonresponse.getData();
		StrategyDayPart updatedStrategyDayPart = (StrategyDayPart) strategyDayPartList.get(0);
		assertEquals(20, updatedStrategyDayPart.getEndHour());

	}

	/**
	 * Associate pixel to a strategy.
	 * 
	 * @throws ClientException
	 */
	@Test
	public void testPixelTargetingToStrategy() throws ClientException {

		TerminalOne jt1 = new TerminalOne(user, password, apiKey);

		QueryCriteria query = QueryCriteria.builder().setCollection("strategies").setEntity(2195001).build();

		JsonResponse<?> jsonresponse = null;

		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Strategy str = (Strategy) jsonresponse.getData();

		// include
		str.setIncludePixels(926800);

		// exclude
		str.setExcludePixels(987860);
		
		Strategy strategy=null;

		try {
			strategy = jt1.save(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(strategy);

	}

	/**
	 * Create Organization.
	 * 
	 * @throws ClientException
	 */
	@Test
	public void testOrganizationPost() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);
		QueryCriteria query = QueryCriteria.builder().setCollection("organizations").setEntity(101558).build();

		JsonResponse<?> jsonresponse = null;

		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Organization org = (Organization) jsonresponse.getData();
		org.setId(101558);

		org.setAddress1("14 World Trade Center");
		org.setZip("10008");
		org.setTerminated(false);

		try {
			org = (Organization) jt1.save(org);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		Organization orgCreated = (Organization) jsonresponse.getData();
		assertEquals(101558, orgCreated.getId());
		// assertEquals("TestOrg", orgCreated.getName());
	}

	/**
	 * Delete strategy concept.
	 * 
	 */
	@Test
	public void testStrategyConceptDelete() {
		TerminalOne T1;
		JsonResponse<?> jr = null;
		StrategyConcept sc = new StrategyConcept();
		sc.setId(2903693);
		try {
			T1 = new TerminalOne(user, password, apiKey);
			jr = T1.delete(sc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(jr);
	}

	/**
	 * Strategy Concept update.
	 * 
	 * @throws ClientException
	 */
	@Test
	public void testStrategyConceptUpdatePost() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);

		QueryCriteria query = QueryCriteria.builder().setCollection("strategy_concepts").setEntity(3627058).build();

		JsonResponse<?> jsonresponse = null;

		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		StrategyConcept strategyConcept = (StrategyConcept) jsonresponse.getData();
		strategyConcept.setStatus(false);

		try {
			strategyConcept = (StrategyConcept) jt1.save(strategyConcept);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		StrategyConcept updatedStrategyConcept = (StrategyConcept) jsonresponse.getData();
		assertEquals(false, updatedStrategyConcept.isStatus());

	}

	@Test
	public void testStrategyConceptPost() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);

		StrategyConcept sc = new StrategyConcept();
		sc.setStrategyId(2196344);
		sc.setConceptId(1126309);
		sc.setStatus(true);

		try {
			sc = (StrategyConcept) jt1.save(sc);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		assertNotNull(sc);

	}

	/**
	 * Create Strategy Supply Sources.
	 * 
	 * @throws ClientException
	 */
	@Test
	public void testStrategySupplySources() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);

		StrategySupplySource strategySupplySource = new StrategySupplySource();
		strategySupplySource.setStrategyId(2195001);
		strategySupplySource.setSupplySourceId(17);

		try {
			strategySupplySource = (StrategySupplySource) jt1.save(strategySupplySource);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		assertEquals(17, strategySupplySource.getSupplySourceId());
	}

	/**
	 * Delete Strategy Day Parts.
	 * 
	 * @throws ClientException
	 * @throws ParseException
	 * 
	 */
	@Test
	public void testStrategyDayPartDelete() throws ClientException, ParseException {
		TerminalOne t1 = new TerminalOne(user, password, productionKey);
		QueryCriteria query = QueryCriteria.builder().setCollection("strategy_day_parts").setEntity(2044892).build();
		JsonResponse<?> jsonresponse = t1.get(query);
		StrategyDayPart sc = (StrategyDayPart) jsonresponse.getData();
		JsonResponse<? extends T1Entity> jr = null;
		try {
			jr = t1.delete(sc);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jr);
	}

	/**
	 * Create Campaign margin.
	 * 
	 * @throws ClientException
	 */
	@Test
	public void testCampaignMarginPost() throws ClientException {
		TerminalOne t1 = new TerminalOne(user, password, apiKey);

		Campaign camp = new Campaign();
		camp.setId(349751);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		camp.setMargins(cal.getTime(), (double) 5.02145);
		cal.add(Calendar.DATE, -2);
		camp.setMargins(cal.getTime(), (double) 10.01);
		cal.add(Calendar.DATE, -3);
		camp.setMargins(cal.getTime(), (double) 11.5656665);
		cal.add(Calendar.DATE, -4);
		camp.setMargins(cal.getTime(), (double) 12.25);
		cal.add(Calendar.DATE, -5);
		camp.setMargins(cal.getTime(), (double) 13.1);

		try {
			camp = (Campaign) t1.save(camp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(camp);

	}

	/**
	 * Create Concept.
	 * 
	 * @throws ClientException
	 */
	@Test
	public void testConceptPost() throws ClientException {
		TerminalOne t1 = new TerminalOne(user, password, apiKey);

		Concept camp = new Concept();
		camp.setAdvertiserId(182395);
		camp.setName("TestConcept1");
		camp.setStatus(true);

		try {
			camp = (Concept) t1.save(camp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(camp);

	}

	/**
	 * Concept update.
	 * 
	 * @throws ClientException
	 */
	@Test
	public void testConceptPostUpdate() throws ClientException {
		TerminalOne t1 = new TerminalOne(user, password, apiKey);
		QueryCriteria query = QueryCriteria.builder().setCollection("concepts").setEntity(813124).build();

		JsonResponse<?> jsonresponse = null;

		try {
			jsonresponse = t1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Concept concept = (Concept) jsonresponse.getData();
		concept.setName("TestConceptUpdated");
		try {
			concept = (Concept) t1.save(concept);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			jsonresponse = t1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Concept updatedConcept = (Concept) jsonresponse.getData();

		assertEquals("TestConceptUpdated", updatedConcept.getName());

	}

	/**
	 * Create ChildPixel.
	 * 
	 * @throws ClientException
	 */
	@Test
	public void testChildPixelPost() throws ClientException {
		TerminalOne t1 = new TerminalOne(user, password, apiKey);

		ChildPixel px = new ChildPixel();
		px.setBundleId(681293);
		px.setDistributed(false);
		px.setPixelType("event");
		px.setTag("http://pixel.mathtag.com/event/js?mt_id=605863&amp;mt_adid=124678");

		try {
			px = (ChildPixel) t1.save(px);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(px);

	}

	/**
	 * Child Pixel Update.
	 * 
	 * @throws ClientException
	 */
	@Test
	public void testChildPixelPostUpdate() throws ClientException {
		TerminalOne t1 = new TerminalOne(user, password, apiKey);
		QueryCriteria query = QueryCriteria.builder().setCollection("pixels").setEntity(154580).build();

		JsonResponse<?> jsonresponse = null;

		try {
			jsonresponse = t1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ChildPixel px = (ChildPixel) jsonresponse.getData();
		px.setDistributed(true);

		try {
			px = (ChildPixel) t1.save(px);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			jsonresponse = t1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ChildPixel updatedPx = (ChildPixel) jsonresponse.getData();

		assertEquals(true, updatedPx.isDistributed());

	}

	@Test
	public void testSiteListAddDomainsPost() throws ClientException {
		TerminalOne t1 = new TerminalOne(user, password, apiKey);
		SiteList sl = new SiteList();
		sl.setId(99059);
		List<String> domains = new ArrayList<String>();

		domains.add("abc.com");
		domains.add("google.com");

		sl.setDomains(domains);

		try {
			sl = (SiteList) t1.save(sl);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(null, sl);

	}

	@Test
	public void testSiteListAssignmentToCampaign() throws ClientException {

		TerminalOne t1 = new TerminalOne(user, password, apiKey);
		Campaign cmp = new Campaign();
		cmp.setId(340177);

		ArrayList<SiteList> siteList = new ArrayList<SiteList>();
		siteList.add(new SiteList(99058, true));
		siteList.add(new SiteList(99059, false));

		cmp.setSiteLists(siteList);

		try {
			cmp = (Campaign) t1.save(cmp);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(cmp);
		assertFalse(cmp.getSiteLists().isEmpty());

	}

	@Test
	public void testSiteListAssignmentToStrategy() throws ClientException {

		TerminalOne t1 = new TerminalOne(user, password, apiKey);
		Strategy cmp = new Strategy();
		cmp.setId(2035005);

		Set<SiteList> siteList = new HashSet<SiteList>();
		siteList.add(new SiteList(99058, true));
		siteList.add(new SiteList(99059, false));

		cmp.setSiteLists(siteList);

		try {
			cmp = (Strategy) t1.save(cmp);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(cmp);
		assertFalse(cmp.getSiteLists().isEmpty());

	}

	/**
	 * Create Atomic Creatives.
	 * 
	 * @throws ClientException
	 */
	@Test
	public void testAtomicCreatives() throws ClientException {

		TerminalOne t1 = new TerminalOne(user, password, apiKey);

		AtomicCreative ac = new AtomicCreative();
		ac.setAdServerType(ac.getAdServerType().DART);
		ac.setAdvertiserId(182395);
		ac.setConceptId(1162348);
		ac.setExternalIdentifier("182395AC");
		ac.setFileType(ac.getFileType().jpeg);
		ac.setHeight(72);
		ac.setName("MyTestAtomicCreative");
		ac.setTag("https://ad.doubleclick.net;sz=1x1;ord=[RANDOM_NUMBER]?");
		ac.setTagType(ac.getTagType().IMG);
		ac.setTpasAdTagName("Sample IMG TAG");
		ac.setWidth(72);
		ac.setMraid(false);

		try {
			ac = (AtomicCreative) t1.save(ac);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(ac);

	}

	/**
	 * Atomic Creative Update.
	 * 
	 * @throws ClientException
	 */
	@Test
	public void testAtomicCreativesUpdate() throws ClientException {

		TerminalOne t1 = new TerminalOne(user, password, apiKey);

		QueryCriteria query = QueryCriteria.builder().setCollection("atomic_creatives").setEntity(2692236).build();

		JsonResponse<?> jsonresponse = null;

		try {
			jsonresponse = t1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		AtomicCreative ac = (AtomicCreative) jsonresponse.getData();
		ac.setName("UpdatedAtomicCreative");

		try {
			ac = (AtomicCreative) t1.save(ac);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			jsonresponse = t1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		AtomicCreative updatedAtomicCreative = (AtomicCreative) jsonresponse.getData();
		assertNotNull(updatedAtomicCreative);

	}

	/**
	 * 3PAS creative upload. and approve.
	 * 
	 * @throws ClientException
	 * @throws IOException
	 * @throws ParseException
	 */
	@Test
	public void test3pasCreativeUpload() throws ClientException, IOException, ParseException {
		TerminalOne t1 = new TerminalOne(user, password, apiKey);

		// 3pas first call
		TPASCreativeUpload response = t1.saveTPASCreativeUpload(
				"C:\\Users\\chauhan_n\\Desktop\\t1attachements\\DFA_IFRAME_Tags_GenericPlaceboTestCreative_PlaceboTestAdvertiser-1.txt",
				"ads1", "DFA_IFRAME_Tags_GenericPlaceboTestCreative_PlaceboTestAdvertiser-1");

		// 3pas second call
		TPASCreativeBatchApprove batchApprove = new TPASCreativeBatchApprove();

		batchApprove.setBatchId(response.getBatch().getId());
		batchApprove.setAdvertiserId("165615");
		batchApprove.setBatchIndex("1", null, null);
		batchApprove.setBatchIndex("4", null, null);
		batchApprove.setBatchIndex("3", null, null);
		JsonResponse<? extends T1Entity> finalJsonResponse = null;

		finalJsonResponse = t1.saveTPASCreativeUploadBatch(batchApprove);

		assertNotNull(finalJsonResponse);
	}

	/**
	 * T1AS creative assets upload and approve.
	 * 
	 * @throws ClientException
	 * @throws IOException
	 */
	@Test
	public void testTOneASCreativeAssetUpload() throws ClientException, IOException {
		TerminalOne t1 = new TerminalOne(user, password, productionKey);

		TOneASCreativeAssetsUpload response = t1.saveTOneASCreativeAssetsUpload(
				"D:\\MediaMath\\t1attachements\\coco_guitars.zip", "coco_guitars.zip", "t1asfileupload");

		assertNotNull(response);

		TOneASCreativeAssetsApprove creativeAssetsApprove = new TOneASCreativeAssetsApprove();
		creativeAssetsApprove.create(false, "182395", "http://ad.vendor.com/clicktracker/?id=1234",
				"http://theactuallandingpage.com", "vidjeo.mp4",
				"vidjeo.mp4", "1162348");

		JsonResponse<? extends T1Entity> secondresponse = t1.saveTOneASCreativeAssetsApprove(creativeAssetsApprove);
		assertNotNull(secondresponse.getData());
	}

	@Test
	public void testTOneASCreativeAssetUploadMultiple() throws ClientException, IOException {
		TerminalOne t1 = new TerminalOne(user, password, productionKey);

		T1File t1File = new T1File("disney_captamerica_300x250.jpg", "disney_captamerica_300x250.jpg",
				"D:\\MediaMath\\html5\\disney_captamerica_300x250.jpg");
		T1File t2File = new T1File("disney_captamerica_300x250.jpg", "disney_captamerica_300x250.zip",
				"D:\\MediaMath\\html5\\disney_captamerica_300x250.zip");
		T1File t3File = new T1File(null, null, null);

		List<T1File> fileList = new ArrayList<>();

		fileList.add(t1File);
		fileList.add(t2File);
		fileList.add(t3File);

		TOneASCreativeAssetsUpload response = t1.saveTOneASCreativeAssetsUpload(fileList);

		assertNotNull(response);

		TOneASCreativeAssetsApprove creativeAssetsApprove = new TOneASCreativeAssetsApprove();
		creativeAssetsApprove.create(false, "182395", "http://ad.vendor.com/clicktracker/?id=1234",
				"http://theactuallandingpage.com", "disney_captamerica_300x250", "disney_captamerica_300x250.jpg",
				"1162348");

		JsonResponse<? extends T1Entity> secondresponse = t1.saveTOneASCreativeAssetsApprove(creativeAssetsApprove);
		assertNotNull(secondresponse.getData());
	}

	/**
	 * this test will only work on production. t1.mediamath.com
	 * 
	 * @throws ClientException
	 * @throws IOException
	 * @throws ParseException
	 */
	@Test
	public void testVideoCreative() throws ClientException, IOException, ParseException {
		// will work only on production.
		TerminalOne t1 = new TerminalOne(user, password, productionKey);

		VideoCreative videoCreative = new VideoCreative();
		videoCreative.setName("videoCreative2");
		videoCreative.setStartTime(1468486396);
		videoCreative.setLandingUrl("http://www.somedomain.com");
		videoCreative.setAdvertiser(122631);
		videoCreative.setEndTime(1470009600);
		videoCreative.setConcept(847527);
		videoCreative.setClickthroughUrl("http://www.somedomain.com");
		videoCreative.setVendors(847527);
		videoCreative.setVendors(847528);
		videoCreative.setVendors(847529);

		VideoCreativeResponse saveResponse = t1.saveVideoCreatives(videoCreative);

		// upload the file.
		String filePath = "C:\\Users\\chauhan_n\\Desktop\\t1attachements\\blah1234.flv";
		String fileName = "blah1234.flv";
		VideoCreativeResponse uploadResponse = t1.uploadVideoCreative(filePath, fileName, saveResponse.getCreativeId());

		// check video creative status VideoCreativeUploadStatus uploadStatus =
		t1.getVideoCreativeUploadStatus(uploadResponse.getCreativeId());

		assertNotNull(saveResponse);
		assertNotNull(saveResponse.getCreativeId());

		assertNotNull(uploadResponse);
		assertNotNull(uploadResponse.getStatus());
	}

	@Test
	public void testStrategyDealsPost() throws ClientException {

		TerminalOne t1 = new TerminalOne(user, password, apiKey);
		Strategy cmp = new Strategy();
		cmp.setId(2145568);

		Set<Integer> deals = new HashSet<>();
		deals.add(172901);
		deals.add(173101);
		deals.add(172912);
		cmp.setDealIds(deals);
		try {
			cmp = (Strategy) t1.save(cmp);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(cmp);
		assertFalse(cmp.getDeals().isEmpty());
	}

	@Test
	public void testStrategyTargetingSegmentsPost() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);

		Strategy str = new Strategy();
		str.setId(2196344);
		str.setTargetingSegmentExcludeOp(Strategy.tgtSegExc.OR);
		str.setTargetingSegmentIncludeOp(Strategy.tgtSegInc.OR);
		List<StrategyTargetingSegment> tsList = new ArrayList<StrategyTargetingSegment>();

		tsList.add(new StrategyTargetingSegment(4569, "INCLUDE", new BigDecimal(2.5f), "OR"));

		str.setStrategyTargetingSegments(tsList);
		try {
			str = jt1.save(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<StrategyTargetingSegment> targetingSeg = str.getStrategyTargetingSegments();
		assertFalse(targetingSeg.isEmpty());
	}

	@Test
	public void testStrategyDayPartsPost() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);

		Strategy str = new Strategy();
		str.setId(2195001);

		List<StrategyDayPart> tsList = new ArrayList<StrategyDayPart>();

		tsList.add(new StrategyDayPart(0, 23, StrategyDayPart.daysEnum.T, true));
		tsList.add(new StrategyDayPart(6, 15, StrategyDayPart.daysEnum.U, true));
		tsList.add(new StrategyDayPart(7, 12, StrategyDayPart.daysEnum.W, true));

		str.setStrategyDayParts(tsList);

		try {
			str = jt1.save(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<StrategyDayPart> sdp = str.getStrategyDayParts();
		assertFalse(sdp.isEmpty());
		assertTrue(sdp.get(0).getId() > 0);
	}

	@Test
	public void testStrategyTargetValuePost() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, productionKey);

		Strategy str = new Strategy();
		str.setId(2196344);

		List<TargetValues> tsList = new ArrayList<TargetValues>();

		List<Integer> valueIds2 = new ArrayList<Integer>();
		valueIds2.add(478);
		valueIds2.add(479);

		tsList.add(new TargetValues(TargetValues.codes.ISPX, TargetValues.restrictions.INCLUDE, TargetValues.oper.OR,
				valueIds2));

		str.setTargetValues(tsList);

		try {
			str = jt1.save(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Set<StrategyTarget> sdp = str.getStrategyTarget();
		assertFalse(sdp.isEmpty());
		assertTrue(sdp.iterator().next().getId() > 0);
	}

	@Test
	public void testStrategyTargetDimensionsPost() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, apiKey);

		Strategy str = new Strategy();
		str.setId(2195001);

		TargetDimensions td = new TargetDimensions();
		td.setId(7);
		List<Integer> exclude = new ArrayList<Integer>();
		exclude.add(20);
		exclude.add(22);
		td.setExclude(exclude);

		List<Integer> include = new ArrayList<Integer>();
		include.add(21);
		td.setInclude(include);

		td.setExcludeOp(excludeOp.OR);
		td.setIncludeOp(includeOp.OR);

		str.setTargetDimensions(td);

		try {
			str = jt1.save(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Set<StrategyTarget> sdp = str.getStrategyTarget();
		assertFalse(sdp.isEmpty());
		assertTrue(sdp.iterator().next().getId() > 0);
	}

	@Test
	public void testCampaignCopyPost() throws ClientException, java.text.ParseException {
		TerminalOne t1 = new TerminalOne(user, password, productionKey);

		Campaign camp = new Campaign();
		camp.setId(349751);
		camp.setName("CampaignTest OneCopy");

		Calendar endcal = Calendar.getInstance();
		Calendar startcal = Calendar.getInstance();
		endcal.roll(Calendar.DATE, true);
		endcal.roll(Calendar.MONTH, true);
		Date endd = endcal.getTime();

		// startcal.roll(Calendar.DATE, true);
		// startcal.roll(Calendar.DATE, true);
		Date startd = startcal.getTime();
		camp.setEndDate(endd);
		camp.setStartDate(startd);
		camp.setCopyCampaign(true);

		try {
			camp = (Campaign) t1.save(camp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(camp);
	}

	@Test
	public void testStrategyCopyPost() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, productionKey);

		Strategy str = new Strategy();
		str.setId(2196344);
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

		cal.roll(Calendar.DATE, true);
		Date startDate = cal.getTime();
		str.setStartDate(startDate);
		cal.roll(Calendar.DATE, true);
		cal.roll(Calendar.MONTH, true);
		Date endd = cal.getTime();
		str.setEndDate(endd);

		str.setCopyStrategy(true);

		try {
			str = jt1.save(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(str);
	}

	@Test
	public void testStrategyBulkCopyPost() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, productionKey);

		JsonResponse<? extends T1Entity> jsonResponse = null;
		Strategy str = new Strategy();
		str.setFromCampaignId(332185);
		str.setToCampaignId(377685);

		List<BulkStrategy> bsList = new ArrayList<BulkStrategy>();
		bsList.add(new BulkStrategy(1966119, "BulkCopyTest1", false, false, false, false, false, false, false, false));

		str.setBulkStrategy(bsList);

		try {
			jsonResponse = jt1.bulkCopy(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonResponse);
		assertNotNull(jsonResponse.getData());

	}

	@Test
	public void testCampaignBudgetFlightBulkPost() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, productionKey);

		Campaign cmp = new Campaign();
		cmp.setId(349751);

		Calendar startcal = Calendar.getInstance();
		startcal.roll(Calendar.DATE, true);
		startcal.roll(Calendar.MONTH, true);
		Date startd = startcal.getTime();

		startcal.roll(Calendar.DATE, true);
		startcal.roll(Calendar.MONTH, true);
		Date endd = startcal.getTime();

		startcal.roll(Calendar.DATE, true);
		startcal.roll(Calendar.MONTH, true);
		Date startd1 = startcal.getTime();

		startcal.roll(Calendar.DATE, true);
		startcal.roll(Calendar.MONTH, true);
		Date endd1 = startcal.getTime();

		BudgetFlight bf1 = new BudgetFlight();
		BudgetFlight bf2 = new BudgetFlight();

		bf1.setStartDate(startd);
		bf1.setEndDate(endd);
		bf1.setTotalBudget(new BigDecimal(10000), "USD");
		bf1.setTotalImpressionBudget(120000);

		bf2.setStartDate(startd1);
		bf2.setEndDate(endd1);
		bf2.setTotalBudget(new BigDecimal(15000), "USD");
		bf2.setTotalImpressionBudget(180000);

		cmp.getBudgetFlights().add(bf1);
		cmp.getBudgetFlights().add(bf2);

		Campaign cmpSave = null;
		try {
			cmpSave = jt1.save(cmp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(cmpSave);
		assertTrue(cmpSave.getBudgetFlights().size() >= 1);

	}

	@Test
	public void testCampaignCustomBrainSelectionPost() throws ClientException {

		TerminalOne jt1 = new TerminalOne(user, password, productionKey);
		Campaign cmp = new Campaign();
		cmp.setId(300982);

		CampaignCustomBrainSelection cCBS = new CampaignCustomBrainSelection();
		cCBS.setActive(true);
		cCBS.setSelectionType(SELTYPES.AudienceTarget);
		cCBS.setSelectionId(691);

		ArrayList<CampaignCustomBrainSelection> cCBSList = new ArrayList<>();
		cCBSList.add(cCBS);
		cmp.setCampaignCustomBrainSelection(cCBSList);

		Campaign cmpSave = null;
		try {
			cmpSave = jt1.save(cmp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(cmpSave);
	}


	@Test
	public void testCampaignCustomBrainSelectionBulkPost() throws ClientException {

		TerminalOne jt1 = new TerminalOne(user, password, productionKey);
		Campaign cmp = new Campaign();
		cmp.setId(300982);

		CampaignCustomBrainSelection cCBS = new CampaignCustomBrainSelection();
		cCBS.setActive(true);
		cCBS.setSelectionType(SELTYPES.AudienceTarget);
		cCBS.setSelectionId(691);

		CampaignCustomBrainSelection cCBS1 = new CampaignCustomBrainSelection();
		cCBS1.setActive(true);
		cCBS1.setSelectionType(SELTYPES.AudienceTarget);
		cCBS1.setSelectionId(692);

		ArrayList<CampaignCustomBrainSelection> cCBSList = new ArrayList<>();
		cCBSList.add(cCBS);
		cCBSList.add(cCBS1);
		cmp.setCampaignCustomBrainSelection(cCBSList);

		Campaign cmpSave = null;
		try {
			cmpSave = jt1.save(cmp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(cmpSave);
	}

	@Test
	public void testCampaignCustomBrainSelectionDeletePost() throws ClientException {

		TerminalOne jt1 = new TerminalOne(user, password, productionKey);
		Campaign cmp = new Campaign();
		cmp.setId(338158);

		CampaignCustomBrainSelection cCBS = new CampaignCustomBrainSelection();
		cCBS.setId(143213);
		cCBS.setDeleted(true);

		ArrayList<CampaignCustomBrainSelection> cCBSList = new ArrayList<>();
		cCBSList.add(cCBS);
		cmp.setCampaignCustomBrainSelection(cCBSList);
		Campaign cmpSave = null;
		try {
			cmpSave = jt1.save(cmp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(cmpSave);
	}

	@Test
	public void testUsersPost() throws ClientException {

		TerminalOne jt1 = new TerminalOne(user, password, productionKey);

		User user = new User();

		user.setFirstName("Test");
		user.setLastName("Jitendra");
		user.setTitle("Test+Jitendra");
		user.setName("Test-Jitendra");
		user.setPhone("808888080");
		user.setUsername("testjitendra122@yopmail.com");
		user.setEmail("testjitendra1@yopmail.com");
		user.setPassword("TestPwd");
		user.setLinkLdap(false);
		user.setEditCampaigns(true);
		user.setEditMarginsAndPerformance(true);
		user.setViewOrganizations(true);
		user.setActive(true);
		user.setType("AGENCY");
		user.setScope("SELECT");
		user.setRole("MANAGER");
		user.setViewSegments(false);
		user.setEditSegments(false);
		user.setViewDataDefinition(false);
		user.setEditDataDefinition(false);
		user.setViewDmpReports(false);

		User userSaved = null;
		try {
			userSaved = (User) jt1.save(user);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(userSaved);
	}

	@Test
	public void testUserPermissionPost() throws ClientException {

		TerminalOne jt1 = new TerminalOne(user, password, productionKey);
		User user = new User();
		user.setId(22513);
		Map<String, Integer> permissions = new HashMap<String, Integer>();

		permissions.put("agency_id", 116678);
		user.setPermissionList(permissions);

		User userSaved = null;
		try {
			userSaved = (User) jt1.save(user);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		assertNotNull(userSaved);
		assertNotNull(userSaved.getPermissions());

	}

	@Test
	public void testVideoCreatives() throws ClientException, IOException, ParseException {
		// will work only on production.
		TerminalOne t1 = new TerminalOne(user, password, productionKey);

		VideoCreative videoCreative = new VideoCreative();
		videoCreative.setName("videoCreative2");
		videoCreative.setStartTime(1468486396);
		videoCreative.setLandingUrl("http://www.somedomain.com");
		videoCreative.setAdvertiser(122631);
		videoCreative.setEndTime(1470009600);
		videoCreative.setConcept(847527);
		videoCreative.setClickthroughUrl("http://www.somedomain.com");

		List<EventPixel> eventPixels = new ArrayList<EventPixel>();
		eventPixels.add(new EventPixel(EventPixelsEnum.ImpSkippable.toString(), "http://google.com"));
		eventPixels.add(new EventPixel(EventPixelsEnum.Skip.toString(), "http://yahoo.com"));

		videoCreative.setEventPixels(eventPixels);

		VideoCreativeResponse saveResponse = t1.saveVideoCreatives(videoCreative);

		// upload the file.
		String filePath = "D:\\MediaMath\\t1attachements\\blah1234.flv";
		String fileName = "blah1234.flv";
		VideoCreativeResponse uploadResponse = t1.uploadVideoCreative(filePath, fileName, saveResponse.getCreativeId());

		VideoCreativeUploadStatus status = null;

		// check video creative status VideoCreativeUploadStatus uploadStatus =
		status = t1.getVideoCreativeUploadStatus(uploadResponse.getCreativeId());

		assertNotNull(status);
		assertNotNull(saveResponse);
		assertNotNull(uploadResponse);
		assertNotNull(uploadResponse.getStatus());
	}

	@Test
	public void testVideoCreativesTemp() throws ClientException, IOException, ParseException {
		// will work only on production.
		TerminalOne t1 = new TerminalOne(user, password, productionKey);

		VideoCreative videoCreative = new VideoCreative();
		videoCreative.setName("videoCreative2");
		videoCreative.setStartTime(1468486396);
		videoCreative.setLandingUrl("http://www.somedomain.com");
		videoCreative.setAdvertiser(122631);
		videoCreative.setEndTime(1470009600);
		videoCreative.setConcept(847527);
		videoCreative.setClickthroughUrl("http://www.somedomain.com");

		List<EventPixel> eventPixels = new ArrayList<EventPixel>();
		eventPixels.add(new EventPixel(EventPixelsEnum.ImpSkippable.toString(), "http://google.com"));
		eventPixels.add(new EventPixel(EventPixelsEnum.Skip.toString(), "http://yahoo.com"));

		videoCreative.setEventPixels(eventPixels);

		VideoCreativeResponse saveResponse = t1.saveVideoCreatives(videoCreative);

		// upload the file.
		QueryCriteria query = QueryCriteria.builder().setCollection("creatives")
				.setEntity(Long.parseLong(saveResponse.getCreativeId())).setCreativeType(CreativeType.video)
				.setChild("upload").build();

		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = t1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		VideoCreativeResponse cdr = (VideoCreativeResponse) jsonresponse.getData();

		String filePath = "D:\\MediaMath\\t1attachements\\blah1234.flv";
		String fileName = "blah1234.flv";
		VideoCreativeUploadResponse uploadResponse = t1.videoCreativeUpload(filePath, fileName, cdr.getKey(),
				cdr.getUploader().getHost());

		VideoCreativeUploadStatus status = null;

		// check video creative status VideoCreativeUploadStatus uploadStatus =
		status = t1.getVideoCreativeUploadStatus(saveResponse.getCreativeId());

		assertNotNull(status);
		assertNotNull(saveResponse);
		assertNotNull(uploadResponse);
		assertNotNull(uploadResponse.getStatus());
	}

	@Test
	public void testVideoCreativeupload() throws ClientException, IOException, ParseException {

		TerminalOne t1 = new TerminalOne(user, password, productionKey);
		String filePath = "D:\\MediaMath\\t1attachements\\blah1234.flv";
		String fileName = "blah1234.flv";
		VideoCreativeUploadResponse uploadResponse = t1.videoCreativeUpload(filePath, fileName,
				"5036698 8420ad739f6e3c166cea9ffa7627900cb25ce891", "video-uploader.mathtag.com");
		
		assertNotNull(uploadResponse);

	}

	@Test
	public void testVideoCreativeSave() throws ClientException, IOException, ParseException {
		// will work only on production.
		TerminalOne t1 = new TerminalOne(user, password, productionKey);

		VideoCreative videoCreative = new VideoCreative();
		videoCreative.setName("videoCreative2");
		videoCreative.setStartTime(1468486396);
		videoCreative.setLandingUrl("http://www.somedomain.com");
		videoCreative.setAdvertiser(122631);
		videoCreative.setEndTime(1470009600);
		videoCreative.setConcept(847527);
		videoCreative.setClickthroughUrl("http://www.somedomain.com");

		List<EventPixel> eventPixels = new ArrayList<EventPixel>();
		eventPixels.add(new EventPixel(EventPixelsEnum.ImpSkippable.toString(), "http://google.com"));
		eventPixels.add(new EventPixel(EventPixelsEnum.Skip.toString(), "http://yahoo.com"));

		videoCreative.setEventPixels(eventPixels);

		VideoCreativeResponse saveResponse = t1.saveVideoCreatives(videoCreative);

		assertNotNull(saveResponse);
	}

	@Test
	public void testVideoCreativeUpdate() throws ClientException, IOException, ParseException {
		// will work only on production.

		TerminalOne t1 = new TerminalOne(user, password, productionKey);

		QueryCriteria query = QueryCriteria.builder().setCollection("creatives").setEntity(4791256)
				.setCreativeType(CreativeType.video).build();

		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = t1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CreativeDetailsResponse cdr = (CreativeDetailsResponse) jsonresponse.getData();

		VideoCreative videoCreative = cdr.getDetails();
		videoCreative.setCreativeId(4791256);
		videoCreative.setClickthroughUrl("http://www.mysomedomain.com");

		VideoCreativeResponse saveResponse = t1.saveVideoCreatives(videoCreative);
		assertNotNull(saveResponse);

	}

	@Test
	public void testAddTSForOrganization() throws ClientException, IOException, ParseException {
		// will work only on production.

		TerminalOne one = new TerminalOne(user, password, productionKey);

		Map<String, Long> limitList = new HashMap<String, Long>();
		limitList.put("campaign.advertiser.agency.organization", Long.valueOf(101558));

		FullParamValues fpv = new FullParamValues();
		fpv.setBoolValue(true);

		QueryCriteria query1 = QueryCriteria.builder().setCollection("strategies").setLimit(limitList) // limit
				.setGetAll(true).setFull(fpv) // getAll
				.build();
		JsonResponse<?> jsonresponse1 = one.get(query1);
		@SuppressWarnings("unchecked")
		List<Strategy> strategies = (List<Strategy>) jsonresponse1.getData();

		for (int j = 0; j < strategies.size(); j++) {
			List<StrategyTargetingSegment> tsList = new ArrayList<StrategyTargetingSegment>();
			tsList.add(new StrategyTargetingSegment(17484, "EXCLUDE", new BigDecimal(0.0f), "OR"));
			Strategy strategy = strategies.get(j);
			strategy.setStrategyTargetingSegments(tsList);
			try {
				strategy = one.save(strategy);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			assertNotNull(strategy);
		} // strategy for loop
		assertNotNull(strategies);
	}

	@Test
	public void testAllASForOrganization() throws ClientException, IOException, ParseException {
		// will work only on production.

		TerminalOne one = new TerminalOne(user, password, productionKey);

		Map<String, Long> limitList = new HashMap<String, Long>();
		limitList.put("campaign.advertiser.agency.organization", Long.valueOf(101558));

		FullParamValues fpv = new FullParamValues();
		fpv.setBoolValue(true);

		QueryCriteria query1 = QueryCriteria.builder().setCollection("strategies").setLimit(limitList) // limit
				.setGetAll(true).setFull(fpv) // getAll
				.build();
		JsonResponse<?> jsonresponse1 = one.get(query1);
		@SuppressWarnings("unchecked")
		List<Strategy> strategies = (List<Strategy>) jsonresponse1.getData();

		for (int j = 0; j < strategies.size(); j++) {
			List<Segments> asList = new ArrayList<Segments>();
			asList.add(new Segments(691, Segments.restrictions.INCLUDE, Segments.audSegExc.OR, Segments.audSegInc.OR));

			Strategy strategy = strategies.get(j);
			strategy.setAudienceSegments(asList);
			try {
				System.out.println("getting saved...");
				strategy = one.save(strategy);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			assertNotNull(strategy);
			System.out.println("saved.....");
		} // strategy for loop
		assertNotNull(strategies);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAddTSForOrganizationWithGET() throws ClientException, IOException, ParseException {
		TerminalOne one = new TerminalOne(user, password, productionKey);

		// Get Strategies
		Map<String, Long> limitList = new HashMap<String, Long>();
		limitList.put("campaign.advertiser.agency.organization", Long.valueOf(101558));

		FullParamValues fpv = new FullParamValues();
		fpv.setBoolValue(true);

		QueryCriteria query1 = QueryCriteria.builder().setCollection("strategies").setLimit(limitList) // limit
				.setGetAll(true).setFull(fpv) // getAll
				.build();
		JsonResponse<?> jsonresponse1 = one.get(query1);
		List<Strategy> strategies = (List<Strategy>) jsonresponse1.getData();

		for (int j = 0; j < strategies.size(); j++) {
			Strategy strategy = strategies.get(j);
			// get previous Targeting Segments
			QueryCriteria query = QueryCriteria.builder().setCollection("strategies").setEntity(strategy.getId())
					.setChild("targeting_segments") // limit
					.setFull(fpv) // getAll
					.build();
			JsonResponse<?> jsonresponse = one.get(query);

			List<StrategyTargetingSegment> tsList = new ArrayList<StrategyTargetingSegment>();
			tsList = (List<StrategyTargetingSegment>) jsonresponse.getData();
			// add new TS
			tsList.add(new StrategyTargetingSegment(119, "INCLUDE", new BigDecimal(7.0f), "OR"));

			strategy.setStrategyTargetingSegments(tsList);
			try {
				strategy = one.save(strategy);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			assertNotNull(strategy);
		} // strategy for loop
		assertNotNull(strategies);
	}

	@Test
	public void testVideoCreativesCustomVAST() throws ClientException, IOException, ParseException {
		// will work only on production.
		TerminalOne t1 = new TerminalOne(user, password, productionKey);

		VideoCreative videoCreative = new VideoCreative();
		videoCreative.setName("TestVASTCreative");
		// videoCreative.setStartTime(1468486396);
		videoCreative.setLandingUrl("http://www.vasttestdomain.com");
		videoCreative.setAdvertiser(122631);
		// videoCreative.setEndTime(1470009600);
		videoCreative.setConcept(847527);
		videoCreative.setCustomVASTUrl(
				"https://bs.serving-sys.com/Serving?cn=display&c=23&pl=VAST&pli=22617024&PluID=0&pos=897&ord=7464&cim=1");

		List<EventPixel> eventPixels = new ArrayList<EventPixel>();
		eventPixels.add(new EventPixel(EventPixelsEnum.ImpSkippable.toString(), "http://google.com"));
		eventPixels.add(new EventPixel(EventPixelsEnum.Skip.toString(), "http://yahoo.com"));

		videoCreative.setEventPixels(eventPixels);

		VideoCreativeResponse saveResponse = t1.saveVideoCreatives(videoCreative);

		assertNotNull(saveResponse);
	}

	@Test
	public void testStrategyPostCodesPost() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, productionKey);
		ZipCodesJsonResponse response = null;
		ZipCodes zipCode = new ZipCodes(2187813, ZipCodes.restrictions.EXCLUDE, "D:\\MediaMath\\Noname1.csv", true,
				true, true);
		try {
			response = (ZipCodesJsonResponse) jt1.save(zipCode);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		assertNotNull(response);
	}

	@Test
	public void testContractsPost() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, productionKey);
		Currency curr = new Currency();
		curr.setCurrencyCode("USD");
		curr.setValue(new BigDecimal(100f));

		List<Currency> cList = new ArrayList<Currency>();
		cList.add(curr);

		Contract contract = new Contract();
		contract.setAdaptiveSegmentCpm(cList);
		contract.setContractNumber(1111);
		contract.setCurrencyCode("USD");
		contract.setEvidonPrivacyCostCpm(cList);
		contract.setExternalMediaTrackingCpm(cList);
		contract.setFbxDynamicCpm(cList);
		contract.setManagedServiceFeePct(11);
		contract.setManagedServiceFeeUnit("PCT_TOTAL_COST");
		contract.setOptimizationFeePct(11);
		contract.setOptimizationFeeUnit("PCT_TOTAL_COST");
		contract.setOrganizationId(101558);
		contract.setPeer39QualityFeeCpm(cList);
		contract.setPeer39SafetyFeeCpm(cList);
		contract.setPlatformAccessFeePct(11);
		contract.setPlatformAccessFeeUnit("PCT_TOTAL_COST");
		contract.setPlatformMonthlyMin(11);
		contract.setPmpOptimizationOffFeeCpm(cList);
		contract.setPmpOptimizationOffUnit("PCT_TOTAL_COST");
		contract.setPmpOptimizationOffFeePct(11);
		contract.setPmpOptimizationOnFeeCpm(cList);
		contract.setPmpOptimizationOnUnit("PCT_TOTAL_COST");
		contract.setPmpOptimizationOnFeePct(11);
		contract.setProfitShareFeePct(11);
		contract.setSpendCap(100);
		contract.setT1AsFeeCpm(cList);
		contract.setT1Html5FeeCpm(cList);
		contract.setT1VadsFeeCpm(cList);
		contract.setExcludeAgencyMargin(true);
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

		cal.roll(Calendar.DATE, true);
		Date startDate = cal.getTime();
		contract.setEffectiveStartDate(startDate);

		cal.roll(Calendar.DATE, true);
		cal.roll(Calendar.YEAR, true);
		Date endd = cal.getTime();
		contract.setEffectiveEndDate(endd);

		Contract contractFinal = null;
		try {
			contractFinal = (Contract) jt1.save(contract);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		assertNotNull(contractFinal);
	}

	@Test
	public void testContractsUpdate() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, productionKey);

		Contract ctrt = new Contract();
		ctrt.setId(633);
		ctrt.setSpendCap(100);
		ctrt.setPlatformMonthlyMin(4);
		ctrt.setVersion(3);

		Contract contractFinal = null;
		try {
			contractFinal = (Contract) jt1.save(ctrt);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		assertNotNull(contractFinal);
	}
	
	@Test
	public void testContractsUpdateNew() throws ClientException, ParseException {
		TerminalOne jt1 = new TerminalOne(user, password, productionKey);

		QueryCriteria query = QueryCriteria.builder().setCollection("contracts").setEntity(4447).build();
		JsonResponse<?> jsonresponse = jt1.get(query);	
		
		Contract ctrt = (Contract) jsonresponse.getData();
		ctrt.setSpendCap(100);

		Contract contractFinal = null;
		try {
			contractFinal = (Contract) jt1.save(ctrt);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		assertNotNull(contractFinal);
	}

	@Test
	public void testVendorContractsSave() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password, productionKey);

		VendorContract venContract = new VendorContract();
		venContract.setCampaignId(349751);
		venContract.setPrice(2.8f);
		venContract.setRateCardType("");
		venContract.setUseMmContract(false);
		venContract.setVendorId(632);

		VendorContract contractFinal = null;
		try {
			contractFinal = (VendorContract) jt1.save(venContract);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		assertNotNull(contractFinal);
	}

	@Test
	public void testVendorContractsUpdate() throws ClientException, ParseException {
		TerminalOne jt1 = new TerminalOne(user, password, productionKey);

		FullParamValues fpv = new FullParamValues();
		fpv.setBoolValue(true);
		QueryCriteria query = QueryCriteria.builder().setCollection("vendor_contracts").setEntity(32773).build();
		JsonResponse<?> jsonresponse = jt1.get(query);

		VendorContract vc = (VendorContract) jsonresponse.getData();

		vc.setPrice(2.9f);
		vc.setUseMmContract(false);

		VendorContract contractFinal = null;
		try {
			contractFinal = (VendorContract) jt1.save(vc);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		assertNotNull(contractFinal);
	}

	@Test
	public void testVendorContractsDelete() throws ClientException, ParseException {
		TerminalOne jt1 = new TerminalOne(user, password, productionKey);

		FullParamValues fpv = new FullParamValues();
		fpv.setBoolValue(true);
		QueryCriteria query = QueryCriteria.builder().setCollection("vendor_contracts").setEntity(32774).build();
		JsonResponse<?> jsonresponse = jt1.get(query);

		VendorContract vc = (VendorContract) jsonresponse.getData();

		JsonResponse<? extends T1Entity> jr = null;
		try {
			jr = jt1.delete(vc);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		assertNotNull(jr);
	}
	
	@Test
	public void testStrategyTargetValuePostNew() throws ClientException, ParseException {
		TerminalOne jt1 = new TerminalOne(user, password, productionKey);

		List<Integer> acceptedDevice = new ArrayList<Integer>();
		acceptedDevice.add(301523);
		acceptedDevice.add(301524);
		List<Integer> blockedDevice = new ArrayList<Integer>();
		blockedDevice.add(301526);
		blockedDevice.add(674590);

		List<Integer> acceptedInventory = new ArrayList<Integer>();
		acceptedInventory.add(477326);
		List<Integer> blockedInventory = new ArrayList<Integer>();
		blockedInventory.add(477325);

		List<Integer> acceptedConnection = new ArrayList<Integer>();
		acceptedConnection.add(15);
		List<Integer> blockedConnection = new ArrayList<Integer>();
		blockedConnection.add(13);
		blockedConnection.add(14);

		List<Integer> acceptedIsp = new ArrayList<Integer>();
		acceptedIsp.add(481);
		acceptedIsp.add(492);
		List<Integer> blockedIsp = new ArrayList<Integer>();
		blockedIsp.add(478);

		List<Integer> acceptedBrowser = new ArrayList<Integer>();
		acceptedBrowser.add(45017);
		acceptedBrowser.add(3);
		List<Integer> blockedBrowser = new ArrayList<Integer>();
		blockedBrowser.add(1);
		blockedBrowser.add(2);

		List<Integer> acceptedLocation = new ArrayList<Integer>();
		acceptedLocation.add(37);
		acceptedLocation.add(38);
		List<Integer> blockedLocation = new ArrayList<Integer>();
		blockedLocation.add(20);
		blockedLocation.add(21);

		List<TargetValues> tsList = new ArrayList<TargetValues>();

		TargetValues targetValues1 = new TargetValues(TargetValues.codes.DVCE, TargetValues.restrictions.INCLUDE,
				TargetValues.oper.OR, acceptedDevice);
		tsList.add(targetValues1);

		TargetValues targetValues2 = new TargetValues(TargetValues.codes.DVCE, TargetValues.restrictions.EXCLUDE,
				TargetValues.oper.OR, blockedDevice);
		tsList.add(targetValues2);

		TargetValues targetValues3 = new TargetValues(TargetValues.codes.INVT, TargetValues.restrictions.INCLUDE,
				TargetValues.oper.OR, acceptedInventory);
		tsList.add(targetValues3);

		TargetValues targetValues4 = new TargetValues(TargetValues.codes.INVT, TargetValues.restrictions.EXCLUDE,
				TargetValues.oper.OR, blockedInventory);
		tsList.add(targetValues4);

		TargetValues targetValues5 = new TargetValues(TargetValues.codes.CSPD, TargetValues.restrictions.INCLUDE,
				TargetValues.oper.OR, acceptedConnection);
		tsList.add(targetValues5);

		TargetValues targetValues6 = new TargetValues(TargetValues.codes.CSPD, TargetValues.restrictions.EXCLUDE,
				TargetValues.oper.OR, blockedConnection);
		tsList.add(targetValues6);

		TargetValues targetValues7 = new TargetValues(TargetValues.codes.ISPX, TargetValues.restrictions.INCLUDE,
				TargetValues.oper.OR, acceptedIsp);
		tsList.add(targetValues7);

		TargetValues targetValues8 = new TargetValues(TargetValues.codes.ISPX, TargetValues.restrictions.EXCLUDE,
				TargetValues.oper.OR, blockedIsp);
		tsList.add(targetValues8);

		TargetValues targetValues9 = new TargetValues(TargetValues.codes.BSER, TargetValues.restrictions.INCLUDE,
				TargetValues.oper.OR, acceptedBrowser);
		tsList.add(targetValues9);

		TargetValues targetValues10 = new TargetValues(TargetValues.codes.BSER, TargetValues.restrictions.EXCLUDE,
				TargetValues.oper.OR, blockedBrowser);
		tsList.add(targetValues10);

		TargetValues targetValues11 = new TargetValues(TargetValues.codes.REGN, TargetValues.restrictions.INCLUDE,
				TargetValues.oper.OR, acceptedLocation);
		tsList.add(targetValues11);

		TargetValues targetValues12 = new TargetValues(TargetValues.codes.REGN, TargetValues.restrictions.EXCLUDE,
				TargetValues.oper.OR, blockedLocation);
		tsList.add(targetValues12);

		Strategy strategy = new Strategy();
		strategy.setId(2195001);
		strategy.setTargetValues(tsList);
		strategy = jt1.save(strategy);
		
		Set<StrategyTarget> sdp = strategy.getStrategyTarget();
		assertFalse(sdp.isEmpty());
		assertTrue(sdp.iterator().next().getId() > 0);
	}

}