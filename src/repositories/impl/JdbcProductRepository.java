package repositories.impl;

import exceptions.JdbcException;
import models.Product;
import repositories.ProductRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcProductRepository extends JdbcBaseProductRepository implements ProductRepository {

    @Override
    public Product save(Product product) {
        try {

            String sql = "INSERT INTO products(id, name, color, size, store) " +
                    "VALUES(?, ?, ?, ?, ?)";
            PreparedStatement statement = prepareStatement(sql);

            statement.setString(1, product.getId());
            statement.setString(2, product.getName());
            statement.setString(3, product.getColor());
            statement.setInt(4, product.getSize());
            statement.setString(5, product.getStore());

            System.out.println(sql);
            System.out.println(statement.executeUpdate());

            close();

        } catch (SQLException e) {
            throw new JdbcException(e);
        }



        return product;
    }

    @Override
    public Product findById(String id) {

        try {
            String sql = "SELECT * FROM products WHERE id = ?";
            PreparedStatement statement = prepareStatement(sql);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String pid = resultSet.getString("id");
                String name = resultSet.getString("name");
                String color = resultSet.getString("color");
                int size = resultSet.getInt("size");
                String store = resultSet.getString("store");
                Product product = new Product(pid, name, color, size, store);
                return product;
            }
            close();
            return null;
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    @Override
    public List<Product> findAll() {
        try {
            String sql = "SELECT * FROM products";
            PreparedStatement statement = prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String color = resultSet.getString("color");
                int size = resultSet.getInt("size");
                String store = resultSet.getString("store");
                Product product = new Product(id, name, color, size, store);
                products.add(product);
            }
            close();
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            String sql = "DELETE FROM products WHERE id = ?";
            PreparedStatement statement = prepareStatement(sql);
            statement.setString(1, id);
            statement.executeUpdate();
            close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
