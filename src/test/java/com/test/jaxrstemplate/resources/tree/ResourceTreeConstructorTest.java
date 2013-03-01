package com.test.jaxrstemplate.resources.tree;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.test.jaxrstemplate.discover.JaxrsResource;

public class ResourceTreeConstructorTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testSingleResource1Level() {
        List<JaxrsResource> invoiceExampleResources =
                createResourceList(
                        "void void GET /a path-params[] query-params[]"//
                );
        
        ResourceTree resourceTree = ResourceTreeConstructor.create(invoiceExampleResources);
        
        StringBuffer tmp = new StringBuffer();
        
        tmp.append("/\n");
        tmp.append("  a  [GET /a]\n");
        
        assertEquals(tmp.toString(), resourceTree.toString());
    }
    
    @Test
    public void singleResource3Level() {
        List<JaxrsResource> invoiceExampleResources =
                createResourceList(
                        "void void GET /a/{b}/c path-params[] query-params[]"//
                );
        
        ResourceTree resourceTree = ResourceTreeConstructor.create(invoiceExampleResources);
        
        StringBuffer tmp = new StringBuffer();
        
        tmp.append("/\n");
        tmp.append("  a\n");
        tmp.append("    {b}\n");
        tmp.append("      c  [GET /a/{b}/c]\n");
        
        assertEquals(tmp.toString(), resourceTree.toString());
    }
    
    @Test
    public void twoResources1Level() {
        List<JaxrsResource> invoiceExampleResources =
                createResourceList(
                        "void void GET /a path-params[] query-params[]",//
                        "void void PUT /a path-params[] query-params[]"//
                );
        
        ResourceTree resourceTree = ResourceTreeConstructor.create(invoiceExampleResources);
        
        StringBuffer tmp = new StringBuffer();
        
        tmp.append("/\n");
        tmp.append("  a  [GET /a, PUT /a]\n");
        
        assertEquals(tmp.toString(), resourceTree.toString());
    }
    
    @Test
    public void twoResources2Level() {
        List<JaxrsResource> invoiceExampleResources =
                createResourceList(
                        "void void GET /a/b path-params[] query-params[]",//
                        "void void PUT /a path-params[] query-params[]"//
                );
        
        ResourceTree resourceTree = ResourceTreeConstructor.create(invoiceExampleResources);
        
        StringBuffer tmp = new StringBuffer();
        
        tmp.append("/\n");
        tmp.append("  a  [PUT /a]\n");
        tmp.append("    b  [GET /a/b]\n");
        
        assertEquals(tmp.toString(), resourceTree.toString());
    }

    @Test
    public void threeResources5Level() {
        List<JaxrsResource> invoiceExampleResources =
                createResourceList(
                        "void void GET /a/b/c path-params[] query-params[]",//
                        "void void GET /a/c/d path-params[] query-params[]"//
                );
        
        ResourceTree resourceTree = ResourceTreeConstructor.create(invoiceExampleResources);
        
        StringBuffer tmp = new StringBuffer();
        
        tmp.append("/\n");
        tmp.append("  a\n");
        tmp.append("    b\n");
        tmp.append("      c  [GET /a/b/c]\n");
        tmp.append("    c\n");
        tmp.append("      d  [GET /a/c/d]\n");
        
        assertEquals(tmp.toString(), resourceTree.toString());
    }


    List<JaxrsResource> createResourceList(String... resourcesAsString) {
        List<JaxrsResource> resourceList = new ArrayList<JaxrsResource>();

        for (String resourceAsString : resourcesAsString) {
            resourceList.add(JaxrsResource.fromString(resourceAsString));
        }

        return resourceList;
    }


}
