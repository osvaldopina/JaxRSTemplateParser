package org.entitatemindex.factory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Factory {
    
    private static List<Creator<?>> creators = new ArrayList<Creator<?>>();
    
    @SuppressWarnings({ "rawtypes", "unchecked"})
    public static void addCreator(Class<?> type,Object creator) {
        
        for(Method method:creator.getClass().getMethods()) {
            if (method.getName().startsWith("create")) {
                creators.add(new Creator(type,creator));
            }
        }
    }
        
    protected static void clear() {
        creators.clear();
    }
    
    protected static void addCreators(List<Creator<?>> creators) {
        Factory.creators.addAll(creators);
    }
    
    protected static List<Creator<?>> getCreators() {
        return Collections.unmodifiableList(creators);
    }
    
    
    @SuppressWarnings("unchecked")
    public static <T> Creator<T> create(Class<T> clazz) {
        for(Creator<?> creator:creators) {
            if (creator.isCreatorFor(clazz)) {
                return (Creator<T>) creator;
            }
        }
        throw new FactoryError("Could not find creator for " + clazz.getName());
    }

}
