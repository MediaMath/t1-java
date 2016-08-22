package com.mediamath.terminalone.functional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Test;

import com.mediamath.terminalone.ReportCriteria;
import com.mediamath.terminalone.TerminalOne;
import com.mediamath.terminalone.Exceptions.ClientException;
import com.mediamath.terminalone.models.JsonResponse;


public class ReportingFunctionalTest {

	@After
	public final void tearDown() throws InterruptedException {
		Thread.sleep(5000);
	}
	
	@Test
	public void testMetaReport() {
		TerminalOne t1;
		
		try {
			t1 = new TerminalOne("nitesh.chauhan@xoriant.com", "xoriant123#","ys7ph5479kfrkpeb747mpgu3");
			assertEquals(true, t1.isAuthenticated());
			
			JsonResponse<?> jsonresponse = null;
			jsonresponse = t1.getMeta();
			
			assertNotNull(jsonresponse);
			
		} catch (ClientException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testAppTransparencyReport() {
		TerminalOne t1;
		try{
			t1 = new TerminalOne("nitesh.chauhan@xoriant.com", "xoriant123#","ys7ph5479kfrkpeb747mpgu3");
			assertEquals(true, t1.isAuthenticated());
			
			ReportCriteria report = new ReportCriteria();
			report.setDimension("hello");
			report.setDimension("world");
			report.setDimension("how");
			report.setDimension("are");
			report.setDimension("you");
			report.setFilter("key1", "=", "val1,val2");
			report.setFilter("key2", "=", "val1");
			report.setFilter("key3", "=", "\"val1,val2\"");
			
			t1.getAppTransparencyReport(report);
			
			JsonResponse<?> jsonresponse = null;
		}catch(ClientException e) {
			
		}
		
		
	}
	
	
}
