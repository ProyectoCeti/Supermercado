package com.citymarket.customers.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/*
   Modelo de cliente
*/

    public class Customer {
    private int id;
    private String name;
    private String address;
    private String email;
    private String password;
    private BigDecimal saldo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //Constructor
    public Customer(int id, String name, String email, String address, String password, BigDecimal saldo, LocalDateTime createdAt,  LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
        this.saldo = saldo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    //Getters
    public int getId(){return id;}

    public String getName(){return name;}

    public String getAddress(){return address;}

    public String getEmail(){return email;}

    public String getPassword(){return password;}

    public BigDecimal getSaldo(){return saldo;}

    public LocalDateTime getCreatedAt(){return createdAt;}

    public LocalDateTime getUpdatedAt(){return updatedAt;}
}
