package com.fertifa.app.models;

import com.fertifa.app.converters.DateConverter;

import java.sql.Timestamp;

public class ChatSession {
    private int ChatSessionId;
    private String ChatSessionToken;
    private Timestamp ChatCreationDate;
    private Timestamp ChatClosingDate;
    private int ChatSessionType;
    private int ChatSessionStatus;
    private int ChatFromId;
    private int ChatToId;
    private String ChatSessionName;
    private String ChatSessionDate;
    private String importantFor;
    private int important_for_admin;
    private int important_for_user;
    private String firstName;
    private String lasetName;
    private String companyName;
    private String StringDate;
    private int uid;

    public ChatSession(int chatSessionId, String chatSessionToken, Timestamp chatCreationDate, Timestamp chatClosingDate, int chatSessionType, int chatSessionStatus, int chatFromId, int chatToId, String chatSessionName, String chatSessionDate) {
        ChatSessionId = chatSessionId;
        ChatSessionToken = chatSessionToken;
        ChatCreationDate = chatCreationDate;
        ChatClosingDate = chatClosingDate;
        ChatSessionType = chatSessionType;
        ChatSessionStatus = chatSessionStatus;
        ChatFromId = chatFromId;
        ChatToId = chatToId;
        ChatSessionName = chatSessionName;
        ChatSessionDate = chatSessionDate;
    }

    public ChatSession() {
    }

    public String getImportantFor() {
        return importantFor;
    }

    public void setImportantFor(String importantFor) {
        this.importantFor = importantFor;
    }

    public ChatSession(String sesionToken, int chatSessionType, int chatSessionStatus, Timestamp chatClosingDate, int userId, int chatToId, String chatSessionName) {
        this.ChatSessionToken = sesionToken;
        this.ChatSessionType = chatSessionType;
        this.ChatSessionStatus = chatSessionStatus;
        this.ChatClosingDate = chatClosingDate;
        this.ChatFromId=userId;
        this.ChatToId=chatToId;
        this. ChatSessionName = chatSessionName;
    }

    public ChatSession(String token, int categorynotification, int i, int from, int to, Timestamp timestamp, int userId, int i1, String title) {
        this.ChatSessionToken = token;
        this.ChatSessionType = categorynotification;
        this.ChatSessionStatus = i;
        this.ChatCreationDate = timestamp;
        this.ChatFromId=userId;
        this.ChatToId=to;
        this. ChatSessionName = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getStringDate() {
        return StringDate;
    }

    public void setStringDate(String stringDate) {
        StringDate = stringDate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLasetName() {
        return lasetName;
    }

    public void setLasetName(String lasetName) {
        this.lasetName = lasetName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getImportant_for_user() {
        return important_for_user;
    }

    public void setImportant_for_user(int important_for_user) {
        this.important_for_user = important_for_user;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getImportant_for_admin() {
        return important_for_admin;
    }

    public void setImportant_for_admin(int important_for_admin) {
        this.important_for_admin = important_for_admin;
    }

    public int getChatSessionId() {
        return ChatSessionId;
    }

    public void setChatSessionId(int chatSessionId) {
        ChatSessionId = chatSessionId;
    }

    public String getChatSessionToken() {
        return ChatSessionToken;
    }

    public void setChatSessionToken(String chatSessionToken) {
        ChatSessionToken = chatSessionToken;
    }

    public Timestamp getChatCreationDate() {
        return ChatCreationDate;
    }

    public void setChatCreationDate(Timestamp chatCreationDate) {
        ChatCreationDate = chatCreationDate;
    }

    public Timestamp getChatClosingDate() {
        return ChatClosingDate;
    }

    public void setChatClosingDate(Timestamp chatClosingDate) {
        ChatClosingDate = chatClosingDate;
    }

    public int getChatSessionType() {
        return ChatSessionType;
    }

    public void setChatSessionType(int chatSessionType) {
        ChatSessionType = chatSessionType;
    }

    public int getChatSessionStatus() {
        return ChatSessionStatus;
    }

    public void setChatSessionStatus(int chatSessionStatus) {
        ChatSessionStatus = chatSessionStatus;
    }

    public int getChatFromId() {
        return ChatFromId;
    }

    public void setChatFromId(int chatFromId) {
        ChatFromId = chatFromId;
    }

    public int getChatToId() {
        return ChatToId;
    }

    public void setChatToId(int chatToId) {
        ChatToId = chatToId;
    }

    public String getChatSessionName() {
        return ChatSessionName;
    }

    public void setChatSessionName(String chatSessionName) {
        ChatSessionName = chatSessionName;
    }

    public String getChatSessionDate() {
        return ChatSessionDate;
    }

    public void setChatSessionDate(String chatSessionDate) {
        ChatSessionDate = chatSessionDate;
    }


    public String getGetCreationDate() {
        return DateConverter.convertDateWithRegex(ChatCreationDate);
    }

    public String getGetClosingDate(){
        return DateConverter.convertDateWithRegex(ChatClosingDate);
    }
}
