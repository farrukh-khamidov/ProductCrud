package repositories.impl;

import models.Product;
import repositories.ProductRepository;
import repositories.jdbc.JdbcAbstractRepository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcProductRepository2 extends JdbcAbstractRepository<Product> implements ProductRepository {

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
        return queryForObject(sql, this::mapRowToProduct);
    }

    @Override
    public List<Product> findAll() {
        String sql = "SELECT * FROM products";
        return query(sql, this::mapRowToProduct);
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM products WHERE id = ?";
        execute(sql, id);
    }

    private Product mapRowToProduct(ResultSet rs){
        try {
            return new Product(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("color"),
                    rs.getInt("size"),
                    rs.getString("store")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
}
