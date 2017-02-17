package com.mediamath.terminalone;

import static org.junit.Assert.assertNotNull;

import java.io.InputStream;
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
import com.mediamath.terminalone.models.JsonResponse;
import com.mediamath.terminalone.models.T1User;

@RunWith(MockitoJUnitRunner.class)
public class ReportingMockTest {

  private static final String META = "{\"reports\":{\"app_transparency\":{\"Description\":\"Standard performance metrics broken out by App ID. Non-app inventory shown as N/A app_id\",\"Name\":\"App Transparency Report\",\"URI_Data\":\"https://api.mediamath.com/reporting/v1/std/app_transparency\",\"URI_Meta\":\"https://api.mediamath.com/reporting/v1/std/app_transparency/meta\",\"Version\":1},\"audience_index\":{\"Description\":\"Special index metrics for comparing your ads' viewers to 3rd party segments.  Broken out by audience name, as well as standard dimensions down to campaign and strategy.  Currently available in one interval: last 14 days.\",\"Name\":\"Audience Index Report\",\"URI_Data\":\"https://api.mediamath.com/reporting/v1/std/audience_index\",\"URI_Meta\":\"https://api.mediamath.com/reporting/v1/std/audience_index/meta\",\"Version\":1},\"audience_index_pixel\":{\"Description\":\"Special index metrics for comparing your site visitors to 3rd party segments.  Broken out by audience name and pixel.  Currently available in one interval: last 14 days.\",\"Name\":\"Audience Index Pixel Report\",\"URI_Data\":\"https://api.mediamath.com/reporting/v1/std/audience_index_pixel\",\"URI_Meta\":\"https://api.mediamath.com/reporting/v1/std/audience_index_pixel/meta\",\"Version\":1},\"contextual_insights\":{\"Description\":\"Standard performance metrics broken out by contextual categories that strategies are targeting\",\"Name\":\"Contextual Insights\",\"URI_Data\":\"https://api.mediamath.com/reporting/v1/std/contextual_insights\",\"URI_Meta\":\"https://api.mediamath.com/reporting/v1/std/contextual_insights/meta\",\"Version\":1},\"data_pixel_loads\":{\"Description\":\"Loads and Uniques metrics for data pixels, broken out by referrer and referrer rank, available by day.\",\"Name\":\"Data Pixel Loads Report\",\"URI_Data\":\"https://api.mediamath.com/reporting/v1/std/data_pixel_loads\",\"URI_Meta\":\"https://api.mediamath.com/reporting/v1/std/data_pixel_loads/meta\",\"Version\":1},\"day_part\":{\"Description\":\"Standard performance metrics broken out by time of day and day of week. Available in standard intervals.\",\"Name\":\"Day Part Report\",\"URI_Data\":\"https://api.mediamath.com/reporting/v1/std/day_part\",\"URI_Meta\":\"https://api.mediamath.com/reporting/v1/std/day_part/meta\",\"Version\":2},\"device_technology\":{\"Description\":\"Standard performance metrics broken out by technology dimensions including browser, operating system, and connection type.  Available in custom date ranges or intervals with the option to aggregate by day, week, or month.\",\"Name\":\"Device Technology Report\",\"URI_Data\":\"https://api.mediamath.com/reporting/v1/std/device_technology\",\"URI_Meta\":\"https://api.mediamath.com/reporting/v1/std/device_technology/meta\",\"Version\":1},\"event_pixel_loads\":{\"Description\":\"Loads and Uniques metrics for event pixels, broken out by referrer and referrer rank, available by day.\",\"Name\":\"Event Pixel Loads Report\",\"URI_Data\":\"https://api.mediamath.com/reporting/v1/std/event_pixel_loads\",\"URI_Meta\":\"https://api.mediamath.com/reporting/v1/std/event_pixel_loads/meta\",\"Version\":1},\"geo\":{\"Description\":\"Standard performance metrics broken out by geographic dimensions including country, region, and metro area.  Available in standard intervals.\",\"Name\":\"Geo Report\",\"URI_Data\":\"https://api.mediamath.com/reporting/v1/std/geo\",\"URI_Meta\":\"https://api.mediamath.com/reporting/v1/std/geo/meta\",\"Version\":2},\"hyperlocal\":{\"Description\":\"Standard performance metrics broken out by Hyperlocal Targeting objects created via third party.\",\"Name\":\"Hyperlocal Report\",\"URI_Data\":\"https://api.mediamath.com/reporting/v1/std/hyperlocal\",\"URI_Meta\":\"https://api.mediamath.com/reporting/v1/std/hyperlocal/meta\",\"Version\":1},\"performance\":{\"Description\":\"Standard performance metrics in campaign currency and broken out by our widest array of dimensions.  Available in custom date ranges or intervals with the option to aggregate by day, week, or month.\",\"Name\":\"Performance Report in Campaign Currency\",\"URI_Data\":\"https://api.mediamath.com/reporting/v1/std/performance\",\"URI_Meta\":\"https://api.mediamath.com/reporting/v1/std/performance/meta\",\"Version\":1},\"postal_code\":{\"Description\":\"Standard performance metrics broken out by postal code. Only includes data for strategies that targeted or anti-targeted postal codes. Available in custom date ranges or intervals with the option to aggregate by day, week, or month.\",\"Name\":\"Postal Code Report\",\"URI_Data\":\"https://api.mediamath.com/reporting/v1/std/postal_code\",\"URI_Meta\":\"https://api.mediamath.com/reporting/v1/std/postal_code/meta\",\"Version\":1},\"pulse\":{\"Description\":\"Standard performance metrics broken out by standard dimensions, available in precise time windows - down to the hour - with the option to aggregate by hour or day.\",\"Name\":\"Pulse Report\",\"URI_Data\":\"https://api.mediamath.com/reporting/v1/std/pulse\",\"URI_Meta\":\"https://api.mediamath.com/reporting/v1/std/pulse/meta\",\"Version\":1},\"reach_frequency\":{\"Description\":\"Basic performance metrics as well as the uniques metric, broken out by frequency of ad exposure.  Available in standard intervals.\",\"Name\":\"Reach and Frequency Report\",\"URI_Data\":\"https://api.mediamath.com/reporting/v1/std/reach_frequency\",\"URI_Meta\":\"https://api.mediamath.com/reporting/v1/std/reach_frequency/meta\",\"Version\":1},\"site_transparency\":{\"Description\":\"Standard performance metrics broken out by the domain of the inventory.  Available in standard intervals.\",\"Name\":\"Site Transparency Report\",\"URI_Data\":\"https://api.mediamath.com/reporting/v1/std/site_transparency\",\"URI_Meta\":\"https://api.mediamath.com/reporting/v1/std/site_transparency/meta\",\"Version\":2},\"video\":{\"Description\":\"Video-specific metrics such as completion rate, skips, and fullscreens broken out by a wide array of dimensions.  Available in custom date-ranges or intervals with the option to aggregate by day, week, or month.\",\"Name\":\"Video Report\",\"URI_Data\":\"https://api.mediamath.com/reporting/v1/std/video\",\"URI_Meta\":\"https://api.mediamath.com/reporting/v1/std/video/meta\",\"Version\":1},\"watermark\":{\"Description\":\"Watermark metrics show how many impressions and how much spend went towards the brain's learning activities.  Viewable by campaign and strategy dimensions and available by day.\",\"Name\":\"Watermark Report in US Dollars\",\"URI_Data\":\"https://api.mediamath.com/reporting/v1/std/watermark\",\"URI_Meta\":\"https://api.mediamath.com/reporting/v1/std/watermark/meta\",\"Version\":1},\"win_loss\":{\"Description\":\"Metrics describe the auction before a win or even a bid has taken place.  Broken out by strategy, exchange, and deal dimensions and available by hour.\",\"Name\":\"Win/Loss Report\",\"URI_Data\":\"https://api.mediamath.com/reporting/v1/std/win_loss\",\"URI_Meta\":\"https://api.mediamath.com/reporting/v1/std/win_loss/meta\",\"Version\":2},\"win_loss_creative\":{\"Description\":\"Metrics describe the auction before a win has taken place. Broken out by creative dimensions and available by hour.\",\"Name\":\"Win/Loss Creative Report\",\"URI_Data\":\"https://api.mediamath.com/reporting/v1/std/win_loss_creative\",\"URI_Meta\":\"https://api.mediamath.com/reporting/v1/std/win_loss_creative/meta\",\"Version\":1}}}";
  private static final String RESPONSEMETA = null;
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
    Mockito.when(response.readEntity(Mockito.any(Class.class))).thenReturn(LOGIN, RESPONSEMETA);
    Mockito.when(connectionmock.get(Mockito.anyString(), Mockito.any(T1User.class))).thenReturn(RESPONSEMETA);
    
    
  }
  
}
