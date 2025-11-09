package com.example.paymentserive.controler;

import com.example.paymentserive.dto.CreatPaymentLinkReqDTO;
import com.example.paymentserive.dto.CreatPaymentLinkResDTO;
import com.example.paymentserive.service.PaymantService;
import com.stripe.exception.StripeException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@AllArgsConstructor
public class PaymentController {

    private final PaymantService paymantService;

    @PostMapping("/")
    public ResponseEntity<CreatPaymentLinkResDTO> createPaymentLink(@RequestBody CreatPaymentLinkReqDTO request) throws StripeException {
        String url = paymantService.createPaymentLink(request.getOrderId());

//        CreatPaymentLinkResDTO response = CreatPaymentLinkResDTO.builder()
//                .provider("stripe")
//                .url(url)
//                .referenceId("ref123")
//                .status("created")
//                .build();

        CreatPaymentLinkResDTO response = CreatPaymentLinkResDTO.builder()
                .url(url)
                .build();

        return ResponseEntity.ok(response);
    }
}

