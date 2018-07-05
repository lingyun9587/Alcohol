package com.alcohol.exceptions;

public class OrderOperationException extends RuntimeException {

    public OrderOperationException(String message){
        super(message);
    }
}
