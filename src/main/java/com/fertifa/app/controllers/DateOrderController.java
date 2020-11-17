package com.fertifa.app.controllers;

import com.fertifa.app.dao.DateorderDao;
import com.fertifa.app.models.DateOrder;
import com.fertifa.app.products.model.products.Products;

import java.sql.SQLException;
import java.util.List;

public class DateOrderController {

    DateorderDao dateorderDao = new DateorderDao();

    public int deleteByOrderId(int userId) throws SQLException {
        return dateorderDao.deleteByOrderId(userId);
    }

    public int AddNewDateOrder(DateOrder dateOrder) throws SQLException {
        return dateorderDao.AddNewDateOrder(dateOrder);
    }

    public int getLastOrderDateid() throws SQLException {
        return dateorderDao.getAllOrdersDate();
    }

    public List<DateOrder> getAllOrdersByUserId(int userId) throws SQLException {
        return dateorderDao.getAllOrdersByUserId(userId);
    }

    public Products getProductById(String value) {
        return dateorderDao.getProductById(value);
    }
}
