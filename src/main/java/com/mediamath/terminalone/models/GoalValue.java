package com.mediamath.terminalone.models;

import java.math.BigDecimal;
import java.util.ArrayList;

public class GoalValue {
    private BigDecimal percentageValue;
    private ArrayList<T1Cost> absoluteValue;

    public BigDecimal getPercentageValue() {
        return percentageValue;
    }

    public void setPercentageValue(BigDecimal percentageValue) {
        this.percentageValue = percentageValue;
    }

    public ArrayList<T1Cost> getAbsoluteValue() {
        return absoluteValue;
    }

    public void setAbsoluteValue(ArrayList<T1Cost> absoluteValue) {
        this.absoluteValue = absoluteValue;
    }
}
