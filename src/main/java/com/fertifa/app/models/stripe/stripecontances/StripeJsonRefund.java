package com.fertifa.app.models.stripe.stripecontances;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StripeJsonRefund {
    @SerializedName("object")
    @Expose
    public String object;
    @SerializedName("data")
    @Expose
    public List<Object> data = null;
    @SerializedName("hasMore")
    @Expose
    public Boolean hasMore;
    @SerializedName("url")
    @Expose
    public String url;
}
