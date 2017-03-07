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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
import com.mediamath.terminalone.models.Advertiser;
import com.mediamath.terminalone.models.Agency;
import com.mediamath.terminalone.models.AtomicCreative;
import com.mediamath.terminalone.models.Campaign;
import com.mediamath.terminalone.models.ChildPixel;
import com.mediamath.terminalone.models.Concept;
import com.mediamath.terminalone.models.JsonResponse;
import com.mediamath.terminalone.models.Organization;
import com.mediamath.terminalone.models.Segments;
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
import com.mediamath.terminalone.models.T1Entity;
import com.mediamath.terminalone.models.TOneASCreativeAssetsApprove;
import com.mediamath.terminalone.models.TOneASCreativeAssetsUpload;
import com.mediamath.terminalone.models.TPASCreativeBatchApprove;
import com.mediamath.terminalone.models.TPASCreativeUpload;
import com.mediamath.terminalone.models.VideoCreative;
import com.mediamath.terminalone.models.VideoCreativeResponse;

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
    InputStream input = PostFunctionalTestIT.class.getClassLoader()
        .getResourceAsStream("test.properties");
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
    t1 = new TerminalOne("nitesh.chauhan@xoriant.com", "xoriant123#", "98waz2495u8n5udu7pzczfh5");
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
      agency = (Agency) t1.save(agency);
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
    agency.setOrganizationId(100048);
    try {
      agency = (Agency) t1.save(agency);
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

  /**
   * Agency Update
   * 
   * @throws ClientException
   */
  @Test
  public void testCommonAgencyUpdatePost() throws ClientException {

    TerminalOne t1 = new TerminalOne(user, password, apiKey);

    QueryCriteria query = QueryCriteria.builder().setCollection("agencies").setEntity(114244)
        .build();

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
    camp.setAdvertiserId(122631);
    camp.setName("CampaignTest One");
    camp.setAdServerFee(10.01, null);
    camp.setConversionType("variable");
    camp.setConversionVariableMinutes(1);
    camp.setGoalType(Campaign.goalTypes.cpa);
    camp.setGoalValue(100, null);
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
    camp.setSpendCapAmount(10, null);
    camp.setSpendCapType(Campaign.freqTypes.asap);
    camp.setImpressionCapType(Campaign.freqTypes.asap);
    camp.setTotalImpressionBudget(100);
    camp.setImpressionCapAutomatic(true);
    camp.setUseMmFreq(false);
    camp.setMeritPixelId(800781);
    camp.setTotalBudget(200, "USD");

    try {
      camp = (Campaign) t1.save(camp);
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
    camp.setAdServerFee(10.01, null);
    camp.setConversionType("variable");
    camp.setConversionVariableMinutes(1);
    camp.setGoalType(Campaign.goalTypes.cpa);
    camp.setGoalValue(100, null);
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
    camp.setSpendCapAmount(10, null);
    camp.setSpendCapType(Campaign.freqTypes.even);
    camp.setImpressionCapType(Campaign.freqTypes.even);
    camp.setImpressionCapAmount(10);
    camp.setUseMmFreq(false);
    camp.setMeritPixelId(800781);
    camp.setTotalBudget(200, "USD");

    try {
      camp = (Campaign) t1.save(camp);
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
    camp.setAdServerFee(10.01, null);
    camp.setConversionType("variable");
    camp.setConversionVariableMinutes(1);
    camp.setGoalType(Campaign.goalTypes.cpa);
    camp.setGoalValue(100, null);
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
    camp.setTotalBudget(200, "USD");
   // camp.setSpendCapAutomatic(false);

    try {
      camp = (Campaign) t1.save(camp);
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
  public void testCampaignPostImpressionCapDefault()
      throws ClientException, java.text.ParseException {
    TerminalOne t1 = new TerminalOne(user, password, apiKey);

    Campaign camp = new Campaign();
    // camp.setId(268746);

    camp.setAdServerId(9);
    camp.setAdvertiserId(122631);
    camp.setName("CampaignTest One");
    camp.setAdServerFee(10.01, null);
    camp.setConversionType("variable");
    camp.setConversionVariableMinutes(1);
    camp.setGoalType(Campaign.goalTypes.cpa);
    camp.setGoalValue(100, null);
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
    camp.setMeritPixelId(800781);
    camp.setTotalBudget(200, "USD");

    try {
      camp = (Campaign) t1.save(camp);
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
    assertEquals(122631, campaignCreated.getAdvertiserId());
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

    QueryCriteria query = QueryCriteria.builder().setCollection("campaigns").setEntity(269897)
        .build();

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
    adv.setAgencyId(109308);
    adv.setDomain("http://www.advertiser.com");
    adv.setName("TestAdvertiser");
    adv.setVerticalId(11);
    try {
      adv = (Advertiser) jt1.save(adv);
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

  /**
   * Advertiser Update.
   * 
   * @throws ClientException
   */
  @Test
  public void testAdvertiserUpdatePost() throws ClientException {
    TerminalOne jt1 = new TerminalOne(user, password, apiKey);

    QueryCriteria query = QueryCriteria.builder().setCollection("advertisers").setEntity(165803)
        .build();

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
    str.setBudget(100.12f);
    str.setCampaignId(269897);
    str.setFrequencyType(freqType.asap);
    str.setFrequencyAmount(10);
    str.setFrequencyInterval(freqInt.day);
    str.setGoalType(goalType.spend);
    str.setGoalValue(12.12f);
    str.setMaxBid(10f);
    str.setPacingAmount(10f);
    str.setType(type.REM);
    str.setUseCampaignStart(false);

    str.setUseCampaignEnd(false);

    // str.setStart_date("2016-09-22T21:42:29+0000");

    // str.setEnd_date("2016-10-15T21:42:29+0000");
    // 2016-10-22T16:28:35+0530
    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

    cal.roll(Calendar.DATE, true);
    Date startDate = cal.getTime();
    str.setStartDate(startDate);

    cal.roll(Calendar.DATE, true);
    cal.roll(Calendar.MONTH, true);
    Date endd = cal.getTime();

    // str.setEndDate(endd);

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

  /**
   * Strategy Update.
   * 
   * @throws ClientException
   */
  @Test
  public void testStrategyUpdatePost() throws ClientException {
    TerminalOne jt1 = new TerminalOne(user, password, apiKey);

    QueryCriteria query = QueryCriteria.builder().setCollection("strategies").setEntity(1378068)
        .build();

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
    str.setId(1377524);
    str.setAudienceSegmentExcludeOp(Strategy.audSegExc.OR);
    str.setAudienceSegmentIncludeOp(Strategy.audSegInc.OR);
    List<Segments> asList = new ArrayList<Segments>();

    asList.add(new Segments(691, Segments.restrictions.INCLUDE, Segments.audSegExc.OR,
        Segments.audSegInc.OR));
    str.setAudienceSegments(asList);
    try {
      str = jt1.save(str);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    List<Segments> audienceSeg = str.getAudienceSegments();
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
  }

  /**
   * create Strategy Day Parts Post.
   * 
   * @throws ClientException
   */
  @Test
  public void testStrategyDayParts() throws ClientException {
    TerminalOne jt1 = new TerminalOne(user, password, apiKey);

    StrategyDayPart strategyDayPart = new StrategyDayPart();

    strategyDayPart.setDays(daysEnum.W);
    strategyDayPart.setEndHour(15);
    strategyDayPart.setStartHour(10);
    strategyDayPart.setStrategyId(1377524);
    strategyDayPart.setUserTime(true);

    try {
      strategyDayPart = (StrategyDayPart) jt1.save(strategyDayPart);
    } catch (ParseException e) {
      e.printStackTrace();
    }

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

  /**
   * Create Organization.
   * 
   * @throws ClientException
   */
  @Test
  public void testOrganizationPost() throws ClientException {
    TerminalOne jt1 = new TerminalOne(user, password, apiKey);
    QueryCriteria query = QueryCriteria.builder().setCollection("organizations").setEntity(100048)
        .build();

    JsonResponse<?> jsonresponse = null;

    try {
      jsonresponse = jt1.get(query);
    } catch (ClientException | ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    Organization org = (Organization) jsonresponse.getData();
    org.setId(100048);

    org.setAddress1("First Lane, New York");
    org.setCity("New York");

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
    assertEquals(100048, orgCreated.getId());
    // assertEquals("TestOrg", orgCreated.getName());
  }

  /**
   * Delete strategy concept.
   * 
   */
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

  /**
   * Strategy Concept update.
   * 
   * @throws ClientException
   */
  @Test
  public void testStrategyConceptPost() throws ClientException {
    TerminalOne jt1 = new TerminalOne(user, password, apiKey);

    QueryCriteria query = QueryCriteria.builder().setCollection("strategy_concepts")
        .setEntity(3627058).build();

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

  /**
   * Create Strategy Supply Sources.
   * 
   * @throws ClientException
   */
  @Test
  public void testStrategySupplySources() throws ClientException {
    TerminalOne jt1 = new TerminalOne(user, password, apiKey);

    StrategySupplySource strategySupplySource = new StrategySupplySource();
    strategySupplySource.setStrategyId(129253);
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
   */
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

  /**
   * Create Campaign margin.
   * 
   * @throws ClientException
   */
  @Test
  public void testCampaignMarginPost() throws ClientException {
    TerminalOne t1 = new TerminalOne(user, password, apiKey);

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

    try {
      camp = (Campaign) t1.save(camp);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

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
    camp.setAdvertiserId(122631);
    camp.setName("TestConcept1");
    camp.setStatus(true);

    try {
      camp = (Concept) t1.save(camp);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  /**
   * Concept update.
   * 
   * @throws ClientException
   */
  @Test
  public void testConceptPostUpdate() throws ClientException {
    TerminalOne t1 = new TerminalOne(user, password, apiKey);
    QueryCriteria query = QueryCriteria.builder().setCollection("concepts").setEntity(813124)
        .build();

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
      ac = (AtomicCreative) t1.save(ac);
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  /**
   * Atomic Creative Update.
   * 
   * @throws ClientException
   */
  @Test
  public void testAtomicCreativesUpdate() throws ClientException {

    TerminalOne t1 = new TerminalOne(user, password, apiKey);

    QueryCriteria query = QueryCriteria.builder().setCollection("atomic_creatives")
        .setEntity(2692236).build();

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
    VideoCreativeResponse uploadResponse = t1.uploadVideoCreative(filePath, fileName,
        saveResponse.getCreativeId());

    // check video creative status VideoCreativeUploadStatus uploadStatus =
    t1.getVideoCreativeUploadStatus(uploadResponse.getCreativeId());

    assertNotNull(saveResponse);
    assertNotNull(saveResponse.getCreativeId());

    assertNotNull(uploadResponse);
    assertNotNull(uploadResponse.getStatus());
  }

}
