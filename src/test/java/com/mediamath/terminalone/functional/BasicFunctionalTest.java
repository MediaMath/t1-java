package com.mediamath.terminalone.functional;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mediamath.terminalone.QueryCriteria;
import com.mediamath.terminalone.TerminalOne;
import com.mediamath.terminalone.Exceptions.ClientException;
import com.mediamath.terminalone.Exceptions.ParseException;
import com.mediamath.terminalone.models.Advertiser;
import com.mediamath.terminalone.models.Agency;
import com.mediamath.terminalone.models.AtomicCreative;
import com.mediamath.terminalone.models.AudienceSegment;
import com.mediamath.terminalone.models.Campaign;
import com.mediamath.terminalone.models.Concept;
import com.mediamath.terminalone.models.Data;
import com.mediamath.terminalone.models.JsonResponse;
import com.mediamath.terminalone.models.Organization;
import com.mediamath.terminalone.models.Segments;
import com.mediamath.terminalone.models.Strategy;
import com.mediamath.terminalone.models.Strategy.freq_int;
import com.mediamath.terminalone.models.Strategy.freq_type;
import com.mediamath.terminalone.models.Strategy.goal_type;
import com.mediamath.terminalone.models.Strategy.type;
import com.mediamath.terminalone.models.StrategyConcept;
import com.mediamath.terminalone.models.StrategyDayPart;
import com.mediamath.terminalone.models.StrategyDomain;
import com.mediamath.terminalone.models.StrategyDomain.restrictions;
import com.mediamath.terminalone.models.StrategySupplySource;
import com.mediamath.terminalone.models.SupplySource;
import com.mediamath.terminalone.models.T1Entity;
import com.mediamath.terminalone.models.TOneASCreativeAssetsApprove;
import com.mediamath.terminalone.models.TOneASCreativeAssetsUpload;
import com.mediamath.terminalone.models.ThreePASCreativeBatchApprove;
import com.mediamath.terminalone.models.ThreePASCreativeUpload;
import com.mediamath.terminalone.models.VideoCreative;
import com.mediamath.terminalone.models.VideoCreativeResponse;
import com.mediamath.terminalone.models.VideoCreativeUploadStatus;
import com.mediamath.terminalone.utils.ConditionQuery;
import com.mediamath.terminalone.utils.Filters;
import com.mediamath.terminalone.utils.FullParamValues;
import com.mediamath.terminalone.utils.QueryParamValues;

public class BasicFunctionalTest {


	private static Properties testConfig = new Properties();
	
	private static String user = null;
	
	private static String password = null;
	
	private static String api_key = null;
	
	private static String production_key = null;
	
	@BeforeClass
	public static void init() throws Exception{
		InputStream input = BasicFunctionalTest.class.getClassLoader().getResourceAsStream("test.properties");
		testConfig.load(input);
		user = testConfig.getProperty("username");
		password = testConfig.getProperty("password");
		api_key = testConfig.getProperty("sandbox_api_key");
		production_key = testConfig.getProperty("production_api_key");
	}
	
	@After
	public final void tearDown() throws InterruptedException {
		Thread.sleep(5000);
	}
	
	@Test
	public void testJTerminalOneStringStringString() throws ClientException {
		TerminalOne t1;
		t1 = new TerminalOne("nitesh.chauhan@xoriant.com", "xoriant123#","e34f74vnubr9uxasz2n7bdfv");
		assertEquals(true, t1.isAuthenticated());
	}
	
	@Test
	public void testAgencyPost() throws ClientException {
		TerminalOne t1 = new TerminalOne(user, password,api_key);
		
		Agency agency = new Agency();
		agency.setName("TestAgency");
		agency.setOrganization_id(100048);
		try {
			agency = t1.save(agency);
			System.out.println(agency.getId());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		QueryCriteria query = QueryCriteria.builder()
									.setCollection("agencies")
									.setEntity(agency.getId())
									.setGetAll(true)
									.build();
		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = t1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(jsonresponse);
		Agency agencyCreated = (Agency)jsonresponse.getData();
		assertEquals("TestAgency", agencyCreated.getName());
		assertEquals(100048, agencyCreated.getOrganization_id());
		
	}
	
	@Test
	public void testCampaignPost() throws ClientException, java.text.ParseException {
		TerminalOne t1 = new TerminalOne(user, password,api_key);
		
		Campaign camp = new Campaign();
		camp.setName("TestCamp");
		camp.setAd_server_fee(10.01, null);
		camp.setAd_server_id(9);
		camp.setAdvertiser_id(122631);
		camp.setConversion_type("variable");
		camp.setConversion_variable_minutes(1);
		camp.setGoal_type(Campaign.goal_types.cpe);
		camp.setGoal_value(100,null);
		camp.setService_type(Campaign.serv_types.SELF);
		
		Calendar cal = Calendar.getInstance();
		
		cal.roll(Calendar.DATE, true);
		cal.roll(Calendar.MONTH, true);
		Date endd = cal.getTime();
		
		camp.setEnd_date(endd);
		
		camp.setStart_date(new Date());
		
		camp.setPc_window_minutes(1);
		camp.setSpend_cap_amount(10,null);
		camp.setTotal_budget(100, null);
		camp.setUse_mm_freq(false);
		camp.setMerit_pixel_id(800781);
		
		try {
			camp = t1.save(camp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		QueryCriteria query = QueryCriteria.builder()
									.setCollection("campaigns")
									.setEntity(camp.getId())
									.setGetAll(true)
									.build();
		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = t1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(jsonresponse);
		Campaign campaignCreated = (Campaign)jsonresponse.getData();
		assertEquals("TestCamp", campaignCreated.getName());
		assertEquals(122631, campaignCreated.getAdvertiser_id());
		assertEquals(Campaign.goal_types.cpe, campaignCreated.getGoal_type());
		assertEquals(Campaign.serv_types.SELF, campaignCreated.getService_type());
		assertEquals(800781, campaignCreated.getMerit_pixel_id());	
	}
	
	
	@Test
	public void testAdvertiserPost() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password,api_key);
		
		Advertiser adv = new Advertiser();
		adv.setAd_server_id(9);
		adv.setAgency_id(109308);
		adv.setDomain("http://www.advertiser.com");
		adv.setName("TestAdvertiser");
		adv.setVertical_id(11);
		try{
			adv = jt1.save(adv);
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		QueryCriteria query = QueryCriteria.builder()
				.setCollection("advertisers")
				.setEntity(adv.getId())
				.setGetAll(true)
				.build();
		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(jsonresponse);
		Advertiser advertiserCreated = (Advertiser)jsonresponse.getData();
		assertEquals("TestAdvertiser", advertiserCreated.getName());
		assertEquals(109308, advertiserCreated.getAgency_id());
		assertEquals(9, advertiserCreated.getAd_server_id());
		assertEquals(11, advertiserCreated.getVertical_id());
	}
	
	@Test
	public void testStrategyPost() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password,api_key);
		
		Strategy str = new Strategy();
		str.setName("TestStrategy");
		str.setBudget(100.12f);
		str.setCampaign_id(267881);
		str.setFrequency_type(freq_type.asap);
		str.setFrequency_amount(10);
		str.setFrequency_interval(freq_int.day);
		str.setGoal_type(goal_type.spend);
		str.setGoal_value(12.12f);
		str.setMax_bid(10f);
		str.setPacing_amount(10f);
		str.setType(type.REM);
		str.setUse_campaign_start(false);
		str.setStart_date(new Date());
		str.setUse_campaign_end(false);
		
	//	str.setStart_date("2016-09-22T21:42:29+0000");
		
	//	str.setEnd_date("2016-10-15T21:42:29+0000");
		//				 2016-10-22T16:28:35+0530
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		cal.roll(Calendar.DATE, true);
		cal.roll(Calendar.MONTH, true);
		Date endd = cal.getTime();
		
		str.setEnd_date(endd);
				
		try{
			str = jt1.save(str);
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		QueryCriteria query = QueryCriteria.builder()
				.setCollection("strategies")
				.setEntity(str.getId())
				.setGetAll(true)
				.build();
		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(jsonresponse);
		Strategy strategyCreated = (Strategy)jsonresponse.getData();
		assertEquals("TestStrategy", strategyCreated.getName());
		assertEquals(267881, strategyCreated.getCampaign_id());
		assertEquals(goal_type.spend, strategyCreated.getGoal_type());
		assertEquals(type.REM,strategyCreated.getType());
		assertEquals(freq_type.asap, strategyCreated.getFrequency_type());
		assertEquals(freq_int.day, strategyCreated.getFrequency_interval());
	}
	
	
	@Test
	public void testStrategyAudienceSegmentsPost() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password,api_key);
		
		Strategy str = new Strategy();
		str.setId(1377457);	
		str.setAudience_segment_exclude_op(Strategy.aud_seg_exc.OR);
		str.setAudience_segment_include_op(Strategy.aud_seg_inc.OR);
		List<Segments> asList = new ArrayList<Segments>();
		
		asList.add(new Segments(691, Segments.restrictions.INCLUDE, Segments.aud_seg_exc.OR, Segments.aud_seg_inc.OR));
		str.setAudience_segments(asList);
		try{
			str = jt1.save(str);
			System.out.println(str);
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Segments> audienceSeg = str.getAudience_segments();
		System.out.println(audienceSeg.get(0).getId());
	}
	
	@Test
	public void testStrategyDomainRestrictionPost() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password,api_key);
		
		Strategy str = new Strategy();
		str.setId(1089192);	
		List<StrategyDomain> sdList = new ArrayList<StrategyDomain>();
		
		sdList.add(new StrategyDomain("google.com", restrictions.EXCLUDE));
		sdList.add(new StrategyDomain("gmail.com", restrictions.INCLUDE));
		str.setStrategy_domain_restrictions(sdList);
		
		try{
			str = jt1.save(str);
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testOrganizationPost() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password,api_key);
		
		Organization org = new Organization();
		org.setId(100048);
		ArrayList<String> listOrgType = new ArrayList<String>();
		listOrgType.add("buyer");
		org.setOrg_type(listOrgType);
		org.setName("TestOrg");
		org.setAddress_1("First Lane, New York");
		org.setCity("New York");
		org.setState("NY");
		org.setContact_name("Michele");
		org.setZip("800293");
		org.setCountry("US");
		org.setMm_contact_name("Mark");
		org.setPhone("408 345 7758");
		org.setVersion(120);
		
		try{
			org = jt1.save(org);
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		QueryCriteria query = QueryCriteria.builder()
									.setCollection("organizations")
									.setEntity(100048)
									.build();
		
		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(jsonresponse);
		Organization orgCreated = (Organization) jsonresponse.getData();
		assertEquals(100048, orgCreated.getId());
		assertEquals("TestOrg", orgCreated.getName());
	}
	
	@Test
	public void testStrategyConceptDelete(){
		TerminalOne T1;
		StrategyConcept sc = new StrategyConcept();
		sc.setId(2903693);
		try {
			T1 = new TerminalOne(user, password,api_key);
			JsonResponse jr = T1.delete(sc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testStrategyDayPartDelete(){
		TerminalOne T1;
		StrategyDayPart sc = new StrategyDayPart();
		sc.setId(626678);
		try {
			T1 = new TerminalOne(user, password,api_key);
			JsonResponse<? extends T1Entity> jr = T1.delete(sc);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//check
	@Test
	public void testCampaignMarginPost() throws ClientException {
		TerminalOne t1 = new TerminalOne(user, password,api_key);
		
		Campaign camp = new Campaign();
		camp.setId(233131);
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
			camp = t1.save(camp);
			System.out.println(camp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testConceptPost() throws ClientException {
		TerminalOne t1 = new TerminalOne(user, password,api_key);
		
		Concept camp = new Concept();
		camp.setAdvertiser_id(122631);
		camp.setName("TestConcept1");
		camp.setStatus(true);
		
		
		try {
			camp = t1.save(camp);
			System.out.println(camp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testAtomicCreatives() throws ClientException {
		
		TerminalOne t1 = new TerminalOne(user, password,api_key);
		
		AtomicCreative ac = new AtomicCreative();
		ac.setAd_server_type(ac.getAd_server_type().DART);
		ac.setAdvertiser_id(150577);
		ac.setConcept_id(622519);
		ac.setExternal_identifier("1234567890abcd");
		ac.setFile_type(ac.getFile_type().jpeg);
		ac.setHeight(72);
		ac.setName("MyTestAtomicCreative");
		ac.setTag("https://ad.doubleclick.net;sz=1x1;ord=[RANDOM_NUMBER]?");
		ac.setTag_type(ac.getTag_type().IMG);
		ac.setTpas_ad_tag_name("Sample IMG TAG");
		ac.setWidth(72);
		

		try {
			ac = t1.save(ac);
			System.out.println(ac);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void test3pasCreativeUpload() throws ClientException, IOException, ParseException {
		TerminalOne t1 = new TerminalOne(user, password,api_key);
		
		// 3pas first call
		ThreePASCreativeUpload response = t1.save3pasCreativeUpload("C:\\Users\\chauhan_n\\Desktop\\t1attachements\\DFA_IFRAME_Tags_GenericPlaceboTestCreative_PlaceboTestAdvertiser-1.txt", "ads1" ,"DFA_IFRAME_Tags_GenericPlaceboTestCreative_PlaceboTestAdvertiser-1");
		
		
		// 3pas second call 
		ThreePASCreativeBatchApprove batchApprove = new ThreePASCreativeBatchApprove();

		batchApprove.setBatchId(response.getBatch().getId());
		batchApprove.setAdvertiser_id("165615");
		batchApprove.setBatchIndex("1", null, null);
		batchApprove.setBatchIndex("4", null, null);
		batchApprove.setBatchIndex("3", null, null);
		JsonResponse<? extends T1Entity> finalJsonResponse = null;
		
		finalJsonResponse = t1.save3pasCreativeUploadBatch(batchApprove);
		
		assertNotNull(finalJsonResponse);
	}
	
	@Test
	public void testTOneASCreativeAssetUpload() throws ClientException, IOException {
		TerminalOne t1 = new TerminalOne(user, password,api_key);
		
		TOneASCreativeAssetsUpload response = t1.saveT1ASCreativeAssetsUpload("C:\\Users\\chauhan_n\\Desktop\\t1attachements\\JPGs.zip", "JPGs.zip", "t1asfileupload");
		
		assertNotNull(response);
		
		TOneASCreativeAssetsApprove creativeAssetsApprove = new TOneASCreativeAssetsApprove(); 
		creativeAssetsApprove.create(false, 
				"165615", 
				"http://ad.vendor.com/clicktracker/?id=1234", 
				"http://theactuallandingpage.com", 
				"BBVA_CaminoaleÔxito_160x600.swf", 
				"BBVA_CaminoaleÔxito_160x600.swf", 
				"665888");
		
		JsonResponse<? extends T1Entity> secondresponse = t1.saveTOneASCreativeAssetsApprove(creativeAssetsApprove);
		assertNotNull(secondresponse.getData());
	}
	
	/**
	 * this test will only work on production. t1.mediamath.com
	 * @throws ClientException
	 * @throws IOException
	 * @throws ParseException 
	 */
	@Test
	public void testVideoCreative() throws ClientException, IOException, ParseException {
		// will work only on production.
		TerminalOne t1 = new TerminalOne(user, password,production_key);
		
		VideoCreative videoCreative = new VideoCreative();
		videoCreative.setName("videoCreative2");
		videoCreative.setStartTime(1468486396);
		videoCreative.setLandingUrl("http://www.somedomain.com");
		videoCreative.setAdvertiser(122631);
		videoCreative.setEndTime(1470009600);
		videoCreative.setConcept(847527);
		videoCreative.setClickthroughUrl("http://www.somedomain.com");
	/*	videoCreative.setVendors(847527);
		videoCreative.setVendors(847528);
		videoCreative.setVendors(847529);*/
		
		VideoCreativeResponse saveResponse = t1.saveVideoCreatives(videoCreative);
		
		// depricated step; fethching the upload token
		// response = t1.getVideoCreativesUploadToken(response);
		
		//upload the file.
		String filePath = "C:\\Users\\chauhan_n\\Desktop\\t1attachements\\blah1234.flv";
		//String filePath = "C:\\Users\\chauhan_n\\Desktop\\t1attachements\\progit.pdf";
		String fileName = "blah1234.flv";
		//String fileName = "progit.pdf";
		VideoCreativeResponse uploadResponse = t1.uploadVideoCreative(filePath, fileName, saveResponse.getCreativeId());
		
		//check video creative status
		VideoCreativeUploadStatus uploadStatus = t1.getVideoCreativeUploadStatus(uploadResponse.getCreativeId());
		
		assertNotNull(saveResponse);
		assertNotNull(saveResponse.getCreativeId());
		
		assertNotNull(uploadResponse);
		assertNotNull(uploadResponse.getStatus());
	}


	@Test
	public void testBaiscGetWithChildUsingQueryCriteria() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password,api_key);
		
		QueryCriteria query = QueryCriteria.builder()
									.setCollection("advertisers")
									.setEntity(154161)
									.setInclude(new ConditionQuery("agency", "organization"))
									.build();
		
		
		query = QueryCriteria.builder(query)
							.setInclude(new ConditionQuery("ad_server"))
							.setInclude(new ConditionQuery("vertical"))
							.build();

		
		JsonResponse<?> jsonresponse = null;
		
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(jsonresponse);
		Advertiser advertiser =  (Advertiser) jsonresponse.getData();
		assertNotNull (advertiser.getAgency());
		assertNotNull (advertiser.getVertical_id());
		assertNotNull (advertiser.getAgency());
		
		query = QueryCriteria.builder()
				.setCollection("advertisers")
				.setEntity(154161)
				.build();
	

		jsonresponse = null;
		
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(jsonresponse);
		advertiser =  (Advertiser) jsonresponse.getData();
		assertNull (advertiser.getAgency());
		assertNotNull (advertiser.getVertical_id());
	}
	
	@Test
	public void testBaiscGetWithSortByUsingQueryCriteria() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password,api_key);
		
		QueryCriteria query = QueryCriteria.builder()
									.setCollection("advertisers")
									.setSortBy("-id")
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
	public void testBaiscGetWithPageLimit() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password,api_key);
		
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
		assertEquals(40,advertisers.size());
		
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
		assertEquals(50,advertisers.size());
		
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
		assertEquals(100,advertisers.size());
	}
	
	@Test
	public void testBaiscGetWithPageLimitOffset() throws ClientException  {
		TerminalOne jt1 = new TerminalOne(user, password,api_key);
		
		// default page offset is 0
		QueryCriteria query = QueryCriteria.builder()
									.setCollection("advertisers")
									.setPageLimit(40)
									.build();
		
		JsonResponse<?> jsonresponse_0_to_40 = null;
		
		try {
			jsonresponse_0_to_40 = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(jsonresponse_0_to_40);
		
		query = QueryCriteria.builder()
				.setCollection("advertisers")
				.setPageLimit(40)
				.setPageOffset(40)
				.build();
		
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
		TerminalOne jt1 = new TerminalOne(user, password,api_key);
		
		Map<String, Long> limitList = new HashMap<String, Long>();
		limitList.put("agency", Long.valueOf(111555));
		FullParamValues fpv = new FullParamValues();
		fpv.setStrValue("advertiser");
		QueryCriteria query = QueryCriteria.builder()
									.setCollection("advertisers")
									.setLimit(limitList)
									.setFull(fpv)
									.setPageLimit(100)
									.build();
		
		JsonResponse<?> jsonresponse = null;
		
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(jsonresponse);
		List<Advertiser> advertisers = (List<Advertiser>) jsonresponse.getData();
		
		for (int i=0; i< advertisers.size(); i++){
			Advertiser advertiser = advertisers.get(i);
			assertEquals(111555, advertiser.getAgency_id());
		}
	}
	
	@Test
	public void testBaiscGetWithQuery() throws ClientException {
		
		TerminalOne jt1 = new TerminalOne(user, password,api_key);
		
		FullParamValues fpv = new FullParamValues();
		fpv.setStrValue("advertiser");
		
		QueryCriteria query = QueryCriteria.builder()
									.setCollection("advertisers")
									.setInclude(new ConditionQuery("agency", "organization"))
									.setQuery("agency_id==109308")
									.setPageLimit(100)
									.setFull(fpv)
									.build();
		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(jsonresponse);
		
		List<Advertiser> advertisers = (List<Advertiser>) jsonresponse.getData();
		
		for (int i=0; i< advertisers.size(); i++){
			Advertiser advertiser = advertisers.get(i);
			assertEquals(109308, advertiser.getAgency_id());
		}
		
	}
	
	@Test
	public void testBaiscGetWithGetAll() throws ClientException {
		
		TerminalOne jt1 = new TerminalOne(user, password,api_key);

		QueryCriteria query = QueryCriteria.builder()
									.setCollection("campaigns")
									.setGetAll(true)
									.setSortBy("-updated_on")
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
		
		query = QueryCriteria.builder()
				.setCollection("campaigns")
				.setSortBy("-updated_on")
				.build();
		jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(jsonresponse);
		List<Campaign> campaigns_without_get_all= (List<Campaign>) jsonresponse.getData();
		assertTrue(campaigns_without_get_all.size()<=100);
		assertTrue(campaigns.size() >= campaigns_without_get_all.size());
		
	}
	
	@Test
	public void testBaiscGetWithFullBoolean() throws ClientException {
		
		TerminalOne jt1 = new TerminalOne(user, password,api_key);
		FullParamValues fpv = new FullParamValues();
		fpv.setBoolValue(true);
		QueryCriteria query = QueryCriteria.builder()
				.setCollection("campaigns")
				.setFull(fpv)
				.setPageLimit(1)
				.setSortBy("-updated_on")
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
		assertTrue(campaigns.get(0).getAdvertiser_id() !=0);
		assertNotNull(campaigns.get(0).getGoal_type());
		
		query = QueryCriteria.builder()
		.setCollection("campaigns")
		.setSortBy("-updated_on")
		.setPageLimit(1)
		.build();
		jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(jsonresponse);
		campaigns = (List<Campaign>) jsonresponse.getData();
		assertEquals(0,campaigns.get(0).getAdvertiser_id());
		
	}
	
	@Test
	public void testBaiscGetWithFullString() throws ClientException {
		
		TerminalOne jt1 = new TerminalOne(user, password,api_key);
		FullParamValues fpv = new FullParamValues();
		fpv.setStrValue("campaign");
		QueryCriteria query = QueryCriteria.builder()
									.setCollection("campaigns")
									.setFull(fpv)
									.setSortBy("-updated_on")
									.setPageLimit(10)
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
		assertTrue(campaigns.get(0).getAdvertiser_id() !=0);
		assertNotNull(campaigns.get(0).getGoal_type());
		
		query = QueryCriteria.builder()
				.setCollection("campaigns")
				.setSortBy("-updated_on")
				.setPageLimit(1)
				.build();
		jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(jsonresponse);
		campaigns = (List<Campaign>) jsonresponse.getData();
		assertEquals(0,campaigns.get(0).getAdvertiser_id());
		
	}
	
	@Test
	public void testBaiscGetWithFullList() throws ClientException {
		
		TerminalOne jt1 = new TerminalOne(user, password,api_key);
		FullParamValues fpv = new FullParamValues();
		List<String> newList = new ArrayList<String>();
		newList.add("campaign");
		newList.add("advertiser");
		fpv.setListValue(newList);
		QueryCriteria query = QueryCriteria.builder()
									.setCollection("campaigns")
									.setInclude(new ConditionQuery("advertiser"))
									.setFull(fpv)
									.setSortBy("-updated_on")
									.setPageLimit(1)
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
		assertTrue(campaigns.get(0).getAdvertiser_id() !=0);
		assertNotNull(campaigns.get(0).getGoal_type());
		assertNotNull(campaigns.get(0).getAdvertiser());
		assertTrue(campaigns.get(0).getAdvertiser().getAgency_id() !=0);
		
		query = QueryCriteria.builder()
				.setCollection("campaigns")
				.setInclude(new ConditionQuery("advertiser"))
				.setSortBy("-updated_on")
				.setPageLimit(1)
				.build();
		jsonresponse = null;
		try {
			jsonresponse = jt1.get(query);
		} catch (ClientException | ParseException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(jsonresponse);
		campaigns = (List<Campaign>) jsonresponse.getData();
		assertTrue(campaigns.get(0).getAdvertiser_id() ==0);
		assertNotNull(campaigns.get(0).getAdvertiser());
		assertTrue(campaigns.get(0).getAdvertiser().getAgency_id() ==0);
		
	}
	
	@Test
	public void testBaiscGetWithFind() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password,api_key);
		
		FullParamValues fpv = new FullParamValues();
		fpv.setBoolValue(true);
		
		QueryCriteria query = QueryCriteria.builder()
									.setCollection("advertisers")
									.setInclude(new ConditionQuery("agency"))
									.setQueryParamName("agency_id")
									.setFull(fpv)
									.setQueryOperator(Filters.GREATER_OR_EQUAL)
									.setQueryParams(new QueryParamValues(109308))
									.setPageLimit(100)
									.build();
		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = jt1.find(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			throw new AssertionError();
			
		}
		
		assertNotNull(jsonresponse);
		List<Advertiser> advertisers = (List<Advertiser>) jsonresponse.getData();
		
		for (int i=0; i< advertisers.size(); i++){
			Advertiser advertiser = advertisers.get(i);
			assertTrue(advertiser.getAgency_id() !=0);
			assertTrue(109308 <= advertiser.getAgency_id());
		}
	}
	
	@Test
	public void testBaiscGetWithFind1() throws ClientException {
		
		TerminalOne jt1 = new TerminalOne(user, password,api_key);
		
		Map<String, Long> limitList = new HashMap<String, Long>();
		limitList.put("agency", Long.valueOf(111555));
		QueryCriteria query = QueryCriteria.builder()
									.setCollection("advertisers")
									.setQueryParamName("name")
									.setQueryOperator(Filters.EQUALS)
									.setQueryParams(new QueryParamValues("Retirement"))
									.setPageLimit(100)
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
		
		for (int i=0; i< advertisers.size(); i++){
			Advertiser advertiser = advertisers.get(i);
			assertEquals("Retirement",advertiser.getName());
		}
	
	}
	
	@Test
	public void testBaiscGetWithFind2() throws ClientException  {
		TerminalOne jt1 = new TerminalOne(user, password,api_key);
		
		Map<String, Long> limitList = new HashMap<String, Long>();
		limitList.put("agency", Long.valueOf(111555));
		List<Object> qParams = new ArrayList<Object>();
		qParams.add(154121);
		qParams.add(153226);
		qParams.add(150994);
		QueryCriteria query = QueryCriteria.builder()
									.setCollection("advertisers")
									.setQueryParams(new QueryParamValues("id"))
									.setQueryOperator(Filters.IN)
									.setQueryParams(new QueryParamValues(qParams))
									.setPageLimit(100)
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
		
		for (int i=0; i< advertisers.size(); i++){
			Advertiser advertiser = advertisers.get(i);
			assertTrue(qParams.contains(advertiser.getId()));
		}
	
	}
	
	@Test
	public void testGetWithChildByUsingQC() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password,api_key);
		QueryCriteria query = QueryCriteria.builder()
				.setCollection("strategies")
				.setEntity(1377524)
				.setChild("domain_restrictions")
				.setPageLimit(1)
				.build();

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
		assertNotNull(strategy.getStrategy_domain_restrictions());
		assertEquals(2, strategy.getStrategy_domain_restrictions().size());
	}
	
	@Test
	public void testGetForStrategyConceptsByUsingQC() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password,api_key);
		QueryCriteria query = QueryCriteria.builder()
				.setCollection("strategies")
				.setEntity(1376198)
				.setChild("concepts")
				.setPageLimit(1)
				.build();

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
		TerminalOne jt1 = new TerminalOne(user, password,api_key);
		QueryCriteria query = QueryCriteria.builder()
				.setCollection("strategies")
				.setEntity(1376198)
				.setChild("total_spend")
				.setPageLimit(1)
				.build();

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
	public void testStrategyGetWithConcepts() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password,api_key);
		
		QueryCriteria query = QueryCriteria.builder()
									.setCollection("strategies")
									.setEntity(1377388)
									.setInclude(new ConditionQuery("concepts"))
									.build();
		
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
		TerminalOne jt1 = new TerminalOne(user, password,api_key);
		QueryCriteria query = QueryCriteria.builder()
				.setCollection("strategies")
				.setEntity(1376198)
				.setChild("browser")
				.setPageLimit(1)
				.build();

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
		assertTrue(data.enabled.getActive()=="true");

	}
	
	@Test
	public void testGetForStrategyChildAudienceSegments() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password,api_key);
		QueryCriteria query = QueryCriteria.builder()
				.setCollection("strategies")
				.setEntity(1376198)
				.setChild("audience_segments")
				.setPageLimit(1)
				.build();

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
		assertTrue(data.enabled.getActive()=="true");

	}
	
	@Test
	public void testGetForSupplySources() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password,api_key);
		QueryCriteria query = QueryCriteria.builder()
				.setCollection("supply_sources")
				.setPageLimit(1)
				.build();

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
		assertTrue(supply_sources.size() >0);
	}
	
	@Test
	public void testGetForAudienceSegments() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password,api_key);
		QueryCriteria query = QueryCriteria.builder()
				.setCollection("audience_segments")
				.setPageLimit(1)
				.build();

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
		assertTrue(audience_segments.size() >0);
	}
	
	@Test
	public void testGetForStrategySupplySources() throws ClientException {
		TerminalOne jt1 = new TerminalOne(user, password,api_key);
		Map<String, Long> limitList = new HashMap<String, Long>();
		limitList.put("strategy", Long.valueOf(1376337));
		QueryCriteria query = QueryCriteria.builder()
				.setCollection("strategy_supply_sources")
				.setLimit(limitList)
				.setPageLimit(1)
				.build();

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
		assertTrue(strategy_supply_sources.size() >0);
	}
	
	
}
