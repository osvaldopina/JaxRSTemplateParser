package com.test.jaxrstemplate.tree;


public class Tree<T> {

    private Node<T> root;

    public Tree(T rootData) {
        root = new Node<T>(rootData);
    }

    public Node<T> getRoot() {
        return root;
    }

    @Override
    public String toString() {
        return root.toString();
    }

}
