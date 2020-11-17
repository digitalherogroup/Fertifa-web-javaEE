package com.fertifa.app.models.stripe.stripecontances;

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
public class StripeJsonItem {
    @SerializedName("object")
    @Expose
    public String object;
    @SerializedName("data")
    @Expose
    public List<StripeJsonDatum> data = null;
    @SerializedName("hasMore")
    @Expose
    public Boolean hasMore;
    @SerializedName("url")
    @Expose
    public String url;
}
