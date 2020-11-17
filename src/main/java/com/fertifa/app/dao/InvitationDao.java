package com.fertifa.app.dao;

import com.fertifa.app.Connection.DBConnection;
import com.fertifa.app.models.Tokens;

import java.sql.*;
import java.util.Date;

public class InvitationDao {
    /**
     * Data gates
     *
     * @return
     * @throws SQLException
     */
    private Connection ConnectToData() throws SQLException {
        return DBConnection.getConnectionToDatabase();
    }


    public int AddNewCompanyInvitation(Tokens tokens) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `tokens`" +
                    "(`id`, `token`,`user_id`,`creation_date`,`name`,`email`) "
                    + "VALUES (Default,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatmentInvitation(tokens, statment);
            rowsAffected = statment.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statment != null) {
                statment.close();
            }
        }

        return rowsAffected;
    }

    private void setStatmentInvitation(Tokens tokens, PreparedStatement statment) throws SQLException {
        statment.setString(1, tokens.getToken());
        statment.setInt(2, tokens.getUserid());
        statment.setTimestamp(3, new Timestamp(new Date().getTime()));
        statment.setString(4, tokens.getName());
        statment.setString(5, tokens.getEmail());
    }

    /**
     * Checking invitation incomes
     *
     * @param tokenId
     * @param company
     * @return
     * @throws SQLException
     */
    public boolean ControllIncomeValues(String tokenId, String company) throws SQLException {
        boolean isValidAdminUser = false;
        PreparedStatement statment = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM  `tokens` WHERE `token`=? AND `name`=? ";
            statment = connection.prepareStatement(sql);
            statment.setString(1, tokenId);
            statment.setString(2, company);
            set = statment.executeQuery();
            while (set.next()) {
                isValidAdminUser = true;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        } finally {
            if (statment != null) {
                statment.close();
            }
            if (set != null) {
                set.close();
            }
        }
        return isValidAdminUser;
    }

    /**
     * Getting email from token
     *
     * @param tokenId
     * @return
     */
    public String getCompanyEmailById(String tokenId) {
        String email = null;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `tokens` WHERE token=?";
            statment = connection.prepareStatement(sql);
            statment.setString(1, tokenId);
            set = statment.executeQuery();
            while (set.next()) {
                email = set.getString("email");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return email;
    }

    public int getCompanyIdByToken(String tokenId) {
        int userid = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `tokens` WHERE token= '" + tokenId + "'";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                userid = set.getInt("user_id");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return userid;
    }

    public boolean CheckingInvitationUser(String tokenId, String firstName, String lastName) throws SQLException {
        boolean isValidAdminUser = false;
        PreparedStatement statment = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM  `tokens` WHERE `token`=? AND `firstname`=? AND `lastname`=?";
            statment = connection.prepareStatement(sql);
            statment.setString(1, tokenId);
            statment.setString(2, firstName);
            statment.setString(3, lastName);
            set = statment.executeQuery();
            while (set.next()) {
                isValidAdminUser = true;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message  CheckingInvitationUser: " + exception);
        } finally {
            if (statment != null) {
                statment.close();
            }
            if (set != null) {
                set.close();
            }
        }
        return isValidAdminUser;
    }

    public int AddNewTokenAndUser(Tokens createUserObject) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `tokens`" +
                    "(`id`, `user_id`,`token`,`email`,`creation_date`,`firstname`,`lastname`) "
                    + "VALUES (Default,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatmentInvitationUser(createUserObject, statment);
            rowsAffected = statment.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statment != null) {
                statment.close();
            }
        }

        return rowsAffected;
    }

    private void setStatmentInvitationUser(Tokens createUserObject, PreparedStatement statment) throws SQLException {
        statment.setInt(1, createUserObject.getUserid());
        statment.setString(2, createUserObject.getToken());
        statment.setString(3, createUserObject.getEmail());
        statment.setTimestamp(4, new Timestamp(new Date().getTime()));
        statment.setString(6, createUserObject.getLastName());
        statment.setString(5, createUserObject.getFirstName());
    }

    public int AddNewTokenAndUserOne(Tokens createUserObject) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `tokens`" +
                    "(`id`, `user_id`,`token`,`firstname`,`creation_date`,`lastname`,`email`) "
                    + "VALUES (Default,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatmentInvitationUserOne(createUserObject, statment);
            rowsAffected = statment.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statment != null) {
                statment.close();
            }
        }

        return rowsAffected;
    }

    private void setStatmentInvitationUserOne(Tokens createUserObject, PreparedStatement statment) throws SQLException {
        statment.setInt(1, createUserObject.getUserid());
        statment.setString(2, createUserObject.getToken());
        statment.setString(5, createUserObject.getEmail());
        statment.setTimestamp(4, new Timestamp(new Date().getTime()));
        statment.setString(3, createUserObject.getLastName());
        statment.setString(6, createUserObject.getFirstName());
    }

    public boolean checkUserId(int userid) throws SQLException {
        boolean isValidAdminUser = false;
        PreparedStatement statment = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM  `tokens` WHERE `user_id`=?";
            statment = connection.prepareStatement(sql);
            statment.setInt(1, userid);

            set = statment.executeQuery();
            while (set.next()) {
                isValidAdminUser = true;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        } finally {
            if (statment != null) {
                statment.close();
            }
            if (set != null) {
                set.close();
            }
        }
        return isValidAdminUser;
    }

    public int UpdateTokenByUserId(String Token, int userEmailId) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `tokens`  " +
                    "SET `token`=? WHERE `user_id`=" + userEmailId;
            statment = connection.prepareStatement(sql);
            statment.setString(1, Token);

            rowsUpdated = statment.executeUpdate();
            if (rowsUpdated > 0) {

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statment != null) {
                statment.close();
            }
        }
        return rowsUpdated;
    }


    public boolean CheckingInvitationAffiliate(String email, String affiliateToken) throws SQLException {
        boolean isValidAdminUser = false;
        PreparedStatement statment = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM  `tokens` WHERE `token`=? AND `email`=?";
            statment = connection.prepareStatement(sql);
            statment.setString(1, affiliateToken);
            statment.setString(2, email);

            set = statment.executeQuery();
            while (set.next()) {
                isValidAdminUser = true;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message  CheckingInvitationUser: " + exception);
        } finally {
            if (statment != null) {
                statment.close();
            }
            if (set != null) {
                set.close();
            }
        }
        //need to change the return
        return true;
    }
}
