package com.mediamath.terminalone.models;

import java.util.ArrayList;

public class GoalValue {
    private Double percentageValue;
    private ArrayList<T1Cost> absoluteValue;

    public Double getPercentageValue() {
        return percentageValue;
    }

    public void setPercentageValue(Double percentageValue) {
        this.percentageValue = percentageValue;
    }

    public ArrayList<T1Cost> getAbsoluteValue() {
        return absoluteValue;
    }

    public void setAbsoluteValue(ArrayList<T1Cost> absoluteValue) {
        this.absoluteValue = absoluteValue;
    }
}
