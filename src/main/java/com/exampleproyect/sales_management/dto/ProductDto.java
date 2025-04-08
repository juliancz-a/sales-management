package com.exampleproyect.sales_management.dto;

public class ProductDto {
    
    private String name;

    private Long price;

    public ProductDto() {
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }


}
