package org.entitatemindex.utils;

public class StringUtils {

    private StringUtils() {

    }

    public static boolean addIfNotFirst(boolean first, StringBuffer buffer, String value) {
        if (!first) {
            buffer.append(value);
        }
        return false;
    }

}
