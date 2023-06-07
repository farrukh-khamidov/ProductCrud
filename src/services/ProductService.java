package services;

import models.Product;

import java.util.List;

public interface ProductService {

    Product save(Product product);
    Product findById(String id);
    List<Product> findAll();
    void deleteById(String id);

}
