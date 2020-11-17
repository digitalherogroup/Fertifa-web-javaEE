package com.fertifa.app.controllers;


import com.fertifa.app.dao.WhiteDao;
import com.fertifa.app.models.WhiteModel;

import java.sql.SQLException;
import java.util.List;

public class WhiteController {

    private WhiteDao whiteDao = new WhiteDao();

    public int save(WhiteModel whiteModel) throws SQLException {
        return whiteDao.saveWhite(whiteModel);
    }

    public List<WhiteModel> findAll(int employeeId) throws SQLException {
        return whiteDao.findAllById(employeeId);
    }

    public List<WhiteModel> findAllByStatus(int employeeId) throws SQLException {
        return whiteDao.findAllByStatus(employeeId);
    }

    public int deleteByUserId(int userId) throws SQLException {
        return whiteDao.delete(userId);
    }

    public List<WhiteModel> findWhiteIdDomain(int id) throws SQLException {
        return whiteDao.findWhiteIdDomain(id);
    }

    public int update(WhiteModel whiteModel) throws SQLException {
        return whiteDao.update(whiteModel);
    }

    public List<WhiteModel> findWhiteIdDomainByID(int id) throws SQLException {
        return whiteDao.findWhiteIdDomainByID(id);
    }

    public List<WhiteModel> findWhiteIdDomainZero(int id) throws SQLException {
        return whiteDao.findWhiteIdDomainZero(id);
    }

    public List<WhiteModel> findWhiteIdDomainOne(int id) throws SQLException {
        return whiteDao.findWhiteIdDomainOne(id);
    }
}
