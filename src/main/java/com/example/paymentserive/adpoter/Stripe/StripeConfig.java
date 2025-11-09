package com.example.paymentserive.adpoter.Stripe;


import com.stripe.StripeClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeConfig {

@Value( "${stripe.api.key}")
private String apiKey;


   @Bean
    public StripeClient getStripeClient() {
       String apiKey = this.apiKey;
        return  new StripeClient(apiKey);

    }
}
