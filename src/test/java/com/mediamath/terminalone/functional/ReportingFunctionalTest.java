package com.mediamath.terminalone.functional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Test;

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
