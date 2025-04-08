package com.exampleproyect.sales_management.infraestructure.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exampleproyect.sales_management.domain.models.CartItem;
import com.exampleproyect.sales_management.domain.models.Invoice;
import com.exampleproyect.sales_management.domain.models.entities.User;
import com.exampleproyect.sales_management.domain.repositories.UserRepository;
import com.exampleproyect.sales_management.dto.SaleDto;
import com.exampleproyect.sales_management.services.SaleService;
import com.exampleproyect.sales_management.utils.SaleDetailsExporter;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class SaleController {

    @Autowired
    SaleService service;

    @Autowired
    UserRepository userRepository;

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

    @PostMapping("/buy")
    public void addSale (Authentication auth, @RequestBody List<CartItem> cartItems,
    HttpServletResponse response) throws IOException {

        Optional<Long> optionalUserId = getCurrentUserId(auth);
        if(optionalUserId.isEmpty()) {
        }

        SaleDto persistedSale = service.save(optionalUserId.get(), cartItems);

        Invoice invoice = new Invoice();
        invoice.setSale(persistedSale);

        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "attachment; filename=details.pdf");
        SaleDetailsExporter exporter = new SaleDetailsExporter(invoice);

        exporter.export(response);

    }

    private Optional<Long> getCurrentUserId(Authentication auth) {
        String username = auth.getName();

        Optional<User> optionalUserDb = userRepository.findByUsername(username);
        
        if(optionalUserDb.isPresent()) {
            User userDb = optionalUserDb.orElseThrow();
            return Optional.of(userDb.getId());
        }

        return Optional.empty();
    }
    



    
    // @PostMapping("/buy")
    // public HttpServletResponse addSale (Authentication auth, @RequestBody List<SaleDetailsDto> salesDetailsDto) {

    //     Optional<Long> optionalUserId = getCurrentUserId(auth);
    //     if(optionalUserId.isEmpty()) {
    //         return ResponseEntity.notFound().build();
    //     }

    //     SaleDto persistedSale = service.save(optionalUserId.get(), salesDetailsDto);

    //     return ResponseEntity.ok(persistedSale);
    // }

}
