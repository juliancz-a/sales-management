package com.exampleproyect.sales_management.utils.exceptions;

import java.util.Map;

public class BadRequestException extends RuntimeException{

    private final Map<String, String> errors;

    public BadRequestException(Map<String, String> errors) {
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
    

}
