package org.entitatemindex.type;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public enum CollectionType {

    LIST("LIST<%1$s>", true), SET("SET<%1$s>", true), NONE("%1$s", false);

    private String format;
    private boolean collection;

    private CollectionType(String format, boolean collection) {
        this.format = format;
        this.collection = collection;
    }


    public Class<?> getActualType(java.lang.reflect.Type type) {
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type[] typeArguments = parameterizedType.getActualTypeArguments();
            return (Class<?>) typeArguments[0];
        } else {
            return (Class<?>) type;
        }
    }
    
    public boolean isCollection() {
        return collection;
    }

    public String toString(Class<?> javaClass) {
        if (javaClass == null) {
            return "void";
        } else {
            return String.format(format, javaClass.getName());
        }
    }

}
