package com.citymarket.providers.repository;

import com.citymarket.providers.model.Provider;

import java.util.List;
import java.util.Optional;

/*
Repository para la gestion de proveedores
 */

public interface ProvidersRepository {

    Optional<Provider> findById(int id); //Busca un proveedor pos su id
    List<Provider> findAllOrderedById(); //Ordena un proveedor por su id
    Provider save(Provider provider); //Agrega un nuevo proveedor
    Provider update(Provider provider); //Actualiza los datos de un proveedor
    Boolean deleteById(int id); //Eliminamos un proveedor mediante su id
}
