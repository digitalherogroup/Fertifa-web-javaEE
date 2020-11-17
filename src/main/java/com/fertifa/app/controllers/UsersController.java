package com.fertifa.app.controllers;


import Dao.UsersDao;
import com.fertifa.app.models.Users;
import com.fertifa.app.models.UsersModalBuilder;
import com.fertifa.app.services.RoleService;
import com.fertifa.app.services.UsersService;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class UsersController implements UsersService, RoleService
{
    private UsersDao usersDao = new UsersDao();
    private RoleController roleController = new RoleController();

    public UsersController() {}

    public int CheckUserInRoleByRoleId(int userId) throws SQLException { return usersDao.getUserRole(userId); }

    public List<Users> getAllUsersListById(int userId)
            throws SQLException
    {
        return usersDao.showAllUserById(userId);
    }

    public List<Users> getAllUsers() throws SQLException
    {
        return usersDao.getAllUsers();
    }

    public List<Users> getAllCompaniesByRole(String RoleName) throws SQLException
    {
        return usersDao.getAllCompanies(getRoleNameById(RoleName));
    }

    public List<Users> getAllUsers(String employees) throws SQLException
    {
        return usersDao.getAllUsers(getRoleNameById(employees));
    }

    public int DeleteUser(int deleteid) throws SQLException
    {
        return usersDao.DeleteUser(deleteid);
    }

    public int UpdateCompany(Users companyOruser, int companyId) throws SQLException
    {
        return usersDao.UpdateCompany(companyOruser, companyId);
    }

    public int getAllPendingUsers() throws SQLException
    {
        return usersDao.CountPendingUsers();
    }

    public int getUsersLastSevenDate() throws SQLException
    {
        return usersDao.getAllBylastSevenDays();
    }

    public List<Users> getAllUsersByCompanyId(int companyId, String dateFrom, String dateto) throws SQLException
    {
        return usersDao.getUsersByCompanyId(companyId, dateFrom, dateto);
    }

    public List<Users> getAllCompaniesByBranchId(int companyId) throws SQLException
    {
        return usersDao.getAllCompaniesByBranchId(companyId);
    }

    public List<Users> getCompanyById(int companyId) throws SQLException
    {
        return usersDao.getCompanyById(companyId);
    }

    public int UpdateUserDetail(Users users, int userid) throws SQLException
    {
        return usersDao.UpdateUserById(users, userid);
    }

    public int AddnewCompanyInvitation(Users company) throws SQLException
    {
        return usersDao.AddNewCompanyInvitation(company);
    }

    public int UpdatenewCompanyRegistrationByID(Users company, int companyId) throws SQLException
    {
        return usersDao.UpdateNewRegisteredCompany(company, companyId);
    }

    public boolean validateUserImputs(String email, String password) throws SQLException
    {
        return usersDao.validateUsersInput(email, password);
    }

    public int getUserIdByEmail(String userEmail)
    {
        return usersDao.getUsersIdByEmail(userEmail);
    }

    public int CheckUserRoleByUserId(int userId)
    {
        return usersDao.getUserRoleByUserId(userId);
    }

    public boolean CheckEmail(String recoveryEmail) throws SQLException
    {
        return usersDao.checkEmail(recoveryEmail);
    }

    public int getCompanyIdByEmail(String companyEmail)
    {
        return usersDao.getCompanyIdByEmail(companyEmail);
    }

    public int updatePasswordById(Users company, int companyId) throws SQLException
    {
        return usersDao.UpdateCompanyPassword(company, companyId);
    }

    public List<Users> getAllCompaniesEmailsByRole(int stringcompany) throws SQLException
    {
        return usersDao.getAllCompaniesEmailsByRole(stringcompany);
    }

    public List<Users> getAllUsersIdies() throws SQLException
    {
        return usersDao.getAllUsersIdies();
    }

    @Override
    public int ChangeCompanyStatusToActive(String companyId) throws SQLException {
        return usersDao.ChangeCompanyStatusToActive(companyId);
    }

    public int ChangeCompanyStatusToWaitingForPayment(String companyId) throws SQLException
    {
        return usersDao.ChangeCompanyStatusToWaitingFOrPayment(companyId);
    }

    public String getCompanyNameById(String Email)
    {
        return usersDao.getCompanyNameById(Email);
    }

    public List<Users> getAllUsersEmailByCompanyId(int id) throws SQLException
    {
        return usersDao.getAllUsersEmailByCompanyId(id);
    }

    public int UpdateCompanyDetailsById(Users users, int companyId) throws SQLException
    {
        return usersDao.UpdateCompanyDetailsById(users, companyId);
    }

    public int UpdateCompanyDetailsByIdWithimage(Users users, int companyId) throws SQLException {
        return usersDao.UpdateCompanyDetailsByIdWithimage(users, companyId);
    }

    public int UpdateUsersDetailsByIdWithimage(Users users, int companyId) throws SQLException { return usersDao.UpdateUserDetailsByIdWithimage(users, companyId); }

    public int AddnewUsersInvitation(Users users)
            throws SQLException
    {
        return usersDao.AddNewUsersInvitation(users);
    }

    public int getCompanyIdByEmailAndCompany(String sessionUserEmail, int company)
    {
        return usersDao.getCompanyIdByEmailAndCompany(sessionUserEmail, company);
    }

    public List<Users> getUsersByCompanyAndUser(int companyId, int users) throws SQLException
    {
        return usersDao.getUsersByCompanyAndUser(companyId, users);
    }

    public boolean checkIfUserExsistInData(String email, int users) throws SQLException
    {
        return usersDao.checkIfUserExsistInData(email, users);
    }

    public int CountUsersMonths(Date now, Date monthsBefore, int users, int companyId)
    {
        return usersDao.CountUsersMonths(now, monthsBefore, users, companyId);
    }

    public int CountUsers(int users, int companyId)
    {
        return usersDao.CountUsersMonths(users, companyId);
    }

    public int UpdatenewUserRegistrationByID(Users userObject, int userid, int branch) throws SQLException
    {
        return usersDao.UpdatenewUserRegistrationByID(userObject, userid, branch);
    }

    public int UpdateUserMainPage(Users users, int userId) throws SQLException
    {
        return usersDao.UpdateUserMainPage(users, userId);
    }

    public String getRoleByUserId(int id)
    {
        return roleController.getRoleByUserId(id);
    }

    public int getRoleNameById(String RoleNamw) throws SQLException
    {
        return roleController.getRoleNameById(RoleNamw);
    }

    public List<Users> getCountAllUsersByCompanyId(int companyId, String now, String timestampLaster) throws SQLException {
        return usersDao.getCountAllUsersByCompanyId(companyId, now, timestampLaster);
    }

    public int getCountAllUsers(int companyId) throws SQLException {
        return usersDao.getCountAllUsers(companyId);
    }

    public int getCountAllUsersForSix(Timestamp timestamp, String timestampSixLaster, int CompanYiD) throws SQLException
    {
        return usersDao.getCountAllUsersForSix(timestamp, timestampSixLaster, CompanYiD);
    }

    public int ChangeUserDeletionStatus(int userEmailId) throws SQLException {
        return usersDao.ChangeUserDeletionStatus(userEmailId);
    }

    public int getUserCompanyIdByEmail(String email) {
        return usersDao.getUserCompanyIdByEmail(email);
    }

    public String getCompanyName(int companyid) {
        return usersDao.getCompanyName(companyid);
    }

    public int CountUserDeleteMonths(Timestamp now, Timestamp monthsBefore, int users, int companyId)
    {
        return usersDao.CountUserDeleteMonths(now, monthsBefore, users, companyId);
    }

    public int AddNewUsersInvitations(Users users) throws SQLException {
        return usersDao.AddNewUsersInvitations(users);
    }

    public int DeletUserByCompanyId(int deleteByID) throws SQLException
    {
        return usersDao.DeletUserByCompanyId(deleteByID);
    }

    public List<Users> getLastFiveDays() throws SQLException {
        return usersDao.getLastFiveDays();
    }

    public List<Users> getLastFiveDaysCompany(Timestamp today, Timestamp lastFiveDays) {
        return usersDao.getLastFiveDaysCompany(today, lastFiveDays);
    }

    public int getCompanyIdByName(String companyName) {
        return usersDao.getCompanyIdByName(companyName);
    }

    public String sessionEmail(int parseInt) throws SQLException {
        return usersDao.sessionEmail(parseInt);
    }

    public int getUSerRoleById(int userId) {
        return usersDao.getUSerRoleById(userId);
    }

    public int getTotalCoastByUsersCount(int companyId, int status, int coast) {
        return usersDao.getTotalCoastByUsersCount(companyId, status, coast);
    }

    public int getStatusByEmail(String companyEmail) throws SQLException {
        return usersDao.getStatusByEmail(companyEmail);
    }

    public boolean userIsExists(String email) throws SQLException
    {
        return usersDao.checkEmailIsExists(email);
    }

    public String getUserFirstNameById(String userId) {
        return usersDao.getUserFirstNameById(userId);
    }

    public String getUserLastNameById(String userId) {
        return usersDao.getUserLastNameById(userId);
    }

    public int getlastUserId() {
        return usersDao.getlastUserId();
    }

    public Users findById(String element, String theElemenet)
    {
        return usersDao.userObject(element, theElemenet);
    }

    public Users userObjectCompanyID(int userid)
    {
        return usersDao.userObjectCompanyID(userid);
    }

    public UsersModalBuilder findEmployeeById(String element, String theElemenet) {
        return usersDao.findEmployeeById(element, theElemenet);
    }

    public int AddnewUsersInvitationAffiliate(Users users) throws SQLException {
        return usersDao.AddUserAffiliate(users);
    }

    public int getUserAffiliateId(int id) {
        return usersDao.getUserAffiliateId(id);
    }

    public int updateEmployeeCompanyName(String companyName, int companyId) throws SQLException {
        return usersDao.UpdateUsersByCompanyIdForCompanyName(companyName, companyId);
    }

    public String getCompanyNameByAffiliateId(String id) {
        return usersDao.getCompanyNameByAffiliateId(id);
    }

    public int ChangeUserStatusAdminDelete(int deleteid) throws SQLException {
        return usersDao.ChangeUserStatusAdminDelete(deleteid);
    }

    public int UpdateCompanyModel(UsersModalBuilder users, int companyId) throws SQLException
    {
        return usersDao.UpdateCompanyModel(users, companyId);
    }

    public boolean validateUserStatus(String email, String password) throws SQLException {
        return usersDao.validateUserStatus(email, password);
    }

    public int updateRecived(String[] tokens) throws SQLException {
        return usersDao.updateRecived(tokens);
    }

    public int updateReviedValuesInToken(String session_token) throws SQLException {
        return usersDao.updateReviedValuesInToken(session_token);
    }

    public boolean checkUserStatusForPayment(String userEmail, String userPassword) throws SQLException {
        return usersDao.checkUserStatusForPayment(userEmail, userPassword);
    }

    public List<Users> getUsersPagination(int pageId, int total) throws SQLException {
        return usersDao.getUsersPagination(pageId,total);
    }

    public int countUsersByBranch(int branchId) throws SQLException {
        return usersDao.countUsersByBranch(branchId);
    }
}