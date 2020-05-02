package com.cognizant.fse.projectmgmt.controller;

import com.cognizant.fse.projectmgmt.exception.AppException;
import com.cognizant.fse.projectmgmt.model.ProjectTbl;
import com.cognizant.fse.projectmgmt.model.UserTbl;
import com.cognizant.fse.projectmgmt.service.ProjectService;
import com.cognizant.fse.projectmgmt.service.TaskService;
import com.cognizant.fse.projectmgmt.vo.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Sanjay on 10/28/2018.
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/project-management")
public class ProjectController {

	private ProjectService projectService;
	private TaskService taskService;

	@Autowired
	public void ProjectController(ProjectService projectService,
								  TaskService taskService) {
		this.projectService = projectService;
		this.taskService = taskService;
	}
	
	@PostMapping(path = "/projects", consumes = "application/json")
	public ResponseEntity<String> addProject(@RequestBody Project project) {

		try {
			projectService.addUpdateProject(project);
		} catch (Exception e) {
			throw new AppException();
		}

		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@PutMapping(path = "/projects", consumes = "application/json")
	public ResponseEntity<String> updateProject(@RequestBody Project project) {
		try {
			projectService.addUpdateProject(project);
		} catch (Exception e) {
			throw new AppException();
		}

		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@DeleteMapping(path="/projects/{projectId}")
	public ResponseEntity<String> deleteProject(@PathVariable("projectId") int projectId) {
		try {
			projectService.deleteProject(projectId);
		} catch (Exception e) {
			throw new AppException();
		}

		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@GetMapping("/projects")
	public ResponseEntity<List> getProjects() {
		List<Project> projectObjList = null;
		try {
			List<ProjectTbl> projectList = projectService.getProject();
			projectObjList = transformData(projectList);
		} catch (Exception e) {
			throw new AppException();
		}

		return new ResponseEntity<List>(projectObjList, HttpStatus.OK);
	}

	@GetMapping("/projects/search/{search}")
	public ResponseEntity<List> searchProject(@PathVariable("search") String searchString) {
		List<Project> projectObjList = null;
		try {
			List<ProjectTbl> projectList = projectService.findProject(searchString);
			projectObjList = transformData(projectList);
		} catch (Exception e) {
			throw new AppException();
		}

		return new ResponseEntity<List>(projectObjList, HttpStatus.OK);
	}

	@GetMapping("/projects/sort/{sortString}")
	public ResponseEntity<List> sortProject(@PathVariable("sortString") String sortString) {
		List<Project> projectObjList = null;
		try {
			List<ProjectTbl> projectList = projectService.sortProject(sortString);
			projectObjList = transformData(projectList);
		} catch (Exception e) {
			throw new AppException();
		}

		return new ResponseEntity<List>(projectObjList, HttpStatus.OK);
	}

	private List<Project> transformData(List<ProjectTbl> projectTblList) {
		List<Project> projectObjList = new ArrayList<>();
		for (ProjectTbl projectTbl : projectTblList) {
			Project project = new Project();
			project.setProjectId(projectTbl.getProjectId());
			project.setProjectName(projectTbl.getProjectName());
			project.setPriority(projectTbl.getPriority());
			project.setStartDate(projectTbl.getStartDate().toString());
			project.setEndDate(projectTbl.getEndDate().toString());
			project.setManagerId(projectTbl.getUserTbl().getUserId());
			project.setManager(projectTbl.getUserTbl().getLastName()+","+projectTbl.getUserTbl().getFirstName());

			int taskCount = taskService.countTask(projectTbl.getProjectId());
			project.setTaskCount(taskCount);
			int taskCompleteCount = taskService.countCompleteTask("Complete");
			project.setCompleteCount(taskCompleteCount);

			projectObjList.add(project);
		}

		return projectObjList;
	}
}
