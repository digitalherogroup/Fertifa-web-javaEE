package com.fertifa.app.controllers;

import com.fertifa.app.dao.OrderDao;
import com.fertifa.app.models.Orders;
import com.fertifa.app.models.ShoppingCartFinal;
import com.fertifa.app.services.OrderService;

import java.sql.SQLException;
import java.util.List;

public class OrderController implements OrderService {

    private OrderDao orderDao = new OrderDao();
    @Override
    public List<Orders> getAll() throws SQLException {
        return orderDao.showAll();
    }

    @Override
    public List<Orders> orderById(int id) throws SQLException {
        return orderDao.orderById(id);
    }

    @Override
    public List<Orders> getAllByUseriD(int userId) throws SQLException {
        return orderDao.UserOrderById(userId);
    }

    public List<ShoppingCartFinal> UserOrderShoopingById(int userId) throws SQLException {
        return orderDao.UserOrderShoopingById(userId);
    }

    @Override
    public int AddNewOrder(Orders orders) throws SQLException {
        return orderDao.AddNewOrder(orders);
    }

    public int AddNewOrderFirst(Orders orders) throws SQLException {
        return orderDao.AddNewOrderFirst(orders);
    }

    public int UpdateOrderDetailWithOrderId(Orders orders, int orderId) throws SQLException {
        return orderDao.UpdateOrderDetailWithOrderId(orders,orderId);
    }

    public int ChangeStatusToApproved(Orders orders, int orderId) throws SQLException {
        return orderDao.ChangeStatusToApproved(orders,orderId);
    }

    public int ChangeStatusToChanged( int orderId) throws SQLException {
        return orderDao.ChangeStatusToChanged(orderId);
    }


    public List<Orders> getAllByOrderId(int dataId) throws SQLException {
        return orderDao.getAllByOrderId(dataId);
    }

    public int getServiceIdByUserid(int userId) {
        return orderDao.getServiceIdByUserid(userId);
    }

    public int UpdateDateTimeIdbyOrderId(int timeid, int dateId, int orderid) throws SQLException {
        return orderDao.UpdateDateTimeIdbyOrderId(timeid,dateId,orderid);
    }

    public int SelectLastOrderId(int userid) throws SQLException {
        return orderDao.SelectLastOrderId(userid);

    }

    public List<ShoppingCartFinal> UserShoppingFinalMyOrderById(int userId) throws SQLException {
        return orderDao.UserShoppingFinalMyOrderById(userId);
    }

    public int lastId(int userId) throws SQLException {
        return orderDao.lastId(userId);
    }
}
