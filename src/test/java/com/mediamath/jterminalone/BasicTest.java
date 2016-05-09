package com.mediamath.jterminalone;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.mediamath.jterminalone.Exceptions.ClientException;
import com.mediamath.jterminalone.Exceptions.ParseException;
import com.mediamath.jterminalone.models.Advertiser;
import com.mediamath.jterminalone.models.Agency;
import com.mediamath.jterminalone.models.AtomicCreative;
import com.mediamath.jterminalone.models.Campaign;
import com.mediamath.jterminalone.models.Concept;
import com.mediamath.jterminalone.models.JsonResponse;
import com.mediamath.jterminalone.models.Organization;
import com.mediamath.jterminalone.models.Strategy;
import com.mediamath.jterminalone.models.Strategy.freq_int;
import com.mediamath.jterminalone.models.Strategy.freq_type;
import com.mediamath.jterminalone.models.Strategy.goal_type;
import com.mediamath.jterminalone.models.Strategy.type;
import com.mediamath.jterminalone.models.StrategyDomain;
import com.mediamath.jterminalone.models.StrategyDomain.restrictions;
import com.mediamath.jterminalone.utils.ConditionQuery;
import com.mediamath.jterminalone.utils.Filters;
import com.mediamath.jterminalone.utils.QueryParamValues;

import junit.framework.TestCase;

public class BasicTest extends TestCase {
	
	@Test
	public void testAgencyPost() throws ClientException {
		JTerminalOne t1 = new JTerminalOne("nitesh.chauhan@xoriant.com", "xoriant123#","e34f74vnubr9uxasz2n7bdfv");
		
		Agency agency = new Agency();
		agency.setName("Nitesh6");
		agency.setOrganization_id(100048);
		try {
			agency = t1.save(agency);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCampaignPost() throws ClientException, java.text.ParseException {
		JTerminalOne t1 = new JTerminalOne("nitesh.chauhan@xoriant.com", "xoriant123#","e34f74vnubr9uxasz2n7bdfv");
		
		Campaign camp = new Campaign();
		camp.setName("NitCamp");
		camp.setAd_server_fee(10.0f);
		camp.setAd_server_id(9);
		camp.setAdvertiser_id(122631);
		camp.setConversion_type("variable");
		camp.setConversion_variable_minutes(1);
		camp.setGoal_type(Campaign.goal_types.cpe);
		camp.setGoal_value(100);
		camp.setService_type(Campaign.serv_types.SELF);
		
		Calendar cal = Calendar.getInstance();
		cal.roll(Calendar.DATE, true);
		Date endd = cal.getTime();
		
		camp.setEnd_date(endd);
		
		camp.setStart_date(new Date());
		
		camp.setPc_window_minutes(1);
		camp.setSpend_cap_amount(10);
		camp.setTotal_budget(100);
		camp.setUse_mm_freq(false);
		camp.setMerit_pixel_id(800781);
		
		try {
			camp = t1.save(camp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testAdvertiserPost() throws ClientException {
		JTerminalOne jt1 = new JTerminalOne("nitesh.chauhan@xoriant.com", "xoriant123#","e34f74vnubr9uxasz2n7bdfv");
		
		Advertiser adv = new Advertiser();
		adv.setAd_server_id(9);
		adv.setAgency_id(109308);
		adv.setDomain("http://www.advertiser.com");
		adv.setName("ABC Advertisers");
		adv.setVertical_id(11);
		try{
			adv = jt1.save(adv);
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testStrategyPost() throws ClientException {
		JTerminalOne jt1 = new JTerminalOne("nitesh.chauhan@xoriant.com", "xoriant123#","e34f74vnubr9uxasz2n7bdfv");
		
		Strategy str = new Strategy();
		str.setName("ABC Advertisers");
		str.setBudget(100.12f);
		str.setCampaign_id(233131);
		str.setFrequency_type(freq_type.asap);
		str.setFrequency_amount(10);
		str.setFrequency_interval(freq_int.day);
		str.setGoal_type(goal_type.spend);
		str.setGoal_value(12.12f);
		str.setMax_bid(10f);
		str.setPacing_amount(10f);
		str.setType(type.REM);
		str.setUse_campaign_start(false);
		str.setStart_date("2016-05-10T21:42:29+0000");
		str.setUse_campaign_end(false);
		str.setEnd_date("2016-10-10T21:42:29+0000");
		try{
			str = jt1.save(str);
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testStrategyDomainRestrictionPost() throws ClientException {
		JTerminalOne jt1 = new JTerminalOne("nitesh.chauhan@xoriant.com", "xoriant123#","e34f74vnubr9uxasz2n7bdfv");
		
		Strategy str = new Strategy();
		str.setId(1089192);	
		List<StrategyDomain> sdList = new ArrayList<StrategyDomain>();
		
		sdList.add(new StrategyDomain("google.com", restrictions.EXCLUDE));
		sdList.add(new StrategyDomain("gmail.com", restrictions.INCLUDE));
		str.setDomain_restrictions(sdList);
		
		try{
			str = jt1.save(str);
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testOrganizationPost() throws ClientException {
		JTerminalOne jt1 = new JTerminalOne("nitesh.chauhan@xoriant.com", "xoriant123#","e34f74vnubr9uxasz2n7bdfv");
		
		Organization org = new Organization();
		org.setName("ABC Advertisers");
		org.setAddress_1("First Lane, New York");
		org.setCity("New York");
		org.setState("NY");
		org.setContact_name("Michele");
		org.setZip("800293");
		org.setCountry("USA");
		org.setMm_contact_name("Mark");
		org.setPhone("408 345 7758");
		
		try{
			org = jt1.save(org);
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testJTerminalOneStringStringString() {
		JTerminalOne t1;
		try {
			t1 = new JTerminalOne("nitesh.chauhan@xoriant.com", "xoriant123#","e34f74vnubr9uxasz2n7bdfv");
			assertEquals(true, t1.isAuthenticated());
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testJTerminalOneAuthenticate() throws ClientException {
		JTerminalOne jt1 = new JTerminalOne("nitesh.chauhan@xoriant.com", "xoriant123#","e34f74vnubr9uxasz2n7bdfv");
		boolean isAuthenticated = jt1.isAuthenticated();
		assertEquals(true, isAuthenticated);
	}

	@Test
	public void testBaiscGetWithChildUsingQueryCriteria() throws ClientException {
		JTerminalOne jt1 = new JTerminalOne("nitesh.chauhan@xoriant.com", "xoriant123#","e34f74vnubr9uxasz2n7bdfv");
		
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
	
	}
	
	@Test
	public void testBaiscGetWithSortByUsingQueryCriteria() throws ClientException {
		JTerminalOne jt1 = new JTerminalOne("nitesh.chauhan@xoriant.com", "xoriant123#","e34f74vnubr9uxasz2n7bdfv");
		
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
		JTerminalOne jt1 = new JTerminalOne("nitesh.chauhan@xoriant.com", "xoriant123#","e34f74vnubr9uxasz2n7bdfv");
		
		QueryCriteria query = QueryCriteria.builder().setCollection("advertisers").setPageLimit(40).build();
		
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
	public void testBaiscGetWithPageLimitOffset() throws ClientException  {
		JTerminalOne jt1 = new JTerminalOne("nitesh.chauhan@xoriant.com", "xoriant123#","e34f74vnubr9uxasz2n7bdfv");
		
		QueryCriteria query = QueryCriteria.builder()
									.setCollection("advertisers")
									.setPageLimit(40)
									.setPageOffset(300)
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
	public void testBaiscGetWithLimit() throws ClientException {
		JTerminalOne jt1 = new JTerminalOne("nitesh.chauhan@xoriant.com", "xoriant123#","e34f74vnubr9uxasz2n7bdfv");
		
		Map<String, Long> limitList = new HashMap<String, Long>();
		limitList.put("agency", Long.valueOf(111555));
		QueryCriteria query = QueryCriteria.builder()
									.setCollection("advertisers")
									.setLimit(limitList)
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
	
	}
	
	@Test
	public void testBaiscGetWithQuery() throws ClientException {
		
		JTerminalOne jt1 = new JTerminalOne("nitesh.chauhan@xoriant.com", "xoriant123#","e34f74vnubr9uxasz2n7bdfv");
		
		Map<String, Long> limitList = new HashMap<String, Long>();
		limitList.put("agency", Long.valueOf(111555));
		QueryCriteria query = QueryCriteria.builder()
									.setCollection("advertisers")
									.setInclude(new ConditionQuery("agency", "organization"))
									.setQuery("agency_id%3E=109308")
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
		
	}
	
	@Test
	public void testBaiscGetWithFind() throws ClientException {
		JTerminalOne jt1 = new JTerminalOne("nitesh.chauhan@xoriant.com", "xoriant123#","e34f74vnubr9uxasz2n7bdfv");
		
		Map<String, Long> limitList = new HashMap<String, Long>();
		limitList.put("agency", Long.valueOf(111555));
		QueryCriteria query = QueryCriteria.builder()
									.setCollection("advertisers")
									.setQueryParamName("agency_id")
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
	
	}
	
	@Test
	public void testBaiscGetWithFind1() throws ClientException {
		
		JTerminalOne jt1 = new JTerminalOne("nitesh.chauhan@xoriant.com", "xoriant123#","e34f74vnubr9uxasz2n7bdfv");
		
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
	
	}
	
	@Test
	public void testBaiscGetWithFind2() throws ClientException  {
		JTerminalOne jt1 = new JTerminalOne("nitesh.chauhan@xoriant.com", "xoriant123#","e34f74vnubr9uxasz2n7bdfv");
		
		Map<String, Long> limitList = new HashMap<String, Long>();
		limitList.put("agency", Long.valueOf(111555));
		List<Object> qParams = new ArrayList<Object>();
		qParams.add(154121);
		qParams.add(153226);
		qParams.add(150994);
		QueryCriteria query = QueryCriteria.builder()
									.setCollection("advertisers")
									.setQueryParams(new QueryParamValues("name"))
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
	
	}
	
	public void testCampaignMarginPost() throws ClientException {
		JTerminalOne t1 = new JTerminalOne("nitesh.chauhan@xoriant.com", "xoriant123#","e34f74vnubr9uxasz2n7bdfv");
		
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
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void testConceptPost() throws ClientException {
		JTerminalOne t1 = new JTerminalOne("nitesh.chauhan@xoriant.com", "xoriant123#","e34f74vnubr9uxasz2n7bdfv");
		
		Concept camp = new Concept();
		camp.setAdvertiser_id(122631);
		camp.setName("TestConcept1");
		camp.setStatus(true);
		
		
		try {
			camp = t1.save(camp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void testAtomicCreatives() throws ClientException {
		
		JTerminalOne t1 = new JTerminalOne("nitesh.chauhan@xoriant.com", "xoriant123#","e34f74vnubr9uxasz2n7bdfv");
		
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
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
