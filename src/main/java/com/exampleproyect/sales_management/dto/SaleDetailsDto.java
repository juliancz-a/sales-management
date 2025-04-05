package com.exampleproyect.sales_management.dto;

import org.springframework.stereotype.Component;

@Component
public class SaleDetailsDto {

    private Long id;

    private Long quantity;

    public SaleDetailsDto() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    
}
