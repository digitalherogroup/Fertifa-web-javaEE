package com.fertifa.app.models.stripe;

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
public class NewStripeUsersRequest {
    private Integer id;
    private String email;
    private String customer_id;
    private String firstName;
    private String lastName;
    private String address1;
    private String address2;
    private String city;
    private String postalCode;
    private String country;
    private String phoneNumber;
}
