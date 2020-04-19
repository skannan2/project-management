package com.cognizant.fse.projectmgmt.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

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
	@ManyToOne
	private ProjectTbl projectTbl;
	@ManyToOne
	private ParentTaskTbl parentTaskTbl;

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

	public ProjectTbl getProjectTbl() {
		return projectTbl;
	}

	public void setProjectTbl(ProjectTbl projectTbl) {
		this.projectTbl = projectTbl;
	}

	public ParentTaskTbl getParentTaskTbl() {
		return parentTaskTbl;
	}

	public void setParentTaskTbl(ParentTaskTbl parentTaskTbl) {
		this.parentTaskTbl = parentTaskTbl;
	}

}