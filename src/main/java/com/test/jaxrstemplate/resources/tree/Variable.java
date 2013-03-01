package com.test.jaxrstemplate.resources.tree;

public class Variable extends ResourcePart {

    public Variable(String name) {
        super(name);
    }
    
    @Override
    public String toString() {
        return "{" + super.toString() + "}";
    }

}
