package com.fertifa.app.payments;

import com.stripe.exception.StripeException;

import java.sql.SQLException;

public interface PaymentService {
    String createCustomer(User user,  int user_id) throws StripeException, SQLException;
    void chargeCreditCard(Order order) throws SQLException;
}
