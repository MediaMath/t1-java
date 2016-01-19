package com.mediamath.jterminalone;


import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.mediamath.jterminalone.models.JsonResponse;
import com.mediamath.jterminalone.utils.Filters;

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
		
		JsonResponse<?> jsonresponse = null;
		
		jsonresponse = jt1.get(query);
		
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
		
		JsonResponse<?> jsonresponse = null;
		
		jsonresponse = jt1.get(query);
		
		assertNotNull(jsonresponse);
	
	}
	
	@Test
	public void testBaiscGetWithPageLimit() {
		JTerminalOne jt1 = new JTerminalOne();
		jt1.authenticate("jitendra.chaudhari@xoriant.com", "xoriant123#", "kdcvkmat98dk7atjx5evsb6d");
		
		QueryCriteria query = QueryCriteria.builder().setCollection("advertisers").setPageLimit(40).build();
		
		JsonResponse<?> jsonresponse = null;
		
		jsonresponse = jt1.get(query);
		
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
		
		JsonResponse<?> jsonresponse = null;
		
		jsonresponse = jt1.get(query);
		
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
		
		JsonResponse<?> jsonresponse = null;
		
		jsonresponse = jt1.get(query);
		
		assertNotNull(jsonresponse);
	
	}
	
	@Test
	public void testBaiscGetWithQuery() {
		JTerminalOne jt1 = new JTerminalOne();
		jt1.authenticate("jitendra.chaudhari@xoriant.com", "xoriant123#", "kdcvkmat98dk7atjx5evsb6d");
		Map<String, Long> limitList = new HashMap<String, Long>();
		limitList.put("agency", Long.valueOf(111555));
		QueryCriteria query = QueryCriteria.builder()
									.setCollection("advertisers")
									.setQuery("agency_id%3E=109308")
									.setPageLimit(100)
									.build();
		JsonResponse<?> jsonresponse = null;
		jsonresponse = jt1.get(query);
		
		assertNotNull(jsonresponse);
		
	}
	
	@Test
	public void testBaiscGetWithFind() {
		JTerminalOne jt1 = new JTerminalOne();
		jt1.authenticate("jitendra.chaudhari@xoriant.com", "xoriant123#", "kdcvkmat98dk7atjx5evsb6d");
		Map<String, Long> limitList = new HashMap<String, Long>();
		limitList.put("agency", Long.valueOf(111555));
		QueryCriteria query = QueryCriteria.builder()
									.setCollection("advertisers")
									.setQueryParam("agency_id")
									.setQueryOperator(Filters.GREATER_OR_EQUAL)
									.setQueryParamValueNumber(109308)
									.setPageLimit(100)
									.build();
		JsonResponse<?> jsonresponse = null;
		jsonresponse = jt1.find(query);
		
		assertNotNull(jsonresponse);
	
	}
	
	@Test
	public void testBaiscGetWithFind1() {
		JTerminalOne jt1 = new JTerminalOne();
		jt1.authenticate("jitendra.chaudhari@xoriant.com", "xoriant123#", "kdcvkmat98dk7atjx5evsb6d");
		Map<String, Long> limitList = new HashMap<String, Long>();
		limitList.put("agency", Long.valueOf(111555));
		QueryCriteria query = QueryCriteria.builder()
									.setCollection("advertisers")
									.setQueryParam("name")
									.setQueryOperator(Filters.EQUALS)
									.setQueryParamValueStr("Retirement")
									.setPageLimit(100)
									.build();
		
		JsonResponse<?> jsonresponse = null;
		jsonresponse = jt1.find(query);
		
		assertNotNull(jsonresponse);
	
	}
	
	/*@Test
	public void testBaiscGetWithFind2() {
		JTerminalOne jt1 = new JTerminalOne();
		jt1.authenticate("jitendra.chaudhari@xoriant.com", "xoriant123#", "kdcvkmat98dk7atjx5evsb6d");
		Map<String, Long> limitList = new HashMap<String, Long>();
		limitList.put("agency", Long.valueOf(111555));
		List<Object> qParams = new ArrayList<Object>();
		qParams.add(154121);
		qParams.add(153226);
		qParams.add(150994);
		QueryCriteria query = QueryCriteria.builder()
									.setCollection("advertisers")
									.setQueryParam("name")
									.setQueryOperator(Filters.IN)
									.setQueryParamValueList(qParams)
									.setPageLimit(100)
									.build();
		JsonResponse<?> jsonresponse = null;
		jsonresponse = jt1.find(query);
		
		assertNotNull(jsonresponse);
	
	}*/
	
}
