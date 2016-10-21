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

import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mediamath.terminalone.QueryCriteria;
import com.mediamath.terminalone.TerminalOne;
import com.mediamath.terminalone.exceptions.ClientException;
import com.mediamath.terminalone.exceptions.ParseException;
import com.mediamath.terminalone.models.AdServer;
import com.mediamath.terminalone.models.Advertiser;
import com.mediamath.terminalone.models.Agency;
import com.mediamath.terminalone.models.AtomicCreative;
import com.mediamath.terminalone.models.AudienceSegment;
import com.mediamath.terminalone.models.Campaign;
import com.mediamath.terminalone.models.Concept;
import com.mediamath.terminalone.models.CreativeApproval;
import com.mediamath.terminalone.models.Data;
import com.mediamath.terminalone.models.JsonResponse;
import com.mediamath.terminalone.models.Organization;
import com.mediamath.terminalone.models.Pixel;
import com.mediamath.terminalone.models.Segments;
import com.mediamath.terminalone.models.Strategy;
import com.mediamath.terminalone.models.Strategy.freqInt;
import com.mediamath.terminalone.models.Strategy.freqType;
import com.mediamath.terminalone.models.Strategy.goalType;
import com.mediamath.terminalone.models.Strategy.type;
import com.mediamath.terminalone.models.StrategyAudienceSegment;
import com.mediamath.terminalone.models.StrategyConcept;
import com.mediamath.terminalone.models.StrategyDayPart;
import com.mediamath.terminalone.models.StrategyDayPart.daysEnum;
import com.mediamath.terminalone.models.StrategyDomain;
import com.mediamath.terminalone.models.StrategyDomain.restrictions;
import com.mediamath.terminalone.models.StrategySupplySource;
import com.mediamath.terminalone.models.SupplySource;
import com.mediamath.terminalone.models.T1Entity;
import com.mediamath.terminalone.models.TOneASCreativeAssetsApprove;
import com.mediamath.terminalone.models.TOneASCreativeAssetsUpload;
import com.mediamath.terminalone.models.TPASCreativeBatchApprove;
import com.mediamath.terminalone.models.TPASCreativeUpload;
import com.mediamath.terminalone.utils.ConditionQuery;
import com.mediamath.terminalone.utils.Filters;
import com.mediamath.terminalone.utils.FullParamValues;
import com.mediamath.terminalone.utils.QueryParamValues;

public class BasicFunctionalTest {

  private static Properties testConfig = new Properties();

  private static String user = null;

  private static String password = null;

  private static String apiKey = null;

  private static String productionKey = null;

  private static String oauthKey = null;

  private static String oauthSecret = null;

  @BeforeClass
  public static void init() throws Exception {
    InputStream input = BasicFunctionalTest.class.getClassLoader()
        .getResourceAsStream("test.properties");
    testConfig.load(input);
    user = testConfig.getProperty("username");
    password = testConfig.getProperty("password");
    apiKey = testConfig.getProperty("sandbox_api_key");
    productionKey = testConfig.getProperty("production_api_key");
    oauthKey = testConfig.getProperty("oauth_api_key");
    oauthSecret = testConfig.getProperty("oauth_secret");
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
  public void testOauthTokenAuthentication() throws ClientException {
    TerminalOne t1 = new TerminalOne();
    t1.authenticate("rydgu76jyap9sjaj5vyqpbg7");
    
    assertEquals(true, t1.isAuthenticated());
    
    Agency agency = new Agency();
    agency.setName("TestAgency");
    agency.setOrganizationId(100048);
    try {
      agency = t1.save(agency);
      System.out.println(agency.getId());
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    QueryCriteria query = QueryCriteria.builder().setCollection("agencies")
        .setEntity(agency.getId()).setGetAll(true).build();
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

  @Test
  public void testOAuthHGetAuthorizationUrl() throws ClientException {
    TerminalOne t1 = new TerminalOne();
    String authorizationUrl = t1.getAuthorizationUrl("https://blog.mediamath.com/", oauthKey);
    String expectedAuthorizationUrl = "https://api.mediamath.com/oauth2/v1.0/authorize";
    System.out.println("Auth URL:" + authorizationUrl);
    assertTrue(authorizationUrl.contains(expectedAuthorizationUrl));
  }

  /*
   * Can't be run from CI. Need a manual process of granting permission by hitting the authorization
   * URL
   */
  @Test
  public void testOAuthHGetToken() throws ClientException {
    TerminalOne t1 = new TerminalOne();
    OAuthJSONAccessTokenResponse oauthResponse = t1.getOauthToken("taqszg4upr8ap3m888fdvsvy",
        oauthKey, oauthSecret, "https://blog.mediamath.com/");
    assertNotNull(oauthResponse);
  }

  // Can't be run from CI. Need a manual process of granting permission by hitting the authorization
  // URL
  @Test
  public void testOAuthHRefreshToken() throws ClientException {
    TerminalOne t1 = new TerminalOne();
    OAuthJSONAccessTokenResponse oauthResponse = t1.getOauthToken("yfdwzubxjghhaxjsh7hb3u8v",
        oauthKey, oauthSecret, "https://blog.mediamath.com/");
    assertNotNull(oauthResponse);
    String refreshToken = oauthResponse.getRefreshToken();
    String accessToken = oauthResponse.getAccessToken();
    Long expiresIn = oauthResponse.getExpiresIn();
    OAuthJSONAccessTokenResponse refreshOauthTokenResponse = t1.refreshOauthToken(refreshToken,
        oauthKey, oauthSecret);
    assertNotNull(refreshOauthTokenResponse);
    String accessTokenAfterRefresh = refreshOauthTokenResponse.getAccessToken();
    Long expiresInAfterRefresh = refreshOauthTokenResponse.getExpiresIn();
    assertFalse(accessToken.equals(accessTokenAfterRefresh));

  }

  @Test
  public void testAgencyPost() throws ClientException {
    TerminalOne t1 = new TerminalOne(user, password, apiKey);

    Agency agency = new Agency();
    agency.setName("TestAgency");
    agency.setOrganizationId(100048);
    try {
      agency = t1.save(agency);
      System.out.println(agency.getId());
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    QueryCriteria query = QueryCriteria.builder().setCollection("agencies")
        .setEntity(agency.getId()).setGetAll(true).build();
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

  @Test
  public void testCampaignPost() throws ClientException, java.text.ParseException {
    TerminalOne t1 = new TerminalOne(user, password, apiKey);

    Campaign camp = new Campaign();
    camp.setName("TestCamp");
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

    try {
      camp = t1.save(camp);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    QueryCriteria query = QueryCriteria.builder().setCollection("campaigns").setEntity(camp.getId())
        .setGetAll(true).build();
    JsonResponse<?> jsonresponse = null;
    try {
      jsonresponse = t1.get(query);
    } catch (ClientException | ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    assertNotNull(jsonresponse);
    Campaign campaignCreated = (Campaign) jsonresponse.getData();
    // assertEquals("TestCamp", campaignCreated.getName());
    assertEquals(122631, campaignCreated.getAdvertiserId());
    assertEquals(Campaign.goalTypes.cpe, campaignCreated.getGoalType());
    assertEquals(Campaign.servTypes.SELF, campaignCreated.getServiceType());
    assertEquals(800781, campaignCreated.getMeritPixelId());
  }

  @Test
  public void testAdvertiserPost() throws ClientException {
    TerminalOne jt1 = new TerminalOne(user, password, apiKey);

    Advertiser adv = new Advertiser();
    adv.setAdServerId(9);
    adv.setAgencyId(109308);
    adv.setDomain("http://www.advertiser.com");
    adv.setName("TestAdvertiser");
    adv.setVerticalId(11);
    try {
      adv = jt1.save(adv);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    QueryCriteria query = QueryCriteria.builder().setCollection("advertisers")
        .setEntity(adv.getId()).setGetAll(true).build();
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
    assertEquals(109308, advertiserCreated.getAgencyId());
    assertEquals(9, advertiserCreated.getAdServerId());
    assertEquals(11, advertiserCreated.getVerticalId());
  }

  @Test
  public void testStrategyPost() throws ClientException {
    TerminalOne jt1 = new TerminalOne(user, password, apiKey);

    Strategy str = new Strategy();
    str.setName("TestStrategy");
    str.setBudget(100.12f);
    str.setCampaignId(267881);
    str.setFrequencyType(freqType.asap);
    str.setFrequencyAmount(10);
    str.setFrequencyInterval(freqInt.day);
    str.setGoalType(goalType.spend);
    str.setGoalValue(12.12f);
    str.setMaxBid(10f);
    str.setPacingAmount(10f);
    str.setType(type.REM);
    str.setUseCampaignStart(false);
    str.setStartDate(new Date());
    str.setUseCampaignEnd(false);

    // str.setStart_date("2016-09-22T21:42:29+0000");

    // str.setEnd_date("2016-10-15T21:42:29+0000");
    // 2016-10-22T16:28:35+0530
    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
    cal.roll(Calendar.DATE, true);
    cal.roll(Calendar.MONTH, true);
    Date endd = cal.getTime();

    str.setEndDate(endd);

    try {
      str = jt1.save(str);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    QueryCriteria query = QueryCriteria.builder().setCollection("strategies").setEntity(str.getId())
        .setGetAll(true).build();
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
    assertEquals(267881, strategyCreated.getCampaignId());
    assertEquals(goalType.spend, strategyCreated.getGoalType());
    assertEquals(type.REM, strategyCreated.getType());
    assertEquals(freqType.asap, strategyCreated.getFrequencyType());
    assertEquals(freqInt.day, strategyCreated.getFrequencyInterval());
  }

  @Test
  public void testStrategyAudienceSegmentsPost() throws ClientException {
    TerminalOne jt1 = new TerminalOne(user, password, apiKey);

    Strategy str = new Strategy();
    str.setId(1376197);
    str.setAudienceSegmentExcludeOp(Strategy.audSegExc.OR);
    str.setAudienceSegmentIncludeOp(Strategy.audSegInc.OR);
    List<Segments> asList = new ArrayList<Segments>();

    asList.add(new Segments(691, Segments.restrictions.INCLUDE, Segments.audSegExc.OR,
        Segments.audSegInc.OR));
    str.setAudienceSegments(asList);
    try {
      str = jt1.save(str);
      System.out.println(str);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    List<Segments> audienceSeg = str.getAudienceSegments();
    System.out.println(audienceSeg.get(0).getId());
  }

  @Test
  public void testStrategyDomainRestrictionPost() throws ClientException {
    TerminalOne jt1 = new TerminalOne(user, password, apiKey);

    Strategy str = new Strategy();
    str.setId(1376197);
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
  }

  @Test
  public void testStrategyDayParts() throws ClientException {
    TerminalOne jt1 = new TerminalOne(user, password, apiKey);

    StrategyDayPart strategyDayPart = new StrategyDayPart();

    strategyDayPart.setDays(daysEnum.W);
    strategyDayPart.setEndHour(15);
    strategyDayPart.setStartHour(10);
    strategyDayPart.setStrategyId(1368325);
    strategyDayPart.setUserTime(true);
    strategyDayPart.setVersion(1);

    try {
      strategyDayPart = jt1.save(strategyDayPart);
    } catch (ParseException e) {
      e.printStackTrace();
    }

  }

  @Test
  public void testPixelTargetingToStrategy() throws ClientException {

    TerminalOne jt1 = new TerminalOne(user, password, apiKey);

    QueryCriteria query = QueryCriteria.builder().setCollection("strategies").setEntity(1376197)
        .build();

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

    try {
      jt1.save(str);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  @Test
  public void testOrganizationPost() throws ClientException {
    TerminalOne jt1 = new TerminalOne(user, password, apiKey);

    Organization org = new Organization();
    org.setId(100048);
    ArrayList<String> listOrgType = new ArrayList<String>();
    listOrgType.add("buyer");
    org.setOrgType(listOrgType);
    org.setAddress1("First Lane, New York");
    org.setCity("New York");
    org.setState("NY");
    org.setContactName("Michele");
    org.setZip("800293");
    org.setCountry("US");
    org.setMmContactName("Mark");
    org.setPhone("408 345 7758");
    org.setVersion(120);

    try {
      org = jt1.save(org);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    QueryCriteria query = QueryCriteria.builder().setCollection("organizations").setEntity(100048)
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
    // assertEquals("TestOrg", orgCreated.getName());
  }

  @Test
  public void testStrategyConceptDelete() {
    TerminalOne T1;
    StrategyConcept sc = new StrategyConcept();
    sc.setId(2903693);
    try {
      T1 = new TerminalOne(user, password, apiKey);
      JsonResponse jr = T1.delete(sc);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Test
  public void testStrategyDayPartDelete() {
    TerminalOne T1;
    StrategyDayPart sc = new StrategyDayPart();
    sc.setId(952850);
    try {
      T1 = new TerminalOne(user, password, apiKey);
      JsonResponse<? extends T1Entity> jr = T1.delete(sc);
    } catch (ClientException | ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  // check
  @Test
  public void testCampaignMarginPost() throws ClientException {
    TerminalOne t1 = new TerminalOne(user, password, apiKey);

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
    TerminalOne t1 = new TerminalOne(user, password, apiKey);

    Concept camp = new Concept();
    camp.setAdvertiserId(122631);
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

    TerminalOne t1 = new TerminalOne(user, password, apiKey);

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
      ac = t1.save(ac);
      System.out.println(ac);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

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

  @Test
  public void testTOneASCreativeAssetUpload() throws ClientException, IOException {
    TerminalOne t1 = new TerminalOne(user, password, apiKey);

    TOneASCreativeAssetsUpload response = t1.saveTOneASCreativeAssetsUpload(
        "C:\\Users\\chauhan_n\\Desktop\\t1attachements\\JPGs.zip", "JPGs.zip", "t1asfileupload");

    assertNotNull(response);

    TOneASCreativeAssetsApprove creativeAssetsApprove = new TOneASCreativeAssetsApprove();
    creativeAssetsApprove.create(false, "165615", "http://ad.vendor.com/clicktracker/?id=1234",
        "http://theactuallandingpage.com", "BBVA_CaminoaleÔxito_160x600.swf",
        "BBVA_CaminoaleÔxito_160x600.swf", "665888");

    JsonResponse<? extends T1Entity> secondresponse = t1
        .saveTOneASCreativeAssetsApprove(creativeAssetsApprove);
    assertNotNull(secondresponse.getData());
  }

  /**
   * this test will only work on production. t1.mediamath.com
   * 
   * @throws ClientException
   * @throws IOException
   * @throws ParseException
   */
  /*
   * @Test public void testVideoCreative() throws ClientException, IOException, ParseException { //
   * will work only on production. TerminalOne t1 = new TerminalOne(user, password,productionKey);
   * 
   * VideoCreative videoCreative = new VideoCreative(); videoCreative.setName("videoCreative2");
   * videoCreative.setStartTime(1468486396);
   * videoCreative.setLandingUrl("http://www.somedomain.com"); videoCreative.setAdvertiser(122631);
   * videoCreative.setEndTime(1470009600); videoCreative.setConcept(847527);
   * videoCreative.setClickthroughUrl("http://www.somedomain.com");
   * videoCreative.setVendors(847527); videoCreative.setVendors(847528);
   * videoCreative.setVendors(847529);
   * 
   * VideoCreativeResponse saveResponse = t1.saveVideoCreatives(videoCreative);
   * 
   * // depricated step; fethching the upload token // response =
   * t1.getVideoCreativesUploadToken(response);
   * 
   * //upload the file. String filePath =
   * "C:\\Users\\chauhan_n\\Desktop\\t1attachements\\blah1234.flv"; //String filePath =
   * "C:\\Users\\chauhan_n\\Desktop\\t1attachements\\progit.pdf"; String fileName = "blah1234.flv";
   * //String fileName = "progit.pdf"; VideoCreativeResponse uploadResponse =
   * t1.uploadVideoCreative(filePath, fileName, saveResponse.getCreativeId());
   * 
   * //check video creative status VideoCreativeUploadStatus uploadStatus =
   * t1.getVideoCreativeUploadStatus(uploadResponse.getCreativeId());
   * 
   * assertNotNull(saveResponse); assertNotNull(saveResponse.getCreativeId());
   * 
   * assertNotNull(uploadResponse); assertNotNull(uploadResponse.getStatus()); }
   */

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

    QueryCriteria query = QueryCriteria.builder().setCollection("advertisers").setSortBy("-id")
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
    TerminalOne jt1 = new TerminalOne(user, password, apiKey);

    QueryCriteria query = QueryCriteria.builder().setCollection("advertisers").setPageLimit(40)
        .build();

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
    QueryCriteria query = QueryCriteria.builder().setCollection("advertisers").setPageLimit(40)
        .build();

    JsonResponse<?> jsonresponse_0_to_40 = null;

    try {
      jsonresponse_0_to_40 = jt1.get(query);
    } catch (ClientException | ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    assertNotNull(jsonresponse_0_to_40);

    query = QueryCriteria.builder().setCollection("advertisers").setPageLimit(40).setPageOffset(40)
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
    TerminalOne jt1 = new TerminalOne(user, password, apiKey);

    Map<String, Long> limitList = new HashMap<String, Long>();
    limitList.put("agency", Long.valueOf(111555));
    FullParamValues fpv = new FullParamValues();
    fpv.setStrValue("advertiser");
    QueryCriteria query = QueryCriteria.builder().setCollection("advertisers").setLimit(limitList)
        .setFull(fpv).setPageLimit(100).build();

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
    QueryCriteria query = QueryCriteria.builder().setCollection("campaigns").setFull(fpv)
        .setPageLimit(1).setSortBy("-updated_on").build();
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

    query = QueryCriteria.builder().setCollection("campaigns").setSortBy("-updated_on")
        .setPageLimit(1).build();
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
    QueryCriteria query = QueryCriteria.builder().setCollection("campaigns").setFull(fpv)
        .setSortBy("-updated_on").setPageLimit(10).build();
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

    query = QueryCriteria.builder().setCollection("campaigns").setSortBy("-updated_on")
        .setPageLimit(1).build();
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
        .setInclude(new ConditionQuery("advertiser")).setFull(fpv).setSortBy("-updated_on")
        .setPageLimit(1).build();
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

    query = QueryCriteria.builder().setCollection("campaigns")
        .setInclude(new ConditionQuery("advertiser")).setSortBy("-updated_on").setPageLimit(1)
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
    QueryCriteria query = QueryCriteria.builder().setCollection("advertisers")
        .setQueryParamName("name").setQueryOperator(Filters.EQUALS)
        .setQueryParams(new QueryParamValues("Retirement")).setPageLimit(100).build();

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
    QueryCriteria query = QueryCriteria.builder().setCollection("strategies").setEntity(1376198)
        .setChild("browser").setPageLimit(1).build();

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
    QueryCriteria query = QueryCriteria.builder().setCollection("supply_sources").setPageLimit(1)
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
    assertTrue(supply_sources.size() > 0);
  }

  @Test
  public void testGetForAudienceSegments() throws ClientException {
    TerminalOne jt1 = new TerminalOne(user, password, apiKey);
    QueryCriteria query = QueryCriteria.builder().setCollection("audience_segments").setPageLimit(1)
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
    assertTrue(audience_segments.size() > 0);
  }

  @Test
  public void testGetForPixelBundles() throws ClientException {
    TerminalOne jt1 = new TerminalOne(user, password, apiKey);
    QueryCriteria query = QueryCriteria.builder().setCollection("pixel_bundles").setPageLimit(1)
        .build();

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
    QueryCriteria query = QueryCriteria.builder().setCollection("strategy_supply_sources")
        .setLimit(limitList).setPageLimit(1).build();

    JsonResponse<?> jsonresponse = null;

    try {
      jsonresponse = jt1.get(query);
    } catch (ClientException | ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    assertNotNull(jsonresponse);
    List<StrategySupplySource> strategy_supply_sources = (List<StrategySupplySource>) jsonresponse
        .getData();
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
    QueryCriteria query = QueryCriteria.builder().setCollection("atomic_creatives").setPageLimit(1)
        .build();

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
        .setInclude(new ConditionQuery("creative_approvals")).setEntity(2691868).setPageLimit(1)
        .build();

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
    QueryCriteria query = QueryCriteria.builder().setCollection("atomic_creatives")
        .setEntity(2691868).setChild("creative_approvals").setPageLimit(1).build();

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
    QueryCriteria query = QueryCriteria.builder().setCollection("ad_servers").setPageLimit(1)
        .build();

    JsonResponse<?> jsonresponse = null;

    try {
      jsonresponse = jt1.get(query);
    } catch (ClientException | ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    assertNotNull(jsonresponse);
    List<AdServer> adservers = (List<AdServer>) jsonresponse.getData();
    assertNotNull(adservers);
    assertTrue(adservers.size() > 0);
  }

}
