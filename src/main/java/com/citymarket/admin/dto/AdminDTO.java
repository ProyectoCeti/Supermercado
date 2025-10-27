package com.citymarket.admin.dto;

import com.citymarket.admin.model.Admin;

public class AdminDTO {

    private int id;
    private String name;
    private String email;
    private String password;

    public AdminDTO(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    //Convierte Admin en un DTO para que el frontend acceda a el
    public static AdminDTO fromAdmin(Admin admin){
        return new AdminDTO(
        admin.getId(),
        admin.getName(),
        admin.getEmail(),
        admin.getPassword()
        );
    }

    //Getters
    public int getId() {return id;}

    public String getName() {return name;}

    public String getEmail() {return email;}

    public String getPassword() {return password;}

}
