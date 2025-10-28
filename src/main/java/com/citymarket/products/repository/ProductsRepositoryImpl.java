package com.citymarket.products.repository;

import com.citymarket.connection.ConnectionDB;
import com.citymarket.products.model.Products;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

public class ProductsRepositoryImpl implements ProductsRepository {

    //Metodo para buscar un producto por su nombre
    public Optional<Products> findByNombre(String nameProduct){
        final String query = "SELECT * FROM productos WHERE name = ?";
        try{Connection connection = new ConnectionDB().getConnection();//Creamos una nueva instancia para despues llamar al metodo getConnection
            PreparedStatement preparedStatement = connection.prepareStatement(query);//Crea un preparedStatement para realizar las consultas de una forma segura
            preparedStatement.setString(1, nameProduct);//Le pasamos el nombre como el valor principal

            ResultSet resultSet = preparedStatement.executeQuery();//Guarda la query y la ejecuta

            if(resultSet.next()){ //Nos muestra el resultado de la busqueda en la base de datos
               Products products = new Products(
                       resultSet.getInt("id"),
                       resultSet.getString("nameProduct"),
                       resultSet.getString("descripcion"),
                       resultSet.getBigDecimal("precio"),
                       resultSet.getInt("cantidad")
                       );
                return Optional.of(products); //Retorna el resultado
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar los datos: " +  e.getMessage());
        }

        return Optional.empty();//No retorna nada si no se encuentra al cliente
    }

    //Metodo para buscar productos con nombres similares
    public List<Products> search(String searchTerm){
        List<Products> products = new ArrayList<>();

        final String query = "SELECT * FROM productos WHERE nameProduct LIKE ? OR descripcion LIKE ?";
        try{Connection connection = new ConnectionDB().getConnection();//Creamos una nueva instancia para despues llamar al metodo getConnection
            PreparedStatement preparedStatement = connection.prepareStatement(query);//Crea un preparedStatement para realizar las consultas de una forma segura

            //Se agrega el % para realizar busquedas parciales
            String likeTerm = "%" + searchTerm + "%";
            preparedStatement.setString(1, likeTerm); // Busca en name
            preparedStatement.setString(2, likeTerm); // Busca en descripcion

            ResultSet resultSet = preparedStatement.executeQuery();//Guarda la query y la ejecuta

            while(resultSet.next()){ //Nos muestra todos los resultados que coincidan con la busqueda
                Products products2 = new Products(
                        resultSet.getInt("id"),
                        resultSet.getString("nameProduct"),
                        resultSet.getString("descripcion"),
                        resultSet.getBigDecimal("precio"),
                        resultSet.getInt("cantidad")
                );

                products.add(products2); //Agregamos en una lista los productos obtenidos
            }

        }catch(SQLException e){
            System.out.println("Error al consultar los datos: " +  e.getMessage());
        }

        return products; //Retornamos la lista esta puede no tener nada
    }

    //Metodo para ver todos los productos
    public List<Products> findAll(){
        List<Products> products = new ArrayList<>();

        final String query = "SELECT * FROM productos";

        try{Connection connection = new ConnectionDB().getConnection();//Establecemos la conexion con la base de datos
            PreparedStatement preparedStatement = connection.prepareStatement(query);//Creamos un preparedStatement para ejecutar las query

            ResultSet resultSet = preparedStatement.executeQuery();//Ejecutamos la query

            while(resultSet.next()){ //Mostramos todos los productos disponibles
                Products products2 = new Products(
                        resultSet.getInt("id"),
                        resultSet.getString("nameProduct"),
                        resultSet.getString("descripcion"),
                        resultSet.getBigDecimal("precio"),
                        resultSet.getInt("cantidad")
                );

                products.add(products2);
            }

        }catch(SQLException e){
            System.out.println("Error al realizar la consulta: " + e.getMessage());
        }
        return products;
    }

    //Metodo para agregar un nuevo producto
    public Products save(Products products){

        final String update = "INSERT INTO productos(nameProduct, cantidad, descripcion, precio) Values(?,?,?,?)";
        try{Connection connection = new ConnectionDB().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(update, Statement.RETURN_GENERATED_KEYS);//Crea un prepardeStetement para las consultas y genera un id
            int ProductoAgregado = preparedStatement.executeUpdate();//Guarda la update y la ejecuta

            //Le pasamos los atributos a agregar
            preparedStatement.setString(1, products.getNameProduct());
            preparedStatement.setInt(2, products.getCantidad());
            preparedStatement.setString(3, products.getDescription());
            preparedStatement.setBigDecimal(4, products.getPrice());

            if(ProductoAgregado > 0){
                ResultSet idGenerado = preparedStatement.getGeneratedKeys(); //Obtenemos el id generado por MySQL
                if(idGenerado.next()){
                    int newId = idGenerado.getInt(1);
                    //Actualizamos el producto con el nuevo id
                    products.setId(newId);
                }
            }

        }catch(SQLException e){
            System.out.println("Error al agregar el registro: " + e.getMessage());
        }
        return products; //Retornamos el producto
    }

    //Metodo para actualizar un producto
    public Products update(Products products){

        final String update = "UPDATE productos SET nameProduct = ?, cantidad = ?, descripcion = ?, precio = ? WHERE id = ?";
        try{Connection connection = new ConnectionDB().getConnection();//Establecemos la conexion a la base de datos
            PreparedStatement preparedStatement = connection.prepareStatement(update); //Creamos un preparedStatement y le pasamos el update

            //Le pasamos los nuevos atributos
            preparedStatement.setString(1, products.getNameProduct());
            preparedStatement.setInt(2, products.getCantidad());
            preparedStatement.setString(3, products.getDescription());
            preparedStatement.setBigDecimal(4, products.getPrice());

            int ProductoActualizado = preparedStatement.executeUpdate();//Guarda y ejecuta la update

            if(ProductoActualizado > 0){
                System.out.println("Producto actualizado exitosamente");
                return products;
            }
            else{
                System.out.println("Error al intentar actualizar");
            }

        }catch(SQLException e){
            System.out.println("Error al actualizar el producto: " + e.getMessage());
        }
        return null; //Retornamos null si no se pudo realizar la actualizacion
    }

    //Metodo para eliminar un producto
    public boolean deleteById(int id){

        final String update = "DELETE FROM productos WHERE id = ?";
        try{Connection connection = new ConnectionDB().getConnection();//Establecemos la conexion a la base de datos
            PreparedStatement preparedStatement = connection.prepareStatement(update);//Crea un objeto preparedStatement y le pasamos la update

            preparedStatement.setInt(1, id);

            int RegistroEliminado = preparedStatement.executeUpdate();//Guarda y ejecuta la update
            if(RegistroEliminado > 0){
                System.out.println("Producto eliminado exitosamente");
            }else{
                System.out.println("Error al intentar elimina el registro");
            }

        }catch(SQLException e){
            System.out.println("No se pudo eliminar el registro: " + e.getMessage());
        }
        return false; //Retornamos false en caso de no haberse eliminado
    }

    //Metodo para ver productos del mas barato al mas caro
    public List<Products> findAllOrderByPrecioAsc(){

        List<Products> productsList = new ArrayList<>();
        final String query = "SELECT * FROM productos ORDER BY precio ASC";
        try{Connection connection = new ConnectionDB().getConnection();//Establecemos una conexion con la base de datos
            PreparedStatement preparedStatement = connection.prepareStatement(query);//Creamos un preparedStatement y le pasamos la query
            ResultSet resultSet = preparedStatement.executeQuery();//Guarda y ejecuta la query

            while(resultSet.next()){ //Mostramos nuestros productos de forma ascendete
                Products products = new Products(
                        resultSet.getInt("id"),
                        resultSet.getString("nameProduct"),
                        resultSet.getString("descripcion"),
                        resultSet.getBigDecimal("precio"),
                        resultSet.getInt("cantidad")
                );
                productsList.add(products); //Agregamos los productos a una lista
            }

        }catch(SQLException e){
            System.out.println("Error al mostrar productos: " + e.getMessage());
        }
        return productsList; //Retornamos una lista vacia
    }

    //Metodo para ver productos del mas caro al mas barato
    public List<Products> findAllOrderByPrecioDesc(){
        List<Products> productsList = new ArrayList<>();
        final String query = "SELECT * FROM productos ORDER BY precio DESC";
        try{Connection connection = new ConnectionDB().getConnection();//Establecemos una conexion con la base de datos
            PreparedStatement preparedStatement = connection.prepareStatement(query);//Creamos un preparedStatement y le pasamos la query
            ResultSet resultSet = preparedStatement.executeQuery();//Guarda y ejecuta la query

            while(resultSet.next()){ //Mostramos nuestros productos de forma descendente
                Products products = new Products(
                        resultSet.getInt("id"),
                        resultSet.getString("nameProduct"),
                        resultSet.getString("descripcion"),
                        resultSet.getBigDecimal("precio"),
                        resultSet.getInt("cantidad")
                );
                productsList.add(products); //Agregamos los productos a una lista
            }

        }catch(SQLException e){
            System.out.println("Error al mostrar productos: " + e.getMessage());
        }
        return productsList; //Retornamos una lista vacia
    }
}
