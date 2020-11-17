package com.fertifa.app.controllers;

import com.fertifa.app.dao.ServiceDao;
import com.fertifa.app.models.Services;
import com.fertifa.app.services.ServiceService;

import java.sql.SQLException;
import java.util.List;

public class ServoceController implements ServiceService {

    ServiceDao servicedao = new ServiceDao();
    @Override
    public List<Services> getAll(int type) throws SQLException {
        return servicedao.getAll(type);
    }

    @Override
    public List<Services> getById(int id) throws SQLException {
        return servicedao.getByID(id);
    }

    public String getServiceNameById(int getServiceNameById) {
        return servicedao.getServiceNameById(getServiceNameById);
    }

    public String getProductNameById(int getServiceNameById) {
        return servicedao.getProductNameById(getServiceNameById);
    }

    public Services findById(int dataId) {
        return servicedao.findById(dataId);
    }//

    public Services findService(int dataId) {
        return servicedao.findService(dataId);
    }
}
