package com.fertifa.app.adminSide.Response;

public class AffiliateUserResponse {

    private String packid;
    private String packName;
    private  String type;
    private String email;
    private String company;
    private String secretKey;

    public AffiliateUserResponse() {
    }

    public AffiliateUserResponse(String email, String companyName, String packageName, String pack,String type) {
        this.email=email;
        this.company=companyName;
        this.packName=packageName;
        this.packid=pack;
        this.type=type;
    }

    public String getType() {
        return type;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPackid() {
        return packid;
    }

    public void setPackid(String packid) {
        this.packid = packid;
    }

    public String getPackName() {
        return packName;
    }

    public void setPackName(String packName) {
        this.packName = packName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
