package org.entitatemindex.entity;

import org.entitatemindex.uri.GenericUri;
import org.entitatemindex.utils.NodeData;

public class EntityNodeData implements NodeData {

    private GenericUri uri;

    private EntityOperations entityOperations;

    private Class<?> type;

    public EntityNodeData(GenericUri uri) {
        this.uri = uri;
        this.entityOperations = new EntityOperations();
    }

    public EntityNodeData(GenericUri uri, Class<?> type) {
        this.uri = uri;
        this.type = type;
        this.entityOperations = new EntityOperations();
    }

    public EntityNodeData(GenericUri uri, Class<?> type, EntityOperations entityOperations) {
        this.uri = uri;
        this.type = type;
        this.entityOperations = entityOperations;
    }

    public GenericUri getUri() {
        return uri;
    }

    public EntityOperations getEntityOperations() {
        return entityOperations;
    }

    public Class<?> getType() {
        return type;
    }
    
    public void setType(Class<?> type) {
        this.type = type;
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
        if (type != null) {
            tmp.append(" (");
            tmp.append(type.getName());
            tmp.append(")");
        }
        if ((entityOperations != null) && (!entityOperations.isEmpty())) {
            tmp.append("\n");
            tmp.append(entityOperations.toString(identation));
        }
        return tmp.toString();
    }


}
