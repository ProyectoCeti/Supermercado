package com.citymarket.providers.model;

public class Provider {

    private int id;
    private String nameProvider;
    private String email;
    private String telefono;
    private Status estatus;

    //Constructor
    public Provider(int id, String nameProvider, String email, String telefono, Status estatus) {
        this.id = id;
        this.nameProvider = nameProvider;
        this.email = email;
        this.telefono = telefono;
        this.estatus = estatus;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNameProvider(String nameProvider) {
        this.nameProvider = nameProvider;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEstatus(Status estatus) {
        this.estatus = estatus;
    }

    //Getters
    public int getId() {return id;}

    public String getNameProvider() {return nameProvider;}

    public String getEmail(){return email;}

    public String getTelefono(){return telefono;}

    public Status getEstatus(){return estatus;}
}
