package com.rest.webservices.demo.baisc_hello_world;

public class HelloWorldBean {

    private String message;

    HelloWorldBean(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("HelloWorldBean [message = %s]", message);
    }
}
