package com.example.ecommerceplatform.controller;

import com.example.ecommerceplatform.model.Product;
import com.example.ecommerceplatform.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProductControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testGetAllProducts() throws Exception {
        // Arrange
        List<Product> products = List.of(new Product(), new Product());
        when(productService.getAllProducts()).thenReturn(products);

        // Act and Assert
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testGetProductById() throws Exception {
        // Arrange
        Long productId = 1L;
        Product product = new Product();
        when(productService.getProductById(productId)).thenReturn(Optional.of(product));

        // Act and Assert
        mockMvc.perform(get("/api/products/{id}", productId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetProductById_NotFound() throws Exception {
        // Arrange
        Long productId = 1L;
        when(productService.getProductById(productId)).thenReturn(Optional.empty());

        // Act and Assert
        mockMvc.perform(get("/api/products/{id}", productId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateProduct() throws Exception {
        // Arrange
        Product product = new Product();
        product.setId(1L);
        product.setName("Product 1");
        product.setDescription("Description for Product 1");
        product.setPrice(BigDecimal.valueOf(50.0));
        when(productService.saveProduct(any(Product.class))).thenReturn(product);

        // Act and Assert
        mockMvc.perform(post("/api/products")
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        // Arrange
        Long productId = 1L;

        // Act and Assert
        mockMvc.perform(delete("/api/products/{id}", productId))
                .andExpect(status().isNoContent());

        verify(productService, times(1)).deleteProduct(productId);
    }
}
