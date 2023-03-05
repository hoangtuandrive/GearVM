package com.gearvmstore.GearVM.service;

import com.stripe.Stripe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class StripeService {
    @Value("${STRIPE_SECRET_KEY}")
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

//    public Charge charge(ChargeRequest chargeRequest)
//            throws AuthenticationException, InvalidRequestException,
//            APIConnectionException, CardException, APIException {
//        Map<String, Object> chargeParams = new HashMap<>();
//        chargeParams.put("amount", chargeRequest.getAmount());
//        chargeParams.put("currency", chargeRequest.getCurrency());
//        chargeParams.put("description", chargeRequest.getDescription());
//        chargeParams.put("source", chargeRequest.getStripeToken());
//        return Charge.create(chargeParams);
//    }
}
