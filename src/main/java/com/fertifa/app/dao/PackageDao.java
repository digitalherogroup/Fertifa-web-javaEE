package com.fertifa.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fertifa.app.Connection.DBConnection;
import com.fertifa.app.models.Packages;

public class PackageDao {
    /**
     * Data gates
     *
     * @return
     * @throws SQLException
     */
    private Connection ConnectToData() throws SQLException {
        return DBConnection.getConnectionToDatabase();
    }

    /**
     * Get all Packag list
     * @return
     * @throws SQLException
     */
    public List<Packages> getAllPackageList() throws SQLException {
        List<Packages> packagesList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `packages`";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfpackages(set, packagesList);

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statment != null) {
                statment.close();
            }
            if (set != null) {
                set.close();
            }
        }
        return packagesList;
    }

    /**
     * Get package id by Name
     * @param packageName
     * @return
     * @throws SQLException
     */
    public int getPackageIdByName(String packageName) throws SQLException {
        int packagId =0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try{
            connection = ConnectToData();
            String sql = "SELECT *  FROM `packages` WHERE `packagname` LIKE '%" + packageName + "%'";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()){
                packagId = set.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statment != null) {
                statment.close();
            }
            if (set != null) {
                set.close();
            }
        }
        return packagId;
    }

    /**
     * List of packages
     * @param set
     * @param packagesList
     * @throws SQLException
     */
    private void ListOfpackages(ResultSet set, List<Packages> packagesList) throws SQLException {
        Packages packages = null;
        while (set.next()){
            packages = new Packages();
            packages.setId(set.getInt("id"));
            packages.setPackagName(set.getString("packagname"));
            packagesList.add(packages);
        }

    }


}
