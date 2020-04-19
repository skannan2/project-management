package com.cognizant.fse.projectmgmt.model;

import javax.persistence.*;
import java.io.Serializable;

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
	@ManyToOne
	private ProjectTbl projectTbl;
	@ManyToOne
	private TaskTbl taskTbl;

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

	public ProjectTbl getProjectTbl() {
		return projectTbl;
	}

	public void setProjectTbl(ProjectTbl projectTbl) {
		this.projectTbl = projectTbl;
	}

	public TaskTbl getTaskTbl() {
		return taskTbl;
	}

	public void setTaskTbl(TaskTbl taskTbl) {
		this.taskTbl = taskTbl;
	}

}