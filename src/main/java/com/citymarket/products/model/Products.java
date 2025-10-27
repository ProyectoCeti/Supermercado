package com.citymarket.products.model;

import java.math.BigDecimal;

public class Products {

    private int id;
    private String nameProduct;
    private String description;
    private BigDecimal price;
    private int cantidad;

    public Products(int id,  String nameProduct, String description, BigDecimal price, int cantidad) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.description = description;
        this.price = price;
        this.cantidad = cantidad;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    //Getters
    public int getId() {return id;}

    public String getNameProduct() {return nameProduct;}

    public String getDescription() {return description;}

    public BigDecimal getPrice() {return price;}

    public int getCantidad() {return cantidad;}
}
