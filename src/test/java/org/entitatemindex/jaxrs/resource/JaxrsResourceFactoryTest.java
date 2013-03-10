package org.entitatemindex.jaxrs.resource;

import static org.junit.Assert.assertEquals;

import org.entitatemindex.factory.Factory;
import org.entitatemindex.jaxrs.template.JaxrsTemplate;
import org.entitatemindex.jaxrs.template.JaxrsTemplateFactory;
import org.entitatemindex.type.Type;
import org.entitatemindex.type.TypeFactory;
import org.entitatemindex.uri.GenericUri;
import org.entitatemindex.uri.GenericUriFactory;
import org.junit.Before;
import org.junit.Test;


public class JaxrsResourceFactoryTest {
    
    private JaxrsResourceFactory jaxrsResourceFactory;
    
    @Before
    public void setUp() {
        jaxrsResourceFactory = new JaxrsResourceFactory();
        Factory.addCreator(Type.class, new TypeFactory());
        Factory.addCreator(JaxrsTemplate.class, new JaxrsTemplateFactory());
        Factory.addCreator(Parameters.class, new ParametersFactory());
        Factory.addCreator(Parameter.class, new ParameterFactory());
        Factory.addCreator(GenericUri.class, new GenericUriFactory());
    }

 
    @Test
    public void getResourcesFromString() {

        String resource1 = "java.lang.String void GET /service/name/{id} path-params[id:int] query-params[]";
        String resource2 =
                "LIST<java.lang.String> void GET /service/name path-params[] query-params[partial-name:java.lang.String]";
        String resource3 = "void java.lang.String POST /service/name path-params[] query-params[]";
        String resource4 = "void java.lang.String PUT /service/name/{id} path-params[id:int] query-params[]";

        assertEquals(jaxrsResourceFactory.createFromString(resource1).toString(), resource1);
        assertEquals(jaxrsResourceFactory.createFromString(resource2).toString(), resource2);
        assertEquals(jaxrsResourceFactory.createFromString(resource3).toString(), resource3);
        assertEquals(jaxrsResourceFactory.createFromString(resource4).toString(), resource4);
    }

}
