package com.cognizant.fse.projectmgmt.service;

import com.cognizant.fse.projectmgmt.dao.ParentTaskDaoInterface;
import com.cognizant.fse.projectmgmt.dao.ProjectDaoInterface;
import com.cognizant.fse.projectmgmt.dao.TaskDaoInterface;
import com.cognizant.fse.projectmgmt.dao.UserDaoInterface;
import com.cognizant.fse.projectmgmt.model.ParentTaskTbl;
import com.cognizant.fse.projectmgmt.model.ProjectTbl;
import com.cognizant.fse.projectmgmt.model.TaskTbl;
import com.cognizant.fse.projectmgmt.model.UserTbl;
import com.cognizant.fse.projectmgmt.vo.Task;
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
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class Test_TaskService {
    @Mock
    private TaskDaoInterface taskDaoInterface;

    @Mock
    private ParentTaskDaoInterface parentTaskDao;

    @Mock
    private ProjectDaoInterface projectDao;

    @Mock
    private UserDaoInterface userDao;

	@InjectMocks
    private TaskService taskService;
//    @TestConfiguration
//    static class TaskServiceTestContextConfiguration {
//        @Bean
//        public TaskService taskService(TaskDaoInterface taskDaoInterface,
//                                       ParentTaskDaoInterface parentTaskDao,
//                                       ProjectDaoInterface projectDao,
//                                       UserDaoInterface userDao) {
//            return new TaskService(taskDaoInterface, parentTaskDao, projectDao, userDao);
//        }
//    }

    @Before
    public void setUp() {
    	MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testValidGetTask() {
        TaskTbl task = new TaskTbl();
        task.setTaskId(1l);
        task.setTask("Task1");
        task.setPriority(1);
        task.setStartDate(LocalDate.now());
        task.setEndDate(LocalDate.now());
        task.setUserTbl(new UserTbl());
        task.setProjectTbl(new ProjectTbl());

        List<TaskTbl> allTasks = Arrays.asList(task);

        Mockito.when(taskDaoInterface.findAll()).thenReturn(allTasks);

        TaskTbl taskNew = new TaskTbl();
        taskNew.setTaskId(1l);
        taskNew.setTask("Task1");
        taskNew.setPriority(1);
        taskNew.setStartDate(LocalDate.now());
        taskNew.setEndDate(LocalDate.now());
        taskNew.setUserTbl(new UserTbl());
        taskNew.setProjectTbl(new ProjectTbl());

        List<TaskTbl> taskList = taskService.getTask();

        assertThat(taskList).hasSize(1).extracting(TaskTbl::getTask).contains(taskNew.getTask());
    }

    @Test
    public void testValidGetParentTask() {
    	ParentTaskTbl parentTaskTbl = new ParentTaskTbl();
    	parentTaskTbl.setParentTaskId(1l);
    	parentTaskTbl.setParentTask("Parent Task1");
    	
    	List<ParentTaskTbl> allParentTasks = Arrays.asList(parentTaskTbl);
    	
    	Mockito.when(parentTaskDao.findAll()).thenReturn(allParentTasks);
    	
        List<ParentTaskTbl> taskList = taskService.getParentTask();
        
    	ParentTaskTbl parentTaskTblNew = new ParentTaskTbl();
    	parentTaskTblNew.setParentTaskId(1l);
    	parentTaskTblNew.setParentTask("Parent Task1");

        assertThat(taskList).hasSize(1).extracting(ParentTaskTbl::getParentTask).contains(parentTaskTblNew.getParentTask());    	
    }
    
    @Test
    public void testValidGetTaskById() {
        TaskTbl task = new TaskTbl();
        task.setTaskId(1l);
        task.setTask("Task1");
        task.setPriority(1);
        task.setStartDate(LocalDate.now());
        task.setEndDate(LocalDate.now());
        task.setUserTbl(new UserTbl());
        task.setProjectTbl(new ProjectTbl());

        Mockito.when(taskDaoInterface.findById(1l)).thenReturn(java.util.Optional.of(task));

        TaskTbl taskNew = new TaskTbl();
        taskNew.setTaskId(1l);
        taskNew.setTask("Task1");
        taskNew.setPriority(1);
        taskNew.setStartDate(LocalDate.now());
        taskNew.setEndDate(LocalDate.now());
        taskNew.setUserTbl(new UserTbl());
        taskNew.setProjectTbl(new ProjectTbl());

        TaskTbl taskObj = taskService.getTaskById(1l);
        
        assertThat(taskObj).extracting(TaskTbl::getTask).contains(taskNew.getTask());
    }

    @Test
    public void testValidGetTaskByProjectId() {
        TaskTbl task = new TaskTbl();
        task.setTaskId(1l);
        task.setTask("Task1");
        task.setPriority(1);
        task.setStartDate(LocalDate.now());
        task.setEndDate(LocalDate.now());
        task.setUserTbl(new UserTbl());
        task.setProjectTbl(new ProjectTbl());

        List<TaskTbl> allTasks = Arrays.asList(task);
        
        Mockito.when(taskDaoInterface.findTaskByProjectId(1l)).thenReturn(allTasks);
        
        TaskTbl taskNew = new TaskTbl();
        taskNew.setTaskId(1l);
        taskNew.setTask("Task1");
        taskNew.setPriority(1);
        taskNew.setStartDate(LocalDate.now());
        taskNew.setEndDate(LocalDate.now());
        taskNew.setUserTbl(new UserTbl());
        taskNew.setProjectTbl(new ProjectTbl());

        List<TaskTbl> taskList = taskService.getTaskByProjectId(1l);

        assertThat(taskList).hasSize(1).extracting(TaskTbl::getTask).contains(taskNew.getTask());


    }

}
