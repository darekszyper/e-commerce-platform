package com.example.ecommerceplatform.controller;

import com.example.ecommerceplatform.model.Cart;
import com.example.ecommerceplatform.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CartControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private CartController cartController;

    @Mock
    private CartService cartService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();
    }

    @Test
    public void testGetCartById() throws Exception {
        // Arrange
        Long cartId = 1L;
        Cart cart = new Cart();
        when(cartService.getCartById(cartId)).thenReturn(Optional.of(cart));

        // Act and Assert
        mockMvc.perform(get("/api/carts/{id}", cartId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetCartById_NotFound() throws Exception {
        // Arrange
        Long cartId = 1L;
        when(cartService.getCartById(cartId)).thenReturn(Optional.empty());

        // Act and Assert
        mockMvc.perform(get("/api/carts/{id}", cartId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateCart() throws Exception {
        // Arrange
        Cart cart = new Cart();
        cart.setId(1L);
        when(cartService.saveCart(any(Cart.class))).thenReturn(cart);

        // Act and Assert
        mockMvc.perform(post("/api/carts")
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testDeleteCart() throws Exception {
        // Arrange
        Long cartId = 1L;

        // Act and Assert
        mockMvc.perform(delete("/api/carts/{id}", cartId))
                .andExpect(status().isNoContent());

        verify(cartService, times(1)).deleteCart(cartId);
    }
}