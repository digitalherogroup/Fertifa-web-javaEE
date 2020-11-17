package com.fertifa.app.controllers.stripecontroller;


import com.fertifa.app.dao.stripeDao.NewStripeCardDao;
import com.fertifa.app.models.stripe.NewStripeCardResponse;

import java.sql.SQLException;
import java.util.List;

public class NewStripeCardController {
    private NewStripeCardDao newStripeUsersCardDao = new NewStripeCardDao();

    public int saveNewStripeCard(NewStripeCardResponse newStripeCardResponse) throws SQLException {
        return newStripeUsersCardDao.save(newStripeCardResponse);
    }

    public List<NewStripeCardResponse> findAllByCustomerId(String customerId) throws SQLException {
        return newStripeUsersCardDao.findAllByCustomerId(customerId);
    }

    public int deleteCard(String cardId) throws SQLException {
        return newStripeUsersCardDao.deleteCard(cardId);
    }

    public NewStripeCardResponse findCardById(String cardId) throws SQLException {
        return newStripeUsersCardDao.findCardById(cardId);
    }

    public int updateNewStripeCard(NewStripeCardResponse newStripeCardResponse) throws SQLException {
        return newStripeUsersCardDao.updateNewStripeCard(newStripeCardResponse);
    }

    public List<NewStripeCardResponse> findAllByCustomerIdStatusNull(String customerId) throws SQLException {
        return newStripeUsersCardDao.findAllByCustomerIdStatusNull(customerId);
    }

    public NewStripeCardResponse getTheDefaultCard(String customerId) throws SQLException {
        return newStripeUsersCardDao.getTheDefaultCard(customerId);
    }

    public int updateStatus(String customerId) throws SQLException {
        return newStripeUsersCardDao.updateStatus(customerId);
    }

    public int updateNewStripeCardStatus(NewStripeCardResponse newStripeCardResponse) throws SQLException {
        return newStripeUsersCardDao.updateNewStripeCardStatus(newStripeCardResponse);
    }
}
