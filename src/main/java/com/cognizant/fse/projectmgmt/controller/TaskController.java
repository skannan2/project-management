package com.cognizant.fse.projectmgmt.controller;

import com.cognizant.fse.projectmgmt.model.TaskTbl;
import com.cognizant.fse.projectmgmt.service.TaskService;
import com.cognizant.fse.projectmgmt.vo.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Sanjay on 10/28/2018.
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/project-management")
public class TaskController {

	private TaskService taskService;

	@Autowired
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	
	@PostMapping(path = "/tasks", consumes = "application/json")
	public ResponseEntity<String> addTask(@RequestBody Task task) {

		taskService.addUpdateTask(task);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@PutMapping("/tasks")
	public ResponseEntity<String> updateTask(@RequestBody Task task) {		

		taskService.addUpdateTask(task)
		;
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@DeleteMapping(path="/tasks/{taskId}")
	public ResponseEntity<String> deleteBook(@PathVariable("taskId") int taskId) {

		taskService.deleteTask(taskId);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@GetMapping("/tasks")
	public ResponseEntity<List> getAllTask() {
		
		List<TaskTbl> taskList = taskService.getTask();
		System.out.println("**TaskList***"+taskList.size());
		
		return new ResponseEntity<List>(taskList, HttpStatus.OK);
	}
	
	@GetMapping("/tasks/{taskId}")
	public ResponseEntity<Task> getTask(@PathVariable("taskId") int taskId) {
		
		TaskTbl task = taskService.getTaskById(taskId);
		System.out.println("**Task***"+task);
		Task taskObj = new Task();
		if (task != null) {
			taskObj.setTaskId(task.getTaskId());
			taskObj.setTask(task.getTask());
			taskObj.setParentTaskId(task.getParentTaskTbl().getParentTaskId());
			taskObj.setParentTask(task.getParentTaskTbl().getParentTask());
			taskObj.setPriority(task.getPriority());
			taskObj.setStartDate(task.getStartDate().toString());
			taskObj.setEndDate(task.getEndDate().toString());
		}		
		
		return new ResponseEntity<Task>(taskObj, HttpStatus.OK);
	}
}
