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

import com.mediamath.terminalone.QueryCriteria.CreativeType;
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

		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class)))
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
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
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

		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class)))
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
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
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
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class)))
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
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
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
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class)))
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
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
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
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class)))
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
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);

	}

	@Test
	public void testBaiscGetWithLimitWithMock() throws ClientException, ParseException {
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class)))
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
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));

		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);

	}

	@Test
	public void testBaiscGetWithLimitnGetAllWithMock() throws ClientException, ParseException {
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1User.class))).thenReturn(
				"{\"data\":[{\"entity_type\":\"advertiser\",\"name\":\"josealexandre\",\"id\":195651},{\"entity_type\":\"advertiser\",\"name\":\"Varsha_Test_Advertiser\",\"id\":194823},"
						+"{\"entity_type\":\"advertiser\",\"name\":\"Reaction1.1AcceptanceTest-DEACTIVATED\",\"id\":194805},{\"entity_type\":\"advertiser\",\"name\":\"test_Amelie\",\"id\":194784},"
						+"{\"entity_type\":\"advertiser\",\"name\":\"Benji'sTestingAdvertiser\",\"id\":193175},{\"entity_type\":\"advertiser\",\"name\":\"TestAdvertiser\",\"id\":192314},"
						+"{\"entity_type\":\"advertiser\",\"name\":\"Matt\",\"id\":191640},{\"entity_type\":\"advertiser\",\"name\":\"Piper'sTestQAAgency\",\"id\":191420},"
						+"{\"entity_type\":\"advertiser\",\"name\":\"Test_Hector_V3\",\"id\":191219},{\"entity_type\":\"advertiser\",\"name\":\"Test_hector_V2\",\"id\":191216},"
						+"{\"entity_type\":\"advertiser\",\"name\":\"SeanTest-ForNewAdvertiserPixelIssues-UPDATED4\",\"id\":191183},"
						+"{\"entity_type\":\"advertiser\",\"name\":\"SeanTest-ForNewAdvertiserPixelIssues-UPDATED3\",\"id\":191181},"
						+"{\"entity_type\":\"advertiser\",\"name\":\"SeanTest-ForNewAdvertiserPixelIssues-UPDATED2\",\"id\":191180},"
						+"{\"entity_type\":\"advertiser\",\"name\":\"SeanTest-ForNewAdvertiserPixelIssues-UPDATED\",\"id\":191179},"
						+"{\"entity_type\":\"advertiser\",\"name\":\"SeanTest-ForNewAdvertiserPixelIssues-UPDATED\",\"id\":191178},"
						+"{\"entity_type\":\"advertiser\",\"name\":\"SeanTest-ForNewAdvertiserPixelIssues-UPDATED\",\"id\":191177},"
						+"{\"entity_type\":\"advertiser\",\"name\":\"SeanTest-ForNewAdvertiserPixelIssues-UPDATED\",\"id\":191176},"
						+"{\"entity_type\":\"advertiser\",\"name\":\"SeanTest-ForNewAdvertiserPixelIssues-UPDATED\",\"id\":191175},"
						+"{\"entity_type\":\"advertiser\",\"name\":\"SeanTest-ForNewAdvertiserPixelIssues-UPDATED\",\"id\":191174},"
						+"{\"entity_type\":\"advertiser\",\"name\":\"SeanTestAdvertiser-Plsdonottouch\",\"id\":191034}],"
						+"\"meta\":{\"next_page\":\"https://api.mediamath.com/api/v2.0/advertisers?api_key=65nvvagvbay89b5jnj6zazsj&get_all=true&page_limit=20&page_offset=20\","
						+"\"etag\":\"bde4c59fdf5f8c05a14b3dcf0275c3cb1538ac27\",\"count\":20,\"called_on\":\"2017-08-29T07:13:12+0000\",\"status\":\"ok\",\"offset\":0,\"total_count\":616}}");

		QueryCriteria query = QueryCriteria.builder().setCollection("advertisers").build();

		JsonResponse<?> jsonresponse = null;

		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			jsonresponse = t1.get(query);
			Mockito.verify(connectionmock).get(Mockito.anyString(), Mockito.any(T1User.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));

		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);

	}

	@Test
	public void testBaiscGetErrorHandling() throws ClientException {

		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1User.class)))
				.thenReturn("{\"errors\":[{\"message\": \"Object 'advertiser' has no relationship 'campaign'\"}],"
						+ "\"meta\": {\"called_on\": \"2017-02-17T09:01:07+0000\",\"status\": \"invalid\"}}");

		Map<String, Long> limitList = new HashMap<String, Long>();
		limitList.put("campaign", Long.valueOf(111555));
		QueryCriteria query = QueryCriteria.builder().setCollection("advertisers").setLimit(limitList).setPageLimit(100)
				.build();

		JsonResponse<?> jsonresponse = null;

		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			jsonresponse = t1.get(query);
			Mockito.verify(connectionmock).get(Mockito.anyString(), Mockito.any(T1User.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));

		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNull(jsonresponse);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testBaiscGetWithQueryWithMock() throws ClientException, ParseException {

		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class)))
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
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));

		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);

	}

	@Test
	public void testBaiscGetWithFindWithMock() throws ClientException, ParseException {
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class)))
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
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));

		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			throw new AssertionError();
		}

		assertNotNull(jsonresponse);
	}

	@Test
	public void testBaiscGetWithFind1WithMock() throws ClientException, ParseException {
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class)))
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
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));

		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);

	}

	@Test
	public void testBaiscGetWithFind2WithMock() throws ClientException, ParseException {
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class)))
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
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));

		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);

	}

	@Test
	public void testGetWithFullBooleanWithMocks() throws ClientException, ParseException {
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class)))
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
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));

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
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class)))
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
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
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
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class)))
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
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));

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
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class)))
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
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));

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
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class)))
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
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));

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
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class)))
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
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));

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
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class)))
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
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));

		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		Data data = (Data) jsonresponse.getData();
		assertNotNull(data);
		assertNotNull(data.enabled.getActive());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testGetWithStrategyChildDealsUsingMocks() throws ClientException, ParseException {
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1User.class))).thenReturn(
				"{\"data\":{\"run_on_streaming\":false,\"use_mm_freq\":false,\"run_on_display\":true,\"zone_name\":\"Europe/London\",\"updated_on\":\"2017-03-23T04:09:09+0000\","
						+ "\"frequency_interval\":\"not-applicable\",\"campaign_id\":349847,\"targeting_segment_exclude_op\":\"OR\",\"created_on\":\"2017-03-23T03:01:55+0000\","
						+ "\"targeting_segment_include_op\":\"OR\",\"run_on_all_pmp\":false,\"id\":2145568,\"impression_pacing_interval\":\"day\",\"currency_code\":\"KRW\",\"use_campaign_start\":true,"
						+ "\"name\":\"GBP-Test\",\"frequency_amount\":0,\"type\":\"AUD\",\"pixel_target_expr\":\"\",\"impression_pacing_type\":\"no-limit\",\"bid_price_is_media_only\":false,"
						+ "\"frequency_optimization\":true,\"supply_type\":\"RTB\",\"pacing_type\":\"asap\",\"goal_type\":\"cpc\",\"frequency_type\":\"no-limit\",\"status\":true,"
						+ "\"budget\":[{\"currency_code\":\"KRW\",\"value\":10}],\"effective_goal_value\":[{\"currency_code\":\"KRW\",\"value\":\"1.0000\"}],\"goal_value\":[{\"currency_code\":\"KRW\",\"value\":1}],"
						+ "\"pacing_amount\":[{\"currency_code\":\"KRW\",\"value\":1}],\"site_restriction_transparent_urls\":false,\"media_type\":\"DISPLAY\",\"entity_type\":\"strategy\","
						+ "\"run_on_mobile\":true,\"deals\":[{\"entity_type\":\"deal\",\"id\":173096}],\"use_optimization\":true,\"max_bid\":[{\"currency_code\":\"KRW\",\"value\":16.03}],"
						+ "\"audience_segment_include_op\":\"OR\",\"run_on_all_exchanges\":false,\"bid_aggressiveness\":50,\"version\":15,\"audience_segment_exclude_op\":\"OR\","
						+ "\"site_selectiveness\":\"REDUCED\",\"use_campaign_end\":true,\"min_bid\":[{\"currency_code\":\"KRW\",\"value\":0}],\"pacing_interval\":\"day\"},"
						+ "\"meta\":{\"etag\":\"51b3b8ca8ddca9397c206bc3b93dcecd406a8d01\",\"called_on\":\"2017-03-23T10:57:39+0000\",\"status\":\"ok\"}}");

		QueryCriteria query = QueryCriteria.builder().setCollection("strategies").setEntity(2145568).setChild("deals")
				.setPageLimit(1).build();

		JsonResponse<?> jsonresponse = null;
		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			jsonresponse = t1.get(query);
			Mockito.verify(connectionmock).get(Mockito.anyString(), Mockito.any(T1User.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));

		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		Strategy data = (Strategy) jsonresponse.getData();
		assertNotNull(data);

	}

	@Test
	@SuppressWarnings("unchecked")
	public void testGetWithDealsUsingMocks() throws ClientException, ParseException {
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1User.class))).thenReturn(
				"{\"meta\":{\"status\":\"ok\"},\"data\":{\"updated_on\":\"2017-03-23T13:45:56+00:00\",\"deal_identifier\":\"TestDeal5\",\"created_on\":\"2017-03-23T10:07:02+00:00\","
						+ "\"price\":{\"currency_code\":\"USD\",\"value\":\"3.5000\"},\"price_type\":\"FIXED\",\"entity_type\":\"deal\",\"name\":\"Test27\","
						+ "\"start_datetime\":\"2017-03-23T10:07:02+00:00\",\"end_datetime\":\"2999-12-31T05:00:00+00:00\",\"price_method\":\"CPM\",\"permissions\":{\"advertiser_ids\":[182016],"
						+ "\"agency_ids\":[],\"all_organizations\":false,\"organization_ids\":[]},\"owner\":{\"id\":182016,\"type\":\"ADVERTISER\"},\"supply_source_id\":17,\"status\":true,"
						+ "\"id\":173131,\"description\":\"\",\"sub_supply_source_id\":null}}");

		QueryCriteria query = QueryCriteria.builder().setCollection("deals").setEntity(173131).setSortBy("-id").build();

		JsonResponse<?> jsonresponse = null;
		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			jsonresponse = t1.get(query);
			Mockito.verify(connectionmock).get(Mockito.anyString(), Mockito.any(T1User.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));

		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testGetWithDealsUsingAdvertiserIdMocks() throws ClientException, ParseException {
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1User.class))).thenReturn(
				"{\"meta\":{\"total_count\":5,\"offset\":0,\"status\":\"ok\",\"count\":5},\"data\":[{\"entity_type\":\"deal\",\"start_datetime\":\"2017-03-28T10:39:30+00:00\","
						+ "\"supply_source_id\":4,\"price_type\":\"FLOOR\",\"owner\":{\"id\":145998,\"type\":\"ADVERTISER\"},\"price\":{\"value\":\"10.0000\",\"currency_code\":\"AUD\"},"
						+ "\"sub_supply_source_id\":null,\"name\":\"CurrencyTest\",\"permissions\":{\"all_organizations\":false,\"organization_ids\":[],\"agency_ids\":[],"
						+ "\"advertiser_ids\":[145998]},\"id\":174186,\"deal_identifier\":\"MM-DA2-64K2\",\"status\":true,\"created_on\":\"2017-03-28T09:39:56+00:00\","
						+ "\"price_method\":\"CPM\",\"end_datetime\":\"2999-12-31T00:00:00+00:00\",\"updated_on\":\"2017-03-28T09:39:56+00:00\"},{\"entity_type\":\"deal\","
						+ "\"start_datetime\":\"2017-03-23T05:08:02+00:00\",\"supply_source_id\":3,\"price_type\":\"FLOOR\",\"owner\":{\"id\":145998,\"type\":\"ADVERTISER\"},"
						+ "\"price\":{\"value\":\"393.2537\",\"currency_code\":\"AUD\"},\"sub_supply_source_id\":null,\"name\":\"TestWon\",\"permissions\":{\"all_organizations\":false,"
						+ "\"organization_ids\":[],\"agency_ids\":[],\"advertiser_ids\":[145998]},\"id\":173101,\"deal_identifier\":\"MM-ER2-628E\",\"description\":\"\",\"status\":false,"
						+ "\"created_on\":\"2017-03-23T04:08:49+00:00\",\"price_method\":\"CPM\",\"end_datetime\":\"2999-12-31T00:00:00+00:00\",\"updated_on\":\"2017-03-28T09:39:27+00:00\"},"
						+ "{\"entity_type\":\"deal\",\"start_datetime\":\"2017-03-23T06:52:28+00:00\",\"supply_source_id\":8,\"price_type\":\"FLOOR\",\"owner\":{\"id\":145998,"
						+ "\"type\":\"ADVERTISER\"},\"price\":{\"value\":\"131.0846\",\"currency_code\":\"AUD\"},\"sub_supply_source_id\":null,\"name\":\"TestPub\","
						+ "\"permissions\":{\"all_organizations\":false,\"organization_ids\":[],\"agency_ids\":[],\"advertiser_ids\":[145998]},\"id\":173102,\"deal_identifier\":\"MM-NUY-64ST\","
						+ "\"description\":\"\",\"status\":false,\"created_on\":\"2017-03-23T05:52:47+00:00\",\"price_method\":\"CPM\",\"end_datetime\":\"2999-12-31T00:00:00+00:00\","
						+ "\"updated_on\":\"2017-03-28T09:39:22+00:00\"},{\"entity_type\":\"deal\",\"start_datetime\":\"2017-03-23T04:02:58+00:00\",\"supply_source_id\":8,"
						+ "\"price_type\":\"FLOOR\",\"owner\":{\"id\":145998,\"type\":\"ADVERTISER\"},\"price\":{\"value\":\"100.0000\",\"currency_code\":\"AUD\"},\"sub_supply_source_id\":null,"
						+ "\"name\":\"GBP-Test-Deal\",\"permissions\":{\"all_organizations\":false,\"organization_ids\":[],\"agency_ids\":[],\"advertiser_ids\":[145998]},\"id\":173096,"
						+ "\"deal_identifier\":\"MM-EST-24QN\",\"description\":\"\",\"status\":false,\"created_on\":\"2017-03-23T03:03:31+00:00\",\"price_method\":\"CPM\","
						+ "\"end_datetime\":\"2999-12-31T00:00:00+00:00\",\"updated_on\":\"2017-03-28T09:39:15+00:00\"},{\"start_datetime\":\"2016-08-04T08:01:00+00:00\","
						+ "\"supply_source_id\":8,\"price_type\":\"FLOOR\",\"owner\":{\"id\":145998,\"type\":\"ADVERTISER\"},\"price\":{\"value\":\"1.0000\",\"currency_code\":\"USD\"},"
						+ "\"sub_supply_source_id\":null,\"name\":\"Wil-Test-Deal\",\"permissions\":{\"all_organizations\":false,\"organization_ids\":[],\"agency_ids\":[],"
						+ "\"advertiser_ids\":[145998]},\"id\":121495,\"deal_identifier\":\"MM-MT3-4AKR\",\"status\":false,\"entity_type\":\"deal\",\"created_on\":\"2016-08-04T07:01:42+00:00\","
						+ "\"price_method\":\"CPM\",\"end_datetime\":\"2999-12-31T00:00:00+00:00\",\"updated_on\":\"2016-08-04T07:11:14+00:00\"}]}");

		Map<String, Long> limitList = new HashMap<String, Long>();
		limitList.put("permissions.advertiser_id", Long.valueOf(145998));

		QueryCriteria query = QueryCriteria.builder().setCollection("deals").setLimit(limitList).setSortBy("-id")
				.build();

		JsonResponse<?> jsonresponse = null;
		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			jsonresponse = t1.get(query);
			Mockito.verify(connectionmock).get(Mockito.anyString(), Mockito.any(T1User.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));

		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testBaiscGetWithParentWithMock() throws ClientException, ParseException {
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1User.class))).thenReturn(
				"{\"data\":[{\"buyable\":false,\"version\":0,\"name\":\"Mosaic\",\"child_count\":19,\"audience_vendor_id\":13,\"updated_on\":\"2017-03-23T18:58:02+0000\",\"parent_audience_segment_id\":27962,"
						+ "\"created_on\":\"2013-03-22T02:03:49+0000\",\"entity_type\":\"audience_segment\",\"full_path\":\"Experian-Mosaic\",\"type\":\"normal\",\"id\":28038,\"code\":\"161613\"},{\"buyable\":false,\"version\":0,"
						+ "\"name\":\"TrueTouch\",\"child_count\":3,\"audience_vendor_id\":13,\"updated_on\":\"2017-03-23T18:58:02+0000\",\"parent_audience_segment_id\":27962,\"created_on\":\"2013-03-22T02:03:49+0000\",\"entity_type\":\"audience_segment\","
						+ "\"full_path\":\"Experian-TrueTouch\",\"type\":\"normal\",\"id\":28127,\"code\":\"161523\"},{\"buyable\":true,\"wholesale_cpm\":[{\"currency_code\":\"USD\",\"value\":1.1481}],\"version\":0,\"name\":\"Financial-FMCGDirect\",\"child_count\":11,"
						+ "\"audience_vendor_id\":13,\"updated_on\":\"2017-03-23T18:58:02+0000\",\"retail_cpm\":[{\"currency_code\":\"USD\",\"value\":1.25}],\"created_on\":\"2014-10-10T20:16:30+0000\",\"parent_audience_segment_id\":27962,\"entity_type\":\"audience_segment\","
						+ "\"full_path\":\"Experian-Financial-FMCGDirect\",\"type\":\"normal\",\"id\":133752,\"code\":\"360861\"},{\"buyable\":false,\"version\":0,\"name\":\"Auto\",\"child_count\":1,\"audience_vendor_id\":13,\"updated_on\":\"2017-03-23T18:58:02+0000\","
						+ "\"parent_audience_segment_id\":27962,\"created_on\":\"2014-10-10T20:16:30+0000\",\"entity_type\":\"audience_segment\",\"full_path\":\"Experian-Auto\",\"type\":\"normal\",\"id\":133756,\"code\":\"330135\"},"
						+ "{\"buyable\":true,\"wholesale_cpm\":[{\"currency_code\":\"USD\",\"value\":1.3777}],\"version\":0,\"name\":\"LifeEvent\",\"child_count\":3,\"audience_vendor_id\":13,\"updated_on\":\"2017-03-23T18:58:02+0000\","
						+ "\"retail_cpm\":[{\"currency_code\":\"USD\",\"value\":1.5}],\"created_on\":\"2014-10-10T20:16:30+0000\",\"parent_audience_segment_id\":27962,\"entity_type\":\"audience_segment\",\"full_path\":\"Experian-LifeEvent\","
						+ "\"type\":\"normal\",\"id\":133837,\"code\":\"363560\"},{\"buyable\":false,\"version\":0,\"name\":\"ExperianUK\",\"child_count\":3,\"audience_vendor_id\":13,\"updated_on\":\"2017-03-23T18:58:02+0000\",\"created_on\":\"2014-10-10T20:16:30+0000\","
						+ "\"parent_audience_segment_id\":27962,\"entity_type\":\"audience_segment\",\"full_path\":\"Experian-ExperianUK\",\"type\":\"normal\",\"id\":133848,\"uniques\":343000,\"code\":\"321887\"},"
						+ "{\"buyable\":true,\"wholesale_cpm\":[{\"currency_code\":\"USD\",\"value\":1.3777}],\"version\":0,\"name\":\"PastPurchase\",\"child_count\":51,\"audience_vendor_id\":13,\"updated_on\":\"2017-03-23T18:58:02+0000\","
						+ "\"retail_cpm\":[{\"currency_code\":\"USD\",\"value\":1.5}],\"created_on\":\"2015-07-20T22:10:40+0000\",\"parent_audience_segment_id\":27962,\"entity_type\":\"audience_segment\",\"full_path\":\"Experian-PastPurchase\","
						+ "\"type\":\"normal\",\"id\":1425925,\"code\":\"401646\"},{\"buyable\":true,\"wholesale_cpm\":[{\"currency_code\":\"USD\",\"value\":1.1481}],\"version\":0,\"name\":\"Psychographic/Attitudes\",\"child_count\":10,\"audience_vendor_id\":13,"
						+ "\"updated_on\":\"2017-03-23T18:58:02+0000\",\"retail_cpm\":[{\"currency_code\":\"USD\",\"value\":1.25}],\"created_on\":\"2015-07-20T22:10:40+0000\",\"parent_audience_segment_id\":27962,\"entity_type\":\"audience_segment\","
						+ "\"full_path\":\"Experian-Psychographic/Attitudes\",\"type\":\"normal\",\"id\":1425926,\"code\":\"401686\"},{\"buyable\":true,\"wholesale_cpm\":[{\"currency_code\":\"USD\",\"value\":1.1481}],\"version\":0,\"name\":\"LifestyleandInterests\","
						+ "\"child_count\":21,\"audience_vendor_id\":13,\"updated_on\":\"2017-03-23T18:58:02+0000\",\"retail_cpm\":[{\"currency_code\":\"USD\",\"value\":1.25}],\"created_on\":\"2015-07-20T22:10:40+0000\",\"parent_audience_segment_id\":27962,"
						+ "\"entity_type\":\"audience_segment\",\"full_path\":\"Experian-LifestyleandInterests\",\"type\":\"normal\",\"id\":1426135,\"code\":\"362521\"},{\"buyable\":true,\"wholesale_cpm\":[{\"currency_code\":\"USD\",\"value\":1.1481}],\"version\":0,"
						+ "\"name\":\"HousingAttributes\",\"child_count\":3,\"audience_vendor_id\":13,\"updated_on\":\"2017-03-23T18:58:02+0000\",\"retail_cpm\":[{\"currency_code\":\"USD\",\"value\":1.25}],\"created_on\":\"2015-07-20T22:10:40+0000\","
						+ "\"parent_audience_segment_id\":27962,\"entity_type\":\"audience_segment\",\"full_path\":\"Experian-HousingAttributes\",\"type\":\"normal\",\"id\":1426141,\"code\":\"401481\"},"
						+ "{\"buyable\":true,\"wholesale_cpm\":[{\"currency_code\":\"USD\",\"value\":1.1481}],\"version\":0,\"name\":\"PurchasePredictors\",\"child_count\":4,\"audience_vendor_id\":13,\"updated_on\":\"2017-03-23T18:58:02+0000\","
						+ "\"retail_cpm\":[{\"currency_code\":\"USD\",\"value\":1.25}],\"created_on\":\"2015-07-20T22:10:40+0000\",\"parent_audience_segment_id\":27962,\"entity_type\":\"audience_segment\",\"full_path\":\"Experian-PurchasePredictors\","
						+ "\"type\":\"normal\",\"id\":1426150,\"code\":\"401688\"},{\"buyable\":false,\"version\":0,\"name\":\"Demographics\",\"child_count\":11,\"audience_vendor_id\":13,\"updated_on\":\"2017-03-23T18:58:02+0000\",\"parent_audience_segment_id\":27962,"
						+ "\"created_on\":\"2015-07-20T22:10:40+0000\",\"entity_type\":\"audience_segment\",\"full_path\":\"Experian-Demographics\",\"type\":\"normal\",\"id\":1426217,\"code\":\"401455\"},"
						+ "{\"buyable\":false,\"version\":0,\"name\":\"Private\",\"child_count\":1,\"audience_vendor_id\":13,\"updated_on\":\"2017-03-23T18:58:02+0000\",\"parent_audience_segment_id\":27962,\"created_on\":\"2015-07-20T22:10:40+0000\","
						+ "\"entity_type\":\"audience_segment\",\"full_path\":\"Experian-Private\",\"type\":\"normal\",\"id\":1426222}],\"meta\":{\"etag\":\"02770a47052ce815488a5fb424d300db26b62507\",\"count\":13,\"called_on\":\"2017-04-26T10:30:58+0000\",\"status\":\"ok\",\"offset\":0,\"total_count\":13}}");

		FullParamValues fpv = new FullParamValues();
		fpv.setBoolValue(true);
		QueryCriteria query = QueryCriteria.builder().setCollection("audience_segments").setSortBy("id")
				.setParent(27962).setFull(fpv).build();

		JsonResponse<?> jsonresponse = null;

		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			jsonresponse = t1.get(query);
			Mockito.verify(connectionmock).get(Mockito.anyString(), Mockito.any(T1User.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));

		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
		assertNotNull(jsonresponse.getData());
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testGetWithCampaignCBS() throws ClientException, ParseException {
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1User.class))).thenReturn(
				"{\"data\":[{\"entity_type\":\"campaign_custom_brain_selection\",\"name\":\"CampaignCustomBrainSelections#143213\",\"id\":143213}],"
		+"\"meta\":{\"etag\":\"528884ebf3a2df890f76e184e732f748d8dbc27b\",\"count\":1,\"called_on\":\"2017-07-24T11:28:10+0000\",\"status\":\"ok\",\"offset\":0,\"total_count\":1}}");

		QueryCriteria query = QueryCriteria.builder().setCollection("campaigns").setEntity(338158).setChild("custom_brain_selections").build();

		JsonResponse<?> jsonresponse = null;
		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			jsonresponse = t1.get(query);
			Mockito.verify(connectionmock).get(Mockito.anyString(), Mockito.any(T1User.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));

		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testGetWithCampaignCBSWithId() throws ClientException, ParseException {
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1User.class))).thenReturn(
				"{\"data\":{\"version\":0,\"active\":true,\"selection_id\":1084588,\"name\":\"CampaignCustomBrainSelections#143213\",\"campaign_id\":338158,"
			+"\"selection_name\":\"karthik-home-page\",\"selection_type\":\"EventPixel\",\"entity_type\":\"campaign_custom_brain_selection\",\"uniques\":0,"
			+"\"id\":143213,\"owner_name\":\"Karthik-Adaptive-Seg-Adv\"},\"meta\":{\"etag\":\"b189d8640ad718aeb7dfacfebd961c9c93b723c5\",\"called_on\":\"2017-07-24T11:30:57+0000\",\"status\":\"ok\"}}");

		QueryCriteria query = QueryCriteria.builder().setCollection("campaigns").setEntity(338158).setChild("custom_brain_selections").setChild("143213").build();

		JsonResponse<?> jsonresponse = null;
		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			jsonresponse = t1.get(query);
			Mockito.verify(connectionmock).get(Mockito.anyString(), Mockito.any(T1User.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));

		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
	}
		
	@Test
	@SuppressWarnings("unchecked")
	public void testGetWithVideoCreativeToOrg() throws ClientException, ParseException {
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class)))
				.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1User.class))).thenReturn(
				"{\"details\":{\"active\":true,\"advertiser\":144197,\"name\":\"TestCreativeJC2\",\"concept\":1350397,\"clickthroughUrl\":\"http://www.example.com\","
				+"\"landingUrl\":\"http://www.example.com\",\"eventPixels\":[],\"vendors\":[1133,1134],\"disabledVariants\":[]},\"companionIds\":[],\"duration\":6,"
				+"\"isUploaded\":true,\"unsecureUrls\":{},\"status\":{\"code\":\"ok\"},\"readyToServe\":true,"
				+"\"thumbnail\":\"https://s3.amazonaws.com/mm-video-assets-us-east-1/1d3fc62d-39d3-4332-a44c-6d2fe534b8ac-00002.png\",\"autoVendors\":[522],"
				+"\"percent\":100,\"isSecure\":true,\"isRotating\":false,\"isDynamic\":false,\"isAudio\":false,\"vastVersion\":2}");

		QueryCriteria query = QueryCriteria.builder().setCollection("creatives").setEntity(4534579).setCreativeType(CreativeType.video).build();

		JsonResponse<?> jsonresponse = null;
		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			jsonresponse = t1.get(query);
			Mockito.verify(connectionmock).get(Mockito.anyString(), Mockito.any(T1User.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));

		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testGetWithAllUsers() throws ClientException, ParseException {
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class)))
		.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1User.class))).thenReturn(
				"{\"data\":[{\"entity_type\":\"user\",\"name\":\"mjptestuser@gmail.com\",\"id\":21904},{\"entity_type\":\"user\",\"name\":\"rcm@reporter.com\",\"id\":21790},"
						+"{\"entity_type\":\"user\",\"name\":\"soledadborneo@yahoo.comar\",\"id\":21613},{\"entity_type\":\"user\",\"name\":\"sdavies+2@mediamath.com\",\"id\":21542},"
						+"{\"entity_type\":\"user\",\"name\":\"restricted_user@mediamath.com\",\"id\":21505},{\"entity_type\":\"user\",\"name\":\"tpiron-test@bar.com\",\"id\":21306},"
						+"{\"entity_type\":\"user\",\"name\":\"clpkeenan+agencyadmin@gmail.com\",\"id\":21204},{\"entity_type\":\"user\",\"name\":\"ss@ss.com\",\"id\":21155},"
						+"{\"entity_type\":\"user\",\"name\":\"sptest12@mm.com\",\"id\":21154},{\"entity_type\":\"user\",\"name\":\"social_user@mediamath.com\",\"id\":21038},"
						+"{\"entity_type\":\"user\",\"name\":\"sonic@3.456\",\"id\":20787},{\"entity_type\":\"user\",\"name\":\"clpkeenan+roletest2@gmail.com\",\"id\":20747},"
						+"{\"entity_type\":\"user\",\"name\":\"clpkeenan+roletest@gmail.com\",\"id\":20746},{\"entity_type\":\"user\",\"name\":\"test1@affiperf.com\",\"id\":20708},"
						+"{\"entity_type\":\"user\",\"name\":\"fsargent+test@mediamath.com\",\"id\":20628},{\"entity_type\":\"user\",\"name\":\"estefania.alba@headwaydigital.com\",\"id\":20534},"
						+"{\"entity_type\":\"user\",\"name\":\"soledad.borneo@headwaydigital.com\",\"id\":20513},"
						+"{\"entity_type\":\"user\",\"name\":\"auto_test_agency_reporter@mediamath.com\",\"id\":20481},"
						+"{\"entity_type\":\"user\",\"name\":\"auto_test_agency_manager@mediamath.com\",\"id\":20480},"
						+"{\"entity_type\":\"user\",\"name\":\"auto_test_agency_admin@mediamath.com\",\"id\":20479}],"
						+"\"meta\":{\"next_page\":\"https://api.mediamath.com/api/v2.0/users?api_key=65nvvagvbay89b5jnj6zazsj&page_limit=20&page_offset=20\","
						+"\"etag\":\"ac6375e4ca73e65460d3c3840475434d87d209d4\",\"count\":20,\"called_on\":\"2017-07-27T08:00:04+0000\",\"status\":\"ok\",\"offset\":0,\"total_count\":232}}");
		QueryCriteria query = QueryCriteria.builder().setCollection("users").build();

		JsonResponse<?> jsonresponse = null;
		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			jsonresponse = t1.get(query);
			Mockito.verify(connectionmock).get(Mockito.anyString(), Mockito.any(T1User.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testGetWithSingleUsers() throws ClientException, ParseException {
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class)))
		.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1User.class))).thenReturn(
				"{\"data\":{\"edit_data_definition\":true,\"view_data_definition\":true,\"edit_campaigns\":true,\"access_internal_fees\":false,\"labs_enable_rmx\":false,"
						+"\"updated_on\":\"2017-05-24T17:56:20+0000\",\"last_name\":\"qa\",\"email\":\"qatest3@mm.com\",\"view_segments\":true,\"created_on\":\"2017-04-20T06:04:46+0000\","
						+"\"view_organizations\":true,\"entity_type\":\"user\",\"view_dmp_reports\":true,\"id\":20301,\"creator_id\":9070,\"scope\":\"SELECT\","
						+"\"link_saml\":false,\"edit_segments\":true,\"version\":3,\"name\":\"qatest3@mm.com\",\"active\":true,\"phone\":\"123456789\",\"username\":\"qatest3@mm.com\","
						+"\"edit_margins_and_performance\":true,\"link_ldap\":false,\"last_login_on\":\"2017-04-20T06:08:56+0000\",\"title\":\"qa\",\"type\":\"AGENCY\",\"role\":\"ADMIN\","
						+"\"first_name\":\"qatest3\"},\"meta\":{\"etag\":\"09fd5feba7c0a2ed429adbefb4a6c46da342e837\",\"called_on\":\"2017-07-27T08:04:24+0000\",\"status\":\"ok\"}}");
		
		QueryCriteria query = QueryCriteria.builder().setCollection("users").setEntity(20301).build();

		JsonResponse<?> jsonresponse = null;
		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			jsonresponse = t1.get(query);
			Mockito.verify(connectionmock).get(Mockito.anyString(), Mockito.any(T1User.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testGetWithUserPermissions() throws ClientException, ParseException {
		Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class)))
		.thenReturn(response);
		Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN);
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1User.class))).thenReturn(
				"{\"data\":{\"permissions\":{\"flags\":[{\"access\":[{\"value\":1,\"type\":\"edit_data_definition\"},{\"value\":1,\"type\":\"view_data_definition\"},{\"value\":1,\"type\":\"edit_segments\"},"
						+"{\"value\":1,\"type\":\"edit_campaigns\"},{\"value\":0,\"type\":\"access_internal_fees\"},{\"value\":1,\"type\":\"edit_margins_and_performance\"},"
						+"{\"value\":1,\"type\":\"view_organizations\"},{\"value\":1,\"type\":\"view_segments\"},{\"value\":1,\"type\":\"view_dmp_reports\"},{\"value\":\"AGENCY\",\"type\":\"type\"},"
						+"{\"value\":\"ADMIN\",\"type\":\"role\"},{\"value\":\"SELECT\",\"type\":\"scope\"}]}],"
						+"\"entities\":{\"advertiser\":[{\"access\":[{\"value\":\"w\",\"dmp_enabled\":\"enabled\",\"agency_id\":101600,\"name\":\"KinectedT1BetaTest\",\"id\":\"111213\"},"
						+"{\"value\":\"w\",\"dmp_enabled\":\"enabled\",\"agency_id\":103570,\"name\":\"ADV_release_v2.83.0\",\"id\":\"125846\"},{\"value\":\"w\",\"dmp_enabled\":\"enabled\",\"agency_id\":101224,"
						+"\"name\":\"Geoffrey\",\"id\":\"107105\"},{\"value\":\"w\",\"dmp_enabled\":\"enabled\",\"agency_id\":103615,\"name\":\"GoFast\",\"id\":\"126261\"}]}],"
						+"\"agency\":[{\"access\":[{\"organization_id\":100048,\"value\":\"w\",\"dmp_enabled\":\"enabled\",\"name\":\"TESTJPPMP\",\"child_count\":1,\"id\":\"109915\"},"
						+"{\"organization_id\":100048,\"value\":\"w\",\"dmp_enabled\":\"enabled\",\"name\":\"JasonTest\",\"child_count\":0,\"id\":\"103181\"},{\"organization_id\":100048,\"value\":\"w\","
						+"\"dmp_enabled\":\"enabled\",\"name\":\"B54\",\"child_count\":1,\"id\":\"114232\"},{\"organization_id\":100048,\"value\":\"w\",\"dmp_enabled\":\"enabled\",\"name\":\"test_agency_py_2.126.1\","
						+"\"child_count\":1,\"id\":\"113421\"}]}],\"organization\":[{\"access\":[{\"value\":\"r\",\"dmp_enabled\":\"enabled\",\"name\":\"ACMEOrg\",\"child_count\":416,\"id\":\"100048\"}]}]"
						+"}},\"user\":{\"entity_type\":\"user\",\"name\":\"ss@ss.com\",\"id\":21155}},\"meta\":{\"status\":\"ok\"}}");

		QueryCriteria query = QueryCriteria.builder().setCollection("users").setEntity(20301).setChild("permissions").build();

		JsonResponse<?> jsonresponse = null;
		try {
			t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
			jsonresponse = t1.get(query);
			Mockito.verify(connectionmock).get(Mockito.anyString(), Mockito.any(T1User.class));
			Mockito.verify(connectionmock, times(1)).post(Mockito.anyString(), Mockito.any(Form.class));
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(jsonresponse);
	}

	

}
