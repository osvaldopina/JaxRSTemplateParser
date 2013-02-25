package com.test.type;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;


public enum CollectionType {

	LIST("LIST<%1$s>"), SET("SET<%1$s>"), NONE("%1$s");
	
	private String format;
	
	private CollectionType(String format) {
		this.format = format;
	}

	public static CollectionType fromClass(Class<?> clazz) {

		if (List.class.isAssignableFrom(clazz)) {
			return LIST;
		} else if (Set.class.isAssignableFrom(clazz)) {
			return SET;
		} else {
			return NONE;
		}
	}
	
	public Class<?> getActualType(java.lang.reflect.Type type) {
		if (type instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) type;
			Type[] typeArguments = parameterizedType.getActualTypeArguments();
			return (Class<?>) typeArguments[0];
		} else {
			return (Class<?>) type;
		}

	}

	public String toString(Class<?> javaClass) {
		return String.format(format, javaClass.getName());
	}

}
