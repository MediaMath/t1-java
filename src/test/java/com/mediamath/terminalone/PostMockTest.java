package com.mediamath.terminalone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.times;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.mediamath.terminalone.exceptions.ClientException;
import com.mediamath.terminalone.exceptions.ParseException;
import com.mediamath.terminalone.functional.PostFunctionalTestIT;
import com.mediamath.terminalone.models.Advertiser;
import com.mediamath.terminalone.models.Agency;
import com.mediamath.terminalone.models.AtomicCreative;
import com.mediamath.terminalone.models.BudgetFlight;
import com.mediamath.terminalone.models.BulkStrategy;
import com.mediamath.terminalone.models.Campaign;
import com.mediamath.terminalone.models.CampaignCustomBrainSelection;
import com.mediamath.terminalone.models.CampaignCustomBrainSelection.SELTYPES;
import com.mediamath.terminalone.models.Concept;
import com.mediamath.terminalone.models.JsonResponse;
import com.mediamath.terminalone.models.Organization;
import com.mediamath.terminalone.models.Segments;
import com.mediamath.terminalone.models.SiteList;
import com.mediamath.terminalone.models.Strategy;
import com.mediamath.terminalone.models.Strategy.freqInt;
import com.mediamath.terminalone.models.Strategy.freqType;
import com.mediamath.terminalone.models.Strategy.goalType;
import com.mediamath.terminalone.models.Strategy.pacInt;
import com.mediamath.terminalone.models.Strategy.pacType;
import com.mediamath.terminalone.models.Strategy.siteSelect;
import com.mediamath.terminalone.models.Strategy.supplyType;
import com.mediamath.terminalone.models.Strategy.type;
import com.mediamath.terminalone.models.StrategyConcept;
import com.mediamath.terminalone.models.StrategyDayPart;
import com.mediamath.terminalone.models.StrategyDomain;
import com.mediamath.terminalone.models.StrategyDomain.restrictions;
import com.mediamath.terminalone.models.StrategyTarget;
import com.mediamath.terminalone.models.StrategyTargetingSegment;
import com.mediamath.terminalone.models.T1Entity;
import com.mediamath.terminalone.models.T1File;
import com.mediamath.terminalone.models.T1User;
import com.mediamath.terminalone.models.TOneASCreativeAssetsApprove;
import com.mediamath.terminalone.models.TOneASCreativeAssetsUpload;
import com.mediamath.terminalone.models.TPASCreativeBatchApprove;
import com.mediamath.terminalone.models.TPASCreativeUpload;
import com.mediamath.terminalone.models.TargetDimensions;
import com.mediamath.terminalone.models.TargetDimensions.excludeOp;
import com.mediamath.terminalone.models.TargetDimensions.includeOp;
import com.mediamath.terminalone.models.TargetValues;
import com.mediamath.terminalone.models.User;
import com.mediamath.terminalone.models.VideoCreative;
import com.mediamath.terminalone.models.VideoCreativeResponse;
import com.mediamath.terminalone.models.ZipCodes;
import com.mediamath.terminalone.models.ZipCodesJsonResponse;

@RunWith(MockitoJUnitRunner.class)
public class PostMockTest {

	private static Properties testConfig = new Properties();

	private static String AGENCY_RESPONSE = null;

	private static String CAMPAIGN_RESPONSE = null;

	private static String ADVERTISER_RESPONSE = null;

	private static String STRATEGY_AUDIENCE_SEGMENTS_RESPONSE = null;

	private static String STRATEGY_DOMAIN_RESTRICTIONS_RESPONSE = null;

	private static String ORGANIZATION_RESPONSE = null;

	private static String CAMPAIGN_MARGIN_RESPONSE = null;

	private static String CONCEPT_RESPONSE = null;

	private static String ATOMICCREATIVE_RESPONSE = null;

	private static String THREEPASCREATIVE_UPLOAD_SECONDCALL_RESPONSE = null;

	private static String THREEPASCREATIVE_UPLOAD_FIRSTCALL_RESPONSE = null;

	private static String TONEAS_CREATIVE_UPLOAD_FIRSTCALL = null;

	private static String TONEAS_CREATIVE_UPLOAD_SECONDCALL = null;

	private static String STRATEGY_CONCEPT_DELETE = null;

	private static String STRATEGY_DAY_PART_DELETE = null;

	private static String STRATEDY_DAYPART_META_ERROR_CHK = null;

	private static String ADVERTISER_ERROR_RESPONSE = null;

	private static String STRATEGY_CONCEPT_RESPONSE = null;

	private static String STRATEGY_DAYPART_RESPONSE = null;

	private static String STRATEGY_DOMAIN_RESPONSE = null;

	private static String VIDEO_CREATIVE_SAVE = null;

	private static String VIDEO_CREATIVE_UPLOAD = null;

	private static String VIDEO_CREATIVE_UPLOAD_STATUS = null;

	private static String STRATEGY_RESPONSE = null;

	private static String STRATEGY_SITELIST_RESPONSE = null;

	private static String CAMPAIGN_SITELIST_RESPONSE = null;

	private static String SITELIST_DOMAIN_RESPONSE = null;

	private static String STRATEGY_DEALS_RESPONSE = null;
	private static String STRATEGY_DAY_PARTS_RESPONSE = null;
	private static String STRATEGY_TGT_VALUES_RESPONSE = null;
	private static String STRATEGY_TGT_SEGMENT_RESPONSE = null;
	private static String STRATEGY_TGT_DIMENSIONS_RESPONSE = null;

	private static String STRATEGY_COPY_RESPONSE = null;
	private static String CAMPAIGN_COPY_RESPONSE = null;
	private static String STRATEGY_BULK_COPY_RESPONSE = null;
	
	private static String CAMPAIGN_BUDGET_FLIGHT_SINGLE = null;
	private static String CAMPAIGN_BUDGET_FLIGHT_BULK = null;
	private static String CAMPAIGN_BUDGET_FLIGHT_UPDATE=null;
	private static String CAMPAIGN_BUDGET_FLIGHT_DELETE=null;
	
	private static String CAMPAIGN_CUSTOM_BRAIN_SELECTION_SINGLE= null;
	private static String CAMPAIGN_CUSTOM_BRAIN_SELECTION_BULK= null;
	private static String CAMPAIGN_CUSTOM_BRAIN_SELECTION_DELETE = null;
	
	private static String USER_RESPONSE = null;
	private static String USER_PERMISSIONS_RESPONSE = null;
	
	private static String ZIPCODE_RESPONSE= null;
	
	private static String TONEAS_CREATIVE_UPLOAD_FIRSTCALL_MULTIPLE = null;

	private static String TONEAS_CREATIVE_UPLOAD_SECONDCALL_MULTIPLE = null;

	private static String LOGIN = null;
	
	private static String OAUTH = null;

	@Mock
	Connection connectionmock;

	@InjectMocks
	TerminalOne t1 = new TerminalOne();

	@Mock
	Response response;
	
	@Mock
	Response responseLogin;

	@BeforeClass
	public static void init() throws Exception {

		InputStream input = PostFunctionalTestIT.class.getClassLoader().getResourceAsStream("mocktest.properties");
		testConfig.load(input);
		LOGIN = testConfig.getProperty("t1.mock.loginResponse");
		OAUTH = testConfig.getProperty("t1.mock.oauthResponse");
		AGENCY_RESPONSE = testConfig.getProperty("t1.mock.save.agency.response");
		CAMPAIGN_RESPONSE = testConfig.getProperty("t1.mock.save.campaign.response");
		ADVERTISER_RESPONSE = testConfig.getProperty("t1.mock.save.advertiser.response");
		STRATEGY_AUDIENCE_SEGMENTS_RESPONSE = testConfig
				.getProperty("t1.mock.save.strategy.audience.segments.response");
		STRATEGY_DOMAIN_RESTRICTIONS_RESPONSE = testConfig
				.getProperty("t1.mock.save.strategy.domain.restriction.response");
		ORGANIZATION_RESPONSE = testConfig.getProperty("t1.mock.save.organization.response");
		CAMPAIGN_MARGIN_RESPONSE = testConfig.getProperty("t1.mock.save.campaign.margin.response");
		CONCEPT_RESPONSE = testConfig.getProperty("t1.mock.save.concept.response");
		ATOMICCREATIVE_RESPONSE = testConfig.getProperty("t1.mock.save.atomiccreative.response");
		THREEPASCREATIVE_UPLOAD_SECONDCALL_RESPONSE = testConfig
				.getProperty("t1.mock.save.3pas.creative.upload.secondcall.response");
		THREEPASCREATIVE_UPLOAD_FIRSTCALL_RESPONSE = testConfig
				.getProperty("t1.mock.save.3pas.creative.upload.firstcall.response");
		TONEAS_CREATIVE_UPLOAD_FIRSTCALL = testConfig
				.getProperty("t1.mock.save.toneas.creative.assets.upload.firstcall.response");
		TONEAS_CREATIVE_UPLOAD_SECONDCALL = testConfig
				.getProperty("t1.mock.save.toneas.creative.assets.upload.secondcall.response");
		STRATEGY_CONCEPT_DELETE = testConfig.getProperty("t1.mock.delete.strategy_concept.response");
		STRATEGY_DAY_PART_DELETE = testConfig.getProperty("t1.mock.delete.strategy_day_part.response");
		STRATEDY_DAYPART_META_ERROR_CHK = testConfig.getProperty("t1.mock.save.strategy_day_part_meta_error.response");
		ADVERTISER_ERROR_RESPONSE = testConfig.getProperty("t1.mock.save.advertiser_error.response");
		STRATEGY_CONCEPT_RESPONSE = testConfig.getProperty("t1.mock.save.strategy_concept.response");
		STRATEGY_DAYPART_RESPONSE = testConfig.getProperty("t1.mock.save.strategy_day_part.response");
		STRATEGY_DOMAIN_RESPONSE = testConfig.getProperty("t1.mock.save.strategy_domain.response");
		VIDEO_CREATIVE_SAVE = testConfig.getProperty("t1.mock.save.video_creative.response");
		VIDEO_CREATIVE_UPLOAD = testConfig.getProperty("t1.mock.upload.video_creative.response");
		VIDEO_CREATIVE_UPLOAD_STATUS = testConfig.getProperty("t1.mock.status.video_creative_upload.response");
		STRATEGY_RESPONSE = testConfig.getProperty("t1.mock.save.strategy.response");
		STRATEGY_SITELIST_RESPONSE = testConfig.getProperty("t1.mock.save.strategy_sitelist.response");
		CAMPAIGN_SITELIST_RESPONSE = testConfig.getProperty("t1.mock.save.campaign_sitelist.response");
		SITELIST_DOMAIN_RESPONSE = testConfig.getProperty("t1.mock.save.sitelist_domains.response");
		STRATEGY_DEALS_RESPONSE = testConfig.getProperty("t1.mock.save.strategy_deals.reaponse");
		STRATEGY_DAY_PARTS_RESPONSE = testConfig.getProperty("t1.mock.save.strategy_day_parts.response");
		STRATEGY_TGT_VALUES_RESPONSE = testConfig.getProperty("t1.mock.save.strategy_target_values.response");
		STRATEGY_TGT_SEGMENT_RESPONSE = testConfig.getProperty("t1.mock.save.strategy_target_segments.response");
		STRATEGY_TGT_DIMENSIONS_RESPONSE = testConfig.getProperty("t1.mock.save.strategy_target_dimensions.response");
		STRATEGY_COPY_RESPONSE = testConfig.getProperty("t1.mock.save.strategy_copy.response");
		CAMPAIGN_COPY_RESPONSE = testConfig.getProperty("t1.mock.save.campaign_copy.response");
		STRATEGY_BULK_COPY_RESPONSE = testConfig.getProperty("t1.mock.save.strategy_bulkcopy.response");
		CAMPAIGN_BUDGET_FLIGHT_SINGLE = testConfig.getProperty("t1.mock.save.budget_flight_single.response");
		CAMPAIGN_BUDGET_FLIGHT_BULK = testConfig.getProperty("t1.mock.save.budget_flight_bulk.response");
		CAMPAIGN_BUDGET_FLIGHT_UPDATE = testConfig.getProperty("t1.mock.update.budget_flight_bulk.response");
		CAMPAIGN_BUDGET_FLIGHT_DELETE = testConfig.getProperty("t1.mock.delete.budget_flight_bulk.response");
		CAMPAIGN_CUSTOM_BRAIN_SELECTION_SINGLE = testConfig.getProperty("t1.mock.save.custom_brain_selection_single.response");
		CAMPAIGN_CUSTOM_BRAIN_SELECTION_BULK =  testConfig.getProperty("t1.mock.save.custom_brain_selection_bulk.response");
		CAMPAIGN_CUSTOM_BRAIN_SELECTION_DELETE =  testConfig.getProperty("t1.mock.delete.custom_brain_selection.response");
		USER_PERMISSIONS_RESPONSE =  testConfig.getProperty("t1.mock.save.user.permissions.response");
		USER_RESPONSE =  testConfig.getProperty("t1.mock.save.user.response");
		TONEAS_CREATIVE_UPLOAD_FIRSTCALL_MULTIPLE = testConfig
				.getProperty("t1.mock.save.toneas.creative.assets.upload.multiple.firstcall.response");
		TONEAS_CREATIVE_UPLOAD_SECONDCALL_MULTIPLE = testConfig
				.getProperty("t1.mock.save.toneas.creative.assets.upload.multiple.secondcall.response");
		ZIPCODE_RESPONSE = testConfig.getProperty("t1.mock.save.zipcode.response");
	}

	@After
	public final void tearDown() throws InterruptedException {
		Thread.sleep(5000);
	}

	@Test
	public void testOauthTokenAuthentication() throws ClientException {
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), isNull(T1User.class)))
		.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(OAUTH);
		t1.authenticate("user","password","clientId","clientSecret");

		assertEquals(true, t1.isAuthenticated());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAgencyPostWithMocks() throws Exception {

		Agency agency = new Agency();
		agency.setName("TestAgency");
		agency.setOrganizationId(100048);
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);

		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(AGENCY_RESPONSE);

		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			agency = (Agency) t1.save(agency);
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),Mockito.any(T1User.class));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		assertNotNull(agency);
		assertEquals("TestAgency", agency.getName());
		assertEquals(100048, agency.getOrganizationId());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testCampaignPostWithMocks() throws ClientException, java.text.ParseException {

		Campaign camp = new Campaign();
		camp.setName("CampaignTest One 271778");
		camp.setAdServerFee(10.01, null);
		camp.setAdServerId(9);
		camp.setAdvertiserId(122631);
		camp.setConversionType("variable");
		camp.setConversionVariableMinutes(1);
		camp.setGoalType(Campaign.goalTypes.cpa);
		camp.setGoalValue(100, null);
		camp.setServiceType(Campaign.servTypes.SELF);

		Calendar cal = Calendar.getInstance();

		cal.roll(Calendar.DATE, true);
		cal.roll(Calendar.MONTH, true);
		Date endd = cal.getTime();

		camp.setEndDate(endd);

		camp.setStartDate(new Date());

		camp.setPcWindowMinutes(1);
		camp.setSpendCapAmount(10, null);
		camp.setTotalBudget(100, null);
		camp.setUseMmFreq(false);
		camp.setMeritPixelId(800781);
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);

		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(CAMPAIGN_RESPONSE);

		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			camp = (Campaign) t1.save(camp);
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),Mockito.any(T1User.class));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(camp);
		assertEquals(800781, camp.getMeritPixelId());

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAdvertiserPostWithMocks() throws ClientException {

		Advertiser adv = new Advertiser();
		adv.setAdServerId(9);
		adv.setAgencyId(109308);
		adv.setDomain("http://www.advertiser.com");
		adv.setName("TestAdvertiser");
		adv.setVerticalId(11);

		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(ADVERTISER_RESPONSE);

		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			adv = (Advertiser) t1.save(adv);
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(adv);
		assertEquals("TestAdvertiser", adv.getName());
		assertEquals(109308, adv.getAgencyId());
		assertEquals(166077, adv.getId());

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAdvertiserPostWithErrorMocks() {

		Advertiser adv = new Advertiser();
		adv.setAdServerId(9);
		adv.setAgencyId(116677);

		Advertiser adv1 = null;
		try {
			Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
			Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
			
			Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
					.thenReturn(response);
			Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(ADVERTISER_ERROR_RESPONSE);
		} catch (ClientException ce) {

		}
		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			adv1 = (Advertiser) t1.save(adv);
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),Mockito.any(T1User.class));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertNull(adv1);
		} catch (ClientException ce) {
			// assertNull(adv1);
		}

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testStrategyPostWithMocks() throws ClientException {

		Strategy str = new Strategy();
		str.setName("TestStrategy");
		str.setBudget(100.12f);
		str.setCampaignId(233131);
		str.setFrequencyType(freqType.asap);
		str.setFrequencyAmount(10);
		str.setFrequencyInterval(freqInt.day);
		str.setGoalType(goalType.spend);
		str.setGoalValue(12.12f);
		str.setMaxBid(10f);
		str.setPacingAmount(10f);
		str.setPacingType(pacType.even);
		str.setPacingInterval(pacInt.day);
		str.setAudienceSegmentExcludeOp(Strategy.audSegExc.OR);
		str.setAudienceSegmentIncludeOp(Strategy.audSegInc.OR);
		str.setBidAggresiveness(50);
		str.setBidPriceIsMediaOnly(false);
		str.setDescription("Test Strategy");
		str.setEffectiveGoalValue(12.12f);
		str.setMediaType(Strategy.mediaType.DISPLAY);
		str.setImpressionPacingInterval(freqInt.day);
		str.setRunOnAllExchanges(false);
		str.setRunOnAllPmp(false);
		str.setRunOnDisplay(false);
		str.setRunOnMobile(false);
		str.setRunOnStreaming(false);
		str.setSiteSelectiveness(siteSelect.REDUCED);
		str.setSiteRestrictionTransparentUrls(false);
		str.setStatus(false);
		str.setSupplyType(supplyType.RTB);
		str.setUseMmFreq(false);
		str.setUseOptimization(false);
		str.setZoneName("America/New_York");
		str.setType(type.REM);
		str.setUseCampaignStart(false);
		str.setUseCampaignEnd(false);

		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(STRATEGY_RESPONSE);

		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			str = t1.save(str);
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(str);
		assertEquals("TestStrategy", str.getName());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testStrategyAudienceSegmentsPostWithMocks() throws ClientException {

		Strategy str = new Strategy();
		str.setId(1377524);
		str.setAudienceSegmentExcludeOp(Strategy.audSegExc.OR);
		str.setAudienceSegmentIncludeOp(Strategy.audSegInc.OR);
		List<Segments> asList = new ArrayList<Segments>();

		asList.add(new Segments(691, Segments.restrictions.INCLUDE, Segments.audSegExc.OR, Segments.audSegInc.OR));
		str.setAudienceSegments(asList);
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);

		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(STRATEGY_AUDIENCE_SEGMENTS_RESPONSE);

		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			str = t1.save(str);
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(str);
		assertEquals(1552491, str.getStrategyAudienceSegments().get(0).getId());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testStrategyDomainRestrictionPostWithMocks() throws ClientException {

		Strategy str = new Strategy();
		str.setId(1089192);
		List<StrategyDomain> sdList = new ArrayList<StrategyDomain>();

		sdList.add(new StrategyDomain("google.com", restrictions.EXCLUDE));
		sdList.add(new StrategyDomain("gmail.com", restrictions.INCLUDE));
		str.setStrategyDomainRestrictions(sdList);
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);

		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(STRATEGY_DOMAIN_RESTRICTIONS_RESPONSE);

		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			str = t1.save(str);
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNull(str);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testStrategyDomainPost() throws ClientException {

		StrategyDomain sd = new StrategyDomain();
		sd.setDomain("google.com");
		sd.setRestriction(StrategyDomain.restrictions.INCLUDE);
		sd.setStrategyId(2035005);
		// sd.setVersion(0);

		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(STRATEGY_DOMAIN_RESPONSE);

		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			sd = (StrategyDomain) t1.save(sd);
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		assertNotNull(sd);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testOrganizationPostWithMocks() throws ClientException {

		Organization org = new Organization();
		org.setId(100048);
		ArrayList<String> listOrgType = new ArrayList<String>();
		listOrgType.add("buyer");
		org.setOrgType(listOrgType);
		org.setName("ACME Org updated");
		org.setAddress1("First Lane, New York");
		org.setCity("New York");
		org.setState("NY");
		org.setContactName("Michele");
		org.setZip("800293");
		org.setCountry("US");
		org.setMmContactName("Mark");
		org.setPhone("408 345 7758");
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);

		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(ORGANIZATION_RESPONSE);

		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			org = (Organization) t1.save(org);
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(org);
		assertEquals("ACME Org updated", org.getName());
		assertEquals(100048, org.getId());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testCampaignMarginPost() throws ClientException {

		Campaign camp = new Campaign();
		camp.setId(269896);
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
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);

		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(CAMPAIGN_MARGIN_RESPONSE);

		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			camp = (Campaign) t1.save(camp);
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNull(camp);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testConceptPost() throws ClientException {

		Concept camp = new Concept();
		camp.setAdvertiserId(122631);
		camp.setName("TestConcept1");
		camp.setStatus(true);

		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(CONCEPT_RESPONSE);

		try {

			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			camp = (Concept) t1.save(camp);
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(camp);
		assertEquals("TestConcept1", camp.getName());

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testStrategyConceptPost() throws ClientException {

		StrategyConcept sc = new StrategyConcept();
		sc.setStatus(true);
		sc.setStrategyId(2035005);
		sc.setConceptId(1064563);
		sc.setVersion(0);
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);

		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(STRATEGY_CONCEPT_RESPONSE);

		try {

			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			sc = (StrategyConcept) t1.save(sc);
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(sc);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testStrategyDayPartPost() throws ClientException {

		StrategyDayPart sc = new StrategyDayPart();
		sc.setStatus(true);
		sc.setStrategyId(2035005);
		sc.setDays(StrategyDayPart.daysEnum.M);
		sc.setStartHour(6);
		sc.setEndHour(23);
		sc.setStatus(true);
		sc.setUserTime(true);
		sc.setVersion(0);
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);

		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(STRATEGY_DAYPART_RESPONSE);

		try {

			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			sc = (StrategyDayPart) t1.save(sc);
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(sc);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testStrategyConceptDelete() throws ClientException {

		JsonResponse jr = null;

		StrategyConcept sc = new StrategyConcept();
		sc.setId(4795645);
		sc.setVersion(0);
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);

		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(STRATEGY_CONCEPT_DELETE);

		try {

			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			jr = t1.delete(sc);
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jr);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testStrategyDayPartDelete() throws ClientException {

		JsonResponse jr = null;

		StrategyDayPart sc = new StrategyDayPart();
		sc.setId(1611126);
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);

		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(STRATEGY_DAY_PART_DELETE);

		try {

			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			jr = t1.delete(sc);
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jr);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testStrategyDayPartPostMetaError() throws ClientException {

		JsonResponse jr = null;

		StrategyDayPart sc = new StrategyDayPart();
		StrategyDayPart sc1 = null;
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);

		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(STRATEDY_DAYPART_META_ERROR_CHK);

		try {

			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			sc1 = (StrategyDayPart) t1.save(sc);
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNull(sc1);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testStrategySiteListPost() throws ClientException {

		Strategy cmp = new Strategy();
		cmp.setId(2035005);

		ArrayList<SiteList> siteList = new ArrayList<SiteList>();
		siteList.add(new SiteList(99058, true));
		siteList.add(new SiteList(99059, false));

		cmp.setSiteLists(siteList);
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);

		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(STRATEGY_SITELIST_RESPONSE);

		try {

			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			cmp = (Strategy) t1.save(cmp);
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(cmp);
		assertTrue(!cmp.getSiteLists().isEmpty());

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testStrategyDealsPost() throws ClientException {

		Strategy cmp = new Strategy();
		cmp.setId(2145568);

		ArrayList<Integer> deals = new ArrayList<>();
		deals.add(173102);
		deals.add(173101);
		deals.add(172912);
		cmp.setDealIds(deals);
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);

		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(STRATEGY_DEALS_RESPONSE);

		try {

			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			cmp = (Strategy) t1.save(cmp);
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(cmp);
		assertTrue(!cmp.getDeals().isEmpty());

	}

	@Test
	public void testSiteListAssignmentToCampaign() throws ClientException {

		Campaign cmp = new Campaign();
		cmp.setId(340177);

		ArrayList<SiteList> siteList = new ArrayList<SiteList>();
		siteList.add(new SiteList(99058, true));
		siteList.add(new SiteList(99059, false));

		cmp.setSiteLists(siteList);

		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(CAMPAIGN_SITELIST_RESPONSE);

		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			cmp = (Campaign) t1.save(cmp);
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(cmp);
		assertTrue(!cmp.getSiteLists().isEmpty());

	}

	@Test
	public void testSiteListAddDomainsPost() throws ClientException {
		SiteList sl = new SiteList();
		sl.setId(99059);
		List<String> domains = new ArrayList<String>();

		domains.add("abc.com");
		domains.add("google.com");

		sl.setDomains(domains);
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);

		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(SITELIST_DOMAIN_RESPONSE);

		try {

			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			sl = (SiteList) t1.save(sl);
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(null, sl);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testStrategyTargetingSegmentsPost() throws ClientException {
		Strategy str = new Strategy();
		str.setId(2196344);
		str.setTargetingSegmentExcludeOp(Strategy.tgtSegExc.OR);
		str.setTargetingSegmentIncludeOp(Strategy.tgtSegInc.OR);
		List<StrategyTargetingSegment> tsList = new ArrayList<StrategyTargetingSegment>();

		tsList.add(new StrategyTargetingSegment(4569, "INCLUDE", 2.5f, "OR"));

		str.setStrategyTargetingSegments(tsList);
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);

		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(STRATEGY_TGT_SEGMENT_RESPONSE);
		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			str = t1.save(str);
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<StrategyTargetingSegment> targetingSeg = str.getStrategyTargetingSegments();
		assertTrue(!targetingSeg.isEmpty());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testStrategyDayPartsPost() throws ClientException {
		Strategy str = new Strategy();
		str.setId(2195001);

		List<StrategyDayPart> tsList = new ArrayList<StrategyDayPart>();

		tsList.add(new StrategyDayPart(0, 23, StrategyDayPart.daysEnum.T, true));
		tsList.add(new StrategyDayPart(6, 15, StrategyDayPart.daysEnum.U, true));
		tsList.add(new StrategyDayPart(7, 12, StrategyDayPart.daysEnum.W, true));

		str.setStrategyDayParts(tsList);

		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(STRATEGY_DAY_PARTS_RESPONSE);

		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			str = t1.save(str);
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<StrategyDayPart> sdp = str.getStrategyDayParts();
		assertTrue(!sdp.isEmpty());
		assertTrue(sdp.get(0).getId() > 0);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testStrategyTargetValuePost() throws ClientException {
		Strategy str = new Strategy();
		str.setId(2196344);

		List<TargetValues> tsList = new ArrayList<TargetValues>();

		List<Integer> valueIds2 = new ArrayList<Integer>();
		valueIds2.add(478);
		valueIds2.add(479);

		tsList.add(new TargetValues(TargetValues.codes.ISPX, TargetValues.restrictions.INCLUDE, TargetValues.oper.OR,
				valueIds2));

		str.setTargetValues(tsList);
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);

		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(STRATEGY_TGT_VALUES_RESPONSE);

		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			str = t1.save(str);
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<StrategyTarget> sdp = str.getStrategyTarget();
		assertTrue(!sdp.isEmpty());
		assertTrue(sdp.get(0).getId() > 0);
	}

	@Test
	public void testStrategyTargetDimensionsPost() throws ClientException {

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

		td.setExclude_op(excludeOp.OR);
		td.setInclude_op(includeOp.OR);

		str.setTargetDimensions(td);
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);

		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(STRATEGY_TGT_DIMENSIONS_RESPONSE);

		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			str = t1.save(str);
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		assertNotNull(str);
		assertNotNull(str.getTargetDimensions());

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testCampaignCopyPost() throws ClientException, java.text.ParseException {

		Campaign camp = new Campaign();
		camp.setId(349751);
		camp.setName("CampaignTest OneCopy");

		Calendar endcal = Calendar.getInstance();
		Calendar startcal = Calendar.getInstance();
		endcal.roll(Calendar.DATE, true);
		endcal.roll(Calendar.MONTH, true);
		Date endd = endcal.getTime();

		Date startd = startcal.getTime();
		camp.setEndDate(endd);
		camp.setStartDate(startd);
		camp.setCopyCampaign(true);
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);

		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(CAMPAIGN_COPY_RESPONSE);

		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			camp = (Campaign) t1.save(camp);
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(camp);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testStrategyCopyPost() throws ClientException {

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
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);

		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(STRATEGY_COPY_RESPONSE);

		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			str = t1.save(str);
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(str);
	}

	@Test
	public void testStrategyBulkCopyPost() throws ClientException {

		JsonResponse<? extends T1Entity> jsonResponse = null;
		Strategy str = new Strategy();
		str.setFromCampaignId(332185);
		str.setToCampaignId(377685);

		List<BulkStrategy> bsList = new ArrayList<BulkStrategy>();
		bsList.add(new BulkStrategy(1966119, "BulkCopyTest1", false, false, false, false, false, false, false, false));

		str.setBulkStrategy(bsList);
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);

		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(STRATEGY_BULK_COPY_RESPONSE);

		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			jsonResponse = t1.bulkCopy(str);
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonResponse);
		assertNotNull(jsonResponse.getData());

	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCampaignBudgetFlightPost() throws ClientException {

		Campaign cmp = new Campaign();
		cmp.setId(349751);
		
		Calendar startcal = Calendar.getInstance();
		startcal.roll(Calendar.DATE, true);
		startcal.roll(Calendar.MONTH, true);
		Date startd1 = startcal.getTime();
		
		startcal.roll(Calendar.DATE, true);
		startcal.roll(Calendar.MONTH, true);
		Date endd1 = startcal.getTime();


		BudgetFlight bf1 = new BudgetFlight();
		BudgetFlight bf2 = new BudgetFlight();
		
		bf1.setStartDate(startd1);
		bf1.setEndDate(endd1);
		bf1.setTotalBudget(10000, "USD");
		bf1.setTotalImpressionBudget(120000);
		cmp.getBudgetFlights().add(bf1);
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
		.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(CAMPAIGN_BUDGET_FLIGHT_SINGLE);

		Campaign cmpSave =null;
		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			cmpSave = t1.save(cmp);
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(cmpSave);
		assertTrue(cmpSave.getBudgetFlights().size()>=1);

	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCampaignBudgetFlightUpdatePost() throws ClientException {

		Campaign cmp = new Campaign();
		cmp.setId(349751);
		
		Calendar startcal = Calendar.getInstance();
		startcal.roll(Calendar.DATE, true);
		startcal.roll(Calendar.MONTH, true);
		Date startd1 = startcal.getTime();
		
		startcal.roll(Calendar.DATE, true);
		startcal.roll(Calendar.MONTH, true);
		Date endd1 = startcal.getTime();


		BudgetFlight bf1 = new BudgetFlight();

		bf1.setId(458041);
		bf1.setStartDate(startd1);
		bf1.setEndDate(endd1);
		bf1.setTotalBudget(3000, "USD");
		bf1.setTotalImpressionBudget(120000);
		bf1.setIsRelevant(true);
		bf1.setVersion(2);
		cmp.getBudgetFlights().add(bf1);
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
		.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(CAMPAIGN_BUDGET_FLIGHT_UPDATE);

		Campaign cmpSave =null;
		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			cmpSave = t1.save(cmp);
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(cmpSave);
		assertTrue(cmpSave.getBudgetFlights().size()>=1);

	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCampaignBudgetFlightDeletePost() throws ClientException {

		Campaign cmp = new Campaign();
		cmp.setId(349751);
		
		BudgetFlight bf1 = new BudgetFlight();

		bf1.setId(458042);
		bf1.setVersion(0);
		bf1.setDeleted(true);
		cmp.getBudgetFlights().add(bf1);
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
		.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(CAMPAIGN_BUDGET_FLIGHT_DELETE);

		Campaign cmpSave =null;
		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			cmpSave = t1.save(cmp);
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(cmpSave);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCampaignBudgetFlightBulkPost() throws ClientException {

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
		bf1.setTotalBudget(10000, "USD");
		bf1.setTotalImpressionBudget(120000);
		
		bf2.setStartDate(startd1);
		bf2.setEndDate(endd1);
		bf2.setTotalBudget(15000, "USD");
		bf2.setTotalImpressionBudget(180000);

		cmp.getBudgetFlights().add(bf1);
		cmp.getBudgetFlights().add(bf2);
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
		.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(CAMPAIGN_BUDGET_FLIGHT_BULK);

		Campaign cmpSave =null;
		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			cmpSave = t1.save(cmp);
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(cmpSave);
		assertTrue(cmpSave.getBudgetFlights().size()>=1);

	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCampaignCustomBrainSelectionPost() throws ClientException {

		Campaign cmp = new Campaign();
		cmp.setId(300982);
		
		CampaignCustomBrainSelection cCBS = new CampaignCustomBrainSelection();
		cCBS.setActive(true);
		cCBS.setSelectionType(SELTYPES.AudienceTarget);
		cCBS.setSelectionId(691);
		
		ArrayList<CampaignCustomBrainSelection> cCBSList = new ArrayList<>();
		cCBSList.add(cCBS);
		cmp.setCampaignCustomBrainSelection(cCBSList);
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
		.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(CAMPAIGN_CUSTOM_BRAIN_SELECTION_SINGLE);

		Campaign cmpSave =null;
		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			cmpSave = t1.save(cmp);
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(cmpSave);
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCampaignCustomBrainSelectionBulkPost() throws ClientException {

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
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
		.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(CAMPAIGN_CUSTOM_BRAIN_SELECTION_BULK);

		Campaign cmpSave =null;
		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			cmpSave = t1.save(cmp);
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(cmpSave);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCampaignCustomBrainSelectionDeletePost() throws ClientException {

		Campaign cmp = new Campaign();
		cmp.setId(338158);
		
		CampaignCustomBrainSelection cCBS = new CampaignCustomBrainSelection();
		cCBS.setId(143213);
		cCBS.setDeleted(true);
		
		ArrayList<CampaignCustomBrainSelection> cCBSList = new ArrayList<>();
		cCBSList.add(cCBS);
		cmp.setCampaignCustomBrainSelection(cCBSList);
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
		.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(CAMPAIGN_CUSTOM_BRAIN_SELECTION_DELETE);

		Campaign cmpSave =null;
		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			cmpSave = t1.save(cmp);
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(cmpSave);
	}
	
	@Test
	public void testUsersPost() throws ClientException {
	
		User user = new User();
		
		user.setFirstName("Test");
		user.setLastName("Jitendra");
		user.setTitle("Test+Jitendra");
		user.setName("Test-Jitendra");
		user.setPhone("808888080");
		user.setUsername("testjitendra11@yopmail.com");
		user.setEmail("testjitendra@yopmail.com");
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
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
		.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(USER_RESPONSE);
		
		User userSaved=null;
		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			userSaved = (User) t1.save(user);
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(userSaved);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testUserPermissionPost() throws ClientException {

		User user =  new User();
		user.setId(22512);
		Map<String, Integer> permissions = new HashMap<String, Integer>();
		
		permissions.put("agency_id", 116678);
		user.setPermissionList(permissions);
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
		.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(USER_PERMISSIONS_RESPONSE);
		
		User userSaved=null;
		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			userSaved = (User) t1.save(user);
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		assertNotNull(userSaved);
		assertNotNull(userSaved.getPermissions());
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testStrategyPostCodesPost() throws ClientException {
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(FormDataMultiPart.class),
				Mockito.any(T1User.class))).thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(ZIPCODE_RESPONSE);
		
		ZipCodesJsonResponse response = null;
		ZipCodes zipCode = new ZipCodes(2187813, ZipCodes.restrictions.EXCLUDE, "D:\\MediaMath\\Noname1.csv", true, true, true);
		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			response = (ZipCodesJsonResponse) t1.save(zipCode);
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(FormDataMultiPart.class),
					Mockito.any(T1User.class));
		} catch (ParseException e) {

			e.printStackTrace();
		}

		assertNotNull(response);
	}
	

	@SuppressWarnings("unchecked")
	@Test
	public void testVideoCreativeSave() throws ClientException, IOException, ParseException {

		// STAGE 1 : SAVE--------------------------------------
		VideoCreative videoCreative = new VideoCreative();
		videoCreative.setName("videoCreative Test March2017");
		videoCreative.setStartTime(1488326400);
		videoCreative.setLandingUrl("http://www.somedomain.com");
		videoCreative.setAdvertiser(182395);
		videoCreative.setEndTime(1491004800);
		videoCreative.setConcept(1064563);
		videoCreative.setClickthroughUrl("http://www.somedomain.com");
		videoCreative.setSkippableDuration(15);
		videoCreative.setVendors(1006);
		videoCreative.setVendors(1046);
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);

		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.anyString(), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(VIDEO_CREATIVE_SAVE);

		VideoCreativeResponse saveResponse = null;
		t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
		saveResponse = t1.saveVideoCreatives(videoCreative);
		Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
		Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.anyString(),
				Mockito.any(T1User.class));

		assertNotNull(saveResponse);
		assertNotNull(saveResponse.getCreativeId());

	}

	// @SuppressWarnings("unchecked")
	// @Test
	// public void testVideoCreativeUpload() throws ClientException, IOException, ParseException {
    //
	// 	Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
	// 	Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
	//	
	// 	Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(InputStream.class),
	// 			Mockito.any(T1User.class))).thenReturn(response);
	// 	Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(VIDEO_CREATIVE_UPLOAD);
    //
	// 	t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
	// 	VideoCreativeResponse uploadResponse = t1.uploadVideoCreative("D:\\MediaMat\\t1attachements\\blah1234.flv", "blah1234.flv", String.valueOf(3595840));
    //
	// 	Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
	// 	Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(InputStream.class),
	// 			Mockito.any(T1User.class));
    //
	// 	assertNotNull(uploadResponse);
	// 	assertNotNull(uploadResponse.getStatus());
	// }

	@SuppressWarnings("unchecked")
	@Test
	public void testVideoCreativeUploadStatus() throws ClientException, IOException, ParseException {

		// STAGE 3 : CHECK UPLOAD STATUS--------------------------
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1User.class)))
				.thenReturn(VIDEO_CREATIVE_UPLOAD_STATUS);
		

		// check video creative status VideoCreativeUploadStatus uploadStatus =
		t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
		t1.getVideoCreativeUploadStatus(String.valueOf(3595840));
		Mockito.verify(connectionmock).get(Mockito.anyString(), Mockito.any(T1User.class));
		Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAtomicCreatives() throws ClientException {

		AtomicCreative ac = new AtomicCreative();
		ac.setAdServerType(ac.getAdServerType().DART);
		ac.setAdvertiserId(150577);
		ac.setConceptId(622519);
		ac.setExternalIdentifier("1234567890abcd");
		ac.setFileType(ac.getFileType().jpeg);
		ac.setHeight(72);
		ac.setName("MyTestAtomicCreative");
		ac.setTag("https://ad.doubleclick.net;sz=1x1;ord=[RANDOM_NUMBER]?");
		ac.setTagType(ac.getTagType().IMG);
		ac.setTpasAdTagName("Sample IMG TAG");
		ac.setWidth(72);

		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(ATOMICCREATIVE_RESPONSE);

		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			ac = (AtomicCreative) t1.save(ac);
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(ac);
		assertEquals("MyTestAtomicCreative", ac.getName());

	}

	@SuppressWarnings("unchecked")
	@Test
	public void test3pasCreativeUpload() throws ClientException, IOException {
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(FormDataMultiPart.class),
				Mockito.any(T1User.class))).thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(THREEPASCREATIVE_UPLOAD_FIRSTCALL_RESPONSE, 
				THREEPASCREATIVE_UPLOAD_SECONDCALL_RESPONSE);

		t1.authenticate("abc", "xyz", "adfadslfadkfakjf");

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

		try {
			finalJsonResponse = t1.saveTPASCreativeUploadBatch(batchApprove);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(finalJsonResponse);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testTOneASCreativeAssetUpload() throws ClientException, IOException {
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(FormDataMultiPart.class),
				Mockito.any(T1User.class))).thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(TONEAS_CREATIVE_UPLOAD_FIRSTCALL,
				TONEAS_CREATIVE_UPLOAD_SECONDCALL);

		t1.authenticate("abc", "xyz", "adfadslfadkfakjf");

		// first call
		TOneASCreativeAssetsUpload response = t1.saveTOneASCreativeAssetsUpload(
				"C:\\Users\\chauhan_n\\Desktop\\t1attachements\\JPGs.zip", "JPGs.zip", "t1asfileupload");
		assertNotNull(response);

		TOneASCreativeAssetsApprove creativeAssetsApprove = new TOneASCreativeAssetsApprove();
		creativeAssetsApprove.create(false, "165615", "http://ad.vendor.com/clicktracker/?id=1234",
				"http://theactuallandingpage.com", "BBVA_CaminoaleÔxito_160x600.swf",
				"BBVA_CaminoaleÔxito_160x600.swf", "665888");

		// second call
		JsonResponse<? extends T1Entity> secondresponse = t1.saveTOneASCreativeAssetsApprove(creativeAssetsApprove);
		assertNotNull(secondresponse.getData());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testTOneASCreativeAssetUploadMultiple() throws ClientException, IOException {
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class))).thenReturn(responseLogin);
		Mockito.when(responseLogin.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(FormDataMultiPart.class),
				Mockito.any(T1User.class))).thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(TONEAS_CREATIVE_UPLOAD_FIRSTCALL_MULTIPLE,
				TONEAS_CREATIVE_UPLOAD_SECONDCALL_MULTIPLE);

		t1.authenticate("abc", "xyz", "adfadslfadkfakjf");

		// first call
		T1File t1File = new T1File("disney_captamerica_300x250.jpg", "disney_captamerica_300x250.jpg", "D:\\MediaMath\\html5\\disney_captamerica_300x250.jpg");
		T1File t2File = new T1File("disney_captamerica_300x250.jpg", "disney_captamerica_300x250.zip", "D:\\MediaMath\\html5\\disney_captamerica_300x250.zip");
		List<T1File> fileList = new ArrayList<>();
		
		fileList.add(t1File);
		fileList.add(t2File);
		
		TOneASCreativeAssetsUpload response = t1.saveTOneASCreativeAssetsUpload(fileList);
		assertNotNull(response);

		TOneASCreativeAssetsApprove creativeAssetsApprove = new TOneASCreativeAssetsApprove();
		creativeAssetsApprove.create(false, "182395", "http://ad.vendor.com/clicktracker/?id=1234",
				"http://theactuallandingpage.com", "disney_captamerica_300x250",
				"disney_captamerica_300x250.jpg", "1162348");

		// second call
		JsonResponse<? extends T1Entity> secondresponse = t1.saveTOneASCreativeAssetsApprove(creativeAssetsApprove);
		assertNotNull(secondresponse.getData());
	}

}
