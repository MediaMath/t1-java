package com.mediamath.terminalone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
import org.junit.Before;
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
    CAMPAIGN_RESPONSE = testConfig.getProperty("t1.mock.savecampaignresponse");
    ADVERTISER_RESPONSE = testConfig.getProperty("t1.mock.saveadvertiserresponse");
    System.out.println(ADVERTISER_RESPONSE);
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

 /* @Test
  public void testStrategyPostWithMocks() throws ClientException {

    t1.setAuthenticated(true);

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
    try {
      Mockito.when(postservicemock.save(str)).thenReturn(str);
      str = t1.save(str);
      Mockito.verify(postservicemock).save(str);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    assertNotNull(str);
    assertEquals("ABC Advertisers", str.getName());
  }*/

/*  @Test
  public void testStrategyAudioSegmentsPostWithMocks() throws ClientException {
    t1.setAuthenticated(true);

    Strategy str = new Strategy();
    str.setId(1089192);
    str.setAudienceSegmentExcludeOp(Strategy.audSegExc.OR);
    str.setAudienceSegmentIncludeOp(Strategy.audSegInc.OR);
    List<Segments> asList = new ArrayList<Segments>();

    asList.add(new Segments(691, Segments.restrictions.INCLUDE, Segments.audSegExc.OR,
        Segments.audSegInc.OR));
    str.setAudienceSegments(asList);

    try {
      Mockito.when(postservicemock.save(str)).thenReturn(str);
      str = t1.save(str);
      Mockito.verify(postservicemock).save(str);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    assertNotNull(str);
    assertEquals(1089192, str.getId());
  }
*/
/*  @Test
  public void testStrategyDomainRestrictionPostWithMocks() throws ClientException {
    t1.setAuthenticated(true);
    ;

    Strategy str = new Strategy();
    str.setId(1089192);
    List<StrategyDomain> sdList = new ArrayList<StrategyDomain>();

    sdList.add(new StrategyDomain("google.com", restrictions.EXCLUDE));
    sdList.add(new StrategyDomain("gmail.com", restrictions.INCLUDE));
    str.setStrategyDomainRestrictions(sdList);

    try {
      Mockito.when(postservicemock.save(str)).thenReturn(str);
      str = t1.save(str);
      Mockito.verify(postservicemock).save(str);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    assertNotNull(str);
    assertEquals(1089192, str.getId());

  }
*/
/*  @Test
  public void testOrganizationPostWithMocks() throws ClientException {

    t1.setAuthenticated(true);

    Organization org = new Organization();
    org.setId(100048);
    ArrayList<String> listOrgType = new ArrayList<String>();
    listOrgType.add("buyer");
    org.setOrgType(listOrgType);
    org.setName("ABC Advertisers");
    org.setAddress1("First Lane, New York");
    org.setCity("New York");
    org.setState("NY");
    org.setContactName("Michele");
    org.setZip("800293");
    org.setCountry("US");
    org.setMmContactName("Mark");
    org.setPhone("408 345 7758");

    try {
      Mockito.when(postservicemock.save(org)).thenReturn(org);
      org = (Organization) t1.save(org);
      Mockito.verify(postservicemock).save(org);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    assertNotNull(org);
    assertEquals(100048, org.getId());
  }
*/
/*  @Test
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
      camp = (Campaign) t1.save(camp);
      Mockito.verify(postservicemock).save(camp);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }
*/
/*  public void testConceptPost() throws ClientException {

    t1.setAuthenticated(true);

    Concept camp = new Concept();
    camp.setAdvertiserId(122631);
    camp.setName("TestConcept1");
    camp.setStatus(true);

    try {
      Mockito.when(postservicemock.save(camp)).thenReturn(camp);
      camp = (Concept) t1.save(camp);
      Mockito.verify(postservicemock).save(camp);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }
*/
/*  public void testAtomicCreatives() throws ClientException {

    t1.setAuthenticated(true);

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

    try {
      Mockito.when(postservicemock.save(ac)).thenReturn(ac);
      ac = (AtomicCreative) t1.save(ac);
      Mockito.verify(postservicemock).save(ac);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }
*/
}
