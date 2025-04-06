package com.exampleproyect.sales_management.infraestructure.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exampleproyect.sales_management.dto.SaleDetailsDto;
import com.exampleproyect.sales_management.dto.SaleDto;
import com.exampleproyect.sales_management.services.SaleService;

@RestController
public class SaleController {

    @Autowired
    SaleService service;


    @GetMapping("/sales")
    public ResponseEntity<?> listAll () {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/sales/{id}")
    public ResponseEntity<?> listById (@PathVariable Long id) {

        Optional<SaleDto> optionalSaleDto = service.findById(id);
        
        if(optionalSaleDto.isPresent()) {
            return ResponseEntity.ok(optionalSaleDto.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/sales/{idUser}")
    public ResponseEntity<?> addSale (@PathVariable Long idUser, @RequestBody List<SaleDetailsDto> salesDetailsDto) {

        SaleDto persistedSale = service.save(idUser, salesDetailsDto);

        return ResponseEntity.ok(persistedSale);
    }
    

}
