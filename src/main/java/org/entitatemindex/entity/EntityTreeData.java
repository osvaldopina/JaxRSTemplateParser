package org.entitatemindex.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.entitatemindex.jaxrs.resource.JaxrsResource;
import org.entitatemindex.uri.GenericUri;
import org.entitatemindex.utils.NodeData;
import org.entitatemindex.utils.StringUtils;


public class EntityTreeData implements NodeData{

    private GenericUri uri;

    private Map<Operation, List<JaxrsResource>> operations = new HashMap<Operation, List<JaxrsResource>>();

    public EntityTreeData(GenericUri uri) {
        this.uri = uri;
        for (Operation operataion : Operation.values()) {
            operations.put(operataion, new ArrayList<JaxrsResource>());
        }
    }

    public void addOperation(Operation operation, JaxrsResource resource) {
        List<JaxrsResource> resources = operations.get(operation);
        resources.add(resource);
    }

    public List<JaxrsResource> getOperationResources(Operation operation) {
        return operations.get(operation);
    }

    @Override
    public String toString() {
        return toString("");
    }
    
    
    @Override
    public String toString(String identation) {
        StringBuffer tmp = new StringBuffer();

        tmp.append("Entity[");
        tmp.append(uri.getLastResource());
        tmp.append("]\n");
        boolean firstOperation = true;
        for (Operation operation : Operation.values()) {
            firstOperation = StringUtils.addIfFalse(firstOperation, tmp, "\n");
            List<JaxrsResource> resources = operations.get(operation);
            boolean firstResource = true;
            tmp.append("  Operation[");
            for (JaxrsResource resource : resources) {
                firstResource = StringUtils.addIfFalse(firstResource, tmp, ",");
                tmp.append(operation.toString());
                tmp.append("]   ");
                tmp.append(resource);
            }
        }
        return tmp.toString();
    }

}
