package org.entitatemindex.jaxrs.resource;

import java.util.ArrayList;
import java.util.List;

import org.entitatemindex.factory.Factory;

public class ParametersFactory {

    public Parameters createFromString(String parametersAsString) {
        String[] parsedParameters = parametersAsString.split(",");

        List<Parameter> parameters = new ArrayList<Parameter>();

        for (String paramAsString : parsedParameters) {
            parameters.add(Factory.create(Parameter.class).with(paramAsString));
        }

        return new Parameters(parameters);

    }
    
    public Parameters create(List<Parameter> parameters) {
        return new Parameters(parameters);
    }

}
