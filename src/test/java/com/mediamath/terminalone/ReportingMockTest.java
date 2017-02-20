package com.mediamath.terminalone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
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
import com.mediamath.terminalone.functional.PostFunctionalTestIT;
import com.mediamath.terminalone.models.JsonResponse;
import com.mediamath.terminalone.models.T1User;
import com.mediamath.terminalone.models.reporting.ReportValidationResponse;
import com.mediamath.terminalone.models.reporting.Reports;
import com.mediamath.terminalone.models.reporting.meta.MetaData;

@RunWith(MockitoJUnitRunner.class)
public class ReportingMockTest {

  private static String VALIDATE_PERFORMANCE_REPORT = null;

  private static String META = null;
  
  private static String REPORTSMETA = null;
  
  private static Properties testConfig = new Properties();
  
  private static String LOGIN = null;
  
  @Mock
  Connection connectionmock;

  @InjectMocks
  TerminalOne t1 = new TerminalOne();
  
  @Mock 
  Response response;
  
  @Mock
  InputStream stream;
  
  @Mock
  MediaType type;
  
  @BeforeClass
  public static void init() throws Exception {
    InputStream input = PostFunctionalTestIT.class.getClassLoader().getResourceAsStream("mocktest.properties");
    testConfig.load(input);
    LOGIN = testConfig.getProperty("t1.mock.loginResponse");
    VALIDATE_PERFORMANCE_REPORT = testConfig.getProperty("t1.mock.reporting.validate.performance.report");
    META = testConfig.getProperty("t1.mock.reporting.meta");
    REPORTSMETA = testConfig.getProperty("t1.mock.reporting.reportsmeta");
  }
  
  @After
  public final void tearDown() throws InterruptedException {
    Thread.sleep(5000);
  }

  
  @SuppressWarnings("unchecked")
  @Test
  public void testGetMeta() throws ClientException {

    Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class))).thenReturn(response);
    Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN, META);
    Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1User.class))).thenReturn(META);

    //login and get the reports.
    t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
    JsonResponse<?> response = t1.getMeta();

    assertNotNull(response.getData());
    
  }
  
  
  @SuppressWarnings("unchecked")
  @Test
  public void testReportsMeta() throws ClientException {
    
    Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class))).thenReturn(response);
    Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN, REPORTSMETA);
    Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1User.class))).thenReturn(REPORTSMETA);
    
    t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
    MetaData response = t1.getReportsMeta(Reports.GEO);
    
    assertNotNull(response);
    
  }
  
  @SuppressWarnings("unchecked")
  @Test
  public void testPerformanceReport() throws ClientException, ParseException {
    
    Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class))).thenReturn(response);
    Mockito.when(connectionmock.getReportData(Mockito.anyString(), Mockito.any(T1User.class))).thenReturn(response);
    Mockito.when(response.getMediaType()).thenReturn(type);
    Mockito.when(response.getMediaType().getType()).thenReturn("text");
    Mockito.when(response.getMediaType().getSubtype()).thenReturn("csv");
    Mockito.when(response.getStatus()).thenReturn(200);
    Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN, stream);

    ReportCriteria report = new ReportCriteria();

    report.setDimension("advertiser_name");
    report.setDimension("campaign_id");
    report.setDimension("campaign_name");
    report.setFilter("organization_id", "=", "100048");
    report.setMetric("impressions");
    report.setMetric("clicks");
    report.setMetric("total_conversions");
    report.setMetric("media_cost");
    report.setMetric("total_spend");

    // set having
    // report.setHaving("key1", "=", "val1,val2");

    // set time_rollup
    report.setTime_rollup("by_day");

    // set time_window only when no start date and end date specified.
    // report.setTime_window("last_60_days");

    /*
     * start date & end_date supported format month - YYYY-MM day - YYYY-MM-DD hour - YYYY-MM-DDThh
     * minute - YYYY-MM-DDThh:mi second - YYYY-MM-DDThh:mi:ss
     */
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    String dateInString = "2015-02-06";
    String endDateInString = "2015-04-16";

    String startDate = df.format(df.parse(dateInString));
    String endDate = df.format(df.parse(endDateInString));

    report.setStart_date(startDate);
    report.setEnd_date(endDate);
    
    t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
    BufferedReader reader = t1.getReport(Reports.PERFORMANCE, report);
    
    assertNotNull(reader);
    
  }
  
  
  @SuppressWarnings("unchecked")
  @Test
  public void testValidatePerformanceReport() throws ParseException, ClientException {

    Mockito.when(connectionmock.post(Mockito.anyString(), Mockito.any(Form.class), Mockito.any(T1User.class))).thenReturn(response);
    Mockito.when(connectionmock.getReportData(Mockito.anyString(), Mockito.any(T1User.class))).thenReturn(response);
    
    Mockito.when(response.getMediaType()).thenReturn(type);
    Mockito.when(response.getMediaType().getType()).thenReturn("text");
    Mockito.when(response.getMediaType().getSubtype()).thenReturn("xml");
    Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN, VALIDATE_PERFORMANCE_REPORT);
    
    
    ReportCriteria report = new ReportCriteria();

    report.setDimension("advertiser_name");
    report.setDimension("campaign_id");
    report.setDimension("campaign_name");
    report.setFilter("organization_id", "=", "100048");
    report.setMetric("impressions");
    report.setMetric("clicks");
    report.setMetric("total_conversions");
    report.setMetric("media_cost");
    report.setMetric("total_spend");

    // set having
    // report.setHaving("key1", "=", "val1,val2");

    // set time_rollup
    report.setTime_rollup("by_day");

    // set time_window only when no start date and end date specified.
    // report.setTime_window("last_60_days");

    /*
     * start date & end_date supported format month - YYYY-MM day - YYYY-MM-DD hour - YYYY-MM-DDThh
     * minute - YYYY-MM-DDThh:mi second - YYYY-MM-DDThh:mi:ss
     */
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    String dateInString = "2015-02-06";
    String endDateInString = "2015-04-16";

    String startDate = df.format(df.parse(dateInString));
    String endDate = df.format(df.parse(endDateInString));

    report.setStart_date(startDate);
    report.setEnd_date(endDate);
    
    t1.authenticate("abc", "xyz", "adfadslfadkfakjf");
    ReportValidationResponse response = t1.validateReport(Reports.PERFORMANCE, report);
    
    assertNotNull(response);
    assertEquals("ok", response.getStatus()[0].getCode());

  }
  
}
