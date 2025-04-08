package com.exampleproyect.sales_management.services;

import java.util.List;
import java.util.Optional;

import com.exampleproyect.sales_management.domain.models.CartItem;
import com.exampleproyect.sales_management.domain.models.entities.Sale;
import com.exampleproyect.sales_management.dto.SaleDto;


public interface SaleService {

    List<SaleDto> findAll();
    
    Optional<SaleDto> findById(Long id);

    SaleDto save(Long userId, List<CartItem> cartItems);

    Optional<Sale> delete(Long id);

}
