package repositories;

import models.Product;

import java.util.List;

public interface ProductRepository {

    Product save(Product product);
    Product findById(String id);
    List<Product> findAll();
    void delete(String id);
}
