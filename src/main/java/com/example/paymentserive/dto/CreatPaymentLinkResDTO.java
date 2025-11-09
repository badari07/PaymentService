package com.example.paymentserive.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatPaymentLinkResDTO {
//    private String provider;
    private String url;
//    private String referenceId;
//    private String status;
}
