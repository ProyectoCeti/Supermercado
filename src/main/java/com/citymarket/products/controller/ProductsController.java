package com.citymarket.products.controller;

import com.citymarket.products.service.ProductsService;
import com.citymarket.products.dto.ProductsDTO;
import java.util.List;
import java.util.Optional;

public class ProductsController {

    private ProductsService productsService;

    public ProductsController() {
        this.productsService = new ProductsService();
    }

    public List<ProductsDTO> obtenerTodosProductos() {
        return productsService.findAll();
    }

    public List<ProductsDTO> buscarProductos(String nombre) {
        return productsService.findByName(nombre);
    }

    public Optional<ProductsDTO> obtenerProductoPorId(int id) {
        return productsService.findById(id);
    }

    public List<ProductsDTO> obtenerProductosSimilares(String nombre) {
        return productsService.findSimilarProducts(nombre);
    }

    public List<ProductsDTO> obtenerProductosPorPrecioAsc() {
        return productsService.findAllOrderByPrecioAsc();
    }

    public List<ProductsDTO> obtenerProductosPorPrecioDesc() {
        return productsService.findAllOrderByPrecioDesc();
    }
}