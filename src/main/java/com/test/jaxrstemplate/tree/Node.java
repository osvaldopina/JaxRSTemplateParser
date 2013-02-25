package com.test.jaxrstemplate.tree;

import java.util.Collections;
import java.util.List;

public class Node<T> {
    
    private T data;
     
    private Node<T> parent;
    
    private List<Node<T>> children;
    
    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public Node<T> getParent() {
        return parent;
    }

    public List<Node<T>> getChildren() {
        return Collections.unmodifiableList(children);
    }
    
    public void addChild(Node<T> child) {
        child.setParent(this);
        children.add(child);
    }

    private void setParent(Node<T> parent) {
        this.parent = parent;
    }
    
    @Override
    public String toString() {
        return toString(this, 0);
    }
    
    private String toString(Node<T> node, int level) {
        StringBuffer tmp = new StringBuffer();
        tmp.append(repeatCharacter('-', level));
        tmp.append(node.getData().toString());
        level ++;
        for (Node<T> child: getChildren()) {
            tmp.append('\n');
            tmp.append(toString(child,level));
        }
        return tmp.toString();
    }
    
    private String repeatCharacter(char character, int times) {
        StringBuffer tmp = new StringBuffer();
        for (int i = 0; i <= times ; i++) {
            tmp.append(character);
            tmp.append(character);
        }
        return tmp.toString();
    }
    
    

}
