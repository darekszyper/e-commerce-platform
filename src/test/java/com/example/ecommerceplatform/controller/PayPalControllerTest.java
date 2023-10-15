package com.example.ecommerceplatform.controller;

import com.example.ecommerceplatform.api.PaymentRequest;
import com.example.ecommerceplatform.controller.PayPalController;
import com.example.ecommerceplatform.service.PayPalService;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class PayPalControllerTest {

    @InjectMocks
    private PayPalController payPalController;

    @Mock
    private PayPalService payPalService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreatePayment() throws PayPalRESTException {
        // Arrange
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setAmount("100.00");
        paymentRequest.setCurrency("USD");

        // Mock the PayPalService createPayment method to return a mocked Payment
        Payment mockPayment = new Payment();
        when(payPalService.createPayment("100.00", "USD", "paypal", "sale")).thenReturn(mockPayment);

        // Act
        Payment result = payPalController.createPayment(paymentRequest);

        // Assert
        assertNotNull(result);
        // Add more assertions as needed
    }

    @Test
    public void testExecutePayment() throws PayPalRESTException {
        // Arrange
        String paymentId = "your_payment_id";
        String payerId = "your_payer_id";

        // Mock the PayPalService executePayment method to return a mocked Payment
        Payment mockPayment = new Payment();
        when(payPalService.executePayment(paymentId, payerId)).thenReturn(mockPayment);

        // Act
        Payment result = payPalController.executePayment(paymentId, payerId);

        // Assert
        assertNotNull(result);
        // Add more assertions as needed
    }
}