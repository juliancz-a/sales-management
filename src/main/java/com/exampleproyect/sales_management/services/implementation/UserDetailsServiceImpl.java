package com.exampleproyect.sales_management.services.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exampleproyect.sales_management.domain.models.entities.User;
import com.exampleproyect.sales_management.domain.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepository repository;
    
    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionaluserDb = repository.findByUsername(username);

        if (optionaluserDb.isEmpty()) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }

        User userDb = optionaluserDb.get();

        List<GrantedAuthority> authorities = userDb.getRoles().stream().map(rol -> 
            new SimpleGrantedAuthority(rol.getName())).collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(username, userDb.getPassword(), true, true, true, true, authorities);

    }

}
