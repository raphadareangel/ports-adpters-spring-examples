package com.zara.zara4.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zara.zara4.exception.ResourceNotFoundException;
import com.zara.zara4.model.Product;
import com.zara.zara4.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
    	Optional<Product> productDB = productRepository.findById(id);
    	if (productDB.isPresent()) {
			return productDB.get();
		}
		throw new ResourceNotFoundException("Product not found for id:" + id);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product updatedProduct) {
        Product dbProduct = getProductById(id);
        dbProduct.setName(updatedProduct.getName());
        return productRepository.save(dbProduct);
    }

    @Override
    public void deleteProduct(Long id) {
    	getProductById(id);
        productRepository.deleteById(id);
    }
}

