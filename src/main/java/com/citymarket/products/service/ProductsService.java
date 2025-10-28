package com.citymarket.products.service;

import com.citymarket.products.repository.ProductsRepository;
import com.citymarket.products.model.Products;
import com.citymarket.products.dto.ProductsDTO;

import java.math.BigDecimal;

public class ProductsService {

    private ProductsRepository productsRepositoryImpl;

    public ProductsService(ProductsRepository productsRepositoryImpl) {
        this.productsRepositoryImpl = productsRepositoryImpl;

    }

    //Metodos para validar un producto

    //Validamos que se registre al menos un producto
    public boolean isCantidadValid(int cantidad){
        if(cantidad <= 0){
            System.out.println("No puedes agregar 0 productos");
            return false;
        }
        return true;
    }

    //Validamos el precio de un producto
    public boolean isPriceValid(BigDecimal price){
        if(price == null){
            System.out.println("No puedes agregar un producto sin precio");
            return false;
        }
        else if(price.compareTo(BigDecimal.ZERO) <= 0){
            System.out.println("No puedes agregar un producto con un preico menor o igual a 0");
        }
        return true;
    }

    //Validamos que el nombre no este vacio
    public boolean isNameValid(String nameProduct){
        if(nameProduct == null || nameProduct.trim().isEmpty()){
            System.out.println("No puedes agregar un producto sin nombre");
            return false;
        }
        return true;
    }

    //Validamos que el producto tenga una descripcion
    public boolean isDescriptionValid(String description){
        if(description == null || description.trim().isEmpty()){
            System.out.println("No puedes agregar un producto sin descripcion");
            return false;
        }
        return true;
    }

    //Agregamos un cliente verificando que se cumplan todas las validaciones
    public ProductsDTO registerProducts(int id, String nameProduct, String description, BigDecimal price, int cantidad){
        //Validaciones

        if(!isCantidadValid(cantidad)){
            throw new IllegalArgumentException("Cantidad invalida.");
        }

        if(!isNameValid(nameProduct)){
            throw new IllegalArgumentException("Nombre invalido.");
        }

        if(!isDescriptionValid(description)){
            throw new IllegalArgumentException("Descripcion invalida.");
        }

        if(isPriceValid(price)){
            throw new IllegalArgumentException("Precio invalido.");
        }

        //Creamos y guardamos un producto
        Products product = new Products(
                id,
                nameProduct.trim(),
                description != null ? description.trim() : "",
                price,
                cantidad
        );

        Products productsSave = productsRepositoryImpl.save(product);

        //Retornamos el dto
        return ProductsDTO.fromProducts(productsSave);
    }
}
