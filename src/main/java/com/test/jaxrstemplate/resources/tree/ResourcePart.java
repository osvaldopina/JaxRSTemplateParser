package com.test.jaxrstemplate.resources.tree;

public class ResourcePart {
    
      private String name;

    public ResourcePart(String name) {
        super();
        this.name = name;
    }
      
      
    public String getName() {
        return name;
    }


 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ResourcePart other = (ResourcePart) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    
    
    
    
    

}
