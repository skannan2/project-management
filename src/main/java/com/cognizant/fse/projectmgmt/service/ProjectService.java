package com.cognizant.fse.projectmgmt.service;

import com.cognizant.fse.projectmgmt.dao.ProjectDaoInterface;
import com.cognizant.fse.projectmgmt.model.ProjectTbl;
import com.cognizant.fse.projectmgmt.vo.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * Created by Sanjay on 10/2/2018.
 */
@Service
public class ProjectService {

	private ProjectDaoInterface projectDao;

	@Autowired
	public ProjectService(ProjectDaoInterface projectDao) {
		this.projectDao = projectDao;
	}


	@Transactional
	public String addUpdateProject(Project project) {
		long projectId = project.getProjectId();
		Optional<ProjectTbl> projectObj = null;
		ProjectTbl projectTbl = null;
		if (projectId > 0) {

			projectObj = projectDao.findById(projectId);
			projectTbl = projectObj.get();
			projectTbl.setProjectId(projectId);

		} else {
			projectTbl = new ProjectTbl();
		}

		projectTbl.setProjectName(project.getProjectName());
		projectTbl.setPriority(project.getPriority());

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate startdate = LocalDate.parse(project.getStartDate(), formatter);

		projectTbl.setStartDate(startdate);
		LocalDate enddateTime = LocalDate.parse(project.getEndDate(), formatter);
		projectTbl.setEndDate(enddateTime);

		projectDao.save(projectTbl);

		return "Successful";
	}


	@Transactional
	public List<ProjectTbl> getProject() {
		List<ProjectTbl> projectList = (List<ProjectTbl>) projectDao.findAll();

		return projectList;
	}

	@Transactional
	public ProjectTbl getProjectById(long projectId) {
		Optional<ProjectTbl> taskObj = projectDao.findById(projectId);
		ProjectTbl project = (ProjectTbl) taskObj.get();

		return project;
	}

	@Transactional
	public void deleteProject(long projectId) {

		projectDao.deleteById(projectId);
	}

	public List<ProjectTbl> findProject(String searchString) {
		List<ProjectTbl> projectList = projectDao.findProjectBySearchString(searchString);

		return projectList;
	}


}
