package com.citymarket.products.dto;

import com.citymarket.products.model.Products;

import java.math.BigDecimal;

public class ProductsDTO {

    private int id;
    private String nameProduct;
    private String description;
    private BigDecimal price;
    private int cantidad;

    public ProductsDTO(int id,  String nameProduct, String description, BigDecimal price, int cantidad){
        this.id = id;
        this.nameProduct = nameProduct;
        this.description = description;
        this.price = price;
        this.cantidad = cantidad;
    }

    //Convierte productos en un dto para que el frontend acceda a la informacion necesaria
    public static ProductsDTO fromProducts(Products products){
        return new ProductsDTO(
        products.getId(),
        products.getNameProduct(),
        products.getDescription(),
        products.getPrice(),
        products.getCantidad()
        );
    }

    //Getters

    public int getId() {return id;}

    public String getNameProduct() {return nameProduct;}

    public String getDescription() {return description;}

    public BigDecimal getPrice() {return price;}

    public int getCantidad() {return cantidad;}

}
