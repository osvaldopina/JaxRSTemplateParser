package org.entitatemindex.uri;

public class UriResource {

    private String name;
    
    public UriResource(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public boolean isVariable() {
        return (name.startsWith("{") && name.endsWith("}"));
    }
    
    public String getVariableName() {
        return name.substring(1, name.length()-1);
    }
    
    @Override
    public String toString() {
        return name;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UriResource other = (UriResource) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
    
    

}
