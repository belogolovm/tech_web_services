package com.maxart.service;

import com.maxart.service.exceptions.IllegalIdException;
import com.maxart.service.exceptions.InsertingException;
import com.maxart.service.exceptions.InvalidEntityException;

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
        return executeQuery(query);
    }

    public int createPicture(Picture picture) throws InsertingException {
        String sql = "INSERT INTO pictures (name, author, year, material, height, width) VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        int id = 0;
        try {
            preparedStatement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, picture.getName());
            preparedStatement.setString(2, picture.getAuthor());
            preparedStatement.setInt(3, picture.getYear());
            preparedStatement.setString(4, picture.getMaterial());
            preparedStatement.setFloat(5, picture.getHeight());
            preparedStatement.setFloat(6, picture.getWidth());

            int affectedRows = preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = (int) generatedKeys.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (id == 0) {
            throw InsertingException.DEFAULT_INSTANCE;
        }

        return id;
    }

    public int updatePicture(int id, Picture picture) throws InvalidEntityException {
        String sql = "UPDATE pictures SET" + createUpdateQuery(picture) + " WHERE id=?";
        PreparedStatement preparedStatement = null;
        int affectedRows = 0;
        try {
            preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            affectedRows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (affectedRows == 0) {
            throw InvalidEntityException.DEFAULT_INSTANCE;
        }

        return affectedRows;
    }

    public int deletePicture(int id) throws InvalidEntityException {
        String sql = "DELETE FROM pictures WHERE id = ?";

        PreparedStatement preparedStatement = null;
        int affectedRows = 0;
        try {
            preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            affectedRows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (affectedRows == 0) {
            throw InvalidEntityException.DEFAULT_INSTANCE;
        }

        return affectedRows;
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

    private String createUpdateQuery(Picture picture) {
        StringBuilder stringBuilderField = new StringBuilder("(");
        StringBuilder stringBuilderValues = new StringBuilder("(");
        if (picture.getId() > 0) {
            stringBuilderField.append("id,");
            stringBuilderValues.append(picture.getId()).append(",");
        }

        if (picture.getName() != null) {
            stringBuilderField.append("name,");
            stringBuilderValues.append("'").append(picture.getName()).append("',");
        }

        if (picture.getAuthor() != null) {
            stringBuilderField.append("author,");
            stringBuilderValues.append("'").append(picture.getAuthor()).append("',");
        }

        if (picture.getYear() > 0) {
            stringBuilderField.append("year,");
            stringBuilderValues.append(picture.getYear()).append(",");
        }

        if (picture.getMaterial() != null) {
            stringBuilderField.append("material,");
            stringBuilderValues.append("'").append(picture.getMaterial()).append("',");
        }

        if (picture.getHeight() > 0) {
            stringBuilderField.append("height,");
            stringBuilderValues.append(picture.getHeight()).append(",");
        }

        if (picture.getWidth() > 0) {
            stringBuilderField.append("width,");
            stringBuilderValues.append(picture.getWidth()).append(",");
        }

        if (stringBuilderField.toString().endsWith(",")) {
            stringBuilderField.setLength(stringBuilderField.length() - 1);
            stringBuilderValues.setLength(stringBuilderValues.length() - 1);
        }

        stringBuilderField.append(")");
        stringBuilderValues.append(")");

        return stringBuilderField.toString() + " = " + stringBuilderValues.toString();
    }
}
