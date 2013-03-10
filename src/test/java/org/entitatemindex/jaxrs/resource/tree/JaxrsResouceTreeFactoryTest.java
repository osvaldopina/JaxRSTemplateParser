package org.entitatemindex.jaxrs.resource.tree;

import static org.junit.Assert.assertEquals;

import org.entitatemindex.factory.Factory;
import org.entitatemindex.jaxrs.resource.JaxrsResource;
import org.entitatemindex.jaxrs.resource.JaxrsResourceFactory;
import org.entitatemindex.jaxrs.resource.JaxrsResources;
import org.entitatemindex.jaxrs.resource.JaxrsResourcesFactory;
import org.entitatemindex.jaxrs.resource.Parameters;
import org.entitatemindex.jaxrs.resource.ParametersFactory;
import org.entitatemindex.jaxrs.template.JaxrsTemplate;
import org.entitatemindex.jaxrs.template.JaxrsTemplateFactory;
import org.entitatemindex.type.Type;
import org.entitatemindex.type.TypeFactory;
import org.entitatemindex.uri.GenericUri;
import org.entitatemindex.uri.GenericUriFactory;
import org.junit.Before;
import org.junit.Test;

public class JaxrsResouceTreeFactoryTest {

    private JaxrsResouceTreeFactory jaxrsResouceTreeFactory;
    private JaxrsResourcesFactory jaxrsResourcesFactory;
    private JaxrsResources jaxrsResources;

    @Before
    public void setUp() throws Exception {
        jaxrsResouceTreeFactory = new JaxrsResouceTreeFactory();
        jaxrsResourcesFactory = new JaxrsResourcesFactory();
        
        Factory.addCreator(JaxrsResource.class, new JaxrsResourceFactory());
        Factory.addCreator(Type.class, new TypeFactory());
        Factory.addCreator(JaxrsTemplate.class, new JaxrsTemplateFactory());
        Factory.addCreator(Parameters.class, new ParametersFactory());
        Factory.addCreator(GenericUri.class, new GenericUriFactory());
    }

    @Test
    public void testSingleResource1Level() {
        jaxrsResources =
                jaxrsResourcesFactory
                        .createJaxrsResourcesFromResourcesString(new String[] {"void void GET /a path-params[] query-params[]"});

        JaxrsResourceTree resourceTree = jaxrsResouceTreeFactory.createFromJaxrsResources(jaxrsResources);

        StringBuffer tmp = new StringBuffer();

        tmp.append("/\n");
        tmp.append(" a  [GET /a]\n");

        assertEquals(tmp.toString(), resourceTree.toString());
    }

    @Test
    public void singleResource3Level() {
        jaxrsResources = //
                jaxrsResourcesFactory //
                        .createJaxrsResourcesFromResourcesString(new String[] { //
                        "void void GET /a/{b}/c path-params[] query-params[]"});

        JaxrsResourceTree resourceTree = jaxrsResouceTreeFactory.createFromJaxrsResources(jaxrsResources);

        StringBuffer tmp = new StringBuffer();

        tmp.append("/\n");
        tmp.append(" a\n");
        tmp.append("  {b}\n");
        tmp.append("   c  [GET /a/{b}/c]\n");

        assertEquals(tmp.toString(), resourceTree.toString());
    }

    @Test
    public void twoResources1Level() {
        jaxrsResources =
                jaxrsResourcesFactory.createJaxrsResourcesFromResourcesString(new String[] {
                        "void void GET /a path-params[] query-params[]",//
                        "void void PUT /a path-params[] query-params[]"});

        JaxrsResourceTree resourceTree = jaxrsResouceTreeFactory.createFromJaxrsResources(jaxrsResources);

        StringBuffer tmp = new StringBuffer();

        tmp.append("/\n");
        tmp.append(" a  [GET /a, PUT /a]\n");

        assertEquals(tmp.toString(), resourceTree.toString());
    }

    @Test
    public void twoResources2Level() {
        jaxrsResources =
                jaxrsResourcesFactory.createJaxrsResourcesFromResourcesString(new String[] {
                        "void void GET /a/b path-params[] query-params[]",//
                        "void void PUT /a path-params[] query-params[]"});

        JaxrsResourceTree resourceTree = jaxrsResouceTreeFactory.createFromJaxrsResources(jaxrsResources);

        StringBuffer tmp = new StringBuffer();

        tmp.append("/\n");
        tmp.append(" a  [PUT /a]\n");
        tmp.append("  b  [GET /a/b]\n");

        assertEquals(tmp.toString(), resourceTree.toString());
    }

    @Test
    public void threeResources5Level() {
        jaxrsResources =
                jaxrsResourcesFactory.createJaxrsResourcesFromResourcesString(new String[] {
                        "void void GET /a/b/c path-params[] query-params[]",//
                        "void void GET /a/c/d path-params[] query-params[]"});


        JaxrsResourceTree resourceTree = jaxrsResouceTreeFactory.createFromJaxrsResources(jaxrsResources);

        StringBuffer tmp = new StringBuffer();

        tmp.append("/\n");
        tmp.append(" a\n");
        tmp.append("  b\n");
        tmp.append("   c  [GET /a/b/c]\n");
        tmp.append("  c\n");
        tmp.append("   d  [GET /a/c/d]\n");

        assertEquals(tmp.toString(), resourceTree.toString());
    }

}
