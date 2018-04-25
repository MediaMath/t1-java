package com.mediamath.terminalone.models;

public class Currency {

    private String currency_code;

    private float value;

    public String getCurrencyCode() {
        return currency_code;
    }

    public void setCurrencyCode(String currency_code) {
        this.currency_code = currency_code;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

}
