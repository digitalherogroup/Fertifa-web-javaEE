package com.fertifa.app.payments;

import com.google.gson.annotations.SerializedName;
import com.stripe.model.StripeObject;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)

public class Address extends StripeObject {
    @SerializedName("city")
    String city;

    @SerializedName("country")
    String country;

    @SerializedName("line1")
    String line1;

    @SerializedName("line2")
    String line2;

    @SerializedName("postal_code")
    String postalCode;

    @SerializedName("state")
    String state;

    public Address(String city, String country, String postal_code, String state) {
        this.city=city;
        this.country=country;
        this.postalCode=postal_code;
        this.state=state;
    }

    public Address() {
    }
}
