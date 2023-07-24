package com.zara.zara4.service;

import java.util.List;

import com.zara.zara4.model.Product;

public interface ProductService {

    public List<Product> getAllProducts() ;

    public Product getProductById(Long id) ;

    public Product createProduct(Product product) ;

    public Product updateProduct(Long id, Product updatedProduct) ;

    public void deleteProduct(Long id) ;
}

