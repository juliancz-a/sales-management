package com.exampleproyect.sales_management.domain.models.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    // Credentials
    @Size(min=6, max=20, message="The username field must contain between 6 and 20 characters")
    @NotBlank(message="{NotBlank}")
    private String username;

    @Size(min=8, max=50, message="The email field must contain between 10 and 20 characters")
    @NotBlank(message="{NotBlank}")
    private String email;

    @NotBlank(message="{NotBlank}")
    private String password;

    @Size(min=3, max=20, message= "The name field must contain between 3 and 20 characters")
    @NotBlank(message="{NotBlank}")
    private String name;

    @Size(min=3, max=20, message= "The lastname field must contain between 3 and 20 characters")
    @NotBlank(message="{NotBlank}")
    private String lastname;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "users_roles", 
                joinColumns = @JoinColumn(name="user_id"), 
                inverseJoinColumns = @JoinColumn(name="role_id"),
                uniqueConstraints = {@UniqueConstraint(columnNames= {"user_id", "role_id"})})
    private List<Role> roles;

    @JsonIgnore
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sale> sales;

    private boolean enabled;

    // Configured in controller
    @Transient
    private boolean admin;

    public User() {
        this.roles = new ArrayList<>();
    }

    @PrePersist
    public void prePersist() {
        enabled = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }
    
    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }



}
