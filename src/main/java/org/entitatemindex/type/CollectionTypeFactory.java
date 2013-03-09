package org.entitatemindex.type;

import java.util.List;
import java.util.Set;

public class CollectionTypeFactory {

    public CollectionType createfromClass(Class<?> clazz) {

        if (List.class.isAssignableFrom(clazz)) {
            return CollectionType.LIST;
        } else if (Set.class.isAssignableFrom(clazz)) {
            return CollectionType.SET;
        } else {
            return CollectionType.NONE;
        }
    }

}
