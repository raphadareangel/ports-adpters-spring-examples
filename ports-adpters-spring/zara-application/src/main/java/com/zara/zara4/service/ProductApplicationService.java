package com.zara.zara4.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zara.zara4.model.Product;

@Service
public class ProductApplicationService {

    private final ProductService productService;

    public ProductApplicationService(ProductService productService) {
        this.productService = productService;
    }

    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    public Product getProductById(Long id) {
    	return productService.getProductById(id);
    }

    public Product createProduct(Product product) {
        return productService.createProduct(product);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        return productService.updateProduct(id, updatedProduct);
    }

    public void deleteProduct(Long id) {
        productService.deleteProduct(id);
    }
}

