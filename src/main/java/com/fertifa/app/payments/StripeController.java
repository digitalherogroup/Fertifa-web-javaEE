package com.fertifa.app.payments;

import com.fertifa.app.models.ShoppingCart;
import com.fertifa.app.models.StripeUsers;

import java.sql.SQLException;
import java.util.List;

public class StripeController {

    StripeDao stripeUserDao = new StripeDao();

    public int save(User user, int userid,String stripId) throws SQLException {
        return stripeUserDao.save(user,userid,stripId);
    }

    public int saveOrder(Order order, User user ,ShoppingCart shoppingCart, int companyId, int serviceid) throws SQLException {
        return stripeUserDao.saveOrder(user,order,shoppingCart,companyId,serviceid);
    }

    public String getStripIdByUserId(int userId) {
        return stripeUserDao.getStripIdByUserId(userId);
    }

    public List<StripeUsers> getStripeUsersByStripe(String stripeId) {
        return stripeUserDao.getStripeUsersByStripe(stripeId);
    }
}
