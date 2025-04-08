package com.exampleproyect.sales_management.domain.models;
import java.util.List;
import java.util.stream.Collectors;

import com.exampleproyect.sales_management.dto.SaleDto;

public class Invoice {

    private String businessName;
    private String businessAddress;
    private String businessPhoneNumber;
    private String businessWebsite;
    private String businessEmail;

    private SaleDto sale;

    public Long calcTotal() {

        Long total = 0L;

        List<Long> subtotals = sale.getSalesDetails().stream().map((saleDetails) -> saleDetails.calcTotal())
            .collect(Collectors.toList());

        for(Long subtotal : subtotals) {
            total += subtotal;
        }

        return total;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getBusinessEmail() {
        return businessEmail;
    }

    public void setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
    }

    public String getBusinessPhoneNumber() {
        return businessPhoneNumber;
    }

    public void setBusinessPhoneNumber(String businessPhoneNumber) {
        this.businessPhoneNumber = businessPhoneNumber;
    }

    public String getBusinessWebsite() {
        return businessWebsite;
    }

    public void setBusinessWebsite(String businessWebsite) {
        this.businessWebsite = businessWebsite;
    }
    
    public SaleDto getSale() {
        return sale;
    }

    public void setSale(SaleDto sale) {
        this.sale = sale;
    }



    
    

}
