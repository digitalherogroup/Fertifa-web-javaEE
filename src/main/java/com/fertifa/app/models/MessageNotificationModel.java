package com.fertifa.app.models;

import java.sql.Timestamp;

public class MessageNotificationModel {
    private final int id;
    private final String sessionId;
    private final int from;
    private final int to;
    private final int isSent;
    private final int delay;
    private final int waiting;
    private final Timestamp date;

    public int getId() {
        return id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public int getIsSent() {
        return isSent;
    }

    public int getDelay() {
        return delay;
    }

    public int getWaiting() {
        return waiting;
    }

    public Timestamp getDate() {
        return date;
    }

    public MessageNotificationModel(MessageNotificationModelBuilder messageNotificationModelBuilder) {
        this.id = messageNotificationModelBuilder.id;
        this.sessionId = messageNotificationModelBuilder.sessionId;
        this.from = messageNotificationModelBuilder.from;
        this.to = messageNotificationModelBuilder.to;
        this.isSent = messageNotificationModelBuilder.isSent;
        this.delay = messageNotificationModelBuilder.delay;
        this.waiting = messageNotificationModelBuilder.waiting;
        this.date = messageNotificationModelBuilder.date;



    }

    public static class MessageNotificationModelBuilder {
        private int id;
        private String sessionId;
        private int from;
        private int to;
        private int isSent;
        private int delay;
        private int waiting;
        private Timestamp date;

        public MessageNotificationModelBuilder id(int id) {
            this.id = id;
            return this;
        }

        public MessageNotificationModelBuilder sessionId(String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public MessageNotificationModelBuilder from(int from) {
            this.from = from;
            return this;
        }

        public MessageNotificationModelBuilder to(int to) {
            this.to = to;
            return this;
        }

        public MessageNotificationModelBuilder isSent(int isSent) {
            this.isSent = isSent;
            return this;
        }

        public MessageNotificationModelBuilder delay(int delay) {
            this.delay = delay;
            return this;
        }

        public MessageNotificationModelBuilder waiting(int waiting) {
            this.waiting = waiting;
            return this;
        }

        public MessageNotificationModelBuilder date(Timestamp date) {
            this.date = date;
            return this;
        }

        public MessageNotificationModel build() {
            return new MessageNotificationModel(this);
        }


    }
}
