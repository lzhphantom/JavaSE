package com.lzhphantom.design.interpreter;

public class ParseException extends Exception {
    public ParseException(String word) {
        super(word);
    }
}
