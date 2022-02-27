package com.jh.shopperweb.food;

//Custom exception for when a food is not found
public class FoodNotFoundException extends Throwable{

    public FoodNotFoundException(String message) {
        super(message);
    }

}
