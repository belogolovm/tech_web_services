package com.maxart.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgreSQLDAO {

    private Connection connection;

    PostgreSQLDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Picture> getAllPictures() {
        return executeQuery("SELECT * FROM pictures");
    }

    public List<Picture> findPictures(MyRequest request) {
        List<Picture> pictures = new ArrayList<>();
        String sql = "SELECT * FROM pictures WHERE " +
                "(id = " + request.getId() + " OR " + request.getId() + " = 0) AND " +
                "(name = '" + request.getName() + "' OR '" + request.getName() + "' = '' OR '" + request.getName() + "' = '?') AND " +
                "(author = '" + request.getAuthor() + "' OR '" + request.getAuthor() + "' = '' OR '" + request.getAuthor() + "' = '?') AND " +
                "(year = " + request.getYear() + " OR " + request.getYear() + " = 0) AND " +
                "(material = '" + request.getMaterial() + "' OR '" + request.getMaterial() + "' = '' OR '" + request.getMaterial() + "' = '?') AND " +
                "(height = " + request.getHeight() + " OR " + request.getHeight() + " = 0) AND " +
                "(width = " + request.getWidth() + " OR " + request.getWidth() + " = 0)";

        return executeQuery(sql);
    }

    private List<Picture> executeQuery(String sql) {
        List<Picture> pictures = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String author = rs.getString("author");
                int year = rs.getInt("year");
                String material = rs.getString("material");
                float height = rs.getFloat("height");
                float width = rs.getFloat("width");
                Picture picture = new Picture(id, name, author, year, material, height, width);
                pictures.add(picture);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pictures;
    }
}
