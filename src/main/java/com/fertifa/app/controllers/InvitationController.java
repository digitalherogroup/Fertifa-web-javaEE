package com.fertifa.app.controllers;

import com.fertifa.app.dao.InvitationDao;
import com.fertifa.app.models.Tokens;
import com.fertifa.app.services.InvitationService;

import java.sql.SQLException;


public class InvitationController implements InvitationService {
private InvitationDao invitationDao = new InvitationDao();

    public int AddNewTokenAndCompany(Tokens tokens) throws SQLException {
       return invitationDao.AddNewCompanyInvitation(tokens);
    }

    @Override
    public boolean CheckingInvitation(String tokenId, String company) throws SQLException {
        return invitationDao.ControllIncomeValues(tokenId,company);
    }

    @Override
    public String GetCompanyByTokenId(String tokenId) {
        return invitationDao.getCompanyEmailById(tokenId);
    }

    @Override
    public int GetCompanyIdTokenId(String tokenId) {
        return invitationDao.getCompanyIdByToken(tokenId);
    }

    @Override
    public boolean CheckingInvitationUser(String tokenId, String firstName, String lastName) throws SQLException {
        return invitationDao.CheckingInvitationUser(tokenId,firstName,lastName);
    }

    @Override
    public int AddNewTokenAndUser(Tokens createUserObject) throws SQLException {
        return invitationDao.AddNewTokenAndUser(createUserObject);
    }

    public boolean checkUserId(int userid) throws SQLException {
        return invitationDao.checkUserId(userid);
    }

    public int UpdateTokenByUserId(String Token, int userEmailId) throws SQLException {
        return invitationDao.UpdateTokenByUserId(Token,userEmailId);
    }

    public int AddNewTokenAndUserOne(Tokens createTokenObject) throws SQLException {
        return invitationDao.AddNewTokenAndUserOne(createTokenObject);
    }


    public boolean CheckingInvitationAffiliate(String email, String affiliateToken) throws SQLException {
        return invitationDao.CheckingInvitationAffiliate(email,affiliateToken);
    }
}
