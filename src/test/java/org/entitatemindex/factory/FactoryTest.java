package org.entitatemindex.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class FactoryTest {

    private MyCreator myCreator;
    
    
    @Before
    public void setUp() throws Exception {
        myCreator = new MyCreator();
    }

    @Test
    public void addCreator() {
        Factory.addCreator(String.class, myCreator);

        assertNotNull(Factory.create(String.class));

    }

    @Test
    public void create() {
        Factory.addCreator(String.class, myCreator);
        assertEquals("25", Factory.create(String.class).with("25"));
    }
    
    @Test
    public void createVarArgs() {
        Factory.addCreator(String.class, myCreator);
        assertEquals("12", Factory.create(String.class).with(Integer.valueOf("1"),Integer.valueOf("2")));
    }


    @Test
    public void createPrimitivesAndWrappers() {
        Factory.addCreator(String.class, myCreator);
        assertEquals("12", Factory.create(String.class).with(1,2));
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
        
        public String createStringFromIntegerArray(Integer...integers) {
            StringBuffer tmp = new StringBuffer();
            for(Integer i:integers) {
                tmp.append(i);
            }
            return tmp.toString();
        }

    }

}
