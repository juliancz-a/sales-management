package com.exampleproyect.sales_management.infraestructure.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exampleproyect.sales_management.domain.models.entities.User;
import com.exampleproyect.sales_management.dto.UserDto;
import com.exampleproyect.sales_management.services.UserService;


@RestController
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/users")
    public ResponseEntity<?> listAll () {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> listById (@PathVariable Long id) {
        Optional<UserDto> optionalUserDto = service.findById(id);

        if(optionalUserDto.isPresent()) {
            return ResponseEntity.ok(optionalUserDto.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/users/add")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        user.setAdmin(false);
        return addUser(user);
    } 

    // TODO => User update credentials and general info

    @DeleteMapping("/users/delete/{id}")
    public ResponseEntity<?> deleteUser (@PathVariable Long id) {
        Optional<User> optionalUserDb = service.delete(id);

        if(optionalUserDb.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(optionalUserDb.orElseThrow());
        }

        return ResponseEntity.notFound().build();

        
    }
    

}
