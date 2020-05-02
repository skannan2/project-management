package com.cognizant.fse.projectmgmt.exception;

public class AppException extends RuntimeException {

    public AppException() {
        super("Application has issue. Please retry later");
    }
}
