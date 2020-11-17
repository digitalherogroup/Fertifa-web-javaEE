package com.fertifa.app.models.stripe;

import com.fertifa.app.models.stripe.stripecontances.StripeJsonData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.stripe.model.BalanceTransaction;
import com.stripe.model.Charge.Outcome;
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
public class NewStripeChargeShoppingCardResponse {
    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("amount")
    @Expose
    public Integer amount;
    @SerializedName("amount_refunded")
    @Expose
    public Integer amountRefunded;
    @SerializedName("balance_transaction")
    @Expose
    public BalanceTransaction balanceTransaction;

    @SerializedName("calculated_statement_descriptor")
    @Expose
    public String calculatedStatementDescriptor;
    @SerializedName("captured")
    @Expose
    public Boolean captured;
    @SerializedName("created")
    @Expose
    public Integer created;
    @SerializedName("currency")
    @Expose
    public String currency;

    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("disputed")
    @Expose
    public Boolean disputed;

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("livemode")
    @Expose
    public Boolean livemode;

    @SerializedName("object")
    @Expose
    public String object;
    @SerializedName("outcome")
    @Expose
    public Outcome outcome;
    @SerializedName("paid")
    @Expose
    public Boolean paid;
    @SerializedName("payment_method")
    @Expose
    public String paymentMethod;

    @SerializedName("receipt_url")
    @Expose
    public String receiptUrl;
    @SerializedName("refunded")
    @Expose
    public Boolean refunded;

    @SerializedName("status")
    @Expose
    public String status;
    private StripeJsonData data;
}