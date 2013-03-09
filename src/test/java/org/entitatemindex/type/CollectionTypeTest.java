package org.entitatemindex.type;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Set;

import org.junit.Test;

public class CollectionTypeTest {
    

	@Test
	public void getActualTypeListString() throws Exception {

		assertEquals(String.class, CollectionType.NONE.getActualType(CollectionTypeTest.class
				.getMethod("genericMethod", Set.class).getGenericReturnType()));

	}
	
	@Test
	public void getActualTypeListInteger() throws Exception {

		assertEquals(Integer.class, CollectionType.NONE.getActualType(CollectionTypeTest.class
				.getMethod("genericMethod", Set.class).getGenericParameterTypes()[0]));

	}

	@Test
	public void getActualTypeString() throws Exception {

		assertEquals(Integer.class, CollectionType.NONE.getActualType(CollectionTypeTest.class
				.getMethod("method", Integer.class).getGenericParameterTypes()[0]));

	}
	
	@Test
	public void toStringList() throws Exception {

		assertEquals("LIST<java.lang.String>",CollectionType.LIST.toString(String.class));

	}

	@Test
	public void toStringSet() throws Exception {

		assertEquals("SET<java.lang.String>",CollectionType.SET.toString(String.class));

	}

	@Test
	public void toStringNone() throws Exception {

		assertEquals("java.lang.String",CollectionType.NONE.toString(String.class));

	}

	public List<String> genericMethod(Set<Integer> param) {
		return null;
	}

	public String method(Integer param) {
		return null;
	}

}
