package com.maxart.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgreSQLDAO {

    private Connection connection;

    PostgreSQLDAO() {
        this.connection = ConnectionUtil.getConnection();
    }

    public List<Picture> findPictures(String id, String name, String author, String year, String material, String height, String width) {
        StringBuilder sb = new StringBuilder("");
        StringBuilder query = new StringBuilder("");
        boolean where = false;
        if (id != null) {
            sb.append("id = ").append(Integer.parseInt(id)).append(" AND ");
            where = true;
        }

        if (name != null) {
            sb.append("name = '").append(name).append("' AND ");
            where = true;
        }

        if (author != null) {
            sb.append("author = '").append(author).append("' AND ");
            where = true;
        }

        if (year != null) {
            sb.append("year = ").append(Integer.parseInt(year)).append(" AND ");
            where = true;
        }

        if (material != null) {
            sb.append("material = '").append(material).append("' AND ");
            where = true;
        }

        if (height != null) {
            sb.append("height = ").append(Float.parseFloat(height)).append(" AND ");
            where = true;
        }

        if (width != null) {
            sb.append("width = ").append(Float.parseFloat(width)).append(" AND ");
            where = true;
        }

        if (where) {
            if (sb.toString().endsWith(" AND ")) {
                sb.setLength(sb.length() - 5);
            }

            query.append("SELECT * FROM pictures WHERE ").append(sb.toString());
        } else {
            query.append("SELECT * FROM pictures");
        }

        return executeQuery(query.toString());
    }

    public List<Picture> findOne(int id) {
        String query = "SELECT * FROM pictures WHERE id = " + id;
        List<Picture> pictures = executeQuery(query);
        return pictures;
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
