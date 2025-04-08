package com.exampleproyect.sales_management.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.exampleproyect.sales_management.utils.exceptions.BadRequestException;

@Component
public class RequestValidation {

    public void validate(BindingResult result) throws BadRequestException{
        if (result.hasErrors()) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach((fieldError) -> {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });

            throw new BadRequestException(errors);
        }
    }
}
