package com.fertifa.app.controllers;

import com.fertifa.app.dao.PackageDao;
import com.fertifa.app.models.Packages;
import com.fertifa.app.services.PackagService;

import java.sql.SQLException;
import java.util.List;

public class PackageController implements PackagService {
    private PackageDao packageDao = new PackageDao();
    @Override
    public List<Packages> getAllpackages() throws SQLException {
        return packageDao.getAllPackageList();
    }

    @Override
    public int getPackagId(String packageName) throws SQLException {
        return packageDao.getPackageIdByName(packageName);
    }


}
