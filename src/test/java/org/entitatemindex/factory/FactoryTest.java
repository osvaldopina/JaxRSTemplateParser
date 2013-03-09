package org.entitatemindex.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FactoryTest {

    private MyCreator myCreator;
    private static List<Creator<?>> creators;
    
    
    @Before
    public void setUp() throws Exception {
        myCreator = new MyCreator();
        creators = Factory.getCreators();
        Factory.clear();
    }


    @After
    public void tearDown() throws Exception {
        Factory.addCreators(creators);
    }
    
    
    @Test
    public void addCreator() {
        Factory.addCreator(String.class, myCreator);

        assertNotNull(Factory.create(String.class));

    }

    @Test(expected=FactoryError.class)
    public void clear() {
        Factory.addCreator(String.class, myCreator);
        Factory.clear();
        Factory.create(String.class);
    }

    @Test
    public void create() {
        Factory.addCreator(String.class, myCreator);
        assertEquals("25", Factory.create(String.class).with("25"));
    }

    @Test(expected=FactoryError.class)
    public void createNoCreatorFoundForType() {
        Factory.addCreator(String.class, myCreator);
        Factory.create(Integer.class);
    }
    
    
    public static class MyCreator {

        public String createStringFromString(String value) {

            return value;
        }

        public String createStringFromInteger(Integer value) {
            return value.toString();
        }

    }

}
