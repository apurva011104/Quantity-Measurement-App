package com.apps.quantitymeasurement.exception;

public class DatabaseException extends Exception{

    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(String string, Throwable e) {
        super(string, e);
    }
    
}
