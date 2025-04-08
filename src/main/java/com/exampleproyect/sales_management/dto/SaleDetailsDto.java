package com.exampleproyect.sales_management.dto;

public class SaleDetailsDto {

    private ProductDto product;
    private Long quantity;

    public SaleDetailsDto() {
    }

    public ProductDto getProduct() {
        return product;
    }
    
    public Long calcTotal() {
        return getProduct().getPrice() * getQuantity();
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }


    
}
