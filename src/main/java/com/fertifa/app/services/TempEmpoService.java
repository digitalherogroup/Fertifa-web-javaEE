package com.fertifa.app.services;

import com.fertifa.app.parser.EmployeeModel;

import java.sql.SQLException;
import java.util.List;

public interface TempEmpoService {
    int saveAll(EmployeeModel createTempEmploObject) throws SQLException;

    List<EmployeeModel> TempEmpoListByComapnyId(int id) throws SQLException;

    List<EmployeeModel> getUserById(int userInvitingId) throws SQLException;

    int DeleteById(int userInvitingId) throws SQLException;

    List<EmployeeModel> GetAllUsers() throws SQLException;

    int DeleteByCompanyId(int id) throws SQLException;

    boolean CheckIfCompanyId(int id) throws SQLException;
}
