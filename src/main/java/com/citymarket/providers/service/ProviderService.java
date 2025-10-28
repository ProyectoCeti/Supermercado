package com.citymarket.providers.service;

import com.citymarket.providers.model.Provider;
import com.citymarket.providers.model.Status;
import com.citymarket.providers.repository.ProvidersRepository;
import com.citymarket.providers.dto.ProviderDTO;
import com.citymarket.providers.repository.ProvidersRepositoryImpl;

public class ProviderService {

    private ProvidersRepository providersRepository;

    public ProviderService(){
        this.providersRepository = new ProvidersRepositoryImpl();
    }

    //Metodos para validar un proveedor

    //Metodo para validar email
    public boolean isEmailValid(String email){
        if(email == null || email.isEmpty()){
            System.out.println("El email no puede estar vacio");
        }

        //Verificamos que el email tenga un @, tenga punto y tenga mas de 6 caracteres
        boolean caracterValid = email.contains("@");
        boolean hasPoint =  email.contains(".");
        boolean longitude = email.length() > 6;

        //Validamos wue primero este el @ y despues el punto
        int atIndex = email.indexOf("@");
        int toIndex = email.indexOf(".");
        boolean correctOrder = atIndex > 0 && toIndex > atIndex + 1;

        boolean isValid = caracterValid && hasPoint && longitude && correctOrder;

        if(!isValid){
            System.out.println("email invalido");
        }
        return isValid;
    }

    //Validamos el numero de telefono de un proveedor
    public boolean isPhoneValid(String telefono){
        //Validamos que el numero no este vacio
        if(telefono== null ||telefono.isEmpty()){
            System.out.println("El numero de telefono no puede estar vacio");
        }

        //Validamos que el numero contenga 10 caracteres y el numero internacional de mexico
        boolean phoneValid = telefono.matches("^\\+52\\d{10}$");

        //Mostramos un mensaje si el numero no es valido
        if(!phoneValid){
            System.out.println("telefono invalido");
        }

        return phoneValid; //Retornamos el numero
    }

    //Validamos que el proveedor tenga un nombre
    public boolean isNameValid(String nameProvider){
        if(nameProvider == null || nameProvider.isEmpty()){
            System.out.println("El nombre no puede estar vacio");
        }

        boolean nameValid = nameProvider.length() > 5;
        if(!nameValid){
            System.out.println("nombre invalido");
        }

        return nameValid; //Retornamos el nombre validado
    }

    //Metodo para agregar un provedor
    public ProviderDTO agregarProvider(String nameProvider, String email, String telefono, Status estatus) {

        if(!isEmailValid(email)){
            throw new IllegalArgumentException("El email no es valido.");
        }

        if(!isPhoneValid(telefono)){
            throw new IllegalArgumentException("El telefono no es valido.");
        }

        if(!isNameValid(nameProvider)){
            throw new IllegalArgumentException("El nombre no es valido.");
        }

        //Agregamos el proveedor
        Provider provider = new Provider(
                0,
                nameProvider,
                email,
                telefono,
                estatus
        );

        //Guardamos en la base de datos
        Provider saveProvider = providersRepository.save(provider);

        //Retornamos un dto
        return ProviderDTO.fromProvider(saveProvider);
    }
}
