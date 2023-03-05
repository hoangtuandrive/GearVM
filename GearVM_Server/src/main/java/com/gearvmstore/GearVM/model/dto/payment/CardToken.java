package com.gearvmstore.GearVM.model.dto.payment;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CardToken {
    String id;
    Long amount;
    String currency;

}
