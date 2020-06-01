package com.app;

public class InjectorException extends Exception{

    private int number;
    public int getNumber(){return number;}
    public InjectorException(Throwable cause) {
        super(cause);
    }
}
