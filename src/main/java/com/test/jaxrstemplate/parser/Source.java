package com.test.jaxrstemplate.parser;


public class Source {

    private String text;
    private int pos;

    public Source(String text) {
        this.text = text;
        pos = 0;
    }

 

    public int getPos() {
        return pos;
    }

    public boolean consume(char... values) {
        if (endReached()) {
            return false;
        }
        for (char value : values) {
            if (text.charAt(pos) == value) {
                advance();
                return true;
            }
        }
        return false;
    }

    public boolean consume() {
        advance();
        return true;
    }

    public Character current() {
        if (endReached()) {
            return 0;
        } else {
            return text.charAt(pos);
        }
    }

    protected void advance() {
        if (pos > text.length() - 1) {
            throw new ParserError("End reached!");
        } else {
            pos++;
        }
    }

    public boolean endReached() {
        return (pos == text.length());
    }

    public String getSubString(int startPos, int endPos) {
        return text.substring(startPos, endPos);
    }

}
