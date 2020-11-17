package com.fertifa.app.models.stripe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewStripeChargeShoppingCardRequest {
    private String customerId;
    private String cardId;
    private int price;
    private String description;
    private String code;
}
