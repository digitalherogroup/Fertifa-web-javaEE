package com.fertifa.app.services;

import com.fertifa.app.models.Packages;

import java.sql.SQLException;
import java.util.List;

public interface PackagService {
    List<Packages> getAllpackages() throws SQLException;

    int getPackagId(String packageName) throws SQLException;
}
