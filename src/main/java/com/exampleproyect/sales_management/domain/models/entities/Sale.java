package com.exampleproyect.sales_management.domain.models.entities;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name="sales")
public class Sale {
    
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    // iNVOICE DETAILS?? to-do
    @OneToMany(mappedBy="sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleDetails> saleDetails;

    private LocalDateTime date;

    public Sale() {
        this.saleDetails = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    @PrePersist
    public void PrePersist() {
        date = LocalDateTime.now(ZoneId.of("GMT-3"));
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<SaleDetails> getSaleDetail() {
        return saleDetails;
    }

    public void setSaleDetails(List<SaleDetails> saleDetails) {
        this.saleDetails = saleDetails;
    }


    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void addSaleDetail(Product product, Long prodQuantity) {
        saleDetails.add(new SaleDetails(this, product, prodQuantity));
    }


}
