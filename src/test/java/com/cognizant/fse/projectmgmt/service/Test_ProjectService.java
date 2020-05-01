package com.cognizant.fse.projectmgmt.service;

import com.cognizant.fse.projectmgmt.dao.ParentTaskDaoInterface;
import com.cognizant.fse.projectmgmt.dao.ProjectDaoInterface;
import com.cognizant.fse.projectmgmt.dao.TaskDaoInterface;
import com.cognizant.fse.projectmgmt.dao.UserDaoInterface;
import com.cognizant.fse.projectmgmt.model.ParentTaskTbl;
import com.cognizant.fse.projectmgmt.model.ProjectTbl;
import com.cognizant.fse.projectmgmt.model.TaskTbl;
import com.cognizant.fse.projectmgmt.model.UserTbl;
import com.cognizant.fse.projectmgmt.vo.Project;
import com.cognizant.fse.projectmgmt.vo.Task;
import com.cognizant.fse.projectmgmt.vo.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class Test_ProjectService {

    @Mock
    private ProjectDaoInterface projectDao;

    @Mock
    private UserDaoInterface userDao;

	@InjectMocks
    private ProjectService projectService;

    @Before
    public void setUp() {
    	MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testAddUser() {
        ProjectTbl project = new ProjectTbl();
        project.setProjectId(1l);
        project.setProjectName("Project1");
        project.setStartDate(LocalDate.now());
        project.setEndDate(LocalDate.now());
        
        Mockito.when(projectDao.save(project)).thenReturn(project);
        Mockito.when(projectDao.findById(1L)).thenReturn(java.util.Optional.of(project)); 
        
        
        Mockito.when(projectDao.findById(1L)).thenReturn(java.util.Optional.of(project));
        
        UserTbl user = new UserTbl();
        user.setUserId(1l);
        user.setFirstName("Sanjay");
        user.setLastName("Kannan");
        user.setUserName("370567");
        
        Mockito.when(userDao.findById(1L)).thenReturn(java.util.Optional.of(user));

        Project newproject = new Project();
        newproject.setProjectId(1l);
        newproject.setProjectName("Project1");
        newproject.setStartDate("04/12/2020");
        newproject.setEndDate("04/27/2020");
        newproject.setManagerId(1l);
        
        String response = projectService.addUpdateProject(newproject);        
       
        assertEquals(response,"Successful");        

    }

    @Test
    public void testValidGetProject() {
        ProjectTbl project = new ProjectTbl();
        project.setProjectId(1l);
        project.setProjectName("Project1");
        project.setStartDate(LocalDate.now());
        project.setEndDate(LocalDate.now());

        List<ProjectTbl> allProjects = Arrays.asList(project);

        Mockito.when(projectDao.findAll()).thenReturn(allProjects);

        ProjectTbl newproject = new ProjectTbl();
        newproject.setProjectId(1l);
        newproject.setProjectName("Project1");
        newproject.setStartDate(LocalDate.now());
        newproject.setEndDate(LocalDate.now());
        
        List<ProjectTbl> projectList = projectService.getProject();

        assertThat(projectList).hasSize(1).extracting(ProjectTbl::getProjectName).contains(newproject.getProjectName());
    }

    
    @Test
    public void testValidGetProjectById() {
        ProjectTbl project = new ProjectTbl();
        project.setProjectId(1l);
        project.setProjectName("Project1");
        project.setStartDate(LocalDate.now());
        project.setEndDate(LocalDate.now());

        Mockito.when(projectDao.findById(1l)).thenReturn(java.util.Optional.of(project));

        ProjectTbl newproject = new ProjectTbl();
        newproject.setProjectId(1l);
        newproject.setProjectName("Project1");
        newproject.setStartDate(LocalDate.now());
        newproject.setEndDate(LocalDate.now());

        ProjectTbl projectObj = projectService.getProjectById(1l);
        
        assertThat(projectObj).extracting(ProjectTbl::getProjectName).contains(newproject.getProjectName());
    }


    
    @Test
    public void testDeleteProject() {
    	long id = 1;
    	projectService.deleteProject(id);
    	verify(projectDao, times(1)).deleteById(1l);
    }

    
    @Test
    public void testSortProject() {
        ProjectTbl project = new ProjectTbl();
        project.setProjectId(1l);
        project.setProjectName("Project1");
        project.setStartDate(LocalDate.now());
        project.setEndDate(LocalDate.now()); 
        
        List<ProjectTbl> allProjects = Arrays.asList(project);
        
        String sortField = "Priority";
        Mockito.when(projectDao.findAll(Sort.by(sortField))).thenReturn(allProjects);
        
        List<ProjectTbl> taskList = projectService.sortProject(sortField);
        assertThat(taskList).hasSize(1).extracting(ProjectTbl::getPriority).contains(project.getPriority());    	
    }
    

}
