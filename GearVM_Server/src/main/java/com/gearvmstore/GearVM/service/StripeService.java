package com.gearvmstore.GearVM.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.model.checkout.Session;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {
    private final static String DOMAIN = "http://localhost:3000";

    @Value("${STRIPE_SECRET_KEY}")
    private String stripePrivateKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripePrivateKey;
    }

    public Product createStripeProduct(String productName) throws StripeException {
        Map<String, Object> params = new HashMap<>();
        params.put("name", productName);

        return Product.create(params);
    }

    public Price createStripePrice(String productName) throws StripeException {
        try {
            PriceCreateParams params =
                    PriceCreateParams
                            .builder()
                            .setCurrency("vnd")
                            .setUnitAmount(1000000L)
                            .setProduct(createStripeProduct(productName).getId())
                            .build();

            return Price.create(params);
        } catch (Exception e) {
            System.out.println("Stripe error");
        }
        return null;
    }

    public PaymentLink createPaymentLink(String productName) throws StripeException {
        PaymentLinkCreateParams params =
                PaymentLinkCreateParams
                        .builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem
                                        .builder()
                                        .setPrice(createStripePrice(productName).getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .addPaymentMethodType(PaymentLinkCreateParams.PaymentMethodType.CARD)
                        .setBillingAddressCollection(
                                PaymentLinkCreateParams.BillingAddressCollection.REQUIRED
                        )
                        .setShippingAddressCollection(
                                PaymentLinkCreateParams.ShippingAddressCollection
                                        .builder()
                                        .addAllowedCountry(
                                                PaymentLinkCreateParams.ShippingAddressCollection.AllowedCountry.VN
                                        )
                                        .addAllowedCountry(
                                                PaymentLinkCreateParams.ShippingAddressCollection.AllowedCountry.SG
                                        )
                                        .build()
                        )
                        .setInvoiceCreation(
                                PaymentLinkCreateParams.InvoiceCreation
                                        .builder()
                                        .setEnabled(true)
                                        .setInvoiceData(
                                                PaymentLinkCreateParams.InvoiceCreation.InvoiceData
                                                        .builder()
                                                        .setDescription(productName)
                                                        .addCustomField(
                                                                PaymentLinkCreateParams.InvoiceCreation.InvoiceData.CustomField
                                                                        .builder()
                                                                        .setName("Purchase Order")
                                                                        .setValue("PO-XYZ")
                                                                        .build()
                                                        )
                                                        .setRenderingOptions(
                                                                PaymentLinkCreateParams.InvoiceCreation.InvoiceData.RenderingOptions
                                                                        .builder()
                                                                        .setAmountTaxDisplay(
                                                                                PaymentLinkCreateParams.InvoiceCreation.InvoiceData.RenderingOptions.AmountTaxDisplay.INCLUDE_INCLUSIVE_TAX
                                                                        )
                                                                        .build()
                                                        )
                                                        .setFooter("GearVM, 100 Cộng Hòa, 0929471420")
                                                        .build()
                                        )
                                        .build())
                        .setAfterCompletion(
                                PaymentLinkCreateParams.AfterCompletion
                                        .builder()
                                        .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                        .setRedirect(
                                                PaymentLinkCreateParams.AfterCompletion.Redirect
                                                        .builder()
                                                        .setUrl(DOMAIN + "/payment-sucess")
                                                        .build()
                                        )
                                        .build()
                        )
                        .build();

        return PaymentLink.create(params);
    }

    public Session createStripeSession() throws StripeException {
        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl(DOMAIN + "?success=true")
                        .setCancelUrl(DOMAIN + "?canceled=true")
                        .addLineItem(
                                SessionCreateParams.LineItem.builder()
                                        .setQuantity(1L)
                                        // Provide the exact Price ID (for example, pr_1234) of the product you want to sell
                                        .setPrice("1000")
                                        .build())
                        .build();
        Session session = Session.create(params);
        System.out.println("Here" + session.getUrl());
        return session;
    }

    public PaymentIntent createPaymentIntent() throws StripeException {
        Stripe.apiKey = stripePrivateKey;

        PaymentIntentCreateParams params =
                PaymentIntentCreateParams
                        .builder()
                        .setAmount(1099L)
                        .setCurrency("usd")
                        .addPaymentMethodType("card")
                        .setStatementDescriptor("Thanh toán GearVM")
                        .setSetupFutureUsage(PaymentIntentCreateParams.SetupFutureUsage.OFF_SESSION)
                        .build();

        return PaymentIntent.create(params);
    }
}