package com.exampleproyect.sales_management.dto;

import java.time.LocalDateTime;
import java.util.List;

public class SaleDto {

    private Long id;

    private UserDto user;

    private List<SaleDetailsDto> saleDetails;

    private LocalDateTime date;

    public SaleDto(Long id, UserDto userDto, List<SaleDetailsDto>  saleDetailsDto, LocalDateTime date) {
        this.id = id;
        this.user = userDto;
        this.saleDetails = saleDetailsDto;
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

    public List<SaleDetailsDto>  getSaleDetails() {
        return saleDetails;
    }

    public void setSaleDetails(List<SaleDetailsDto>  saleDetailsDto) {
        this.saleDetails = saleDetailsDto;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }


    
}
