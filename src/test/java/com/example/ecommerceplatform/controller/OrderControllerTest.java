package com.example.ecommerceplatform.controller;

import com.example.ecommerceplatform.model.Order;
import com.example.ecommerceplatform.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class OrderControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    public void testGetOrderById() throws Exception {
        // Arrange
        Long orderId = 1L;
        Order order = new Order();
        when(orderService.getOrderById(orderId)).thenReturn(Optional.of(order));

        // Act and Assert
        mockMvc.perform(get("/api/orders/{id}", orderId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetOrderById_NotFound() throws Exception {
        // Arrange
        Long orderId = 1L;
        when(orderService.getOrderById(orderId)).thenReturn(Optional.empty());

        // Act and Assert
        mockMvc.perform(get("/api/orders/{id}", orderId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateOrder() throws Exception {
        // Arrange
        Order order = new Order();
        order.setId(1L);
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(BigDecimal.valueOf(100.0));
        when(orderService.saveOrder(any(Order.class))).thenReturn(order);

        // Act and Assert
        mockMvc.perform(post("/api/orders")
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testDeleteOrder() throws Exception {
        // Arrange
        Long orderId = 1L;

        // Act and Assert
        mockMvc.perform(delete("/api/orders/{id}", orderId))
                .andExpect(status().isNoContent());

        verify(orderService, times(1)).deleteOrder(orderId);
    }
}