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
public class NewStripeSubscriptionRequest {
    private String customerId;
    private String title;
    private Integer price;
    private String description;
    private Integer trial_period_days;
    private String cardId;
}
