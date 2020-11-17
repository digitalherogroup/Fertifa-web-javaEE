package com.fertifa.app.models;

import java.sql.Timestamp;

public interface UsersModalGettersService {
    int getId();

    String getEmail();

    String getPassword();

    String getFirstName();

    String getLastName();

    String getAddress();

    String getPhoneNumber();

    int getBranchId();

    Timestamp getCreationDate();

    Timestamp getUpdateDate();

    Timestamp getLastLoginDate();

    String getDomain();

    String getPackageName();

    String getDescription();

    int getGender();

    int getStatus();

    String getUserImage();

    String getCompany();

    int getPackageId();

    int getCompanyId();

    int getAge();

    String getDateString();

    int getCount();

    String getToken();

    int getAffiliateId();

    String getRole();
}
