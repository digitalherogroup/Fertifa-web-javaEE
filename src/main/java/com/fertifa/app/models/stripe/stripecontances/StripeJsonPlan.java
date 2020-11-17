package com.fertifa.app.models.stripe.stripecontances;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
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
public class StripeJsonPlan {
    @SerializedName("active")
    @Expose
    public Boolean active;
    @SerializedName("amount")
    @Expose
    public Float amount;
    @SerializedName("amount_decimal")
    @Expose
    public Float amountDecimal;
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
    @SerializedName("interval")
    @Expose
    public String interval;
    @SerializedName("interval_count")
    @Expose
    public Float intervalCount;
    @SerializedName("livemode")
    @Expose
    public Boolean livemode;
    @SerializedName("metadata")
    @Expose
    public StripeJsonData metadata;
    @SerializedName("nickname")
    @Expose
    public String nickname;
    @SerializedName("object")
    @Expose
    public String object;
    @SerializedName("product")
    @Expose
    public StripeJsonProduct product;
    @SerializedName("trial_period_days")
    @Expose
    public Float trialPeriodDays;
    @SerializedName("usage_type")
    @Expose
    public String usageType;

}
