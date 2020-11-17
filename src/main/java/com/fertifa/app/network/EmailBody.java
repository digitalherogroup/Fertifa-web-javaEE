package com.fertifa.app.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EmailBody {

    @SerializedName("personalizations")
    @Expose
    private List<Personalization> personalizations = null;
    @SerializedName("from")
    @Expose
    private From from;
    @SerializedName("content")
    @Expose
    private List<Content> content = null;

    public List<Personalization> getPersonalizations() {
        return personalizations;
    }

    public void setPersonalizations(List<Personalization> personalizations) {
        this.personalizations = personalizations;
    }

    public From getFrom() {
        return from;
    }

    public void setFrom(From from) {
        this.from = from;
    }

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }



    public static class Content {

        @SerializedName("type")
        @Expose
        private String type = "text/html";
        @SerializedName("value")
        @Expose
        private String value;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }

    public static class From {

        @SerializedName("email")
        @Expose
        private String email;


        public From(String email) {
            this.email = email;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

    }

    public static class Personalization {

        @SerializedName("to")
        @Expose
        private List<To> to = null;
        @SerializedName("subject")
        @Expose
        private String subject;

        public List<To> getTo() {
            return to;
        }

        public void setTo(List<To> to) {
            this.to = to;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

    }

    public static class To {

        @SerializedName("email")
        @Expose
        private String email;

        public To(String email) {
            this.email = email;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

    }
}
