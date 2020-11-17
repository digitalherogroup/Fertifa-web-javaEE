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
public class StripJsonCard {
    @SerializedName("brand")
    @Expose
    public String brand;
    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("cvc_check")
    @Expose
    public String cvcCheck;
    @SerializedName("exp_month")
    @Expose
    public Float expMonth;
    @SerializedName("exp_year")
    @Expose
    public Float expYear;
    @SerializedName("fingerprint")
    @Expose
    public String fingerprint;
    @SerializedName("funding")
    @Expose
    public String funding;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("last4")
    @Expose
    public String last4;
    @SerializedName("metadata")
    @Expose
    public StripeJsonMetaData metadata;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("object")
    @Expose
    public String object;
}
