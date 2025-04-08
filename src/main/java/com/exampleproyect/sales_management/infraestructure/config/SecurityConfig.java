package com.exampleproyect.sales_management.infraestructure.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.exampleproyect.sales_management.infraestructure.security.filters.AuthenticationFilter;
import com.exampleproyect.sales_management.infraestructure.security.filters.ValidationFilter;

@Configuration
@EnableConfigurationProperties(EndpointsConfig.class)
public class SecurityConfig {

    private final EndpointsConfig endpointsConfig;

    public SecurityConfig(EndpointsConfig endpointsConfig) {
        this.endpointsConfig = endpointsConfig;
    }

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;
    
    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        
        return  http.authorizeHttpRequests(authz -> 
                authz.requestMatchers(endpointsConfig.obtainAdminRequests()).hasRole("ADMIN")
                .requestMatchers(endpointsConfig.obtainAuthRequests()).hasAnyRole("USER", "ADMIN")
                .requestMatchers(endpointsConfig.obtainNoAuthRequests()).permitAll()
                .anyRequest().authenticated())
                .addFilter(new AuthenticationFilter(authenticationManager()))
                .addFilter(new ValidationFilter(authenticationManager()))
                .csrf(config -> config.disable())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
            
    }

}
