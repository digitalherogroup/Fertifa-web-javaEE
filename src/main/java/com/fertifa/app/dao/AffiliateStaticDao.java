package com.fertifa.app.dao;

import com.fertifa.app.Connection.DBConnection;
import com.fertifa.app.affiliate.builder.AffiliateStatics;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AffiliateStaticDao {

    private Connection ConnectToData() throws SQLException {
        return DBConnection.getConnectionToDatabase();

    }

    public int saveStatic(AffiliateStatics affiliatStatic) throws SQLException {
        int rows = 0;
        Connection connection = null;
        //ResultSet set = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `afstatics`" +
                    "(`id`, `affiliate_email`, `affiliat_id`, `link_date`,`link_click`,`register_click`,`ip`,`link_five_seconds`,`link_les_five_seconds`) "
                    + "VALUES (Default,?,?,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            statment.setString(1, affiliatStatic.getAffiliateEmail());
            statment.setInt(2, affiliatStatic.getAffiliateId());
            statment.setTimestamp(3, affiliatStatic.getDateOfStatic());
            statment.setInt(4, affiliatStatic.getClickId());
            statment.setInt(5, affiliatStatic.getRegisterId());
            statment.setString(6, affiliatStatic.getIp());
            statment.setInt(7, affiliatStatic.getFiveSecondsClick());
            statment.setInt(8, affiliatStatic.getLessFiveSecondsClick());
            rows = statment.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error inside save affiliate " + e);
        } finally {
            closeAll(statment, null, connection);
        }
        return rows;
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

    public boolean checkingClicksByIpInAffiliateStatic(String check, String toCheck, String Where, int i) throws SQLException {
        boolean isTrue = false;
        PreparedStatement statment = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT `" + check + "` FROM `afstatics` WHERE `" + check + "`=? AND `" + Where + "`=?";
            statment = connection.prepareStatement(sql);
            statment.setString(1, toCheck);
            statment.setInt(2, i);
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


    public int CountClicksByAffiliateId(String toCount, int affiliateId) throws SQLException {
        int countUsers = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT SUM(" + toCount + ") FROM `afstatics` where `affiliat_id`=" + affiliateId;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                countUsers = (set.getInt(1));
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        } finally {
            closeAll(statment, set, connection);
        }
        return countUsers;
    }

    public int updateStaticLessFiveSeconds(AffiliateStatics affiliateStatic) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `afstatics`  " +
                    "SET `affiliate_email`=?, `affiliat_id`=?, `link_date`=?,`link_click`=?,`register_click`=?,`ip`=?,`link_five_seconds`=? WHERE `link_les_five_seconds` = 1 AND id=" + affiliateStatic.getId();
            statment = connection.prepareStatement(sql);
            UpdateAffiliateStatic(affiliateStatic, statment);

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

    private void UpdateAffiliateStatic(AffiliateStatics affiliateStatic, PreparedStatement statment) throws SQLException {
        statment.setString(1, affiliateStatic.getAffiliateEmail());
        statment.setInt(2, affiliateStatic.getAffiliateId());
        statment.setTimestamp(3, affiliateStatic.getDateOfStatic());
        statment.setInt(4, affiliateStatic.getClickId());
        statment.setInt(5, affiliateStatic.getRegisterId());
        statment.setString(6, affiliateStatic.getIp());
        statment.setInt(7, affiliateStatic.getFiveSecondsClick());

    }

    public int CountLostClicks(String toCount, int affiliateId) throws SQLException {
        int countUsers = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT SUM(" + toCount + ") FROM `afstatics` where `link_five_seconds`= 0 AND `affiliat_id`=" + affiliateId;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                countUsers = (set.getInt(1));
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        } finally {
            closeAll(statment, set, connection);
        }
        return countUsers;
    }
}
