package com.example.ecommerceplatform.service;

import com.example.ecommerceplatform.model.Cart;
import com.example.ecommerceplatform.repository.CartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CartServiceTest {

    @InjectMocks
    private CartService cartService;

    @Mock
    private CartRepository cartRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCartById() {
        // Arrange
        Long cartId = 1L;
        Cart cart = new Cart();
        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));

        // Act
        Optional<Cart> result = cartService.getCartById(cartId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(cart, result.get());
    }

    @Test
    public void testGetCartById_NotFound() {
        // Arrange
        Long cartId = 1L;
        when(cartRepository.findById(cartId)).thenReturn(Optional.empty());

        // Act
        Optional<Cart> result = cartService.getCartById(cartId);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    public void testSaveCart() {
        // Arrange
        Cart cart = new Cart();
        when(cartRepository.save(cart)).thenReturn(cart);

        // Act
        Cart result = cartService.saveCart(cart);

        // Assert
        assertNotNull(result);
    }

    @Test
    public void testDeleteCart() {
        // Arrange
        Long cartId = 1L;

        // Act
        cartService.deleteCart(cartId);

        // Assert
        verify(cartRepository, times(1)).deleteById(cartId);
    }
}