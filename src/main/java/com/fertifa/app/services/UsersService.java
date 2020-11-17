package com.fertifa.app.services;

import com.fertifa.app.models.Users;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface UsersService {
    int CheckUserInRoleByRoleId(int userId) throws SQLException;
    List<Users> getAllUsersListById(int userId) throws SQLException;
    List<Users> getAllUsers() throws SQLException;
    List<Users> getAllCompaniesByRole(String RoleName) throws SQLException;
    List<Users> getAllUsers(String employees) throws SQLException;
    int DeleteUser(int deleteid) throws SQLException;
    int UpdateCompany(Users createObjectOfCompany, int companyId) throws SQLException;
    int getAllPendingUsers() throws SQLException;
    int getUsersLastSevenDate() throws SQLException;
    List<Users> getAllUsersByCompanyId(int companyId, String dateFrom, String dateto) throws SQLException;
    List<Users> getAllCompaniesByBranchId(int companyId) throws SQLException;
    List<Users> getCompanyById(int companyId) throws SQLException;
    int UpdateUserDetail(Users createObjectOfUsers, int userid) throws SQLException;
    int AddnewCompanyInvitation(Users createCompanyCategory) throws SQLException;
    int UpdatenewCompanyRegistrationByID(Users createObjectCompany, int parseInt) throws SQLException;
    boolean validateUserImputs(String email, String password) throws SQLException;
    int getUserIdByEmail(String userEmail);
    int CheckUserRoleByUserId(int userId);
    boolean CheckEmail(String recoveryEmail) throws SQLException;
    int getCompanyIdByEmail(String companyEmail);
    int updatePasswordById(Users createCompanyObject, int companyId) throws SQLException;
    List<Users> getAllCompaniesEmailsByRole(int stringcompany) throws SQLException;
    List<Users> getAllUsersIdies() throws SQLException;
    int ChangeCompanyStatusToActive(String companyId) throws SQLException;
    String getCompanyNameById(String id);
    List<Users> getAllUsersEmailByCompanyId(int id) throws SQLException;
    int UpdateCompanyDetailsById(Users users, int companyId) throws SQLException;

    int AddnewUsersInvitation(Users users) throws SQLException;

    int getCompanyIdByEmailAndCompany(String sessionUserEmail, int company);

    List<Users> getUsersByCompanyAndUser(int companyId, int users) throws SQLException;

    boolean checkIfUserExsistInData(String email, int users) throws SQLException;

    int CountUsersMonths(Date now, Date monthsBefore, int users, int companyId);

    int CountUsers(int users, int companyId);

    int UpdatenewUserRegistrationByID(Users createObjectCompany, int parseInt, int branch) throws SQLException;

    int UpdateUserMainPage(Users users, int userId) throws SQLException;



}
