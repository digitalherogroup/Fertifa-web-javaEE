package com.fertifa.app.services;

import com.fertifa.app.models.Admins;
import com.fertifa.app.models.ChatSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public interface AdminServices {
    boolean validateAdminUsers(String email, String password) throws SQLException;

    int getAdminId(String email);

    int CheckAdminRoleId(int adminId);

    List<Admins> getAllAdminListById(int adminId);

    int saveNurse(Admins admin) throws SQLException;

    List<Admins> GetAllNurseList() throws SQLException;

    boolean checkIfEmail(String email) throws SQLException;

    List<Admins> getAllNurses() throws SQLException;

    int DeleteById(int nersesId) throws SQLException;

    int UpdatNurseById(Admins admins, int id, HttpServletRequest request, HttpServletResponse response) throws SQLException;

}
