package org.entitatemindex.jaxrs.resource;

import org.entitatemindex.factory.Factory;
import org.entitatemindex.jaxrs.template.JaxrsTemplate;
import org.entitatemindex.type.Type;

public class JaxrsResource {

    private Type type;
    
    private Type payLoadType;
    
    private HttpMethod httpMethod;

    private Parameters queryParameters;

    private Parameters pathParameters;

    private JaxrsTemplate template;


   protected JaxrsResource(Type type, Type payloadType, HttpMethod httpMethod,
			Parameters queryParameters, Parameters pathParameters,
			JaxrsTemplate template) {
		this.type = type;
		this.payLoadType = payloadType;
		this.httpMethod = httpMethod;
		this.queryParameters = Factory.create(Parameters.class).with(queryParameters);
		this.pathParameters = Factory.create(Parameters.class).with(pathParameters);
		this.template = template;
	}

    
    public Type getType() {
        return type;
    }
    
    public Type getPayLoadType() {
        return payLoadType;
    }

    public JaxrsTemplate getTemplate() {
        return template;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public Parameters getQueryParameters() {
        return queryParameters;
    }

    public Parameters getPathParameters() {
        return pathParameters;
    }
    
    @Override
    public String toString() {
    	StringBuffer tmp = new StringBuffer();
    	
    	tmp.append(type);
    	tmp.append(" ");
        tmp.append(payLoadType);
        tmp.append(" ");
    	tmp.append(httpMethod);
    	tmp.append(" ");
    	tmp.append(template.toUri());
    	tmp.append(" path-params[");
    	boolean first = true;
    	for(Parameter param:pathParameters) {
    	    if (first) {
    	        first = false;
    	    }
    	    else {
    	        tmp.append(",");
    	    }
        	tmp.append(param);
    	}
    	tmp.append("]");
    	tmp.append(" query-params[");
        first = true;
    	for(Parameter param:queryParameters) {
            if (first) {
                first = false;
            }
            else {
                tmp.append(",");
            }
        	tmp.append(param);
    	}
    	tmp.append("]");
    	return tmp.toString();	
    }
    
    


}