package com.citymarket.customers.repository;

/*
 Implementación de la interfaz CustomerRepository usando JDBC
*/
import com.citymarket.connection.ConnectionDB;
import com.citymarket.customers.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerRepositoryImpl implements CustomerRepository {

    //Encuentra un cliente por su id
    public Optional<Customer> findById(int id) {
        final String query = "SELECT * FROM Customer WHERE id = ?";
        try{ Connection connection = new ConnectionDB().getConnection(); //Creamos una nueva instancia para despues llamar al metodo getConnection
            PreparedStatement preparedStatement = connection.prepareStatement(query);//Crea un preparedStatement para realizar las consultas de una forma segura
            preparedStatement.setInt(1, id); //Le pasamos el id como el valor principal

            final ResultSet resultSet = preparedStatement.executeQuery();//Guarda la query y la ejecuta
            if(resultSet.next()){//Nos muestra cada campo de la tabla de uno en uno
                Customer customer = new Customer(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("addres"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getBigDecimal("saldo"),
                    resultSet.getTimestamp("createdAt").toLocalDateTime(),
                    resultSet.getTimestamp("updateAt").toLocalDateTime()
                );
                return Optional.of(customer); //Retorna el resultado
            }

        }catch(SQLException e){
            System.out.println("Error al buscar el registro: " +  e.getMessage());
        }
        return Optional.empty(); //No retorna nada en caso de no haber encontrado el cliente
    }

    //Ordena los clientes por id
    public List<Customer> findAllOrderedById(){

        List <Customer> customers = new ArrayList<>();
        final String query = "SELECT * FROM Customer ORDER BY id";

        try{ Connection connection = new ConnectionDB().getConnection(); //Creamos una nueva instancia para despues llamar al metodo getConnection
        PreparedStatement preparedStatement = connection.prepareStatement(query);//Crea un preparedStatement para realizar las consultas de una forma segura
        ResultSet resultSet = preparedStatement.executeQuery();//Guarda la query y la ejecuta

            while(resultSet.next()){ //Nos muestra cada campo ordenados por su id
                Customer customer = new Customer(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("addres"),
                resultSet.getString("email"),
                resultSet.getString("password"),
                resultSet.getBigDecimal("saldo"),
                resultSet.getTimestamp("createdAt").toLocalDateTime(),
                resultSet.getTimestamp("updateAt").toLocalDateTime()
                );
                customers.add(customer);
            }

        }catch(SQLException e){
            System.out.println("Error al ordenar las listas: " +  e.getMessage());
        }
        return customers; //Nos retorna la lista de customers
    }

    //Guardamos nuevos clientes

    public Customer save(Customer customer){

        final String Update = "INSERT INTO Customer(name, addres, email, password, saldo) Values(?,?,?,?,?)";
        try{ Connection connection = new ConnectionDB().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Update, Statement.RETURN_GENERATED_KEYS);//Crea un preparedStatement para realizar las consultas de una forma segura
            int ClienteAgregado = preparedStatement.executeUpdate();//Guarda la update y la ejecuta

            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getAddress());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setString(4, customer.getPassword());
            preparedStatement.setBigDecimal(5, customer.getSaldo());

            if(ClienteAgregado > 0){
                ResultSet idGenerado = preparedStatement.getGeneratedKeys();//Obtenemos el id generado por MySQl
                if (idGenerado.next()) {
                    int newId = idGenerado.getInt(1);
                    // Actualizar el cliente con el ID generado
                    customer = findById(newId).orElse(customer);
                }
            }

        }catch(SQLException e){
            System.out.println("Error al agregar Cliente: " +  e.getMessage());
        }
        return customer; //Retornamos el cliente
    }

    //Actualizamos Datos de un cliente
    public Customer update(Customer customer){

        final String Update = "Update Customer SET name = ?, addres = ?, email = ?, password = ?, saldo = ? WHERE id = ?";
        try{ Connection connection = new ConnectionDB().getConnection(); //Establecemos la conexion con la base de datos
            PreparedStatement preparedStatement = connection.prepareStatement(Update); //Le pasamos la executeQuery y creamos un preparedStatement

            //Agregamos los nuevos valores
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getAddress());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setString(4, customer.getPassword());
            preparedStatement.setBigDecimal(5, customer.getSaldo());
            preparedStatement.setInt(6, customer.getId());

            int clienteAct = preparedStatement.executeUpdate(); //Ejecutamos la update

            if(clienteAct > 0){
                System.out.println("Cliente actualizado exitosamente");
                return customer;
            }
            else{
                System.out.println("No se pudo actualizar el cliente");
            }
        }catch(SQLException e){
            System.out.println("Error al actualizar cliente: " + e.getMessage());
        }

        return null; //Retornamos nulo en caso de no haberse agregado
    }

    //Eliminamos un cliente
    public boolean deleteById(int id){

        final String Update = "DELETE FROM Customer WHERE id = ?";
        try{ Connection connection = new ConnectionDB().getConnection();//Establecemos la conexion con la base de datos
            PreparedStatement preparedStatement = connection.prepareStatement(Update);//Le pasamos la executeQuery y creamos un preparedStatement

            int RegistroEliminado = preparedStatement.executeUpdate(); //Ejecutamos la update
            if(RegistroEliminado > 0){
                System.out.println("Registro elimando exitosamente");
            }
            else{
                System.out.println("No se pudo eliminar el registro");
            }

        }catch(SQLException e){
            System.out.println("No se pudo realizar la eliminacion del registro: " + e.getMessage());
        }
        return false; //Retornamos false si no se pudo eliminar
    }

    //Buscamos un cliente que ya existe por su nombre
    public List <Customer> findByName(String name){

        List <Customer> customers = new ArrayList<>();

        final String query = "SELECT * FROM Customer WHERE name LIKE ?";
        try {
            Connection connection = new ConnectionDB().getConnection(); //Creamos una nueva instancia para despues llamar al metodo getConnection
            PreparedStatement preparedStatement = connection.prepareStatement(query);//Crea un preparedStatement para realizar las consultas de una forma segura
            preparedStatement.setString(1 , "%" + name + "%");//Le pasamos el id como el valor principal

            ResultSet resultSet = preparedStatement.executeQuery(); //Ejecuta la query y la guarda
            while(resultSet.next()){
                Customer customer = new Customer(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("addres"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getBigDecimal("saldo"),
                        resultSet.getTimestamp("createdAt").toLocalDateTime(),
                        resultSet.getTimestamp("updateAt").toLocalDateTime()
                );
                customers.add(customer); //Almacena el resultado
            }
        }catch(SQLException e){
            System.out.println("Error al buscar cliente: " + e.getMessage());
        }
        return customers; //Retorna la lista de clientes
    }

    //Buscamos un cliente por su email
    public Optional<Customer> findByEmail(String email) {
        final String query = "SELECT * FROM Customer WHERE email = ?";

        try {
            Connection connection = new ConnectionDB().getConnection(); //Creamos una nueva instancia para despues llamar al metodo getConnection
            PreparedStatement preparedStatement = connection.prepareStatement(query);//Crea un preparedStatement para realizar las consultas de una forma segura
            preparedStatement.setString(1, email);//Le pasamos el id como el valor principal

            ResultSet resultSet = preparedStatement.executeQuery(); //Ejecuta la query y la guarda

            if(resultSet.next()){//Nos muestra cada campo de la tabla de uno en uno
                Customer customer = new Customer(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("addres"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getBigDecimal("saldo"),
                        resultSet.getTimestamp("createdAt").toLocalDateTime(),
                        resultSet.getTimestamp("updateAt").toLocalDateTime()
                );
                return Optional.of(customer); //Retorna el resultado
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar cliente: " + e.getMessage());
        }
        return Optional.empty();//No retorna nada
    }
}
