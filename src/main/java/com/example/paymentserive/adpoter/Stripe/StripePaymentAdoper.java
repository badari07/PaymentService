package com.example.paymentserive.adpoter.Stripe;

import com.example.paymentserive.adpoter.PaymentGatewayAdoper;
import com.stripe.StripeClient;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class StripePaymentAdoper implements PaymentGatewayAdoper {

    private static final Logger log = LoggerFactory.getLogger(StripePaymentAdoper.class);

    private final StripeClient stripeClient;

    public StripePaymentAdoper(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }

    @Override
    public String getGatewayCode() {
        return "stripe";
    }

    @Override
    public String createPaymentLink(Long price) throws StripeException {
        long unitAmount = price * 100; // convert to smallest currency unit (e.g. paise)

        PriceCreateParams priceParams = PriceCreateParams.builder()
                .setCurrency("inr")
                .setUnitAmount(unitAmount)
                .setProductData(
                        PriceCreateParams.ProductData.builder()
                                .setName("Order #" + price)
                                .build()
                )
                .build();

        log.debug("Creating Stripe price for amount={} currency={}", unitAmount, "inr");
        Price stripePrice = stripeClient.prices().create(priceParams);
        log.debug("Created Stripe price id={}", stripePrice.getId());

        PaymentLinkCreateParams params = PaymentLinkCreateParams.builder()
                .addLineItem(
                        PaymentLinkCreateParams.LineItem.builder()
                                .setQuantity(1L)
                                .setPrice(stripePrice.getId())
                                .build()
                )
                .setAfterCompletion(
                        PaymentLinkCreateParams.AfterCompletion.builder()
                                .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                .setRedirect(
                                        PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                .setUrl("https://scaler.com")
                                                .build()
                                )
                                .build()
                )
                .build();

        log.debug("Creating Stripe payment link for price={} with params={}", price, params);

        try {
            PaymentLink paymentLink = stripeClient.paymentLinks().create(params);
            log.info("Created Stripe payment link id={} url={}", paymentLink.getId(), paymentLink.getUrl());
            return paymentLink.getUrl();
        } catch (StripeException ex) {
            log.error("Stripe payment link creation failed: status={} code={} message={}",
                    ex.getStatusCode(), ex.getCode(), ex.getMessage(), ex);
            throw ex;
        }
    }
}

