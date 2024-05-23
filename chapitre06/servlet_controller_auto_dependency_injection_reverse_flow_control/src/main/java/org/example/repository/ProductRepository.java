package org.example.repository;


import org.example.dto.Product;
import org.example.framework.CustomRepositoryAnnotation;

import java.util.Arrays;
import java.util.List;

@CustomRepositoryAnnotation
public class ProductRepository {
    public List<Product> getAllProducts() {
        return Arrays.asList(
                new Product(1, "Product 1", 10.0),
                new Product(2, "Product 2", 20.0),
                new Product(3, "Product 3", 30.0)
        );
    }

    public Product getProductById(int id) {
        return new Product(id, "Product " + id, id * 10.0);
    }

    public Product addProduct(Product product) {
        // In a real application, you would persist the product.
        return product;
    }

    public Product updateProduct(Product product) {
        // In a real application, you would update the product in the database.
        return product;
    }
}