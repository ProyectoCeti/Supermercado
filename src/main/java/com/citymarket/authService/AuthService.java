package com.citymarket.authService;

import com.citymarket.customers.repository.CustomerRepository;
import com.citymarket.customers.repository.CustomerRepositoryImpl;
import com.citymarket.customers.model.Customer;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AuthService {

    private CustomerRepository customerRepository;

    public AuthService(){
        this.customerRepository = new CustomerRepositoryImpl();
    }

    //Autenticamos el usuario
    public Customer autenticacion(String email, String password) {
        //Buscamos al cliente en la base de datos
        Customer customer = customerRepository.findByEmail(email).orElse(null);//Si no lo encuentra retorna null

        if(customer != null && verifyPassword(password, customer.getPassword())){
            return customer; //Inicio de sesion exitoso
        }
        return null; //Retornamos null
    }

    //Metodo para encriptar la contraseña
    public String encryptPassword(String password) {
        try {
            //Obtenemos el algoritmo de encriptacion
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            //Convierte el String a bytes y guarda el resultado
            byte[] hash = digest.digest(password.getBytes());
            //Convierte los bytes encriptados a string y los retorna
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error encriptando contraseña", e);
        }
    }

    //Metodo para verificar la contraseña
    public boolean verifyPassword(String inputPassword, String storedPassword) {
        //Toma la contraseña que el usuario inngreso y la encripta
        String encryptedInput = encryptPassword(inputPassword);
        //Compara la contraseña encriptada y la compara con la de la base de datos y devuelve true o false
        return encryptedInput.equals(storedPassword);
    }

    //Metodo para cambiar la contraseña
    public boolean changePassword(int customerId, String currentPassword, String newPassword) {
        //Verifica que el usuario y la contraseña existan
        Customer customer = customerRepository.findById(customerId).orElse(null);

        if (customer != null && verifyPassword(currentPassword, customer.getPassword())) {
            //Encripta la nueva contraseña
            String newEncryptedPassword = encryptPassword(newPassword);
            //Cambia la contraseña del objeto customer
            customer.setPassword(newEncryptedPassword);
            //Actualiza el cliente y retorna distinto de null si fue exitoso
            return customerRepository.update(customer) != null;
        }
        //Retorna false si no se pudo cambiar la contraseña
        return false;
    }
}
