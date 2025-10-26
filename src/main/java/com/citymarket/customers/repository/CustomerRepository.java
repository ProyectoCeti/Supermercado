package com.citymarket.customers.repository;

import com.citymarket.customers.model.Customer;

import java.util.List;
import java.util.Optional;

/*
Repository para la gestion de clientes
*/

public interface CustomerRepository {
    Optional<Customer> findById(int id); //Busca un cliente por su id
    List<Customer> findAllOrderedById(); //Ordena los clientes por su id
    Customer save(Customer customer); //Guarda un nuevo cliente
    Customer update(Customer customer);//Actualiza los datos de un cliente
    boolean deleteById(int id); //Elimina un cliente por su id
    List <Customer> findByName(String name);//Busca un cliente que ya existe por su nombre
    Optional<Customer> findByEmail(String email);//Busca un cliente por su email
}
