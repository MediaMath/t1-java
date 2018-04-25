package com.mediamath.terminalone.functional;

import com.mediamath.terminalone.ReportCriteria;
import com.mediamath.terminalone.TerminalOne;
import com.mediamath.terminalone.exceptions.ClientException;
import com.mediamath.terminalone.models.JsonResponse;
import com.mediamath.terminalone.models.reporting.ReportValidationResponse;
import com.mediamath.terminalone.models.reporting.Reports;
import com.mediamath.terminalone.models.reporting.meta.MetaData;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ReportingFunctionalTestIT {

    private static Properties testConfig = new Properties();

    private static String user = null;

    private static String password = null;

    private static String apiKey = null;

    @BeforeClass
    public static void init() throws Exception {
        InputStream input = ReportingFunctionalTestIT.class.getClassLoader()
                .getResourceAsStream("test.properties");
        testConfig.load(input);
        user = testConfig.getProperty("t1.username");
        password = testConfig.getProperty("t1.password");
        apiKey = testConfig.getProperty("t1.production_api_key");
    }


    @Test
    public void testMetaReport() {
        TerminalOne t1;

        try {
            t1 = new TerminalOne(user, password, apiKey);
            assertEquals(true, t1.isAuthenticated());

            JsonResponse<?> jsonresponse = null;
            jsonresponse = t1.getMeta();

            assertNotNull(jsonresponse);

        } catch (ClientException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testReportsMeta() {
        TerminalOne t1;

        try {
            t1 = new TerminalOne(user, password, apiKey);
            assertEquals(true, t1.isAuthenticated());
            MetaData metaResponse = t1.getReportsMeta(Reports.GEO);
            assertNotNull(metaResponse);
        } catch (ClientException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testPerformanceReport() throws ParseException, ClientException {
        TerminalOne t1;

        t1 = new TerminalOne(user, password, apiKey);
        assertEquals(true, t1.isAuthenticated());

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
        report.setTimeRollup("by_day");

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

        report.setStartDate(startDate);
        report.setEndDate(endDate);

        BufferedReader reader = t1.getReport(Reports.PERFORMANCE, report);
        assertNotNull(reader);
    }

    @Test
    public void testValidatePerformanceReport() throws ParseException, ClientException {
        TerminalOne t1;

        t1 = new TerminalOne(user, password, apiKey);
        assertEquals(true, t1.isAuthenticated());

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
        report.setTimeRollup("by_day");

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

        report.setStartDate(startDate);
        report.setEndDate(endDate);

        ReportValidationResponse response = t1.validateReport(Reports.PERFORMANCE, report);

        assertNotNull(response);
    }

}
