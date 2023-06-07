package repositories.impl;

import models.Product;
import repositories.ProductRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapProductRepository implements ProductRepository {

    private Map<String, Product> map = new HashMap<>();

    @Override
    public Product save(Product product) {
        if (product.getId() == null) {
            map.put(product.getId(), product);
            // insert
        } else {
            // update
            map.put(product.getId(), product);
        }
        return product;
    }

    @Override
    public Product findById(String id) {
        return map.get(id);
    }

    @Override
    public List<Product> findAll() {
        return map.values().stream().toList();
    }

    @Override
    public void delete(String id) {
        map.remove(id);
    }
}
