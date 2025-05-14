package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection conectar() throws SQLException {
        String url = "jdbc:sqlserver://18.229.68.43:2002;databaseName=db_visual_hs_teste;encrypt=false";
        String user = "rodolfoBernardo";
        String password = "oO5j2pH11FAu";
        return DriverManager.getConnection(url, user, password);
    }
}
