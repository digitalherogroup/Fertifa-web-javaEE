package com.fertifa.app.controllers;

import com.fertifa.app.dao.TimeDateOrderDao;
import com.fertifa.app.models.ServiceDateTime;

import java.sql.SQLException;
import java.util.List;

public class TimeDateOrderController {
    TimeDateOrderDao timeDateOrderDao = new TimeDateOrderDao();

    public List<ServiceDateTime> getByUserId(int userId) throws SQLException {
        return timeDateOrderDao.getByUserId(userId);
    }

    public List<ServiceDateTime> getAll() throws SQLException {
        return timeDateOrderDao.getAll();

    }

    public List<ServiceDateTime> getByUserIdAndOrderId(int userIdFromOrder, int dataId) throws SQLException {
        return timeDateOrderDao.getByUserIdAndOrderId(userIdFromOrder,dataId);
    }

    public List<ServiceDateTime> getByUserIdFullDetails(int userId) throws SQLException {
        return timeDateOrderDao.getByUserIdFullDetails(userId);
    }

    public List<ServiceDateTime> getFullOrderDetails(int orderid) throws SQLException {
        return timeDateOrderDao.getFullOrderDetails(orderid);
    }
}
