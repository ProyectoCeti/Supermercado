package com.citymarket.connection;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionDB {

    private Connection con; //Lamamos un objeto de tipo conexion

    public ConnectionDB() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");//Le pasamos el driver
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/citymarket","root","");//Le pasamos la direccion y el usuario

        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    //Accedemos a la conexion
    public Connection getConnection(){return con;}

    //Metodo para cerrar la conexion
    public void CloseConnection(){
        try{
            //Verficamos que la conexion no sea nula o que ya este cerrada
            if(con != null && !con.isClosed()){
                con.close();
                System.out.println("Connection closed");
            }
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
