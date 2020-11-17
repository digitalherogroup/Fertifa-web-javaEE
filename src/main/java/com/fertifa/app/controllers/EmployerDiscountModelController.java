package com.fertifa.app.controllers;


import com.fertifa.app.dao.EmployerDiscountModelDao;
import com.fertifa.app.models.EmployerDiscountsModel;

import java.sql.SQLException;


public class EmployerDiscountModelController {

    private EmployerDiscountModelDao employeeDiscountModelDao = new EmployerDiscountModelDao();


    public int save(EmployerDiscountsModel employerDiscountsModel) throws SQLException {
        return employeeDiscountModelDao.save(employerDiscountsModel);
    }

    public EmployerDiscountsModel findById(int linkId) throws SQLException {
        return employeeDiscountModelDao.findById(linkId);
    }

    public int updateDiscounts(EmployerDiscountsModel employerDiscountsModel) throws SQLException {
        return employeeDiscountModelDao.updateDiscounts(employerDiscountsModel);
    }
}
