package org.entitatemindex.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Node<T> {
    
    private T data;
    
    private Node<T> parent;
    
    private List<Node<T>> children = new ArrayList<Node<T>>();
    
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
    
    public boolean hasChildSameData(Node<T> childToGet) {
    	return getChildSameData(childToGet) != null;
    }
    
    public Node<T> getChildSameData(Node<T> childToGet) {
        for (Node<T> child:children) {
            if (child.sameData(childToGet)) {
                return child;
            }
        }
        return null;
    	
    }
    
    public Node<T> addChild(Node<T> child) {
        child.setParent(this);
        children.add(child);
        return child;
    }

    private void setParent(Node<T> parent) {
        this.parent = parent;
    }
    
    @Override
    public String toString() {
        return getIdentation() + getData().toString();
    }
    
    public int getLevel() {
        int i=0;
        Node<T> node = this;
        while(node.getParent() != null) {
            i ++;
            node = node.getParent();
        }
        return i;
    }
    
    public Node<T> getRoot() {
        Node<T> node = this;
        
        while(node.getParent() != null) {
            node = node.getParent();
        }
        
        return node;
    }

    public String getIdentation() {
        StringBuffer tmp = new StringBuffer();
        for (int i = 0; i < getLevel() ; i++) {
            tmp.append(" ");
        }
        return tmp.toString();
    }
    
    public boolean sameData(Node<T> other) {
    	return this.getData().equals(other.getData());
    }

}
