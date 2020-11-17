package com.fertifa.app.models;

public class WhiteModel {
    private final int id;
    private final int employeeId;
    private final String whiteDomain;
    private final int status;

    public WhiteModel(WhiteBuilder whiteBuilder) {
        this.id = whiteBuilder.id;
        this.employeeId = whiteBuilder.employeeId;
        this.whiteDomain = whiteBuilder.whiteDomain;
        this.status = whiteBuilder.status;
    }

    public int getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public int getEmployerId() {
        return employeeId;
    }

    public String getWhiteDomain() {
        return whiteDomain;
    }

    public static class WhiteBuilder {
        private int id;
        private int employeeId;
        private String whiteDomain;
        private int status;

        public WhiteBuilder id(int id) {
            this.id = id;
            return this;
        }

        public WhiteBuilder status(int status) {
            this.status = status;
            return this;
        }

        public WhiteBuilder employerId(int employerId) {
            this.employeeId = employerId;
            return this;
        }

        public WhiteBuilder whiteDomain(String whiteDomain) {
            this.whiteDomain = whiteDomain;
            return this;
        }

        public WhiteModel build() {
            return new WhiteModel(this);
        }

    }
}
