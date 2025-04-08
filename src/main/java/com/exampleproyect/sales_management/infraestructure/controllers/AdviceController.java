package com.exampleproyect.sales_management.infraestructure.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.exampleproyect.sales_management.utils.exceptions.BadRequestException;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> badRequestHandler (BadRequestException ex) {
        return ResponseEntity.badRequest().body(ex.getErrors());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> typeMismatchHandler (MethodArgumentTypeMismatchException ex) {
        Map<String, String> body = getResponseBody(ex, "Mismatch Argument Type", HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.badRequest().body(body);
    }

    private Map<String, String> getResponseBody(RuntimeException ex, String error, int statusCode) {
        Map<String, String> body = new HashMap<>();

        body.put("date", new Date().toString());
        body.put("error", error);
        body.put("message", ex.getMessage());
        body.put("status", String.valueOf(statusCode));

        return body;

    }

    //mismatch
    //unauth

}
