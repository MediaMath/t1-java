package com.mediamath.jterminalone;

import org.junit.Test;

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
		String uri = jt1.get("advertisers", 154161, null);
		String response = jt1.connection.get(uri, jt1.getUser());
		assertNotNull(response);
	
	}
	
	@Test
	public void testBaiscGetWithChild() {
		JTerminalOne jt1 = new JTerminalOne();
		jt1.authenticate("nitesh.chauhan@xoriant.com", "xoriant123#", "e34f74vnubr9uxasz2n7bdfv");
		String uri = jt1.get("advertisers", 154161, "concepts");
		String response = jt1.connection.get(uri, jt1.getUser());
		assertNotNull(response);
	
	}
}
