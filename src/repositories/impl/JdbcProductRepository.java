package repositories.impl;

import exceptions.JdbcException;
import models.Product;
import repositories.ProductRepository;
import repositories.jdbc.JdbcAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcProductRepository extends JdbcAbstractRepository<Product> implements ProductRepository {

    public static final String JDBC_URL = "jdbc:postgresql://localhost:5432/third_course";
    public static final String JDBC_USER = "postgres";
    public static final String JDBC_PASSWORD = "postgres";

    @Override
    public Product save(Product product) {
        String sql = "INSERT INTO products(id, name, color, size, store) " +
                "VALUES(?, ?, ?, ?, ?)";
        execute(sql, product.getId(), product.getName(), product.getColor(), product.getSize(), product.getStore());

        return product;
    }

    @Override
    public Product findById(String id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        return queryForObject(sql, this::mapToProduct,id);
    }

    @Override
    public List<Product> findAll() {
        String sql = "SELECT * FROM products";
        return query(sql, this::mapToProduct);
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM products WHERE id = ?";
        execute(sql, id);
    }

    @Override
    protected String getJdbcUrl() {
        return JDBC_URL;
    }

    @Override
    protected String getJdbcUser() {
        return JDBC_USER;
    }

    @Override
    protected String getJdbcPassword() {
        return JDBC_PASSWORD;
    }

    private Product mapToProduct(ResultSet rs) {
        try {
            return new Product(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("color"),
                    rs.getInt("size"),
                    rs.getString("store")
            );
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }
}
