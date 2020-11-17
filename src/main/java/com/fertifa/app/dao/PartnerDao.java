package com.fertifa.app.dao;

import com.fertifa.app.Connection.DBConnection;
import com.fertifa.app.models.Partners;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartnerDao {

    /**
     * Data gates
     *
     * @return
     * @throws SQLException
     */
    private Connection ConnectToData() throws SQLException {
        return DBConnection.getConnectionToDatabase();
    }

    public List<Partners> showAll() throws SQLException {
        List<Partners> partnersList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `partners`";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfPartners(set, partnersList);

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
        return partnersList;
    }

    private void ListOfPartners(ResultSet set, List<Partners> partnersList) throws SQLException {
        Partners partners = null;
        while (set.next()) {
            partners = new Partners();
            partners.setId(set.getInt("id"));
            partners.setPartnerName(set.getString("partnerName"));
            partners.setCreationDate(set.getTimestamp("createddate"));
            partners.setPartnerDiscription(set.getString("discription"));
            partners.setLogoLink(set.getString("logolink"));
            partners.setDomain(set.getString("domain"));
            partnersList.add(partners);
        }
    }

    public int save(Partners newPartner) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `partners`" +
                    "(`id`, `partnerName`, `createddate`, `logolink`,`discription`,`domain`) "
                    + "VALUES (Default,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatmentFoSavePartner(newPartner, statment);
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

    private void setStatmentFoSavePartner(Partners newPartner, PreparedStatement statment) throws SQLException {
        statment.setString(1,newPartner.getPartnerName());
        statment.setTimestamp(2,newPartner.getCreationDate());
        statment.setString(3,newPartner.getLogoLink());
        statment.setString(4,newPartner.getPartnerDiscription());
        statment.setString(5,newPartner.getDomain());
    }

    public List<Partners> getById(int partnerId) throws SQLException {
        List<Partners> partnerList = new ArrayList<>();
        Partners partners = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `partners` WHERE id=" + partnerId;
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            ListOfPartners(set, partnerList);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (set != null) {
                set.close();
            }
        }
        return partnerList;
    }

    public int UpdateImageById(Partners partnerModel, int id) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `partners`  " +
                    "SET  logolink=?,partnerName=?,discription=?,domain=?" +
                    "WHERE id=" + id;
            statment = connection.prepareStatement(sql);
            statment.setString(1,partnerModel.getLogoLink());
            statment.setString(2,partnerModel.getPartnerName());
            statment.setString(3,partnerModel.getPartnerDiscription());
            statment.setString(4,partnerModel.getDomain());

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

    public int Update(Partners partnersModel, int id) throws SQLException {

        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `partners`  " +
                    "SET partnerName=?,discription=?,domain=? WHERE id=" + id;
            statment = connection.prepareStatement(sql);
            setStatmentFoUpdatePartner(partnersModel, statment);

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

    private void setStatmentFoUpdatePartner(Partners partnersModel, PreparedStatement statment) throws SQLException {
        statment.setString(1,partnersModel.getPartnerName());
        statment.setString(2,partnersModel.getPartnerDiscription());
        statment.setString(3,partnersModel.getDomain());
    }

    public int DeletePartner(int partnerId) throws SQLException {
        int rowsDeleted = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = ConnectToData();

            String sql = "DELETE FROM `partners` WHERE  id=" + partnerId;
            statment = connection.prepareStatement(sql);

            rowsDeleted = statment.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A News was deleted successfully!");
            }

        } catch (SQLException exception) {
            System.out.println("sqlException in Application in CATEGORY DELETE  Section  : " + exception);
            exception.printStackTrace();
        } finally {
            if (statment != null) {
                statment.close();
            }
            if(connection != null){
                connection.close();
            }
        }
        return rowsDeleted;
    }
}
