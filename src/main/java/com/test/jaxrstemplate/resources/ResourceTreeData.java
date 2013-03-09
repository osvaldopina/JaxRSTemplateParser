package com.test.jaxrstemplate.resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.entitatemindex.jaxrs.resource.JaxrsResource;
import org.entitatemindex.jaxrs.template.TemplatePart;
import org.entitatemindex.utils.Node;


public class ResourceTreeData {

    private Node<ResourceTreeData> node;

    private TemplatePart templatePart;

    private Map<Operation, List<JaxrsResource>> operations = new HashMap<Operation, List<JaxrsResource>>();

    public ResourceTreeData(TemplatePart templatePart) {
        this.templatePart = templatePart;
        for (Operation operataion : Operation.values()) {
            operations.put(operataion, new ArrayList<JaxrsResource>());
        }
    }

    private String getFullLiteralPath() {
        Node<ResourceTreeData> node = getNode();
        List<String> literalParts = new ArrayList<String>();
        while (node.getParent() != null) {
            literalParts.add(node.getData().getTemplatePart().toString());
        }
        StringBuffer tmp = new StringBuffer();
        Collections.reverse(literalParts);
        boolean first = true;
        for (String literalPart : literalParts) {
            if (first) {
                first = false;
            } else {
                tmp.append("/");
            }
            tmp.append(literalPart);
        }
        return tmp.toString();
    }

    public Node<ResourceTreeData> getNode() {
        return node;
    }

    public TemplatePart getTemplatePart() {
        return templatePart;
    }

    public void setTemplatePart(TemplatePart templatePart) {
        this.templatePart = templatePart;
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
        String identation = getNode().getIdentation();
        StringBuffer tmp = new StringBuffer();

        tmp.append("Resource[" + getTemplatePart().toUri() + "]");
        for (Operation operation : Operation.values()) {
            List<JaxrsResource> resources = operations.get(operation);
            boolean first = true;
            for (JaxrsResource resource : resources) {
                //                if (first) {
                //                    first = false;
                //                } else {
                //                    tmp.append("\n");
                //                }
                tmp.append("\n");
                tmp.append(identation);
                tmp.append("  Operation[");
                tmp.append(operation.toString());
                tmp.append("]   ");
                tmp.append(resource);
            }
        }
        return tmp.toString();
    }

    protected void setNode(ResourceNode node) {
        this.node = node;
    }

}
