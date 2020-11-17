package com.fertifa.app.models.stripe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewStripePlanResponse {
    private String active;
    private Long amount;
    private String billing_scheme;
    private Timestamp created;
    private String currency;
    private String id;
    private String interval;
    private Integer interval_count;
    private String liveMode;
    private String nickname;
    private String object;
    private String usage_type;
    private NewStripeProductResponse product;

}
