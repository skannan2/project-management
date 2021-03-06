package com.cognizant.fse.projectmgmt.service;

import com.cognizant.fse.projectmgmt.dao.ProjectDaoInterface;
import com.cognizant.fse.projectmgmt.dao.UserDaoInterface;
import com.cognizant.fse.projectmgmt.model.ProjectTbl;
import com.cognizant.fse.projectmgmt.model.UserTbl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.fse.projectmgmt.dao.ParentTaskDaoInterface;
import com.cognizant.fse.projectmgmt.dao.TaskDaoInterface;
import com.cognizant.fse.projectmgmt.model.ParentTaskTbl;
import com.cognizant.fse.projectmgmt.vo.Task;
import com.cognizant.fse.projectmgmt.model.TaskTbl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Sanjay on 10/2/2018.
 */
@Service
public class TaskService {

    private TaskDaoInterface taskDao;
    private ParentTaskDaoInterface parentTaskDao;
    private ProjectDaoInterface projectDao;
    private UserDaoInterface userDao;

	@Autowired
	public TaskService(TaskDaoInterface taskDao, ParentTaskDaoInterface parentTaskDao,
					   ProjectDaoInterface projectDao, UserDaoInterface userDao) {
		this.taskDao = taskDao;
		this.parentTaskDao = parentTaskDao;
		this.projectDao = projectDao;
		this.userDao = userDao;
	}

	@Transactional
    public String addUpdateTask(Task task) {
		ParentTaskTbl parentTaskTbl = null;
		boolean isParentTask = task.isParentTaskSelected();
		System.out.println("***isParentTask***"+isParentTask);
		if(isParentTask) {
			String parentTaskName = task.getTask();
			parentTaskTbl = new ParentTaskTbl();
			parentTaskTbl.setParentTask(parentTaskName);

			parentTaskDao.save(parentTaskTbl);
		} else {
			long parentTaskId = task.getParentTaskId();
			Optional<ParentTaskTbl> parentTaskObj = null;

			if (parentTaskId > 0) {
				parentTaskObj = parentTaskDao.findById(parentTaskId);
				parentTaskTbl = parentTaskObj.get();
				parentTaskTbl.setParentTaskId(parentTaskId);

			} else {
				parentTaskTbl = new ParentTaskTbl();
			}

			parentTaskTbl.setParentTask(task.getParentTask());
			parentTaskDao.save(parentTaskTbl);

			long taskId = task.getTaskId();
			Optional<TaskTbl> taskObj = null;
			TaskTbl taskTbl = null;
			if (taskId > 0) {
				taskObj = taskDao.findById(taskId);
				taskTbl = taskObj.get();
				taskTbl.setTaskId(taskId);
			} else {
				taskTbl = new TaskTbl();
				taskTbl.setStatus("Created");
			}

			System.out.println("***taskId**" + task.getTaskId());
			System.out.println("***task Name**" + task.getTask());
			System.out.println("***parent task Id**" + task.getParentTaskId());
			System.out.println("***parent task Name**" + task.getParentTask());
			System.out.println("***Priority**" + task.getPriority());
			System.out.println("***Start Date**" + task.getStartDate());
			System.out.println("***End date**" + task.getEndDate());
			System.out.println("***Project ID**" + task.getProjectId());
			System.out.println("***Project Name**" + task.getProject());
			System.out.println("***Manager ID**" + task.getManagerId());
			System.out.println("***Manager**" + task.getManager());

			taskTbl.setTask(task.getTask());
			//taskTbl.setParentTaskId(parentTaskId);
			taskTbl.setPriority(task.getPriority());

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			LocalDate startdate = LocalDate.parse(task.getStartDate(), formatter);

			taskTbl.setStartDate(startdate);
			LocalDate enddate = LocalDate.parse(task.getEndDate(), formatter);
			taskTbl.setEndDate(enddate);
			taskTbl.setParentTaskTbl(parentTaskTbl);

			long projectId = task.getProjectId();
			ProjectTbl projectTbl = projectDao.findById(projectId).get();
			taskTbl.setProjectTbl(projectTbl);

			long userId = task.getManagerId();
			UserTbl userTbl = userDao.findById(userId).get();
			taskTbl.setUserTbl(userTbl);

			taskDao.save(taskTbl);
		}

		return "Successful";
    }
	
		
	@Transactional
    public List<TaskTbl> getTask() {
		List<TaskTbl> taskList = (List<TaskTbl>) taskDao.findAll();
		
		return taskList;
    }

	@Transactional
	public List<ParentTaskTbl> getParentTask() {
		List<ParentTaskTbl> parentTaskList = (List<ParentTaskTbl>) parentTaskDao.findAll();

		return parentTaskList;
	}
	
	@Transactional
    public TaskTbl getTaskById(long taskId) {
		Optional<TaskTbl> taskObj = taskDao.findById(taskId);
		TaskTbl task = (TaskTbl) taskObj.get();
		
		return task;
    }

	@Transactional
	public List<TaskTbl> getTaskByProjectId(long projectId) {
		List<TaskTbl> taskList = (List<TaskTbl>) taskDao.findTaskByProjectId(projectId);

		return taskList;
	}
	
	@Transactional
    public void deleteTask(long taskId) {
		taskDao.completeTask(taskId);
    }

	@Transactional
	public int countTask(long projectId) {
		return taskDao.countTask(projectId);
	}

	@Transactional
	public int countCompleteTask(String status) {
		return taskDao.countCompleteTask(status);
	}

	@Transactional
	public List<TaskTbl> sortTask(String sortField) {

		return (List<TaskTbl>)taskDao.findAll(Sort.by(""+sortField+""));
	}
		
}
