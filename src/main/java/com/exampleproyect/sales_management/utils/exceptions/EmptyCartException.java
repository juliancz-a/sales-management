package com.exampleproyect.sales_management.utils.exceptions;


public class EmptyCartException extends RuntimeException{

    private final String message;

    public EmptyCartException(String message) {
        this.message = message;
    }



}
