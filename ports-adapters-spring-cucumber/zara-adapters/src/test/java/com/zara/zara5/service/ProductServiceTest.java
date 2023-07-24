package com.zara.zara5.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.zara.zara5.exception.ResourceNotFoundException;
import com.zara.zara5.model.Product;
import com.zara.zara5.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void testGetAllProducts() {
        Product product1 = new Product(1L, "Product 1");
        Product product2 = new Product(2L, "Product 2");
        List<Product> productList = Arrays.asList(product1, product2);

        when(productRepository.findAll()).thenReturn(productList);

        List<Product> result = productService.getAllProducts();

        assertEquals(2, result.size());
        assertEquals("Product 1", result.get(0).getName());
        assertEquals("Product 2", result.get(1).getName());

        verify(productRepository, Mockito.times(1)).findAll();
    }

    @Test
    void testGetProductById() {
        Product product = new Product(1L, "Product 1");

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product result = productService.getProductById(1L);

        assertEquals("Product 1", result.getName());

        verify(productRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void testGetProductByIdThrowsException() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.getProductById(1L));

        verify(productRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void testCreateProduct() {
        Product product = new Product(null, "New Product");
        Product savedProduct = new Product(1L, "New Product");

        when(productRepository.save(product)).thenReturn(savedProduct);

        Product result = productService.createProduct(product);

        assertEquals("New Product", result.getName());

        verify(productRepository, Mockito.times(1)).save(product);
    }

    @Test
    void testUpdateProduct() {
        Product existingProduct = new Product(1L, "Product 1");
        Product updatedProduct = new Product(1L, "Updated Product");

        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(Mockito.any(Product.class))).thenReturn(updatedProduct);

        Product result = productService.updateProduct(1L, updatedProduct);

        assertEquals("Updated Product", result.getName());

        verify(productRepository, Mockito.times(1)).findById(1L);
        verify(productRepository, Mockito.times(1)).save(Mockito.any(Product.class));
    }

    @Test
    void testUpdateProductThrowsException() {
        Product updatedProduct = new Product(1L, "Updated Product");

        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.updateProduct(1L, updatedProduct));

        verify(productRepository, Mockito.times(1)).findById(1L);
        verify(productRepository, Mockito.never()).save(updatedProduct);
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product(1L, "Product 1");

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        productService.deleteProduct(1L);

        verify(productRepository, Mockito.times(1)).findById(1L);
        verify(productRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    void testDeleteProductThrowsException() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.deleteProduct(1L));

        verify(productRepository, Mockito.times(1)).findById(1L);
        verify(productRepository, Mockito.never()).deleteById(1L);
    }
}

