package com.fertifa.app.cron.job;

import java.sql.Timestamp;

public class ChatNotificationModal {
    private final String session_token;
    private final int from_id;
    private final int to_id;
    private final Timestamp creation_date;
    private final int type;
    private final int status;
    private final int recieved;

    public String getSession_token() {
        return session_token;
    }

    public int getFrom_id() {
        return from_id;
    }

    public int getTo_id() {
        return to_id;
    }

    public Timestamp getCreation_date() {
        return creation_date;
    }

    public int getType() {
        return type;
    }

    public int getStatus() {
        return status;
    }

    public int getRecieved() {
        return recieved;
    }

    public ChatNotificationModal(ChatNotificationModalBuilder chatNotificationModalBuilder) {
        this.session_token = chatNotificationModalBuilder.session_token;
        this.from_id = chatNotificationModalBuilder.from_id;
        this.to_id = chatNotificationModalBuilder.to_id;
        this.type = chatNotificationModalBuilder.type;
        this.status = chatNotificationModalBuilder.status;
        this.creation_date = chatNotificationModalBuilder.creation_date;
        this.recieved = chatNotificationModalBuilder.recieved;

    }

    public static class ChatNotificationModalBuilder{
        private  String session_token;
        private  int from_id;
        private  int to_id;
        private  Timestamp creation_date;
        private  int type;
        private  int status;
        private int recieved;

        public ChatNotificationModalBuilder session_token(String session_token){
            this.session_token = session_token;
            return this;
        }

        public ChatNotificationModalBuilder from_id(int from_id){
            this.from_id = from_id;
            return this;
        }

        public ChatNotificationModalBuilder to_id(int to_id){
            this.to_id = to_id;
            return this;
        }

        public ChatNotificationModalBuilder creation_date(Timestamp creation_date){
            this.creation_date = creation_date;
            return this;
        }

        public ChatNotificationModalBuilder type(int type){
            this.type = type;
            return this;
        }

        public ChatNotificationModalBuilder status(int status){
            this.status = status;
            return this;
        }
        public ChatNotificationModalBuilder recieved(int recieved){
            this.recieved = recieved;
            return this;
        }

        public ChatNotificationModal build(){
            return new ChatNotificationModal(this);
        }
    }
}
