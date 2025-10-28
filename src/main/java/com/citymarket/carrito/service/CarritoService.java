package com.citymarket.carrito.service;

import com.citymarket.products.model.Products;

import java.util.*;
import java.math.BigDecimal;

public class CarritoService {

    public static class CarritoItem {
        private Products producto;
        private int cantidad;
        private BigDecimal subtotal;

        public CarritoItem(Products producto, int cantidad) {
            this.producto = producto;
            this.cantidad = cantidad;
            calcularSubtotal();
        }

        public void calcularSubtotal() {
            this.subtotal = producto.getPrice().multiply(new BigDecimal(cantidad));
        }

        // Getters y setters...
        public Products getProducto() {return producto;}

        public int getCantidad() {return cantidad;}

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
            calcularSubtotal();
        }

        public BigDecimal getSubtotal() { return subtotal; }
    }

    private Map<Integer,CarritoItem> items = new HashMap<>();

    //Metodo para agregar un producto al carrito
    public void agregarProducto(Products products, int cantidad ){

        //Validamos que no sean productos nulos
        if(products == null){
            throw new  IllegalArgumentException("El producto no puede ser nulo.");
        }

        //Validamos que guarde al menos un producto
        if(cantidad <= 0){
            throw new IllegalArgumentException("El cantidad no puede ser menor o igual a 0");
        }

        if(items.containsKey(products.getId())){ //Verificamos si el carrito ya tiene un producto con este id
            CarritoItem item = items.get(products.getId()); //Obtiene el producto con ese id
            item.setCantidad(item.getCantidad() + cantidad); //Suma la cantidad de ese producto con la anterior
        } else {
            CarritoItem nuevoItem = new CarritoItem(products, cantidad);
            items.put(products.getId(), nuevoItem); //Si no estaba agrega el nuevo producto al carrito
        }

    }

    //Quitamos un producto del carrito
    public void eliminarProducto(int id){
        if(items.containsKey(id)){
            items.remove(id);
        }
    }

    //Limpiamos el carrito
    public void limpiarCarrito(){
        items.clear();
    }

    //Verificamos si un producto existe
    public boolean contieneProducto(int id) {
        return items.containsKey(id);
    }

    //Obtenemos la lista de productos agregados
    public Collection<CarritoItem> getItems(){
        return items.values();
    }

    //Calculamos el total general de todos los productos
    public BigDecimal calcularTotal() {
        return items.values().stream() //Obtiene la lista de items y las convierte a stream
                .map(CarritoItem::getSubtotal) //Saca el subtotal de cada producto
                .reduce(BigDecimal.ZERO, BigDecimal::add); //Este combina todos los valores y los convierte en uno solo
    }
}
