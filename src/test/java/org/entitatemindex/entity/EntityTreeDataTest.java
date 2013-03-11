package org.entitatemindex.entity;

import static org.junit.Assert.assertEquals;

import org.entitatemindex.factory.Factory;
import org.entitatemindex.jaxrs.resource.JaxrsResource;
import org.entitatemindex.jaxrs.resource.JaxrsResourceFactory;
import org.entitatemindex.jaxrs.resource.Parameter;
import org.entitatemindex.jaxrs.resource.ParameterFactory;
import org.entitatemindex.jaxrs.resource.Parameters;
import org.entitatemindex.jaxrs.resource.ParametersFactory;
import org.entitatemindex.jaxrs.template.JaxrsTemplate;
import org.entitatemindex.jaxrs.template.JaxrsTemplateFactory;
import org.entitatemindex.type.Type;
import org.entitatemindex.type.TypeFactory;
import org.entitatemindex.uri.GenericUri;
import org.entitatemindex.uri.GenericUriFactory;
import org.entitatemindex.utils.Node;
import org.junit.Before;
import org.junit.Test;

public class EntityTreeDataTest {
    
    @Before
    public void setUp() {
        Factory.addCreator(JaxrsResource.class,new JaxrsResourceFactory());
        Factory.addCreator(JaxrsTemplate.class,new JaxrsTemplateFactory());
        Factory.addCreator(GenericUri.class,new GenericUriFactory());
        Factory.addCreator(Type.class,new TypeFactory());
        Factory.addCreator(Parameters.class,new ParametersFactory());
        Factory.addCreator(Parameter.class,new ParameterFactory());
    }

    @Test
    public void testToString() {

        EntityTreeData data = new EntityTreeData(Factory.create(GenericUri.class).with("/invoice"));
        data.addOperation(
                Operation.SEARCH,
                Factory.create(JaxrsResource.class)
                        .with("LIST<java.lang.String> void GET /invoice path-params[] query-params[customer-name:java.lang.String]"));
        
        data.addOperation(
                Operation.READ,
                Factory.create(JaxrsResource.class).with(
                        "java.lang.String void GET /invoice/{invoice-id} path-params[invoice-id:int] query-params[]"));
        
        data.addOperation(
                Operation.CREATE,
                Factory.create(JaxrsResource.class).with(
                        "java.lang.String java.lang.String POST /invoice path-params[] query-params[]"));
        
        data.addOperation(
                Operation.UPDATE,
                Factory.create(JaxrsResource.class).with(
                        "void java.lang.String PUT /invoice/{invoice-id} path-params[invoice-id:int] query-params[]"));

        Node<EntityTreeData> node = new Node<EntityTreeData>(data);
        
        EntityTree entityTree = new EntityTree(node);

        StringBuffer expected = new StringBuffer();

        expected.append("Entity[invoice]\n");
        expected.append("  Operation[SEARCH]   LIST<java.lang.String> void GET /invoice path-params[] query-params[customer-name:java.lang.String]\n");
        expected.append("  Operation[READ]   java.lang.String void GET /invoice/{invoice-id} path-params[invoice-id:int] query-params[]\n");
        expected.append("  Operation[CREATE]   java.lang.String java.lang.String POST /invoice path-params[] query-params[]\n");
        expected.append("  Operation[UPDATE]   void java.lang.String PUT /invoice/{invoice-id} path-params[invoice-id:int] query-params[]\n");

        assertEquals(expected.toString(), entityTree.toString());

    }
   
}
