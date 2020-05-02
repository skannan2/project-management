package com.cognizant.fse.projectmgmt.exception;

public class ParentTaskNotFoundException extends RuntimeException {
    public ParentTaskNotFoundException() {
        super("Parent Task not found");
    }
}
