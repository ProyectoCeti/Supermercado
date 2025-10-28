package com.citymarket.providers.repository;

import com.citymarket.providers.model.Provider;
import com.citymarket.connection.ConnectionDB;
import com.citymarket.providers.model.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 Implementación de la interfaz ProvidersRepository usando JDBC
*/

public class ProvidersRepositoryImpl implements ProvidersRepository {

    //Metodo para buscar un proveedor por su id
    public Optional<Provider> findById(int id){

        final String query = "SELECT * FROM Proveedores WHERE id = ?";
        try{Connection connetion = new ConnectionDB().getConnection(); //Establecemos la conexion a la base de datos
            PreparedStatement preparedStatement = connetion.prepareStatement(query); //Creamos un PreparedStatement y le pasamos la query
            preparedStatement.setInt(1, id); //Le pasamos el id

            final ResultSet resultSet = preparedStatement.executeQuery();//Guarda y ejecuta la query

            //Obtiene el estatus de la base de datos y la convierte a enum
            String statusString = resultSet.getString("status");
            Status status = Status.valueOf(statusString.toUpperCase()); //Convierte el String a enum
            if(resultSet.next()){
                Provider provider = new Provider(
                    resultSet.getInt("id"),
                    resultSet.getString("nameProvider"),
                    resultSet.getString("email"),
                    resultSet.getString("telefono"),
                    status
                );
                return Optional.of(provider); //Retornamos el proveedor
            }

        }catch(SQLException e){
            System.out.println("Error al buscar el proveedor: " + e.getMessage());
        }
        return Optional.empty(); //Retornamos vacio si no se encontro
    }

    //Mostramos los proveedores ordenados por id
    public List<Provider> findAllOrderedById(){

        List<Provider> providers = new ArrayList<>();

        final String query = "SELECT * FROM Proveedores ORDER BY id";
        try{Connection connetion = new ConnectionDB().getConnection(); //Establecemos una conexion con la base de datos
            PreparedStatement preparedStatement = connetion.prepareStatement(query); //Le pasamos la query y creamos un preparedStatement
            final ResultSet resultSet = preparedStatement.executeQuery();//Guarda y ejecuta la query

            //Obtiene el estatus de la base de datos y la convierte a enum
            String statusString = resultSet.getString("status");
            Status status = Status.valueOf(statusString.toUpperCase()); //Convierte el String a enum

            while(resultSet.next()){ //Recorremos cada registro hasta que ya no quede ninguno
                Provider provider = new Provider(
                        resultSet.getInt("id"),
                        resultSet.getString("nameProvider"),
                        resultSet.getString("email"),
                        resultSet.getString("telefono"),
                        status
                );
                providers.add(provider); //Agregamos los proveedores a una lista
            }

        }catch(SQLException e){
            System.out.println("Error al cargar proveedores: " + e.getMessage());
        }
        return providers; //Retornamos una lista vacia
    }

    //Agregamos un proveedor nuevo
    public Provider save(Provider provider){

        final String update = "INSET INTO Proveedores(nameProvider, email, telefono, status) VALUES (?, ?, ?, ?)";
        try{Connection connetion = new ConnectionDB().getConnection(); //Establecemos la conexion a la base de datos
            PreparedStatement preparedStatement = connetion.prepareStatement(update); //Le pasamos la update y creamos un preparedStatement
            int ProvedorAgregado = preparedStatement.executeUpdate(); //Guarda y ejecuta la update

            //Agregamos los datos del proveedor
            preparedStatement.setString(1, provider.getNameProvider());
            preparedStatement.setString(2, provider.getEmail());
            preparedStatement.setString(3, provider.getTelefono());
            preparedStatement.setString(4, provider.getEstatus().name()); //Convierte el enum a String

            if(ProvedorAgregado > 0){
                ResultSet resultSet = preparedStatement.getGeneratedKeys(); //Obtenemos el id generado por MySQL
                if(resultSet.next()){
                    int newId =  resultSet.getInt(1);

                    //Actualizamos el proveedor pasandole el nuevo id
                    provider = findById(newId).orElse(provider);
                }
            }

        }catch(SQLException e){
            System.out.println("Error al agregar el registro: " + e.getMessage());
        }
        return provider; //Retornamos el proveedor vacio
    }

    public Provider update(Provider provider){

        final String update = "UPDATE SET nameProvider = ?, email = ?, telefono = ?, status = ? WHERE id = ?";
        try{Connection connetion = new ConnectionDB().getConnection(); //Establecemos la conexion a la base de datos
            PreparedStatement preparedStatement = connetion.prepareStatement(update); //Le pasamos la update y crea un preparedStatement
            int ProvedorActualizado = preparedStatement.executeUpdate(); //Guarda y ejecuta la update

            //Agregamos los datos del proveedor
            preparedStatement.setString(1, provider.getNameProvider());
            preparedStatement.setString(2, provider.getEmail());
            preparedStatement.setString(3, provider.getTelefono());
            preparedStatement.setString(4, provider.getEstatus().name()); //Convierte el enum a String

            if(ProvedorActualizado > 0){
                System.out.println("Proveedor actualizado correctamente");
            }
            else{
                System.out.println("Hubo un error al actualizar los datos del proveedor");
            }

        }catch(SQLException e){
            System.out.println("Error al agregar el actualizar datos: " + e.getMessage());
        }
        return provider; //Retornamos el proveedor vacio
    }

    public Boolean deleteById(int id){

        final String update = "DELETE FROM Proveedores WHERE id = ?";
        try{Connection connetion = new ConnectionDB().getConnection(); //Establecemos la conexion a la base de datos
            PreparedStatement preparedStatement = connetion.prepareStatement(update); //Creamos el preparedStatement y le pasamos la update

            int RegistroEliminado = preparedStatement.executeUpdate(); //Guarda y ejecuta la update

            if(RegistroEliminado > 0){
                System.out.println("Registro eliminado correctamente");
            }
            else{
                System.out.println("Hubo un error al eliminar el registro");
            }

        }catch(SQLException e){
            System.out.println("Error al eliminar el registro: " + e.getMessage());
        }
        return false;
    }
}
