package com.exampleproyect.sales_management.utils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.exampleproyect.sales_management.domain.models.entities.User;
import com.exampleproyect.sales_management.domain.repositories.UserRepository;

@Component
public class AuthenticationUtil {

    @Autowired
    UserRepository userRepository;
   
    
    public Optional<Long> getCurrentUserId(){
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        Optional<User> optionalUser = userRepository.findByUsername(authentication.getName());
     
        if(optionalUser.isPresent()) {
            return Optional.of(optionalUser.orElseThrow().getId());
        }
        return Optional.empty();
    

}
}