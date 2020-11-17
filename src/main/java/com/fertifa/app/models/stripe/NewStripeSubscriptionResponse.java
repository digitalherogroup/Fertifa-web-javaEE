package com.fertifa.app.models.stripe;

import com.fertifa.app.models.stripe.stripecontances.StripeJsonData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.stripe.model.Plan;
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
public class NewStripeSubscriptionResponse {
    @SerializedName("subscription_id")
    @Expose
    private String subscriptionId;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("billing_cycle")
    @Expose
    private Integer billingCycle;
    @SerializedName("payment_Cycle")
    @Expose
    private String paymentCycle;
    @SerializedName("latest_invoice")
    @Expose
    private String latestInvoice;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("plan")
    @Expose
    private Plan plan;
    @SerializedName("product")
    @Expose
    private String product;
    @SerializedName("creationDate")
    @Expose
    private String creationDate;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("customer_id")
    @Expose
    private String customerId;

    private StripeJsonData data;

}
