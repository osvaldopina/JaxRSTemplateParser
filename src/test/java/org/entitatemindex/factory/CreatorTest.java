package org.entitatemindex.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CreatorTest {

    @Before
    public void setUp() throws Exception {
     }

    @Test(expected=FactoryError.class)
    public void creatorConstructorWrongType() {
        new Creator<Integer>(Integer.class, new MyCreator());
    }
    
    @Test(expected=FactoryError.class)
    public void creatorConstructorNoCreateMethod() {
        new Creator<Integer>(Integer.class, new MyCreatorNoCreateMethod());
    }


    @Test
    public void isCreatorForString() {
        Creator<String> creator = new Creator<String>(String.class, new MyCreator());
        
        assertTrue(creator.isCreatorFor(String.class));
    }
    
    @Test
    public void isNotCreatorForInteger() {
        Creator<String> creator = new Creator<String>(String.class,new MyCreator());
        
        assertFalse(creator.isCreatorFor(Integer.class));
    }


    @Test
    public void creatorWithForStringParam() {
        Creator<String> creator = new Creator<String>(String.class,new MyCreator());
        
        assertEquals("param", creator.with("param"));
        
    }
    
    @Test
    public void creatorWithForIntegerParam() {
        Creator<String> creator = new Creator<String>(String.class,new MyCreator());
        
        assertEquals("25", creator.with(Integer.valueOf(25)));
        
    }

    @Test(expected=FactoryError.class)
    public void creatorWithForTypeNotFoundInParam() {
        Creator<String> creator = new Creator<String>(String.class,new MyCreator());
        
        assertEquals("25", creator.with(Short.valueOf("25")));
        
    }

    @Test(expected=FactoryError.class)
    public void creatorWithForDiferentTypeNumbers() {
        Creator<String> creator = new Creator<String>(String.class,new MyCreator());
        
        assertEquals("25", creator.with("a","b"));
        
    }
    
    public static class MyCreator {
        public String createStringFromString(String value) {
            
                
            return value;
        }
        
        public String createStringFromInteger(Integer value) {
            return value.toString();
        }
        
    }
    public static class MyCreatorNoCreateMethod {
        public String stringFromString(String value) {
            return null;
        }
        
        public String stringFromInteger(Integer value) {
            return null;
        }
        
    }


}
