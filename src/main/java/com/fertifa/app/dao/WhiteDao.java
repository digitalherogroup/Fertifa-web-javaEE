package com.fertifa.app.dao;

import com.fertifa.app.Connection.DBConnection;
import com.fertifa.app.models.WhiteModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WhiteDao {

    private Connection ConnectToData() throws SQLException {
        return DBConnection.getConnectionToDatabase();
    }

    public int saveWhite(WhiteModel whiteModel) throws SQLException {
        String str = whiteModel.getWhiteDomain();
        int rowsAffected = 0;
        String[] whiteDomainArray = whiteModel.getWhiteDomain().split(",");
        Connection connection = null;
        PreparedStatement statement = null;
        for (String s : whiteDomainArray) {
            if (s.contains("[") && s.contains("]")) {
                int indexOfOpenBracket = s.indexOf("[");
                int indexOfLastBracket = s.lastIndexOf("]");
                str = s.substring(indexOfOpenBracket + 1, indexOfLastBracket);
            } else if (s.contains("[")) {
                str = s.replace("[", "");
            } else if (s.contains("]")) {
                str = s.replace("]", "");
            }
            try {
                connection = ConnectToData();
                String insertQuery = "INSERT INTO `white`" +
                    "(`id`, `employeeId`, `whitedomain`,`status`) "
                    + "VALUES (Default,?,?,?)";
                statement = connection.prepareStatement(insertQuery);
                statement.setInt(1, whiteModel.getEmployerId());
                statement.setString(2, str);
                statement.setInt(3, whiteModel.getStatus());
                rowsAffected = statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();

            } finally {
                closeConnections(connection, statement, null);
            }
        }

        return rowsAffected;

    }

    private void closeConnections(Connection connection, PreparedStatement statment, ResultSet set) throws SQLException {
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

    public List<WhiteModel> findAllById(int employeeId) throws SQLException {
        List<WhiteModel> whiteModelList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `white` WHERE `employeeId`=?";
            statment = connection.prepareStatement(sql);
            statment.setInt(1, employeeId);
            set = statment.executeQuery();
            ListOfWhite(set, whiteModelList);

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        } finally {
            closeConnections(connection, statment, set);
        }
        return whiteModelList;
    }

    public List<WhiteModel> findAllByIdStatus(int employeeId) throws SQLException {
        List<WhiteModel> whiteModelList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `white` WHERE `status`=1 and `employeeId`=?";
            statment = connection.prepareStatement(sql);
            statment.setInt(1, employeeId);
            set = statment.executeQuery();
            ListOfWhite(set, whiteModelList);

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        } finally {
            closeConnections(connection, statment, set);
        }
        return whiteModelList;
    }

    private void ListOfWhite(ResultSet set, List<WhiteModel> whiteModelList) throws SQLException {
        WhiteModel whiteModel = null;
        while (set.next()) {
            whiteModel = new WhiteModel.WhiteBuilder()
                .id(set.getInt("id"))
                .employerId(set.getInt("employeeId"))
                .whiteDomain(set.getString("whitedomain"))
                .status(set.getInt("status"))
                .build();
            whiteModelList.addAll(Collections.singleton(whiteModel));
        }
    }

    public int delete(int userId) throws SQLException {
        int rowsDeleted = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = ConnectToData();

            String sql = "DELETE FROM `white` WHERE  employeeId=" + userId;
            statment = connection.prepareStatement(sql);

            rowsDeleted = statment.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A White label was deleted successfully!");
            }

        } catch (SQLException exception) {
            System.out.println("sqlException in Application in CATEGORY DELETE  Section  : " + exception);
            exception.printStackTrace();
        } finally {
            closeConnections(connection, statment, null);
        }
        return rowsDeleted;
    }

    public List<WhiteModel> findWhiteIdDomain(int id) throws SQLException {
        List<WhiteModel> whiteModelList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        WhiteModel whiteModel = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT `id`,`whitedomain`  FROM `white` WHERE `employeeId`=?";
            statment = connection.prepareStatement(sql);
            statment.setInt(1, id);
            set = statment.executeQuery();
            while (set.next()) {
                whiteModel = new WhiteModel.WhiteBuilder()
                    .id(set.getInt("id"))
                    .whiteDomain(set.getString("whitedomain"))
                    .build();
                whiteModelList.addAll(Collections.singleton(whiteModel));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        } finally {
            closeConnections(connection, statment, set);
        }
        return whiteModelList;
    }

    public List<WhiteModel> findWhiteIdDomainZero(int id) throws SQLException {
        List<WhiteModel> whiteModelList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        WhiteModel whiteModel = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT `id`,`whitedomain`  FROM `white` WHERE `status`= 1 AND `employeeId`=?";
            statment = connection.prepareStatement(sql);
            statment.setInt(1, id);
            set = statment.executeQuery();
            while (set.next()) {
                whiteModel = new WhiteModel.WhiteBuilder()
                    .id(set.getInt("id"))
                    .whiteDomain(set.getString("whitedomain"))
                    .build();
                whiteModelList.addAll(Collections.singleton(whiteModel));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        } finally {
            closeConnections(connection, statment, set);
        }
        return whiteModelList;
    }


    public int update(WhiteModel whiteModel) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `white`  " +
                "SET `status`=? WHERE `id`=" + whiteModel.getId();
            statment = connection.prepareStatement(sql);
            updatestatusDelete(whiteModel, statment);
            rowsUpdated = statment.executeUpdate();
            if (rowsUpdated > 0) {

            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            closeConnections(connection, statment, null);
        }
        return rowsUpdated;
    }

    private void updatestatusDelete(WhiteModel whiteModel, PreparedStatement statment) throws SQLException {
        statment.setInt(1, whiteModel.getStatus());
    }

    public List<WhiteModel> findWhiteIdDomainByID(int id) throws SQLException {
        List<WhiteModel> whiteModelList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        WhiteModel whiteModel = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT `id`,`whitedomain`  FROM `white` WHERE `id`=?";
            statment = connection.prepareStatement(sql);
            statment.setInt(1, id);
            set = statment.executeQuery();
            while (set.next()) {
                whiteModel = new WhiteModel.WhiteBuilder()
                    .id(set.getInt("id"))
                    .whiteDomain(set.getString("whitedomain"))
                    .build();
                whiteModelList.addAll(Collections.singleton(whiteModel));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        } finally {
            closeConnections(connection, statment, set);
        }
        return whiteModelList;
    }

    public List<WhiteModel> findAllByStatus(int employeeId) throws SQLException {
        List<WhiteModel> whiteModelList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `white` WHERE `status`= 1 AND `employeeId`=?";
            statment = connection.prepareStatement(sql);
            statment.setInt(1, employeeId);
            set = statment.executeQuery();
            ListOfWhite(set, whiteModelList);

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        } finally {
            closeConnections(connection, statment, set);
        }
        return whiteModelList;
    }

    public List<WhiteModel> findWhiteIdDomainOne(int id) throws SQLException {
        List<WhiteModel> whiteModelList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        WhiteModel whiteModel = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT `id`,`whitedomain`  FROM `white` WHERE `status`= 0 AND `employeeId`= ?";
            statment = connection.prepareStatement(sql);
            statment.setInt(1, id);
            set = statment.executeQuery();
            while (set.next()) {
                whiteModel = new WhiteModel.WhiteBuilder()
                    .id(set.getInt("id"))
                    .whiteDomain(set.getString("whitedomain"))
                    .build();
                whiteModelList.addAll(Collections.singleton(whiteModel));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        } finally {
            closeConnections(connection, statment, set);
        }
        return whiteModelList;
    }
}
