package com.cognizant.fse.projectmgmt.service;

import com.cognizant.fse.projectmgmt.dao.UserDaoInterface;
import com.cognizant.fse.projectmgmt.model.UserTbl;
import com.cognizant.fse.projectmgmt.vo.User;

import org.junit.Before;
import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class Test_UserService {

    @Mock
    private UserDaoInterface userDaoInterface;
    
	@InjectMocks
    private UserService userService;



//    @TestConfiguration
//    static class UserServiceTestContextConfiguration {
//        @Bean
//        public UserService userService(UserDaoInterface userDaoInterface) {
//            return new UserService(userDaoInterface);
//        }
//    }

    @Before
    public void setUp() {
    	MockitoAnnotations.initMocks(this);
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
    	
    	List<UserTbl> userListNotAvailable = new ArrayList<UserTbl>();

        Mockito.when(userDaoInterface.findAll()).thenReturn(userListNotAvailable);

        List<UserTbl> userList = userService.getUser();

        assertThat(userList).hasSize(0).isEmpty();
    }

    @Test
    public void testAddUser() {
        UserTbl user = new UserTbl();
        user.setUserId(1L);
        user.setFirstName("TestUserFirst1");
        user.setLastName("TestUserLast1");
        user.setUserName("370567");

        User newuser = new User();
        newuser.setUserId(1L);
        newuser.setFirstName("TestUserFirst1");
        newuser.setLastName("TestUserLast1");
        newuser.setUserName("370567");
        
        Mockito.when(userDaoInterface.save(user)).thenReturn(user);
        Mockito.when(userDaoInterface.findById(1L)).thenReturn(java.util.Optional.of(user));        

        User newuser1 = new User();
        newuser1.setUserId(1L);
        newuser1.setFirstName("TestUserFirst1");
        newuser1.setLastName("TestUserLast1");
        newuser1.setUserName("370567");

        String response = userService.addUpdateUser(newuser);        
       
        assertEquals(response,"Successful");
        //verify(userService, times(1)).addUpdateUser(newuser);

    }
    
    @Test
    public void testDeleteUser() {
    	long id = 1;
    	userService.deleteUser(id);
    	verify(userDaoInterface, times(1)).deleteById(1l);
    }

    @Test
    public void testFindUser() {
        UserTbl user = new UserTbl();
        user.setUserId(1L);
        user.setFirstName("TestUserFirst1");
        user.setLastName("TestUserLast1");
        user.setUserName("370567");
        
        List<UserTbl> allUsers = Arrays.asList(user);
        
        String searchString = "370567";
        Mockito.when(userDaoInterface.findUserBySearchString(searchString)).thenReturn(allUsers);
        
        List<UserTbl> userList = userService.findUser(searchString);
        assertThat(userList).hasSize(1).extracting(UserTbl::getFirstName).contains(user.getFirstName());
        
    }
    
    @Test
    public void testSortUser() {
        UserTbl user = new UserTbl();
        user.setUserId(1L);
        user.setFirstName("TestUserFirst1");
        user.setLastName("TestUserLast1");
        user.setUserName("370567");
        
        List<UserTbl> allUsers = Arrays.asList(user);
        
        String sortField = "firstName";
        Mockito.when(userDaoInterface.findAll(Sort.by(sortField))).thenReturn(allUsers);
        
        List<UserTbl> userList = userService.sortUser(sortField);
        assertThat(userList).hasSize(1).extracting(UserTbl::getFirstName).contains(user.getFirstName());    	
    }


}
