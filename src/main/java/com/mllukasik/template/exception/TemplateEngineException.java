package com.mllukasik.template.exception;

public class TemplateEngineException extends RuntimeException {

    public TemplateEngineException(Exception exception) {
        super(exception);
    }
}
