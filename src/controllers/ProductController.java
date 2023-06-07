package controllers;

import models.Product;
import services.ProductService;
import services.impl.ProductServiceImpl;

import java.util.List;

public class ProductController {
    private static ProductService productService = new ProductServiceImpl();
    public static class Insert {
        public static void main(String[] args) {
            Product product = new Product("p16", "Ko'ylak", "Qora", 60, "Jizzax");
            productService.save(product);
        }
    }

    public static class GetById {
        public static void main(String[] args) {
            String id = "p16";
            System.out.println(productService.findById(id));
        }
    }

    public static class GetAll {
        public static void main(String[] args) {
            List<Product> all = productService.findAll();
            all.forEach(System.out::println);
        }
    }

    public static class DeleteById {
        public static void main(String[] args) {
            String id = "p15";
           productService.deleteById(id);
        }
    }

}
