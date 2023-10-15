package com.example.ecommerceplatform.service;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayPalService {

    @Value("${paypal.clientId}")
    private String clientId;

    @Value("${paypal.clientSecret}")
    private String clientSecret;

    public Payment createPayment(String amount, String currency, String method, String intent) throws PayPalRESTException {
        APIContext apiContext = new APIContext(clientId, clientSecret, "sandbox");

        Amount amountDetails = new Amount();
        amountDetails.setTotal(amount);
        amountDetails.setCurrency(currency);

        Transaction transaction = new Transaction();
        transaction.setAmount(amountDetails);

        Payment payment = new Payment();
        payment.setIntent(intent);
        payment.setPayer(new Payer().setPaymentMethod(method));
        payment.setTransactions(List.of(transaction));

        return payment.create(apiContext);
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        APIContext apiContext = new APIContext(clientId, clientSecret, "sandbox");
        Payment payment = new Payment().setId(paymentId);

        PaymentExecution paymentExecution = new PaymentExecution().setPayerId(payerId);

        return payment.execute(apiContext, paymentExecution);
    }
}
