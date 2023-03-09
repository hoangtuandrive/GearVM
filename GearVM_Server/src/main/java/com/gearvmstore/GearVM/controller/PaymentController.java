package com.gearvmstore.GearVM.controller;

import com.gearvmstore.GearVM.model.dto.payment.CardToken;
import com.gearvmstore.GearVM.model.dto.payment.PaymentStatus;
import com.gearvmstore.GearVM.service.StripeService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.PaymentIntent;
import com.stripe.param.ChargeCreateParams;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    private StripeService paymentsService;

    @Value("${STRIPE_SECRET_KEY}")
    private String stripePrivateKey;

    @PostMapping("/false")
    public ResponseEntity<PaymentStatus> chargeCustomer(@RequestBody CardToken cardToken) throws StripeException {


        Stripe.apiKey = stripePrivateKey;
        Stripe.setMaxNetworkRetries(2);

        Charge charge;
        PaymentStatus paymentStatus;

        try {
            ChargeCreateParams params =
                    ChargeCreateParams.builder()
                            .setAmount(cardToken.getAmount())
                            .setCurrency(cardToken.getCurrency())
                            .setDescription("Shopper Buy")
                            .setSource(cardToken.getId())
                            .build();

            charge = Charge.create(params);
            System.out.println("Charge = " + charge);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            paymentStatus = new PaymentStatus(timestamp.getTime(), false,
                    charge.getId(),
                    charge.getBalanceTransaction(),
                    charge.getReceiptUrl()
            );

        } catch (Exception e) {
            paymentStatus = new PaymentStatus();
            paymentStatus.setPayment_failed(true);
            System.out.println("Something went wrong with Stripe API");
            System.out.println(e);
            return ResponseEntity.badRequest().body(paymentStatus);
        }

        System.out.println("Payment is successful....");
        return ResponseEntity.ok(paymentStatus);
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "result";
    }

    @PostMapping("/true")
    public ResponseEntity<String> CreatePaymentIntent() throws StripeException {
        Stripe.apiKey = stripePrivateKey;

        PaymentIntentCreateParams params =
                PaymentIntentCreateParams
                        .builder()
                        .setAmount(1099L)
                        .setCurrency("usd")
                        .addPaymentMethodType("card")
                        .build();

        PaymentIntent paymentIntent = PaymentIntent.create(params);
        return ResponseEntity.ok().body(paymentIntent.getClientSecret());
    }
}