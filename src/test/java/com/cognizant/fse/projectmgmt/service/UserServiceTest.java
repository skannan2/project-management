package com.cognizant.fse.projectmgmt.service;

import com.cognizant.fse.projectmgmt.dao.UserDaoInterface;
import com.cognizant.fse.projectmgmt.model.UserTbl;
import com.cognizant.fse.projectmgmt.vo.User;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserDaoInterface userDaoInterface;

    @TestConfiguration
    static class UserServiceTestContextConfiguration {
        @Bean
        public UserService userService(UserDaoInterface userDaoInterface) {
            return new UserService(userDaoInterface);
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
    public void testValidGetUser() {
        UserTbl user = new UserTbl();
        user.setUserId(1L);
        user.setFirstName("TestUserFirst1");
        user.setLastName("TestUserLast1");
        user.setUserName("370567");

        List<UserTbl> allUsers = Arrays.asList(user);

        Mockito.when(userDaoInterface.findAll()).thenReturn(allUsers);

        UserTbl newuser = new UserTbl();
        newuser.setUserId(1L);
        newuser.setFirstName("TestUserFirst1");
        newuser.setLastName("TestUserLast1");
        newuser.setUserName("370567");

        List<UserTbl> userList = userService.getUser();

        assertThat(userList).hasSize(1).extracting(UserTbl::getFirstName).contains(newuser.getFirstName());
    }

    @Test
    public void testValidGetUserById() {
        UserTbl user = new UserTbl();
        user.setUserId(1L);
        user.setFirstName("TestUserFirst1");
        user.setLastName("TestUserLast1");
        user.setUserName("370567");

        // List<UserTbl> allUsers = Arrays.asList(user);

        Mockito.when(userDaoInterface.findById(1L)).thenReturn(java.util.Optional.of(user));

        UserTbl newuser = new UserTbl();
        newuser.setUserId(1L);
        newuser.setFirstName("TestUserFirst1");
        newuser.setLastName("TestUserLast1");
        newuser.setUserName("370567");

        UserTbl userObj = userService.getUserById(1l);

        assertThat(userObj).extracting(UserTbl::getFirstName).contains(newuser.getFirstName());
    }


    @Test
    public void testNotFoundGetUser() {

        Mockito.when(userDaoInterface.findAll()).thenReturn(new ArrayList());

        List<UserTbl> userList = userService.getUser();

        assertThat(userList).hasSize(0).isEmpty();
    }

/*    @Test
    public void testAddUser() {
        UserTbl user = new UserTbl();
        user.setUserId(1L);
        user.setFirstName("TestUserFirst1");
        user.setLastName("TestUserLast1");
        user.setUserName("370567");


        Mockito.when(userDaoInterface.save(user)).thenReturn(user);

        User newuser = new User();
        newuser.setUserId(1L);
        newuser.setFirstName("TestUserFirst1");
        newuser.setLastName("TestUserLast1");
        newuser.setUserName("370567");

        verify(userService, times(1)).addUpdateUser(newuser);

    }*/




}
