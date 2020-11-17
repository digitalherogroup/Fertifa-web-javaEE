package com.fertifa.app.models.stripe;

import com.fertifa.app.models.stripe.stripecontances.StripeJsonData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewStripeUsersResponse {
    @SerializedName("userId")
    @Expose
    private int userId;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("stripeUserUrl")
    @Expose
    private List<String> stripeUserUrl = null;
    @SerializedName("stripeBalance")
    @Expose
    private Integer stripeBalance;
    @SerializedName("creationDate")
    @Expose
    private String creationDate;
    @SerializedName("customer_id")
    @Expose
    private String customerId;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("zipCode")
    @Expose
    private String zipCode;
    @SerializedName("data")
    @Expose
    private StripeJsonData data;


}
