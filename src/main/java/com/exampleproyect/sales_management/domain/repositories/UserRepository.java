package com.exampleproyect.sales_management.domain.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.exampleproyect.sales_management.domain.models.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
