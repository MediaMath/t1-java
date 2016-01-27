package com.mediamath.jterminalone;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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
	public void testBaiscGet() {
		JTerminalOne jt1 = new JTerminalOne();
		jt1.authenticate("nitesh.chauhan@xoriant.com", "xoriant123#", "e34f74vnubr9uxasz2n7bdfv");
		String uri = jt1.get("advertisers", 154161, null, null);
		String response = jt1.connection.get(uri, jt1.getUser());
		assertNotNull(response);
	
	}
	
	@Test
	public void testBaiscGetWithChild() {
		JTerminalOne jt1 = new JTerminalOne();
		jt1.authenticate("nitesh.chauhan@xoriant.com", "xoriant123#", "e34f74vnubr9uxasz2n7bdfv");
		
		String[] s = {"agency,organization", "ad_server", "vendor"};
		String uri = jt1.get("advertisers", 154161, null, s);
		String response = jt1.connection.get(uri, jt1.getUser());
		
		T1JsonToObjParser<Advertiser> parser = new T1JsonToObjParser<Advertiser>();
		Type JsonResponseType = new TypeToken<JsonResponse<Advertiser>>(){}.getType();
		JsonResponse<Advertiser> jsonresponse = parser.parseJsonToObj(response, JsonResponseType);
		assertNotNull(jsonresponse);
	
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
}
