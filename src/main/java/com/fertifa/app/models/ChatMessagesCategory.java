package com.fertifa.app.models;

public class ChatMessagesCategory {
    private int chatMessageCategoryId;
    private String chatMessageName;

    public ChatMessagesCategory(int chatMessageCategoryId, String chatMessageName) {
        this.chatMessageCategoryId = chatMessageCategoryId;
        this.chatMessageName = chatMessageName;
    }

    public ChatMessagesCategory() {
    }

    public int getChatMessageCategoryId() {
        return chatMessageCategoryId;
    }

    public void setChatMessageCategoryId(int chatMessageCategoryId) {
        this.chatMessageCategoryId = chatMessageCategoryId;
    }

    public String getChatMessageName() {
        return chatMessageName;
    }

    public void setChatMessageName(String chatMessageName) {
        this.chatMessageName = chatMessageName;
    }
}
