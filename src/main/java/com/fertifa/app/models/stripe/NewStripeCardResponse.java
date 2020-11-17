package com.fertifa.app.models.stripe;

import com.fertifa.app.models.stripe.stripecontances.StripeJsonData;
import com.fertifa.app.models.stripe.stripecontances.StripeJsonMetaData;
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
public class NewStripeCardResponse {

    @SerializedName("cardId")
    @Expose
    private String cardId;
    @SerializedName("last4Digits")
    @Expose
    private String last4Digits;
    @SerializedName("customer_id")
    @Expose
    private String customerId;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("card_type")
    @Expose
    private String cardType;
    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("month")
    @Expose
    private Integer month;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("updated_date")
    @Expose
    public String updatedDate;
    @SerializedName("default_card")
    @Expose
    public Integer defaultCard;

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

    @SerializedName("object")
    @Expose
    public String object;

    public StripeJsonData data;
}
