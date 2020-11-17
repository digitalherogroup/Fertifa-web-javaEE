package com.fertifa.app.services;


import com.fertifa.app.models.Tokens;

import java.sql.SQLException;

public interface InvitationService {
    int AddNewTokenAndCompany(Tokens tokens) throws SQLException;

    boolean CheckingInvitation(String tokenId, String company) throws SQLException;

    String GetCompanyByTokenId(String tokenId);

    int GetCompanyIdTokenId(String tokenId);

    boolean CheckingInvitationUser(String tokenId, String firstName, String lastName) throws SQLException;

    int AddNewTokenAndUser(Tokens createUserObject) throws SQLException;

}
