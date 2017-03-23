package com.mediamath.terminalone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.mediamath.terminalone.models.Campaign;
import com.mediamath.terminalone.models.Concept;
import com.mediamath.terminalone.models.Data;
import com.mediamath.terminalone.models.JsonResponse;
import com.mediamath.terminalone.models.Strategy;
import com.mediamath.terminalone.models.T1User;
import com.mediamath.terminalone.utils.ConditionQuery;
import com.mediamath.terminalone.utils.Filters;
import com.mediamath.terminalone.utils.FullParamValues;
import com.mediamath.terminalone.utils.QueryParamValues;

@RunWith(MockitoJUnitRunner.class)
public class GetMockTest {

	private static Properties testConfig = new Properties();
	private static String LOGIN = null;

	@Mock
	Connection connectionmock;

	@InjectMocks
	TerminalOne t1 = new TerminalOne();

	@Mock
	Response response;

	@BeforeClass
	public static void init() throws Exception {
		InputStream input = PostFunctionalTestIT.class.getClassLoader().getResourceAsStream("mocktest.properties");
		testConfig.load(input);
		LOGIN = testConfig.getProperty("t1.mock.loginResponse");
	}

	@After
	public final void tearDown() throws InterruptedException {
		Thread.sleep(5000);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAdvertiserGettWithMocks() throws ClientException, ParseException {

		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1User.class))).thenReturn(
				"{\"data\" : [{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\",\"id\" : 165615}],"
						+ "\"meta\" : {\"next_page\" : \"https://t1sandbox-origin.mediamath.com/api/v2.0/advertisers"
						+ "?page_offset=40&api_key=e34f74vnubr9uxasz2n7bdfv&page_limit=40\",\"etag\" : \"e2fae343fdc5b6aeb4f782c9ea31860c64ec47c9\","
						+ "\"count\" : 1,\"called_on\" : \"2016-07-01T15:25:07+0000\",\"status\" : \"ok\",\"offset\" : 0,\"total_count\" : 437}}");

		QueryCriteria query = QueryCriteria.builder().setCollection("advertisers").setPageLimit(1).build();

		JsonResponse<?> jsonresponse = null;

		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			jsonresponse = t1.get(query);
			Mockito.verify(connectionmock).get(Mockito.anyString(), Mockito.any(T1User.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		assertNotNull(jsonresponse.getData());
		ArrayList<Advertiser> advertisers = ((ArrayList<Advertiser>) jsonresponse.getData());
		assertEquals(165615, advertisers.get(0).getId());
		assertNotNull(jsonresponse.getMeta());
	}

	@Test
	public void testBaiscGetWithChildUsingQueryCriteriaWithMocks() throws ClientException, ParseException {

		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1User.class))).thenReturn(
				"{\"data\" : {\"minimize_multi_ads\" : false,\"frequency_type\" : \"no-limit\",\"status\" : true,\"dmp_enabled\" : \"inherits\","
						+ "\"agency_id\" : 111718,\"frequency_interval\" : \"not-applicable\",\"updated_on\" : \"2015-11-27T18:02:24+0000\","
						+ "\"domain\" : \"http://www.mahou-sanmiguel.com\",\"created_on\" : \"2015-11-27T18:02:24+0000\",\"entity_type\" : \"advertiser\","
						+ "\"agency\" : {\"organization_id\" : 100048,\"rel\" : \"agency\",\"status\" : true,\"version\" : 0,\"dmp_enabled\" : \"inherits\","
						+ "\"name\" : \"OLO 111718\",\"allow_x_adv_pixels\" : true,\"updated_on\" : \"2015-11-27T18:00:25+0000\",\"logo\" : \"http://t1.mediamath.com/app/\","
						+ "\"created_on\" : \"2015-11-27T18:00:25+0000\",\"entity_type\" : \"agency\",\"id\" : 111718,\"allow_x_adv_optimization\" : false,"
						+ "\"organization\" : {\"rel\" : \"organization\",\"status\" : true,\"dmp_enabled\" : \"enabled\",\"contact_name\" : \"Ian Hummel\","
						+ "\"org_type\" : [\"buyer\"],\"updated_on\" : \"2016-04-21T14:40:44+0000\",\"state\" : \"NY\",\"address_2\" : \"4WTC\","
						+ "\"address_1\" : \"1440 Broadway\",\"mm_contact_name\" : \"Ian Hummel\",\"city\" : \"New York\",\"allow_x_agency_pixels\" : true,"
						+ "\"restrict_targeting_to_deterministic_id\" : true,\"created_on\" : \"2010-10-19T16:58:05+0000\",\"entity_type\" : \"organization\","
						+ "\"id\" : 100048,\"country\" : \"US\",\"opt_out_connected_id_mathid\" : false,\"currency_code\" : \"USD\",\"override_suspicious_traffic_filter\" : false,"
						+ "\"version\" : 120,\"name\" : \"ACME Org\",\"restrict_targeting_to_same_device_id\" : true,\"phone\" : \"(347) 420-0116\",\"suspicious_traffic_filter_level\" : 25,"
						+ "\"allow_byo_price\" : true,\"opt_out_connected_id\" : true,\"zip\" : \"10018\",\"billing_country_code\" : \"US\",\"use_evidon_optout\" : true,\"adx_seat_account_id\" : 139299155}},"
						+ "\"id\" : 154161,\"version\" : 0,\"name\" : \"MAHOU - SMIGUEL\",\"ad_server_id\" : 6,\"allow_x_strat_optimization\" : false,\"vertical_id\" : 30,"
						+ "\"vertical\" : {\"rel\" : \"vertical\",\"created_on\" : \"2012-10-23T22:22:15+0000\",\"version\" : 1,\"entity_type\" : \"vertical\",\"name\" : \"Alcohol\","
						+ "\"id\" : 30,\"updated_on\" : \"2012-10-23T22:22:34+0000\"},"
						+ "\"ad_server\" : {\"rel\" : \"ad_server\",\"version\" : 0,\"entity_type\" : \"ad_server\",\"name\" : \"Other\",\"id\" : 6}},"
						+ "\"meta\" : {\"etag\" : \"72de229843688c896864e7a67358edf8e3122551\",\"called_on\" : \"2016-07-14T09:11:39+0000\",\"status\" : \"ok\"}}");
		QueryCriteria query = QueryCriteria.builder().setCollection("advertisers").setEntity(154161)
				.setInclude(new ConditionQuery("agency", "organization")).build();
		query = QueryCriteria.builder(query).setInclude(new ConditionQuery("ad_server"))
				.setInclude(new ConditionQuery("vertical")).build();

		JsonResponse<?> jsonresponse = null;

		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			jsonresponse = t1.get(query);
			Mockito.verify(connectionmock).get(Mockito.anyString(), Mockito.any(T1User.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		assertNotNull(jsonresponse.getData());
		Advertiser advertisers = ((Advertiser) jsonresponse.getData());
		assertEquals(154161, advertisers.getId());
		assertNotNull(jsonresponse.getMeta());

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testBaiscGetWithSortByUsingQueryCriteriaWithMock() throws ClientException, ParseException {
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1User.class))).thenReturn(
				"{\"data\" : [{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\",\"id\" : 165615},"
						+ "{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\",\"id\" : 165612},"
						+ "{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\",\"id\" : 165611},"
						+ "{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\",\"id\" : 165608},"
						+ "{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\",\"id\" : 165607}],"
						+ "\"meta\" : {\"next_page\" : \"https://t1sandbox-origin.mediamath.com/api/v2.0/advertisers?page_offset=5&sort_by=-id&api_key=e34f74vnubr9uxasz2n7bdfv&page_limit=5\","
						+ "\"etag\" : \"b81e3860fa348058a011846933765bb08a59af6b\",\"count\" : 5,\"called_on\" : \"2016-07-14T11:14:37+0000\",\"status\" : \"ok\",\"offset\" : 0,\"total_count\" : 437}}");

		QueryCriteria query = QueryCriteria.builder().setCollection("advertisers").setSortBy("-id").setPageLimit(5)
				.build();
		JsonResponse<?> jsonresponse = null;

		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			jsonresponse = t1.get(query);
			Mockito.verify(connectionmock).get(Mockito.anyString(), Mockito.any(T1User.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		assertNotNull(jsonresponse.getData());
		ArrayList<Advertiser> advertisers = ((ArrayList<Advertiser>) jsonresponse.getData());
		assertTrue(advertisers.get(0).getId() > advertisers.get(1).getId());
		assertNotNull(jsonresponse.getMeta());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testBaiscGetWithPageLimitWithMock() throws ClientException, ParseException {
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1User.class))).thenReturn(
				"{\"data\" : [{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\",\"id\" : 165615},"
						+ "{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\",\"id\" : 165612},"
						+ "{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\",\"id\" : 165611},"
						+ "{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\",\"id\" : 165608},"
						+ "{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\",\"id\" : 165607}],"
						+ "\"meta\" : {\"next_page\" : \"https://t1sandbox-origin.mediamath.com/api/v2.0/advertisers?page_offset=5&sort_by=-id&api_key=e34f74vnubr9uxasz2n7bdfv&page_limit=5\","
						+ "\"etag\" : \"b81e3860fa348058a011846933765bb08a59af6b\",\"count\" : 5,\"called_on\" : \"2016-07-14T11:14:37+0000\",\"status\" : \"ok\",\"offset\" : 0,\"total_count\" : 437}}");

		QueryCriteria query = QueryCriteria.builder().setCollection("advertisers").setPageLimit(5).build();

		JsonResponse<?> jsonresponse = null;

		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			jsonresponse = t1.get(query);
			Mockito.verify(connectionmock).get(Mockito.anyString(), Mockito.any(T1User.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		assertNotNull(jsonresponse.getData());
		ArrayList<Advertiser> advertisers = ((ArrayList<Advertiser>) jsonresponse.getData());
		assertTrue(advertisers.size() == 5);
		assertNotNull(jsonresponse.getMeta());
	}

	@Test
	public void testBaiscGetWithPageLimitOffsetWithMock() throws ClientException, ParseException {
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1User.class))).thenReturn(
				"{\"data\" : [{\"entity_type\" : \"advertiser\",\"name\" : \"ITtest - Wed Jul  9 19:27:53 2014\",\"id\" : 125814},"
						+ "{\"entity_type\" : \"advertiser\",\"name\" : \"ITtest - Wed Jul  9 19:26:35 2014\",\"id\" : 125813},"
						+ "{\"entity_type\" : \"advertiser\",\"name\" : \"ITtest - Wed Jul  9 19:16:35 2014\",\"id\" : 125812},"
						+ "{\"entity_type\" : \"advertiser\",\"name\" : \"ittest35\",\"id\" : 125810},"
						+ "{\"entity_type\" : \"advertiser\",\"name\" : \"ITtest - Wed Jul  9 18:23:07 2014\",\"id\" : 125809}],"
						+ "\"meta\" : {\"next_page\" : \"https://t1sandbox-origin.mediamath.com/api/v2.0/advertisers?page_offset=305&api_key=e34f74vnubr9uxasz2n7bdfv&page_limit=5\","
						+ "\"etag\" : \"dec1c26f7e2799a15684b07cbe0ef8fdcd1ee101\",\"count\" : 5,\"called_on\" : \"2016-07-18T06:51:33+0000\",\"status\" : \"ok\",\"offset\" : \"300\",\"total_count\" : 437}}");
		QueryCriteria query = QueryCriteria.builder().setCollection("advertisers").setPageLimit(5).setPageOffset(300)
				.build();
		JsonResponse<?> jsonresponse = null;

		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			jsonresponse = t1.get(query);
			Mockito.verify(connectionmock).get(Mockito.anyString(), Mockito.any(T1User.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);

	}

	@Test
	public void testBaiscGetWithLimitWithMock() throws ClientException, ParseException {
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1User.class))).thenReturn(
				"{\"data\" : [{\"entity_type\" : \"advertiser\",\"name\" : \"JPTAK updated advertiser name\",\"id\" : 153648}],"
						+ "\"meta\" : {\"etag\" : \"5b27a314978a549556affb650670a8f902bec1c9\",\"count\" : 1,\"called_on\" : \"2016-07-18T07:04:49+0000\",\"status\" : \"ok\",\"offset\" : 0,\"total_count\" : 1}}");

		Map<String, Long> limitList = new HashMap<String, Long>();
		limitList.put("agency", Long.valueOf(111555));
		QueryCriteria query = QueryCriteria.builder().setCollection("advertisers").setLimit(limitList).setPageLimit(5)
				.build();

		JsonResponse<?> jsonresponse = null;

		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			jsonresponse = t1.get(query);
			Mockito.verify(connectionmock).get(Mockito.anyString(), Mockito.any(T1User.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));

		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);

	}
	
	@Test
	public void testBaiscGetWithLimitnGetAllWithMock() throws ClientException, ParseException {
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1User.class))).thenReturn(
				"{\"data\": [{\"entity_type\": \"advertiser\",\"name\": \"TestAdvertiser\",\"id\": 166077},{\"entity_type\": \"advertiser\",\"name\": \"TestAdvertiser\",\"id\": 166076},"
+"{\"entity_type\": \"advertiser\",\"name\": \"Riot\",\"id\": 166074},{\"entity_type\": \"advertiser\",\"name\": \"Riot\",\"id\": 166072},{\"entity_type\": \"advertiser\","
+"\"name\": \"Riot\",\"id\": 166069},{\"entity_type\": \"advertiser\",\"name\": \"Riot\",\"id\": 166068},{\"entity_type\": \"advertiser\",\"name\": \"Riot\",\"id\": 166067"
+"},{\"entity_type\": \"advertiser\",\"name\": \"TestAdvertiser\",\"id\": 165985},{\"entity_type\": \"advertiser\",\"name\": \"TestAdvertiser\",\"id\": 165984},"
+"{\"entity_type\": \"advertiser\",\"name\": \"TestAdvertiser\", \"id\": 165978},{\"entity_type\": \"advertiser\",\"name\": \"TestAdvertiser\",\"id\": 165977},"
+"{\"entity_type\": \"advertiser\",\"name\": \"TestAdvertiser\", \"id\": 165968},{\"entity_type\": \"advertiser\",\"name\": \"TestAdvertiser\",\"id\": 165967},"
+"{\"entity_type\": \"advertiser\",\"name\": \"TestAdvertiser\", \"id\": 165966},{\"entity_type\": \"advertiser\",\"name\": \"TestAdvertiser\",\"id\": 165804},"
+"{\"entity_type\": \"advertiser\",\"name\": \"TestAdvertiser\", \"id\": 165803},{\"entity_type\": \"advertiser\",\"name\": \"TestAdvertiser\",\"id\": 165802},"
+"{\"entity_type\": \"advertiser\",\"name\": \"TestAdvertiser\", \"id\": 165801},{\"entity_type\": \"advertiser\",\"name\": \"ABC Advertisers\", \"id\": 165778},"
+"{\"entity_type\": \"advertiser\",\"name\": \"ABC Advertisers\", \"id\": 165776},{\"entity_type\": \"advertiser\",\"name\": \"ABC Advertisers\",\"id\": 165775},"
+"{\"entity_type\": \"advertiser\",\"name\": \"ABC Advertisers\", \"id\": 165656},{\"entity_type\": \"advertiser\",\"name\": \"ABC Advertisers\",\"id\": 165615},"
+"{\"entity_type\": \"advertiser\",\"name\": \"ABC Advertisers\", \"id\": 165612},{\"entity_type\": \"advertiser\",\"name\": \"ABC Advertisers\",\"id\": 165611},"
+"{\"entity_type\": \"advertiser\",\"name\": \"ABC Advertisers\", \"id\": 165608},{\"entity_type\": \"advertiser\",\"name\": \"ABC Advertisers\",\"id\": 165607},"
+"{\"entity_type\": \"advertiser\",\"name\": \"Test_Hector\",\"id\": 165343 },{ \"entity_type\": \"advertiser\",\"name\": \"AutoDesk\",\"id\": 165316},"
+"{\"entity_type\": \"advertiser\",\"name\": \"Ciel- Luisa\",\"id\": 165304 },{ \"entity_type\": \"advertiser\",\"name\": \"Wade Test Advertiser\",\"id\": 165169},"
+"{\"entity_type\": \"advertiser\",\"name\": \"KMAC\",\"id\": 164903},{  \"entity_type\": \"advertiser\",\"name\": \"JMADVERTISER5\",\"id\": 163257},"
+"{\"entity_type\": \"advertiser\",\"name\": \"JMADVERTISER4\",\"id\": 163256},{\"entity_type\": \"advertiser\",\"name\": \"Julie Test Advertiser 3\",\"id\": 163057},"
+"{\"entity_type\": \"advertiser\",\"name\": \"HL Advertiser\",\"id\": 162863},{\"entity_type\": \"advertiser\",\"name\": \"Fan Giraffe Hotel\",\"id\": 162808},"
+"{\"entity_type\": \"advertiser\",\"name\": \"JoyTestAdvertiser\",\"id\": 162654},{\"entity_type\": \"advertiser\",\"name\": \"B17ADVERTISER\",\"id\": 162650},"
+"{\"entity_type\": \"advertiser\",\"name\": \"Jesse's Girls Kids Clothing\",\"id\": 162385},{\"entity_type\": \"advertiser\",\"name\": \"Shoes by Olive\",\"id\": 162384},"
+"{\"entity_type\": \"advertiser\",\"name\": \"JP_Pub_Test\",\"id\": 162373},{ \"entity_type\": \"advertiser\",\"name\": \"ALYSE TEST ADVERTISER 2\",\"id\": 162185},"
+"{\"entity_type\": \"advertiser\",\"name\": \"Gillette\", \"id\": 162142},{\"entity_type\": \"advertiser\",\"name\": \"Anj Advertiser\",\"id\": 162141},"
+"{\"entity_type\": \"advertiser\",\"name\": \"033116 - Advertiser\",\"id\": 161967},{\"entity_type\": \"advertiser\",\"name\": \"test 2.127.0 adv\",\"id\": 161966},"
+"{\"entity_type\": \"advertiser\",\"name\": \"test_Adv_2.126.1_py\",\"id\": 161498},{\"entity_type\": \"advertiser\",\"name\": \"test_update_postgres\",\"id\": 161359}],"
+"\"meta\": {\"next_page\": \"https://t1sandbox-origin.mediamath.com/api/v2.0/advertisers?page_offset=100&api_key=98waz2495u8n5udu7pzczfh5&get_all=true\",\"etag\": \"dacfb09f38adbe1df5de1b6b5bd4cffa52f38df4\", \"count\": 70,"
+"\"called_on\": \"2017-02-17T06:55:58+0000\",\"status\": \"ok\",\"offset\": 0,\"total_count\": 459}}");

		QueryCriteria query = QueryCriteria.builder().setCollection("advertisers").setGetAll(true).build();

		JsonResponse<?> jsonresponse = null;

		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			jsonresponse = t1.get(query);
			Mockito.verify(connectionmock).get(Mockito.anyString(), Mockito.any(T1User.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));

		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);

	}
	
	  @Test
	  public void testBaiscGetErrorHandling() throws ClientException {

			Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
			.thenReturn(response);
	Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
	Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1User.class))).thenReturn(
			"{\"errors\":[{\"message\": \"Object 'advertiser' has no relationship 'campaign'\"}],"
					+"\"meta\": {\"called_on\": \"2017-02-17T09:01:07+0000\",\"status\": \"invalid\"}}");

    Map<String, Long> limitList = new HashMap<String, Long>();
    limitList.put("campaign", Long.valueOf(111555));
    QueryCriteria query = QueryCriteria.builder().setCollection("advertisers")
        .setLimit(limitList).setPageLimit(100).build();

	JsonResponse<?> jsonresponse = null;

	try {
		t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
		jsonresponse = t1.get(query);
		Mockito.verify(connectionmock).get(Mockito.anyString(), Mockito.any(T1User.class));
		Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
				Mockito.any(T1User.class));

	} catch (ClientException | ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	    assertNull(jsonresponse);
	  }


	@SuppressWarnings("unchecked")
	@Test
	public void testBaiscGetWithQueryWithMock() throws ClientException, ParseException {

		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1User.class))).thenReturn(
				"{\"data\" : [{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\",\"id\" : 165615,\"agency\" : {\"rel\" : \"agency\",\"entity_type\" : \"agency\",\"name\" : \"EGLE AM 109308\","
						+ "\"id\" : 109308,\"organization\" : {\"rel\" : \"organization\",\"entity_type\" : \"organization\",\"name\" : \"ACME Org\",\"id\" : 100048}}},"
						+ "{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\",\"id\" : 165612,\"agency\" : {\"rel\" : \"agency\",\"entity_type\" : \"agency\",\"name\" : \"EGLE AM 109308\",\"id\" : 109308,"
						+ "\"organization\" : {\"rel\" : \"organization\",\"entity_type\" : \"organization\",\"name\" : \"ACME Org\",\"id\" : 100048}}},{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\","
						+ "\"id\" : 165611,\"agency\" : {\"rel\" : \"agency\",\"entity_type\" : \"agency\",\"name\" : \"EGLE AM 109308\",\"id\" : 109308,\"organization\" : {\"rel\" : \"organization\",\"entity_type\" : \"organization\",\"name\" : \"ACME Org\",\"id\" : 100048}}},"
						+ "{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\",\"id\" : 165608,\"agency\" : {\"rel\" : \"agency\",\"entity_type\" : \"agency\",\"name\" : \"EGLE AM 109308\",\"id\" : 109308,"
						+ "\"organization\" : {\"rel\" : \"organization\",\"entity_type\" : \"organization\",\"name\" : \"ACME Org\",\"id\" : 100048}}},{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\","
						+ "\"id\" : 165607,\"agency\" : {\"rel\" : \"agency\",\"entity_type\" : \"agency\",\"name\" : \"EGLE AM 109308\",\"id\" : 109308,\"organization\" : {\"rel\" : \"organization\",\"entity_type\" : \"organization\",\"name\" : \"ACME Org\",\"id\" : 100048}}}],"
						+ "\"meta\" : {\"next_page\" : \"https://t1sandbox-origin.mediamath.com/api/v2.0/advertisers?page_offset=5&q=agency_id%3E%3D109308&with=agency%2Corganization&api_key=e34f74vnubr9uxasz2n7bdfv&page_limit=5\","
						+ "\"etag\" : \"0fb44654d91976113634f32e03795e591b64d1e8\",\"count\" : 5,\"called_on\" : \"2016-07-18T07:24:19+0000\",\"status\" : \"ok\",\"offset\" : 0,\"total_count\" : 124}}");

		Map<String, Long> limitList = new HashMap<String, Long>();
		limitList.put("agency", Long.valueOf(111555));
		QueryCriteria query = QueryCriteria.builder().setCollection("advertisers")
				.setInclude(new ConditionQuery("agency", "organization")).setQuery("agency_id%3E=109308")
				.setPageLimit(5).build();
		JsonResponse<?> jsonresponse = null;
		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			jsonresponse = t1.get(query);
			Mockito.verify(connectionmock).get(Mockito.anyString(), Mockito.any(T1User.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));

		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);

	}

	@Test
	public void testBaiscGetWithFindWithMock() throws ClientException, ParseException {
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1User.class))).thenReturn(
				"{\"data\" : [{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\",\"id\" : 165615},{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\",\"id\" : 165612},{\"entity_type\" : \"advertiser\","
						+ "\"name\" : \"ABC Advertisers\",\"id\" : 165611},{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\",\"id\" : 165608},{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\",\"id\" : 165607}],"
						+ "\"meta\" : {\"next_page\" : \"https://t1sandbox-origin.mediamath.com/api/v2.0/advertisers?page_offset=5&q=agency_id%3E%3D109308&api_key=e34f74vnubr9uxasz2n7bdfv&page_limit=5\","
						+ "\"etag\" : \"b81e3860fa348058a011846933765bb08a59af6b\",\"count\" : 5,\"called_on\" : \"2016-07-18T08:40:48+0000\",\"status\" : \"ok\",\"offset\" : 0,\"total_count\" : 124}}");

		Map<String, Long> limitList = new HashMap<String, Long>();
		limitList.put("agency", Long.valueOf(111555));
		QueryCriteria query = QueryCriteria.builder().setCollection("advertisers").setQueryParamName("agency_id")
				.setQueryOperator(Filters.GREATER_OR_EQUAL).setQueryParams(new QueryParamValues(109308))
				.setPageLimit(100).build();

		JsonResponse<?> jsonresponse = null;
		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			jsonresponse = t1.find(query);
			Mockito.verify(connectionmock).get(Mockito.anyString(), Mockito.any(T1User.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));

		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			throw new AssertionError();
		}

		assertNotNull(jsonresponse);
	}

	@Test
	public void testBaiscGetWithFind1WithMock() throws ClientException, ParseException {
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1User.class))).thenReturn(
				"{\"data\" : [],\"meta\" : {\"etag\" : \"97d170e1550eee4afc0af065b78cda302a97674c\",\"count\" : 0,\"called_on\" : \"2016-07-18T08:48:22+0000\",\"status\" : \"ok\",\"offset\" : 0,\"total_count\" : 0}}");

		Map<String, Long> limitList = new HashMap<String, Long>();
		limitList.put("agency", Long.valueOf(111555));
		QueryCriteria query = QueryCriteria.builder().setCollection("advertisers").setQueryParamName("name")
				.setQueryOperator(Filters.EQUAL).setQueryParams(new QueryParamValues("Retirement")).setPageLimit(100)
				.build();

		JsonResponse<?> jsonresponse = null;
		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			jsonresponse = t1.find(query);
			Mockito.verify(connectionmock).get(Mockito.anyString(), Mockito.any(T1User.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));

		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);

	}

	@Test
	public void testBaiscGetWithFind2WithMock() throws ClientException, ParseException {
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1User.class))).thenReturn(
				"{\"data\" : [{\"entity_type\" : \"advertiser\",\"name\" : \"Japan Samurai\",\"id\" : 154121},{\"entity_type\" : \"advertiser\",\"name\" : \"upcast_test\",\"id\" : 153226},{\"entity_type\" : \"advertiser\",\"name\" : \"Test_Dell\",\"id\" : 150994}],"
						+ "\"meta\" : {\"etag\" : \"9c2310a74a3984d3e789895531eb79f30c858231\",\"count\" : 3,\"called_on\" : \"2016-07-18T08:54:54+0000\",\"status\" : \"ok\",\"offset\" : 0,\"total_count\" : 3}}");

		List<Object> qParams = new ArrayList<Object>();
		qParams.add(154121);
		qParams.add(153226);
		qParams.add(150994);
		QueryCriteria query = QueryCriteria.builder().setCollection("advertisers")
				.setQueryParams(new QueryParamValues("name")).setQueryOperator(Filters.IN)
				.setQueryParams(new QueryParamValues(qParams)).build();
		JsonResponse<?> jsonresponse = null;
		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			jsonresponse = t1.find(query);
			Mockito.verify(connectionmock).get(Mockito.anyString(), Mockito.any(T1User.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));

		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);

	}

	@Test
	public void testGetWithFullBooleanWithMocks() throws ClientException, ParseException {
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1User.class))).thenReturn(
				"{\"data\" : [{\"use_mm_freq\" : false,\"spend_cap_type\" : \"no-limit\",\"zone_name\" : \"America/New_York\",\"frequency_interval\" : \"not-applicable\",\"updated_on\" : \"2016-07-20T06:28:04+0000\","
						+ "\"use_default_ad_server\" : false,\"initial_start_date\" : \"2016-07-20T06:28:07+0000\",\"restrict_targeting_to_deterministic_id\" : false,\"created_on\" : \"2016-07-20T06:28:04+0000\",\"id\" : 266952,"
						+ "\"total_budget\":[{\"currency_code\" : \"USD\",\"value\" : 100}],\"goal_alert\" : 0,\"service_type\" : \"SELF\",\"currency_code\" : \"USD\",\"ad_server_fee\":[{\"currency_code\" : \"USD\",\"value\" : 10.01}],"
						+ "\"has_custom_attribution\" : false,\"name\" : \"NitCamp 266952\",\"ad_server_id\" : 9,\"frequency_amount\" : 0,\"restrict_targeting_to_same_device_id\" : false,\"suspicious_traffic_filter_level\" : 25,"
						+ "\"end_date\" : \"2016-08-21T06:28:07+0000\",\"frequency_optimization\" : false,\"advertiser_id\" : 122631,\"conversion_variable_minutes\" : 1,\"minimize_multi_ads\" : false,\"status\" : false,\"goal_type\" : \"cpa\","
						+ "\"frequency_type\" : \"no-limit\",\"margin_pct\" : 0,\"dcs_data_is_campaign_level\" : false,\"impression_cap_type\" : \"no-limit\",\"goal_value\":[{\"currency_code\" : \"USD\",\"value\" : 100}],"
						+ "\"spend_cap_amount\" : [{\"currency_code\" : \"USD\",\"value\" : 10}],\"entity_type\" : \"campaign\",\"pc_window_minutes\" : 1,\"start_date\" : \"2016-07-20T06:28:07+0000\",\"override_suspicious_traffic_filter\" : false,"
						+ "\"spend_cap_enabled\" : false,\"version\" : 0,\"agency_fee_pct\" : 0,\"impression_cap_automatic\" : false,\"spend_cap_automatic\" : false,\"conversion_type\" : \"variable\",\"merit_pixel_id\" : 800781,\"pv_pct\" : 100}],"
						+ "\"meta\" : {\"next_page\" : \"https://t1sandbox-origin.mediamath.com/api/v2.0/campaigns?page_offset=1&sort_by=-updated_on&full=*&api_key=e34f74vnubr9uxasz2n7bdfv&page_limit=1\",\"etag\" : \"cda36b62cea24758c4738e1bb8693f9eb344cbe2\","
						+ "\"count\" : 1,\"called_on\" : \"2016-09-12T09:26:11+0000\",\"status\" : \"ok\",\"offset\" : 0,\"total_count\" : 2224}}");
		FullParamValues fpv = new FullParamValues();
		fpv.setBoolValue(true);
		Map<String, Long> limitList = new HashMap<String, Long>();
		limitList.put("agency", Long.valueOf(111555));
		QueryCriteria query = QueryCriteria.builder().setCollection("campaigns").setFull(fpv).setSortBy("-updated_on")
				.setPageLimit(1).build();
		JsonResponse<?> jsonresponse = null;
		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			jsonresponse = t1.get(query);
			Mockito.verify(connectionmock).get(Mockito.anyString(), Mockito.any(T1User.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));

		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		assertNotNull(jsonresponse.getData());
		ArrayList<Campaign> campaigns = ((ArrayList<Campaign>) jsonresponse.getData());
	}

	@Test
	public void testBaiscGetWithFullStringWithMocks() throws ClientException, ParseException {
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1User.class))).thenReturn(
				"{\"data\" : [{\"entity_type\" : \"campaign\",\"name\" : \"NitCamp 266952\",\"id\" : 266952},{\"entity_type\" : \"campaign\",\"name\" : \"NitCamp 266857\",\"id\" : 266857},{\"entity_type\" : \"campaign\","
						+ "\"name\" : \"NitCamp 266851\",\"id\" : 266851},{\"entity_type\" : \"campaign\",\"name\" : \"NitCamp 266850\",\"id\" : 266850},{\"entity_type\" : \"campaign\",\"name\" : \"campaign dupe\",\"id\" : 266846}],"
						+ "\"meta\" : {\"next_page\" : \"https://t1sandbox-origin.mediamath.com/api/v2.0/campaigns?page_offset=10&sort_by=-updated_on&full=agency&api_key=e34f74vnubr9uxasz2n7bdfv&page_limit=10\","
						+ "\"etag\" : \"ae1576d736beea65fe66beb1c59a2d7aae27333a\",\"count\" : 10,\"called_on\" : \"2016-09-14T10:01:45+0000\",\"status\" : \"ok\",\"offset\" : 0,\"total_count\" : 2224}}");
		FullParamValues fpv = new FullParamValues();
		fpv.setStrValue("agency");
		Map<String, Long> limitList = new HashMap<String, Long>();
		limitList.put("agency", Long.valueOf(111555));
		QueryCriteria query = QueryCriteria.builder().setCollection("campaigns").setFull(fpv).setSortBy("-updated_on")
				.setPageLimit(10).build();
		JsonResponse<?> jsonresponse = null;
		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			jsonresponse = t1.get(query);
			Mockito.verify(connectionmock).get(Mockito.anyString(), Mockito.any(T1User.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));

		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		assertNotNull(jsonresponse.getData());
		ArrayList<Campaign> campaigns = ((ArrayList<Campaign>) jsonresponse.getData());
	}

	@Test
	public void testBaiscGetWithFullListWithMocks() throws ClientException, ParseException {
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1User.class))).thenReturn(
				"{\"data\": [{\"use_mm_freq\": false,\"spend_cap_type\": \"no-limit\",\"zone_name\": \"America/New_York\",\"frequency_interval\": \"not-applicable\",\"updated_on\": \"2016-09-19T07:53:07+0000\",\"use_default_ad_server\": false,"
						+ "\"initial_start_date\": \"2016-09-19T07:54:56+0000\",\"restrict_targeting_to_deterministic_id\": false,\"created_on\": \"2016-09-19T07:53:07+0000\",\"id\": 267886,\"total_budget\": [{\"currency_code\": \"USD\",\"value\": 100}],"
						+ "\"goal_alert\": 0,\"service_type\": \"SELF\",\"currency_code\": \"USD\",\"ad_server_fee\": [{\"currency_code\": \"USD\",\"value\": 10.01}],\"has_custom_attribution\": false,\"name\": \"NitCamp 267886\",\"ad_server_id\": 9,"
						+ "\"frequency_amount\": 0,\"restrict_targeting_to_same_device_id\": false,\"suspicious_traffic_filter_level\": 25,\"end_date\": \"2016-10-20T07:54:56+0000\",\"frequency_optimization\": false,\"advertiser_id\": 122631,"
						+ "\"conversion_variable_minutes\": 1,\"minimize_multi_ads\": false,\"status\": false,\"goal_type\": \"cpa\",\"frequency_type\": \"no-limit\",\"margin_pct\": 0,\"dcs_data_is_campaign_level\": false,\"impression_cap_type\": \"no-limit\","
						+ "\"goal_value\": [{\"currency_code\": \"USD\",\"value\": 100}],\"spend_cap_amount\": [{\"currency_code\": \"USD\",\"value\": 10}],\"entity_type\": \"campaign\",\"pc_window_minutes\": 1,\"start_date\": \"2016-09-19T07:54:56+0000\","
						+ "\"override_suspicious_traffic_filter\": false,\"spend_cap_enabled\": false,\"version\": 0,\"agency_fee_pct\": 0,\"impression_cap_automatic\": false,\"spend_cap_automatic\": false,\"conversion_type\": \"variable\","
						+ "\"merit_pixel_id\": 800781,\"pv_pct\": 100}],\"meta\": {\"next_page\": \"https://t1sandbox-origin.mediamath.com/api/v2.0/campaigns?page_offset=1&sort_by=-updated_on&full=campaign%2Cadvertiser&api_key=e34f74vnubr9uxasz2n7bdfv&page_limit=1\","
						+ "\"etag\": \"e25035a72158e56d83910320528a3614eb7ae489\",\"count\": 1,\"called_on\": \"2016-09-20T07:41:49+0000\",\"status\": \"ok\",\"offset\": 0,\"total_count\": 2225}}");
		FullParamValues fpv = new FullParamValues();
		List<String> newList = new ArrayList<String>();
		newList.add("campaign");
		newList.add("advertiser");
		fpv.setListValue(newList);
		Map<String, Long> limitList = new HashMap<String, Long>();
		limitList.put("agency", Long.valueOf(111555));
		QueryCriteria query = QueryCriteria.builder().setCollection("campaigns").setFull(fpv).setSortBy("-updated_on")
				.setPageLimit(1).build();
		JsonResponse<?> jsonresponse = null;
		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			jsonresponse = t1.get(query);
			Mockito.verify(connectionmock).get(Mockito.anyString(), Mockito.any(T1User.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));

		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		assertNotNull(jsonresponse.getData());
		ArrayList<Campaign> campaigns = ((ArrayList<Campaign>) jsonresponse.getData());
	}

	@Test
	public void testGetWithChildByUsingQC() throws ClientException, ParseException {
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1User.class))).thenReturn(
				"{\"data\" : {\"run_on_streaming\" : false,\"use_mm_freq\" : false, \"run_on_display\" : false,\"zone_name\" : \"America/New_York\",\"updated_on\" : \"2016-09-22T04:25:57+0000\",\"frequency_interval\" : \"day\",\"campaign_id\" : 267886,\"targeting_segment_exclude_op\" : \"OR\",\"created_on\" : \"2016-09-22T04:25:57+0000\",\"targeting_segment_include_op\" : \"OR\","
						+ "\"run_on_all_pmp\" : false,\"id\" : 1377524,\"impression_pacing_interval\" : \"day\",\"currency_code\" : \"USD\",\"use_campaign_start\" : false,\"name\" : \"ABC Advertisers\",\"frequency_amount\" : 10,\"end_date\" : \"2016-10-23T04:27:40+0000\",\"type\" : \"REM\",\"pixel_target_expr\" : \"\", \"impression_pacing_type\" : \"no-limit\",\"bid_price_is_media_only\" : false,"
						+ "\"frequency_optimization\" : false,\"supply_type\" : \"RTB\",\"strategy_domain_restrictions\" : [{\"strategy_id\" : 1377524,\"rel\" : \"strategy_domain_restrictions\",\"version\" : 0,\"name\" : \"Strategy Domain Restrictions #19556457\",\"updated_on\" : \"2016-09-22T10:24:40+0000\",\"domain\" : \"google.com\",\"created_at\" : \"2016-09-22T10:24:40+0000\",\"entity_type\" : \"strategy_domain_restriction\","
						+ "\"target_type\" : \"DOMAIN\",\"id\" : 19556457,\"restriction\" : \"INCLUDE\"},{\"strategy_id\" : 1377524,\"rel\" : \"strategy_domain_restrictions\",\"version\" : 0,\"name\" : \"Strategy Domain Restrictions #19556458\",\"updated_on\" : \"2016-09-22T10:24:40+0000\",\"domain\" : \"yahoo.com\",\"created_at\" : \"2016-09-22T10:24:40+0000\",\"entity_type\" : \"strategy_domain_restriction\",\"target_type\" : \"DOMAIN\","
						+ "\"id\" : 19556458,\"restriction\" : \"EXCLUDE\"}],\"pacing_type\" : \"even\",\"goal_type\" : \"spend\",\"frequency_type\" : \"asap\",\"status\" : false,\"budget\" : [{\"currency_code\" : \"USD\",\"value\" : 100.12}],\"effective_goal_value\" : [{\"currency_code\" : \"USD\",\"value\" : \"12.1200\"}],\"goal_value\" : [{\"currency_code\" : \"USD\",\"value\" : 12.12}],\"pacing_amount\" : [{\"currency_code\" : \"USD\",\"value\" : 10}],"
						+ "\"site_restriction_transparent_urls\" : false,\"media_type\" : \"DISPLAY\",\"entity_type\" : \"strategy\",\"start_date\" : \"2016-09-22T04:27:40+0000\",\"run_on_mobile\" : false,\"use_optimization\" : false,\"max_bid\" : [{\"currency_code\" : \"USD\",\"value\" : 10}],\"audience_segment_include_op\" : \"OR\",\"bid_aggressiveness\" : 50,\"version\" : 4,\"run_on_all_exchanges\" : false,"
						+ "\"audience_segment_exclude_op\" : \"OR\",\"site_selectiveness\" : \"REDUCED\",\"use_campaign_end\" : false,\"pacing_interval\" : \"day\"},\"meta\" : {\"etag\" : \"16db9fe4c3f1882061cd34551b2b473e71e75433\",\"called_on\" : \"2016-09-23T06:50:03+0000\",\"status\" : \"ok\"}}");

		QueryCriteria query = QueryCriteria.builder().setCollection("strategies").setEntity(1377524)
				.setChild("domain_restrictions").setPageLimit(1).build();

		JsonResponse<?> jsonresponse = null;
		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			jsonresponse = t1.get(query);
			Mockito.verify(connectionmock).get(Mockito.anyString(), Mockito.any(T1User.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));

		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		Strategy strategy = (Strategy) jsonresponse.getData();
		assertNotNull(strategy);
		assertEquals(1377524, strategy.getId());
		assertNotNull(strategy.getStrategyDomainRestrictions());
	}

	public void testGetWithStrategyConceptUsingMocks() throws ClientException, ParseException {
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1User.class))).thenReturn(
				"{\"data\": [{\"advertiser_id\": 147142,\"created_on\": \"2016-05-12T18:22:16+0000\",\"status\": true,\"version\": 0,\"entity_type\": \"concept\",\"name\": \"this is me\",\"id\": 786680,\"updated_on\": \"2016-05-12T18:22:16+0000\"},"
						+ "\"meta\": {\"etag\": \"76a511898da9c214283e71f3ef78f172e6cbed78\",\"count\": 1,\"called_on\": \"2016-09-23T10:10:16+0000\",\"status\": \"ok\",\"offset\": 0,\"total_count\": 1}}");

		QueryCriteria query = QueryCriteria.builder().setCollection("strategies").setEntity(1376198)
				.setChild("concepts").setPageLimit(1).build();

		JsonResponse<?> jsonresponse = null;
		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			jsonresponse = t1.get(query);
			Mockito.verify(connectionmock).get(Mockito.anyString(), Mockito.any(T1User.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));

		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		ArrayList<Concept> concept = (ArrayList<Concept>) jsonresponse.getData();
		assertNotNull(concept);
		assertNotNull(concept.get(0));
	}

	@SuppressWarnings("unchecked")
	public void testGetWithStrategyTotalSpendUsingMocks() throws ClientException, ParseException {
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1User.class))).thenReturn(
				"{\"data\": {\"entity_type\": \"strategy\",\"name\": \"Sajeela Test Strategy 1 - fod\",\"aggregate\": {\"function\": \"sum\",\"value\": 0,\"name\": \"total_spend\"},\"id\": 1376198},"
						+ "\"meta\": {\"status\": \"ok\"}");

		QueryCriteria query = QueryCriteria.builder().setCollection("strategies").setEntity(1376198)
				.setChild("concepts").setPageLimit(1).build();

		JsonResponse<?> jsonresponse = null;
		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			jsonresponse = t1.get(query);
			Mockito.verify(connectionmock).get(Mockito.anyString(), Mockito.any(T1User.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));

		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		@SuppressWarnings("unchecked")
		ArrayList<Concept> concept = (ArrayList<Concept>) jsonresponse.getData();
		assertNotNull(concept);
		assertNotNull(concept.get(0));
	}

	@SuppressWarnings("unchecked")
	public void testGetWithStrategyChildBrowserUsingMocks() throws ClientException, ParseException {
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1User.class)))
				.thenReturn("{\"data\": {\"enabled\": {\"active\": true}},\"meta\": {\"status\": \"ok\"}}");

		QueryCriteria query = QueryCriteria.builder().setCollection("strategies").setEntity(1376198).setChild("browser")
				.setPageLimit(1).build();

		JsonResponse<?> jsonresponse = null;
		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			jsonresponse = t1.get(query);
			Mockito.verify(connectionmock).get(Mockito.anyString(), Mockito.any(T1User.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class),
					Mockito.any(T1User.class));

		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		Data data = (Data) jsonresponse.getData();
		assertNotNull(data);
		assertNotNull(data.enabled.getActive());
	}

}
