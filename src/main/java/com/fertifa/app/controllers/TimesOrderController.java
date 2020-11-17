package com.fertifa.app.controllers;

import com.fertifa.app.dao.TimesOrderDao;
import com.fertifa.app.models.TimesOrder;

import java.sql.SQLException;
import java.util.List;

public class TimesOrderController {
    TimesOrderDao timesOrderDao = new TimesOrderDao();


    public int AddNewTimeOrder(TimesOrder timesOrder, int dateid) throws SQLException {
        return timesOrderDao.AddNewTimeOrder(timesOrder, dateid);
    }

    public List<TimesOrder> getLastOrderTimeid() throws SQLException {
        return timesOrderDao.getLastOrderTimeid();
    }

    public int getLastTimeId() throws SQLException {
        return timesOrderDao.getLastTimeId();
    }

    public List<TimesOrder> getTimeOrderByOrderId(int order_id) throws SQLException {
        return timesOrderDao.getTimeOrderByOrderId(order_id);
    }

    public int deleteByOrderId(int oderId) throws SQLException {
        return timesOrderDao.deleteByOrderId(oderId);
    }
}
