package com.exampleproyect.sales_management.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

public class SaleDetailsDto {

    private Long id;

    @Min(value=1, message="Quantity should not be less than 1")
    @Positive(message="Quantity should be a positive number")
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
