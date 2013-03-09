package org.entitatemindex.type;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class CollectionTypeFactoryTest {
    
    private CollectionTypeFactory collectionTypeFactory;
    
    @Before
    public void setUp() {
        collectionTypeFactory = new CollectionTypeFactory();
    }

	@Test
	public void fromClassList() throws Exception {

		assertEquals(CollectionType.LIST, collectionTypeFactory.createfromClass(CollectionTypeFactoryTest.class
				.getMethod("genericMethod", Set.class).getReturnType()));

	}

	@Test
	public void fromClassSet() throws Exception {

		assertEquals(CollectionType.SET, collectionTypeFactory.createfromClass(CollectionTypeFactoryTest.class
				.getMethod("genericMethod", Set.class).getParameterTypes()[0]));

	}

	@Test
	public void fromClassString() throws Exception {

		assertEquals(CollectionType.NONE, collectionTypeFactory.createfromClass(CollectionTypeFactoryTest.class
				.getMethod("method", Integer.class).getReturnType()));

	}
	
	public List<String> genericMethod(Set<Integer> param) {
		return null;
	}

	public String method(Integer param) {
		return null;
	}

}
