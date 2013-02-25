package com.test.type;

public class Type {
	
	private Class<?> javaClass;
	
	private CollectionType collectionType;
	

	protected Type(Class<?> javaClass, CollectionType collectionType) {
		this.javaClass = javaClass;
		this.collectionType = collectionType;
	}

	public Class<?> getJavaClass() {
		return javaClass;
	}

	public CollectionType getCollectionType() {
		return collectionType;
	}
	
	public String toString() {
		return collectionType.toString(javaClass);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Type other = (Type) obj;
		if (collectionType != other.collectionType)
			return false;
		if (javaClass == null) {
			if (other.javaClass != null)
				return false;
		} else if (!javaClass.equals(other.javaClass))
			return false;
		return true;
	}
	
	
	
	

}
