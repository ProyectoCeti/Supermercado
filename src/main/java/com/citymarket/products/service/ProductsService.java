package com.citymarket.products.service;

import com.citymarket.products.repository.ProductsRepository;
import com.citymarket.products.repository.ProductsRepositoryImpl;
import com.citymarket.products.model.Products;
import com.citymarket.products.dto.ProductsDTO;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductsService {

    private ProductsRepository productsRepository;

    public ProductsService() {
        this.productsRepository = new ProductsRepositoryImpl();
    }

    public boolean isCantidadValid(int cantidad) {
        return cantidad > 0;
    }

    public boolean isPriceValid(java.math.BigDecimal price) {
        return price != null && price.compareTo(java.math.BigDecimal.ZERO) > 0;
    }

    public boolean isNameValid(String nameProduct) {
        return nameProduct != null && !nameProduct.trim().isEmpty();
    }

    public boolean isDescriptionValid(String description) {
        return description != null && !description.trim().isEmpty();
    }

    public ProductsDTO registerProducts(int id, String nameProduct, String description, java.math.BigDecimal price, int cantidad) {
        if (!isCantidadValid(cantidad)) {
            throw new IllegalArgumentException("Cantidad invalida.");
        }
        if (!isNameValid(nameProduct)) {
            throw new IllegalArgumentException("Nombre invalido.");
        }
        if (!isDescriptionValid(description)) {
            throw new IllegalArgumentException("Descripcion invalida.");
        }
        if (!isPriceValid(price)) {
            throw new IllegalArgumentException("Precio invalido.");
        }

        Products product = new Products(id, nameProduct.trim(), description.trim(), price, cantidad);
        Products productsSave = productsRepository.save(product);
        return ProductsDTO.fromProducts(productsSave);
    }

    public List<ProductsDTO> findAll() {
        List<Products> products = productsRepository.findAll();
        return products.stream()
                .map(ProductsDTO::fromProducts)
                .collect(Collectors.toList());
    }

    public List<ProductsDTO> findByName(String name) {
        List<Products> products = productsRepository.search(name);
        return products.stream()
                .map(ProductsDTO::fromProducts)
                .collect(Collectors.toList());
    }

    public Optional<ProductsDTO> findById(int id) {
        List<Products> allProducts = productsRepository.findAll();
        return allProducts.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .map(ProductsDTO::fromProducts);
    }

    public List<ProductsDTO> findSimilarProducts(String name) {
        return findByName(name);
    }

    public List<ProductsDTO> findAllOrderByPrecioAsc() {
        List<Products> products = productsRepository.findAllOrderByPrecioAsc();
        return products.stream()
                .map(ProductsDTO::fromProducts)
                .collect(Collectors.toList());
    }

    public List<ProductsDTO> findAllOrderByPrecioDesc() {
        List<Products> products = productsRepository.findAllOrderByPrecioDesc();
        return products.stream()
                .map(ProductsDTO::fromProducts)
                .collect(Collectors.toList());
    }
}