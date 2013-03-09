package com.test.jaxrstemplate.tree;

import static org.junit.Assert.assertEquals;

import org.entitatemindex.utils.Node;
import org.junit.Test;

public class NodeTest {
    

    @Test
    public void testToString() {
        Node<String> root = new Node<String>("root");
        Node<String> child1 = new Node<String>("child1");
        Node<String> granChild1 = new Node<String>("granChild1");
        Node<String> child2 = new Node<String>("child2");
        root.addChild(child1);
        child1.addChild(granChild1);
        root.addChild(child2);
        
        
        StringBuffer expected = new StringBuffer();
        expected.append("root\n");
        expected.append(" child1\n");
        expected.append("  granChild1\n");
        expected.append(" child2\n");
        assertEquals(expected.toString(),root.toString());
    }

}
