package com.mediamath.terminalone;

import java.util.ArrayList;
import java.util.Date;

import com.mediamath.terminalone.models.reporting.Having;
import com.mediamath.terminalone.models.reporting.ReportFilter;

public class ReportCriteria {
	
	private String reportName = null;
	
	private long precision = 0; 

	private ArrayList<String> dimensions = new ArrayList<String>();
	
	private ArrayList<String> metrics = new ArrayList<String>();
	
	ArrayList<ReportFilter> filters = new ArrayList<ReportFilter>();

	ArrayList<Having> having = new ArrayList<Having>();
	
	/*private String time_field;
	
	private String time_aggregation;*/
	
	//private ArrayList<String> time_rollups = new ArrayList<String>();
	
	private String time_rollup;
	
	//private ArrayList<String> time_windows = new ArrayList<String>();
	
	private String time_window;
	
	private String timezone;
	
	private Date start_date;
	
	private Date end_date;
	
	private String page_limit;
	
	private String page_offset;
	
	private ArrayList<String> order = new ArrayList<String>();
	
	// Getters & Setters
	public void setDimension(String dimension){
		this.dimensions.add(dimension);
	}
	
	public void setDimensions(ArrayList<String> dimensions) {
		this.dimensions = dimensions;
	}
	
	public ArrayList<String> getDimensions() {
		return this.dimensions;
	}
	
	public String getReportName() {
		return reportName;
	}
	
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}


	public ArrayList<String> getMetrics() {
		return metrics;
	}
	
	public void setMetric(String metric) {
		this.metrics.add(metric);
	}

	public void setMetrics(ArrayList<String> metrics) {
		this.metrics = metrics;
	}

/*	public String getTime_field() {
		return time_field;
	}

	public void setTime_field(String time_field) {
		this.time_field = time_field;
	}

	public String getTime_aggregation() {
		return time_aggregation;
	}

	public void setTime_aggregation(String time_aggregation) {
		this.time_aggregation = time_aggregation;
	}*/

/*	public ArrayList<String> getTime_windows() {
		return time_windows;
	}
	
	public void setTime_window(String window) {
		this.time_windows.add(window);
	}

	public void setTime_windows(ArrayList<String> time_windows) {
		this.time_windows = time_windows;
	}*/

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public long getPrecision() {
		return precision;
	}

	public void setPrecision(long precision) {
		this.precision = precision;
	}
	
	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public ArrayList<ReportFilter> getFilters() {
		return filters;
	}

	public void setFilters(ArrayList<ReportFilter> filters) {
		this.filters = filters;
	}
	
	public void setFilter(String key, String operator, String value) {
		if(!key.isEmpty() && !value.isEmpty()) {
			ReportFilter filter = new ReportFilter();
			filter.setKey(key);
			filter.setOperator(operator);
			filter.setValue(value);
			this.filters.add(filter);
		}
	}
	
	public void setHaving(String key, String operator, String value) {
		if(!key.isEmpty() && !operator.isEmpty() && !value.isEmpty()) {
			Having having = new Having();
			having.setKey(key);
			having.setOperator(operator);
			having.setValue(value);
			this.having.add(having);
		}
	}

	public ArrayList<Having> getHaving() {
		return having;
	}

	public void setHaving(ArrayList<Having> having) {
		this.having = having;
	}

	public String getTime_rollup() {
		return time_rollup;
	}

	public void setTime_rollup(String time_rollup) {
		this.time_rollup = time_rollup;
	}

	public String getTime_window() {
		return time_window;
	}

	public void setTime_window(String time_window) {
		this.time_window = time_window;
	}

	public ArrayList<String> getOrder() {
		return order;
	}

	public void setOrder(ArrayList<String> order) {
		this.order = order;
	}
	
	public void setOrder(String order) {
		this.order.add(order);
	}

	public String getPage_limit() {
		return page_limit;
	}

	public void setPage_limit(String page_limit) {
		this.page_limit = page_limit;
	}

	public String getPage_offset() {
		return page_offset;
	}

	public void setPage_offset(String page_offset) {
		this.page_offset = page_offset;
	}

}
