package com.fertifa.app.controllers;

import com.fertifa.app.dao.NurseDao;
import com.fertifa.app.models.Partners;
import com.fertifa.app.services.NurseServices;

public class NurseController implements NurseServices {
    NurseDao nurseDao = new NurseDao();

    public boolean save(Partners newPartnerWithoutImage) {
        /*return nurseDao.save(newPartnerWithoutImage);*/
return false;
    }
}
