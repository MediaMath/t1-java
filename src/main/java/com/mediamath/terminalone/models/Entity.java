package com.mediamath.terminalone.models;

import com.mediamath.terminalone.exceptions.ClientException;

import javax.ws.rs.core.Form;
import java.util.ArrayList;

public class Entity  implements T1Entity {

    protected String entityName;

    public Entity(String entityName) {
        this.entityName = entityName;
    }

    @Override
    public String getEntityname() {
        return this.entityName;
    }

    @Override
    public Form getForm() {
        return null;
    }

    @Override
    public String getUri() throws ClientException {
        return null;
    }

    protected void setCurrencyFieldValue(double value, String currency_code, ArrayList<T1Cost> currencies) {
        currencies.clear();
        if (value > 0) {
            T1Cost cost = new T1Cost();
            cost.setValue(value);

            if (currency_code != null && !currency_code.isEmpty()) {
                cost.setCurrencyCode(currency_code);
            }

            currencies.add(cost);
        }
    }


}
