package com.cognizant.fse.projectmgmt.vo;

import java.io.Serializable;

import org.springframework.stereotype.Component;

/**
 * Created by Sanjay
 */
@Component
public class User implements Serializable {

    private long userId;
    private String userName = null;
    private String firstName = null;
    private String lastName = null;

    public User() {

    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
