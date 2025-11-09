package com.example.paymentserive.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatPaymentLinkReqDTO {
    private Long orderId;
//    private BigDecimal amount;
//    private String currency;
//    /**
//     * Identifier for the third-party payment provider to use (e.g. "razorpay", "stripe").
//     */
//    private String provider;
//    private String customerEmail;
//    private String description;
}
