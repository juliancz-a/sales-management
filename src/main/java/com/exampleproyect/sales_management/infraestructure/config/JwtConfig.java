package com.exampleproyect.sales_management.infraestructure.config;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;

public class JwtConfig {

    public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build(); // secret_key gen
    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String CONTENT_TYPE = "application/json";
}
