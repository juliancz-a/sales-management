package com.exampleproyect.sales_management.infraestructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:messages.properties")
public class AppConfig {
}
