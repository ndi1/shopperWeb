package com.jh.shopperweb.item;

//Custom exception for when an item is not found
public class ItemNotFoundException extends Throwable {

    public ItemNotFoundException(String message) {
        super(message);
    }


}
