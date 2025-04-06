package com.exampleproyect.sales_management.domain.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;


@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Size(min=3, max=20, message="The name field must contain between 6 and 20 characters")
    @NotBlank(message="{NotBlank}")
    private String name;

    @Size(min=3, max=128, message="The description field must contain between 6 and 20 characters")
    @NotBlank(message="{NotBlank}")
    private String description;

    @Min(value=1, message="Price should not be less than 1")
    @Positive(message="Price should be a positive number")
    private Long price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }



}
