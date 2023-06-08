/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package book.connectDatabase;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ADMIN
 */
public class MySQLConnection {
    private String dbName = "sellbook";
    private String userName = "root";
    private String password = "tin2002t";
    
    public Connection getMySQLConnection() throws Exception
    {
        String connectionURL = "jdbc:mysql://localhost:3306/" + dbName;       
        Class.forName("com.mysql.jdbc.Driver");     
        
        Connection conn = DriverManager.getConnection(connectionURL, userName, password);
        return conn;
    }
}
