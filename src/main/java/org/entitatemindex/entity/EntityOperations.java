package org.entitatemindex.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.entitatemindex.jaxrs.resource.JaxrsResource;
import org.entitatemindex.utils.StringUtils;

public class EntityOperations {

    public static final EntityOperations NO_OPERATIONS = new NoOperations();

    private Map<Operation, List<JaxrsResource>> operations = new HashMap<Operation, List<JaxrsResource>>();

    private boolean empty = true;

    public EntityOperations() {
        for (Operation operataion : Operation.values()) {
            operations.put(operataion, new ArrayList<JaxrsResource>());
        }
    }

    public void addOperation(Operation operation, JaxrsResource resource) {
        List<JaxrsResource> resources = operations.get(operation);
        resources.add(resource);
        empty = false;
    }

    public boolean isEmpty() {
        return empty;
    }

    public List<JaxrsResource> getOperationResources(Operation operation) {
        return operations.get(operation);
    }

    @Override
    public String toString() {
        return toString("");
    }

    public String toString(String identation) {
        if (empty) {
            return "";
        } else {
            StringBuffer tmp = new StringBuffer();
            boolean firstOperation = true;
            for (Operation operation : Operation.values()) {
                List<JaxrsResource> resources = operations.get(operation);
                if (resources.size() > 0) {
                    firstOperation = StringUtils.addIfFalse(firstOperation, tmp, "\n");
                    boolean firstResource = true;
                    tmp.append(identation);
                    tmp.append(identation);
                    tmp.append(" Operation[");
                    tmp.append(operation);
                    tmp.append("]   ");
                    for (JaxrsResource resource : resources) {
                        firstResource = StringUtils.addIfFalse(firstResource, tmp, ",");
                        tmp.append(resource);
                    }
                }
            }
            return tmp.toString();
        }
    }

    public static class NoOperations extends EntityOperations {

        public List<JaxrsResource> getOperationResources(Operation operation) {
            return Collections.emptyList();
        }

        @Override
        public String toString() {
            return toString("");
        }

        public String toString(String identation) {
            return "";
        }
    }

}
