package com.example.paymentserive.adpoter;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RazorpayPaymentAdoper implements PaymentGatewayAdoper {

    @Override
    public String getGatewayCode() {
        return "razorpay";
    }

    @Override
    public String createPaymentLink(Long price) {
        return "https://razorpay.com/pay/" + UUID.randomUUID();
    }
}