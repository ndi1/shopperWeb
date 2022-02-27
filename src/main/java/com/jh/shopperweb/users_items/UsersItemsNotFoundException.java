package com.jh.shopperweb.users_items;

//Custom exception for when a user's item is not found
public class UsersItemsNotFoundException extends Throwable{
    public UsersItemsNotFoundException(String message) {
        super(message);
    }
}
