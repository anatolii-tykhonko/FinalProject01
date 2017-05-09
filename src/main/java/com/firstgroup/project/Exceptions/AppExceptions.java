package com.firstgroup.project.Exceptions;

/**
 * Created by Sonikb on 25.04.2017.
 */
public class AppExceptions extends Exception {
    String message;

    public AppExceptions(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
