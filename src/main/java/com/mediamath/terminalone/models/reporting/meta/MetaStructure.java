package com.mediamath.terminalone.models.reporting.meta;

public class MetaStructure {

	MetaDimensions dimensions;
	MetaMetrics metrics;
	TimeField time_field;

	public MetaDimensions getDimensions() {
		return dimensions;
	}

	public void setDimensions(MetaDimensions dimensions) {
		this.dimensions = dimensions;
	}

	public MetaMetrics getMetrics() {
		return metrics;
	}

	public void setMetrics(MetaMetrics metrics) {
		this.metrics = metrics;
	}

	public TimeField getTime_field() {
		return time_field;
	}

	public void setTime_field(TimeField time_field) {
		this.time_field = time_field;
	}

}
