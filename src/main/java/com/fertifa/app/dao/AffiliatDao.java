package com.fertifa.app.dao;

import com.fertifa.app.Connection.DBConnection;
import com.fertifa.app.constants.AffiliateConstances;
import com.fertifa.app.models.Affiliate;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AffiliatDao {

    private Connection ConnectToData() throws SQLException {
        return DBConnection.getConnectionToDatabase();

    }

    //Check anyThing In Affiliates
    public boolean checkingAnythingInAffiliate(String check, String toCheck) throws SQLException {
        boolean isTrue = false;
        PreparedStatement statment = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT `" + check + "` FROM `affiliate` WHERE `" + check + "`=?";
            statment = connection.prepareStatement(sql);
            statment.setString(1, toCheck);
            set = statment.executeQuery();
            while (set.next()) {
                isTrue = true;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        } finally {
            closeAll(statment, set, connection);
        }
        return isTrue;
    }


    public int saveNewAffiliate(Affiliate affiliate) throws SQLException {
        int rows = 0;
        Connection connection = null;
        //ResultSet set = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `affiliate`" +
                    "(`id`, `firstname`, `lastname`, `email`,`role`,`status`,`creation_date`,`company`,`secret_key`,`package`) "
                    + "VALUES (Default,?,?,?,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            statment.setString(1, affiliate.getFirstName());
            statment.setString(2, affiliate.getLastName());
            statment.setString(3, affiliate.getEmail());
            statment.setString(4, AffiliateConstances.AFFILIATE_ROLE_NAME);
            statment.setInt(5, AffiliateConstances.AFFILIATE_ACTIVE);
            statment.setString(6, new Timestamp(new Date().getTime()).toString());
            statment.setString(7, affiliate.getCompany());
            statment.setString(8, affiliate.getSecretKey());
            statment.setString(9, affiliate.getPackageId());
            rows = statment.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error inside save com.fertifa.app.affiliate " + e);
        } finally {
            closeAll(statment, null, connection);
        }
        return rows;

    }

    public int updateAffiliate(Affiliate affiliate) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `affiliate`  " +
                    "SET `firstname`=?,`lastname`=?,`email`=?,`password`=?,`role`=?,`status`=?,`gender`=?,`secret_key`=?, `registered_date`=? WHERE id=" + affiliate.getId();
            statment = connection.prepareStatement(sql);
            UpdateAffiliateList(affiliate, statment);

            rowsUpdated = statment.executeUpdate();
            if (rowsUpdated > 0) {

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            closeAll(statment, null, connection);
        }
        return rowsUpdated;

    }

    private void UpdateAffiliateList(Affiliate affiliate, PreparedStatement statment) throws SQLException {
        statment.setString(1, affiliate.getFirstName());
        statment.setString(2, affiliate.getLastName());
        statment.setString(3, affiliate.getEmail());
        statment.setString(4, affiliate.getPassword());
        statment.setString(5, affiliate.getRole());
        statment.setInt(6, affiliate.getStatus());
        statment.setString(7, affiliate.getGender());
        statment.setString(8, affiliate.getSecretKey());
        statment.setString(9, new Timestamp(new Date().getTime()).toString());
    }

    public List<Affiliate> findAll() throws SQLException {
        List<Affiliate> affiliateArrayList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `affiliate`";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfAffiliate(set, affiliateArrayList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(statment, set, connection);
        }
        return affiliateArrayList;
    }

    private void ListOfAffiliate(ResultSet set, List<Affiliate> affiliateArrayList) throws SQLException {
        Affiliate affiliate = null;
        while (set.next()) {
            affiliate = new Affiliate();
            affiliate.setId(set.getInt("id"));
            affiliate.setFirstName(set.getString("firstname"));
            affiliate.setLastName(set.getString("lastname"));
            affiliate.setEmail(set.getString("email"));
            affiliate.setStatus(set.getInt("status"));
            affiliate.setRole(set.getString("role"));
            affiliate.setCreationDate(set.getString("creation_date"));
            affiliateArrayList.add(affiliate);
        }
    }

    public int getLastId() throws SQLException {
        int userId = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT id FROM affiliate WHERE id=( SELECT max(id) FROM affiliate )";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                userId = set.getInt("id");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        } finally {
            closeAll(statment, set, connection);
        }
        return userId;
    }

    public Affiliate findById(String id) throws SQLException {
        Affiliate affiliate = new Affiliate();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `affiliate` where `id`=" + id;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            if (set.next()) {
                affiliate = AffiliateObject(set);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(statment, set, connection);
        }
        return affiliate;
    }

    private void closeAll(PreparedStatement statment, ResultSet set, Connection connection) throws SQLException {
        if (statment != null) {
            statment.close();
        }
        if (set != null) {
            set.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    private Affiliate AffiliateObject(ResultSet set) throws SQLException {
        return new Affiliate(set.getInt("id"),
                set.getString("firstname"),
                set.getString("lastname"),
                set.getString("email"),
                set.getInt("status"),
                set.getString("role"),
                set.getString("creation_date"),
                set.getString("secret_key"));

    }

}
