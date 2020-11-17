package com.fertifa.app.models.stripe.stripecontances;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StripeJsonOutcome {
    @SerializedName("network_status")
    @Expose
    public String networkStatus;
    @SerializedName("risk_level")
    @Expose
    public String riskLevel;
    @SerializedName("risk_score")
    @Expose
    public Float riskScore;
    @SerializedName("seller_message")
    @Expose
    public String sellerMessage;
    @SerializedName("type")
    @Expose
    public String type;
}
