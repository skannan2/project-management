package com.cognizant.fse.projectmgmt.controller;

import com.cognizant.fse.projectmgmt.dao.UserDaoInterface;
import com.cognizant.fse.projectmgmt.model.UserTbl;
import com.cognizant.fse.projectmgmt.service.UserService;
import com.cognizant.fse.projectmgmt.vo.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.BDDMockito.*;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class Test_UserController {

    private MockMvc mvc;
    @Mock
    private UserService userService;
    @InjectMocks
    private UserController userController;

    private JacksonTester<User> jsonUser;

    @Before
    public void setup() {
/*        MockitoAnnotations.initMocks(this);
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(userController)
                .build();*/
    }

    @Test
    public void testAddUser() throws Exception {
    /*
        User newuser = new User();
        newuser.setUserId(1L);
        newuser.setFirstName("TestUserFirst1");
        newuser.setLastName("TestUserLast1");
        newuser.setUserName("370567");

        given(userService.addUpdateUser(newuser))
                .willReturn("Successful");

        // when
        MockHttpServletResponse response = mvc.perform(post("/users/")
                        .contentType(MediaType.APPLICATION_JSON).content(
                        jsonUser.write(newuser).getJson()
                )).andReturn().getResponse();

    */
    }
}
