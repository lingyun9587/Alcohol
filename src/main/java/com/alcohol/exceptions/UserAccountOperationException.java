package com.alcohol.exceptions;

public class UserAccountOperationException extends  RuntimeException {

    public UserAccountOperationException(String msg){
      super(msg);
    }
}
