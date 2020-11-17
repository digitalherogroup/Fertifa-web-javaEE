package com.fertifa.app.models;

public class ChatSessionUsers {

    private int ChatSessionUsersId;
    private int ChatSessionID;
    private int ChatUserId;
    private String ChatUserToken;

    public ChatSessionUsers(int chatSessionUsersId, int chatSessionID, int chatUserId) {
        ChatSessionUsersId = chatSessionUsersId;
        ChatSessionID = chatSessionID;
        ChatUserId = chatUserId;
    }

    public ChatSessionUsers() {
    }

    public String getChatUserToken() {
        return ChatUserToken;
    }

    public void setChatUserToken(String chatUserToken) {
        ChatUserToken = chatUserToken;
    }

    public ChatSessionUsers(int chatSessionUsersId) {
        ChatSessionUsersId = chatSessionUsersId;
    }

    public ChatSessionUsers(int sessionIdByToken, int parseInt) {
        ChatSessionUsersId = sessionIdByToken;
        ChatUserId = parseInt;
    }


    public int getChatSessionUsersId() {
        return ChatSessionUsersId;
    }

    public void setChatSessionUsersId(int chatSessionUsersId) {
        ChatSessionUsersId = chatSessionUsersId;
    }

    public int getChatSessionID() {
        return ChatSessionID;
    }

    public void setChatSessionID(int chatSessionID) {
        ChatSessionID = chatSessionID;
    }

    public int getChatUserId() {
        return ChatUserId;
    }

    public void setChatUserId(int chatUserId) {
        ChatUserId = chatUserId;
    }
}
