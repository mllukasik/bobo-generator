package com.mllukasik.fragment.exception;

public class FragmentParserException extends RuntimeException {

    public FragmentParserException(String message) {
        super(message);
    }

    public FragmentParserException(Throwable cause) {
        super(cause);
    }
}
