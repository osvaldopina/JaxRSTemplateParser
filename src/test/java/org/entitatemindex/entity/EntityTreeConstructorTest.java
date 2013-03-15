package org.entitatemindex.entity;

import static org.junit.Assert.assertEquals;

import org.entitatemindex.factory.Factory;
import org.entitatemindex.jaxrs.resource.JaxrsResource;
import org.entitatemindex.jaxrs.resource.JaxrsResourceFactory;
import org.entitatemindex.jaxrs.resource.JaxrsResources;
import org.entitatemindex.jaxrs.resource.JaxrsResourcesFactory;
import org.entitatemindex.jaxrs.resource.Parameter;
import org.entitatemindex.jaxrs.resource.ParameterFactory;
import org.entitatemindex.jaxrs.resource.Parameters;
import org.entitatemindex.jaxrs.resource.ParametersFactory;
import org.entitatemindex.jaxrs.resource.tree.JaxrsResourceTree;
import org.entitatemindex.jaxrs.resource.tree.JaxrsResourceTreeFactory;
import org.entitatemindex.jaxrs.template.JaxrsTemplate;
import org.entitatemindex.jaxrs.template.JaxrsTemplateFactory;
import org.entitatemindex.type.Type;
import org.entitatemindex.type.TypeFactory;
import org.entitatemindex.uri.GenericUri;
import org.entitatemindex.uri.GenericUriFactory;
import org.junit.Before;
import org.junit.Test;

public class EntityTreeConstructorTest {

    private EntityTreeFactory entityTreeFactory;

    @Before
    public void setUp() {
        entityTreeFactory = new EntityTreeFactory();
        Factory.addCreator(JaxrsResources.class, new JaxrsResourcesFactory());
        Factory.addCreator(JaxrsResource.class, new JaxrsResourceFactory());
        Factory.addCreator(Type.class, new TypeFactory());
        Factory.addCreator(JaxrsTemplate.class, new JaxrsTemplateFactory());
        Factory.addCreator(Parameters.class, new ParametersFactory());
        Factory.addCreator(Parameter.class, new ParameterFactory());
        Factory.addCreator(JaxrsResourceTree.class, new JaxrsResourceTreeFactory());
        Factory.addCreator(GenericUri.class, new GenericUriFactory());
    }

    @Test
    public void createX() {
        JaxrsResources jaxrsResources =
                Factory.create(JaxrsResources.class)
                        .with("LIST<java.lang.String> void GET /a/invoice path-params[] query-params[customer-name:java.lang.String]");

        JaxrsResourceTree resourceTree = Factory.create(JaxrsResourceTree.class).with(jaxrsResources);
        EntityTree entityTree = entityTreeFactory.create(resourceTree);

        StringBuffer expected = new StringBuffer();

        expected.append("Entity[/]\n");
        expected.append("  Entity[a]\n");
        expected.append("    Entity[invoice] (LIST<java.lang.String>)\n");
        expected.append("     Operation[SEARCH]   LIST<java.lang.String> void GET /a/invoice path-params[] query-params[customer-name:java.lang.String]\n");

        assertEquals(expected.toString(), entityTree.toString());

    }

    @Test
    public void createX1() {
        JaxrsResources jaxrsResources =
                Factory.create(JaxrsResources.class)
                        .with("java.lang.String void GET /a/invoice/{num-invoice} path-params[] query-params[customer-name:java.lang.String]",
                                "void void DELETE /a/invoice/{num-invoice} path-params[] query-params[]",
                                "void java.lang.String PUT /a/invoice/{num-invoice} path-params[] query-params[]");

        JaxrsResourceTree resourceTree = Factory.create(JaxrsResourceTree.class).with(jaxrsResources);
        EntityTree entityTree = entityTreeFactory.create(resourceTree);

        StringBuffer expected = new StringBuffer();

        expected.append("Entity[/]\n");
        expected.append("  Entity[a]\n");
        expected.append("    Entity[invoice] (java.lang.String)\n");
        expected.append("     Operation[READ]   java.lang.String void GET /a/invoice/{num-invoice} path-params[] query-params[customer-name:java.lang.String]\n");
        expected.append("     Operation[UPDATE]   void java.lang.String PUT /a/invoice/{num-invoice} path-params[] query-params[]\n");
        expected.append("     Operation[DELETE]   void void DELETE /a/invoice/{num-invoice} path-params[] query-params[]\n");

        assertEquals(expected.toString(), entityTree.toString());

    }

    /*
     * @Test public void createResourceTreeInvoiceExample() { JaxrsResources jaxrsResources =
     * Factory.create(JaxrsResources.class) .with(
     * "LIST<org.test.Invoice> void GET /invoice path-params[] query-params[customer-name:java.lang.String]"
     * ,
     * "org.test.Invoice void GET /invoice/{invoice-id} path-params[invoice-id:int] query-params[]"
     * , "org.test.Invoice org.test.Invoice POST /invoice path-params[] query-params[]",
     * "void org.test.Invoice PUT /invoice/{invoice-id} path-params[invoice-id:int] query-params[]"
     * ,//
     * "org.test.Customer void GET /invoice/{invoice-id}/customer path-params[invoice-id:int] query-params[]"
     * , // //
     * "LIST<org.test.Customer> void GET /customer path-params[] query-params[name:java.lang.String]"
     * ,//
     * "org.test.Invoice void GET /customer/{customer-id} path-params[customer-id:int] query-params[]"
     * ,// "org.test.Customer org.test.Customer POST /customer path-params[] query-params[]",//
     * "void org.test.Customer PUT /invoice/{invoice-id} path-params[invoice-id:int] query-params[]"
     * // );
     * 
     * JaxrsResourceTree resourceTree =
     * Factory.create(JaxrsResourceTree.class).with(jaxrsResources); EntityTree entityTree =
     * entityTreeFactory.create(resourceTree);
     * 
     * StringBuffer expected = new StringBuffer();
     * 
     * expected.append("Entity[/]\n"); expected.append("  Entity[invoice] \n"); expected.append(
     * "    Operation[search]   LIST<org.test.Invoice> void GET /invoice path-params[] query-params[customer-name:java.lang.String]\n"
     * ); expected.append(
     * "    Operation[read]   org.test.Invoice void GET /invoice/{invoice-id} path-params[invoice-id:int] query-params[]\n"
     * ); expected.append(
     * "    Operation[create]   org.test.Invoice org.test.Invoice POST /invoice path-params[] query-params[]\n"
     * ); expected.append(
     * "    Operation[update]   void org.test.Invoice PUT /invoice/{invoice-id} path-params[invoice-id:int] query-params[] }\n"
     * ); expected.append("    Resource[Customer]\n"); expected.append(
     * "      Operation[read]   org.test.Customer void GET /invoice/{invoice-id}/customer path-params[invoice-id:int] query-params[]\n"
     * ); // expected.append("  Entity[Customer] \n"); expected.append(
     * "    Operation[search]   LIST<org.test.Customer> void GET /customer path-params[] query-params[name:java.lang.String]\n"
     * ); expected.append(
     * "    Operation[read]   org.test.Invoice void GET /customer/{customer-id} path-params[customer-id:int] query-params[]\n"
     * ); expected.append(
     * "    Operation[create]   org.test.Customer org.test.Customer POST /customer path-params[] query-params[]\n"
     * ); expected.append(
     * "    Operation[update]   void org.test.Customer PUT /invoice/{invoice-id} path-params[invoice-id:int] query-params[]\n"
     * );
     * 
     * assertEquals(expected.toString(), entityTree.toString());
     * 
     * }
     * 
     * @Test public void createResourceTreeCustomerExample() { JaxrsResources jaxrsResources =
     * Factory.create(JaxrsResources.class) .with(
     * "LIST<org.test.Customer> void GET /customer path-params[] query-params[name:java.lang.String]"
     * ,//
     * "org.test.Invoice void GET /customer/{customer-id} path-params[customer-id:int] query-params[]"
     * ,// "org.test.Customer org.test.Customer POST /customer path-params[] query-params[]",//
     * "void org.test.Customer PUT /customer/{customer-id} path-params[customer-id:int] query-params[]"
     * // );
     * 
     * JaxrsResourceTree resourceTree =
     * Factory.create(JaxrsResourceTree.class).with(jaxrsResources); EntityTree entityTree =
     * entityTreeFactory.create(resourceTree);
     * 
     * StringBuffer expected = new StringBuffer();
     * 
     * expected.append("Entity[/]\n"); expected.append("  Entity[Customer]\n"); expected.append(
     * "    Operation[search]   LIST<org.test.Customer> void GET /customer path-params[] query-params[name:java.lang.String]\n"
     * ); expected.append(
     * "    Operation[read]   org.test.Invoice void GET /customer/{customer-id} path-params[customer-id:int] query-params[]\n"
     * ); expected.append(
     * "    Operation[create]   org.test.Customer org.test.Customer POST /customer path-params[] query-params[]\n"
     * ); expected.append(
     * "    Operation[update]   void org.test.Customer PUT /invoice/{invoice-id} path-params[invoice-id:int] query-params[]\n"
     * );
     * 
     * assertEquals(expected.toString(), entityTree.toString());
     * 
     * }
     */
}
