package com.citymarket.customers.controller;

import com.citymarket.customers.service.CustomerService;
import com.citymarket.customers.dto.CustomerDTO;

import java.util.Optional;

public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public CustomerDTO registrar(String name, String email, String address, String password){
        //Realizamos el registo llamando al service
       return customerService.save( name, email, address, password);
    }

    public CustomerDTO login(String email, String password){
        Optional login = customerService.autenticacion(email, password);
        return (CustomerDTO) login.get();
    }
}
