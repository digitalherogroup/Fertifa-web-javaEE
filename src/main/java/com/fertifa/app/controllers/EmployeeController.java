package com.fertifa.app.controllers;

import com.fertifa.app.dao.AdminDao;
import com.fertifa.app.models.Admins;
import com.fertifa.app.services.EmployeeService;

import java.sql.SQLException;

public class EmployeeController implements EmployeeService {
    private AdminDao adminDao = new AdminDao();
    @Override
    public int addNewEmployeeToData(Admins admins) throws SQLException {
        return adminDao.addNewCompany(admins) ;
    }
}
