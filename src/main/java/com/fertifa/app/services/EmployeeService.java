package com.fertifa.app.services;

import com.fertifa.app.models.Admins;

import java.sql.SQLException;

public interface EmployeeService {

    int addNewEmployeeToData(Admins admins) throws SQLException;
}
