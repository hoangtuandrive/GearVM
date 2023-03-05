package com.gearvmstore.GearVM.model.dto.payment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChargeRequest {
    public enum Currency {
        USD, VND;
    }

    private String description;
    private int amount;
    private Currency currency;
    private String stripeEmail;
    private String stripeToken;
}
