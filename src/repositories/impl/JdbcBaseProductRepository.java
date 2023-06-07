package repositories.impl;

import exceptions.JdbcException;
import models.Product;
import repositories.ProductRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcBaseProductRepository {
    public static final String JDBC_URL = "jdbc:postgresql://localhost:5432/third_course";
    public static final String JDBC_USER = "postgres";
    public static final String JDBC_PASSWORD = "postgres";

    private Connection connection;

    private Connection getConnection() {
        try {
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            return connection;
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }
    protected PreparedStatement prepareStatement(String sql) {
        try {
            return getConnection().prepareStatement(sql);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    protected void close() {
        if (connection != null) {
            try {
                connection.close();
            }catch (SQLException e) {
                throw new JdbcException(e);
            }
        }
    }

}
