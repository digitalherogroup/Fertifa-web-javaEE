package com.fertifa.app.controllers;

import com.fertifa.app.dao.TempEmployeDao;
import com.fertifa.app.services.TempEmpoService;
import com.fertifa.app.parser.EmployeeModel;

import java.sql.SQLException;
import java.util.List;

public class TempEmpoController implements TempEmpoService {

    private TempEmployeDao tempEmployeDao = new TempEmployeDao();

    @Override
    public int saveAll(EmployeeModel employeeModel) throws SQLException {
        return tempEmployeDao.saveAll(employeeModel);
    }

    @Override
    public List<EmployeeModel> TempEmpoListByComapnyId(int id) throws SQLException {
        return tempEmployeDao.tempEmpoListByComapnyId(id);
    }

    @Override
    public List<EmployeeModel> getUserById(int userInvitingId) throws SQLException {
        return tempEmployeDao.getUserById(userInvitingId);
    }

    @Override
    public int DeleteById(int userInvitingId) throws SQLException {
        return tempEmployeDao.deleteById(userInvitingId);
    }

    @Override
    public List<EmployeeModel> GetAllUsers() throws SQLException {
        return tempEmployeDao.GetAll();
    }

    @Override
    public int DeleteByCompanyId(int id) throws SQLException {
        return tempEmployeDao.DeleteByCompanyId(id);
    }

    @Override
    public boolean CheckIfCompanyId(int id) throws SQLException {
        return tempEmployeDao.CheckIfCompanyId(id);
    }
}
