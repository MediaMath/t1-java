package com.mediamath.terminalone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

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
import com.mediamath.terminalone.models.Organization;
import com.mediamath.terminalone.models.Segments;
import com.mediamath.terminalone.models.Strategy;
import com.mediamath.terminalone.models.StrategyDomain;
import com.mediamath.terminalone.models.StrategyDomain.restrictions;
import com.mediamath.terminalone.models.T1User;

@RunWith(MockitoJUnitRunner.class)
public class PostMockTest {
  
  private static Properties testConfig = new Properties();

  private static final String AGENCY_RESPONSE = "{" + 
     " \"data\" : { " +
     " \"organization_id\" : 100048, " + 
     " \"status\" : false," +
     " \"version\" : 0," +
     " \"dmp_enabled\" : \"inherits\"," +
     " \"name\" : \"TestAgency\"," +
     " \"allow_x_adv_pixels\" : false," +
     " \"updated_on\" : \"2017-02-06T08:37:59+0000\"," +
     " \"created_on\" : \"2017-02-06T08:37:59+0000\"," +
     " \"entity_type\" : \"agency\"," +
     " \"id\" : 114385," +
     " \"allow_x_adv_optimization\" : false" + 
   " }, " +
   "\"meta\" : { " +
    "  \"etag\" : \"c82c6b064721323187e7a608b516f8ab15aa04d4\", " +
    "  \"called_on\" : \"2017-02-06T08:37:59+0000\", " +
     " \"status\" : \"ok\" "+
   "} }";
  
  
  private static String CAMPAIGN_RESPONSE = null;

  private static String ADVERTISER_RESPONSE = null;
  
  private static String STRATEGY_AUDIENCE_SEGMENTS_RESPONSE = null;
  
  private static String STRATEGY_DOMAIN_RESTRICTIONS_RESPONSE = null;
  
  private static String ORGANIZATION_RESPONSE = null;
  
  private static String CAMPAIGN_MARGIN_RESPONSE = null;
  
  private static String CONCEPT_RESPONSE = null;
  
  private static String ATOMICCREATIVE_RESPONSE = null;
  
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
    CAMPAIGN_RESPONSE = testConfig.getProperty("t1.mock.save.campaign.response");
    ADVERTISER_RESPONSE = testConfig.getProperty("t1.mock.save.advertiser.response");
    STRATEGY_AUDIENCE_SEGMENTS_RESPONSE = testConfig.getProperty("t1.mock.save.strategy.audience.segments.response");
    STRATEGY_DOMAIN_RESTRICTIONS_RESPONSE = testConfig.getProperty("t1.mock.save.strategy.domain.restriction.response");
    ORGANIZATION_RESPONSE = testConfig.getProperty("t1.mock.save.organization.response");
    CAMPAIGN_MARGIN_RESPONSE = testConfig.getProperty("t1.mock.save.campaign.margin.response");
    CONCEPT_RESPONSE = testConfig.getProperty("t1.mock.save.concept.response");
    ATOMICCREATIVE_RESPONSE = testConfig.getProperty("t1.mock.save.atomiccreative.response");
    
  }
  
  @After
  public final void tearDown() throws InterruptedException {
    Thread.sleep(5000);
  }
  
  @SuppressWarnings("unchecked")
  @Test
  public void testAgencyPostWithMocks2() throws Exception {
    
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

}
