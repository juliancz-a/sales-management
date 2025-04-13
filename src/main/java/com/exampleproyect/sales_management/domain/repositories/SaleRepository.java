package com.exampleproyect.sales_management.domain.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.exampleproyect.sales_management.domain.models.entities.Sale;
import com.exampleproyect.sales_management.domain.models.entities.User;


public interface SaleRepository extends CrudRepository<Sale, Long>{

    Optional<Sale> findByIdAndUser(Long id, User user);

}
