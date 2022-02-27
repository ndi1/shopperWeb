package com.jh.shopperweb.users_foods;

//Custom exception for when a user's food is not found
public class UsersFoodsNotFoundException extends Throwable{
    public UsersFoodsNotFoundException(String message) {
        super(message);
    }
}
