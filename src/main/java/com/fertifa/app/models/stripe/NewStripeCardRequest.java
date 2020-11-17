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
public class NewStripeCardRequest {
    private String number;
    private String object;
    private String month;
    private String year;
    private String cvc;
    private String name;
    private String customerId;
    private String cardId;
    private String firstName;
    private String lastName;

}
