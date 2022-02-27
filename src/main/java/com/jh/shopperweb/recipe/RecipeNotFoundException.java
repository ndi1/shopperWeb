package com.jh.shopperweb.recipe;

//Custom exception for when a recipe is not found
public class RecipeNotFoundException extends Throwable{
    public RecipeNotFoundException(String message) {
        super(message);
    }
}
