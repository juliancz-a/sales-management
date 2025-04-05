package com.exampleproyect.sales_management.domain.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="sales_details")
public class SaleDetails {

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_sale")
    @Id
    private Sale sale;
    
    @ManyToOne
    @JoinColumn(name="id_prod")
    @Id
    private Product product;
    
    @Column(name="prod_quantity")
    private Long productQuantity;
    
    public SaleDetails(Sale sale, Product product, Long productQuantity) {
        this.sale = sale;
        this.product = product;
        this.productQuantity = productQuantity;
    }

    public SaleDetails() {
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Long productQuantity) {
        this.productQuantity = productQuantity;
    }

    

}
