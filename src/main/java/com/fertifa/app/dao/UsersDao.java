package Dao;


import com.fertifa.app.Connection.DBConnection;
import com.fertifa.app.constants.Constances;
import com.fertifa.app.models.Users;
import com.fertifa.app.models.UsersModalBuilder;
import com.mysql.cj.jdbc.result.ResultSetImpl;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsersDao {

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
     * Get admin role by Id
     *
     * @param userId
     * @return
     */
    public int getUserRole(int userId) throws SQLException {
        int Role = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `users` WHERE `id`=?";
            statment = connection.prepareStatement(sql);
            statment.setInt(1, userId);
            set = statment.executeQuery();
            while (set.next()) {
                Role = set.getInt("branch_id");
            }

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
        return Role;
    }

    /**
     * List All Users
     *
     * @return
     */
    public List<Users> getAllUsers() throws SQLException {
        List<Users> usersList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `users`";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfUsers(set, usersList);

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
        return usersList;
    }


    /**
     * Gett all Companies in users
     *
     * @return
     */
    public List<Users> getAllCompaniesByBranchId(int id) throws SQLException {
        List<Users> usersList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `users` WHERE `branch_id`=" + id;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfUsers(set, usersList);

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
        return usersList;
    }


    /**
     * All Admin by id
     *
     * @param userId
     * @return
     */
    public List<Users> showAllUserById(int userId) throws SQLException {
        List<Users> usersList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `users` WHERE `id`=?";
            statment = connection.prepareStatement(sql);
            statment.setInt(1, userId);
            set = statment.executeQuery();
            ListOfUsers(set, usersList);

        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Exception exception message : " + exception);
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
        return usersList;
    }


    public List<Users> getAllUsers(int roleNameById) throws SQLException {
        List<Users> usersList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `users` where `branch_id`=" + roleNameById;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfUsers(set, usersList);

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
        return usersList;
    }

    public List<Users> getCompanyById(int companyId) throws SQLException {
        List<Users> usersList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `users` where `id`=" + companyId;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfUsers(set, usersList);

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
        return usersList;
    }

    public List<Users> getAllCompanies(int roleNameById) throws SQLException {
        List<Users> usersList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `users` where `branch_id`=" + roleNameById;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfUsers(set, usersList);

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
        return usersList;
    }

    /**
     * @param companyId
     * @param dateFrom
     * @param dateto
     * @return
     * @throws SQLException
     */
    public List<Users> getUsersByCompanyId(int companyId, String dateFrom, String dateto) throws SQLException {
        String sql = "";
        List<Users> usersList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();

            sql = "SELECT * from users where status <> 9000";

            if (companyId > 0) {
                sql += " and `companyid`=" + companyId;

            }

            if (!dateFrom.isEmpty()) {
                sql += " and `creation_date` between" + "\"" + dateFrom + "\"";
            }

            if (!dateto.isEmpty()) {
                sql += " and " + "\"" + dateto + "\"";
            }
            System.out.println("sql" + sql);
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfUsers(set, usersList);

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
        return usersList;
    }


    /**
     * Deleteing users and Companies
     *
     * @param deleteid
     * @return
     * @throws SQLException
     */
    public int DeleteUser(int deleteid) throws SQLException {
        int rowsDeleted = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = ConnectToData();
            String sql = "DELETE FROM `users` WHERE  id=" + deleteid;
            statment = connection.prepareStatement(sql);

            rowsDeleted = statment.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A user was deleted successfully!");
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
     * Updating Company
     *
     * @param companyOruser
     * @param companyId
     * @return
     * @throws SQLException
     */
    public int UpdateCompany(Users companyOruser, int companyId) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `users`  " +
                    "SET `address`=?,`phonenumber`=?,`status`=?,`updated_date`=?,`domain`=?,`packageid`=?,`company`=?,`package`=? WHERE id=" + companyId;
            statment = connection.prepareStatement(sql);
            UpdateCompanyList(companyOruser, statment);

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

    public int UpdateUserById(Users users, int userid) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `users`  " +
                    "SET `firstname`=?,`lastname`=?,`address`=?,`phonenumber`=?,`updated_date`=?,`gender`=?,`status`=?,`company`=?,`age`=?,`companyid`=? WHERE id=" + userid;
            statment = connection.prepareStatement(sql);
            UpdateUserList(users, statment);

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

    public int UpdateUserMainPage(Users users, int userid) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `users`  " +
                    "SET `firstname`=?,`lastname`=?,`address`=?,`phonenumber`=?,`updated_date`=? WHERE id=" + userid;
            statment = connection.prepareStatement(sql);
            UpdateUserMainPageList(users, statment);

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

    private void UpdateUserMainPageList(Users users, PreparedStatement statment) throws SQLException {
        statment.setString(1, users.getFirstName());
        statment.setString(2, users.getLastName());
        statment.setString(3, users.getAddress());
        statment.setString(4, users.getPhoneNumber());
        statment.setTimestamp(5, new Timestamp(new Date().getTime()));


    }


    /**
     * firstname`=?,`lastname`=?,`address`=?,`phonenumber`=?,`updated_date`=?,`gender`=?,`status`=?,`company`=?,`age`=?
     *
     * @param users
     * @param statment
     * @throws SQLException
     */
    private void UpdateUserList(Users users, PreparedStatement statment) throws SQLException {
        statment.setString(1, users.getFirstName());
        statment.setString(2, users.getLastName());
        statment.setString(3, users.getAddress());
        statment.setString(4, users.getPhoneNumber());
        statment.setTimestamp(5, users.getUpdateDate());
        statment.setInt(6, users.getGender());
        statment.setInt(7, users.getStatus());
        statment.setString(8, users.getComapny());
        statment.setInt(9, users.getAge());
        statment.setInt(10, users.getCompanyId());
    }


    /**
     * Counting pending users
     *
     * @return
     * @throws SQLException
     */
    public int CountPendingUsers() throws SQLException {
        int countUsers = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT count(*) FROM `users` where `status`=" + 0;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                countUsers = set.getInt(1);
            }

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
        return countUsers;
    }

    /**
     * getting users companies last 7 days
     *
     * @return
     * @throws SQLException
     */
    public int getAllBylastSevenDays() throws SQLException {
        int counts = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT  COUNT(*), DATE_FORMAT(last_login_date, '%Y-%m-%d') tDate  FROM `users`  WHERE creation_date >= DATE(NOW()) - INTERVAL 7 DAY AND  creation_date <= DATE(NOW()) GROUP BY tDate ;";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                counts = set.getInt(1);
            }

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
        return counts;
    }

    /**
     * Updaing Company fertify section
     * address`=?,`phonenumber`=?,`branch_id`=?,`updated_date`=?,`domain`=?,`packageid`=?`company`=?
     *
     * @param companyOruser
     * @param statment
     */
    private void UpdateCompanyList(Users companyOruser, PreparedStatement statment) throws SQLException {
        statment.setString(1, companyOruser.getAddress());
        statment.setString(2, companyOruser.getPhoneNumber());
        statment.setInt(3, companyOruser.getStatus());
        statment.setTimestamp(4, companyOruser.getUpdateDate());
        statment.setString(5, companyOruser.getDomain());
        statment.setInt(6, companyOruser.getPackagId());
        statment.setString(7, companyOruser.getComapny());
        statment.setString(8, companyOruser.getPackagName());
    }

    /**
     * List of the admin details without password
     *
     * @param set
     * @param usersList
     * @throws SQLException
     */
    private void ListOfUsers(ResultSet set, List<Users> usersList) throws SQLException {
        Users users = null;
        while (set.next()) {
            users = new Users();
            users.setId(set.getInt("id"));
            users.setEmail(set.getString("email"));
            users.setPassword(set.getString("password"));
            users.setAddress(set.getString("address"));
            users.setBranchId(set.getInt("branch_id"));
            users.setCreationDate(set.getTimestamp("creation_date"));
            users.setUpdateDate(set.getTimestamp("updated_date"));
            users.setLastLoginDate(set.getTimestamp("last_login_date"));
            users.setDomain(set.getString("domain"));
            users.setPackagName(set.getString("package"));
            users.setDiscription(set.getString("discription"));
            users.setGender(set.getInt("gender"));
            users.setStatus(set.getInt("status"));
            users.setUserImage(set.getString("imagelink"));
            users.setComapny(set.getString("company"));
            users.setFirstName(set.getString("firstname"));
            users.setLastName(set.getString("lastname"));
            users.setPackagId(set.getInt("packageId"));
            users.setCompanyId(set.getInt("companyid"));
            users.setComapny(set.getString("company"));
            users.setAge(set.getInt("age"));
            users.setPhoneNumber(set.getString("phonenumber"));
            users.setUserImage(set.getString("imagelink"));
            users.setAffiliateId(set.getInt("affiliateid"));
            usersList.add(users);

        }
    }

    /**
     * Adding company after invitation
     *
     * @param users
     * @return
     * @throws SQLException
     */

    public int AddNewCompanyInvitation(Users users) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `users`" +
                    "(`id`, `email`,`company`,`branch_id`,`status`,`creation_date`,`companyid`,`package`,`packageId`,`affiliateid`)"
                    + "VALUES (Default,?,?,?,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            AddNewCompanyInvitationList(users, statment);
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

    public int AddNewUsersInvitation(Users users) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `users`" +
                    "(`id`, `email`, `firstname`, `lastname`,`branch_id`,`status`,`creation_date`,`companyid`,`company`)"
                    + "VALUES (Default,?,?,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            AddUSerToEmail(users, statment);
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

    public int AddUserAffiliate(Users users) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `users`" +
                    "(`id`, `email`, `firstname`, `lastname`,`branch_id`,`status`,`creation_date`,`companyid`,`password`,`company`,`gender`,`age`)"
                    + "VALUES (Default,?,?,?,?,?,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            AddUSerToEmailAff(users, statment);
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

    private void AddUSerToEmailAff(Users users, PreparedStatement statment) throws SQLException {
        statment.setString(1, users.getEmail());
        statment.setString(2, users.getFirstName());
        statment.setString(3, users.getLastName());
        statment.setInt(4, Constances.USERS);
        statment.setInt(5, Constances.PENDING);
        statment.setTimestamp(6, new Timestamp(new Date().getTime()));
        statment.setInt(7, users.getCompanyId());
        statment.setString(8, users.getPassword());
        statment.setString(9, users.getComapny());
        statment.setInt(10, users.getGender());
        statment.setInt(11, users.getAge());


    }


    private void AddUSerToEmail(Users users, PreparedStatement statment) throws SQLException {
        statment.setString(1, users.getEmail());
        statment.setString(2, users.getFirstName());
        statment.setString(3, users.getLastName());
        statment.setInt(4, Constances.USERS);
        statment.setInt(5, Constances.PENDING);
        statment.setTimestamp(6, new Timestamp(new Date().getTime()));
        statment.setInt(7, users.getCompanyId());
        statment.setString(8, users.getComapny());
    }

    public int AddNewUsersInvitations(Users users) throws SQLException {
        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String insertQuery = "INSERT INTO `users`" +
                    "(`id`, `email`, `lastname`, `firstname`,`branch_id`,`status`,`creation_date`,`companyid`)"
                    + "VALUES (Default,?,?,?,?,?,?,?)";
            statment = connection.prepareStatement(insertQuery);
            AddNewUsersInvitationList(users, statment);
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

    private void AddNewUsersInvitationList(Users users, PreparedStatement statment) throws SQLException {

        statment.setString(1, users.getFirstName());
        statment.setString(2, users.getLastName());
        statment.setString(3, users.getEmail());
        statment.setInt(4, Constances.USERS);
        statment.setInt(5, Constances.PENDING);
        statment.setTimestamp(6, new Timestamp(new Date().getTime()));
        statment.setInt(7, users.getCompanyId());
    }

    private void AddNewCompanyInvitationList(Users users, PreparedStatement statment) throws SQLException {
        statment.setString(1, users.getEmail());
        statment.setString(2, users.getComapny());
        statment.setInt(3, Constances.COMPANY);
        statment.setInt(4, Constances.PENDING);
        statment.setTimestamp(5, new Timestamp(new Date().getTime()));
        statment.setInt(6, users.getCompanyId());
        statment.setString(7, users.getPackagName());
        statment.setInt(8, users.getPackagId());
        statment.setInt(9, users.getAffiliateId());
    }


    public int UpdateNewRegisteredCompany(Users company, int companyId) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `users`  " +
                    "SET `password`=?,`address`=?,`domain`=?,`status`=? WHERE `id`=" + companyId;
            statment = connection.prepareStatement(sql);
            UpdateCompanyewRegistered(company, statment);

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

    private void UpdateCompanyewRegistered(Users company, PreparedStatement statment) throws SQLException {
        statment.setString(1, company.getPassword());
        statment.setString(2, company.getAddress());
        statment.setString(3, company.getDomain());
        statment.setInt(4, Constances.ACTIVENOW);
    }

    public int UpdateNewRegisteredUsers(Users company, int companyId) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `users`  " +
                    "SET `password`=?,`firstname`=?,`lastname`=?,`gender`=?,`age`=? WHERE `id`=" + companyId;
            statment = connection.prepareStatement(sql);
            UpdateUsersRegistered(company, statment);

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

    private void UpdateUsersRegistered(Users company, PreparedStatement statment) throws SQLException {
        statment.setString(1, company.getPassword());
        statment.setString(2, company.getFirstName());
        statment.setString(3, company.getLastName());
        statment.setInt(4, company.getGender());
        statment.setInt(5, company.getAge());

    }


    public boolean validateUsersInput(String email, String password) throws SQLException {
        return validatePass(email, password);
    }

    private boolean validatePass(String email, String password) throws SQLException {
        boolean isValidAdminUser = false;
        PreparedStatement statment = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM  `users` WHERE `email`=? AND `password`=? AND `status`= 1";
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

    public int getUsersIdByEmail(String userEmail) {
        int userId = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `users` WHERE `email`=?";
            statment = connection.prepareStatement(sql);
            statment.setString(1, userEmail);
            set = statment.executeQuery();
            while (set.next()) {
                userId = set.getInt("id");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return userId;
    }


    public int getUserRoleByUserId(int userId) {
        int Role = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `users` WHERE `id`=?";
            statment = connection.prepareStatement(sql);
            statment.setInt(1, userId);
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

    public boolean checkEmail(String recoveryEmail) throws SQLException {
        boolean isValidEmail = false;
        PreparedStatement statment = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM  `users` WHERE `email`=?";
            statment = connection.prepareStatement(sql);
            statment.setString(1, recoveryEmail);
            set = statment.executeQuery();
            while (set.next()) {
                isValidEmail = true;
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
        return isValidEmail;
    }

    public int getCompanyIdByEmail(String companyEmail) {
        int companyId = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `users` WHERE `email`=?";
            statment = connection.prepareStatement(sql);
            statment.setString(1, companyEmail);
            set = statment.executeQuery();
            while (set.next()) {
                companyId = set.getInt("id");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return companyId;
    }

    public int UpdateCompanyPassword(Users company, int companyId) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `users`  " +
                    "SET `password`=? WHERE `id`=" + companyId;
            statment = connection.prepareStatement(sql);
            statment.setString(1, company.getPassword());

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

    public List<Users> getAllCompaniesEmailsByRole(int stringcompany) throws SQLException {
        List<Users> usersList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `users` where `branch_id`=" + stringcompany;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            Users users = null;
            while (set.next()) {
                users = new Users();
                users.setEmail(set.getString("email"));
                usersList.add(users);
            }

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
        return usersList;
    }

    public List<Users> getAllUsersIdies() throws SQLException {
        List<Users> usersList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `users`";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            Users users = null;
            while (set.next()) {
                users = new Users();
                users.setId(set.getInt("id"));
                usersList.add(users);
            }

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
        return usersList;
    }

    /**
     * Change status active
     *
     * @param companyId
     * @return
     * @throws SQLException
     */
    public int ChangeCompanyStatusToWaitingFOrPayment(String companyId) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `users`  " +
                    "SET `status`=? WHERE `id`=" + companyId;
            statment = connection.prepareStatement(sql);
            statment.setInt(1, Constances.FEEDBACKSTATUSREQUESTPAYMENT);

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

    public String getCompanyNameById(String email) {
        String companyName = null;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `users` WHERE `email` LIKE '%" + email + "%'";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                companyName = set.getString("company");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return companyName;
    }

    public String getCompanyName(int id) {
        String companyName = null;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `users` WHERE `companyid`=" + id;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                companyName = set.getString("company");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return companyName;
    }

    public List<Users> getAllUsersEmailByCompanyId(int id) throws SQLException {
        List<Users> usersList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT  *  FROM `users` where `companyid`=" + id;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            Users users = null;
            while (set.next()) {
                users = new Users();
                users.setEmail(set.getString("email"));
                users.setCreationDate(set.getTimestamp("creation_date"));
                users.setFirstName(set.getString("firstname"));
                users.setLastName(set.getString("lastname"));
                users.setStatus(set.getInt("status"));
                users.setId(set.getInt("id"));
                users.setCompanyId(set.getInt("companyid"));
                usersList.add(users);
            }

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
        return usersList;
    }

    public String sessionEmail(int id) throws SQLException {

        String companyId = "";
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `users` WHERE `id`=?";
            statment = connection.prepareStatement(sql);
            statment.setInt(1, id);

            set = statment.executeQuery();
            while (set.next()) {
                companyId = set.getString("email");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return companyId;
    }

    public int UpdateCompanyDetailsById(Users company, int companyId) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `users`  " +
                    "SET `email`=?,`phonenumber`=?,`address`=?,`company`=? WHERE `id`=" + companyId;
            statment = connection.prepareStatement(sql);
            statment.setString(1, company.getEmail());
            statment.setString(2, company.getPhoneNumber());
            statment.setString(3, company.getAddress());
            statment.setString(4, company.getComapny());

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

    public int getCompanyIdByEmailAndCompany(String companyEmail, int company) {
        int companyId = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `users` WHERE `email`=? AND `branch_id`=?";
            statment = connection.prepareStatement(sql);
            statment.setString(1, companyEmail);
            statment.setInt(2, company);
            set = statment.executeQuery();
            while (set.next()) {
                companyId = set.getInt("id");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return companyId;
    }

    public List<Users> getUsersByCompanyAndUser(int companyId, int users) throws SQLException {
        List<Users> usersList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT  *  FROM `users` where `branch_id`= " + users + " AND `companyid`= " + companyId + " AND `status`< " + 3 + " ORDER BY `creation_date` DESC";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            Users userss = null;
            while (set.next()) {
                userss = new Users();
                userss.setEmail(set.getString("email"));
                userss.setCreationDate(set.getTimestamp("creation_date"));
                userss.setFirstName(set.getString("firstname"));
                userss.setLastName(set.getString("lastname"));
                userss.setStatus(set.getInt("status"));
                userss.setId(set.getInt("id"));
                userss.setCompanyId(set.getInt("companyid"));
                usersList.add(userss);
            }

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
        return usersList;
    }

    public boolean checkIfUserExsistInData(String email, int users) throws SQLException {
        boolean isValidEmail = false;
        PreparedStatement statment = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM  `users` WHERE `branch_id`=? AND `email`=?";
            statment = connection.prepareStatement(sql);
            statment.setInt(1, users);
            statment.setString(2, email);
            set = statment.executeQuery();
            while (set.next()) {
                isValidEmail = true;
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
        return isValidEmail;
    }

    public int CountUsersMonths(Date now, Date monthsBefore, int branch, int companId) {
        int count = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT COUNT(*) FROM `users` WHERE (`creation_date` > '" + monthsBefore + "') AND `branch_id`=" + branch + " AND `companyid`=" + companId;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                count = set.getInt(1);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return count;
    }

    public int CountUsersMonths(int users, int companyId) {
        int count = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT COUNT(*) FROM `users` WHERE `branch_id`=" + users + " AND `companyid`=" + companyId;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                count = set.getInt(1);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return count;
    }

    public int UpdatenewUserRegistrationByID(Users userObject, int userid, int branch) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `users`  " +
                    "SET `firstname`=?,`lastname`=?,`status`=?,`password`=?,`gender`=?,`age`=? WHERE `branch_id`=" + branch + " AND id=" + userid;
            statment = connection.prepareStatement(sql);
            UpdateUsersList(userObject, statment);

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

    private void UpdateUsersList(Users userObject, PreparedStatement statment) throws SQLException {
        statment.setString(1, userObject.getFirstName());
        statment.setString(2, userObject.getLastName());
        statment.setInt(3, Constances.FEEDBACKSTATUSPASSEDREGISTRATION);
        statment.setString(4, userObject.getPassword());
        statment.setInt(5, userObject.getGender());
        statment.setInt(6, userObject.getAge());

    }

    public List<Users> getCountAllUsersByCompanyId(int companyId, String dateFrom, String dateto) throws SQLException {
        int count = 0;
        String sql = "";
        List<Users> usersList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            //sql = "SELECT COUNT(*) users, DATE_FORMAT(creation_date, '%d.%m') DateString FROM `users` WHERE (`creation_date` BETWEEN'" + dateto + "' AND '" + dateFrom+ "') AND  `companyid`='" + companyId +"' GROUP BY DateString;";
            // sql = "SELECT COUNT(*) id, DATE_FORMAT(creation_date, '%d.%m') DateString FROM `users` WHERE `creation_date` >= '" + dateto + "' AND `creation_date` <= '" + dateFrom + "' AND  `companyid`=" + companyId + " GROUP BY DateString ORDER BY DateString ASC ";
            sql = "SELECT COUNT(*) id, DATE_FORMAT(creation_date, '%d.%m') DateString FROM `users` WHERE DATE_FORMAT(`creation_date`,'%Y-%m-%d') >= '" + dateto + "' AND DATE_FORMAT(`creation_date`,'%Y-%m-%d') <= '" + dateFrom + "' AND  `companyid`=" + companyId + " GROUP BY DateString ORDER BY DateString ASC ";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            Users users = null;
            while (set.next()) {
                users = new Users();
                users.setId(set.getInt("id"));
                users.setDateString(set.getString("DateString"));
                usersList.add(users);
            }

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
        return usersList;
    }

    public int getCountAllUsers(int companyId) throws SQLException {
        int count = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT Count(*) FROM `users` WHERE `companyid`=" + companyId;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                count = set.getInt(1);
            }

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
        return count;

    }

    public int getCountAllUsersForSix(Timestamp timestamp, String timestampSixLaster, int companyId) throws SQLException {
        int count = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT Count(*) FROM `users` WHERE (`creation_date` BETWEEN'" + timestampSixLaster + "' AND '" + timestamp + "') AND `companyid`=" + companyId;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                count = set.getInt(1);
            }

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
        return count;
    }

    public int ChangeUserDeletionStatus(int userEmailId) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `users`  " +
                    "SET `status`=? WHERE `id`=" + userEmailId;
            statment = connection.prepareStatement(sql);
            statment.setInt(1, Constances.FORDELETE);

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

    public int getUserCompanyIdByEmail(String email) {
        int companyId = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `users` WHERE `email`=?";
            statment = connection.prepareStatement(sql);
            statment.setString(1, email);
            set = statment.executeQuery();
            while (set.next()) {
                companyId = set.getInt("companyid");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return companyId;
    }

    public int CountUserDeleteMonths(Timestamp now, Timestamp monthsBefore, int users, int companyId) {
        int count = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT COUNT(*) FROM `users` WHERE `creation_date` >= '" + monthsBefore + "' AND `status`=" + Constances.FORDELETE + " AND `branch_id`=" + users + " AND `companyid`=" + companyId;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                count = set.getInt(1);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return count;
    }

    public int UpdateCompanyDetailsByIdWithimage(Users users, int companyId) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `users`  " +
                    "SET `email`=?,`phonenumber`=?,`address`=?,`imagelink`=?, `company`=? WHERE `id`=" + companyId;
            statment = connection.prepareStatement(sql);
            statment.setString(1, users.getEmail());
            statment.setString(2, users.getPhoneNumber());
            statment.setString(3, users.getAddress());
            statment.setString(4, users.getUserImage());
            statment.setString(5, users.getComapny());

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

    public int UpdateUserDetailsByIdWithimage(Users users, int companyId) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `users`  " +
                    "SET `email`=?,`phonenumber`=?,`address`=?,`imagelink`=?,`firstname`=?,`lastname`=? WHERE `id`=" + companyId;
            statment = connection.prepareStatement(sql);
            statment.setString(1, users.getEmail());
            statment.setString(2, users.getPhoneNumber());
            statment.setString(3, users.getAddress());
            statment.setString(4, users.getUserImage());
            statment.setString(5, users.getFirstName());
            statment.setString(6, users.getLastName());

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

    public int DeletUserByCompanyId(int deleteByID) throws SQLException {
        int rowsDeleted = 0;
        PreparedStatement statment = null;
        Connection connection = null;
        try {
            connection = ConnectToData();
            String sql = "DELETE FROM `users` WHERE  companyid=" + deleteByID;
            statment = connection.prepareStatement(sql);

            rowsDeleted = statment.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A user was deleted successfully!");
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

    public List<Users> getLastFiveDays() throws SQLException {
        List<Users> usersList = new ArrayList<>();
        int count = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT\n" +
                    " COUNT(IF(branch_id = 2, 1, NULL)) employer,\n" +
                    " COUNT(IF(branch_id = 3, 1, NULL)) employee,\n" +
                    " DATE_FORMAT( creation_date, '%d.%m' ) DateString,\n" +
                    " DATE_FORMAT( creation_date, '%d.%m.%y' ) check_date,\n" +
                    " branch_id\n" +
                    "FROM\n" +
                    " users\n" +
                    "WHERE\n" +
                    " DATE(creation_date) > (NOW() - INTERVAL 5 DAY)\n" +
                    "GROUP BY\n" +
                    " check_date";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            Users users = null;
            while (set.next()) {
                users = new Users();
                users.setCount(set.getInt(1));
                users.setBranchId(set.getInt("branch_id"));
                users.setDateString(set.getString("DateString"));
                usersList.add(users);
            }

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
        return usersList;


    }

    public List<Users> getLastFiveDaysCompany(Timestamp today, Timestamp lastFiveDays) {
        List<Users> usersList = null;
        String date = "";
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `users` WHERE (`creation_date` > '" + lastFiveDays + "' AND `creation_date` < '" + today + "' AND `branch_id`=" + Constances.COMPANY;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                Users users = null;
                users = new Users();
                users.setCreationDate(set.getTimestamp("creation_date"));
                users.setId(set.getInt("id"));
                usersList.add(users);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return usersList;
    }

    public int getCompanyIdByName(String companyName) {
        int userId = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT `id`  FROM `users` WHERE `company`=? AND `companyid`=0";
            statment = connection.prepareStatement(sql);
            statment.setString(1, companyName);
            set = statment.executeQuery();
            while (set.next()) {
                userId = set.getInt("id");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return userId;
    }

    public int getUSerRoleById(int userId) {
        int role = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM `users` WHERE `id`=?";
            statment = connection.prepareStatement(sql);
            statment.setInt(1, userId);
            set = statment.executeQuery();
            while (set.next()) {
                role = set.getInt("branch_id");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return role;
    }


    public int getTotalCoastByUsersCount(int companyId, int status, int coast) {
        int totalCoast = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT COUNT(*)*" + coast + " as total_cost FROM users where status=? AND companyid=?";
            statment = connection.prepareStatement(sql);
            statment.setInt(1, status);
            statment.setInt(2, companyId);
            set = statment.executeQuery();
            while (set.next()) {
                totalCoast = set.getInt("total_cost");
                String totalCoastString = set.getString("total_cost");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return totalCoast;

    }

    public int getStatusByEmail(String companyEmail) throws SQLException {
        int isValidEmail = 0;
        PreparedStatement statment = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT `status`  FROM  `users` WHERE `email`=?";
            statment = connection.prepareStatement(sql);
            statment.setString(1, companyEmail);

            set = statment.executeQuery();
            while (set.next()) {
                isValidEmail = set.getInt("status");
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
        return isValidEmail;
    }

    public boolean checkEmailIsExists(String email) throws SQLException {

        boolean emailIsExists = false;
        PreparedStatement statment = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT EXISTS (SELECT * FROM users WHERE email = '" + email + "' )";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            try {
                int result = Integer.parseInt(new String(((ResultSetImpl) set).getRows().get(0).getBytes(0)));
                emailIsExists = result == 1;
            } catch (Exception e) {
                emailIsExists = false;
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

        return emailIsExists;
    }

    public String getUserFirstNameById(String userId) {
        String userFirstName = "";
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT `firstname` FROM users where id=" + userId;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                userFirstName = set.getString("firstname");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return userFirstName;
    }

    public String getUserLastNameById(String userId) {
        String userLastName = "";
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT `lastname` FROM users where id=" + userId;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                userLastName = set.getString("firstname");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return userLastName;
    }

    public int getlastUserId() {
        int userId = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT id FROM users WHERE id=( SELECT max(id) FROM users )";
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                userId = set.getInt("id");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return userId;

    }


    public Users userObject(String element, String theElemenet) {
        Users users = null;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `users` WHERE `" + element + "`=?";
            statment = connection.prepareStatement(sql);
            statment.setString(1, theElemenet);
            set = statment.executeQuery();
            if (set.next()) {
                users = UsersObject(set);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return users;
    }

    public Users userObjectCompanyID(int userID) {
        Users users = null;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `users` WHERE `id`=?";
            statment = connection.prepareStatement(sql);
            statment.setInt(1, userID);
            set = statment.executeQuery();
            if (set.next()) {
                users = UsersObject(set);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return users;
    }

    private Users UsersObject(ResultSet set) throws SQLException {
        return new Users(set.getInt("id"),
                set.getString("email"),
                set.getString("address"),
                set.getString("phonenumber"),
                set.getInt("branch_id"),
                set.getTimestamp("creation_date"),
                set.getString("domain"),
                set.getString("package"),
                set.getString("discription"),
                set.getInt("gender"),
                set.getInt("age"),
                set.getInt("status"),
                set.getString("firstname"),
                set.getString("lastname"),
                set.getString("company"),
                set.getInt("packageId"),
                set.getInt("companyid"),
                set.getString("imagelink"),
                set.getInt("affiliateid"),
                set.getString("role"));

    }

    public UsersModalBuilder findEmployeeById(String element, String theElemenet) {
        UsersModalBuilder t = null;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `users` WHERE `" + element + "`=?";
            statment = connection.prepareStatement(sql);
            statment.setString(1, theElemenet);
            set = statment.executeQuery();
            if (set.next()) {
                t = EmployeeObject(set);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return t;
    }

    private UsersModalBuilder EmployeeObject(@NotNull ResultSet set) throws SQLException {
        return new UsersModalBuilder.UsersBuilder()
                .id(set.getInt("id"))
                .firstName(set.getString("firstname"))
                .build();

          /*  .id(set.getInt("id"))
            .firstName(set.getString("firstname"))
            .lastName(set.getString("lastname")).build();*/
    }

    public int getUserAffiliateId(int id) {
        int affiliateId = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT `affiliateid` FROM `users` where `id`=" + id;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                affiliateId = set.getInt("affiliateid");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return affiliateId;
    }

    public int UpdateUsersByCompanyIdForCompanyName(String companyName, int companyId) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `users`  " +
                    "SET `company`=? WHERE `companyid`=" + companyId;
            statment = connection.prepareStatement(sql);
            statment.setString(1, companyName);

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

    public String getCompanyNameByAffiliateId(String id) {
        String companyName = "";
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT `company` FROM `users` where `affiliateid`=" + id;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                companyName = set.getString("company");
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("SQLException exception message : " + exception);
        }
        return companyName;
    }

    public int ChangeUserStatusAdminDelete(int deleteid) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `users`  " +
                    "SET `status`=? WHERE `id`=" + deleteid;
            statment = connection.prepareStatement(sql);
            statment.setInt(1, Constances.DELETE_COMPANY);

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

    public int UpdateCompanyModel(UsersModalBuilder users, int companyId) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `users`  " +
                    "SET `address`=?,`phonenumber`=?,`status`=?,`updated_date`=?,`domain`=?,`packageid`=?,`company`=?,`package`=?,`affiliateid`=? WHERE id=" + companyId;
            statment = connection.prepareStatement(sql);
            UpdateCompanyListModel(users, statment);

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

    private void UpdateCompanyListModel(UsersModalBuilder companyOruser, PreparedStatement statment) throws SQLException {
        statment.setString(1, companyOruser.getAddress());
        statment.setString(2, companyOruser.getPhoneNumber());
        statment.setInt(3, companyOruser.getStatus());
        statment.setTimestamp(4, companyOruser.getUpdateDate());
        statment.setString(5, companyOruser.getDomain());
        statment.setInt(6, companyOruser.getPackageId());
        statment.setString(7, companyOruser.getCompany());
        statment.setString(8, companyOruser.getPackageName());
        statment.setInt(9, companyOruser.getAffiliateId());
    }

    public boolean validateUserStatus(String email, String password) throws SQLException {
        return validatePassWithStatus(email, password);
    }

    private boolean validatePassWithStatus(String email, String password) throws SQLException {
        boolean isValidAdminUser = false;
        PreparedStatement statment = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM  `users` WHERE `email`=? AND `password`=? AND `status`> 1";
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

    public int updateRecived(String[] tokens) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `messages`  " +
                    "SET `recived`=? WHERE session_token IN(" + String.join(",", tokens) + ")";
            statment = connection.prepareStatement(sql);

            statment.setInt(1, 1);
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

    public int updateReviedValuesInToken(String session_token) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `messages`  " +
                    "SET `recived`=? WHERE session_token='" + session_token + "'";
            statment = connection.prepareStatement(sql);

            statment.setInt(1, 1);
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

    public int ChangeCompanyStatusToActive(String companyId) throws SQLException {
        int rowsUpdated = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        try {
            connection = ConnectToData();
            String sql = "UPDATE `users`  " +
                    "SET `status`=? WHERE `id`=" + companyId;
            statment = connection.prepareStatement(sql);
            statment.setInt(1, Constances.FEEDBACKSTATUSAPPROVED);

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

    public boolean checkUserStatusForPayment(String userEmail, String userPassword) throws SQLException {
        boolean isValidAdminUser = false;
        PreparedStatement statment = null;
        ResultSet set = null;
        Connection connection = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT *  FROM  `users` WHERE `email`=? AND `password`=? AND `status`= 3";
            statment = connection.prepareStatement(sql);
            statment.setString(1, userEmail);
            statment.setString(2, userPassword);
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

    public List<Users> getUsersPagination(int pageId, int total) throws SQLException {
        List<Users> usersList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT * FROM `users` where branch_id=3 LIMIT " + pageId + "," + total;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            ListOfUsers(set, usersList);

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
        return usersList;
    }

    public int countUsersByBranch(int branchId) throws SQLException {
        int count = 0;
        Connection connection = null;
        PreparedStatement statment = null;
        ResultSet set = null;
        try {
            connection = ConnectToData();
            String sql = "SELECT COUNT(*) FROM `users` where branch_id="+branchId;
            statment = connection.prepareStatement(sql);
            set = statment.executeQuery();
            while (set.next()) {
                return set.getInt(1);
            }

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
        return count;
    }
}

