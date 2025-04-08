package com.exampleproyect.sales_management.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.exampleproyect.sales_management.domain.models.entities.SaleDetails;
import com.exampleproyect.sales_management.dto.SaleDetailsDto;

public class SaleDetailsMapper {

    public static SaleDetailsDto toDto(SaleDetails saleDetails) {
        if (saleDetails == null) {
            return null;
        }

        SaleDetailsDto saleDetailsDto = new SaleDetailsDto();

        saleDetailsDto.setProduct(ProductMapper.toDto(saleDetails.getProduct()));
        saleDetailsDto.setQuantity(saleDetails.getProductQuantity());
        return saleDetailsDto;
    } 

    public static List<SaleDetailsDto> listToDto (List<SaleDetails> salesDetails) {
        return salesDetails.stream()
        .map((saleDetails) -> SaleDetailsMapper.toDto(saleDetails))
        .collect(Collectors.toList());
    }

}
