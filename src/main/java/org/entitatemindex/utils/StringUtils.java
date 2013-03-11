package org.entitatemindex.utils;

public class StringUtils {

    private StringUtils() {

    }

    public static boolean addIfFalse(boolean control, StringBuffer buffer, String value) {
        if (!control) {
            buffer.append(value);
        }
        return false;
    }
    
    

}
