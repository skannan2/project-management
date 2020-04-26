package com.cognizant.fse.projectmgmt.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Sanjay
 */
@Entity
@Table
public class UserTbl implements Serializable {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long userId;
	@Column
	String userName;
	@Column
	String firstName;
	@Column
	String lastName;


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		System.out.println("***Inside equals***");
		if (o == null || getClass() != o.getClass()) {
			System.out.println("***Inside equals return false***");
			return false;
		}
		UserTbl userTbl = (UserTbl) o;
		System.out.println("***Inside equals userId***"+userTbl.getUserId());
		return userId.equals(userTbl.userId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId);
	}
}
