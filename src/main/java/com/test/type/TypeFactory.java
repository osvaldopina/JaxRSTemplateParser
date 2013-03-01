package com.test.type;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class TypeFactory {

	private static Map<String, Class<?>> primitives = new HashMap<String, Class<?>>();
	
	private static Type voidType = new Type(null, CollectionType.NONE); 

	static {
		primitives.put("void", void.class);
		primitives.put("boolean", boolean.class);
		primitives.put("byte", byte.class);
		primitives.put("short", short.class);
		primitives.put("int", int.class);
		primitives.put("long", long.class);
		primitives.put("float", float.class);
		primitives.put("double", double.class);
	}
	
	public static void addToPrimitives(String typeName, Class<?> typeClass) {
	    primitives.put(typeName, typeClass);
	}

	public static Type createFromMethodReturn(Method method) {

		CollectionType collectionType = CollectionType.fromClass(method
				.getReturnType());
		return new Type(collectionType.getActualType(method
				.getGenericReturnType()), collectionType);
	}
	
	public static Type getVoidType() {
	    return voidType;
	}

	public static Type createFromMethodParameter(Method method,
			int parameterIndex) {

		CollectionType collectionType = CollectionType.fromClass(method
				.getParameterTypes()[parameterIndex]);
		return new Type(
				collectionType
						.getActualType(method.getGenericParameterTypes()[parameterIndex]),
				collectionType);
	}

	public static Type createFromString(String typeString) {
		if (typeString.startsWith("LIST<")) {
			return new Type(forName(typeString.substring(5,
					typeString.length() - 1)), CollectionType.LIST);
		} else if (typeString.startsWith("SET<")) {
			return new Type(forName(typeString.substring(5,
					typeString.length() - 1)), CollectionType.SET);
		} else {
			return new Type(forName(typeString), CollectionType.NONE);
		}
	}

	private static Class<?> forName(String className) {
		try {
			if (primitives.get(className) != null) {
				return primitives.get(className);
			} else {
				return Class.forName(className);
			}
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

}
