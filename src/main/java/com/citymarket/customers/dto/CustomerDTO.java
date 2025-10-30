package com.citymarket.customers.dto;

import com.citymarket.customers.model.Customer;

import java.math.BigDecimal;

    public class CustomerDTO {
    private int id;
    private String name;
    private String address;
    private String email;
    private String password;
    private BigDecimal saldo;


    public CustomerDTO(int id, String name, String email, String address, String password ,BigDecimal saldo) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.saldo = saldo;
        this.password = password;
    }

    //Convierte customer en un dto para que el frontend pueda acceder a la informacion necesaria
    public static CustomerDTO fromCustomer(Customer customer) {
        return new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getAddress(),
                customer.getEmail(),
                customer.getPassword(),
                customer.getSaldo()
            );
        }

    //Getters
    public  int getId() {return id;}
    public  String getName() {return name;}
    public  String getAddress() {return address;}
    public  String getEmail() {return email;}
    public  BigDecimal getSaldo() {return saldo;}

}
