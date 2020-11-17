package com.fertifa.app.controllers;

import com.fertifa.app.dao.AdminDao;
import com.fertifa.app.models.Admins;
import com.fertifa.app.services.AdminServices;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class AdminController implements AdminServices {

    private AdminDao adminDao = new AdminDao();

    @Override
    public boolean validateAdminUsers(String email, String password) throws SQLException {
        return adminDao.validateAdminUsers(email, password);
    }

    @Override
    public int getAdminId(String email) {
        return adminDao.getAdminIdByEmail(email);
    }

    @Override
    public int CheckAdminRoleId(int adminId) {
        return adminDao.getAdminRole(adminId);
    }

    @Override
    public List<Admins> getAllAdminListById(int userId) {
        return adminDao.showAllAdminById(userId);
    }

/*    public Admins getAdminsById(int userId) {
        return adminDao.getAdminObject(userId);
    }*/

    @Override
    public int saveNurse(Admins admin) throws SQLException {
        return adminDao.saveNurse(admin);
    }

    @Override
    public List<Admins> GetAllNurseList() throws SQLException {
        return adminDao.getUsersByRoleUser(4);
    }

    @Override
    public boolean checkIfEmail(String email) throws SQLException {
        return adminDao.checkEmail(email);
    }

    @Override
    public List<Admins> getAllNurses() throws SQLException {
        return adminDao.getAllNerses();
    }

    @Override
    public int DeleteById(int nersesId) throws SQLException {
        return adminDao.DeleteById(nersesId);
    }

    @Override
    public int UpdatNurseById(Admins admins, int id, HttpServletRequest request, HttpServletResponse response) throws SQLException {
        return adminDao.UpdateById(admins,id);
    }

    public boolean isAdminExsist(String userEmail) throws SQLException {
        return adminDao.isAdminExsist(userEmail);
    }


    public Admins getAdmin(String element, String email) {
        return adminDao.getAdminObject(element,email);
    }

    //for chat
    public Admins getAdminObjectById(Long id) throws SQLException {
        return adminDao.getAdminObjectById(id);
    }

    public List<Admins> getAllAdmins() throws SQLException {
        return adminDao.getAllAdmins();
    }
}
