package com.fertifa.app.models.stripe.stripecontances;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StripeJsonDatum {
    @SerializedName("created")
    @Expose
    public Float created;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("metadata")
    @Expose
    public StripeJsonData metadata;
    @SerializedName("object")
    @Expose
    public String object;
    @SerializedName("plan")
    @Expose
    public StripeJsonPlan plan;
    @SerializedName("price")
    @Expose
    public StripeJsonPrice price;
    @SerializedName("quantity")
    @Expose
    public Float quantity;
    @SerializedName("subscription")
    @Expose
    public String subscription;
    @SerializedName("tax_rates")
    @Expose
    public List<Object> taxRates = null;

}
