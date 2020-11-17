package com.fertifa.app.models;

import java.sql.Timestamp;

public class UsersModalBuilder implements UsersModalGettersService{
    private final int id;
    private final String email;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String phoneNumber;
    private final int branchId;
    private final Timestamp creationDate;
    private final Timestamp updateDate;
    private final Timestamp lastLoginDate;
    private final String domain;
    private final String packageName;
    private final String description;
    private final int gender;
    private final int status;
    private final String userImage;
    private final String company;
    private final int packageId;
    private final int companyId;
    private final int age;
    private final String DateString;
    private final int count;
    private final String token;
    private final int affiliateId;
    private final String role;

    public UsersModalBuilder(UsersBuilder usersBuilder) {
        this.id = usersBuilder.id;
        this.email = usersBuilder.email;
        this.password = usersBuilder.password;
        this.firstName = usersBuilder.firstName;
        this.lastName = usersBuilder.lastName;
        this.address = usersBuilder.address;
        this.phoneNumber = usersBuilder.phoneNumber;
        this.branchId = usersBuilder.branchId;
        this.creationDate = usersBuilder.creationDate;
        this.updateDate = usersBuilder.updateDate;
        this.lastLoginDate = usersBuilder.lastLoginDate;
        this.domain = usersBuilder.domain;
        this.packageName = usersBuilder.packageName;
        this.description = usersBuilder.description;
        this.gender = usersBuilder.gender;
        this.status = usersBuilder.status;
        this.userImage = usersBuilder.userImage;
        this.company = usersBuilder.company;
        this.packageId = usersBuilder.packageId;
        this.companyId = usersBuilder.companyId;
        this.age = usersBuilder.age;
        this.DateString = usersBuilder.DateString;
        this.count = usersBuilder.count;
        this.token = usersBuilder.token;
        this.affiliateId = usersBuilder.affiliateId;
        this.role = usersBuilder.role;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public int getBranchId() {
        return branchId;
    }

    @Override
    public Timestamp getCreationDate() {
        return creationDate;
    }

    @Override
    public Timestamp getUpdateDate() {
        return updateDate;
    }

    @Override
    public Timestamp getLastLoginDate() {
        return lastLoginDate;
    }

    @Override
    public String getDomain() {
        return domain;
    }

    @Override
    public String getPackageName() {
        return packageName;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getGender() {
        return gender;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getUserImage() {
        return userImage;
    }

    @Override
    public String getCompany() {
        return company;
    }

    @Override
    public int getPackageId() {
        return packageId;
    }

    @Override
    public int getCompanyId() {
        return companyId;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String getDateString() {
        return DateString;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public int getAffiliateId() {
        return affiliateId;
    }

    @Override
    public String getRole() {
        return role;
    }

    public static class UsersBuilder  {
        private int id;
        private String email;
        private String password;
        private String firstName;
        private String lastName;
        private String address;
        private String phoneNumber;
        private int branchId;
        private Timestamp creationDate;
        private Timestamp updateDate;
        private Timestamp lastLoginDate;
        private String domain;
        private String packageName;
        private String description;
        private int gender;
        private int status;
        private String userImage;
        private String company;
        private int packageId;
        private int companyId;
        private int age;
        private String DateString;
        private int count;
        private String token;
        private int affiliateId;
        private String role;

        public UsersBuilder id(int id) {
            this.id = id;
            return this;
        }

        public UsersBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UsersBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UsersBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UsersBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UsersBuilder address(String address) {
            this.address = address;
            return this;
        }

        public UsersBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UsersBuilder branchId(int branchId) {
            this.branchId = branchId;
            return this;
        }

        public UsersBuilder creationDate(Timestamp creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public UsersBuilder updateDate(Timestamp updateDate) {
            this.updateDate = updateDate;
            return this;
        }

        public UsersBuilder lastLoginDate(Timestamp lastLoginDate) {
            this.lastLoginDate = lastLoginDate;
            return this;
        }

        public UsersBuilder domain(String domain) {
            this.domain = domain;
            return this;
        }

        public UsersBuilder packageName(String packageName) {
            this.packageName = packageName;
            return this;
        }

        public UsersBuilder description(String description) {
            this.description = description;
            return this;
        }

        public UsersBuilder status(int status) {
            this.status = status;
            return this;
        }

        public UsersBuilder gender(int gender) {
            this.gender = gender;
            return this;
        }

        public UsersBuilder userImage(String userImage) {
            this.userImage = userImage;
            return this;
        }

        public UsersBuilder company(String company) {
            this.company = company;
            return this;
        }

        public UsersBuilder packageId(int packageId) {
            this.packageId = packageId;
            return this;
        }

        public UsersBuilder companyId(int companyId) {
            this.companyId = companyId;
            return this;
        }

        public UsersBuilder age(int age) {
            this.age = age;
            return this;
        }

        public UsersBuilder count(int count) {
            this.count = count;
            return this;
        }

        public UsersBuilder DateString(String DateString) {
            this.DateString = DateString;
            return this;
        }

        public UsersBuilder token(String token) {
            this.token = token;
            return this;
        }

        public UsersBuilder affiliateId(int affiliateId) {
            this.affiliateId = affiliateId;
            return this;
        }

        public UsersBuilder role(String role) {
            this.role = role;
            return this;
        }

        public UsersModalBuilder build() {
            return new UsersModalBuilder(this);
        }
    }
}
