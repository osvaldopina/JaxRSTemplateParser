package com.test.jaxrstemplate.discover;

import java.util.ArrayList;
import java.util.List;

import com.test.jaxrstemplate.JaxRsTemplate;
import com.test.type.Type;

public class JaxrsResource {

    private Type type;
    
    private String httpMethod;

    private List<Parameter> queryParameters = new ArrayList<Parameter>();

    private List<Parameter> pathParameters = new ArrayList<Parameter>();

    private JaxRsTemplate template;


    public JaxrsResource(Type type, String httpMethod,
			List<Parameter> queryParameters, List<Parameter> pathParameters,
			JaxRsTemplate template) {
		super();
		this.type = type;
		this.httpMethod = httpMethod;
		this.queryParameters = new ArrayList<Parameter>(queryParameters);
		this.pathParameters = new ArrayList<Parameter>(pathParameters);
		this.template = template;
	}

    
    public Type getType() {
        return type;
    }

    public JaxRsTemplate getTemplate() {
        return template;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public List<Parameter> getQueryParameters() {
        return queryParameters;
    }

    public List<Parameter> getPathParameters() {
        return pathParameters;
    }
    
    @Override
    public String toString() {
    	StringBuffer tmp = new StringBuffer();
    	
    	tmp.append(type);
    	tmp.append(" ");
    	tmp.append(httpMethod);
    	tmp.append(" ");
    	tmp.append(template.toUri());
    	tmp.append(" path-params[");
    	for(Parameter param:pathParameters) {
        	tmp.append(param);
    	}
    	tmp.append("]");
    	tmp.append(" query-params[");
    	for(Parameter param:queryParameters) {
        	tmp.append(param);
    	}
    	tmp.append("]");
    	return tmp.toString();	
    }
    
    
}