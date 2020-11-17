package com.fertifa.app.dao;

import com.fertifa.app.Connection.DBConnection;
import com.fertifa.app.models.Admins;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {

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
     * Method will check if com.fertifa.app.Admin exist or not
     *
     * @param email
     * @param password
     * @return
     */
    public boolean validateAdminUsers(String email, String password) throws SQLException {
        return validatePass(email, password);
    }

    /**
     * Check admin inputs
     *
     * @param email
     * @param password
     * @return
     */
    private boolean validatePass(String email, String password) throws SQLException {
        boolean isValidAdminUser = false;
        PreparedStatement statment = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM  `admins` WHERE `email`=? AND `password`=?";
            statment = connection.prepareStatement(sql);
            statment.setString(1, email);
            statment.setString(2, password);
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
     * Adding new Nurse
     *
     * @param admins
     * @return
     * @throws SQLException
     */
    public int saveNurse(Admins admins) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `admins`" +
                    "(`id`, `firstname`, `lastname`, `email`,`status`,`address`,`phonenumber`,`password`,`branch_id`,`creation_date`) "
                    + "VALUES (Default,?,?,?,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            setStatmentFoAdingNurse(admins, statment);
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

    /**
     * Statment to add new Nurse
     *
     * @param admins
     * @param statement
     * @throws SQLException
     */

    private void setStatmentFoAdingNurse(Admins admins, PreparedStatement statement) throws SQLException {
        statement.setString(1, admins.getFirstName());
        statement.setString(2, admins.getLastName());
        statement.setString(3, admins.getEmail());
        statement.setInt(4, admins.getStatus());
        statement.setString(5, admins.getAddress());
        statement.setString(6, admins.getPhonenumber());
        statement.setString(7, admins.getPassword());
        statement.setInt(8, admins.getBranchid());
        statement.setTimestamp(9, (Timestamp) admins.getCreationDate());
    }

    /**
     * Get com.fertifa.app.Admin id by Email
     *
     * @param email
     * @return
     */
    public int getAdminIdByEmail(String email) {
        int adminId = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `admins` WHERE email=?";
            statment = connection.prepareStatement(sql);
            statment.setString(1, email);
            set = statment.executeQuery();
            while (set.next()) {
                adminId = set.getInt("id");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return adminId;
    }

    /**
     * Get admin role by Id
     *
     * @param adminId
     * @return
     */
    public int getAdminRole(int adminId) {
        int Role = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `admins` WHERE `id`=?";
            statment = connection.prepareStatement(sql);
            statment.setInt(1, adminId);
            set = statment.executeQuery();
            while (set.next()) {
                Role = set.getInt("branch_id");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return Role;
    }

    /**
     * All com.fertifa.app.Admin by id
     *
     * @param adminId
     * @return
     */
    public List<Admins> showAllAdminById(int adminId) {
        List<Admins> adminList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `admins` WHERE `id`=?";
            statment = connection.prepareStatement(sql);
            statment.setInt(1, adminId);
            set = statment.executeQuery();
            ListOfAdmins(set, adminList);

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return adminList;
    }


    /**
     * get users as USERS
     *
     * @param RoleId
     * @return
     * @throws SQLException
     */
    public List<Admins> getUsersByRoleUser(int RoleId) throws SQLException {
        List<Admins> adminsList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `admins` WHERE `branch_id`=?";
            statment = connection.prepareStatement(sql);
            statment.setInt(1, RoleId);
            set = statment.executeQuery();
            ListOfAdmins(set, adminsList);
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
        return adminsList;
    }

    /**
     * List of the admin details without password
     *
     * @param set
     * @param adminList
     * @throws SQLException
     */
    private void ListOfAdmins(ResultSet set, List<Admins> adminList) throws SQLException {
        Admins admins = null;
        while (set.next()) {
            admins = new Admins();
            admins.setId(set.getInt("id"));
            admins.setEmail(set.getString("email"));
            admins.setPassword(set.getString("password"));
            admins.setFirstName(set.getString("firstname"));
            admins.setLastName(set.getString("lastname"));
            admins.setStatus(set.getInt("status"));
            admins.setAddress(set.getString("address"));
            admins.setPhonenumber(set.getString("phonenumber"));
            admins.setCreationDate(set.getTimestamp("creation_date"));
            admins.setRole("ADMIN");
            adminList.add(admins);

        }
    }

    /**
     * Adding new COMPANY EMPLOYEE to com.fertifa.app.Admin
     *
     * @param admins
     * @return
     * @throws SQLException
     */
    public int addNewCompany(Admins admins) throws SQLException {
        int isInserted = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `admins`(`id`,`email`,`branch_id`,`status`) values(Default,?,?,?)";
            statement = connection.prepareStatement(insertQuery);
            setStatment(admins, statement);

            isInserted = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return isInserted;
    }

    private void setStatment(Admins admins, PreparedStatement statement) throws SQLException {
        statement.setString(1, admins.getEmail());
        statement.setInt(2, 2);
        statement.setInt(3, 0);
    }


    /**
     * Check if the Nurse email exists
     *
     * @param email
     * @return
     * @throws SQLException
     */
    public boolean checkEmail(String email) throws SQLException {
        boolean isEmail = false;
        PreparedStatement statment = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM  `admins` WHERE `email`=?";
            statment = connection.prepareStatement(sql);
            statment.setString(1, email);
            set = statment.executeQuery();
            while (set.next()) {
                isEmail = true;
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
        return isEmail;

    }

    /**
     * Get all Nurses
     *
     * @return
     * @throws SQLException
     */
    public List<Admins> getAllNerses() throws SQLException {
        List<Admins> NursesList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `admins` WHERE `branch_id`=?";
            statment = connection.prepareStatement(sql);
            statment.setInt(1, 4);
            set = statment.executeQuery();
            ListOfAdmins(set, NursesList);
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
        return NursesList;
    }

    /**
     * Delete com.fertifa.app.Admin
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public int DeleteById(int id) throws SQLException {
        int rowsDeleted = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = ConnectToData();

            String sql = "DELETE FROM `admins` WHERE  id=" + id;
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
            if (connection != null) {
                connection.close();
            }
        }
        return rowsDeleted;
    }

    /**
     * Updaing Nurse in com.fertifa.app.Admin
     *
     * @param admins
     * @param id
     * @return
     * @throws SQLException
     */
    public int UpdateById(Admins admins, int id) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `admins`  " +
                    "SET `password`=?,`address`=?,`phonenumber`=?,`firstname`=?,`lastname`=?,`status`=? WHERE id=" + id;
            statment = connection.prepareStatement(sql);
            UpdateAdmin(admins, statment);

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

    /**
     * Statment for uodaing nurse
     *
     * @param admins
     * @param statment
     * @throws SQLException
     */
    private void UpdateAdmin(Admins admins, PreparedStatement statment) throws SQLException {
        statment.setString(1, admins.getPassword());
        statment.setString(2, admins.getAddress());
        statment.setString(3, admins.getPhonenumber());
        statment.setString(4, admins.getFirstName());
        statment.setString(5, admins.getLastName());
        statment.setInt(6, admins.getStatus());

    }

    public boolean isAdminExsist(String userEmail) throws SQLException {
        boolean isValidAdminUser = false;
        PreparedStatement statment = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM  `admins` WHERE `email`=?";
            statment = connection.prepareStatement(sql);
            statment.setString(1, userEmail);
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

    public Admins getAdminObject(String element, String theElemenet) {
        Admins admins = null;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `admins` WHERE `" + element + "`=?";
            statment = connection.prepareStatement(sql);
            statment.setString(1, theElemenet);
            set = statment.executeQuery();
            if (set.next()) {
                admins = AdminsObject(set);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return admins;
    }

    private Admins AdminsObject(ResultSet set) throws SQLException {
        return new Admins(set.getInt("id"),
                set.getString("firstname"),
                set.getString("lastname"),
                set.getString("email"),
                set.getInt("status"),
                set.getString("phonenumber"),
                set.getTimestamp("creation_date"),
                set.getString("role"),
                set.getInt("branch_id"));

    }

    public Admins getAdminObjectById(Long id) throws SQLException {
        Admins admins = null;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `admins` WHERE id="+id;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            if (set.next()) {
                admins = AdminsObject(set);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }finally {
            if (statment != null) {
                statment.close();
            }
            if (set != null) {
                set.close();
            }if(connection != null){
                connection.close();
            }
        }
        return admins;
    }

    public List<Admins> getAllAdmins() throws SQLException {
        List<Admins> adminsList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `admins`";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfAdmins(set, adminsList);
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
        return adminsList;
    }
}

