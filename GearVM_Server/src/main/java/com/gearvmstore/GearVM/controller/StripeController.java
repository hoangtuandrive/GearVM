package com.gearvmstore.GearVM.controller;

import com.gearvmstore.GearVM.model.dto.payment.CreatePaymentLink;
import com.gearvmstore.GearVM.service.OrderService;
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
public class StripeController {

    private final StripeService stripeService;
    private final OrderService orderService;

    @Autowired
    public StripeController(StripeService stripeService, OrderService orderService) {
        this.stripeService = stripeService;
        this.orderService = orderService;
    }

    @PostMapping("/create-session")
    public ResponseEntity<String> CreateSession() throws StripeException {
        return ResponseEntity.ok().body(stripeService.createStripeSession().getUrl());
    }

    @PostMapping("/create-payment-intent")
    public ResponseEntity<String> CreatePaymentIntent() throws StripeException {
        return ResponseEntity.ok().body(stripeService.createPaymentIntent().getClientSecret());
    }

    @PostMapping("/create-payment-link")
    public ResponseEntity<String> CreatePaymentLink(@RequestBody CreatePaymentLink createPaymentLink) throws StripeException {
        String paymentLink = stripeService.createPaymentLink(createPaymentLink.getId(), createPaymentLink.getTotalPrice()).getUrl();
        orderService.addPaymentLinkToOrder(paymentLink, Long.parseLong(createPaymentLink.getId()));
        return ResponseEntity.ok().body(paymentLink);
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
            /*case "payment_intent.succeeded":
                JSONObject jsonObjectPaymentIntent = new JSONObject(stripeObject.toJson());
                System.out.println(jsonObjectPaymentIntent);

                String paymentId = jsonObjectPaymentIntent.getString("id");
                String customerEmail = jsonObjectPaymentIntent.getString("receipt_email");
                String totalPrice = jsonObjectPaymentIntent.getString("amount"); //bug

                System.out.println(paymentId);
                System.out.println(customerEmail);
                System.out.println(totalPrice);

                break;*/

            case "invoice.payment_succeeded":
                JSONObject jsonObjectInvoice = new JSONObject(stripeObject.toJson());

                String description = jsonObjectInvoice.getString("description");
                String paymentDescription = jsonObjectInvoice.getString("payment_intent");
                String customerName = jsonObjectInvoice.getString("customer_name");
                String customerEmail = jsonObjectInvoice.getString("customer_email");

                JSONObject shippingDetailObject = jsonObjectInvoice.getJSONObject("customer_shipping");
                JSONObject addressObject = shippingDetailObject.getJSONObject("address");
                String line1 = addressObject.getString("line1");
                String city = addressObject.getString("city");
//                String country = addressObject.getString("country");
                String address = line1 + ", " + city;

                int index = description.indexOf("#");
                if (index != -1) {
                    String orderId = description.substring(index + 1);
                    orderService.updateOrderAfterPaymentInvoiceSucceeded(Long.parseLong(orderId), paymentDescription, customerName, address, customerEmail);
                }

                break;

            case "charge.refunded":
                // TODO Hoàn tiền --> update trạng thái đơn hàng
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