package com.exampleproyect.sales_management.infraestructure.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exampleproyect.sales_management.domain.models.entities.Product;
import com.exampleproyect.sales_management.services.ProductService;
import com.exampleproyect.sales_management.utils.RequestValidation;

import jakarta.validation.Valid;

@RestController
public class ProductController {

    @Autowired
    RequestValidation validationUtil;

    @Autowired
    ProductService service;

    @GetMapping("/products")
    public ResponseEntity<?> listAll () {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> listById (@PathVariable Long id) {
        Optional<Product> optionalUserDto = service.findById(id);

        if(optionalUserDto.isPresent()) {
            return ResponseEntity.ok(optionalUserDto.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/products/add")
    public ResponseEntity<?> addProduct(@Valid @RequestBody Product product, BindingResult result) {

        validationUtil.validate(result);

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
    }

    @PutMapping("products/update/{id}")
    public ResponseEntity<?> updateProduct (@RequestBody Product product, BindingResult result, @PathVariable Long id ) {

        validationUtil.validate(result);

        Optional<Product> optionalProductDb = service.update(id, product);

        if(optionalProductDb.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(optionalProductDb.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/products/delete/{id}")
    public ResponseEntity<?> deleteProduct (@PathVariable Long id, BindingResult result) {
        validationUtil.validate(result);

        Optional<Product> optionalProductDb = service.delete(id);

        if(optionalProductDb.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(optionalProductDb.orElseThrow());
        }

        return ResponseEntity.notFound().build();

        }
    

}
