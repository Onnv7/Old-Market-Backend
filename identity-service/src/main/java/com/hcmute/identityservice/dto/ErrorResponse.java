package com.hcmute.identityservice.dto;


public class ErrorResponse {
    private boolean success;
    private int status;
    private String message;
    private String stack;

    public ErrorResponse(boolean success, int status, String message, String stack) {
        this.success = success;
        this.status = status;
        this.message = message;
        this.stack = stack;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }
}
