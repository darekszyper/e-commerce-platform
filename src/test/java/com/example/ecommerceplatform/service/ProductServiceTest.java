package com.example.ecommerceplatform.service;

import com.example.ecommerceplatform.model.Product;
import com.example.ecommerceplatform.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        List<Product> products = List.of(new Product(), new Product());
        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getAllProducts();

        assertEquals(2, result.size());
    }

    @Test
    public void testGetProductById() {
        long productId = 1L;
        Product product = new Product();
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Optional<Product> result = productService.getProductById(productId);

        assertTrue(result.isPresent());
    }

    @Test
    public void testGetProductById_NotFound() {
        long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        Optional<Product> result = productService.getProductById(productId);

        assertFalse(result.isPresent());
    }

    @Test
    public void testSaveProduct() {
        Product product = new Product();
        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.saveProduct(product);

        assertNotNull(result);
    }

    @Test
    public void testDeleteProduct() {
        long productId = 1L;

        productService.deleteProduct(productId);

        verify(productRepository, times(1)).deleteById(productId);
    }
}
