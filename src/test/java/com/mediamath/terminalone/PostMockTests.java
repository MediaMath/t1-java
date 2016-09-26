package com.mediamath.terminalone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.mediamath.terminalone.Exceptions.ClientException;
import com.mediamath.terminalone.Exceptions.ParseException;
import com.mediamath.terminalone.models.Advertiser;
import com.mediamath.terminalone.models.Agency;
import com.mediamath.terminalone.models.AtomicCreative;
import com.mediamath.terminalone.models.Campaign;
import com.mediamath.terminalone.models.Concept;
import com.mediamath.terminalone.models.JsonResponse;
import com.mediamath.terminalone.models.Organization;
import com.mediamath.terminalone.models.Segments;
import com.mediamath.terminalone.models.Strategy;
import com.mediamath.terminalone.models.Strategy.freq_int;
import com.mediamath.terminalone.models.Strategy.freq_type;
import com.mediamath.terminalone.models.Strategy.goal_type;
import com.mediamath.terminalone.models.Strategy.type;
import com.mediamath.terminalone.models.StrategyDomain;
import com.mediamath.terminalone.models.StrategyDomain.restrictions;
import com.mediamath.terminalone.models.T1Entity;
import com.mediamath.terminalone.models.T1Response;
import com.mediamath.terminalone.models.TOneASCreativeAssetsApprove;
import com.mediamath.terminalone.models.TOneASCreativeAssetsUpload;
import com.mediamath.terminalone.models.ThreePASCreativeBatchApprove;
import com.mediamath.terminalone.models.ThreePASCreativeUpload;
import com.mediamath.terminalone.service.GetService;
import com.mediamath.terminalone.service.PostService;
import com.mediamath.terminalone.service.T1Service;


@RunWith(MockitoJUnitRunner.class)
public class PostMockTests {

	@Mock
	T1Service t1servicemock;
	
	@Mock
	PostService postservicemock;
	
	@Mock
	GetService getservicemock;
	
	@Mock
	Connection connectionmock;
	
	@InjectMocks
	TerminalOne t1 = new TerminalOne();

	@After
	public final void tearDown() throws InterruptedException {
		Thread.sleep(5000);
	}
	
	@Test
	public void testAgencyPostWithMocks() throws Exception {	
		t1.setAuthenticated(true);
		Agency agency = new Agency();
		agency.setName("agency_name");
		agency.setOrganization_id(100048);
		Mockito.when(postservicemock.save(agency)).thenReturn(agency);
		try {
			agency = t1.save(agency);
			Mockito.verify(postservicemock).save(agency);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		assertNotNull(agency);
		assertEquals("agency_name", agency.getName());
		assertEquals(100048, agency.getOrganization_id());
	}
	
	
	
	
	@Test
	public void testCampaignPostWithMocks() throws ClientException, java.text.ParseException {
		
		t1.setAuthenticated(true);
		
		Campaign camp = new Campaign();
		camp.setName("NitCamp");
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
			Mockito.when(postservicemock.save(camp)).thenReturn(camp);
			camp = t1.save(camp);
			Mockito.verify(postservicemock).save(camp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(camp);
		assertEquals("NitCamp", camp.getName());
		
	}
	
	@Test
	public void testAdvertiserPostWithMocks() throws ClientException {
		
		t1.setAuthenticated(true);
		
		Advertiser adv = new Advertiser();
		adv.setAd_server_id(9);
		adv.setAgency_id(109308);
		adv.setDomain("http://www.advertiser.com");
		adv.setName("ABC Advertisers");
		adv.setVertical_id(11);
		
		try{
			Mockito.when(postservicemock.save(adv)).thenReturn(adv);
			adv = t1.save(adv);
			Mockito.verify(postservicemock).save(adv);
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(adv);
		assertEquals("ABC Advertisers", adv.getName());
		
		
	}
	
	@Test
	public void testStrategyPostWithMocks() throws ClientException {
		
		t1.setAuthenticated(true);
		
		
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
		//str.setStart_date("2016-05-13T21:42:29+0000");
		str.setUse_campaign_end(false);
	//	str.setEnd_date("2016-10-12T21:42:29+0000");
		try{
			Mockito.when(postservicemock.save(str)).thenReturn(str);
			str = t1.save(str);
			Mockito.verify(postservicemock).save(str);
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(str);
		assertEquals("ABC Advertisers", str.getName());
	}
	
	@Test
	public void testStrategyAudioSegmentsPostWithMocks() throws ClientException {
		t1.setAuthenticated(true);
		
		Strategy str = new Strategy();
		str.setId(1089192);	
		str.setAudience_segment_exclude_op(Strategy.aud_seg_exc.OR);
		str.setAudience_segment_include_op(Strategy.aud_seg_inc.OR);
		List<Segments> asList = new ArrayList<Segments>();
		
		asList.add(new Segments(691, Segments.restrictions.INCLUDE, Segments.aud_seg_exc.OR, Segments.aud_seg_inc.OR));
		str.setAudience_segments(asList);
		
		try{
			Mockito.when(postservicemock.save(str)).thenReturn(str);
			str = t1.save(str);
			Mockito.verify(postservicemock).save(str);
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(str);
		assertEquals(1089192, str.getId());
	}
	
	@Test
	public void testStrategyDomainRestrictionPostWithMocks() throws ClientException {
		t1.setAuthenticated(true);;
		
		Strategy str = new Strategy();
		str.setId(1089192);	
		List<StrategyDomain> sdList = new ArrayList<StrategyDomain>();
		
		sdList.add(new StrategyDomain("google.com", restrictions.EXCLUDE));
		sdList.add(new StrategyDomain("gmail.com", restrictions.INCLUDE));
		str.setStrategy_domain_restrictions(sdList);
		
		try{
			Mockito.when(postservicemock.save(str)).thenReturn(str);
			str = t1.save(str);
			Mockito.verify(postservicemock).save(str);
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(str);
		assertEquals(1089192, str.getId());
		
	}
	
	@Test
	public void testOrganizationPostWithMocks() throws ClientException {
		
		t1.setAuthenticated(true);
				
		Organization org = new Organization();
		org.setId(100048);
		ArrayList<String> listOrgType = new ArrayList<String>();
		listOrgType.add("buyer");
		org.setOrg_type(listOrgType);
		org.setName("ABC Advertisers");
		org.setAddress_1("First Lane, New York");
		org.setCity("New York");
		org.setState("NY");
		org.setContact_name("Michele");
		org.setZip("800293");
		org.setCountry("US");
		org.setMm_contact_name("Mark");
		org.setPhone("408 345 7758");
		
		try{
			Mockito.when(postservicemock.save(org)).thenReturn(org);
			org = t1.save(org);
			Mockito.verify(postservicemock).save(org);
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(org);
		assertEquals(100048, org.getId());
	}

	@Test
	public void testCampaignMarginPost() throws ClientException {

		t1.setAuthenticated(true);
		
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
			Mockito.when(postservicemock.save(camp)).thenReturn(camp);
			camp = t1.save(camp);
			Mockito.verify(postservicemock).save(camp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void testConceptPost() throws ClientException {
		
		t1.setAuthenticated(true);
		
		Concept camp = new Concept();
		camp.setAdvertiser_id(122631);
		camp.setName("TestConcept1");
		camp.setStatus(true);
		
		try {
			Mockito.when(postservicemock.save(camp)).thenReturn(camp);
			camp = t1.save(camp);
			Mockito.verify(postservicemock).save(camp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void testAtomicCreatives() throws ClientException {
		
		t1.setAuthenticated(true);
		
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
			Mockito.when(postservicemock.save(ac)).thenReturn(ac);
			ac = t1.save(ac);
			Mockito.verify(postservicemock).save(ac);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
