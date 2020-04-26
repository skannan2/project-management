package com.cognizant.fse.projectmgmt.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Sanjay
 */
@Entity
@Table
public class TaskTbl implements Serializable {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long taskId;
	@Column
	String task;
	@Column
	LocalDate startDate;
	@Column
	LocalDate endDate;
	@Column
	int priority;

	@Column
	String status;

	@ManyToOne
	private ParentTaskTbl parentTaskTbl;

	@OneToOne
	private UserTbl userTbl;
	@OneToOne
	private ProjectTbl projectTbl;

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public ParentTaskTbl getParentTaskTbl() {
		return parentTaskTbl;
	}

	public void setParentTaskTbl(ParentTaskTbl parentTaskTbl) {
		this.parentTaskTbl = parentTaskTbl;
	}

	public UserTbl getUserTbl() {
		return userTbl;
	}

	public void setUserTbl(UserTbl userTbl) {
		this.userTbl = userTbl;
	}

	public ProjectTbl getProjectTbl() {
		return projectTbl;
	}

	public void setProjectTbl(ProjectTbl projectTbl) {
		this.projectTbl = projectTbl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TaskTbl taskTbl = (TaskTbl) o;
		return taskId.equals(taskTbl.taskId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(taskId);
	}
}
