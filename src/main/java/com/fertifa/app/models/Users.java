package com.fertifa.app.models;

import com.fertifa.app.converters.DateConverter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Users {
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
    private String PackagName;
    private String discription;
    private int gender;
    private int status;
    private String userImage;
    private String comapny;
    private int packagId;
    private int companyId;
    private int age;
    private String DateString;
    private int count;
    private String token;
    private int affiliateId;
    private String role;


    public Users(String email, String password, String firstName, String lastName, String address, String phoneNumber, int branchId, Timestamp creationDate, Timestamp updateDate, Timestamp lastLoginDate, String domain, String packagName, String discription, int gender, int status, String userImage, String comapny) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.branchId = branchId;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.lastLoginDate = lastLoginDate;
        this.domain = domain;
        this.PackagName = packagName;
        this.discription = discription;
        this.gender = gender;
        this.status = status;
        this.userImage = userImage;
        this.comapny = comapny;
    }

    public Users(String address, String phoneNumber, String domain, String packagName, String comapny, int packagId) {
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.domain = domain;
        this.PackagName = packagName;
        this.comapny = comapny;
        this.packagId = packagId;
    }

    public Users(String firstName, String lastName, String address, String phoneNumber, Timestamp updateDate, int gender, int status, String comapny, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.updateDate = updateDate;
        this.gender = gender;
        this.status = status;
        this.comapny = comapny;
        this.age = age;
    }

    //CompanyName,Status,Domain,Address, new Timestamp(date.getTime()),PhoneNumber,PackageName


    public Users() {
    }

    public Users(String companyName, int status, String domain, String address, Timestamp timestamp, String phoneNumber, String packageName, int PackageId) {
        this.comapny = companyName;
        this.status = status;
        this.domain = domain;
        this.address = address;
        this.updateDate = timestamp;
        this.phoneNumber = phoneNumber;
        this.PackagName = packageName;
        this.packagId = PackageId;
    }

    public Users(String companyName, int status, String domain, String address, Timestamp timestamp, String phoneNumber, String packageName, int PackageId, String token) {
        this.comapny = companyName;
        this.status = status;
        this.domain = domain;
        this.address = address;
        this.updateDate = timestamp;
        this.phoneNumber = phoneNumber;
        this.PackagName = packageName;
        this.packagId = PackageId;
        this.token = token;
    }

    public Users(String companyEmail, String companyName) {
        this.email = companyEmail;
        this.comapny = companyName;
    }

    public Users(String companyPassword, String companyAddress, String companyDomain) {
        this.password = companyPassword;
        this.address = companyAddress;
        this.domain = companyDomain;
    }

    public Users(String password) {
        this.password = password;
    }

    public Users(String firsName, String lastName, String email, int status, int branchId, Timestamp created_date, int company_id) {
        this.firstName = firsName;
        this.lastName = lastName;
        this.email = email;
        this.status = status;
        this.branchId = branchId;
        this.creationDate = created_date;
        this.companyId = company_id;
    }

    public Users(String sessionUserEmail, String companyPhone, String companyAddress, String companyName) {
        this.email = sessionUserEmail;
        this.phoneNumber = companyPhone;
        this.address = companyAddress;
        this.comapny = companyName;
    }


    public Users(String userFirstName, String userLastName, String userEmail, int status, int genderId, String userPassword) {
        this.firstName = userFirstName;
        this.lastName = userLastName;
        this.email = userEmail;
        this.status = status;
        this.gender = genderId;
        this.password = userPassword;
    }

    public Users(String sessionUserEmail, String companyPhone, String userAddress, String userFirstName, String userSecondName) {
        this.email = sessionUserEmail;
        this.phoneNumber = companyPhone;
        this.address = userAddress;
        this.firstName = userFirstName;
        this.lastName = userSecondName;
    }

    public Users(String sessionUserEmail, String companyPhone, String companyAddress, String companyName, String filepat, boolean b) {
        this.email = sessionUserEmail;
        this.phoneNumber = companyPhone;
        this.address = companyAddress;
        this.comapny = companyName;
        this.userImage = filepat;
    }

    public Users(String sessionUserEmail, String userPhone, String userAddress, String userFirstName, String userSecondName, String filepat) {
        this.email = sessionUserEmail;
        this.phoneNumber = userPhone;
        this.address = userAddress;
        this.firstName = userFirstName;
        this.lastName = userSecondName;
        this.userImage = filepat;
    }

    public Users(String companyEmail, String firstName, String lastName, int companyName, int users, int feedbackstatuspending, Timestamp timestamp, String companyNameFromId) {
        this.email = companyEmail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyId = companyName;
        this.branchId = users;
        this.status = feedbackstatuspending;
        this.creationDate = timestamp;
        this.comapny = companyNameFromId;

    }

    public Users(String email, String firsName, String lastName, int pending, int users, Timestamp timestamp, int companyId, String token) {
        this.email = email;
        this.firstName = firsName;
        this.lastName = lastName;
        this.status = pending;
        this.branchId = users;
        this.creationDate = timestamp;
        this.companyId = companyId;
        this.token = token;
    }

    public Users(String companyEmail, String companyName, String packageName, int packageId) {
        this.email = companyEmail;
        this.comapny = companyName;
        this.PackagName = packageName;
        this.packagId = packageId;

    }

    public Users(int id, String email, String address, String phonenumber, int branch_id,
                 Timestamp creation_date, String domain, String aPackage, String discription,
                 int gender, int age, int status, String firstname, String lastname, String company,
                 int packageId, int companyid, String imagelink, int affiliateid, String role) {
        this.id = id;
        this.email = email;
        this.address = address;
        this.phoneNumber = phonenumber;
        this.branchId = branch_id;
        this.creationDate = creation_date;
        this.domain = domain;
        this.PackagName = aPackage;
        this.discription = discription;
        this.gender = gender;
        this.age = age;
        this.status = status;
        this.firstName = firstname;
        this.lastName = lastname;
        this.comapny = company;
        this.packagId = packageId;
        this.companyId = companyid;
        this.userImage = imagelink;
        this.affiliateId = affiliateid;
        this.role = role;

    }

    public Users(String email, String comapny, int packagId, int affiliateId) {
        this.email = email;
        this.comapny = comapny;
        this.packagId = packagId;
        this.affiliateId = affiliateId;

    }

    public Users(String email, String company, int packagId, int affiliateId, String packName) {
        this.email = email;
        this.comapny = company;
        this.packagId = packagId;
        this.affiliateId = affiliateId;
        this.PackagName = packName;
    }

    public Users(String[] firstNames, String[] lastNames, String[] emails, String[] genders, String[] passwords, String companyName, String[] ages, int companyid,boolean t) {
        this.firstName = firstNames[0];
        this.lastName = lastNames[0];
        this.email = emails[0];
        this.gender = Integer.parseInt(genders[0]);
        this.password = passwords[0];
        this.comapny = companyName;
        this.age = Integer.parseInt(ages[0]);
        this.companyId=companyid;
    }

    public Users(String firstName, String lastName, String address, String phone, Timestamp timestamp, int Gender, int status, String company, int Age, int companyId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phone;
        this.updateDate = timestamp;
        this.gender = Gender;
        this.status = status;
        this.comapny = company;
        this.age = Age;
        this.companyId = companyId;
    }

    public Users(String userFirstName, String userLastName, String userEmail, int pending, int genderId, String userPassword, int age) {
        this.firstName = userFirstName;
        this.lastName = userLastName;
        this.email = userEmail;
        this.status = pending;
        this.gender = genderId;
        this.password = userPassword;
        this.age = age;
    }

    public Users(String firstName, String lastName, String userEmailId, int i, int users, Timestamp timestamp, int id, String comapny, boolean b) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = userEmailId;
        this.status = i;
        this.branchId = users;
        this.creationDate = timestamp;
        this.companyId = id;
        this.comapny = comapny;

    }

    public Users(int id, String email, String address,
                 String phonenumber, int branch_id, Timestamp creation_date,
                 String domain, String aPackage, String discription,
                 int gender, int age, int status, String firstname,
                 String lastname, String company, int packageId, int companyid,
                 String imagelink, int affiliateid, String updated_date, String role) {

        this.id = id;
        this.email = email;
        this.address = address;
        this.phoneNumber = phonenumber;
        this.branchId = branch_id;
        this.creationDate = creation_date;
        this.domain = domain;
        this.PackagName = aPackage;
        this.discription = discription;
        this.gender = gender;
        this.age = age;
        this.status = status;
        this.firstName = firstname;
        this.lastName = lastname;
        this.comapny = company;
        this.packagId = packageId;
        this.companyId = companyid;
        this.userImage = imagelink;
        this.affiliateId = affiliateid;
        this.updateDate = Timestamp.valueOf(updated_date);
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getConvertedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(new Date(creationDate.getTime()));
    }

    public int getAffiliateId() {
        return affiliateId;
    }

    public void setAffiliateId(int affiliateId) {
        this.affiliateId = affiliateId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDateString() {
        return DateString;
    }

    public void setDateString(String dateString) {
        DateString = dateString;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public Timestamp getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Timestamp lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPackagName() {
        return PackagName;
    }

    public void setPackagName(String packagName) {
        PackagName = packagName;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getComapny() {
        return comapny;
    }

    public void setComapny(String comapny) {
        this.comapny = comapny;
    }

    public int getPackagId() {
        return packagId;
    }

    public void setPackagId(int packagId) {
        this.packagId = packagId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getGetCreationDate() {
        return DateConverter.convertDateWithRegex(creationDate);
    }

    public String getGetUpdateDate(){
        return DateConverter.convertDateWithRegex(updateDate);
    }

    public String getGetLastLoginDate(){
        return DateConverter.convertDateWithRegex(lastLoginDate);
    }


}
