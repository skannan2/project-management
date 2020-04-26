package com.cognizant.fse.projectmgmt.service;

import com.cognizant.fse.projectmgmt.dao.ProjectDaoInterface;
import com.cognizant.fse.projectmgmt.dao.UserDaoInterface;
import com.cognizant.fse.projectmgmt.model.ProjectTbl;
import com.cognizant.fse.projectmgmt.model.UserTbl;
import com.cognizant.fse.projectmgmt.vo.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
	private UserDaoInterface userDao;

	@Autowired
	public ProjectService(ProjectDaoInterface projectDao,
						  UserDaoInterface userDao) {
		this.projectDao = projectDao;
		this.userDao = userDao;
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



		//Map the project to the user
		long userId = project.getManagerId();
		System.out.println("***Selected UserId**"+userId);
		Optional<UserTbl> userObj = userDao.findById(userId);
		UserTbl user = (UserTbl) userObj.get();
		//boolean foundUser = false;
		projectTbl.setUserTbl(user);

/*

		List<UserTbl> userTblList = projectTbl.getUserList();
		//for (UserTbl userInList: userTblList) {
		System.out.println("***User from DB***"+user.getUserId());
			if (userTblList.contains(user)){
				System.out.println("***Inside contains**"+userId);
				int index = userTblList.indexOf(user);
				userTblList.set(index,user);
				foundUser = true;
				//break;
			}
		//}
		System.out.println("***User from after DB***"+user.getUserId());

		if (!foundUser) {
			System.out.println("***Inside does not contains**"+userId);
			userTblList.add(user);
		}
*/

		//projectTbl.setUserList(userTblList);
		System.out.println("***Saving User Data**");
		projectDao.save(projectTbl);
		System.out.println("***Saved User Data**");

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

	@Transactional
	public List<ProjectTbl> sortProject(String sortField) {

		return (List<ProjectTbl>)projectDao.findAll(Sort.by(""+sortField+""));
	}



}
