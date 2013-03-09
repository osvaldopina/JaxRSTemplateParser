package org.entitatemindex.jaxrs.resource;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.entitatemindex.factory.Factory;
import org.entitatemindex.jaxrs.template.JaxrsTemplate;
import org.entitatemindex.type.Type;

public class JaxrsResourceFactory {

    public JaxrsResource createFromString(String jaxrsResourceAsString) {
        String[] parsedJaxrsResource = jaxrsResourceAsString.split(" ");

        Type type = Factory.create(Type.class).with(parsedJaxrsResource[0]);

        Type payloadType = Factory.create(Type.class).with(parsedJaxrsResource[1]);

        HttpMethod httpMethod = HttpMethod.valueOf(parsedJaxrsResource[2]);

        JaxrsTemplate template = Factory.create(JaxrsTemplate.class).with(parsedJaxrsResource[3]);

        Parameters pathParameters = new Parameters();
        
        if (!jaxrsResourceAsString.contains("path-params[]")) {
            pathParameters  = Factory.create(Parameters.class).with(parsedJaxrsResource[4].substring(12,
                    parsedJaxrsResource[4].length() - 1));
        }

        Parameters queryParameters = new Parameters();
        if (!jaxrsResourceAsString.contains("query-params[]")) {
            queryParameters  = Factory.create(Parameters.class).with(parsedJaxrsResource[5].substring(13,
                    parsedJaxrsResource[5].length() - 1));
        }

        return new JaxrsResource(type, payloadType, httpMethod, Factory.create(Parameters.class).with(queryParameters),
                Factory.create(Parameters.class).with(pathParameters), template);
    }

    public JaxrsResource create(Type type, Type payloadType, HttpMethod httpMethod, Parameters queryParameters,
            Parameters pathParameters, JaxrsTemplate template) {
        return new JaxrsResource(type, payloadType, httpMethod, queryParameters, pathParameters, template);
    }

    public JaxrsResource createFromAnnotatedMethod(String classPath, Method method) {

        Type type = Factory.create(Type.class).with(method);

        Type payloadType = JaxrsReflectionUtils.getPayloadParameterType(method);

        HttpMethod httpMethod = JaxrsReflectionUtils.getHttpMethod(method);

        Parameters queryParameters = JaxrsReflectionUtils.getQueryParameters(method);

        Parameters pathParameters = JaxrsReflectionUtils.getPathParameters(method);

        JaxrsTemplate template =
                Factory.create(JaxrsTemplate.class).with(
                        JaxrsReflectionUtils.concatPaths(classPath, JaxrsReflectionUtils.getMethodPath(method)));

        return new JaxrsResource(type, payloadType, httpMethod, queryParameters, pathParameters, template);

    }

    private Parameters  parseParameters(String paramsAsString) {
        String[] parsedParameters = paramsAsString.split(",");

        List<Parameter> parameters = new ArrayList<Parameter>();

        for (String paramAsString : parsedParameters) {
            parameters.add(Factory.create(Parameter.class).with(paramAsString));
        }

        return Factory.create(Parameters.class).with(parameters);
    }

}
