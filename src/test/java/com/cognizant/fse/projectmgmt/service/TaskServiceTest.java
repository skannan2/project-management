package com.cognizant.fse.projectmgmt.service;

import com.cognizant.fse.projectmgmt.dao.ParentTaskDaoInterface;
import com.cognizant.fse.projectmgmt.dao.ProjectDaoInterface;
import com.cognizant.fse.projectmgmt.dao.TaskDaoInterface;
import com.cognizant.fse.projectmgmt.dao.UserDaoInterface;
import com.cognizant.fse.projectmgmt.model.ProjectTbl;
import com.cognizant.fse.projectmgmt.model.TaskTbl;
import com.cognizant.fse.projectmgmt.model.UserTbl;
import com.cognizant.fse.projectmgmt.vo.Task;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
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


@RunWith(SpringRunner.class)
public class TaskServiceTest {
    @Autowired
    private TaskService taskService;

    @MockBean
    private TaskDaoInterface taskDaoInterface;

    @MockBean
    private ParentTaskDaoInterface parentTaskDao;

    @MockBean
    private ProjectDaoInterface projectDao;

    @MockBean
    private UserDaoInterface userDao;

    @TestConfiguration
    static class TaskServiceTestContextConfiguration {
        @Bean
        public TaskService taskService(TaskDaoInterface taskDaoInterface,
                                       ParentTaskDaoInterface parentTaskDao,
                                       ProjectDaoInterface projectDao,
                                       UserDaoInterface userDao) {
            return new TaskService(taskDaoInterface, parentTaskDao, projectDao, userDao);
        }
    }

    @Before
    public void setUp() {
/*        UserTbl user = new UserTbl();
        user.setUserId(1L);
        user.setFirstName("TestUserFirst1");
        user.setLastName("TestUserLast1");
        user.setUserName("370567");

        List<UserTbl> allUsers = Arrays.asList(user);

        Mockito.when(userDaoInterface.findAll()).thenReturn(allUsers);*/
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


}
