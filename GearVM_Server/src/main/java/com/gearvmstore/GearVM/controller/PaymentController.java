package com.gearvmstore.GearVM.controller;

import com.gearvmstore.GearVM.service.StripeService;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.StripeObject;
import com.stripe.net.Webhook;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
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

    @PostMapping("/create-payment-link/{orderId}")
    public ResponseEntity<String> CreatePaymentLink(@PathVariable(value = "orderId") String orderId) throws StripeException {
        return ResponseEntity.ok().body(stripeService.createPaymentLink(orderId).getUrl());
    }

    @PostMapping("/webhook")
    public ResponseEntity<String> WebHook(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) throws JSONException {
        Event event = null;
        try {
            event = Webhook.constructEvent(payload, sigHeader, "whsec_d3b41c87f134816de2dc0794a10290a22286618974d2bb91576e388e3abe7097");
        } catch (SignatureVerificationException e) {
            System.out.println("Failed signature verification");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
        StripeObject stripeObject = null;

        if (dataObjectDeserializer.getObject().isPresent()) {
            stripeObject = dataObjectDeserializer.getObject().get();

        } else {
            // Deserialization failed, probably due to an API version mismatch.
            // Refer to the Javadoc documentation on `EventDataObjectDeserializer` for
            // instructions on how to handle this case, or return an error here.
        }

        switch (event.getType()) {
           /* case "payment_intent.succeeded":
                JSONObject jsonObjectPaymentIntent = new JSONObject(stripeObject.toJson());

                String paymentId = jsonObjectPaymentIntent.getString("id");
                String customerEmail = jsonObjectPaymentIntent.getString("receipt_email");
                String totalPrice = jsonObjectPaymentIntent.getString("amount");

                System.out.println(paymentId);
                System.out.println(customerEmail);
                System.out.println(totalPrice);

                break;*/

            case "invoice.payment_succeeded":
                JSONObject jsonObjectInvoice = new JSONObject(stripeObject.toJson());

                String description = jsonObjectInvoice.getString("description");
                String paymentId = jsonObjectInvoice.getString("payment_intent");

                int index = description.indexOf("#"); // get the index of the "#" symbol
                if (index != -1) {
                    String result = description.substring(index + 1); // get the substring after the "#" symbol
                    System.out.println(result);
                }
                System.out.println(paymentId);
                break;

            case "charge.refunded":
                // Hoàn tiền --> update trạng thái đơn hàng TODO
                System.out.println(stripeObject);
                // ...
                break;

            default:
                // Unexpected event type
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}