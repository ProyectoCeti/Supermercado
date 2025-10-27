package com.citymarket.products.repository;

import com.citymarket.products.model.Products;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository {

    Optional<Products> findByNombre(String nombre); //Buscamos un producto por su nombre
    List<Products> search(String searchTerm); //Buscamos un producto de forma parcial
    List<Products> findAll(); //Obtenemos todos los productos
    Products save(Products products); //Guardamos un nuevo producto
    Products update(Products products); //Actualizamos los datos de un producto
    boolean deleteById(int id); //Eliminamos un producto por su nombre
    List<Products> findAllOrderByPrecioAsc(); //Mostramos primero los productos mas baratos
    List<Products> findAllOrderByPrecioDesc(); //Mostramos primero los productos mas caros
    List<Products> findAllOrderByNombre(); //Ordenamos un producto por orden alfabético

}
