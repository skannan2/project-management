package com.cognizant.fse.projectmgmt.exception;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException() {
        super("Project not found");
    }
}
