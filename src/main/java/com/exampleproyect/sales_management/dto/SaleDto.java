package com.exampleproyect.sales_management.dto;

import java.time.LocalDateTime;
import java.util.List;

public class SaleDto {

    private Long id;

    private UserDto user;

    private List<SaleDetailsDto> salesDetails;

    private LocalDateTime date;

    public SaleDto(Long id, UserDto userDto, List<SaleDetailsDto>  salesDetailsDto, LocalDateTime date) {
        this.id = id;
        this.user = userDto;
        this.salesDetails = salesDetailsDto;
        this.date = date;
    }

    public SaleDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto userDto) {
        this.user = userDto;
    }

    public List<SaleDetailsDto>  getSalesDetails() {
        return salesDetails;
    }

    public void setSalesDetails(List<SaleDetailsDto>  saleDetailsDto) {
        this.salesDetails = saleDetailsDto;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }


    
}
