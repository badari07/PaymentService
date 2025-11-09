package com.example.paymentserive.adpoter;

import com.example.paymentserive.adpoter.Stripe.StripePaymentAdoper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentGatewayStrategy {

    private static Random random = new Random();
    private final StripePaymentAdoper stripePaymentAdoper;

    public PaymentGatewayStrategy(StripePaymentAdoper stripePaymentAdoper) {
        this.stripePaymentAdoper = stripePaymentAdoper;
    }

    public PaymentGatewayAdoper getPaymentGateway() {
        return stripePaymentAdoper;
    }
}
