package com.exampleproyect.sales_management.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import com.exampleproyect.sales_management.domain.models.entities.Sale;

public interface SaleRepository extends CrudRepository<Sale, Long>{

}
