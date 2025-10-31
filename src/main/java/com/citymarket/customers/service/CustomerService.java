package com.citymarket.customers.service;

import com.citymarket.customers.model.Customer;
import com.citymarket.customers.dto.CustomerDTO;
import com.citymarket.customers.repository.CustomerRepository;
import com.citymarket.customers.repository.CustomerRepositoryImpl;
import com.citymarket.authService.AuthService;

import javax.swing.*;
import java.util.Optional;

public class CustomerService {

    private CustomerRepository customerRepository;
    private AuthService authService;

    public CustomerService(){
        this.customerRepository = new CustomerRepositoryImpl();
        this.authService = new AuthService();

    }

    //Metodo para validar un email
    public boolean isValidEmail(String email){
        if(email == null || email.isEmpty()){
            System.out.println("Email vacio o nullo");
            return false;
        }

        //Validamos que email contenga estos caracteres y sea mayor a 6 caracteres
        boolean hasASteal = email.contains("@");
        boolean hasPoint =  email.contains(".");
        boolean eldestAsix = email.length() > 6;

        //Verificamos que primero este el arroba antes del punto
        int atIndex = email.indexOf("@");
        int dotIndex = email.lastIndexOf(".");
        boolean correctOrder = atIndex > 0 &&  dotIndex > atIndex + 1 && dotIndex < email.length() -2;

        // No debe haber espacios
        boolean noSpaces = !email.contains(" ");

        // Solo debe haber un @
        boolean singleAt = email.indexOf("@") == email.lastIndexOf("@");

        // Valida que cumpla con todas las validaciones
        boolean isValid = hasASteal && hasPoint && eldestAsix && correctOrder && noSpaces && singleAt;

        //Si email no es valido mostramos un mensaje
        if(!isValid){
            System.out.println("Invalid Email.");
        }

        return isValid; //Retornamos el email si es valido
    }

    //Validamos que tenga una contraseña segura
    public boolean isValidPassword(String password){
        //Verificamos que la contraseña no sea nula
        if(password == null || password.isEmpty()){
            System.out.println("La contraseña no puede ser nula");
            return false;
        }

        //Validamos que la contraseña contenga al menos un caracter y un numero
        boolean caracter = password.matches(".*\\d.*");
        boolean eldestAsix = password.length() >= 6;

        boolean isValid = caracter && eldestAsix;

        if(!isValid){
            System.out.println("Contraseña Invalida. Debe tener al menos 6 caracteres y tener un numero");
        }

        return isValid;
    }

    //Metodo que recibe datos de un nuevo cliente y retorna un object
    public CustomerDTO save(String name, String email, String address, String password){

        //Validamos el email
        if(!isValidEmail(email)){
            JOptionPane.showMessageDialog(null,"Email Invalido");
            throw new IllegalArgumentException("Email invalido.");
        }

        //Validamos la contraseña
        if(!isValidPassword(password)){
            JOptionPane.showMessageDialog(null, "Contraseña invalida.");
            throw new IllegalArgumentException("Contraseña invalida.");
        }

        //Encriptar contraseña y guardamos la contraseña
        String encriptedPassword = authService.encryptPassword(password);

        //Agregamos un cliente
        Customer newCustomer = new Customer(
            0, //Le pasamos un id temporal
            name,
            email,
            address,
            encriptedPassword,
            null, //Saldo
            null, //createdAt
            null //updatedAt
        );

        //Envia el cliente al repository para guardarlo en la base de datos
        Customer savedCustomer = customerRepository.save(newCustomer);
        //Convierte el customer a dto(data transfer object) y este los retorna
        return CustomerDTO.fromCustomer(savedCustomer);

    }

    public Optional<CustomerDTO> autenticacion(String email, String password){
        //verificamos el email y la contraseña
        Customer customer = authService.autenticacion(email, password);

        //Si retorna un customer lo convierte a dto y lo envuelve en optional
        if(customer != null){
            return Optional.of(CustomerDTO.fromCustomer(customer));
        }
        //Retorna un optional vacio si el login fue fallido
        return Optional.empty();
    }
}
