package com.jh.shopperweb.users_recipes;

//Custom exception for when a user's recipe is not found
public class UsersRecipesNotFoundException extends Throwable{
    public UsersRecipesNotFoundException(String message) {
        super(message);
    }
}
