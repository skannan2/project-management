package com.cognizant.fse.projectmgmt.controller;

import com.cognizant.fse.projectmgmt.model.ProjectTbl;
import com.cognizant.fse.projectmgmt.service.ProjectService;
import com.cognizant.fse.projectmgmt.vo.Project;
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
public class ProjectController {

	private ProjectService projectService;

	@Autowired
	public void ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	@PostMapping(path = "/projects", consumes = "application/json")
	public ResponseEntity<String> addProject(@RequestBody Project project) {

		projectService.addUpdateProject(project);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@PutMapping(path = "/projects", consumes = "application/json")
	public ResponseEntity<String> updateProject(@RequestBody Project project) {

		projectService.addUpdateProject(project);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@DeleteMapping(path="/projects/{projectId}")
	public ResponseEntity<String> deleteProject(@PathVariable("projectId") int projectId) {

		projectService.deleteProject(projectId);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@GetMapping("/projects")
	public ResponseEntity<List> getProjects() {
		
		List<ProjectTbl> projectList = projectService.getProject();
		
		return new ResponseEntity<List>(projectList, HttpStatus.OK);
	}

	@GetMapping("/projects/search/{search}")
	public ResponseEntity<List> searchUser(@PathVariable("search") String searchString) {

		List<ProjectTbl> projectList = projectService.findProject(searchString);

		return new ResponseEntity<List>(projectList, HttpStatus.OK);
	}
}
