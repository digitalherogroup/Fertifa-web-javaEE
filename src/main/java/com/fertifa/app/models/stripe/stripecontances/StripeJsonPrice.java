package com.fertifa.app.models.stripe.stripecontances;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StripeJsonPrice {
    @SerializedName("active")
    @Expose
    public Boolean active;
    @SerializedName("billing_scheme")
    @Expose
    public String billingScheme;
    @SerializedName("created")
    @Expose
    public Float created;
    @SerializedName("currency")
    @Expose
    public String currency;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("livemode")
    @Expose
    public Boolean livemode;
    @SerializedName("metadata")
    @Expose
    public StripeJsonMetaData metadata;
    @SerializedName("nickname")
    @Expose
    public String nickname;
    @SerializedName("object")
    @Expose
    public String object;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("unit_amount")
    @Expose
    public Float unitAmount;
    @SerializedName("unit_amount_decimal")
    @Expose
    public Float unitAmountDecimal;
}
