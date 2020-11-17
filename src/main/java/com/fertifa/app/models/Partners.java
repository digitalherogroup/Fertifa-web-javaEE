package com.fertifa.app.models;

import java.sql.Timestamp;

public class Partners {
    private int id;
    private String partnerName;
    private Timestamp creationDate;
    private String partnerDiscription;
    private String logoLink;
    private String domain;

    public Partners(int id, String partnerName, Timestamp creationDate, String partnerDiscription, String logoLink) {
        this.id = id;
        this.partnerName = partnerName;
        this.creationDate = creationDate;
        this.partnerDiscription = partnerDiscription;
        this.logoLink = logoLink;
    }

    public Partners() {
    }

    public Partners(String title, String description,Timestamp  creationDate, String linkToSave) {
        this.partnerName = title;
        this.partnerDiscription = description;
        this.creationDate = creationDate;
        this.logoLink = linkToSave;
    }

    public Partners(String title, String description, Timestamp creationDate) {
        this.partnerName = title;
        this.partnerDiscription = description;
        this.creationDate = creationDate;
    }

    public Partners(String linkToSave) {
        this.logoLink = linkToSave;
    }

    public Partners(String title, String description) {
        this.partnerName = title;
        this.partnerDiscription = description;
    }

    public Partners(String title, String description, String linkToSave) {
        this.partnerName = title;
        this.partnerDiscription = description;
        this.logoLink = linkToSave;

    }

    public Partners(String title, String description, Timestamp creationDate, String linkToSave, String domain) {
        this.partnerName = title;
        this.partnerDiscription = description;
        this.creationDate = creationDate;
        this.logoLink = linkToSave;
        this.domain=domain;
    }

    public Partners(String title, String description, String linkToSave, String updateDomain) {
        this.partnerName = title;
        this.partnerDiscription = description;
        this.logoLink = linkToSave;
        this.domain=updateDomain;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public String getPartnerDiscription() {
        return partnerDiscription;
    }

    public void setPartnerDiscription(String partnerDiscription) {
        this.partnerDiscription = partnerDiscription;
    }

    public String getLogoLink() {
        return logoLink;
    }

    public void setLogoLink(String logoLink) {
        this.logoLink = logoLink;
    }
}
