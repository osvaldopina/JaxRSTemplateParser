package org.entitatemindex.entity;

import org.entitatemindex.type.Type;
import org.entitatemindex.uri.GenericUri;
import org.entitatemindex.utils.NodeData;

public class EntityNodeData implements NodeData {

    private GenericUri uri;

    private EntityOperations entityOperations;

    private Type entityType;

    public EntityNodeData(GenericUri uri) {
        this.uri = uri;
        this.entityOperations = new EntityOperations();
    }

    public EntityNodeData(GenericUri uri, Type entityType) {
        this.uri = uri;
        this.entityType = entityType;
        this.entityOperations = new EntityOperations();
    }

    public EntityNodeData(GenericUri uri, Type entityType, EntityOperations entityOperations) {
        this.uri = uri;
        this.entityType = entityType;
        this.entityOperations = entityOperations;
    }

    public GenericUri getUri() {
        return uri;
    }

    public EntityOperations getEntityOperations() {
        return entityOperations;
    }

    public Type getType() {
        return entityType;
    }
    
    public void setType(Type type) {
        this.entityType = type;
    }


    @Override
    public String toString() {
        return toString("");
    }

    @Override
    public String toString(String identation) {
        StringBuffer tmp = new StringBuffer();
        tmp.append(identation);
        tmp.append(identation);
        tmp.append("Entity[");
        tmp.append(uri.getLastResource());
        tmp.append("]");
        if (entityType != null) {
            tmp.append(" (");
            tmp.append(entityType);
            tmp.append(")");
        }
        if ((entityOperations != null) && (!entityOperations.isEmpty())) {
            tmp.append("\n");
            tmp.append(entityOperations.toString(identation));
        }
        return tmp.toString();
    }


}
