package com.exampleproyect.sales_management.services;

import java.util.List;
import java.util.Optional;

import com.exampleproyect.sales_management.domain.models.entities.User;
import com.exampleproyect.sales_management.dto.UserDto;

public interface UserService {

    List<UserDto> findAll();

    Optional<UserDto> findById(Long id);

    User save(User user);

    Optional<User> delete(Long id);

}
