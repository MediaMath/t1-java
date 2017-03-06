package com.mediamath.terminalone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

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
import com.mediamath.terminalone.models.Campaign;
import com.mediamath.terminalone.models.Concept;
import com.mediamath.terminalone.models.JsonResponse;
import com.mediamath.terminalone.models.Organization;
import com.mediamath.terminalone.models.Segments;
import com.mediamath.terminalone.models.Strategy;
import com.mediamath.terminalone.models.StrategyConcept;
import com.mediamath.terminalone.models.StrategyDayPart;
import com.mediamath.terminalone.models.StrategyDomain;
import com.mediamath.terminalone.models.T1Entity;
import com.mediamath.terminalone.models.StrategyDomain.restrictions;
import com.mediamath.terminalone.models.T1User;
import com.mediamath.terminalone.models.TOneASCreativeAssetsApprove;
import com.mediamath.terminalone.models.TOneASCreativeAssetsUpload;
import com.mediamath.terminalone.models.TPASCreativeBatchApprove;
import com.mediamath.terminalone.models.TPASCreativeUpload;
import com.mediamath.terminalone.models.VideoCreative;
import com.mediamath.terminalone.models.VideoCreativeResponse;

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
  
  private static String STRATEDY_DAYPART_META_ERROR_CHK  = null;
  
  private static String ADVERTISER_ERROR_RESPONSE = null;
  
  private static String STRATEGY_CONCEPT_RESPONSE = null;
  
  private static String STRATEGY_DAYPART_RESPONSE = null;
  
  private static String STRATEGY_DOMAIN_RESPONSE = null;
  
  private static String VIDEO_CREATIVE_SAVE = null;
  
  private static String VIDEO_CREATIVE_UPLOAD = null;
  
  private static String VIDEO_CREATIVE_UPLOAD_STATUS = null;
  
  
  private static String LOGIN = null;
  
  
  @Mock
  Connection connectionmock;

  @InjectMocks
  TerminalOne t1 = new TerminalOne();
  
  @Mock Response response;
  
  @BeforeClass
  public static void init() throws Exception {
 
    InputStream input = PostFunctionalTestIT.class.getClassLoader().getResourceAsStream("mocktest.properties");
    testConfig.load(input);
    LOGIN = testConfig.getProperty("t1.mock.loginResponse");
    AGENCY_RESPONSE = testConfig.getProperty("t1.mock.save.agency.response");
    CAMPAIGN_RESPONSE = testConfig.getProperty("t1.mock.save.campaign.response");
    ADVERTISER_RESPONSE = testConfig.getProperty("t1.mock.save.advertiser.response");
    STRATEGY_AUDIENCE_SEGMENTS_RESPONSE = testConfig.getProperty("t1.mock.save.strategy.audience.segments.response");
    STRATEGY_DOMAIN_RESTRICTIONS_RESPONSE = testConfig.getProperty("t1.mock.save.strategy.domain.restriction.response");
    ORGANIZATION_RESPONSE = testConfig.getProperty("t1.mock.save.organization.response");
    CAMPAIGN_MARGIN_RESPONSE = testConfig.getProperty("t1.mock.save.campaign.margin.response");
    CONCEPT_RESPONSE = testConfig.getProperty("t1.mock.save.concept.response");
    ATOMICCREATIVE_RESPONSE = testConfig.getProperty("t1.mock.save.atomiccreative.response");
    THREEPASCREATIVE_UPLOAD_SECONDCALL_RESPONSE = testConfig.getProperty("t1.mock.save.3pas.creative.upload.secondcall.response");
    THREEPASCREATIVE_UPLOAD_FIRSTCALL_RESPONSE = testConfig.getProperty("t1.mock.save.3pas.creative.upload.firstcall.response");
    TONEAS_CREATIVE_UPLOAD_FIRSTCALL = testConfig.getProperty("t1.mock.save.toneas.creative.assets.upload.firstcall.response");
    TONEAS_CREATIVE_UPLOAD_SECONDCALL = testConfig.getProperty("t1.mock.save.toneas.creative.assets.upload.secondcall.response");
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
    
  }
  
  @After
  public final void tearDown() throws InterruptedException {
    Thread.sleep(5000);
  }
  
  @Test
  public void testOauthTokenAuthentication() throws ClientException {
    TerminalOne t1 = new TerminalOne();
    t1.authenticate("rydgu76jyap9sjaj5vyqpbg7");

    assertEquals(true, t1.isAuthenticated());
  }
  
  @SuppressWarnings("unchecked")
  @Test
  public void testAgencyPostWithMocks() throws Exception {
    
    Agency agency = new Agency();
    agency.setName("TestAgency");
    agency.setOrganizationId(100048);
    
    Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class))).thenReturn(response);
    Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN, AGENCY_RESPONSE);
    
    try {
      t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
      agency = (Agency) t1.save(agency);
      Mockito.verify(connectionmock, times(2)).post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class));
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
    camp.setGoalType(Campaign.goalTypes.cpe);
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
    
    Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class))).thenReturn(response);
    Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN, CAMPAIGN_RESPONSE);

    
    
    try {
      t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
      camp = (Campaign) t1.save(camp);
      Mockito.verify(connectionmock, times(2)).post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class));
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

    Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class))).thenReturn(response);
    Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN, ADVERTISER_RESPONSE);
    
    try {
    	t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
    	adv = (Advertiser) t1.save(adv);
    	Mockito.verify(connectionmock, times(2)).post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class));
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
    try{
    Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class))).thenReturn(response);
    Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN, ADVERTISER_ERROR_RESPONSE);
    }catch(ClientException ce){
    	
    }
    try {
    	t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
    	adv1 = (Advertiser) t1.save(adv);
    	Mockito.verify(connectionmock, times(2)).post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class));
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      assertNull(adv1);
    }catch(ClientException ce){
    	//assertNull(adv1);
    }

    

  }

/*  @Test
  public void testStrategyPostWithMocks() throws ClientException {

    Strategy str = new Strategy();
    str.setName("ABC Advertisers");
    str.setBudget(100.12f);
    str.setCampaignId(233131);
    str.setFrequencyType(freqType.asap);
    str.setFrequencyAmount(10);
    str.setFrequencyInterval(freqInt.day);
    str.setGoalType(goalType.spend);
    str.setGoalValue(12.12f);
    str.setMaxBid(10f);
    str.setPacingAmount(10f);
    str.setType(type.REM);
    str.setUseCampaignStart(false);
    // str.setStart_date("2016-05-13T21:42:29+0000");
    str.setUseCampaignEnd(false);
    // str.setEnd_date("2016-10-12T21:42:29+0000");
    
    Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class))).thenReturn(response);
    //Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN, STRATEGY_RESPONSE);
    
    try {
      t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
      str = t1.save(str);
      Mockito.verify(connectionmock, times(2)).post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class));
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    assertNotNull(str);
    assertEquals("ABC Advertisers", str.getName());
  }
*/

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

    Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class))).thenReturn(response);
    Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN, STRATEGY_AUDIENCE_SEGMENTS_RESPONSE);
    
    
    try {
      t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
      str = t1.save(str);
      Mockito.verify(connectionmock, times(2)).post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class));
    
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

    Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class))).thenReturn(response);
    Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN, STRATEGY_DOMAIN_RESTRICTIONS_RESPONSE);

    try {
      t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
      str = t1.save(str);
      Mockito.verify(connectionmock, times(2)).post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class));
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    assertNull(str);
  }
  
  
  @Test
  public void testStrategyDomainPost() throws ClientException {

    StrategyDomain sd = new StrategyDomain();
    sd.setDomain("google.com");
    sd.setRestriction(StrategyDomain.restrictions.INCLUDE);
    sd.setStrategyId(2035005);
    //sd.setVersion(0);

    Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class))).thenReturn(response);
    Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN, STRATEGY_DOMAIN_RESPONSE);
  
    
    try {
        t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
        sd = (StrategyDomain) t1.save(sd);
        Mockito.verify(connectionmock, times(2)).post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)); 
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
    

    Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class))).thenReturn(response);
    Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN, ORGANIZATION_RESPONSE);
    

    try {
      t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
      org = (Organization) t1.save(org);
      Mockito.verify(connectionmock, times(2)).post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class));
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

    Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class))).thenReturn(response);
    Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN, CAMPAIGN_MARGIN_RESPONSE);
    
    try {
      t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
      camp = (Campaign) t1.save(camp);
      Mockito.verify(connectionmock, times(2)).post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class));
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

    Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class))).thenReturn(response);
    Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN, CONCEPT_RESPONSE);
    
    try {
      
      t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
      camp = (Concept) t1.save(camp);
      Mockito.verify(connectionmock, times(2)).post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class));
    
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

    Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class))).thenReturn(response);
    Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN, STRATEGY_CONCEPT_RESPONSE);
    
    try {
      
      t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
      sc = (StrategyConcept) t1.save(sc);
      Mockito.verify(connectionmock, times(2)).post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class));
    
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    assertNotNull(sc);

  }

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

    Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class))).thenReturn(response);
    Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN, STRATEGY_DAYPART_RESPONSE);
    
    try {
      
      t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
      sc = (StrategyDayPart) t1.save(sc);
      Mockito.verify(connectionmock, times(2)).post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class));
    
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

    Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class))).thenReturn(response);
    Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN, STRATEGY_CONCEPT_DELETE);
    
    try {
      
      t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
      jr = t1.delete(sc);
      Mockito.verify(connectionmock, times(2)).post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class));
    
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
	  
	TerminalOne T1;
    StrategyDayPart sc = new StrategyDayPart();
    sc.setId(1611126);

    Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class))).thenReturn(response);
    Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN, STRATEGY_DAY_PART_DELETE);
    
    try {
      
      t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
      jr = t1.delete(sc);
      Mockito.verify(connectionmock, times(2)).post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class));
    
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
    

    Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class))).thenReturn(response);
    Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN, STRATEDY_DAYPART_META_ERROR_CHK);
    
    try {
      
      t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
      sc1 = (StrategyDayPart) t1.save(sc);
      Mockito.verify(connectionmock, times(2)).post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class));
    
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    assertNull(sc1);
  

  }
  
  
  @SuppressWarnings("unchecked")
@Test
  public void testVideoCreativeSave() throws ClientException, IOException, ParseException {
   
	//STAGE 1 : SAVE--------------------------------------
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
    
    Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class))).thenReturn(response);
    Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.anyString(), Mockito.any(T1User.class))).thenReturn(response);
    Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN, VIDEO_CREATIVE_SAVE);

    
    VideoCreativeResponse saveResponse =null;
    t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
	saveResponse = t1.saveVideoCreatives(videoCreative);
	Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class));
	Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.anyString(), Mockito.any(T1User.class));
    
    assertNotNull(saveResponse);
    assertNotNull(saveResponse.getCreativeId());

  }

  @Test
  public void testVideoCreativeUpload() throws ClientException, IOException, ParseException {
   
    String filePath = "C:\\Users\\chaudhari_j\\Desktop\\t1attachements\\blah1234.flv";
    String fileName = "blah1234.flv";
    
    Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class))).thenReturn(response);
    Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(FormDataMultiPart.class), Mockito.any(T1User.class))).thenReturn(response);
    Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN, VIDEO_CREATIVE_UPLOAD);
    
    t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
    VideoCreativeResponse uploadResponse = t1.uploadVideoCreative(filePath, fileName,String.valueOf(3595840));
    
    Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class));
    Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(FormDataMultiPart.class), Mockito.any(T1User.class));
    
    
    assertNotNull(uploadResponse);
    assertNotNull(uploadResponse.getStatus());
  }
  
  @Test
  public void testVideoCreativeUploadStatus() throws ClientException, IOException, ParseException {
   
    //STAGE 3 : CHECK UPLOAD STATUS--------------------------
    Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class))).thenReturn(response);
    Mockito.when(connectionmock.get(Mockito.anyString(),  Mockito.any(T1User.class))).thenReturn(VIDEO_CREATIVE_UPLOAD_STATUS);
    Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
    
    // check video creative status VideoCreativeUploadStatus uploadStatus =
    t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
    t1.getVideoCreativeUploadStatus(String.valueOf(3595840));
    Mockito.verify(connectionmock).get(Mockito.anyString(), Mockito.any(T1User.class));
    Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class));
    
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

    Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class))).thenReturn(response);
    Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN, ATOMICCREATIVE_RESPONSE);
    
    try {
      t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
      ac = (AtomicCreative) t1.save(ac);
      Mockito.verify(connectionmock, times(2)).post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class));
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    assertNotNull(ac);
    assertEquals("MyTestAtomicCreative",  ac.getName());
    
  }
  
  @SuppressWarnings("unchecked")
  @Test
  public void test3pasCreativeUpload() throws ClientException, IOException {
    Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class))).thenReturn(response);
    Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(FormDataMultiPart.class), Mockito.any(T1User.class))).thenReturn(response);
    Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN, THREEPASCREATIVE_UPLOAD_FIRSTCALL_RESPONSE ,THREEPASCREATIVE_UPLOAD_SECONDCALL_RESPONSE );
    
    t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
    
    //  3pas first call
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
    Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class))).thenReturn(response);
    Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(FormDataMultiPart.class), Mockito.any(T1User.class))).thenReturn(response);
    Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN, TONEAS_CREATIVE_UPLOAD_FIRSTCALL ,TONEAS_CREATIVE_UPLOAD_SECONDCALL);
    
    t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
    
    //first call
    TOneASCreativeAssetsUpload response = t1.saveTOneASCreativeAssetsUpload("C:\\Users\\chauhan_n\\Desktop\\t1attachements\\JPGs.zip", "JPGs.zip", "t1asfileupload");
    assertNotNull(response);

    TOneASCreativeAssetsApprove creativeAssetsApprove = new TOneASCreativeAssetsApprove();
    creativeAssetsApprove.create(false, "165615", "http://ad.vendor.com/clicktracker/?id=1234","http://theactuallandingpage.com", "BBVA_CaminoaleÔxito_160x600.swf","BBVA_CaminoaleÔxito_160x600.swf", "665888");

    //second call
    JsonResponse<? extends T1Entity> secondresponse = t1.saveTOneASCreativeAssetsApprove(creativeAssetsApprove);
    assertNotNull(secondresponse.getData());
  }
  

}
