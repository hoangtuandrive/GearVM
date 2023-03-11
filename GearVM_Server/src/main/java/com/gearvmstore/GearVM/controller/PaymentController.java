package com.gearvmstore.GearVM.controller;

import com.gearvmstore.GearVM.service.StripeService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    private StripeService stripeService;

    @PostMapping("/create-session")
    public ResponseEntity<String> CreateSession() throws StripeException {
        return ResponseEntity.ok().body(stripeService.createStripeSession().getUrl());
    }

    @PostMapping("/create-payment-intent")
    public ResponseEntity<String> CreatePaymentIntent() throws StripeException {
        return ResponseEntity.ok().body(stripeService.createPaymentIntent().getClientSecret());
    }

    @PostMapping("/create-payment-link")
    public ResponseEntity<String> CreatePaymentLink() throws StripeException {
        return ResponseEntity.ok().body(stripeService.createPaymentLink("Hóa đơn mã số #12").getUrl());
    }
}