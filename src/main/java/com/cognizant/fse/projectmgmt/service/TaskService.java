package com.cognizant.fse.projectmgmt.service;

import com.cognizant.fse.projectmgmt.dao.UserDaoInterface;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	public TaskService(TaskDaoInterface taskDao, ParentTaskDaoInterface parentTaskDao) {
		this.taskDao = taskDao;
		this.parentTaskDao = parentTaskDao;
	}

	@Transactional
    public String addUpdateTask(Task task) {
		long parentTaskId = task.getParentTaskId();
		Optional<ParentTaskTbl> parentTaskObj = null;
		ParentTaskTbl parentTaskTbl = null;
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
		} 
				
		System.out.println("***taskId**"+task.getTaskId());
		System.out.println("***task Name**"+task.getTask());
		System.out.println("***parent task Id**"+task.getParentTaskId());
		System.out.println("***parent task Name**"+task.getParentTask());
		System.out.println("***Priority**"+task.getPriority());
		System.out.println("***Start Date**"+task.getStartDate());
		System.out.println("***End date**"+task.getEndDate());
		
		taskTbl.setTask(task.getTask());
		//taskTbl.setParentTaskId(parentTaskId);
		taskTbl.setPriority(task.getPriority());
				
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate startdate = LocalDate.parse(task.getStartDate(), formatter);
		 
		taskTbl.setStartDate(startdate);
		LocalDate enddate = LocalDate.parse(task.getEndDate(), formatter);
		taskTbl.setEndDate(enddate);
		taskTbl.setParentTaskTbl(parentTaskTbl);
		
		taskDao.save(taskTbl);
		
		return "Successful";
    }
	
		
	@Transactional
    public List<TaskTbl> getTask() {
		List<TaskTbl> taskList = (List<TaskTbl>) taskDao.findAll();
		
		return taskList;
    }
	
	@Transactional
    public TaskTbl getTaskById(long taskId) {
		Optional<TaskTbl> taskObj = taskDao.findById(taskId);
		TaskTbl task = (TaskTbl) taskObj.get();
		
		return task;
    }
	
	@Transactional
    public void deleteTask(long taskId) {
		
		taskDao.deleteById(taskId);
    }
			
		
}
