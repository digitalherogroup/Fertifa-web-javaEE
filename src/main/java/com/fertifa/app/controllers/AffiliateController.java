package com.fertifa.app.controllers;

import com.fertifa.app.dao.AffiliatDao;
import com.fertifa.app.models.Affiliate;

import java.sql.SQLException;
import java.util.List;

public class AffiliateController {

    private AffiliatDao affiliatDao = new AffiliatDao();


    public boolean checkingAnythingInAffiliate(String check, String email) throws SQLException {
        return affiliatDao.checkingAnythingInAffiliate(check,email);
    }

    public int saveAffiliate(Affiliate affiliate) throws SQLException {
        return affiliatDao.saveNewAffiliate(affiliate);
    }

    public int updateAffiliate(Affiliate affiliate) throws SQLException {
        return affiliatDao.updateAffiliate(affiliate);
    }

    public List<Affiliate> findAll() throws SQLException {
        return affiliatDao.findAll();
    }

    public int getTheAffiliateId() throws SQLException {
        return affiliatDao.getLastId();
    }

    public Affiliate findAllById(String id) throws SQLException {
        return  affiliatDao.findById(id);
    }
}
