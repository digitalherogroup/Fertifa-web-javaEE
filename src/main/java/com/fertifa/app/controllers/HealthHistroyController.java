package com.fertifa.app.controllers;

import com.fertifa.app.dao.HealthHistoryDao;
import com.fertifa.app.models.HealthHistory;

import java.sql.SQLException;
import java.util.List;

public class HealthHistroyController {

    private HealthHistoryDao healthHistoryDao = new HealthHistoryDao();

    public int save(HealthHistory healthHistory) throws SQLException {
        return healthHistoryDao.save(healthHistory);
    }

    public List<HealthHistory> getHistoryById(int userId) throws SQLException {
        return healthHistoryDao.getHistoryById(userId);
    }

    public int updatesave(HealthHistory healthHistory, int userId) throws SQLException {
        return healthHistoryDao.updatesave(healthHistory,userId);
    }
}
