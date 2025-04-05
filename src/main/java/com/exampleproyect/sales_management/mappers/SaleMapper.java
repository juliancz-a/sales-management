package com.exampleproyect.sales_management.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.exampleproyect.sales_management.domain.models.entities.Sale;
import com.exampleproyect.sales_management.dto.SaleDto;

public class SaleMapper {

    public static SaleDto toDto(Sale sale) {
        if (sale == null) {
            return null;
        }

        SaleDto saleDto = new SaleDto();

        saleDto.setId(sale.getId());
        saleDto.setUser(UserMapper.toDto(sale.getUser()));
        saleDto.setSaleDetails(SaleDetailsMapper.listToDto(sale.getSaleDetail()));
        saleDto.setDate(sale.getDate());

        return saleDto;
    } 

     public static List<SaleDto> listToDto (List<Sale> sales) {
        return sales.stream()
        .map((sale) -> SaleMapper.toDto(sale))
        .collect(Collectors.toList());
    }

}
