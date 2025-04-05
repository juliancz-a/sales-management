package com.exampleproyect.sales_management.services.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exampleproyect.sales_management.domain.models.entities.Role;
import com.exampleproyect.sales_management.domain.models.entities.User;
import com.exampleproyect.sales_management.domain.repositories.RoleRepository;
import com.exampleproyect.sales_management.domain.repositories.UserRepository;
import com.exampleproyect.sales_management.dto.UserDto;
import com.exampleproyect.sales_management.mappers.UserMapper;
import com.exampleproyect.sales_management.services.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository repository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly=true)
    public List<UserDto> findAll() {

        List<User> users = (List<User>) repository.findAll();
        return UserMapper.listToDto(users);
    }

    @Override
    @Transactional(readOnly=true)
    public Optional<UserDto> findById(Long id) {
        Optional<User> optionalUserDb = repository.findById(id);

        if (optionalUserDb.isPresent()) {
            User userDb = optionalUserDb.orElseThrow();
            return Optional.of(UserMapper.toDto(userDb));
        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public User save(User user) {
        ArrayList<Role> roles = new ArrayList<>();

        // Default ROLE_USER role for all users
        Optional<Role> optionalUserRole = roleRepository.findByName("ROLE_USER");
        optionalUserRole.ifPresent(roles::add);

        //Set ROLE_ADMIN role if possible
        if(user.isAdmin()) {
            Optional<Role> optionalAdminRole = roleRepository.findByName("ROLE_ADMIN");
            optionalAdminRole.ifPresent(roles::add);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roles);

        return repository.save(user);
    }

    @Override
    @Transactional
    public Optional<User> delete(Long id) {

        Optional<User> optionalUserDb = repository.findById(id);

        optionalUserDb.ifPresent((userDb) -> 
            repository.delete(userDb));

        return optionalUserDb;
    }


}
