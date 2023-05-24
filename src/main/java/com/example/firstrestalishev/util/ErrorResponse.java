package com.example.firstrestalishev.util;

public class ErrorResponse {

    //fields which sent if it will be error

    private String message;
    private Long timeStamp;

    public ErrorResponse(String message, Long timeStamp) {
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
