package org.entitatemindex.entity;

import static org.junit.Assert.assertEquals;

import org.entitatemindex.entity.Operation;
import org.entitatemindex.entity.EntityTreeData;
import org.entitatemindex.jaxrs.resource.JaxrsResource;
import org.entitatemindex.jaxrs.template.Literal;
import org.junit.Test;

import com.test.jaxrstemplate.resources.ResourceNode;
import com.test.jaxrstemplate.resources.ResourceTree;


public class ResourceTreeTest {

    @Test
    public void toStringSingleNode() {
        
        
        EntityTreeData data = new EntityTreeData(new Literal("invoice"));
        data.addOperation(Operation.SEARCH,JaxrsResource.createFromString("LIST<java.lang.String> void GET /invoice path-params[] query-params[customer-name:java.lang.String]"));
        data.addOperation(Operation.READ,JaxrsResource.createFromString("java.lang.String void GET /invoice/{invoice-id} path-params[invoice-id:int] query-params[]"));
        data.addOperation(Operation.CREATE,JaxrsResource.createFromString("java.lang.String java.lang.String POST /invoice path-params[] query-params[]"));
        data.addOperation(Operation.UPDATE,JaxrsResource.createFromString("void java.lang.String PUT /invoice/{invoice-id} path-params[invoice-id:int] query-params[]"));
        
        ResourceNode node = new ResourceNode(data);
        
        ResourceTree resourceTree = new ResourceTree("/");
        resourceTree.getRoot().addChild(node);
        
        
        
        StringBuffer expected = new StringBuffer();
        
        expected.append("Root[/]\n"); 
        expected.append(" Resource[invoice]\n"); 
        expected.append("   Operation[SEARCH]   LIST<java.lang.String> void GET /invoice path-params[] query-params[customer-name:java.lang.String]\n");
        expected.append("   Operation[READ]   java.lang.String void GET /invoice/{invoice-id} path-params[invoice-id:int] query-params[]\n");
        expected.append("   Operation[CREATE]   java.lang.String java.lang.String POST /invoice path-params[] query-params[]\n");
        expected.append("   Operation[UPDATE]   void java.lang.String PUT /invoice/{invoice-id} path-params[invoice-id:int] query-params[]\n");

        assertEquals(expected.toString(), resourceTree.toString());
        
    }
    
    @Test
    public void toString2Nodes() {
        
        EntityTreeData dataParent = new EntityTreeData(new Literal("invoice"));
        dataParent.addOperation(Operation.SEARCH,JaxrsResource.createFromString("LIST<java.lang.String> void GET /invoice path-params[] query-params[customer-name:java.lang.String]"));
        dataParent.addOperation(Operation.READ,JaxrsResource.createFromString("java.lang.String void GET /invoice/{invoice-id} path-params[invoice-id:int] query-params[]"));
        dataParent.addOperation(Operation.CREATE,JaxrsResource.createFromString("java.lang.String java.lang.String POST /invoice path-params[] query-params[]"));
        dataParent.addOperation(Operation.UPDATE,JaxrsResource.createFromString("void java.lang.String PUT /invoice/{invoice-id} path-params[invoice-id:int] query-params[]"));
        
        ResourceNode nodeParent = new ResourceNode(dataParent);
  
        ResourceTree resourceTree = new ResourceTree("/");
        resourceTree.getRoot().addChild(nodeParent);
        
        
        EntityTreeData dataChild = new EntityTreeData(new Literal("customer"));
        dataChild.addOperation(Operation.READ,JaxrsResource.createFromString("java.lang.String void GET /invoice/{invoice-id} path-params[invoice-id:int] query-params[]"));
        
        ResourceNode nodeChild = new ResourceNode(dataChild);
        nodeParent.addChild(nodeChild);
        
        
        StringBuffer expected = new StringBuffer();
        
        expected.append("Root[/]\n"); 
        expected.append(" Resource[invoice]\n"); 
        expected.append("   Operation[SEARCH]   LIST<java.lang.String> void GET /invoice path-params[] query-params[customer-name:java.lang.String]\n");
        expected.append("   Operation[READ]   java.lang.String void GET /invoice/{invoice-id} path-params[invoice-id:int] query-params[]\n");
        expected.append("   Operation[CREATE]   java.lang.String java.lang.String POST /invoice path-params[] query-params[]\n");
        expected.append("   Operation[UPDATE]   void java.lang.String PUT /invoice/{invoice-id} path-params[invoice-id:int] query-params[]\n");
        expected.append("  Resource[customer]\n"); 
        expected.append("    Operation[READ]   java.lang.String void GET /invoice/{invoice-id} path-params[invoice-id:int] query-params[]\n");

        assertEquals(expected.toString(), resourceTree.toString());
        
    }

}
