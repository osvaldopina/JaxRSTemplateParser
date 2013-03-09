package com.test.jaxrstemplate.resources;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.entitatemindex.jaxrs.resource.JaxrsResource;
import org.entitatemindex.type.TypeFactory;
import org.junit.Before;
import org.junit.Test;


public class TreeConstructorTest {

    @Before
    public void setUp() {
        TypeFactory.addToPrimitives("org.test.Invoice", int.class);
        TypeFactory.addToPrimitives("org.test.Customer", String.class);
    }

    @Test
    public void createResourceTreeInvoiceExample() {
        List<JaxrsResource> invoiceExampleResources =
                createResourceList(
                        "LIST<org.test.Invoice> void GET /invoice path-params[] query-params[customer-name:java.lang.String]",
                        "org.test.Invoice void GET /invoice/{invoice-id} path-params[invoice-id:int] query-params[]",
                        "org.test.Invoice org.test.Invoice POST /invoice path-params[] query-params[]",
                        "void org.test.Invoice PUT /invoice/{invoice-id} path-params[invoice-id:int] query-params[]",//
                        "org.test.Customer void GET /invoice/{invoice-id}/customer path-params[invoice-id:int] query-params[]", //
                        //
                        "LIST<org.test.Customer> void GET /customer path-params[] query-params[name:java.lang.String]",//
                        "org.test.Invoice void GET /customer/{customer-id} path-params[customer-id:int] query-params[]",//
                        "org.test.Customer org.test.Customer POST /customer path-params[] query-params[]",//
                        "void org.test.Customer PUT /invoice/{invoice-id} path-params[invoice-id:int] query-params[]"//
                );

        StringBuffer expected = new StringBuffer();

        expected.append("Root[/]\n");
        expected.append("  Resource[invoice] \n"); 
        expected.append("    Operation[search]   LIST<org.test.Invoice> void GET /invoice path-params[] query-params[customer-name:java.lang.String]\n");
        expected.append("    Operation[read]   org.test.Invoice void GET /invoice/{invoice-id} path-params[invoice-id:int] query-params[]\n");
        expected.append("    Operation[create]   org.test.Invoice org.test.Invoice POST /invoice path-params[] query-params[]\n");
        expected.append("    Operation[update]   void org.test.Invoice PUT /invoice/{invoice-id} path-params[invoice-id:int] query-params[] }\n");
        expected.append("    Resource[Customer]\n");
        expected.append("      Operation[read]   org.test.Customer void GET /invoice/{invoice-id}/customer path-params[invoice-id:int] query-params[]\n"); //
        expected.append("  Resource[Customer] \n"); 
        expected.append("    Operation[search]   LIST<org.test.Customer> void GET /customer path-params[] query-params[name:java.lang.String]\n");
        expected.append("    Operation[read]   org.test.Invoice void GET /customer/{customer-id} path-params[customer-id:int] query-params[]\n");
        expected.append("    Operation[create]   org.test.Customer org.test.Customer POST /customer path-params[] query-params[]\n");
        expected.append("    Operation[update]   void org.test.Customer PUT /invoice/{invoice-id} path-params[invoice-id:int] query-params[]\n");

     //   assertEquals(expected.toString(), TreeConstructor.createResourceTree(invoiceExampleResources));

    }
    
    @Test
    public void createResourceTreeCustomerExample() {
        List<JaxrsResource> invoiceExampleResources =
                createResourceList(
                        "LIST<org.test.Customer> void GET /customer path-params[] query-params[name:java.lang.String]",//
                        "org.test.Invoice void GET /customer/{customer-id} path-params[customer-id:int] query-params[]",//
                        "org.test.Customer org.test.Customer POST /customer path-params[] query-params[]",//
                        "void org.test.Customer PUT /customer/{customer-id} path-params[customer-id:int] query-params[]"//
                );

        StringBuffer expected = new StringBuffer();

        expected.append("Root[/]\n");
        expected.append("  Resource[Customer]\n"); 
        expected.append("    Operation[search]   LIST<org.test.Customer> void GET /customer path-params[] query-params[name:java.lang.String]\n");
        expected.append("    Operation[read]   org.test.Invoice void GET /customer/{customer-id} path-params[customer-id:int] query-params[]\n");
        expected.append("    Operation[create]   org.test.Customer org.test.Customer POST /customer path-params[] query-params[]\n");
        expected.append("    Operation[update]   void org.test.Customer PUT /invoice/{invoice-id} path-params[invoice-id:int] query-params[]\n");

  //      assertEquals(expected.toString(), TreeConstructor.createResourceTree(invoiceExampleResources));

    }

    
    @Test
    public void createEmptyResourceTree() {
        List<JaxrsResource> invoiceExampleResources = new ArrayList<JaxrsResource>();


  //      assertEquals("Root[/]\n", TreeConstructor.createResourceTree(invoiceExampleResources).toString());

    }

    
    @Test
    public void getCommonTemplateStartNoCommonStart() {
        
        List<JaxrsResource> resources =
                createResourceList(
                        "void void GET /a path-params[] query-params[]",
                        "void void GET /b path-params[] query-params[]"
                );
        
        assertEquals("",TreeConstructor.getCommonTemplateStart(resources));
        
    }
    
    @Test
    public void getCommonTemplateStartWith2ResourcesCommonStart() {
        
        List<JaxrsResource> resources =
                createResourceList(
                        "void void GET /resource1/resource2/{param1}/resource3 path-params[param1:void] query-params[]",
                        "void void GET /resource1/resource2/{param1}/resource3 path-params[] query-params[]"
                );
        
        assertEquals("/resource1/resource2",TreeConstructor.getCommonTemplateStart(resources));
        
    }
     
    @Test
    public void getCommonTemplateStartWith3ResourcesCommonStart() {
        
        List<JaxrsResource> resources =
                createResourceList(
                        "void void GET /resource1/resource2/resource3/resource4/{param1}/resource3 path-params[param1:void] query-params[]",
                        "void void GET /resource1/resource2/resource3/{param1}/resource3 path-params[] query-params[]",
                        "void void GET /resource1/resource2/resource3/resource4/{param1}/resource3 path-params[] query-params[]",
                        "void void GET /resource1/resource2/resource3/resource4/{param1}/resource3 path-params[] query-params[]"
                );
        
        assertEquals("/resource1/resource2/resource3",TreeConstructor.getCommonTemplateStart(resources));
        
    }

    List<JaxrsResource> createResourceList(String... resourcesAsString) {
        List<JaxrsResource> resourceList = new ArrayList<JaxrsResource>();

        for (String resourceAsString : resourcesAsString) {
            resourceList.add(JaxrsResource.createFromString(resourceAsString));
        }

        return resourceList;
    }
}
