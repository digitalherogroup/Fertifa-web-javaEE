package com.fertifa.app.services;

import java.sql.SQLException;

public interface RoleService {
    String getRoleByUserId(int id);
    int getRoleNameById(String RoleNamw) throws SQLException;

}
