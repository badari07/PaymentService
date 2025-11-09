package com.example.paymentserive.service;

import com.example.paymentserive.adpoter.PaymentGatewayAdoper;
import com.example.paymentserive.adpoter.PaymentGatewayStrategy;
import com.example.paymentserive.dto.CreatPaymentLinkReqDTO;
import com.example.paymentserive.dto.CreatPaymentLinkResDTO;
import com.stripe.exception.StripeException;
import org.springframework.stereotype.Service;

@Service
public class PaymantService {

    private PaymentGatewayStrategy paymentGatewayStrategy;

    public PaymantService(PaymentGatewayStrategy paymentGatewayStrategy) throws StripeException {
        this.paymentGatewayStrategy = paymentGatewayStrategy;
    }

    public String createPaymentLink(Long orderId) throws StripeException {
        PaymentGatewayAdoper paymentGatewayAdoper = paymentGatewayStrategy.getPaymentGateway();

        String url = "";

        try {
            url = paymentGatewayAdoper.createPaymentLink(100L);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return url;
    }
}
