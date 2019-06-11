package com.mediamath.terminalone.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mediamath.terminalone.exceptions.ClientException;
import org.javers.core.metamodel.annotation.DiffIgnore;

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
    @DiffIgnore
    @JsonIgnore
	public Form getForm() {
		return null;
	}

	@Override
    @DiffIgnore
    @JsonIgnore
	public String getUri() throws ClientException {
		return null;
	}

}
