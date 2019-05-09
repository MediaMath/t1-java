package com.mediamath.terminalone.models;

import java.math.BigDecimal;

public class Currency {

	private String currency_code;

	private BigDecimal value;

	public String getCurrencyCode() {
		return currency_code;
	}

	public void setCurrencyCode(String currency_code) {
		this.currency_code = currency_code;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

}
