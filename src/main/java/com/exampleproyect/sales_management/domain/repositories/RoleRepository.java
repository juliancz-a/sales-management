package com.exampleproyect.sales_management.domain.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.exampleproyect.sales_management.domain.models.entities.Role;

public interface  RoleRepository extends CrudRepository<Role, Long>{

    Optional<Role> findByName(String name);
}
