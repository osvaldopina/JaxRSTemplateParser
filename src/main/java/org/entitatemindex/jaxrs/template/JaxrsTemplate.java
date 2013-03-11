package org.entitatemindex.jaxrs.template;

import java.util.Arrays;
import java.util.List;

import org.entitatemindex.factory.Factory;
import org.entitatemindex.uri.GenericUri;
import org.entitatemindex.utils.UnmodifiableList;


public class JaxrsTemplate extends UnmodifiableList<TemplatePart> {
    
    private GenericUri uri;
    
    public JaxrsTemplate() {
    }

    public JaxrsTemplate(TemplatePart templatePart) {
        super(Arrays.asList(templatePart));
    }

    
    public JaxrsTemplate(List<TemplatePart> parts) {
        super(parts);
    }
    
    public GenericUri toUri() {
        if (uri == null) {
            uri = Factory.create(GenericUri.class).with(toString());
        }
        return uri;
    }
    
    public  String getValue() {
        return toString();
    }
    
    @Override
    public String toString() {
        StringBuffer tmp = new StringBuffer();
        for(TemplatePart templatePart:this) {
            tmp.append(templatePart.getValue());
        }
        return tmp.toString();
    }


}
