package com.test.jaxrstemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.test.jaxrstemplate.parser.Parser;
import com.test.jaxrstemplate.parser.Source;
import com.test.jaxrstemplate.parser.token.Tokenizer;

public class JaxRsTemplate implements TemplatePart {
    
    public static JaxRsTemplate parse(String text) {
        Source source = new Source(text);
        Tokenizer tokenizer = new Tokenizer(source);
        Parser parser = new Parser(tokenizer);
        return parser.parseTemplate();
    }
    
    
    private List<TemplatePart> parts;
    
    public JaxRsTemplate() {
        this.parts = new ArrayList<TemplatePart>();
    }

    public JaxRsTemplate(TemplatePart templatePart) {
        this.parts = new ArrayList<TemplatePart>();
        this.parts.add(templatePart);
    }

    
    public JaxRsTemplate(List<TemplatePart> parts) {
        this.parts = new ArrayList<TemplatePart>(parts);
    }
    
    
    public List<TemplatePart> getParts() {
        return Collections.unmodifiableList(parts);
    }


    @Override
    public String toUri() {
        StringBuffer tmp = new StringBuffer();
        for(TemplatePart templatePart:parts) {
            tmp.append(templatePart.toUri());
        }
        return tmp.toString();
    }
    
    public JaxRsTemplate getSubTemplateUntilFirstVariable() {
        String uri = toUri();
        String subUri = uri.substring(0,uri.indexOf("{")==-1?uri.length():uri.indexOf("{")-1);
        return JaxRsTemplate.parse(subUri);
    }
    
    public String[] getUriResources() {
        String uri = toUri();
        String[] resources = uri.split("/");
        List<String> realResouces = new ArrayList<String>();
        for(String resource:resources) {
            if (!resource.trim().equals("")) {
                realResouces.add(resource);
            }
        }
        return realResouces.toArray(new String[] {});
    }

}
