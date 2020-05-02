package com.cognizant.fse.projectmgmt.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String searchString) {
        super("User not found using search string "+searchString);
    }
}
