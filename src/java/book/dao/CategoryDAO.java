/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package book.dao;

import book.connectDatabase.MySQLConnection;
import book.dto.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class CategoryDAO {

    Connection connect = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public Category getDetailCategory(int id) {
        String query = "SELECT * FROM sellbook.category where cateID = ?";
        try {
            connect = new MySQLConnection().getMySQLConnection();
            ps = connect.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return (new Category(rs.getInt(1),
                        rs.getString(2)));
            }
        } catch (Exception e) {
        }

        return null;
    }
}
