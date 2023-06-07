package services.impl;

import models.Product;
import repositories.ProductRepository;
import repositories.impl.JdbcProductRepository;
import repositories.impl.JdbcProductRepository2;
import repositories.impl.MapProductRepository;
import services.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository = new JdbcProductRepository2();

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findById(String id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        productRepository.delete(id);
    }
}
