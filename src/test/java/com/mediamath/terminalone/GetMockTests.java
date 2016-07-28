package com.mediamath.terminalone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.mediamath.terminalone.models.JsonResponse;
import com.mediamath.terminalone.models.T1Response;
import com.mediamath.terminalone.service.GetService;
import com.mediamath.terminalone.service.PostService;
import com.mediamath.terminalone.service.T1Service;
import com.mediamath.terminalone.utils.ConditionQuery;
import com.mediamath.terminalone.utils.Filters;
import com.mediamath.terminalone.utils.QueryParamValues;

@RunWith(MockitoJUnitRunner.class)
public class GetMockTests {

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
	
	@SuppressWarnings("unchecked")
	@Test
	public void testAdvertiserGettWithMocks() throws ClientException, ParseException {
		
		t1.setAuthenticated(true);
		Mockito.when(getservicemock.get(Mockito.any(QueryCriteria.class))) .thenReturn( new StringBuffer("advertisers?null"));
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1Response.class))).thenReturn("{\"data\" : [{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\",\"id\" : 165615}],"
														+ "\"meta\" : {\"next_page\" : \"https://t1sandbox-origin.mediamath.com/api/v2.0/advertisers"
														+ "?page_offset=40&api_key=e34f74vnubr9uxasz2n7bdfv&page_limit=40\",\"etag\" : \"e2fae343fdc5b6aeb4f782c9ea31860c64ec47c9\","
														+ "\"count\" : 1,\"called_on\" : \"2016-07-01T15:25:07+0000\",\"status\" : \"ok\",\"offset\" : 0,\"total_count\" : 437}}");
		QueryCriteria query = QueryCriteria.builder().setCollection("advertisers").setPageLimit(1).build();
		
		JsonResponse<?> jsonresponse = null;
		
		try {
			jsonresponse = t1.get(query);
			Mockito.verify(connectionmock).get(Mockito.anyString(), Mockito.any(T1Response.class));
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(jsonresponse);
		assertNotNull(jsonresponse.getData());
		ArrayList<Advertiser> advertisers= ((ArrayList<Advertiser>)jsonresponse.getData());
		assertEquals(165615, advertisers.get(0).getId());
		assertNotNull(jsonresponse.getMeta());
	}
	
	@Test
	public void testBaiscGetWithChildUsingQueryCriteriaWithMocks() throws ClientException, ParseException {
			
			t1.setAuthenticated(true);
			Mockito.when(getservicemock.get(Mockito.any(QueryCriteria.class))) .thenReturn( new StringBuffer("advertisers/154161?with=agency,organization&with=ad_server&with=vertical&page_limit=100"));
			Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1Response.class))).thenReturn("{\"data\" : {\"minimize_multi_ads\" : false,\"frequency_type\" : \"no-limit\",\"status\" : true,\"dmp_enabled\" : \"inherits\","
							+"\"agency_id\" : 111718,\"frequency_interval\" : \"not-applicable\",\"updated_on\" : \"2015-11-27T18:02:24+0000\","
							+"\"domain\" : \"http://www.mahou-sanmiguel.com\",\"created_on\" : \"2015-11-27T18:02:24+0000\",\"entity_type\" : \"advertiser\","
							+"\"agency\" : {\"organization_id\" : 100048,\"rel\" : \"agency\",\"status\" : true,\"version\" : 0,\"dmp_enabled\" : \"inherits\","
						         +"\"name\" : \"OLO 111718\",\"allow_x_adv_pixels\" : true,\"updated_on\" : \"2015-11-27T18:00:25+0000\",\"logo\" : \"http://t1.mediamath.com/app/\","
						         +"\"created_on\" : \"2015-11-27T18:00:25+0000\",\"entity_type\" : \"agency\",\"id\" : 111718,\"allow_x_adv_optimization\" : false,"
						         +"\"organization\" : {\"rel\" : \"organization\",\"status\" : true,\"dmp_enabled\" : \"enabled\",\"contact_name\" : \"Ian Hummel\","
					            +"\"org_type\" : [\"buyer\"],\"updated_on\" : \"2016-04-21T14:40:44+0000\",\"state\" : \"NY\",\"address_2\" : \"4WTC\","
					            +"\"address_1\" : \"1440 Broadway\",\"mm_contact_name\" : \"Ian Hummel\",\"city\" : \"New York\",\"allow_x_agency_pixels\" : true,"
					            +"\"restrict_targeting_to_deterministic_id\" : true,\"created_on\" : \"2010-10-19T16:58:05+0000\",\"entity_type\" : \"organization\","
					            +"\"id\" : 100048,\"country\" : \"US\",\"opt_out_connected_id_mathid\" : false,\"currency_code\" : \"USD\",\"override_suspicious_traffic_filter\" : false,"
					            +"\"version\" : 120,\"name\" : \"ACME Org\",\"restrict_targeting_to_same_device_id\" : true,\"phone\" : \"(347) 420-0116\",\"suspicious_traffic_filter_level\" : 25,"
					            +"\"allow_byo_price\" : true,\"opt_out_connected_id\" : true,\"zip\" : \"10018\",\"billing_country_code\" : \"US\",\"use_evidon_optout\" : true,\"adx_seat_account_id\" : 139299155}},"
						      +"\"id\" : 154161,\"version\" : 0,\"name\" : \"MAHOU - SMIGUEL\",\"ad_server_id\" : 6,\"allow_x_strat_optimization\" : false,\"vertical_id\" : 30,"
						      +"\"vertical\" : {\"rel\" : \"vertical\",\"created_on\" : \"2012-10-23T22:22:15+0000\",\"version\" : 1,\"entity_type\" : \"vertical\",\"name\" : \"Alcohol\","
					            +"\"id\" : 30,\"updated_on\" : \"2012-10-23T22:22:34+0000\"},"
					            +"\"ad_server\" : {\"rel\" : \"ad_server\",\"version\" : 0,\"entity_type\" : \"ad_server\",\"name\" : \"Other\",\"id\" : 6}},"
						    +"\"meta\" : {\"etag\" : \"72de229843688c896864e7a67358edf8e3122551\",\"called_on\" : \"2016-07-14T09:11:39+0000\",\"status\" : \"ok\"}}");
			QueryCriteria query = QueryCriteria.builder().setCollection("advertisers").setEntity(154161).setInclude(new ConditionQuery("agency", "organization")).build();
			query = QueryCriteria.builder(query).setInclude(new ConditionQuery("ad_server")).setInclude(new ConditionQuery("vertical")).build();
			
			JsonResponse<?> jsonresponse = null;
			
			try {
				jsonresponse = t1.get(query);
				Mockito.verify(connectionmock).get(Mockito.anyString(), Mockito.any(T1Response.class));
			} catch (ClientException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			assertNotNull(jsonresponse);
			assertNotNull(jsonresponse.getData());
			Advertiser advertisers= ((Advertiser)jsonresponse.getData());
			assertEquals(154161, advertisers.getId());
			assertNotNull(jsonresponse.getMeta());
			
		
		}

	
	@SuppressWarnings("unchecked")
	@Test
	public void testBaiscGetWithSortByUsingQueryCriteriaWithMock() throws ClientException, ParseException {
		t1.setAuthenticated(true);
		Mockito.when(getservicemock.get(Mockito.any(QueryCriteria.class))) .thenReturn( new StringBuffer("advertisers?sort_by=-id&page_limit=5"));
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1Response.class))).thenReturn("{\"data\" : [{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\",\"id\" : 165615},"
		+"{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\",\"id\" : 165612},"
		+"{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\",\"id\" : 165611},"
		+"{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\",\"id\" : 165608},"
		+"{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\",\"id\" : 165607}],"
		+"\"meta\" : {\"next_page\" : \"https://t1sandbox-origin.mediamath.com/api/v2.0/advertisers?page_offset=5&sort_by=-id&api_key=e34f74vnubr9uxasz2n7bdfv&page_limit=5\","
		+"\"etag\" : \"b81e3860fa348058a011846933765bb08a59af6b\",\"count\" : 5,\"called_on\" : \"2016-07-14T11:14:37+0000\",\"status\" : \"ok\",\"offset\" : 0,\"total_count\" : 437}}");

		QueryCriteria query = QueryCriteria.builder().setCollection("advertisers").setSortBy("-id").setPageLimit(5).build();
		JsonResponse<?> jsonresponse = null;
		
		try {
			jsonresponse = t1.get(query);
			Mockito.verify(connectionmock).get(Mockito.anyString(), Mockito.any(T1Response.class));
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(jsonresponse);
		assertNotNull(jsonresponse.getData());
		ArrayList<Advertiser> advertisers= ((ArrayList<Advertiser>)jsonresponse.getData());
		assertTrue(advertisers.get(0).getId()>advertisers.get(1).getId());
		assertNotNull(jsonresponse.getMeta());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testBaiscGetWithPageLimitWithMock() throws ClientException, ParseException {
		t1.setAuthenticated(true);
		Mockito.when(getservicemock.get(Mockito.any(QueryCriteria.class))) .thenReturn( new StringBuffer("advertisers?page_limit=5"));
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1Response.class))).thenReturn("{\"data\" : [{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\",\"id\" : 165615},"
				+"{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\",\"id\" : 165612},"
				+"{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\",\"id\" : 165611},"
				+"{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\",\"id\" : 165608},"
				+"{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\",\"id\" : 165607}],"
				+"\"meta\" : {\"next_page\" : \"https://t1sandbox-origin.mediamath.com/api/v2.0/advertisers?page_offset=5&sort_by=-id&api_key=e34f74vnubr9uxasz2n7bdfv&page_limit=5\","
				+"\"etag\" : \"b81e3860fa348058a011846933765bb08a59af6b\",\"count\" : 5,\"called_on\" : \"2016-07-14T11:14:37+0000\",\"status\" : \"ok\",\"offset\" : 0,\"total_count\" : 437}}");
	
		QueryCriteria query = QueryCriteria.builder().setCollection("advertisers").setPageLimit(5).build();

		JsonResponse<?> jsonresponse = null;
		
		try {
			jsonresponse = t1.get(query);
			Mockito.verify(connectionmock).get(Mockito.anyString(), Mockito.any(T1Response.class));
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(jsonresponse);
		assertNotNull(jsonresponse.getData());
		ArrayList<Advertiser> advertisers= ((ArrayList<Advertiser>)jsonresponse.getData());
		assertTrue(advertisers.size()==5);
		assertNotNull(jsonresponse.getMeta());
	}
	
	@Test
	public void testBaiscGetWithPageLimitOffsetWithMock() throws ClientException, ParseException  {
		t1.setAuthenticated(true);
		Mockito.when(getservicemock.get(Mockito.any(QueryCriteria.class))) .thenReturn( new StringBuffer("advertisers?page_limit=5&page_offset=300"));
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1Response.class))).thenReturn("{\"data\" : [{\"entity_type\" : \"advertiser\",\"name\" : \"ITtest - Wed Jul  9 19:27:53 2014\",\"id\" : 125814},"
				+"{\"entity_type\" : \"advertiser\",\"name\" : \"ITtest - Wed Jul  9 19:26:35 2014\",\"id\" : 125813},"
				+"{\"entity_type\" : \"advertiser\",\"name\" : \"ITtest - Wed Jul  9 19:16:35 2014\",\"id\" : 125812},"
				+"{\"entity_type\" : \"advertiser\",\"name\" : \"ittest35\",\"id\" : 125810},"
				+"{\"entity_type\" : \"advertiser\",\"name\" : \"ITtest - Wed Jul  9 18:23:07 2014\",\"id\" : 125809}],"
				+"\"meta\" : {\"next_page\" : \"https://t1sandbox-origin.mediamath.com/api/v2.0/advertisers?page_offset=305&api_key=e34f74vnubr9uxasz2n7bdfv&page_limit=5\","
				+"\"etag\" : \"dec1c26f7e2799a15684b07cbe0ef8fdcd1ee101\",\"count\" : 5,\"called_on\" : \"2016-07-18T06:51:33+0000\",\"status\" : \"ok\",\"offset\" : \"300\",\"total_count\" : 437}}");
		QueryCriteria query = QueryCriteria.builder().setCollection("advertisers").setPageLimit(5).setPageOffset(300).build();
		JsonResponse<?> jsonresponse = null;

		try {
			jsonresponse = t1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(jsonresponse);
		
	}
	
	@Test
	public void testBaiscGetWithLimitWithMock() throws ClientException, ParseException {
		t1.setAuthenticated(true);
		Mockito.when(getservicemock.get(Mockito.any(QueryCriteria.class))) .thenReturn( new StringBuffer("advertisers/limit/agency=111555?page_limit=5"));
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1Response.class))).thenReturn("{\"data\" : [{\"entity_type\" : \"advertiser\",\"name\" : \"JPTAK updated advertiser name\",\"id\" : 153648}],"
				+"\"meta\" : {\"etag\" : \"5b27a314978a549556affb650670a8f902bec1c9\",\"count\" : 1,\"called_on\" : \"2016-07-18T07:04:49+0000\",\"status\" : \"ok\",\"offset\" : 0,\"total_count\" : 1}}");
		
		Map<String, Long> limitList = new HashMap<String, Long>();
		limitList.put("agency", Long.valueOf(111555));
		QueryCriteria query = QueryCriteria.builder().setCollection("advertisers").setLimit(limitList).setPageLimit(5).build();
		
		JsonResponse<?> jsonresponse = null;
		
		try {
			jsonresponse = t1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(jsonresponse);
	
	}
	
	@Test
	public void testBaiscGetWithQueryWithMock() throws ClientException, ParseException {
		
		t1.setAuthenticated(true);
		Mockito.when(getservicemock.get(Mockito.any(QueryCriteria.class))) .thenReturn( new StringBuffer("advertisers?with=agency,organization&page_limit=5&q=agency_id%3E=109308"));
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1Response.class))).thenReturn("{\"data\" : [{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\",\"id\" : 165615,\"agency\" : {\"rel\" : \"agency\",\"entity_type\" : \"agency\",\"name\" : \"EGLE AM 109308\","
				+"\"id\" : 109308,\"organization\" : {\"rel\" : \"organization\",\"entity_type\" : \"organization\",\"name\" : \"ACME Org\",\"id\" : 100048}}},"
				+"{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\",\"id\" : 165612,\"agency\" : {\"rel\" : \"agency\",\"entity_type\" : \"agency\",\"name\" : \"EGLE AM 109308\",\"id\" : 109308,"
				+"\"organization\" : {\"rel\" : \"organization\",\"entity_type\" : \"organization\",\"name\" : \"ACME Org\",\"id\" : 100048}}},{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\","
				+"\"id\" : 165611,\"agency\" : {\"rel\" : \"agency\",\"entity_type\" : \"agency\",\"name\" : \"EGLE AM 109308\",\"id\" : 109308,\"organization\" : {\"rel\" : \"organization\",\"entity_type\" : \"organization\",\"name\" : \"ACME Org\",\"id\" : 100048}}},"
				+"{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\",\"id\" : 165608,\"agency\" : {\"rel\" : \"agency\",\"entity_type\" : \"agency\",\"name\" : \"EGLE AM 109308\",\"id\" : 109308,"
				+"\"organization\" : {\"rel\" : \"organization\",\"entity_type\" : \"organization\",\"name\" : \"ACME Org\",\"id\" : 100048}}},{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\","
				+"\"id\" : 165607,\"agency\" : {\"rel\" : \"agency\",\"entity_type\" : \"agency\",\"name\" : \"EGLE AM 109308\",\"id\" : 109308,\"organization\" : {\"rel\" : \"organization\",\"entity_type\" : \"organization\",\"name\" : \"ACME Org\",\"id\" : 100048}}}],"
				+"\"meta\" : {\"next_page\" : \"https://t1sandbox-origin.mediamath.com/api/v2.0/advertisers?page_offset=5&q=agency_id%3E%3D109308&with=agency%2Corganization&api_key=e34f74vnubr9uxasz2n7bdfv&page_limit=5\","
				+"\"etag\" : \"0fb44654d91976113634f32e03795e591b64d1e8\",\"count\" : 5,\"called_on\" : \"2016-07-18T07:24:19+0000\",\"status\" : \"ok\",\"offset\" : 0,\"total_count\" : 124}}");

		
		Map<String, Long> limitList = new HashMap<String, Long>();
		limitList.put("agency", Long.valueOf(111555));
		QueryCriteria query = QueryCriteria.builder().setCollection("advertisers").setInclude(new ConditionQuery("agency", "organization")).setQuery("agency_id%3E=109308").setPageLimit(5).build();
		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = t1.get(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(jsonresponse);
		
	}
	
	@Test
	public void testBaiscGetWithFindWithMock() throws ClientException, ParseException {
		t1.setAuthenticated(true);
		Mockito.when(getservicemock.get(Mockito.any(QueryCriteria.class))) .thenReturn( new StringBuffer("advertisers?page_limit=5&q=agency_id%3E=109308"));
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1Response.class))).thenReturn("{\"data\" : [{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\",\"id\" : 165615},{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\",\"id\" : 165612},{\"entity_type\" : \"advertiser\","
				+"\"name\" : \"ABC Advertisers\",\"id\" : 165611},{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\",\"id\" : 165608},{\"entity_type\" : \"advertiser\",\"name\" : \"ABC Advertisers\",\"id\" : 165607}],"
				+"\"meta\" : {\"next_page\" : \"https://t1sandbox-origin.mediamath.com/api/v2.0/advertisers?page_offset=5&q=agency_id%3E%3D109308&api_key=e34f74vnubr9uxasz2n7bdfv&page_limit=5\","
				+"\"etag\" : \"b81e3860fa348058a011846933765bb08a59af6b\",\"count\" : 5,\"called_on\" : \"2016-07-18T08:40:48+0000\",\"status\" : \"ok\",\"offset\" : 0,\"total_count\" : 124}}");

		Map<String, Long> limitList = new HashMap<String, Long>();
		limitList.put("agency", Long.valueOf(111555));
		QueryCriteria query = QueryCriteria.builder().setCollection("advertisers").setQueryParamName("agency_id").setQueryOperator(Filters.GREATER_OR_EQUAL).setQueryParams(new QueryParamValues(109308)).setPageLimit(100).build();

		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = t1.find(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			throw new AssertionError();
		}
		
		assertNotNull(jsonresponse);
	}
	
	
	@Test
	public void testBaiscGetWithFind1WithMock() throws ClientException, ParseException {
		t1.setAuthenticated(true);
		Mockito.when(getservicemock.get(Mockito.any(QueryCriteria.class))) .thenReturn( new StringBuffer("advertisers?page_limit=100&q=name==Retirement"));
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1Response.class))).thenReturn("{\"data\" : [],\"meta\" : {\"etag\" : \"97d170e1550eee4afc0af065b78cda302a97674c\",\"count\" : 0,\"called_on\" : \"2016-07-18T08:48:22+0000\",\"status\" : \"ok\",\"offset\" : 0,\"total_count\" : 0}}");
		
		Map<String, Long> limitList = new HashMap<String, Long>();
		limitList.put("agency", Long.valueOf(111555));
		QueryCriteria query = QueryCriteria.builder().setCollection("advertisers").setQueryParamName("name").setQueryOperator(Filters.EQUALS).setQueryParams(new QueryParamValues("Retirement")).setPageLimit(100).build();
		
		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = t1.find(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(jsonresponse);
	
	}
	
	
	@Test
	public void testBaiscGetWithFind2WithMock() throws ClientException, ParseException  {
		t1.setAuthenticated(true);
		Mockito.when(getservicemock.get(Mockito.any(QueryCriteria.class))) .thenReturn( new StringBuffer("advertisers?page_limit=100&q=(154121,153226,150994)"));
		Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1Response.class))).thenReturn("{\"data\" : [{\"entity_type\" : \"advertiser\",\"name\" : \"Japan Samurai\",\"id\" : 154121},{\"entity_type\" : \"advertiser\",\"name\" : \"upcast_test\",\"id\" : 153226},{\"entity_type\" : \"advertiser\",\"name\" : \"Test_Dell\",\"id\" : 150994}],"
				+"\"meta\" : {\"etag\" : \"9c2310a74a3984d3e789895531eb79f30c858231\",\"count\" : 3,\"called_on\" : \"2016-07-18T08:54:54+0000\",\"status\" : \"ok\",\"offset\" : 0,\"total_count\" : 3}}");

		List<Object> qParams = new ArrayList<Object>();
		qParams.add(154121);
		qParams.add(153226);
		qParams.add(150994);
		QueryCriteria query = QueryCriteria.builder().setCollection("advertisers").setQueryParams(new QueryParamValues("name")).setQueryOperator(Filters.IN).setQueryParams(new QueryParamValues(qParams)).build();
		JsonResponse<?> jsonresponse = null;
		try {
			jsonresponse = t1.find(query);
		} catch (ClientException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(jsonresponse);
	
	}
	
	
}
