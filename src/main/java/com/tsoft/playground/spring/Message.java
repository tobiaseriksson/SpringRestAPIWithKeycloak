package com.tsoft.playground.spring;

public class Message {

    private String msg;
    private String status;

    public Message(String msg) {
        this.msg = msg;
        this.status = "OK";
    }

    public String getMessage() {
        return msg;
    }

    public String getStatus() {
        return status;
    }
}
