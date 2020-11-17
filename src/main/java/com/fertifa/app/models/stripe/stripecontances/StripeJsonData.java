package com.fertifa.app.models.stripe.stripecontances;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.stripe.model.Charge.FraudDetails;
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
public class StripeJsonData {
    @SerializedName("brand")
    @Expose
    public String brand;
    @SerializedName("balance")
    @Expose
    public Float balance;
    @SerializedName("created")
    @Expose
    public Float created;
    @SerializedName("delinquent")
    @Expose
    public Boolean delinquent;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("invoice_prefix")
    @Expose
    public String invoicePrefix;
    @SerializedName("invoice_settings")
    @Expose
    public StripeJsonInvoice invoiceSettings;
    @SerializedName("livemode")
    @Expose
    public Boolean livemode;
    @SerializedName("metadata")
    @Expose
    public StripeJsonMetaData metadata;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("next_invoice_sequence")
    @Expose
    public Float nextInvoiceSequence;
    @SerializedName("object")
    @Expose
    public String object;
    @SerializedName("phone")
    @Expose
    public String phone;
    @SerializedName("preferred_locales")
    @Expose
    public List<Object> preferredLocales = null;


    private StripeJsonData data;
    private StripeJsonSource sources;
    private StripeJsonSubscription subscriptions;
    private StripeJsonTaxId tax_ids;

    @SerializedName("client_ip")
    @Expose
    public String clientIp;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("used")
    @Expose

    public Boolean used;

    public StripJsonCard card;

    @SerializedName("billing_cycle_anchor")
    @Expose
    public Float billingCycleAnchor;
    @SerializedName("cancel_at_period_end")
    @Expose
    public Boolean cancelAtPeriodEnd;
    @SerializedName("collection_method")
    @Expose
    public String collectionMethod;

    @SerializedName("current_period_end")
    @Expose
    public Float currentPeriodEnd;
    @SerializedName("current_period_start")
    @Expose
    public Float currentPeriodStart;
    @SerializedName("default_tax_rates")
    @Expose
    public List<Object> defaultTaxRates = null;

    @SerializedName("quantity")
    @Expose
    public Float quantity;
    @SerializedName("start_date")
    @Expose
    public Float startDate;
    @SerializedName("status")
    @Expose
    public String status;


    @SerializedName("customer")
    @Expose
    public StripeJsonCustomer customer;

    @SerializedName("items")
    @Expose
    public StripeJsonItem items;
    @SerializedName("latest_invoice")
    @Expose
    public StripeJsonInvoice latestInvoice;

    @SerializedName("plan")
    @Expose
    public StripeJsonPlan plan;

    public StripeJsonPrice price;

    @SerializedName("amount")
    @Expose
    public Float amount;
    @SerializedName("amount_refunded")
    @Expose
    public Float amountRefunded;
    @SerializedName("calculated_statement_descriptor")
    @Expose
    public String calculatedStatementDescriptor;
    @SerializedName("captured")
    @Expose
    public Boolean captured;

    @SerializedName("currency")
    @Expose
    public String currency;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("disputed")
    @Expose
    public Boolean disputed;
    @SerializedName("fraud_details")
    @Expose
    public FraudDetails fraudDetails;

    @SerializedName("paid")
    @Expose
    public Boolean paid;
    @SerializedName("payment_method")
    @Expose
    public String paymentMethod;
    @SerializedName("receipt_url")
    @Expose
    public String receiptUrl;
    @SerializedName("refunded")
    @Expose
    public Boolean refunded;

    @SerializedName("refunds")
    @Expose
    private StripeJsonRefund refunds;
    @SerializedName("outcome")
    @Expose
    private StripeJsonOutcome outcome;
    @SerializedName("balance_transaction")
    @Expose
    private StripeJsonBalanceTransaction balance_transaction;
}
