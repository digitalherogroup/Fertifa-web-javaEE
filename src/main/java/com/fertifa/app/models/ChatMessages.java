package com.fertifa.app.models;


import com.fertifa.app.converters.DateConverter;

import java.sql.Timestamp;

public class ChatMessages {

    private int ChatMessageId;
    private int ChatSessionId;
    private String ChatTokenId;
    private int ChatFrom;
    private int ChatTo;
    private String ChatBody;
    private int ChatType;
    private int ChatCategoryId;
    private Timestamp ChatCreationDate;
    private int ChatMessageStatus;
    private String DateString;

    public ChatMessages(int chatMessageId, int chatSessionId, String chatTokenId, int chatFrom, int chatTo, String chatBody, int chatType, int chatCategoryId, Timestamp chatCreationDate, int chatMessageStatus) {
        ChatMessageId = chatMessageId;
        ChatSessionId = chatSessionId;
        ChatTokenId = chatTokenId;
        ChatFrom = chatFrom;
        ChatTo = chatTo;
        ChatBody = chatBody;
        ChatType = chatType;
        ChatCategoryId = chatCategoryId;
        ChatCreationDate = chatCreationDate;
        ChatMessageStatus = chatMessageStatus;
    }

    public ChatMessages() {
    }

    public ChatMessages(String no_message_t_show) {
        ChatBody = no_message_t_show;

    }

    public ChatMessages(int chatSessionId, int chatFromId, int chatToId, int chatSessionType, String s) {
        ChatSessionId = chatSessionId;
        ChatFrom = chatFromId;
        ChatTo = chatToId;
        ChatBody = s;
        ChatType = chatSessionType;
    }

    public int getChatMessageId() {
        return ChatMessageId;
    }

    public String getDateString() {
        return DateString;
    }

    public void setDateString(String dateString) {
        DateString = dateString;
    }

    public void setChatMessageId(int chatMessageId) {
        ChatMessageId = chatMessageId;
    }

    public int getChatSessionId() {
        return ChatSessionId;
    }

    public void setChatSessionId(int chatSessionId) {
        ChatSessionId = chatSessionId;
    }

    public String getChatTokenId() {
        return ChatTokenId;
    }

    public void setChatTokenId(String chatTokenId) {
        ChatTokenId = chatTokenId;
    }

    public int getChatFrom() {
        return ChatFrom;
    }

    public void setChatFrom(int chatFrom) {
        ChatFrom = chatFrom;
    }

    public int getChatTo() {
        return ChatTo;
    }

    public void setChatTo(int chatTo) {
        ChatTo = chatTo;
    }

    public String getChatBody() {
        return ChatBody;
    }

    public void setChatBody(String chatBody) {
        ChatBody = chatBody;
    }

    public int getChatType() {
        return ChatType;
    }

    public void setChatType(int chatType) {
        ChatType = chatType;
    }

    public int getChatCategoryId() {
        return ChatCategoryId;
    }

    public void setChatCategoryId(int chatCategoryId) {
        ChatCategoryId = chatCategoryId;
    }

    public Timestamp getChatCreationDate() {
        return ChatCreationDate;
    }

    public void setChatCreationDate(Timestamp chatCreationDate) {
        ChatCreationDate = chatCreationDate;
    }

    public int getChatMessageStatus() {
        return ChatMessageStatus;
    }

    public void setChatMessageStatus(int chatMessageStatus) {
        ChatMessageStatus = chatMessageStatus;
    }

    public String getChatString() {
        return DateConverter.convertDateWithRegex(ChatCreationDate);
    }

}
