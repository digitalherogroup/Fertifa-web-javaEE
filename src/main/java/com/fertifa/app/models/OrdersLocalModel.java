package com.fertifa.app.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdersLocalModel {
    private int id;
    private int user_id;
    private int date_id;
    private int service_id;
    private String appointment;
    private String clinic;
    private String address;
    private float coast;
    private int status;
    private int timeId;
    private Timestamp creation_date;
    private String SessionToken;
    private int chatId;
    private int type;
    private String serviceTime;
    private String priceId;
    private String productId;
    private String invoiceId;
    private String paymentId;
}
