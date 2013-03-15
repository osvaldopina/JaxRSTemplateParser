package org.entitatemindex.entity;

import org.entitatemindex.factory.Factory;
import org.entitatemindex.jaxrs.resource.HttpMethod;
import org.entitatemindex.jaxrs.resource.JaxrsResource;
import org.entitatemindex.jaxrs.resource.tree.JaxrsResourceNodeData;
import org.entitatemindex.jaxrs.resource.tree.JaxrsResourceTree;
import org.entitatemindex.uri.GenericUri;
import org.entitatemindex.utils.Node;

public class EntityTreeFactory {

    public EntityTree create(JaxrsResourceTree jaxrsResourceTree) {
        EntityTree entityTree =
                new EntityTree(new Node<EntityNodeData>(new EntityNodeData(Factory.create(GenericUri.class).with("/"))));
        walkJaxrsNoResourceTree(jaxrsResourceTree.getRoot(), entityTree.getRoot());
        return entityTree;
    }

    private void walkJaxrsNoResourceTree(Node<JaxrsResourceNodeData> jaxrsNode, Node<EntityNodeData> entityNode) {
        for (Node<JaxrsResourceNodeData> jaxrsChildNode : jaxrsNode.getChildren()) {
            // there is no resource in jaxrsNode
            if (jaxrsChildNode.getData().getJaxrsResources().isEmpty()) {
                if (!jaxrsChildNode.getData().getUriResource().isVariable()) {
                    Node<EntityNodeData> entityChild =
                            new Node<EntityNodeData>(new EntityNodeData(Factory.create(GenericUri.class).with(
                                    jaxrsChildNode.getData().getUriResource().toString())));
                    entityNode.addChild(entityChild);
                    walkJaxrsNoResourceTree(jaxrsChildNode, entityChild);
                } else {
                    walkJaxrsNoResourceTree(jaxrsChildNode, entityNode);
                }
            }
            // there is resource in jaxrsNode
            else {
                // the resource type is the same as the current entityNode
                if (jaxrsChildNode.getData().getJaxrsResources().size() > 0
                        && jaxrsChildNode.getData().getJaxrsResources().get(0).getType()
                                .equals(entityNode.getData().getType())) {
                    for (JaxrsResource jaxrsResouce : jaxrsChildNode.getData().getJaxrsResources()) {
                        if (jaxrsResouce.getHttpMethod() == HttpMethod.GET) {
                            if (jaxrsResouce.getType().getCollectionType().isCollection()) {
                                entityNode.getData().getEntityOperations().addOperation(Operation.SEARCH, jaxrsResouce);
                            } else {
                                entityNode.getData().getEntityOperations().addOperation(Operation.READ, jaxrsResouce);
                            }
                        } else if (jaxrsResouce.getHttpMethod() == HttpMethod.POST) {
                            entityNode.getData().getEntityOperations().addOperation(Operation.CREATE, jaxrsResouce);
                        } else if (jaxrsResouce.getHttpMethod() == HttpMethod.DELETE) {
                            entityNode.getData().getEntityOperations().addOperation(Operation.DELETE, jaxrsResouce);
                        } else if (jaxrsResouce.getHttpMethod() == HttpMethod.PUT) {
                            entityNode.getData().getEntityOperations().addOperation(Operation.UPDATE, jaxrsResouce);
                        }
                    }
                    walkJaxrsNoResourceTree(jaxrsChildNode, entityNode);
                } else {
                    Node<EntityNodeData> currentEntity;
                    if (!jaxrsChildNode.getData().getUriResource().isVariable()) {
                        currentEntity =
                                new Node<EntityNodeData>(new EntityNodeData(Factory.create(GenericUri.class).with(
                                        jaxrsChildNode.getData().getUriResource().toString()), jaxrsChildNode.getData()
                                        .getJaxrsResources().getType()));
                        entityNode.addChild(currentEntity);
                    } else {
                        currentEntity = entityNode;
                        currentEntity.getData().setType(jaxrsChildNode.getData().getJaxrsResources().getType());
                    }
                    for (JaxrsResource jaxrsResouce : jaxrsChildNode.getData().getJaxrsResources()) {
                        if (jaxrsResouce.getHttpMethod() == HttpMethod.GET) {
                            if (jaxrsResouce.getType().getCollectionType().isCollection()) {
                                currentEntity.getData().getEntityOperations()
                                        .addOperation(Operation.SEARCH, jaxrsResouce);
                            } else {
                                currentEntity.getData().getEntityOperations().addOperation(Operation.READ, jaxrsResouce);
                            }
                        } else if (jaxrsResouce.getHttpMethod() == HttpMethod.POST) {
                            currentEntity.getData().getEntityOperations().addOperation(Operation.CREATE, jaxrsResouce);
                        } else if (jaxrsResouce.getHttpMethod() == HttpMethod.DELETE) {
                            currentEntity.getData().getEntityOperations().addOperation(Operation.DELETE, jaxrsResouce);
                        } else if (jaxrsResouce.getHttpMethod() == HttpMethod.PUT) {
                            currentEntity.getData().getEntityOperations().addOperation(Operation.UPDATE, jaxrsResouce);
                        }
                    }
                    walkJaxrsNoResourceTree(jaxrsChildNode, entityNode);
                }

            }
        }

    }
}
