package com.exampleproyect.sales_management.infraestructure.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SimpleGrantedAuthorityMixIn {

    @JsonCreator
    public SimpleGrantedAuthorityMixIn (@JsonProperty("authority") String role) {}
}
