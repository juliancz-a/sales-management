package com.exampleproyect.sales_management.infraestructure.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.exampleproyect.sales_management.utils.BadRequestException;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> badRequest(BadRequestException ex) {
        return ResponseEntity.badRequest().body(ex.getErrors());
    }

    //mismatch
    //unauth

}
