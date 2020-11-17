package com.fertifa.app.services;

import com.fertifa.app.models.Partners;

import java.sql.SQLException;
import java.util.List;

public interface PartnerService {

    List<Partners> showAll() throws SQLException;

    int save(Partners newPartner) throws SQLException;

    List<Partners> getById(int partnerId) throws SQLException;

    int UpdateImageById(Partners partnerModel, int id) throws SQLException;

    int Update(Partners partnersModel, int id) throws SQLException;

    int DeletePartner(int partnerId) throws SQLException;

}
