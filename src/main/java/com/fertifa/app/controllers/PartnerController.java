package com.fertifa.app.controllers;

import com.fertifa.app.dao.PartnerDao;
import com.fertifa.app.models.Partners;
import com.fertifa.app.services.PartnerService;

import java.sql.SQLException;
import java.util.List;

public class PartnerController implements PartnerService {
    private PartnerDao partnerDao  = new PartnerDao();

    @Override
    public List<Partners> showAll() throws SQLException {
        return partnerDao.showAll();
    }

    @Override
    public int save(Partners newPartner) throws SQLException {
        return partnerDao.save(newPartner);
    }

    @Override
    public List<Partners> getById(int partnerId) throws SQLException {
        return partnerDao.getById(partnerId);
    }

    @Override
    public int UpdateImageById(Partners partnerModel, int id) throws SQLException {
        return partnerDao.UpdateImageById(partnerModel,id);
    }

    @Override
    public int Update(Partners partnersModel, int id) throws SQLException {
        return partnerDao.Update(partnersModel,id);
    }

    @Override
    public int DeletePartner(int partnerId) throws SQLException {
        return partnerDao.DeletePartner(partnerId);
    }
}
