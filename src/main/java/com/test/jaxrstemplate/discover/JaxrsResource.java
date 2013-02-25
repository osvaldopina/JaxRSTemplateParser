package com.test.jaxrstemplate.discover;

import java.util.ArrayList;
import java.util.List;

import com.test.jaxrstemplate.JaxRsTemplate;
import com.test.type.Type;
import com.test.type.TypeFactory;

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
    
    public static JaxrsResource fromString(String jaxrsResourceAsString) {
        String[] parsedJaxrsResource = jaxrsResourceAsString.split(" ");
        
        Type type = TypeFactory.createFromString(parsedJaxrsResource[0]);
        String httpMethod = parsedJaxrsResource[1];
        
        JaxRsTemplate template = JaxRsTemplate.parse(parsedJaxrsResource[2]);
        
        List<Parameter> pathParameters = new ArrayList<Parameter>();
        if (!jaxrsResourceAsString.contains("path-params[]" )) {
            pathParameters.addAll(parseParameters(parsedJaxrsResource[3].substring(12,parsedJaxrsResource[3].length()-1)));
        }
        
        List<Parameter> queryParameters = new ArrayList<Parameter>();
        if (!jaxrsResourceAsString.contains("query-params[]" )) {
            queryParameters.addAll(parseParameters(parsedJaxrsResource[4].substring(13,parsedJaxrsResource[4].length()-1)));
        }
        
        
        return new JaxrsResource(type, httpMethod, queryParameters, pathParameters, template);
    }


    private static List<Parameter> parseParameters(String paramsAsString) {
        String[] parsedParameters = paramsAsString.split(",");
        
        List<Parameter> parameters = new ArrayList<Parameter>();
        
        for(String paramAsString:parsedParameters) {
          parameters.add(Parameter.fromString(paramAsString));    
        }
        
        return parameters;
    }
    
    
}