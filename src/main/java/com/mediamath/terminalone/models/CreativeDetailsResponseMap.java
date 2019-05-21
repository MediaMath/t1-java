package com.mediamath.terminalone.models;

import com.mediamath.terminalone.exceptions.ClientException;

import javax.ws.rs.core.Form;
import java.util.Map;

public class CreativeDetailsResponseMap implements T1Entity {
    private Map<String,CreativeDetailsResponse> value;

    public CreativeDetailsResponseMap(Map<String, CreativeDetailsResponse> value) {
        this.value = value;
    }

    public Map<String, CreativeDetailsResponse> getValue() {
        return value;
    }

    public void setValue(Map<String, CreativeDetailsResponse> value) {
        this.value = value;
    }

    @Override
	public String getEntityname() {
		return null;
	}

	@Override
	public Form getForm() {
		return null;
	}

	@Override
	public String getUri() throws ClientException {
		return null;
	}

}
