package com.example.examate.user;

public class UserNotFoundException extends Throwable {
    public UserNotFoundException(String msg) {
        super(msg);
    }
}
