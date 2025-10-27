package com.citymarket.providers.dto;

import com.citymarket.providers.model.Provider;
import com.citymarket.providers.model.Status;

public class ProviderDTO {

    private int id;
    private String nameProvider;
    private String email;
    private String telefono;
    private Status estatus;

    public ProviderDTO(int id,  String nameProvider, String email, String telefono, Status estatus){
        this.id = id;
        this.nameProvider = nameProvider;
        this.email = email;
        this.telefono = telefono;
        this.estatus = estatus;
    }

    //Convierte provedores en un dto para que el forntend pueda acceder a la informacion necesaria
    public static ProviderDTO fromProvider(Provider provider){
        return new ProviderDTO(
           provider.getId(),
           provider.getNameProvider(),
           provider.getEmail(),
           provider.getTelefono(),
           provider.getEstatus()
        );
    }

    //Getters
    public int getId() {return id;}

    public String getNameProvider() {return nameProvider;}

    public String getEmail() {return email;}

    public String getTelefono() {return telefono;}

    public Status getEstatus() {return estatus;}
}
