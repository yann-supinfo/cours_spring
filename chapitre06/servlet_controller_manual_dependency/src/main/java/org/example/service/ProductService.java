package org.example.service;

import org.example.dto.Product;
import org.example.repository.ProductRepository;

import java.util.List;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    public Product getProductById(int id) {
        return productRepository.getProductById(id);
    }

    public Product addProduct(Product product) {
        return productRepository.addProduct(product);
    }

    public Product updateProduct(Product product) {
        return productRepository.updateProduct(product);
    }
}