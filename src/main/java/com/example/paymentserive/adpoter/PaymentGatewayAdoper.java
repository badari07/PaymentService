package com.example.paymentserive.adpoter;

import com.stripe.exception.StripeException;

public interface PaymentGatewayAdoper {

    /**
     * Identifier for gateway (e.g. "razorpay", "stripe").
     */
    String getGatewayCode();

    String createPaymentLink(Long price) throws StripeException;
}

