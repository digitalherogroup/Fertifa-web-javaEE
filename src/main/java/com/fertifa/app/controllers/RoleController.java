package com.fertifa.app.controllers;

import com.fertifa.app.dao.RoleDao;
import com.fertifa.app.services.RoleService;

import java.sql.SQLException;

public class RoleController implements RoleService {

    private RoleDao roleDao = new RoleDao();

    @Override
    public String getRoleByUserId(int roleId) {
        return roleDao.getRoleById(roleId);
    }

    @Override
    public int getRoleNameById(String RoleName) throws SQLException {
        return roleDao.getRoleByName( RoleName);
    }
}
