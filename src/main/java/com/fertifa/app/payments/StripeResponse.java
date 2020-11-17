package com.fertifa.app.payments;

public class StripeResponse {
    private boolean success;
    private String message;

    public StripeResponse() {
    }

    public StripeResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
