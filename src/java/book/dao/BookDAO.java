/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package book.dao;

import book.connectDatabase.MySQLConnection;
import book.dto.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class BookDAO {
    Connection connect = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public List<Book> getAllBook()
    {
        List<Book> list = new ArrayList<>();
        String query = "SELECT * FROM sellbook.book";
        try
        {
            connect = new MySQLConnection().getMySQLConnection();
            ps = connect.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next())
            {
                list.add(new Book( rs.getInt(1),
                                   rs.getInt(2),
                                    rs.getString(3),
                                   rs.getString(4),
                                   rs.getDouble(5),
                               rs.getString(6)));
            }
        }catch(Exception e)
        {}
        
        return list;
    }    
    
    public Book getDetailProductByID(String id)
    {
        String query = "SELECT * FROM sellbook.book where id = ?";
        try
        {
            connect = new MySQLConnection().getMySQLConnection();
            ps = connect.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while(rs.next())
            {
                return (new Book( rs.getInt(1),
                                   rs.getInt(2),
                                    rs.getString(3),
                                   rs.getString(4),
                                   rs.getDouble(5),
                               rs.getString(6)));
            }
        }catch(Exception e)
        {}       
        return null;
    }
    
    public int getCategoryID(String id)
    {
        
        String query = "SELECT cateID FROM sellbook.book where id = ?";
        try
        {
            connect = new MySQLConnection().getMySQLConnection();
            ps = connect.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while(rs.next())
            {
                return rs.getInt(1);
            }
        }catch(Exception e)
        {}       
        return -1;
    }
}
