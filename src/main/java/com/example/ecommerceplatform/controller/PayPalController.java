package com.example.ecommerceplatform.controller;

import com.example.ecommerceplatform.api.PaymentRequest;
import com.example.ecommerceplatform.service.PayPalService;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/paypal")
public class PayPalController {

    @Autowired
    private PayPalService payPalService;

    @PostMapping("/create-payment")
    public Payment createPayment(@RequestBody PaymentRequest paymentRequest) throws PayPalRESTException {
        // Call the PayPal service to create a payment
        return payPalService.createPayment(paymentRequest.getAmount(), paymentRequest.getCurrency(), "paypal", "sale");
    }

    @GetMapping("/execute-payment")
    public Payment executePayment(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) throws PayPalRESTException {
        // Call the PayPal service to execute a payment
        return payPalService.executePayment(paymentId, payerId);
    }
}
