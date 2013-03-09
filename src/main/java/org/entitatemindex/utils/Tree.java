package org.entitatemindex.utils;

public class Tree<T> {

    private Node<T> root;

    public Tree(Node<T> root) {
        this.root = root;
    }

    public Node<T> getRoot() {
        return root;
    }

    @Override
    public String toString() {
        return toString(root);
    }

    private String toString(Node<T> node) {
        StringBuffer tmp = new StringBuffer();
        tmp.append(node.toString());
        tmp.append("\n");
        for (Node<T> child : node.getChildren()) {
            tmp.append(toString(child));
        }
        return tmp.toString();
    }

}
