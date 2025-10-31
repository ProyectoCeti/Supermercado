package com.citymarket.admin.service;

import com.citymarket.products.repository.ProductsRepository;
import com.citymarket.providers.repository.ProvidersRepository;
import com.citymarket.customers.repository.CustomerRepository;

public class AdminService {

    private ProductsRepository productsRepository;
    private ProvidersRepository providerRepository;
    private CustomerRepository customerRepository;

    public AdminService(){
        this.productsRepository = productsRepository;
        this.customerRepository =  customerRepository;
        this.providerRepository = providerRepository;
    }
}
