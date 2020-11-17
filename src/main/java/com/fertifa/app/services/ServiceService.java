package com.fertifa.app.services;

import com.fertifa.app.models.Services;

import java.sql.SQLException;
import java.util.List;

public interface ServiceService {

    List<Services> getAll(int type) throws SQLException;
    List<Services> getById (int id) throws SQLException;
}
