package com.fertifa.app.services;

import com.fertifa.app.models.ShoppingCart;

import java.sql.SQLException;
import java.util.List;

public interface ShoopingCartService {
    int addShoppingCart(ShoppingCart shoppingCart) throws SQLException;

    List<ShoppingCart> getAllById(int userId) throws SQLException;

    int deleteOrderByUseridAndOrderId(int userId, int userIdFromWeb) throws SQLException;

}
