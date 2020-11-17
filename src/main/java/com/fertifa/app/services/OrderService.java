package com.fertifa.app.services;

import com.fertifa.app.models.Orders;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {
    List<Orders> getAll() throws SQLException;
    List<Orders> orderById(int id) throws SQLException;
    List<Orders> getAllByUseriD(int userId) throws SQLException;

    int AddNewOrder(Orders orders) throws SQLException;
}
