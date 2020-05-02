package com.cognizant.fse.projectmgmt.controller;

import com.cognizant.fse.projectmgmt.exception.AppException;
import com.cognizant.fse.projectmgmt.exception.ParentTaskNotFoundException;
import com.cognizant.fse.projectmgmt.exception.UserNotFoundException;
import com.cognizant.fse.projectmgmt.model.ParentTaskTbl;
import com.cognizant.fse.projectmgmt.model.ProjectTbl;
import com.cognizant.fse.projectmgmt.model.TaskTbl;
import com.cognizant.fse.projectmgmt.service.TaskService;
import com.cognizant.fse.projectmgmt.vo.Project;
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

        try {
            taskService.addUpdateTask(task);
        } catch (Exception e) {
            throw new AppException();
        }

        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @PutMapping("/tasks")
    public ResponseEntity<String> updateTask(@RequestBody Task task) {

        try {
            taskService.addUpdateTask(task);
        } catch (Exception e) {
            throw new AppException();
        }

        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/tasks/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable("taskId") int taskId) {

        try {
            taskService.deleteTask(taskId);
        } catch (Exception e) {
            throw new AppException();
        }

        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List> getAllTask() {
        List<TaskTbl> taskList = null;
        try {
            taskList = taskService.getTask();
        } catch (Exception e) {
            throw new AppException();
        }

        return new ResponseEntity<List>(taskList, HttpStatus.OK);
    }

    @GetMapping("/tasks/parenttasks")
    public ResponseEntity<List> getAllParentTask() {
        List<ParentTaskTbl> parentTaskList = null;

        try {
            parentTaskList = taskService.getParentTask();

            if (parentTaskList.size() == 0) {
                throw new ParentTaskNotFoundException();
            }
        } catch (Exception e) {
            throw new AppException();
        }

        return new ResponseEntity<List>(parentTaskList, HttpStatus.OK);
    }

    @GetMapping("/tasks/count/{projectId}")
    public ResponseEntity<Integer> getTaskCount(@PathVariable("projectId") long projectId) {
        int taskCount = 0;
        try {
            taskCount = taskService.countTask(projectId);
        } catch (Exception e) {
            throw new AppException();
        }

        return new ResponseEntity<Integer>(Integer.valueOf(taskCount), HttpStatus.OK);
    }

    @GetMapping("/tasks/complete")
    public ResponseEntity<Integer> getCompleteTaskCount() {
        int taskCount = 0;

        try {
            taskCount = taskService.countCompleteTask("Complete");
        } catch (Exception e) {
            throw new AppException();
        }

        return new ResponseEntity<Integer>(Integer.valueOf(taskCount), HttpStatus.OK);
    }

    @GetMapping("/tasks/sort/{sortString}")
    public ResponseEntity<List> sortTask(@PathVariable("sortString") String sortString) {
        List<TaskTbl> taskList = null;

        try {
            taskList = taskService.sortTask(sortString);
        } catch (Exception e) {
            throw new AppException();
        }

        return new ResponseEntity<List>(taskList, HttpStatus.OK);
    }

    @GetMapping("/tasks/{taskId}")
    public ResponseEntity<Task> getTask(@PathVariable("taskId") int taskId) {
        TaskTbl task = null;
        try {
            task = taskService.getTaskById(taskId);
        } catch (Exception e) {
            throw new AppException();
        }

        Task taskObj = new Task();
        if (task != null) {
            taskObj.setTaskId(task.getTaskId());
            taskObj.setTask(task.getTask());
            taskObj.setParentTaskId(task.getParentTaskTbl().getParentTaskId());
            taskObj.setParentTask(task.getParentTaskTbl().getParentTask());
            taskObj.setPriority(task.getPriority());
            taskObj.setStartDate(task.getStartDate().toString());
            taskObj.setEndDate(task.getEndDate().toString());
            taskObj.setManager(task.getUserTbl().getLastName() + "," + task.getUserTbl().getFirstName());
            taskObj.setManagerId(task.getUserTbl().getUserId());
            taskObj.setProject(task.getProjectTbl().getProjectName());
            taskObj.setProjectId(task.getProjectTbl().getProjectId());
        }

        return new ResponseEntity<Task>(taskObj, HttpStatus.OK);
    }

    @GetMapping("/tasks/project/{projectId}")
    public ResponseEntity<List> getTaskByProjectId(@PathVariable("projectId") long projectId) {
        List<TaskTbl> taskList = null;

        try {
            taskList = taskService.getTaskByProjectId(projectId);
        } catch (Exception e) {
            throw new AppException();
        }

        return new ResponseEntity<List>(taskList, HttpStatus.OK);
    }
}
