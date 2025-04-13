package com.exampleproyect.sales_management.infraestructure.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exampleproyect.sales_management.domain.models.CartItem;
import com.exampleproyect.sales_management.domain.models.Invoice;
import com.exampleproyect.sales_management.domain.models.entities.Product;
import com.exampleproyect.sales_management.dto.SaleDto;
import com.exampleproyect.sales_management.services.CartService;
import com.exampleproyect.sales_management.services.SaleService;
import com.exampleproyect.sales_management.utils.SaleDetailsExporter;
import com.exampleproyect.sales_management.utils.exceptions.EmptyCartException;

import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService service;

    @Autowired
    SaleService saleService;

    @GetMapping()
    public List<CartItem> viewCart() {
        return service.getCart();
    }

    @PostMapping("/add")
    public List<CartItem> addToCart(@RequestBody CartItem item) { // check validations
        return service.addToCart(item);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> removeFromCart(@RequestParam Long itemId) {
        Optional<Product> optionalRemovedProd = service.removeFromCart(itemId);

        if (optionalRemovedProd.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(optionalRemovedProd.get());
    }

    @GetMapping("/checkout")
    public ResponseEntity<?> checkout() {

        List<CartItem> cart = service.getCart();

        if(cart.isEmpty()) {
            throw new EmptyCartException("You cannot process an order with an empty cart");
        }

        service.clearCart();

        return ResponseEntity.ok(saleService.save(cart));

    }

    @GetMapping("/export-invoice/{saleId}")
    public ResponseEntity<?> export(@PathVariable Long saleId, HttpServletResponse response) throws IOException {
        

        Optional<SaleDto> optionalSale = saleService.findByIdAndUser(saleId);

        if(optionalSale.isPresent()) {
            SaleDto sale = optionalSale.orElseThrow();

            Invoice invoice = new Invoice();

            invoice.setSale(sale);
            
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "attachment; filename=details.pdf");
            SaleDetailsExporter exporter = new SaleDetailsExporter(invoice);
            exporter.export(response);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
        
    }


}
