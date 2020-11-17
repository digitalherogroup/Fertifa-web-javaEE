package com.fertifa.app.models;

public class Chat {

    private ChatSession chatSession;
    private ChatSessionUsers chatSessionUsers;
    private ChatMessages chatMessages;
    private ChatMessagesCategory chatMessagesCategory;

    public Chat(ChatSession chatSession, ChatSessionUsers chatSessionUsers, ChatMessages chatMessages, ChatMessagesCategory chatMessagesCategory) {
        this.chatSession = chatSession;
        this.chatSessionUsers = chatSessionUsers;
        this.chatMessages = chatMessages;
        this.chatMessagesCategory = chatMessagesCategory;
    }

    public Chat() {
    }

    public Chat(ChatSession chatSession) {
        this.chatSession = chatSession;
    }

    public Chat(ChatSessionUsers chatSessionUsers) {
        this.chatSessionUsers = chatSessionUsers;
    }

    public ChatSession getChatSession() {
        return chatSession;
    }

    public void setChatSession(ChatSession chatSession) {
        this.chatSession = chatSession;
    }

    public ChatSessionUsers getChatSessionUsers() {
        return chatSessionUsers;
    }

    public void setChatSessionUsers(ChatSessionUsers chatSessionUsers) {
        this.chatSessionUsers = chatSessionUsers;
    }

    public ChatMessages getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(ChatMessages chatMessages) {
        this.chatMessages = chatMessages;
    }

    public ChatMessagesCategory getChatMessagesCategory() {
        return chatMessagesCategory;
    }

    public void setChatMessagesCategory(ChatMessagesCategory chatMessagesCategory) {
        this.chatMessagesCategory = chatMessagesCategory;
    }
}
