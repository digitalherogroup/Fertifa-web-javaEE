package com.fertifa.app.controllers.stripecontroller;


import com.fertifa.app.dao.stripeDao.NewStripeSubscriptionDao;
import com.fertifa.app.models.stripe.NewStripeSubscriptionResponse;

import java.sql.SQLException;

public class NewStripeSubscriptionController {
    private NewStripeSubscriptionDao newStripeSubscriptionDao = new NewStripeSubscriptionDao();

    public int saveNewStripeSubscription(NewStripeSubscriptionResponse newStripeSubscriptionResponse) throws SQLException {
        return newStripeSubscriptionDao.save(newStripeSubscriptionResponse);

    }
}
