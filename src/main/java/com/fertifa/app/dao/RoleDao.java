package com.fertifa.app.dao;

import java.sql.*;

import com.fertifa.app.Connection.DBConnection;
public class RoleDao {

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
     * get Role branch name by id
     * @param roleId
     * @return
     */
    public String getRoleById(int roleId) {
        String Role = null;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `branches` WHERE `id`=?";
            statment = connection.prepareStatement(sql);
            statment.setInt(1, roleId);
            set = statment.executeQuery();
            while (set.next()) {
                Role = set.getString("branch");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return Role;
    }

    /**
     * Get Role id by String name
     * @param roleName
     * @return
     * @throws SQLException
     */
    public int getRoleByName(String roleName) throws SQLException {
        int Role =0;
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;
        try{
            connection = ConnectToData();
            String sql = "SELECT * FROM `branches` WHERE `branch` LIKE '%" + roleName + "%'";
            statement = connection.createStatement();
            set = statement.executeQuery(sql);
            while (set.next()) {
                Role = set.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(connection != null){
                connection.close();
            }
            if(statement != null){
                statement.close();
            }
            if(set != null){
                set.close();
            }
        }
        return Role;
    }
}
