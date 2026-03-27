package com.apps.quantity_measurement_app.exception;

public class DatabaseException extends Exception{

    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(String string, Throwable e) {
        super(string, e);
    }
    
}