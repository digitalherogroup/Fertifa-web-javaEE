package com.fertifa.app.controllers;

import com.fertifa.app.dao.TestingOrdersDao;
import com.fertifa.app.models.TestingOrders;

import java.sql.SQLException;
import java.util.List;

public class TestingOrdersController {

    TestingOrdersDao testingOrdersDao = new TestingOrdersDao();

    public List<TestingOrders> getAll() throws SQLException {
        return testingOrdersDao.getAll();
    }

    public List<TestingOrders> getById(int testingId) throws SQLException {
        return testingOrdersDao.getById(testingId);
    }

    public TestingOrders findById(int dataId) {
        return testingOrdersDao.findById(dataId);
    }//

    public TestingOrders findByTestId(int dataId) {
        return testingOrdersDao.findByTestId(dataId);
    }
}
