package com.example.ecommerceplatform.api;

import lombok.Data;

@Data
public class PaymentRequest {
    private String amount;       // The payment amount (e.g., "100.00")
    private String currency;     // The currency of the payment (e.g., "USD")
    private String paymentMethod; // The payment method ("paypal" or "credit_card")
    private String description;  // Description of the payment

    // Additional fields you may need for your specific application:
    // private String returnUrl;  // The URL to redirect users after successful payment
    // private String cancelUrl;  // The URL to redirect users after canceling the payment
    // private String invoiceNumber; // Invoice number or order ID
    // private String custom;     // Custom data associated with the payment
}
