package com.fertifa.app.payments;

import com.fertifa.app.models.ShoppingCart;
import com.fertifa.app.models.StripeUsers;

import java.sql.SQLException;
import java.util.List;

public class ActionStripes {
    private StripeController stripeController = new StripeController();
    private PaymentServiceImpl paymentService = new PaymentServiceImpl();

    public void createCustomerInData(User user, int userId, String stripeId) throws SQLException {
        // Save user to database
        if(stripeController.save(user,userId,stripeId)>0){
            System.out.println("Done adding new User details Stripe");
        }else{
            System.out.println("Something wrong adding new User details Stripe");
        }
    }

    public void chargeCreditCardInData(Order order, User user, ShoppingCart shoppingCart, int companyid,int serviceId) throws SQLException {
        order.setChargeAmountDollars(order.getChargeAmountDollars());
        order.setUser(user);

        if(stripeController.saveOrder(order,user,shoppingCart,companyid,serviceId)>0){
            System.out.println("Done adding new Order details Stripe");
        }else{
            System.out.println("Something wrong adding new Order details Stripe");
        }

    }

    public String getStripeIdByUserId(int userId) {
        return stripeController.getStripIdByUserId(userId);
    }

    public List<StripeUsers> getStripeUsersByStripe(String stripeId) {
        return stripeController.getStripeUsersByStripe(stripeId);
    }
}
