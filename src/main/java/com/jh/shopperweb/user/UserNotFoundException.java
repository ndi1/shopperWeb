package com.jh.shopperweb.user;

//Custom exception for when a user is not found
public class UserNotFoundException extends Throwable {

    public UserNotFoundException(String message) {
        super(message);
    }
}
