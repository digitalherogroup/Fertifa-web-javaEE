package com.fertifa.app.dao;

import com.fertifa.app.parser.EmployeeModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.fertifa.app.Connection.DBConnection;

public class TempEmployeDao {

    private Connection ConnectToData() throws SQLException {
        return DBConnection.getConnectionToDatabase();
    }

    public int saveAll(EmployeeModel employeeModel) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `tempemplo`" +
                    "(`id`, `email`, `lastname`, `firsname`,`status`,`branchId`,`createddate`,`companyid`) "
                    + "VALUES (Default,?,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatmentFoUpdateEmployeTemp(employeeModel, statment);
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

    private void setStatmentFoUpdateEmployeTemp(EmployeeModel employeeModel, PreparedStatement statment) throws SQLException {
        statment.setString(1,employeeModel.getFirsName());
        statment.setString(2,employeeModel.getLastName());
        statment.setString(3,employeeModel.getEmail());
        statment.setInt(4,employeeModel.getStatus());
        statment.setInt(5,employeeModel.getBranchId());
        statment.setTimestamp(6,employeeModel.getCreated_date());
        statment.setLong(7,employeeModel.getCompany_id());
    }

    public List<EmployeeModel> tempEmpoListByComapnyId(int id) throws SQLException {
        List<EmployeeModel> employeeModelsList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `tempemplo` WHERE `companyid`=" + id;
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            TempEmpoStatment(set, employeeModelsList);

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
        return employeeModelsList;
    }

    private void TempEmpoStatment(ResultSet set, List<EmployeeModel> employeeModelsList) throws SQLException {
        EmployeeModel employeeModel = null;
        while (set.next()) {
            employeeModel = new EmployeeModel();
            employeeModel.setId(set.getInt("id"));
            employeeModel.setFirsName(set.getString("firsname"));
            employeeModel.setLastName(set.getString("lastname"));
            employeeModel.setEmail(set.getString("email"));
            employeeModel.setStatus(set.getInt("status"));
            employeeModel.setBranchId(set.getInt("branchId"));
            employeeModel.setCreated_date(set.getTimestamp("createddate"));
            employeeModel.setCompany_id(set.getInt("companyid"));
            employeeModelsList.add(employeeModel);
        }

    }

    public List<EmployeeModel> getUserById(int userInvitingId) throws SQLException {
        List<EmployeeModel> employeeModelList =new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;
        EmployeeModel employeeModel =null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `tempemplo` WHERE `id`=" + userInvitingId;
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            while (set.next()) {
                employeeModel = new EmployeeModel();
                employeeModel.setId(set.getInt("id"));
                employeeModel.setFirsName(set.getString("firsname"));
                employeeModel.setLastName(set.getString("lastname"));
                employeeModel.setEmail(set.getString("email"));
                employeeModel.setStatus(set.getInt("status"));
                employeeModel.setBranchId(set.getInt("branchId"));
                employeeModel.setCreated_date(set.getTimestamp("createddate"));
                employeeModel.setCompany_id(set.getInt("companyid"));
                employeeModelList.add(employeeModel);
            }

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
        return employeeModelList;
    }


    public int deleteById(int userInvitingId) throws SQLException {
        int rowsDeleted = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = ConnectToData();

            String sql = "DELETE FROM `tempemplo` WHERE  id=" + userInvitingId;
            statment = connection.prepareStatement(sql);
            rowsDeleted = statment.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A FeedBack was deleted successfully!");
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

    public List<EmployeeModel> GetAll() throws SQLException {
        List<EmployeeModel> employeeModelList =new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;
        EmployeeModel employeeModel =null;

        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `tempemplo`";
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            while (set.next()) {
                employeeModel = new EmployeeModel();
                employeeModel.setId(set.getInt("id"));
                employeeModel.setFirsName(set.getString("firsname"));
                employeeModel.setLastName(set.getString("lastname"));
                employeeModel.setEmail(set.getString("email"));
                employeeModel.setStatus(set.getInt("status"));
                employeeModel.setBranchId(set.getInt("branchId"));
                employeeModel.setCreated_date(set.getTimestamp("createddate"));
                employeeModel.setCompany_id(set.getInt("companyid"));
                employeeModelList.add(employeeModel);
            }

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
        return employeeModelList;
    }

    public int DeleteByCompanyId(int id) throws SQLException {
        int rowsDeleted = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = ConnectToData();

            String sql = "DELETE FROM `tempemplo` WHERE  `companyid`=" + id;
            statment = connection.prepareStatement(sql);
            rowsDeleted = statment.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A FeedBack was deleted successfully!");
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

    public boolean CheckIfCompanyId(int id) throws SQLException {
        boolean isValidAdminUser = false;
        PreparedStatement statment = null;
        ResultSet set = null;
        Connection connection =null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM  `tempemplo` WHERE `companyid`=?";
            statment = connection.prepareStatement(sql);
            statment.setInt(1, id);
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
}
