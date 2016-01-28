package com.mediamath.jterminalone;


import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.google.gson.reflect.TypeToken;
import com.mediamath.jterminalone.models.Advertiser;
import com.mediamath.jterminalone.models.JsonResponse;
import com.mediamath.jterminalone.utils.T1JsonToObjParser;

import junit.framework.TestCase;

public class BasicTest extends TestCase {

	@Test
	public void testJTerminalOneStringStringString() {
		JTerminalOne t1 = new JTerminalOne("nitesh.chauhan@xoriant.com", "xoriant123#","e34f74vnubr9uxasz2n7bdfv");
		assertEquals(true, t1.isAuthenticated());
	}

	@Test
	public void testJTerminalOneAuthenticate() {
		JTerminalOne t1 = new JTerminalOne();
		boolean isAuthenticated = t1.authenticate("nitesh.chauhan@xoriant.com", "xoriant123#","e34f74vnubr9uxasz2n7bdfv");
		assertEquals(true, isAuthenticated);
	}

	@Test
	public void testBaiscGetWithChildUsingQueryCriteria() {
		JTerminalOne jt1 = new JTerminalOne();
		jt1.authenticate("nitesh.chauhan@xoriant.com", "xoriant123#", "e34f74vnubr9uxasz2n7bdfv");
		
		QueryCriteria query = QueryCriteria.builder()
									.setCollection("advertisers")
									.setEntity(154161)
									.setInclude(new ConditionQuery("agency", "organization"))
									.setInclude(new ConditionQuery("ad_server"))
									.setInclude(new ConditionQuery("vertical"))
									.build();
		
		String uri = jt1.get(query);
		
		String response = jt1.connection.get(uri, jt1.getUser());
		
		T1JsonToObjParser<Advertiser> parser = new T1JsonToObjParser<Advertiser>();
		Type JsonResponseType = new TypeToken<JsonResponse<Advertiser>>(){}.getType();
		JsonResponse<Advertiser> jsonresponse = parser.parseJsonToObj(response, JsonResponseType);
		assertNotNull(jsonresponse);
	
	}
	
	@Test
	public void testBaiscGetWithSortByUsingQueryCriteria() {
		JTerminalOne jt1 = new JTerminalOne();
		jt1.authenticate("jitendra.chaudhari@xoriant.com", "xoriant123#", "kdcvkmat98dk7atjx5evsb6d");
		
		QueryCriteria query = QueryCriteria.builder()
									.setCollection("advertisers")
									.setSortby("-id")
									.build();
		
		String uri = jt1.get(query);
		
		String response = jt1.connection.get(uri, jt1.getUser());
		
		T1JsonToObjParser<List<Advertiser>> parser = new T1JsonToObjParser<List<Advertiser>>();
		Type JsonResponseType = new TypeToken<JsonResponse<List<Advertiser>>>(){}.getType();
		JsonResponse<List<Advertiser>> jsonresponse = parser.parseJsonToObj(response, JsonResponseType);
		assertNotNull(jsonresponse);
	
	}
	
	@Test
	public void testBaiscGetWithPageLimit() {
		JTerminalOne jt1 = new JTerminalOne();
		jt1.authenticate("jitendra.chaudhari@xoriant.com", "xoriant123#", "kdcvkmat98dk7atjx5evsb6d");
		
		QueryCriteria query = QueryCriteria.builder()
									.setCollection("advertisers")
									.setPageLimit(40)
									.build();
		
		String uri = jt1.get(query);
		
		String response = jt1.connection.get(uri, jt1.getUser());
		
		T1JsonToObjParser<List<Advertiser>> parser = new T1JsonToObjParser<List<Advertiser>>();
		Type JsonResponseType = new TypeToken<JsonResponse<List<Advertiser>>>(){}.getType();
		JsonResponse<List<Advertiser>> jsonresponse = parser.parseJsonToObj(response, JsonResponseType);
		assertNotNull(jsonresponse);
	
	}
	@Test
	public void testBaiscGetWithPageLimitOffset() {
		JTerminalOne jt1 = new JTerminalOne();
		jt1.authenticate("jitendra.chaudhari@xoriant.com", "xoriant123#", "kdcvkmat98dk7atjx5evsb6d");
		
		QueryCriteria query = QueryCriteria.builder()
									.setCollection("advertisers")
									.setPageLimit(40)
									.setPageOffset(300)
									.build();
		
		String uri = jt1.get(query);
		
		String response = jt1.connection.get(uri, jt1.getUser());
		
		T1JsonToObjParser<List<Advertiser>> parser = new T1JsonToObjParser<List<Advertiser>>();
		Type JsonResponseType = new TypeToken<JsonResponse<List<Advertiser>>>(){}.getType();
		JsonResponse<List<Advertiser>> jsonresponse = parser.parseJsonToObj(response, JsonResponseType);
		assertNotNull(jsonresponse);
	
	}
	
	@Test
	public void testBaiscGetWithLimit() {
		JTerminalOne jt1 = new JTerminalOne();
		jt1.authenticate("jitendra.chaudhari@xoriant.com", "xoriant123#", "kdcvkmat98dk7atjx5evsb6d");
		Map<String, Long> limitList = new HashMap<String, Long>();
		limitList.put("agency", Long.valueOf(111555));
		QueryCriteria query = QueryCriteria.builder()
									.setCollection("advertisers")
									.setLimit(limitList)
									.setPageLimit(100)
									.build();
		
		String uri = jt1.get(query);
		
		String response = jt1.connection.get(uri, jt1.getUser());
		
		T1JsonToObjParser<List<Advertiser>> parser = new T1JsonToObjParser<List<Advertiser>>();
		Type JsonResponseType = new TypeToken<JsonResponse<List<Advertiser>>>(){}.getType();
		JsonResponse<List<Advertiser>> jsonresponse = parser.parseJsonToObj(response, JsonResponseType);
		assertNotNull(jsonresponse);
	
	}
	
}
